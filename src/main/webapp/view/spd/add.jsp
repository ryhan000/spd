<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Новый контрагент</title>

<div class="container-fluid">

	<c:set var="address" value="${spd.address}" scope="page" />
	<c:set var="registrationInfo" value="${spd.registrationInfo}" scope="page" />

	<form class="form" role="form" action="spd" method="post">
		<input type="hidden" name="add"> 
		<input type="hidden" name="id" value="${spd.id}">

		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="spds">Список СПД</a> 
					<span class="breadcrumb-item active"><b>Добавление нового контрагента</b></span>
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

		<div id="accordion" role="tablist" aria-multiselectable="true">
			<div class="card">
				<div class="card-header" role="tab" id="headingOne">
					<h5 class="mb-0">
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne"> Основные данные </a>
					</h5>
				</div>
				<div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<label for="surname" class="col-sm"><b>Фамилия</b></label> <input type="text" class="form-control" id="surname"
									name="surname" placeholder="Введите фамилию">
							</div>
							<div class="col">
								<label for="firstname" class="col-sm"><b>Имя</b></label> <input type="text" class="form-control" id="firstname"
									name="firstname" placeholder="Введите имя">
							</div>
							<div class="col">
								<label for="lastname" class="col-sm"><b>Отчество</b></label> <input type="text" class="form-control"
									id="lastname" name="lastname" placeholder="Введите отчество">
							</div>
						</div>
						<p>
						<div class="row">
							<div class="col-8">
								<label for="alias"><b>Короткое ФИО</b></label> <input type="text" class="form-control" id="alias" name="alias"
									placeholder="Введите короткое ФИО">
							</div>
							<div class="col-4">
								<label for="inn"><b>ИНН</b></label> <input type="text" class="form-control" id="inn" name="inn"
									placeholder="Введите идентификационный номер">
							</div>
						</div>
						<p>
						<div class="row">
							<div class="col">
								<label for="passport"><b>Паспорт</b></label> <input type="text" class="form-control" id="passport"
									name="passport" placeholder="Введите паспортные данные" >
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card">
				<div class="card-header" role="tab" id="headingTwo">
					<h5 class="mb-0">
						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
							aria-controls="collapseTwo"> Адрес деятельности </a>
					</h5>
				</div>
				<div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
					<div class="card-body">
						<div class="row">
							<div class="col-3">
								<label for="country"><b>Страна</b></label> <input type="text" class="form-control" id="country" name="country"
									placeholder="Страна">
							</div>
							<div class="col">
								<label for="region"><b>Область</b></label> <input type="text" class="form-control" id="region" name="region"
									placeholder="Область">
							</div>
							<div class="col">
								<label for="city"><b>Населенный пункт</b></label> <input type="text" class="form-control" id="city" name="city"
									placeholder="Населенный пункт">
							</div>
						</div>
						<p>
						<div class="row">
							<div class="col-6">
								<label for="street"><b>Улица</b></label> <input type="text" class="form-control" id="street" name="street"
									placeholder="Улица">
							</div>
							<div class="col-2"></div>
							<div class="col-1">
								<label for="building"><b>№ дома</b></label> <input type="text" class="form-control" id="building"
									name="building" placeholder="№ дома">
							</div>
							<div class="col-1">
								<label for="flat"><b>Квартира</b></label> <input type="text" class="form-control" id="flat" name="flat"
									placeholder="№ кв.">
							</div>
							<div class="col-2">
								<label for="zip"><b>Индекс</b></label> <input type="text" class="form-control" id="zip" name="zip"
									placeholder="Индекс">
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="card">
				<div class="card-header" role="tab" id="headingThree">
					<h5 class="mb-0">
						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false"
							aria-controls="collapseThree"> Информация о гос. регистрации </a>
					</h5>
				</div>
				<div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<label for="description"><b>Запись в ЕГРПОУ</b></label>
								<input type="text" class="form-control" id="description" name="description" placeholder="Описание">
							</div>
							<div class="col-2">
								<label for="dated"><b>Дата</b></label>
								<input type="date" class="form-control" id="dated" name="dated" placeholder="Дата" >
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />