package br.com.ricardotulio.mikrotikadmin.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.dao.FaturaDao;

@Service
public class GerenciadorDeFaturas {

	private ClienteDao clienteDao;
	private FaturaDao faturaDao;
	private GeradorDeFatura geradorDeFatura;

	@Autowired
	public GerenciadorDeFaturas(ClienteDao clienteDao, FaturaDao faturaDao, GeradorDeFatura geradorDeFatura) {
		this.clienteDao = clienteDao;
		this.faturaDao = faturaDao;
		this.geradorDeFatura = geradorDeFatura;
	}

	public List<Fatura> geraProximasFaturas() {
		Calendar dataVencimentoPrevisto = Calendar.getInstance();
		dataVencimentoPrevisto.add(Calendar.DATE, DataVencimento.NUMERO_DIAS_ANTECEDENCIA_VECTO_FATURA);
		List<Cliente> clientes = this.clienteDao
				.obtemListaAtivosComVencimentoNoDia(dataVencimentoPrevisto.get(Calendar.DAY_OF_MONTH));
		List<Fatura> faturas = new ArrayList<Fatura>();

		List<AcaoAposGerarFatura> acoesAposGerarFatura = new ArrayList<AcaoAposGerarFatura>();
		AcaoAposGerarFatura gerarFaturaPagSeguro = new GerarFaturaPagSeguro();
		AcaoAposGerarFatura gravaFaturaNoBanco = new GravaFaturaNoBanco(this.faturaDao);
		AcaoAposGerarFatura enviarFaturaPorEmail = new EnviarFaturaPorEmail();

		acoesAposGerarFatura.add(gerarFaturaPagSeguro);
		acoesAposGerarFatura.add(gravaFaturaNoBanco);
		acoesAposGerarFatura.add(enviarFaturaPorEmail);

		for (Cliente cliente : clientes) {
			Fatura fatura = this.geradorDeFatura.geraFatura(cliente);

			if (fatura.deveSerLancada()) {
				for (AcaoAposGerarFatura acao : acoesAposGerarFatura) {
					acao.executa(fatura);
				}

				faturas.add(fatura);
			}
		}

		return faturas;
	}

}
