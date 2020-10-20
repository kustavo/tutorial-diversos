# Modificadores

[TOC]

## Introdução

Os modificadores podem ser classificados em duas categorias:

1. Modificadores de acesso
1. Modificadores sem controle de acesso

## Modificadores de acesso

Modificadores de acesso controlam o nível de acesso. Existem 3 modificadores de acesso e 4 níveis de acesso. De forma geral os modificadores restringem o acesso da seguinte forma:

|           | na classe | dentro do pacote | fora do pacote (subclasse) | fora do pacote |
| :-------- | :-------: | :--------------: | :------------------------: | :------------: |
| public    |     X     |        X         |             X              |       X        |
| protected |     X     |        X         |             X              |                |
| default   |     X     |        X         |                            |                |
| private   |     X     |                  |                            |                |

Modificador de acesso para classes

| Modificador | Descrição                                                     |
| :---------- | :------------------------------------------------------------ |
| public      | A classe é acessível a qualquer outra classe.                 |
| default     | A classe é acessível a qualquer outra classe no mesmo pacote. |

Modificador de acesso para campos, métodos e construtores

| Modificador | Descrição                                                                      |
| :---------- | :----------------------------------------------------------------------------- |
| public      | O recurso é acessível a todas classes.                                         |
| private     | O recurso é acessível somente a classe que o declarou.                         |
| default     | O recurso é acessível somente ao mesmo pacote.                                 |
| protected   | O recurso é acessível ao mesmo pacote ou para subclasses de pacotes diferentes |

Classes com nível de acesso `default` mas com métodos de nível `public` são permitidas. Pois as classes podem implementar alguma interface ou estender classes abstratas que possuem métodos `public`, não sendo permitido reduzir a visibilidade destes métodos que deverão ser implementados. Portanto a restrição de acesso pode ser contornada através do nível de acesso `default` da classe.

As subclasses com nível de acesso `public` podem herdar e disponibilizar estes métodos de nível `public`.

<div class="importante">

Construtores podem ter nível de acesso `private`, entretanto a classe só poderá ser instanciada por seus próprios métodos estáticos. Esta configuração geralmente é utilizada para criar classes com o padrão de projeto `Singleton`.

</div>

### Exemplo de restrição de acesso para os métodos

```java
package exemplo_acesso_campos.pacote1;

public class Classe1 {

    private String privateC1;
    String defaultC1;
    protected String protectedC1;
    public String publicC1;

    public Classe1() {

        this.privateC1 = "private";
        this.defaultC1 = "default";
        this.protectedC1 = "protected";
        this.publicC1 = "public";
    }

    public void fncPublicC1() {

        System.out.println("\nC1 Private: " + this.privateC1);
        System.out.println("C1 Default: " + this.defaultC1);
        System.out.println("C1 Protected: " + this.protectedC1);
        System.out.println("C1 Public: " + this.publicC1);
    }
}
```

```java
package exemplo_acesso_campos.pacote1;

public class Classe2 {

    public static void main(String[] args) {

        Classe1 c1 = new Classe1();
        // System.out.println("Campo private: " + c1.privateC1); // ERRO!
        System.out.println("Campo default: " + c1.defaultC1);
        System.out.println("Campo protected: " + c1.protectedC1);
        System.out.println("Campo public: " + c1.publicC1);
    }
}

// Saida:
// > Campo default: default
// > Campo protected: protected
// > Campo public: public
```

A `Classe2` está no mesmo pacote da `Classe1`, portanto somente não consegue acessar o campo `private` da `Classe1`.

```java
package exemplo_acesso_campos.pacote2;

import exemplo_acesso_campos.pacote1.Classe1;

public class Classe3 {

    public static void main(String[] args) {

        Classe1 c1 = new Classe1();
        // System.out.println("Campo private: " + c1.privateC1); // ERRO!
        // System.out.println("Campo default: " + c1.defaultC1); // ERRO!
        // System.out.println("Campo protected: " + c1.protectedC1); // ERRO!
        System.out.println("Campo public: " + c1.publicC1);
    }
}

// Saida
// > Campo public: public
```

