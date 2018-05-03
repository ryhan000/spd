<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Налоги</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Налоги</b></span>
	</nav>
	
	<div class="row">
	
		<div class="col-3">
			<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist">
				<a class="nav-link active" id="v-pills-esv-tab" data-toggle="pill" 
					href="#v-pills-esv" role="tab" aria-controls="v-pills-esv" aria-expanded="true">
					ЕСВ
				</a>
				<a class="nav-link" id="v-pills-simpleTax-tab" data-toggle="pill" 
					href="#v-pills-simpleTax" role="tab" aria-controls="v-pills-simpleTax" aria-expanded="true">
					Единый налог
				</a>
			</div>
		</div>
		
		<div class="col-9">
		
			<div class="tab-content" id="v-pills-tabContent">
			
				<div class="tab-pane fade show active" id="v-pills-esv" role="tabpanel" aria-labelledby="v-pills-esv-tab">
				
					<div class="col-8">
						<!-- Кнопка 'Добавить' -->
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalEsvTax">
							<i class="fa fa-plus"></i> Добавить
						</button>
			
						<!-- Modal -->
						<div class="modal fade bd-example-modal" id="modalEsvTax" tabindex="-1" role="dialog" aria-labelledby="exampleEsvTax"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Новая ставка ЕСВ</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
									<form action="esvtax" method="post">
										<input type="hidden" name="add"> 
										
										<div class="row">
											<div class="col-5">
												<label for="value"><b>Значение</b></label>
												<input type="text" class="form-control text-center" id="value" name="value">
											</div>
											<div class="col"></div>
											<div class="col-5">
												<label for="dateStart"><b>Действует с</b></label>
												<input type="date" class="form-control text-center" id="dateStart" name="dateStart">
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
									<th class="text-center">Значение</th>
									<th class="text-center">Действует с</th>
									<th></th>
								</tr>
							</thead>
							<c:forEach items="${esvTaxes}" var="esvTax">
								<tr>
									<c:set var="openModal" value="$('#modalEsvTax${esvTax.id}').modal('show')" />
									<td class="text-center" onclick="${openModal}">
										<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${esvTax.value}" />
									</td>
									<td class="text-center" onclick="${openModal}">
										<fmt:formatDate	value="${esvTax.dateStart}" pattern="dd.MM.yyyy" />
									</td>
									<td class="text-center">
										<div class="d-flex justify-content-end">
											<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
												<div class="btn-group mr-2" role="group" aria-label="First group">
												<!-- Button trigger modal -->
													<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalEsvTax${esvTax.id}">
														<i class="fa fa-edit"></i> Изменить
													</button>
												</div>
												<!-- Modal -->
												<div class="modal fade bd-example-modal" id="modalEsvTax${esvTax.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
														aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLabel">Редактирование ставки ЕСВ</h5>
																<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<form action="esvtax" method="post">
																	<input type="hidden" name="edit">
																	<input type="hidden" name="id" value="${esvTax.id}"/>
																	
																	<div class="row">
																		<div class="col-5">
																			<label for="value"><b>Значение</b></label>
																			<input type="text" class="form-control text-center" id="value" name="value"
																				value="${esvTax.value}" >
																		</div>
																		<div class="col"></div>
																		<div class="col-5">
																			<label for="dateStart"><b>Действует с</b></label>
																			<input type="date" class="form-control text-right" id="dateStart" name="dateStart"
																				value="${esvTax.dateStart}" >
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
													<form action="esvtax" method="post">
														<input type="hidden" name="delete"> 
														<input type="hidden" name="id" value="${esvTax.id}">
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
									<th class="text-center">Всего: ${fn:length(esvTaxes)} </th>
									<th></th>
									<th></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				
				<div class="tab-pane fade" id="v-pills-simpleTax" role="tabpanel" aria-labelledby="v-pills-simpleTax-tab">
					
					<div class="col-8">
						<!-- Кнопка 'Добавить' -->
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSimpleTax">
							<i class="fa fa-plus"></i> Добавить
						</button>
			
						<!-- Modal -->
						<div class="modal fade bd-example-modal" id="modalSimpleTax" tabindex="-1" role="dialog" aria-labelledby="exampleSimpleTax"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleSimpleTax">Новая ставка Единого налога</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
									<form action="simpletax" method="post">
										<input type="hidden" name="add"> 
										
										<div class="row">
											<div class="col-5">
												<label for="value"><b>Значение</b></label>
												<input type="text" class="form-control text-center" id="value" name="value">
											</div>
											<div class="col"></div>
											<div class="col-5">
												<label for="dateStart"><b>Действует с</b></label>
												<input type="date" class="form-control text-center" id="dateStart" name="dateStart">
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
									<th class="text-center">Значение</th>
									<th class="text-center">Действует с</th>
									<th></th>
								</tr>
							</thead>
							<c:forEach items="${simpleTaxes}" var="simpleTax">
								<tr>
									<c:set var="openModal2" value="$('#modalSimpleTax${simpleTax.id}').modal('show')" />
									<td class="text-center" onclick="${openModal2}">
										<fmt:formatNumber type="percent" minFractionDigits="2" value="${simpleTax.value}" />
									</td>
									<td class="text-center" onclick="${openModal2}">
										<fmt:formatDate	value="${simpleTax.dateStart}" pattern="dd.MM.yyyy" />
									</td>
									<td class="text-center">
										<div class="d-flex justify-content-end">
											<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
												<div class="btn-group mr-2" role="group" aria-label="First group">
												<!-- Button trigger modal -->
													<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalSimpleTax${simpleTax.id}">
														<i class="fa fa-edit"></i> Изменить
													</button>
												</div>
												<!-- Modal -->
												<div class="modal fade bd-example-modal" id="modalSimpleTax${simpleTax.id}" tabindex="-1" role="dialog" aria-labelledby="exampleSimpleTax"
														aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleSimpleTax">Редактирование ставки Единого налога</h5>
																<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<form action="simpletax" method="post">
																	<input type="hidden" name="edit">
																	<input type="hidden" name="id" value="${simpleTax.id}"/>
																	
																	<div class="row">
																		<div class="col-5">
																			<label for="value"><b>Значение</b></label>
																			<input type="text" class="form-control text-center" id="value" name="value"
																				value="${simpleTax.value}" >
																		</div>
																		<div class="col"></div>
																		<div class="col-5">
																			<label for="dateStart"><b>Действует с</b></label>
																			<input type="date" class="form-control text-right" id="dateStart" name="dateStart"
																				value="${simpleTax.dateStart}" >
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
													<form action="simpletax" method="post">
														<input type="hidden" name="delete"> 
														<input type="hidden" name="id" value="${simpleTax.id}">
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
									<th class="text-center">Всего: ${fn:length(simpleTaxes)} </th>
									<th></th>
									<th></th>
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
				
			</div>
			
		</div>
		
	</div>
	
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />