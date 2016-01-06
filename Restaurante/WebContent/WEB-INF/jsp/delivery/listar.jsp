<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="${request.contextPath}/menu" />

<div class="container">
	<%-- <c:url var="url" value="/delivery/filtrar" />
	<form:form action="${url}" method="get" modelAttribute="filtro">
		<div class="row">
			<div class="col-md-1">Número do Pedido:</div>
			<div class="col-md-2">
				<form:input maxlength="30" path="id" size="30"
					class="form-control" />
			</div>
			<div class="col-md-3">Status do Pedido:</div>
			<div class="col-md-4">
					<form:select items="${selectStatus}"  length="30" path="status" />
				</div>
			<div class="col-md-5">
				<input type="submit" value="Pesquisar" class="btn btn-primary">
			</div>
		</div>

	</form:form>--%>
	
	
	<c:url var="url" value="/delivery/novoDelivery" />
	<a href="${url}" class="btn btn-primary">Novo</a> <br /> <br />
	<table class="table table-bordered">
		<tr>
			<td>Número</td>
			<td>Data</td>
			<td>Total</td>
			<td>Status</td>
			<td>Tipo</td>
			<td>Ações</td>
			
		</tr>

		<c:forEach items="${pedidos}" var="pedido">
			<tr>
				<td>${pedido.id}</td>
				<td>${pedido.data}</td>
				<td>${pedido.getTotal()}</td>
				<td>${pedido.status}</td>
				<td>Delivery</td>
				
				<c:url var="url" value="/delivery/${pedido.id}" />
				<td><a href="${url}/detalharDelivery" class="btn btn-primary">Detalhar</a>
					</td>
			</tr>
		</c:forEach>
	</table>
</div>
