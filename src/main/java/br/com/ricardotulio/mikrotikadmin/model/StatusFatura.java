package br.com.ricardotulio.mikrotikadmin.model;

public enum StatusFatura {

	GERADA(0), ENVIADA(1), CANCELADA(2), PAGA(3);

	private int status;

	StatusFatura(int status) {
		this.status = status;
	}

	public int getValor() {
		return this.status;
	}

}
