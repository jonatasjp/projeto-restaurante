<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="${request.contextPath}/menu" />

<div class="container">
	<div class="box box-warning">
		<div class="box-header">
			<h3 class="box-title">Efetuar Pedido</h3>
		</div>
		<div class="box-body">

			<c:url var="url" value="/pedido/adicionarItem" />

			<form:form action="${url}" method="get" modelAttribute="item">

				<form:hidden cssStyle="width:250px" maxlength="30" path="id"
					size="30" class="form-control" />
				
				
				<div class="row">
					<div class="form-group col-md-2">
						<label for="nome">Status:</label>
						<form:select items="${selectStatus}" length="30"
							path="tradicional.status" />
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-2">
						<label for="nome">Mesa:</label>
						<form:select items="${selectMesas}" length="30"
							path="tradicional.mesa.id" />
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-3">
						<label for="nome">Cardapio:</label>
						<form:select items="${selectCardapio}" length="30" path="cardapio.id" />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-4">
						<label for="nome">Quantidade:</label>
						<form:input cssStyle="width:250px" maxlength="30"
							path="quantidade" size="30" class="form-control" />
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-4">
						<label for="nome"></label> 
						<input type="submit" value="Adicionar Item" class="btn btn-primary">
					</div>
				</div>


			</form:form>

	<table class="table table-bordered">
		<tr>
			
			<td>Status</td>
			<td>Produto</td>
			<td>Total</td>
			<td>Quantidade</td>
			<td>Ações</td>
		</tr>

		<c:forEach items="${carrinho}" var="carrin">
		
		<c:set var="totalItem" value="${carrin.quantidade * carrin.cardapio.preco}" />
		<c:set var="total" value="${total + totalItem}" />
		
			<tr>
				<td>${carrin.pedido.status}</td>
				<td>${carrin.cardapio.nome}</td>
				<td>${carrin.cardapio.preco * carrin.quantidade}</td>
				<td>${carrin.quantidade}</td>
				<c:url var="url" value="/pedido/${id}" />
				<td><a href="${url}/removerItem" class="btn btn-danger">deletar</a></td>
			</tr>
		</c:forEach>
		
		Valor parcial: R$<p>${total}</p>
		
	</table>
	
	<c:url var="url" value="/pedido/finalizarPedido" />

			<form:form action="${url}" method="get">

				
				<div class="row">
					<div class="form-group col-md-1">
						<label for="nome"></label> 
						<input type="submit" value="Finalizar Pedido" class="btn btn-primary">
					</div>
				</div>


			</form:form>

	

		</div>
	</div>
</div>

