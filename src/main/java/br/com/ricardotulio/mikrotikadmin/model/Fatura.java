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

	public static final Double VALOR_MINIMO_FATURA = 30.0;

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
	@Temporal(TemporalType.DATE)
	@NotNull
	private Calendar dataPagamento;

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

	public PeriodoFaturamento getPeriodoFaturamento() {
		return periodoFaturamento;
	}

	public void setPeriodoFaturamento(PeriodoFaturamento periodoFaturamento) {
		this.periodoFaturamento = periodoFaturamento;
		this.dataFaturamentoInicio = periodoFaturamento.getDataFaturamentoInicio();
		this.dataFaturamentoTermino = periodoFaturamento.getDataFaturamentoTermino();
	}

	public Calendar getDataFaturamentoInicio() {
		return dataFaturamentoInicio;
	}

	public void setDataFaturamentoInicio(Calendar dataFaturamentoInicio) {
		this.dataFaturamentoInicio = dataFaturamentoInicio;
	}

	public Calendar getDataFaturamentoTermino() {
		return dataFaturamentoTermino;
	}

	public void setDataFaturamentoTermino(Calendar dataFaturamentoTermino) {
		this.dataFaturamentoTermino = dataFaturamentoTermino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public StatusFatura getStatus() {
		return status;
	}

	public void setStatus(StatusFatura status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
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
		return !this.periodoFaturamento.completaUmMes();
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

	public boolean cancelada() {
		return this.status == StatusFatura.CANCELADA;
	}

	public void cancelar() {
		this.status = StatusFatura.CANCELADA;
	}

	public boolean paga() {
		return this.status == StatusFatura.PAGA;
	}

	public void pagar(FormaPagamento formaPagamento) {
		this.status = StatusFatura.PAGA;
		this.formaPagamento = formaPagamento;
		this.dataPagamento = Calendar.getInstance();
	}

}
