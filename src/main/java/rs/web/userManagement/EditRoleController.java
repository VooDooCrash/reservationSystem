/*
package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.UserManagementService;
import rs.model.Role;

@Controller
public class EditRoleController {

	@Autowired
	UserManagementService userMS;
	
	private static final Logger LOGGER = Logger.getLogger(EditRoleController.class);

	@RequestMapping(value = "/role/{roleID}", method = RequestMethod.POST)
	public String execute( EditRoleForm roleForm,
						   @PathVariable("roleID") Long roleID )
			throws Exception {

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
*/
