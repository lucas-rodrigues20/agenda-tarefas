<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<div class="container col-md-2"></div>
	
	<div class="container col-md-8">
	
		<div class="page-header">
			<h1>Atualizar Informações</h1>
		</div>
	
		<c:if test="${not empty mensagem}">
			<div class="alert alert-success" role="alert">
				<p>${mensagem}</p>
			</div>
		</c:if>

		<form action="${linkTo[UsuarioController].editarUsuario(null)}" method="post">
	
			<div class="form-group">
				<input type="hidden" name="usuario.id" value="${usuario.id}">
			</div>
			
			<div class="form-group">
				<label for="nome">Nome</label>
				<input type="text" name="usuario.nome" id="nome" class="form-control" 
					value="${usuario.nome}">
				<alura:ValidationMessage name="usuario.nome" />
			</div>

			<div class="form-group">
				<label for="email">Email</label>
				<input type="text" name="usuario.email" id="email" class="form-control"
					value="${usuario.email}">
				<alura:ValidationMessage name="usuario.email" />
			</div>

			<div class="form-group">
				<label for="senha">Senha</label>
				<input type="password" name="usuario.senha" id="senha" class="form-control"
					value="${usuario.senha}">
				<alura:ValidationMessage name="usuario.senha" />
			</div>
	
			<button class="btn btn-primary btn-block" type="submit">Atualizar</button>
	
		</form>

</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>