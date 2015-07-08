<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<div class="page-header">
		<h1>Usuários Cadastrados</h1>
	</div>
	
	
	<div class="panel panel-primary">
  		<div class="panel-heading">
    		<h3 class="panel-title">Pesquisar por</h3>
  		</div>
  		
  		<div class="panel-body">
			<form action="${linkTo[UsuarioController].lista(null)}" method="post" class="form-horizontal">
				
				<div class="form-group">
					
					<div class="col-md-5">
						<select name="filtro" id="filtro" class="form-control">
							<option value="">Selecione...</option>
							<c:forEach items="${filtroUsuario}" var="f">
								<option value="${f}" ${filtro eq f ? 'selected' : ''}>${f.name}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-5">
						<input type="text" name="parametro" id="parametro" class="form-control" value="${parametro}">
					</div>
					
					<div class="col-md-2">
						<button class="btn btn-primary btn-block" type="submit">Pesquisar</button>
					</div>
					
				</div>
				
			</form>
		</div>
	</div>
	
	<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Tipo de Acesso</th>
					<th>Acessar Tarefas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ltUsuarios}" var="usuario">
					<tr>
						<td>${usuario.id}</td>
						<td>${usuario.nome}</td>
						<td>${usuario.email}</td>
						<td>${usuario.tipoUsuario}</td>
						<td><a href="<c:url value='/tarefa/listarPorUsuario?id=${usuario.id}'/>">Acessar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>