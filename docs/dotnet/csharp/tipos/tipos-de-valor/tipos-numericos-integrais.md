# Tipos numéricos integrais

Os tipos numéricos integrais representam números inteiros. Todos os tipos numéricos integrais são tipos de valor. Eles também são tipos simples e podem ser inicializados com literais. Todos os tipos numéricos integrais suportam operadores aritméticos, lógicos bit a bit, de comparação e de igualdade.

| palavra-chave | Faixa                                                   | Tamanho                           | Tipo .NET      |
| ------------- | ------------------------------------------------------- | --------------------------------- | -------------- |
| sbyte         | -128 to 127                                             | Signed 8-bit integer              | System.SByte   |
| byte          | 0 to 255                                                | Unsigned 8-bit integer            | System.Byte    |
| short         | -32,768 to 32,767                                       | Signed 16-bit integer             | System.Int16   |
| ushort        | 0 to 65,535                                             | Unsigned 16-bit integer           | System.UInt16  |
| int           | -2,147,483,648 to 2,147,483,647                         | Signed 32-bit integer             | System.Int32   |
| uint          | 0 to 4,294,967,295                                      | Unsigned 32-bit integer           | System.UInt32  |
| long          | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 | Signed 64-bit integer             | System.Int64   |
| ulong         | 0 to 18,446,744,073,709,551,615                         | Unsigned 64-bit integer           | System.UInt64  |
| nint          | Depends on platform                                     | Signed 32-bit or 64-bit integer   | System.IntPtr  |
| nuint         | Depends on platform                                     | Unsigned 32-bit or 64-bit integer | System.UIntPtr |

Em todas as linhas da tabela, exceto as duas últimas, cada palavra-chave do tipo C# da coluna mais à esquerda é um alias para o tipo .NET correspondente. A palavra-chave e o nome do tipo .NET são intercambiáveis. Por exemplo, as seguintes declarações declaram variáveis do mesmo tipo:

```c#
int a = 123;
System.Int32 b = 123;
```

Os tipos `nint` e `nuint` nas duas últimas linhas da tabela são inteiros de tamanho nativo. Eles são representados internamente pelos tipos .NET indicados, mas em cada caso a palavra-chave e o tipo .NET não são intercambiáveis. O compilador fornece operações e conversões para `nint` e `nuint` como tipos inteiros que não são fornecidos para os tipos de ponteiro `System.IntPtr` e `System.UIntPtr`.

O valor padrão de cada tipo integral é zero, `0`. Cada um dos tipos integrais, exceto os tipos de tamanho nativo, tem constantes `MinValue` e `MaxValue` que fornecem os valores mínimo e máximo desse tipo.

```c#
// Valore: 64bit | 32bit
Console.WriteLine($"size of nint = {sizeof(nint)}"); // 8 | 4
Console.WriteLine($"size of nuint = {sizeof(nuint)}"); // 8 | 4
Console.WriteLine($"nint.MinValue = {nint.MinValue}"); // -9223372036854775808 | -2147483648
Console.WriteLine($"nint.MaxValue = {nint.MaxValue}"); // 9223372036854775807 | 2147483647
Console.WriteLine($"nuint.MinValue = {nuint.MinValue}"); // 0 | 0
Console.WriteLine($"nuint.MaxValue = {nuint.MaxValue}"); // 18446744073709551615 | 4294967295
```

!!! note "Notas"

    Use a estrutura `System.Numerics.BigInteger` para representar um inteiro assinado sem limites superior ou inferior.

Você também pode usar um _cast_ para converter o valor representado por um literal inteiro para o tipo diferente do tipo determinado do literal:

```c#
var signedByte = (sbyte)42;
var longVariable = (long)42;
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/integral-numeric-types>
