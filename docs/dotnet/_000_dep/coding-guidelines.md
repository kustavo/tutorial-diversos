# Convenções de codificação

[TOC]

[[_TOC_]]

## Introdução

As convenções de codificação atendem às seguintes finalidades:

- Criam uma aparência consistente para o código, para que os leitores possam se concentrar no conteúdo e não no layout.

- Permitem que os leitores entendam o código com mais rapidez, fazendo suposições com base na experiência anterior.

- Facilitam a cópia, a alteração e a manutenção do código.

- Demonstram as práticas recomendadas do C#.

As diretrizes neste artigo são usadas pela Microsoft para desenvolver exemplos e documentação.

## Layout

- Recuo e tabulação de quatro espaços.

- Uma instrução por linha.

- Uma declaração por linha.

- Para a quebra de linhas logas, recue-as uma tabulação (quatro espaços).

- Adicione pelo menos uma linha em branco entre as definições de método e de propriedade.

- Use parênteses para criar cláusulas em uma expressão aparente.

    ```c#
    if ((val1 > val2) && (val1 > val3))
    {
        // Instruções...
    }
    ```

- Nomes qualificados podem ser interrompidos após um ponto (.) se forem muito longos para uma única linha.

    ```c#
    var currentPerformanceCounterCategory = new System.Diagnostics.
        PerformanceCounterCategory();
    ```
- As seguintes quebras de linha e alinhamentos são os mais indicadas para métodos com muitos parâmetros.

    ```c#
    public void Metodo(
        int a,
        int b,
        int c,
        int d)
    {
        var foo = "foo"
        var bar = "bar"
    }

    public void Metodo(int a,
                       int b,
                       int c,
                       int d)
    {
        var foo = "foo"
        var bar = "bar"
    }

    public void Metodo(int a,
        int b,
        int c,
        int d)
    {
        var foo = "foo"
        var bar = "bar"
    }
    ```

- As seguintes quebras de linha e alinhamentos são os mais indicadas para cadeias de chamadas longas.

    ```c#
    string s = variavel.chamadaA()
        .chamadaB()
        .chamadaC()
        .chamadaD();

    string s = variavel.chamadaA()
                       .chamadaB()
                       .chamadaC()
                       .chamadaD();
    ```

- As seguinte quebra de linha e alinhamento é o mais indicadas para expressões binárias longas.

```c#
if (a != b
    && b != c
    && c == d)
{
}
```

## Nomeações

### Geral

- Não use abreveações

- Não use sublinhados em identificadores, hifens ou quaisquer outros caracteres não alfanuméricos.

- Identificadores, **exceto nomes de parâmetro**, usa a convenção **PascalCasin**. Ou seja, coloca em maiúscula o primeiro caractere de cada palavra (incluindo acrônimos com duas letras). 

    ```c#
    // Exemplos.
    PropertyDescriptor
    HtmlTag
    IOStream
    ```

- Usar identificadores descritivos, em vez de curtos.

- Usar um nome de tipo CLR genérico, em vez de um nome específico de idioma, nos casos raros em que um identificador não tem um significado semântico além de seu tipo.

    - Por exemplo, um método que converte para `Int64` deve ser nomeado `ToInt64`, não `ToLong` (porque `Int64` é o nome CLR para o alias específico `long`)

    | C#    | CLR     |
    |----  |----    |
    |sbyte  | SByte   |
    |byte   | Byte    |
    |short  | Int16   |
    |ushort | UInt16  |
    |int    | Int32   |
    |uint   | UInt32  |
    |long   | Int64   |
    |ulong  | UInt64  |
    |float  | Single  |
    |double | Double  |
    |bool   | Boolean |
    |char   | Char    |
    |string | String  |
    |object | Object  |

### Métodos

- Usar a convenção **PascalCasin**

- Nomes devem ser verbos ou frases de verbo.

### Propriedades

- Usar a convenção **PascalCasin**

- Nomes devem ser um substantivo, uma frase de substantivo ou um adjetivo.

- Nomes de propriedades de coleção devem ser uma frase plural que descreve os itens na coleção em vez de usar uma frase singular seguida de "List" ou "Collection".

