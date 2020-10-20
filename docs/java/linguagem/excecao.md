# Exceções

[TOC]

## Introdução

Exceções são objetos que sinalizam que ocorreu algum problema no tempo de execução de um programa. Tais problemas podem ocorrer ao acessar um índice de vetor que esteja além de seu tamanho, ao tentar acessar algum membro de uma referência nula, ao dividir um número por zero, entre outros.

Ao ocorrer uma exceção, a JVM cria uma instância de um objeto da classe **Exception** ou de uma de suas subclasses (o que é chamado de lançamento de exceção) e encerra a execução da aplicação caso essa exceção não seja tratada (o que é chamado de captura de exceção).

## Hierarquia

A classe `Throwable` é a superclasse de `Exception` e `Error` e indiretamente de `RuntimeException`.

<div class='imagem' markdown='1'>

![java-throwable-hierarquia](_excecao/java-throwable-hierarquia.png)

</div>

## Tipos

- **Exceções não checadas:**
  
  - Representam defeitos no programa (bugs). Geralmente ocorrem de problemas na construção da lógica do algoritmo e são evitáveis.

  - São subclasses de `RuntimeException`,

  - Um método não é obrigado a estabelecer uma política para as exceções não checadas lançadas por sua **execução** (e quase sempre nunca o fazem).
  
  - Exemplos:
    - `ArithmeticException`,
    - `ArrayIndexOutOfBoundsException`,
    - `NullPointerException`,
    - `NumberFormatException`,
    - `IllegalArgumentException`.

- **Exceções checadas:**

  - Representam condições inválidas em áreas fora do controle imediato do programa (problemas de entradas inválidas do usuário, banco de dados, falhas de rede, arquivos ausentes).

  - São subclasses de `Exception`.

  - Um método não é obrigado a estabelecer uma política para os erros (não checado).

  - Um método é obrigado a estabelecer uma política para todas as exceções checadas lançadas por sua **implementação** (ou passar a exceção checada mais acima na pilha, ou manipulá-la de alguma forma).

  - Exemplos:
    - `SQLException`,
    - `IOException`,
    - `ParseException`.

- **Erros:**
  
  - Além das exceções, há também a possibilidade de lançamento de erros. Embora a ocorrência de erros seja muito mais remota, no geral, são irreparáveis e causam o término abrupto da execução de um programa.
  
  - São subclasses de `Error`.
  
  - Exemplos:
    - estouro da memória.

## Instrução throws

A palavra-chave `throws` declarada na assinatura do método indica que há a possibilidade de ser lançada uma exceção em seu bloco de instruções. `Throwable` e qualquer outra de suas subclasses podem ser utilizadas. São opcionais para exceções não checadas e erros.

```java
package exemplo_excecao_throws;

class Exemplo {

    void metodoA() throws Throwable {
        System.out.println("throws Throwable");
    }

    void metodoB() throws Exception {
        System.out.println("throws Exception");
    }

    /*
     * O metodoC deve utilizar "throws Throwable" para passar a resolução das
     * exceções de metodoA e metodoB para acima da pilha. Utilizar
     * "throws Throwable, Exception" não é necessário já que Exception é subclasse
     * de Throwable. Outra solução seria não utilizar "throws Throwable" mas ser
     * obrigado a tratar a exceção.
     */
    void metodoC() throws Throwable {

        metodoA();
        metodoB();
    }
}
```

## Instrução throw

A palavra-chave `throw` lança uma instância de uma exceção e interrompe o fluxo normal de execução do programa. Qualquer subclasse de `Throwable` pode ser utilizada.

O lançamento de exceções não checadas e erros não exige que elas sejam declaradas na assinatura do método. A utilização de `throws`, então, é opcional. Enquanto que para exceções checadas a declaração é obrigatória.

```java
class Exececao {

    void metodoA() {  // throws opcional

        throw new RuntimeException();
    }


    void metodoB() throws Exception { // throws obrigatório

        throw new Exception();
    }

}
```

