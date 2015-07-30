package rs.web.resourceManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.logic.ResourseManagementService;
import rs.model.Reservation;
import rs.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class TimeToReservationFilter extends OncePerRequestFilter {

	@Autowired
	ResourseManagementService resMS;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			Long time = resMS.getTimeToNextReservation(user.getId());
			Reservation nextReservation = resMS.getNextReservation(user.getId());

			if (time == null) {
				time = 0L;
			}

			request.getSession().setAttribute("timeToReservation",
					time);
			request.getSession().setAttribute("nextReservation",
					nextReservation);
			
		}

		filterChain.doFilter(request, response);
	}
}