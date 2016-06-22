package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

@Repository("planoDao")
public class JpaPlanoDao implements PlanoDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Plano obtem(Long id) {
		return this.entityManager.find(Plano.class, id);
	}

	public List<Plano> obtemLista() {
		return this.entityManager.createQuery("SELECT p FROM Plano p", Plano.class).getResultList();
	}

	public void persiste(Plano plano) {
		if (plano.getId() != null) {
			this.entityManager.merge(plano);
		} else {
			this.entityManager.persist(plano);
		}
	}

	public void remove(Plano plano) {
		this.entityManager.remove(plano);
	}

	public List<Plano> obtemListaDePlanosAtivos() {
		return this.entityManager.createQuery("SELECT p FROM Plano p WHERE p.ativo = true", Plano.class)
				.getResultList();
	}

	public boolean planoPossuiClientes(Plano plano) {
		// TODO Auto-generated method stub
		return false;
	}
}
