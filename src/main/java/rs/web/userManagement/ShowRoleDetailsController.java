package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.logic.UserManagementService;
import rs.model.Role;
import rs.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShowRoleDetailsController {

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

	@RequestMapping("/role/{roleId}")
	public String execute( RoleForm roleForm,
						   @PathVariable("roleID") Long roleID,
						   HttpServletRequest request) throws Exception {

		LOGGER.info("In ShowRoleDetailsAction");

		RoleForm form = roleForm;
		long roleId = roleID;

		Role r = userMS.getRole(roleId);
		request.setAttribute("role", r);
		request.setAttribute("action", "edit");

		List<User> usersList = userMS.getUsersByRole(roleId);
		request.setAttribute("usersList", usersList);

		form.setId(roleId);
		form.setName(r.getName());

		return "role-details";
	}
}
