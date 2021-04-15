# Instruções, expressões e operadores

[TOC]

[[_TOC_]]

O código C# que compõe um aplicativo consiste em instruções (em inglês, *statements*) compostas por palavras-chave, expressões e operadores.

## Instruções

As ações que um programa realiza são expressas em instruções (em inglês, *statements*). Ações comuns incluem declarar variáveis, atribuir valores, chamar métodos, lações de repetição em coleções e execução de um ou outro bloco de código, dependendo de uma determinada condição. A ordem na qual as instruções são executadas em um programa é chamada de fluxo de controle ou fluxo de execução. O fluxo de controle pode variar sempre que um programa é executado, dependendo de como o programa reage às entradas que recebe em tempo de execução.

Uma instrução pode consistir em uma única linha de código que termina em (`;`) ou uma série de instruções de uma linha em um bloco. Um bloco de instrução é colocado entre (`{}`) e pode conter blocos aninhados.

### Instruções de declaração

Uma declaração de instrução introduz uma nova variável ou constante. Uma declaração variável pode, opcionalmente, atribuir um valor à variável. Uma declaração constante, a atribuição é obrigatória.

```c#
// Instrução de declaração variável.
double area;
double radius = 2;

// Instrução de declaração constante.
const double pi = 3.14159;
```

### Instruções de expressão

Uma expressão é uma sequência de um ou mais operandos e zero ou mais operadores que podem ser avaliados como um valor, objeto, método ou namespace único. As expressões podem consistir de um valor literal, uma invocação de método, um operador e seus operandos ou um nome simples. Os nomes simples podem ser o nome de uma variável, um membro de tipo, um parâmetro de método, um namespace ou um tipo.

[Leia mais aqui](<https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/statements-expressions-operators/expressions>)

### Instruções de seleção

Instruções de seleção permitem que você ramifique para diferentes seções de código, dependendo de uma ou mais condições especificadas.

Palavras-chave: `if`, `else`, `switch`, `case`

```c#
// Exemplo 1: 
// if, else
if (condicaoA)
{
    // A
}
else
{
    // #
}


// Exemplo 2
// if, else
// Para uma única instrução, as chaves são opcionais, mas recomendadas
if (condicaoA)
    if (condicaoB)
    {
        // A e B
    }
    else
    {
        // A e #
    }


// Exemplo 3
// if, else if, else
if (condicaoA)
{

}
else if (condicaoB)
{

}
else if (condicaoC)
{
    if (condicaoD)
    {

    }
    else
    {

    }
}
else
{

}


// Exemplo 4
// switch, case
switch (valor)
{
    case 1:
        Console.WriteLine("Valor 1");
        break;
    case 2:
    case 3:
        Console.WriteLine($"Valor {caseSwitch}");
        break;
    default:
        Console.WriteLine($"Valor não esperado ({caseSwitch})");
        break;
}
```

### Instruções de iteração

Instruções de iteração permitem que você percorra coleções como matrizes ou execute o mesmo conjunto de instruções repetidamente até que uma determinada condição seja atendida.

Palavras-chave: `do`, `for`, `foreach`, `in`, `while`

```c#
// Exemplo 1
// do
int n = 0;
do
{
    Console.WriteLine(n);
    n++;
} while (n < 5);


// Exemplo 2
// for
for (int i = 0; i < 5; i++)
{
    Console.WriteLine(i);
}


// Exemplo 3
// for
// Loop infinito
for ( ; ; )
{
}

```

#### foreach

Em qualquer ponto dentro do bloco de instrução foreach, você pode sair do loop usando a instrução `break` ou seguir para a próxima iteração no loop usando a instrução `continue`. Você também pode sair de um loop foreach com a instrução `goto`, `return` ou `throw`.

Se a instrução foreach for aplicada a `null`, uma `NullReferenceException` será lançada. Se a coleção de origem da instrução foreach estiver vazia, o corpo do loop foreach não será executado e será ignorado.

```c#
// Exemplo 1
// foreach, in
var fibNumbers = new List<int> { 0, 1, 1, 2, 3, 5, 8, 13 };
foreach (int element in fibNumbers)
{
    Console.WriteLine(element);
}
```

#### while

A qualquer momento dentro do bloco de instruções while, interrompa o loop usando a instrução `break`.

Você pode seguir diretamente para a avaliação da expressão while usando a instrução `continue`. Se a expressão for avaliada como `true`, a execução continuará na primeira instrução do loop. Caso contrário, a execução continuará na primeira instrução após o loop.

Você também pode sair de um loop while com a instrução `goto`, `return` ou `throw`.

```c#
// Exemplo 1
// while
int n = 0;
while (n < 5)
{
    Console.WriteLine(n);
    n++;
}
```

### Instruções de salto

Instruções de salto transferem o controle para outra seção de código.

Palavras-chave: `break`, `continue`, `default`, `goto`, `return`, `yield`

