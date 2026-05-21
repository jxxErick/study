# Anotações — Testes em Java / JUnit
---
# 3. Testando sem Ferramenta

  

## Objetivo

Entender como funcionam testes manualmente antes de usar frameworks.

  

## Estrutura básica

1. Criar método

2. Executar

3. Comparar resultado esperado

4. Exibir sucesso/erro

  

## Exemplo

```java

public class Calculadora {

  

    public int somar(int a, int b) {

        return a + b;

    }

}

```

  

```java

public class Main {

  

    public static void main(String[] args) {

  

        Calculadora calc = new Calculadora();

  

        int resultado = calc.somar(2, 3);

  

        if(resultado == 5) {

            System.out.println("Teste OK");

        } else {

            System.out.println("Teste FALHOU");

        }

    }

}

```

  

## Problemas do teste manual

- Muito repetitivo

- Difícil manutenção

- Sem automação

- Pouco organizado

- Escala mal

  

---

  

# 4. JUnit

  

## O que é

Framework de testes para Java.

  

## Dependência Maven

```xml

<dependency>

    <groupId>org.junit.jupiter</groupId>

    <artifactId>junit-jupiter</artifactId>

    <version>5.10.0</version>

    <scope>test</scope>

</dependency>

```

  

## Estrutura básica

```java

import org.junit.jupiter.api.Test;

  

public class CalculadoraTest {

  

    @Test

    void deveSomarDoisNumeros() {

  

    }

}

```

  

## Convenção

- Classe: `NomeClasseTest`

- Método: descrição do comportamento

  

---

  

# 5. Organização dos arquivos de teste

  

## Estrutura recomendada

```text

src

 ├── main

 │    └── java

 └── test

      └── java

```

  

## Regra importante

Cada classe principal deve possuir sua classe de teste correspondente.

  

## Exemplo

```text

Calculadora.java

CalculadoraTest.java

```

  

## Boas práticas

- Um cenário por teste

- Nome descritivo

- Independência entre testes

  

---

  

# 6. Assertivas

  

## Objetivo

Validar comportamento esperado.

  

## Principais asserts

  

### assertEquals

```java

assertEquals(10, resultado);

```

  

### assertTrue

```java

assertTrue(valor > 0);

```

  

### assertFalse

```java

assertFalse(lista.isEmpty());

```

  

### assertNull

```java

assertNull(usuario);

```

  

### assertNotNull

```java

assertNotNull(usuario);

```

  

### assertThrows

```java

assertThrows(

    RuntimeException.class,

    () -> metodo()

);

```

  

---

  

# 7. AssertThat

  

## Objetivo

Criar asserts mais legíveis.

  

## Dependência

Hamcrest.

  

## Exemplo

```java

assertThat(nome, is("Erick"));

```

  

## Exemplos úteis

```java

assertThat(numero, greaterThan(10));

  

assertThat(lista, hasSize(3));

  

assertThat(texto, containsString("Java"));

```

  

## Benefícios

- Melhor leitura

- Mais fluido

- Mais expressivo

  

---

  

# 8. Formas de dividir um teste

  

## AAA Pattern

  

### Arrange

Preparar dados

  

### Act

Executar ação

  

### Assert

Validar resultado

  

## Exemplo

```java

@Test

void deveSomar() {

  

    // Arrange

    Calculadora calc = new Calculadora();

  

    // Act

    int resultado = calc.somar(2, 3);

  

    // Assert

    assertEquals(5, resultado);

}

```

  

---

  

# 9. Tratamento de exceções — Parte 1

  

## Testando exceções

  

### Exemplo

```java

@Test

void deveLancarExcecao() {

  

    assertThrows(

        IllegalArgumentException.class,

        () -> {

            throw new IllegalArgumentException();

        }

    );

}

```

  

## Validando mensagem

```java

Exception ex = assertThrows(

    RuntimeException.class,

    () -> metodo()

);

  

assertEquals("Erro", ex.getMessage());

```

  

---

  

# 10. Tratamento de exceções — Parte 2

  

## Não lançar exceção

```java

assertDoesNotThrow(() -> metodo());

```

  

## Cenários importantes

- Entrada inválida

- Divisão por zero

- NullPointer

