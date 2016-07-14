<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/views/template/header.jsp" />
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Clientes <small>Lista de Clientes</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li class="active">Clientes</li>
	</ol>

	<c:if test="${not empty success}">
		<div class="alert alert-success">${success}</div>
	</c:if>
	<c:if test="${not empty error}">
		<div class="alert alert-danger">${error}</div>
	</c:if>	
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<a href="<c:url value="/clientes/cadastrar" />"
						class="btn btn-primary pull-right"><i
						class="fa fa-plus-circle" aria-hidden="true"></i> Cadastrar</a>
				</div>
				<div class="box-body">
					<div id="example2_wrapper"
						class="dataTables_wrapper form-inline dt-bootstrap">
						<div class="row">
							<div class="col-sm-6"></div>
							<div class="col-sm-6"></div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="example2"
									class="table table-bordered table-hover dataTable" role="grid"
									aria-describedby="example2_info">
									<thead>
										<tr role="row">
											<th class="sorting_asc" tabindex="0" rowspan="1" colspan="1"
												aria-sort="ascending"><input type="checkbox"
												id="select-all-regiters"></th>
											<th class="sorting_asc" tabindex="0" rowspan="1" colspan="1"
												aria-sort="ascending">#ID</th>
											<th class="sorting" tabindex="0" rowspan="1" colspan="1">Nome</th>
											<th class="sorting" tabindex="0" rowspan="1" colspan="1">Login</th>
											<th class="sorting" tabindex="0" rowspan="1" colspan="1">Plano</th>
											<th class="sorting" tabindex="0" rowspan="1" colspan="1">Data Contrato</th>
											<th class="sorting" tabindex="0" rowspan="1" colspan="1">Dia Pagamento</th>
											<th class="sorting" tabindex="0" rowspan="1" colspan="1">Ativo</th>
											<th class="sorting" tabindex="0" rowspan="1" colspan="1">Ações</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${fn:length(clientes) gt 0}">
												<c:forEach var="cliente" items="${clientes}">
													<tr role="row" class="odd" data-id="${cliente.id}">
														<td><input type="checkbox" id="register-${cliente.id}"></td>
														<td><fmt:formatNumber type="number" pattern="0000"
																value="${cliente.id}" /></td>
														<td>${cliente.nome}</td>
														<td>${cliente.login}</td>
														<td>${cliente.plano.titulo}</td>
														<td><fmt:formatDate value="${cliente.dataContrato.time}" pattern="dd/MM/yyyy"/></td>
														<td>${cliente.diaParaPagamentos}</td>
														<td>${cliente.ativo}</td>
														<td><a
															href="<c:url value="/clientes/editar/" />${cliente.id}"
															title="Editar" class="btn-editar"><i class="fa fa-pencil"
																aria-hidden="true"></i></a> <a href="javascript:void(0)"
															id="btn-excluir-cliente" class="btn-excluir-cliente"
															data-id="${cliente.id}" data-nome="${cliente.nome}" title="Excluir"><i
																class="fa fa-trash-o" aria-hidden="true"></i></a></td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="9">
														<h3>Não há nenhum cliente cadastrado.</h3>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-5">
								<div class="dataTables_info" role="status" aria-live="polite">Exibindo
									10 de 57 registros</div>
							</div>
							<div class="col-sm-12 text-center">
								<div class="dataTables_paginate paging_simple_numbers"
									id="example2_paginate">
									<ul class="pagination">
										<li class="paginate_button previous disabled"
											id="example2_previous"><a href="#" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
										</a></li>
										<li class="paginate_button active"><a href="#"
											aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li>
										<li class="paginate_button "><a href="#"
											aria-controls="example2" data-dt-idx="2" tabindex="0">2</a></li>
										<li class="paginate_button "><a href="#"
											aria-controls="example2" data-dt-idx="3" tabindex="0">3</a></li>
										<li class="paginate_button "><a href="#"
											aria-controls="example2" data-dt-idx="4" tabindex="0">4</a></li>
										<li class="paginate_button "><a href="#"
											aria-controls="example2" data-dt-idx="5" tabindex="0">5</a></li>
										<li class="paginate_button "><a href="#"
											aria-controls="example2" data-dt-idx="6" tabindex="0">6</a></li>
										<li class="paginate_button next" id="example2_next"><a
											href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="/WEB-INF/views/template/footer.jsp" />