# Tipos de valor anuláveis

Um tipo de valor anulável `T?` representa todos os valores de seu tipo de valor subjacente `T` e um valor `null` adicional. Por exemplo, você pode atribuir qualquer um dos três valores a seguir a uma variável `bool?`: `true`, `false` ou `null`. Um tipo de valor subjacente `T` não pode ser um tipo de valor anulável em si.

Qualquer tipo de valor anulável é uma instância da estrutura genérica `System.Nullable <T>`. Você pode se referir a um tipo de valor anulável com um tipo subjacente `T` em qualquer uma das seguintes formas intercambiáveis: `Nullable<T>` ou `T?`.

Normalmente, você usa um tipo de valor anulável quando precisa representar o valor indefinido de um tipo de valor subjacente. Por exemplo, uma variável `bool` só pode ser verdadeira ou falsa. No entanto, em alguns aplicativos, um valor de variável pode ser indefinido ou ausente. Por exemplo, um campo de banco de dados pode conter `true` ou `false` ou pode não conter nenhum valor, ou seja, `NULL`. Você pode usar o tipo `bool?` nesse cenário.

## Declaração e atribuição

Como um tipo de valor é implicitamente conversível no tipo de valor anulável correspondente, você pode atribuir um valor a uma variável de um tipo de valor anulável como faria para seu tipo de valor subjacente. Você também pode atribuir o valor `null`. Por exemplo:

```c#
double? pi = 3.14;
char? letter = 'a';

int m2 = 10;
int? m = m2;

bool? flag = null;

// Um array de tipos de valores anuláveis
int?[] arr = new int?[10];
```

O valor padrão de um tipo de valor anulável representa `null`, ou seja, é uma instância cuja propriedade `Nullable<T>.HasValue` retorna `false`.

## Examinação de uma instância de um tipo de valor anulável

Você pode usar o operador `is` com um padrão de tipo para examinar uma instância de um tipo de valor anulável para `null` e recuperar um valor de um tipo subjacente:

```c#
int? a = 42;
if (a is int valorA)
    Console.WriteLine($"a is {valorA}");
else
    Console.WriteLine("a does not have a value");
// Saída:
// a is 42
```

Você sempre pode usar as seguintes propriedades somente leitura para examinar e obter um valor de uma variável de tipo de valor anulável:

- `Nullable<T>.HasValue`: indica se uma instância de um tipo de valor anulável tem um valor de seu tipo subjacente.
- `Nullable<T>.Value`: obtém o valor de um tipo subjacente se `HasValue` for `true`. Se `HasValue` for `false`, a propriedade `Value` lança uma `InvalidOperationException`.

O exemplo a seguir usa a propriedade `HasValue` para testar se a variável contém um valor antes de exibi-lo:

```c#
int? b = 10;
if (b.HasValue)
    Console.WriteLine($"b is {b.Value}");
else
    Console.WriteLine("b does not have a value");
// Saída:
// b is 10
```

Você também pode comparar uma variável de um tipo de valor anulável com `null` em vez de usar a propriedade `HasValue`, como mostra o exemplo a seguir:

```c#
int? c = 7;
if (c != null)
    Console.WriteLine($"c is {c.Value}");
else
    Console.WriteLine("c does not have a value");
// Saída:
// c is 7
```

## Conversão de um tipo de valor anulável para um tipo subjacente

Se você deseja atribuir um valor de um tipo de valor anulável a uma variável de tipo de valor não anulável, pode ser necessário especificar o valor a ser atribuído no lugar de `null`. Use o operador de coalescência nula `??` para fazer isso (você também pode usar o método `Nullable<T>.GetValueOrDefault(T)` para o mesmo propósito):

```c#
int? a = 28;
int b = a ?? -1;
Console.WriteLine($"b is {b}");  // Saída: b is 28

int? c = null;
int d = c ?? -1;
Console.WriteLine($"d is {d}");  // Saída: d is -1
```

Se você quiser usar o valor padrão do tipo de valor subjacente no lugar de `null`, use o método `Nullable<T>.GetValueOrDefault()`.

Você também pode converter explicitamente um tipo de valor anulável em um tipo não anulável, como mostra o exemplo a seguir:

```c#
int? n = null;

//int m1 = n;    // Não compila
int n2 = (int)n; // Compila mas lança exceção se n é null
```

Em tempo de execução, se o valor de um tipo de valor anulável for `null`, a conversão explícita lançará uma `InvalidOperationException`.

Um tipo de valor não anulável `T` é implicitamente conversível no tipo de valor anulável correspondente `T?`.

## Operadores elevados

Os operadores unários e binários predefinidos ou quaisquer operadores sobrecarregados que são suportados por um tipo de valor `T` também são suportados pelo tipo de valor anulável correspondente `T?`. Esses operadores, também conhecidos como operadores elevados, produzem `null` se um ou ambos os operandos forem `null`; caso contrário, o operador usa os valores contidos de seus operandos para calcular o resultado. Por exemplo:

```c#
int? a = 10;
int? b = null;
int? c = 10;

a++;        // a is 11
a = a * c;  // a is 110
a = a + b;  // a is null
```

