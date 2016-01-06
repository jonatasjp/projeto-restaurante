<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="${request.contextPath}/menu" />

<div class="container">
	
	<c:url var="url" value="/categoria/filtrar" />
	<form:form action="${url}" method="get" modelAttribute="filtro">
		<div class="row">
			<div class="col-md-1">Categoria:</div>
			<div class="col-md-2">
				<form:input maxlength="30" path="nome" size="30"
					class="form-control" />
			</div>
			<div class="col-md-3">
				<input type="submit" value="Pesquisar" class="btn btn-primary">
			</div>
		</div>

	</form:form>
	
	
	<c:url var="url" value="/categoria/novo" />
	<a href="${url}" class="btn btn-primary">Novo</a> <br /> <br />
	<table class="table table-bordered">
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Status</td>
			<td>Ações</td>
		</tr>

		<c:forEach items="${categorias}" var="categoria">
			<tr>
				<td>${categoria.id}</td>
				<td>${categoria.nome}</td>
				<td>${categoria.status}</td>
				<c:url var="url" value="/categoria/${categoria.id}" />
				<td><a href="${url}/updateForm" class="btn btn-primary">atualizar</a>
					<a href="${url}/remove" class="btn btn-danger">deletar</a>
					<c:if test="${categoria.status == 'Não-Ativo'}">
  						<a href="${url}/ativar" class="btn btn-primary">Ativar</a>
					</c:if>
					<c:if test="${categoria.status == 'Ativo'}">
  						<a href="${url}/desativar" class="btn btn-primary">Desativar</a>
					</c:if>
			</tr>
		</c:forEach>
	</table>
</div>
