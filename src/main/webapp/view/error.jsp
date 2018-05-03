<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<title>Error</title>

<div class="container-fluid">
	
	<div class="jumbotron">
		<h1 class="display-3 text-center">Oops!!! Error...</h1>
		<hr class="my-4">
		
		<div class="row">
			<div class="col text-center">
				Return back => 
				<button class="btn btn-danger btn-lg" onclick="goBack()">Go Back</button>
			</div>
		</div>
	</div>
	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="footer.jsp" />