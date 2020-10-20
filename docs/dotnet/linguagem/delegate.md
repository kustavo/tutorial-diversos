# Delegate

[TOC]

## Introdução

Um delegado (em inglês, *delegate*) é um tipo que encapsula com segurança um método, semelhante a um ponteiro de função em `C` e `C++`. No entanto, ao contrário dos ponteiros de função de `C`, delegados são orientados a objeto, fortemente tipados e seguros. O tipo de um delegado é definido pelo nome do delegado. O exemplo a seguir declara um delegado chamado `Del` que pode encapsular um método que usa uma cadeia de caracteres como um argumento e retorna nulo:

```c#
public delegate void Del(string message);
```

Um objeto delegado é normalmente construído fornecendo-se o nome do método que o delegado encapsulará ou com uma função anônima. Quando um delegado é instanciado, uma chamada de método feita ao delegado será passada pelo delegado para esse método. Os parâmetros passados para o delegado pelo chamador são passados para o método e o valor de retorno, se houver, do método é retornado ao chamador pelo delegado. Isso é conhecido como invocar o delegado. Um delegado instanciado pode ser invocado como se fosse o método encapsulado em si. Por exemplo:

```c#
// Declara o delegado
public delegate void Del(string message);

// Cria o método para o delegado
public static void DelegateMethod(string message)
{
    System.Console.WriteLine(message);
}

// Instancia o delegado
Del handler = DelegateMethod;

// Chama o delegado
handler("Hello World");
```

Veja mais [aqui](<https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/delegates/using-delegates>) e [aqui](https://pt.stackoverflow.com/questions/101238/quando-e-onde-usar-um-delegate-no-c)

## Forma mais moderna

Hoje, em `C#`, usamos mais a `lambda`, que é uma função anônima com uma sintaxe mais simples e que, em geral, dispensa a declaração do delegado.

Isto não quer dizer que ele não exista, apenas que não é preciso escrevê-lo. O compilador infere a assinatura daquele método e cria o delegado para você. Em alguns casos onde a inferência não é possível, dá para usar um `Func` ou `Action` para substituir a declaração do delegado.

## Comparando com interfaces

Uma interface se assemelha muito com um delegado, só que a interface concretiza o método de forma fixa e é definida em tempo de compilação. O delegado pode ser definido em tempo de execução (ainda que o código em si a ser executado também será definido em tempo de compilação).

## Tipos de delegate

### Action

O delegate `Action` encapsula um método que não retorna nada (void) e pode receber de 0 a 4 parâmetros de entrada.

```c#
public delegate void Action();
public delegate void Action<in T1>(T arg1);
public delegate void Action<in T1,in T2>(T1 arg1, T2 arg2);
...
```

Usando

```c#
delegate void ConcatStrings(string string1, string string2);
ConcatStrings concat;

// ou
Action<string, string> concat;
```

Usando com métodos anônimos

```c#
Action<string, string> concat;
concat = delegate(string s1, string s2) { WriteToConsole(s1, s2); };

private static void WriteToConsole(string string1, string string2)
{
    Console.WriteLine("{0}\n{1}", string1, string2);
}
```

Usando com expressões lâmbidas

```c#
Action<string, string> concat;
concat = (s1, s2) => WriteToConsole(s1, s2);

private static void WriteToConsole(string string1, string string2)
{
    Console.WriteLine("{0}\n{1}", string1, string2);
}
```

### Function

O delegate `Function` encapsula um método que retorna um valor do tipo especificado pelo parâmetro `TResult` e pode receber de 0 a 16 parâmetros de entrada.

```c#
public delegate TResult Func<out TResult>();
public delegate TResult Func<in T1, out TResult>(T1 arg1);
public delegate TResult Func<in T1, in T2, out TResult>(T1 arg1, T2 arg2);
...
```

Usando

```c#
delegate string ConvertMethod(string inString);

// ou
Func<string, string> selector;
```

Usando com métodos anônimos

```c#
Func<string, string> convert = delegate(string s)
    { return s.ToUpper();};
```

Usando com expressões lâmbidas

```c#
Func<string, string> convert = s => s.ToUpper();
```

### Predicate

Representa o método que define um conjunto de critérios e determina se o objeto especificado atende a esses critérios. retorna um valor booleano.

```c#
public delegate bool Predicate<in T>(T arg);
```

Usando

```c#
Point[] points = {
new Point(100, 200),
new Point(150, 250),
new Point(250, 375),
new Point(275, 395),
new Point(295, 450)
};

Predicate<Point> predicate = FindPoints;
Point first = Array.Find(points, predicate);

private static bool FindPoints(Point obj)
{
    return obj.X * obj.Y > 100000;
}
```

Usando lambda

```c#
Point first = Array.Find(points, x => x.X * x.Y > 100000 );
```
