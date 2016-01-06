<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="${request.contextPath}/menu" />

<div class="container" >
<div class="box box-warning">
	<div class="box-header">
		<h3 class="box-title">Detalhamento de Pedido (Delivery)</h3>
	</div>
	<div class="box-body">
		
		<form:form action="${url}" method="post" modelAttribute="delivery">
		
			Cliente: <c:out value="${delivery.cliente.nome}" />
			<br>
			Número do Pedido: <c:out value="${delivery.id}" />
			<br> 
			Status: <c:out value="${delivery.status}" />
			<br> 
			Tipo: <c:out value="Delivery" />
			<br> 
			Troco para: <c:out value="${delivery.trocoPara}" />
			<br>
		</form:form>
	
	<table class="table table-bordered">
		<tr>
			<td>Código</td>
			<td>Descrição</td>
			<td>Quantidade</td>
			<td>Total</td>
		</tr>

		<c:forEach items="${itensDoPedido}" var="itemPedido">
			
			<c:set var="totalItem" value="${itemPedido.quantidade * itemPedido.preco}" />
			<c:set var="total" value="${total + totalItem}" />
			
			<tr>
				<td>${itemPedido.cardapio.id}</td>
				<td>${itemPedido.cardapio.nome}</td>
				<td>${itemPedido.quantidade}</td>
				<td>${itemPedido.quantidade * itemPedido.preco}</td>
			</tr>
		</c:forEach>
		
		<p>Total Geral: R$ ${total}</p>
		
	</table>
	<a href="http://localhost:8080/Restaurante/delivery/listar" class="btn btn-primary">Voltar</a>
	</div>
</div>
</div>


