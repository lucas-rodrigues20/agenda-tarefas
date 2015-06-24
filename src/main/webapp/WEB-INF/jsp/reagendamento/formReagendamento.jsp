<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Reagendar Tarefas Manualmente</h1>
	
	<p style="color: green;">${mensagem}</p>
	
	<c:if test="${reagendamentoFeito}">
		<p>O reagendamento já foi feito</p>
	</c:if>
	
	<c:if test="${!reagendamentoFeito}">
	
		<form action="${linkTo[ReagendamentoController].reagendar()}" method="post">
			<input type="submit" value="Reagendar" class="btn">
		</form>
		
	</c:if>

<c:import url="/WEB-INF/jsp/footer.jsp"/>