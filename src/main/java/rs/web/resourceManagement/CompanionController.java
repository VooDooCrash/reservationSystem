package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;

@Controller
public class CompanionController {

	@Autowired
	ResourseManagementService resMS;
	
	private static final Logger LOGGER = Logger.getLogger(CompanionController.class.getName());

	@RequestMapping(value = "/reservation/{reservationID}/companion/{companionID}", method = RequestMethod.PUT)
	public String addCompanion( @PathVariable("reservationID") Long reservationID,
						   @PathVariable("companionID") Long companionID ) throws Exception {

		LOGGER.info("In AddCompanionAction");
		
		resMS.addCompanion(companionID, reservationID);

		return "redirect:/reservation/" + reservationID;
	}

	@RequestMapping(value = "/reservation/{reservationID}/companion/{companionID}", method = RequestMethod.DELETE)
	public String deleteCompanion(@PathVariable("reservationID") Long reservationID,
									@PathVariable("companionID") Long companionID) throws Exception {

		LOGGER.info("In RemoveCompanionAction");

//		ReservationForm form = (ReservationForm) reservationForm;

		resMS.removeCompanion(companionID, reservationID);

//		ActionRedirect redirect = new ActionRedirect(
//				mapping.findForward(AF_SUCESS));
//		redirect.addParameter("id", reservationId);

		return "redirect:/reservation/" + reservationID;
	}
}
