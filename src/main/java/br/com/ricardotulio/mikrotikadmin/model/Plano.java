package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	private Double taxaDownload = 1.0;

	@Column
	@NotNull
	@DecimalMin("0.1")
	private Double taxaUpload = 1.0;

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
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="radGroupReplyId")
	private RadGroupReply radGroupReply = new RadGroupReply();

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
		this.radGroupReply.setGroupName(titulo.trim().replaceAll("\\s+", "").toLowerCase());
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
		this.radGroupReply.setValue(Integer.toString((int) (this.taxaUpload * 1024)) + "k/" + Integer.toString((int) (this.taxaDownload * 1024)));
	}

	public Double getTaxaUpload() {
		return taxaUpload;
	}

	public void setTaxaUpload(Double taxaUpload) {
		this.taxaUpload = taxaUpload;
		this.radGroupReply.setValue(Integer.toString((int) (this.taxaUpload * 1024)) + "k/" + Integer.toString((int) (this.taxaDownload * 1024)) + "k");
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
	
	public RadGroupReply getRadGroupReply() {
		return this.radGroupReply;
	}	
}