A `Classe3` não está no mesmo pacote da `Classe1` e não é classe herdeira de `Classe1`, portanto somente consegue acessar o campo `public` da `Classe1`.

```java
package pacote2;

import pacote1.Classe1;

public class Classe4 extends Classe1 {

    public static void main(String[] args) {

        Classe1 c1 = new Classe1();
        Classe4 c4 = new Classe4();

        // System.out.println("Campo private C1: " + c1.privateC1); // ERRO!
        // System.out.println("Campo private C4: " + c4.privateC1); // ERRO!

        // System.out.println("Campo default C1: " + c1.defaultC1); // ERRO!
        // System.out.println("Campo default C4: " + c4.defaultC1); // ERRO!

        /*
         * Modificador protected permite acessado somente através de hierarquia.
         * Ele não estará acessível na subclasse que está fora do pacote usando
         * a instância de alguma superclasse (classe pai).
         */

        // System.out.println("Campo protected C1: " + c1.protectedC1); // ERRO!
        System.out.println("Campo protected C4: " + c4.protectedC1);

        System.out.println("Campo public C1: " + c1.publicC1);
        System.out.println("Campo public C4: " + c4.publicC1);

        /*
         * A Função da classe C1 tem acesso a todos campos de sua classe
         */
        c4.fncPublicC1();

    }
}

// Saida
// > Campo protected C4: protected
// > Campo public C1: public
// > Campo public C4: public

// > C1 Private: private
// > C1 Default: default
// > C1 Protected: protected
// > C1 Public: public
```

A `Classe4` não está no mesmo pacote da `Classe1`, mas é classe herdeira de `Classe1`, portanto consegue acessar os campos `public` e `protected` da `Classe1`.

```java
package exemplo_acesso_campos.pacote2;

public class Classe5 extends Classe4 {

    public static void main(String[] args) {

        Classe4 c4 = new Classe4();
        Classe5 c5 = new Classe5();

        // System.out.println("Campo private C4: " + c4.privateC1); // ERRO!
        // System.out.println("Campo private C5: " + c5.privateC1); // ERRO!

        // System.out.println("Campo default C4: " + c4.defaultC1); // ERRO!
        // System.out.println("Campo default C5: " + c5.defaultC1); // ERRO!

        /*
         * Modificador protected não estará acessível na subclasse que está fora
         * do pacote usando a instância de alguma superclasse intermediária.
         */

        // System.out.println("Campo protected C4: " + c4.protectedC1); // ERRO!
        System.out.println("Campo protected C5: " + c5.protectedC1);

        System.out.println("Campo public C4: " + c4.publicC1);
        System.out.println("Campo public C5: " + c5.publicC1);
    }
}

// Saida
// > Campo protected C5: protected
// > Campo public C4: public
// > Campo public C5: public
```

A `Classe5` é herdeira da `Classe4` que por sua vez é herdeira da `Classe1`, entretanto campos `protected` não são acessíveis na subclasse que está fora do pacote usando a instância de qualquer superclasse intermediária, apenas pela instância da subclasse corrente.

A instância da `Classe4` não possui relação de herança com a classe `Classe5`, portanto no escopo da `Classe5` não é possível acessar os campos `protected` da `Classe1` através da `Classe4`. Já a instância da própria `Classe5` possui relação de herança com a classe `Classe4` e `Classe1` consequentemente.

<div class='importante' markdown='1'>

Os campos `protected` são acessados somente pelo elo de herança até a subclasse corrente. Portanto somente a instância da subclasse corrente pode acessar métodos `protected` de uma superclasse.

</div>

### Exemplo de restrição de acesso para as classes

```java
package exemplo_acesso_classes.pacote1;

class Classe1 {

    private String privateC1;
    String defaultC1;
    protected String protectedC1;
    public String publicC1;

    Classe1() {

        this.privateC1 = "private";
        this.defaultC1 = "default";
        this.protectedC1 = "protected";
        this.publicC1 = "public";
    }

}
```

