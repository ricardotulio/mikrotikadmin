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

import br.com.ricardotulio.mikrotikadmin.model.Plano;

@RunWith(Parameterized.class)
public class EditaPlanosTest extends AceitacaoTest {

	private Plano plano;
	private String urlEsperadaAposSubmeterFormulario;
	private String mensagemEsperadaRetorno;
	private boolean ehUmPlanoValido;
	private String campoInvalido;

	public EditaPlanosTest(Plano plano, String urlEsperadaAposSubmeterFormulario, boolean ehUmPlanoValido,
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

		List<Object[]> parametros = new ArrayList<Object[]>();

		Iterator<Object[]> iteradorPlanosValidos = planosValidos.iterator();

		while (iteradorPlanosValidos.hasNext()) {
			Object[] dadosPlanoValido = iteradorPlanosValidos.next();
			Plano planoValido = (Plano) dadosPlanoValido[0];
			
			Object[] parametro = new Object[5];

			parametro[0] = planoValido;
			parametro[1] = "planos/";
			parametro[2] = true;
			parametro[3] = "";
			parametro[4] = "Plano atualizado com sucesso!";
			parametros.add(parametro);
		}

		Iterator<Object[]> iteradorPlanosInvalidos = planosInvalidos.iterator();

		while (iteradorPlanosInvalidos.hasNext()) {
			Object[] dadosPlanoInvalido = iteradorPlanosInvalidos.next();
			Plano planoInvalido = (Plano) dadosPlanoInvalido[0];

			if (dadosPlanoInvalido[1].equals("titulo"))
				continue;

			Object[] parametro = new Object[5];
			
			parametro[0] = planoInvalido;
			parametro[1] = "planos/cadastrar";
			parametro[2] = false;
			parametro[3] = dadosPlanoInvalido[1];
			parametro[4] = dadosPlanoInvalido[2];
			parametros.add(parametro);
		}

		return parametros;
	}

	@Test
	public void testaSeAtualizaPlano() {
		driver.navigate().to(baseUrl + "planos/");
		List<WebElement> elements = driver.findElements(By.cssSelector("table tbody tr"));

		String[] idsPlanos = new String[elements.size()];

		for (int i = 0; i < elements.size(); i++) {
			idsPlanos[i] = elements.get(i).getAttribute("data-id");
		}

		for (String idPlano : idsPlanos) {
			WebElement element = driver.findElement(By.cssSelector("table tbody tr[data-id='" + idPlano + "']"));
			element.findElement(By.cssSelector(".btn-editar")).click();
			assertTrue(driver.getCurrentUrl().contains(baseUrl + "planos/editar/" + idPlano));

			WebElement descricao = driver.findElement(By.name("descricao"));
			descricao.clear();
			descricao.sendKeys(plano.getDescricao());

			WebElement taxaDownload = driver.findElement(By.name("taxaDownload"));
			taxaDownload.clear();
			taxaDownload.sendKeys(transformaDoubleEmString(plano.getTaxaDownload()));

			WebElement taxaUpload = driver.findElement(By.name("taxaUpload"));
			taxaUpload.clear();
			taxaUpload.sendKeys(transformaDoubleEmString(plano.getTaxaUpload()));

			WebElement valorMensal = driver.findElement(By.name("valorMensal"));
			valorMensal.clear();
			valorMensal.sendKeys(transformaDoubleEmString(plano.getValorMensal()));

			driver.findElement(By.id("btn-salvar")).click();

			if (this.ehUmPlanoValido) {
				assertEquals(this.urlEsperadaAposSubmeterFormulario, driver.getCurrentUrl());
				assertEquals(this.mensagemEsperadaRetorno, driver.findElement(By.className("alert-success")).getText());
			} else {
				String mensagemErro = driver.findElement(By.id(this.campoInvalido)).findElement(By.xpath(".."))
						.findElement(By.cssSelector("ul.parsley-errors-list li")).getText();

				assertEquals(baseUrl + "planos/editar/" + idPlano, driver.getCurrentUrl());
				assertTrue(
						driver.findElement(By.id(this.campoInvalido)).getAttribute("class").contains("parsley-error"));
				assertEquals(this.mensagemEsperadaRetorno, mensagemErro);
			}

			driver.navigate().to(baseUrl + "planos/");
		}
	}

}
