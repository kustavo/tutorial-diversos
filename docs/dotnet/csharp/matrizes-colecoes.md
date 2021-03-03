# Matrizes e Coleções

Para muitos aplicativos, você desejará criar e gerenciar grupos de objetos relacionados. Há duas maneiras de agrupar objetos: criando matrizes de objetos e criando coleções de objetos.

As matrizes são mais úteis para criar e trabalhar com um número fixo de objetos fortemente tipados. Já as coleções fornecem uma maneira mais flexível de trabalhar com grupos de objetos. Ao contrário das matrizes, o grupo de objetos com o qual você trabalha pode crescer e reduzir dinamicamente conforme as necessidades do aplicativo são alteradas. Para algumas coleções, você pode atribuir uma chave para qualquer objeto que coloque na coleção para que você possa recuperar rapidamente o objeto usando a chave.

## Matrizes

Você pode armazenar diversas variáveis do mesmo tipo em uma estrutura de dados de matriz. Você pode declarar uma matriz especificando o tipo de seus elementos. Se você quiser que a matriz armazene elementos de qualquer tipo, você pode especificar `object` como seu tipo. No sistema de tipos unificado do C#, todos os tipos, predefinidos e definidos pelo usuário, tipos de referência e tipos de valor, herdam direta ou indiretamente de `Object`.

- Uma matriz pode ser unidimensional, multidimensional ou irregulares (ou denteadas).
- O número de dimensões e o tamanho de cada dimensão são estabelecidos quando a instância de matriz é criada. Esses valores não podem ser alterados durante o ciclo de vida da instância.
- Os valores padrão dos elementos de matriz numérica são definidos como `0` (zero), e os elementos de referência são definidos como `null`.
- Uma matriz denteada é uma matriz de matrizes e, portanto, seus elementos são tipos de referência e são inicializados para `null`.
- As matrizes são indexadas por zero: uma matriz com elementos n é indexada de 0 para n-1.
- Os elementos de matriz podem ser de qualquer tipo, inclusive um tipo de matriz.
- Os tipos de matriz são tipos de referência derivados do tipo base abstrato `Array`. Como esse tipo implementa `IEnumerable` e `IEnumerable<T>`, você pode usar a iteração `foreach` em todas as matrizes em c#.

### Matrizes unidimensionais

Uma matriz unidimensional pode ser declarada da seguinte forma:

```c#
int[] array = new int[5];
```

Uma matriz unidimensional pode ser declarada e instanciada na mesma instrução (mesma linha) da seguinte forma:

```c#
int[] array = new int[] { 1, 3, 5, 7, 9 };
// Ou
int[] array = { 1, 3, 5, 7, 9 };
```

Iterando uma matriz unidimensional:

```c#
string[] array = new string[5];

// Usando foreach
foreach (var item in array)
    Console.WriteLine(item);

// Usando for
for (int i = 0; i < array.Length; i++)
    Console.WriteLine(array[i]);

// Usando o método Array.ForEach<T>
Array.ForEach(array, Console.WriteLine);

// Saída:
// Maria
// João
// Sara
// Lucas
```

Iterando uma matriz incompleta:

```c#
int[] array = new int[5];
array[0] = 1;
array[3] = 4;

foreach (var item in array)
    Console.WriteLine(item);

// Saída:
// 1
// 0
// 0
// 4
// 0
```

Concatenando uma matriz unidimensional usando apenas métodos da classe `Array`.

Concatenando usando o método `Array.CopyTo`:

```c#
int[] arrayA = {1, 2};
int[] arrayB = {3, 4, 5};

// Criar nova matriz com o tamanho para as duas matrizes.
int[] arrayAB = new int[arrayA.Length + arrayB.Length];

// Copia arrayA na posição 0.
arrayA.CopyTo(arrayAB, 0);

// Copia arrayB depois da ultima posição de arrayA.
arrayB.CopyTo(arrayAB, arrayA.Length);

Array.ForEach(arrayAB, Console.WriteLine);
// Saída
// 1
// 2
// 3
// 4
// 5
```

Concatenando usando os métodos `Array.Resize + Array.Copy`:

```c#
int[] arrayA = {1, 2};
int[] arrayB = {3, 4, 5};

// Aumenta o tamanho de arrayA para conter as duas matrizes.
Array.Resize(ref arrayA, arrayA.Length + arrayB.Length);

// Copia arrayB desde a posição 0 para arrayA na posição que casa arrayB 
// até o ultimo elemento de arrayB.
Array.Copy(arrayB, 0, arrayA, arrayA.Length - arrayB.Length, arrayB.Length);

Array.ForEach(arrayA, Console.WriteLine);
// Saída
// 1
// 2
// 3
// 4
// 5
```

### Matrizes multidimensionais

Uma matriz multidimensional pode ser declarada da seguinte forma:

```c#
// Declara uma matriz de duas dimensões 2 x 3
int[,] array2x3 = new int[2, 3];

// Declara uma matriz de 3 dimensões 2 x 3 x 2
int[,,] array2x3x2 = new int[2, 3, 2];
```

Uma matriz multidimensional pode ser declarada e instanciada na mesma instrução da seguinte forma:

```c#
// Declara e define uma matriz de duas dimensões 2 x 3
int[,] array2x3 = { {1, 2, 3}, {4, 5, 6} };

// Declara e define uma matriz de duas dimensões 2 x 2 x 3
int[,,] array2x2x3 = { { {1, 2, 3}, {4, 5, 6} }, { {7, 8, 9}, {10, 11, 12} } };
```

### Matrizes irregulares

Uma matriz irregular (ou denteada) pode ser declarada da seguinte forma:

```c#
// Declara uma matriz irregular 6x?
int[][] array = new int[6][];

// Define os valores da matriz na posição 0 e 1
array[0] = new int[4] { 1, 2, 3, 4 };
array[1] = new int[2] { 9, 10};

// Iterando a matriz
Array.ForEach(array, e => {
    if (e != null)
    {
        Console.WriteLine("---");
        Array.ForEach(e, Console.WriteLine);
    }
});

// Saída
// ---
// 1
// 2
// 3
// 4
// ---
// 9
// 10
```

## Referências

- <https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/concepts/collections>
- <https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/arrays/>
