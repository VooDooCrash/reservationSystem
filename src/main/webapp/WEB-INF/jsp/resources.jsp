<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader" value="Resources" />
	<tiles:putAttribute name="body">
		<div class="container">
			<shiro:hasRole name="Admin">
				<button type="button" class="btn btn-success btn-xs"
					data-toggle="collapse" data-target="#add-resource-div"
					style="margin-bottom: 10px">
					<spring:message code="btn.resource.add" />
				</button>


				<!-- logic:messagesPresent>
					<div id="add-resource-div" class="collapse in">
						<h3>Add resource</h3>
						<jsp:include page="/WEB-INF/jspf/form/edit-resource-form.jspf" /></div>
				</logic:messagesPresent --!>
				<!-- logic:messagesNotPresent>
					<div id="add-resource-div" class="collapse">
						<h3>Add resource</h3>
						<jsp:include page="/WEB-INF/jspf/form/edit-resource-form.jspf" /></div>
				</logic:messagesNotPresent --!>
			</shiro:hasRole>

			<jsp:include page="/WEB-INF/jspf/table/resources-table.jspf" />
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>