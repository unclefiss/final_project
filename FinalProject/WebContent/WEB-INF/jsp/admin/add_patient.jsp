<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<title><fmt:message key="add_patient_jsp.title" /></title>

<!-- Bootstrap core CSS -->
<link href="./Signin Template for Bootstrap_files/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./Signin Template for Bootstrap_files/signin.css"
	rel="stylesheet">
<html>

<c:set var="title" value="Nurses" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body class="text-center">
<body>
	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div role="main" class="container">


		<c:if test="${loginExist == true}">
			<div class="alert alert-danger" role="alert">
				<fmt:message key="add_patient_jsp.form.login_is_already_taken" />
			</div>
		</c:if>


		<div class="col-md-5   justify-content align-items-center">
			<form class="form-signin" action="controller" method="post">
				<input type="hidden" name="command" value="addPatient" />
				<div class="form-group row">
					<label for="inputLogin" class="col-sm-4 col-form-label"><fmt:message
							key="add_patient_jsp.form.login" /></label>
					<div class="col-sm-8">
						<input name="newLogin" class="form-control" id="inputLogin"
							required>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-4 col-form-label"><fmt:message
							key="add_doctor_jsp.form.password" /></label>
					<div class="col-sm-8">
						<input type="password" name="newPassword" class="form-control" id="inputPassword3"
							required>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-4 col-form-label"><fmt:message
							key="add_doctor_jsp.form.password" /></label>
					<div class="col-sm-8">
						<input type="password" name="newPasswordRepeat" class="form-control" id="inputPassword3"
							required>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputFirstName" class="col-sm-4 col-form-label"><fmt:message
							key="add_patient_jsp.form.first_name" /></label>
					<div class="col-sm-8">
						<input name="newFirstName" class="form-control"
							id="inputFirstName" required>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputLastName" class="col-sm-4 col-form-label"><fmt:message
							key="add_patient_jsp.form.last_name" /></label>
					<div class="col-sm-8">
						<input name="newLastName" class="form-control" id="inputLastName"
							required>
					</div>
				</div>

				<div class="form-group row">
					<label for="newBirthday" class="col-sm-4 col-form-label"><fmt:message
							key="add_patient_jsp.form.birthday" /></label>
					<div class="col-sm-8">
						<input type="date" name="newBirthday" class="form-control"
							id="inputLastName" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="newPhonenumber" class="col-sm-4 col-form-label"><fmt:message
							key="add_patient_jsp.form.phonenumber" /></label>
					<div class="col-sm-8">
						<input type="tel" name="newPhonenumber" class="form-control" placeholder="(0**)*******"
							id="inputLastName" required pattern="^\(0\d{2}\)\d{7}$">
					</div>
				</div>

				<div class="form-group row">
					<div class="col-sm-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="add_patient_jsp.form.button" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>