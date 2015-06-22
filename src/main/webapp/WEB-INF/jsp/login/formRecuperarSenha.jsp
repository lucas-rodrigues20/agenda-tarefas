<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Recuperar Senha</h1>
	
	<form action="${linkTo[LoginController].recuperaSenha(null)}" method="post">
		<alura:ValidationMessage name="email_invalido"/>
		
		<label for="email">Email</label>
		<input type="text" name="email" id="email" class="form-control">
		
		<input type="submit" value="Recuperar" class="btn">
		
	</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>