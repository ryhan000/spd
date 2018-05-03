<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-datepicker3.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet">

</head>

<body style="background-color: #FFFFFF;">

<nav class="navbar fixed-top navbar-expand-lg navbar-dark" style="background-color: #00477e;"> <!-- colour: MidnightBlue -->

	<sec:authorize access="authenticated" var="authenticated" />
		
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<c:choose>
				<c:when test="${authenticated}">
					<span class="navbar-brand mb-0">
						<i class="fa fa-leaf"></i> SPDApp2
					</span>
					
					<sec:authentication property="name" var="userName"/>
					
					<c:url value="/user?edit=&name=${userName}" var="urlUser"></c:url>
					<c:url value="/logout" var="urlLogout"></c:url>
					<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active">
								<c:url value="/main" var="urlMain"></c:url>
								<a class="nav-item nav-link" href="${urlMain}"><i class="fa fa-home"></i> Главная</a>
							</li>
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<i class="fa fa-list"></i> Справочники
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="taxes">Налоги</a>
									<a class="dropdown-item" href="spds">Предприниматели</a>
									<a class="dropdown-item" href="companies">Компании</a>
									<a class="dropdown-item" href="agreements">Договоры с СПД</a>
								</div>
							</li>
							<sec:authorize access="hasRole('ROLE_ADMIN')" >
								<li class="nav-item active">
									<c:url value="/users" var="urlUsers"></c:url>
									<a class="nav-item nav-link" href="${urlUsers}"><i class="fa fa-users"></i> Пользователи</a>
								</li>
							</sec:authorize>
							<li class="nav-item active">
								<c:url value="/about" var="urlAbout"></c:url>
								<a class="nav-item nav-link" href="${urlAbout}"><i class="fa fa-info-circle"></i> Инфо</a>
							</li>
						</ul>
	
						<ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<c:out value="${userName}" />
								</a>
								<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="${urlUser}"><i class="fa fa-user-circle-o"></i> Профиль</a>
									<div class="dropdown-divider"></div>
									<a id ="logout" class="dropdown-item" href=""><i class="fa fa-sign-out"></i> Выход</a>
								</div>
							</li>
						</ul>
					</div>
				
					<form id="logout-form" action="<c:url value="/logout"/>" method="post">
						<sec:csrfInput/>
					</form>
				</c:when>
				<c:otherwise>
					<c:url value="/login" var="urlValueLogin" />
					<c:url value="/register" var="urlValueRegister" />
					<a class="navbar-brand" href="${urlValueLogin}">
						<i class="fa fa-leaf"></i> SPDApp2
					</a>
					<ul class="navbar-nav ml-auto">
						<li class="nav-item active">
							<a class="nav-link" href="${urlValueLogin}">
								<i class="fa fa-sign-in"></i> Войти
							</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link">/</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="${urlValueRegister}"> <i
								class="fa fa-user-plus"></i> Регистрация
						</a></li>
					</ul>
			</c:otherwise>
			</c:choose>
	</nav>
	
	<p class="p4">