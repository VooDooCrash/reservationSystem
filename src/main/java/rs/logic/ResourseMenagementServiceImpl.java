package rs.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.model.Occurrence;
import rs.model.OccurrenceDao;
import rs.model.Reservation;
import rs.model.Reservation.Repeat;
import rs.model.ReservationDao;
import rs.model.Resource;
import rs.model.ResourceDao;
import rs.model.User;

@Service
public class ResourseMenagementServiceImpl implements ResourseManagementService {

	@Autowired
	ResourceDao resourceDao;

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	OccurrenceDao occurrenceDao;

	@Autowired
	UserManagementService userMS;

	@Override
	public List<Resource> getResources() {

		return resourceDao.list();
	}

	@Override
	public Resource getResource(Long id) {

		return resourceDao.get(id);
	}

	@Override
	public Resource getResourceByName(String name) {

		return resourceDao.findByName(name);
	}

	@Override
	public void createResource(Resource r) {

		resourceDao.create(r);
	}

	@Override
	public void updateResource(Resource r) {

		resourceDao.update(r);
	}

	@Override
	public boolean removeResource(Long resourceId) {

		Resource resource = resourceDao.get(resourceId);
		resource.getReservations();

		List<Reservation> reservations = resource.getReservations();

		if (reservations == null || reservations.isEmpty()) {
			resourceDao.delete(resourceId);
			return true;
		}

		return false;
	}

	@Override
	public Reservation getReservation(Long id) {
		return reservationDao.get(id);
	}

	// @Override
	// public List<Reservation> getReservations(Long resourceId) {
	//
	// Resource res = resourceDao.get(resourceId);
	// return res.getReservations();
	// }

	@Override
	public List<Reservation> getReservations(Date from, Date to, Long resourceId) {

		List<Reservation> reservationsList = reservationDao.listByDate(from,
				to, resourceId);

		List<Reservation> repeatingReservations = reservationDao
				.listRepeatingReservations(resourceId);

		List<Reservation> occurrences = new ArrayList<>();

		for (Reservation r : repeatingReservations) {
			switch (r.getRepeat()) {
			case DAILY:
				occurrences.addAll(generateReservations(r, from, to,
						Reservation.Repeat.DAILY));
				break;

			case WEEKLY:
				occurrences.addAll(generateReservations(r, from, to,
						Reservation.Repeat.WEEKLY));
				break;

			case MONTHLY:
				occurrences.addAll(generateReservations(r, from, to,
						Reservation.Repeat.MONTHLY));
				break;

			case YEARLY:
				occurrences.addAll(generateReservations(r, from, to,
						Reservation.Repeat.YEARLY));
				break;

			default:
				break;
			}
		}
		reservationsList.addAll(occurrences);
		return reservationsList;
	}

	@Override
	public List<Reservation> getReservationsByUser(Long userId, Date from,
			Date to) {

		List<Reservation> reservationsList = reservationDao.findByUser(userId);

		List<Reservation> occurrences = new ArrayList<>();

		for (Reservation r : reservationsList) {
			if (r.getRepeat() != Reservation.Repeat.NONE) {
				switch (r.getRepeat()) {
				case DAILY:
					occurrences.addAll(generateReservations(r, from, to,
							Reservation.Repeat.DAILY));
					break;

				case WEEKLY:
					occurrences.addAll(generateReservations(r, from, to,
							Reservation.Repeat.WEEKLY));
					break;

				case MONTHLY:
					occurrences.addAll(generateReservations(r, from, to,
							Reservation.Repeat.MONTHLY));
					break;

				case YEARLY:
					occurrences.addAll(generateReservations(r, from, to,
							Reservation.Repeat.YEARLY));
					break;

				default:
					break;
				}
			}
		}
		reservationsList.addAll(occurrences);

		return reservationsList;
	}

	@Override
	public void addReservation(Reservation reservation, Long resourceId) {

		Resource resource = resourceDao.get(resourceId);

		reservationDao.create(reservation);
		resourceDao.addReservation(reservation, resource);
	}

