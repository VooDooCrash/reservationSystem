<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" >
	<c:url value="/"/>
</c:set>

<div style="margin: 30px">

	<div id="menu" class="panel panel-default">
		<div class="panel-heading">Menu</div>
		<div>
			<ul class="nav nav-pills nav-stacked">
				<c:set var="uri" value="${pageContext.request.requestURI}" />
				<li> ${pageContext.request.requestURI} </li>
				<li class="${uri eq ctx.concat('WEB-INF/jsp/users.jsp') ? 'active' : ''}"><a
					href="${ctx}users">Users</a></li>
				<li class="${uri eq ctx.concat('WEB-INF/jsp/roles.jsp') ? 'active' : ''}"><a
					href="${ctx}roles">Roles</a></li>
				<li class="${uri eq ctx.concat('WEB-INF/jsp/resources.jsp') ? 'active' : ''}"><a
					href="${ctx}resources">Resources</a></li>
			</ul>
		</div>
	</div>

	<div id="details" class="panel panel-default">
		<div class="panel-heading">Profile Details</div>
		<div class="panel-body">


			<shiro:guest>

				<label>Status:</label>
				<p>GUEST</p>
			</shiro:guest>

			<shiro:user>
				<c:if test="${nextReservation ne null and timeToReservation / (1000*60*60) < 24}">
					<label>Next reservation:</label>
					
					<p>Hours: ${fn:substringBefore((timeToReservation / (1000*60*60)) % 24,'.')}</p>
					<p>Minutes: ${fn:substringBefore((timeToReservation / (1000*60)) % 60,'.')}</p>
					<p>Seconds: ${fn:substringBefore(((timeToReservation / 1000) % 60) % 60,'.')}</p>
					
					<c:if test="${nextReservation.occurrenceIndex == null or nextReservation.occurrenceIndex == 0}">
						<p><a href="${ctx}reservation-details.do?id=${nextReservation.id}"><button class="btn btn-info btn-xs">Details</button></a></p>
					</c:if>
					<c:if test="${nextReservation.occurrenceIndex != null and nextReservation.occurrenceIndex != 0}">
						<p><a href="${ctx}occurrence-details.do?reservationId=${nextReservation.id}&occIndex=${nextReservation.occurrenceIndex}"><button class="btn btn-info btn-xs">Details</button></a></p>
					</c:if>
				</c:if>

				<label>Status:</label>
				<p>USER</p>
				<label>Name:</label>
				<p>${loggedInUser.firstName} ${loggedInUser.lastName}</p>
				<label>Login email:</label>
				<shiro:principal />

				<a href="${ctx}logout"><button class="btn btn-info btn-xs">Logout</button></a>
				<a href="${ctx}user/${loggedInUser.id}">
					<button	class="btn btn-info btn-xs">Details</button>
				</a>
			</shiro:user>
		</div>
	</div>
</div>