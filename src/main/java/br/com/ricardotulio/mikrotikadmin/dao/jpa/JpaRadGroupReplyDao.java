package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.RadGroupReplyDao;
import br.com.ricardotulio.mikrotikadmin.model.RadGroupReply;

@Repository("radGroupReplyDao")
public class JpaRadGroupReplyDao implements RadGroupReplyDao {

	@PersistenceContext
	private EntityManager entityManager;

	public RadGroupReply get(Long id) {
		return this.entityManager.find(RadGroupReply.class, id);
	}
	
	public void persist(RadGroupReply radGroupReply) {
		this.entityManager.persist(radGroupReply);
	}

	public void remove(RadGroupReply radGroupReply) {
		this.entityManager.remove(radGroupReply);
	}
	
}
