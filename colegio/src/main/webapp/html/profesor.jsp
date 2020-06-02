<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="_header.jsp">
	<jsp:param value="Evaluaciones" name="pageTitle"/>
</jsp:include>
</head>
<body>
	<div class="container">
		<h1>Evaluaciones de ${profesor.nombre} ${profesor.apellidos}</h1>
		<h2>Selecciona un curso y una asignatura para evaluar</h2>
		<div class="row">
			<div class="col-12">
				<form action="/profesor/ver-evaluacion" method="post" class="form-inline">
					<div class="form-group">
						<label>Curso: </label>
						<select name="curso" class="form-control ml-2" id="select-curso">
							<c:forEach items="${cursos}" var="curso">
								<option value="${curso.id}">${curso.nombre} ${curso.linea}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group ml-2">
						<label>Asignatura: </label>
						<select name="asignatura" class="form-control ml-2" id="select-asignatura">
							<c:forEach items="${asignaturas}" var="asignatura">
								<option value="${asignatura.id}">${asignatura.nombre}</option>
							</c:forEach>
						</select>
					</div>
					<input type="submit" value="Evaluar" class="btn btn-primary ml-4"/>
				</form>
			</div>
		</div>
		<c:if test="${not empty evaluacionesClase}">
			<div class="row">
				<div class="col-12 mt-4">
					<p>Evaluación de <span id="asignatura-name" style="font-weight: bold;"></span> de <span id="curso-name" style="font-weight: bold;"></span></p>
					<div class="row">
						<div class="col-8">
							<form:form modelAttribute="evaluacionesClase" method="post" action="/profesor/guardar-evaluaciones">
								<form:input type="hidden" path="curso" />
								<form:input type="hidden" path="asignatura" />
								<table class="table table-striped table-hover mt-4">
									<thead class="thead-light">
										<tr>
											<th class="col-8">Alumno</th>
											<th>Nota</th>
										</tr>
									</thead>
									<c:forEach items="${evaluacionesClase.evaluaciones}" var="evaluacion" varStatus="status">
										<tr>
											<td>
												${evaluacion.nombreAlumno}
												<form:hidden path="evaluaciones[${status.index}].nombreAlumno" />
												<form:hidden path="evaluaciones[${status.index}].idClase" />
												<form:hidden path="evaluaciones[${status.index}].idAlumno" />
											</td>
											<td>
												<form:input path="evaluaciones[${status.index}].nota" type="text" class="form-control" style="width:60px" />
												<form:errors path="evaluaciones[${status.index}].nota" cssClass="text-danger" />
											</td>
										</tr>
									</c:forEach>
								</table>
								<input type="submit" value="Guardar evaluaciones" class="btn btn-primary"/>
							</form:form>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$("#select-asignatura option[value='" + ${evaluacionesClase.asignatura} + "']").prop("selected", "true");
				$("#select-curso option[value='" + ${evaluacionesClase.curso} + "']").prop("selected", "true");
				$("#curso-name").html($("#select-curso option:selected").html());
				$("#asignatura-name").html($("#select-asignatura option:selected").html());
			</script>
		</c:if>
	</div>
</body>
</html>