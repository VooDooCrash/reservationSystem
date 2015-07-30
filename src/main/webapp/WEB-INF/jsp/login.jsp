<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tiles:insertTemplate
	template="/WEB-INF/jsp/templates/main-template.jsp">
	<tiles:putAttribute name="smallHeader"
		value="Login" />
	<tiles:putAttribute name="body">
		<div class="container">
			<jsp:include page="/WEB-INF/jspf/form/login-form.jspf" />
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>