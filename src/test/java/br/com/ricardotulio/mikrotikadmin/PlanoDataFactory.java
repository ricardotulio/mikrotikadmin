package br.com.ricardotulio.mikrotikadmin;

import java.util.Arrays;
import java.util.Collection;

import br.com.ricardotulio.mikrotikadmin.model.Plano;

public class PlanoDataFactory {

	public static Collection<Object[]> obtemPlanosValidos() {
		Object[][] planosValidos = new Object[6][1];

		Plano planoValido1 = new Plano();
		planoValido1.setTitulo("Plano");
		planoValido1.setDescricao("Plano para clientes com 1MB de download e 1MB de upload");
		planoValido1.setTaxaUpload(1.0);
		planoValido1.setTaxaDownload(1.0);
		planoValido1.setValorMensal(60.0);
		planoValido1.setAtivo(true);
		planosValidos[0][0] = planoValido1;

		Plano planoValido2 = new Plano();
		planoValido2.setTitulo("Plano de Internet para Cliente");
		planoValido2.setDescricao("Plano para clientes com 1MB de download e 1MB de upload");
		planoValido2.setTaxaUpload(1.0);
		planoValido2.setTaxaDownload(1.0);
		planoValido2.setValorMensal(60.0);
		planoValido2.setAtivo(true);
		planosValidos[1][0] = planoValido2;

		Plano planoValido3 = new Plano();
		planoValido3.setTitulo("Plano de Internet");
		planoValido3.setDescricao(
				"Plano para clientes com 1MB de download e 1MB de upload que se conectarao no youtube, facebook, instagram, usarao whatsa");
		planoValido3.setTaxaUpload(1.0);
		planoValido3.setTaxaDownload(1.0);
		planoValido3.setValorMensal(60.0);
		planoValido3.setAtivo(true);
		planosValidos[2][0] = planoValido3;

		Plano planoValido4 = new Plano();
		planoValido4.setTitulo("Plano de 5MB");
		planoValido4.setDescricao("Plano para clientes com 5mb");
		planoValido4.setTaxaUpload(0.1);
		planoValido4.setTaxaDownload(1.0);
		planoValido4.setValorMensal(60.0);
		planoValido4.setAtivo(true);
		planosValidos[3][0] = planoValido4;

		Plano planoValido5 = new Plano();
		planoValido5.setTitulo("Plano de Clientes de 10MB");
		planoValido5.setDescricao(
				"Plano para clientes com 1MB de download e 1MB de upload que se conectarao no youtube, facebook, instagram, usarao whatsa");
		planoValido5.setTaxaUpload(0.1);
		planoValido5.setTaxaDownload(0.1);
		planoValido5.setValorMensal(60.0);
		planoValido5.setAtivo(true);
		planosValidos[4][0] = planoValido5;

		Plano planoValido6 = new Plano();
		planoValido6.setTitulo("Plano de Clientes de 8MB");
		planoValido6.setDescricao(
				"Plano para clientes com 1MB de download e 1MB de upload que se conectarao no youtube, facebook, instagram, usarao whatsa");
		planoValido6.setTaxaUpload(0.1);
		planoValido6.setTaxaDownload(0.1);
		planoValido6.setValorMensal(1.0);
		planoValido6.setAtivo(true);
		planosValidos[5][0] = planoValido6;

		return Arrays.asList(planosValidos);
	}

	public static Collection<Object[]> obtemPlanosInvalidos() {
		Object[][] planosInvalidos = new Object[10][3];

		// Sem título
		Plano planoInalido1 = new Plano();
		planoInalido1.setTitulo("");
		planoInalido1.setDescricao("Plano para clientes com 1MB de download e 1MB de upload");
		planoInalido1.setTaxaUpload(1.0);
		planoInalido1.setTaxaDownload(1.0);
		planoInalido1.setValorMensal(60.0);
		planoInalido1.setAtivo(true);
		planosInvalidos[0][0] = planoInalido1;
		planosInvalidos[0][1] = "titulo";
		planosInvalidos[0][2] = "Este campo é obrigatório.";

		// Título < 5
		Plano planoInvalido2 = new Plano();
		planoInvalido2.setTitulo("Plan");
		planoInvalido2.setDescricao("Plano para clientes com 1MB de download e 1MB de upload");
		planoInvalido2.setTaxaUpload(1.0);
		planoInvalido2.setTaxaDownload(1.0);
		planoInvalido2.setValorMensal(60.0);
		planoInvalido2.setAtivo(true);
		planosInvalidos[1][0] = planoInvalido2;
		planosInvalidos[1][1] = "titulo";
		planosInvalidos[1][2] = "Este campo é pequeno demais. Ele deveria ter 5 caracteres ou mais.";		

		// Título > 30
		Plano planoInvalido3 = new Plano();
		planoInvalido3.setTitulo("Plano de Internet para pessoass");
		planoInvalido3.setDescricao("Plano para clientes com 1MB de download e 1MB de upload");
		planoInvalido3.setTaxaUpload(1.0);
		planoInvalido3.setTaxaDownload(1.0);
		planoInvalido3.setValorMensal(60.0);
		planoInvalido3.setAtivo(true);
		planosInvalidos[2][0] = planoInvalido3;
		planosInvalidos[2][1] = "titulo";
		planosInvalidos[2][2] = "Este campo é grande demais. Ele deveria ter 30 caracteres ou menos.";			

		// Descrição > 120
		Plano planoInvalido4 = new Plano();
		planoInvalido4.setTitulo("Plano de Internet 4");
		planoInvalido4.setDescricao(
				"Plano para clientes com 1MB de download e 1MB de upload que se conectarao no youtube, facebook, instagram, usarao whatsap");
		planoInvalido4.setTaxaUpload(1.0);
		planoInvalido4.setTaxaDownload(1.0);
		planoInvalido4.setValorMensal(60.0);
		planoInvalido4.setAtivo(true);
		planosInvalidos[3][0] = planoInvalido4;
		planosInvalidos[3][1] = "descricao";
		planosInvalidos[3][2] = "Este campo é grande demais. Ele deveria ter 120 caracteres ou menos.";			

		// Sem taxa de download definida
		Plano planoInvalido5 = new Plano();
		planoInvalido5.setTitulo("Plano de Internet 4");
		planoInvalido5.setDescricao("");
		planoInvalido5.setTaxaDownload(null);
		planoInvalido5.setTaxaUpload(0.1);
		planoInvalido5.setValorMensal(60.0);
		planoInvalido5.setAtivo(true);
		planosInvalidos[4][0] = planoInvalido5;
		planosInvalidos[4][1] = "taxaDownload";
		planosInvalidos[4][2] = "Este campo é obrigatório.";	
		
		// Taxa de Download < 0.1
		Plano planoInvalido6 = new Plano();
		planoInvalido6.setTitulo("Plano de Internet 4");
		planoInvalido6.setDescricao("");
		planoInvalido6.setTaxaDownload(0.099);
		planoInvalido6.setTaxaUpload(0.1);
		planoInvalido6.setValorMensal(60.0);
		planoInvalido6.setAtivo(true);
		planosInvalidos[5][0] = planoInvalido6;
		planosInvalidos[5][1] = "taxaDownload";
		planosInvalidos[5][2] = "Este campo deve ser maior ou igual a 0.1.";		

		// Sem taxa de Upload
		Plano planoInvalido7 = new Plano();
		planoInvalido7.setTitulo("Plano de Internet 4");
		planoInvalido7.setDescricao("");
		planoInvalido7.setTaxaUpload(null);
		planoInvalido7.setTaxaDownload(1.0);
		planoInvalido7.setValorMensal(60.0);
		planoInvalido7.setAtivo(true);
		planosInvalidos[6][0] = planoInvalido7;
		planosInvalidos[6][1] = "taxaUpload";
		planosInvalidos[6][2] = "Este campo é obrigatório.";	
		
		// Taxa de Upload < 0.1
		Plano planoInvalido8 = new Plano();
		planoInvalido8.setTitulo("Plano de Internet 4");
		planoInvalido8.setDescricao("");
		planoInvalido8.setTaxaDownload(0.1);
		planoInvalido8.setTaxaUpload(0.099);
		planoInvalido8.setValorMensal(60.0);
		planoInvalido8.setAtivo(true);
		planosInvalidos[7][0] = planoInvalido8;
		planosInvalidos[7][1] = "taxaUpload";
		planosInvalidos[7][2] = "Este campo deve ser maior ou igual a 0.1.";		

		// Sem valor mensal
		Plano planoInvalido9 = new Plano();
		planoInvalido9.setTitulo("Plano de Internet 4");
		planoInvalido9.setDescricao("");
		planoInvalido9.setTaxaUpload(0.1);
		planoInvalido9.setTaxaDownload(0.1);
		planoInvalido9.setAtivo(true);
		planosInvalidos[8][0] = planoInvalido9;
		planosInvalidos[8][1] = "valorMensal";
		planosInvalidos[8][2] = "Este campo é obrigatório.";		

		// Valor mensal < 1
		Plano planoInvalido10 = new Plano();
		planoInvalido10.setTitulo("Plano de Internet 4");
		planoInvalido10.setDescricao("");
		planoInvalido10.setTaxaUpload(0.1);
		planoInvalido10.setTaxaDownload(0.1);
		planoInvalido10.setValorMensal(0.99);
		planoInvalido10.setAtivo(true);
		planosInvalidos[9][0] = planoInvalido10;
		planosInvalidos[9][1] = "valorMensal";
		planosInvalidos[9][2] = "Este campo deve ser maior ou igual a 1.";		

		return Arrays.asList(planosInvalidos);
	}

}
