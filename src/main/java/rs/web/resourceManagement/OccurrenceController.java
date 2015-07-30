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
import rs.model.Occurrence;
import rs.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Objects;

@Controller
public class OccurrenceController {

	@Autowired
	ResourseManagementService resMS;

	@Autowired
	UserManagementService userMS;
	
	private static final Logger LOGGER = Logger.getLogger(OccurrenceController.class.getName());

	@RequestMapping(value = "reservation/{reservationID}/occurrence/{occurrenceIndex}", method = RequestMethod.GET)
	public String getOccurrence( @PathVariable("reservationID") Long reservationID,
						   @PathVariable("occurrenceIndex") Long occurrenceIndex,
						   Model model) throws Exception {

		LOGGER.info("In ShowOccurrenceDetailsAction");
		
//		OccurrenceForm form = (OccurrenceForm) occurrenceForm;
		
//		Long reservationId = Long.parseLong(request.getParameter("reservationId"));
//		Long occurrenceIndex = Long.parseLong(request.getParameter("occIndex"));
		
		Reservation originReservation = resMS.getReservation(reservationID);

		model.addAttribute("originReservation", originReservation);

		model.addAttribute("occurrenceIndex", occurrenceIndex);

		Reservation occurrence = resMS.getOccurrance(reservationID, occurrenceIndex);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
//		form.setStartTime(sdf.format(occurrence.getStartTime()));
//		form.setEndTime(sdf.format(occurrence.getEndTime()));
		
		model.addAttribute("occurrence", occurrence);
		
		return "occurrence-details";
	}

	@RequestMapping(value = "reservation/{reservationID}/occurrence/{occurrenceIndex}", method = RequestMethod.POST)
	public String updateOccurrence( @PathVariable("reservationID") Long reservationID,
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

	@RequestMapping(value = "reservation/{reservationID}/occurrence/{occurrenceID}", method = RequestMethod.DELETE)
	public String deleteOccurrence( @PathVariable("reservationID") Long reservationID,
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
}
