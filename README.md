# Clean code

O Clean Code é uma abordagem essencial no desenvolvimento de software, buscando tornar o código fonte compreensível, legível e de fácil manutenção. Este se utiliza alguns princípios do livro *Clean Code: A Handbook of Agile Software Craftsmanship* de Robert C. Martin para aprimorar partes do código e promover boas práticas de programação.

## Portugues ou ingles?

A decisão entre escrever o código em português ou inglês é crucial para a clareza e consistência do código. Considere as seguintes orientações:

1. **Diretrizes superiores ou padrões estabelecidos:** Siga as diretrizes existentes.

2. **Decisão consensual pela equipe:** Discuta e alcance um consenso.

3. **Aspirações globais de carreira:** O inglês é a linguagem universal para programadores.

4. **Decisão pessoal:** Escolha confortável e eficaz.

Na internet existem várias postagens e discussões sobre este tema, para mais insights, consulte este [link](https://carlosschults.net/pt/programar-portugues-ou-ingles/)

## Nomes significativos 

No Clean Code, escolher nomes claros e representativos é crucial para a legibilidade e manutenção do código.

Aseguir, será abordado as alterações dos nomes realizada. 

### Nomes das Classes

Ao nomear classes, use substantivos que representem claramente a entidade.

|        Antes        |      Depois      | 
|---------------------|------------------|
| CarrinhoComprasTest | ShoppingCartTest |


### Nomes dos Métodos

Nomes de métodos devem conter verbos e prefixos de ação.

| Antes                             | Depois                            | 
|-----------------------------------|-----------------------------------|
| conficuracaoDasTentativaUpp       | ~initializeRetryLimit~             |
| setUp                             | setUpTestEnvironment              |
| tearDown                          | tearDownTestEnvironment           |
| exibirResultadoExcecoes           | ~displayExceptionResultsOnConsole~  |
| adicionarItemNoCarrinho           | testAddItemToCart                 |
| editarQuantidadeDeItensNoCarrinho | testEditCartItemQuantity          |
| removerItemNoCarrinho             | testRemoveItemFromCart            |
| fazerCheckout                     | testCheckout                      |
	
		
### Variáveis
Os nomoes devem revelar explicitamente o seu propósito, dando um contexto signinficativo. Importante que sejão pronunciaveis e passiveis de busca.

*Variáveis de Classe*  
| Antes                  | Depois                 |
|------------------------|------------------------|
| logger                 | logger                 |
| contadorDeException    | ~exceptionCounter~       |
| tentativasMaximas      | MAX_RETRIES            |
| tentativasRestantes    | remainingRetries       |
| navegador              | driver                 |
| wait                   | wait                   |


*Variáveis Locais*
  
| Antes                           | Depois                                    |
|---------------------------------|-------------------------------------------|
| by_BotaoCompar                  | BUY_BUTTON_SELECTOR                       |
| by_campoStore                   | STORE_INPUT_SELECTOR                      |
| qtdeItem                        | itemQuantity                              |
| resultado                       | result                                    |
| expectativa                     | ~expected~                                |
| botaoXPath                      | STORE_INPUT_SELECTOR                      |
| botaoXPath                      | CARD_STORE_QUANTITY_RIGHT_BUTTON_SELECTOR |
| botaoXPath                      | REMOVE_BUTTON_SELECTOR                    |
| botaoXPath                      | CHECKOUT_BUTTON_SELECTOR                  |
| qtdaItem_XPath                  | EMPTY_CART_MESSAGE_SELECTOR               |
| quantidadeItem_texto            | itemQuantity                              |
| quantidadeItem                  | ~itemQuantity~                            |
| elementProduct                  | BUY_BUTTON_SELECTOR                       |
| substringEsperada               | EXPECTED_URL_SUBSTRING                    |
| contemSubstringEsperada         | ~CONTAINS_SUBSTRING~                      |


## Funções

Para melhorar a legibilidade e manutenibilidade do código é importante manter funções pequenas, com responsabilidade única e nomes descritivos.


### Classes


#### 1. `ShoppingCartTest`

Esta classe contém os testes end-to-end para interações com o carrinho de compras.

#### Atributos

- `PRODUCT_URL`: URL da página de produtos.
- `EXPECTED_EMPTY_CART_MESSAGE`: Mensagem esperada quando o carrinho está vazio.
- `EXPECTED_URL_SUBSTRING`: Substring esperada na URL durante o checkout.
- `BUY_BUTTON_SELECTOR`: Seletor CSS do botão de compra.
- `STORE_INPUT_SELECTOR`: Seletor CSS do campo de entrada de quantidade no carrinho.
- `CARD_STORE_QUANTITY_RIGHT_BUTTON_SELECTOR`: Seletor CSS do botão de incremento de quantidade no carrinho.
- `REMOVE_BUTTON_SELECTOR`: Seletor CSS do botão de remoção de item do carrinho.
- `EMPTY_CART_MESSAGE_SELECTOR`: Seletor XPath da mensagem de carrinho vazio.
- `CHECKOUT_BUTTON_SELECTOR`: Seletor CSS do botão de checkout.

#### Métodos

- `testAddItemToCart()`: Testa a adição de um item ao carrinho de compras.
  - Navega até a página do produto.
  - Clica no botão de compra.
  - Verifica se a quantidade do item no carrinho é 1.

- `retryTestAddItemToCart(exception)`: Trata exceções durante o teste `testAddItemToCart` e permite reteste.

- `testEditCartItemQuantity()`: Testa a edição da quantidade de itens no carrinho.
  - Navega até a página do produto.
  - Clica no botão de compra.
  - Clica no botão de incremento de quantidade no carrinho.
  - Verifica se a quantidade do item no carrinho é 2.

- `retryTestEditCartItemQuantity(exception)`: Trata exceções durante o teste `testEditCartItemQuantity` e permite reteste.

- `testRemoveItemFromCart()`: Testa a remoção de um item do carrinho.
  - Navega até a página do produto.
  - Clica no botão de compra.
  - Clica no botão de remoção do item do carrinho.
  - Verifica se a mensagem de carrinho vazio é exibida.

- `retrytestRemoveItemFromCart(exception)`: Trata exceções durante o teste `testRemoveItemFromCart` e permite reteste.

- `testCheckout()`: Testa o processo de checkout.
  - Navega até a página do produto.
  - Clica no botão de compra.
  - Clica no botão de checkout.
  - Verifica se a URL contém a substring esperada.

- `retryTestCheckout(exception)`: Trata exceções durante o teste `testCheckout` e permite reteste.

- Métodos auxiliares privados (`clickBuyButton()`, `clickCardStoreQuantityRightButton()`, `clickRemoveButton()`, `clickCheckoutButton()`, `getItemQuantity()`, `getEmptyCartMessage()`, `navigateToUrlProduct()`): Simplificam a execução de ações específicas nos testes.


#### 2. `BaseTest`

Esta classe é uma classe base para os testes e contém configurações iniciais e métodos comuns.

#### Atributos

- `BASE_URL`: URL base do site [Mercafe](https://www.mercafe.com.br).
- `driver`: Instância do WebDriver para interação com o navegador.
- `webDriverHelper`: Instância do `WebDriverHelper` para auxiliar na interação com o WebDriver.
- `exceptionHandlingHelper`: Instância do `ExceptionHandlingHelper` para tratar exceções durante os testes.

#### Métodos

- `setUpTestEnvironment()`: Configura o ambiente de teste antes de cada teste.
  - Configura o WebDriver.
  - Navega para a página inicial.
  - Fecha o popup de anúncio.
  
- `tearDownTestEnvironment()`: Limpa o ambiente de teste após cada teste.
  - Fecha o WebDriver.

- `configureExceptionHandling()`: Configura o tratamento de exceções durante os testes.

- `configureWebDriver()`: Configura o WebDriver, inicializando a instância e configurando o helper.

- `navigateToHomePage()`: Navega para a página inicial do site.

- `closeAnnouncementPopup()`: Fecha o popup de anúncio, se presente.

- `closeWebDriver()`: Encerra a instância do WebDriver.

- `handleExceptionWithRetryAndLog(exception, retryAction, testName)`: Lida com exceções durante os testes, permitindo reteste e registrando detalhes.
  - `exception`: A exceção capturada.
  - `retryAction`: A ação a ser reexecutada em caso de exceção.
  - `testName`: Nome do teste em execução.

- `resetTestEnvironment()`: Reinicializa o ambiente de teste.
  - Fecha o WebDriver.
  - Configura novamente o WebDriver.
  - Navega para a página inicial.
  - Fecha o popup de anúncio.

- `isHandlingFailed()`: Verifica se o tratamento de exceção falhou.

- `waitElementAndClick(locator)`: Aguarda um elemento ser clicável e o clica.
  - `locator`: O seletor do elemento.

- `waitElementAndGetValue(locator)`: Aguarda a visibilidade de um elemento e retorna o valor do atributo "value".
  - `locator`: O seletor do elemento.

- `waitElementAndGetText(locator)`: Aguarda a visibilidade de um elemento e retorna o texto contido nele.
  - `locator`: O seletor do elemento.

- `urlContains(urlSubstring)`: Verifica se a URL atual contém uma substring específica.
  - `urlSubstring`: A substring a ser verificada na URL.

#### 3. `WebDriverHelper`

Esta classe auxilia na configuração e interação com o WebDriver.

#### Atributos

- `driver`: Instância do WebDriver associada a esta instância do `WebDriverHelper`.
- `waitHelper`: Instância do `WaitHelper` para auxiliar nas operações de espera.

#### Métodos

- `configure()`: Configura o WebDriver.
  - Maximiza a janela.
  - Define o tempo de espera implícito.

- `maximizeWindow()`: Maximiza a janela do navegador.

- `setImplicitWait(duration)`: Define o tempo de espera implícito.
  - `duration`: A duração do tempo de espera implícito.

- `waitElementAndClick(locator)`: Aguarda um elemento ser clicável e o clica.
  - `locator`: O seletor do elemento.

- `waitElementAndGetValue(locator)`: Aguarda a visibilidade de um elemento e retorna o valor do atributo "value".
  - `locator`: O seletor do elemento.

- `waitElementAndGetText(locator)`: Aguarda a visibilidade de um elemento e retorna o texto contido nele.
  - `locator`: O seletor do elemento.

- `urlContains(urlSubstring)`: Verifica se a URL atual contém uma substring específica.
  - `urlSubstring`: A substring a ser verificada na URL.


#### 4. `WaitHelper`

Esta classe fornece métodos para esperar por elementos específicos antes de interagir com eles.

#### Atributos

- `wait`: Instância da classe `WebDriverWait` associada a esta instância do `WaitHelper`.
- `DEFAULT_TIMEOUT`: Tempo de espera padrão definido para aguardar a visibilidade de elementos.
- `TIMES_TO_RETRY`: Número de tentativas de retentativa em caso de falha ao aguardar a visibilidade de elementos.
- `WEB_ELEMENT_NOT_LOCATED_MESSAGE`: Mensagem de erro para exceção quando o elemento não é localizado após várias tentativas.

#### Métodos

- `urlContains(urlSubstring)`: Aguarda até que a URL atual contenha uma substring específica.
  - `urlSubstring`: A substring a ser verificada na URL.

- `waitElementToBeClickableWithShouldRetry(locator)`: Aguarda até que um elemento seja clicável, com retentativa em caso de falha.
  - `locator`: O seletor do elemento.

- `waitVisibilityOfElementLocated(locator)`: Aguarda até que um elemento seja visível, com retentativa em caso de falha.
  - `locator`: O seletor do elemento.

- `retryLogic(action, locator)`: Lógica de retentativa para operações com elementos.
  - `action`: A ação a ser executada.
  - `locator`: O seletor do elemento.

#### 5. `ExceptionHandlingHelper`

Esta classe lida com exceções durante os testes, permitindo reteste e registrando detalhes.

#### Atributos

- `MAX_RETRIES`: Número máximo de retentativas permitidas.
- `remainingRetries`: Número de retentativas restantes.
- `handlingFailed`: Indicador de falha no tratamento.

#### Métodos

- `handleRetryAndLog(exception, retryAction, testName)`: Trata uma exceção durante a execução de um teste, permite uma retentativa e registra informações sobre a exceção.
  - `exception`: A exceção ocorrida durante o teste.
  - `retryAction`: A ação a ser retentada em caso de falha.
  - `testName`: Nome do teste em execução.

- `isHandlingFailed()`: Verifica se o tratamento falhou.

- `isHandling()`: Verifica se ainda é possível realizar retentativas.

- `registerAttempts(exception, testName)`: Registra as tentativas de retentativa e loga as informações.
  - `exception`: A exceção ocorrida durante o teste.
  - `testName`: Nome do teste em execução.

- `registerAttemptsExhausted(exception, testName)`: Registra a falha no tratamento após esgotar as retentativas.
  - `exception`: A exceção ocorrida durante o teste.
  - `testName`: Nome do teste em execução.


