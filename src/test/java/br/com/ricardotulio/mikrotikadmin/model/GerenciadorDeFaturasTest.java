package br.com.ricardotulio.mikrotikadmin.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;

public class GerenciadorDeFaturasTest {

	@Mock
	private ClienteDao clienteDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testaSeGeraUmaFaturaParaCadaCliente() {

	}
	
	

}
