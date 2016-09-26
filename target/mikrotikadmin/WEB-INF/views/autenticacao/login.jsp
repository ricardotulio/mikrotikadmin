<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminLTE 2 | Log in</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/assets/plugins/bootstrap/css/bootstrap.min.css" />">
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" type="text/css"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/assets/css/AdminLTE.min.css" />">
<!-- iCheck -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/assets/plugins/iCheck/flat/blue.css" />">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>Admin</b>LTE</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Faça o login para iniciar sua sessão</p>
			<form method="post">
				<div
					class="form-group<c:if test="${not empty error}"> has-error</c:if> has-feedback">
					<input type="text" id="login" name="login" class="form-control"
						placeholder="Login"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div
					class="form-group<c:if test="${not empty error}"> has-error</c:if> has-feedback">
					<input type="password" id="senha" name="senha" class="form-control"
						placeholder="Senha"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-12 text-center">
						<c:if test="${not empty error}">
							<p class="text-center" style="color: red; font-weight: bolder">${error}</p>
						</c:if>
					</div>
				</div>
				<div class="row">
					<!-- /.col -->
					<div class="col-xs-4 pull-right">
						<button type="submit" class="btn btn-primary btn-block btn-flat">Entrar</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- jQuery 2.1.4 -->
	<script
		src="<c:url value="/assets/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
	<!-- /.login-box -->
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<c:url value="/assets/plugins/bootstrap/js/bootstrap.min.js" />"></script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<c:url value="/assets/plugins/bootstrap/js/bootstrap.min.js" />"></script>
</body>
</html>