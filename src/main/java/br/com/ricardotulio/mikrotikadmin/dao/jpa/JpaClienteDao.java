package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

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
		
		this.entityManager.flush();
	}

	public void remove(Cliente cliente) {
		this.entityManager.remove(cliente);
	}

	public List<Cliente> obtemListaAtivosComVencimentoNoDia(Integer dia) {
		// TODO: Refatorar essa query
		return this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.ativo = true AND diaParaPagamentos = " + dia,
						Cliente.class)
				.getResultList();
	}

	public Cliente obtemClientePorLogin(String login) {
		Query query = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.login = ?", Cliente.class);
		query.setParameter(1, login);

		List<Cliente> resultado = query.getResultList();

		if (resultado.size() > 0)
			return resultado.get(0);
		return null;
	}

	public Cliente obtemClientePorCpf(String cpf) {
		Query query = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpf = ?", Cliente.class);
		query.setParameter(1, cpf);

		List<Cliente> resultado = query.getResultList();

		if (resultado.size() > 0)
			return resultado.get(0);
		return null;
	}
	
	public List<Cliente> obtemClientesPorPlano(Plano plano) {
		Query query = this.entityManager.createQuery("SELECT c FROM Cliente c JOIN c.plano p WHERE p.id = ?", Cliente.class);
		query.setParameter(1,  plano.getId());
				
		return query.getResultList();
	}
}
