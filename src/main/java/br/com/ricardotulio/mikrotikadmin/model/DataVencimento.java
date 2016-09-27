package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Calendar;

public class DataVencimento {

	public static final int NUMERO_DIAS_ANTECEDENCIA_VECTO_FATURA = 3;

	private Calendar dataVencimento;

	public DataVencimento(Calendar dataFaturamentoTermino) {
		this.dataVencimento = Calendar.getInstance();
		this.dataVencimento.setTimeInMillis(dataFaturamentoTermino.getTimeInMillis());
		this.dataVencimento.add(Calendar.DATE, DataVencimento.NUMERO_DIAS_ANTECEDENCIA_VECTO_FATURA);

		if (this.dataVencimento.get(Calendar.DAY_OF_WEEK) == 1) {
			this.dataVencimento.add(Calendar.DATE, 1);
		} else if (this.dataVencimento.get(Calendar.DAY_OF_WEEK) == 1) {
			this.dataVencimento.add(Calendar.DATE, 2);
		}
	}

	public Calendar getDataVencimentoEmCalendar() {
		return this.dataVencimento;
	}

}
