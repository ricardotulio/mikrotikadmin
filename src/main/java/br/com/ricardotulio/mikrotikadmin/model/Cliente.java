package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotNull
	private String nome;

	@Column(unique = true)
	@NotNull
	@CPF
	private String cpf;

	@Column
	@NotNull
	private String rg;

	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Calendar dataContrato;

	@Column
	@NotNull
	@Min(1)
	@Max(31)
	private Integer diaParaPagamentos;

	@Column(unique = true)
	@NotNull
	@Size(min = 5, max = 20)
	private String login;

	@Column
	@NotNull
	@Size(min = 5, max = 20)
	private String senha;

	@Column(updatable = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar criadoEm = Calendar.getInstance();

	@Column(insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar atualizadoEm = Calendar.getInstance();

	@Column
	@NotNull
	private boolean ativo = true;

	@ManyToOne
	@NotNull
	private Plano plano;

	@OneToOne
	private Fatura ultimaFaturaPaga;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private RadCheck radCheck = new RadCheck();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Calendar getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(Calendar dataContrato) {
		this.dataContrato = dataContrato;
	}

	public Integer getDiaParaPagamentos() {
		return diaParaPagamentos;
	}

	public void setDiaParaPagamentos(Integer diaParaPagamentos) {
		this.diaParaPagamentos = diaParaPagamentos;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.radCheck.setUserName(login);
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.radCheck.setValue(senha);
		this.senha = senha;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
		this.radCheck.addRadGroupReply(plano.getRadGroupReply());
	}

	public Double getValorMensalAPagar() {
		return this.getPlano().getValorMensal();
	}

	public Fatura getUltimaFaturaPaga() {
		return ultimaFaturaPaga;
	}

	public void setUltimaFaturaPaga(Fatura ultimaFaturaPaga) {
		this.ultimaFaturaPaga = ultimaFaturaPaga;
	}

	public Calendar getDataFaturamentoInicioProximaFatura() {
		if (this.ultimaFaturaPaga == null) {
			return this.dataContrato;
		}

		Calendar dataFaturamentoInicioProximaFatura = Calendar.getInstance();
		dataFaturamentoInicioProximaFatura
				.setTimeInMillis(this.ultimaFaturaPaga.getDataFaturamentoTermino().getTimeInMillis());
		dataFaturamentoInicioProximaFatura.add(Calendar.DATE, 1);

		return dataFaturamentoInicioProximaFatura;
	}

	public RadCheck getRadCheck() {
		return this.radCheck;
	}

}
