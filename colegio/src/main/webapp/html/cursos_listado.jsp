<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Listado de cursos" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Listado de cursos</h1>
		<p><a href="/director">Volver al menú anterior</a></p>
		<p><a class="btn btn-primary" href="cursos/nuevo" role="button">Crear nuevo curso</a></p>
		<c:if test="${not empty cursos}">
			<table class="table table-striped table-hover mt-4 col-md-6">
				<thead class="thead-light">
					<tr>
						<th>Nombre</th>
						<th>Línea</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<c:forEach items="${cursos}" var="curso">
					<tr>
						<td>${curso.nombre}</td>
						<td>${curso.linea}</td>
						<td><a class="btn btn-primary" href="cursos/${curso.id}">Modificar</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>