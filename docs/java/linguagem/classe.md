
# Classe

[TOC]

## Introdução

Uma classe é uma estrutura que abstrai um conjunto de objetos com características similares. Uma classe define o comportamento de seus objetos - através de métodos - e os estados possíveis destes objetos - através de campos.

Em outras palavras, uma classe descreve os serviços oferecidos por seus objetos e quais informações eles podem armazenar.

## Classes aninhadas

A palavra "aninhada" em programação (*nested*, em inglês) diz respeito que é uma subrotina encapsulada noutra. O escopo da subrotina aninhada é limitado a subrotina que a encapsula. Isso significa que ela pode ser chamada somente pela subrotina que a encapsula, ou pelas subrotinas diretamente ou indiretamente aninhadas pela mesma subrotina encapsuladora. O aninhamento é teoricamente ilimitado, ainda que na prática somente alguns níveis são aceitos, o que depende da implementação.

Porque deveríamos criar uma classe aninhada em vez de uma top-level? Quais as vantagens no seu uso?

- **Agrupamento de classes que são usadas em apenas um lugar**: Se você possui uma classe B que com certeza será usada apenas dentro da classe A, o melhor é criar a classe B como interna a classe A.
- **Aumento de Encapsulamento**: Sendo que a classe B e A são duas classes top-level e B precisa acessar os membros de A, poderíamos colocar a classe B dentro de A e colocar os membros de A como privados e ainda assim B conseguiria continuar acessando os membros de A por ser uma classe aninhada.
- **Código mais legível e de fácil manutenção**: Colocar classes pequenas dentro de classes top-level, já que apenas esta a usará, faz com que a lógica da classe top-level seja mais fácil de ser identificada, consequentemente tornando o código mais legível e de fácil manutenção.

Classes Aninhadas são dividas em quatro categorias:

- Interna comum (Não-Estática)
- Interna a um método
- Interna anônima.
- Estática

### Classe interna comum

Uma classe interna comum só pode ser instanciada se estiver dentro de um objeto da classe externa.

<div class='importante' markdown='1'>

- Uma classe interna tem acesso aos membros estáticos e aos membros da instância da classe externa.

- Classes aninhadas não podem ter campos ou métodos estáticos.

</div>

```java
package _classe.exemplo_aninhada_interna;

class Externa {

    private String attrExternoPrv = "campo externo privado";
    private String attrExternoPub = "campo externo publico";

    class InternaA {

        /*
         * Classes aninhadas não podem ter campos ou métodos estáticos.
         */
        // static String atr = "valor"; // ERRO!

        String attrInternoA = "campo interno A";

        void imprime() {

            System.out.println("método da classe aninhada A");
            System.out.println(attrExternoPrv);
            System.out.println(attrExternoPub);
        }
    }

    class InternaB {

        void imprime() {

            System.out.println("método da classe aninhada B");
            /*
             * Necessário uma instância da Classe InternaA
             */
            System.out.println(new InternaA().attrInternoA);
        }
    }
}

class Exemplo {

    public static void main(String[] args) {

        /*
         * Necessário uma instância da Classe Externa
         */
        // Externa.InternaA internaA = new Externa.InternaA(); // ERRO!
        Externa externa = new Externa();
        Externa.InternaA internaA = externa.new InternaA();
        internaA.imprime();

        Externa.InternaB internaB = externa.new InternaB();
        internaB.imprime();

    }
}

// Saida:
// > método da classe aninhada A
// > campo externo privado
// > campo externo publico
// > método da classe aninhada B
// > campo interno A
```

### Classe interna a um método

Uma classe Interna a um método, só pode ser instanciada dentro deste próprio método. Portanto o modificador padrão de acesso de uma classe de método possui comportamento do modificador `private`, visto que esse não pode ser instanciado de nenhum local fora do método. Os modificadores de podem ser aplicados a este tipo de classe são: `default`, `abstract` e `final`.

<div class='importante' markdown='1'>

Há uma exceção a ser notada quando utilizamos classes de métodos, não podemos fazer referência as variáveis do método.

</div>

```java
package exemplo_aninhada_interna_metodo;

class Externa {

    String attrExterno = "Campo externo";

    void metodoExterno() {

        class Interna {

            void metodoInterno() {
                System.out.println("método da classe interna ao método");
                System.out.println(attrExterno);
            }
        }

        Interna interna = new Interna();
        interna.metodoInterno();
    }
}

class Exemplo {

    public static void main(String[] args) {

        Externa externa = new Externa();
        externa.metodoExterno();

        /*
         * Não possui acesso fora do método
         */
        // Externa.Interna = externa.new Interna(); //ERRO!

    }
}

// Saida:
// > método da classe interna ao método
// > Campo externo
```

### Classe interna anônima

Classe anônima nada mais é do que a herança de determinada classe em um local exclusivo, ou seja, apenas em um determinado escopo foi preciso redefinir a classe-pai.

```java
package exemplo_aninhada_interna_anonima;

class Funcionario {

    void trabalhar() { System.out.println("trabalhar"); }
}

class Exemplo {

    Funcionario gerente = new Funcionario() {

        /*
         * Sobrescreve o função trabalhar do funcionário
         */
        void trabalhar() { System.out.println("mandar"); }
    };

    Funcionario peao = new Funcionario() {

        /*
         * Sobrescreve o função trabalhar do funcionário
         */
        void trabalhar() { System.out.println("executar"); }

        /*
         * Método descansar não poderá ser usado, pois não foi definido em Funcionario
         */
        void descansar() { System.out.println("descansar"); }
    };

    public static void main(String[] args) {

        Exemplo a = new Exemplo();
        a.gerente.trabalhar();
        a.peao.trabalhar();

        /*
         * Método descansar não existe na classe Funcionario
         */
        // a.peao.descansar(); // ERRO!
    }
}

// Saida:
// > mandar
// > executar
```