- Regras de negócio

  

## Boa prática

Sempre testar fluxo feliz e fluxo de erro.

  

---

  

# 11. Before e After

  

## Objetivo

Executar ações antes/depois dos testes.

  

## BeforeEach

Executa antes de cada teste.

  

```java

@BeforeEach

void setup() {

  

}

```

  

## AfterEach

Executa depois de cada teste.

  

```java

@AfterEach

void cleanup() {

  

}

```

  

## BeforeAll

Executa uma única vez.

  

```java

@BeforeAll

static void iniciar() {

  

}

```

  

## AfterAll

Executa no final.

  

```java

@AfterAll

static void finalizar() {

  

}

```

  

---

  

# 12. Ordem de execução dos testes

  

## Padrão

JUnit não garante ordem.

  

## Definindo ordem

```java

@TestMethodOrder(OrderAnnotation.class)

```

  

## Exemplo

```java

@Order(1)

@Test

void teste1() {

  

}

```

  

## Importante

Evitar dependência entre testes.

  

---

  

# 13. Desafio

  

## Ideias comuns

- Criar testes completos

- Cobrir cenários

- Aplicar asserts

- Validar exceções

  

## Foco

- Legibilidade

- Cobertura

- Organização

  

---

  

# 14. TDD — Parte 1

  

## Test Driven Development

  

Fluxo:

1. Criar teste

2. Ver teste falhar

3. Implementar solução

4. Refatorar

  

## Ciclo RED → GREEN → REFACTOR

  

### RED

Teste falha

  

### GREEN

Implementação mínima

  

### REFACTOR

Melhorar código

  

---

  

# 15. TDD — Parte 2

  

## Benefícios

- Código mais seguro

- Menos bugs

- Melhor design

- Refatoração segura

  

## Características

- Pequenos passos

- Testes rápidos

- Feedback constante

  

---

  

# 16. TDD — Parte 3

  

## Regras importantes

- Não escrever produção sem teste

- Não exagerar implementação

- Refatorar constantemente

  

## Objetivo principal

Garantir comportamento correto.

  

---

  

# 17. @Ignore e Assumptions

  

## Ignorando testes

  

### JUnit 4

```java

@Ignore

```

  

### JUnit 5

```java

@Disabled

```

  

## Assumptions

Executa apenas em determinadas condições.

  

```java

assumeTrue(condicao);

```

  

## Exemplo

```java

assumeTrue("dev".equals(ambiente));

```

  

---

  

# 18. Testes parametrizáveis

  

## Objetivo

Executar mesmo teste com múltiplos valores.

  

## Exemplo

```java

@ParameterizedTest

@ValueSource(ints = {1, 2, 3})

void deveSerPositivo(int numero) {

  

    assertTrue(numero > 0);

}

```

  

## CSV Source

```java

@CsvSource({

    "1,2,3",

    "2,3,5"

})

```

  

---

  

# 19. Matchers Próprios

  

## Objetivo

Criar validações personalizadas.

  

## Exemplo conceitual

```java

public class MeuMatcher {

  

}

```

  

## Uso

```java

assertThat(valor, meuMatcher());

```

  

## Benefícios

- Reutilização

- Melhor leitura

- Centralização de regra

  

---

  

# 20. Desafio

  

## Possíveis objetivos

- Criar suíte de testes

- Cobrir regras de negócio

- Trabalhar TDD

- Validar exceções

  

---

  

# 21. Suíte de testes

  

## Objetivo

Executar vários testes juntos.

  

## Exemplo

```java

@Suite

@SelectClasses({

    CalculadoraTest.class,

    UsuarioTest.class

})

public class SuiteTest {

  

}

```

  

## Benefícios

- Organização

- Execução agrupada

- Facilidade automação

  

---

  

# Resumo rápido

  

## Principais anotações

```java

@Test

@BeforeEach

@AfterEach

@BeforeAll

@AfterAll

@ParameterizedTest

@Disabled

```

  

## Principais asserts

```java

assertEquals

assertTrue

assertFalse

assertNull

assertNotNull

assertThrows

assertDoesNotThrow

```

  

## Conceitos importantes

- TDD

- AAA

- Independência dos testes

- Legibilidade

- Cobertura

- Automação