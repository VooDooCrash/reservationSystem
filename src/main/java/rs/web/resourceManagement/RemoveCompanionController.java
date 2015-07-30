/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;

@Controller
public class RemoveCompanionController {

	@Autowired
	ResourseManagementService serMS;
	
	private static final Logger LOGGER = Logger.getLogger(RemoveCompanionController.class);

	@RequestMapping(value = "/reservation/{reservationID}/companion/{companionID}", method = RequestMethod.DELETE)
	public String execute(@PathVariable("reservationID") Long reservationID,
						  @PathVariable("companionID") Long companionID) throws Exception {

		LOGGER.info("In RemoveCompanionAction");

//		ReservationForm form = (ReservationForm) reservationForm;

		serMS.removeCompanion(companionID, reservationID);

//		ActionRedirect redirect = new ActionRedirect(
//				mapping.findForward(AF_SUCESS));
//		redirect.addParameter("id", reservationId);

		return "redirect:/reservation/" + reservationID;
	}
}*/
