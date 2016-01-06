<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="${request.contextPath}/menu" />

<div class="container" >
<div class="box box-warning">
	<div class="box-header">
		<h3 class="box-title">Cadastro de Reserva</h3>
	</div>
	<div class="box-body">
	
		<c:url var="url" value="/reserva/salvar" />
		
		<form:form action="${url}" method="get" modelAttribute="reserva">
		
		    <form:hidden cssStyle="width:250px"  path="id" size="30"  />
			<div class="row">
				<div class="form-group col-md-1">
					<label for="nome">Mesa:</label>
					<form:select items="${selectMesas}"  length="30" path="mesa.id" />
				</div>
			</div>	
			<div class="row">
				<div class="form-group col-md-1">
					<label for="nome">Nome do Responsável:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="nome_responsavel" size="30" class="form-control" />
					<form:errors path="nome_responsavel" />
				</div>
			</div>	
			<div class="row">	
				<div class="form-group col-md-2">
					<label for="nome">Número de Pessoas:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="num_Pessoas" size="30" class="form-control"/>
					<form:errors path="num_Pessoas" />
				</div>
			</div>
			
			<div class="row">	
				<div class="form-group col-md-2">
					<label for="nome">Data Inicial:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="dataInicial" size="30" class="form-control"/>
				</div>
			</div>
			
			<div class="row">	
				<div class="form-group col-md-2">
					<label for="nome">Data Final:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="dataFinal" size="30" class="form-control"/>
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
