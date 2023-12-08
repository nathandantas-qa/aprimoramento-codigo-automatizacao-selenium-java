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
  * [Resultados dos Testes](#resultados-dos-testes)

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

A arquitetura inicial é simplória, sem padrão de projeto ou boas práticas de codificação.

## Atualizações

1. [feature/automacao-carrinho-compras](https://github.com/nathandantas-qa/aprimoramento-codigo-automatizacao-selenium-java/tree/feature/automacao-carrinho-compras)
   - Adiciona testes automatizados para o gerenciamento do carrinho de compras, incluindo adição, ajuste, remoção de itens e avanço para o processo de checkout.
     
2. [fix/flaky-tests](https://github.com/nathandantas-qa/aprimoramento-codigo-automatizacao-selenium-java/tree/fix/flaky-tests)	
   - Aprimora o registro de logs com o Log4j para facilitar a identificação de Flaky Tests.
   - Reforça a estabilidade dos testes com a implementação de espera explícita.
   - Aprimora a confiabilidade dos testes com a implementação de reteste.

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

### Resultados dos Testes

Durante 100 execuções dos testes automatizados, foram observadas 24 ocorrências de exceções, incluindo 23 do tipo StaleElementReferenceException e 1 do tipo TimeoutException. No entanto, graças à estratégia de reteste implementada, todas as ocorrências foram tratadas com sucesso. O uso de espera explícita (WebDriverWait) e a reintrodução dos testes em caso de falha contribuíram significativamente para a estabilidade e confiabilidade do conjunto de testes.

![Exception](/assets/Run_4.jpg)

```text
[INFO ] 2023-12-06 17:40:28.989 [main] CarrinhoComprasTest - lambda$1 - StaleElementReferenceException: 23 ocorrência(s)
[INFO ] 2023-12-06 17:40:28.989 [main] CarrinhoComprasTest - lambda$1 - TimeoutException: 1 ocorrência(s)
```
