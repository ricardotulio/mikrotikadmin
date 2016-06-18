package br.com.ricardotulio.mikrotikadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	@RequestMapping({"/", "/dashboard", "/dashboard/"})
	public String index() {
		return "index/index";
	}
	
}
