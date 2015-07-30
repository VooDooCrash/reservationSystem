package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.logic.UserManagementService;
import rs.model.Reservation;
import rs.model.Reservation.Repeat;
import rs.model.Resource;
import rs.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ReservationController {

	@Autowired
	ResourseManagementService resMS;

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = Logger.getLogger(ReservationController.class.getName());

	// private static final int DEFAULT_MAXIMUM_RESERVATION_HOURS = 10;

	@RequestMapping(value = "/reservation", method = RequestMethod.PUT)
	public String addReservation( @ModelAttribute("reservationForm") ReservationForm reservationForm ) throws Exception {

		LOGGER.info("In AddReservationAction");

		ReservationForm form =  reservationForm;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Reservation reservation = new Reservation();

		Long resourceId = form.getResourceId();
		User u = userMS.getUser(form.getUserId());
		String[] companionsIds = form.getCompanions();
		String description = form.getDescription();
		Boolean allDay = form.getAllDay();
		Boolean reminder = form.isReminder();
		Integer reminderTime = form.getReminderTime();
		List<User> companionsList = new ArrayList<>();
		Date currentTime = new Date();

		reservation.setUser(u);
		reservation.setDescription(description);
		
		if (reminder.equals(true)) { 
			reservation.setReminder(true);
			reservation.setReminderTime(reminderTime);
		} else {
			reservation.setReminder(false);
			reservation.setReminderTime(null);
		}

		Repeat repeat = Repeat.values()[form.getRepeat()];
		reservation.setRepeat(repeat);

		// ---------------- Get and parse dates ------------

		Date startTime;
		Date endTime;

		if (Boolean.TRUE.equals(allDay)) {
			startTime = sdf.parse(form.getStartTime());
			Calendar cal = Calendar.getInstance();
			reservation.setAllDay(true);
			cal.setTime(startTime);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 1);
			startTime = cal.getTime();
			cal.add(Calendar.DAY_OF_YEAR, 1);
			cal.add(Calendar.SECOND, -2);
			endTime = cal.getTime();
		} else {
			reservation.setAllDay(false);
			startTime = sdf.parse(form.getStartTime());
			endTime = sdf.parse(form.getEndTime());
		}
		reservation.setStartTime(startTime);
		reservation.setEndTime(endTime);

		// ----------------- Set companions ----------------

		if (companionsIds != null) {
			for (String id : companionsIds) {
				User companion = userMS.getUser(Long.parseLong(id));
				companionsList.add(companion);
			}
		}

		reservation.setCompanions(companionsList);

		Resource resource = resMS.getResource(resourceId);

		reservation.setResource(resource);

		// ---------------- Business logic validation -------

//		ActionErrors errors = form.validate(mapping, request);
//		saveErrors(request, errors);

		// if (resMS.getReservationTime(reservation) >
		// DEFAULT_MAXIMUM_RESERVATION_HOURS) {
		//
		// errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		// "errors.reservation.toolong"));
		// saveErrors(request, errors);
		//
		// }
		if (!resMS.isAvailable(startTime, endTime, 0L, resourceId)) {

//			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"errors.reservation.unavailable"));
//			saveErrors(request, errors);

		}
		if (startTime.compareTo(currentTime) < 0) {
//			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"errors.reservation.past"));
//			saveErrors(request, errors);
		}

//		if (errors.isEmpty()) {

			resMS.addReservation(reservation, resourceId);
//			ActionRedirect redirect = new ActionRedirect(
//					mapping.findForward(AF_SUCESS));
//			redirect.addParameter("id", resourceId);

			return "reservations";

//		} else {
//
//			return new ActionForward("/reservations.do?id="
//					+ resourceId);
//		}
	}

	@RequestMapping(value = "/reservation/{reservationID}", method = RequestMethod.GET)
	public String getReservation( @PathVariable("reservationID") Long reservationID, Model model ) throws Exception {

		LOGGER.info("In ShowReservationDetails");

//		ReservationForm form = (ReservationForm) reservationForm;
		Reservation reservation = resMS.getReservation(reservationID);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

//		form.setResourceName(reservation.getResource().getName());
//		form.setResourceId(reservation.getResource().getId());
//		form.setCompanionsList(reservation.getCompanions());
//		form.setUser(reservation.getUser());
//		form.setStartTime(sdf.format(reservation.getStartTime()));
//		form.setEndTime(sdf.format(reservation.getEndTime()));
//		form.setRepeat(reservation.getRepeat().getValue());
//		form.setReminder(reservation.isReminder());
//		form.setReminderTime(reservation.getReminderTime());
//		form.setDescription(reservation.getDescription());

		model.addAttribute("reservation", reservation);
		model.addAttribute("action", "edit");

		//------ Pass only users available for selecting for companions -----
		List<User> usersForSelect = userMS.getUsers();
		usersForSelect = filterUsers(usersForSelect, reservation);
		model.addAttribute("usersList", usersForSelect);

		return "reservation-details";
	}

	private List<User> filterUsers(List<User> usersForSelect,
								   Reservation reservation) {
		List<User> newList = new ArrayList<User>();
		for (User u : usersForSelect) {
			if (!reservation.getCompanions().contains(u)) {
				newList.add(u);
			}
		}
		return newList;
	}

	@RequestMapping(value = "/reservation/{reservationID}", method = RequestMethod.POST)
	public String editReservation( @PathVariable("reservationID") Long reservationID,
						   @ModelAttribute("reservationForm") ReservationForm reservationForm ) throws Exception {

		LOGGER.info("In EditReservationAction");

		ReservationForm form = (ReservationForm) reservationForm;

		long reservationId = form.getId();
		Repeat repeat = Repeat.values()[form.getRepeat()];
		User user = userMS.getUser(form.getUserId());
		String description = form.getDescription();
		Boolean allDay = form.getAllDay();
		Integer reminderTime = form.getReminderTime();
		Boolean reminder = form.isReminder();

		Reservation reservation = resMS.getReservation(reservationId);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		ActionErrors errors = form.validate(mapping, request);

		Date currentTime = new Date();
		Date startTime;
		Date endTime;

		// -------------- Parse and update Times ------------

		if (Boolean.TRUE.equals(allDay)) {
			startTime = sdf.parse(form.getStartTime());
			Calendar cal = Calendar.getInstance();
			reservation.setAllDay(true);
			cal.setTime(startTime);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 1);
			startTime = cal.getTime();
			cal.add(Calendar.DAY_OF_YEAR, 1);
			cal.add(Calendar.SECOND, -2);
			endTime = cal.getTime();
		} else {
			reservation.setAllDay(false);
			startTime = sdf.parse(form.getStartTime());
			endTime = sdf.parse(form.getEndTime());
		}
		reservation.setStartTime(startTime);
		reservation.setEndTime(endTime);

		// ---------------- Business logic validation -------

		long resourceId = reservation.getResource().getId();

