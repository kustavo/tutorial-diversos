# Tipos numéricos de ponto flutuante

Os tipos numéricos de ponto flutuante representam números reais. Todos os tipos numéricos de ponto flutuante são tipos de valor. Eles também são tipos simples e podem ser inicializados com literais. Todos os tipos numéricos de ponto flutuante oferecem suporte a operadores aritméticos, de comparação e de igualdade.

| palavra-chave | Faixa                                     | Precisão      | Tamanho  | Tipo .NET      |
| ------------- | ----------------------------------------- | ------------- | -------- | -------------- |
| float         | ±1.5 x 10<sup>−45</sup> to ±3.4 x 10<sup>38</sup>    | ~6-9 digits   | 4 bytes  | System.Single  |
| double        | ±5.0 × 10<sup>−324</sup> to ±1.7 × 10<sup>308</sup>  | ~15-17 digits | 8 bytes  | System.Double  |
| decimal       | ±1.0 x 10<sup>-28</sup> to ±7.9228 x 10<sup>28</sup> | 28-29 digits  | 16 bytes | System.Decimal |

Na tabela anterior, cada palavra-chave do tipo C# da coluna mais à esquerda é um alias para o tipo .NET correspondente. Eles são intercambiáveis. Por exemplo, as seguintes declarações declaram variáveis do mesmo tipo:

```c#
double a = 12.3;
System.Double b = 12.3;
```

O valor padrão de cada tipo de ponto flutuante é zero, `0`. Cada um dos tipos de ponto flutuante tem as constantes `MinValue` e `MaxValue` que fornecem os valores finitos mínimo e máximo desse tipo. Os tipos `float` e `double` também fornecem constantes que representam valores não numéricos e infinitos. Por exemplo, o tipo `double` fornece as seguintes constantes: `Double.NaN`, `Double.NegativeInfinity` e `Double.PositiveInfinity`.

O tipo `decimal` é apropriado quando o grau de precisão exigido é determinado pelo número de dígitos à direita da vírgula decimal. Esses números são comumente usados em aplicações financeiras, para valores em moeda (por exemplo, _$1.00_), taxas de juros (por exemplo, _2.625%_) e assim por diante. Mesmo os números precisos para apenas um dígito decimal são tratados com mais precisão pelo tipo `decimal`. Por exemplo, _0.1_ pode ser representado exatamente por uma instância `decimal`, enquanto não há instância `double` ou `float` que representa exatamente _0.1_. Por causa dessa diferença nos tipos numéricos, erros inesperados de arredondamento podem ocorrer em cálculos aritméticos quando você usa `double` ou `float` para dados decimais. Você pode usar `double` em vez de `decimal` quando otimizar o desempenho é mais importante do que garantir a precisão. No entanto, qualquer diferença no desempenho passaria despercebida por todos, exceto os aplicativos de cálculo mais intensivo. Outro motivo possível para evitar o `decimal` é minimizar os requisitos de armazenamento. Por exemplo, `ML.NET` usa `float` porque a diferença entre 4 bytes e 16 bytes criam conjuntos de dados muito grandes.

Podemos misturar tipos integrais e os tipos float e double em uma expressão. Nesse caso, os tipos integrais são convertidos implicitamente em um dos tipos de ponto flutuante e, se necessário, o tipo float é convertido implicitamente em double.

Não podemos misturar o tipo `decimal` com os tipos `float` e `double` em uma expressão. Nesse caso, se você deseja realizar operações aritméticas, de comparação ou de igualdade, deve converter explicitamente os operandos de ou para o tipo decimal, como mostra o exemplo a seguir:

```c#
double a = 1.0;
decimal b = 2.1m;
Console.WriteLine(a + (double)b);
Console.WriteLine((decimal)a + b);
```

## Literais

O tipo de um literal real é determinado por seu sufixo da seguinte forma:

- O literal sem sufixo ou com o sufixo `d` ou `D` é do tipo duplo
- O literal com o sufixo `f` ou `F` é do tipo float
- O literal com sufixo `m` ou `M` é do tipo decimal

Podemos usar o separador de dígitos `_` com todos os tipos de literais numéricos.

```c#
double d = 3D;
d = 4d;
d = 3.934_001;

float f = 3_000.5F;
f = 5.4f;

decimal myMoney = 3_000.5m;
myMoney = 400.75M;
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/floating-point-numeric-types>
