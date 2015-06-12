<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Editar Tarefa</h1>
	
	<form action="${linkTo[TarefaController].salvaEdicao(null)}" method="post">
		<alura:ValidationMessage name="data_invalida"/>
		
		<input type="hidden" name="tarefa.id" value="${tarefa.id}">
		
		<label for="descricao">Descrição</label>
		<input type="text" name="tarefa.descricao" id="descricao" class="form-control" value="${tarefa.descricao}">
		<alura:ValidationMessage name="tarefa.descricao"/>
		
		<label for="data">Data</label>
		<input type="text" name="tarefa.data" id="data" class="form-control" value="${tarefa.data.time}">
		<alura:ValidationMessage name="tarefa.data"/>
		
		<label for="horario">Horário</label>
		<input type="text" name="tarefa.horario" id="horario" class="form-control" value="${tarefa.horario}">
		<alura:ValidationMessage name="tarefa.horario"/>
		
		<input type="submit" value="Salvar" class="btn">
		
	</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>