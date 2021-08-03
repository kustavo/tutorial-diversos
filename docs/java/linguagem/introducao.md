# Introdução

[TOC]

## Linguagem

Java é uma linguagem de programação orientada a objetos desenvolvida na década de 90 por uma equipe de programadores chefiada por James Gosling, na empresa Sun Microsystems. Em 2008 o Java foi adquirido pela empresa Oracle Corporation. A linguagem Java é compilada para um *bytecode* que é interpretado por uma máquina virtual (Java Virtual Machine - JVM).

## Compilação

A compilação dos arquivos `.java` deve ser feita a partir do diretório raiz do projeto.

- Compilando arquivos já separados em diretórios referentes aos pacotes:

  ```bash
  javac pacote/Arquivo1.java pacote/Arquivo2.java
  ou
  javac pacote/*.java
  ```

- Se os arquivos não tiverem separados em diretórios referentes ao pacotes, é usado o parâmetro `-d` que irá criar os diretórios automaticamente.

   ```bash
    javac -d . Arquivo1.java Arquivo2.java
    ou
    javac -d . *.java
   ```

## Criação de executável

Criação do arquivo executável `.jar`.

- Com o arquivo externo `MANIFEST.mf`:

  ```txt
  Manifest-Version: 1.0
  Main-Class: br.com.teste.Arquivo
  SplashScreen-Image: logo.png
  ```

  ```bash
    jar -cvfm Arquivo.jar MANIFEST.mf Arquivo.class
  ```

- Sem o arquivo `MANIFEST.mf`, estabelecendo o ponto de entrada:

  ```bash
  jar -cvfe Arquivo.jar <MainClass> Arquivo.class
  ```

### Comando -d

O comando `-d` indicação do destino de onde o .class será gerado.

```bash
javac -d bin Arquivo.java # Arquivo.class será gerado no diretório bin
```

### Comando -ch

*Classpaths* são variáveis de localização de um diretório que podem conter as classes que serão necessárias para a execução do sistema.

```bash
-ch /src/teste1/dir1:/src/teste2/dir2
```

No exemplo, será feita uma busca nos diretórios `/src/teste1/dir1` e `/src/teste2/dir2` para procurar as classes necessárias para a execução do comando.

## Execução

- Execução do arquivo `.class`

  ```bash
  java Arquivo
  ```

- Execução do arquivo `.jar`

  ```bash
  java Arquivo.jar
  ```

## Package

### Nomenclatura

Os nomes dos pacotes são escritos em letras minúsculas para evitar conflito com os nomes de classes ou interfaces.

As empresas usam seu nome de domínio da Internet invertida para começar seus nomes de pacotes - por exemplo, `com.example.mypackage` para um pacote chamado `mypackage` criado por um programador em `example.com`.

As colisões de nomes que ocorrem dentro de uma única empresa precisam ser tratadas por convenção dentro dessa empresa, talvez incluindo a região ou o nome do projeto após o nome da empresa (por exemplo, `com.example.region.mypackage`).

Pacotes na própria linguagem Java começam com java. ou javax.

Em alguns casos, o nome de domínio da Internet pode não ser um nome de pacote válido. Isso pode ocorrer se o nome do domínio contiver um hífen ou outro caractere especial, se o nome do pacote começar com um dígito ou outro caractere ilegal para usar como o início de um nome Java ou se o nome do pacote contiver uma palavra-chave reservada Java como "int". Nesse caso, a convenção sugerida é adicionar um sublinhado. Por exemplo:

| Nome do domínio               | Nome do prefixo do pacote   |
|-------------------------------|-----------------------------|
| `hyphenated-name.example.org` | org.example.hyphenated_name |
| `example.int`                 | int_.example                |
| `123name.example.com`         | com.example._123name        |

## Wrappers

O uso de classes *wrappers* é importante quando precisamos encapsular tipos primitivos, de modo que eles possam participar de funcionalidades relacionadas à orientação a objetos. Um exemplo disso é seu uso em operações com *collections*.

Tipos primitivos em Java a tornam uma linguagem híbrida. Porém, as classes *wrappers* contornam esse problema, pois permitem que tipos primitivos sejam encapsulados e possam ser empregados em operações que exijam o uso de objetos, tais como em *collections*.

Cada tipo primitivo em Java possui uma classe empacotadora correspondente:

