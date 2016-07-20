package br.com.ricardotulio.mikrotikadmin.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/WEB-INF/spring.xml" })
public class JpaPlanoDaoTest {

	@Autowired
	private PlanoDao planoDao;

	private static Plano plano;

	@Test
	@Transactional
	@Rollback(false)
	public void testaSeCadastraPlano() {
		Plano plano = new Plano();
		plano.setTitulo("Planoj dse 10MB");
		plano.setTaxaDownload(1.0);
		plano.setTaxaUpload(0.5);
		plano.setValorMensal(60.0);

		this.planoDao.persiste(plano);

		assertTrue(plano.getId() > 0);
		assertNotNull(plano.getRadGroupReply());
		assertTrue(plano.getRadGroupReply().getId() > 0);
		assertEquals(plano.getTitulo().replaceAll("\\s", "").toLowerCase(), plano.getRadGroupReply().getGroupname());
		assertEquals("==", plano.getRadGroupReply().getOp());

		String taxaDownloadUpload = Integer.toString((int) (plano.getTaxaUpload() * 1024)) + "k/"
				+ Integer.toString((int) (plano.getTaxaDownload() * 1024)) + "k";

		assertEquals(taxaDownloadUpload, plano.getRadGroupReply().getValue());

		JpaPlanoDaoTest.plano = plano;
	}

	@Test
	@Transactional
	@Rollback(false)
	@DependsOn(value = "testaSeCadastraPlano")
	public void testaSeObtemPlanoPorId() {
		Plano plano = this.planoDao.obtem(JpaPlanoDaoTest.plano.getId());

		assertEquals(JpaPlanoDaoTest.plano.getTitulo(), plano.getTitulo());
		assertEquals(JpaPlanoDaoTest.plano.getDescricao(), plano.getDescricao());
		assertEquals(JpaPlanoDaoTest.plano.getTaxaDownload(), plano.getTaxaDownload());
		assertEquals(JpaPlanoDaoTest.plano.getTaxaUpload(), plano.getTaxaUpload());
		assertEquals(plano.getTitulo().replaceAll("\\s", "").toLowerCase(),
				JpaPlanoDaoTest.plano.getRadGroupReply().getGroupname());
		assertEquals("==", JpaPlanoDaoTest.plano.getRadGroupReply().getOp());

		String taxaDownloadUpload = Integer.toString((int) (JpaPlanoDaoTest.plano.getTaxaUpload() * 1024)) + "k/"
				+ Integer.toString((int) (JpaPlanoDaoTest.plano.getTaxaDownload() * 1024)) + "k";

		assertEquals(taxaDownloadUpload, plano.getRadGroupReply().getValue());
	}

	@Test
	@Transactional
	@Rollback(false)
	@DependsOn(value = "testaSeObtemPlanoPorId")
	public void testaSeAtualizaPlano() {
		Long planoId = JpaPlanoDaoTest.plano.getId();

		Plano plano = this.planoDao.obtem(planoId);
		plano.setTaxaDownload(5.0);
		plano.setTaxaUpload(3.0);

		this.planoDao.persiste(plano);

		Plano planoAtualizado = this.planoDao.obtem(planoId);

		assertEquals(plano.getTaxaDownload(), planoAtualizado.getTaxaDownload());
		assertEquals(plano.getTaxaUpload(), planoAtualizado.getTaxaUpload());
		assertEquals(plano.getRadGroupReply().getValue(), planoAtualizado.getRadGroupReply().getValue());

		String taxaDownloadUpload = Integer.toString((int) (plano.getTaxaUpload() * 1024)) + "k/"
				+ Integer.toString((int) (plano.getTaxaDownload() * 1024)) + "k";
		
		assertEquals(taxaDownloadUpload, planoAtualizado.getRadGroupReply().getValue());
	}

	public void testaSeRemovePlano() {

	}

}
