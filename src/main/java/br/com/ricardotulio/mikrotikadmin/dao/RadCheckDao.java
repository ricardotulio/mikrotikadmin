package br.com.ricardotulio.mikrotikadmin.dao;

import br.com.ricardotulio.mikrotikadmin.model.RadCheck;

public interface RadCheckDao {
	
	public RadCheck get(Long id);

	public void persist(RadCheck radCheck);
	
	public void remove(RadCheck radCheck);
	
}