//		if (!resMS.isAvailable(startTime, endTime, reservationId, resourceId)) {
//			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"errors.reservation.unavailable"));
//			saveErrors(request, errors);
//		}
//		if (startTime.compareTo(currentTime) < 0) {
//			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"errors.reservation.past"));
//			saveErrors(request, errors);
//		}

//		if (getErrors(request).isEmpty()) {

		reservation.setUser(user);
		reservation.setStartTime(startTime);
		reservation.setEndTime(endTime);
		reservation.setRepeat(repeat);
		reservation.setDescription(description);

		if (reminder.equals(true)) {
			reservation.setReminder(true);
			reservation.setReminderTime(reminderTime);
		} else {
			reservation.setReminder(false);
			reservation.setReminderTime(null);
		}

		resMS.updateReservation(reservation);

//			ActionRedirect redirect = new ActionRedirect(
//					mapping.findForward(AF_SUCESS));
//			redirect.addParameter("id", reservationId);

		return "redirect:/reservation/" + reservationID;

//		} else {
//			return mapping.findForward(AF_SUCESS);
//		}
	}

	@RequestMapping(value = "/reservation/{reservationID}", method = RequestMethod.DELETE)
	public String deleteReservation(@PathVariable("reservationID") Long reservationID) throws Exception {

		LOGGER.info("In RemoveReservationAction");

//		ReservationForm form = (ReservationForm) reservationForm;
//		Long reservationId = form.getId();
		Reservation res = resMS.getReservation(reservationID);
		Long resourceId = res.getResource().getId();

		resMS.removeReservation(reservationID, resourceId);

//		ActionRedirect redirect = new ActionRedirect(
//				mapping.findForward(AF_SUCESS));
//		redirect.addParameter("id", resourceId);

		return "redirect:/reservations";
	}
}
