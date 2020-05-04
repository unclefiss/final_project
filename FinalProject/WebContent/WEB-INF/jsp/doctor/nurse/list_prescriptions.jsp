<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html>

<title><fmt:message key="prescriptions_list_jsp.title" /></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div class="bg-white">
		<div class="container">
			<div class="card p-2">
				<div class="col-md-8">
					<h3 style="text-align: center">
						<fmt:message key="prescriptions_list_jsp.undone" />
					</h3>
					<hr>
				</div>
				<div class="row">

					<div class="col-md-8">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>№</th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.first_name" /></th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.last_name" /></th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.diagnosis" /></th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.comment" /></th>
									<th><fmt:message key="prescriptions_list_jsp.table.type" /></th>
									<th><fmt:message key="prescriptions_list_jsp.table.action" /></th>
								</tr>
							</thead>
							<tbody>
								<c:set var="k" value="0" />
								<c:forEach var="item" items="${patientPrescriptionBeansUndone}">
									<c:set var="k" value="${k+1}" />
									<tr>
										<td><c:out value="${k}" /></td>
										<td>${item.patientFirstName}</td>
										<td>${item.patientLastName}</td>
										<td>${item.diagnosisComment}</td>
										<td>${item.prescriptionComment}</td>
										<td>
										<m:status_name status="${types}" lang="${lang}" statusId="${item.typeId}" /></td>
										<td>
											<form action="controller" method="post">
												<input type="hidden" name="command" value="doPrescription" />
												<c:set var="ID" value="${item.prescriptionId}" />
												<input type="hidden" name="id"
													value="${item.prescriptionId}" />
												<button type="submit" class="btn btn-outline-success">
													<fmt:message key="prescriptions_list_jsp.table.button" />
												</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-md-8">
					<h3 style="text-align: center">
						<fmt:message key="prescriptions_list_jsp.done" />
					</h3>
					<hr>
				</div>
				<div class="row">

					<div class="col-md-8">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>№</th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.first_name" /></th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.last_name" /></th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.diagnosis" /></th>
									<th><fmt:message
											key="prescriptions_list_jsp.table.comment" /></th>
									<th><fmt:message key="prescriptions_list_jsp.table.type" /></th>
								</tr>
							</thead>
							<tbody>
								<c:set var="k" value="0" />
								<c:forEach var="item" items="${patientPrescriptionBeansDone}">
									<c:set var="k" value="${k+1}" />
									<tr>
										<td><c:out value="${k}" /></td>
										<td>${item.patientFirstName}</td>
										<td>${item.patientLastName}</td>
										<td>${item.diagnosisComment}</td>
										<td>${item.prescriptionComment}</td>
										<td><m:status_name status="${types}" lang="${lang}" statusId="${item.typeId}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>