package br.com.ricardotulio.mikrotikadmin.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public List<Fatura> geraProximasFaturas() {
		Calendar dataVencimentoPrevisto = Calendar.getInstance();
		dataVencimentoPrevisto.add(Calendar.DATE, DataVencimento.NUMERO_DIAS_ANTECEDENCIA_VECTO_FATURA);

		System.out.println("Dia do vencimento: " + dataVencimentoPrevisto.get(Calendar.DAY_OF_MONTH));

		List<Cliente> clientes = this.clienteDao
				.obtemListaAtivosComVencimentoNoDia(dataVencimentoPrevisto.get(Calendar.DAY_OF_MONTH));
		List<Fatura> faturas = new ArrayList<Fatura>();

		List<AcaoAposGerarFatura> acoesAposGerarFatura = new ArrayList<AcaoAposGerarFatura>();
		AcaoAposGerarFatura geraFaturaPagSeguro = new GeraFaturaPagSeguro();
		AcaoAposGerarFatura gravaFaturaNoBanco = new GravaFaturaNoBanco(this.faturaDao);
		AcaoAposGerarFatura enviarFaturaPorEmail = new EnviaFaturaPorEmail();

		acoesAposGerarFatura.add(geraFaturaPagSeguro);
		acoesAposGerarFatura.add(gravaFaturaNoBanco);
		acoesAposGerarFatura.add(enviarFaturaPorEmail);

		System.out.println("Número de clientes: " + clientes.size());

		for (Cliente cliente : clientes) {
			Fatura fatura = this.geradorDeFatura.geraFatura(cliente);
			fatura.setUrlBoleto("teste");

			if (fatura.deveSerLancada()) {
				for (AcaoAposGerarFatura acao : acoesAposGerarFatura) {
					try {
						acao.executa(fatura);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}

				faturas.add(fatura);
			} else {
				System.out.println("Não foi lançada!");
			}

			System.out.println("Foi lançada!");
		}

		System.out.println("Número de faturas lançadas: " + faturas.size());

		return faturas;
	}
}
