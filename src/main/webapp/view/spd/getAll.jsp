<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Предприниматели</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Предприниматели</b></span>
	</nav>

	<form action="spd" method="get">
		<input type="hidden" name="add">
		<button type="submit" class="btn btn-primary">
			<i class="fa fa-plus"></i>
			<c:out value=" Новый контрагент" />
		</button>
	</form>

	<p>
	<div class="row">
		<div class="col">
			<table class="table table-sm table-hover">
			
				
				<c:set var="totalSpdCount" />
				
				<thead class="thead-default">
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">ФИО</th>
						<th class="text-center">ИНН</th>
						<th class="text-center"></th>
					</tr>
				</thead>
		
				<c:forEach items="${spds}" var="spd">
					<tr>
						<td class="text-center" onclick="goToAddress('${spd.url}')"><c:out value="${spd.id}" /></td>
						<td class="text-center" onclick="goToAddress('${spd.url}')"><c:out value="${spd.surname} ${spd.firstname} ${spd.lastname}" /></td>
						<td class="text-center" onclick="goToAddress('${spd.url}')"><c:out value="${spd.inn}" /></td>
						<td class="text-center">
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
		
										<div class="btn-group mr-2" role="group" aria-label="First group">
											<a class="btn btn-success btn-sm" href="${spd.url}" role="button"><i class="fa fa-edit"></i> Подробнее</a>
										</div>
										<div class="btn-group mr-2" role="group" aria-label="Second group">
											<form action="spd" method="post">
												<input type="hidden" name="delete"> <input type="hidden" name="id" value="${spd.id}">
												<sec:csrfInput />
												<sec:authorize access="hasRole('ROLE_ADMIN')" >
													<button type="submit" class="btn btn-danger btn-sm">
														<i class="fa fa-trash-o"></i> Удалить
													</button>
												</sec:authorize>
											</form>
										</div>
		
									</div>
								</div>
						</td>
					</tr>
					<c:set var="totalSpdCount" value="${totalSpdCount + 1}" />
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalSpdCount}" /></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />