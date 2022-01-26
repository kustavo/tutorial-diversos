# Tipos de estrutura

Um tipo de estrutura é um tipo de valor que pode encapsular dados e funcionalidades relacionadas. Você usa a palavra-chave `struct` para definir um tipo de estrutura:

```c#
public struct Coords
{
    public Coords(double x, double y)
    {
        X = x;
        Y = y;
    }

    public double X { get; }
    public double Y { get; }

    public override string ToString() => $"({X}, {Y})";
}
```

Os tipos de estrutura têm semântica de valor. Ou seja, uma variável de um tipo de estrutura contém uma instância do tipo. Por padrão, os valores das variáveis são copiados na atribuição, passando um argumento para um método e retornando um resultado do método. No caso de uma variável do tipo de estrutura, uma instância do tipo é copiada.

!!! note "Notas"

    Normalmente, você usa tipos de estrutura para projetar pequenos tipos centrados em dados que fornecem pouco ou nenhum comportamento. Por exemplo, o .NET usa tipos de estrutura para representar um número (inteiro e real), um valor booleano, um caractere Unicode, uma instância de tempo. Se você está focado no comportamento de um tipo, considere definir uma classe. Os tipos de classe têm semântica de referência. Ou seja, uma variável de um tipo de classe contém uma referência a uma instância do tipo, não a própria instância.

Como os tipos de estrutura têm semântica de valor, recomendamos que você defina os tipos de estrutura imutáveis.

## readonly struct

Podemos usar o modificador `readonly` para declarar que um tipo de estrutura é imutável. Todos os membros de dados de uma estrutura `readonly` devem ser somente leitura da seguinte maneira:

- Qualquer declaração de campo deve ter o modificador `readonly`
- Qualquer propriedade, incluindo as implementadas automaticamente, deve ser somente leitura. No C# 9.0 e posterior, uma propriedade pode ter um acessador `init`.

Isso garante que nenhum membro de uma estrutura `readonly` modifique o estado da estrutura. Isso significa que outros membros da instância, exceto os construtores, são implicitamente somente leitura.

!!! note "Notas"

    Em uma estrutura `readonly`, um membro de dados de um tipo de referência mutável ainda pode sofrer mutação em seu próprio estado. Por exemplo, você não pode substituir uma instância de `List<T>`, mas pode adicionar novos elementos a ela.

O código a seguir define uma estrutura `readonly` com configuradores de propriedade somente `init`, disponíveis em C# 9.0 e posterior:

```c#
public readonly struct Coords
{
    public Coords(double x, double y)
    {
        X = x;
        Y = y;
    }

    public double X { get; init; }
    public double Y { get; init; }

    public override string ToString() => $"({X}, {Y})";
}
```

Se tentarmos alterar o valor de alguma propriedade de uma estrutura `readonly`, teremos erro em tempo de compilação.

```c#
class Exemplo
{
    public static void Main() {
        var a = new Coords(1,2);
        a.X = 4 ; // Erro! Propriedade de estrutura "readonly" precisa ser somente leitura.
    }

    public readonly struct Coords
    {
        public Coords(double x, double y)
        {
            X = x;
            Y = y;
        }

        public double X { get; set;} // Permite escrita
        public double Y { get; init;}

        public override string ToString() => $"({X}, {Y})";
    }
}
```

## readonly membros de instância

Também podemos usar o modificador `readonly` para declarar que um membro da instância não modifica o estado de uma estrutura. Se você não pode declarar todo o tipo de estrutura como `readonly`, use o modificador `readonly` para marcar os membros da instância que não modificam o estado da estrutura.

Em um membro de instância `readonly`, você não pode atribuir aos campos de instância da estrutura. No entanto, um membro `readonly` pode chamar um membro não `readonly`. Nesse caso, o compilador cria uma cópia da instância da estrutura e chama o membro não `readonly` dessa cópia. Como resultado, a instância da estrutura original não é modificada.

Normalmente, você aplica o modificador `readonly` aos seguintes tipos de membros de instância:

### Métodos

```c#
public readonly double Sum()
{
    return X + Y;
}
```

Você também pode aplicar o modificador `readonly` a métodos que substituem métodos declarados em `System.Object`:

```c#
public readonly override string ToString() => $"({X}, {Y})";
```

#### Propriedades e indexadores

```c#
private int counter;
public int Counter
{
    readonly get => counter;
    set => counter = value;
}
```

Se você precisar aplicar o modificador `readonly` a ambos os acessadores de uma propriedade ou indexador, aplique-o na declaração da propriedade ou indexador.

