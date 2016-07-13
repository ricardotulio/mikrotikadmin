package br.com.ricardotulio.mikrotikadmin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ricardotulio.mikrotikadmin.dao.ClienteDao;
import br.com.ricardotulio.mikrotikadmin.dao.PlanoDao;
import br.com.ricardotulio.mikrotikadmin.dao.RadCheckDao;
import br.com.ricardotulio.mikrotikadmin.dao.RadGroupReplyDao;
import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Contato;
import br.com.ricardotulio.mikrotikadmin.model.Endereco;
import br.com.ricardotulio.mikrotikadmin.model.Plano;
import br.com.ricardotulio.mikrotikadmin.model.RadCheck;
import br.com.ricardotulio.mikrotikadmin.model.RadGroupReply;
import br.com.ricardotulio.mikrotikadmin.model.UF;

@Controller
public class ClientesController {

	private ClienteDao clienteDao;

	private PlanoDao planoDao;

	private RadCheckDao radCheckDao;

	private RadGroupReplyDao radGroupReplyDao;

	private static final String CLIENTE_CADASTRADO_COM_SUCESSO = "Cliente cadastrado com sucesso!";
	private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado!";
	private static final String CLIENTE_ATUALIZADO_COM_SUCESSO = "Cliente atualizado com sucesso!";
	private static final String CLIENTE_EXCLUIDO_COM_SUCESSO = "Cliente excluído com sucesso!";

	@Autowired
	public ClientesController(ClienteDao clienteDao, PlanoDao planoDao, RadCheckDao radCheckDao,
			RadGroupReplyDao radGroupReplyDao) {
		this.clienteDao = clienteDao;
		this.planoDao = planoDao;
		this.radCheckDao = radCheckDao;
		this.radGroupReplyDao = radGroupReplyDao;
	}

	@RequestMapping(value = "/clientes/", method = RequestMethod.GET)
	public String index(Model model) {
		List<Cliente> clientes = this.clienteDao.obtemLista();
		model.addAttribute("clientes", clientes);
		return "clientes/index";
	}

	@RequestMapping(value = { "/clientes/buscaPorLogin/{login}" }, method = RequestMethod.GET)
	@ResponseBody
	public String buscaClientePorLogin(@PathVariable("login") String login) {
		Cliente cliente = this.clienteDao.obtemClientePorLogin(login);
		String responseBody = "";

		if (cliente != null) {
			responseBody += "{ \"success\": true, \"plano\": {";
			responseBody += "\"nome\": \"" + cliente.getNome() + "\", ";
			responseBody += "\"login\": \"" + cliente.getLogin() + "\"";
			responseBody += "}}";
		} else {
			responseBody += "{ \"success\": false }";
		}

		return responseBody;
	}

	@RequestMapping(value = { "/clientes/buscaPorCpf/{cpf:.+}" }, method = RequestMethod.GET)
	@ResponseBody
	public String buscaClientePorCpf(@PathVariable("cpf") String cpf) {
		Cliente cliente = this.clienteDao.obtemClientePorCpf(cpf);
		String responseBody = "";

		if (cliente != null) {
			responseBody += "{ \"success\": true, \"plano\": {";
			responseBody += "\"nome\": \"" + cliente.getNome() + "\", ";
			responseBody += "\"login\": \"" + cliente.getLogin() + "\"";
			responseBody += "}}";
		} else {
			responseBody += "{ \"success\": false }";
		}

		return responseBody;
	}

	@RequestMapping(value = { "/clientes/cadastrar", "/clientes/cadastrar/" }, method = RequestMethod.GET)
	public String cadastrarGet(Model model) {
		List<Plano> planos = this.planoDao.obtemListaDePlanosAtivos();
		List<UF> listaUfs = Arrays.asList(UF.values());

		model.addAttribute("planos", planos);
		model.addAttribute("ufs", listaUfs);
		return "clientes/cadastrar";
	}

	@RequestMapping(value = { "/clientes/cadastrar", "/clientes/cadastrar/" }, method = RequestMethod.POST)
	@Transactional
	public String cadastrarPost(Cliente cliente, Endereco endereco, Contato contato,
			@RequestParam("planoId") Long planoId, final RedirectAttributes redirectAttributes) {
		Plano plano = this.planoDao.obtem(planoId);
		cliente.setPlano(plano);
		cliente.adicionaEndereco(endereco);
		cliente.adicionaContato(contato);
		this.clienteDao.persiste(cliente);

		RadCheck radCheck = new RadCheck();
		radCheck.setId(cliente.getId());
		radCheck.setUsername(cliente.getLogin());
		radCheck.setValue(cliente.getSenha());

		RadGroupReply radGroupReply = this.radGroupReplyDao.get(planoId);
		radCheck.setRadGroupReply(radGroupReply);
		this.radCheckDao.persist(radCheck);

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
		List<UF> listaUfs = Arrays.asList(UF.values());

		model.addAttribute("planos", planos);
		model.addAttribute("ufs", listaUfs);
		model.addAttribute("cliente", cliente);
		model.addAttribute("endereco", cliente.getEnderecos().iterator().next());
		model.addAttribute("contato", cliente.getContatos().iterator().next());
		return "clientes/editar";
	}

	@RequestMapping(value = "/clientes/editar/{id}", method = RequestMethod.POST)
	@Transactional
	public String editarPost(@PathVariable("id") Long id, Cliente cliente, Endereco endereco, Contato contato,
			@RequestParam("planoId") Long planoId, final RedirectAttributes redirectAttributes) {
		Cliente clienteExiste = this.clienteDao.obtem(id);

		if (clienteExiste == null) {
			return this.informaQueClienteNaoExiste(redirectAttributes);
		}

		if (cliente.getSenha().equals("00000000")) {
			cliente.setSenha(clienteExiste.getSenha());
		}

		cliente.setId(id);

		Long enderecoId = clienteExiste.getEnderecos().iterator().next().getId();
		endereco.setId(enderecoId);
		cliente.adicionaEndereco(endereco);

		Long contatoId = clienteExiste.getContatos().iterator().next().getId();
		contato.setId(contatoId);
		cliente.adicionaContato(contato);

		cliente.setPlano(this.planoDao.obtem(planoId));
		this.clienteDao.persiste(cliente);

		RadCheck radCheck = radCheckDao.get(cliente.getId());
		radCheck.setUsername(cliente.getLogin());
		radCheck.setValue(cliente.getSenha());

		RadGroupReply radGroupReply = this.radGroupReplyDao.get(planoId);
		radCheck.setRadGroupReply(radGroupReply);
		this.radCheckDao.persist(radCheck);

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

		RadCheck radCheck = this.radCheckDao.get(cliente.getId());
		this.radCheckDao.remove(radCheck);

		redirectAttributes.addFlashAttribute("success", ClientesController.CLIENTE_EXCLUIDO_COM_SUCESSO);
		return "redirect:/clientes/";
	}

	private String informaQueClienteNaoExiste(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", ClientesController.CLIENTE_NAO_ENCONTRADO);
		return "redirect:/clientes/";
	}

}
