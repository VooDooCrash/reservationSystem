<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="body">
		<div class="container">
			<div class="col-lg-5 col-md-5 col-sm-5">

				<a href="${ctx}reservation-details.do?id=${originReservation.id}">
					<button class="btn btn-info btn-xs">Original Reservation</button>
				</a>

				<c:if
					test="${ (loggedInUser.role.name eq 'Admin') or (loggedInUser.id eq originReservation.user.id) }">

					<button class="btn btn-danger btn-xs" onclick="removeOccurrence();">Remove
						Occurrence</button>

					<form id="remove-occurrence-form" action="${ctx}remove-occurrence.do"
						method="post" hidden="true" style="margin-bottom: 0em;">
						<input type="hidden" id="reservationId" name="reservationId"
							value="${originReservation.id}"> <input type="hidden"
							name="occIndex" value="${occurrenceIndex}">
					</form>

					<script type="text/javascript">
						function removeOccurrence() {
							document.getElementById('remove-occurrence-form')
									.submit();
						}
					</script>
					
					<button type="button" class="btn btn-success btn-xs"
						data-toggle="collapse" data-target="#edit-occurrance">
						<spring:message code="btn.edit" />
					</button>

					<!-- logic:messagesPresent>
						<div id="edit-occurrance" class="collapse in">
							<jsp:include page="/WEB-INF/jspf/form/edit-occurrence-form.jspf" />
						</div>
					</logic:messagesPresent --!>
					<!-- logic:messagesNotPresent>
						<div id="edit-occurrance" class="collapse">
							<jsp:include page="/WEB-INF/jspf/form/edit-occurrence-form.jspf" />
						</div>
					</logic:messagesNotPresent --!>
				</c:if>

				<div class="panel panel-default">
					<div class="panel-heading">Details</div>
					<div class="panel-body">
						<label>User:</label>
						<p>${occurrence.user.firstName} ${occurrence.user.lastName}</p>
						<label>Starting time:</label>
						<p>
							<fmt:formatDate value="${occurrence.startTime}"
								pattern="dd/MM/yyyy HH:mm" />
						</p>
						<label>Ending time:</label>
						<p>
							<fmt:formatDate value="${occurrence.endTime}"
								pattern="dd/MM/yyyy HH:mm" />
						</p>
						<label>All day:</label>
						<p>${occurrence.allDay}</p>
						<label>Description:</label>
						<p>${occurrence.description}</p>
					</div>
				</div>

				<a href="${ctx}reservations.do?id=${originReservation.resource.id}">
					<button type="button" class="btn btn-info btn-xs">Back To
						Resource</button>
				</a>

			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>