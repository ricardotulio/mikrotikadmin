package br.com.ricardotulio.mikrotikadmin.model;

public enum StatusFatura {

	GERADA(1), ENVIADA(2), CANCELADA(3), PAGA(4);

	private int status;

	StatusFatura(int status) {
		this.status = status;
	}

	public int getValor() {
		return this.status;
	}
}
