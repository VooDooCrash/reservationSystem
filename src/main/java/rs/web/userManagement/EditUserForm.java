package rs.web.userManagement;

import java.util.logging.Logger;

public class EditUserForm extends UserForm {

	private static final long serialVersionUID = -9031782584232094847L;
	
	private static final Logger LOGGER = Logger.getLogger(EditUserForm.class.getName());
	
/*	@Override
	public ActionErrors validate(
			HttpServletRequest request) {

		log.info("Validating EditUserForm");

		ActionErrors errors = new ActionErrors();

		if (StringUtils.isEmpty(firstName)) {
			errors.add("firstName", new ActionMessage("errors.required",
					"First name"));
		}

		if (StringUtils.isEmpty(lastName)) {
			errors.add("lastName", new ActionMessage("errors.required",
					"Last name"));
		}

		if (StringUtils.isEmpty(email)) {
			errors.add("email", new ActionMessage("errors.required",
					"Valid email"));
		}

		if (!email.matches(".+@.+\\..+")) {
			errors.add("email", new ActionMessage("errors.invalid", "Email"));
		}

		return errors;
	}*/
}
