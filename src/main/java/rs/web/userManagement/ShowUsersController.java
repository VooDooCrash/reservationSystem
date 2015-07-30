/*
package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.logic.UserManagementService;
import rs.model.Role;
import rs.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Controller
public class ShowUsersController {

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ShowUsersController.class);

	@RequestMapping("/users")
	public String execute(UserForm userForm, HttpServletRequest request)
			throws Exception {

		LOGGER.info("In ShowIndexAction");

		UserForm form = userForm;

		List<User> usersList = userMS.getUsers();
		List<Role> rolesList = userMS.getRoles();

		request.setAttribute("usersList", usersList);
		form.setRolesList(rolesList);

		return "users";
	}
}
*/
