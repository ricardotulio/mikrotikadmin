package br.com.ricardotulio.mikrotikadmin.dao;

import br.com.ricardotulio.mikrotikadmin.model.Usuario;

public interface UsuarioDao {

	public Usuario obtemUsuarioPorLogin(String login);
	
}
