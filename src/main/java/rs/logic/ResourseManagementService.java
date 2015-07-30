package rs.logic;

import java.util.Date;
import java.util.List;

import rs.model.Occurrence;
import rs.model.Reservation;
import rs.model.ReservationDao;
import rs.model.Resource;
import rs.model.ResourceDao;

public interface ResourseManagementService {

	public List<Resource> getResources();

	public Resource getResource(Long id);

	public Resource getResourceByName(String name);

	public void createResource(Resource r);

	public void updateResource(Resource r);

	public boolean removeResource(Long resourceId);

	public Reservation getReservation(Long id);

	public List<Reservation> getReservationsByUser(Long id, Date from, Date to);

	public void addReservation(Reservation reservation, Long resourseId);

	public void updateReservation(Reservation r);

	public void removeReservation(Long reservationId, Long resourceId);

	public List<Reservation> getReservations(Date from, Date to, Long resourceId);

	public Long getReservationTime(Reservation reservation);

	public boolean isAvailable(Date startTime, Date endTime,
			Long excludeReservationId, Long resourseId);

	public void addCompanion(Long companionId, Long reservationId);

	public void removeCompanion(Long companionId, Long reservationId);

	public void addSpecialOccurrance(Occurrence occ);

	public void saveResourcesData(String fileName);

	public void loadResourcesData(String fileName);

	public void setResourceDao(ResourceDao resourceDao);

	public void setReservationDao(ReservationDao reservationDao);

	public Occurrence getSpecialOccurrance(Long reservationId, Long occurrenceIndex);

	public Reservation getOccurrance(Long reservationId, Long occurrenceIndex);

	public Long getTimeToNextReservation(Long userId);

	public Reservation getNextReservation(Long userId);

}