- Nomes de propriedades booleanas devem ser uma frase afirmativo (CanSeek em vez de CantSeek). Opcionalmente, você também pode prefixar propriedades booleanas com "Is", "Can" ou "Has", mas apenas onde ele agrega valor.

- Considere atribuir uma propriedade com o mesmo nome que o seu tipo.

    ```c#
    public enum Color {...}

    public class Control {
        public Color Color { get {...} set {...} }
    }
    ```

### Campos

- As diretrizes de nomenclatura de campo se aplicam a campos `public` e `protected` **estáticos**. Campos internos e `private` Não são cobertos pela convenção, e campos `public` e `protected` não estáticos, não são permitidos.

- Usar a convenção **PascalCasin**

- Nomes devem ser um substantivo, uma frase de substantivo ou um adjetivo.

### Variáveis locais

- Usar a convenção **camelCasing**

### Parâmetros

- Usar a convenção **camelCasing**. Ou seja coloca em maiúscula o primeiro caractere de cada palavra, exceto a primeira palavra.

    ```c#
    // Exemplos.
    propertyDescriptor
    htmlTag
    ioStream
    ```
- Em sobrecarga de métodos, evitar nomes de parâmetro diferentes casa um parâmetro em uma sobrecarga representa a mesma entrada que um parâmetro em outra sobrecarga.

- Em sobrecarga de métodos, os parâmetros com o mesmo nome devem aparecer na mesma posição em todas as sobrecargas.

### Eventos

- Usar a convenção **PascalCasin**

- Nomes devem ser um verbo ou uma frase verbal.
    - Exemplos: Clicked, Painting, DroppedDown, etc.

- Manipuladores de eventos de nome (delegados usados como tipos de eventos) com o sufixo "EventHandler".

    ```c#
    public delegate void ClickedEventHandler(object sender, ClickedEventArgs e);
    ```
- Use dois parâmetros chamados `sender` e `e` em manipuladores de eventos

- Nomes de classes de argumento de evento devem ter o sufixo "EventArgs".

### Namespaces

- Usar a convenção **PascalCasin** e separar componentes de namespace com pontos.
    - Por exemplo, `Microsoft.Office.PowerPoint`.

- Usar prefixos namespace com um nome de empresa para impedir que os namespaces de diferentes empresas tenham o mesmo nome.

- Não use o mesmo nome para um namespace e um tipo nesse namespace.
    - Por exemplo, não use Debug como um nome de namespace e, em seguida, forneça uma classe chamada Debug no mesmo namespace. Vários compiladores exigem que esses tipos sejam totalmente qualificados.

## Métodos

- Permitir que null sejam passados para argumentos opcionais.

- Usar a sobrecarga de membros em vez de definir membros com argumentos padrão.

- Não use os modificadores `ref` ou `out` para sobrecarregar os membros.

### Métodos de extensão

