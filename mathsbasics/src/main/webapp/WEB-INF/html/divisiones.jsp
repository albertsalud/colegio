<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Integer divisionLevel = (Integer) request.getAttribute("divisionLevel");
if(divisionLevel == null) divisionLevel = 0;

Boolean status = (Boolean) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<title>Ejercicios de divisiones</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<h1>Ejercicios de divisiones</h1>
	<p>Vamos a hacer divisiones!</p>
	<form method="post" action="/mates/divisiones/setLevel">
		<p>Elije el número para dividir:
			<select name="divisionLevel">
				<%for(int i=1; i<11; i++){ %>
					<option value="<%=i %>" <%if(i == divisionLevel) out.print("selected"); %>><%=i %></option>
				<%} %>
			</select>
			<button>Empezar!</button>
		</p>
	</form>
	<%if(status != null) { %>
		<%if(status) { %>
			<p class="correcto">Correcto! <%=request.getParameter("num1") %> &divide; <%=request.getParameter("num2") %> = <%=request.getParameter("result") %> R <%=request.getParameter("mod") %></p>
		
		<%} else { %>
			<p class="incorrecto">
				Error! <%=request.getParameter("num1") %> &divide; <%=request.getParameter("num2") %> = 
				<span style="text-decoration: line-through;"><%=request.getParameter("result") %> R <%=request.getParameter("mod") %></span> 
				<span style="font-size: 1.5em">
					<strong>
						<%=Integer.parseInt(request.getParameter("num1")) / Integer.parseInt(request.getParameter("num2")) %> R 
						<%=Integer.parseInt(request.getParameter("num1")) % Integer.parseInt(request.getParameter("num2")) %>
					</strong>
				</span>
			</p>
		
		<%} %>
	<%} %>
	<%if(request.getAttribute("num1") != null){	%>
		<form action="/mates/divisiones/validate" method="post">
			<input type="hidden" name="num1" value="<%=request.getAttribute("num1") %>" />
			<input type="hidden" name="num2" value="<%=request.getAttribute("num2") %>" />
			<table class="operationTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td align="right"><%=request.getAttribute("num1") %></td>
				</tr>
				<tr class="lastLine">
					<td>&divide;</td>
					<td align="right"><%=request.getAttribute("num2") %></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input autocomplete="off" type="text" name="result" style="text-align:rigth" id="result"/> R <input autocomplete="off" type="text" name="mod"/></td>
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