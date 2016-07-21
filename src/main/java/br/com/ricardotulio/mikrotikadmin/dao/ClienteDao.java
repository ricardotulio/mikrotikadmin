package br.com.ricardotulio.mikrotikadmin.dao;

import java.util.List;

import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

public interface ClienteDao {

	public Cliente obtem(Long id);

	public List<Cliente> obtemLista();

	public void persiste(Cliente cliente);

	public void remove(Cliente cliente);

	public List<Cliente> obtemListaAtivosComVencimentoNoDia(Integer dia);

	public Cliente obtemClientePorLogin(String login);

	public Cliente obtemClientePorCpf(String cpf);
	
	public List<Cliente> obtemClientesPorPlano(Plano plano);

}
