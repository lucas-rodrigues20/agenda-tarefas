<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>

	<div class="well well-lg">
		<div class="row">

			<div class="col-md-6">
				<div class="page-header">
					<h1>Vantagens</h1>
				</div>

				<h3>Simplicidade</h3>
				<p>Interface extremamente fácil de se usar, muito simples e intuitiva, indo diretamente ao ponto.</p>
	
				<h3>Flexibilidade</h3>
				<p>Cadastre tarefas unicas ou tarefas que se repetem em intervalos totalmente personalizados.</p>
	
				<h3>Garantia</h3>
				<p>Ainda assim esqueceu sua tarefa? Continue sendo lembrado até finaliza-la de verdade.</p>

			</div><!-- COL-MD-6 -->

			<div class="col-md-6">
				<div class="page-header">
					<h1>Cadastre-se</h1>
				</div>
	
				<form action="${linkTo[UsuarioController].adiciona(null)}" method="post">
					
					<div class="form-group">
						<label for="nome">Nome</label>
						<input type="text" name="usuario.nome" id="nome" class="form-control" value="${usuario.nome}">
						<alura:ValidationMessage name="usuario.nome"/>
					</div>
					
					<div class="form-group">
						<label for="email">Email</label>
						<input type="text" name="usuario.email" id="email" class="form-control" value="${usuario.email}">
						<alura:ValidationMessage name="usuario.email"/>
					</div>
					
					<div class="form-group">
						<label for="senha">Senha</label>
						<input type="password" name="usuario.senha" id="senha" class="form-control">
						<alura:ValidationMessage name="usuario.senha"/>
					</div>
					
					<button class="btn btn-primary btn-block" type="submit">Cadastrar</button>
					
				</form>
				
			</div><!-- COL-MD-6 -->

		</div><!-- ROW -->
	</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>