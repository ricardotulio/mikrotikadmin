package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.FaturaDao;
import br.com.ricardotulio.mikrotikadmin.model.Fatura;

@Repository("faturaDao")
public class JpaFaturaDao implements FaturaDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Fatura obtem(Long id) {
		return this.entityManager.find(Fatura.class, id);
	}

	public List<Fatura> obtemLista() {
		return this.entityManager.createQuery("SELECT f FROM Fatura f", Fatura.class).getResultList();
	}

	public void persiste(Fatura fatura) {
		this.entityManager.persist(fatura);
	}

	public void remove(Fatura fatura) {
		this.entityManager.remove(fatura);
	}

}
