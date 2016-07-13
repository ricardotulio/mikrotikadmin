package br.com.ricardotulio.mikrotikadmin;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Contato;
import br.com.ricardotulio.mikrotikadmin.model.Endereco;

public class ClienteDataFactory {

	public static Collection<Object[]> obtemClientesValidos() {
		Object[][] clientesValidos = new Object[10][1];

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua Manajeru");
		endereco.setNumero(200L);
		endereco.setBairro("Vila Isolina Mazzei");
		endereco.setCidade("São Paulo");
		endereco.setUf("sp");
		endereco.setCep("04235-025");

		Contato contato = new Contato();
		contato.setTelefone("(11) 95419-3524");
		contato.setCelular("(11) 95429-3432");
		contato.setEmail("ricardo.tulio@fatec.sp.gov.br");

		// Nome com 3 caracteres
		Cliente clienteValido1 = new Cliente();
		clienteValido1.setNome("Ana");
		clienteValido1.setCpf("847.928.930-97");
		clienteValido1.setRg("33.738.816-7");
		clienteValido1.setDataContrato(Calendar.getInstance());
		clienteValido1.setDiaParaPagamentos(5);
		clienteValido1.setLogin("ricardo948");
		clienteValido1.setSenha("gf155c44");
		clienteValido1.adicionaEndereco(endereco);
		clienteValido1.adicionaContato(contato);
		clientesValidos[0][0] = clienteValido1;
		
		// Nome com 60 caracteres
		Cliente clienteValido2 = new Cliente();
		clienteValido2.setNome("Um nome bem grande que ultrapasse o valor limite definido pelo banco de dados para que nao haja problemas com isso quand");
		clienteValido2.setCpf("546.291.602-72");
		clienteValido2.setRg("33.738.816-7");
		clienteValido2.setDataContrato(Calendar.getInstance());
		clienteValido2.setDiaParaPagamentos(5);
		clienteValido2.setLogin("ricardokdi");
		clienteValido2.setSenha("gf155c44");
		clienteValido2.adicionaEndereco(endereco);
		clienteValido2.adicionaContato(contato);
		clientesValidos[1][0] = clienteValido2;
		
		// Dia para pagamento igual a 1
		Cliente clienteValido3 = new Cliente();
		clienteValido3.setNome("Kassia Oliveira");
		clienteValido3.setCpf("082.912.023-89");
		clienteValido3.setRg("33.738.816-7");
		clienteValido3.setDataContrato(Calendar.getInstance());
		clienteValido3.setDiaParaPagamentos(1);
		clienteValido3.setLogin("ricardoafa");
		clienteValido3.setSenha("gf155c44");
		clienteValido3.adicionaEndereco(endereco);
		clienteValido3.adicionaContato(contato);
		clientesValidos[2][0] = clienteValido3;
		
		// Dia para pagamento igual a 28
		Cliente clienteValido4 = new Cliente();
		clienteValido4.setNome("Leandro Ledo");
		clienteValido4.setCpf("638.066.036-42");
		clienteValido4.setRg("33.738.816-7");
		clienteValido4.setDataContrato(Calendar.getInstance());
		clienteValido4.setDiaParaPagamentos(28);
		clienteValido4.setLogin("ricaddd");
		clienteValido4.setSenha("gf155c44");
		clienteValido4.adicionaEndereco(endereco);
		clienteValido4.adicionaContato(contato);
		clientesValidos[3][0] = clienteValido4;	
		
		// Login com 6 caracteres
		Cliente clienteValido5 = new Cliente();
		clienteValido5.setNome("Yasmin");
		clienteValido5.setCpf("146.626.869-70");
		clienteValido5.setRg("33.738.816-7");
		clienteValido5.setDataContrato(Calendar.getInstance());
		clienteValido5.setDiaParaPagamentos(28);
		clienteValido5.setLogin("ricard");
		clienteValido5.setSenha("gf155c44");
		clienteValido5.adicionaEndereco(endereco);
		clienteValido5.adicionaContato(contato);
		clientesValidos[4][0] = clienteValido5;		
		
		// Login com 20 caracteres
		Cliente clienteValido6 = new Cliente();
		clienteValido6.setNome("Kleber Ferreira");
		clienteValido6.setCpf("189.353.167-84");
		clienteValido6.setRg("33.738.816-7");
		clienteValido6.setDataContrato(Calendar.getInstance());
		clienteValido6.setDiaParaPagamentos(28);
		clienteValido6.setLogin("ricardoledodetulioak");
		clienteValido6.setSenha("gf155c44");
		clienteValido6.adicionaEndereco(endereco);
		clienteValido6.adicionaContato(contato);
		clientesValidos[5][0] = clienteValido6;		

		// Senha com 6 caracteres
		Cliente clienteValido7 = new Cliente();
		clienteValido7.setNome("Nome Teste");
		clienteValido7.setCpf("391.074.542-36");
		clienteValido7.setRg("33.738.816-7");
		clienteValido7.setDataContrato(Calendar.getInstance());
		clienteValido7.setDiaParaPagamentos(28);
		clienteValido7.setLogin("ricardoabcdef");
		clienteValido7.setSenha("gf155c");
		clienteValido7.adicionaEndereco(endereco);
		clienteValido7.adicionaContato(contato);
		clientesValidos[6][0] = clienteValido7;		

		// Senha com 20 caracteres
		Cliente clienteValido8 = new Cliente();
		clienteValido8.setNome("Ana Clara");
		clienteValido8.setCpf("574.722.960-58");
		clienteValido8.setRg("33.738.816-7");
		clienteValido8.setDataContrato(Calendar.getInstance());
		clienteValido8.setDiaParaPagamentos(28);
		clienteValido8.setLogin("ricardoumdois");
		clienteValido8.setSenha("gf15324fasdfadfasdfd");
		clienteValido8.adicionaEndereco(endereco);
		clienteValido8.adicionaContato(contato);
		clientesValidos[7][0] = clienteValido8;
		
		// Logradouro com 5 caracteres
		Cliente clienteValido9 = new Cliente();
		clienteValido9.setNome("João Pedro");
		clienteValido9.setCpf("290.851.063-46");
		clienteValido9.setRg("33.738.816-7");
		clienteValido9.setDataContrato(Calendar.getInstance());
		clienteValido9.setDiaParaPagamentos(28);
		clienteValido9.setLogin("ricardotresquatro");
		clienteValido9.setSenha("gf15324fasdfadfasdfd");
		
		Endereco enderecoValido1 = new Endereco();
		enderecoValido1.setLogradouro("Rua B");
		enderecoValido1.setNumero(200L);
		enderecoValido1.setBairro("Vila Isolina Mazzei");
		enderecoValido1.setCidade("São Paulo");
		enderecoValido1.setUf("sp");
		enderecoValido1.setCep("04235-025");
		
		clienteValido9.adicionaEndereco(enderecoValido1);
		clienteValido9.adicionaContato(contato);
		clientesValidos[8][0] = clienteValido9;				
		
		// Logradouro com 120 caracteres
		Cliente clienteValido10 = new Cliente();
		clienteValido10.setNome("João Silva");
		clienteValido10.setCpf("825.233.690-60");
		clienteValido10.setRg("33.738.816-7");
		clienteValido10.setDataContrato(Calendar.getInstance());
		clienteValido10.setDiaParaPagamentos(28);
		clienteValido10.setLogin("pipipitchu");
		clienteValido10.setSenha("gf15324fasdfadfasdfd");
		
		Endereco enderecoValido2 = new Endereco();
		enderecoValido2.setLogradouro("Rua do fundo do quintal da parafuseta que esta presa no topo do predio onde eu moro que fica do lado da casa tal que fui");
		enderecoValido2.setNumero(200L);
		enderecoValido2.setBairro("Vila Isolina Mazzei");
		enderecoValido2.setCidade("São Paulo");
		enderecoValido2.setUf("sp");
		enderecoValido2.setCep("04235-025");
		
		clienteValido10.adicionaEndereco(enderecoValido2);
		clienteValido10.adicionaContato(contato);
		clientesValidos[9][0] = clienteValido10;				
		
		return Arrays.asList(clientesValidos);
	}

