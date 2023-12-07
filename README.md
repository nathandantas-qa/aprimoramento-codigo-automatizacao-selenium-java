# Correção de Testes Inconsistentes

[Flaky tests](https://dl.acm.org/doi/fullHtml/10.1145/3476105#Bib0109) é um termo utilizado na área de desenvolvimento de software para descrever testes automatizados que são inconsistentes em seus resultados.
                                                                       
A detecção dos testes instáveis será abordada de forma mais direta executando o conjunto de testes repetidamente. Grandes empresas de software, como Google e Microsoft, utilizam essa abordagem como parte de sua infraestrutura.


## Arquitetura do Teste - Alterações 

### Log4j - Adicionado

- O [Log4j](https://junit.org/junit5/docs/current/user-guide/) é uma estrutura de log de código aberto para Java.

**Arquivo pom.xml**

```xml
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
	<dependency>
	    <groupId>org.apache.logging.log4j</groupId>
	    <artifactId>log4j-core</artifactId>
	    <version>2.22.0</version>
	</dependency>
```

**Arquivo log4j2.properties**

O arquivo *log4j2.properties* pode ser adicionado no "src/test/resources" ou "src/main/resources".

```console
# Configuração para o console
appender.console.type = Console
appender.console.name = ConsoleAppender
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %M - %m%n

# Configuração para o arquivo
appender.file.type = File
appender.file.name = FileAppender
appender.file.fileName = logs/test_automation.log
appender.file.layout.type = PatternLayout
appender.file.layout.pattern = [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %M - %m%n

# Configuração do rootLogger
rootLogger.level = error
rootLogger.appenderRefs = stdout, file
rootLogger.appenderRef.stdout.ref = ConsoleAppender
rootLogger.appenderRef.file.ref = FileAppender
```

**Guia de configuração**

- [**appender.console.type:**](https://logging.apache.org/log4j/2.x/manual/appenders.html#ConsoleAppender) Define o tipo de Appenders usado para entrega de LogEvents.

- **appender.console.name:** Atribui um nome ao console appender.

- [**appender.console.layout.type:**](https://logging.apache.org/log4j/2.x/manual/layouts.html#pattern-layout) Especifica o layout utilizado pelo console appender.

- [**appender.console.layout.pattern:**](https://logging.apache.org/log4j/2.x/manual/layouts.html#patterns) Define o padrão de formatação das mensagens de log no console.


**Configuração para o arquivo**
- [**appender.file.type:**](https://logging.apache.org/log4j/2.x/manual/appenders.html#fileappender) Define o tipo de appender para gravação em arquivo.

- **appender.file.name:** Atribui um nome ao appender de arquivo.

- **appender.file.fileName:** Especifica o nome do arquivo para gravação. Se o arquivo ou qualquer um de seus diretórios pais não existir, eles serão criados.

- [**appender.file.layout.type:**](https://logging.apache.org/log4j/2.x/manual/appenders.html#fileappender) Define o tipo de layout utilizado pelo appender de arquivo.

- [**appender.file.layout.pattern:**](https://logging.apache.org/log4j/2.x/manual/layouts.html#patterns) Define o padrão de formatação das mensagens de log no arquivo.

**Configuração do rootLogger**

- [**rootLogger.level:**](https://logging.apache.org/log4j/2.x/manual/architecture.html#log-levels) Define o nível de log para o logger raiz.
- **rootLogger.appenderRefs:**  Especifica os appenders associados ao rootLogger.
- **rootLogger.appenderRef.stdout.ref:** Vincula o appender associado ao identificador "stdout" para o logger raiz.
- **rootLogger.appenderRef.file.ref:**	 Vincula o appender associado ao identificador "file" para o logger raiz.


## Detecção dos testes instáveis 

Para a detecção, foram adicionadas duas variáveis, 'logger' e 'contadorDeException', com o propósito de gerar registros e contabilizar as exceções. Essas adições tornarão mais fácil a identificação de inconsistências nos resultados durante a execução dos testes.

```java
private static final Logger logger = LogManager.getLogger(CarrinhoComprasTest.class);
private static Map<Class<? extends Exception>, Integer> contadorDeException = new HashMap<>();
```
	
Com a finalidade de exibir o resultado dos registros das exceções ocorridas e a quantidade de vezes que ocorreram durante a execução dos testes, foi desenvolvido o método exibirRelatorio, marcado com a anotação @AfterAll.

**@AfterAll:** Denota que o método anotado deve ser executado após a execução de todos os testes;

```java
public static void exibirResultadoExcecoes() {
	logger.info("Resultados das Exceções:\n");
	
	contadorDeException.entrySet().stream()
	.map(entry -> entry.getKey().getSimpleName() + ": " + entry.getValue() + " ocorrência(s)")
	.forEach(logMessage -> logger.info(logMessage));
}
```

A estratégia de repetição dos testes será aplicada às 4 funcionalidades, com cada uma sendo repetida 25 vezes, resultando em um total de 100 testes. Para isso, cada anotação @Test será substituída pela anotação @RepeatedTest(25).

```java
@RepeatedTest(25)
//	@Test
```

A detecção da fragilidade foi realizada envolvendo o código do teste em um bloco try-catch, visando o registro e contabilização de logs de erro.
	
```java
try {
...			
} catch (Exception e) {
	logger.error(format("Ocorreu uma exceção: %s", e.getClass().getSimpleName()));
	exceptionCounters.put(e.getClass(), exceptionCounters.getOrDefault(e.getClass(), 0) + 1);
	throw e;
}
```

**Resumo geral da detecção dos testes instáveis**

Abaixo está um resumo das execuções 1 e 2, juntamente com as explicações das exceções exibidas:

**Execucao 1**

![Exception](/assets/Run_1.jpg)

```text
[INFO ] 2023-12-04 12:29:01.283 [main] CarrinhoComprasTest - lambda$1 - NoSuchElementException: 1 ocorrência(s)
[INFO ] 2023-12-04 12:29:01.284 [main] CarrinhoComprasTest - lambda$1 - WebDriverException: 2 ocorrência(s)
[INFO ] 2023-12-04 12:29:01.284 [main] CarrinhoComprasTest - lambda$1 - ElementClickInterceptedException: 3 ocorrência(s)
[INFO ] 2023-12-04 12:29:01.284 [main] CarrinhoComprasTest - lambda$1 - StaleElementReferenceException: 24 ocorrência(s)
```


**Execucao 2**

![Exception](/assets/Run_1.jpg)

```text
[INFO ] 2023-12-05 18:43:53.685 [main] CarrinhoComprasTest - lambda$1 - WebDriverException: 6 ocorrência(s)
[INFO ] 2023-12-05 18:43:53.686 [main] CarrinhoComprasTest - lambda$1 - StaleElementReferenceException: 57 ocorrência(s)
```

A tabela abaixo apresenta as porcentagens de erro para diferentes exceções durante a execução de 200 testes automatizados.

| Exceção                           | Porcentagem  |
|-----------------------------------|--------------|
| StaleElementReferenceException    | 40.5%        |
| ElementClickInterceptedException  | 4.0%         |
| WebDriverException                | 1.0%         |
| NoSuchElementException            | 0.5%         |



**Exceções**

- [NoSuchElementException](https://www.lambdatest.com/blog/nosuchelementexception-in-selenium/):
*Possível Causa:* Elemento esperado não encontrado na página.
* Solução: * Utilizar esperas explícitas para garantir que o elemento esteja presente antes de interagir com ele.
			  
- [WebDriverException] (https://www.lambdatest.com/automation-testing-advisor/selenium/classes/org.openqa.selenium.WebDriverException) :
*Possível Causa:* Erro geral relacionado ao WebDriver, acontece quando o driver tenta processar um comando e ocorre um erro não especificado.
* Solução: * Analisar mensagens de erro específicas para diagnóstico preciso.

- [ElementClickInterceptedException](https://www.lambdatest.com/blog/elementclickinterceptedexception-in-selenium-java/) : 
*Possível Causa:* O clique em um elemento foi interceptado por outro elemento.
* Solução: * Garantir que elementos sobrepostos ou invisíveis não estejam interferindo nas interações.

- [StaleElementReferenceException](https://www.lambdatest.com/blog/handling-stale-element-exceptions-in-selenium-java/): 
*Possível Causa:* Referência a um elemento não mais existente ou alterado desde a última interação.
* Solução: * Implementar esperas explícitas, como WebDriverWait, para aguardar a estabilidade do elemento antes de interagir com ele.


## Correções dos Testes Instáveis

Foram implementadas soluções para lidar com inconsistências nos testes, visando maior estabilidade e confiabilidade. As principais alterações incluem o uso da espera explícita WebDriverWait e a introdução de um bloco try-catch para reteste em até 3 vezes em caso de falhas.

**WebDriverWait** 

Para maior estabilidade, especialmente em casos de StaleElementReferenceException e NoSuchElementException, é necessário fazer uso das esperas explícitas. Neste caso, temos:


- Declaração do atributo da classe WebDriverWait:
```java
WebDriverWait wait; 
```

- Instanciação do WebDriverWait no método @BeforeEach:
```java
@BeforeEach
public void setUp() {
	wait = new WebDriverWait(navegador, Duration.ofSeconds(5));
// Outras configurações de setup
}

```

- Substituição do método WebDriver.findElement() pelo uso de WebDriverWait:
```java
	
By by_BotaoCompar = By.cssSelector("[data-testid='buy-button']");
	
//______________________________________
//_______________Removido_______________
// navegador.findElement(elementProduct).click(); 
	
//______________________________________
//_______________Adicionado_____________
wait.until(ExpectedConditions.elementToBeClickable(by_BotaoCompar)).click();
```

- Substituição do uso de Thread.sleep pelo WebDriverWait:
```java
//______________________________________
//_______________Removido_______________
// try {
// 	Thread.sleep(Duration.ofSeconds(3));
// } catch (InterruptedException e) {
//	e.printStackTrace();
// }
// String urlAposCheckout = navegador.getCurrentUrl();
// String substringEsperada = "secure.mercafe.com.br";	
// Assertions.assertTrue(urlAposCheckout.contains(substringEsperada));

//______________________________________
//_______________Adicionado_____________
String substringEsperada = "secure.mercafe.com.br";
boolean contemSubstringEsperada =  wait.until(ExpectedConditions.urlContains(substringEsperada));
Assertions.assertTrue(contemSubstringEsperada);
```

**Execucao 3**

![Exception](/assets/Run_3.jpg)

```text
[INFO ] 2023-12-05 23:24:31.307 [main] CarrinhoComprasTest - lambda$1 - WebDriverException: 2 ocorrência(s)
[INFO ] 2023-12-05 23:24:31.307 [main] CarrinhoComprasTest - lambda$1 - TimeoutException: 2 ocorrência(s)
[INFO ] 2023-12-05 23:24:31.308 [main] CarrinhoComprasTest - lambda$1 - ElementClickInterceptedException: 3 ocorrência(s)
[INFO ] 2023-12-05 23:24:31.309 [main] CarrinhoComprasTest - lambda$1 - StaleElementReferenceException: 10 ocorrência(s)
```


**Reteste** 

Para maior confiabilidade, nos casos de apresentar qualquer exceção, rodamos os testes novamente.

- Atributo da classe para controle do reteste:

```java
private static final int  tentativasMaximas = 3; 
private static int tentativasRestantes;
```

- Inicialização do contador para o reteste ocorre em dois momentos.

1- No método @BeforeAll para o primeiro teste:

```java
@BeforeAll
public static void conficuracaoDasTentativaUpp(){
	tentativasRestantes = tentativasMaximas;
	}
```

2- Pode ocorrer quando o teste é bem-sucedido (sem entrar no bloco catch) ou quando falha definitivamente (entra no bloco catch e tentativasRestantes é zerado):
	
```java
...
tentativasRestantes = tentativasMaximas;
} catch (Exception e) {
	if (tentativasRestantes <= 0) {
		tentativasRestantes = tentativasMaximas;
		// Outras ações específicas
		throw e;
	}			
	// Outras ações específicas
```

Quando há uma exceção, é registrado o incidente e realizado o reteste usando os métodos tearDown, setUp e recursão:
	
```java
logger.error(format("Ocorreu uma exceção: %s. Tentativas restantes: %d ", e.getClass().getSimpleName(), tentativasRestantes ));
tentativasRestantes--;
contadorDeException.put(e.getClass(), contadorDeException.getOrDefault(e.getClass(), 0) + 1);
tearDown();
setUp();
adicionarItemNoCarrinho();
```

### Resultados dos Testes

Os testes foram executados 100 vezes, resultando em 24 ocorrências de exceções, sendo 23 do tipo StaleElementReferenceException e 1 do tipo TimeoutException. No entanto, devido à estratégia de reteste implementada, todas as ocorrências foram tratadas com sucesso. O uso de espera explícita (WebDriverWait) e a reintrodução dos testes em caso de falha contribuíram para a estabilidade e confiabilidade do conjunto de testes.

![Exception](/assets/Run_4.jpg)

```text
[INFO ] 2023-12-06 17:40:28.989 [main] CarrinhoComprasTest - lambda$1 - StaleElementReferenceException: 23 ocorrência(s)
[INFO ] 2023-12-06 17:40:28.989 [main] CarrinhoComprasTest - lambda$1 - TimeoutException: 1 ocorrência(s)
```

## Conclusão

As implementações de espera explícita e reteste mostraram-se eficazes na estabilização dos testes automatizados. Apesar das ocorrências iniciais de exceções, a estratégia de reteste permitiu que todos os casos fossem bem-sucedidos. Isso evidencia a melhoria na confiabilidade dos testes, proporcionando maior robustez ao processo de automação de testes.