/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.logic.UserManagementService;
import rs.model.Reservation;
import rs.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowReservationDetailsController {

	@Autowired
	ResourseManagementService resMS;

	@Autowired
	UserManagementService userMS;
	
	private static final Logger LOGGER = Logger.getLogger(ShowReservationDetailsController.class);

	@RequestMapping(value = "/reservation/{reservationID}", method = RequestMethod.GET)
	public String execute( @PathVariable("reservationID") Long reservationID, Model model ) throws Exception {

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
}
*/