A `Classe1` possui restrição de acesso `default`, portanto pode ser acessada somente por classes do mesmo pacote.

```java
package exemplo_acesso_classes.pacote1;

public class Classe2 extends Classe1 {

    public static void main(String[] args) {

        Classe1 c1 = new Classe1();
        // System.out.println("Campo private: " + c1.privateC1); // ERRO!
        System.out.println("Campo default: " + c1.defaultC1);
        System.out.println("Campo protected: " + c1.protectedC1);
        System.out.println("Campo public: " + c1.publicC1);
    }
}

// Saida:
// > Campo default: default
// > Campo protected: protected
// > Campo public: public
```

A `Classe2` está no mesmo pacote e é herdeira da `Classe1`, e possui restrição `public`. Dessa forma, possibilita através de sua instância, acesso a `Classe1` como se esta fosse  `public`.

```java
package exemplo_acesso_classes.pacote2;

// import pacote1.Classe1; // ERRO!

public class Classe3 {

}
```

Como a `Classe3` está em outro pacote, não possui permissão para acessar a `Classe1`.

```java
package exemplo_acesso_classes.pacote2;

import exemplo_acesso_classes.pacote1.Classe2;

public class Classe4 extends Classe2 {

    public static void main(String[] args) {

        Classe2 c2 = new Classe2();
        Classe4 c4 = new Classe4();

        // System.out.println("Campo private C2: " + c2.privateC1); // ERRO!
        // System.out.println("Campo private C4: " + c4.privateC1); // ERRO!

        // System.out.println("Campo default C2: " + c2.defaultC1); // ERRO!
        // System.out.println("Campo default C4: " + c4.defaultC1); // ERRO!

        // System.out.println("Campo protected C2: " + c2.protectedC1); // ERRO!
        System.out.println("Campo protected C4: " + c4.protectedC1);

        System.out.println("Campo public C2: " + c2.publicC1);
        System.out.println("Campo public C4: " + c4.publicC1);

    }
}

// Saida
// > Campo protected C4: protected
// > Campo public C2: public
// > Campo public C4: public
```

Através da herança da `Classe2`, a `Classe4` que está em outro pacote, consegue acessar os campos da `Classe1` mesmo não sendo uma classe `public`.

## Modificadores sem controle de acesso

Não controlam o nível de acesso, mas proveem outras funcionalidades. São eles:

- static
- final
- abstract
- synchronized
- transient
- volatile
- native
- strictfp

### static

A palavra-chave `static` significa que é algo pertence diretamente a classe e que não precisa de uma instância dessa classe para poder acessá-lo.

<span class='mais' markdown='1'>[Classe estática](classe.md)</span>

```java
package exemplo_static;

class Static {

    String atrNaoEstatico = "Campo Não estático";
    static String atrEstatico = "Campo estático";

    static void metodoEstatico() {

        System.out.println("Método estático");

        /*
         * Não é necesseráio instância para chamar um campo ou método
         * estático
         */
        System.out.println(atrEstatico);

        /*
         * Um método estático não pode acessar uma variável de instância, já
         * que o método pertence à classe. Somente uma instância pode acessar
         * uma variável de instância.
         */
        // System.out.println(atrNaoEstatico); // ERRO!
        Static i = new Static();
        System.out.println(i.atrNaoEstatico);
    }

    void metodoNaoEstatico() {

        System.out.println("Método Não estático");

        /*
         * Um método não estático pode acessar uma variável de instância, já
         * que o método pertence à instância.
         */
        System.out.println(atrEstatico);
        System.out.println(atrNaoEstatico);
    }

    public static void main(String[] args) {

        metodoEstatico(); // ou Static.metodoEstatico()

        /*
         * O método main é estático portanto não pode acessar um campo de
         * instância, seu escopo é de classe, portanto também não pode acessar
         * a instância com this.
         */
        // System.out.println(atrNaoEstatico); // ERRO!
        // System.out.println(this.metodoNaoEstatico()); // ERRO!

        Static i = new Static();
        i.metodoNaoEstatico();

    }
}

class Exemplo {

    public static void main(String[] args) {

        /*
         * Chamando um método estático de Static em outra classe
         */
        Static.metodoEstatico();

        Static i = new Static();
        i.metodoNaoEstatico();
    }
}

// Saida:
// > Método estático
// > Campo estático
// > Campo Não estático
// > Método Não estático
// > Campo estático
// > Campo Não estático
```

