package rs.web.resourceManagement;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.ResourseManagementService;
import rs.model.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ResourceController {

	@Autowired
	ResourseManagementService resMS;
	
	private static final Logger LOGGER = Logger.getLogger(ResourceController.class.getName());
	
	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public String get( Model model) {

		LOGGER.info("In ShowResourcesAction");
		
		List<Resource> resourcesList = resMS.getResources();
		model.addAttribute("resourcesList", resourcesList);

		return "resources";
	}

	@RequestMapping("/resource/{resourceID}")
	public String showUpdate( @PathVariable("resourceID") Long resourceID, Model model )
			throws Exception {

		LOGGER.info("In ShowEditResourceAction");

//		ResourceForm form = (ResourceForm) roleForm;
//		long resourceId = form.getId();

		Resource resource = resMS.getResource(resourceID);

		model.addAttribute("action", "edit");
		model.addAttribute("resource", resource);

//		form.setId(resourceId);
//		form.setName(resource.getName());

		return "redirect:/resource/" + resourceID;
	}

	@RequestMapping(value = "/resource/{resourceID}", method = RequestMethod.POST)
	public String update( @PathVariable("resourceID") Long resourceID,
						   @ModelAttribute("resourceForm") ResourceForm resourceForm) throws Exception {

		LOGGER.info("In EditResourceAction");

		ResourceForm form = resourceForm;
//
//		long resourceId = form.getId();

		Resource r = resMS.getResource(resourceID);
		r.setName(form.getName());
		resMS.updateResource(r);

//		if (getErrors(request).isEmpty()) {
//			return mapping.findForward("success");
//		} else {
		return "redirect:/resources";
//		}
	}

	@RequestMapping(value = "/resource/{resourceID}", method = RequestMethod.DELETE)
	public String delete( @PathVariable("resourceID") Long resourceID) {

		LOGGER.info("In RemoveResourceAction");

//		ResourceForm form = (ResourceForm) resourceForm;
//		long resourceId = form.getId();
		boolean successFlag = resMS.removeResource(resourceID);

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

	@RequestMapping(value = "/resource" , method = RequestMethod.PUT)
	public String add( @ModelAttribute("addResourceForm") AddResourceForm addResourceForm,
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
