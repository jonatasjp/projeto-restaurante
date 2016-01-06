<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="${request.contextPath}/menu" />

<div class="container" >
<div class="box box-warning">
	<div class="box-header">
		<h3 class="box-title">Cadastro de Mesa</h3>
	</div>
	<div class="box-body">
	
		<c:url var="url" value="/mesa/salvar" />
		
		<form:form action="${url}" method="post" modelAttribute="mesa">
		
		    <form:hidden cssStyle="width:250px"  path="id" size="30"  />
			<div class="row">
				<div class="form-group col-md-1">
					<label for="nome">De Reserva:</label>
					<form:select items="${selectDeReserva}"  length="30" path="deReserva" />
				</div>
			</div>	
			<div class="row">
				<div class="form-group col-md-1">
					<label for="nome">Número:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="numero" size="30" class="form-control" />
					<form:errors path="numero" />
				</div>
			</div>	
			<div class="row">	
				<div class="form-group col-md-2">
					<label for="nome">Capacidade:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="capacidade" size="30" class="form-control"/>
					<form:errors path="capacidade" />
				</div>
			</div>	
		
		   <div class="row">
				<div class="form-group col-md-4">
					<label for="nome"></label>
					<input type="submit" value="enviar" class="btn btn-primary">
				</div>
		   </div>	
		   
			
		</form:form>

	</div>
</div>
</div>
