<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Información de evaluaciones" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Evaluaciones de ${alumno.nombre} ${alumno.apellidos}</h1>
		<h2>Curso: ${alumno.curso.nombre} ${alumno.curso.linea}</h2>
		<table class="table table-striped table-hover">
			<thead class="thead-light">
				<tr>
					<th>Asignatura</th>
					<th>Profesor</th>
					<th>Nota</th>
				</tr>
			</thead>
			<c:forEach items="${alumno.evaluaciones}" var="evaluacion">
				<tr>
					<td>${evaluacion.clase.asignatura.nombre}</td>
					<td>${evaluacion.clase.profesor.nombre} ${evaluacion.clase.profesor.apellidos}</td>
					<td>${evaluacion.nota}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>