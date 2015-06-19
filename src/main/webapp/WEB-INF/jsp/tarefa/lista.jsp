<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Tarefas Cadastradas</h1>
	
	<p style="color: green;">${mensagem}</p>
	<alura:ValidationMessage name="exclusao_invalida"/>
	<alura:ValidationMessage name="edicao_invalida"/>
	<alura:ValidationMessage name="finalizacao_invalida"/>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Descrição</th>
				<th>Data</th>
				<th>Horário</th>
				<th>Frequência</th>
				<th>Finalizar</th>
				<th>Alterar</th>
				<th>Remover</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ltTarefas}" var="tarefas">
				<tr>
					<td>${tarefas.descricao}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${tarefas.data.time}" /></td>
					<td>${tarefas.horario}</td>
					<td>
						<c:if test="${tarefas.frequencia != 'NENHUMA'}">${tarefas.valorFrequencia}</c:if>
						${tarefas.frequencia.name}
					</td>
					<td>
						<c:if test="${tarefas.finalizado == 'SIM'}">
							${tarefas.finalizado.name}
						</c:if>
						<c:if test="${tarefas.finalizado == 'NAO'}">
							<a href="<c:url value='/tarefa/finalizar?tarefa.id=${tarefas.id}'/>">${tarefas.finalizado.name}</a>
						</c:if>
					</td>
					<td><a href="<c:url value='/tarefa/edita?tarefa.id=${tarefas.id}'/>">Alterar</a></td>
					<td><a href="<c:url value='/tarefa/remove?tarefa.id=${tarefas.id}'/>">Remover</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>