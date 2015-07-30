/*
package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.UserManagementService;

@Controller
public class RemoveRoleController {

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RemoveRoleController.class);

	@RequestMapping(value = "/role/{roleID}", method = RequestMethod.DELETE)
	public String execute( @PathVariable Long roleID ) {

		LOGGER.info("In RemoveRoleAction");

		userMS.removeRole(roleID, "Guest");

		return "redirect:/roles";
	}
}
*/
