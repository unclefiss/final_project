<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<title><fmt:message key="add_doctor_jsp.title" /></title>

<html>

<c:set var="title" value="Doctors" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body class="text-center">
<body>
	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div role="main" class="container">

		<div class="col-md-5   justify-content align-items-center">
		
		
		
			<form class="form-signin" action="controller" method="post">
				<input type="hidden" name="command" value="addDoctor" /> <input
					type="hidden" name="command" value="login" />

				<div class="form-group row">
					<label for="inputLogin" class="col-sm-4 col-form-label"><fmt:message
							key="add_doctor_jsp.form.login" /></label>
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
							key="add_doctor_jsp.form.first_name" /></label>
					<div class="col-sm-8">
						<input name="newFirstName" class="form-control"
							id="inputFirstName" required>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputLastName" class="col-sm-4 col-form-label"><fmt:message
							key="add_doctor_jsp.form.last_name" /></label>
					<div class="col-sm-8">
						<input name="newLastName" class="form-control" id="inputLastName"
							required>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputCategory" class="col-sm-4 col-form-label"><fmt:message
							key="add_doctor_jsp.form.category" /></label>
					<div class="col-sm-8">
						<select name="newCategory" class="form-control" id="inputCategory">
							<c:forEach var="category" items="${categories}">
								<c:if test="${lang == 'en'}">
									<option value="${category.id}">${category.name}</option>
								</c:if>
								<c:if test="${lang == 'ru' || empty lang}">
									<option value="${category.id}">${category.nameRu}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-sm-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="add_doctor_jsp.form.button" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>