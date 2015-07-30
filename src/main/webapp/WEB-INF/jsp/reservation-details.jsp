<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="body">
		<div class="container">
			<div class="col-lg-5 col-md-5 col-sm-5">

				<c:if
					test="${ (loggedInUser.role.name eq 'Admin') or (loggedInUser.id eq reservation.user.id) }">

					<button class="btn btn-danger btn-xs"
						onclick="removeReservation();">Remove</button>

					<form id="remove-reservation-form" action="${ctx}remove-reservation.do"
						method="post" hidden="true" style="margin-bottom: 0em;">
						<input type="hidden" id="reservationId" name="id"
							value="${param.id}"> <input type="hidden"
							name="resourceId" value="${reservation.resource.id}">
					</form>

					<script type="text/javascript">
						function removeReservation() {
							document.getElementById('remove-reservation-form')
									.submit();
						}
					</script>

					<button type="button" class="btn btn-success btn-xs"
						data-toggle="collapse" data-target="#edit-reservation">
						<spring:message code="btn.reservation.edit" />
					</button>

					<!-- logic:messagesPresent>
						<div id="edit-reservation" class="collapse in">
							<jsp:include page="/WEB-INF/jspf/form/edit-reservation-form.jspf" />
						</div>
					</logic:messagesPresent --!>
					<!-- logic:messagesNotPresent>
						<div id="edit-reservation" class="collapse">
							<jsp:include page="/WEB-INF/jspf/form/edit-reservation-form.jspf" />
						</div>
					</logic:messagesNotPresent --!>
				</c:if>

				<div class="panel panel-default">
					<div class="panel-heading">Details</div>
					<div class="panel-body">
						<label>User:</label>
						<p>${reservation.user.firstName} ${reservation.user.lastName}</p>
						<label>Resource:</label>
						<p>${reservation.resource.name}</p>
						<label>Starting time:</label>
						<p>
							<fmt:formatDate value="${reservation.startTime}"
								pattern="dd/MM/yyyy HH:mm" />
						</p>
						<label>Ending time:</label>
						<p>
							<fmt:formatDate value="${reservation.endTime}"
								pattern="dd/MM/yyyy HH:mm" />
						</p>
						<label>All day:</label>
						<p>${reservation.allDay}</p>
						<label>Repeat:</label>
						<p>${reservation.repeat}</p>
						<c:if test="${reservation.reminder eq true}">
							<label>Reminder:</label>
							<p>${reservation.reminderTime} minutes</p>
						</c:if>
						<label>Description:</label>
						<p>${reservation.description}</p>
					</div>
				</div>

				<a href="${ctx}reservations.do?id=${ReservationForm.resourceId}">
					<button type="button" class="btn btn-info btn-xs">Back To
						Resource</button>
				</a>

			</div>
			<div class="col-lg-7 col-md-7 col-sm-7">

				<button type="button" class="btn btn-success btn-xs"
					data-toggle="collapse" data-target="#add-companion-div">
					<spring:message code="btn.companion.add" />
				</button>


				<div id="add-companion-div" class="collapse">
					<jsp:include page="/WEB-INF/jspf/form/add-companion-form.jspf" />
				</div>

				<jsp:include page="/WEB-INF/jspf/table/companions-table.jspf"></jsp:include>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>