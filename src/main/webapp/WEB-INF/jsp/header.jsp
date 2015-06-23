<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Agenda de Tarefas</title>
	<link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet" />
	<link href="<c:url value='/css/site.css' />" rel="stylesheet" />
</head>
<body>

	<nav>
		<ul class="nav nav-tabs">
			<li><a href="${linkTo[UsuarioController].formCadastro()}">Home</a></li>
			
			<c:if test="${usuarioLogado.logado}">
				<c:if test="${usuarioLogado.admin}">
					<li><a href="${linkTo[UsuarioController].lista()}">Usuários</a></li>
					<li><a href="${linkTo[ReagendamentoController].formReagendamento()}">Reagendar Tarefas</a></li>
				</c:if>
				
				<c:if test="${!usuarioLogado.admin}">
					<li><a href="${linkTo[TarefaController].formTarefa()}">Nova Tarefa</a></li>
					<li><a href="${linkTo[TarefaController].lista()}">Tarefas</a></li>
				</c:if>
				
				<li><a href="${linkTo[LoginController].desloga()}">Logout</a></li>
			</c:if>
			<c:if test="${!usuarioLogado.logado}">
				<li><a href="${linkTo[LoginController].formLogin()}">Login</a></li>
			</c:if>
			
		</ul>
	</nav>
	
	<div class="container">
		<main class="col-sm-8">