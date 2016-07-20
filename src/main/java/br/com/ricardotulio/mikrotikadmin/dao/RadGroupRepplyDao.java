package br.com.ricardotulio.mikrotikadmin.dao;

import br.com.ricardotulio.mikrotikadmin.model.RadGroupRepply;

public interface RadGroupRepplyDao {
	
	public RadGroupRepply get(Long id);

	public void persist(RadGroupRepply radCheck);
	
	public void remove(RadGroupRepply radCheck);
}
