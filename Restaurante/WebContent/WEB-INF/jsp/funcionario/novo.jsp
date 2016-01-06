<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="${request.contextPath}/menu" />

<div class="container" >
<div class="box box-warning">
	<div class="box-header">
		<h3 class="box-title">Cadastro de funcionario</h3>
	</div>
	<div class="box-body">
	
		<c:url var="url" value="/funcionario/salvar" />
		
		<form:form action="${url}" method="post" modelAttribute="funcionario">
		
		 <form:hidden cssStyle="width:250px" maxlength="30" path="id" size="30"
		class="form-control" />

	<label for="login">Login:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="login"
		size="30" class="form-control" />

	<label for="senha">Senha:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="senha"
		size="30" class="form-control" />

	<label for="nome">Nome:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="nome" size="30"
		class="form-control" />

	<label for="telefone">Telefone:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="telefone"
		size="30" class="form-control" />

	<label for="email">E-mail:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="email"
		size="30" class="form-control" />

	<label for="email">CPF:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="cpf" size="30"
		class="form-control" />

	<label for="email">Cargo:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="cargo"
		size="30" class="form-control" />

	<label for="email">Salário:</label>
	<form:input cssStyle="width:250px" maxlength="30" path="salario"
		size="30" class="form-control" />	
		   
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
