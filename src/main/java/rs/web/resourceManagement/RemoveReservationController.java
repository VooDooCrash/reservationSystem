/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.model.Reservation;

@Controller
public class RemoveReservationController {

	private static final String AF_SUCESS = "success";

	@Autowired
	ResourseManagementService resMS;
	
	private static final Logger LOGGER = Logger.getLogger(RemoveReservationController.class);

	@RequestMapping(value = "/reservation/{reservationID}", method = RequestMethod.DELETE)
	public String execute(@PathVariable("reservationID") Long reservationID) throws Exception {

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
}*/
