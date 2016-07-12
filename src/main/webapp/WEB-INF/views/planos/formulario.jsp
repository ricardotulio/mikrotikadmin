<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div
		class="col-xs-12 col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
		<div class="box box-primary">
			<div class="box-header with-border">
				<h3 class="box-title">Informe os dados do plano</h3>
			</div>
			<form id="form-cadastrar-plano" data-parsley-validate="" role="form"
				method="POST">
				<div class="box-body">
					<div class="form-group">
						<label for="titulo">Título: </label> <input type="text"
							class="form-control" id="titulo" name="titulo"
							placeholder="Digite o título" value="${plano.titulo}"
							required="required"
							<c:if test="${not empty plano}">readonly</c:if>
							<c:if test="${empty plano}">data-parsley-minlength="5" data-parsley-maxlength="30" data-parsley-remote="buscaPorTitulo/{value}" data-parsley-trigger="focusout" data-parsley-remote-reverse data-parsley-remote-validator="titulo-plano-duplicado" data-parsley-remote-options='{"type": "GET", "dataType": "json", "token": "value"}' data-parsley-remote-message="Já existe um plano com este título. Por favor, informe outro título."</c:if>>
					</div>
					<div class="form-group">
						<label for="descricao">Descrição: </label>
						<textarea class="form-control" id="descricao" name="descricao"
							placeholder="Digite a descrição" data-parsley-maxlength="120">${plano.descricao}</textarea>
					</div>
					<div class="form-group">
						<label for="descricao">Taxa de Download em MB: </label> <input
							type="number" step="any" class="form-control" id="taxaDownload"
							name="taxaDownload" placeholder="Digite a taxa de download"
							value="${plano.taxaDownload}" required="required"
							data-parsley-min="0.1">
					</div>
					<div class="form-group">
						<label for="descricao">Taxa de Upload em MB: </label> <input
							type="number" step="any" class="form-control" id="taxaUpload"
							name="taxaUpload" placeholder="Digite a taxa de Upload"
							value="${plano.taxaUpload}" required="required"
							data-parsley-min="0.1">
					</div>
					<div class="form-group">
						<label for="valor">Valor Mensal: </label> <input type="number"
							step="any" class="form-control" id="valorMensal"
							name="valorMensal" placeholder="Digite o valor"
							value="${plano.valorMensal}" required="required"
							data-parsley-min="1">
					</div>
				</div>
				<div class="box-footer">
					<button type="submit" id="btn-salvar"
						class="btn btn-primary pull-left">
						<i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar
					</button>
					<a href="<c:url value="/planos/" />"
						class="btn btn-danger pull-right"> <i class="fa fa-ban"
						aria-hidden="true"></i> Cancelar
					</a>
				</div>
			</form>
		</div>
	</div>
</div>