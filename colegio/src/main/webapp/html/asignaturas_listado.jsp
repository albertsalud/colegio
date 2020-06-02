<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Listado de asignaturas" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Listado de asignaturas</h1>
		<p><a href="/director">Volver al menú anterior</a></p>
		<p><a class="btn btn-primary" href="asignaturas/nueva">Crear nueva asignatura</a></p>
		<c:if test="${not empty asignaturas}">
			<table class="table col-md-5 table-striped table-hover">
				<thead class="thead-light">
					<tr>
						<th>Nombre</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<c:forEach items="${asignaturas}" var="asignatura">
					<tr>
						<td>${asignatura.nombre}</td>
						<td><a class="btn btn-primary" href="asignaturas/${asignatura.id}">Modificar</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>