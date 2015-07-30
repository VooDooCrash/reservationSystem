/*
package rs.web.userManagement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Controller
public class UserLogoutController {

	private static final String AF_SUCESS = "success";
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserLogoutController.class);

	@RequestMapping("/logout")
	public String execute()
			throws Exception {

		LOGGER.info("In UserLoginAction");

		Subject currentUser = SecurityUtils.getSubject();
		
		currentUser.logout();

		return "redirect:/login";
	}
}
*/
