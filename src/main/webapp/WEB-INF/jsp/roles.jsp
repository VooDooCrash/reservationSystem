<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader" value="Roles" />
	<tiles:putAttribute name="body">
		<div class="container">

			<shiro:hasRole name="Admin">
				<button type="button" class="btn btn-success btn-xs"
					data-toggle="collapse" data-target="#add-role-div"
					style="margin-bottom: 10px">
					<spring:message code="btn.role.add" />
				</button>

				<!-- logic:messagesPresent>
					<div id="add-role-div" class="collapse in">
						<h3>Add role</h3>
						<jsp:include page="/WEB-INF/jspf/form/edit-role-form.jspf" />
					</div>
				</logic:messagesPresent --!>
				<!-- logic:messagesNotPresent>
					<div id="add-role-div" class="collapse">
						<h3>Add role</h3>
						<jsp:include page="/WEB-INF/jspf/form/edit-role-form.jspf" />
					</div>
				</logic:messagesNotPresent --!>
			</shiro:hasRole>

			<jsp:include page="/WEB-INF/jspf/table/roles-table.jspf" />
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>