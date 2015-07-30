/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.model.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AddResourceController {

	@Autowired
	ResourseManagementService resMS;

	private static final Logger LOGGER = Logger.getLogger(AddResourceController.class);
	
	@RequestMapping(value = "/resource" , method = RequestMethod.PUT)
	public String execute( @ModelAttribute("addResourceForm") AddResourceForm addResourceForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LOGGER.info("In AddResourceAction");

		AddResourceForm form = addResourceForm;
//		ActionErrors errors = new ActionErrors();
		
//		if (resMS.getResourceByName(form.getName()) != null) {
//			errors.add("name", new ActionMessage("errors.existing", "Name"));
//			saveErrors(request, errors);
//
//			return mapping.getInputForward();
//		} else {
			Resource r = new Resource();			
			r.setName(form.getName());			
			resMS.createResource(r);	
			
			return "redirect:/resources";
//		}
	}
}
*/
