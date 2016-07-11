package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class PeriodoFaturamento {

	private Calendar dataFaturamentoInicio;

	private Calendar dataFaturamentoTermino;

	public PeriodoFaturamento(Calendar dataFechamentoInicio, Calendar dataFechamentoFim) {
		this.dataFaturamentoInicio = this.clonaCalendar(dataFechamentoInicio);
		this.dataFaturamentoTermino = this.clonaCalendar(dataFechamentoFim);
	}

	public PeriodoFaturamento(Calendar dataFechamentoInicio, Integer diferencaEmDias) {
		this.dataFaturamentoInicio = dataFechamentoInicio;
		this.dataFaturamentoTermino = this.clonaCalendar(dataFechamentoInicio);
		this.dataFaturamentoTermino.add(Calendar.DAY_OF_MONTH, diferencaEmDias);
	}
	
	private Calendar clonaCalendar(Calendar dataOriginal) {
		Calendar clone = Calendar.getInstance();
		clone.setTimeInMillis(dataOriginal.getTimeInMillis());
		clone.setTimeZone(dataOriginal.getTimeZone());
		
		return clone;
	}

	public Long calculaIntervaloEmDias() {
		Long diferencaEmMilisegundos = this.dataFaturamentoTermino.getTimeInMillis()
				- this.dataFaturamentoInicio.getTimeInMillis();		

		// 360000 = Uma hora para compensar horário de verão
		return TimeUnit.DAYS.convert(diferencaEmMilisegundos + 3600000, TimeUnit.MILLISECONDS);
	}
	
	public boolean completaUmMes() {
		Long quantidadeDiasNoMes = (long) dataFaturamentoInicio.getActualMaximum(Calendar.DAY_OF_MONTH);
		return ((quantidadeDiasNoMes - 1) == this.calculaIntervaloEmDias());
	}
	
	public Calendar getDataFaturamentoInicio() {
		return this.clonaCalendar(this.dataFaturamentoInicio);
	}
		
	public void setDataFaturamentoInicio(Calendar dataFaturamentoInicio) {
		this.dataFaturamentoInicio = dataFaturamentoInicio;
	}

	public Calendar getDataFaturamentoTermino() {
		return this.clonaCalendar(this.dataFaturamentoTermino);
	}
	
	public void setDataFaturamentoTermino(Calendar dataFaturamentoFim) {
		this.dataFaturamentoTermino = dataFaturamentoFim;
	}

}