!!! note "Notas"

    O compilador declara um acessador `get` de uma propriedade autoimplementada como `readonly`, independentemente da presença do modificador `readonly` em uma declaração de propriedade.

No C# 9.0 e posterior, você pode aplicar o modificador `readonly` a uma propriedade ou indexador com um acessador `init`:

```c#
public readonly double X { get; init; }
```

!!! note "Notas"

    - Você não pode aplicar o modificador `readonly` a membros estáticos de um tipo de estrutura.
    - O compilador pode usar o modificador `readonly` para otimizações de desempenho.

## Mutação não destrutiva

No C# 10.0 e posterior, você pode usar a expressão `with` se precisar alterar propriedades ou campos imutáveis de uma instância do tipo de estrutura. Uma expressão `with` faz uma cópia de seu operando com as propriedades especificadas e os campos modificados. Use a sintaxe do inicializador de objetos para especificar quais membros modificar e seus novos valores, como mostra o exemplo a seguir:

```c#
public readonly struct Coords
{
    public Coords(double x, double y)
    {
        X = x;
        Y = y;
    }

    public double X { get; init; }
    public double Y { get; init; }

    public override string ToString() => $"({X}, {Y})";
}

public static void Main()
{
    var p1 = new Coords(0, 0);
    Console.WriteLine(p1);  // saída: (0, 0)

    var p2 = p1 with { X = 3 };
    Console.WriteLine(p2);  // saída: (3, 0)

    var p3 = p1 with { X = 1, Y = 4 };
    Console.WriteLine(p3);  // saída: (1, 4)
}
```

## Limitações de projeto de um tipo de estrutura

- Um construtor de um tipo de estrutura deve inicializar todos os campos de instância do tipo.

- Um tipo de estrutura não pode herdar de outra classe ou tipo de estrutura e não pode ser a base de uma classe. No entanto, um tipo de estrutura pode implementar interfaces.

- Você não pode declarar um `finalizer` dentro de um tipo de estrutura.

## Construtores sem parâmetros e inicializadores de campo

No C# 10.0 e posterior, você pode declarar um construtor de instância sem parâmetros em um tipo de estrutura, como mostra o exemplo a seguir:

```c#
public readonly struct Measurement
{
    public Measurement()
    {
        Value = double.NaN;
        Description = "Undefined";
    }

    public Measurement(double value, string description)
    {
        Value = value;
        Description = description;
    }

    public double Value { get; init; }
    public string Description { get; init; }

    public override string ToString() => $"{Value} ({Description})";
}

public static void Main()
{
    var m1 = new Measurement();
    Console.WriteLine(m1);  // saída: NaN (Undefined)

    var m2 = default(Measurement);
    Console.WriteLine(m2);  // saída: 0 ()

    var ms = new Measurement[2];
    Console.WriteLine(string.Join(", ", ms));  // saída: 0 (), 0 ()
}
```

Como mostra o exemplo anterior, a expressão de valor padrão ignora um construtor sem parâmetros e produz o valor padrão de um tipo de estrutura, que é o valor produzido pela definição de todos os campos de tipo valor para seus valores padrão (o padrão de 0 bits) e todas os campos tipo referência como `null`. A instanciação da matriz de tipo de estrutura também ignora um construtor sem parâmetros e produz uma matriz preenchida com os valores padrão de um tipo de estrutura.

A partir do C# 10.0, você também pode inicializar um campo de instância ou propriedade em sua declaração, como mostra o exemplo a seguir:

```c#
public readonly struct Measurement
{
    public Measurement(double value)
    {
        Value = value;
    }

    public Measurement(double value, string description)
    {
        Value = value;
        Description = description;
    }

    public double Value { get; init; }
    public string Description { get; init; } = "Ordinary measurement";

    public override string ToString() => $"{Value} ({Description})";
}

public static void Main()
{
    var m1 = new Measurement(5);
    Console.WriteLine(m1);  // saída: 5 (Ordinary measurement)

    var m2 = new Measurement();
    Console.WriteLine(m2);  // saída: 0 ()
}
```

Se você não declarar um construtor sem parâmetros explicitamente, um tipo de estrutura fornece um construtor sem parâmetros cujo comportamento é o seguinte:

- Se um tipo de estrutura tem construtores de instância explícitos ou não tem inicializadores de campo, um construtor implícito sem parâmetros produz o valor padrão de um tipo de estrutura, independentemente dos inicializadores de campo, como mostra o exemplo anterior.

