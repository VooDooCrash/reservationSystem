/*
package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.logic.ResourseManagementService;
import rs.model.Resource;

@Controller
public class ShowResourceDetailsController {

	@Autowired
	ResourseManagementService resMS;
	
	private static final Logger LOGGER = Logger.getLogger(ShowResourceDetailsController.class);

	@RequestMapping("/resource/{resourceID}")
	public String execute( @PathVariable("reqsourceID") Long reqsourceID, Model model )
			throws Exception {

		LOGGER.info("In ShowEditResourceAction");

//		ResourceForm form = (ResourceForm) roleForm;
//		long resourceId = form.getId();

		Resource resource = resMS.getResource(reqsourceID);

		model.addAttribute("action", "edit");
		model.addAttribute("resource", resource);
		
//		form.setId(resourceId);
//		form.setName(resource.getName());

		return "redirect:/resource/" + reqsourceID;
	}
}
*/
