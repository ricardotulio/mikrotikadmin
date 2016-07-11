package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class GeradorDeFatura {

	public Fatura geraFatura(Cliente cliente) {
		Calendar dataFaturamentoInicio = cliente.getDataFaturamentoInicioProximaFatura();

		Calendar dataFaturamentoTermino = Calendar.getInstance();
		dataFaturamentoTermino.set(Calendar.HOUR, 0);
		dataFaturamentoTermino.set(Calendar.MINUTE, 0);
		dataFaturamentoTermino.set(Calendar.SECOND, 0);
		dataFaturamentoTermino.set(Calendar.MILLISECOND, 0);

		PeriodoFaturamento periodoFaturamento = new PeriodoFaturamento(dataFaturamentoInicio, dataFaturamentoTermino);

		return this.geraFatura(cliente, periodoFaturamento);
	}

	public Fatura geraFatura(Cliente cliente, PeriodoFaturamento periodoFaturamento) {
		DataVencimento dataVencimento = new DataVencimento(periodoFaturamento.getDataFaturamentoTermino());

		return this.geraFatura(cliente, periodoFaturamento, dataVencimento.getDataVencimentoEmCalendar());
	}

	public Fatura geraFatura(Cliente cliente, PeriodoFaturamento periodoFaturamento, Calendar dataVencimento) {
		Fatura fatura = new Fatura();
		fatura.setCliente(cliente);
		fatura.setPeriodoFaturamento(periodoFaturamento);
		fatura.setDataVencimento(dataVencimento);
		fatura.calculaValor();

		return fatura;
	}
}
