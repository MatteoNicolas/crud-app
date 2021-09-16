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
<title>Admin</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Gerenciamento</h1>
	<a href="new.html" class="btn1">Cadastrar</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id:</th>
				<th>Nome:</th>
				<th>Login:</th>
				<th>Password:</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getLogin()%></td>
				<td><%=lista.get(i).getPassword()%></td>
				<td><a href="select?id=<%=lista.get(i).getId()%>" class="btn2">Editar</a>
					<a href="javascript: confirmation(<%=lista.get(i).getId()%>)" class="btnRed">Excluir</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmation.js"></script>
</body>
</html>