### final

A palavra-chave `final` em classes significa que elas não pode ser herdadas. Em métodos, significa que eles não podem ser sobrescritos. Já em campos, significa que eles não podem ser modificado, ou seja, um campo `final` pode receber uma atribuição somente em sua criação.

<span class='mais' markdown='1'>[Classe final](classe.md)</span>

```java
package exemplo_final;

class Final {

    final String atrFinal; // Poderia ser atribuído o valor aqui

    Final() {

        this.atrFinal = "Final";

        /*
         * Um campo final não pode ser alterado
         */
        // this.atrFinal = "Novo Final"; // ERRO!
    }

    final void metodoFinal() {

        System.out.println("Método final");
    }

    public static void main(String[] args) {

        Final t = new Final();

        /*
         * Um campo final não pode ser alterado
         */
        // t.atrFinal = "Novo final"; // ERRO!
        System.out.println(t.atrFinal);
        t.metodoFinal();
    }

}

class Exemplo extends Final {

    /*
     * Um método final não pode ser sobrescrito
     */
    // void metodoFinal() { } // ERRO!

    public static void main(String[] args) {

        Final t = new Final();

        /*
         * Um campo final não pode ser alterado
         */
        // t.atrFinal = "Novo final"; // ERRO!
        System.out.println(t.atrFinal);
        t.metodoFinal();
    }
}

// Final:
// > Final
// > Método final
```

### abstract

A palavra-chave `abstract` pode ser usada para classes e métodos dessas classes. Classes abstratas não podem ser instanciadas, somente herdadas. Métodos abstratos dessas classes devem ser obrigatoriamente implementados nas subclasses.

<span class='mais' markdown='1'>[Classe abstract](classe.md)</span>

```java
package exemplo_abstract;

abstract class Abstract {

    String funcaoNaoAbstrata() {
        return "método não abstrato";
    };

    /*
     * Função abstrata não possui corpo
     */
    abstract String funcaoAbstrata();
}

class Exemplo extends Abstract {

    /*
     * Obrigado a implementar os métodos abstratos da classe pai
     */
    String funcaoAbstrata() {
        return "método abstrato implementado";
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();
        System.out.println(t.funcaoAbstrata());
        System.out.println(t.funcaoNaoAbstrata());

        /*
         * Não é possível instanciar uma classe abstrata
         */
        // Abstrata a = new Abstrata(); // ERRO!
    }
}

// Saida:
// > método abstrato implementado
// > método não abstrato
```

### synchronized

A palavra-chave `synchronized` é usada para indicar que um método ou um bloco em um *statement* só pode ser acessado por uma *thread* por vez. Poder ser usado com qualquer modificador de acesso.

Programas multi-threaded podem muitas vezes chegar a uma situação em que vários threads tentam acessar os mesmos recursos e, finalmente, produzir resultados errados e imprevistos.

Portanto, é necessário garantir que, por meio de algum método de sincronização, apenas um thread possa acessar o recurso em um determinado momento.

### Exemplo como método

