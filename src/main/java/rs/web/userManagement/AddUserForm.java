package rs.web.userManagement;

import java.util.logging.Logger;

public class AddUserForm extends UserForm {

	private static final long serialVersionUID = 611109468466850585L;
	
	private static final Logger LOGGER = Logger.getLogger(AddUserForm.class.getName());

//	@Override
//	public ActionErrors validate(
//			HttpServletRequest request) {
//
//		log.info("Validating AddUserForm");
//
//		ActionErrors errors = new ActionErrors();
//
//		if (StringUtils.isEmpty(firstName)) {
//			errors.add("firstName", new ActionMessage("errors.required",
//					"First name"));
//		}
//
//		if (StringUtils.isEmpty(lastName)) {
//			errors.add("lastName", new ActionMessage("errors.required",
//					"Last name"));
//		}
//
//		if (StringUtils.isEmpty(email)) {
//			errors.add("email", new ActionMessage("errors.required",
//					"Email"));
//
//		} else if (!email.matches(".+@.+\\..+")) {
//			errors.add("email", new ActionMessage("errors.invalid",
//					"Email"));
//
//		}
//
//		return errors;
//	}
}