Se fossemos fazer uma herança "comum", teríamos que criar uma classe `Peao extends Funcionario` e mudar o comportamento do método trabalhar, e também criaríamos uma classe `Gerente extends Funcionario`, isso é o que normalmente se faz quando há necessidade do uso de tais comportamentos distintos em vários locais distintos, mas em nosso caso só precisamos desses comportamentos dentro da classe `Exemplo` e a melhor prática é usar classes anônimas.

As classes anônimas ainda tem algo muito interessante, o uso de Interfaces.

```java
package exemplo_aninhada_interna_anonima_interface;

interface Evento {

    abstract void clicar();

    abstract void arrastar();
}

class Exemplo {

    static void acao(Evento e) {
        e.clicar();
        e.arrastar();
    }

    public static void main(String[] args) {

        acao(new Evento() {

            public void clicar() {
                System.out.println("clicou");
            }

            public void arrastar() {
                System.out.println("arrastou");
            }
        });
    }

}

// Saida:
// > clicou
// > arrastou
```

O que você provavelmente percebeu com o código acima é que estamos fazendo uma instanciação da interface `Evento` como uma classe anônima, mas como isso é possível se o java não permite que uma interface seja instanciada?

Acontece que ao chamar a interface usando a expressão `new Evento()`, é criada uma classe anônima (oculta) que estende a interface `Evento` e não a interface é instanciada diretamente, tanto que é obrigatório usar todos os métodos que estão na interface.

### Classe estática

A palavra-chave `static` significa que é algo pertence diretamente a classe e que não precisa de uma instância dessa classe para poder acessá-lo. Portanto classes estáticas devem ser sempre **aninhadas** (internas), já que estruturas estáticas pertencem diretamente às classes.

<div class='importante' markdown='1'>

Uma classe estática não tem acesso aos membros da instância da classe externa, somente aos membros estáticos.

</div>

```java
package exemplo_aninhada_estatica;

class Externa {

    static String attrEstatico = "campo estático";
    String attrNaoEstatico = "campo NÃO estático";

    static class Estatica {

        void imprime() {
            System.out.println("método da classe aninhada estática");
            System.out.println(attrEstatico);

            /*
             * Uma classe estática não tem acesso aos membros da instância da classe
             * externa. Possui acesso somente aos membros estáticos.
             */
            // System.out.println(attrNaoEstatico); //ERRO!
        }
    }

    class NaoEstatica {

        void imprime() {
            System.out.println("método da classe aninhada NÃO estática");
            System.out.println(attrEstatico);
            System.out.println(attrNaoEstatico);
        }
    }
}

class Exemplo {

    public static void main(String[] args) {

        /*
         * Não é necessário uma instância da Classe Externa
         */
        Externa.Estatica estatica = new Externa.Estatica();
        estatica.imprime();

        /*
         * Necessário uma instância da Classe Externa
         */
        // Externa.NaoEstatica naoEstatica = new Externa.NaoEstatica(); // ERRO!
        Externa.NaoEstatica naoEstatica = new Externa().new NaoEstatica();
        naoEstatica.imprime();

    }
}

// Saida:
// > método da classe aninhada estática
// > campo estático
// > método da classe aninhada NÃO estática
// > campo estático
// > campo NÃO estático
```

## Classe abstrata

As classes abstratas servem como "modelo" para outras classes que dela herdem, não podendo ser instanciada por si só. Para ter um objeto de uma classe abstrata é necessário criar uma classe herdando dela e então instanciar essa nova classe. Os métodos abstratos da classe abstrata devem então serem sobrescritos nas classes filhas.

<div class='importante' markdown='1'>

- As classes abstratas devem conter pelo menos um método abstrato, que não tem corpo.
- Diferente de interfaces, não há como criar métodos `default` que possuem corpo.
- É um tipo especial de classe que não há como criar instâncias dela.
- É usada apenas para ser herdada, funciona como uma super classe.
- Uma grande vantagem é que força a hierarquia para todas as subclasses.
- É um tipo de contrato que faz com que as subclasses contemplem as mesmas hierarquias e/ou padrões.

</div>

```java
package exemplo_abstrata;

abstract class Abstract {

    String funcaoNaoAbstrata() {
        return "método não abstrato";
    };

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
        // Abstract a = new Abstract(); // ERRO!
    }
}

// Saida:
// > método abstrato implementado
// > método não abstrato
```

## Classe final

Uma classe `final`, é uma classe que não permite que seja criada subclasses dela. Ou seja, ela não pode ser herdada.

```java
package exemplo_final;

final class Exemplo {

    void funcaoNAbstrata() {

        System.out.println("Função não abstrata");
    }
}

/*
 * Não pode haver herança de uma classe declarada como "final"
 */
// class Teste extends Exemplo { } // ERRO!
```

## Links

- [Classes anônimas e aninhadas](https://www.devmedia.com.br/classes-anonimas-e-aninhadas-em-java/31167)
