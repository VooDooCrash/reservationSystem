/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.logic.UserManagementService;
import rs.model.Reservation;
import rs.model.Reservation.Repeat;
import rs.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class EditReservationController {

	@Autowired
	ResourseManagementService resMS;

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EditReservationController.class);

	@RequestMapping(value = "/reservation/{reservationID}", method = RequestMethod.POST)
	public String execute( @PathVariable("reservationID") Long reservationID,
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
}
*/
