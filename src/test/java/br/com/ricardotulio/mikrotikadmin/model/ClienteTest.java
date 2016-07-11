package br.com.ricardotulio.mikrotikadmin.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ClienteTest {

	private static final int QTD_EXECUCOES = 100;
	
	private Cliente cliente;
	private Calendar dataEsperada;
	
	@Parameters
	public static Collection<Object[]> parametros() {
		Object[][] parametros = new Object[QTD_EXECUCOES][2];
		
		for(int i = 0; i < QTD_EXECUCOES; i++) {
			Integer qtdDiasDataContrato = new Double(Math.random() * 365).intValue();
			
			Calendar dataContrato = Calendar.getInstance();
			dataContrato.set(Calendar.HOUR, 0);
			dataContrato.set(Calendar.MINUTE, 0);
			dataContrato.set(Calendar.SECOND, 0);
			dataContrato.set(Calendar.MILLISECOND, 0);
			dataContrato.add(Calendar.DATE, qtdDiasDataContrato * -1);
			
			Cliente cliente = new Cliente();
			cliente.setDataContrato(dataContrato);
			
			if(i % 2 == 0) {
				Calendar dataFaturamentoTermino = Calendar.getInstance();
				dataFaturamentoTermino.set(Calendar.HOUR, 0);
				dataFaturamentoTermino.set(Calendar.MINUTE, 0);
				dataFaturamentoTermino.set(Calendar.SECOND, 0);
				dataFaturamentoTermino.set(Calendar.MILLISECOND, 0);
				dataFaturamentoTermino.add(Calendar.DATE, (qtdDiasDataContrato - 10) * -1);
				
				Fatura fatura = mock(Fatura.class);
				when(fatura.getDataFaturamentoTermino()).thenReturn(dataFaturamentoTermino);
				
				Calendar dataEsperada = Calendar.getInstance();
				dataEsperada.setTimeInMillis(dataFaturamentoTermino.getTimeInMillis());
				dataEsperada.add(Calendar.DATE, 1);
				
				cliente.setUltimaFaturaPaga(fatura);
				
				parametros[i][0] = cliente;
				parametros[i][1] = dataEsperada;
			} else {
				parametros[i][0] = cliente;
				parametros[i][1] = dataContrato;
			}
		}
		
		return Arrays.asList(parametros);
	}
	
	public ClienteTest(Cliente cliente, Calendar dataEsperada) {
		this.cliente = cliente;
		this.dataEsperada = dataEsperada;
	}
	
	@Test
	public void testaSeObtemDataInicioFaturamentoProximaFatura() {
		assertEquals(this.cliente.getDataFaturamentoInicioProximaFatura(), this.dataEsperada);
	}
}
 