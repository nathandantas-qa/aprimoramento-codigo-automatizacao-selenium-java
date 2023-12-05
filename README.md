# Correção de Testes Inconsistentes

[Flaky tests](https://dl.acm.org/doi/fullHtml/10.1145/3476105#Bib0109) é um termo utilizado na área de desenvolvimento de software para descrever testes automatizados que são inconsistentes em seus resultados.

## Dependências Adicionadas
	
- [Log4j](https://logging.apache.org/log4j/2.x/index.html) Gerenciador de registro


## Atualizações


###Log4j 

- O [Log4j](https://junit.org/junit5/docs/current/user-guide/) é uma estrutura de log de código aberto para Java.

**Arquivo pom.xml**

```text
	<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
		<dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-core</artifactId>
		    <version>2.22.0</version>
		</dependency>
		
```

**Arquivo log4j2.properties**

O arquivo *log4j2.properties* pode ser adicionado no "src/test/resources" ou "src/main/resources".

```text
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

[*appender.console.type*](https://logging.apache.org/log4j/2.x/manual/appenders.html#ConsoleAppender): Define o tipo de Appenders usado para entrega de LogEvents.

*appender.console.name* Atribui um nome ao console appender.

[*appender.console.layout.type*](https://logging.apache.org/log4j/2.x/manual/layouts.html#pattern-layout) Especifica o layout utilizado pelo console appender.

[*appender.console.layout.pattern*](https://logging.apache.org/log4j/2.x/manual/layouts.html#patterns) Define o padrão de formatação das mensagens de log no console.


**Configuração para o arquivo**
[*appender.file.type*](https://logging.apache.org/log4j/2.x/manual/appenders.html#fileappender) Define o tipo de appender para gravação em arquivo.

*appender.file.name* Atribui um nome ao appender de arquivo.

*appender.file.fileName* Especifica o nome do arquivo para gravação. Se o arquivo ou qualquer um de seus diretórios pais não existir, eles serão criados.

[*appender.file.layout.type*](https://logging.apache.org/log4j/2.x/manual/appenders.html#fileappender) Define o tipo de layout utilizado pelo appender de arquivo.

[*appender.file.layout.pattern*](https://logging.apache.org/log4j/2.x/manual/layouts.html#patterns) Define o padrão de formatação das mensagens de log no arquivo.

**Configuração do rootLogger**

[*rootLogger.level*](https://logging.apache.org/log4j/2.x/manual/architecture.html#log-levels) Define o nível de log para o logger raiz.
*rootLogger.appenderRefs*  Especifica os appenders associados ao rootLogger.
*rootLogger.appenderRef.stdout.ref* Vincula o appender associado ao identificador "stdout" para o logger raiz.
*rootLogger.appenderRef.file.ref* Vincula o appender associado ao identificador "file" para o logger raiz.


### Testes inconsistentes 

Após a configuração do log4j, foram criados os atributos "logger" e "exceptionCounters" para registro de log e identificação e contagem de exceções, respectivamente.

```java
	private static final Logger logger = LogManager.getLogger(CarrinhoComprasTest.class);
	private static Map<Class<? extends Exception>, Integer> exceptionCounters = new HashMap<>();
```
	
A fim de visualizar as exceções ocorridas e a quantidade de vezes durante a execução dos testes, foi desenvolvido o método exibirResultadoExcecoes, marcado com a anotação @AfterAll.

** @AfterAll:** Denota que o método anotado deve ser executado após o a execução do ultimo @Test método;

```java
	public static void exibirResultadoExcecoes() {
		logger.info("Resultados das Exceções:\n");
		
		exceptionCounters.entrySet().stream()
	    .map(entry -> entry.getKey().getSimpleName() + ": " + entry.getValue() + " ocorrência(s)")
	    .forEach(logMessage -> logger.info(logMessage));

	}
```
Para cada bloco de teste, o @Test foi modificado para @RepeatedTest(25), totalizando 100 testes. O código dentro do bloco foi envolto em um bloco try-catch para registro e contabilização de logs de erro.

```java
@RepeatedTest(25)
//	@Test
```
```java
		try {
			...			
		} catch (Exception e) {
			logger.error(format("Ocorreu uma exceção: %s", e.getClass().getSimpleName()));
			exceptionCounters.put(e.getClass(), exceptionCounters.getOrDefault(e.getClass(), 0) + 1);
			throw e;
		}
```


## Execução

### Resultados dos Testes

Os resultados do teste apresentaram um cenário indeterminado ao longo de 25 repetições das execuções.

**Resumo Geral dos Testes**

![Exception](/assets/Exception.jpg)

```text
[INFO ] 2023-12-04 12:29:01.283 [main] CarrinhoComprasTest - lambda$1 - NoSuchElementException: 1 ocorrência(s)
[INFO ] 2023-12-04 12:29:01.284 [main] CarrinhoComprasTest - lambda$1 - WebDriverException: 2 ocorrência(s)
[INFO ] 2023-12-04 12:29:01.284 [main] CarrinhoComprasTest - lambda$1 - ElementClickInterceptedException: 3 ocorrência(s)
[INFO ] 2023-12-04 12:29:01.284 [main] CarrinhoComprasTest - lambda$1 - StaleElementReferenceException: 24 ocorrência(s)
```
