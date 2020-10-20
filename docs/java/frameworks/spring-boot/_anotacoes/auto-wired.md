# AutoWired

[TOC]

## Introdução

Permite a injeção da dependência do objeto implicitamente, ou seja, marca uma dependência que o Spring resolverá e injetará. De forma resumida, `@AutoWired` indica para o Spring que ele precisa instanciar o objeto para nós.

A variável de referência (dependência) anotada com `@Autowired`, será injetada pelo contêiner Spring com o `@Bean` correspondente seja `@Component`, `@Repository`, `@Service`, `@Configuration` ou `@Controller`.

*Auto wiring* no Spring significa que o contêiner Spring pode resolver automaticamente a dependências entre *beans* inspecionando o conteúdo do `ApplicationContext`.

A anotação `@Autowired` ser utilizada em:

- Campos

    ```java
    public class ClasseA {
        @Autowired
        private ClasseB classeB;
    }
    ```

- Construtores

    ```java
    public class ClasseA {

        private ClasseB classeB;

        @Autowired
        ClasseA(ClasseB classeB) {
            this.classeB = classeB
        }
    }
    ```

- Métodos (mais comumente, os setters)

    ```java
    public class ClasseA {

        private ClasseB classeB;

        @Autowired
        void setClasseB(ClasseB classeB) {
            this.classeB = classeB
        }
    }
    ```

Sem a notação `@Autowire` o cliente precisa saber instanciar os objetos no qual depende.

```java
package com.example.auto_wired;

/* imports */

@SpringBootApplication
public class AutoWiredApplication {

    public static void main(String[] args) {

        SpringApplication.run(AutoWiredApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(AutoWiredApplication.class.getPackage().getName());
        context.refresh();

        /**
         * Com @AutoWired
         */
        UserClassWired objWired = context.getBean(UserClassWired.class);
        objWired.doSomething();

        /**
         * Sem @AutoWired
         */
        UserClassNoWired objNoWired = context.getBean(UserClassNoWired.class);
        objNoWired.doSomething();
    }
}

@Component
class ClassA {
    ClassB classB;

    ClassA(ClassB classB){
        this.classB = classB;
    }

    void metodo() {
        System.out.println("Metodo do A");
        this.classB.metodo();
    }
}

@Component
class ClassB {
    ClassC classC;

    ClassB(ClassC classC){
        this.classC = classC;
    }

    void metodo() {
        System.out.println("Metodo do B");
    }
}

@Component
class ClassC {

    ClassC(){}
}

@Component
class UserClassWired {

    ClassA classA;

    @Autowired
    UserClassWired(ClassA classA) {
        this.classA = classA;
    }

    UserClassWired() {}

    void doSomething() {
        classA.metodo();
    }
}

@Component
class UserClassNoWired {

    ClassA classA;

    UserClassNoWired(ClassA classA) {
        this.classA = classA;
    }

    UserClassNoWired() {}

    /**
     * Sem @AutoWired o UserClassNoWired passa a ter responsabilidade de saber todas as
     * dependências para instanciar um objeto de ClassA.
     */
    void doSomething(){

        ClassC classC = new ClassC();
        ClassB classB = new ClassB(classC);
        this.classA = new ClassA(classB);
        classA.metodo();
    }
}

// Saida:
// > Metodo do A
// > Metodo do B
// > Metodo do A
// > Metodo do B
```

<div class='importante' markdown='1'>

- O Spring não tem o requisito para o construtor com notação `@Autowire` ser público.

- Classes que possuem somente um construtor não precisam declarar a notação `@Autowire`, pois a notação está presente implicitamente.

- Se usarmos a notação `@Autowire` no construtor, todos os argumentos do construtor serão obrigatórios.

- Só uma classe *bean* (ou seja, gerenciada pelo Spring) pode ter injetados nela outros *beans*.

</div>

## Prioridade do *auto wiring*

Por padrão, sempre que os contêineres do Spring encontrarem a notação `@Autowire`, ele conectará automaticamente o bean levando em consideração a ordem de prioridade.

1. Correspondência por tipo
1. Correspondência por qualificador (@Qualifier)
1. Correspondência por nome (@Component(value="nome"))

### Correspondência por tipo

```java
package com.example.auto_wired_match_type;

/* imports */

@SpringBootApplication
public class AutoWiredMatchTypeApplication {

    public static void main(String[] args) {

        SpringApplication.run(AutoWiredMatchTypeApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(AutoWiredMatchTypeApplication.class.getPackage().getName());
        context.refresh();

        /**
         * Com @AutoWired
         */
        ClassA classA = context.getBean(ClassA.class);
        classA.metodo();

    }
}

@Component
class ClassA {

    /*
     * Injeção ocorrerá apartir da ClasseC, que retorna o bean de tipo classB
     */
    @Autowired
    ClassB classB;

    void metodo() {
        classB.metodoB();
    }
}

@Configuration
class ClassB {

    void metodoB() {
        System.out.println("Método B");
    }
}

@Configuration
class ClassC {

    @Bean
    ClassB getClassB(){
        return new ClassB();
    }
}

// Saida:
// > Método B
```

### Correspondência por qualificador

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
        System.out.println(classB.getClass().getSimpleName().substring(0, 7));
        System.out.println(classC.metodoUsado);
    }
}

@Configuration
class ClassB1 extends ClassB {

    @Bean
    ClassB1 getClassB1(){
        return new ClassB1();
    }
}

@Configuration
class ClassB2 extends ClassB {

    @Bean
    ClassB2 getClassB2(){
        return new ClassB2();
    }
}

class ClassB { }

@Configuration
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
// > ClassB1
// > metodo getClassC1
```

### Correspondência por nome

```java
package com.example.auto_wired_match_nome;

/* imports */

@SpringBootApplication
public class AutoWiredMatchNomeApplication {

    public static void main(String[] args) {

        SpringApplication.run(AutoWiredMatchNomeApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(AutoWiredMatchNomeApplication.class.getPackage().getName());
        context.refresh();

        ClassA classA = context.getBean(ClassA.class);
        classA.metodo();
    }
}

@Component
class ClassA {

    /*
     * Injeção ocorrerá verificando @Component(value="testeClassB")
     */
    @Autowired
    ClassB testeClassB;

    void metodo() {
        System.out.println(testeClassB.getClass().getSimpleName().substring(0, 7));
    }
}

@Configuration(value="testeClassB")
class ClassB1 extends ClassB { }

@Configuration
class ClassB2 extends ClassB { }

abstract class ClassB { }

// Saida:
// > ClassB1
```
