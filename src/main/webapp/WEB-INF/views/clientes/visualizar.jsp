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
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Informe os dados do cliente</h3>
				</div>
				<div class="form-group">
					<label for="nome">Nome: </label> <input type="text"
						class="form-control" id="nome" name="nome"
						placeholder="Digite o nome" value="${cliente.nome}" />
				</div>
				<div class="form-group">
					<label for="cpf">CPF: </label> <input type="text"
						class="form-control" id="rg" name="cpf" placeholder="Digite o CPF"
						value="${cliente.cpf}" />
				</div>
				<div class="form-group">
					<label for="rg">RG: </label> <input type="text"
						class="form-control" id="rg" name="rg" placeholder="Digite o rg"
						value="${cliente.rg}" disabled="true"/>
				</div>
				<div class="form-group">
					<label for="planoId">Plano: </label> <select id="planoId"
						name="planoId" class="form-control">
						<option disabled="true">Selecione um plano</option>
						<c:forEach var="plano" items="${planos}">
							<option
								<c:if test="${plano.id == cliente.plano.id}">selected="selected"</c:if>
								value="${plano.id}">${plano.titulo}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="dataContrato">Data do Contrato: </label> <input
						type="text" class="form-control" id="dataContrato"
						name="dataContrato" placeholder="Digite a Data do Contrato"
						value="<fmt:formatDate value="${cliente.dataContrato.time}" pattern="dd/MM/yyyy" />" disabled="true" />
				</div>
				<div class="form-group">
					<label for="diaParaPagamentos">Dia do Mês para Pagamentos:
					</label> <input type="number" class="form-control" id="diaParaPagamentos"
						name="diaParaPagamentos"
						placeholder="Digite a Dia do Mẽs para Pagamentos"
						value="${cliente.diaParaPagamentos}" disabled="true" />
				</div>
				<div class="form-group">
					<label for="login">Login: </label> <input type="text"
						class="form-control" id="login" name="login"
						placeholder="Digite o login" value="${cliente.login}" disabled="true" />
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6"></div>
	</div>
</section>
<c:import url="/WEB-INF/views/template/footer.jsp" />