package br.com.ricardotulio.mikrotikadmin.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.dao.FaturaDao;

public class GerenciadorDeFaturasTest {

	@Test
	public void testaSeGeraProximasFaturas() {
		Calendar dataFaturamentoInicio1 = Calendar.getInstance();
		dataFaturamentoInicio1.add(Calendar.DATE, -26);

		Cliente cliente1 = mock(Cliente.class);
		when(cliente1.getValorMensalAPagar()).thenReturn(100.00);
		when(cliente1.getDataFaturamentoInicioProximaFatura()).thenReturn(dataFaturamentoInicio1);

		Calendar dataFaturamentoInicio2 = Calendar.getInstance();
		dataFaturamentoInicio2.add(Calendar.DATE, -50);

		Cliente cliente2 = mock(Cliente.class);
		when(cliente2.getValorMensalAPagar()).thenReturn(130.00);
		when(cliente2.getDataFaturamentoInicioProximaFatura()).thenReturn(dataFaturamentoInicio2);

		Calendar dataFaturamentoInicio3 = Calendar.getInstance();
		dataFaturamentoInicio3.add(Calendar.DATE, -2);

		Cliente cliente3 = mock(Cliente.class);
		when(cliente3.getValorMensalAPagar()).thenReturn(150.00);
		when(cliente3.getDataFaturamentoInicioProximaFatura()).thenReturn(dataFaturamentoInicio3);

		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);

		Calendar dataFaturamentoTermino = Calendar.getInstance();
		dataFaturamentoTermino.set(Calendar.HOUR, 0);
		dataFaturamentoTermino.set(Calendar.MINUTE, 0);
		dataFaturamentoTermino.set(Calendar.SECOND, 0);
		dataFaturamentoTermino.set(Calendar.MILLISECOND, 0);

		DataVencimento dataVencimento = new DataVencimento(dataFaturamentoTermino);

		ClienteDao clienteDao = mock(ClienteDao.class);
		when(clienteDao.obtemListaAtivosComVencimentoNoDia(any(Integer.class))).thenReturn(clientes);

		FaturaDao faturaDao = mock(FaturaDao.class);

		GerenciadorDeFaturas gerenciadorDeFaturas = new GerenciadorDeFaturas(clienteDao, faturaDao,
				new GeradorDeFatura());
		List<Fatura> faturas = gerenciadorDeFaturas.geraProximasFaturas();

		assertEquals(2, faturas.size());

		Fatura faturaGerada1 = faturas.get(0);
		assertEquals(dataFaturamentoInicio1, faturaGerada1.getDataFaturamentoInicio());
		assertEquals(dataFaturamentoTermino, faturaGerada1.getDataFaturamentoTermino());
		assertEquals(dataVencimento.getDataVencimentoEmCalendar(), faturaGerada1.getDataVencimento());
		assertEquals(83.3333, faturaGerada1.getValor(), 0.01);

		Fatura faturaGerada2 = faturas.get(1);
		assertEquals(dataFaturamentoInicio2, faturaGerada2.getDataFaturamentoInicio());
		assertEquals(dataFaturamentoTermino, faturaGerada2.getDataFaturamentoTermino());
		assertEquals(dataVencimento.getDataVencimentoEmCalendar(), faturaGerada2.getDataVencimento());
		assertEquals(212.3333, faturaGerada2.getValor(), 0.01);
		verify(faturaDao, times(2)).persiste(any(Fatura.class));
	}

}
