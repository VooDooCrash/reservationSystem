/*
package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.logic.UserManagementService;
import rs.model.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShowRolesController{

	@Autowired
	UserManagementService userMS;
	
	private static final Logger LOGGER = Logger.getLogger(ShowRolesController.class);

	@RequestMapping("/roles")
	public String execute( HttpServletRequest request ) {

		LOGGER.info("In ShowRolesAction");
		
		List<Role> rolesList = userMS.getRoles();
		request.setAttribute("rolesList", rolesList);

		return "roles";
	}
}*/
