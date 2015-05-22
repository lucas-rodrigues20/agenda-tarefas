<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Login</h1>
	
	<form action="${linkTo[LoginController].autentica(null, null)}" method="post">
		<alura:ValidationMessage name="login_invalido"/>
		
		<label for="email">Email</label>
		<input type="text" name="email" id="email" class="form-control"">
		
		<label for="senha">Senha</label>
		<input type="password" name="senha" id="senha" class="form-control">
		
		<input type="submit" value="Login" class="btn">
		<a href="${linkTo[UsuarioController].formCadastro()}" class="btn">Cadastrar-se</a>
		
	</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>