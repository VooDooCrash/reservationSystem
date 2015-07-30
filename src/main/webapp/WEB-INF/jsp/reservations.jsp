<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader" value="${resource.name}" />
	<tiles:putAttribute name="body">
		<div class="container">
			<div class="panel panel-default">
				<div class="panel-heading">Controls</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3" align="left">
							<button type="button" class="btn btn-success btn-xs"
								data-toggle="collapse" data-target="#add-reservation-div"
								style="margin-bottom: 10px">
								<spring:message code="btn.reservation.add" />
							</button>
						</div>

						<div class="col-lg-6 col-md-6 col-sm-6">
							<html:form method="get" class="form-inline"
								style="margin-bottom: 0px">
								<html:hidden property="id" name="ReservationForm"
									value="${param.id}"></html:hidden>
								<div class="form-group">
									<label for="displayDate" style="font-weight: normal;">From:</label>
									<input property="displayIntervalStart"
										name="ReservationForm" class="form-control date-picker"
										style="height: 22px; width: 100px" />
								</div>
								<div class="form-group">
									<label for="displayDate" style="font-weight: normal;">To:</label>
									<input property="displayIntervalEnd" name="ReservationForm"
										class="form-control date-picker"
										style="height: 22px; width: 100px" />
								</div>
								<button type="submit" class="btn btn-info btn-xs">Show</button type="submit">
							</html:form>

							<script type="text/javascript">
								$(".date-picker").datepicker({
									dateFormat : "dd.mm.yy",
									constrainInput: false
								});
							</script>
						</div>

						<div class="col-lg-3 col-md-3 col-sm-3" align="right">
							<div class="row container" style="margin-bottom: 10px">
								<a href="${ctx}reservations.do?id=${param.id}&displayInterval=Day">
									<button class="btn btn-info btn-xs">Today</button>
								</a>
							</div>
							<div class="row container">
								<a href="${ctx}reservations.do?id=${param.id}">
									<button class="btn btn-info btn-xs">This month</button>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- logic:messagesPresent>
				<div id="add-reservation-div" class="collapse in">
					<jsp:include page="/WEB-INF/jspf/form/add-reservation-form.jspf">
						<jsp:param value="add" name="action" />
					</jsp:include>
				</div>
			</logic:messagesPresent --!>

			<!-- logic:messagesNotPresent>
				<div id="add-reservation-div" class="collapse">
					<jsp:include page="/WEB-INF/jspf/form/add-reservation-form.jspf">
						<jsp:param value="add" name="action" />
					</jsp:include>
				</div>
			</logic:messagesNotPresent --!>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div id="mytimeline"></div>
				</div>
			</div>

			<jsp:include page="/WEB-INF/jspf/table/reservations-table.jspf" />

			<script type="text/javascript">
				var rowsForTimeline = new Array();
				
				<logic:iterate id="reservation" name="reservationsList" indexId="index" >
					rowsForTimeline[${index}] = [
					new Date(<fmt:formatDate value="${reservation.startTime}" pattern="yyyy,MM-1,dd,HH,mm,ss"/>), 
					new Date(<fmt:formatDate value="${reservation.endTime}" pattern="yyyy,MM-1,dd,HH,mm,ss"/>), 

					<c:if test="${reservation.occurrenceIndex == null or reservation.occurrenceIndex == 0}">
						'<p><a href="${ctx}reservation-details.do?id=${reservation.id}">'+
							'${reservation.user.firstName} ${reservation.user.lastName}</a></p>'
					</c:if>
					<c:if test="${reservation.occurrenceIndex != null and reservation.occurrenceIndex != 0}">
						'<p><a href="${ctx}occurrence-details.do?reservationId=${reservation.id}&occIndex=${reservation.occurrenceIndex}">' +
							'${reservation.user.firstName} ${reservation.user.lastName}</a></p>'
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