!!! note "Notas"
    Para o tipo `bool?`, os operadores predefinidos `&` e `|` não seguem as regras descritas nesta seção: o resultado da avaliação de um operador pode ser não nulo, mesmo se um dos operandos for `null`.

Para os operadores de comparação `<`,`>`, `<=` e `>=`, se um ou ambos os operandos forem `null`, o resultado será `false`; caso contrário, os valores contidos dos operandos são comparados. Não assuma que, como uma comparação específica (por exemplo, `<=`) retorna `false`, a comparação oposta (`>`) retorna `true`. O exemplo a seguir mostra que 10 é:

- nem maior ou igual a `null`
- nem menor que `null`

```c#
int? a = 10;
Console.WriteLine($"{a} >= null is {a >= null}");
Console.WriteLine($"{a} < null is {a < null}");
Console.WriteLine($"{a} == null is {a == null}");
// Saída:
// 10 >= null is False
// 10 < null is False
// 10 == null is False

int? b = null;
int? c = null;
Console.WriteLine($"null >= null is {b >= c}");
Console.WriteLine($"null == null is {b == c}");
// Saída:
// null >= null is False
// null == null is True
```

Para o operador de igualdade `==`, se ambos os operandos forem `null`, o resultado será `true`; se apenas um dos operandos for `null`, o resultado será `false`; caso contrário, os valores contidos dos operandos são comparados.

Para o operador de desigualdade `!=`, Se ambos os operandos forem `null`, o resultado será `false`; se apenas um dos operandos for `null`, o resultado será `true`; caso contrário, os valores contidos dos operandos são comparados.

Se houver uma conversão definida pelo usuário entre dois tipos de valor, a mesma conversão também pode ser usada entre os tipos de valor anuláveis correspondentes.

## Embalando e desembalando

Uma instância de um tipo de valor anulável `T?` é embalado da seguinte forma:

- Se `HasValue` retornar `false`, a referência nula será produzida.
- Se `HasValue` retornar `true`, o valor correspondente do tipo de valor subjacente `T` é embalado, não a instância de `Nullable<T>`.

Você pode desembalar um valor embalado de um tipo de valor `T` para o tipo de valor anulável correspondente `T?`, como mostra o exemplo a seguir:

```c#
int a = 41;
object aBoxed = a;
int? aNullable = (int?)aBoxed;
Console.WriteLine($"Value of aNullable: {aNullable}");

object aNullableBoxed = aNullable;
if (aNullableBoxed is int valueOfA)
{
    Console.WriteLine($"aNullableBoxed is boxed int: {valueOfA}");
}
// Saída:
// Value of aNullable: 41
// aNullableBoxed is boxed int: 41
```

## Como identificar um tipo de valor anulável

O exemplo a seguir mostra como determinar se uma instância `System.Type` representa um tipo de valor anulável construído, ou seja, o tipo `System.Nullable<T>` com um parâmetro de tipo especificado `T`:

```c#
Console.WriteLine($"int? is {(IsNullable(typeof(int?)) ? "nullable" : "non nullable")} value type");
Console.WriteLine($"int is {(IsNullable(typeof(int)) ? "nullable" : "non-nullable")} value type");

bool IsNullable(Type type) => Nullable.GetUnderlyingType(type) != null;

// Saída:
// int? is nullable value type
// int is non-nullable value type
```

Como mostra o exemplo, você usa o operador `typeof` para criar uma instância `System.Type`.

Se você deseja determinar se uma instância é de um tipo de valor anulável, não use o método `Object.GetType` para fazer com que uma instância de `Type` seja testada com o código anterior. Quando você chama o método `Object.GetType` em uma instância de um tipo de valor anulável, a instância é embalada em `Object`. Como o embalamento de uma instância não nula de um tipo de valor anulável é equivalente ao embalamento de um valor do tipo subjacente, `GetType` retorna uma instância de `Type` que representa o tipo subjacente de um tipo de valor anulável:

```c#
int? a = 17;
Type typeOfA = a.GetType();
Console.WriteLine(typeOfA.FullName);
// Saída:
// System.Int32
```

Além disso, não use o operador `is` para determinar se uma instância é de um tipo de valor anulável. Como mostra o exemplo a seguir, você **não** pode distinguir os tipos de uma instância de tipo de valor anulável e sua instância de tipo subjacente com o operador `is`:

```c#
int? a = 14;
if (a is int)
    Console.WriteLine("int? instance is compatible with int");

int b = 17;
if (b is int?)
    Console.WriteLine("int instance is compatible with int?");

// Saída:
// int? instance is compatible with int
// int instance is compatible with int?
```

Você pode usar o código apresentado no exemplo a seguir para determinar se uma instância é de um tipo de valor anulável:

```c#
int? a = 14;
Console.WriteLine(IsOfNullableType(a));  // saída: True

int b = 17;
Console.WriteLine(IsOfNullableType(b));  // saída: False

bool IsOfNullableType<T>(T o)
{
    var type = typeof(T);
    return Nullable.GetUnderlyingType(type) != null;
}
```

!!! warning "Atenção"
    Os métodos descritos nesta seção não são aplicáveis no caso de tipos de referência anuláveis.

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/nullable-value-types>
