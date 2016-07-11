package br.com.ricardotulio.mikrotikadmin;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Contato;
import br.com.ricardotulio.mikrotikadmin.model.Endereco;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

@RunWith(Parameterized.class)
public class ClientesTest extends AceitacaoTest {

	private Cliente cliente;
	private Endereco endereco;
	private Contato contato;
	private Plano plano;

	public ClientesTest(Cliente cliente, Endereco endereco, Contato contato, Plano plano) {
		this.cliente = cliente;
		this.endereco = endereco;
		this.contato = contato;
		this.plano = plano;
	}

	@Parameters
	public static Collection<Object[]> parametros() {
		Object[][] parametros = new Object[1][4];

		Cliente cliente = new Cliente();
		cliente.setNome("Leandro Ledo de Tulio");
		cliente.setCpf("374.707.968-78");
		cliente.setRg("33.738.816-7");
		cliente.setDataContrato(Calendar.getInstance());
		cliente.setDiaParaPagamentos(5);
		cliente.setLogin("leandroledo");
		cliente.setSenha("123456");

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua do Pacificador");
		endereco.setNumero(251L);
		endereco.setBairro("Heliópolis");
		endereco.setCidade("São Paulo");
		endereco.setUf("SP");
		endereco.setCep("04235-025");

		Contato contato = new Contato();
		contato.setTelefone("11954193524");
		contato.setCelular("119342934");
		contato.setEmail("ricardo.tulio@fatec.sp.gov.br");

		Plano plano = new Plano();
		plano.setId(109L);

		parametros[0][0] = cliente;
		parametros[0][1] = endereco;
		parametros[0][2] = contato;
		parametros[0][3] = plano;

		return Arrays.asList(parametros);
	}

	@Test
	public void testaSeCadastraClienteValido() {		
		driver.navigate().to(baseUrl + "clientes/cadastrar");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

		driver.findElement(By.name("nome")).sendKeys(cliente.getNome());
		driver.findElement(By.name("cpf")).sendKeys("374.707.968-78");
		driver.findElement(By.name("rg")).sendKeys("33.738.816-7");
		driver.findElement(By.name("dataContrato")).sendKeys(sdf.format(cliente.getDataContrato().getTime()));
		new Select(driver.findElement(By.name("planoId"))).selectByValue(plano.getId().toString());
		driver.findElement(By.name("diaParaPagamentos")).sendKeys(cliente.getDiaParaPagamentos().toString());
		driver.findElement(By.name("login")).sendKeys(cliente.getLogin());
		driver.findElement(By.name("senha")).sendKeys("123456");
		driver.findElement(By.name("confirmarSenha")).sendKeys("123456");
		driver.findElement(By.name("telefone")).sendKeys(contato.getTelefone());
		driver.findElement(By.name("celular")).sendKeys(contato.getCelular());
		driver.findElement(By.name("email")).sendKeys(contato.getEmail());
		driver.findElement(By.name("logradouro")).sendKeys(endereco.getLogradouro());
		driver.findElement(By.name("numero")).sendKeys(endereco.getNumero().toString());
		driver.findElement(By.name("complemento")).sendKeys(endereco.getComplemento());
		driver.findElement(By.name("bairro")).sendKeys(endereco.getBairro());
		driver.findElement(By.name("cidade")).sendKeys(endereco.getCidade());
		driver.findElement(By.name("uf")).sendKeys(endereco.getUf());
		driver.findElement(By.name("cep")).sendKeys(endereco.getCep());
		driver.findElement(By.id("btn-salvar")).click();

		assertEquals(baseUrl + "clientes/", driver.getCurrentUrl());
		assertEquals("Cliente cadastrado com sucesso!", driver.findElement(By.className("alert-success")).getText());

		driver.close();
	}

}