| Primitivo | Classe Wrapper | Argumentos do construtor |
| --------- | -------------- | ------------------------ |
| boolean   | Boolean        | boolean, String          |
| Byte      | Byte           | byte, String             |
| Char      | Character      | char                     |
| double    | Double         | double, String           |
| Float     | Float          | float, double, String    |
| Int       | Integer        | ...                      |

Convertendo um *wrapper* em um tipo primitivo:

```java
int i = 20;
Integer iw = Integer.valueOf(i);
// ou
Integer iw = i;
```

Convertendo um tipo primitivo em *wrapper*:

```java
Integer iw = 20;
int i = Integer.valueOf(iw);
// ou
int i = iw.intValue();
```

A comparação entre *wrappers* é feita usando o método `equals()`. O operador `==` compara apenas a referência do objeto (endereço de memória).

```java
Integer a = 20;
Integer b = 20;
a.equals(b);   // true
a == b;        // false
```

## Termos

### Campo

Campo (em inglês, field) algumas vezes chamado também de variável de classe ou de instância, é o que guarda um estado do objeto. É uma das formas de se criar um campo. Essencialmente é uma variável. É um mecanismo concreto e existente nas implementações das linguagens e outras ferramentas.

Um campo Java é um bloco de memória que possui um nome, tipo e tamanho que correspondem a ele.

```java
private String name = "Marcin";
```

### Campo

Define qualidade (característica) de alguma parte ou característica de um objeto (uma parte do código, por exemplo). É um metadado (adjetivo) e não o dado, como o campo.

É uma característica em específico que um objeto terá, mas não se define bem como esta característica será disponível no objeto. Não importa se será um campo, uma propriedade, um método, ou outra coisa, ou até um conjunto desses mecanismos.

Por exemplo, uma pessoa pode ter como campos, um nome e um endereço. Já os campos poderiam ser: nome, sobrenome, rua, bairro, número, etc.

### Propriedade

É um membro de uma classe que fornece uma informação sobre o objeto/classe.

Uma propriedade Java também é muito parecida com um campo. A diferença real está no escopo pretendido. Os campos devem ser privados ou protegidos no escopo, o que significa que o acesso é restrito. As propriedades devem ter escopo público, o que significa que o acesso não é restrito. Podemos pensar que em Java a propriedade em si é um método (geralmente getters e setters). Muitas vezes ela acessa o estado que está efetivamente em um campo.

```java
private String name = "Marcin";

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}
```

### Variável

Uma variável é o nome dado a um local de memória. É a unidade básica de armazenamento em um programa. Pode ser sinônimo de campo.

### Parâmetro

Parâmetro é a variável que irá receber um valor em uma função (ou método). O parâmetro faz parte da assinatura do método.

```java

  public void metodo(String parametroA, String parametroB, String parametroC) {}
```

### Argumento

Argumento é o valor (que pode originar de uma variável ou expressão) que é passado para a função (ou método) durante a sua chamada.

```java

  String argumentoA;
  String argumentoB;

  /*
   * Passando 3 argumentos para o método
   */
  metodo(argumentoA, argumentoB, 'arguemntoC') {}
```

### Variância

No contexto da programação, variância descreve as propriedades de uma estrutura variar dentro de uma hierarquia de tipos.

```java
public class X {
    Object getValue() { return null; }
}

public class Y extends X {
    String getValue() { return null; }
}
```

Existem três tipos de variância:

- **Covariância**: significa que se a classe `Y` é mais específica que `X` (`Y` < `X`) então qualquer método de `Y` que faça override a um método de `X` tem de retornar um tipo **igual ou mais específico**. Neste caso, o tipo de retorno do método que faz override é `String`, que é mais específico que `Object`.

- **Contravariância**: contravariância é efetivamente o inverso de covariância. Significa que se a classe `Y` é mais específica que `X` (`Y` < `X`) então qualquer método de `Y` que faça override a um método de `X` tem de retornar um tipo **igual ou mais genérico**.

  Em Java não há contravariância e *overriding* de métodos é sempre invariante. No entanto possível ter contravariância usando *wildcards* de *generics* (`<? super Classe>`).
  
