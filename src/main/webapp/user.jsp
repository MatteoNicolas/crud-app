<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html lang="pt=br">
<head>
<meta charset="utf-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Usuário: </h1>
	<a href="new.html" class="btn1">Cadastrar</a>
		<table id="tabela">	
		<thead>			
			<tr>
				<th>Id:</th>
				<th>Nome:</th>
				<th>Login:</th>
				<th>Password:</th>
			</tr>			
		</thead>
		<tbody>
			<% for (int i = 0; i <lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getId()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getLogin()%></td>
					<td><%=lista.get(i).getPassword()%></td>					
				</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>