package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.model.Cliente;

@Repository("clienteDao")
public class JpaClienteDao implements ClienteDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Cliente obtem(Long id) {
		return this.entityManager.find(Cliente.class, id);
	}

	public List<Cliente> obtemLista() {
		return this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
	}

	public void persiste(Cliente cliente) {
		if (cliente.getId() != null) {
			this.entityManager.merge(cliente);
		} else {
			this.entityManager.persist(cliente);
		}
	}

	public void remove(Cliente cliente) {
		this.entityManager.remove(cliente);
	}

	public List<Cliente> obtemListaAtivosComVencimentoEm(Integer dia) {
		// TODO: Refatorar essa query
		return this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.ativo = true AND diaParaPagamentos = " + dia,
						Cliente.class)
				.getResultList();
	}

}
