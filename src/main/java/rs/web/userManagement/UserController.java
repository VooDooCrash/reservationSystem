package rs.web.userManagement;

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
import rs.model.Role;
import rs.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	UserManagementService userMS;

	@Autowired
	ResourseManagementService resMS;
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String list(UserForm userForm, HttpServletRequest request)
			throws Exception {

		LOGGER.info("In ShowIndexAction");

		UserForm form = userForm;

		List<User> usersList = userMS.getUsers();
		List<Role> rolesList = userMS.getRoles();

		request.setAttribute("usersList", usersList);
		form.setRolesList(rolesList);

		return "users";
	}

	@RequestMapping(value = "/user/{userID}", method = RequestMethod.GET)
	public String get( @PathVariable("userID") Long userID, Model model ) throws Exception {

		LOGGER.info("In ShowUserDetailsAction");

//		long userId = userID;

		User u = userMS.getUser(userID);

		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MONTH, 1);

		model.addAttribute("user", u);
		model.addAttribute("reservationsList", resMS.getReservationsByUser(u.getId(), currentDate, cal.getTime()));
		model.addAttribute("action", "edit");

//		form.setId(userId);
//		form.setFirstName(u.getFirstName());
//		form.setLastName(u.getLastName());
//		form.setEmail(u.getEmail());
//		form.setRoleId(u.getRole().getId());
//		form.setRolesList(userMS.getRoles());

		return "user-details";
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public String add( @ModelAttribute("userForm") UserForm userForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LOGGER.info("In AddUserAction");

		UserForm form = (UserForm) userForm;
		Long userID;
//		userFormActionErrors errors = new ActionErrors();

//		if (userMS.getUserByEmail(form.getEmail()) != null) {
//			errors.add("email", new ActionMessage("errors.existing", "Email"));
//			saveErrors(request, errors);
//			return "redirect:/user/" + userID;
//		} else {
			User u = new User();
			Role new_role = userMS.getRole(form.getRoleId());

			u.setFirstName(form.getFirstName());
			u.setLastName(form.getLastName());
			u.setEmail(form.getEmail());
			u.setPassword(form.getPassword());

			u.setRole(new_role);

			userID = userMS.createUser(u);
//		}

//		if (getErrors(request).isEmpty()) {
//			return mapping.findForward("success");
//		} else {
			return "redirect:/user/" + userID;
//		}
	}

	@RequestMapping(value = "/user/{userID}", method = RequestMethod.DELETE)
	public String delete( Long userID )
			throws Exception {

		LOGGER.info("In RemoveUserAction");

		boolean successFlag = userMS.removeUser(userID);

		if (!successFlag) {

//			ActionErrors errors = new ActionErrors();
//			errors.add("delete", new ActionMessage("errors.user.delete"));
//			saveErrors(request, errors);

			return "redirect:/user/" + userID;
		}

		return "redirect:/users";
	}

	@RequestMapping(value = "/user/{userID}", method = RequestMethod.POST)
	public String update( @ModelAttribute("userForm") UserForm userForm, HttpServletRequest request)
			throws Exception {

		LOGGER.info("In EditUserAction");

		UserForm form = (UserForm) userForm;
//		ActionErrors errors = new ActionErrors();
		long userId = form.getId();

		if (userMS.getUserByEmail(form.getEmail()) != userMS.getUser(form
				.getId()) && userMS.getUserByEmail(form.getEmail()) != null) {

//			errors.add("email", new ActionMessage("errors.required",
//					"Non existing email"));
//			saveErrors(request, errors);

		} else {

			User u = userMS.getUser(userId);
			Role new_role = userMS.getRole(form.getRoleId());

			u.setFirstName(form.getFirstName());
			u.setLastName(form.getLastName());
			u.setEmail(form.getEmail());

			u.setRole(new_role);

			userMS.updateUser(u);
		}

//		if (getErrors(request).isEmpty()) {
//			ActionRedirect redirect = new ActionRedirect(mapping.findForward(AF_SUCESS));
//			redirect.addParameter("id", userId);
//
//			return redirect;
//		} else {
		return "redirect:/user/" + userId;
//		}
	}
}