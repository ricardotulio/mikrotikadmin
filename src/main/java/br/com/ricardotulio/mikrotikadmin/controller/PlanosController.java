package br.com.ricardotulio.mikrotikadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.model.Plano;
import me.legrange.mikrotik.ApiConnection;

@Controller
public class PlanosController {

	private PlanoDao planoDao;
	
	private static final String PLANO_CADASTRADO_COM_SUCESSO = "Plano cadastrado encontrado!";
	private static final String PLANO_NAO_ENCONTRADO = "Plano não encontrado!";
	private static final String PLANO_ATUALIZADO_COM_SUCESSO = "Plano atualizado encontrado!";
	private static final String PLANO_EXCLUIDO_COM_SUCESSO = "Plano excluído encontrado!";

	@Autowired
	public PlanosController(PlanoDao planoDao, ApiConnection conn) {
		this.planoDao = planoDao;
	}
	
	@RequestMapping(value = "/planos/", method = RequestMethod.GET)
	public String index(Model model) {
		List<Plano> planos = this.planoDao.obtemLista();
		model.addAttribute("planos", planos);
		return "planos/index";
	}

	@RequestMapping(value = { "/planos/cadastrar", "/planos/cadastrar/" }, method = RequestMethod.GET)
	public String cadastrarGet() {
		return "planos/cadastrar";
	}

	@RequestMapping(value = { "/planos/cadastrar", "/planos/cadastrar/" }, method = RequestMethod.POST)
	@Transactional
	public String cadastrarPost(Plano plano, final RedirectAttributes redirectAttributes) {
		this.planoDao.persiste(plano);
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
	public String editarPost(@PathVariable(value = "id") Long id, Plano plano, final RedirectAttributes redirectAttributes) {
		if(this.planoDao.obtem(id) == null) {
			return this.informaQuePlanoNaoExiste(redirectAttributes);
		}
		
		plano.setId(id);
		this.planoDao.persiste(plano);
		redirectAttributes.addFlashAttribute("success", PlanosController.PLANO_ATUALIZADO_COM_SUCESSO);
		return "redirect:/planos/";
	}

	@RequestMapping(value = "/planos/excluir/{id}", method = RequestMethod.GET)
	@Transactional
	public String excluirGet(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		Plano plano = this.planoDao.obtem(id);
		
		if(plano == null) {
			return this.informaQuePlanoNaoExiste(redirectAttributes);
		}
		
		this.planoDao.remove(plano);
		redirectAttributes.addFlashAttribute("success", PlanosController.PLANO_EXCLUIDO_COM_SUCESSO);
		return "redirect:/planos/";
	}

	private String informaQuePlanoNaoExiste(final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", PlanosController.PLANO_NAO_ENCONTRADO);
		return "redirect:/planos/";
	}
}
