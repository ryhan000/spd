<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Пользователь ${user.username}</title>

<div class="container-fluid">

	<form class="form" role="form" action="user" method="post">
		<input type="hidden" name="edit"> 
		<input type="hidden" name="name" value="${user.username}">

		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="users">Пользователи</a> 
					<span class="breadcrumb-item active"><b>${user.username}</b></span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<sec:csrfInput/>
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="spds" role="button">Отмена</a>
				</div>
			</div>
		</nav>

		<div class="row">
			<div class="col-4">
				<label for="firstName" class="col-sm"><b>Имя</b></label>
				<input type="text" class="form-control" id=firstName name="firstName"
					value="${user.firstName}">
			</div>
			
			<div class="col-4">
				<label for="lastname" class="col-sm"><b>Фамилия</b></label>
				<input type="text" class="form-control" id="lastName" name="lastName"
					value="${user.lastName}">
			</div>
			
			<sec:authorize access="hasRole('ROLE_ADMIN')" >
				<div class="col-2">
					<label for="role"><b>Роль</b></label>
					<select class="form-control" id="role">
						<option>USER_ROLE</option>
						<option>ADMIN_ROLE</option>
					</select> 
				</div>
			</sec:authorize>
		</div>
		<p>
		<div class="row">
			<div class="col-4">
				<label for="username"><b>Логин</b></label>
				<input type="text" class="form-control" id="username" name="username"
					value="${user.username}">
			</div>
			<div class="col-4">
				<label for="password"><b>Пароль</b></label>
				<input type="password" class="form-control" id="password" name="password"
					value="">
			</div>
			
		</div>
		<p>
		<div class="row">
			<div class="col-4">
				<label for="email"><b>E-mail</b></label>
				<input type="email" class="form-control" id="email" name="email"
					value="${user.email}">
			</div>
			<sec:authorize access="hasRole('ROLE_ADMIN')" >
				<div class="col-4">
					<div class="form-check">
						<label class="enabled">
							<input class="form-check-input" type="checkbox" value="checked" checked> 
								активный
						</label>
					</div>
				</div>
			</sec:authorize>
		</div>
		<div class="row">
			
		</div>
					
	</form>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />