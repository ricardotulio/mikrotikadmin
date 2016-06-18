package br.com.ricardotulio.mikrotikadmin.model;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class PeriodoFechamentoTest {
	
	@Test
	public void testaSeCriaPeriodoFechamentoComIntervaloEmDias() {
		Calendar dataFechamentoInicio = Calendar.getInstance();
		dataFechamentoInicio.set(2016, 5, 10, 0, 0, 0);
		
		Calendar dataFechamentoFim = Calendar.getInstance();
		dataFechamentoFim.setTimeInMillis(dataFechamentoInicio.getTimeInMillis());
		dataFechamentoFim.add(Calendar.DAY_OF_MONTH, 15);
				
		PeriodoFaturamento periodoFechamento = new PeriodoFaturamento(dataFechamentoInicio, 15);
		
		assertEquals(15, periodoFechamento.calculaIntervaloEmDias(), 0.01);
	}

	@Test
	public void testaSeCalculaIntervaloEntrePeriodoFechamento() {
		Calendar dataFechamentoInicio = Calendar.getInstance();
		dataFechamentoInicio.set(2016, 5, 15, 0, 0, 0);

		Calendar dataFechamentoFim = Calendar.getInstance();
		dataFechamentoFim.set(2016, 5, 25, 0, 0, 0);

		PeriodoFaturamento periodoFechamento = new PeriodoFaturamento(dataFechamentoInicio, dataFechamentoFim);
		Long intervaloPeriodo = periodoFechamento.calculaIntervaloEmDias();
		
		assertEquals(10, intervaloPeriodo, 0.01);
	}
	
	@Test
	public void testaSeCalculaPeriodoCompletaUmMes() {
		Calendar dataFechamentoInicio = Calendar.getInstance();
		dataFechamentoInicio.set(2016, 4, 10, 0, 0, 0);
		
		Calendar dataFechamentoFim = Calendar.getInstance();
		dataFechamentoFim.setTimeInMillis(dataFechamentoInicio.getTimeInMillis());
		dataFechamentoFim.set(2016, 5, 9, 0, 0, 0);
		
		PeriodoFaturamento periodoFechamento1 = new PeriodoFaturamento(dataFechamentoInicio, dataFechamentoFim);
				
		assertTrue(periodoFechamento1.ehUmMesCompleto());
		
		dataFechamentoFim.set(2015, 4, 8, 0, 0, 0);
		periodoFechamento1.setDataFaturamentoTermino(dataFechamentoFim);
		
		assertFalse(periodoFechamento1.ehUmMesCompleto());
		
		dataFechamentoInicio.set(2016, 1, 10, 0, 0, 0);
		dataFechamentoFim.setTimeInMillis(dataFechamentoInicio.getTimeInMillis());
		dataFechamentoFim.set(2016, 2, 10, 0, 0, 0);
		
		PeriodoFaturamento periodoFechamento2 = new PeriodoFaturamento(dataFechamentoInicio, dataFechamentoFim);
		
		assertFalse(periodoFechamento2.ehUmMesCompleto());
	}
	
}
