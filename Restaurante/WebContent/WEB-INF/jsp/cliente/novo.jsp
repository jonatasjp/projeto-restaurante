<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="${request.contextPath}/menu" />

<div class="container" >
<div class="box box-warning">
	<div class="box-header">
		<h3 class="box-title">Cadastro de cliente</h3>
	</div>
	<div class="box-body">
	
		<c:url var="url" value="/cliente/salvar" />
		
		<form:form action="${url}" method="post" modelAttribute="cliente">
		
		<form:hidden cssStyle="width:250px" maxlength="30" path="id" size="30" class="form-control" />
		
		    <div class="row">
				<div class="form-group col-md-1">
					<label for="login">Login:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="login" size="30" class="form-control" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-1">
					<label for="senha">Senha:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="senha" size="30" class="form-control" />
				</div>
			</div>
		    
			<div class="row">
				<div class="form-group col-md-1">
					<label for="nome">Nome:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="nome" size="30" class="form-control" />
					<form:errors path="nome" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-1">
					<label for="telefone">Telefone:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="telefone" size="30" class="form-control" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-1">
					<label for="email">E-mail:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="email" size="30" class="form-control" />
				</div>
			</div>
			<div class="row">	
				<div class="form-group col-md-2">
					<label for="rua">Rua:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="endereco.rua" size="30" class="form-control"/>
				</div>
			</div>
			<div class="row">	
				<div class="form-group col-md-2">
					<label for="rua">Número:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="endereco.numero" size="30" class="form-control"/>
				</div>
			</div>
				<div class="row">
				<div class="form-group col-md-3">
					<label for="cep">Cep:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="endereco.cep" size="30" class="form-control"/>
				</div>
			</div>	
			<div class="row">
				<div class="form-group col-md-4">
					<label for="bairro">Bairro:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="endereco.bairro" size="30" class="form-control"/>
				</div>
		   </div>
		   <div class="row">
				<div class="form-group col-md-4">
					<label for="complemento">Complemento:</label>
					<form:input cssStyle="width:250px" maxlength="30" path="endereco.complemento" size="30" class="form-control"/>
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
<%-- <c:import url="${request.contextPath}/rodape" />--%>
