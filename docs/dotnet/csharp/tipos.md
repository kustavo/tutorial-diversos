# Tipos

[TOC]

[[_TOC_]]

## Introdução

As informações armazenadas em um tipo podem incluir o seguinte:

- O espaço de armazenamento que uma variável do tipo requer.
- Os valores mínimo e máximo que ele pode representar.
- Os membros (métodos, campos, eventos e etc.) que ele contém.
- O tipo base do qual ele herda.
- O local no qual a memória para as variáveis será alocada em tempo de execução.
- Os tipos de operações que são permitidos.

O compilador usa as informações de tipo para garantir que todas as operações que são realizadas em seu código sejam fortemente tipadas.

Quando declara uma variável ou constante em um programa, você deve especificar o tipo da variável ou usar a palavra-chave `var` para permitir que o compilador infira o tipo. 

## Tipos internos

A imagem a seguir mostra os tipos de valor e tipos de referência no CTS:

<div class='imagem' markdown='1'>

![tipo-valor-referencia](_tipos/tipos-referencia-e-valor.png)

</div>

### Tipos de valor

Os tipos de valor derivam de `System.ValueType`, que deriva de `System.Object`. Os tipos que derivam de `System.ValueType` apresentam um comportamento especial no CLR. As variáveis de tipo de valor contêm diretamente seus valores, o que significa que a memória é alocada embutida em qualquer contexto em que a variável é declarada. Não há nenhuma alocação de heap separada ou sobrecarga de coleta de lixo para variáveis do tipo de valor.

Um tipo de valor pode ser um dos dois tipos a seguir:

- um tipo de estrutura (`struct`), que encapsula dados e funcionalidades relacionadas.

- um tipo de enumeração (`enum`), que é definido por um conjunto de constantes nomeadas e representa uma escolha ou uma combinação de escolhas.

Os tipos numéricos internos são `structs` e têm propriedades e métodos que você pode acessar:

```c#
// Static method on type byte.
byte b = byte.MaxValue;
```

Mas você declara e atribui valores a eles como se fossem tipos de não agregação simples:

```c#
byte num = 0xA;
int i = 5;
char c = 'Z';
```

Você usa a palavra-chave `struct` para criar seus próprios tipos de valor personalizados. Normalmente, um `struct` é usado como um contêiner para um pequeno conjunto de variáveis relacionadas,

```c#
public struct Coords
{
    public int x, y;

    public Coords(int p1, int p2)
    {
        x = p1;
        y = p2;
    }
}
```

A outra categoria de tipos de valor é `enum`. Uma `enum` define um conjunto de constantes integrais nomeadas. Por exemplo, a enumeração` System.IO.FileMode` na biblioteca de classes do .NET contém um conjunto de números inteiros constantes nomeados que especificam como um arquivo deve ser aberto.

```c#
public enum FileMode
{
    CreateNew = 1,
    Create = 2,
    Open = 3,
    OpenOrCreate = 4,
    Truncate = 5,
    Append = 6,
}
```

Tipos de **valor** incorporado C#:

palavra-chave|Tipo .NET
-----|-----
bool|System.Boolean
byte|System.Byte
sbyte|System.SByte
char|System.Char
decimal|System.Decimal
double|System.Double
float|System.Single
int|System.Int32
uint|System.UInt32
long|System.Int64
ulong|System.UInt64
short|System.Int16
ushort|System.UInt16

### Tipos de referência

No tempo de execução, quando você declara uma variável de um tipo de referência, a variável contém o valor `null` até você criar explicitamente um objeto usando o operador `new` ou atribuir a ele um objeto que foi criado em outro lugar com o operador `new`.

Tipos de referencia: 

- class
- interface
- delegate

- Tipos de referência incorporados:

    palavra-chave|Tipo .NET
    -----|-----
    object | System.Object
    string | System.String
    dynamic| System.Dynamic

## Tipos personalizados

Você usa os construtores `struct`, `classe`, `interface` e `enum` para criar seus próprios tipos personalizados. A biblioteca de classes do .NET em si é uma coleção de tipos personalizados fornecida pela Microsoft, que você pode usar em seus próprios aplicativos.

## Tipos genéricos

Um tipo pode ser declarado com um ou mais parâmetros de tipo que servem como um espaço reservado para o tipo real (o tipo concreto) que o código cliente fornecerá ao criar uma instância do tipo. Esses tipos são chamados de tipos genéricos.

```c#
List<string> stringList = new List<string>();
stringList.Add("String example");

// Erro em tempo de compilação.
stringList.Add(4);
```

## Link
- <https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/types>
