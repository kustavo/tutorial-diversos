# Injeção de dependência

[TOC]

## Introdução

Injeção de dependência (em ingles, *Depedency Injection*) é um padrão de projeto utilizado para manter o acoplamento fraco entre classes ou módulos do sistema. O foco principal é fazer com que uma classe não tenha conhecimento de como instanciar um objeto de um tipo do qual é dependente. Isto permite que os objetos sejam fracamente acoplados e siga os princípios de inversão de dependência e responsabilidade única.

Abaixo, um exemplo de algo que deve ser evitado devido ao alto acoplamento. Neste exemplo a `ClasseA` tem conhecimento de como instanciar objetos da `ClasseB`.

```java
public class ClasseA {

    public void metodoA() {

        ClasseB b = new ClasseB();
        b.metodoB();
    }
}
```

Através da injeção de dependência, existem pelo menos três maneiras pelas quais um objeto cliente pode receber uma referência a um objeto externo.

- **Injeção por construtor**: as dependências são fornecidas por meio do construtor de classe do cliente.

- **Injeção por método**: o cliente expõe um método que o injetor usa para injetar a dependência.

- **Injeção por interface**: a interface da dependência fornece um método que injetará a dependência em qualquer cliente passado a ela. Os clientes devem implementar uma interface que expõe um método que aceita a dependência.

## Injeção por construtor

Este método requer que o cliente forneça um parâmetro em um construtor para a dependência.

```java
public class Client {

    Client(Service service) {

        this.service = service;
    }
}
```

## Injeção por método

Este método requer que o cliente forneça um método *setter* para a dependência.

```java
public class Client {

    public void setService(Service service) {
        this.service = service;
    }
}
```

## Injeção por interface

Isso é simplesmente o cliente publicando uma interface para os métodos *setter* das dependências do cliente. Pode ser usado para estabelecer como o injetor deve falar com o cliente ao injetar dependências.

```java

public interface ServiceSetter {
    public void setService(Service service);
}

public class Client implements ServiceSetter {

    // Referência interna ao Service usado por este Client.
    private Service service;

    @Override
    public void setService(Service service) {
        this.service = service;
    }
}
```
