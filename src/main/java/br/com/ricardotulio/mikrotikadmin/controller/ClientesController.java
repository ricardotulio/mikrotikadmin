package br.com.ricardotulio.mikrotikadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

@Controller
public class ClientesController {

	private ClienteDao clienteDao;

	private PlanoDao planoDao;

	private static final String CLIENTE_CADASTRADO_COM_SUCESSO = "Cliente cadastrado com sucesso!";
	private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado!";
	private static final String CLIENTE_ATUALIZADO_COM_SUCESSO = "Cliente atualizado com sucesso!";
	private static final String CLIENTE_EXCLUIDO_COM_SUCESSO = "Cliente excluído com sucesso!";

	@Autowired
	public ClientesController(ClienteDao clienteDao, PlanoDao planoDao) {
		this.clienteDao = clienteDao;
		this.planoDao = planoDao;
	}

	@RequestMapping(value = "/clientes/", method = RequestMethod.GET)
	public String index(Model model) {
		List<Cliente> clientes = this.clienteDao.obtemLista();
		model.addAttribute("clientes", clientes);
		return "clientes/index";
	}

	@RequestMapping(value = { "/clientes/cadastrar", "/clientes/cadastrar/" }, method = RequestMethod.GET)
	public String cadastrarGet(Model model) {
		List<Plano> planos = this.planoDao.obtemListaDePlanosAtivos();
		model.addAttribute("planos", planos);
		return "clientes/cadastrar";
	}

	@RequestMapping(value = { "/clientes/cadastrar", "/clientes/cadastrar/" }, method = RequestMethod.POST)
	@Transactional
	public String cadastrarPost(Cliente cliente, @RequestParam("planoId") Long planoId,
			final RedirectAttributes redirectAttributes) {
		Plano plano = this.planoDao.obtem(planoId);
		cliente.setPlano(plano);
		this.clienteDao.persiste(cliente);
		redirectAttributes.addFlashAttribute("success", ClientesController.CLIENTE_CADASTRADO_COM_SUCESSO);
		return "redirect:/clientes/";
	}

	@RequestMapping(value = "/clientes/editar/{id}", method = RequestMethod.GET)
	public String editarGet(@PathVariable("id") Long id, Model model, final RedirectAttributes redirectAttributes) {
		Cliente cliente = this.clienteDao.obtem(id);

		if (cliente == null) {
			return this.informaQueClienteNaoExiste(redirectAttributes);
		}

		List<Plano> planos = this.planoDao.obtemListaDePlanosAtivos();
		model.addAttribute("planos", planos);
		model.addAttribute("cliente", cliente);
		return "clientes/editar";
	}

	@RequestMapping(value = "/clientes/editar/{id}", method = RequestMethod.POST)
	@Transactional
	public String editarPost(@PathVariable("id") Long id, Cliente cliente, @RequestParam("planoId") Long planoId,
			final RedirectAttributes redirectAttributes) {
		Cliente clienteExiste = this.clienteDao.obtem(id);
		if (clienteExiste == null) {
			return this.informaQueClienteNaoExiste(redirectAttributes);
		}

		if (cliente.getSenha() == null || cliente.getSenha().trim() == "") {
			cliente.setSenha(clienteExiste.getSenha());
		}

		cliente.setId(id);
		cliente.setPlano(this.planoDao.obtem(planoId));
		this.clienteDao.persiste(cliente);
		redirectAttributes.addFlashAttribute("success", ClientesController.CLIENTE_ATUALIZADO_COM_SUCESSO);
		return "redirect:/clientes/";
	}
	
	public String visualizarGet(@PathVariable("id") Long id, Model model, final RedirectAttributes redirectAttributes) {
		Cliente cliente = this.clienteDao.obtem(id);

		if (cliente == null) {
			this.informaQueClienteNaoExiste(redirectAttributes);
		}
		
		model.addAttribute("cliente", cliente);		
		
		return "clientes/visualizar";
	}

	@RequestMapping(value = "/clientes/excluir/{id}", method = RequestMethod.GET)
	@Transactional
	public String excluirGet(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		Cliente cliente = this.clienteDao.obtem(id);

		if (cliente == null) {
			this.informaQueClienteNaoExiste(redirectAttributes);
		}

		this.clienteDao.remove(cliente);
		redirectAttributes.addFlashAttribute("success", ClientesController.CLIENTE_EXCLUIDO_COM_SUCESSO);
		return "redirect:/clientes/";
	}

	private String informaQueClienteNaoExiste(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", ClientesController.CLIENTE_NAO_ENCONTRADO);
		return "redirect:/clientes/";
	}

}
