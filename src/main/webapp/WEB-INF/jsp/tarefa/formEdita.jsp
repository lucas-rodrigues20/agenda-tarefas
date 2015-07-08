<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<div class="container col-md-2"></div>
		
		<div class="container col-md-8">
	
			<div class="page-header">
				<h1>Editar Tarefa</h1>
			</div>
	
			<form action="${linkTo[TarefaController].salvaEdicao(null)}" method="post">
				<alura:ValidationMessage name="data_invalida"/>
				
				<div class="form-group">
					<input type="hidden" name="tarefa.id" value="${tarefa.id}">
				</div>
				
				<div class="form-group">
					<label for="descricao">Descrição</label>
					<input type="text" name="tarefa.descricao" id="descricao" class="form-control"
						value="${tarefa.descricao}" required placeholder="Descrição da tarefa">
					<alura:ValidationMessage name="tarefa.descricao"/>
				</div>
				
				<div class="form-group">
					<label for="data">Data</label>
					<input type="text" name="tarefa.data" id="data" class="form-control"
							value="<fmt:formatDate pattern='dd/MM/yyyy' value='${tarefa.data.time}' />"
							required placeholder="DD/MM/YYYY">
					<alura:ValidationMessage name="tarefa.data"/>
				</div>
				
				<div class="form-group">
					<label for="horario">Horário</label>
					<input type="text" name="tarefa.horario" id="horario" class="form-control"
						value="${tarefa.horario}" required placeholder="HH:MM">
					<alura:ValidationMessage name="tarefa.horario"/>
				</div>
				
				<div class="form-group form-horizontal">
					<div class="form-group col-md-6">
						<label for="frequencia">Frequência</label>
						<select name="tarefa.frequencia" id="frequencia" class="form-control" required>
							<c:forEach items="${ltFrequencia}" var="freq">
								<option value="${freq}" ${tarefa.frequencia eq freq ? 'selected' : ''}>${freq.name}</option>
							</c:forEach>
						</select>
					</div>
				
					<div class="form-group col-md-6 pull-right">
						<label for="valorFrequencia">Repetir a cada</label>
						<input type="text" name="tarefa.valorFrequencia" id="valorFrequencia"
							class="form-control" value="${tarefa.valorFrequencia}"
							required placeholder="Repetir a cada X horas, dias...">
						<alura:ValidationMessage name="tarefa.valorFrequencia"/>
					</div>
				</div>
				
				<button class="btn btn-primary btn-block" type="submit">Salvar</button>
				
			</form>
	
		</div>
	
	<div class="container col-md-2"></div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>