<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form method="POST">
	<div class="row">
		<div class="col-xs-12">
			<a href="<c:url value="/clientes/" />"
				class="btn btn-danger pull-left"> <i class="fa fa-ban"
				aria-hidden="true"></i> Cancelar
			</a>
			<button type="submit" id="btn-salvar"
				class="btn btn-success pull-right">
				<i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar
			</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Dados Pessoais</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label for="nome">Nome: </label> <input type="text"
							class="form-control" id="nome" name="nome"
							placeholder="Digite o nome" value="${cliente.nome}"
							required="required" data-parsley-minlength="3"
							data-parsley-maxlength="120" />
					</div>
					<div class="form-group">
						<label for="cpf">CPF: </label> <input type="text"
							class="form-control" id="cpf" name="cpf"
							placeholder="Digite o CPF" value="${cliente.cpf}"
							required="required" data-parsley-cpf
							<c:if test="${not empty cliente}">readonly</c:if> />
					</div>
					<div class="form-group">
						<label for="rg">RG: </label> <input type="text"
							class="form-control" id="rg" name="rg" placeholder="Digite o rg"
							value="${cliente.rg}" required="required"
							<c:if test="${not empty cliente}">readonly</c:if> />
					</div>
				</div>
			</div>
			<div class="box box-warning">
				<div class="box-header with-border">
					<h3 class="box-title">Dados para Contato</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label for="telefone">Telefone: </label> <input type="text"
							class="form-control" id="telefone" name="telefone"
							placeholder="Digite o Número de Telefone" />
					</div>
					<div class="form-group">
						<label for="celular">Celular: </label> <input type="text"
							class="form-control" id="celular" name="celular"
							placeholder="Digite o Número de Celular" />
					</div>
					<div class="form-group">
						<label for="email">E-Mail: </label> <input type="text"
							class="form-control" id="email" name="email"
							placeholder="Digite o E-Mail" />
					</div>
				</div>
			</div>
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Dados Contratuais</h3>
				</div>
				<div class="box-body">
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
							name="dataContrato" placeholder="Digite a Data do Contrato"
							required="required"
							value="<fmt:formatDate value="${cliente.dataContrato.time}" pattern="dd/MM/yyyy" />"
							<c:if test="${not empty cliente}">readonly="readonly"</c:if> />
					</div>
					<div class="form-group">
						<label for="diaParaPagamentos">Dia do Mês para Pagamentos:
						</label> <input type="number" class="form-control" id="diaParaPagamentos"
							name="diaParaPagamentos"
							placeholder="Digite a Dia do Mẽs para Pagamentos"
							required="required" data-parsley-range="[1, 31]"
							data-parsley-range-message="Informe um dia do mês válido."
							value="${cliente.diaParaPagamentos}" />
					</div>
					<div class="form-group">
						<label for="login">Login: </label> <input type="text"
							class="form-control" id="login" name="login"
							placeholder="Digite o login" value="${cliente.login}"
							required="required" data-parsley-minlength="6"
							data-parsley-maxlength="20"
							<c:if test="${not empty cliente}">readonly</c:if> />
					</div>
					<div class="form-group">
						<label for="senha">Senha: </label> <input type="password"
							class="form-control" id="senha" name="senha"
							placeholder="Digite a senha" required="required"
							data-parsley-minlength="6" data-parsley-maxlength="20"
							<c:if test="${not empty cliente}">value="00000000"</c:if> />
					</div>
					<div class="form-group">
						<label for="confirmarSenha">Digite a senha novamente: </label> <input
							type="password" class="form-control" id="confirmarSenha"
							name="confirmarSenha" placeholder="Digite a senha novamente"
							required="required" data-parsley-equalto="#senha"
							data-parsley-equalto-message="Senha e confirmação de senha não conferem."
							<c:if test="${not empty cliente}">value="00000000"</c:if> />
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">Endereço</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label for="logradouro">Logradouro: </label> <input type="text"
							class="form-control" id="logradouro" name="logradouro"
							placeholder="Digite o logradouro"
							value="${cliente.endereco.logradouro}" required="required"
							data-parsley-minlength="5" data-parsley-maxlength="120" />
					</div>
					<div class="form-group">
						<label for="numero">Número: </label> <input type="text"
							class="form-control" id="numero" name="numero"
							placeholder="Digite o número" value="${cliente.endereco.numero}"
							required="required" />
					</div>
					<div class="form-group">
						<label for="complemento">Complemento: </label> <input type="text"
							class="form-control" id="complemento" name="complemento"
							placeholder="Complemento" value="${cliente.endereco.complemento}" />
					</div>
					<div class="form-group">
						<label for="bairro">Bairro: </label> <input type="text"
							class="form-control" id="bairro" name="bairro"
							placeholder="Digite o Bairro" value="${cliente.endereco.bairro}"
							required="required" />
					</div>
					<div class="form-group">
						<label for="cidade">Cidade: </label> <input type="text"
							class="form-control" id="cidade" name="cidade"
							placeholder="Digite a Cidade" required="required"
							value="${cliente.endereco.cidade}" />
					</div>
					<div class="form-group">
						<label for="uf">UF: </label> <input type="text"
							class="form-control" id="uf" name="uf" placeholder="Digite a UF"
							required="required" value="${cliente.endereco.uf}" />
					</div>
					<div class="form-group">
						<label for="cep">CEP: </label> <input type="text"
							class="form-control" id="cep" name="cep"
							placeholder="Digite o CEP" required="required"
							value="${cliente.endereco.cep}" />
					</div>
				</div>
			</div>
		</div>
	</div>
</form>