<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page import="ua.nure.chernev.FinalTask.db.entity.Status"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html>
<title><fmt:message key="patient_list_jsp.title" /></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div class="bg-white">
		<div class="container card">
			<div class="row">
				<div class="col-md-8 align-items-center">
					<div class="card p-2 my-2">
						<form class="form-inline" action="controller" method="post">
							<input type="hidden" name="command" value="listPatients" /> <label
								for="sortSelest" class="mr-sm-4 mt-3"><fmt:message
									key="patient_list_jsp.form.sort.sort_by" /></label> <select
								name="sortId" class="form-control mt-3 mr-sm-4" id="sortSelest">
								<option value="0"><fmt:message
										key="patient_list_jsp.form.sort.alphabet" /></option>
								<option value="1"><fmt:message
										key="patient_list_jsp.form.sort.birthday" /></option>
							</select> <select name="sortDirection" class="form-control mt-3  mr-sm-4"
								id="sortSelest">
								<option value="1">^</option>
								<option value="-1">v</option>
							</select>
							<button type="submit" class="btn btn-primary mt-3">
								<fmt:message key="patient_list_jsp.button.sort" />
							</button>
						</form>
					</div>

					<table class="table table-striped">
						<thead>
							<tr>
								<th>№</th>
								<th><fmt:message
										key="patient_list_jsp.table.header.first_name" /></th>
								<th><fmt:message
										key="patient_list_jsp.table.header.last_name" /></th>
								<th><fmt:message
										key="patient_list_jsp.table.header.birthday" /></th>
								<th><fmt:message
										key="patient_list_jsp.table.header.phonenumber" /></th>
								<th><fmt:message key="patient_list_jsp.table.header.status" /></th>
								<th><fmt:message
										key="patient_list_jsp.table.header.check_card" /></th>
							</tr>
						</thead>
						<tbody>
							<c:set var="k" value="0" />
							<c:forEach var="item" items="${patients}">
								<c:set var="k" value="${k+1}" />
								<tr>
									<td><c:out value="${k}" /></td>
									<td>${item.firstName}</td>
									<td>${item.lastName}</td>
									<td>${item.birthday}</td>
									<td>${item.phonenumber}</td>
									<td><m:status_name status="${patientStatuses}"
											lang="${lang}" statusId="${item.statusId}" /></td>
									<td><form action="controller" method="post">
											<input type="hidden" name="command" value="patientCard" /> <input
												type="hidden" name="currentPatientId" value="${item.id}" />
											<div class="mx-ms-2">
													<button type="submit" class="btn btn-outline-secondary">
														<fmt:message key="patient_list_jsp.button.check_card" />
													</button>
											</div>
										</form></td>
										<c:if test="${userRole.name == 'admin'}">
									<td><form action="controller" method="post">
											<input type="hidden" name="command" value="deletePatient" />
											<input type="hidden" name="patientId" value="${item.id}" />
											<div class="form-group row">
													<button type="submit" class="btn btn-outline-danger">
														<fmt:message key="patient_list_jsp.button.delete" />
													</button>
											</div>
										</form></td>
										</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:if test="${userRole.name == 'admin'}">
						<a href="controller?command=viewAddPatient"
							class="btn btn-success btn-block" role="button"
							aria-pressed="true"><fmt:message
								key="patient_list_jsp.button.add_new_patient" /></a>
					</c:if>
					<div class="p-4 my-2"></div>

				</div>
				<c:if test="${userRole.name == 'admin'}">
					<div class="col-md-4 p-2">
						<div class="card flex-md-row shadow-sm ">
							<div class="card-body">
								<form action="controller" method="post">
									<input type="hidden" name="command" value="appointDoctor" /> <input
										type="hidden" name="page" value="patient" />
									<h1 class="h3 mb-3 font-weight-normal">
										<fmt:message
											key="patient_list_jsp.form.appoint.appoint_doctor" />
									</h1>
									<div class="form-group row">
										<label for="doctorsBean" class="col-sm-4 col-form-label"><fmt:message
												key="patient_list_jsp.form.appoint.select_doctor" /></label>
										<div class="col-sm-8">
											<select name="doctorId" class="form-control" id="doctorsBean">
												<option value="0">---</option>
												<c:set var="c" value="0" />

												<c:forEach var="doctor" items="${doctorsBean}">
													<option value="${doctor.id}">${doctor.firstName}
														${doctor.lastName}
														<m:status_name status="${categories}" lang="${lang}"
															statusId="${doctor.categoryId}" />
													</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label for="patientsBean" class="col-sm-4 col-form-label"><fmt:message
												key="patient_list_jsp.form.appoint.select_patient" /></label>
										<div class="col-sm-8">
											<select name="patientId" class="form-control"
												id="patientsBean">
												<option value="0">---</option>
												<c:set var="c" value="0" />
												<c:forEach var="patient" items="${patientsBean}">
													<option value="${patient.id}">${patient.firstName}
														${patient.lastName}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-10">
											<button type="submit" class="btn btn-primary">
												<fmt:message key="patient_list_jsp.button.appoint" />
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</c:if>
			</div>

		</div>
	</div>
</body>