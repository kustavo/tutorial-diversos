# Lambda

[TOC]

## Introdução

Uma expressão Lambda é uma função anônima que você pode usar para criar delegados ou tipos de árvore de expressão. Ao usar expressões lambda, você pode escrever funções locais que podem ser passadas como argumentos ou retornadas como o valor de chamadas de função. Essas expressões são particularmente úteis para escrever expressões de consulta `LINQ`.

Para facilitar o entendimento, a expressão lambda é uma espécie de função, porém sem nome. Ela realiza cálculos, filtros e retorna valores ou coleções de valores.

Use o operador de declaração lambda **`=>`** para separar a lista de parâmetros de lambda do corpo. Para criar uma expressão lambda, especifique os parâmetros de entrada (se houver) no lado esquerdo do operador lambda e uma expressão ou um bloco de instrução do outro lado.

Qualquer expressão lambda pode ser convertida para um tipo delegado. O tipo delegado no qual uma expressão lambda pode ser convertida é definido pelos tipos de parâmetros e pelo valor retornado. Se uma expressão lambda não retornar um valor, ela poderá ser convertida em um dos tipos delegados `Action`; caso contrário, ela poderá ser convertida em um dos tipos delegados `Func`. Por exemplo, uma expressão lambda que tem dois parâmetros e não retorna nenhum valor pode ser convertida em um delegado `Action<T1,T2>`. Uma expressão lambda que tem um parâmetro e retorna um valor pode ser convertida em um delegado `Func<T, TResult>`. No seguinte exemplo, a expressão lambda `x => x * x`, que especifica um parâmetro chamado `x` e retorna o valor de `x` ao quadrado, é atribuída a uma variável de um tipo delegado:

```c#
Func<int, int> square = x => x * x;
Console.WriteLine(square(5));

// Saida:
// > 25
```

As expressões lambdas também podem ser convertidas nos tipos de árvore de expressão, como mostra o seguinte exemplo:

```c#
System.Linq.Expressions.Expression<Func<int, int>> e = x => x * x;
Console.WriteLine(e);

// saida:
// >  x => (x * x)
```

Use expressões lambda em qualquer código que exija instâncias de tipos delegados ou árvores de expressão, como por exemplo, como um argumento ao método `Task.Run(Action)` para passar o código que deve ser executado em segundo plano. Use também expressões lambda ao escrever expressões de consulta LINQ, como mostra o seguinte exemplo:

```c#
int[] numbers = { 2, 3, 4, 5 };
var squaredNumbers = numbers.Select(x => x * x);
Console.WriteLine(string.Join(" ", squaredNumbers));

// Saida:
// > 4 9 16 25
```

## Lambdas de expressão

Formato:

```c#
(<parametros>) => expressão
```

Exemplos:

```c#
// Sem parâmetros de entrada
Action line = () => Console.WriteLine();

// Dois ou mais parâmetros de entrada
// O último parâmetro é sempre o retorno
Func<int, int, bool> isIgual = (x, y) => x == y;

// Especificando os tipos de entrada
Func<int, string, bool> isMaior = (int x, string s) => s.Length > x;
```

## Lambdas de instrução

Formato:

```c#
(<parametros) => { <sequência de comandos> }
```

Exemplo:

```c#
Action<string> dizer = name => 
{ 
    string msg = $"Hello {name}!";
    Console.WriteLine(msg);
};
dizer("World");

// Saida:
// > Hello World!
```

## Lambdas assíncronos

Exemplo:

```c#
public partial class Form1 : Form
{
    public Form1()
    {
        InitializeComponent();
        button1.Click += async (sender, e) =>
        {
            await ExemploAsync();
            textBox1.Text += "Cliquei!";
        };
    }

    private async Task ExemploAsync()
    {
        await Task.Delay(1000);
    }
}
```

## Expressões lambda e tuplas

Normalmente, os campos de uma tupla são chamados de Item1, Item2, etc.

```c#
// Parâmetros de entrada e tipos de saídas
Func<(int, int, int), (int, int, int)> dobrar = ns => 
    (2 * ns.Item1, 2 * ns.Item2, 2 * ns.Item3);

var numeros = (2, 3, 4);
var dobro = dobrar(numeros);
Console.WriteLine($"O conjunto {numeros} e o dobro: {dobro}");

// Saida:
// O conjunto (2, 3, 4) e o dobro: (4, 6, 8)
```

Campos nomeados:

```c#
// Parâmetros de entrada e tipos de saídas

Func<(int n1, int n2, int n3), (int, int, int)> dobrar = ns => 
    (2 * ns.n1, 2 * ns.n2, 2 * ns.n3);
var numeros = (2, 3, 4);
var dobro = dobrar(numeros);
Console.WriteLine($"O conjunto {numeros} e o dobro: {dobro}");

// Saida:
// O conjunto (2, 3, 4) e o dobro: (4, 6, 8)
```

