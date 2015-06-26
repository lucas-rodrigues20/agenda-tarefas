<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

		<div class="container col-md-4"></div>

        <div class="container col-md-4">

			<div class="page-header">
				<h1>Login</h1>
			</div>
			
			<c:if test="${not empty mensagem}">
				<div class="alert alert-success" role="alert">
					<p>${mensagem}</p>
				</div>
			</c:if>
			
			<form action="${linkTo[LoginController].autentica(null, null)}" method="post">
			
				<div class="form-group">
					<label for="email">Email</label>
					<input type="text" name="email" id="email" class="form-control">
				</div>
				
				<div class="form-group">
					<label for="senha">Senha</label>
					<input type="password" name="senha" id="senha" class="form-control">
				</div>
				
				<div class="form-group">
					<alura:ValidationMessage name="login_invalido"/>
				</div>
				
				<button class="btn btn-primary btn-block" type="submit">Login</button>
				
			</form>
			
			<a href="${linkTo[UsuarioController].formCadastro()}" class="pull-left">Cadastrar-se</a>
			<a href="${linkTo[LoginController].formRecuperarSenha()}" class="pull-right">Recuperar Senha</a>
			
	</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>