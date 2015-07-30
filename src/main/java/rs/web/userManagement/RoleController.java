package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.UserManagementService;
import rs.model.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RoleController{

	@Autowired
	UserManagementService userMS;
	
	private static final Logger LOGGER = Logger.getLogger(RoleController.class.getName());

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String execute( HttpServletRequest request ) {

		LOGGER.info("In ShowRolesAction");

		List<Role> rolesList = userMS.getRoles();
		request.setAttribute("rolesList", rolesList);

		return "roles";
	}

	@RequestMapping(value = "/role", method = RequestMethod.PUT)
	public String execute( RoleForm roleForm ) {

		LOGGER.info("In AddRoleAction");
		
		AddRoleForm form = (AddRoleForm) roleForm;
		Long roleID;
//		ActionErrors errors = new ActionErrors();
		
		if (userMS.getRoleByName(form.getName()) != null) {
//			errors.add("name", new ActionMessage("errors.existing", "Name"));
//			saveErrors(request, errors);
			
			return "";
		} else {
			Role r = new Role();			
			r.setName(form.getName());
			roleID = userMS.createRole(r);
			
			return "redirect:/role/" + roleID;
		}
	}

	@RequestMapping(value = "/role/{roleID}", method = RequestMethod.DELETE)
	public String deleteRole( @PathVariable Long roleID ) {

		LOGGER.info("In RemoveRoleAction");

		userMS.removeRole(roleID, "Guest");

		return "redirect:/roles";
	}

	@RequestMapping(value = "/role/{roleID}", method = RequestMethod.POST)
	public String updateRole(@ModelAttribute("roleForm") EditRoleForm roleForm,
							 @PathVariable("roleID") Long roleID ) throws Exception {

		LOGGER.info("In EditRoleAction");

		EditRoleForm form = roleForm;
		long roleId = roleID;
//		ActionMessages errors = new ActionMessages();

		if (userMS.getRoleByName(form.getName()) != userMS.getRole(form.getId())
				&& userMS.getRoleByName(form.getName()) != null) {

//			errors.add("name", new ActionMessage("errors.existing", "Name"));
//			saveErrors(request, errors);

		} else {
			Role r = userMS.getRole(roleId);
			r.setName(form.getName());
			userMS.updateRole(r);
		}

//		if (getErrors(request).isEmpty()) {
//			ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig(AF_SUCESS));
//			redirect.addParameter("id", roleId);
//			return redirect;
//		} else {
		return "redirect:/role/" + roleID;
//		}
	}
}
