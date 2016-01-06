<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <c:import url="${request.contextPath}/menu" />

	<div class="container" >
		
		<c:url var="url" value="/mesa/filtrar" />
		<form:form action="${url}" method="get" modelAttribute="filtro">
			<div class="row">
				<div class="col-md-1">De Reserva:</div>
				<div class="col-md-2">
					<form:select items="${selectDeReserva}"  length="30" path="deReserva" />
				</div>
				<div class="col-md-3">
					<input type="submit" value="Pesquisar" class="btn btn-primary">
				</div>
			</div>
		</form:form>
		
		<c:url var="url" value="/mesa/novo" />
		</br>
		</br>
		<a href="${url}" class="btn btn-primary">Nova Mesa</a> <br /> <br />
		<table class="table table-bordered">
			<tr>
				<td>Código</td>
				<td>Número</td>
				<td>Capacidade</td>
				<td>De Reserva</td>
				<td>Ações</td>
			</tr>

			<c:forEach items="${mesas}" var="mesa">
				<tr>
					<td>${mesa.id}</td>
					<td>${mesa.numero}</td>
					<td>${mesa.capacidade} Pessoas</td>
					<c:if test="${mesa.deReserva == false}">
 						<td>Não</td>
					</c:if>
					<c:if test="${mesa.deReserva == true}">
 						<td>Sim</td>
					</c:if>
					
					<c:url var="url" value="/mesa/${mesa.id}" />
					<td><a href="${url}/updateForm" class="btn btn-primary">Atualizar</a>
						<a href="${url}/remove" class="btn btn-danger">Deletar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>