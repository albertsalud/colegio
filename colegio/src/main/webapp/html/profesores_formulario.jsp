<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Formulario de profesor" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Formulario de profesor</h1>
		<p><a href="/director/profesores">Volver a la pantalla anterior</a></p>
		<form:form action="guardar" method="post" modelAttribute="profesorBO" class="col-sm-5 col-md-8">
			<form:hidden path="id"/>
			<div class="form-group">
				<label for="nombre">Nombre:</label>
				<form:input path="nombre" class="form-control" />
				<form:errors path="nombre" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="apellidos">Apellidos:</label>
				<form:input path="apellidos" class="form-control" />
				<form:errors path="apellidos" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="dni">DNI (será el identificador de usuario):</label>
				<form:input path="dni" class="form-control" />
				<form:errors path="dni" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<form:input path="email" class="form-control" />
				<form:errors path="email" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="telefonoContacto">Tel. Contacto:</label>
				<form:input path="telefonoContacto" class="form-control" />
				<form:errors path="telefonoContacto" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="contrasena">Contraseña:</label>
				<form:password path="contrasena" class="form-control" showPassword="true"/>
				<form:errors path="contrasena" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label for="contrasena2">Repite contraseña:</label>
				<form:password path="contrasena2" class="form-control" showPassword="true" />
				<form:errors path="contrasena2" cssClass="text-danger" />
			</div>
			<p><input type="submit" value="Guardar" class="btn btn-primary"/></p>
		</form:form>
	</div>
</body>
</html>