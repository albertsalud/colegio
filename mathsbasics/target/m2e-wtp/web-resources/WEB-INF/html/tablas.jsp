<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Integer tableLevel = null;
try {
	tableLevel = Integer.parseInt(request.getParameter("tableLevel"));
} catch (Exception e){
	tableLevel = 0;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Tablas de multiplicar</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<h1>Tablas de multiplicar</h1>
	<p>Vamos a aprender las tablas de multiplicar!</p>
	<form method="post" action="/mates/tablas">
		<p>Elije una tabla:
			<select name="tableLevel">
				<%for(int i=1; i<11; i++){ %>
					<option value="<%=i %>" <%if(i == tableLevel) out.print("selected"); %>><%=i %></option>
				<%} %>
			</select>
			<button>Mostrar!</button>
		</p>
	</form>
	<%if(tableLevel != 0){
		for(int i = 0; i < 11; i++){%>
			<p><%=tableLevel %> x <%=i %> = <%= tableLevel * i %></p>
		<%}
	} %>
	<p><a href="/mates">&lt; Volver al inicio</a></p>
</body>
</html>