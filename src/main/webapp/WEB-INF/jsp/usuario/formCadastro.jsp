<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Cadastrar</h1>
	
	<form action="${linkTo[UsuarioController].adiciona(null)}" method="post">
		
		<label for="nome">Nome</label>
		<input type="text" name="usuario.nome" id="nome" class="form-control" value="${usuario.nome}">
		<alura:ValidationMessage name="usuario.nome"/>
		
		<label for="email">Email</label>
		<input type="text" name="usuario.email" id="email" class="form-control" value="${usuario.email}">
		<alura:ValidationMessage name="usuario.email"/>
		
		<label for="senha">Senha</label>
		<input type="password" name="usuario.senha" id="senha" class="form-control">
		<alura:ValidationMessage name="usuario.senha"/>
		
		<input type="submit" value="Cadastrar" class="btn">
		
	</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>