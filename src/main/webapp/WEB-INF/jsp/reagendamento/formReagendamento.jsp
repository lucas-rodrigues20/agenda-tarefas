<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>
	
	<div class="container col-md-4"></div>

        <div class="container col-md-4">
	
			<div class="page-header">
				<h1>Reagendar Tarefas Manualmente</h1>
			</div>
		
			<c:if test="${not empty mensagem}">
				<div class="alert alert-success" role="alert">
					<p>${mensagem}</p>
				</div>
			</c:if>
			
			<c:if test="${reagendamentoFeito}">
				<p>O reagendamento já foi feito</p>
			</c:if>
			
			<c:if test="${!reagendamentoFeito}">
			
				<form action="${linkTo[ReagendamentoController].reagendar()}" method="post">
					<button class="btn btn-primary btn-block" type="submit">Reagendar</button>
				</form>
				
			</c:if>
			
		</div>
			

<c:import url="/WEB-INF/jsp/footer.jsp"/>