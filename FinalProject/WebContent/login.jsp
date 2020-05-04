<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<title><fmt:message key="login_jsp.title" /></title>

<!-- Bootstrap core CSS -->
<link href="./Signin Template for Bootstrap_files/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./Signin Template for Bootstrap_files/signin.css"
	rel="stylesheet">
<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body class="text-center">
	<div class="mallbery-ext-install"
		style="z-index: 2147483647 !important; text-transform: none !important; margin-top: 10%"></div>
	<div id="main-container">
		<div
			class="col-md-4  mx-auto my-6 justify-content-end align-items-center">
			<form class="form-signin" action="controller" method="post">
				<input type="hidden" name="command" value="login" />
				<h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login_jsp.please_login" /></h1>
				<label for="inputEmail" class="sr-only"><fmt:message key="login_jsp.label.login" /></label> <input
					name="login" id="inputEmail" class="form-control"
					placeholder="<fmt:message key="login_jsp.label.login" />" required="" autofocus=""> <label
					for="inputPassword" class="sr-only"><fmt:message key="login_jsp.label.pasword" /></label> <input
					name="password" type="password" id="inputPassword"
					class="form-control" placeholder="<fmt:message key="login_jsp.label.password" />" required="">
				<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login_jsp.button.login" /></button>
			</form>
		</div>
	</div>
</body>

</html>