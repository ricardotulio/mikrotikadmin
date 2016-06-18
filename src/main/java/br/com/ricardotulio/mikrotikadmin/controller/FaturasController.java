package br.com.ricardotulio.mikrotikadmin.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.dao.FaturaDao;
import br.com.ricardotulio.mikrotikadmin.model.AcaoAposGerarFaturas;
import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Fatura;
import br.com.ricardotulio.mikrotikadmin.model.GerenciadorDeFaturas;
import br.com.ricardotulio.mikrotikadmin.model.GravaFaturaNoBanco;

@Controller
public class FaturasController {

	private ClienteDao clienteDao;

	private FaturaDao faturaDao;

	@Autowired
	public FaturasController(ClienteDao clienteDao, FaturaDao faturaDao) {
		this.clienteDao = clienteDao;
		this.faturaDao = faturaDao;
	}

	@RequestMapping(value = { "/faturas", "/faturas/" }, method = RequestMethod.GET)
	public String index(Model model) {
		List<Fatura> faturas = this.faturaDao.obtemLista();
		model.addAttribute("faturas", faturas);
		return "faturas/index";
	}

	@Transactional
	public void geraProximasFaturas() {
		System.out.println("Executando sistema de faturamento...");
		Calendar dataFaturamentoTermino = Calendar.getInstance();

		Calendar dataVencimento = Calendar.getInstance();
		dataVencimento.add(Calendar.DATE, GerenciadorDeFaturas.NUMERO_DIAS_ANTECEDENCIA_GERACAO_FATURA);
		Integer diaDoMesParaPgto = dataVencimento.get(Calendar.DAY_OF_MONTH);

		List<Cliente> clientes = clienteDao.obtemListaAtivosComVencimentoEm(diaDoMesParaPgto);

		List<AcaoAposGerarFaturas> acoes = new ArrayList<AcaoAposGerarFaturas>();
		GravaFaturaNoBanco gravaFaturaNoBanco = new GravaFaturaNoBanco(this.faturaDao);
		acoes.add(gravaFaturaNoBanco);

		GerenciadorDeFaturas gerenciadorDeFaturas = new GerenciadorDeFaturas();
		gerenciadorDeFaturas.geraProximasFaturas(clientes, dataFaturamentoTermino, dataVencimento, acoes);
	}

}
