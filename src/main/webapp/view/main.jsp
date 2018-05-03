<%@ page session="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="header.jsp" />

<title>Главная</title>

<div class="container-fluid">
	
		<div class="row">
			<div class="col-md-3">
				<div class="alert alert-dark" role="alert">
					<h4 class="text-center">
						<strong>
							<a href="spds" class="text-dark">Предприниматели</a>
						</strong>
					</h4>
				</div>
			</div>
			<div class="col-md-9">
				<div class="alert alert-dark" role="alert">
					<h4 class="text-center"><strong>Документы</strong></h4>
				</div>
			</div>
		</div>
	
		<p>
		
		


				<div class="row">
					<div class="col-md-3">
						<div class="list-group" id="list-tab" role="tablist">
							<c:forEach items="${spds}" var="spd">
								<a class="list-group-item list-group-item-action" id="list-spd-${spd.id}-list" data-toggle="list"
									href="#list-spd-${spd.id}" role="tab" aria-controls="spd-${spd.id}" ondblclick="goToAddress('${spd.url}')">
									<strong>${spd.alias}</strong>
								</a>
							</c:forEach>
						</div>
					</div>
					
					<div class="col-md-9">
						<div class="tab-content" id="nav-tabContent">

							<c:forEach items="${spds}" var="spd">
								<div class="tab-pane fade" id="list-spd-${spd.id}" role="tabpanel" aria-labelledby="list-spd-${spd.id}-list">

									<c:forEach items="${spd.agreements}" var="agreement">

										<c:set var="totalSpecificationAmount" value="${0}" />
										
									<div class="card">
										<h5 class="card-header alert alert-dark text-center">
											Договор: <a class="text-danger" href="${agreement.url}"> <c:out value="${agreement.number}" />
												от <fmt:formatDate value="${agreement.dateStart}" pattern="dd.MM.yyyy" />г. </a>
										</h5>
									
										<div class="card-body">
										
										<table class="table table-sm ">

											<thead>

												<tr>
													<th class="text-center table-active"><strong class="text-dark"> Спецификации </strong></th>
													<th class="text-center table-active"><strong class="text-dark"> Расчеты </strong></th>
													<th class="text-center table-active"><strong class="text-dark"></strong></th>
												</tr>
											</thead>

											<c:forEach var="specification" items="${agreement.specifications}">

												<tr>
													<td class="text-center align-middle"><a href="${specification.url}" class="text-success"> <strong>№
																<c:out value="${specification.specificationNumber}" /> от <fmt:formatDate
																	value="${specification.dateStart}" pattern="dd.MM.yyyy" />г. (<fmt:formatNumber type="number"
																	minFractionDigits="2" maxFractionDigits="2" value="${specification.specificationSum}" /> грн.)
														</strong>
													</a></td>
													<td class="text-center align-middle"><c:forEach var="calculation"
															items="${specification.calculations}">
															<a href="${calculation.url}" class="text-info"> <strong>№ <c:out
																		value="${calculation.partNumber}" /> за <fmt:formatDate value="${calculation.dateStart}"
																		pattern="MMMM" /> <fmt:formatDate value="${calculation.dateStart}" pattern="yyyy" />г. (<fmt:formatNumber
																		type="number" minFractionDigits="2" maxFractionDigits="2" value="${calculation.turnover}" /> грн.)
															</strong>
															</a>
															<br>
														</c:forEach></td>
													<td class="text-right align-middle">

														<div class="dropdown">
															<button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
																aria-haspopup="true" aria-expanded="false">
																<i class="fa fa-print"></i> Печать
															</button>
															<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
																<a class="dropdown-item" href=<c:url value="/specification/printpdf/spec?id=${specification.id}"/>>
																	<i class="fa fa-file-pdf-o"></i> Спецификация
																</a> <a class="dropdown-item" href=<c:url value="/specification/printpdf/cert?id=${specification.id}"/>>
																	<i class="fa fa-file-pdf-o"></i> Акт вып. работ
																</a>
															</div>
														</div>

													</td>
												<tr>
													<c:set var="totalSpecificationAmount" value="${totalSpecificationAmount + specification.specificationSum}" />
											</c:forEach>
											<thead class="thead-default">
												<tr>
													<th class="text-center"><strong class="text-dark"> Кол-во: <fmt:formatNumber type="number"
																minFractionDigits="0" maxFractionDigits="0" value="${fn:length(agreement.specifications)}" />
													</strong></th>
													<th class="text-center"><strong class="text-dark"> Сумма всего: <fmt:formatNumber
																type="number" minFractionDigits="2" maxFractionDigits="2" value="${totalSpecificationAmount}" /> грн.
													</strong></th>
													<th></th>
												</tr>
											</thead>
										</table>
										
										</div>
									</div>
										<br>
									</c:forEach>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			
				<br>

			</div>

			<!-- footer -->
			<jsp:include page="footer.jsp" />