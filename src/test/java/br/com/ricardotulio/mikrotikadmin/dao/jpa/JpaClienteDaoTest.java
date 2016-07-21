package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Plano;
import br.com.ricardotulio.mikrotikadmin.model.RadCheck;
import br.com.ricardotulio.mikrotikadmin.model.RadGroupRepply;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/WEB-INF/spring.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpaClienteDaoTest {

	@Autowired
	private ClienteDao clienteDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PlanoDao planoDao;

	private static Plano plano;

	private static Cliente cliente;

	@Before
	@Transactional
	@Rollback(false)
	public void setUp() {
		if (JpaClienteDaoTest.plano == null) {
			Plano plano = new Plano();
			plano.setTitulo("Plano de 1MB");
			plano.setTaxaDownload(1.0);
			plano.setTaxaUpload(0.5);
			plano.setValorMensal(60.0);
			this.planoDao.persiste(plano);

			JpaClienteDaoTest.plano = plano;
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	public void test1TestaSeCadastraCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Ricardo Ledo");
		cliente.setCpf("374.707.968-78");
		cliente.setRg("33.738.816-7");
		cliente.setDataContrato(Calendar.getInstance());
		cliente.setDiaParaPagamentos(5);
		cliente.setLogin("ricardoledo");
		cliente.setSenha("123456");
		cliente.setPlano(JpaClienteDaoTest.plano);

		this.clienteDao.persiste(cliente);

		assertNotNull(cliente.getRadCheck());
		assertEquals(cliente.getId(), cliente.getRadCheck().getId());
		assertEquals(cliente.getLogin(), cliente.getRadCheck().getUsername());

		Plano plano = this.planoDao.obtem(JpaClienteDaoTest.plano.getId());

		int contaRadChecksIguais = 0;

		for (RadCheck radCheck : plano.getRadGroupReply().getRadChecks()) {
			if (radCheck.getId() == cliente.getRadCheck().getId()) {
				contaRadChecksIguais++;
				assertEquals(radCheck.getUsername(), cliente.getLogin());
			}
		}

		assertEquals(1, contaRadChecksIguais);

		JpaClienteDaoTest.cliente = cliente;
	}

	@Test
	@Transactional
	@Rollback(false)
	@DependsOn(value = "testaSeCadastraCliente")
	public void test2TestaSeObtemClientePorId() {
		Cliente cliente = this.clienteDao.obtem(JpaClienteDaoTest.cliente.getId());

		assertEquals(JpaClienteDaoTest.cliente.getPlano().getId(), cliente.getPlano().getId());
		assertEquals(JpaClienteDaoTest.cliente.getNome(), cliente.getNome());
		assertEquals(JpaClienteDaoTest.cliente.getCpf(), cliente.getCpf());
		assertEquals(JpaClienteDaoTest.cliente.getRg(), cliente.getRg());
		assertEquals(JpaClienteDaoTest.cliente.getLogin(), cliente.getLogin());
		assertEquals(JpaClienteDaoTest.cliente.getSenha(), cliente.getSenha());
	}

	@Test
	@Transactional
	@Rollback(false)
	@DependsOn(value = "testaSeObtemClientePorId")
	public void test3TestaSeAtualizaCliente() {
		Cliente cliente = JpaClienteDaoTest.cliente;

		cliente.setNome("Adalberto");
		cliente.setRg("44443333");
		cliente.setSenha("321321");

		this.clienteDao.persiste(cliente);

		Cliente clienteAtualizado = this.clienteDao.obtem(cliente.getId());

		assertEquals(cliente.getNome(), clienteAtualizado.getNome());
		assertEquals(cliente.getRg(), clienteAtualizado.getRg());
		assertEquals(cliente.getSenha(), clienteAtualizado.getSenha());
	}

	@Test
	@Transactional
	@Rollback(false)
	@DependsOn(value = "testaSeObtemClientePorId")
	public void test4TestaSeAlteraPlanoCliente() {
		Cliente cliente = JpaClienteDaoTest.cliente;
		Plano planoAntigo = cliente.getPlano();

		Plano planoAtual = new Plano();
		planoAtual.setTitulo("Plano de 3MB");
		planoAtual.setTaxaDownload(3.0);
		planoAtual.setTaxaUpload(1.5);
		planoAtual.setValorMensal(70.0);

		this.planoDao.persiste(planoAtual);

		cliente.setPlano(planoAtual);
		this.clienteDao.persiste(cliente);

		assertEquals(0, this.clienteDao.obtemClientesPorPlano(planoAntigo).size());
		assertEquals(1, this.clienteDao.obtemClientesPorPlano(planoAtual).size());

		RadGroupRepply radGroupRepplyPlanoAntigo = this.entityManager
				.createQuery("SELECT r FROM br.com.ricardotulio.mikrotikadmin.model.RadGroupRepply r LEFT JOIN r.radChecks rc WHERE r.id = ?", RadGroupRepply.class)
				.setParameter(1, planoAntigo.getId()).getSingleResult();

		assertEquals(0, radGroupRepplyPlanoAntigo.getRadChecks().size());

		RadGroupRepply radGroupRepplyPlanoAtual = this.entityManager
				.createQuery("SELECT r FROM br.com.ricardotulio.mikrotikadmin.model.RadGroupRepply r LEFT JOIN r.radChecks rc WHERE r.id = ?", RadGroupRepply.class)
				.setParameter(1, planoAtual.getId()).getSingleResult();

		assertEquals(1, radGroupRepplyPlanoAtual.getRadChecks().size());
	}
}
