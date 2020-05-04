<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html>

<title><fmt:message key="card_jsp.title" /></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body bgcolor="#000000">
	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div class="row">
		<div role="main" class="container" style="height: 100%">


			<div class="row">
				<%-- CONTENT --%>
				<div class="col-md-8 align-items-center">
					<div class="card flex-md-row shadow-sm ">
						<div class="card-body">
							<div class="row">
								<div class="col-md-4 align-items-center">
									<img class="img-fluid" src="https://i.imgur.com/b29WYCs.png">
								</div>
								<div class="col-md-8 align-items-center">
									<h2>${currentPatient.firstName}</h2>
									<h2>${currentPatient.lastName}</h2>
									<fmt:message key="card_jsp.birthday" />
									: ${currentPatient.birthday} <br>
									<fmt:message key="card_jsp.phonenumber" />
									: ${currentPatient.phonenumber}<br>

									<fmt:message key="card_jsp.attendingDoctor" />
									: ${attendingDoctor.firstName} ${attendingDoctor.lastName }<br>

									<fmt:message key="card_jsp.dateRegistration" />
									: ${currentPatient.dateRegistration }<br>
									<c:if test="${currentPatient.statusId == 2 }">
										<fmt:message key="card_jsp.dateDischarge" />
									: ${currentPatient.dateDischarge }<br>
									</c:if>

								</div>
							</div>
							<c:if test="${prescriptionsEmpty == true}">
								<h4 style="text-align: center">
									<fmt:message key="card_jsp.no_therapy" />
								</h4>
							</c:if>
							<table class="table  mt-2">
								<c:if test="${prescriptionsEmpty == false}">
									<tr>
										<th colspan="5" style="text-align: center"><h4>
												<fmt:message key="card_jsp.therapy" />
											</h4></th>
									</tr>
									<tr>
										<th>#</th>
										<th><fmt:message key="card_jsp.table.diagnosis" /></th>
										<th><fmt:message key="card_jsp.table.prescription" /></th>
										<th><fmt:message key="card_jsp.table.type" /></th>
										<th><fmt:message key="card_jsp.table.status" /></th>
									</tr>
								</c:if>
								<c:set var="k" value="0" />
								<c:forEach var="item" items="${prescriptions}">
									<c:set var="k" value="${k+1}" />
									<tr>
										<td>${k}</td>
										<td>${item.diagnosisComment}</td>
										<td>${item.comment}</td>
										<td><m:status_name status="${types}" lang="${lang}"
												statusId="${item.typeId}" /></td>
										<td><m:status_name status="${prescriptionStatuses}"
												lang="${lang}" statusId="${item.statusId}" /></td>

									</tr>
								</c:forEach>
							</table>
							<c:if test="${currentPatient.statusId != 2 }">
								<c:if test="${userRole.name  == 'doctor'}">
									<a href="controller?command=viewAddInterimDiagnosis"
										class="btn btn-info btn-block" role="button"
										aria-pressed="true"><fmt:message
											key="card_jsp.button.add_new_interim_diagnosis" /></a>
									<div class="md-2">
										<hr>
									</div>
									<h4 style="text-align: center">
										<fmt:message key="card_jsp.final_diagnosis" />
									</h4>
									<div class="mx-md-2">
										<hr>
									</div>
									<form action="controller" method="post">
										<input type="hidden" name="command" value="addFinalDiagnosis">
										<input type="text" name="finalDiagnosis" class="form-control"
											id="finalDiagnosis" required>
										<button type="submit"
											class="btn btn-success btn-block my-md-2">
											<fmt:message key="card_jsp.button.discharge" />
										</button>
									</form>
								</c:if>
							</c:if>
							<c:if test="${currentPatient.statusId == 2 }">
								<hr>
								<h4>
									<fmt:message key="card_jsp.final_diagnosis" />
									:
								</h4>
									${finalDiagnosis.comment}
								<hr>
							</c:if>
						</div>
					</div>
					<c:if test="${currentPatient.statusId == 2 }">
						<div>
							<form action="controller">
								<input type="hidden" name="command" value="downloadCard">
								<button type="submit" class="btn btn-success btn-block my-md-2">
									<h4>
										<fmt:message key="card_jsp.download" />
									</h4>
								</button>
							</form>
						</div>
					</c:if>

					<div class="row mb-2">
						<div class="col-md-6">
							<div class="card flex-md-row mb-4 shadow-sm h-md-250"></div>
						</div>
					</div>
				</div>


			</div>

		</div>
	</div>


</body>