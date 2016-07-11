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
public class DataVencimentoTest {

	private static final int QTD_EXECUCOES = 30;

	private Calendar dataFaturamentoTermino;
	private Calendar dataVencimentoEsperada;

	@Parameters
	public static Collection<Object[]> parametros() {
		Object[][] parametros = new Object[QTD_EXECUCOES][2];

		for (int i = 0; i < QTD_EXECUCOES; i++) {
			Calendar dataFechamento = Calendar.getInstance();
			dataFechamento.set(Calendar.HOUR, 6);
			dataFechamento.set(Calendar.MINUTE, 0);
			dataFechamento.set(Calendar.SECOND, 0);
			dataFechamento.set(Calendar.MILLISECOND, 0);
			dataFechamento.add(Calendar.DATE, new Double(Math.random() * 360).intValue());

			Calendar dataVencimentoEsperada = Calendar.getInstance();
			dataVencimentoEsperada.setTimeInMillis(dataFechamento.getTimeInMillis());
			dataVencimentoEsperada.add(Calendar.DATE, DataVencimento.NUMERO_DIAS_ANTECEDENCIA_VECTO_FATURA);

			if (dataVencimentoEsperada.get(Calendar.DAY_OF_WEEK) == 1) {
				dataVencimentoEsperada.add(Calendar.DATE, 1);
			} else if (dataVencimentoEsperada.get(Calendar.DAY_OF_WEEK) == 1) {
				dataVencimentoEsperada.add(Calendar.DATE, 2);
			}

			parametros[i][0] = dataFechamento;
			parametros[i][1] = dataVencimentoEsperada;
		}

		return Arrays.asList(parametros);
	}

	public DataVencimentoTest(Calendar dataFaturamentoTermino, Calendar dataVencimentoEsperada) {
		this.dataFaturamentoTermino = dataFaturamentoTermino;
		this.dataVencimentoEsperada = dataVencimentoEsperada;
	}

	@Test
	public void testaSeCalculaDataVencimento() {
		DataVencimento dataVencimento = new DataVencimento(this.dataFaturamentoTermino);
		assertEquals(this.dataVencimentoEsperada.getTimeInMillis(),
				dataVencimento.getDataVencimentoEmCalendar().getTimeInMillis());
	}

}
