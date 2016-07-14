package br.com.ricardotulio.mikrotikadmin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.ricardotulio.mikrotikadmin.model.Cliente;
import br.com.ricardotulio.mikrotikadmin.model.Contato;
import br.com.ricardotulio.mikrotikadmin.model.Endereco;

@RunWith(Parameterized.class)
public class EditaClientesTest extends AceitacaoTest {

	private Cliente cliente;
	private String urlEsperadaAposSubmeterFormulario;
	private boolean ehUmClienteValido;
	private String campoInvalido;
	private String mensagemEsperadaRetorno;

	public EditaClientesTest(Cliente cliente, String urlEsperadaAposSubmeterFormulario, boolean ehUmClienteValido,
			String campoInvalido, String mensagemEsperadaRetorno) {
		this.cliente = cliente;
		this.urlEsperadaAposSubmeterFormulario = baseUrl + urlEsperadaAposSubmeterFormulario;
		this.ehUmClienteValido = ehUmClienteValido;
		this.campoInvalido = campoInvalido;
		this.mensagemEsperadaRetorno = mensagemEsperadaRetorno;
	}

	@Parameters
	public static Collection<Object[]> parametros() {
		Collection<Object[]> clientesValidos = ClienteDataFactory.obtemClientesValidos();
		Collection<Object[]> clientesInvalidos = ClienteDataFactory.obtemClientesInvalidos();

		List<Object[]> parametros = new ArrayList<Object[]>();

		Iterator<Object[]> iteratorClientesValidos = clientesValidos.iterator();

		while (iteratorClientesValidos.hasNext()) {
			Object[] dadosClienteValido = iteratorClientesValidos.next();
			Object[] parametro = new Object[5];

			Cliente clienteValido = (Cliente) dadosClienteValido[0];
			parametro[0] = clienteValido;
			parametro[1] = "clientes/";
			parametro[2] = true;
			parametro[3] = "";
			parametro[4] = "Cliente atualizado com sucesso!";
			parametros.add(parametro);
		}

		Iterator<Object[]> iteratorClientesInvalidos = clientesInvalidos.iterator();

		while (iteratorClientesInvalidos.hasNext()) {
			Object[] dadosClienteInvalido = iteratorClientesInvalidos.next();

			if (dadosClienteInvalido[1].equals("cpf") || dadosClienteInvalido[1].equals("rg")
					|| dadosClienteInvalido[1].equals("login") || dadosClienteInvalido[1].equals("dataContrato"))
				continue;

			Object[] parametro = new Object[5];

			Cliente clienteInvalido = (Cliente) dadosClienteInvalido[0];
			parametro[0] = clienteInvalido;
			parametro[1] = "clientes/editar/";
			parametro[2] = false;
			parametro[3] = dadosClienteInvalido[1];
			parametro[4] = dadosClienteInvalido[2];
			parametros.add(parametro);
		}

		return parametros;
	}

	@Test
	public void testaSeCadastraCliente() {
		driver.navigate().to(baseUrl + "clientes/");
		List<WebElement> elements = driver.findElements(By.cssSelector("table tbody tr"));

		String[] idsClientes = new String[elements.size()];

		for (int i = 0; i < elements.size(); i++) {
			idsClientes[i] = elements.get(i).getAttribute("data-id");
		}

		for (String idCliente : idsClientes) {
			WebElement element = driver.findElement(By.cssSelector("table tbody tr[data-id='" + idCliente + "']"));
			element.findElement(By.cssSelector(".btn-editar")).click();
			assertTrue(driver.getCurrentUrl().contains(baseUrl + "clientes/editar/" + idCliente));

			Endereco endereco = this.cliente.getEnderecos().iterator().next();
			Contato contato = this.cliente.getContatos().iterator().next();

			WebElement nome = driver.findElement(By.name("nome"));
			nome.clear();
			nome.sendKeys(this.cliente.getNome());

			new Select(driver.findElement(By.name("planoId"))).selectByIndex(1);

			if (this.cliente.getDiaParaPagamentos() != null) {
				WebElement diaParaPagamentos = driver.findElement(By.name("diaParaPagamentos"));
				diaParaPagamentos.clear();
				diaParaPagamentos.sendKeys(this.cliente.getDiaParaPagamentos().toString());
			}

			WebElement senha = driver.findElement(By.name("senha"));
			senha.clear();
			senha.sendKeys(this.cliente.getSenha());

			WebElement confirmarSenha = driver.findElement(By.name("confirmarSenha"));
			confirmarSenha.clear();
			confirmarSenha.sendKeys(this.cliente.getSenha());

			WebElement telefone = driver.findElement(By.name("telefone"));
			telefone.clear();
			telefone.sendKeys(contato.getTelefone());
			
			WebElement celular = driver.findElement(By.name("celular"));
			celular.clear();
			celular.sendKeys(contato.getCelular());
			
			WebElement email = driver.findElement(By.name("email"));
			email.clear();
			email.sendKeys(contato.getEmail());
			
			
			WebElement logradouro = driver.findElement(By.name("logradouro"));
			logradouro.clear();
			logradouro.sendKeys(endereco.getLogradouro());

			if (endereco.getNumero() != null) {
				WebElement numero = driver.findElement(By.name("numero"));
				numero.clear();
				numero.sendKeys(endereco.getNumero().toString());
			}

			WebElement complemento = driver.findElement(By.name("complemento"));
			complemento.clear();
			complemento.sendKeys(endereco.getComplemento());
			
			WebElement bairro = driver.findElement(By.name("bairro"));
			bairro.clear();
			bairro.sendKeys(endereco.getBairro());
			
			WebElement cidade = driver.findElement(By.name("cidade"));
			cidade.clear();
			cidade.sendKeys(endereco.getCidade());
			
			if (endereco.getUf() != null) {
				new Select(driver.findElement(By.name("uf"))).selectByValue(endereco.getUf());
			}

			WebElement cep = driver.findElement(By.name("cep"));
			cep.clear();
			cep.sendKeys(endereco.getCep());
			
			driver.findElement(By.id("btn-salvar")).click();


			if (ehUmClienteValido) {
				assertEquals(this.urlEsperadaAposSubmeterFormulario, driver.getCurrentUrl());
				assertEquals("Cliente atualizado com sucesso!",
						driver.findElement(By.className("alert-success")).getText());
			} else {
				assertEquals(this.urlEsperadaAposSubmeterFormulario + idCliente, driver.getCurrentUrl());
				if (this.cliente.getSenha().equals("")) {
					assertEquals(2, driver.findElements(By.cssSelector("ul.parsley-errors-list")).size());
				} else {
					assertEquals(1, driver.findElements(By.cssSelector("ul.parsley-errors-list")).size());
				}

				String mensagemErro = driver.findElement(By.id(this.campoInvalido)).findElement(By.xpath(".."))
						.findElement(By.cssSelector("ul.parsley-errors-list li")).getText();

				assertTrue(
						driver.findElement(By.id(this.campoInvalido)).getAttribute("class").contains("parsley-error"));
				assertEquals(this.mensagemEsperadaRetorno, mensagemErro);
			}
		}
	}
}