	public static Collection<Object[]> obtemClientesInvalidos() {
		Object[][] clientesInvalidos = new Object[34][3];

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua Manajeru");
		endereco.setNumero(200L);
		endereco.setBairro("Vila Isolina Mazzei");
		endereco.setCidade("São Paulo");
		endereco.setUf("sp");
		endereco.setCep("04235-025");

		Contato contato = new Contato();
		contato.setTelefone("(11) 95419-3524");
		contato.setCelular("(11) 95429-3432");
		contato.setEmail("ricardo.tulio@fatec.sp.gov.br");

		// Sem nome
		Cliente clienteInvalido1 = new Cliente();
		clienteInvalido1.setNome("");
		clienteInvalido1.setCpf("374.707.968-78");
		clienteInvalido1.setRg("33.738.816-7");
		clienteInvalido1.setDataContrato(Calendar.getInstance());
		clienteInvalido1.setDiaParaPagamentos(5);
		clienteInvalido1.setLogin("ricardoledo");
		clienteInvalido1.setSenha("gf155c44");
		clienteInvalido1.adicionaEndereco(endereco);
		clienteInvalido1.adicionaContato(contato);
		clientesInvalidos[0][0] = clienteInvalido1;
		clientesInvalidos[0][1] = "nome";
		clientesInvalidos[0][2] = "Este campo é obrigatório.";

		// Nome < 2 caracteres
		Cliente clienteInvalido2 = new Cliente();
		clienteInvalido2.setNome("Ri");
		clienteInvalido2.setCpf("374.707.968-78");
		clienteInvalido2.setRg("33.738.816-7");
		clienteInvalido2.setDataContrato(Calendar.getInstance());
		clienteInvalido2.setDiaParaPagamentos(5);
		clienteInvalido2.setLogin("ricardoledo");
		clienteInvalido2.setSenha("gf155c44");
		clienteInvalido2.adicionaEndereco(endereco);
		clienteInvalido2.adicionaContato(contato);
		clientesInvalidos[1][0] = clienteInvalido2;
		clientesInvalidos[1][1] = "nome";
		clientesInvalidos[1][2] = "Este campo é pequeno demais. Ele deveria ter 3 caracteres ou mais.";

		// Nome > 120 caracteres
		Cliente clienteInvalido3 = new Cliente();
		clienteInvalido3.setNome(
				"Ricardo Ledo de Tulio Oliveira da Silva Santos Aparecido José Alceu Valenca Machado Porto Zampietro Oliveira De Novo Tuli");
		clienteInvalido3.setCpf("374.707.968-78");
		clienteInvalido3.setRg("33.738.816-7");
		clienteInvalido3.setDataContrato(Calendar.getInstance());
		clienteInvalido3.setDiaParaPagamentos(5);
		clienteInvalido3.setLogin("ricardoledo");
		clienteInvalido3.setSenha("gf155c44");
		clienteInvalido3.adicionaEndereco(endereco);
		clienteInvalido3.adicionaContato(contato);
		clientesInvalidos[2][0] = clienteInvalido3;
		clientesInvalidos[2][1] = "nome";
		clientesInvalidos[2][2] = "Este campo é grande demais. Ele deveria ter 120 caracteres ou menos.";

		// Sem CPF
		Cliente clienteInvalido4 = new Cliente();
		clienteInvalido4.setNome("Ricardo Ledo de Tulio");
		clienteInvalido4.setCpf("");
		clienteInvalido4.setRg("33.738.816-7");
		clienteInvalido4.setDataContrato(Calendar.getInstance());
		clienteInvalido4.setDiaParaPagamentos(5);
		clienteInvalido4.setLogin("ricardoledo");
		clienteInvalido4.setSenha("gf155c44");
		clienteInvalido4.adicionaEndereco(endereco);
		clienteInvalido4.adicionaContato(contato);
		clientesInvalidos[3][0] = clienteInvalido4;
		clientesInvalidos[3][1] = "cpf";
		clientesInvalidos[3][2] = "Este campo é obrigatório.";

		// CPF Inválido
		Cliente clienteInvalido5 = new Cliente();
		clienteInvalido5.setNome("Ricardo Ledo de Tulio");
		clienteInvalido5.setCpf("374.707.968-09");
		clienteInvalido5.setRg("33.738.816-7");
		clienteInvalido5.setDataContrato(Calendar.getInstance());
		clienteInvalido5.setDiaParaPagamentos(5);
		clienteInvalido5.setLogin("ricardoledo");
		clienteInvalido5.setSenha("gf155c44");
		clienteInvalido5.adicionaEndereco(endereco);
		clienteInvalido5.adicionaContato(contato);
		clientesInvalidos[4][0] = clienteInvalido5;
		clientesInvalidos[4][1] = "cpf";
		clientesInvalidos[4][2] = "Este valor parece ser inválido.";

		// CPF Inválido
		Cliente clienteInvalido6 = new Cliente();
		clienteInvalido6.setNome("Ricardo Ledo de Tulio");
		clienteInvalido6.setCpf("374.707.968-78");
		clienteInvalido6.setRg("");
		clienteInvalido6.setDataContrato(Calendar.getInstance());
		clienteInvalido6.setDiaParaPagamentos(5);
		clienteInvalido6.setLogin("ricardoledo");
		clienteInvalido6.setSenha("gf155c44");
		clienteInvalido6.adicionaEndereco(endereco);
		clienteInvalido6.adicionaContato(contato);
		clientesInvalidos[5][0] = clienteInvalido6;
		clientesInvalidos[5][1] = "rg";
		clientesInvalidos[5][2] = "Este campo é obrigatório.";

		// Sem data de contrato
		Cliente clienteInvalido7 = new Cliente();
		clienteInvalido7.setNome("Ricardo Ledo de Tulio");
		clienteInvalido7.setCpf("374.707.968-78");
		clienteInvalido7.setRg("33.738.816-7");
		clienteInvalido7.setDiaParaPagamentos(5);
		clienteInvalido7.setLogin("ricardoledo");
		clienteInvalido7.setSenha("gf155c44");
		clienteInvalido7.adicionaEndereco(endereco);
		clienteInvalido7.adicionaContato(contato);
		clientesInvalidos[6][0] = clienteInvalido7;
		clientesInvalidos[6][1] = "dataContrato";
		clientesInvalidos[6][2] = "Este campo é obrigatório.";

		// Sem dia para pagamentos
		Cliente clienteInvalido8 = new Cliente();
		clienteInvalido8.setNome("Ricardo Ledo de Tulio");
		clienteInvalido8.setCpf("374.707.968-78");
		clienteInvalido8.setRg("33.738.816-7");
		clienteInvalido8.setDataContrato(Calendar.getInstance());
		clienteInvalido8.setLogin("ricardoledo");
		clienteInvalido8.setSenha("gf155c44");
		clienteInvalido8.adicionaEndereco(endereco);
		clienteInvalido8.adicionaContato(contato);
		clientesInvalidos[7][0] = clienteInvalido8;
		clientesInvalidos[7][1] = "diaParaPagamentos";
		clientesInvalidos[7][2] = "Este campo é obrigatório.";

		// Dia para pagamentos < 1
		Cliente clienteInvalido9 = new Cliente();
		clienteInvalido9.setNome("Ricardo Ledo de Tulio");
		clienteInvalido9.setCpf("374.707.968-78");
		clienteInvalido9.setRg("33.738.816-7");
		clienteInvalido9.setDataContrato(Calendar.getInstance());
		clienteInvalido9.setDiaParaPagamentos(0);
		clienteInvalido9.setLogin("ricardoledo");
		clienteInvalido9.setSenha("gf155c44");
		clienteInvalido9.adicionaEndereco(endereco);
		clienteInvalido9.adicionaContato(contato);
		clientesInvalidos[8][0] = clienteInvalido9;
		clientesInvalidos[8][1] = "diaParaPagamentos";
		clientesInvalidos[8][2] = "Informe um dia do mês válido.";

		// Dia para pagamentos > 28
		Cliente clienteInvalido10 = new Cliente();
		clienteInvalido10.setNome("Ricardo Ledo de Tulio");
		clienteInvalido10.setCpf("374.707.968-78");
		clienteInvalido10.setRg("33.738.816-7");
		clienteInvalido10.setDataContrato(Calendar.getInstance());
		clienteInvalido10.setDiaParaPagamentos(29);
		clienteInvalido10.setLogin("ricardoledo");
		clienteInvalido10.setSenha("gf155c44");
		clienteInvalido10.adicionaEndereco(endereco);
		clienteInvalido10.adicionaContato(contato);
		clientesInvalidos[9][0] = clienteInvalido10;
		clientesInvalidos[9][1] = "diaParaPagamentos";
		clientesInvalidos[9][2] = "Informe um dia do mês válido.";

		// Login vazio
		Cliente clienteInvalido11 = new Cliente();
		clienteInvalido11.setNome("Ricardo Ledo de Tulio");
		clienteInvalido11.setCpf("374.707.968-78");
		clienteInvalido11.setRg("33.738.816-7");
		clienteInvalido11.setDataContrato(Calendar.getInstance());
		clienteInvalido11.setDiaParaPagamentos(26);
		clienteInvalido11.setLogin("");
		clienteInvalido11.setSenha("gf155c44");
		clienteInvalido11.adicionaEndereco(endereco);
		clienteInvalido11.adicionaContato(contato);
		clientesInvalidos[10][0] = clienteInvalido11;
		clientesInvalidos[10][1] = "login";
		clientesInvalidos[10][2] = "Este campo é obrigatório.";

		// Login < 5
		Cliente clienteInvalido12 = new Cliente();
		clienteInvalido12.setNome("Ricardo Ledo de Tulio");
		clienteInvalido12.setCpf("374.707.968-78");
		clienteInvalido12.setRg("33.738.816-7");
		clienteInvalido12.setDataContrato(Calendar.getInstance());
		clienteInvalido12.setDiaParaPagamentos(26);
		clienteInvalido12.setLogin("ricar");
		clienteInvalido12.setSenha("gf155c44");
		clienteInvalido12.adicionaEndereco(endereco);
		clienteInvalido12.adicionaContato(contato);
		clientesInvalidos[11][0] = clienteInvalido12;
		clientesInvalidos[11][1] = "login";
		clientesInvalidos[11][2] = "Este campo é pequeno demais. Ele deveria ter 6 caracteres ou mais.";

		// Login > 20
		Cliente clienteInvalido13 = new Cliente();
		clienteInvalido13.setNome("Ricardo Ledo de Tulio");
		clienteInvalido13.setCpf("374.707.968-78");
		clienteInvalido13.setRg("33.738.816-7");
		clienteInvalido13.setDataContrato(Calendar.getInstance());
		clienteInvalido13.setDiaParaPagamentos(26);
		clienteInvalido13.setLogin("ricardoledodetuliooli");
		clienteInvalido13.setSenha("gf155c44");
		clienteInvalido13.adicionaEndereco(endereco);
		clienteInvalido13.adicionaContato(contato);
		clientesInvalidos[12][0] = clienteInvalido13;
		clientesInvalidos[12][1] = "login";
		clientesInvalidos[12][2] = "Este campo é grande demais. Ele deveria ter 20 caracteres ou menos.";

		// Sem senha
		Cliente clienteInvalido14 = new Cliente();
		clienteInvalido14.setNome("Ricardo Ledo de Tulio");
		clienteInvalido14.setCpf("374.707.968-78");
		clienteInvalido14.setRg("33.738.816-7");
		clienteInvalido14.setDataContrato(Calendar.getInstance());
		clienteInvalido14.setDiaParaPagamentos(26);
		clienteInvalido14.setLogin("ricardoledo");
		clienteInvalido14.setSenha("");
		clienteInvalido14.adicionaEndereco(endereco);
		clienteInvalido14.adicionaContato(contato);
		clientesInvalidos[13][0] = clienteInvalido14;
		clientesInvalidos[13][1] = "senha";
		clientesInvalidos[13][2] = "Este campo é obrigatório.";

		// Senha < 5 caracteres
		Cliente clienteInvalido15 = new Cliente();
		clienteInvalido15.setNome("Ricardo Ledo de Tulio");
		clienteInvalido15.setCpf("374.707.968-78");
		clienteInvalido15.setRg("33.738.816-7");
		clienteInvalido15.setDataContrato(Calendar.getInstance());
		clienteInvalido15.setDiaParaPagamentos(26);
		clienteInvalido15.setLogin("ricardoledo");
		clienteInvalido15.setSenha("gf155");
		clienteInvalido15.adicionaEndereco(endereco);
		clienteInvalido15.adicionaContato(contato);
		clientesInvalidos[14][0] = clienteInvalido15;
		clientesInvalidos[14][1] = "senha";
		clientesInvalidos[14][2] = "Este campo é pequeno demais. Ele deveria ter 6 caracteres ou mais.";

		// Senha > 20
		Cliente clienteInvalido16 = new Cliente();
		clienteInvalido16.setNome("Ricardo Ledo de Tulio");
		clienteInvalido16.setCpf("374.707.968-78");
		clienteInvalido16.setRg("33.738.816-7");
		clienteInvalido16.setDataContrato(Calendar.getInstance());
		clienteInvalido16.setDiaParaPagamentos(26);
		clienteInvalido16.setLogin("ricardoledo");
		clienteInvalido16.setSenha("gf155c44fgdifkadfifkd");
		clienteInvalido16.adicionaEndereco(endereco);
		clienteInvalido16.adicionaContato(contato);
		clientesInvalidos[15][0] = clienteInvalido16;
		clientesInvalidos[15][1] = "senha";
		clientesInvalidos[15][2] = "Este campo é grande demais. Ele deveria ter 20 caracteres ou menos.";

		// Sem logradouro no endereço
		Cliente clienteInvalido17 = new Cliente();
		clienteInvalido17.setNome("Ricardo Ledo de Tulio");
		clienteInvalido17.setCpf("374.707.968-78");
		clienteInvalido17.setRg("33.738.816-7");
		clienteInvalido17.setDataContrato(Calendar.getInstance());
		clienteInvalido17.setDiaParaPagamentos(26);
		clienteInvalido17.setLogin("ricardoledo");
		clienteInvalido17.setSenha("gf155c44");

		Endereco enderecoInvalido1 = new Endereco();
		enderecoInvalido1.setLogradouro("");
		enderecoInvalido1.setNumero(200L);
		enderecoInvalido1.setBairro("Vila Isolina Mazzei");
		enderecoInvalido1.setCidade("São Paulo");
		enderecoInvalido1.setUf("sp");
		enderecoInvalido1.setCep("04235-025");

		clienteInvalido17.adicionaEndereco(enderecoInvalido1);
		clienteInvalido17.adicionaContato(contato);
		clientesInvalidos[16][0] = clienteInvalido17;
		clientesInvalidos[16][1] = "logradouro";
		clientesInvalidos[16][2] = "Este campo é obrigatório.";

		// Logradouro < 5
		Cliente clienteInvalido18 = new Cliente();
		clienteInvalido18.setNome("Ricardo Ledo de Tulio");
		clienteInvalido18.setCpf("374.707.968-78");
		clienteInvalido18.setRg("33.738.816-7");
		clienteInvalido18.setDataContrato(Calendar.getInstance());
		clienteInvalido18.setDiaParaPagamentos(26);
		clienteInvalido18.setLogin("ricardoledo");
		clienteInvalido18.setSenha("gf155c44");

		Endereco enderecoInvalido2 = new Endereco();
		enderecoInvalido2.setLogradouro("Ruab");
		enderecoInvalido2.setNumero(200L);
		enderecoInvalido2.setBairro("Vila Isolina Mazzei");
		enderecoInvalido2.setCidade("São Paulo");
		enderecoInvalido2.setUf("sp");
		enderecoInvalido2.setCep("04235-025");

		clienteInvalido18.adicionaEndereco(enderecoInvalido2);
		clienteInvalido18.adicionaContato(contato);
		clientesInvalidos[17][0] = clienteInvalido18;
		clientesInvalidos[17][1] = "logradouro";
		clientesInvalidos[17][2] = "Este campo é pequeno demais. Ele deveria ter 5 caracteres ou mais.";

		// Logradouro < 5
		Cliente clienteInvalido19 = new Cliente();
		clienteInvalido19.setNome("Ricardo Ledo de Tulio");
		clienteInvalido19.setCpf("374.707.968-78");
		clienteInvalido19.setRg("33.738.816-7");
		clienteInvalido19.setDataContrato(Calendar.getInstance());
		clienteInvalido19.setDiaParaPagamentos(26);
		clienteInvalido19.setLogin("ricardoledo");
		clienteInvalido19.setSenha("gf155c44");

		Endereco enderecoInvalido3 = new Endereco();
		enderecoInvalido3.setLogradouro(
				"Rua do fundo do quintal da parafuseta que está presa no topo do prédio onde eu moro que fica do lado da casa tal que fuil");
		enderecoInvalido3.setNumero(200L);
		enderecoInvalido3.setBairro("Vila Isolina Mazzei");
		enderecoInvalido3.setCidade("São Paulo");
		enderecoInvalido3.setUf("sp");
		enderecoInvalido3.setCep("04235-025");

		clienteInvalido19.adicionaEndereco(enderecoInvalido3);
		clienteInvalido19.adicionaContato(contato);
		clientesInvalidos[18][0] = clienteInvalido19;
		clientesInvalidos[18][1] = "logradouro";
		clientesInvalidos[18][2] = "Este campo é grande demais. Ele deveria ter 120 caracteres ou menos.";

		// Sem número no endereço
		Cliente clienteInvalido20 = new Cliente();
		clienteInvalido20.setNome("Ricardo Ledo de Tulio");
		clienteInvalido20.setCpf("374.707.968-78");
		clienteInvalido20.setRg("33.738.816-7");
		clienteInvalido20.setDataContrato(Calendar.getInstance());
		clienteInvalido20.setDiaParaPagamentos(26);
		clienteInvalido20.setLogin("ricardoledo");
		clienteInvalido20.setSenha("gf155c44");

		Endereco enderecoInvalido4 = new Endereco();
		enderecoInvalido4.setLogradouro("Rua Manajeru");
		enderecoInvalido4.setBairro("Vila Isolina Mazzei");
		enderecoInvalido4.setCidade("São Paulo");
		enderecoInvalido4.setUf("sp");
		enderecoInvalido4.setCep("04235-025");

		clienteInvalido20.adicionaEndereco(enderecoInvalido4);
		clienteInvalido20.adicionaContato(contato);
		clientesInvalidos[19][0] = clienteInvalido20;
		clientesInvalidos[19][1] = "numero";
		clientesInvalidos[19][2] = "Este campo é obrigatório.";

		// Complemento > 30
		Cliente clienteInvalido21 = new Cliente();
		clienteInvalido21.setNome("Ricardo Ledo de Tulio");
		clienteInvalido21.setCpf("374.707.968-78");
		clienteInvalido21.setRg("33.738.816-7");
		clienteInvalido21.setDataContrato(Calendar.getInstance());
		clienteInvalido21.setDiaParaPagamentos(26);
		clienteInvalido21.setLogin("ricardoledo");
		clienteInvalido21.setSenha("gf155c44");

		Endereco enderecoInvalido5 = new Endereco();
		enderecoInvalido5.setLogradouro("Rua Manajeru");
		enderecoInvalido5.setNumero(200L);
		enderecoInvalido5.setComplemento("Casa ao lado da casa que é roxa");
		enderecoInvalido5.setBairro("Vila Isolina Mazzei");
		enderecoInvalido5.setCidade("São Paulo");
		enderecoInvalido5.setUf("sp");
		enderecoInvalido5.setCep("04235-025");

		clienteInvalido21.adicionaEndereco(enderecoInvalido5);
		clienteInvalido21.adicionaContato(contato);
		clientesInvalidos[20][0] = clienteInvalido21;
		clientesInvalidos[20][1] = "complemento";
		clientesInvalidos[20][2] = "Este campo é grande demais. Ele deveria ter 30 caracteres ou menos.";

		// Sem bairro
		Cliente clienteInvalido22 = new Cliente();
		clienteInvalido22.setNome("Ricardo Ledo de Tulio");
		clienteInvalido22.setCpf("374.707.968-78");
		clienteInvalido22.setRg("33.738.816-7");
		clienteInvalido22.setDataContrato(Calendar.getInstance());
		clienteInvalido22.setDiaParaPagamentos(26);
		clienteInvalido22.setLogin("ricardoledo");
		clienteInvalido22.setSenha("gf155c44");

		Endereco enderecoInvalido6 = new Endereco();
		enderecoInvalido6.setLogradouro("Rua Manajeru");
		enderecoInvalido6.setNumero(200L);
		enderecoInvalido6.setComplemento("Casa C");
		enderecoInvalido6.setBairro("");
		enderecoInvalido6.setCidade("São Paulo");
		enderecoInvalido6.setUf("sp");
		enderecoInvalido6.setCep("04235-025");

		clienteInvalido22.adicionaEndereco(enderecoInvalido6);
		clienteInvalido22.adicionaContato(contato);
		clientesInvalidos[21][0] = clienteInvalido22;
		clientesInvalidos[21][1] = "bairro";
		clientesInvalidos[21][2] = "Este campo é obrigatório.";

		// Bairro < 5 caracteres
		Cliente clienteInvalido23 = new Cliente();
		clienteInvalido23.setNome("Ricardo Ledo de Tulio");
		clienteInvalido23.setCpf("374.707.968-78");
		clienteInvalido23.setRg("33.738.816-7");
		clienteInvalido23.setDataContrato(Calendar.getInstance());
		clienteInvalido23.setDiaParaPagamentos(26);
		clienteInvalido23.setLogin("ricardoledo");
		clienteInvalido23.setSenha("gf155c44");

		Endereco enderecoInvalido7 = new Endereco();
		enderecoInvalido7.setLogradouro("Rua Manajeru");
		enderecoInvalido7.setNumero(200L);
		enderecoInvalido7.setComplemento("Casa C");
		enderecoInvalido7.setBairro("bair");
		enderecoInvalido7.setCidade("São Paulo");
		enderecoInvalido7.setUf("sp");
		enderecoInvalido7.setCep("04235-025");

		clienteInvalido23.adicionaEndereco(enderecoInvalido7);
		clienteInvalido23.adicionaContato(contato);
		clientesInvalidos[22][0] = clienteInvalido23;
		clientesInvalidos[22][1] = "bairro";
		clientesInvalidos[22][2] = "Este campo é pequeno demais. Ele deveria ter 5 caracteres ou mais.";

		// Bairro > 60 caracteres
		Cliente clienteInvalido24 = new Cliente();
		clienteInvalido24.setNome("Ricardo Ledo de Tulio");
		clienteInvalido24.setCpf("374.707.968-78");
		clienteInvalido24.setRg("33.738.816-7");
		clienteInvalido24.setDataContrato(Calendar.getInstance());
		clienteInvalido24.setDiaParaPagamentos(26);
		clienteInvalido24.setLogin("ricardoledo");
		clienteInvalido24.setSenha("gf155c44");

		Endereco enderecoInvalido8 = new Endereco();
		enderecoInvalido8.setLogradouro("Rua Manajeru");
		enderecoInvalido8.setNumero(200L);
		enderecoInvalido8.setComplemento("Casa D");
		enderecoInvalido8.setBairro("Bairro muito loco de gente legal que gosta de fazer coisas le");
		enderecoInvalido8.setCidade("São Paulo");
		enderecoInvalido8.setUf("sp");
		enderecoInvalido8.setCep("04235-025");

		clienteInvalido24.adicionaEndereco(enderecoInvalido8);
		clienteInvalido24.adicionaContato(contato);
		clientesInvalidos[23][0] = clienteInvalido24;
		clientesInvalidos[23][1] = "bairro";
		clientesInvalidos[23][2] = "Este campo é grande demais. Ele deveria ter 60 caracteres ou menos.";

		// Sem informar cidade
		Cliente clienteInvalido25 = new Cliente();
		clienteInvalido25.setNome("Ricardo Ledo de Tulio");
		clienteInvalido25.setCpf("374.707.968-78");
		clienteInvalido25.setRg("33.738.816-7");
		clienteInvalido25.setDataContrato(Calendar.getInstance());
		clienteInvalido25.setDiaParaPagamentos(26);
		clienteInvalido25.setLogin("ricardoledo");
		clienteInvalido25.setSenha("gf155c44");

		Endereco enderecoInvalido9 = new Endereco();
		enderecoInvalido9.setLogradouro("Rua Manajeru");
		enderecoInvalido9.setNumero(200L);
		enderecoInvalido9.setComplemento("Casa D");
		enderecoInvalido9.setBairro("Vila Isolina Mazzei");
		enderecoInvalido9.setCidade("");
		enderecoInvalido9.setUf("sp");
		enderecoInvalido9.setCep("04235-025");

		clienteInvalido25.adicionaEndereco(enderecoInvalido9);
		clienteInvalido25.adicionaContato(contato);
		clientesInvalidos[24][0] = clienteInvalido25;
		clientesInvalidos[24][1] = "cidade";
		clientesInvalidos[24][2] = "Este campo é obrigatório.";

		// Cidade < 5 caracteres
		Cliente clienteInvalido26 = new Cliente();
		clienteInvalido26.setNome("Ricardo Ledo de Tulio");
		clienteInvalido26.setCpf("374.707.968-78");
		clienteInvalido26.setRg("33.738.816-7");
		clienteInvalido26.setDataContrato(Calendar.getInstance());
		clienteInvalido26.setDiaParaPagamentos(26);
		clienteInvalido26.setLogin("ricardoledo");
		clienteInvalido26.setSenha("gf155c44");

		Endereco enderecoInvalido10 = new Endereco();
		enderecoInvalido10.setLogradouro("Rua Manajeru");
		enderecoInvalido10.setNumero(200L);
		enderecoInvalido10.setComplemento("Casa D");
		enderecoInvalido10.setBairro("Vila Isolina Mazzei");
		enderecoInvalido10.setCidade("Cida");
		enderecoInvalido10.setUf("sp");
		enderecoInvalido10.setCep("04235-025");

		clienteInvalido26.adicionaEndereco(enderecoInvalido10);
		clienteInvalido26.adicionaContato(contato);
		clientesInvalidos[25][0] = clienteInvalido26;
		clientesInvalidos[25][1] = "cidade";
		clientesInvalidos[25][2] = "Este campo é pequeno demais. Ele deveria ter 5 caracteres ou mais.";

		// Cidade > 60 caracteres
		Cliente clienteInvalido27 = new Cliente();
		clienteInvalido27.setNome("Ricardo Ledo de Tulio");
		clienteInvalido27.setCpf("374.707.968-78");
		clienteInvalido27.setRg("33.738.816-7");
		clienteInvalido27.setDataContrato(Calendar.getInstance());
		clienteInvalido27.setDiaParaPagamentos(26);
		clienteInvalido27.setLogin("ricardoledo");
		clienteInvalido27.setSenha("gf155c44");

		Endereco enderecoInvalido11 = new Endereco();
		enderecoInvalido11.setLogradouro("Rua Manajeru");
		enderecoInvalido11.setNumero(200L);
		enderecoInvalido11.setComplemento("Casa D");
		enderecoInvalido11.setBairro("Vila Isolina Mazzei");
		enderecoInvalido11.setCidade("Cidade muito loco de gente legal que gosta de fazer coisas le");
		enderecoInvalido11.setUf("sp");
		enderecoInvalido11.setCep("04235-025");

		clienteInvalido27.adicionaEndereco(enderecoInvalido11);
		clienteInvalido27.adicionaContato(contato);
		clientesInvalidos[26][0] = clienteInvalido27;
		clientesInvalidos[26][1] = "cidade";
		clientesInvalidos[26][2] = "Este campo é grande demais. Ele deveria ter 60 caracteres ou menos.";

		// Sem informar CEP
		Cliente clienteInvalido28 = new Cliente();
		clienteInvalido28.setNome("Ricardo Ledo de Tulio");
		clienteInvalido28.setCpf("374.707.968-78");
		clienteInvalido28.setRg("33.738.816-7");
		clienteInvalido28.setDataContrato(Calendar.getInstance());
		clienteInvalido28.setDiaParaPagamentos(26);
		clienteInvalido28.setLogin("ricardoledo");
		clienteInvalido28.setSenha("gf155c44");

		Endereco enderecoInvalido12 = new Endereco();
		enderecoInvalido12.setLogradouro("Rua Manajeru");
		enderecoInvalido12.setNumero(200L);
		enderecoInvalido12.setComplemento("Casa D");
		enderecoInvalido12.setBairro("Vila Isolina Mazzei");
		enderecoInvalido12.setCidade("São Paulo");
		enderecoInvalido12.setUf("sp");
		enderecoInvalido12.setCep("");

		clienteInvalido28.adicionaEndereco(enderecoInvalido12);
		clienteInvalido28.adicionaContato(contato);
		clientesInvalidos[27][0] = clienteInvalido28;
		clientesInvalidos[27][1] = "cep";
		clientesInvalidos[27][2] = "Este campo é obrigatório.";

		// CEP Inválido
		Cliente clienteInvalido29 = new Cliente();
		clienteInvalido29.setNome("Ricardo Ledo de Tulio");
		clienteInvalido29.setCpf("374.707.968-78");
		clienteInvalido29.setRg("33.738.816-7");
		clienteInvalido29.setDataContrato(Calendar.getInstance());
		clienteInvalido29.setDiaParaPagamentos(26);
		clienteInvalido29.setLogin("ricardoledo");
		clienteInvalido29.setSenha("gf155c44");

		Endereco enderecoInvalido13 = new Endereco();
		enderecoInvalido13.setLogradouro("Rua Manajeru");
		enderecoInvalido13.setNumero(200L);
		enderecoInvalido13.setComplemento("Casa D");
		enderecoInvalido13.setBairro("Vila Isolina Mazzei");
		enderecoInvalido13.setCidade("São Paulo");
		enderecoInvalido13.setUf("sp");
		enderecoInvalido13.setCep("013456");

		clienteInvalido29.adicionaEndereco(enderecoInvalido13);
		clienteInvalido29.adicionaContato(contato);
		clientesInvalidos[28][0] = clienteInvalido29;
		clientesInvalidos[28][1] = "cep";
		clientesInvalidos[28][2] = "Este valor parece ser inválido.";

		// Telefone inválido
		Cliente clienteInvalido30 = new Cliente();
		clienteInvalido30.setNome("Ricardo Ledo de Tulio");
		clienteInvalido30.setCpf("374.707.968-78");
		clienteInvalido30.setRg("33.738.816-7");
		clienteInvalido30.setDataContrato(Calendar.getInstance());
		clienteInvalido30.setDiaParaPagamentos(26);
		clienteInvalido30.setLogin("ricardoledo");
		clienteInvalido30.setSenha("gf155c44");

		Contato contatoInvalido1 = new Contato();
		contatoInvalido1.setTelefone("(11) 930493");
		contatoInvalido1.setCelular("(11) 95419-3524");
		contatoInvalido1.setEmail("ricardo.tulio@fatec.sp.gov.br");

		clienteInvalido30.adicionaEndereco(endereco);
		clienteInvalido30.adicionaContato(contatoInvalido1);
		clientesInvalidos[29][0] = clienteInvalido30;
		clientesInvalidos[29][1] = "telefone";
		clientesInvalidos[29][2] = "Este valor parece ser inválido.";

		// Celular inválido
		Cliente clienteInvalido31 = new Cliente();
		clienteInvalido31.setNome("Ricardo Ledo de Tulio");
		clienteInvalido31.setCpf("374.707.968-78");
		clienteInvalido31.setRg("33.738.816-7");
		clienteInvalido31.setDataContrato(Calendar.getInstance());
		clienteInvalido31.setDiaParaPagamentos(26);
		clienteInvalido31.setLogin("ricardoledo");
		clienteInvalido31.setSenha("gf155c44");

		Contato contatoInvalido2 = new Contato();
		contatoInvalido2.setTelefone("(11) 2914-2275");
		contatoInvalido2.setCelular("(11) 930493");
		contatoInvalido2.setEmail("ricardo.tulio@fatec.sp.gov.br");
		;

		clienteInvalido31.adicionaEndereco(endereco);
		clienteInvalido31.adicionaContato(contatoInvalido2);
		clientesInvalidos[30][0] = clienteInvalido31;
		clientesInvalidos[30][1] = "celular";
		clientesInvalidos[30][2] = "Este valor parece ser inválido.";

		// Sem e-mail
		Cliente clienteInvalido32 = new Cliente();
		clienteInvalido32.setNome("Ricardo Ledo de Tulio");
		clienteInvalido32.setCpf("374.707.968-78");
		clienteInvalido32.setRg("33.738.816-7");
		clienteInvalido32.setDataContrato(Calendar.getInstance());
		clienteInvalido32.setDiaParaPagamentos(26);
		clienteInvalido32.setLogin("ricardoledo");
		clienteInvalido32.setSenha("gf155c44");

		Contato contatoInvalido3 = new Contato();
		contatoInvalido3.setTelefone("(11) 2914-2275");
		contatoInvalido3.setCelular("(11) 95419-3524");
		contatoInvalido3.setEmail("");

		clienteInvalido32.adicionaEndereco(endereco);
		clienteInvalido32.adicionaContato(contatoInvalido3);
		clientesInvalidos[31][0] = clienteInvalido32;
		clientesInvalidos[31][1] = "email";
		clientesInvalidos[31][2] = "Este campo é obrigatório.";

		// E-Mail inválido
		Cliente clienteInvalido33 = new Cliente();
		clienteInvalido33.setNome("Ricardo Ledo de Tulio");
		clienteInvalido33.setCpf("374.707.968-78");
		clienteInvalido33.setRg("33.738.816-7");
		clienteInvalido33.setDataContrato(Calendar.getInstance());
		clienteInvalido33.setDiaParaPagamentos(26);
		clienteInvalido33.setLogin("ricardoledo");
		clienteInvalido33.setSenha("gf155c44");

		Contato contatoInvalido4 = new Contato();
		contatoInvalido4.setTelefone("(11) 2914-2275");
		contatoInvalido4.setCelular("(11) 95419-3524");
		contatoInvalido4.setEmail("ricarodleod@teste");

		clienteInvalido33.adicionaEndereco(endereco);
		clienteInvalido33.adicionaContato(contatoInvalido4);
		clientesInvalidos[32][0] = clienteInvalido33;
		clientesInvalidos[32][1] = "email";
		clientesInvalidos[32][2] = "Este campo deve ser um email válido.";

		// CEP Inválido
		Cliente clienteInvalido34 = new Cliente();
		clienteInvalido34.setNome("Ricardo Ledo de Tulio");
		clienteInvalido34.setCpf("374.707.968-78");
		clienteInvalido34.setRg("33.738.816-7");
		clienteInvalido34.setDataContrato(Calendar.getInstance());
		clienteInvalido34.setDiaParaPagamentos(26);
		clienteInvalido34.setLogin("ricardoledo");
		clienteInvalido34.setSenha("gf155c44");

		Endereco enderecoInvalido14 = new Endereco();
		enderecoInvalido14.setLogradouro("Rua Manajeru");
		enderecoInvalido14.setNumero(200L);
		enderecoInvalido14.setComplemento("Casa D");
		enderecoInvalido14.setBairro("Vila Isolina Mazzei");
		enderecoInvalido14.setCidade("São Paulo");
		enderecoInvalido14.setCep("04235-025");

		clienteInvalido34.adicionaEndereco(enderecoInvalido14);
		clienteInvalido34.adicionaContato(contato);
		clientesInvalidos[33][0] = clienteInvalido34;
		clientesInvalidos[33][1] = "uf";
		clientesInvalidos[33][2] = "Este campo é obrigatório.";

		return Arrays.asList(clientesInvalidos);
	}

}
