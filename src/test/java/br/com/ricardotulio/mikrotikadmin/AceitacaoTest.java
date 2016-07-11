package br.com.ricardotulio.mikrotikadmin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AceitacaoTest {

	@Rule
	public TestName testName = new TestName();
	protected static WebDriver driver;
	protected static String baseUrl;
	protected static String screenshotPath = "/home/ricardo/workspace/mikrotikadmin/src/test/";

	@BeforeClass
	public static void criaWebDriver() {
		FirefoxBinary firefoxBinary = new FirefoxBinary(new File("/opt/firefox/firefox"));
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
		baseUrl = "http://localhost:8080/mikrotikadmin/";
	}

	@After
	public void capturaScreenshotAposFimTeste() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String nomeArquivo = this.getClass().getName() + "_" + testName.getMethodName() + "_" + sdf.format(new Date())
				+ ".png";

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotPath + nomeArquivo));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void fechaWebDriver() {
		driver.close();
	}

	protected String transformaDoubleEmString(Double valor) {
		if (valor == null)
			return "";
		return valor.toString().replace(".", ",");
	}
}
