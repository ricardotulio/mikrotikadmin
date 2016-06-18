package br.com.ricardotulio.mikrotikadmin.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ricardotulio.mikrotikadmin.dao.PlanoDataFactory;
import br.com.ricardotulio.mikrotikadmin.model.Plano;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/WEB-INF/spring_test.xml")
public class PlanoControllerTest {

	@PersistenceContext
	private EntityManager entityManager;

	private WebDriver driver;
	private String baseUrl;

	private EntityManager obtemEntityManager() {
		return this.entityManager;
	}

	@Before
	public void setUp() {
		FirefoxBinary firefoxBinary = new FirefoxBinary(new File("/opt/firefox/firefox"));
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
		baseUrl = "http://localhost:8080/mikrotikadmin/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testaSeCadastraPlanoValido() {
		Plano planoValido = PlanoDataFactory.criaPlanosValidos().get(0);

		driver.get(baseUrl + "planos/cadastrar/");
		driver.findElement(By.name("titulo")).sendKeys(planoValido.getTitulo());
		driver.findElement(By.name("descricao")).sendKeys(planoValido.getDescricao());
		driver.findElement(By.name("valor")).sendKeys(planoValido.getValorMensal().toString());
		driver.findElement(By.id("btn-cadastrar")).click();

		assertEquals(driver.getCurrentUrl(), baseUrl + "planos/");

		String mensagemSucesso = driver.findElement(By.className("alert-success")).getAttribute("innerHTML").trim();
		assertEquals(mensagemSucesso, "Plano cadastrado com sucesso!");

		Plano planoDoBancoDeDados = this.obtemEntityManager()
				.createQuery("SELECT p FROM Plano p WHERE titulo LIKE '%" + planoValido.getTitulo() + "%'", Plano.class)
				.getSingleResult();

		assertEquals(planoValido.getTitulo(), planoDoBancoDeDados.getTitulo());
		assertEquals(planoValido.getDescricao(), planoDoBancoDeDados.getDescricao());
		assertEquals(planoValido.getValorMensal(), planoDoBancoDeDados.getValorMensal());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
