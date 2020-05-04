<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />


<html>
<title><fmt:message key="doctor_list_jsp.title" /></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div class="row">
		<div class="container card bg-white">
			<div class="row">
				<div class="col-md-8 align-items-center p-2">
					<div class="card text-center my-1">
						<div class="card-header ">
							<ul class="nav nav-tabs card-header-tabs">
								<c:set var="c" value="0" />
								<li class="nav-item active"><form action="controller"
										method="post">
										<input type="hidden" name="command" value="listDoctors">
										<input type="hidden" name="categoryId" value="-1">
										<button class="btn btn-link text-muted active" role="link"
											type="submit" name="op" value="Link 1">
											<fmt:message key="doctor_list_jsp.nav.all" />
										</button>
									</form></li>
								<c:forEach var="category" items="${categories}">
									<c:set var="c" value="${c+1}" />
									<li class="nav-item"><form action="controller"
											method="post">
											<input type="hidden" name="command" value="listDoctors">
											<input type="hidden" name="categoryId" value="${category.id}">
											<button class="btn btn-link text-muted" role="link"
												type="submit" name="op" value="Link 1">
												<m:status_name status="${categories}" lang="${lang}"
													statusId="${category.id}" />
											</button>
										</form></li>
								</c:forEach>
							</ul>
						</div>
						<div class="card-body">
							<form class="form-inline" action="controller" method="post">
								<input type="hidden" name="command" value="listDoctors" /> <label
									for="sortSelest" class="mr-sm-2 mt-3"><fmt:message
										key="doctor_list_jsp.form.sort.sort_by" /></label> <select
									name="sortId" class="form-control  mt-3 mr-sm-2"
									id="sortSelest">
									<option value="0"><fmt:message
											key="doctor_list_jsp.form.sort.alphabet" /></option>
									<option value="1"><fmt:message
											key="doctor_list_jsp.form.sort.patients_count" /></option>
								</select> <select name="sortDirection" class="form-control  mt-3 mr-sm-2"
									id="sortSelest">
									<option value="1">^</option>
									<option value="-1">v</option>
								</select>
								<button type="submit" class="btn btn-primary mt-3">
									<fmt:message key="doctor_list_jsp.button.sort" />
								</button>
							</form>
						</div>
					</div>

					<table class="table table-striped">
						<thead>
							<tr>
								<th>№</th>
								<th><fmt:message
										key="doctor_list_jsp.table.header.first_name" /></th>
								<th><fmt:message
										key="doctor_list_jsp.table.header.last_name" /></th>
								<th><fmt:message
										key="doctor_list_jsp.table.header.patient_count" /></th>
								<th><fmt:message
										key="doctor_list_jsp.table.header.category" /></th>
							</tr>
						</thead>
						<tbody>
							<c:set var="k" value="0" />
							<c:forEach var="item" items="${doctors}">
								<c:set var="k" value="${k+1}" />
								<tr>
									<td><c:out value="${k}" /></td>
									<td>${item.firstName}</td>
									<td>${item.lastName}</td>
									<td>${item.patientCount}</td>
									<td><m:status_name status="${categories}" lang="${lang}"
											statusId="${item.categoryId}" /></td>
									<td><form action="controller" method="post">
											<input type="hidden" name="command" value="deleteDoctor" />
											<input type="hidden" name="doctorId" value="${item.id}" />
											<div class="form-group row">
												<div class="col-sm-12">
													<button type="submit" class="btn btn-outline-danger">
														<fmt:message key="doctor_list_jsp.table.button.retire" />
													</button>
												</div>
											</div>
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<a href="controller?command=viewAddDoctor"
						class="btn btn-info btn-block p-2" role="button"
						aria-pressed="true"><fmt:message
							key="doctor_list_jsp.button.add_new_doctor" /></a>

					<div class="p-4 my-2"></div>
					<c:if test="${nursesList != false}">
						<table class="table table-striped p-2">
							<thead>
								<tr>
									<th>№</th>
									<th><fmt:message
											key="nurse_list_jsp.table.header.first_name" /></th>
									<th><fmt:message
											key="nurse_list_jsp.table.header.last_name" /></th>
									<th><fmt:message
											key="nurse_list_jsp.table.header.position" /></th>
								</tr>
							</thead>
							<tbody>
								<c:set var="k" value="0" />
								<c:forEach var="item" items="${nurses}">
									<c:set var="k" value="${k+1}" />
									<tr>
										<td><c:out value="${k}" /></td>
										<td>${item.firstName}</td>
										<td>${item.lastName}</td>
										<td><fmt:message key="nurse_list_jsp.table.nurse" /></td>
										<td><form action="controller" method="post">
												<input type="hidden" name="command" value="deleteDoctor" />
												<input type="hidden" name="doctorId" value="${item.id}" />
												<div class="form-group row">
													<div class="col-sm-12">
														<button type="submit" class="btn btn-outline-danger">
															<fmt:message key="doctor_list_jsp.table.button.retire" />
														</button>
													</div>
												</div>
											</form></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<a href="controller?command=viewAddNurse"
							class="btn btn-success btn-block p-2" role="button"><fmt:message
								key="nurse_list_jsp.button.add_new_nurse" /></a>

						<div class="p-4 my-2"></div>
					</c:if>
				</div>



				<div class="col-md-4 p-2">
					<div class=" card flex-md-row shadow-sm ">
						<div class="card-body">
							<form action="controller" method="post">
								<input type="hidden" name="command" value="appointDoctor" /> <input
									type="hidden" name="page" value="doctor" />
								<h1 class="h3 mb-3 font-weight-normal">
									<fmt:message key="doctor_list_jsp.form.appoint.appoint_doctor" />
								</h1>
								<div class="form-group row">
									<label for="doctorsBean" class="col-sm-4 col-form-label"><fmt:message
											key="doctor_list_jsp.form.appoint.select_doctor" /></label>
									<div class="col-sm-8">
										<select name="doctorId" class="form-control" id="doctorsBean">
											<option value="0">---</option>
											<c:set var="c" value="0" />
											<c:forEach var="doctor" items="${doctorsBean}">
												<option value="${doctor.id}">${doctor.firstName}
													${doctor.lastName}
													<m:status_name status="${categories}" lang="${lang}"
														statusId="${doctor.categoryId}" />
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<label for="patientsBean" class="col-sm-4 col-form-label"><fmt:message
											key="doctor_list_jsp.form.appoint.select_patient" /> </label>
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
											<fmt:message key="doctor_list_jsp.button.appoint" />
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>