<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader" value="Users" />
	<tiles:putAttribute name="body">
		<div class="container">

			<shiro:hasRole name="Admin">
				<button type="button" class="btn btn-success btn-xs"
					data-toggle="collapse" data-target="#add-user-div"
					style="margin-bottom: 10px">
					<spring:message code="btn.user.add" />
				</button>

				<c:set var="errors">
					<form:errors />
				</c:set>

				<div id="add-user-div" class="collapse">
					<h3>Add user</h3>
					<jsp:include page="/WEB-INF/jspf/form/edit-user-form.jspf">
						<jsp:param value="add" name="action" />
					</jsp:include>
				</div>
				<!-- logic:messagesNotPresent>
					<div id="add-user-div" class="collapse">
						<h3>Add user</h3>
						<jsp:include page="/WEB-INF/jspf/form/edit-user-form.jspf">
							<jsp:param value="add" name="action" />
						</jsp:include>
					</div>
				</logic:messagesNotPresent --!>
			</shiro:hasRole>

			<jsp:include page="/WEB-INF/jspf/table/users-table.jspf" />
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>