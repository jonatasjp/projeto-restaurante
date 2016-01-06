<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="${request.contextPath}/menu" />

<div class="container">
	<p>Bem vindo, ${usuario.nome}</p>
	<c:url var="url" value="/cliente/filtrar" />
	<form:form action="${url}" method="get" modelAttribute="filtro">
		<div class="row">
			<div class="col-md-1">Nome:</div>
			<div class="col-md-2">
				<form:input maxlength="30" path="nome" size="30"
					class="form-control" />
			</div>
			<div class="col-md-3">
				<input type="submit" value="Pesquisar" class="btn btn-primary">
			</div>
		</div>

	</form:form>
	
	
	<c:url var="url" value="/cliente/novo" />
	<a href="${url}" class="btn btn-primary">Novo</a> <br /> <br />
	<table class="table table-bordered">
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Rua</td>
			<td>Cep</td>
			<td>Bairro</td>
			<td>Ações</td>
		</tr>

		<c:forEach items="${clientes}" var="cli">
			<tr>
				<td>${cli.id}</td>
				<td>${cli.nome}</td>
				<td>${cli.endereco.rua}</td>
				<td>${cli.endereco.cep}</td>
				<td>${cli.endereco.bairro}</td>
				<c:url var="url" value="/cliente/${cli.id}" />
				<td><a href="${url}/updateForm" class="btn btn-primary">Atualizar</a>
					<a href="${url}/remove" class="btn btn-danger">Deletar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
