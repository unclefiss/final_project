<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html>

<title><fmt:message key="add_interim_diagnosis_jsp.title" /></title>
<c:set var="title" value="Interim diagnosis" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body class="text-center">
	<div role="main" class="container">

		<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>


		<div class="col-md-4   justify-content align-items-center">
			<form class="form-signin" action="controller" method="post">
				<input type="hidden" name="command" value="addInterimDiagnosis" />
				<div class="form-group row">
					<label for="diagnosis" class="col-sm-4 col-form-label">
											<fmt:message key="add_interim_diagnosis_jsp.diagnosis" /></label>
					<div class="col-sm-8">
						<input type="text" name="interimDiagnosis" class="form-control" id="diagnosis"
							 required>
					</div>
				</div>
				<div class="form-group row">
					<label for="prescription" class="col-sm-4 col-form-label"><fmt:message key="add_interim_diagnosis_jsp.prescription" /> </label>
					<div class="col-sm-8">
						<input name="prescriptionComment" class="form-control" id="prescription"
							 required>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputFirstName" class="col-sm-4 col-form-label"><fmt:message key="add_interim_diagnosis_jsp.prescription" />
						</label>
					<div class="col-sm-8">
						<select name="typeId" class="form-control">
						<c:forEach  var="type" items="${types}">
						<option value="${type.id }"> <m:status_name status="${types}" lang="${lang}" statusId="${type.id}" /></option>
						</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-sm-10">
						<button type="submit" class="btn btn-primary"><fmt:message key="add_interim_diagnosis_jsp.button" /></button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>