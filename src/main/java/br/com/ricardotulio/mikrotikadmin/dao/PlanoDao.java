package br.com.ricardotulio.mikrotikadmin.dao;

import java.util.List;

import br.com.ricardotulio.mikrotikadmin.model.Plano;

public interface PlanoDao {

	public Plano obtem(Long id);
	
	public List<Plano> obtemLista();
	
	public void persiste(Plano plano);
	
	public void remove(Plano plano);

	public List<Plano> obtemListaDePlanosAtivos();
}
