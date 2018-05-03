<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Edit specification</title>

<div class="container-fluid">

	<c:set var="agreement" value="${specification.agreement}" />
	<c:set var="spd" value="${agreement.spd}" />
	
	<form class="form" role="form" action="specification" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${specification.id}">
		
		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="spds">Предприниматели</a> 
					<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a> 
					<a class="breadcrumb-item" href="${agreement.url}">Договор <c:out value="${agreement.number}" /></a> 
					<span class="breadcrumb-item active"><b>Спецификация № <c:out value="${specification.specificationNumber}" /> от 
						<fmt:formatDate	value="${specification.dateStart}" pattern="dd.MM.yyyy" />г.</b>
					</span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col-2">
					<sec:csrfInput/>
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="${agreement.url}" role="button">Отмена</a>
				</div>
				<div class="col">
					<div class="dropdown">
						<button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"><i class="fa fa-print"></i> Печать
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href=<c:url value="/specification/printpdf/spec?id=${specification.id}"/>>
								<i class="fa fa-file-pdf-o"></i> Спецификация
							</a>
							<a class="dropdown-item" href=<c:url value="/specification/printpdf/cert?id=${specification.id}"/>>
								<i class="fa fa-file-pdf-o"></i> Акт вып. работ
							</a>
						</div>
					</div>
				</div>
			</div>
		</nav>
		
		<p>
		
		<c:set var="calcSpecificationSum" value="calcSpecificationSum(${currentTarif.configuring}, ${currentTarif.programming}, ${currentTarif.architecting})" />
		
		<div class="row" >
			<div class="col-1">
				<label for="specificationNumber"><b>№ п/п</b></label>
				<input type="text" style="font-weight: bold;" class="form-control text-center" id="specificationNumber" name="specificationNumber"
					value=<c:out value="${specification.specificationNumber}"/> readonly>
			</div>
			<div class="col-2">
				<label for="specificationSum"><b>Общая сумма</b></label>
				<fmt:formatNumber value="${calculationsTotalAmount}" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" var="specificationSum" />
				
				<input type="text" style="font-weight: bold;" class="form-control text-center" id="specificationSum" name="specificationSum" 
					value="${specificationSum}" readonly>
			</div>
			<div class="col"></div>
			<div class="col-2">
				<label for="dateStart"><b>Дата создания</b></label>
				<input type="date" class="form-control" id="dateStart" name="dateStart"
					value="${specification.dateStart}" >
			</div>
			<div class="col-2">
				<label for="dateFinish"><b>Дата закрытия</b></label>
				<input type="date" class="form-control" id="dateFinish" name="dateFinish"
					value=<c:out value="${specification.dateFinish}"/>>
			</div>
		</div>
		<p>
		<div class="row">
			<div class="col-2">
				<label for="configuringHours"><b>Конфигуратор</b></label>
			</div>
			<div class="col-2">
				<label for="programmingHours"><b>Программист</b></label>
			</div>
			<div class="col-2">
				<label for="architectingHours"><b>Архит. доработки</b></label>
			</div>
		</div>
		
		<div class="row">
			<div class="col-2">
				<div class="input-group">
					<input onchange="${calcSpecificationSum}" type="number" class="form-control text-center" id="configuringHours"
						name="configuringHours" value=<c:out value="${specification.configuringHours}"/>>
					<span class="input-group-addon"><b>&#215; <fmt:formatNumber type="number" pattern="0" value="${currentTarif.configuring}"/> грн/ч</b></span>
				</div>
			</div>
			
			<div class="col-2">
				<div class="input-group">
					<input onchange="${calcSpecificationSum}" type="number" class="form-control text-center" id="programmingHours"
						name="programmingHours" value=<c:out value="${specification.programmingHours}"/>>
					<span class="input-group-addon"><b>&#215; <fmt:formatNumber type="number" pattern="0" value="${currentTarif.programming}"/> грн/ч</b></span>
				</div>
			</div>
			<div class="col-2">
				<div class="input-group">
					<input onchange="${calcSpecificationSum}" type="number" class="form-control text-center" id="architectingHours"
						name="architectingHours" value=<c:out value="${specification.architectingHours}"/>>
					<span class="input-group-addon"><b>&#215; <fmt:formatNumber type="number" pattern="0" value="${currentTarif.architecting}"/> грн/ч</b></span>
				</div>
			</div>
			<div class="col">
				<div class="input-group">
					<span class="input-group-addon"><b>Итого:</b></span>
					<span id="sum" class="input-group-addon" data-onload="${calcSpecificationSum}" onclick="${calcSpecificationSum}"></span>
				</div>
			</div>
		</div>
	</form>
	<p>
	
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#calculation" role="tab">Расчеты</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" 
			href="#jobName" role="tab">Список работ</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" 
			href="#specPayment" role="tab">Оплаты</a></li>
	</ul>
	<p>
	
	<!-- Tab panes -->
	<div class="tab-content">
	
		<div class="tab-pane active" id="calculation" role="tabpanel">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCalculation">
				<i class="fa fa-plus"></i> Добавить расчет
			</button>
			<!-- Modal -->
			<div class="modal fade" id="modalCalculation" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Новый расчет</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="calculation" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="specificationId"	value="${specification.id}">
								
								<div class="row">
									<div class="col">
										<label for=partNumber><b>№ п/п</b></label>
									</div>
									<div class="col">
										<input type="text" class="form-control text-right" id="partNumber" name="partNumber" 
											value="${nextCalculationNumber}">
									</div>
								</div>
								<p>
								<div class="row">
									<div class="col">
										<label for="dateStart"><b>Дата</b></label>
									</div>
									<div class="col">
										<input type="date" class="form-control" id="dateStart" name="dateStart"
											value="${dateStart}">
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
				<thead class="thead-default">
					<tr>
						<th class="text-center">№ п/п</th>
						<th class="text-center">Период</th>
						<th class="text-center">Сальдо на начало</th>
						<th class="text-center">Сальдо на конец</th>
						<th class="text-center">Сумма</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${specification.calculations}" var="calculation">
					<tr>
						<td class="text-center" onclick="goToAddress('${calculation.url}')">${calculation.partNumber}</td>
						<td class="text-center" onclick="goToAddress('${calculation.url}')"><fmt:formatDate pattern="MMMM" value="${calculation.dateStart}"/> <fmt:formatDate pattern="yyyy" value="${calculation.dateStart}"/></td>
						<td class="text-center" onclick="goToAddress('${calculation.url}')"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${calculation.openingBalance}" /></td>
						<td class="text-center" onclick="goToAddress('${calculation.url}')"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${calculation.closingBalance}"/></td>
						<td class="text-center" onclick="goToAddress('${calculation.url}')"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${calculation.turnover}"/></td>
						<td class="text-center">
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
										<a class="btn btn-success btn-sm" href="${calculation.url}" role="button">
											<i class="fa fa-edit"></i> Изменить
										</a>
									</div>
									<div class="btn-group mr-2" role="group" aria-label="Second group">
										<form action="calculation" method="post">
											<input type="hidden" name="delete">
											<input type="hidden" name="id" value="${calculation.id}">
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
						<th></th>
						<th class="text-center">Итого:</th>
						<th></th>
						<th></th>
						<th class="text-center"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${calculationsTotalAmount}" /></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
	
		<div class="tab-pane" id="jobName" role="tabpanel">

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalJobName">
				<i class="fa fa-plus"></i> Добавить работу
			</button>
			<!-- Modal -->
			<div class="modal fade bd-example-modal-lg" id="modalJobName" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Добавить работу</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
						<form action="job" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="specificationId"	value="${specification.id}">
							
							<div class="row">
								<div class="col">
									<label for="jobName"><b>Наименование</b></label>
									<input type="text" class="form-control" id="jobName" name="jobName">
								</div>
							</div>
							<p>
							<div class="row">
								<div class="col">
									<label for="configuring"><b>Конфигурирование, часы</b></label>
									<input type="text" class="form-control text-center" id="configuring" name="configuring">
								</div>
								<div class="col">
									<label for="programming"><b>Программирование, часы</b></label>
									<input type="text" class="form-control text-center" id="programming" name="programming">
								</div>
								<div class="col">
									<label for="architecting"><b>Архит. доработки, часы</b></label>
									<input type="text" class="form-control text-center" id="architecting" name="architecting">
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
			
				<c:set var="configuringHoursAmount" />
				<c:set var="programmingHoursAmount" />
				<c:set var="architectingHoursAmount" />
				<c:set var="jobsSumAmount" />
				
				<thead class="thead-default">
					<tr>
						<th class="text-center align-middle">Наименование</th>
						<th class="text-center align-middle">Конфиг.</th>
						<th class="text-center align-middle">Прогр. дораб.</th>
						<th class="text-center align-middle">Архит. дораб.</th>
						<th class="text-center align-middle">Всего</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${specification.jobs}" var="job">
					<tr>
						<c:set var="openModal" value="$('#modalJobEdit${job.id}').modal('show')" />
						<td class="text-left" onclick="${openModal}">
							<c:out value="${job.jobName}"/>
						</td>
						<td class="text-center align-middle" onclick="${openModal}">
							<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${job.configuringHours}"/>
						</td>
						<td class="text-center align-middle" onclick="${openModal}">
							<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${job.programmingHours}"/>
						</td>
						<td class="text-center align-middle" onclick="${openModal}">
							<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${job.architectingHours}"/>
						</td>
						<td class="text-center align-middle" onclick="${openModal}">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" 
								value="${(job.configuringHours * currentTarif.configuring) + (job.programmingHours * currentTarif.programming) + (job.architectingHours * currentTarif.architecting)}"/>
						</td>
						<td class="text-center align-middle">
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
									<!-- Button trigger modal -->
										<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalJobEdit${job.id}">
											<i class="fa fa-edit"></i> Изменить
										</button>
									</div>
									<!-- Modal -->
									<div class="modal fade bd-example-modal-lg" id="modalJobEdit${job.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
										<div class="modal-dialog modal-lg" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Редактирование данных об услуге к Спецификации № <c:out value="${specification.specificationNumber}" /> от 
															<fmt:formatDate	value="${specification.dateStart}" pattern="dd.MM.yyyy" />г.</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<form action="job" method="post">
														<input type="hidden" name="edit">
														<input type="hidden" name="id" value="${job.id}"/>
														
														<div class="row">
															<div class="col">
																<label for="jobName" class="text-left"><b>Наименование</b></label>
																<input type="text" class="form-control" id="jobName" name="jobName"
																	value="${job.jobName}">
															</div>
														</div>
														<p>
														<div class="row">
															<div class="col">
																<label for="configuring"><b>Конфигурирование, часы</b></label>
																<input type="text" class="form-control text-center" id="configuring" name="configuring" 
																	value="${job.configuringHours}" >
															</div>
															<div class="col">
																<label for="programming"><b>Программирование, часы</b></label>
																<input type="text" class="form-control text-center" id="programming" name="programming" 
																	value="${job.programmingHours}" >
															</div>
															<div class="col">
																<label for="architecting"><b>Архит. доработки, часы</b></label>
																<input type="text" class="form-control text-center" id="architecting" name="architecting" 
																	value="${job.architectingHours}" >
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
								</div>
								<div class="btn-group mr-2" role="group" aria-label="Second group">
									<form action="job" method="post">
										<input type="hidden" name="delete"> 
										<input type="hidden" name="id" value="${job.id}">
										<sec:csrfInput/>
										<button type="submit" class="btn btn-danger btn-sm">
											<i class="fa fa-trash-o"></i> Удалить
										</button>
									</form>
								</div>
							</div>	
						</td>
					</tr>
					<c:set var="configuringHoursAmount" value="${configuringHoursAmount + job.configuringHours}"/>
					<c:set var="programmingHoursAmount" value="${programmingHoursAmount + job.programmingHours}"/>
					<c:set var="architectingHoursAmount" value="${architectingHoursAmount + job.architectingHours}"/>
					<c:set var="jobsSumAmount" value="${jobsSumAmount + ((job.configuringHours * currentTarif.configuring) + (job.programmingHours * currentTarif.programming) + (job.architectingHours * currentTarif.architecting))}"/>
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center align-middle">Итого:</th>
						<th class="text-center align-middle"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${configuringHoursAmount}" /></th>
						<th class="text-center align-middle"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${programmingHoursAmount}" /></th>
						<th class="text-center align-middle"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${architectingHoursAmount}" /></th>
						<th class="text-center align-middle"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${jobsSumAmount}" /></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div class="tab-pane" id="specPayment" role="tabpanel">

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSpecPayment">
				<i class="fa fa-plus"></i> Добавить оплату
			</button>

			<!-- Modal -->
			<div class="modal fade bd-example-modal-lg" id="modalSpecPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Добавить оплату</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
						<form action="specificationpayment" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="specificationId"	value="${specification.id}">
							
							<div class="row">
								<div class="col-3">
									<label for="paymentNumber"><b>№ п/п</b></label>
									<input type="text" class="form-control text-center" id="paymentNumber" name="paymentNumber">
								</div>
								<div class="col-5">
									<label for="paymentSum"><b>Сумма платежа, грн</b></label>
									<input type="text" class="form-control text-right" id="paymentSum" name="paymentSum">
								</div>
								<div class="col">
									<label for="paymentDays"><b>Срок оплаты, дн.</b></label>
									<input type="text" class="form-control text-center" id="paymentDays" name="paymentDays">
								</div>
							</div>
							<p>
							<div class="row">
								<div class="col">
									<label for="comment"><b>Примечание</b></label>
									<input type="text" class="form-control text-left" id="comment" name="comment">
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
			
				<c:set var="totalPaymentSum" />
				<c:set var="totalPaymentDays" />
				
				<thead class="thead-default">
					<tr>
						<th class="text-center align-middle">№ п/п</th>
						<th class="text-center align-middle">Сумма платежа</th>
						<th class="text-center align-middle">Срок оплаты</th>
						<th class="text-center align-middle">Примечание</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${specification.specPayments}" var="specPayment">
					<tr>
						<c:set var="openModal" value="$('#modalSpecPayment${specPayment.id}').modal('show')" />
						<td class="text-center" onclick="${openModal}">
							<c:out value="${specPayment.paymentNumber}"/>
						</td>
						<td class="text-center align-middle" onclick="${openModal}">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${specPayment.paymentSum}"/>
						</td>
						<td class="text-center align-middle" onclick="${openModal}">
							<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${specPayment.paymentDays}"/>
						</td>
						<td class="text-center align-middle" onclick="${openModal}">
							<c:out value="${specPayment.comment}" />
						</td>
						
						<td class="text-center align-middle">
							<div class="d-flex justify-content-end">
								<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
									<div class="btn-group mr-2" role="group" aria-label="First group">
									<!-- Button trigger modal -->
										<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalSpecPayment${specPayment.id}">
											<i class="fa fa-edit"></i> Изменить
										</button>
									</div>
									<!-- Modal -->
									<div class="modal fade bd-example-modal" id="modalSpecPayment${specPayment.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Редактирование данных об оплате к Спецификации № <c:out value="${specification.specificationNumber}" /> от 
															<fmt:formatDate	value="${specification.dateStart}" pattern="dd.MM.yyyy" />г.</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<form action="specificationpayment" method="post">
														<input type="hidden" name="edit">
														<input type="hidden" name="id" value="${specPayment.id}"/>
														
														<div class="row">
															<div class="col-3">
																<label for="paymentNumber"><b>№ п/п</b></label>
																<input type="text" class="form-control text-center" id="paymentNumber" name="paymentNumber"
																	value="${specPayment.paymentNumber}" >
															</div>
															<div class="col-5">
																<label for="paymentSum"><b>Сумма платежа, грн</b></label>
																<input type="text" class="form-control text-right" id="paymentSum" name="paymentSum"
																	value="${specPayment.paymentSum}" >
															</div>
															<div class="col">
																<label for="paymentDays"><b>Срок оплаты, дн.</b></label>
																<input type="text" class="form-control text-center" id="paymentDays" name="paymentDays"
																	value="${specPayment.paymentDays}" >
															</div>
														</div>
														<p>
														<div class="row">
															<div class="col">
																<label for="comment"><b>Примечание</b></label>
																<input type="text" class="form-control text-left" id="comment" name="comment"
																	value="${specPayment.comment}" >
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
										<form action="specificationpayment" method="post">
											<input type="hidden" name="delete"> 
											<input type="hidden" name="id" value="${specPayment.id}">
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
					<c:set var="totalPaymentSum" value="${totalPaymentSum + specPayment.paymentSum}"/>
					<c:set var="totalPaymentDays" value="${totalPaymentDays + specPayment.paymentDays}"/>
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th class="text-center align-middle">Итого:</th>
						<th class="text-center align-middle"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totalPaymentSum}" /></th>
						<th class="text-center align-middle"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${totalPaymentDays}" /></th>
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