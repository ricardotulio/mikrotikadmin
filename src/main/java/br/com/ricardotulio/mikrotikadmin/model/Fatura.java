package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Fatura {

	private static final Double VALOR_MINIMO_FATURA = 30.0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@NotNull
	private Cliente cliente;

	@Transient
	private PeriodoFaturamento periodoFaturamento;

	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	private Calendar dataFaturamentoInicio;

	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	private Calendar dataFaturamentoTermino;

	@Column(precision = 12, scale = 2)
	@DecimalMin("0.1")
	private Double valor;

	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	private Calendar dataVencimento;

	@Column
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private StatusFatura status = StatusFatura.GERADA;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private FormaPagamento formaPagamento;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Calendar criadoEm = Calendar.getInstance();

	@Column(insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar atualizadoEm = Calendar.getInstance();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setPeriodoFaturamento(PeriodoFaturamento periodoFaturamento) {
		this.periodoFaturamento = periodoFaturamento;
		this.dataFaturamentoInicio = periodoFaturamento.getDataFaturamentoInicio();
		this.dataFaturamentoTermino = periodoFaturamento.getDataFaturamentoTermino();
	}

	public Calendar getDataFaturamentoInicio() {
		return dataFaturamentoInicio;
	}

	public Calendar getDataFaturamentoTermino() {
		return dataFaturamentoTermino;
	}

	public Double getValor() {
		return valor;
	}

	public Calendar getDataVencimento() {
		Calendar copia = Calendar.getInstance();
		copia.setTimeInMillis(this.dataVencimento.getTimeInMillis());
		return copia;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		if (dataVencimento.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			dataVencimento.add(Calendar.DATE, 2);
		} else if (dataVencimento.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			dataVencimento.add(Calendar.DATE, 1);
		}

		this.dataVencimento = dataVencimento;
	}

	public StatusFatura getStatus() {
		return status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public Calendar getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Calendar criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Calendar getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Calendar atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public void calculaValor() {
		if (this.deveCalcularValorQuebrado()) {
			this.calculaValorQuebrado();
		} else {
			this.calculaValorCheio();
		}
	}

	private boolean deveCalcularValorQuebrado() {
		return !this.periodoFaturamento.ehUmMesCompleto();
	}

	private void calculaValorCheio() {
		this.valor = this.getCliente().getValorMensalAPagar();
	}

	private void calculaValorQuebrado() {
		Long quantidadeDiasFaturar = this.periodoFaturamento.calculaIntervaloEmDias();
		this.valor = this.getCliente().getValorMensalAPagar() / 30 * quantidadeDiasFaturar;
	}

	public boolean deveSerLancada() {
		return this.valor > Fatura.VALOR_MINIMO_FATURA;
	}

}
