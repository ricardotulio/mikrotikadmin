package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ricardotulio.mikrotikadmin.dao.UsuarioDao;
import br.com.ricardotulio.mikrotikadmin.model.Usuario;

@Repository("usuarioDao")
public class JpaUsuarioDao implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Usuario obtemUsuarioPorLogin(String login) {
		List<Usuario> resultado = this.entityManager
				.createQuery("SELECT u FROM Usuario u WHERE u.login = ?", Usuario.class).setParameter(1, login)
				.getResultList();

		if (resultado.size() == 1)
			return resultado.get(0);
		return null;
	}

}