- Se um tipo de estrutura não tem construtores de instância explícitos e tem inicializadores de campo, o compilador sintetiza um construtor público sem parâmetros que executa as inicializações de campo especificadas, como mostra o exemplo a seguir:

  ```c#
  public struct Coords
  {
      public double X = double.NaN;
      public double Y = double.NaN;

      public override string ToString() => $"({X}, {Y})";
  }

  public static void Main()
  {
      var p1 = new Coords();
      Console.WriteLine(p1);  // saída: (NaN, NaN)

      var p2 = default(Coords);
      Console.WriteLine(p2);  // saída: (0, 0)

      var ps = new Coords[3];
      Console.WriteLine(string.Join(", ", ps));  // saída: (0, 0), (0, 0), (0, 0)
  }
  ```

  Como mostra o exemplo anterior, a expressão de valor padrão e a instanciação da matriz ignoram os inicializadores de campo.

## Instanciação de um tipo de estrutura

Em C#, você deve inicializar uma variável declarada antes que ela possa ser usada. Como uma variável de tipo de estrutura não pode ser nula (a menos que seja uma variável de um tipo de valor anulável), você deve instanciar uma instância do tipo correspondente. Existem várias maneiras de fazer isso.

Normalmente, você instancia um tipo de estrutura chamando um construtor apropriado com o novo operador. Cada tipo de estrutura tem pelo menos um construtor. Esse é um construtor implícito sem parâmetros, que produz o valor padrão do tipo. Você também pode usar uma expressão de valor padrão para produzir o valor padrão de um tipo.

Se todos os campos de instância de um tipo de estrutura estiverem acessíveis, você também pode instanciá-lo sem o novo operador. Nesse caso, você deve inicializar todos os campos da instância antes do primeiro uso da instância. O exemplo a seguir mostra como fazer isso:

```c#
public static class StructWithoutNew
{
    public struct Coords
    {
        public double x;
        public double y;
    }

    public static void Main()
    {
        Coords p;
        p.x = 3;
        p.y = 4;
        Console.WriteLine($"({p.x}, {p.y})");  // saída: (3, 4)
    }
}
```

No caso dos tipos de valor integrados, use os literais correspondentes para especificar um valor do tipo.

## Passando variáveis de tipo de estrutura por referência

Quando você passa uma variável de tipo de estrutura para um método como um argumento ou retorna um valor de tipo de estrutura de um método, toda a instância de um tipo de estrutura é copiada. Isso pode afetar o desempenho do seu código em cenários de alto desempenho que envolvem grandes tipos de estrutura. Você pode evitar a cópia de valor passando uma variável do tipo de estrutura por referência. Use os modificadores de parâmetro de método `ref`, `out` ou `in` para indicar que um argumento deve ser passado por referência. Use _returns_ `ref` para retornar um resultado de método por referência.

### Estrutura `ref`

Podemos usar o modificador `ref` na declaração de um tipo de estrutura. Instâncias de um tipo de estrutura `ref` são alocadas na **pilha** e não podem ir para o _heap_ gerenciado. Para garantir isso, o compilador limita o uso de tipos de estrutura `ref` da seguinte maneira:

- Uma estrutura `ref` não pode ser o tipo de elemento de uma matriz.
- Uma estrutura `ref` não pode ser um tipo declarado de um campo de uma classe ou uma estrutura não `ref`.
- Uma estrutura `ref` não pode implementar interfaces.
- Uma estrutura `ref` não pode ser envelopada em `System.ValueType` ou `System.Object`.
- Uma estrutura `ref` não pode ser um argumento de tipo.
- Uma variável de estrutura `ref` não pode ser capturada por uma expressão lambda ou uma função local.
- Uma variável de estrutura `ref` não pode ser usada em um método assíncrono. No entanto, você pode usar variáveis estrutura `ref` em métodos síncronos, por exemplo, naqueles que retornam `Task` ou `Task <TResult>`.
- Uma variável de estrutura `ref` não pode ser usada em iteradores.

Normalmente, você define um tipo de estrutura `ref` quando precisa de um tipo que também inclui membros de dados de tipos de estrutura `ref`:

```c#
public ref struct CustomRef
{
    public bool IsValid;
    public Span<int> Inputs;
    public Span<int> Outputs;
}
```

Para declarar uma estrutura `ref` como `readonly`, combine os modificadores `readonly` e `ref` na declaração de tipo (o modificador `readonly` deve vir antes do modificador `ref`):

```c#
public readonly ref struct ConversionRequest
{
    public ConversionRequest(double rate, ReadOnlySpan<double> values)
    {
        Rate = rate;
        Values = values;
    }

    public double Rate { get; }
    public ReadOnlySpan<double> Values { get; }
}
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/struct>
