# Padrão Page Object

O padrão [Page Object](https://martinfowler.com/bliki/PageObject.html) é uma abordagem que visa encapsular a interação com elementos da interface do usuário e mapear funcionalidades específicas para classes dedicadas.

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte maneira:1

```plaintext
project-root
│
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── page
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── CapsulesPage.java
│   │   │   │   ├── CheckoutPage.java 
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

### BasePage

A classe BasePage foi criada como uma base para as demais páginas. Ela compartilha o WebDriver e WebDriverHelper, bem como métodos comuns utilizados por outras classes de Page. O objetivo é promover a reusabilidade e manter uma estrutura consistente entre as páginas.

### CapsulesPage

A classe CapsulesPage é responsável por mapear e manipular os elementos da página Capsules. Ela herda da BasePage para aproveitar funcionalidades comuns e estabelecer uma estrutura consistente.


### CheckoutPage

A classe CheckoutPage foi criada para mapear e manipular os elementos da página Checkout. Seguindo o padrão Page Object, ela encapsula a lógica relacionada à página de Checkout, promovendo a modularidade e facilitando a manutenção.


## Benefícios do Padrão Page Object

- **Reusabilidade:** Compartilhamento de métodos e funcionalidades comuns entre as páginas.

- **Manutenção Facilitada:** Alterações na interface do usuário são isoladas nas classes de Page, reduzindo o impacto em outros locais do código.

- **Legibilidade Aprimorada:** Código mais claro e compreensível, pois as interações com a interface são encapsuladas nas classes de Page.

- **Fluxo de Teste Mais Explícito:** Métodos significativos nas classes de Page tornam o fluxo de teste mais descritivo e fácil de entender.

## Como Utilizar

1. **Crie uma Classe para Cada Página:**
   - Para cada página da aplicação, crie uma classe que herde da `BasePage`.

2. **Mapeie os Elementos da Página:**
   - Utilize localizadores (By) para mapear os elementos da página na classe de Page correspondente.

3. **Encapsule a Lógica da Página:**
   - Mova a lógica de interação com a página para a classe de Page correspondente, encapsulando as operações relacionadas àquela página.

4. **Herança e Compartilhamento:**
   - Aproveite a herança da `BasePage` para compartilhar métodos e funcionalidades comuns entre as páginas.

5. **Crie Testes Claros e Modulares:**
   - Nos testes, utilize os métodos das classes de Page para criar testes mais claros e modulares.
