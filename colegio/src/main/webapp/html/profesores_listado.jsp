<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Listado de profesores" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Listado de profesores</h1>
		<p><a href="/director">Volver al menú anterior</a></p>
		<p><a class="btn btn-primary" href="profesores/nuevo">Crear nuevo profesor</a></p>
		<c:if test="${not empty profesores}">
			<table class="table table-striped table-hover col-md-10 table-responsive">
				<thead class="thead-light">
					<tr>
						<th>Apellidos</th>
						<th>Nombre</th>
						<th>E-mail</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<c:forEach items="${profesores}" var="profesor">
					<tr>
						<td>${profesor.apellidos}</td>
						<td>${profesor.nombre}</td>
						<td>${profesor.email}</td>
						<td><a class="btn btn-primary" href="profesores/${profesor.id}">Modificar</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>