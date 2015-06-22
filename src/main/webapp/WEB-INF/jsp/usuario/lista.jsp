<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Usuários Cadastrados</h1>
	
	<form action="${linkTo[UsuarioController].lista(null)}" method="post">
		
		<label>Filtrar por</label>
		<select name="filtro" id="filtro" class="form-control">
			<option value="">Selecione...</option>
			<c:forEach items="${filtroUsuario}" var="filtro">
				<option value="${filtro}">${filtro.name}</option>
			</c:forEach>
		</select>
		
		<input type="text" name="parametro" id="parametro" class="form-control" value="${parametro}">
		
		<input type="submit" value="Pesquisar" class="btn">
		
	</form>
	
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

<c:import url="/WEB-INF/jsp/footer.jsp"/>