<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Formulario de asignatura" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Formulario de asignatura</h1>
		<p><a href="/director/asignaturas">Volver a la pantalla anterior</a></p>
		<div class="row">
			<form:form action="guardar" method="post" modelAttribute="asignaturaBO" class="col-sm-3">
				<form:hidden path="id"/>
				<div class="form-group">
					<label for="nombre">Nombre:</label>
					<form:input path="nombre" class="form-control" id="nombre"/>
					<form:errors path="nombre" class="text-danger" />
				</div>
				<p><input type="submit" value="Guardar" class="btn btn-primary"/></p>
			</form:form>
		</div>
	</div>
</body>
</html>