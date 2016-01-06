<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="${request.contextPath}/menu" />

<div class="container">

	<c:url var="url" value="/reserva/filtrar" />
	<form:form action="${url}" method="get" modelAttribute="filtro">
		<div class="row">

			<div class="col-md-1">Responsável:</div>
			<div class="col-md-2">
				<form:input maxlength="30" path="nome_responsavel" size="30"
					class="form-control" />
			</div>

			<div class="col-md-1">Mesa:</div>
			<div class="col-md-2">
				<form:select items="${selectMesas}" length="30" path="mesa.id" />
			</div>
			<div class="col-md-3">
				<input type="submit" value="Pesquisar" class="btn btn-primary">
			</div>
		</div>
	</form:form>


	<c:url var="url" value="/reserva/novo" />
	<a href="${url}" class="btn btn-primary">Nova Reserva</a> <br /> <br />
	<table class="table table-bordered">
		<tr>
			<td>Data Início</td>
			<td>Data Fim</td>
			<td>Nº da Mesa</td>
			<td>Nº de Pessoas</td>
			<td>Nome do Responsável</td>
			<td>Ações</td>
		</tr>

		<c:forEach items="${reservas}" var="reserva">
			<tr>
				<td>${reserva.dataInicial}</td>
				<td>${reserva.dataFinal}</td>
				<td>${reserva.mesa.numero}</td>
				<td>${reserva.num_Pessoas}Pessoas</td>
				<td>${reserva.nome_responsavel}</td>
				<c:url var="url" value="/reserva/${reserva.id}" />
				<td><a href="${url}/updateForm" class="btn btn-primary">Atualizar</a>
					<a href="${url}/remove" class="btn btn-danger">Deletar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
