package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.model.Plano;
import br.com.ricardotulio.mikrotikadmin.model.RadGroupRepply;

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
		if (plano.getId() == null) {
			this.entityManager.persist(plano);

			RadGroupRepply radGroupReply = new RadGroupRepply();
			radGroupReply = new RadGroupRepply();
			radGroupReply.setId(plano.getId());
			radGroupReply.setGroupname(plano.getTitulo().replaceAll("\\s", "").toLowerCase());
			radGroupReply.setValue(Integer.toString((int) (plano.getTaxaUpload() * 1024)) + "k/"
					+ Integer.toString((int) (plano.getTaxaDownload() * 1024)) + "k");

			this.entityManager.persist(radGroupReply);
		} else {
			this.entityManager.merge(plano);

			RadGroupRepply radGroupReply = this.entityManager.createQuery(
					"SELECT rg FROM br.com.ricardotulio.mikrotikadmin.model.RadGroupRepply rg WHERE rg.id = ?",
					RadGroupRepply.class).setParameter(1, plano.getId()).getSingleResult();

			radGroupReply.setValue(Integer.toString((int) (plano.getTaxaUpload() * 1024)) + "k/"
					+ Integer.toString((int) (plano.getTaxaDownload() * 1024)) + "k");

			this.entityManager.merge(radGroupReply);
		}

		this.entityManager.flush();
	}

	public void remove(Plano plano) {
		this.entityManager.remove(plano);
	}

	public List<Plano> obtemListaDePlanosAtivos() {
		return this.entityManager.createQuery("SELECT p FROM Plano p WHERE p.ativo = true", Plano.class)
				.getResultList();
	}

	public Plano obtemPlanoPorTitulo(String titulo) {
		Query query = this.entityManager.createQuery("SELECT p FROM Plano p WHERE p.titulo LIKE ?", Plano.class);
		query.setParameter(1, titulo);
		List<Plano> resultado = query.getResultList();

		if (resultado.size() > 0)
			return resultado.get(0);
		return null;
	}

	public boolean planoPossuiClientes(Plano plano) {
		// TODO Auto-generated method stub
		return false;
	}
}