- **Invariância**: não há flexão de tipos. Em Java *overriding* de métodos é invariante, ou seja, para se redefinir um método numa subclasse, os parâmetros têm de ser exatamente do mesmo tipo da superclasse.

  ```java
    public class A {

      boolean equals(A object) {
          return true;
      }
  }
  ```

  Como o *overriding* é invariante, não estamos na realidade fazendo *override* do método `boolean equals(Object o)` mas sim *overload*, adicionando um novo método com outra assinatura.

  A solução desde Java 5 é adicionar a anotação `@Override` que vai permitir que o compilador detecte estes casos e informe que não estamos redefinindo um método.

### POJO

Plain Old Java Object (tradução, Velho e Simples Objeto Java) é um referencia a objetos que não dependem da herança de interfaces ou classes de *frameworks* externos.

De maneira ideal, um POJO é um objeto Java não vinculado a nenhuma restrição além daquelas impostas pela *Java Language Specification*; ou seja, um POJO **não** deve ter que:

- Estenda classes pré-especificadas, como em:

    ```java
    public class Foo extends javax.servlet.http.HttpServlet { ...
    ```

- Implemente interfaces pré-especificadas, como em:

    ```java
    public class Bar implements javax.ejb.EntityBean { ...
    ```

- Contêm anotações pré-especificadas, como em:

    ```java
    @javax.persistence.Entity public class Baz { ...
    ```

O código abaixo exemplifica um POJO que segue o padrão JavaBean:

```java
 public class Carro implements java.io.Serializable {  

    private String  nome;

    private String cor;

    public Carro() {}

    public Carro(String nome, String cor) {
         this.nome = nome;
         this.cor = cor;
    }

    public String getCor() {
         return cor;
    }

    public void setCor(String cor) {
         this.cor = cor;
    }

    public String getNome() {
         return nome;
    }

    public void setNome(String nome) {
         this.nome = nome;
    }
 }
```

### JavaBean

Um JavaBean (ou Bean) é um POJO que é serializável, possui um construtor sem argumento e permite acesso a propriedades usando métodos *getter* e *setter* que seguem uma convenção de nomenclatura simples.

### Tópicos para o estudo da linguagem

- **Concepts from core Java**

  - OOPS concepts (Data Abstraction, Encapsulation, Inheritance, Polymorphism)
  - Basic Java constructs like loops and data types
  - String handling
  - Collection framework
  - Multithreading
  - Exception handling
  - Generics
  - Synchronisation
  - Serialization & De-serialization
  - Concurrent collection

1. **Core Java Programming Introduction of Java**
   - Introduction to Java; features of Java
   - Comparison with C and C++
   - Download and install JDK/JRE (Environment variables set up)
   - The JDK Directory Structure
   - First Java Program through command prompt
   - First Java Program through Eclipse/IntelliJ/whatever-editor-you-want

1. **Data types and Operators**
   - Primitive Datatypes, Declarations, Ranges
   - Variable Names Conventions
   - Numeric Literals, Character Literals
   - String Literals
   - Arrays(One dimensional; two- dimensional)
   - Array of Object References
   - Accessing arrays, manipulating arrays
   - Enumerated Data Types
   - Non-Primitive Datatypes
   - Defining a class, variable and method in Java
   - Method Signature; method calls
   - Expressions in Java; introduction to various operators
   - Assignment Operator
   - Arithmetic Operators
   - Relational Operators
   - Logical Operators
   - Conditional Operators
   - Operator Precedence
   - Implicit Type Conversions
   - Upcasting and downcasting
   - Strict typing
   - Type conversion

1. **Control Flow statements**
   - Statements and it's various categories in Java
   - if, if-else, if-else-if
   - switch case
   - for statement(old vs enhanced)
   - while and do-while loops
   - The continue Statement; labelled continue statement
   - The break Statement; labelled break statement
   - return statement

1. **OOPS and its application in Java**
   - Classes and Objects
   - Defining a class;Defining instance variables and methods
   - Creating objects out of a class
   - Method calls via object references
   - Abstraction
   - Interfaces and Abstract classes
   - Abstract and non-abstract methods
   - Inheritance
   - extends and implements keywords in Java
   - Super class and Sub class
   - this keyword, super keyword in Java for inheritance
   - Concrete classes in Java
   - Polymorphism
   - Compile time polymorphism -- Overloading of methods
   - Run time polymorphism -- Overriding of methods
   - Method Overriding rules and method overloading rules
   - Introduction to Object class and it's methods
   - Encapsulation
   - Protection of data
   - Java Bean, POJO
   - Getters/Setters
   - Memory management in Java
   - Heap
   - Stack
  
