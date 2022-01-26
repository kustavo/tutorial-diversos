# Tipos de tuplas

O recurso de tuplas fornece sintaxe concisa para agrupar vários elementos de dados em uma estrutura de dados leve. O exemplo a seguir mostra como você pode declarar uma variável de tupla, inicializá-la e acessar seus membros de dados:

```c#
(double, int) t1 = (4.5, 3);
Console.WriteLine($"Tuple with elements {t1.Item1} and {t1.Item2}.");
// Saída:
// Tuple with elements 4.5 and 3.

(double Sum, int Count) t2 = (4.5, 3);
Console.WriteLine($"Sum of {t2.Count} elements is {t2.Sum}.");
// Saída:
// Sum of 3 elements is 4.5.
```

Como mostra o exemplo anterior, para definir um tipo de tupla, você especifica os tipos de todos os seus membros de dados e, opcionalmente, os nomes dos campos. Você não pode definir métodos em um tipo de tupla, mas pode usar os métodos fornecidos pelo .NET, como mostra o exemplo a seguir:

```c#
(double, int) t = (4.5, 3);
Console.WriteLine(t.ToString());
Console.WriteLine($"Hash code of {t} is {t.GetHashCode()}.");
// Saída:
// (4.5, 3)
// Hash code of (4.5, 3) is 718460086.
```

Você pode definir tuplas com um grande número arbitrário de elementos:

```c#
var t =
(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
11, 12, 13, 14, 15, 16, 17, 18,
19, 20, 21, 22, 23, 24, 25, 26);
Console.WriteLine(t.Item26);  // output: 26
```

Os tipos de tupla oferecem suporte a operadores de igualdade `==` e `!=`.

Tipos de tupla são tipos de valor; os elementos da tupla são campos públicos. Isso torna as tuplas tipos de valor mutáveis.

## Casos de uso de tuplas

Um dos casos de uso mais comuns de tuplas é como um tipo de retorno de método. Ou seja, em vez de definir os parâmetros do método, você pode agrupar os resultados do método em um tipo de retorno de tupla. Como mostra o exemplo abaixo, você pode trabalhar com a instância de tupla retornada diretamente ou desconstruí-la em variáveis separadas. Você também pode usar tipos de tupla em vez de tipos anônimos; por exemplo, em consultas LINQ.

```c#
var xs = new[] { 4, 7, 9 };
var limits = FindMinMax(xs);
Console.WriteLine($"Limits of [{string.Join(" ", xs)}] are {limits.min} and {limits.max}");
// Saída:
// Limits of [4 7 9] are 4 and 9

var ys = new[] { -9, 0, 67, 100 };
var (minimum, maximum) = FindMinMax(ys);
Console.WriteLine($"Limits of [{string.Join(" ", ys)}] are {minimum} and {maximum}");
// Saída:
// Limits of [-9 0 67 100] are -9 and 100

(int min, int max) FindMinMax(int[] input)
{
    if (input is null || input.Length == 0)
        throw new ArgumentException("Cannot find minimum and maximum of a null or empty array.");

    var min = int.MaxValue;
    var max = int.MinValue;
    foreach (var i in input)
    {
        if (i < min)
            min = i;

        if (i > max)
            max = i;
    }
    return (min, max);
}
```

Normalmente, você usa tuplas para agrupar elementos de dados vagamente relacionados. Isso geralmente é útil em métodos de utilitários privados e internos. No caso de API pública, considere definir uma classe ou um tipo de estrutura.

## Nomes de campos de tupla

Você pode especificar explicitamente os nomes dos campos de tupla em uma expressão de inicialização de tupla ou na definição de um tipo de tupla, como mostra o exemplo a seguir:

```c#
var t = (Sum: 4.5, Count: 3);
Console.WriteLine($"Sum of {t.Count} elements is {t.Sum}.");

(double Sum, int Count) d = (4.5, 3);
Console.WriteLine($"Sum of {d.Count} elements is {d.Sum}.");
```

Se você não especificar um nome de campo, ele pode ser inferido a partir do nome da variável correspondente em uma expressão de inicialização de tupla, como mostra o exemplo a seguir:

```c#
var sum = 4.5;
var count = 3;
var t = (sum, count);
Console.WriteLine($"Sum of {t.count} elements is {t.sum}.");
```

Isso é conhecido como inicializadores de projeção de tupla. O nome de uma variável não é projetado em um nome de campo de tupla nos seguintes casos:

- O nome do candidato é um nome de membro de um tipo de tupla, por exemplo, `Item3`, `ToString` ou `Rest`.
- O nome do candidato é uma duplicata de outro nome de campo de tupla, explícito ou implícito.

Nesses casos, você especifica explicitamente o nome de um campo ou acessa um campo por seu nome padrão.

Os nomes padrão dos campos de tupla são `Item1`, `Item2`, `Item3` e assim por diante. Você sempre pode usar o nome padrão de um campo, mesmo quando um nome de campo é especificado explicitamente ou inferido, como mostra o exemplo a seguir:

```c#
var a = 1;
var t = (a, b: 2, 3);
Console.WriteLine($"The 1st element is {t.Item1} (same as {t.a}).");
Console.WriteLine($"The 2nd element is {t.Item2} (same as {t.b}).");
Console.WriteLine($"The 3rd element is {t.Item3}.");
// Saída:
// The 1st element is 1 (same as 1).
// The 2nd element is 2 (same as 2).
// The 3rd element is 3.
```

