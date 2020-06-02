<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Formulario de curso" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Formulario de cursos</h1>
		<p><a href="/director/cursos">Volver a la pantalla anterior</a></p>
		<form:form action="guardar" method="post" modelAttribute="cursoBO" class="form">
			<form:hidden path="id"/>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="nombre">Nombre: </label>
						<form:input path="nombre" class="form-control" id="nombre"/>
					</div>
					<div class="form-group">
						<label for="linea">Línea: </label>
						<form:input path="linea" class="form-control" id="linea"/>
					</div>
					<div class="form-check">
						<form:checkbox path="vigente" id="vigente" class="form-check-input"/>
						<label for="vigente">Vigente</label>
					</div>
					<div class="form-group">
						<label for="asignaturas-disponibles">Listado de asignaturas disponibles:</label>
						<select size="6" id="asignaturas-disponibles" class="form-control">
							<c:forEach items="${asignaturas}" var="asignatura">
								<option id="asignatura-${asignatura.id}" value="${asignatura.id}">${asignatura.nombre}</option>
							</c:forEach>
						</select>
						<button class="btn btn-primary mt-3" onclick="return addAsignatura()">Añadir asignatura</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<p>Listado de asignaturas asignadas al curso:</p>
					<table id="tabla-de-asignaturas" class="table table-striped table-hover">
						<thead class="thead-light">
							<tr>
								<th>Asignatura</th>
								<th>Profesor</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<c:forEach items="${cursoBO.clases}" var="clase">
							<script>
								$("option#asignatura-" + ${clase.id}).remove();
							</script>
							<tr id="clase-${clase.asignatura.id}">
								<td>
									<input type="hidden" name="idAsignatura" value="${clase.asignatura.id}" />
									${clase.asignatura.nombre}
								</td>
								<td>
									<select name="idProfesor" class="form-control">
										<c:forEach items="${profesores}" var="profesor">
											<c:set var="selected" value="${profesor.id == clase.profesor.id}" />
											<option ${selected == true ? "selected" : ""} value="${profesor.id}">${profesor.nombre} ${profesor.apellidos}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<button class="btn btn-outline-danger" onclick="return removeAsignatura(${clase.asignatura.id})">Eliminar</button>
								</td>
							</tr>
						</c:forEach>
					</table>
					<p><input class="btn btn-primary" type="submit" value="Guardar" /></p>
				</div>
			</div>
		</form:form>
		<div style="display:none">
			<select id="profesores-template" name="idProfesor">
				<c:forEach items="${profesores}" var="profesor">
					<option ${selected == true ? "selected" : ""} value="${profesor.id}">${profesor.nombre} ${profesor.apellidos}</option>
				</c:forEach>
			</select>
			<select id="asignaturas-template">
				<c:forEach items="${asignaturas}" var="asignatura">
					<option id="asignatura-${asignatura.id}" value="${asignatura.id}">${asignatura.nombre}</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
<script>
function addAsignatura(){
	var asignaturaSeleccionada = $("#asignaturas-disponibles option:selected");
	
	if(asignaturaSeleccionada.length == 0) {
		alert("Debe seleccionar una asignatura");
		return false;
	}
	
	$("#tabla-de-asignaturas").append(
		"<tr id='clase-" + asignaturaSeleccionada.val() + "'>" +
		"<td>" + asignaturaSeleccionada.html() +
		"<input type='hidden' name='idAsignatura' value='" + asignaturaSeleccionada.val() + "' />" +
		"</td>" +
		"<td><select class='form-control' name='idProfesor'>" + $("#profesores-template").html() + "</select>"+
		"</td>" +
		"<td>" +
		"<button class='btn btn-outline-danger' onclick='removeAsignatura(" + asignaturaSeleccionada.val() + ")'>Eliminar</button>" +
		"</td>"
	);
	
	asignaturaSeleccionada.remove();
	
	return false;
}

function removeAsignatura(idAsignatura){
	
	$("tr#clase-" + idAsignatura).remove();
	$("select#asignaturas-template option#asignatura-" + idAsignatura).clone().appendTo("select#asignaturas-disponibles");
	
	return false;
}
</script>
</html>