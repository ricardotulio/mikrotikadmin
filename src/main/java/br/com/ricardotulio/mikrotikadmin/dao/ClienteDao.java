package br.com.ricardotulio.mikrotikadmin.dao;

import java.util.List;

import br.com.ricardotulio.mikrotikadmin.model.Cliente;

public interface ClienteDao {

	public Cliente obtem(Long id);

	public List<Cliente> obtemLista();

	public void persiste(Cliente cliente);

	public void remove(Cliente cliente);

	public List<Cliente> obtemListaAtivosComVencimentoEm(Integer dia);

}
