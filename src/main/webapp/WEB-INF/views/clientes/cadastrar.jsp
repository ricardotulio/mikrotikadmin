<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/template/header.jsp" />
<link rel="stylesheet"
	href="<c:url value="/assets/css/pages/planos_cadastrar.css" />">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Clientes <small>Cadastrar Cliente</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li>Clientes</li>
		<li class="active">Cadastrar</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div
			class="col-xs-12 col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Informe os dados do cliente</h3>
				</div>
				<form id="form-cadastrar-cliente" role="form" method="POST">
					<c:import url="/WEB-INF/views/clientes/formulario.jsp" />
				</form>
			</div>
		</div>
	</div>
</section>
<c:import url="/WEB-INF/views/template/footer.jsp" />