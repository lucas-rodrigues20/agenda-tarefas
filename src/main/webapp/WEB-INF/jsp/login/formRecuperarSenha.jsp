<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

		<div class="container col-md-4"></div>

        <div class="container col-md-4">

			<div class="page-header">
				<h1>Recuperar Senha</h1>
			</div>
			
			<form action="${linkTo[LoginController].recuperaSenha(null)}" method="post">
				
				<div class="form-group">
					<label for="email">Email</label>
					<input type="text" name="email" id="email" class="form-control">
				</div>
				
				<div class="form-group">
					<alura:ValidationMessage name="email_invalido"/>
				</div>
				
				<button class="btn btn-primary btn-block" type="submit">Recuperar</button>
				
			</form>
			
		</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>