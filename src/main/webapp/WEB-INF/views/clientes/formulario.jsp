<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="box-body">
	<div class="form-group">
		<label for="nome">Nome: </label> <input type="text"
			class="form-control" id="nome" name="nome"
			placeholder="Digite o nome" value="${cliente.nome}" 
			required="required" 
			data-parsley-minlength="3"
			data-parsley-maxlength="120" />
	</div>
	<div class="form-group">
		<label for="cpf">CPF: </label> <input type="text" class="form-control"
			id="rg" name="cpf" placeholder="Digite o CPF" value="${cliente.cpf}" 
			required="required" data-parsley-cpf
			<c:if test="${not empty cliente}">readonly</c:if> />
	</div>
	<div class="form-group">
		<label for="rg">RG: </label> <input type="text" class="form-control"
			id="rg" name="rg" placeholder="Digite o rg" value="${cliente.rg}" required="required"
			<c:if test="${not empty cliente}">readonly</c:if> />
	</div>
	<div class="form-group">
		<label for="planoId">Plano: </label> <select id="planoId"
			name="planoId" class="form-control" required="required">
			<option value="">Selecione um plano</option>
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
			name="dataContrato" placeholder="Digite a Data do Contrato" required="required"
			value="<fmt:formatDate value="${cliente.dataContrato.time}" pattern="dd/MM/yyyy" />" 
			<c:if test="${not empty cliente}">readonly="readonly"</c:if> />
	</div>
	<div class="form-group">
		<label for="diaParaPagamentos">Dia do Mês para Pagamentos: </label> <input
			type="number" class="form-control" id="diaParaPagamentos"
			name="diaParaPagamentos"
			placeholder="Digite a Dia do Mẽs para Pagamentos" required="required"
			data-parsley-range="[1, 31]"
			data-parsley-range-message="Informe um dia do mês válido."
			value="${cliente.diaParaPagamentos}" />
	</div>
	<div class="form-group">
		<label for="login">Login: </label> <input type="text"
			class="form-control" id="login" name="login"
			placeholder="Digite o login" value="${cliente.login}" required="required"
			data-parsley-minlength="6" data-parsley-maxlength="20"
			<c:if test="${not empty cliente}">readonly</c:if> />
	</div>
	<div class="form-group">
		<label for="senha">Senha: </label> <input type="password"
			class="form-control" id="senha" name="senha"
			placeholder="Digite a senha" required="required" 
			data-parsley-minlength="6" 
			data-parsley-maxlength="20"
			<c:if test="${not empty cliente}">value="00000000"</c:if> />
	</div>
	<div class="form-group">
		<label for="confirmarSenha">Digite a senha novamente: </label> <input type="password"
			class="form-control" id="confirmarSenha" name="confirmarSenha"
			placeholder="Digite a senha novamente" required="required"
			data-parsley-equalto="#senha"
			data-parsley-equalto-message="Senha e confirmação de senha não conferem."
			<c:if test="${not empty cliente}">value="00000000"</c:if> />
	</div>
</div>
<div class="box-footer">
	<button type="submit" id="btn-salvar"
		class="btn btn-primary pull-left">
		<i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar
	</button>
	<a href="<c:url value="/clientes/" />"
		class="btn btn-danger pull-right"> <i class="fa fa-ban"
		aria-hidden="true"></i> Cancelar
	</a>
</div>