package br.com.ricardotulio.mikrotikadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.dao.RadGroupReplyDao;
import br.com.ricardotulio.mikrotikadmin.model.Plano;
import br.com.ricardotulio.mikrotikadmin.model.RadGroupReply;

@Controller
public class PlanosController {

	private PlanoDao planoDao;

	private RadGroupReplyDao radGroupReplyDao;

	private static final String PLANO_CADASTRADO_COM_SUCESSO = "Plano cadastrado com sucesso!";
	private static final String PLANO_NAO_ENCONTRADO = "Plano não encontrado!";
	private static final String PLANO_ATUALIZADO_COM_SUCESSO = "Plano atualizado com sucesso!";
	private static final String PLANO_EXCLUIDO_COM_SUCESSO = "Plano excluído encontrado!";
	private static final String PLANO_NAO_EXCLUIDO_POSSUI_CLIENTES = "Plano não pode ser excluído pois possui clientes vinculados.";

	@Autowired
	public PlanosController(PlanoDao planoDao, RadGroupReplyDao radGroupReplyDao) {
		this.planoDao = planoDao;
		this.radGroupReplyDao = radGroupReplyDao;
	}

	@RequestMapping(value = "/planos/", method = RequestMethod.GET)
	public String index(Model model) {
		List<Plano> planos = this.planoDao.obtemLista();

		model.addAttribute("planos", planos);
		return "planos/index";
	}

	@RequestMapping(value = "/planos/buscaPorTitulo/{titulo}", method = RequestMethod.GET)
	@ResponseBody
	public String buscaPlanoPorTitulo(@PathVariable("titulo") String titulo) {
		Plano plano = this.planoDao.obtemPlanoPorTitulo(titulo);
		String responseBody = "";

		if (plano != null) {
			responseBody += "{ \"success\": true, \"plano\": {";
			responseBody += "\"titulo\": \"" + plano.getTitulo() + "\", ";
			responseBody += "\"descricao\": \"" + plano.getDescricao() + "\", ";
			responseBody += "\"taxaDownload\": \"" + plano.getTaxaDownload() + "\", ";
			responseBody += "\"taxaUpload\": \"" + plano.getTaxaUpload() + "\", ";
			responseBody += "\"valorMensal\": \"" + plano.getValorMensal() + "\", ";
			responseBody += "\"ativo\": " + plano.isAtivo();
			responseBody += "}}";
		} else {
			responseBody += "{ \"success\": false }";
		}

		return responseBody;
	}

	@RequestMapping(value = { "/planos/cadastrar", "/planos/cadastrar/" }, method = RequestMethod.GET)
	public String cadastrarGet() {
		return "planos/cadastrar";
	}

	@RequestMapping(value = { "/planos/cadastrar", "/planos/cadastrar/" }, method = RequestMethod.POST)
	@Transactional
	public String cadastrarPost(Plano plano, final RedirectAttributes redirectAttributes) {
		this.planoDao.persiste(plano);

		RadGroupReply radGroupReply = new RadGroupReply();
		radGroupReply.setId(plano.getId());
		radGroupReply.setGroupname(plano.getTitulo().replaceAll("\\s", "").toLowerCase());
		radGroupReply.setValue(Integer.toString((int) (plano.getTaxaUpload() * 1024)) + "k/"
				+ Integer.toString((int) (plano.getTaxaDownload() * 1024)) + "k");
		this.radGroupReplyDao.persist(radGroupReply);

		redirectAttributes.addFlashAttribute("success", PlanosController.PLANO_CADASTRADO_COM_SUCESSO);
		return "redirect:/planos/";
	}

	@RequestMapping(value = "/planos/editar/{id}", method = RequestMethod.GET)
	public String editarGet(@PathVariable(value = "id") Long id, Model model,
			final RedirectAttributes redirectAttributes) {
		Plano plano = this.planoDao.obtem(id);

		if (plano == null) {
			return this.informaQuePlanoNaoExiste(redirectAttributes);
		}

		model.addAttribute("plano", plano);
		return "planos/editar";
	}

	@RequestMapping(value = "/planos/editar/{id}", method = RequestMethod.POST)
	@Transactional
	public String editarPost(@PathVariable(value = "id") Long id, Plano plano,
			final RedirectAttributes redirectAttributes) {
		if (this.planoDao.obtem(id) == null) {
			return this.informaQuePlanoNaoExiste(redirectAttributes);
		}

		plano.setId(id);
		this.planoDao.persiste(plano);

		RadGroupReply radGroupReply = radGroupReplyDao.get(plano.getId());
		radGroupReply.setValue(Integer.toString((int) (plano.getTaxaUpload() * 1024)) + "k/"
				+ Integer.toString((int) (plano.getTaxaDownload() * 1024)) + "k");

		redirectAttributes.addFlashAttribute("success", PlanosController.PLANO_ATUALIZADO_COM_SUCESSO);
		return "redirect:/planos/";
	}

	@RequestMapping(value = "/planos/excluir/{id}", method = RequestMethod.GET)
	@Transactional
	public String excluirGet(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		Plano plano = this.planoDao.obtem(id);

		if (plano == null) {
			return this.informaQuePlanoNaoExiste(redirectAttributes);
		}

		if (this.planoDao.planoPossuiClientes(plano)) {
			redirectAttributes.addFlashAttribute("error", PlanosController.PLANO_NAO_EXCLUIDO_POSSUI_CLIENTES);
			return "redirect:/planos/";
		}

		this.planoDao.remove(plano);

		RadGroupReply radGroupReply = radGroupReplyDao.get(plano.getId());
		radGroupReplyDao.remove(radGroupReply);

		redirectAttributes.addFlashAttribute("success", PlanosController.PLANO_EXCLUIDO_COM_SUCESSO);
		return "redirect:/planos/";
	}

	private String informaQuePlanoNaoExiste(final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", PlanosController.PLANO_NAO_ENCONTRADO);
		return "redirect:/planos/";
	}
}
