<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>

	<div class="container col-md-2"></div>
	
	<div class="container col-md-8">

		<div class="page-header">
			<h1>Agendar Nova Tarefa</h1>
		</div>

		<form action="${linkTo[TarefaController].adiciona(null)}" method="post">
			<alura:ValidationMessage name="data_invalida"/>
			
			<div class="form-group">
				<label for="descricao">Descrição</label>
				<input type="text" name="tarefa.descricao" id="descricao" class="form-control">
				<alura:ValidationMessage name="tarefa.descricao"/>
			</div>
			
			<div class="form-group">
				<label for="data">Data</label>
				<input type="text" name="tarefa.data" id="data" class="form-control">
				<alura:ValidationMessage name="tarefa.data"/>
			</div>
			
			<div class="form-group">
				<label for="horario">Horário</label>
				<input type="text" name="tarefa.horario" id="horario" class="form-control">
				<alura:ValidationMessage name="tarefa.horario"/>
			</div>
			
			<div class="form-group form-horizontal">
				<div class="form-group col-md-6">
					<label for="frequencia">Frequência</label>
					<select name="tarefa.frequencia" id="frequencia" class="form-control">
						<c:forEach items="${ltFrequencia}" var="freq">
							<option value="${freq}">${freq.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group col-md-6 pull-right">
					<label for="valorFrequencia">Valor da Frequência</label>
					<input type="text" name="tarefa.valorFrequencia" id="valorFrequencia"
						class="form-control" value="${tarefa.valorFrequencia}">
					<alura:ValidationMessage name="tarefa.valorFrequencia"/>
				</div>
				
			</div>
			
			<button class="btn btn-primary btn-block" type="submit">Adicionar</button>
			
		</form>

	</div>
	
	<div class="container col-md-2"></div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>