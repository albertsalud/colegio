<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Integer sumLevel = (Integer) request.getAttribute("sumLevel");
if(sumLevel == null) sumLevel = 0;

Boolean status = (Boolean) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<title>Ejercicios de sumas</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<h1>Ejercicios de sumas</h1>
	<p>Vamos a hacer sumas!</p>
	<form method="post" action="/mates/sumas/setLevel">
		<p>Elije un nivel de sumas:
			<select name="sumLevel" class="form-control">
				<option value="1" <%if(1 == sumLevel) out.print("selected"); %>>1</option>
				<option value="2" <%if(2 == sumLevel) out.print("selected"); %>>2</option>
				<option value="3" <%if(3 == sumLevel) out.print("selected"); %>>3</option>
				<option value="4" <%if(4 == sumLevel) out.print("selected"); %>>4</option>
				<option value="5" <%if(5 == sumLevel) out.print("selected"); %>>5</option>
			</select>
			<button class="btn btn-primary">Empezar!</button>
		</p>
	</form>
	<%if(status != null) { %>
		<%if(status) { %>
			<p class="correcto">Correcto!</p>
		
		<%} else { %>
			<p class="incorrecto">Error! <%=request.getParameter("num1") %> + <%=request.getParameter("num2") %> = <span style="text-decoration: line-through;"><%=request.getParameter("result") %></span> <span style="font-size: 1.5em"><strong><%=Integer.parseInt(request.getParameter("num1")) + Integer.parseInt(request.getParameter("num2")) %></strong></span></p>
		
		<%} %>
	<%} %>
	<%if(request.getAttribute("num1") != null){	%>
		<form action="/mates/sumas/validate" method="post">
			<input type="hidden" name="num1" value="<%=request.getAttribute("num1") %>" />
			<input type="hidden" name="num2" value="<%=request.getAttribute("num2") %>" />
			<table class="operationTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td align="right"><%=request.getAttribute("num1") %></td>
				</tr>
				<tr class="lastLine">
					<td>+</td>
					<td align="right"><%=request.getAttribute("num2") %></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input autocomplete="off" class="form-control" type="text" name="result" style="text-align:rigth" id="result"/></td>
				</tr>
			</table>
			<p><button class="btn btn-primary">Validar!</button></p>
		</form>
		<script>
			$("#result").focus();
		</script>
	<%} %>
	<p><a href="/mates">&lt; Volver al inicio</a></p>
</body>
</html>