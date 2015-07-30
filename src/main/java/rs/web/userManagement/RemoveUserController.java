/*
package rs.web.userManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.UserManagementService;

@Service
@Controller
public class RemoveUserController {

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RemoveUserController.class);

	@RequestMapping(value = "/user/{userID}", method = RequestMethod.DELETE)
	public String execute( Long userID )
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

}
*/
