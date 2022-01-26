# Classes e objetos

## Introdução

Um tipo de dado abstrato é uma descrição de como vemos os dados e que operações são permitidas com eles. Isso nos permite usar e manipular esses dados sem nos preocuparmos em como eles são realmente implementados em uma linguagem de baixo nível.

Na evolução das linguagens de programação, a necessidade de se representar e manipular informações complexas resultou no conceito de classes e objetos. Classes são abstrações e descrevem como os dados serão representados e manipulados.

Na programação orientada a objeto, **classe** é uma estrutura que funciona como um template para a criação de objetos. Uma classe define os possíveis estados e  comportamentos que seus objetos irão possuir. Um **objeto**, é uma instância (ou seja, um exemplar) de uma classe.

Uma classe comumente define o estado e o comportamento de um objeto implementando **atributos** e **métodos**. Os atributos (por vezes referidos como "campos", "membros de dados" ou "propriedades"), definem as possíveis informações armazenadas por um objeto de uma classe, representando o **estado** de cada objeto. Os métodos (por vezes referidos como "operações" ou serviços) definem comportamentos e ações oferecidos por objetos de uma classe, sendo responsáveis por alterar o estado ou fornecer informações sobre um objeto.

!!! note "Notas"
    Em C# os atributos são referenciados como **campos** (em inglês, *fields*).

Em outras palavras, uma classe descreve os serviços oferecidos por seus objetos e quais informações eles podem armazenar.

**Outros elementos associados a uma classe:**

- **Construtor e destrutor:** métodos especiais que definem o comportamento do objeto de uma classe no momento da sua criação e destruição.
- **Propriedade:** define o acesso a um estado do objeto.
- **Evento:** define um ponto em que o objeto pode chamar outros procedimentos de acordo com seu comportamento e estado interno.

Exemplo de uma classe em C#:

```c#
class Pessoa
{
    string nome; // atributo (campo em C#)
    int idade;   // atributo (campo em C#)

    bool PessoaIdosa() // método
    {
        return idade > 65;
    }
}
```

## Classes aninhadas

A palavra "aninhada" em programação (*nested*, em inglês) diz respeito que é uma sub-rotina encapsulada em outra. O escopo da sub-rotina aninhada é limitado a sub-rotina que a encapsula. Isso significa que ela pode ser chamada somente pela sub-rotina que a encapsula, ou pelas sub-rotinas diretamente ou indiretamente aninhadas pela mesma sub-rotina encapsuladora. O aninhamento é teoricamente ilimitado, ainda que na prática somente alguns níveis são aceitos, o que depende da implementação.

**Vantagens:**

- Agrupamento de classes pequenas que são usadas em apenas uma classe.
- Aumento de Encapsulamento.
- Código mais legível e de fácil manutenção.

```c#
public class Container
{
    public Container()
    {
        var a = new Aninhada();
        a.AninhadaPublic();
        a.AninhadaPrivate(); // Erro! Método inacessível
    }

    public void ContainerPublic() { }
    private void ContainerPrivate() { }

    public class Aninhada
    {
        public Aninhada()
        {
            var c = new Container();
            c.ContainerPublic();
            c.ContainerPrivate(); // Ok!
        }

        public void AninhadaPublic() { }
        private void AninhadaPrivate() { }
    }
}
```

## Referências

- <https://pt.wikibooks.org/wiki/Programa%C3%A7%C3%A3o_Orientada_a_Objetos/Classes_e_Objetos>
- <https://panda.ime.usp.br/algoritmos/static/algoritmos/02-poo.html>
- <https://pt.wikipedia.org/wiki/Classe_(programa%C3%A7%C3%A3o)>
