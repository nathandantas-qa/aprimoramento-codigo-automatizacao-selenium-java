# Projeto de automação de testes em Java

* [Sobre o Projeto](#sobre-o-projeto)
* [Arquitetura de Teste](#arquitetura-de-teste)
  * [Linguagens e Estruturas](#linguagens-e-estruturas)
  * [Pre-requisitos](#pre-requisitos)
  * [Tipo de Execução](#tipo-de-execução)
  * [Ambiente de Execução](#ambiente-de-execução)
  * [Estrutura do Projeto](#estrutura-do-projeto)
* [Atualizações](#atualizações)
* [Execução](#execução)
  * [Instruções](#instruções)

## Sobre o Projeto 
Este projeto tem como objetivo fornecer orientações e passos para implantar uma checagens automatizadas.	
> ℹ️ Não faz parte do objetivo fornecer orientações sobre o planejamento dos cenários de teste automatizados.
Será abordado uma checagem automatizada end-to-end (E2E) para o gerenciamento do carrinho de compras em um sistema de comércio eletrônico. 
O gerenciamento do carrinho de compras será testada no [Mercafé](https://www.mercafe.com.br/), escolhido após uma ação significativa e de grande afinidade realizada por profissionais de TI, especialmente aqueles que apreciam o hábito de desfrutar de uma boa xícara de café.

A seguir, a História do Usuário fictícia que orientará os testes:

>**User Story: Gerenciar Carrinho de Compras**
>
>**Como um** cliente do sistema de comércio eletrônico
>
>**Eu quero** poder gerenciar o meu carrinho de compras
>
>**Para que** eu possa revisar, editar, remover e concluir minha compra posteriormente.
>
>**Critérios de Aceitação**
>1. *Devo ser capaz de adicionar facilmente um item ao meu carrinho de compras a partir da página do produto.*
>2. *Devo poder ajustar a quantidade de cada item diretamente no carrinho de compras.*
>3. *Devo ser capaz de remover um item específico do meu carrinho com facilidade.*
>4. *Devo ser capaz de avançar para o processo de checkout de forma intuitiva.*

## Arquitetura de teste

A arquitetura de teste deste projeto é baseada nas seguintes tecnologias e estruturas:

### Linguagens e Estruturas

- **Java:** Linguagem de programação utilizada.

- **Selenium:** Estrutura de automação do navegador web.

- **JUnit Jupiter:** Estrutura base para os testes.

- **Maven:** Gerenciador de dependências, responsável pela gestão das versões do compilador Java e executor dos testes.

- **Log4j:*** Gerenciador de registros.


### Pre-requisitos

Antes de executar os testes automatizados neste projeto, certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- [Java 21](https://www.oracle.com/br/java/technologies/downloads/#java21)
 
- [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

- [Google Chrome](https://support.google.com/chrome/answer/95346?hl=pt)

### Tipo de Execução

- Local.

### Ambiente de Execução

- Google Chrome

### Estrutura do Projeto

Abaixo está uma visão geral dos principais componentes:

```css
project-root
│
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── tests
│   │   │   │   ├── ShoppingCartTest.java
│   │   │   │   └── BaseTest.java
│   │   │   ├── helpers
│   │   │   │   ├── WebDriverHelper.java
│   │   │   │   ├── WaitHelper.java
│   │   │   │   └── ExceptionHandlingHelper.java
│   │   ├── resources
│   │   │   └── log4j2.properties
├── logs
│   ├── test_automation.log
├── .gitignore
├── LICENSE
├── pom.xml
└── README.md
```

**src:** Contém o código-fonte do projeto.  
**test:** Diretório para testes.  
**java:** Código-fonte dos testes.  
**tests:** Pacote que contém classes de teste.  
**ShoppingCartTest.java:** Classe de teste relacionada ao carrinho de compras.  
**BaseTest.java:** Classe de teste base com configurações iniciais e métodos comuns.  
**helpers:** Pacote com classes auxiliares para testes.  
**WebDriverHelper.java:** Auxilia na configuração e interação com o WebDriver.  
**WaitHelper.java:** Fornece métodos para esperar por elementos específicos antes de interagir com eles.  
**ExceptionHandlingHelper.java:** Lida com exceções durante os testes, permitindo reteste e registrando detalhes.  
**resources:** Recursos utilizados nos testes.  
**log4j2.properties:** Configurações do Log4j2 para logar informações durante a execução dos testes.  
**logs:** Diretório para armazenar logs do projeto.  
**test_automation.log:** Arquivo de log contendo informações sobre a execução dos testes automatizados.  
**gitignore:** Arquivo que especifica arquivos e pastas que devem ser ignorados pelo controle de versão Git.  
**LICENSE:** Arquivo que contém os termos da licença do projeto.  
**pom.xml:** Arquivo de configuração do Maven, utilizado para gerenciar as dependências e configurações do projeto.  
**README.md:** Arquivo que fornece informações essenciais sobre o projeto, como sua descrição, instruções de instalação e uso, entre outros.  

## Atualizações

1. [feature/automacao-carrinho-compras](https://github.com/nathandantas-qa/aprimoramento-codigo-automatizacao-selenium-java/tree/feature/automacao-carrinho-compras)
   - Adiciona testes automatizados para o gerenciamento do carrinho de compras, incluindo adição, ajuste, remoção de itens e avanço para o processo de checkout.
     
2. [fix/flaky-tests](https://github.com/nathandantas-qa/aprimoramento-codigo-automatizacao-selenium-java/tree/fix/flaky-tests)	
   - Aprimora o registro de logs com o Log4j para facilitar a identificação de Flaky Tests.
   - Reforça a estabilidade dos testes com a implementação de espera explícita.
   - Aprimora a confiabilidade dos testes com a implementação de reteste.
  
3. [refactor/cleaner-code]](https://github.com/nathandantas-qa/aprimoramento-codigo-automatizacao-selenium-java/tree/refactor/cleaner-code)
   - Atualização de nomes de métodos e classes para seguir as boas práticas de Clean Code.
   - Adição de classes auxiliares para lidar com exceções, espera e interação com o WebDriver.
   - Modificação na estrutura de diretórios para organizar melhor os testes.
   - Remoção de métodos obsoletos e redundantes.

Para obter mais detalhes sobre cada atualização, você pode explorar os branches associados.

## Execução

### Instruções 
Para executar os testes automatizados, siga as etapas abaixo:

1. *Abra um console ou terminal:*
   - No Windows, você pode pressionar `Win + R`, digitar `cmd` ou `powershell`, e pressionar Enter.
   - No Linux ou macOS, você pode usar o terminal padrão do sistema.
  
2. *Navegue até o diretório do projeto:*
```bash
   cd caminho/do/seu/projeto
```

3. *Executar os testes:*
```bash
   mvn test
```

> :warning: **Observações**
> Se estiver utilizando uma IDE, como o IntelliJ ou Eclipse, você também pode executar os testes diretamente na IDE.  
> Certifique-se de que a configuração do ambiente e as dependências estejam configuradas corretamente na IDE.
