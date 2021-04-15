# Classes e structs

[TOC]

[[_TOC_]]

## Introdução

Classes e *structs* são uma estrutura de dados que encapsula um conjunto de dados e os comportamentos que são uma unidade lógica. Os dados e os comportamentos são os membros da classe ou *struct*, e eles incluem seus métodos, propriedades e eventos, e etc.

Uma classe é um tipo de **referência**. Quando um objeto da classe é criado, a variável à qual o objeto é atribuído armazena apenas uma referência na memória. Quando a referência de objeto é atribuída a uma nova variável, a nova variável refere-se ao objeto original. As alterações feitas por meio de uma variável são refletidas na outra variável porque ambas se referem aos mesmos dados.

Um *struct* é um tipo de **valor**. Quando um *struct* é criado, a variável à qual o *struct* está atribuído contém os dados reais do *struct*. Quando o *struct* é atribuído a uma nova variável, ele é copiado. A nova variável e a variável original, portanto, contêm duas cópias separadas dos mesmos dados. As alterações feitas em uma cópia não afetam a outra cópia.

Em geral, as classes são usadas para modelar o comportamento mais complexo ou dados que serão modificados depois que um objeto de classe for criado. Os *structs* são mais adequados para estruturas de dados pequenas que contêm principalmente dados que não serão modificados depois que o *struct* for criado.

## Membros

Todos os métodos, campos, constantes, propriedades e eventos devem ser declarados em um tipo. Eles são chamados de membros do tipo. No C#, não existem variáveis globais ou métodos como em algumas das outras linguagens. Até mesmo um ponto de entrada de um programa, o método `Main`, deve ser declarado em uma classe ou *struct*. A lista a seguir inclui todos os vários tipos de membros que podem ser declarados em uma classe ou *struct*.

- Campos
- Constantes
- Propriedades
- Métodos
- Construtores
- Eventos
- Finalizadores
- Indexadores
- Operadores
- Tipos aninhados

### Campos

Um campo é uma variável de qualquer tipo que é declarada diretamente em uma classe ou *struct*.

Em geral, você só deve usar campos para variáveis que têm acessibilidade `private` ou `protected`. Os dados que sua classe expõe ao código do cliente devem ser fornecidos através de métodos, propriedades e indexadores.

```c#
public class CalendarEntry
{
    // campo privado
    private DateTime date;

    // campo público (geralmente não recomendado)
    public string day;

    // propriedade pública (expõe o campo data de forma segura)
    public DateTime Date 
    {
        get 
        {
            return date;
        }
        set 
        {
            if (value.Year > 1900 && value.Year <= DateTime.Today.Year)
            {
                date = value;
            }
            else
            {
                throw new ArgumentOutOfRangeException();
            }
        }
    }
}
```

### Constantes

As constantes são valores imutáveis que são conhecidos no tempo de compilação e não são alterados durante a vida útil do programa. Constantes são declaradas com o modificador `const`.

Tipos definidos pelo usuário, incluindo classes, struct e matrizes, não podem ser constantes. Use o modificador `readonly` para criar uma classe, struct ou matriz que é inicializada uma vez em runtime (por exemplo, em um construtor) e, assim, não pode ser alterada.

O C# não dá suporte aos métodos, propriedades ou eventos `const`.

A expressão que é usada para inicializar uma constante poderá fazer referência a outra constante se ela não criar uma referência circular. Por exemplo:

```c#
class Calendar
{
    public const int Months = 12;
    public const int Weeks = 52;
    public const int Days = 365;

    public const double DaysPerWeek = (double) Days / (double) Weeks;
    public const double DaysPerMonth = (double) Days / (double) Months;
}
```

As constantes são acessadas como se fossem campos `static` porque o valor da constante é o mesmo para todas as instâncias do tipo.

### Propriedades

Uma propriedade é um membro que oferece um mecanismo flexível para ler, gravar ou calcular o valor de um campo particular. As propriedades podem ser usadas como se fossem membros de dados públicos, mas na verdade elas são **métodos especiais chamados acessadores**. Isso permite que os dados sejam acessados facilmente e ainda ajuda a promover a segurança e a flexibilidade dos métodos.

Um acessador de propriedade `get` é usado para retornar o valor da propriedade e um acessador de propriedade `set` é usado para atribuir um novo valor.

As propriedades podem ser de leitura/gravação (possui acessadores `get` e `set`), somente leitura (somente acessador `get`) ou somente gravação (somente acessador `set`).

A palavra-chave `value` é usada para definir o valor que está sendo atribuído pelo acessador `set`.

```c#
class TimePeriod
{
   private double _seconds;

   public double Hours
   {
       get { return _seconds / 3600; }
       set { 
          if (value < 0 || value > 24)
             throw new ArgumentOutOfRangeException(
                   $"{nameof(value)} must be between 0 and 24.");

          _seconds = value * 3600; 
       }
   }
}
```

As propriedades simples que não exigem nenhum código de acessador personalizado podem ser implementadas como definições de corpo da expressão ou como **propriedades autoimplementadas**.

```c#
class Teste
{
   public int Id { get; set; }
}
```

#### Definições de corpo de expressão

Os acessadores de propriedade geralmente consistem em instruções de linha única que simplesmente atribuem ou retornam o resultado de uma expressão. Você pode implementar essas propriedades como membros aptos para expressão. As definições de corpo da expressão consistem no símbolo `=>` seguido pela expressão à qual atribuir ou recuperar da propriedade.

