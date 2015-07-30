<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader" value="Users" />
	<tiles:putAttribute name="body">

		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12">

					<c:if
						test="${(loggedInUser.role.name eq 'Admin') or (loggedInUser.id eq user.id)}">

						<p class="text-danger">
							<form:errors path="delete" />
						</p>
						<button class="btn btn-danger btn-xs"
							onclick="myFunction(${param.id});">Remove</button>

						<form id="remove-user-form" action="${ctx}remove-user.do" method="post"
							hidden="true" style="margin-bottom: 0em;">
							<input type="hidden" id="userId" name="id">
						</form>

						<script type="text/javascript">
							function myFunction(id) {
								document.getElementById('userId').value = id;
								document.getElementById('remove-user-form').submit();
							}
						</script>

						<button type="button" class="btn btn-success btn-xs"
							data-toggle="collapse" data-target="#edit-user">
							<spring:message code="btn.user.edit" />
						</button>

						<!-- logic:messagesPresent>
							<logic:messagesNotPresent property="delete">
								<div id="edit-user" class="collapse in">
									<jsp:include page="/WEB-INF/jspf/form/edit-user-form.jspf" />
								</div>
							</logic:messagesNotPresent --!>
							<logic:messagesPresent property="delete">
								<div id="edit-user" class="collapse">
									<jsp:include page="/WEB-INF/jspf/form/edit-user-form.jspf" />
								</div>
							</logic:messagesPresent --!>
						</logic:messagesPresent --!>
						<!-- logic:messagesNotPresent>
							<div id="edit-user" class="collapse">
								<jsp:include page="/WEB-INF/jspf/form/edit-user-form.jspf" />
							</div>
						</logic:messagesNotPresent --!>
					</c:if>

					<div class="panel panel-default">
						<div class="panel-heading">Details</div>
						<div class="panel-body">
							<label>First Name:</label>
							<p>${user.firstName}</p>
							<label>Last Name:</label>
							<p>${user.lastName}</p>
							<label>Email:</label>
							<p>${user.email}</p>
							<label>Role:</label>
							<p>${user.role.name}</p>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div id="mytimeline"></div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<jsp:include page="/WEB-INF/jspf/table/reservations-table.jspf"></jsp:include>
				</div>
			</div>
			
			<script type="text/javascript">
				var rowsForTimeline = new Array();
				
				<logic:iterate id="reservation" name="reservationsList" indexId="index" >
					rowsForTimeline[${index}] = [
					new Date(<fmt:formatDate value="${reservation.startTime}" pattern="yyyy,MM-1,dd,HH,mm,ss"/>), 
					new Date(<fmt:formatDate value="${reservation.endTime}" pattern="yyyy,MM-1,dd,HH,mm,ss"/>), 
					
					<c:if test="${reservation.occurrenceIndex == null or reservation.occurrenceIndex == 0}">
						'<p><a href="${ctx}reservation-details.do?id=${reservation.id}">'+
							'${reservation.resource.name}</a></p>'
					</c:if>
					<c:if test="${reservation.occurrenceIndex != null and reservation.occurrenceIndex != 0}">
						'<p><a href="${ctx}occurrence-details.do?reservationId=${reservation.id}&occIndex=${reservation.occurrenceIndex}">' +
							'${reservation.resource.name}</a></p>'
					</c:if>]
					
				</logic:iterate>
					
				google.load("visualization", "1");

				// Set callback to run when API is loaded
				google.setOnLoadCallback(drawVisualization);

				// Called when the Visualization API is loaded.
				function drawVisualization() {
					// Create and populate a data table.
					var data = new google.visualization.DataTable();
					data.addColumn('datetime', 'start');
					data.addColumn('datetime', 'end');
					data.addColumn('string', 'content');

					data.addRows(rowsForTimeline);

					// specify options
					var options = {
						"width" : "100%",
						"height" : "200px",
						"style" : "box" // optional
					};

					// Instantiate our timeline object.
					var timeline = new links.Timeline(document
							.getElementById('mytimeline'));

					// Draw our timeline with the created data and options
					timeline.draw(data, options);
				}
			</script>

		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>