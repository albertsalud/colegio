<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Integer multiplicationLevel = (Integer) request.getAttribute("multiplicationLevel");
if(multiplicationLevel == null) multiplicationLevel = 0;

Integer multiplicationTable = (Integer) request.getAttribute("multiplicationTable");
if(multiplicationTable == null) multiplicationTable = 0;

Boolean status = (Boolean) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<title>Ejercicios de multiplicaciones</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<h1>Ejercicios de multiplicaciones</h1>
	<p>Vamos a hacer multiplicaciones!</p>
	<form method="post" action="/mates/multiplicaciones/setLevel">
		<p>Elije la tabla para multiplicar:
			<select name="multiplicationTable">
				<%for(int i=1; i<11; i++){ %>
					<option value="<%=i %>" <%if(i == multiplicationTable) out.print("selected"); %>><%=i %></option>
				<%} %>
			</select>
		</p>
		<p>Elije el nivel de las multiplicaciones:
			<select name="multiplicationLevel">
				<%for(int i=1; i<5; i++){ %>
					<option value="<%=i %>" <%if(i == multiplicationLevel) out.print("selected"); %>><%=i %></option>
				<%} %>
			</select>
			<button>Empezar!</button>
		</p>
	</form>
	<%if(status != null) { %>
		<%if(status) { %>
			<p class="correcto">Correcto!</p>
		
		<%} else { %>
			<p class="incorrecto">Error! <%=request.getParameter("num1") %> * <%=request.getParameter("num2") %> = <span style="text-decoration: line-through;"><%=request.getParameter("result") %></span> <span style="font-size: 1.5em"><strong><%=Integer.parseInt(request.getParameter("num1")) * Integer.parseInt(request.getParameter("num2")) %></strong></span></p>
		
		<%} %>
	<%} %>
	<%if(request.getAttribute("num1") != null){	%>
		<form action="/mates/multiplicaciones/validate" method="post">
			<input type="hidden" name="num1" value="<%=request.getAttribute("num1") %>" />
			<input type="hidden" name="num2" value="<%=request.getAttribute("num2") %>" />
			<table class="operationTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td align="right"><%=request.getAttribute("num1") %></td>
				</tr>
				<tr class="lastLine">
					<td>x</td>
					<td align="right"><%=request.getAttribute("num2") %></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input autocomplete="off" type="text" name="result" style="text-align:rigth" id="result"/></td>
				</tr>
			</table>
			<p><button>Validar!</button></p>
		</form>
		<script>
			$("#result").focus();
		</script>
	<%} %>
	<p><a href="/mates">&lt; Volver al inicio</a></p>
</body>
</html>