<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Пользователи</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Пользователи</b></span>
	</nav>
	
	<form action="user" method="get">
		<input type="hidden" name="add"> 
		<button type="submit" class = "btn btn-primary">
			<i class="fa fa-plus" ></i><c:out value=" Новый пользователь" />
		</button>
	</form>
	
	<p>

	<table class="table table-sm table-hover">
	
		<c:set var="totalUserCount" />
	
		<thead class="thead-default">
			<tr>
				<th class="text-center">Логин</th>
				<th class="text-center">Права доступа</th>
				<th></th>
			</tr>
		</thead>

		<c:forEach items="${users}" var="user">
			<tr>
				<td class="text-center" onclick="goToAddress('${user.url}')">${user.username}</td>
				<td class="text-center" onclick="goToAddress('${user.url}')">${user.role}</td>
				<td class="text-center">
					<div class="d-flex justify-content-end">
						<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
							<div class="btn-group mr-2" role="group" aria-label="First group">
								<a class="btn btn-success btn-sm" href="${user.url}" role="button">
									<i class="fa fa-edit"></i> Подробнее
								</a>
							</div>
							<div class="btn-group mr-2" role="group" aria-label="Second group">
								<form action="user" method="post">
									<input type="hidden" name="delete"> 
									<input type="hidden" name="id" value="${user.id}"> 
									<sec:csrfInput/>
									<button type="submit" class="btn btn-danger btn-sm">
										<i class="fa fa-trash-o"></i> Удалить
									</button>
								</form>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<c:set var="totalUserCount" value="${totalUserCount + 1}" />
		</c:forEach>
		<thead class="thead-default">
			<tr>
				<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalUserCount}" /></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
	</table>

</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />