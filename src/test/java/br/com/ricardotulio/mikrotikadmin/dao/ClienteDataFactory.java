package br.com.ricardotulio.mikrotikadmin.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

public class ClienteDataFactory {

	public static List<Cliente> criaClientesValidos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Calendar dataContrato1 = Calendar.getInstance();

		Plano plano1 = new Plano();
		plano1.setId(1L);
		plano1.setTitulo("Plano de 5mb");
		plano1.setTaxaDownload(5.0);
		plano1.setTaxaUpload(3.0);
		plano1.setValorMensal(60.0);

		Plano plano2 = new Plano();
		plano2.setId(2L);
		plano2.setTitulo("Plano de 8mb");
		plano2.setTaxaDownload(8.0);
		plano2.setTaxaUpload(4.0);
		plano2.setValorMensal(80.0);

		dataContrato1.set(2016, 5, 2, 0, 0, 0);

		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Ricardo Ledo");
		cliente1.setCpf("374.707.968-78");
		cliente1.setRg("33738816-7");
		cliente1.setPlano(plano1);
		cliente1.setAtivo(true);
		cliente1.setDataContrato(dataContrato1);
		cliente1.setDiaParaPagamentos(3);
		cliente1.setLogin("ricardoledo");
		cliente1.setSenha("123456");

		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Kassia Oliveira");
		cliente2.setCpf("998.056.505-53");
		cliente2.setRg("337388160");
		cliente2.setPlano(plano2);
		cliente2.setAtivo(true);
		cliente2.setDataContrato(dataContrato1);
		cliente2.setDiaParaPagamentos(3);
		cliente2.setLogin("kassiaoli");
		cliente2.setSenha("123456");

		Cliente cliente3 = new Cliente();
		cliente3.setId(3L);
		cliente3.setNome("Leandro Ledo");
		cliente3.setCpf("043.991.304-74");
		cliente3.setRg("37382738372");
		cliente3.setPlano(plano2);
		cliente3.setAtivo(true);
		cliente3.setDataContrato(dataContrato1);
		cliente3.setDiaParaPagamentos(3);
		cliente3.setLogin("leandroledo");
		cliente3.setSenha("123456");

		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);

		return clientes;
	}

}
