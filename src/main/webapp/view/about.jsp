<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<title>About</title>

<div class="container-fluid">
	
	<br>
	<h1 class="text-center">
		Кратко о приложении SPDApp2
	</h1>
	<h5 class="text-center">
		(приложение находится в разработке, поэтому некоторые элементы либо могу отсутствовать, либо иметь ограниченный функционал)
	</h5>
	<br>
	
	<div class="row">
		<div class="col-1"></div>
		<div class="col">
			
			<p class="lead text-justify">
			<strong>Приложение SPDApp2 создано в помощь для бухгалтеров IT-отрасли и предназначено для автоматизации 
			учета расчетов с субъектами предпринимательской деятельности.</strong></p>
			<br>
			<p class="lead text-justify">		
			<strong>Данное приложение позволяет существенно сократить время на подготовку документов, связанных с ведением
			документооборота между СПД и юридическим лицом.</strong></p>
			<br>
			<p class="lead text-justify">
				<strong>Технологии, использованные в данном приложении:</strong>
			</p>
			<ul>
				<li><em>Java 8</em></li>
				<li><em>Spring Framework (MVC, Data, Security)</em></li>
				<li><em>JPA/Hibernate, MySQL</em></li>
				<li><em>JSP + Bootstrap 4 + Javascript</em></li>
				<li><em>JasperReports (for generation PDF)</em></li>
				<li><em>Maven</em></li>
				<li><em>Tomcat 8</em></li>
			</ul>
		</div>
		<div class="col-1"></div>
	</div>
	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="footer.jsp" />