# bool

A palavra-chave `bool` é um alias para o tipo de estrutura .NET `System.Boolean` que representa um valor booleano, que pode ser `true` ou `false`.

!!! note "Notas"

    O valor padrão do tipo bool é falso.

## Lógica booleana de três valores

Usamos o `bool?` _nullable_ para oferecer suporte à lógica de três valores, por exemplo, ao trabalhar com bancos de dados que oferecem suporte a um tipo booleano de três valores. Para os operandos `bool?`, os operadores predefinido `&` e `|` suportam a lógica de três valores.

- O operador `&` produz verdadeiro apenas se ambos os operandos forem avaliados como verdadeiros. Se `x` ou `y` for avaliado como falso, `x & y` produzirá falso (mesmo se outro operando for avaliado como nulo). Caso contrário, o resultado de `x & y` é nulo.

- O operador `|` produz falso apenas se ambos os operandos forem avaliados como falsos. Se `x` ou `y` for avaliado como verdadeiro, `x | y` produz verdadeiro (mesmo se outro operando for avaliado como nulo). Caso contrário, o resultado de `x | y` é nulo.

Para obter mais informações sobre tipos de valores anuláveis, consulte Tipos de valores anuláveis.

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/bool>
