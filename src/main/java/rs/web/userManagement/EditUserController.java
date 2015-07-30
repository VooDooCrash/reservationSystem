/*
package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.UserManagementService;
import rs.model.Role;
import rs.model.User;

import javax.servlet.http.HttpServletRequest;

@Service
@Controller
public class EditUserController {

	@Autowired
	UserManagementService userMS;
	
	private static final Logger LOGGER = Logger.getLogger(EditUserController.class);

	@RequestMapping(value = "/user/{userID}", method = RequestMethod.POST)
	public String execute( @ModelAttribute("userForm") UserForm userForm, HttpServletRequest request)
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
*/