```java
package exemplo_synchronized_metodo;

class Sender {

    /*
     * Método sincronizado, apenas uma thread irá executá-lo por vez
     */
    public synchronized void send(String msg) {

        System.out.println("Enviando: " + msg);

        try {

            Thread.sleep(1000);

        } catch (Exception e) {

            System.out.println("Thread interrompida.");
        }

        System.out.println("Enviada: " + msg);
    }
}

class ThreadedSend extends Thread {

    Sender obj;
    private String msg;

    // Recebe o objeto que irá enviar a msg e a msg
    ThreadedSend(String msg, Sender obj) {

        this.msg = msg;
        this.obj = obj;
    }

    // Função chamada ao start() da thread
    public void run() {

        obj.send(msg);
    }
}


class Exemplo {

    public static void main(String[] args) {

        Sender obj = new Sender();

        ThreadedSend S1 = new ThreadedSend("Oi", obj);
        ThreadedSend S2 = new ThreadedSend("Tchau", obj);

        // Iniciando duas threads do tipo ThreadedSend
        S1.start();
        S2.start();

        try {

            // Esperando as threads terminarem
            S1.join();
            S2.join();

        } catch (Exception e) {

            System.out.println("Interrompido");
        }
    }
}

// Saida
// > Enviando: Oi
// > Enviada: Oi
// > Enviando: Tchau
// > Enviada: Tchau
```

### Exemplo como statement

Diferente de usar `synchronized` no método, `synchronized` como *statement* precisa especificar o objeto que o monitor irá aplicar o bloqueio. O monitor está presente em todos os objetos Java, e garante que o bloqueio de um objeto será realizado para uma única tarefa a cada momento. Então a chamada do especificador `synchronized` por uma tarefa em execução irá utilizar o monitor do objeto para bloquear o trecho de código. Sendo que o objeto do exemplo normalmente é substituído pela palavra-chave `this` para se referir ao objeto em execução, isto é, o uso do this normalmente irá representar o objeto que está em execução multitarefa naquele instante em que for executar o trecho de código sincronizado.

```java
package exemplo_synchronized_statement.exemplo1;

class Sender {

    public synchronized void send(String msg) {

        System.out.println("Enviando: " + msg);

        try {

            Thread.sleep(1000);

        } catch (Exception e) {

            System.out.println("Thread interrompida.");
        }

        System.out.println("Enviada: " + msg);
    }
}

class ThreadedSend extends Thread {

    Sender sender;
    private String msg;

    ThreadedSend(String m, Sender obj) {

        msg = m;
        sender = obj;
    }

    public void run() {

        /* Usando synchronized como statement. Somente uma thread pode enviar
         * uma mensagem por vez.
         */
        synchronized(sender) {

            sender.send(msg);
        }
    }
}


class TesteSynchronizedStatement1 {

    public static void main(String[] args) {

        Sender snd = new Sender();

        ThreadedSend S1 = new ThreadedSend("Oi", snd);
        ThreadedSend S2 = new ThreadedSend("Tchau", snd);

        S1.start();
        S2.start();

        try {

            S1.join();
            S2.join();

        } catch (Exception e) {

            System.out.println("Interrompido");
        }
    }
}

// Saida
// > Enviando: Oi
// > Enviada: Oi
// > Enviando: Tchau
// > Enviada: Tchau
```

Outra forma seria restringir ainda mais onde a sincronização ocorrerá, como restringir dentro do método, passando assim o objeto em execução, usando `this`.

```java
class Sender {

    public synchronized void send(String msg) {

        synchronized(this) {

            System.out.println("Enviando: " + msg);

            try {

                Thread.sleep(1000);

            } catch (Exception e) {

                System.out.println("Thread interrompida.");
            }

            System.out.println("Enviada: " + msg);
        }
    }
}
```

### transient

A palavra-chave `transient` é modificador de variáveis usado na serialização. Ela é usada quando não é desejado salvar o valor de uma variável após uma serialização, seja por questão de restrição de segurança ou esse valor pode ser calculado.

```java
package exemplo_transient;

import java.io.*;

class Exemplo implements Serializable {

    // Campos normais
    int i = 10, j = 20;

    // Campos transient
    transient int k = 30;
    transient static int l = 40; // Uso do transient não há impacto
    transient final int m = 50;  // Uso do transient não há impacto

    public static void main(String[] args) throws Exception {

        Exemplo input = new Exemplo();

        // Serialização
        FileOutputStream fos = new FileOutputStream("teste.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(input);

        // Deserialização
        FileInputStream fis = new FileInputStream("teste.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Exemplo output = (Exemplo)ois.readObject();

        System.out.println("i = " + output.i);
        System.out.println("j = " + output.j);
        System.out.println("k = " + output.k);
        System.out.println("l = " + output.l);
        System.out.println("m = " + output.m);
    }
}

// Saida
// > i = 10
// > j = 20
// > k = 0
// > l = 40
// > m = 50
```

