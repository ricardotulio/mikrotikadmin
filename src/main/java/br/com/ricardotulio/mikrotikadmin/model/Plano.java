package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Plano {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotNull
	@Size(min = 5, max = 30)
	private String titulo;

	@Column
	@Size(max = 120)
	private String descricao;

	@Column
	@NotNull
	@DecimalMin("0.1")
	private Double taxaDownload;

	@Column
	@NotNull
	@DecimalMin("0.1")
	private Double taxaUpload;

	@Column(precision = 12, scale = 2)
	@NotNull
	@DecimalMin("0.1")
	private Double valorMensal;

	@Column(updatable = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar criadoEm = Calendar.getInstance();

	@Column(insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar atualizadoEm = Calendar.getInstance();

	@NotNull
	private boolean ativo = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getTaxaDownload() {
		return taxaDownload;
	}

	public void setTaxaDownload(Double taxaDownload) {
		this.taxaDownload = taxaDownload;
	}

	public Double getTaxaUpload() {
		return taxaUpload;
	}

	public void setTaxaUpload(Double taxaUpload) {
		this.taxaUpload = taxaUpload;
	}

	public Double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(Double valor) {
		this.valorMensal = valor;
	}

	public Calendar getCriadoEm() {
		return criadoEm;
	}

	public Calendar getAtualizadoEm() {
		return atualizadoEm;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
