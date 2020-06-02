<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Director" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Bienvenido, ${usuario.nombre}</h1>
		<p>Selecciona una de las siguientes opciones:</p>
		<ul class="list-group list-group-flush">
			<li class="list-group-item"><a href="director/asignaturas">Gestionar asignaturas</a></li>
			<li class="list-group-item"><a href="director/profesores">Gestionar profesores</a></li>
			<li class="list-group-item"><a href="director/cursos">Gestionar cursos</a></li>
			<li class="list-group-item"><a href="director/alumnos">Gestionar alumnos</a></li>
		</ul>
	</div>
</body>
</html>