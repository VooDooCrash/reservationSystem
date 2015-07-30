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
import rs.model.Occurrence;
import rs.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Objects;

@Controller
public class EditOccurrenceController {

	@Autowired
	ResourseManagementService resMS;

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EditOccurrenceController.class);

	@RequestMapping(value = "reservation/{reservationID}/occurrence/{occurrenceIndex}", method = RequestMethod.POST)
	public String execute( @PathVariable("reservationID") Long reservationID,
						   @PathVariable("occurrenceIndex") Long occurrenceIndex,
						   @ModelAttribute("occurrenceForm") OccurrenceForm occurrenceForm ) throws Exception {

		LOGGER.info("In EditOccurrenceAction");

		OccurrenceForm form = (OccurrenceForm) occurrenceForm;
		String description = form.getDescription();
//		Long reservationId = form.getReservationId();
//		Long occIndex = form.getOccIndex();

		Reservation reservation = resMS.getReservation(reservationID);
		Occurrence occurrence = new Occurrence();
		occurrence.setIndex(occurrenceIndex);
		occurrence.setDescription(description);
		occurrence.setReservation(reservation);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String startTimeStr = form.getStartTime();
		String endTimeStr = form.getEndTime();

		if (startTimeStr != null && !Objects.equals(startTimeStr, "") && endTimeStr != null
				&& !Objects.equals(endTimeStr, "")) {
			occurrence.setStartTime(sdf.parse(startTimeStr));
			occurrence.setEndTime(sdf.parse(endTimeStr));
		}

		resMS.addSpecialOccurrance(occurrence);

//		ActionRedirect redirect = new ActionRedirect(
//				mapping.findForwardConfig(AF_SUCESS));
//		redirect.addParameter("reservationId", reservationId);
//		redirect.addParameter("occIndex", occIndex);
		return "redirect:/reservation/" + reservationID + "/occurrence" + occurrenceIndex;
	}
}
*/
