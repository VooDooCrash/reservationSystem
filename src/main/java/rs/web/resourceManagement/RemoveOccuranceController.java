/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.model.Occurrence;
import rs.model.Reservation;

@Controller
public class RemoveOccuranceController {
	
	@Autowired
	ResourseManagementService resMS;

	@Autowired
	ResourseManagementService serMS;
	
	private static final Logger LOGGER = Logger.getLogger(RemoveOccuranceController.class);

	@RequestMapping(value = "reservation/{reservationID}/occurrence/{occurrenceID}", method = RequestMethod.DELETE)
	public String execute( @PathVariable("reservationID") Long reservationID,
						   @PathVariable("occurrenceID") Long occurrenceID) throws Exception {

		LOGGER.info("In RemoveOccuranceAction");

//		Long reservationId = Long.parseLong(request.getParameter("reservationId"));
//		Long occurrenceIndex = Long.parseLong(request.getParameter("occIndex"));
		
		Reservation reservation = resMS.getReservation(reservationID);
		Occurrence occ = new Occurrence();
		occ.setCanceled(true);
		occ.setIndex(occurrenceID);
		occ.setReservation(reservation);
		resMS.addSpecialOccurrance(occ);
		
		Long resourceId = resMS.getReservation(reservationID).getResource().getId();

//		ActionRedirect redirect = new ActionRedirect(
//				mapping.findForward(AF_SUCESS));
//		redirect.addParameter("id", resourceId);

		return "recirect:/reservation/" + reservationID;

	}
}*/
