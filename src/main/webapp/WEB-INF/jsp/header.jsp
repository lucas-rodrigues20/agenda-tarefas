<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Agenda de Tarefas</title>
	
	<link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet" />
	<link href="<c:url value='/css/site.css' />" rel="stylesheet" />
	<link href="<c:url value='/css/sticky-footer.css' />" rel="stylesheet" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="<c:url value='/js/bootstrap.js' />"></script>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${linkTo[UsuarioController].formCadastro()}">Agenda Tarefas</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${linkTo[UsuarioController].formCadastro()}">Home</a></li>
					
					<c:if test="${usuarioLogado.logado}">
						<c:if test="${usuarioLogado.admin}">
							<li><a href="${linkTo[UsuarioController].lista()}">Usuários</a></li>
							<li><a href="${linkTo[ReagendamentoController].formReagendamento()}">Reagendar Tarefas</a></li>
						</c:if>
					</c:if>

					<c:if test="${usuarioLogado.logado}">
						<c:if test="${!usuarioLogado.admin}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Tarefas <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${linkTo[TarefaController].formTarefa()}">Nova Tarefa</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="${linkTo[TarefaController].lista()}">Tarefas Cadastradas</a></li>
								</ul></li>
						</c:if>
					</c:if>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					
					<c:if test="${!usuarioLogado.logado}">
						<li><a href="${linkTo[LoginController].formLogin()}">Login</a></li>
					</c:if>
					
					<c:if test="${usuarioLogado.logado}">
						<li class="dropdown">

						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
							aria-haspopup="true" aria-expanded="false">${usuarioLogado.usuario.nome} <span class="caret"></span></a>
							
							<ul class="dropdown-menu">
								<li><a href="${linkTo[UsuarioController].formEdita()}">Atualizar Dados</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="${linkTo[LoginController].desloga()}">Logout</a></li>
							</ul>
							
						</li>
					</c:if>

				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container">