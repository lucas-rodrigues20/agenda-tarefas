<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Agendar Nova Tarefa</h1>
	
	<form action="${linkTo[TarefaController].adiciona(null)}" method="post">
		<alura:ValidationMessage name="data_invalida"/>
		
		<label for="descricao">Descrição</label>
		<input type="text" name="tarefa.descricao" id="descricao" class="form-control">
		<alura:ValidationMessage name="tarefa.descricao"/>
		
		<label for="data">Data</label>
		<input type="text" name="tarefa.data" id="data" class="form-control">
		<alura:ValidationMessage name="tarefa.data"/>
		
		<label for="horario">Horário</label>
		<input type="text" name="tarefa.horario" id="horario" class="form-control">
		<alura:ValidationMessage name="tarefa.horario"/>
		
		<label for="frequencia">Frequência</label>
		<select name="tarefa.frequencia" id="frequencia" class="form-control">
			<c:forEach items="${ltFrequencia}" var="freq">
				<option value="${freq}">${freq.name}</option>
			</c:forEach>
		</select>
		
		<label for="valorFrequencia">Valor da Frequência</label>
		<input type="text" name="tarefa.valorFrequencia" id="valorFrequencia"
			class="form-control" value="${tarefa.valorFrequencia}">
		<alura:ValidationMessage name="tarefa.valorFrequencia"/>
		
		<input type="submit" value="Adicionar" class="btn">
		
	</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>