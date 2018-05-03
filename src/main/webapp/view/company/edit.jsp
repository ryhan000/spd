<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title><c:out value="${company.title}" /></title>

<div class="container-fluid">

	<form class="form" role="form" action="company" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${company.id}">

		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="companies">Компании</a>
					<span class="breadcrumb-item active"><b><c:out value="${company.title}" /></b></span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<sec:csrfInput/>
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="companies" role="button">Отмена</a>
				</div>
			</div>
		</nav>
	
		<p>	
			
		<div class="row">
			<div class="col-3">
				<label for="title"><b>Название</b></label>
				<input type="text" class="form-control" id="title" name="title" 
					placeholder="Введите номер счета" value="${fn:replace(company.title, '"', '&quot;')}" >  
			</div>
			<div class="col-3">
				<label for="edrpou"><b>ЕДРПОУ</b></label>
				<input type="text" class="form-control" id="edrpou" name="edrpou" 
					placeholder="Введите наименование банка" value=<c:out value="${company.edrpou}"/> >
			</div>
			<div class="col-3">
				<label for="inn"><b>ИНН</b></label>
				<input type="text" class="form-control" id="inn" name="inn" 
					placeholder="Введите ИНН" value="${company.inn}">
			</div>
			<div class="col-3">
				<label for="vatCertificate"><b>№ свидетельства плат. НДС</b></label>
				<input type="text" class="form-control" id="vatCertificate"	name="vatCertificate" 
					placeholder="Введите № свидетельства" value="${company.vatCertificate}">
			</div>
		</div>
	
	</form>

	<!-- Nav tabs -->
	<p>
	
	<ul class="nav nav-tabs nav-fill" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#address" role="tab">Адрес</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#director" role="tab">Директор</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#account" role="tab">Банковские реквизиты</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#agreements" role="tab">Связанные договоры</a></li>
	</ul>

	<p>

	<!-- Tab panes -->
	<div class="tab-content">
		
		<!-- Tab pane 'Address'  -->
		<div class="tab-pane fade show active" id="address" role="tabpanel">
			<p>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCompanyAddressAdd">
				<i class="fa fa-plus"></i> Новый адрес
			</button>
			<!-- Modal -->
			<div class="modal fade" id="modalCompanyAddressAdd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true" >
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Новый адрес</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="companyAddress" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="companyId" value="${company.id}"> 
								<div class="row">
									<div class="col-sm">
										<label for="presentation" class="col-sm"><b>Представление</b></label> 
										<input type="text" class="form-control" id="presentation"
											name="presentation" placeholder="Введите адрес">
									</div>
								</div>
								<p>
								<div class="row">
									<div class="col-6">
										<label for="dateStart" class="col-sm"><b>Действует с</b></label> 
										<input type="date" class="form-control" id="dateStart"
											name="dateStart" placeholder="Введите дату начала действия">
									</div>
								</div>
								<p>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
									<sec:csrfInput/>
									<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<p>
			<table class="table table-sm table-hover">
				
				<c:set var="totalAddressCount" />
				
				<thead class="thead-default">
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Адрес</th>
						<th class="text-center">Действует с</th>
						<th class="text-center"></th>
					</tr>
				</thead>
				<c:forEach items="${company.addresses}" var="address">
					<tr>
						<c:set var="openModal" value="$('#modalCompanyAddressEdit${address.id}').modal('show')" />
						<td class="text-center" onclick="${openModal}"><c:out value="${address.id}"/></td>
						<td class="text-center" onclick="${openModal}"><c:out value="${address.presentation}"/></td>
						<td class="text-center" onclick="${openModal}"><fmt:formatDate	value="${address.dateStart}" pattern="dd.MM.yyyy" /></td>
						<td>
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
									<!-- Button trigger modal -->
										<button type="button" class="btn btn-success btn-sm" data-toggle="modal" 
											data-target="#modalCompanyAddressEdit${address.id}">
											<i class="fa fa-edit"></i> Изменить
										</button>
									</div>
									<!-- Modal -->
									<div class="modal fade" id="modalCompanyAddressEdit${address.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Редактировать адрес</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<form action="companyAddress" method="post">
														<input type="hidden" name="edit"> 
														<input type="hidden" name="id" value="${address.id}"> 
														<div class="row">
															<div class="col-sm">
																<label for="presentation" class="col-sm"><b>Представление</b></label> 
																<input type="text" class="form-control" id="presentation"
																	name="presentation" placeholder="Введите адрес" value="${address.presentation}">
															</div>
														</div>
														<p>
														<div class="row">
															<div class="col-6">
																<label for="dateStart" class="col-sm"><b>Действует с</b></label> 
																<input type="date" class="form-control" id="dateStart"
																	name="dateStart" placeholder="Введите дату начала действия" value="${address.dateStart}">
															</div>
														</div>
														<p>	
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
															<sec:csrfInput/>
															<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
									<div class="btn-group mr-2" role="group" aria-label="Second group">
										<form action="companyAddress" method="post">
											<input type="hidden" name="delete"> 
											<input type="hidden" name="id" value="${address.id}">
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
					<c:set var="totalAddressCount" value="${totalAddressCount + 1}" />
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalAddressCount}" /></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<!-- Tab pane 'Director'  -->
		<div class="tab-pane fade" id="director" role="tabpanel">
			<p>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCompanyDirectorAdd">
				<i class="fa fa-plus"></i> Добавить
			</button>
			<!-- Modal -->
			<div class="modal fade" id="modalCompanyDirectorAdd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true" >
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Новый директор</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="companyDirector" method="post">
								<input type="hidden" name="add">
								<input type="hidden" name="companyId" value="${company.id}"> 
								<div class="row">
									<div class="col">
										<label for="fullName"><b>ФИО</b></label> 
										<input type="text" class="form-control" id="fullName" name="fullName" >
									</div>
								</div>
								<p>
								<div class="row">
									<div class="col">
										<label for="shortName"><b>Фамилия, инициалы</b></label> 
										<input type="text" class="form-control" id="shortName" name="shortName" >
									</div>
								</div>
								<p>
								<div class="row">
									<div class="col">
										<label for="post"><b>Должность</b></label> 
										<input type="text" class="form-control" id="post" name="post" >
									</div>
								</div>
								<p>
								<div class="row">
									<div class="col"></div>
									<div class="col">
										<label for="employmentDate"><b>Дата начала работы</b></label> 
										<input type="date" class="form-control" id="employmentDate" name="employmentDate" >
									</div>
								</div>
								<p>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
									<sec:csrfInput/>
									<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<p>
			<table class="table table-sm table-hover">
				
				<c:set var="totalDirectorCount" />
				
				<thead class="thead-default">
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">ФИО</th>
						<th class="text-center">Фамилия, инициалы</th>
						<th class="text-center">Работает с</th>
						<th class="text-center"></th>
					</tr>
				</thead>
				<c:forEach items="${company.directors}" var="director">
					<tr>
						<c:set var="openModal" value="$('#modalCompanyDirectorEdit${director.id}').modal('show')" />
						<td class="text-center" onclick="${openModal}"><c:out value="${director.id}"/></td>
						<td class="text-center" onclick="${openModal}"><c:out value="${director.fullName}"/></td>
						<td class="text-center" onclick="${openModal}"><c:out value="${director.shortName}"/></td>
						<td class="text-center" onclick="${openModal}"><fmt:formatDate	value="${director.employmentDate}" pattern="dd.MM.yyyy" /></td>
						<td>
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
									<!-- Button trigger modal -->
										<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalCompanyDirectorEdit${director.id}">
											<i class="fa fa-edit"></i> Изменить
										</button>
									</div>
									<!-- Modal -->
									<div class="modal fade" id="modalCompanyDirectorEdit${director.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Редактирование данных о директоре</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<form action="companyDirector" method="post">
														<input type="hidden" name="edit">
														<input type="hidden" name="id" value="${director.id}">
														
														<div class="row">
															<div class="col">
																<label for="fullName"><b>ФИО</b></label> 
																<input type="text" class="form-control" id="fullName" name="fullName" 
																	value="${director.fullName}" >
															</div>
														</div>
														<p>
														<div class="row">
															<div class="col">
																<label for="shortName"><b>Фамилия, инициалы</b></label> 
																<input type="text" class="form-control" id="shortName" name="shortName" 
																	value="${director.shortName}" >
															</div>
														</div>
														<p>
														<div class="row">
															<div class="col">
																<label for="post"><b>Должность</b></label> 
																<input type="text" class="form-control" id="post" name="post" 
																	value="${director.post}" >
															</div>
														</div>
														<p>
														<div class="row">
															<div class="col">
																<label for="employmentDate"><b>Дата начала работы</b></label> 
																<input type="date" class="form-control" id="employmentDate" name="employmentDate" 
																	value="${director.employmentDate}" >
															</div>
															<div class="col">
																<label for="firedDate"><b>Дата увольнения</b></label> 
																<input type="date" class="form-control" id="firedDate" name="firedDate" 
																	value="${director.firedDate}">
															</div>
														</div>
														<p>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
															<sec:csrfInput/>
															<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
									<div class="btn-group mr-2" role="group" aria-label="Second group">
										<form action="companyDirector" method="post" >
											<input type="hidden" name="delete">
											<input type="hidden" name="id" value="${director.id}">
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
					<c:set var="totalDirectorCount" value="${totalDirectorCount + 1}" />
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalDirectorCount}" /></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<!-- Tab pane 'Account'  -->
		<div class="tab-pane fade" id="account" role="tabpanel">
			<p>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCompanyAccountAdd">
				<i class="fa fa-plus"></i> Новый счет
			</button>
			<!-- Modal -->
			<div class="modal fade" id="modalCompanyAccountAdd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true" >
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Новый банк. счет</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="companyAccount" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="companyId" value="${company.id}"> 
								<div class="row">
									<div class="col-sm">
										<label for="presentation" class="col-sm"><b>Представление</b></label> 
										<input type="text" class="form-control" id="presentation"
											name="presentation" placeholder="">
									</div>
								</div>
								<p>
								<div class="row">
									<div class="col-6">
										<label for="dateStart" class="col-sm"><b>Действует с</b></label> 
										<input type="date" class="form-control" id="dateStart"
											name="dateStart" placeholder="">
									</div>
								</div>
								<p>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
									<sec:csrfInput/>
									<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<p>
			<table class="table table-sm table-hover">
				
				<thead class="thead-default">
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Наименование</th>
						<th class="text-center">Действует с</th>
						<th class="text-center"></th>
					</tr>
				</thead>
				<c:forEach items="${company.accounts}" var="account">
					<tr>
						<c:set var="openModal" value="$('#modalCompanyAccountEdit${account.id}').modal('show')" />
						<td class="text-center" onclick="${openModal}"><c:out value="${account.id}"/></td>
						<td class="text-center" onclick="${openModal}"><c:out value="${account.presentation}"/></td>
						<td class="text-center" onclick="${openModal}"><fmt:formatDate	value="${account.dateStart}" pattern="dd.MM.yyyy" /></td>
						<td>
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
									<!-- Button trigger modal -->
										<button type="button" class="btn btn-success btn-sm" data-toggle="modal" 
											data-target="#modalCompanyAccountEdit${account.id}">
											<i class="fa fa-edit"></i> Изменить
										</button>
									</div>
									<!-- Modal -->
									<div class="modal fade" id="modalCompanyAccountEdit${account.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Редактировать банк. счет</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<form action="companyAccount" method="post">
														<input type="hidden" name="edit"> 
														<input type="hidden" name="id" value="${account.id}"> 
														<div class="row">
															<div class="col-sm">
																<label for="presentation" class="col-sm"><b>Представление</b></label> 
																<input type="text" class="form-control" id="presentation"
																	name="presentation" placeholder="Введите адрес" value="${fn:replace(account.presentation, '"', '&quot;')}">
															</div>
														</div>
														<p>
														<div class="row">
															<div class="col-6">
																<label for="dateStart" class="col-sm"><b>Действует с</b></label> 
																<input type="date" class="form-control" id="dateStart"
																	name="dateStart" placeholder="Введите дату начала действия" value="${account.dateStart}">
															</div>
														</div>
														<p>	
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
															<sec:csrfInput/>
															<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
									<div class="btn-group mr-2" role="group" aria-label="Second group">
										<form action="companyAccount" method="post">
											<input type="hidden" name="delete"> 
											<input type="hidden" name="id" value="${account.id}">
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
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" 
							value="${fn:length(company.accounts)}" /></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div class="tab-pane fade" id="agreements" role="tabpanel">
			...
		</div>
		
	</div>


</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />