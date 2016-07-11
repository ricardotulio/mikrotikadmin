package br.com.ricardotulio.mikrotikadmin.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PeriodoFaturamentoTest {

	private static final int QTD_EXECUCOES = 100;
	
	private Calendar dataInicioPeriodo;
	private Calendar dataFimPeriodo;
	private int diferencaEmDias;
	private boolean completaUmMes;
	
	public static Calendar geraDataAleatoria() {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.YEAR, 2016 + ((int) (Math.random() * 100)));
		data.set(Calendar.MONTH, (int) (Math.random() * 11));
		data.set(Calendar.HOUR, 0);
		data.set(Calendar.MINUTE, 0);
		data.set(Calendar.SECOND, 0);
		data.set(Calendar.MILLISECOND, 0);
		data.set(Calendar.DAY_OF_MONTH, (int) (Math.random() * (data.getActualMaximum(Calendar.DAY_OF_MONTH))));
		
		return data;
	}
	
	@Parameters
	public static Collection<Object[]> geraParametros() {
		Object[][] parametros = new Object[QTD_EXECUCOES][4];
		
		for(int i = 0; i < QTD_EXECUCOES; i++) {
			int qtdDias = 0;
			boolean completaUmMes = false;
			
			Calendar dataInicioPeriodo = geraDataAleatoria();
			
			// A cada 5 execuções, faz uma data com mês completo
			if(i % 5 == 0) {				
				qtdDias = (int) (Math.random() * 10000);
			} else {
				qtdDias = dataInicioPeriodo.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
				completaUmMes = true;
			}
			
			Calendar dataFimPeriodo = Calendar.getInstance();
			dataFimPeriodo.setTimeInMillis(dataInicioPeriodo.getTimeInMillis());
			dataFimPeriodo.add(Calendar.DATE, qtdDias);
						
			parametros[i][0] = dataInicioPeriodo;
			parametros[i][1] = dataFimPeriodo;
			parametros[i][2] = qtdDias;
			parametros[i][3] = completaUmMes;
					
		}
		
		return Arrays.asList(parametros);
	}
	
	public PeriodoFaturamentoTest(Calendar dataInicioPeriodo, Calendar dataFimPeriodo, int diferencaEmDias, boolean completaUmMes) {
		this.dataInicioPeriodo = dataInicioPeriodo;
		this.dataFimPeriodo = dataFimPeriodo;
		this.diferencaEmDias = diferencaEmDias;
		this.completaUmMes = completaUmMes;
	}
	
	@Test
	public void testaSeCriaPeriodoFaturamentoComIntervaloEmDias() {				
		PeriodoFaturamento periodoFaturamento = new PeriodoFaturamento(this.dataInicioPeriodo, this.diferencaEmDias);
		assertEquals(this.diferencaEmDias, periodoFaturamento.calculaIntervaloEmDias(), 0.01);
	}

	@Test
	public void testaSeCalculaIntervaloEntrePeriodoFaturamento() {
		PeriodoFaturamento periodoFechamento = new PeriodoFaturamento(this.dataInicioPeriodo, this.dataFimPeriodo);
		assertEquals(this.diferencaEmDias, periodoFechamento.calculaIntervaloEmDias(), 0.01);
	}
	
	@Test
	public void testaSeCalculaPeriodoCompletaUmMes() {	
		PeriodoFaturamento periodoFechamento = new PeriodoFaturamento(this.dataInicioPeriodo, this.dataFimPeriodo);
		assertEquals(this.completaUmMes, periodoFechamento.completaUmMes());
	}
	
}
