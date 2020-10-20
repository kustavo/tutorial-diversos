# Configuration

[TOC]

## Introdução

Indica que uma classe declara um ou mais métodos `@Bean` e pode ser processada pelo contêiner Spring para gerar definições de *bean* e solicitações de serviço para esses *beans* em tempo de execução. `@Configuration` engloba a anotação `@Component`. Classes anotadas com `@Configuration` mão devem ser classes `final`.

```java
@Configuration
public static class Config {

    @Bean
    public ClasseA getClasseA() {
        return new ClasseA();
    }

    @Bean
    public ClasseB getClasseB() {
        return new ClasseB(getClasseA());
    }
}
```

Se a anotação `@Component` for usada no lugar de `@Configuration` o spring criará um *bean* *singleton* de `ClasseA`, mas `ClasseB` obterá outra instância de `ClasseA` que está fora do controle de contexto do spring.

Se você usar `@Configuration`, todos os métodos marcados como `@Bean` serão agrupados em um *wrapper* `CGLIB` que funciona como se fosse a primeira chamada desse método, o corpo do método original será executado e o objeto resultante será registrado no contexto do Spring. Todas as chamadas adicionais retornam o *bean* recuperado do contexto.

```java
@Component
public static class Config {

    @Bean
    public ClasseA getClasseA() {
        return new ClasseA();
    }

    @Bean
    public ClasseB getClasseB() {
        return new ClasseB(getClasseA());
    }
}
```

Usando `@Component` a forma equivalente seria:

```java
@Component
public static class Config {

    @Autowired
    ClasseA classeA;

    @Bean
    public ClasseA getClasseA() {
        return new ClasseA();
    }

    @Bean
    public ClasseB getClasseB() {
        return new ClasseB(classeA);
    }
}
```