As propriedades **somente leitura** podem implementar o acessador `get` como um membro apto para expressão. Nesse caso, nem a palavra-chave do acessador `get` nem a palavra-chave return é usada.

```c#
public class Person
{
   // campos privados
   private string _firstName;
   private string _lastName;
   
   // propriedade com apenas acessador get
   public string Name => $"{_firstName} {_lastName}";   
}
```

Propriedade com acessadores `get` e `set`

```c#
public class SaleItem
{
   string _name;
   decimal _cost;

   public string Name 
   {
      get => _name;
      set => _name = value;
   }

   public decimal Price
   {
      get => _cost;
      set => _cost = value; 
   }
}
```

### Métodos

Um método é um bloco de código que contém uma série de instruções. Um programa faz com que as instruções sejam executadas chamando o método e especificando os argumentos de método necessários.

Os métodos são declarados em uma classe, *estruct* ou interface especificando o nível de acesso, como `public` ou `private`, modificadores opcionais, como `abstract` ou `sealed`, o valor de retorno, o nome do método e quaisquer parâmetros do método. Juntas, essas partes são a assinatura do método.

<div class='importante' markdown='1'>

Um tipo de retorno de um método não faz parte da assinatura do método para fins de sobrecarga de método.

</div>

#### Parâmetros do método x argumentos

A definição do método especifica os nomes e tipos de quaisquer parâmetros obrigatórios. Quando o código de chamada chama o método, ele fornece valores concretos, chamados **argumentos**, para cada parâmetro. Os argumentos devem ser compatíveis com o tipo de parâmetro, mas o nome do argumento (se houver) usado no código de chamada não precisa ser o mesmo que o parâmetro nomeado definido no método.

#### Passando por referência x passando por valor

Por padrão, quando uma instância de um tipo de valor é passada para um método, sua cópia é passada em vez da própria instância. Portanto, as alterações no argumento não têm efeito sobre a instância original no método de chamada. Para passar uma instância de tipo de valor por referência, use a palavra-chave `ref`.

```c#
void Method(ref int refArgument)
{
    refArgument = refArgument + 44;
}

int number = 1;
Method(ref number);
Console.WriteLine(number); // Saida: 45
```

Quando um objeto de tipo de referência é passado para um método, uma referência ao objeto é passada. Ou seja, o método recebe não o objeto em si, mas um argumento que indica o local do objeto. Se você alterar um membro do objeto usando essa referência, a alteração será refletida no argumento no método de chamada, ainda que você passe o objeto por valor.

A palavra-chave `out` faz com que os argumentos sejam passados por referência. Ela torna o parâmetro formal um alias para o argumento, que deve ser uma variável. Em outras palavras, qualquer operação no parâmetro é feita no argumento. É como a palavra-chave `ref`, exceto pelo fato de que `ref` requer que a variável seja inicializada antes de ser passada. Também é como a palavra-chave `in`, exceto que `in` não permite que o método chamado modifique o valor do argumento. Para usar um parâmetro `out`, a definição do método e o método de chamada devem usar explicitamente a palavra-chave `out`. Por exemplo:

A palavra-chave `out` também pode ser usada com um parâmetro de tipo genérico para especificar que o parâmetro de tipo é covariante.

```c#
int initializeInMethod;
OutArgExample(out initializeInMethod);
Console.WriteLine(initializeInMethod); // valor é agora 44

void OutArgExample(out int number)
{
    number = 44;
}
```

Variáveis passadas como argumentos `out` não precisam ser inicializadas antes de serem passadas em uma chamada de método. No entanto, o método chamado é necessário para atribuir um valor antes que o método seja retornado.

As palavras-chave `in`, `ref` e `out` não são consideradas parte da assinatura do método para fins de resolução de sobrecarga.

#### Valores retornados

Os métodos podem retornar um valor para o chamador se o tipo de retorno não for `void`. O método poderá retornar o valor usando a palavra-chave `return`.

O valor pode ser retornado ao chamador por valor ou, por referência. Valores são retornados ao chamador por referência se a palavra-chave `ref` for usada na assinatura do método e antes de `return`. Por exemplo, a instrução de retorno e a assinatura de método a seguir indicam que o método retorna uma variável chamada `estDistance` por referência para o chamador. 

```c#
public ref double GetEstimatedDistance()
{
    return ref estDistance;
}
```

Neste outro exemplo, é retornado uma referência a um objeto `Person` chamado `p`:

```c#
public ref Person GetContactInformation(string fname, string lname)
{
    return ref p;
}
```

#### Métodos assíncronos

Usando o recurso `async`, você pode invocar métodos assíncronos sem usar retornos de chamada explícitos ou dividir manualmente seu código entre vários métodos ou expressões lambda.

Se marcar um método com o modificador `async`, você poderá usar o operador `await` no método. Quando o controle atinge uma expressão `await` no método assíncrono, ele retorna para o chamador e o progresso no método é suspenso até a tarefa aguardada ser concluída. Quando a tarefa for concluída, a execução poderá ser retomada no método.

Um método assíncrono pode conter um tipo de retorno `Task<TResult>`, `Task` ou nulo. O tipo de retorno nulo é usado principalmente para definir manipuladores de eventos, em que um tipo de retorno nulo é necessário. Um método assíncrono que retorna nulo não pode ser aguardado e o chamador de um método de retorno nulo não pode capturar as exceções que esse método gera.

Um método assíncrono não pode declarar nenhum parâmetro `ref` ou `out`, mas pode chamar métodos com tais parâmetros.

### Construtores



## Links

- <https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/classes-and-structs>