```c#
// Exemplo 1
// goto
switch (n)
{
    case 1:
        cost += 25;
        break;
    case 2:
        cost += 25;
        goto case 1;
    case 3:
        cost += 50;
        goto case 1;
    default:
        Console.WriteLine("Invalid selection.");
        break;
}

// Exemplo 1
// goto
var achei = 5;
	
if (achei < 10 ) {
    goto Found;
} else {
    goto Finish;
}

Found:
    Console.WriteLine("Achei");

Finish:
    Console.WriteLine("Fim");

// Saida:
// Achei
// Fim
```

#### yield 

Quando você usa o `yield` (palavra-chave contextual em uma instrução), você indica que o método, operador ou acessador de `get` no qual ele aparece é um iterador. Usar `yield` para definir um iterador elimina a necessidade de uma classe adicional explícita (a classe que mantém o estado de uma enumeração) ao implementar o padrão `IEnumerable` e `IEnumerator` para um tipo de coleção personalizado.

Você usa uma instrução `yield return` para retornar cada elemento individualmente.

A sequência retornada de um método iterador pode ser consumida usando uma instrução `foreach` ou uma consulta LINQ. Cada iteração do loop `foreach` chama o método iterador. Quando uma instrução `yield return` é atingida no método iterador, expression é retornado e o local atual no código é retido. A execução será reiniciada desse local na próxima vez que a função iteradora for chamada.

Você pode usar uma instrução `yield break` para terminar a iteração.


O exemplo a seguir contém uma instrução `yield return` dentro de um loop `for`. Cada iteração do corpo da instrução `foreach` no método `Main` cria uma chamada à função iteradora `Power`. Cada chamada à função iteradora prossegue para a próxima execução da instrução `yield return` que ocorre durante a próxima iteração do loop `for`.

O tipo de retorno do método iterador é `IEnumerable` que é um tipo de interface de iterador. Quando o método iterador é chamado, ele retorna um objeto enumerável que contém as potências de um número.

```c#
public class PowersOf2
{
    static void Main()
    {
        // Exibe potências de 2 até o expoente de 8
        foreach (int i in Power(2, 8))
        {
            Console.Write("{0} ", i);
        }
    }

    public static IEnumerable<int> Power(int number, int exponent)
    {
        int result = 1;

        for (int i = 0; i < exponent; i++)
        {
            result = result * number;
            yield return result;
        }
    }

    // Saida: 2 4 8 16 32 64 128 256
}
```

### Instruções para tratamento de exceções

Instruções para tratamento de exceções permitem que você se recupere normalmente de condições excepcionais que ocorrem em tempo de execução. Para mais informações, consulte os seguintes tópicos:

Palavras-chave: `throw`, `try-catch`, `try-finally`, `try-catch-finally`

```c#
// Exemplo 1
// throw
if (index < 0 || index >= numbers.Length) {
    throw new IndexOutOfRangeException();
}


// Exemplo 2
// try, catch, throw
try {
    return Value[0];
}
catch (NullReferenceException e) {
    throw;   
}


// Exemplo 3
// try, catch, finally
try
{
    file.ReadBlock(buffer, index, buffer.Length);
}
catch (System.IO.IOException e)
{
    Console.WriteLine("Error reading from {0}. Message = {1}", path, e.Message);
}
finally
{
    if (file != null)
    {
        file.Close();
    }
}
```

### Instruções checked e unchecked

As instruções `checked` e `unchecked` permitem que você especifique se operações numéricas podem causar um estouro quando o resultado for armazenado em uma variável que é muito pequena para conter o valor resultante.

```c#
// Causes erro em tempo de compilação porque 2147483647 é o limite para inteiros.
int i1 = 2147483647 + 10;

// Causa erro em tempo de execução.
int ten = 10;
int i2 = 2147483647 + ten;

// Expresão Checked.
Console.WriteLine(checked(2147483647 + ten));

// Bloco Checked.
checked
{
    int i3 = 2147483647 + ten;
    Console.WriteLine(i3);
}
```

A palavra-chave `unchecked` pode ser usada para impedir a verificação de estouro.

```c#
// Expresão Unchecked.
int1 = unchecked(ConstantMax + 10);

// Bloco Unchecked.
unchecked
{
    // Não ocorrerá erro de compilação, o resultado será "-2,147,483,639"
    int1 = 2147483647 + 10;
}
```

### Instrução await

Se marcar um método com o modificador `async`, você poderá usar o operador `await` no método. Quando o controle atinge uma expressão `await` no método assíncrono, ele retorna para o chamador e o progresso no método é suspenso até a tarefa aguardada ser concluída. Quando a tarefa for concluída, a execução poderá ser retomada no método.

### Instrução yield return

Um iterador realiza uma iteração personalizada em uma coleção, como uma lista ou uma matriz. Um iterador usa a instrução `yield return` para retornar um elemento de cada vez. Quando uma instrução `yield return` for atingida, o local atual no código será lembrado. A execução será reiniciada desse local quando o iterador for chamado na próxima vez.

