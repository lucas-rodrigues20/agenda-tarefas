<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Tarefas Cadastrados</h1>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Descrição</th>
				<th>Data</th>
				<th>Horário</th>
				<th>Finalizada?</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ltTarefas}" var="tarefas">
				<tr>
					<td>${tarefas.descricao}</td>
					<td>${tarefas.data.time}</td>
					<td>${tarefas.horario}</td>
					<td>${tarefas.finalizado}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>