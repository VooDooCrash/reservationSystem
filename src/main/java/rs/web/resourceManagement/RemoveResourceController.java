/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;

@Controller
public class RemoveResourceController{
	
//	@Forward(path = "/resources.do", redirect=true)
//	private static final String AF_SUCESS = "success";
//
//	@Forward(path = "/show-resource-details.do")
//	private static final String AF_FAILURE = "failure";
	
	@Autowired
	ResourseManagementService serMS;
	
	private static final Logger LOGGER = Logger.getLogger(RemoveResourceController.class);

	@RequestMapping(value = "/resource/{resourceID}", method = RequestMethod.DELETE)
	public String execute( @PathVariable("resourceID") Long resourceID) {

		LOGGER.info("In RemoveResourceAction");

//		ResourceForm form = (ResourceForm) resourceForm;
//		long resourceId = form.getId();
		boolean successFlag = serMS.removeResource(resourceID);
		
//		if (!successFlag) {
//
//			ActionErrors errors = new ActionErrors();
//			errors.add("delete", new ActionMessage("errors.user.delete"));
//			saveErrors(request, errors);
//
//			return mapping.findForward(AF_FAILURE);
//		}
				
		return "redirect:resources";
	}

}
*/
