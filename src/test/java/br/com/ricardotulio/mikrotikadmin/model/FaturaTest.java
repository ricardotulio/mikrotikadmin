package br.com.ricardotulio.mikrotikadmin.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FaturaTest {

	private static final int QTD_EXECUCOES = 100;

	private Cliente cliente;
	private PeriodoFaturamento periodoFaturamento;
	private Object valorEsperadoFatura;
	private boolean deveLancarFatura;

	@Parameters
	public static Collection<Object[]> parametros() {
		Object[][] parametros = new Object[QTD_EXECUCOES][4];

		for (int i = 0; i < QTD_EXECUCOES; i++) {
			PeriodoFaturamento periodoFaturamento;
			
			Cliente cliente = mock(Cliente.class);
			when(cliente.getValorMensalAPagar()).thenReturn(Math.random() * 300);
			
			Double valorEsperado;
			boolean deveLancarFatura;
			
			if(i % 5 == 0) {
				periodoFaturamento = mock(PeriodoFaturamento.class);
				when(periodoFaturamento.completaUmMes()).thenReturn(true);
				
				valorEsperado = cliente.getValorMensalAPagar();
			} else {
				periodoFaturamento = mock(PeriodoFaturamento.class);
				when(periodoFaturamento.completaUmMes()).thenReturn(false);
				when(periodoFaturamento.calculaIntervaloEmDias()).thenReturn(new Double(Math.random() * 120).longValue());
				
				valorEsperado = cliente.getValorMensalAPagar() / 30 * periodoFaturamento.calculaIntervaloEmDias();
			}
			
			if(valorEsperado < Fatura.VALOR_MINIMO_FATURA) {
				deveLancarFatura = false;
			} else {
				deveLancarFatura = true;
			}
			
			parametros[i][0] = cliente;
			parametros[i][1] = periodoFaturamento;
			parametros[i][2] = valorEsperado;
			parametros[i][3] = deveLancarFatura;
		}

		return Arrays.asList(parametros);
	}

	public FaturaTest(Cliente cliente, PeriodoFaturamento periodoFaturamento, Double valorEsperadoFatura, boolean deveLancarFatura) {
		this.cliente = cliente;
		this.periodoFaturamento = periodoFaturamento;
		this.valorEsperadoFatura = valorEsperadoFatura;
		this.deveLancarFatura = deveLancarFatura;
	}

	@Test
	public void testaSeCalculaFatura() {
		Fatura fatura = new Fatura();
		fatura.setCliente(this.cliente);
		fatura.setPeriodoFaturamento(this.periodoFaturamento);
		fatura.calculaValor();

		assertEquals((Double) this.valorEsperadoFatura, fatura.getValor(), 0.01);
	}

	@Test
	public void testaSeDeveLancarFaturaSeValorAbaixoDoMinimo() {
		Fatura fatura = new Fatura();
		fatura.setCliente(this.cliente);
		fatura.setPeriodoFaturamento(this.periodoFaturamento);
		fatura.calculaValor();

		assertEquals(this.deveLancarFatura, fatura.deveSerLancada());
	}
}
