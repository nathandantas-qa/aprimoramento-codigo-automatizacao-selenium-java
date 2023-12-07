	package test;
	
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;
	
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.junit.jupiter.api.AfterAll;
	import org.junit.jupiter.api.AfterEach;
	import org.junit.jupiter.api.Assertions;
	import org.junit.jupiter.api.BeforeAll;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.DisplayName;
	import org.junit.jupiter.api.RepeatedTest;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	import static java.lang.String.format;
	
	@DisplayName("Testes E2E")
	public class CarrinhoComprasTest {
	
		private static final Logger logger = LogManager.getLogger(CarrinhoComprasTest.class);
	
		private static Map<Class<? extends Exception>, Integer> contadorDeException = new HashMap<>();
		
		private static final int  tentativasMaximas = 3; 
		private static int tentativasRestantes;
	
		WebDriver navegador;
		WebDriverWait wait;
		
	
		@BeforeAll
		public static void conficuracaoDasTentativaUpp(){
			tentativasRestantes = tentativasMaximas;
		}
		
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
	
			wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
	
		}
	
		@AfterEach
		public void tearDown() {
			navegador.quit();
		}
	
		@AfterAll
		public static void exibirResultadoExcecoes() {
			logger.info("Resultados das Exceções:\n");
	
			contadorDeException.entrySet().stream()
					.map(entry -> entry.getKey().getSimpleName() + ": " + entry.getValue() + " ocorrência(s)")
					.forEach(logMessage -> logger.info(logMessage));
	
		}
	
		// 1. "Devo ser capaz de adicionar facilmente um item ao meu carrinho de compras
		// a partir da página do produto."
		@RepeatedTest(25)
	//	@Test
		public void adicionarItemNoCarrinho() throws Exception {
			try {		
				
				navegador.get("https://www.mercafe.com.br/colecoes-cafes/metodo/capsulas");
	
				By by_BotaoCompar = By.cssSelector("[data-testid='buy-button']");
	
				wait.until(ExpectedConditions.elementToBeClickable(by_BotaoCompar)).click();
	
				By by_campoStore = By.cssSelector("[data-testid='store-input']");
	
				String qtdeItem = wait.until(ExpectedConditions.visibilityOfElementLocated(by_campoStore))
						.getAttribute("value");
	
				int resultado = Integer.parseInt(qtdeItem);
	
				int expectativa = 1;
	
				Assertions.assertEquals(expectativa, resultado);
				
				tentativasRestantes = tentativasMaximas;
	
			} catch (Exception e) {
				if (tentativasRestantes <= 0) {
					logger.error(format("Tentativas esgotadas, lançando a exceção: %s", e.getClass().getSimpleName()));
					tentativasRestantes = tentativasMaximas;
					throw e;  // Lança a exceção após 3 falhas consecutivas
				}			
				logger.error(format("Ocorreu uma exceção: %s. Tentativas restantes: %d ", e.getClass().getSimpleName(), tentativasRestantes ));
				tentativasRestantes--;
				contadorDeException.put(e.getClass(), contadorDeException.getOrDefault(e.getClass(), 0) + 1);
				tearDown();
				setUp();
				adicionarItemNoCarrinho();
	
			} 
	
		}
	
		// 2. "Devo poder ajustar a quantidade de cada item diretamente no carrinho de
		// compras."
		@RepeatedTest(25)
	//	@Test
		public void editarQuantidadeDeItensNoCarrinho() {
			try {
				navegador.get("https://www.mercafe.com.br/colecoes-cafes/metodo/capsulas");
	
				By by_BotaoCompar = By.cssSelector("[data-testid='buy-button']");
	
				wait.until(ExpectedConditions.elementToBeClickable(by_BotaoCompar)).click();
	
				By botaoXPath = By.cssSelector(
						"section[data-testid='store-card-content'] button[data-testid='store-quantity-selector-right']");
	
				wait.until(ExpectedConditions.elementToBeClickable(botaoXPath)).click();
				
				
				By qtdaItem_XPath = By.cssSelector("[data-testid='store-input']");
	
				String quantidadeItem_texto = wait.until(ExpectedConditions.visibilityOfElementLocated(qtdaItem_XPath)).getAttribute("value");
	
				int quantidadeItem = Integer.parseInt(quantidadeItem_texto);
	
				Assertions.assertEquals(2, quantidadeItem);
				
				tentativasRestantes = tentativasMaximas;
				
			} catch (Exception e) {
				if (tentativasRestantes <= 0) {
					logger.error(format("Tentativas esgotadas, lançando a exceção: %s", e.getClass().getSimpleName()));
					tentativasRestantes = tentativasMaximas;
					throw e;  // Lança a exceção após 3 falhas consecutivas
				}			
				logger.error(format("Ocorreu uma exceção: %s. Tentativas restantes: %d ", e.getClass().getSimpleName(), tentativasRestantes ));
				tentativasRestantes--;
				contadorDeException.put(e.getClass(), contadorDeException.getOrDefault(e.getClass(), 0) + 1);
				tearDown();
				setUp();
				editarQuantidadeDeItensNoCarrinho();
	
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
	
				wait.until	(ExpectedConditions.elementToBeClickable(elementProduct)).click();
	
				By botaoXPath = By.cssSelector("button[data-testid='remove-from-cart-button']");
	
				wait.until(ExpectedConditions.elementToBeClickable(botaoXPath)).click();
	
				By qtdaItem_XPath = By.xpath(
						"//strong[@class='BoldText-codeby__sc-j4zszc-2 hITtQO'][contains(.,'Seu carrinho de compras está vazio')]");
	
				
				String result = wait.until(ExpectedConditions.visibilityOfElementLocated(qtdaItem_XPath)).getText();
	
				Assertions.assertEquals("Seu carrinho de compras está vazio", result);
	
				tentativasRestantes = tentativasMaximas;
							
			} catch (Exception e) {
				if (tentativasRestantes <= 0) {
					logger.error(format("Tentativas esgotadas, lançando a exceção: %s", e.getClass().getSimpleName()));
					tentativasRestantes = tentativasMaximas;
					throw e;  // Lança a exceção após 3 falhas consecutivas
				}			
				logger.error(format("Ocorreu uma exceção: %s. Tentativas restantes: %d ", e.getClass().getSimpleName(), tentativasRestantes ));
				tentativasRestantes--;
				contadorDeException.put(e.getClass(), contadorDeException.getOrDefault(e.getClass(), 0) + 1);
				tearDown();
				setUp();
				removerItemNoCarrinho();
	
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
	
				wait.until(ExpectedConditions.elementToBeClickable(elementProduct)).click();
	
				By botaoXPath = By.cssSelector("[data-testid='checkout-button']");
	
				wait.until(ExpectedConditions.elementToBeClickable(botaoXPath)).click();
	
				String substringEsperada = "secure.mercafe.com.br";
				
				boolean contemSubstringEsperada =  wait.until(ExpectedConditions.urlContains(substringEsperada));
				
				Assertions.assertTrue(contemSubstringEsperada);
				
				tentativasRestantes = tentativasMaximas;
				
			} catch (Exception e) {
				if (tentativasRestantes <= 0) {
					logger.error(format("Tentativas esgotadas, lançando a exceção: %s", e.getClass().getSimpleName()));
					tentativasRestantes = tentativasMaximas;
					throw e;  // Lança a exceção após 3 falhas consecutivas
				}			
				logger.error(format("Ocorreu uma exceção: %s. Tentativas restantes: %d ", e.getClass().getSimpleName(), tentativasRestantes ));
				tentativasRestantes--;
				contadorDeException.put(e.getClass(), contadorDeException.getOrDefault(e.getClass(), 0) + 1);
				tearDown();
				setUp();
				fazerCheckout();
	
			} 
		}
	}