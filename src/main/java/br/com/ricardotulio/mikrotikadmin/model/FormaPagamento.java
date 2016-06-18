package br.com.ricardotulio.mikrotikadmin.model;

public enum FormaPagamento {

	DINHEIRO(1), PAGSEGURO(2);
	
	private int formaPagamento;
	
	FormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public int getFormaPagamento() {
		return this.formaPagamento;
	}
}
