<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html>

<title><fmt:message key="nurse_list_jsp.title" /></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header_bt.jspf"%>
	<div class="white">
		<div role="main" class="container">
			<div class="col-md-8 card p-2">

				<table class="table table-striped">
					<thead>
						<tr>
							<th>№</th>
							<th><fmt:message
									key="nurse_list_jsp.table.header.first_name" /></th>
							<th><fmt:message key="nurse_list_jsp.table.header.last_name" /></th>
							<th><fmt:message key="nurse_list_jsp.table.header.position" /></th>
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
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%-- CONTENT --%>
				<a href="controller?command=viewAddNurse"
					class="btn btn-success btn-block" role="button" aria-pressed="true"><fmt:message
						key="nurse_list_jsp.button.add_new_nurse" /></a>
			</div>
		</div>
	</div>


</body>