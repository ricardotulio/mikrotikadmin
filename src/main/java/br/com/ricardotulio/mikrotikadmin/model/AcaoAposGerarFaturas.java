package br.com.ricardotulio.mikrotikadmin.model;

import java.util.List;

public interface AcaoAposGerarFaturas {

	public void executa(List<Fatura> faturas);
	
}
