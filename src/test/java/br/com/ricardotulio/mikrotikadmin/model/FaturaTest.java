package br.com.ricardotulio.mikrotikadmin.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class FaturaTest {
	
	@Test
	public void testaSeCalculaValorCheio() {
		Calendar dataFaturamentoInicio = Calendar.getInstance();
		dataFaturamentoInicio.add(Calendar.DATE, dataFaturamentoInicio.getActualMaximum(Calendar.DAY_OF_MONTH) * -1);
		
		Fatura ultimaFaturaPaga = new Fatura();
		
		Cliente cliente = mock(Cliente.class);
		when(cliente.getValorMensalAPagar()).thenReturn(87.0);
		
		PeriodoFaturamento periodoFaturamento = new PeriodoFaturamento(dataFaturamentoInicio, Calendar.getInstance());
		
		Fatura novaFatura = new Fatura();
		novaFatura.setCliente(cliente);
		novaFatura.setPeriodoFaturamento(periodoFaturamento);
		novaFatura.calculaValor();
		
		assertEquals(87.0, novaFatura.getValor(), 0.01);
	}
	
	@Test
	public void testaSeCalculaValorQuebrado() {
		Calendar dataFaturamentoInicio = Calendar.getInstance();
		dataFaturamentoInicio.add(Calendar.DATE, -20);
		
		Fatura ultimaFaturaPaga = new Fatura();
		
		Cliente cliente = mock(Cliente.class);
		when(cliente.getValorMensalAPagar()).thenReturn(87.0);
		
		PeriodoFaturamento periodoFaturamento = new PeriodoFaturamento(dataFaturamentoInicio, Calendar.getInstance());
		
		Fatura novaFatura = new Fatura();
		novaFatura.setCliente(cliente);
		novaFatura.setPeriodoFaturamento(periodoFaturamento);
		novaFatura.calculaValor();
		
		assertEquals(58.0, novaFatura.getValor(), 0.01);
	}

	@Test
	public void testaSeNaoLancaFaturaComValorAbaixoDoMinimo() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -10);
		
		Cliente cliente = mock(Cliente.class);
		when(cliente.getDataContrato()).thenReturn(calendar);
		when(cliente.getValorMensalAPagar()).thenReturn(87.0);
		
		PeriodoFaturamento periodoFechamento = new PeriodoFaturamento(calendar, Calendar.getInstance());
		
		Fatura fatura = new Fatura();
		fatura.setCliente(cliente);
		fatura.setPeriodoFaturamento(periodoFechamento);
		fatura.calculaValor();
		
		assertFalse(fatura.deveSerLancada());
	}
	
	@Test
	public void testaSeLancaFaturaComValorAcimaDoMinimo() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -12);
		
		Cliente cliente = mock(Cliente.class);
		when(cliente.getDataContrato()).thenReturn(calendar);
		when(cliente.getValorMensalAPagar()).thenReturn(87.0);
		
		PeriodoFaturamento periodoFechamento = new PeriodoFaturamento(calendar, Calendar.getInstance());
		
		Fatura fatura = new Fatura();
		fatura.setCliente(cliente);
		fatura.setPeriodoFaturamento(periodoFechamento);
		fatura.calculaValor();
		
		assertTrue(fatura.deveSerLancada());
	}
	
	@Test
	public void testaSeCalculaDataVencimento() {
		Calendar dataVencimento1 = Calendar.getInstance();
		dataVencimento1.set(2016, 5, 4, 0, 0, 0);
		
		Calendar dataVencimento2 = Calendar.getInstance();
		dataVencimento2.set(2016, 5, 5, 0, 0, 0);
		
		Calendar proximoDiaUtil = Calendar.getInstance();
		proximoDiaUtil.set(2016, 5, 6, 0, 0, 0);
		
		Fatura fatura = new Fatura();
		fatura.setDataVencimento(dataVencimento1);
		assertEquals(proximoDiaUtil.get(Calendar.YEAR), fatura.getDataVencimento().get(Calendar.YEAR));
		assertEquals(proximoDiaUtil.get(Calendar.MONTH), fatura.getDataVencimento().get(Calendar.MONTH));
		assertEquals(proximoDiaUtil.get(Calendar.DAY_OF_MONTH), fatura.getDataVencimento().get(Calendar.DAY_OF_MONTH));
		
		fatura.setDataVencimento(dataVencimento2);
		assertEquals(proximoDiaUtil.get(Calendar.YEAR), fatura.getDataVencimento().get(Calendar.YEAR));
		assertEquals(proximoDiaUtil.get(Calendar.MONTH), fatura.getDataVencimento().get(Calendar.MONTH));
		assertEquals(proximoDiaUtil.get(Calendar.DAY_OF_MONTH), fatura.getDataVencimento().get(Calendar.DAY_OF_MONTH));
	}
	
	
}