### Instrução fixed

A instrução `fixed` impede que o coletor de lixo faça a realocação de uma variável móvel.

A instrução `fixed` impede que o coletor de lixo faça a realocação de uma variável móvel. A instrução `fixed` é permitida somente em um contexto não seguro. Você também pode usar a palavra-chave `fixed` para criar buffers de tamanho fixo.

A instrução `fixed` define um ponteiro para uma variável gerenciada e "fixa" essa variável durante a execução da instrução. Os ponteiros móveis gerenciados são úteis apenas em um contexto `fixed`. Sem um contexto `fixed`, a coleta de lixo poderia realocar as variáveis de forma imprevisível. O compilador do C# só permite que você atribua um ponteiro a uma variável gerenciada em uma instrução `fixed`.

```c#
class Point 
{ 
    public int x;
    public int y; 
}

unsafe private static void ModifyFixedStorage()
{
    // A variável pt é uma variável gerenciada, sujeita a coleta de lixo.
    Point pt = new Point();

    // O uso de Fixed permite que o endereço dos membros pt seja "fixo" e não será realocado.
    fixed (int* p = &pt.x)
    {
        *p = 1;
    }
}
```

### Instrução lock

A instrução `lock` permite limitar o acesso a blocos de código a apenas um thread por vez.

A instrução `lock` obtém o bloqueio de exclusão mútua para um determinado objeto, executa um bloco de instruções e, em seguida, libera o bloqueio. Embora um bloqueio seja mantido, o thread que mantém o bloqueio pode adquiri-lo novamente e liberá-lo. Qualquer outro thread é impedido de adquirir o bloqueio e aguarda até que ele seja liberado.

```c#
lock (x)
{
    // Instruções...
}
```

### Instruções rotuladas

Você pode atribuir um rótulo a uma instrução e, em seguida, usar a palavra-chave `goto` para ir diretamente para a instrução rotulada.

### Instrução vazia

A instrução vazia consiste em um único (`;`). Ela não faz nada e pode ser usada em locais em que uma instrução é necessária, mas nenhuma ação precisa ser executada.

```c#
void ProcessMessages()
{
    while (ProcessMessage())
        ; // Instrução vazia.
}

void F()
{

    if (done) goto exit;

    exit:
        ; // Instrução vazia.
}
```

## Operadores

O C# oferece vários operadores predefinidos, compatíveis com os tipos internos. Por exemplo, os operadores aritméticos executam operações aritméticas com operandos numéricos, já os operadores lógicos boolianos executam operações lógicas com operandos bool. Determinados operadores podem ser sobrecarregados. Com a sobrecarga de operador, você pode especificar o comportamento do operador para os operandos de um tipo definido pelo usuário.

Em uma expressão, a precedência e a associação dos operadores determinam a ordem na qual as operações são executadas. Você pode usar parênteses para alterar a ordem de avaliação imposta pela prioridade e pela associação dos operadores.

|Operadores          |Categoria ou nome       |
|--------------------|------------------------|
|`x.y`, `x?.y`, `x?[y]`, `f(x)`, `a[i]`, `x++`, `x--`, `new`, `typeof`, `checked`, `unchecked`, `default`, `nameof`, `delegate`, `sizeof`, `stackalloc`, `x->y` | Primária|
|`+x`, `-x`, `!x`, `~x`, `++x`, `--x`, `^x`, `(T)x`, `await`, `&x`, `*x`, `true`, `false` | Unário|
|`X.. Y`               | Intervalo |
|`x * y`, `x / y`, `x % y` | Multiplicativo |
|`x + y`, `x – y`        | Aditiva |
|`< x < y`, `x >> y`     | Turno |
|`x < y`, `x > y`, `x <= y`, `x >= y`, `is`, `as` | Teste de tipo e relacional |
|`x == y`, `x != y`      | Igualitário |
|`x & y`               | AND lógico booliano ou AND lógico bit a bit |
|`x ^ y`               | XOR lógico booliano ou XOR lógico bit a bit |
|`x \| y`               | OR lógico booliano ou OR lógico bit a bit |
|`x && y`              | AND condicional |
|`x \|\| y`              | OR condicional |
|`x ?? y`              | Operador de coalescência nula |
|`c ? t : f`           | Operador condicional |
|`x = y`, `x += y`, `x -= y`, `x *= y`, `x /= y`, `x % = y`, `x &= y`, `x \|= y`, `x ^ y`, `x <<= y`, `x >>= y`, `x ?? = y`,`=>` | Declaração de atribuição e lambda |

Use `()` para alterar a ordem de avaliação imposta pela associação de operador:

```c#
int a = 13 / 5 / 2;
int b = 13 / (5 / 2);
Console.WriteLine($"a = {a}, b = {b}");  // saída: a = 1, b = 6
```