[Leia mais aqui](https://docs.microsoft.com/pt-br/dotnet/standard/design-guidelines/extension-methods)


## Propriedades

- Criar propriedades somente com opção `get` se o chamador não puder alterar o valor da propriedade.

    - Se o tipo da propriedade for um tipo de referência mutável, o valor da propriedade poderá ser alterado mesmo se a propriedade for somente `get`.

- O acesso ao `set` não deve ser mais restrito que `get`.

- Preservar o valor anterior se um `ters` da propriedade lançar uma exceção.

- Evitar lançar exceções de `getters` da propriedade.

- Os `getters` de propriedade devem ser operações simples e não devem ter nenhuma condição.

- Permitir que as propriedades sejam definidas em qualquer ordem, mesmo se isso resultar em um estado temporário inválido do objeto.

### Propriedade indexada

- Usar indexadores para fornecer acesso aos dados armazenados em uma matriz interna.

- Fornecer indexadores em tipos que representam coleções de itens.

- Evitar o uso de propriedades indexadas com mais de um parâmetro.

- Use o nome `Item` para propriedades indexadas, a menos que haja um nome obviamente melhor.

- Não fornecer um indexador e métodos que são semanticamente equivalentes.

## Construtor

- Considere fornecer construtores simples. Ou seja, poucos parâmetros e todos os parâmetros são primitivos ou enums.

- Usar o mesmo nome para parâmetros de construtor e uma propriedade se os parâmetros do construtor forem usados para simplesmente definir a propriedade.

- Executar o mínimo de trabalho no construtor.

- Gerar exceções de construtores de instância, se apropriado.

- Declarar explicitamente o construtor público sem parâmetros em classes, se esse construtor for necessário ou haja um outro construtor com parâmetros.

- Evitar definir explicitamente construtores sem parâmetros em structs.

    - Isso torna a criação de matriz mais rápida, porque se o construtor sem parâmetros não estiver definido, ele não precisará ser executado em todos os slots na matriz.

- Evitar chamar membros virtuais em um objeto dentro de seu construtor.

    - Chamar um membro virtual fará com que a sobrecarga mais derivada seja chamada, mesmo que o construtor do tipo mais derivado ainda não tenha sido totalmente executado.

- Tornar os construtores estáticos privados.

- Não lançar exceções de construtores estáticos.

- Considere a inicialização de campos estáticos *inline*  em vez de usar construtores estáticos explicitamente, pois o tempo de execução é capaz de otimizar o desempenho dos tipos que não têm um construtor estático explicitamente definido.

## Comentários

- Coloque o comentário em uma linha separada, não no final de uma linha de código.

- Comece o texto do comentário com uma letra maiúscula.

- Termine o texto do comentário com um ponto final.

- Insira um espaço entre o delimitador de comentário (//) e o texto do comentário

    ```c#
    // The following declaration creates a query. It does not run
    // the query.
    ```

- Não crie blocos de asteriscos formatados em torno dos comentários.

## Strings

- Use a interpolação de cadeia de caracteres para concatenar cadeias de caracteres curtas.

    ```c#
    string displayName = $"{nameList[n].LastName}, {nameList[n].FirstName}";
    ```

- Para acrescentar cadeias de caracteres em loops, especialmente quando você estiver trabalhando com grandes quantidades de texto, use um objeto `StringBuilder`.

    ```c#
    var phrase = "lalala";
    var manyPhrases = new StringBuilder();

    for (var i = 0; i < 10000; i++)
    {
        manyPhrases.Append(phrase);
    }

    Console.WriteLine("tra" + manyPhrases);
    ```

## Variáveis Locais

- Use a tipagem implícita para variáveis locais quando o tipo da variável for óbvio do lado direito da atribuição ou quando o tipo exato não for importante.

    ```c#
    var var1 = "This is clearly a string.";
    var var2 = 27;
    var var3 = Convert.ToInt32(Console.ReadLine());
    ```

- Não use var quando o tipo não estiver aparente no lado direito da atribuição.

    ```c#
    int var4 = ExampleClass.ResultSoFar();
    ```

- Não se baseie no nome da variável para especificar o tipo dela. Ele pode não estar correto.

    ```c#
    var inputInt = Console.ReadLine();
    Console.WriteLine(inputInt);
    ```

- Evite o uso de `var` em vez de `dynamic`.

- Use a tipagem implícita para determinar o tipo da variável de loop nos `loops for`.

    ```c#
    for (var i = 0; i < 10000; i++)
    {
        // Instruções...
    }
    ```

- Não use a tipagem implícita para determinar o tipo da variável de loop em `loops foreach`.

    ```c#
    // O exemplo a seguir usa a tipagem explícita em uma instrução foreach.
    foreach (var ch in laugh)
    {
        if (ch == 'h')
            Console.Write("H");
        else
            Console.Write(ch);
    }
    Console.WriteLine();
    ```

    <div class='importante' markdown='1'>
    Tenha cuidado para não alterar acidentalmente um tipo de elemento da coleção iterável. Por exemplo, é fácil mudar de `System.Linq.IQueryable` para `System.Collections.IEnumerable` em uma instrução foreach, que altera a execução de uma consulta.
    </div>

## Matrizes

Use a sintaxe concisa ao inicializar matrizes na linha da declaração.

```c#
// Sintaxe preferencial. Var não pode ser usada aqui.
string[] vowels1 = { "a", "e", "i", "o", "u" };

// Se você usar a instanciação explícita, você pode usar var.
var vowels2 = new string[] { "a", "e", "i", "o", "u" };

// Se o tamanho do array for especificado, os elementos são inicializados um por vez.
var vowels3 = new string[5];
vowels3[0] = "a";
vowels3[1] = "e";
// ...
```

## Delegados

Use a sintaxe concisa ao criar instâncias de um tipo delegado.

```c#
// Primeiramente, na Classe defina o delegado e o método que casa com a assinatura.

// Defina o tipo.
public delegate void Del(string message);

// Defina o método que casa com a assinatura
public static void DelMethod(string str)
{
    Console.WriteLine("DelMethod argument: {0}", str);
}
```

```c#
// Posteriormente, como contéudo do método, é criado uma instância de Del.

// Preferencial: Criar a instância de Del usando a sintaxe concisa.
Del exampleDel2 = DelMethod;

// Usando a sintaxe verbosa.
Del exampleDel1 = new Del(DelMethod);
```

## Try-catch-finally

- Use uma instrução try-catch para a maioria da manipulação de exceções.

    ```c#
    static string GetValueFromArray(string[] array, int index)
    {
        try
        {
            return array[index];
        }
        catch (System.IndexOutOfRangeException ex)
        {
            Console.WriteLine("Index is out of range: {0}", index);
            throw;
        }
    }
    ```

- Simplifique o código usando a instrução `using`. Se você tiver uma instrução `try-finally` na qual o único código do bloco finally é uma chamada para o método `Dispose`, use, em vez disso, uma instrução using.

    ```c#
    // Try-finally somente chama Dispose no bloco finally.
    Font font1 = new Font("Arial", 10.0f);
    try
    {
        byte charset = font1.GdiCharSet;
    }
    finally
    {
        if (font1 != null)
        {
            ((IDisposable)font1).Dispose();
        }
    }

    // Preferencial: Podemos fazer a mesma coisa usando using.
    using (Font font2 = new Font("Arial", 10.0f))
    {
        byte charset = font2.GdiCharSet;
    }
    ```

## Operadores 

### Operador && e ||

Para evitar exceções e aumentar o desempenho ignorando comparações desnecessárias, use `&&` em vez de `&` e `||` em vez de `|` ao executar comparações.

```c#
// Usando &&, se o primeira expressão for falsa a segunda já não será avaliada.
if ((divisor != 0) && (dividend / divisor > 0))
{
    Console.WriteLine("Quotient: {0}", dividend / divisor);
}
else
{
    Console.WriteLine("Attempted division by 0 ends up here.");
}
```

### Operador new

- Use a forma concisa de instanciação de objeto com tipagem implícita.

    ```c#
    // Preferencial
    var instance1 = new ExampleClass();

    // Usando tipagem explícita
    ExampleClass instance2 = new ExampleClass();
    ```

- Use iniciadores de objeto para simplificar a criação do objeto.

    ```c#
    // Preferencial: Usando iniciadores de objetos 
    var instance3 = new ExampleClass { Name = "Desktop", ID = 37414, 
        Location = "Redmond", Age = 2.3 };

    // Usando constrtutor padrão
    var instance4 = new ExampleClass();
    instance4.Name = "Desktop";
    instance4.ID = 37414;
    instance4.Location = "Redmond";
    instance4.Age = 2.3;
    ```

### Sobrecargas de operador

- Evitar definir sobrecargas de operador, exceto em tipos que devem se sentir como tipos primitivos (internos).

- Definir sobrecargas de operador em structs que representam números (como System.Decimal).

- Defina operadores de sobrecarga de maneira simétrica

    - Por exemplo, se você sobrecarregar o operator `==`, também deverá sobrecarregar o operator `!=`. Da mesma forma, se você sobrecarregar o operator `<`, também deverá sobrecarregar o operator `>` e assim por diante.

- Considere fornecer métodos com nomes amigáveis que correspondam a cada operador sobrecarregado.

    - Muitas linguagens não dão suporte à sobrecarga de operador. Por esse motivo, é recomendável que os tipos que sobrecarregam operadores incluam um método secundário com um nome específico de domínio apropriado que forneça funcionalidade equivalente.

    Símbolo de operador|Nome de metadados   |Nome Amigável
    -----      |-----                       |-----
    N/A        |op\_Implicit                |To<TypeName>/From<TypeName>
    N/A        |op\_Explicit                |To<TypeName>/From<TypeName>
    \+ (binary)|op\_Addition                |Add
    \- (binary)|op\_Subtraction             |Subtract
    \* (binary)|op\_Multiply                |Multiply
    \/         |op\_Division                |Divide
    \%         |op\_Modulus                 |Mod or Remainder
    \^         |op\_ExclusiveOr             |Xor
    \& (binary)|op\_BitwiseAnd              |BitwiseAnd
    \|         |op\_BitwiseOr               |BitwiseOr
    \&&        |op\_LogicalAnd              |And
    \||        |op\_LogicalOr               |Or
    \=         |op\_Assign                  |Assign
    \<<        |op\_LeftShift               |LeftShift
    \>>        |op\_RightShift              |RightShift
    N/A        |op\_SignedRightShift        |SignedRightShift
    N/A        |op\_UnsignedRightShift      |UnsignedRightShift
    \==        |op\_Equality                |Equals
    \!=        |op\_Inequality              |Equals
    \>         |op\_GreaterThan             |CompareTo
    \<         |op\_LessThan                |CompareTo
    \>=        |op\_GreaterThanOrEqual      |CompareTo
    \<=        |op\_LessThanOrEqual         |CompareTo
    \*=        |op\_MultiplicationAssignment|Multiply
    \-=        |op\_SubtractionAssignment   |Subtract
    \^=        |op\_ExclusiveOrAssignment   |Xor
    \<<=       |op\_LeftShiftAssignment     |LeftShift
    \%=        |op\_ModulusAssignment       |Mod
    \+=        |op\_AdditionAssignment      |Add
    \&=        |op\_BitwiseAndAssignment    |BitwiseAnd
    \|=        |op\_BitwiseOrAssignment     |BitwiseOr
    \,         |op\_Comma                   |Comma
    \/=        |op\_DivisionAssignment      |Divide
    \--        |op\_Decrement               |Decrement
    \++        |op\_Increment               |Increment
    \- (unary) |op\_UnaryNegation           |Negate
    \+ (unary) |op\_UnaryPlus               |Plus
    \~         |op\_OnesComplement          |OnesComplement

- O sobrecarregamento de operator `==` é bastante complicado. A semântica do operador precisa ser compatível com vários outros membros, como `Object.Equals`.

#### Operadores de conversão

- Não definir operadores de conversão fora do domínio de um tipo.

    - Por exemplo, `Int32`, `Double` e `Decimal` são todos os tipos numéricos, enquanto DateTime não é. Portanto, não deve haver nenhum operador de conversão para converter um `Double(long)` em um `DateTime`. Um construtor é preferencial nesse caso.

- Não fornecer um operador de conversão implícita se a conversão tiver potencialmente perda.

    - Por exemplo, não deve haver uma conversão implícita de `Double` para `Int32` porque `Double` tem um intervalo maior do que `Int32`. Um operador de conversão explícita pode ser fornecido mesmo que a conversão tenha potencialmente perda.

- Não lançar exceções de conversões implícitas.

    - É muito difícil para os usuários finais entenderem o que está acontecendo, pois eles podem não estar cientes de que uma conversão está ocorrendo.

- Lançar `System.InvalidCastException` se uma chamada para um operador de conversão resultar em uma conversão com perdas e o contrato do operador não permitir conversões com perdas.

## Parâmetros

- Usar o tipo de parâmetro menos derivado que fornece a funcionalidade exigida pelo membro.

    - Por exemplo, suponha que você queira criar um método que enumere uma coleção e imprima cada item no console. Esse método deve levar `IEnumerable` como o parâmetro, não `ArrayList` ou `IList`, por exemplo.

- Não use parâmetros reservados.

    - Se mais entradas para um membro forem necessárias em alguma versão futura, uma nova sobrecarga poderá ser adicionada.

- Não tenha métodos publicamente expostos que usam ponteiros, matrizes de ponteiros ou matrizes multidimensionais como parâmetros.

    - Ponteiros e matrizes multidimensionais são relativamente difíceis de usar corretamente. Em quase todos os casos, as APIs podem ser reprojetadas para evitar a criação desses tipos como parâmetros.

- Coloque todos os parâmetros de `out` após todos os parâmetros por valor e `ref` (excluindo matrizes de parâmetros), mesmo que resultem em uma inconsistência na ordenação de parâmetros entre sobrecargas.

    - Os parâmetros de `out` podem ser vistos como valores de retorno extras, e agrupá-los juntos torna a assinatura do método mais fácil de entender.

- Prefira `enums` ao invés de boleanos, a menos que tenha certeza de que nunca haverá necessidade de mais de dois valores.

- Considere usar boleanos para parâmetros de construtor que são verdadeiramente valores de dois estados e são simplesmente usados para inicializar propriedades booleanas.

- Validar argumentos passados para membros públicos, protegidos ou explicitamente implementados. Lance `System.ArgumentException`, ou uma de suas subclasses, se a validação falhar.

    - Observe que a validação real não precisa necessariamente acontecer no próprio membro público ou protegido. Isso pode acontecer em um nível inferior em alguma rotina privada ou interna. O ponto principal é que toda a área de superfície exposta aos usuários finais verifica os argumentos.

- Lançar `ArgumentNullException` se um argumento nulo for passado e o membro não oferecer suporte a argumentos nulos.

- Esteja ciente de que argumentos mutáveis podem ter sido alterados depois de serem validados.

    - Se o membro for sensível à segurança, você será incentivado a fazer uma cópia e, em seguida, validar e processar o argumento.

### Membros com número variável de parâmetros

- Considere adicionar a palavra-chave `params` aos parâmetros de matriz se você espera que os usuários finais passem matrizes com um pequeno número de elementos. Se for esperado que muitos elementos sejam passados em cenários comuns, os usuários provavelmente não passarão esses elementos embutidos de qualquer forma e, portanto, a palavra-chave `params` não será necessária.

    ```c#
    public class String {
        public static string Format(string format, params object[] parameters);
    }
    ```

- Evitar o uso de matrizes `params` se o chamador quase sempre tiver a entrada já em uma matriz.

    - Por exemplo, os membros com parâmetros de matriz de bytes quase nunca seriam chamados passando bytes individuais. Por esse motivo, os parâmetros de matriz de bytes no .NET Framework não usam a palavra-chave `params`.

- Não use matrizes `params` se a matriz for modificada pelo membro. Modificaçôes na matriz pode ser perdida.

    - Compiladores transformam os argumentos para o membro em uma matriz temporária no local de chamada, a matriz pode ser um objeto temporário e, portanto, qualquer modificação na matriz será perdida.

- Considere usar a palavra-chave `params` em uma sobrecarga simples, mesmo se uma sobrecarga mais complexa não puder usá-la.

    - Pergunte-se se os usuários teriam valor com a matriz `params` em uma sobrecarga, mesmo que não estivesse em todas as sobrecargas.

- Tente ordenar os parâmetros para tornar possível usar a palavra-chave `params`.

- Esteja ciente de que `NULL` pode ser passado como um argumento de matriz `params`.

- Não usar os métodos de `varargs`, também conhecidos como reticências.

## Eventos

- Usar o termo "Raise" para eventos em vez de "Fire" ou "Trigger".

- Usar `System.EventHandler<TEventArgs>` em vez de criar manualmente novos delegados para serem usados como manipuladores de eventos.

- Considere o uso de uma subclasse de `EventArgs` como o argumento de evento, a menos que você tenha certeza absoluta de que o evento nunca precisará transportar dados para o método de manipulação de eventos; nesse caso, você pode usar o tipo de `EventArgs` diretamente.

- Não passe nulo como o *sender* ao gerar um evento não estático.

- Passar nulo como o *sender* ao gerar um evento estático.

- Não passar nulo como o parâmetro de dados de evento ao gerar um evento.

    - Você deve passar `EventArgs.Empty` se não quiser passar dados para o método de manipulação de eventos.

- Considere a geração de eventos que o usuário final pode cancelar.

    - Use `System.ComponentModel.CancelEventArgs` ou sua subclasse como o argumento de evento para permitir que o usuário final cancele eventos.

- Se você estiver definindo um manipulador de eventos que não necessita ser removido posteriormente, use uma expressão lambda.

    ```c#
    // Preferencial: Usando expressão lambda para definir um manipulador de eventos.
    public Form2()
    {
        this.Click += (s, e) =>
            {
                MessageBox.Show(((MouseEventArgs)e).Location.ToString());
            };
    }

    // Forma tradicional.
    public Form1()
    {
        this.Click += new EventHandler(Form1_Click);
    }

    void Form1_Click(object sender, EventArgs e)
    {
        MessageBox.Show(((MouseEventArgs)e).Location.ToString());
    }
    ```

### Eventos personalizados

- Usar um tipo de retorno de void para manipuladores de eventos.

    - Um manipulador de eventos pode invocar vários métodos de manipulação de eventos, possivelmente em vários objetos. Se os métodos de manipulação de eventos tivessem permissão para retornar um valor, haveria vários valores de retorno para cada invocação de evento.

- Usar `object` como o tipo do primeiro parâmetro do manipulador de eventos e chamá-lo `sender`.

- Usar `System.EventArgs` ou sua subclasse como o tipo do segundo parâmetro do manipulador de eventos e chamá-lo `e`.

    - Não tem mais de dois parâmetros em manipuladores de eventos.

## LINQ

- Use nomes significativos para variáveis de consulta. 

    ```c#
    // seattleCustomers para os clientes que estão localizados em Seattle.
    var seattleCustomers = from customer in customers
                           where customer.City == "Seattle"
                           select customer.Name;
    ```

- Use a tipagem implícita na declaração de variáveis de consulta e de intervalo.

    ```c#
    var seattleCustomers = from customer in customers
                           where customer.City == "Seattle"
                           select customer.Name;
    ```

- Alinhe cláusulas de consulta na cláusula `from`. Use cláusulas `where` **antes de outras cláusulas de consulta** para garantir que cláusulas de consulta posteriores operem no conjunto de dados filtrado e reduzido.

    ```c#
    var seattleCustomers2 = from customer in customers
                            where customer.City == "Seattle"
                            orderby customer.Name
                            select customer;
    ```

- Use aliases para se certificar de que os nomes de propriedades de tipos anônimos sejam colocados corretamente em maiúsculas, usando o padrão Pascal-Case.

    ```c#
    var localDistributors =
        from customer in customers
        join distributor in distributors on customer.City equals distributor.City
        select new { Customer = customer, Distributor = distributor };
    ```

- Renomeie propriedades quando os nomes de propriedades no resultado forem ambíguos. Por exemplo, se a sua consulta retornar um nome de cliente e uma ID de distribuidor, em vez de deixá-los como `Name` e `ID` no resultado, renomeie-os para esclarecer que `Name` é o nome de um cliente, e `ID` é a identificação de um distribuidor.

    ```c#
    var localDistributors2 =
        from customer in customers
        join distributor in distributors on customer.City equals distributor.City
        select new { CustomerName = customer.Name, DistributorID = distributor.ID };
    ```

- Use várias cláusulas `from` **em vez de uma cláusula** `join` para acessar coleções internas. Por exemplo, cada coleção de objetos `Student` pode conter um conjunto de pontuações no teste. Quando a próxima consulta for executada, ela retorna cada pontuação que seja acima de 90, juntamente com o sobrenome do estudante que recebeu a pontuação.

    ```c#
    var scoreQuery = from student in students
                     from score in student.Scores
                     where score > 90
                     select new { Last = student.LastName, score };
    ```

## Links

- https://docs.microsoft.com/pt-br/dotnet/standard/design-guidelines/
