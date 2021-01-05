# Hello World

[TOC]

## Inicializadores

Os inicializadores são parte importante da magia do *Spring Boot*, usados para limitar a quantia de configuração de dependência manual que precisa ser feita. Para utilizar o *Spring Boot* de forma eficaz, é necessário conhecer os inicializadores.

Um inicializador é, essencialmente, um conjunto de dependências (como um POM Maven) específicas do tipo de aplicativo que o inicializador representa.

Todos os inicializadores usam a convenção de nomenclatura: `spring-boot-starter-XYZ`, em que `XYZ` é o tipo de aplicativo que você deseja desenvolver. Estes são alguns inicializadores populares do *Spring Boot*:

- `spring-boot-starter-web`: é utilizado para desenvolver serviços da web RESTful usando Spring MVC e Tomcat como contêiner do aplicativo integrado.

- `spring-boot-starter-jersey`: é uma alternativa ao spring-boot-starter-web que usa o Apache Jersey em vez do Spring MVC.

- `spring-boot-starter-jdbc` é utilizado para definição do conjunto de conexões do JDBC. Baseia-se na implementação do conjunto de conexões do JDBC do Tomcat.

## Configuração

Para iniciar um projeto é necessário criar arquivo de configuração `pom.xml`. Este arquivo também pode ser gerado pelo site <http://start.spring.io>. Para executar o exemplo `Hello World` será usado o iniciador `spring-boot-starter-web`, portanto deve estar presentes nas dependências do projeto.

O arquivo `pom.xml` gerado será algo como:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.0.BUILD-SNAPSHOT</version>
        <relativePath/>
    </parent>
    <groupId>com.example</groupId>
    <artifactId>hello_world</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>hello_world</name>
    <description>Hello World</description>

    <properties>
        <java.version>12</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        ...
    </repositories>
    <pluginRepositories>
        ...
    </pluginRepositories>

</project>
```

A estrutura criada será algo como:

```txt
.
|-- pom.xml
|-- src
|   |-- main
|       |-- java
|           |--HelloWorldApplication.java
|-- target
```

A classe gerada é mostrada abaixo:

```java
package com.example.hello_world;

/* imports */

@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
```

De maneira geral, o *Spring Boot* se preocupa com 4 coisas principais em sua inicialização:

1. Configurar automaticamente o seu projeto a partir das dependências que você declara no sistema de build (`pom.xml`) sobre o qual o projeto é construído. Isso é feito pela anotação `@EnableAutoConfiguration`.

2. Varrer a aplicação em busca de classes que possuam certas anotações específicas (`@Repository`, `@Service`, `@Configuration` e `@Controller`) que informem que essas classes devem ter seu ciclo de vida gerenciado pelo Spring. Essas classes são os *beans* e o fruto desse gerenciamento nada mais é do que a injeção de dependência (`@Autowired`). O ato de varrer é disparado pela anotação `@ComponentScan`.

3. Varrer a aplicação em busca de classes com notação `@ConfigurationProperties`. O ato de varrer é disparado pela anotação `@ConfigurationPropertiesScan`

4. Definir que a própria classe que contém o método main seja ela mesma um *bean*, anotando-a com `@Configuration`. Por quê? Para permitir que você possa, caso queria, ter outros beans (já previamente escaneados pelo item 2) injetados nessa classe, poupando você da necessidade de criar classes adicionais para isso. Lembrando que só uma classe *bean* (ou seja, gerenciada pelo Spring) pode ter injetados nela outros *beans*.

Para conveniência, a anotação `@SpringBootApplication` incorpora as quatro anotações acima. Na aplicação, deve ser adicionada somente **uma única** anotação `@SpringBootApplication` ou `@EnableAutoConfiguration`. Geralmente, é recomendado que seja adicionado apenas um ou outro à classe `@Configuration` principal.

Para testar a aplicação Web modificamos a classe `HelloWorldApplication.java` para:

```java
package com.example.hello_world;

/* imports */

@SpringBootApplication
@RestController
public class HelloWorldApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
```

Onde:

**`@RestController`**: define a classe que irá executar papel do controlador Web, ou seja, irá lidar com as requisições REST.

**`@RequestMapping`**: define o mapeamento ente a rota da requisição e o método que será chamado.

Para rodar a aplicação devemos executar o comando `run` do plugin `spring-boot`:

```bash
mvn spring-boot:run
```

Para testar basta acessar o endereço <http://localhost:8080> pelo navegador.

O *Spring Boot* **empacota o aplicativo e suas dependências** em um único JAR executável, criando algo denominado *Fat JAR*.

Para executar a aplicação através do *JAR*, primeiramente execute o comando abaixo para criar o *Fat JAR*.

```bash
mvn clean package
```

Para rodar o *JAR*, execute:

```bash
java -jar CAMINHO/target/hello_world-0.0.1-SNAPSHOT.jar
```
