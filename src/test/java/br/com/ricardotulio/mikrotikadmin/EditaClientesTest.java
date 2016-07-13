package br.com.ricardotulio.mikrotikadmin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
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

		Object[][] parametros = new Object[clientesInvalidos.size() + clientesValidos.size() + 2][5];

		int contadorParametros = 0;

		Iterator<Object[]> iteratorClientesValidos = clientesValidos.iterator();

		while (iteratorClientesValidos.hasNext()) {
			Object[] dadosClienteValido = iteratorClientesValidos.next();

			Cliente clienteValido = (Cliente) dadosClienteValido[0];
			parametros[contadorParametros][0] = clienteValido;
			parametros[contadorParametros][1] = "clientes/";
			parametros[contadorParametros][2] = true;
			parametros[contadorParametros][3] = "";
			parametros[contadorParametros][4] = "Cliente cadastrado com sucesso!";
			contadorParametros++;
		}

		Cliente copia1 = (Cliente) parametros[contadorParametros - 1][0];
		Cliente clienteComLoginDuplicado = new Cliente();
		clienteComLoginDuplicado.setNome(copia1.getNome());
		clienteComLoginDuplicado.setCpf("583.797.209-97");
		clienteComLoginDuplicado.setLogin(copia1.getLogin());
		clienteComLoginDuplicado.setSenha(copia1.getSenha());
		clienteComLoginDuplicado.setRg(copia1.getRg());
		clienteComLoginDuplicado.setDataContrato(copia1.getDataContrato());
		clienteComLoginDuplicado.setDiaParaPagamentos(copia1.getDiaParaPagamentos());
		clienteComLoginDuplicado.adicionaEndereco(copia1.getEnderecos().iterator().next());
		clienteComLoginDuplicado.adicionaContato(copia1.getContatos().iterator().next());

		parametros[contadorParametros][0] = clienteComLoginDuplicado;
		parametros[contadorParametros][1] = "clientes/cadastrar";
		parametros[contadorParametros][2] = false;
		parametros[contadorParametros][3] = "login";
		parametros[contadorParametros][4] = "Já existe um cliente com este login.";
		contadorParametros++;

		Cliente copia2 = (Cliente) parametros[contadorParametros - 2][0];
		Cliente clienteComCpfDuplicado = new Cliente();
		clienteComCpfDuplicado.setNome("Novo Nome do Usuário");
		clienteComCpfDuplicado.setCpf(copia2.getCpf());
		clienteComCpfDuplicado.setLogin("tulioabcd");
		clienteComCpfDuplicado.setSenha(copia2.getSenha());
		clienteComCpfDuplicado.setRg(copia2.getRg());
		clienteComCpfDuplicado.setDataContrato(copia2.getDataContrato());
		clienteComCpfDuplicado.setDiaParaPagamentos(copia2.getDiaParaPagamentos());
		clienteComCpfDuplicado.adicionaEndereco(copia2.getEnderecos().iterator().next());
		clienteComCpfDuplicado.adicionaContato(copia2.getContatos().iterator().next());

		parametros[contadorParametros][0] = clienteComCpfDuplicado;
		parametros[contadorParametros][1] = "clientes/cadastrar";
		parametros[contadorParametros][2] = false;
		parametros[contadorParametros][3] = "cpf";
		parametros[contadorParametros][4] = "Já existe um cliente com este CPF.";
		contadorParametros++;

		Iterator<Object[]> iteratorClientesInvalidos = clientesInvalidos.iterator();

		while (iteratorClientesInvalidos.hasNext()) {
			Object[] dadosClienteInvalido = iteratorClientesInvalidos.next();

			Cliente clienteInvalido = (Cliente) dadosClienteInvalido[0];
			parametros[contadorParametros][0] = clienteInvalido;
			parametros[contadorParametros][1] = "clientes/cadastrar";
			parametros[contadorParametros][2] = false;
			parametros[contadorParametros][3] = dadosClienteInvalido[1];
			parametros[contadorParametros][4] = dadosClienteInvalido[2];

			contadorParametros++;
		}

		return Arrays.asList(parametros);
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

			driver.findElement(By.name("nome")).sendKeys(this.cliente.getNome());

			new Select(driver.findElement(By.name("planoId"))).selectByIndex(1);

			if (this.cliente.getDiaParaPagamentos() != null) {
				String diaParaPagamentos = this.cliente.getDiaParaPagamentos().toString();
				driver.findElement(By.name("diaParaPagamentos")).sendKeys(diaParaPagamentos);
			}

			driver.findElement(By.name("login")).sendKeys(this.cliente.getLogin());
			driver.findElement(By.name("senha")).sendKeys(this.cliente.getSenha());
			driver.findElement(By.name("confirmarSenha")).sendKeys(this.cliente.getSenha());
			driver.findElement(By.name("telefone")).sendKeys(contato.getTelefone());
			driver.findElement(By.name("celular")).sendKeys(contato.getCelular());
			driver.findElement(By.name("email")).sendKeys(contato.getEmail());
			driver.findElement(By.name("logradouro")).sendKeys(endereco.getLogradouro());

			if (endereco.getNumero() != null) {
				String numero = endereco.getNumero().toString();
				driver.findElement(By.name("numero")).sendKeys(numero);
			}

			driver.findElement(By.name("complemento")).sendKeys(endereco.getComplemento());
			driver.findElement(By.name("bairro")).sendKeys(endereco.getBairro());
			driver.findElement(By.name("cidade")).sendKeys(endereco.getCidade());

			if (endereco.getUf() != null) {
				new Select(driver.findElement(By.name("uf"))).selectByValue(endereco.getUf());
			}

			driver.findElement(By.name("cep")).sendKeys(endereco.getCep());
			driver.findElement(By.id("btn-salvar")).click();

			assertEquals(this.urlEsperadaAposSubmeterFormulario, driver.getCurrentUrl());

			if (ehUmClienteValido) {
				assertEquals("Cliente cadastrado com sucesso!",
						driver.findElement(By.className("alert-success")).getText());
			} else {
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
