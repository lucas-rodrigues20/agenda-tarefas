<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<div class="page-header">
		<h1>Tarefas Cadastradas</h1>
	</div>

	<c:if test="${not empty mensagem}">
		<div class="alert alert-success" role="alert">
			<p>${mensagem}</p>
		</div>
	</c:if>
	
	<alura:ValidationMessage name="exclusao_invalida"/>
	<alura:ValidationMessage name="finalizacao_invalida"/>
	
	<p>Usuário: ${usuario.nome}</p>
	<p>Email: ${usuario.email}</p>
	
	<div><!-- AGRUPA ABAS -->

		<!-- CABECALHO DE NAVEGACAO -->
		<ul class="nav nav-tabs nav-justified" role="tablist">
		    <li role="presentation" class="active">
		        <a href="#nao-finalizadas" id="exibir-nao-finalizadas" role="tab" data-toggle="tab">Tarefas Não Finalizadas</a>
		    </li>
		    <li role="presentation">
		        <a href="#finalizadas" id="exibir-finalizadas" role="tab" data-toggle="tab">Tarefas Finalizadas</a>
		    </li>
		</ul>
		<!-- CABECALHO DE NAVEGACAO -->
		
		<!-- CONTEUDO DAS ABAS -->
		<div class="tab-content">
		
			<!-- TAREFAS NAO FINALIZADAS -->
		    <div role="tabpanel" class="tab-pane fade in active" id="nao-finalizadas">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Descrição</th>
								<th>Data</th>
								<th>Horário</th>
								<th>Frequência</th>
								<th>Finalizar</th>
								<th>Remover</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ltTarefasNaoFinalizadas}" var="tarefas">
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
									<td><a href="<c:url value='/tarefa/remove?tarefa.id=${tarefas.id}'/>">Remover</a>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div><!-- FIM DAS TAREFAS NAO FINALIZADAS -->
	
			<!-- TAREFAS FINALIZADAS -->
			<div role="tabpanel" class="tab-pane fade" id="finalizadas">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Descrição</th>
								<th>Data</th>
								<th>Horário</th>
								<th>Frequência</th>
								<th>Finalizar</th>
								<th>Remover</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ltTarefasFinalizadas}" var="tarefas">
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
									<td><a href="<c:url value='/tarefa/remove?tarefa.id=${tarefas.id}'/>">Remover</a>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div><!-- FIM DAS TAREFAS FINALIZADAS -->

		</div><!-- FIM CONTEUDO ABAS -->
	</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>