Usar `transient` com `static` não possui efeito já que campos `static` não pertencem a instância da classe (objeto).

Usar `transient` com `final` não possui efeito pois campos `final` são diretamente serializados por seus valores.

### volatile

A palavra-chave `volatile` é usada para evitar que as *threads* utilizem sua mémoria *cache* para ler/gravar valores das variáveis, evitando inconsistência de dados com outras  *threads*.

No Java, quando há instâncias de duas ou mais threads, é criada uma memória cache local para cada thread. Quando múltiplas threads compartilham a mesma variável, cada uma copia o valor da variável em sua própria cache local. Em qualquer mudança de valor dessa variável, a atualização é realizada no cache local, ao invés de ser na variável alocada na memória principal. Por exemplo, em um momento M1, a thread A realiza uma alteração na variável x. Em um momento T2, a thread B também acessa essa variável, porém a thread B não terá conhecimento de qualquer mudança de valor realizado pela thread A, causando uma possível inconsistência de dados, pois cada thread realizou a alteração apenas em sua cache local.

Para evitar esse tipo de situação, o operador `volatile` evita que qualquer alteração de variável compartilhada entre múltiplas threads seja realizada no cache local de uma thread. O `volatile` é aplicado exclusivamente em variáveis e, seu uso só terá sentido quando essas forem compartilhadas entre threads. Tal operador é pouco difundido entre os programadores e raramente detalhado na literatura. Os desenvolvedores usualmente utilizam o `synchronized` para bloquear o acesso a objetos e métodos compartilhados, enquanto o operador `volatile` permite o bloqueio de variáveis de tipos primitivos e, também, o de objetos.

Quando uma variável `volatile` sofre qualquer mudança de valor por alguma thread, tal alteração é realizada diretamente na memória principal. Em um bloco ou método sincronizado, qualquer alteração de valor de uma variável é realizada na memória cache da JVM. Esta ação, em algumas situações, pode levar a uma inconsistência de dados quando uma thread realiza a leitura em uma variável compartilhada.

```java
package exemplo_volatile;

public class Exemplo {

    private static volatile int valor = 0;

    static class Escuta extends Thread {

        public void run() {

            int captura = valor;

            while (captura < 5) {

                if (captura != valor) {

                    System.out.println("Escutou: " + valor);

                    captura = valor;
                }
            }
        }
    }

    static class Altera extends Thread{

        public void run() {

            while (valor < 5) {

                System.out.println("Alterou: " + (valor + 1));

                valor++;

                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public static void main(String[] args) {

        new Escuta().start();
        new Altera().start();
    }
}

// Saida
// > Alterou: 1
// > Escutou: 1
// > Alterou: 2
// > Escutou: 2
// > Alterou: 3
// > Escutou: 3
// > Alterou: 4
// > Escutou: 4
// > Alterou: 5
// > Escutou: 5
```

Quando a *thread* `Altera` altera o campo `valor`, a *thread* `Escuta` percebe a mudança. Sem o uso do `volatile` a *thread* `Escuta` não perceberia a mudança, já que o campo `valor` seria o armazenado em sua cache.

### native

A palavra-chave `native` é aplicada a um método para indicar que o método é implementado no código nativo usando **JNI (Java Native Interface)**. Marca um método, que será implementado em outras linguagens, não em Java.

Os métodos nativos foram usados no passado para escrever seções críticas de desempenho, mas com o Java ficando mais rápido, isso agora é menos comum.

Métodos nativos são atualmente necessários quando é necessário chamar uma biblioteca do Java que está escrita em outro linguagem, ou acessar recursos do sistema ou de hardware que só podem ser acessados em outras linguagens.

