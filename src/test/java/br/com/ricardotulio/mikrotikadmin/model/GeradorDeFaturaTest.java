package br.com.ricardotulio.mikrotikadmin.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class GeradorDeFaturaTest {

	private GeradorDeFatura geradorDeFatura;

	@Before
	public void setUp() {
		this.geradorDeFatura = new GeradorDeFatura();
	}

	@Test
	public void testaSeGeraFatura() {
		Cliente cliente = mock(Cliente.class);
		when(cliente.getValorMensalAPagar()).thenReturn(150.0);

		Calendar dataFaturamentoInicio = Calendar.getInstance();
		dataFaturamentoInicio.set(Calendar.HOUR, 0);
		dataFaturamentoInicio.set(Calendar.MINUTE, 0);
		dataFaturamentoInicio.set(Calendar.SECOND, 0);
		dataFaturamentoInicio.set(Calendar.MILLISECOND, 0);
		dataFaturamentoInicio.add(Calendar.MONTH, -1);
		dataFaturamentoInicio.add(Calendar.DATE, dataFaturamentoInicio.getActualMaximum(Calendar.DAY_OF_MONTH) * -1);
		dataFaturamentoInicio.add(Calendar.MONDAY, 1);

		Calendar dataFaturamentoTermino = Calendar.getInstance();
		dataFaturamentoTermino.set(Calendar.HOUR, 0);
		dataFaturamentoTermino.set(Calendar.MINUTE, 0);
		dataFaturamentoTermino.set(Calendar.SECOND, 0);
		dataFaturamentoTermino.set(Calendar.MILLISECOND, 0);

		DataVencimento dataVencimento = new DataVencimento(dataFaturamentoTermino);

		when(cliente.getDataFaturamentoInicioProximaFatura()).thenReturn(dataFaturamentoInicio);

		Fatura fatura = this.geradorDeFatura.geraFatura(cliente);

		assertEquals(150.0, fatura.getValor(), 0.01);
		assertEquals(dataFaturamentoInicio, fatura.getDataFaturamentoInicio());
		assertEquals(dataFaturamentoTermino, fatura.getDataFaturamentoTermino());
		assertEquals(dataVencimento.getDataVencimentoEmCalendar(), fatura.getDataVencimento());
	}

	@Test
	public void testaSeGeraFaturaComPeriodoFaturamento() {
		Cliente cliente = mock(Cliente.class);
		when(cliente.getValorMensalAPagar()).thenReturn(100.0);

		Calendar dataFaturamentoInicio = Calendar.getInstance();
		dataFaturamentoInicio.set(Calendar.HOUR, 0);
		dataFaturamentoInicio.set(Calendar.MINUTE, 0);
		dataFaturamentoInicio.set(Calendar.SECOND, 0);
		dataFaturamentoInicio.set(Calendar.MILLISECOND, 0);
		dataFaturamentoInicio.add(Calendar.DATE, -20);

		Calendar dataFaturamentoTermino = Calendar.getInstance();
		dataFaturamentoTermino.set(Calendar.HOUR, 0);
		dataFaturamentoTermino.set(Calendar.MINUTE, 0);
		dataFaturamentoTermino.set(Calendar.SECOND, 0);
		dataFaturamentoTermino.set(Calendar.MILLISECOND, 0);

		PeriodoFaturamento periodoFaturamento = new PeriodoFaturamento(dataFaturamentoInicio, dataFaturamentoTermino);

		DataVencimento dataVencimento = new DataVencimento(dataFaturamentoTermino);

		when(cliente.getDataFaturamentoInicioProximaFatura()).thenReturn(dataFaturamentoInicio);

		Fatura fatura = this.geradorDeFatura.geraFatura(cliente, periodoFaturamento);

		assertEquals(66.6666, fatura.getValor(), 0.01);
		assertEquals(dataFaturamentoInicio, fatura.getDataFaturamentoInicio());
		assertEquals(dataFaturamentoTermino, fatura.getDataFaturamentoTermino());
		assertEquals(dataVencimento.getDataVencimentoEmCalendar(), fatura.getDataVencimento());
	}

	@Test
	public void testaSeGeraFaturaComPeriodoFaturamentoEDataVencimento() {
		Cliente cliente = mock(Cliente.class);
		when(cliente.getValorMensalAPagar()).thenReturn(200.0);

		Calendar dataFaturamentoInicio = Calendar.getInstance();
		dataFaturamentoInicio.set(Calendar.HOUR, 0);
		dataFaturamentoInicio.set(Calendar.MINUTE, 0);
		dataFaturamentoInicio.set(Calendar.SECOND, 0);
		dataFaturamentoInicio.set(Calendar.MILLISECOND, 0);
		dataFaturamentoInicio.add(Calendar.DATE, -40);

		Calendar dataFaturamentoTermino = Calendar.getInstance();
		dataFaturamentoTermino.set(Calendar.HOUR, 0);
		dataFaturamentoTermino.set(Calendar.MINUTE, 0);
		dataFaturamentoTermino.set(Calendar.SECOND, 0);
		dataFaturamentoTermino.set(Calendar.MILLISECOND, 0);
		dataFaturamentoTermino.add(Calendar.DATE, 10);

		PeriodoFaturamento periodoFaturamento = new PeriodoFaturamento(dataFaturamentoInicio, dataFaturamentoTermino);

		DataVencimento dataVencimento = new DataVencimento(dataFaturamentoTermino);

		when(cliente.getDataFaturamentoInicioProximaFatura()).thenReturn(dataFaturamentoInicio);

		Fatura fatura = this.geradorDeFatura.geraFatura(cliente, periodoFaturamento,
				dataVencimento.getDataVencimentoEmCalendar());

		assertEquals(333.3333, fatura.getValor(), 0.01);
		assertEquals(dataFaturamentoInicio, fatura.getDataFaturamentoInicio());
		assertEquals(dataFaturamentoTermino, fatura.getDataFaturamentoTermino());
		assertEquals(dataVencimento.getDataVencimentoEmCalendar(), fatura.getDataVencimento());
	}

}
