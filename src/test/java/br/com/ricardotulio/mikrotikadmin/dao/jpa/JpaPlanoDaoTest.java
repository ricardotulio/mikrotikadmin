package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.dao.PlanoDataFactory;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/WEB-INF/spring_test.xml")
@Transactional
public class JpaPlanoDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	@Qualifier("jpaPlanoDao")
	private PlanoDao planoDao;

	private List<Plano> planosValidos = PlanoDataFactory.criaPlanosValidos();
	private List<Plano> planosInvalidos = PlanoDataFactory.criaPlanosInvalidos();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private EntityManager obtemEntityManager() {
		return this.entityManager;
	}

	private PlanoDao obtemPlanoDao() {
		return this.planoDao;
	}

	@Test
	@Rollback(false)
	public void testaSeValidaPlano() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		int contaPlanosValidos = 0;
		int contaPlanosInvalidos = 0;

		for (Plano planoInvalido : planosInvalidos) {
			Set<ConstraintViolation<Plano>> constraintViolations = validator.validate(planoInvalido);

			if (constraintViolations.size() > 0)
				contaPlanosInvalidos++;
		}

		for (Plano planoValido : planosValidos) {
			Set<ConstraintViolation<Plano>> constraintViolations = validator.validate(planoValido);

			if (constraintViolations.size() == 0)
				contaPlanosValidos++;
		}

		assertEquals(planosValidos.size(), contaPlanosValidos);
		assertEquals(planosInvalidos.size(), contaPlanosInvalidos);
	}

	@Test
	@Rollback(false)
	public void testaSePersistePlanoValido() {
		Plano planoValido = planosValidos.get(0);

		this.obtemPlanoDao().persiste(planoValido);

		Plano planoNoBancoDeDados = this.obtemEntityManager().find(Plano.class, planoValido.getId());

		assertTrue(planoNoBancoDeDados.getId() > 0);
		assertEquals(planoValido.getTitulo(), planoNoBancoDeDados.getTitulo());
		assertEquals(planoValido.getDescricao(), planoNoBancoDeDados.getDescricao());
		assertEquals(planoValido.getValorMensal(), planoNoBancoDeDados.getValorMensal());
	}

	@Test
	@Rollback(false)
	public void testaSeLancaExcecaoComPlanoInvalido() {
		thrown.expect(TransactionSystemException.class);
		
		Plano planoInvalido = planosInvalidos.get(0);
		this.obtemPlanoDao().persiste(planoInvalido);
	}
}
