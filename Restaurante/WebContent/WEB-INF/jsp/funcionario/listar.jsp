<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="${request.contextPath}/menu" />

<div class="container">
	<c:url var="url" value="/funcionario/filtrar" />
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
	
	
	<c:url var="url" value="/funcionario/novo" />
	<a href="${url}" class="btn btn-primary">Novo</a> <br /> <br />
	<table class="table table-bordered">
		<tr>
			<td>id</td>
			<td>Nome</td>
			<td>Cargo</td>
			<td>CPF</td>
			<td>Salário</td>
			<td>Ações</td>
		</tr>

		<c:forEach items="${funcionarios}" var="fun">
			<tr>
				<td>${fun.id}</td>
				<td>${fun.nome}</td>
				<td>${fun.cargo}</td>
				<td>${fun.cpf}</td>
				<td>${fun.salario}</td>
				<c:url var="url" value="/funcionario/${fun.id}" />
				<td><a href="${url}/editar" class="btn btn-primary">Editar</a>
					<a href="${url}/remover" class="btn btn-danger">Deletar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
