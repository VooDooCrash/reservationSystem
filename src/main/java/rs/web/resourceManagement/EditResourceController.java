/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.model.Resource;

@Controller
public class EditResourceController {

	@Autowired
	ResourseManagementService resMS;
	
	private static final Logger LOGGER = Logger.getLogger(EditResourceController.class);

	@RequestMapping(value = "/resource/{resourceID}", method = RequestMethod.POST)
	public String execute( @PathVariable("resourceID") Long resourceID,
			@ModelAttribute("resourceForm") ResourceForm resourceForm) throws Exception {

		LOGGER.info("In EditResourceAction");

		ResourceForm form = (ResourceForm) resourceForm;

		long resourceId = form.getId();

		Resource r = resMS.getResource(resourceId);
		r.setName(form.getName());
		resMS.updateResource(r);

//		if (getErrors(request).isEmpty()) {
//			return mapping.findForward("success");
//		} else {
			return "redirect:/resources";
//		}
	}
}
*/
