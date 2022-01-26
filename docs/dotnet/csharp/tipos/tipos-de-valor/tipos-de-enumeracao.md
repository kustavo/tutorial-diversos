# Tipos de enumeração

Um tipo de enumeração é um tipo de valor definido por um conjunto de constantes nomeadas do tipo numérico integral subjacente. Para definir um tipo de enumeração, use a palavra-chave `enum` e especifique os nomes dos membros de enumeração:

```c#
enum Season
{
    Spring,
    Summer,
    Autumn,
    Winter
}
```

Por padrão, os valores constantes associados de membros enum são do tipo `int`; eles começam com zero e aumentam em um seguindo a ordem do texto de definição. Você pode especificar explicitamente qualquer outro tipo numérico integral como um tipo subjacente de um tipo de enumeração, como mostra o exemplo a seguir:

```c#
enum ErrorCode : ushort
{
    None = 0,
    Unknown = 1,
    ConnectionLost = 100,
    OutlierReading = 200
}
```

Para qualquer tipo de enumeração, existem conversões explícitas entre o tipo de enumeração e seu tipo integral subjacente. Se você converter um valor enum em seu tipo subjacente, o resultado será o valor integral associado de um membro enum.

```c#
public enum Season
{
    Spring,
    Summer,
    Autumn,
    Winter
}

public class EnumConversionExample
{
    public static void Main()
    {
        Season a = Season.Autumn;
        Console.WriteLine($"Valor integral de {a} é {(int)a}");  // saída: Valor integral de Autumn é 2

        var b = (Season)1;
        Console.WriteLine(b);  // saída: Summer

        var c = (Season)4;
        Console.WriteLine(c);  // saída: 4
    }
}
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/enum>
