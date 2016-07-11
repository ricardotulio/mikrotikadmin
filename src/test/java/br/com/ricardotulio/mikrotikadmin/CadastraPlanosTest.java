package br.com.ricardotulio.mikrotikadmin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;

import br.com.ricardotulio.mikrotikadmin.model.Plano;

@RunWith(Parameterized.class)
public class CadastraPlanosTest extends AceitacaoTest {

	private Plano plano;
	private String urlEsperadaAposSubmeterFormulario;
	private String mensagemEsperadaRetorno;
	private boolean ehUmPlanoValido;
	private String campoInvalido;

	public CadastraPlanosTest(Plano plano, String urlEsperadaAposSubmeterFormulario, boolean ehUmPlanoValido,
			String campoInvalido, String mensagemEsperadaRetorno) {
		this.plano = plano;
		this.urlEsperadaAposSubmeterFormulario = baseUrl + urlEsperadaAposSubmeterFormulario;
		this.ehUmPlanoValido = ehUmPlanoValido;
		this.mensagemEsperadaRetorno = mensagemEsperadaRetorno;
		this.campoInvalido = campoInvalido;
	}

	@Parameters
	public static Collection<Object[]> parametros() {
		Collection<Object[]> planosValidos = PlanoDataFactory.obtemPlanosValidos();
		Collection<Object[]> planosInvalidos = PlanoDataFactory.obtemPlanosInvalidos();

		Object[][] parametros = new Object[planosValidos.size() + planosInvalidos.size() + 1][5];

		Iterator<Object[]> iteradorPlanosValidos = planosValidos.iterator();
		int contadorParametros = 0;

		while (iteradorPlanosValidos.hasNext()) {
			Object[] dadosPlanoValido = iteradorPlanosValidos.next();
			Plano planoValido = (Plano) dadosPlanoValido[0];

			parametros[contadorParametros][0] = planoValido;
			parametros[contadorParametros][1] = "planos/";
			parametros[contadorParametros][2] = true;
			parametros[contadorParametros][3] = "";
			parametros[contadorParametros][4] = "Plano cadastrado com sucesso!";
			contadorParametros++;
		}
		
		// Testa cadastro de plano com título duplicado
		parametros[contadorParametros][0] = (Plano) parametros[contadorParametros - 1][0];
		parametros[contadorParametros][1] = "planos/cadastrar";
		parametros[contadorParametros][2] = false;
		parametros[contadorParametros][3] = "titulo";
		parametros[contadorParametros][4] = "Já existe um plano com este título. Por favor, informe outro título.";
		contadorParametros++;

		Iterator<Object[]> iteradorPlanosInvalidos = planosInvalidos.iterator();

		while (iteradorPlanosInvalidos.hasNext()) {
			Object[] dadosPlanoInvalido = iteradorPlanosInvalidos.next();
			Plano planoInvalido = (Plano) dadosPlanoInvalido[0];

			parametros[contadorParametros][0] = planoInvalido;
			parametros[contadorParametros][1] = "planos/cadastrar";
			parametros[contadorParametros][2] = false;
			parametros[contadorParametros][3] = dadosPlanoInvalido[1];
			parametros[contadorParametros][4] = dadosPlanoInvalido[2];

			contadorParametros++;
		}

		return Arrays.asList(parametros);
	}

	@Test
	public void testaCadastroDePlanos() {
		driver.navigate().to(baseUrl + "planos/cadastrar");
		driver.findElement(By.name("titulo")).sendKeys(plano.getTitulo());
		driver.findElement(By.name("descricao")).sendKeys(plano.getDescricao());
		driver.findElement(By.name("taxaDownload")).sendKeys(transformaDoubleEmString(plano.getTaxaDownload()));
		driver.findElement(By.name("taxaUpload")).sendKeys(transformaDoubleEmString(plano.getTaxaUpload()));
		driver.findElement(By.name("valorMensal")).sendKeys(transformaDoubleEmString(plano.getValorMensal()));
		driver.findElement(By.id("btn-salvar")).click();

		assertEquals(this.urlEsperadaAposSubmeterFormulario, driver.getCurrentUrl());

		if (this.ehUmPlanoValido) {
			assertEquals(this.mensagemEsperadaRetorno, driver.findElement(By.className("alert-success")).getText());
		} else {
			String mensagemErro = driver.findElement(By.id(this.campoInvalido)).findElement(By.xpath(".."))
					.findElement(By.cssSelector("ul.parsley-errors-list li")).getText();

			assertTrue(driver.findElement(By.id(this.campoInvalido)).getAttribute("class").contains("parsley-error"));
			assertEquals(this.mensagemEsperadaRetorno, mensagemErro);
		}
	}

}
