<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>${spd.alias}</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<a class="breadcrumb-item" href="spds">Предприниматели</a> 
		<span class="breadcrumb-item active"><b>СПД <c:out value="${spd.alias}" /></b></span>
	</nav>
	
	<!-- Nav tabs -->
	<ul class="nav nav-tabs nav-fill" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#main" role="tab"><i class="fa fa-address-card-o"></i> Основные данные</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#agreement" role="tab"><i class="fa fa-file-text-o"></i> Договоры</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#bankprops" role="tab"><i class="fa fa-bank"></i> Банковские реквизиты</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#payments" role="tab"><i class="fa fa-usd"></i> Выплаты</a></li>
	</ul>


	<!-- Tab panes -->
	<div class="tab-content">
	
		<div class="tab-pane fade show active" id="main" role="tabpanel">
			<p>
			<ul>
				<c:set var="address" value="${spd.address}" />
				<c:set var="registrationInfo" value="${spd.registrationInfo}" />
				<li><b>Полное ФИО: </b> <c:out
						value="${spd.surname} ${spd.firstname} ${spd.lastname}" /></li>
				<li><b>ИНН: </b> <c:out value="${spd.inn}" /></li>
				<li><b>Паспортные данные: </b> <c:out
						value="${empty spd.passport ? '-' : spd.passport}" /></li>
				<li><b>Адрес: </b> <c:if test="${not empty address.zip}">${address.zip}, </c:if><c:if test="${not empty address.country}">${address.country}</c:if><c:if test="${not empty address.region}">, ${address.region}</c:if><c:if test="${not empty address.city}">, ${address.city}</c:if><c:if test="${not empty address.street}">, ${address.street}</c:if><c:if test="${not empty address.building}">, буд. ${address.building}</c:if><c:if test="${not empty address.flat}">, кв. ${address.flat}</c:if></li>
				<li><b>Данные о регистрации: Запис в ЄДР № </b> <c:out
						value="${registrationInfo.description} від " /> <fmt:formatDate
						value="${registrationInfo.dated}" pattern="dd.MM.yyyy" />р.</li>
			</ul>
			<p>
			<div class="d-flex justify-content-start">
				<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
					<div class="btn-group mr-2" role="group" aria-label="First group">
						<form action="spd" method="get">
							<input type="hidden" name="edit"> <input type="hidden" name="id" value="${spd.id}">
							<button type="submit" class="btn btn-warning"><i class="fa fa-edit"></i> Редактировать данные</button>
						</form>
					</div>
					<div class="btn-group mr-2" role="group" aria-label="Second group">
						<form action="spd" method="post">
							<input type="hidden" name="delete"> 
							<input type="hidden" name="id" value="${spd.id}">
							<sec:csrfInput />
							<sec:authorize access="hasRole('ROLE_ADMIN')" >
								<button type="submit" class="btn btn-danger"><i class="fa fa-trash-o"></i> Удалить СПД (осторожно!)</button>
							</sec:authorize>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<p>
		
		<div class="tab-pane fade" id="agreement" role="tabpanel">
			<p>
			<p>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAgreement">
				<i class="fa fa-plus"></i> Новый договор
			</button>
			<!-- Modal -->
			<div class="modal fade" id="modalAgreement" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">СПД <c:out value="${spd.alias}"/> | Новый договор</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="agreement" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="spdId" value=<c:out value="${spd.id}"/>>
								<div class="row">
									<div class="col">
										<label for="number"><b>Номер</b></label>
										<input type="text" class="form-control" id="number" name="number" 
											placeholder="№ договора">
										</div>
								</div>
								<p>
								<div class="row">
									<div class="col-6">
									<label for="dateStart"><b>Дата</b></label>
										<input type="date" class="form-control" id="dateStart"
											name="dateStart" placeholder="Дата договора">
									</div>
								</div>
								<p>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
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
			
				<c:set var="totalAgreementCount" />
			
				<thead class="thead-default">
					<tr>
						<th class="text-center">Номер</th>
						<th class="text-center">Дата</th>
						<th class="text-center">Заказчик</th>
						<th class="text-center"></th>
					</tr>
				</thead>
				<c:forEach items="${spd.agreements}" var="agreement">
					<tr>
						<td class="text-center" onclick="goToAddress('${agreement.url}')"><c:out value="${agreement.number}"/></td>
						<td class="text-center" onclick="goToAddress('${agreement.url}')"><fmt:formatDate value="${agreement.dateStart}" pattern="dd.MM.yyyy" /></td>
						<td class="text-center" onclick="goToAddress('${agreement.url}')"><c:out value="${agreement.company.title}" /></td>
						<td class="text-center">
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
										<a class="btn btn-success btn-sm" href="${agreement.url}" role="button"><i class="fa fa-edit"></i> Подробнее</a>
									</div>
									<div class="btn-group mr-2" role="group" aria-label="Second group">
										<form action="agreement" method="post">
											<input type="hidden" name="delete"> 
											<input type="hidden" name="id" value="${agreement.id}">
											<sec:csrfInput/>
											<button type="submit" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Удалить</button>
										</form>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<c:set var="totalAgreementCount" value="${totalAgreementCount + 1}" />
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalAgreementCount}" /></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<p>
		
		<div class="tab-pane fade" id="bankprops" role="tabpanel">
		
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAccount">
				<i class="fa fa-plus"></i> Новый счет
			</button>
			<!-- Modal -->
			<div class="modal fade" id="modalAccount" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">СПД <c:out value="${spd.alias}"/> | Новый счет</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="account" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="spdId" value=<c:out value="${spd.id}"/>>
								<div class="row">
									<div class="col">
										<label for="bankName"><b>Наименование банка</b></label>
										<input type="text" class="form-control" id="bankName"
											name="bankName" placeholder="Введите наименование банка">
									</div>
								</div>
								<p>	
								<div class="row">
									<div class="col-6">
										<label for="accountNumber"><b>Номер счета</b></label>
										<input type="text" class="form-control" id="accountNumber"
											name="accountNumber" placeholder="Введите номер счета">
									</div>
									<div class="col-6">
										<label for="mfo"><b>МФО</b></label>
										<input type="text" class="form-control" id="mfo"
											name="mfo" placeholder="Введите МФО">
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
				
				<c:set var="totalAccountCount" />
				
				<thead class="thead-default">
					<tr>
						<th class="text-center">Номер счета</th>
						<th class="text-center">МФО</th>
						<th class="text-center">Наименование банка</th>
						<th class="text-center"></th>
					</tr>
				</thead>
				<c:forEach items="${spd.accounts}" var="account">
					<tr>
						<c:set var="openModal" value="$('#modalSPDAccountEdit${account.id}').modal('show')" />
						<td class="text-center" onclick="${openModal}">${account.accountNumber}</td>
						<td class="text-center" onclick="${openModal}">${account.mfo}</td>
						<td class="text-center" onclick="${openModal}">${account.bankName}</td>
						<td class="text-center">
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalSPDAccountEdit${account.id}"><i class="fa fa-edit"></i> Редактировать</button>
									</div>
										<!-- Modal -->
										<div class="modal fade" id="modalSPDAccountEdit${account.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel"><c:out value="${spd.alias}"/> | Редактирование счета</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<form action="account" method="post">
															<input type="hidden" name="edit"> 
															<input type="hidden" name="id" value="${account.id}"> 
															<div class="row">
																<div class="col">
																	<label for="bankName"><b>Наименование банка</b></label>
																	<input type="text" class="form-control" id="bankName"
																		name="bankName" placeholder="Введите наименование банка" value="${fn:replace(account.bankName, '"', '&quot;')}">
																</div>
															</div>
															<p>	
															<div class="row">
																<div class="col-6">
																	<label for="accountNumber"><b>Номер счета</b></label>
																	<input type="text" class="form-control" id="accountNumber"
																		name="accountNumber" placeholder="Введите номер счета" value="${account.accountNumber}">
																</div>
																<div class="col-6">
																	<label for="mfo"><b>МФО</b></label>
																	<input type="text" class="form-control" id="mfo"
																		name="mfo" placeholder="Введите МФО" value="${account.mfo}">
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
										<form action="account" method="post">
											<input type="hidden" name="delete"> 
											<input type="hidden" name="id" value="${account.id}">
											<sec:csrfInput/>
											<button type="submit" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Удалить</button>
										</form>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<c:set var="totalAccountCount" value="${totalAccountCount + 1}" />
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalAccountCount}" /></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div class="tab-pane fade" id="payments" role="tabpanel">
			<p>
			<p>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalPayment">
				<i class="fa fa-plus"></i> Добавить выплату
			</button>
			<p>
			<!-- Modal -->
			<div class="modal fade" id="modalPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Выплата | Добавление</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
						<form action="payment" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="spdId" value="${spd.id}">
							
							<div class="row">
								<div class="col">
									<label for="value"><b>Тип выплаты</b></label>
									<select class="form-control" name="payment_type_id">
										<c:forEach items="${paymentTypes}" var="paymentType">
											<option value="${paymentType.id}">${paymentType.title}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<p>
							<div class="row">
								<div class="col-6">
									<label for="value"><b>Сумма</b></label>
									<input type="text" class="form-control" id="value" placeholder="Введите сумму"
										name="value">
								</div>
							</div>
							<p>
							<div class="row">
								<div class="col">
									<label for="dateStart"><b>Действует с</b></label>
									<input type="date" class="form-control" id="dateStart" name="dateStart"
										placeholder="" >
								</div>
								<div class="col">			
									<label for="dateFinish"><b>Действует по</b></label>
									<input type="date" class="form-control" id="dateFinish" name="dateFinish"
										placeholder="" >
								</div>
							</div>			
							<p>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
								<sec:csrfInput/>
								<input type="submit" class="btn btn-primary" id="button" value="Добавить">
							</div>
						</form>
						</div>
					</div>
				</div>
			</div>
			<p>
			<table class="table table-sm table-hover">
				
				<c:set var="totalPaymentCount" />
				
				<thead class="thead-default">
					<tr>
						<th class="text-center" rowspan="2">Наименование</th>
						<th class="text-center" rowspan="2">Сумма</th>
						<th class="text-center" rowspan="1">Действует с</th>
						<th class="text-center" rowspan="1">Действует по</th>
						<th class="text-center" rowspan="2"></th>
					</tr>
				</thead>
				<c:forEach items="${spd.payments}" var="payment">
				<c:set var="paymentType" value="${payment.paymentType}" />
					<tr>
						<c:set var="openModal" value="$('#modalPaymentEdit${payment.id}').modal('show')" />
						<td class="text-center" onclick="${openModal}">${paymentType.title}</td>
						<td class="text-center" onclick="${openModal}">
							<c:choose>
								<c:when test="${paymentType.isPercent == true}" >
									<fmt:formatNumber type="percent" minFractionDigits="2" value="${payment.value}" /> 
								</c:when>
								<c:otherwise>
									<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${payment.value}" />
								</c:otherwise>
							</c:choose>
						</td>
						<td class="text-center" onclick="${openModal}"><fmt:formatDate pattern="dd.MM.yyyy" value="${payment.dateStart}" /></td>
						<td class="text-center" onclick="${openModal}"><fmt:formatDate pattern="dd.MM.yyyy" value="${payment.dateFinish}" /></td>
						<td class="text-center">
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalPaymentEdit${payment.id}"><i class="fa fa-edit"></i> Редактировать</button>
									</div>
									<!-- Modal -->
									<div class="modal fade" id="modalPaymentEdit${payment.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Выплата | Редактирование</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
												<form action="payment" method="post">
													<input type="hidden" name="edit">
													<input type="hidden" name="id" value="${payment.id}">
													
													<div class="row">
														<div class="col">
															<label for="value"><b>Тип выплаты</b></label>
															<select class="form-control" name="payment_type_id">
																<c:forEach var="paymentType" items="${paymentTypes}">
																	<c:choose>
																		<c:when test="${paymentType == payment.paymentType}">
																			<option value="${paymentType.id}" selected>${paymentType.title}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${paymentType.id}">${paymentType.title}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
													<p>
													<div class="row">
														<div class="col-6">
															<label for="value"><b>Сумма</b></label>
																<input type="text" class="form-control" id="value" placeholder="Введите сумму"
																	name="value" value="${payment.value}" >
														</div>
													</div>
													<p>
													<div class="row">
														<div class="col">			
															<label for="dateStart"><b>Действует с</b></label>
															<input type="date" class="form-control" id="dateStart" name="dateStart"
																placeholder="" value="${payment.dateStart}">
														</div>
														<div class="col">			
															<label for="dateFinish"><b>Действует по</b></label>
															<input type="date" class="form-control" id="dateFinish" name="dateFinish"
																placeholder="" value="${payment.dateFinish}">
														</div>
													</div>			
													<p>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
														<sec:csrfInput/>
														<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
													</div>
												</form>
												</div>
											</div>
										</div>
									</div>
									<div class="btn-group mr-2" role="group" aria-label="Second group">
										<form action="payment" method="post">
											<input type="hidden" name="delete">
											<input type="hidden" name="id" value="${payment.id}">
											<sec:csrfInput/>
											<button type="submit" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Удалить</button>
										</form>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<c:set var="totalPaymentCount" value="${totalPaymentCount + 1}" />
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center">Всего: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalPaymentCount}" /></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
	</div>
	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />