package test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.lang.String.format;

@DisplayName("Testes E2E")
public class CarrinhoComprasTest {

	private static final Logger logger = LogManager.getLogger(CarrinhoComprasTest.class);

	private static Map<Class<? extends Exception>, Integer> exceptionCounters = new HashMap<>();

	WebDriver navegador;

	@BeforeEach
	public void setUp() {
		this.navegador = new ChromeDriver();
		navegador.manage().window().maximize();
		navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		navegador.get("https://www.mercafe.com.br");
		navegador
				.findElement(By.xpath(
						"//button[@class='Button-codeby__sc-qkp43z-4 gUUsRN'][contains(.,'CONTINUAR E FECHAR')]"))
				.click();

	}

	@AfterEach
	public void tearDown() {
		navegador.quit();
	}

	@AfterAll
	public static void exibirResultadoExcecoes() {
		logger.info("Resultados das Exceções:\n");
		
		exceptionCounters.entrySet().stream()
	    .map(entry -> entry.getKey().getSimpleName() + ": " + entry.getValue() + " ocorrência(s)")
	    .forEach(logMessage -> logger.info(logMessage));
		
	}

	// 1. "Devo ser capaz de adicionar facilmente um item ao meu carrinho de compras
	// a partir da página do produto."
	@RepeatedTest(25)
//	@Test
	public void adicionarItemNoCarrinho() {
		try {
			navegador.get("https://www.mercafe.com.br/colecoes-cafes/metodo/capsulas");

			By elementProduct = By.cssSelector("[data-testid='buy-button']");

			navegador.findElement(elementProduct).click();

			String quantidadeItem_texto = navegador.findElement(By.cssSelector("[data-testid='store-input']"))
					.getAttribute("value");

			int quantidadeItem = Integer.parseInt(quantidadeItem_texto);

			Assertions.assertEquals(1, quantidadeItem);

		} catch (Exception e) {
			logger.error(format("Ocorreu uma exceção: %s", e.getClass().getSimpleName()));
			exceptionCounters.put(e.getClass(), exceptionCounters.getOrDefault(e.getClass(), 0) + 1);
			throw e;
		}

	}

	// 2. "Devo poder ajustar a quantidade de cada item diretamente no carrinho de
	// compras."
	@RepeatedTest(25)
//	@Test
	public void editarQuantidadeDeItensNoCarrinho() {
		try {
			navegador.get("https://www.mercafe.com.br/colecoes-cafes/metodo/capsulas");

			By elementProduct = By.cssSelector("[data-testid='buy-button']");

			navegador.findElement(elementProduct).click();

			By botaoXPath = By.cssSelector(
					"section[data-testid='store-card-content'] button[data-testid='store-quantity-selector-right']");

			navegador.findElement(botaoXPath).click();

			By qtdaItem_XPath = By.cssSelector("[data-testid='store-input']");

			String quantidadeItem_texto = navegador.findElement(qtdaItem_XPath).getAttribute("value");

			int quantidadeItem = Integer.parseInt(quantidadeItem_texto);

			Assertions.assertEquals(2, quantidadeItem);
		} catch (Exception e) {
			logger.error(format("Ocorreu uma exceção: %s", e.getClass().getSimpleName()));
			exceptionCounters.put(e.getClass(), exceptionCounters.getOrDefault(e.getClass(), 0) + 1);
			throw e;
		}
	}

	// 3. "Devo ser capaz de remover um item específico do meu carrinho com
	// facilidade.")
	@RepeatedTest(25)
//	@Test
	public void removerItemNoCarrinho() {
		try {
			navegador.get("https://www.mercafe.com.br/colecoes-cafes/metodo/capsulas");

			By elementProduct = By.cssSelector("[data-testid='buy-button']");

			navegador.findElement(elementProduct).click();

			By botaoXPath = By.cssSelector("button[data-testid='remove-from-cart-button']");

			navegador.findElement(botaoXPath).click();

			By qtdaItem_XPath = By.xpath(
					"//strong[@class='BoldText-codeby__sc-j4zszc-2 hITtQO'][contains(.,'Seu carrinho de compras está vazio')]");

			String result = navegador.findElement(qtdaItem_XPath).getText();

			Assertions.assertEquals("Seu carrinho de compras está vazio", result);
		} catch (Exception e) {
			logger.error(format("Ocorreu uma exceção: %s", e.getClass().getSimpleName()));
			exceptionCounters.put(e.getClass(), exceptionCounters.getOrDefault(e.getClass(), 0) + 1);
			throw e;
		}
	}

	// 4. "Devo ser capaz de avançar para o processo de checkout de forma
	// intuitiva."
	@RepeatedTest(25)
//	@Test
	public void fazerCheckout() {
		try {
			navegador.get("https://www.mercafe.com.br/colecoes-cafes/metodo/capsulas");

			By elementProduct = By.cssSelector("[data-testid='buy-button']");

			navegador.findElement(elementProduct).click();

			By botaoXPath = By.cssSelector("[data-testid='checkout-button']");

			try {
				Thread.sleep(Duration.ofSeconds(3));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			navegador.findElement(botaoXPath).click();

			try {
				Thread.sleep(Duration.ofSeconds(3));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String urlAposCheckout = navegador.getCurrentUrl();

			String substringEsperada = "secure.mercafe.com.br";
			System.out.println("urlAposCheckout = " + urlAposCheckout);
			Assertions.assertTrue(urlAposCheckout.contains(substringEsperada));

		} catch (Exception e) {
			logger.error(format("Ocorreu uma exceção: %s", e.getClass().getSimpleName()));
			exceptionCounters.put(e.getClass(), exceptionCounters.getOrDefault(e.getClass(), 0) + 1);
			throw e;
		}
	}
}