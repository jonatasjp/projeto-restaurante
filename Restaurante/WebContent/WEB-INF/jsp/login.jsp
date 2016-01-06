<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="<c:url value ='/css/bootstrap.min.css'/>" rel="stylesheet"
	type="text/css" />
<script src="<c:url value ='/js/bootstrap.min.js'/>"
	type="text/javascript"></script>

<body>
	<c:url var="url" value="/login" />
	<div class="container-fluid">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Login</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="<c:url value='/cliente/cadastro'/>">Cadastre-se</a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>
					<form:form action="${url}" method="post" modelAttribute="usuario"
						class="form-horizontal">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input cssStyle="width:250px" maxlength="30" path="login"
								size="30" class="form-control" />
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
							<form:input cssStyle="width:250px" maxlength="30" path="senha"
								type="password" size="30" class="form-control" />
						</div>

						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-12 controls">
								<input type="submit" class="btn btn-primary" value="Login" />
							</div>
						</div>


					</form:form>



				</div>
			</div>
		</div>
	</div>
</body>