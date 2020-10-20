# EnableAutoConfiguration

[TOC]

## Introdução

Configura a aplicação automaticamente baseando nos *JARs* presentes no `CLASSPATH` e em como os *beans* foram definidos:

- O *Spring Boot* utiliza os *JARs* cuja presença no `CLASSPATH` foi especificada para formar uma opinião acerca de como configurar um comportamento automático específico. Por exemplo, se você tem o *JAR* do banco de dados *H2* no seu caminho de classe e não configurou nenhum outro *bean* *DataSource*, seu aplicativo será configurado automaticamente com um banco de dados em memória.

- O *Spring Boot* usa a maneira como os *beans* foram definidos para determinar como se configurar automaticamente. Por exemplo, se seus *beans* *JPA* forem anotados com `@Entity`, o *Spring Boot* configurará o *JPA* automaticamente para não haver necessidade de um arquivo `persistence.xml`.

```java
@EnableAutoConfiguration
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
```

Para ignorar as configurações de uma classe utilize:

```java
@EnableAutoConfiguration(exclude={Exemplo.class})
```
