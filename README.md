# Projeto de automação de testes em Java

* [Sobre o Projeto](#Sobre-o-Projeto)

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
Devido ao fato de o site ser de terceiros e o foco principal estar no aprimoramento do código, a abordagem de definir o que testar não será levada em consideração. Em vez disso, serão considerados apenas cenários genéricos e adaptáveis ao site.

## Linguagens e Estruturas

- **Java:** Linguagem de programação utilizada.

- **Selenium:** Estrutura de automação do navegador web.

- **JUnit Jupiter:** Estrutura base para os testes.

- **Maven:** Gerenciador de dependências, responsável pela gestão das versões do compilador Java e executor dos testes.


## Pre-requisitos

Antes de executar os testes automatizados neste projeto, certifique-se de que as dependências estejam instaladas e corretamente configuradas: 

- [Java 21](https://www.oracle.com/br/java/technologies/downloads/#java21)
 
- [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

- [Google Chrome](https://support.google.com/chrome/answer/95346?hl=pt)

## Arquitetura de teste

A arquitetura inicial é simplória, sem padrão de projeto ou boas práticas de codificação.

**Tipo de execução** 

- Local.

**Ambiente de execução**

- Google Chrome


## Atualizações

1. [feature/automacao-carrinho-compras](https://github.com/nathandantas-qa/aprimoramento-codigo-automatizacao-selenium-java/tree/feature/automacao-carrinho-compras)



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

Os resultados do teste apresentaram um cenário indeterminado ao longo de 12 repetições das execuções.

**Resumo Geral dos Testes**

Teste                                | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 |   
-------------------------------------|---|---|---|---|---|---|---|---|---|----|----|----|
adicionarItemNoCarrinho              | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 1 | 0  | 0  | 0  |
editarQuantidadeDeItensNoCarrinho    | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | 0  | 0  | 0  |
removerItemNoCarrinho                | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0  | 1  | 1  |
fazerCheckout                        | 0 | 1 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0  | 0  | 1  |  
-------------------------------------|---|---|---|---|---|---|---|---|---|----|----|----|
Erros                                | 4 | 3 | 4 | 4 | 2 | 4 | 4 | 3 | 3 | 4  | 3  | 2  |
Sucessos                             | 0 | 1 | 0 | 0 | 2 | 0 | 0 | 1 | 1 | 0  | 1  | 2  |


ERROS                                | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 |
-------------------------------------|---|---|---|---|---|---|---|---|---|----|----|----|
StaleElementReferenceException       | 4 | 3 | 4 | 4 | 2 | 4 | 4 | 3 | 3 | 4  | 3  | 2  |

A presença de StaleElementReferenceException tounou o sistema instável devido a instabilidade nos elementos da interface do usuário durante a execução dos testes automatizados.  
Esta inconsistência pode ser resultado de alterações dinâmicas na página, tempo de espera insuficiente ou outros fatores relacionados à interação com os elementos da interface.
