# SpringBootApplication

[TOC]

## Introdução

O mesmo que `@EnableAutoConfiguration` + `@ComponentScan` + `@Configuration` + `@ConfigurationPropertiesScan`.

De maneira geral, o *Spring Boot* se preocupa com 4 coisas principais em sua inicialização:

1. Configurar automaticamente o seu projeto a partir das dependências que você declara no sistema de build (`pom.xml`) sobre o qual o projeto é construído. Isso é feito pela anotação `@EnableAutoConfiguration`.

2. Varrer a aplicação em busca de classes que possuam certas anotações específicas (`@Repository`, `@Service`, `@Configuration` e `@Controller`) que informem que essas classes devem ter seu ciclo de vida gerenciado pelo Spring. Essas classes são os *beans* e o fruto desse gerenciamento nada mais é do que a injeção de dependência (`@Autowired`). O ato de varrer é disparado pela anotação `@ComponentScan`.

3. Varrer a aplicação em busca de classes com notação `@ConfigurationProperties`. O ato de varrer é disparado pela anotação `@ConfigurationPropertiesScan`

4. Definir que a própria classe que contém o método main seja ela mesma um *bean*, anotando-a com `@Configuration`. Por quê? Para permitir que você possa, caso queria, ter outros beans (já previamente escaneados pelo item 2) injetados nessa classe, poupando você da necessidade de criar classes adicionais para isso. Lembrando que só uma classe *bean* (ou seja, gerenciada pelo Spring) pode ter injetados nela outros *beans*.

Para conveniência, a anotação `@SpringBootApplication` incorpora as quatro anotações acima. Na aplicação, deve ser adicionada somente **uma única** anotação `@SpringBootApplication` ou `@EnableAutoConfiguration`. Geralmente, é recomendado que seja adicionado apenas um ou outro à classe `@Configuration` principal.

```java
@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
```

Sem a notação `@ConfigurationPropertiesScan` ou `@ComponentScan`, as classes anotadas com `@Component` e `@ConfigurationProperties` devem ser incorporadas através de `@Import`. Como no exemplo:

```java
@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@Import({ MyConfig.class, MyAnotherConfig.class })
public class Application {

    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }
}
```
