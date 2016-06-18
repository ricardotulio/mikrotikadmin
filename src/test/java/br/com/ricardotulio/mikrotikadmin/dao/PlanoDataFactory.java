package br.com.ricardotulio.mikrotikadmin.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardotulio.mikrotikadmin.model.Plano;

public class PlanoDataFactory {

	public static List<Plano> criaPlanosValidos() {
		List<Plano> planos = new ArrayList<Plano>();

		Plano planoValido1 = new Plano();
		planoValido1.setTitulo("Plano");
		planoValido1.setDescricao("Plano");
		planoValido1.setValorMensal(60.00);
		planoValido1.setTaxaUpload(0.1);
		planoValido1.setTaxaDownload(0.1);

		Plano planoValido2 = new Plano();
		planoValido2.setTitulo("Plano para clientes com 5 mega");
		planoValido2.setDescricao("Plano");
		planoValido2.setValorMensal(80.00);
		planoValido2.setTaxaDownload(1.0);
		planoValido2.setTaxaUpload(1.0);

		Plano planoValido3 = new Plano();
		planoValido3.setTitulo("Plano para clientes com 10mega");
		planoValido3.setDescricao(
				"Plano criado para atender a necessidade de cliente que precisam de uma velocidade rápida, neste plano, temos a velocidad");
		planoValido3.setValorMensal(100.00);
		planoValido3.setTaxaDownload(1.0);
		planoValido3.setTaxaUpload(0.1);

		planos.add(planoValido1);
		planos.add(planoValido2);
		planos.add(planoValido3);

		return planos;
	}

	public static List<Plano> criaPlanosInvalidos() {
		List<Plano> planos = new ArrayList<Plano>();

		// Título nulo
		Plano planoInvalido1 = new Plano();
		planoInvalido1.setDescricao("Plano de 3MB");
		planoInvalido1.setValorMensal(60.00);

		// Título descrição nula
		Plano planoInvalido2 = new Plano();
		planoInvalido2.setTitulo("Plano de 5MB");
		planoInvalido2.setValorMensal(80.00);

		// Título valor nulo
		Plano planoInvalido3 = new Plano();
		planoInvalido3.setTitulo("Plano de 10MB");
		planoInvalido3.setDescricao("Descrição do plano de 10MB");

		// Título < 5
		Plano planoInvalido4 = new Plano();
		planoInvalido4.setTitulo("Plan");
		planoInvalido4.setDescricao("Descrição do plano de 10MB");
		planoInvalido4.setValorMensal(10.00);

		// Título > 30
		Plano planoInvalido5 = new Plano();
		planoInvalido5.setTitulo("Plano para clientes com 10 mega");
		planoInvalido5.setDescricao("Descrição do plano de 10MB");
		planoInvalido5.setValorMensal(10.00);

		// Descrição < 5
		Plano planoInvalido6 = new Plano();
		planoInvalido6.setTitulo("Plano para clientes com 10mega");
		planoInvalido6.setDescricao("Plan");
		planoInvalido6.setValorMensal(10.00);

		// Descrição > 130
		Plano planoInvalido7 = new Plano();
		planoInvalido7.setTitulo("Plano tal");
		planoInvalido7.setDescricao(
				"Plano criado para atender a necessidade de cliente que precisam de uma velocidade rápida, neste plano, temos a velocidade");
		planoInvalido7.setValorMensal(10.00);

		// Valor zerado
		Plano planoInvalido8 = new Plano();
		planoInvalido8.setTitulo("Plano para clientes com 5 mega");
		planoInvalido8.setDescricao("Plano");
		planoInvalido8.setValorMensal(0.0);

		planos.add(planoInvalido1);
		planos.add(planoInvalido2);
		planos.add(planoInvalido3);
		planos.add(planoInvalido4);
		planos.add(planoInvalido5);
		planos.add(planoInvalido6);
		planos.add(planoInvalido7);
		planos.add(planoInvalido8);

		return planos;
	}
}