Atribuição de tupla e comparações de igualdade de tupla não levam nomes de campo em consideração.

Em tempo de compilação, o compilador substitui os nomes de campo não padrão pelos nomes padrão correspondentes. Como resultado, nomes de campo explicitamente especificados ou inferidos não estão disponíveis em tempo de execução.

## Atribuição e desconstrução de tupla

C# oferece suporte à atribuição entre tipos de tupla que satisfazem as duas condições a seguir:

- Ambos os tipos de tupla têm o mesmo número de elementos.
- Para cada posição da tupla, o tipo do elemento da tupla à direita é o mesmo ou implicitamente conversível para o tipo do elemento da tupla à esquerda correspondente.

Os valores dos elementos da tupla são atribuídos de acordo com a ordem dos elementos da tupla. Os nomes dos campos de tupla são ignorados e não atribuídos, como mostra o exemplo a seguir:

```c#
(int, double) t1 = (17, 3.14);
(double First, double Second) t2 = (0.0, 1.0);
t2 = t1;
Console.WriteLine($"{nameof(t2)}: {t2.First} and {t2.Second}");
// Saída:
// t2: 17 and 3.14

(double A, double B) t3 = (2.0, 3.0);
t3 = t2;
Console.WriteLine($"{nameof(t3)}: {t3.A} and {t3.B}");
// Saída:
// t3: 17 and 3.14
```

Você também pode usar o operador de atribuição `=` para desconstruir uma instância de tupla em variáveis separadas. Você pode fazer isso de uma das seguintes maneiras:

- Declare explicitamente o tipo de cada variável entre parênteses:

  ```c#
  var t = ("post office", 3.6);
  (string destination, double distance) = t;
  Console.WriteLine($"Distance to {destination} is {distance} kilometers.");
  // Saída:
  // Distance to post office is 3.6 kilometers.
  ```

- Use a palavra-chave `var` fora dos parênteses para declarar variáveis digitadas implicitamente e deixar o compilador inferir seus tipos:

  ```c#
  var t = ("post office", 3.6);
  var (destination, distance) = t;
  Console.WriteLine($"Distance to {destination} is {distance} kilometers.");
  // Saída:
  // Distance to post office is 3.6 kilometers.
  ```

- Use variáveis existentes:

  ```c#
  var destination = string.Empty;
  var distance = 0.0;

  var t = ("post office", 3.6);
  (destination, distance) = t;
  Console.WriteLine($"Distance to {destination} is {distance} kilometers.");
  // Saída:
  // Distance to post office is 3.6 kilometers.
  ```

## Igualdade de tupla

Os tipos de tupla suportam os operadores `==` e `!=`. Esses operadores comparam membros do operando à esquerda com os membros correspondentes do operando à direita seguindo a ordem dos elementos da tupla.

```c#
(int a, byte b) left = (5, 10);
(long a, int b) right = (5, 10);
Console.WriteLine(left == right);  // saída: True
Console.WriteLine(left != right);  // saída: False

var t1 = (A: 5, B: 10);
var t2 = (B: 5, A: 10);
Console.WriteLine(t1 == t2);  // saída: True
Console.WriteLine(t1 != t2);  // saída: False
```

Como mostra o exemplo anterior, as operações `==` e `!=` não levam em consideração nomes de campo de tupla.

Duas tuplas são comparáveis quando ambas as seguintes condições são satisfeitas:

- Ambas as tuplas têm o mesmo número de elementos. Por exemplo, `t1 != t2` não é compilado se `t1` e `t2` têm números diferentes de elementos.
- Para cada posição da tupla, os elementos correspondentes dos operandos da tupla à esquerda e à direita são comparáveis aos operadores `==` e `!=`. Por exemplo, `(1, (2, 3)) == ((1, 2), 3)` não compila porque `1` não é comparável com `(1, 2)`.

Os operadores `==` e `!=` comparam tuplas em curto-circuito. Ou seja, uma operação para assim que encontra um par de elementos não iguais ou atinge o final das tuplas. No entanto, antes de qualquer comparação, todos os elementos da tupla são avaliados, como mostra o exemplo a seguir:

```c#
Console.WriteLine((Display(1), Display(2)) == (Display(3), Display(4)));

int Display(int s)
{
    Console.WriteLine(s);
    return s;
}
// Saída:
// 1
// 2
// 3
// 4
// False
```

## Tuplas como parâmetros de saída

Normalmente, você refatora um método que tem parâmetros `out` em um método que retorna uma tupla. No entanto, há casos em que um parâmetro `out` pode ser do tipo tupla. O exemplo a seguir mostra como trabalhar com tuplas como parâmetros `out`:

```c#
var limitsLookup = new Dictionary<int, (int Min, int Max)>()
{
    [2] = (4, 10),
    [4] = (10, 20),
    [6] = (0, 23)
};

if (limitsLookup.TryGetValue(4, out (int Min, int Max) limits))
{
    Console.WriteLine($"Found limits: min is {limits.Min}, max is {limits.Max}");
}
// Saída:
// Found limits: min is 10, max is 20
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/value-tuples>
