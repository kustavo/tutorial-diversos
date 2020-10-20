# Qualifier

[TOC]

## Introdução

Esta anotação é usada junto com a anotação `@Autowired`. Essa anotação é usada para evitar confusão que ocorre quando você cria mais de um *bean* do mesmo tipo e deseja conectar apenas um deles a uma propriedade. `@Qualifier` pode ser especificado em argumentos de construtores individuais ou parâmetros de método.

```java
package com.example.auto_wired_match_qualifier;

/* imports */

@SpringBootApplication
public class AutoWiredMatchQualifierApplication {

    public static void main(String[] args) {

        SpringApplication.run(AutoWiredMatchQualifierApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(AutoWiredMatchQualifierApplication.class.getPackage().getName());
        context.refresh();

        ClassA classA = context.getBean(ClassA.class);
        classA.metodo();
    }
}

@Component
class ClassA {

    /*
     * Indicando a classe que retornou o bean
     */
    @Autowired
    @Qualifier("classB1")
    ClassB classB;

    /*
     * Indicando o método que retornou o bean
     */
    @Autowired
    @Qualifier("getClassC1")
    ClassC classC;

    void metodo() {
        System.out.println(classB.getClass().getName());
        System.out.println(classC.metodoUsado);
    }
}

@Component
class ClassB1 extends ClassB {

    @Bean
    ClassB1 getClassB1(){
        return new ClassB1();
    }
}

@Component
class ClassB2 extends ClassB {

    @Bean
    ClassB2 getClassB2(){
        return new ClassB2();
    }
}

class ClassB { }

@Component
class ClassC {

    String metodoUsado;

    ClassC() {}

    ClassC (String metodoUsado) {
        this.metodoUsado = metodoUsado;
    }

    @Bean
    ClassC getClassC1(){
        return new ClassC("metodo getClassC1");
    }

    @Bean
    ClassC getClassC2(){
        return new ClassC("metodo getClassC2");
    }
}

// Saida:
// > com.example.auto_wired_match_qualifier.ClassB1
// > metodo getClassC1
```
