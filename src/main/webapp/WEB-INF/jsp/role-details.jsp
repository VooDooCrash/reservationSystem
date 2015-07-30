<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader" value="Roles" />
	<tiles:putAttribute name="body">

		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12">

					<shiro:hasRole name="Admin">

						<button class="btn btn-danger btn-xs"
							onclick="removeRole(${param.id});">Remove</button>

						<form id="remove-role-form" action="${ctx}remove-role.do" method="post"
							hidden="true" style="margin-bottom: 0em;">
							<input type="hidden" id="roleId" name="id">
						</form>

						<script type="text/javascript">
							function removeRole(id) {
								document.getElementById('roleId').value = id;
								document.getElementById('remove-role-form')
										.submit();
							}
						</script>

						<button type="button" class="btn btn-success btn-xs"
							data-toggle="collapse" data-target="#edit-role">
							<spring:message code="btn.role.edit" />
						</button>

						<!-- logic:messagesPresent>
							<div id="edit-role" class="collapse in">
								<jsp:include page="/WEB-INF/jspf/form/edit-role-form.jspf" />
							</div>
						</logic:messagesPresent --!>
						<!-- logic:messagesNotPresent>
							<div id="edit-role" class="collapse">
								<jsp:include page="/WEB-INF/jspf/form/edit-role-form.jspf" />
							</div>
						</logic:messagesNotPresent --!>

					</shiro:hasRole>

					<div class="panel panel-default">
						<div class="panel-heading">Details</div>
						<div class="panel-body">
							<label>Name:</label>
							<p>${role.name}</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<jsp:include page="/WEB-INF/jspf/table/users-table.jspf" />
				</div>
			</div>

		</div>

	</tiles:putAttribute>
</tiles:insertTemplate>