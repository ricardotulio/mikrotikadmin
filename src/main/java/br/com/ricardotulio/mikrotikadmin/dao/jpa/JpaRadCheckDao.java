package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.RadCheckDao;
import br.com.ricardotulio.mikrotikadmin.model.RadCheck;

@Repository("radCheckDao")
public class JpaRadCheckDao implements RadCheckDao {

	@PersistenceContext
	private EntityManager entityManager;

	public RadCheck get(Long id) {
		return this.entityManager.find(RadCheck.class, id);
	}

	public void persist(RadCheck radCheck) {
		this.entityManager.persist(radCheck);
	}

	public void remove(RadCheck radCheck) {
		this.entityManager.remove(radCheck);
	}

}