	@Override
	public void updateReservation(Reservation r) {

		reservationDao.update(r);
	}

	@Override
	public void removeReservation(Long reservationId, Long resourceId) {

		Resource resource = resourceDao.get(resourceId);
		resourceDao.removeReservation(resource, reservationId);
		reservationDao.delete(reservationId);
	}

	@Override
	public Long getReservationTime(Reservation reservation) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(reservation.getStartTime());

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(reservation.getEndTime());
		Long startTimeMs = calStart.getTimeInMillis();
		Long endTimeMs = calEnd.getTimeInMillis();
		Long timeDifInMilliSec;

		if (startTimeMs >= endTimeMs) {
			timeDifInMilliSec = startTimeMs - endTimeMs;
		} else {
			timeDifInMilliSec = endTimeMs - startTimeMs;
		}

		return (timeDifInMilliSec / (60 * 60 * 1000));
	}

	@Override
	public boolean isAvailable(Date startTime, Date endTime,
			Long excludeReservationId, Long resourseId) {

		List<Reservation> reservationsList = getReservations(startTime,
				endTime, resourseId);
		Long startInMin = startTime.getTime() / (60 * 1000);
		Long endInMin = endTime.getTime() / (60 * 1000);

		for (Reservation r : reservationsList) {

			if (r.getId().equals(excludeReservationId)) {
				continue;
			}

			Long resStartTimeInMin = r.getStartTime().getTime() / (60 * 1000);
			Long resEndTimeInMin = r.getEndTime().getTime() / (60 * 1000);

			// |--------| startInMin-----endInMin
			// |--------| resStartTimeInMin-----resEndTimeInMin

			if (resStartTimeInMin < startInMin && resEndTimeInMin > startInMin) {
				return false;
			}

			// |--------| startInMin-----endInMin
			// |--------| resStartTimeInMin-----resEndTimeInMin

			if (resStartTimeInMin < endInMin && resEndTimeInMin > endInMin) {
				return false;
			}

			// |--------------| startInMin-----endInMin
			// |--------| resStartTimeInMin-----resEndTimeInMin

			if (resStartTimeInMin > startInMin && resEndTimeInMin < endInMin) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void addCompanion(Long companionId, Long reservationId) {

		Reservation reservation = getReservation(reservationId);
		User companion = userMS.getUser(companionId);
		List<User> companionsList = reservation.getCompanions();
		companionsList.add(companion);
	}

	@Override
	public void removeCompanion(Long companionId, Long reservationId) {

		Reservation reservation = reservationDao.get(reservationId);
		List<User> companions = reservation.getCompanions();
		Iterator<User> it = companions.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.getId() == companionId) {
				it.remove();
			}
		}
	}

	@Override
	public Occurrence getSpecialOccurrance(Long reservationId,
			Long occurrenceIndex) {

		Reservation reservation = getReservation(reservationId);
		List<Occurrence> occList = reservation.getSpecialOccurrances();

		for (Occurrence occ : occList) {
			if (occ.getIndex().equals(occurrenceIndex)) {
				return occ;
			}
		}
		return null;
	}

	@Override
	public Reservation getOccurrance(Long reservationId, Long occurrenceIndex) {

		Date endDate = new Date();
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		endCal.add(Calendar.MONTH, 1);
		Reservation reservation = getReservation(reservationId);
		List<Reservation> reservationsList;

		// ------------ Searching in Special Occurrences first ----------

		for (Occurrence occ : reservation.getSpecialOccurrances()) {
			if (occ.getIndex().equals(occurrenceIndex)) {
				Reservation res = new Reservation();
				res.setDescription(occ.getDescription());
				res.setStartTime(occ.getStartTime());
				res.setEndTime(occ.getEndTime());

				// TODO Add other properties
			}
		}

		// ------------ Then in generated occurrences ----------

		while (endCal.get(Calendar.YEAR) < 2500) {

			reservationsList = generateReservations(reservation,
					reservation.getStartTime(), endCal.getTime(),
					reservation.getRepeat());

			for (Reservation res : reservationsList) {
				if (res.getOccurrenceIndex().equals(occurrenceIndex)) {
					return res;
				}
			}
			endCal.add(Calendar.MONTH, 1);
		}
		return null;
	}

	@Override
	public void addSpecialOccurrance(Occurrence occ) {

		List<Occurrence> occList = occ.getReservation().getSpecialOccurrances();
		boolean existingFlag = false;

		for (Occurrence o : occList) {
			if (o.getIndex().equals(occ.getIndex())) {
				o.setDescription(occ.getDescription());
				o.setCanceled(occ.getCanceled());
				o.setStartTime(occ.getStartTime());
				o.setEndTime(occ.getEndTime());
				existingFlag = true;
			}
		}

		if (!existingFlag) {
			occurrenceDao.create(occ);
			occList.add(occ);
		}

		reservationDao.update(occ.getReservation());
	}

	@Override
	public Long getTimeToNextReservation(Long userId) {

		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MONTH, 1);
		List<Reservation> reservations = getReservationsByUser(userId,
				currentDate, cal.getTime());
		Long currentTime = System.currentTimeMillis();

		if (reservations != null && !reservations.isEmpty()) {

			Long minTime = reservations.get(0).getStartTime().getTime();
			for (Reservation res : reservations) {
				Long time = res.getStartTime().getTime() - currentTime;
				if (time < minTime
						&& res.getStartTime().getTime() > currentTime) {
					minTime = time;
				}
			}
			return minTime;
		} else {

			return null;
		}
	}

	@Override
	public Reservation getNextReservation(Long userId) {

		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MONTH, 1);
		List<Reservation> reservations = getReservationsByUser(userId,
				currentDate, cal.getTime());
		Long currentTime = System.currentTimeMillis();

		if (reservations != null && !reservations.isEmpty()) {

			Long minTime = reservations.get(0).getStartTime().getTime();
			Reservation nextReservation = null;
			for (Reservation res : reservations) {
				Long time = res.getStartTime().getTime() - currentTime;
				if (time < minTime
						&& res.getStartTime().getTime() > currentTime) {
					minTime = time;
					nextReservation = res;
				}
			}
			return nextReservation;
		} else {

			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadResourcesData(String fileName) {

		List<Resource> resourcesList = new ArrayList<>();

		try {
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			resourcesList = (List<Resource>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resourcesList == null) {
			resourceDao.setList(new ArrayList<Resource>());
		} else {
			resourceDao.setList(resourcesList);
		}

		// updateReferences(resourcesList);

	}

	@Override
	public void saveResourcesData(String fileName) {

		List<Resource> resoursesList = resourceDao.list();

		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(resoursesList);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	private List<Reservation> generateReservations(Reservation r, Date from,
			Date to, Repeat repeat) {

		List<Reservation> reservationsList = new ArrayList<>();
		Calendar resStartCal = Calendar.getInstance();
		Calendar resEndCal = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		calTo.setTime(to);
		resStartCal.setTime(r.getStartTime());
		resEndCal.setTime(r.getEndTime());

		if (repeat.equals(Reservation.Repeat.DAILY)) {

			Long occurrenceIndex = 1L;
			// ---------- This excludes the day of the original event ------
			resStartCal.add(Calendar.DAY_OF_YEAR, 1);
			resEndCal.add(Calendar.DAY_OF_YEAR, 1);

			// ---------- Generate occurrences -----------------------------

			while (resStartCal.compareTo(calTo) <= 0) {
				Date resStartTime = resStartCal.getTime();
				Date resEndTime = resEndCal.getTime();
				
				if (resStartTime.getTime() > from.getTime()
						|| (resStartTime.getTime() < from.getTime()
							&& resEndTime.getTime() > from.getTime() )) {
					
					addOccurrence(r, occurrenceIndex, resStartCal.getTime(),
							resEndCal.getTime(), reservationsList);
				}
				occurrenceIndex++;
				resStartCal.add(Calendar.DAY_OF_YEAR, 1);
				resEndCal.add(Calendar.DAY_OF_YEAR, 1);
			}
		} else if (repeat.equals(Reservation.Repeat.WEEKLY)) {

			Long occurrenceIndex = 1L;
			// ---------- This excludes the week of the original event ------
			resStartCal.add(Calendar.WEEK_OF_YEAR, 1);
			resEndCal.add(Calendar.WEEK_OF_YEAR, 1);

			// ---------- Generate occurrences -----------------------------
			while (resStartCal.compareTo(calTo) <= 0) {

				if (resStartCal.getTime().compareTo(from) >= 0) {
					addOccurrence(r, occurrenceIndex, resStartCal.getTime(),
							resEndCal.getTime(), reservationsList);
				}
				occurrenceIndex++;
				resStartCal.add(Calendar.WEEK_OF_YEAR, 1);
				resEndCal.add(Calendar.WEEK_OF_YEAR, 1);
			}
		} else if (repeat.equals(Reservation.Repeat.MONTHLY)) {

			Long occurrenceIndex = 1L;
			// ---------- This excludes the month of the original event ------
			resStartCal.add(Calendar.MONTH, 1);
			resEndCal.add(Calendar.MONTH, 1);

			// ---------- Generate occurrences -----------------------------
			while (resStartCal.compareTo(calTo) <= 0) {

				if (resStartCal.getTime().compareTo(from) >= 0) {
					addOccurrence(r, occurrenceIndex, resStartCal.getTime(),
							resEndCal.getTime(), reservationsList);
				}
				occurrenceIndex++;
				resStartCal.add(Calendar.MONTH, 1);
				resEndCal.add(Calendar.MONTH, 1);
			}
		} else if (repeat.equals(Reservation.Repeat.YEARLY)) {

			Long occurrenceIndex = 1L;
			// ---------- This excludes the year of the original event ------
			resStartCal.add(Calendar.YEAR, 1);
			resEndCal.add(Calendar.YEAR, 1);

			// ---------- Generate occurrences -----------------------------
			while (resStartCal.compareTo(calTo) <= 0) {

				if (resStartCal.getTime().compareTo(from) >= 0) {
					addOccurrence(r, occurrenceIndex, resStartCal.getTime(),
							resEndCal.getTime(), reservationsList);
				}
				occurrenceIndex++;
				resStartCal.add(Calendar.YEAR, 1);
				resEndCal.add(Calendar.YEAR, 1);
			}
		}
		return reservationsList;
	}

	private void addOccurrence(Reservation r, Long occurrenceIndex, Date start,
			Date end, List<Reservation> reservationsList) {

		List<Occurrence> specialOccurrances = r.getSpecialOccurrances();
		Boolean canceledFlag = false;
		String description = null;
		Date startTime = null;
		Date endTime = null;

		for (Occurrence occ : specialOccurrances) {
			if (occ.getIndex().equals(occurrenceIndex)) {
				if (Boolean.TRUE.equals(occ.getCanceled())) {
					canceledFlag = true;
				}
				description = occ.getDescription();
				startTime = occ.getStartTime();
				endTime = occ.getEndTime();
			}
		}

		if (!canceledFlag) {
			Reservation generatedReservation = new Reservation(r);
			generatedReservation.setOriginReservation(r);
			generatedReservation.setOccurrenceIndex(occurrenceIndex);
			generatedReservation.setDescription(description);

			if (startTime != null && endTime != null) {
				generatedReservation.setStartTime(startTime);
				generatedReservation.setEndTime(endTime);
			} else {
				generatedReservation.setStartTime(start);
				generatedReservation.setEndTime(end);
			}

			reservationsList.add(generatedReservation);
		}
	}
}
