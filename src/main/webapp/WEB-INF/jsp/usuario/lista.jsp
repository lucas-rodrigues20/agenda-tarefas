<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<h1>Usuários Cadastrados</h1>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Email</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ltUsuarios}" var="usuario">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.nome}</td>
					<td>${usuario.email}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>