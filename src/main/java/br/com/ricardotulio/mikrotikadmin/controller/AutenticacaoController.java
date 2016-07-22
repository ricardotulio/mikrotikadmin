package br.com.ricardotulio.mikrotikadmin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ricardotulio.mikrotikadmin.dao.UsuarioDao;
import br.com.ricardotulio.mikrotikadmin.model.Usuario;

@Controller
public class AutenticacaoController {

	private UsuarioDao usuarioDao;

	@Autowired
	public AutenticacaoController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
	public String loginGet() {
		return "autenticacao/login";
	}

	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.POST)
	public String loginPost(@RequestParam("login") String login, @RequestParam("senha") String senha,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		Usuario usuario = this.usuarioDao.obtemUsuarioPorLogin(login);

		if (usuario != null && usuario.getSenha().equals(senha)) {
			session.setAttribute("usuarioLogado", usuario);
			return "redirect:/";
		}

		redirectAttributes.addFlashAttribute("error", "Login e/ou senha inválidos!");
		return "redirect:/login";
	}

	@RequestMapping(value = { "/logout", "/logout/" }, method = RequestMethod.GET)
	public String logoutGet(HttpSession session) {
		session.removeAttribute("usuarioLogado");
		return "redirect:/login";
	}

}
