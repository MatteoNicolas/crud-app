<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar</title>

<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar usuário:</h1>
	<form name="formRegister" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="box3" readonly
					value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="box1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="login" class="box2"
					value="<%out.print(request.getAttribute("login"));%>"></td>
			</tr>
			<tr>
				<td><input type="password" name="password" class="box2"
					value="<%out.print(request.getAttribute("password"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="btn1"
			onclick="validation()"> <a href="main" class="btn1">Voltar</a>
	</form>
	<script src="scripts/validation.js"></script>
</body>
</html>