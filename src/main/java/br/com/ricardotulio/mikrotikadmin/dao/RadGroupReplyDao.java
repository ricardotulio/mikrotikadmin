package br.com.ricardotulio.mikrotikadmin.dao;

import br.com.ricardotulio.mikrotikadmin.model.RadGroupReply;

public interface RadGroupReplyDao {
	
	public RadGroupReply get(Long id);

	public void persist(RadGroupReply radCheck);
	
	public void remove(RadGroupReply radCheck);
}
