<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<p class="p4">

<div class="container">
	<nav class="navbar fixed-bottom navbar-light" style="background-color: #E6E6FA;">
		<jsp:useBean id="date" class="java.util.Date" /> 
			<div class="mx-auto">
				<span>© 2016-<fmt:formatDate value="${date}" pattern="yyyy" /> Apollon Saifullin. All rights reserved. 
					Contact information: <a href="mailto:apollo.saifullin@gmail.com"> e-mail</a>
				</span>
			</div>
			
			
			
	</nav>
	
</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<c:url value="/resources/js/jquery-3.2.1.slim.min.js"/>" ></script>
	<script src="<c:url value="/resources/js/popper.min.js"/>" ></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>" ></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js"/>"></script>
	<script src="<c:url value="/resources/js/accounting.min.js"/>"></script>
	<script src="<c:url value="/resources/scripts.js"/>"> charset="utf-8"</script>
	
</body>
</html>