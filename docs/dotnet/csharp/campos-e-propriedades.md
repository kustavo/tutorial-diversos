# Campos e propriedades

## Campos

Em C#, a palavra "atributos", como mencionados anteriormente, é referido como **campos** (em inglês, *fields*).

```c#
public class Pessoa
{
    public string nome; // field
    public int idade;   // field
}

class Program
{
    static void Main(string[] args)
    {
        var p = new Pessoa();
        p.nome = "José";
        p.idade = 43;
        Console.WriteLine($"{p.nome }, {p.idade} anos.");
        // saída: José, 43 anos.
    }
}
```

No exemplo acima a classe `Pessoa` tem dois campos públicos que podem ser acessados pela classe `Program`. O estado do objeto `p` está exposto ao mundo exterior.

Um dos pilares da programação orientada a objetos é justamente o **encapsulamento** dos dados da classe, e isso significa que o trabalho interno de uma classe deve ser escondido do mundo exterior.

Ao definir os campos `nome` e `idade` como público, estamos expondo a implementação interna da classe, e isso não é uma boa prática. Nessa abordagem, não temos controle como os dados serão atribuídos ou acessados.

## Propriedades

Para termos controle sobre o acesso aos dados de um objeto, podemos definir os campos como **privados** e utilizar **propriedades** (em inglês, *properties*).

Uma propriedade fornece um mecanismo flexível para ler, escrever ou calcular o valor de um campo privado.

Portanto, envolvemos campos com propriedades para nos dar a capacidade de alterar a implementação sem quebrar o código e para permitir um maior controle sobre os membros da classe que desejamos expor ou ocultar.

Dessa forma, as propriedades expõem campos, o que significa que permitem que uma classe exponha uma forma pública de obter e definir valores, enquanto oculta a implementação.

```c#
class Program
{
    static void Main(string[] args)
    {
        var p = new Pessoa();
        p.Nome = "José Augusto da Silva";
        p.Idade = 43;
        Console.WriteLine($"{p.Nome }, {p.Idade} anos.");
        // saída: José Augus..., 43 anos.
    }
}

public class Pessoa
{
    private string nome; // field
    private int idade;   // field

    // property
    public string Nome {
        get => nome; 
        set 
        {
            if (value.Length > 15)
                nome = $"{value.Substring(0, 10)}...";
            else
                nome = value;
        }
    }

    // property
    public int Idade { get => idade; set => idade = value;}                                  
}
```

No exemplo acima disponibilizamos duas propriedades públicas `Idade` e `Nome`, enquanto protegemos os campos do acesso externo os mantendo privados.

A palavra-chave `value` recebe o valor que foi atribuído a propriedade. As palavras-chaves `get` e `set` significa que estamos dando acesso a essa propriedade para leitura e escrita respectivamente.

Para liberar a leitura da propriedade `Idade` mas restringir a escrita para apenas métodos da classe podemos utilizar `private set`.

```c#
public int Idade { get => idade; private set => idade = value; }
```

Podemos restringir a escrita da propriedade `Idade` removendo `set`. Neste caso, diferente das propriedades automáticas, restringimos o acesso por completo. Podendo agora apenas alterar diretamente o valor do campo `idade` através de algum método ou construtor da classe.

```c#
public int Idade { get => idade; }
```

### Propriedades Automáticas

Propriedade automática é uma propriedade que possui um campo gerado em **tempo de compilação**. Isto evita que os desenvolvedores escrevam *getters* e *setters* primitivos que apenas retornam ou atribuem o valor do campo.

```c#
class Program
{
    static void Main(string[] args)
    {
        var p = new Pessoa();
        p.Nome = "José Augusto da Silva";
        p.Idade = 43;
        Console.WriteLine($"{p.Nome }, {p.Idade} anos.");
    }
}

public class Pessoa
{
    public string Nome { get; set;}
    public int Idade { get; set;}
}
```

Conforme o código abaixo gerado pelo compilador, podemos ver que será criado um "campo de apoio" (em inglês, *backing field*) para cada propriedade automática.

```c#
public class Pessoa
{
    [CompilerGenerated]
    private string <Nome>k__BackingField;

    [CompilerGenerated]
    private int <Idade>k__BackingField;
 
    public string Nome
    {
        [CompilerGenerated]
        get { return <Nome>k__BackingField; }
 
        [CompilerGenerated]
        set { <Nome>k__BackingField = value; }
    }

    public int Idade
    {
        [CompilerGenerated]
        get { return <Idade>k__BackingField; }
 
        [CompilerGenerated]
        set { <Idade>k__BackingField = value; }
    }
}
```

Para liberar a leitura da propriedade `Idade` mas restringir a escrita para apenas métodos da classe podemos utilizar `private set`.

```c#
public int Idade { get; private set;}
```

Diferente da propriedade regular, na propriedade automática, se restringirmos a escrita da propriedade `Idade` removendo `set`, ainda podemos realizar a escrita da propriedade no construtor da classe.

```c#
public int Idade { get;}
```

## Diferença entre campos, atributos e propriedades

- um campo é uma variável declarada diretamente dentro de uma classe ou estrutura. Em geral campos são declarados com visibilidade privada ou protegida para serem inacessíveis pelo mundo exterior.

- uma propriedade é um membro de uma classe ou estrutura com métodos específicos para acessar um campo. Propriedades permitem à uma classe expôr uma maneira de definir os valores de um campo enquanto esconde sua implementação.

- atributos no C# têm um significado diferente do mundo Java e orientação a objetos em geral. Em orientação a objetos atributos representam os dados que uma classe possui. No C# esse conceito é justamente definido pelos campos. No mundo C# um atributo é uma maneira de adicionar informação a um tipo ou membro de tipo. Podemos dizer que são metadados sobre o código.

```c#
public class Produto
{
    private string nome; // campo
    
    [Obsolete] // atributo
    public string Nome { get => nome; set => nome = value;}  // propriedade

    public void Metodo() // método
    { 
        // ...
    } 
}
```

## Referências

- <https://imasters.com.br/back-end/c-diferenca-entre-camposfields-e-propriedadesproperties>
- <https://cursos.alura.com.br/forum/topico-diferenca-entre-campos-atributos-e-propriedades-78582>
