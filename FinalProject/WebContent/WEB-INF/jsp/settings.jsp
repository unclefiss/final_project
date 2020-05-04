<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html>

<title><fmt:message key="settings_jsp.title" /></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>

	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div class="container">

		<div class="row">
			<div class="col-md-3  mx-2"></div>
			<div class="col-md-6  card mx-2">
				<form class="form-signin" id="settings_form" action="controller"
					method="get">
					<input type="hidden" name="command" value="updateSettings" />
					<div class="row">
						<div class="col-md-6">
							<div>
								<p style="text-align: center">
									<fmt:message key="settings_jsp.label.localization" />
								</p>
								<select name="sessionLocale" class="form-control my-2">
									<option value="${sessionScope.lang}"><fmt:message
											key="settings_jsp.label.default" />
									</option>
									<option value="en"><fmt:message
											key="settings_jsp.label.english" /></option>
									<option value="ru"><fmt:message
											key="settings_jsp.label.russian" /></option>
								</select>
							</div>

							<div>
								<p style="text-align: center">
									<fmt:message key="settings_jsp.label.first_name" />
								</p>
								<input name="firstName" class="form-control my-2">
							</div>

							<div>
								<p style="text-align: center">
									<fmt:message key="settings_jsp.label.last_name" />
								</p>
								<input name="lastName" class="form-control my-2">
							</div>


						</div>
						<div class="col-md-6">

							<div>
								<p style="text-align: center">
									<fmt:message key="settings_jsp.label.login" />
								</p>
								<input name="newLogin" class="form-control my-2">
							</div>

							<div>
								<p style="text-align: center">
									<fmt:message key="settings_jsp.label.new_password" />
								</p>
								<input name="newPassword" class="form-control my-2">
							</div>
							<div>
								<p style="text-align: center">
									<fmt:message key="settings_jsp.label.repeat_new_password" />
								</p>
								<input name="newPasswordRepeat" class="form-control my-2">
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-block btn-outline-danger">
						<fmt:message key="settings_jsp.button.update" />
					</button>
				</form>
			</div>
			<div class="col-md-3  mx-2"></div>
		</div>


	</div>
</body>
</html>