Para exceções não checadas e erros o compilador não verifica se a classe da exceção declarada é [covariante](https://pt.wikipedia.org/wiki/en:Covariant_return_type) com a classe da exceção lançada.

Já para exceções checadas é exigido pelo compilador que a exceção lançada seja covariante da exceção declarada.

```java
package exemplo_excecao_throw;

import java.io.IOException;

class Excecao {

    void metodoA() throws Exception {

        /*
         * Covariantes. Exception é superclasse de IOException.
         */
        throw new IOException();
    }

    void metodoB() throws Error {

        /*
         * Não Covariantes. RuntimeException não é subclasse de Error. Entretanto é uma
         * exceção NÃO checada.
         */
        throw new RuntimeException(); // ERRO!
    }

    void metodoC() throws IOException {

        /*
         * Não Covariantes. Exception não é subclasse de IOException e sim superclasse.
         * É uma exceção checada.
         */
        // throw new Exception(); // ERRO!
    }
}
```

## Instruções try-catch-finally

As palavras-chave `try`, `catch` ou `finally` são utilizadas para controlar o fluxo de um trecho do programa que pode lançar exceção, e no caso de uma exceção ser lançada executar determinados blocos de instruções. Podemos dizer que estas palavras-chave são usadas para tratar a exceção.

### try

A palavra-chave `try` é utilizada para delimitar um bloco em que métodos chamados podem lançar exceções a serem tratadas. Essas exceções lançadas podem ser capturadas por uma cláusula `catch`, e em seu bloco pode ser chamado instruções para tratamento das exceções lançadas.

<div class='importante' markdown='1'>

Encadeado à `try` deve sempre haver o `catch` ou `finally`. Caso após `try` haja `catch` e `finally` a ordem sempre deve ser `try-catch-finally`

</div>

```java
// try {} // ERRO!

try {} catch(Throwable t){}

try {} finally {}

try {} catch(Throwable t){} finally {}

// try {} finally{} catch(Throwable t){} // ERRO!
```

### catch

A instrução `catch` captura exceções e erros lançados dentro de `try` e possibilita o desenvolvimento de instruções para tratá-los.

Métodos que declarem exceções checadas devem sempre ser chamados ou dentro de `try` e ter uma cláusula `catch` com um tipo covariante ou dentro de métodos utilizando `throws` para repassar o tratamento da exceção para quem chamar o método.

No caso do método chamador relançar a exceção, quando chamado deverá estar dentro de `try-catch` ou dentro de outro método que também relance a exceção.

```java
package exemplo_excecao_try_catch_finally;

class Exemplo {

    static void metodoLancaExcecao() throws Exception {

        throw new Exception();
    }

    /*
     * Obrigatório utilizar "throws Exception" na chamada da função.
     */
    static void metodoRepassaExcecao() throws Exception {

        metodoLancaExcecao();
    }

    /*
     * Como a exceção será tratada pelo método, não é necessário utilizar
     * "throws Exception" na chamada do método.
     */
    static void metodoTrataExcecao() {

        try {

            metodoLancaExcecao();

            /*
             * Não será chamado, pois foi redirecionado para o catch()
             */
            System.out.println("Não será executado");

        } catch (RuntimeException r) {

            System.out.println("RuntimeException: " + r);

        } catch (Exception e) {

            /*
             * Como a exceção foi capturada, o programa pode continuar sua execução a partir
             * do try, e não necessariamente após a instrução que lançou a exceção.
             */
            System.out.println("Exception: " + e);

        } finally {

            System.out.println("Finally");
        }

        System.out.println("Fim método");
    }

    public static void main(String[] args) {

        metodoTrataExcecao();

        System.out.println("Fim programa");
    }
}

// Saida
// > Exceção Exception: java.lang.Exception
// > Finally
// > Fim método
// > Fim programa
```

Se há mais de uma cláusula `catch` elas deverão ser dispostas da subclasse mais específica até a subclasse mais abrangente, caso contrário haverá erro na compilação.

```java
try {}
catch (IllegalArgumentException e) {} // subclasse de RuntimeException.
catch (RuntimeException e) {}         // subclasse de Exception.
catch (Exception e) {}
```

### finally

A instrução `finally` permite que um bloco seja sempre executado após a execução de um bloco de `catch` e/ou de `try`.

```java
try {
    throw new Exception();
} catch (Exception e) {
    System.out.println("Dentro de catch");
} finally {
    System.out.println("Dentro de finally");
}

// Saida:
// > Dentro de catch
// > Dentro de finally
```

No código abaixo, o rastreamento de pilha da exceção lançada por catch() é exibido após o `println()` devido à prioridade característica de `finally` em sempre ser executado.

```java
try {
    throw new Exception();
} catch (Exception e) {
    throw new RuntimeException();
} finally {
    System.out.println("É exibido ainda que catch lance uma exceção.");
}

// Saida:
// > É exibido ainda que catch lance uma exceção.
// > Exception in thread "main" java.lang.RuntimeException
```

Como `finally` sempre executa, caso ocorra uma exceção dentro do `finally` há a possibilidade de uma exceção lançada em `catch` não ser executada.

```java
try {
    throw new Exception();
} catch (Exception e) {
    throw new RuntimeException("dentro de catch");
} finally {
    throw new RuntimeException("dentro de finally");
}

// Saida:
// > Exception in thread "main" java.lang.RuntimeException: dentro de finally
```

Há uma **exceção** na regra de finally sempre executar após catch que ocorre quando em `catch` é chamado o método `System.exit()`. Esse método executa o encerramento da JVM.

```java
try {
    throw new Exception();
} catch (Exception e) {
    System.out.println("Última execução antes da JVM desligar");
    System.exit(0);
} finally {
    System.out.println("Não será executado");
}

// Saida:
// > Última execução antes da JVM desligar
```
