<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/views/template/header.jsp" />
<link rel="stylesheet"
	href="<c:url value="/assets/css/pages/planos_cadastrar.css" />">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Clientes <small>Editar Plano <fmt:formatNumber type="number"
				pattern="0000" value="${cliente.id}" /></small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li>Clientes</li>
		<li class="active">Editar</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
				<c:import url="/WEB-INF/views/clientes/formulario.jsp" />
				</form>
			</div>
		</div>
	</div>
</section>
<c:import url="/WEB-INF/views/template/footer.jsp" />