<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader" value="Resources" />
	<tiles:putAttribute name="body">

		<div class="container">
			<div class="col-lg-6 col-md-6 col-sm-12">

				<shiro:hasRole name="Admin">

					<p class="text-danger">
						<form:errors path="delete" />
					</p>
					<button class="btn btn-danger btn-xs"
						onclick="removeResource(${param.id});">Remove</button>

					<form id="remove-resource-form" action="${ctx}remove-resource.do"
						method="post" hidden="true" style="margin-bottom: 0em;">
						<input type="hidden" id="resourceId" name="id">
					</form>

					<script type="text/javascript">
						function removeResource(id) {
							document.getElementById('resourceId').value = id;
							document.getElementById('remove-resource-form')
									.submit();
						}
					</script>

					<button type="button" class="btn btn-success btn-xs"
						data-toggle="collapse" data-target="#edit-resource">
						<spring:message code="btn.resource.edit" />
					</button>

					<!-- logic:messagesPresent>
						<logic:messagesNotPresent property="delete">
							<div id="edit-resource" class="collapse in">
								<jsp:include page="/WEB-INF/jspf/form/edit-resource-form.jspf" />
							</div>
						</logic:messagesNotPresent --!>
						<logic:messagesPresent property="delete">
							<div id="edit-resource" class="collapse">
								<jsp:include page="/WEB-INF/jspf/form/edit-resource-form.jspf" />
							</div>
						</logic:messagesPresent --!>
					</logic:messagesPresent --!>
					<!-- logic:messagesNotPresent>
						<div id="edit-resource" class="collapse">
							<jsp:include page="/WEB-INF/jspf/form/edit-resource-form.jspf" />
						</div>
					</logic:messagesNotPresent --!>

				</shiro:hasRole>

				<div class="panel panel-default">
					<div class="panel-heading">Details</div>
					<div class="panel-body">
						<label>Name:</label>
						<p>${resource.name}</p>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>