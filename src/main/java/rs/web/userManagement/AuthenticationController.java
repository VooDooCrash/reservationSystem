package rs.web.userManagement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.logic.UserManagementService;
import rs.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

	@Autowired
	UserManagementService userMS;

	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {

		LOGGER.info("In ShowUserLoginAction");

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm") UserLoginForm loginForm, HttpServletRequest request)
			throws Exception {

		LOGGER.info("In UserLoginAction");

		Subject currentUser = SecurityUtils.getSubject();

		String email = loginForm.getEmail();
		String password = loginForm.getPassword();

		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(email,
					password);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				return "login";
			}
		}
		
		User loggedInUser = userMS.getUserByEmail(email);
		request.getSession().setAttribute("loggedInUser", loggedInUser);

		return "redirect:/users.do";
	}

	@RequestMapping("/logout")
	public String logout() throws Exception {

		LOGGER.info("In UserLoginAction");

		Subject currentUser = SecurityUtils.getSubject();

		currentUser.logout();

		return "redirect:/login";
	}
}
