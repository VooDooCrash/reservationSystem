/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.logic.UserManagementService;
import rs.model.Reservation;
import rs.model.Resource;
import rs.model.User;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class ShowReservationsController {

	@Autowired
	ResourseManagementService resMS;

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ShowReservationsController.class);

	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public String execute( ReservationForm reservationForm, HttpServletRequest request ) throws Exception {

		ReservationForm form = reservationForm;

		LOGGER.info("In ShowReservationsAction");

		long resourceId = form.getId();
		String displayIntervalStartStr = form.getDisplayIntervalStart();
		String displayIntervalEndStr = form.getDisplayIntervalEnd();

		Resource res = resMS.getResource(resourceId);
		request.setAttribute("resource", res);

		Date displayIntervalStart;
		Date displayIntervalEnd;
		// ------- List for selecting user in Add form -------------------

		List<User> allUsersList = userMS.getUsers();
		request.setAttribute("allUsersList", allUsersList);

		// ------- Parsing date from "filter by date" field --------------
		// ------- and filter the reservations ---------------------------

		if (displayIntervalStartStr != null && !Objects.equals(displayIntervalStartStr, "")
				&& displayIntervalEndStr != null && !Objects.equals(displayIntervalEndStr, "")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			displayIntervalStart = sdf.parse(displayIntervalStartStr);
			displayIntervalEnd = sdf.parse(displayIntervalEndStr);

			// ------------ Add 1 day to endTime to display events including
			// events on this day

			Calendar cal = Calendar.getInstance();
			cal.setTime(displayIntervalEnd);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			displayIntervalEnd = cal.getTime();

		} else if ("Day".equals(form.getDisplayInterval())) {
			Date currentTime = new Date();
			Calendar currentTimeCal = Calendar.getInstance();
			currentTimeCal.setTime(currentTime);
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(Calendar.YEAR, currentTimeCal.get(Calendar.YEAR));
			cal.set(Calendar.MONTH, currentTimeCal.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_YEAR, currentTimeCal.get(Calendar.DAY_OF_YEAR));
			displayIntervalStart = cal.getTime();
			cal.add(Calendar.DAY_OF_YEAR, 1);
			displayIntervalEnd = cal.getTime();		
		} else {
			Date currentTime = new Date();
			Calendar currentTimeCal = Calendar.getInstance();
			currentTimeCal.setTime(currentTime);
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(Calendar.YEAR, currentTimeCal.get(Calendar.YEAR));
			cal.set(Calendar.MONTH, currentTimeCal.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, 1);
			displayIntervalStart = cal.getTime();
			cal.add(Calendar.MONTH, 1);
			displayIntervalEnd = cal.getTime();
		}

		List<Reservation> reservationsList = resMS.getReservations(
				displayIntervalStart, displayIntervalEnd, resourceId);

		request.setAttribute("reservationsList", reservationsList);

		return "reservations";
	}
}
*/
