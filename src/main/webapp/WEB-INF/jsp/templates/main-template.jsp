<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:set var="ctx" >
	<c:url value="/"/>
</c:set>

<link rel="stylesheet" href="${ctx}css/bootstrap.css">
<link rel="stylesheet" href="${ctx}css/jquery-ui-1.10.3.custom.css">
<link rel="stylesheet" href="${ctx}css/jquery-ui-timepicker-addon.css">
<link rel="stylesheet" href="${ctx}css/select2.css">
<link rel="stylesheet" href="${ctx}css/selectize.bootstrap3.css">
<link rel="stylesheet" href="${ctx}css/timeline.css">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- jQuery -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script type="text/javascript" src="${ctx}js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${ctx}js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${ctx}js/select2.js"></script>
<script type="text/javascript" src="${ctx}js/selectize.js"></script>

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="${ctx}js/timeline.js"></script>

</head>

<body>
	<div id="wrap">
		<div class="row">
			<div id="header" class="page-header">
				<div class="row">
					<div class="col-lg-2 col-lg-offset-2 col-md-3 col-sm-3">
						<a href="${ctx}users.do"><img
							src="/css/images/logo.png"
							style="padding-left: 30px"></a>
					</div>
					<div class="col-lg-6 col-md-9 col-sm-8">
						<h1 class="text-center">
							Reservation System <small><tiles:getAsString
									name="smallHeader" defaultValue="" /></small>
						</h1>
					</div>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-lg-2 col-lg-offset-2 col-md-3 col-sm-3">
				<tiles:insertAttribute name="menu"
					defaultValue="/WEB-INF/jsp/commons/menu.jsp" />
			</div>
			<div class="col-lg-6 col-md-9 col-sm-8">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<tiles:insertAttribute name="footer"
				defaultValue="/WEB-INF/jsp/commons/footer.jsp" />
	</div>
</body>
</html>