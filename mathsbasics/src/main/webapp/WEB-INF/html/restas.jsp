<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Integer remainderLevel = (Integer) request.getAttribute("remainderLevel");
if(remainderLevel == null) remainderLevel = 0;

Boolean status = (Boolean) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<title>Ejercicios de restas</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<h1>Ejercicios de restas</h1>
	<p>Vamos a hacer restas!</p>
	<form method="post" action="/mates/restas/setLevel">
		<p>Elije un nivel de restas:
			<select name="remainderLevel">
				<option value="1" <%if(1 == remainderLevel) out.print("selected"); %>>1</option>
				<option value="2" <%if(2 == remainderLevel) out.print("selected"); %>>2</option>
				<option value="3" <%if(3 == remainderLevel) out.print("selected"); %>>3</option>
				<option value="4" <%if(4 == remainderLevel) out.print("selected"); %>>4</option>
				<option value="5" <%if(5 == remainderLevel) out.print("selected"); %>>5</option>
			</select>
			<button>Empezar!</button>
		</p>
	</form>
	<%if(status != null) { %>
		<%if(status) { %>
			<p class="correcto">Correcto!</p>
		
		<%} else { %>
			<p class="incorrecto">Error! <%=request.getParameter("num1") %> - <%=request.getParameter("num2") %> = <span style="text-decoration: line-through;"><%=request.getParameter("result") %></span> <span style="font-size: 1.5em"><strong><%=Integer.parseInt(request.getParameter("num1")) - Integer.parseInt(request.getParameter("num2")) %></strong></span></p>
		
		<%} %>
	<%} %>
	<%if(request.getAttribute("num1") != null){	%>
		<form action="/mates/restas/validate" method="post">
			<input type="hidden" name="num1" value="<%=request.getAttribute("num1") %>" />
			<input type="hidden" name="num2" value="<%=request.getAttribute("num2") %>" />
			<table class="operationTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td align="right"><%=request.getAttribute("num1") %></td>
				</tr>
				<tr class="lastLine">
					<td>-</td>
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