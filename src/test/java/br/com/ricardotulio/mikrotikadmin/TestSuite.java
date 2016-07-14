package br.com.ricardotulio.mikrotikadmin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.ricardotulio.mikrotikadmin.model.ClienteTest;
import br.com.ricardotulio.mikrotikadmin.model.DataVencimentoTest;
import br.com.ricardotulio.mikrotikadmin.model.FaturaTest;
import br.com.ricardotulio.mikrotikadmin.model.GeradorDeFaturaTest;
import br.com.ricardotulio.mikrotikadmin.model.GerenciadorDeFaturasTest;
import br.com.ricardotulio.mikrotikadmin.model.PeriodoFaturamentoTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ClienteTest.class,
	PeriodoFaturamentoTest.class,
	DataVencimentoTest.class,
	FaturaTest.class,
	GeradorDeFaturaTest.class,
	GerenciadorDeFaturasTest.class
})
public class TestSuite {

	@BeforeClass
	public static void setUp() {
	}
	
	@AfterClass
	public static void tearDown() {
		
	}
	
}
