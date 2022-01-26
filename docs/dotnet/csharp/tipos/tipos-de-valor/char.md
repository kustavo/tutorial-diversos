# char

A palavra-chave `char` é um alias para o tipo de estrutura .NET `System.Char` que representa um caractere Unicode UTF-16.

| palavra-chave | Faixa            | Tamanho | Tipo .NET   |
| ------------- | ---------------- | ------- | ----------- |
| char          | U+0000 to U+FFFF | 16 bit  | System.Char |

!!! note "Notas"

    O valor padrão do tipo `char` é `\0`, ou seja, `U+0000`.

O tipo `char` oferece suporte a operadores de comparação, igualdade, incremento e decremento. Além disso, para operandos `char`, os operadores lógicos aritméticos e bit a bit executam uma operação nos códigos de caracteres correspondentes e produzem o resultado do tipo `int`.

!!! note "Notas"

    O tipo `string` representa o texto como uma sequência de valores `char`.

## Literais

Você pode especificar um valor `char` com:

- um literal de caractere.
- uma sequência de escape Unicode, que é `\u` seguida pela representação hexadecimal de quatro símbolos de um código de caractere.
- uma sequência de escape hexadecimal, que é `\x` seguida pela representação hexadecimal de um código de caractere.

```c#
var chars = new[]
{
    'j',
    '\u006A',
    '\x006A',
    (char)106,
};
Console.WriteLine(string.Join(" ", chars));  // saída: j j j j
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/char>
