# Reflexão

[TOC]

## Introdução

A utilização de aplicações para as mais diversas finalidades tem aumentado, e conseqüentemente a exigência de funcionalidades por seus usuários. A necessidade de funcionalidades muito específicas e/ou em maior número, podem tornar o seu desenvolvimento inviável para a empresa responsável pela aplicação. Uma alternativa para viabilizar o desenvolvimento destas funcionalidades é permitir que estas sejam criadas por terceiros e acopladas à aplicação. Muitos aplicativos permitem a extensão de funcionalidades através da utilização de módulos externos, chamados de ***plug-ins***, que são acoplados a uma aplicação-base. Esta extensão pode ser permitida em aplicativos Java através da utilização da reflexão de classes.

Imagine se toda vez que um novo *plugin* fosse desenvolvido para algum aplicativo (Eclipse, Firefox, Chrome, etc) ele tivesse que ser refatorado a fim de adaptar-se ao uso desse novo *plugin*. Seria muito trabalhoso e inviável, dado a quantidade de *plugins* que são desenvolvidos para estes aplicativos. A solução para isso é sem dúvida o uso de reflexão, com ela podemos deixar, por exemplo, pontos estratégicos do sistema para aceitar a instalação de *plugins* afim de tornar o sistema extensível.

Reflexão é um recurso da API Java que possibilita aos aplicativos o acesso e a modificação do comportamento de aplicações que estão rodando na *Java Virtual Machine*. Uma classe pode acessar outras classes **em tempo de execução**, sem conhecer sua definição no momento da compilação. Informações relativas à esta definição, como seus construtores, métodos e campos, podem ser facilmente acessados através de métodos de reflexão da API Java. Classes externas à aplicação, que não foram compiladas junto a mesma, podem ser instanciadas para utilização de seus recursos. Os recursos de reflexão oferecidos pela API Java, na maioria dos casos, são utilizados para prover extensão de funcionalidades a aplicações, desenvolvimento de ferramentas de *debug* e aplicativos que permitem a navegação no conteúdo de classes compiladas.

Para todo tipo de objeto, a *Java Virtual Machine* cria uma instância imutável de `java.lang.Class`, que provê métodos para examinar as propriedades do objeto em tempo de execução. Esta classe é o ponto de partida para trabalhar com reflexão.

No exemplo abaixo não sabemos quais os métodos e campos da classe `Pessoa`, para isso vamos utilizar as ferramentas oferecidas pelo reflexão afim de descobrir essas informações em tempo de execução. Veja que com os métodos `getDeclaredFields()` e `getDeclaredMethods()` conseguimos capturar todos os campos e métodos da classe em questão, mesmo sem saber como ela foi construída.

```java
package _reflexao.exemplo1_reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Exemplo {

    public static void main(String[] args) {

        /*
         * O método getClass() da classe Object retorna a classe de um determinado objeto em
         * tempo de execução.
         */
        Class c1 = "minha string".getClass();
        Class c2 = boolean.class;
        Class c3 = java.io.PrintStream.class;

        System.out.println(c1 + " | " + c2 + " |" + c3);

        Class<Pessoa> classe = Pessoa.class;
        for (Field campo : classe.getDeclaredFields()) {
            System.out.println(campo.getName());
        }

        for (Method metodo : classe.getDeclaredMethods()) {
            System.out.println(metodo.getName());
        }
    }
}

class Pessoa {

    String nome;
    int idade;

    Pessoa() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

// Saida
// > class java.lang.String | boolean |class java.io.PrintStream
// > nome
// > idade
// > setNome
// > getIdade
// > setIdade
// > getNome
```

No exemplo abaixo, usamos o método `invoke()` para invocar o método e passamos como argumento a instância que executará o método.

```java
package _reflexao.exemplo2_reflexao;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Exemplo {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        pessoa.setSexo('M');
        pessoa.setNome("Gabriel");

        Map<String, Object> attributes = Reflexao.getMapaCampos(pessoa);

        for (String key : attributes.keySet()) {
            System.out.println(key + ": " + attributes.get(key));
        }
    }
}

class Reflexao {

    public static Map<String, Object> getMapaCampos(Object obj) {

        Map<String, Object> attributesMap = new HashMap<>();

        Class<?> classeObj = obj.getClass();
        Method[] metodos = classeObj.getMethods();

        for (Method metodo : metodos) {

            if (metodo.getName().startsWith("get") && metodo.getReturnType() != void.class) {

                String nomeCampo = metodo.getName().substring(3);

                try {

                    /*
                     * Invoca o método corrente passando o objeto como argumento e recebe o
                     * retorno do método.
                     */
                    Object value = metodo.invoke(obj);

                    /*
                     * Adiciona as chaves 'nome método sem get' + 'retorno do método'
                     */
                    attributesMap.put(nomeCampo, value);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return attributesMap;
    }
}

class Pessoa {

    String nome;
    char sexo;

    Pessoa() {}

    public String getNome() {
        return (sexo == 'M' ? "Sr. " : "Sra. ") + nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}

// Saida
// > Class: class _reflexao.exemplo2_reflexao.Pessoa
// > Nome: Sr. Gabriel
// > Sexo: M
```

<div class='importante' markdown='1'>

Os termos comumente utilizados são reflexão e introspecção. Na reflexão, um programa observa e modifica seu comportamento enquanto que na introspecção ele apenas observa e obtém informações dele mesmo.

A linguagem Java possui uma API de *reflection* que, na verdade, possui muito mais características de introspecção do que reflexão.

</div>
