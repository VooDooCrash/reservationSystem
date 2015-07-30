/*
package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.logic.UserManagementService;
import rs.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ShowUserDetailsController {

	@Autowired
	UserManagementService userMS;
	
	@Autowired
	ResourseManagementService resMS;
	
	private static final Logger LOGGER = Logger.getLogger(ShowUserDetailsController.class);

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String execute( @ModelAttribute("userForm") UserForm userForm,
						   @PathVariable("userID") Long userID,
						   HttpServletRequest request)
			throws Exception {

		LOGGER.info("In ShowUserDetailsAction");

		UserForm form = userForm;
		long userId = userID;

		User u = userMS.getUser(userId);
		
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MONTH, 1);

		request.setAttribute("user", u);
		request.setAttribute("reservationsList", resMS.getReservationsByUser(u.getId(), currentDate, cal.getTime()));
		request.setAttribute("action", "edit");

		form.setId(userId);
		form.setFirstName(u.getFirstName());
		form.setLastName(u.getLastName());
		form.setEmail(u.getEmail());
		form.setRoleId(u.getRole().getId());
		form.setRolesList(userMS.getRoles());

		return "user-details";
	}
}
*/
