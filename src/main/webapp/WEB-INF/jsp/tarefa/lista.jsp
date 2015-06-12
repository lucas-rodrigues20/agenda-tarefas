<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Tarefas Cadastrados</h1>
	
	<p style="color: green;">${mensagem}</p>
	<alura:ValidationMessage name="exclusao_invalida"/>
	<alura:ValidationMessage name="edicao_invalida"/>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Descrição</th>
				<th>Data</th>
				<th>Horário</th>
				<th>Finalizada?</th>
				<th>Alterar</th>
				<th>Remover</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ltTarefas}" var="tarefas">
				<tr>
					<td>${tarefas.descricao}</td>
					<td>${tarefas.data.time}</td>
					<td>${tarefas.horario}</td>
					<td>${tarefas.finalizado}</td>
					<td><a href="<c:url value='/tarefa/edita?tarefa.id=${tarefas.id}'/>">Alterar</a></td>
					<td><a href="<c:url value='/tarefa/remove?tarefa.id=${tarefas.id}'/>">Remover</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>