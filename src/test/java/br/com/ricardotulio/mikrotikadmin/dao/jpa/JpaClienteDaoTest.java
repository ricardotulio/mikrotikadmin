package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/WEB-INF/spring.xml" })
public class JpaClienteDaoTest {

	@Autowired
	private ClienteDao clienteDao;

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
	public void testaSeCadastraCliente() {
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
		assertEquals(plano.getRadGroupReply(), cliente.getPlano().getRadGroupReply());

		JpaClienteDaoTest.cliente = cliente;
	}

}