```java
/* TesteNative.java */
package exemplo_native;

public class TesteNative {

    /*
     * O método será provido por um código nativo
     */
    private native void imprimir();

    public static void main(String args[]) {

        new TesteNative().imprimir();
    }

    static {
        System.loadLibrary("TesteNative");
    }
}
```

No exemplo acima a classe `TesteNative` contendo a declaração de um método nativo (`imprimir()`) implementado na linguagem C. O bloco `static` inicializa o carregamento da biblioteca nativa contendo a implementação do método `imprimir()`.

Primeiro é compilado a classe Java:

```sh
javac TesteNative.java

```

Em seguida, utiliza-se a ferramenta `javah` para gerar o arquivo de cabeçalho baseado em JNI para implementação do método nativo em C. O `javah` é usado para gerar cabeçalhos e códigos fonte em C a partir de uma classe Java.

```sh
javah -jni TesteNative
```

O `javah` cria um arquivo de extensão `.h` com o mesmo nome da classe. Tal arquivo contém o protótipo do método nativo, cuja sintaxe é `Java_<nome_classe>_<nome_método>()`. Nesse sentido, ao compilar a classe `TesteNative` utilizando o `javah`, temos o método `imprimir()` da classe Java `TesteNative` convertido para a função `Java_TesteNative_imprimir()` no arquivo `TesteNative.h`, o qual deve ser implementado em C.

```c
/* TesteNative.h */
#include <jni.h>

#ifndef _Included_TesteNative
#define _Included_TesteNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     TesteNative
 * Method:    imprimir
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_TesteNative_imprimir
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
```

Abaixo a implementação do método `imprimir()` em C.

```c
#include <stdio.h>
#include <jni.h>
#include "TesteNative.h"

JNIEXPORT void JNICALL
Java_TesteNative_imprimir(JNIEnv *env, jobject obj) {

  printf("Olá! Invocando um método nativo!");
  return;
}
```

A implementação da função nativa em C, segue exatamente a assinatura do protótipo do método presente no arquivo `TesteNative.h` gerado pelo `javah` a partir da classe `TesteNative`. A inclusão do cabeçalho `jni.h` oferece diferentes tipos de dados não disponíveis na linguagem C/C++ tratados pelo JNI para a passagem de parâmetros às funções de código nativo. Depois de implementado, pode-se compilar o código nativo a partir do diretório onde as classes Java estão presentes da seguinte forma:

```sh
gcc -I/usr/lib/jvm/java-12-oracle/include/ -I/usr/lib/jvm/java-12-oracle/include/linux -o libTesteNative.so -shared TesteNative.c
```

Na instrução acima é passado como parâmetro o diretório do arquivo de cabeçalho JNI. A opção `-o` determina o nome da biblioteca nativa a ser gerada e deve seguir o formato `lib<nome_argumento_do_método_loadlibrary>.so`. Já a opção `-shared` indica a criação de uma biblioteca compartilhada.

Depois de todos esses passos, é preciso verificar a existência dos arquivos na raiz do diretório do projeto Java: `TesteNative.java`, `TesteNative.class`, `TesteNative.h`, `TesteNative.c` e `libTesteNative.so`.

O comando abaixo realiza a execução do binário java:

```sh
java -Djava.library.path=. TesteNative
```

O seguinte resultado é gerado:

```sh
Olá! Invocando um método nativo!
```

### strictfp

A palavra-chave `strictfp` é usada para restringir os cálculos de ponto flutuante para garantir a portabilidade.

O `strictfp` força os pontos flutuante a seguirem o padrão **IEEE754**, que seria, todos os pontos flutuantes são calculados da mesma maneira independente da plataforma. Se não for usado o `strictfp`, a implementação da JVM estará livre para usar precisão extra quando disponível.

O `strictfp` pode ser usado em classes, interfaces e métodos não abstratos. Quando aplicado a um método, ele faz com que todos os cálculos dentro do método usem uma matemática estrita de ponto flutuante. Quando aplicado a uma classe, todos os cálculos dentro da classe usam uma matemática estrita de ponto flutuante.

```java
public strictfp class TesteStrictfp {}
```
