<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Login" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<script type="text/javascript">
	<%if(request.getParameter("error") != null){%>
		alert(<%=request.getParameter("error") %>);
	<%} %>
	
	</script>
	<div class="container">
		<h1>Bienvenido</h1>
		<div class="row">
			<form action="/login" method="post" class="form col-sm-6">
				<div class="form-group">
					<label class="col-label">Nombre de usuario:</label>
					<input type="text" name="username" class="form-control"/>
				</div>
				<div class="form-group">
					<label class="col-label">Contraseña:</label>
					<input type="password" name="password" class="form-control"/>
				</div>
				<input type="submit" value="Entrar" class="btn btn-primary"/>
			</form>
			<c:if test="${not empty error_message}">
				<p class="text-danger">${error_message}</p>
			</c:if>
		</div>
	</div>
</body>
</html>