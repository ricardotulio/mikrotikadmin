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
	<c:import url="/WEB-INF/views/clientes/formulario.jsp" />
</section>
<c:import url="/WEB-INF/views/template/footer.jsp" />