1. **Packages**
    - Need for packages
    - What are packages; package declaration in Java
    - Import statement in Java
    - How do packages resolve name clashes?

1. **Access Modifiers in Java**
    - What are access modifiers?
    - Default
    - Protected
    - Private
    - Public

1. **Miscellaneous**
    - Var-Args
    - Reference variables, local variables, instance variables
    - Memory allocations to variables
    - Double equals operator(==) operator for primitives and objects
    - toString() method on an object

1. **Statics**
    - Static variables and methods
    - Static imports
    - Static initialization blocks; instance initialization blocks
    - Static concept in inheritance

1. **Constructors**
    - What are Constructors?
    - Properties of Constructors
    - Default and Parameterized Constructors
    - Rules for constructor implementation
    - Constructor Chaining
    - this call; super call for constructors
    - Constructors for Enumerated Data Types
    - Constructors concept for Abstract classes and interfaces

1. **Exceptions in Java**
    - What are Exceptions?
    - Need for exceptions
    - How can Exceptions be coded in Java?
    - API heirarchy for Exceptions
    - Types of Exceptions
    - Keywords in Exception API: `try`, `catch`, `finally`, `throw`, `throws`
    - Rules for coding Exceptions
    - Declaring Exceptions
    - Defining and Throwing Exceptions
    - Errors and Runtime Exceptions
    - Custom Exception

1. **Strings in Java**
    - What are Strings?
    - String heap memory and Constant Pool memory
    - Immutability in Strings
    - String creation on heap and constant pool
    - Method APIs on String; operations on Strings
    - Mutability of String Objects - StringBuilder and StringBuffer

1. **Collection Framework in Java**
    - The Collections Framework
    - The Set Interface
    - Set Implementation Classes
    - The List Interface
    - List Implementation Classes
    - The Map Interface
    - Map Implementation Classes
    - Queue Interface
    - Queue Implementation classes
    - Sorting collections using utility methods
    - equals() and hashCode contract in Java collections

1. **Generics**
    - Generics for Collections
    - Generics for class
    - Generics for methods

1. **Input-Output in Java**
    - What is a stream?
    - Overview of Streams
    - Bytes vs. Characters
    - Overview of the entire Java IO API
    - Reading a file; writing to a file using various APIs
    - Reading User input from console
    - PrintWriter Class

1. **Serialization**
    - Object Serialization
    - Serializable Interface
    - Serialization API
    - ObjectInputStream and ObjectOutput
    - Transient Fields
    - readObject and writeObject

1. **Inner Classes**
    - Inner Classes
    - Member Classes
    - Local Classes
    - Anonymous Classes
    - Static Nested Classes

1. **Threads in Java**
    - Non-Threaded Applications
    - Threaded Applications
    - Process based multitasking Vs Thread based multitasking
    - Thread API in Java
    - Creating Threads
    - States of a Thread
    - Synchronization for threads; static and non-static synchronized methods; blocks; concept - of object and class locks
    - Coordination between threads - wait, notify and notifyAll methods for inter-thread - communication

1. **JDBC**
    - What is JDBC; introduction; everything about JDBC
    - create database and a schema
    - Writing JDBC code to connect to DB
    - CRUD Operations with JDBC
  
1. **Java 8 Features**
   - Java Lambda Expressions
   - Java Method References
   - Java Functional Interfaces
   - Java Stream Tutorial
   - Java Stream Filter Tutorial
   - Java Interface changes – introduction of default and static methods
   - Java forEach
   - Java Stream Collectors class
   - Java StringJoiner class
   - Java Optional class
   - Java Arrays Parallel Sort

1. **Java 9 Features**
   - Java 9 – REPL (JShell)
   - Factory methods to create immutable List, Set and Map
   - Introduction of Private methods in interfaces
   - Try with resources enhancements
   - Anonymous inner class and diamond operator
   - SafeVarargs Annotation (with examples)
   - Java 9 – Stream API Enhancements (With Examples)
   - Java 9 Modules

## Links

- Linguagem Java
  - <https://www.javatpoint.com/java-tutorial>

- Projetos em Java em destaque
  - <https://github.com/Vedenin/useful-java-links>
  - <https://githublists.com/lists/akullpp/awesome-java>
