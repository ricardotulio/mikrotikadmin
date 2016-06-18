package br.com.ricardotulio.mikrotikadmin.dao;

import java.util.List;

import br.com.ricardotulio.mikrotikadmin.model.Fatura;

public interface FaturaDao {

	public Fatura obtem(Long id);
	
	public List<Fatura> obtemLista();
	
	public void persiste(Fatura fatura);
	
	public void remove(Fatura fatura);
	
}
