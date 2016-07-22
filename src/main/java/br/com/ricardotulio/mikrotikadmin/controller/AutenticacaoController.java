package br.com.ricardotulio.mikrotikadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AutenticacaoController {

	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
	public String loginGet() {
		return "autenticacao/login";
	}

}
