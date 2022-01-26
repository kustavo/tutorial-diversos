# using

A palavra-chave `using` pode ser usada como instrução (em inglês, `statement`) ou diretiva (em inglês, `directive`).

Como uma instrução, ela é usada para definir um escopo no qual no final um objeto será descartado. Como diretiva, cria um alias para os tipos *namespace* ou `import` definidos em outros *namespace*.

## Instrução `statement`

Fornece uma sintaxe conveniente que garante o uso correto de objetos `IDisposable`. A instrução `using` garante o uso correto dos objetos `IAsyncDisposable`.

```c#
string manyLines = @"Linha 1
Linha 2
Linha 3
Linha 4";

using (var reader = new StringReader(manyLines))
{
    string item;
    do
    {
        item = reader.ReadLine();
        Console.WriteLine(item);
    } while (item != null);
}
```

A partir do C# 8.0, não é necessário o uso de chaves:

```c#
string manyLines = @"Linha 1
Linha 2
Linha 3
Linha 4";

using var reader = new StringReader(manyLines);
string item;
do
{
    item = reader.ReadLine();
    Console.WriteLine(item);
} while (item != null);
```

`File` e `Font` são exemplos de tipos gerenciados que acessam recursos não gerenciados (neste caso, identificadores de arquivo e contextos de dispositivo). Existem muitos outros tipos de recursos não gerenciados e tipos de biblioteca de classes que os encapsulam. Todos esses tipos devem implementar a interface `IDisposable` ou a interface `IAsyncDisposable`.

Quando o tempo de vida de um objeto `IDisposable` é limitado a um único método, você deve declará-lo e instanciá-lo na instrução `using`. A instrução `using` chama o método `Dispose` do objeto da maneira correta e, também faz com que o próprio objeto saia do escopo assim que `Dispose` for chamado. Dentro do bloco `using`, o objeto é somente leitura e não pode ser modificado ou reatribuído. Se o objeto implementa `IAsyncDisposable` em vez de `IDisposable`, a instrução `using` chama `DisposeAsync` e aguarda (`await`) o `ValueTask` retornado.

A instrução using garante que `Dispose` (ou `DisposeAsync`) seja chamado mesmo se ocorrer uma exceção dentro do bloco `using`. Você pode obter o mesmo resultado colocando o objeto dentro de um bloco `try` e, em seguida, chamando `Dispose` (ou `DisposeAsync`) em um bloco `finally`; na verdade, é assim que a instrução `using` é traduzida pelo compilador.

O exemplo de código anterior se expande para o seguinte código em tempo de compilação (observe as chaves extras para criar o escopo limitado do objeto):

```c#
string manyLines = @"Linha 1
Linha 2
Linha 3
Linha 4";

{
    var reader = new StringReader(manyLines);
    try
    {
        string item;
        do
        {
            item = reader.ReadLine();
            Console.WriteLine(item);
        } while (item != null);
    }
    finally
    {
        reader?.Dispose();
    }
}
```

Usando a sintaxe sem chaves, o código gerado é semelhante. O bloco `try` é aberto onde a variável é declarada. O bloco `finally` é adicionado no fechamento do bloco envolvente, normalmente no final de um método. Várias instâncias de um tipo podem ser declaradas em uma única instrução `using`, conforme mostrado no exemplo a seguir. Observe que você não pode usar variáveis tipadas implicitamente (`var`) ao declarar várias variáveis ​​em uma única instrução:

```c#
string numbers = @"One
Two
Three
Four.";
string letters = @"A
B
C
D.";

using (StringReader left = new StringReader(numbers), right = new StringReader(letters))
{
    string item;
    do
    {
        item = left.ReadLine();
        Console.Write($"{item}   ");
        item = right.ReadLine();
        Console.WriteLine(item);
    } while (item != null);
}
```

Você também pode combinar várias declarações do mesmo tipo usando a nova sintaxe introduzida sem chaves, conforme mostrado no exemplo a seguir:

```c#
string numbers = @"One
Two
Three
Four.";
string letters = @"A
B
C
D.";

using StringReader left = new StringReader(numbers), right = new StringReader(letters);
string item;
do
{
    item = left.ReadLine();
    Console.Write($"{item}   ");
    item = right.ReadLine();
    Console.WriteLine(item);
} while (item != null);
```

!!! warning "Evite"
    Você pode instanciar o objeto e, em seguida, passar a variável para a instrução `using`, mas essa **não** é uma prática recomendada. Nesse caso, depois que o controle deixa o bloco em uso, o objeto permanece no escopo, mas provavelmente não tem acesso aos seus recursos não gerenciados. Em outras palavras, ele não está mais totalmente inicializado. Se você tentar usar o objeto fora do bloco de uso, corre o risco de causar o lançamento de uma exceção. Por esse motivo, é melhor instanciar o objeto na instrução `using` e limitar seu escopo ao bloco `using`.

    ```c#
    string manyLines = @"Linha 1
    Linha 2
    Linha 3
    Linha 4";

    var reader = new StringReader(manyLines);
    using (reader)
    {
        string? item;
        do {
            item = reader.ReadLine();
            Console.WriteLine(item);
        } while(item != null);
    }
    // aqui "reader" está no escopo, mas foi disposed
    ```

## Diretiva `statement`

A diretiva `using` permite que você use tipos definidos em um *namespace* sem especificar o *namespace* totalmente qualificado desse tipo. Em sua forma básica, a diretiva `using` importa todos os tipos de um único *namespace*, conforme mostrado no exemplo a seguir:

```c#
using System.Text;
```

Você pode aplicar dois modificadores a uma diretiva `using`:

- O modificador global tem o mesmo efeito que adicionar a mesma diretiva `using` a todos os arquivos-fonte em seu projeto. Esse modificador foi introduzido no C# 10.0.
- O modificador `static` importa os membros `static` e tipos aninhados de um único tipo, em vez de importar todos os tipos em um namespace. Este modificador foi introduzido.

Você pode combinar os dois modificadores para importar os membros estáticos de um tipo em todos os arquivos de origem em seu projeto.

Você também pode criar um alias para um *namespace* ou um tipo com uma diretiva *using alias*, como no exemplo abaixo:

```c#
using Project = PC.MyCompany.Project;
```

Você pode usar o modificador `global` em uma diretiva *using alias*.

O escopo de uma diretiva `using` sem o modificador `global` é o arquivo no qual ela aparece.

A diretiva `using` pode aparecer:

- No início de um arquivo de código-fonte, antes de qualquer *namespace* ou declaração de tipo.
- Em qualquer *namespace*, mas antes de quaisquer *namespaces* ou tipos declarados nesse *namespace*, a menos que o modificador `global` seja usado, caso em que a diretiva deve aparecer antes de todos os *namespaces* e declarações de tipo.

Crie uma diretiva `using` para usar os tipos em um *namespace* sem precisar especificar o *namespace*. Uma diretiva `using` não dá acesso a nenhum *namespace* aninhado no *namespace* especificado. Os *namespaces* vêm em duas categorias: definidos pelo usuário e definidos pelo sistema. *Namespaces* definidos pelo usuário são *namespaces* definidos em seu código.

### Modificador `global`

Adicionar o modificador `global` a uma diretiva `using` significa que `using` é aplicado a todos os arquivos na compilação (normalmente um projeto). A diretiva `global using` foi adicionada no C# 10.0. Sua sintaxe é:

```c#
global using <namespace-totalmente-qualificado>;
```

onde `namespace-totalmente-qualificado` é o nome totalmente qualificado do *namespace* cujos tipos podem ser referenciados sem especificar o *namespace*.

Uma diretiva `global using` pode aparecer no início de qualquer arquivo de código-fonte. Todas as diretivas `global using` em um arquivo devem aparecer antes de:

- Todas diretivas `using` sem o modificador `global`.
- Todas as declarações de *namespace* e tipo no arquivo.

Você pode adicionar diretivas `global using` a qualquer arquivo de código-fonte. Normalmente, você deseja mantê-los em um único local. A ordem de diretivas `global using` não importa, seja em um único arquivo ou entre arquivos.

O modificador `global` pode ser combinado com o modificador `static`. O modificador `global` pode ser aplicado a uma diretiva *using alias*. Em ambos os casos, o escopo da diretiva são todos os arquivos na compilação atual. O exemplo a seguir permite usar todos os métodos declarados em `System.Math` em todos os arquivos do um projeto.

```c#
global using static System.Math;
```

### Modificador `static`

A diretiva `using static` nomeia um tipo cujos membros estáticos e tipos aninhados você pode acessar sem especificar um nome de tipo. Sua sintaxe é:

```c#
using static <nome-tipo-totalmente-qualificado>;
```

O `<nome-tipo-totalmente-qualificado>` é o nome do tipo cujos membros estáticos e tipos aninhados podem ser referenciados sem especificar um nome de tipo. Se você não fornecer um nome de tipo totalmente qualificado (o nome completo do namespace junto com o nome do tipo), C# gera erro ao compilar.

A diretiva `using static` se aplica a qualquer tipo que tenha membros estáticos (ou tipos aninhados), mesmo se também tiver membros de instância. No entanto, os membros da instância só podem ser chamados por meio da instância do tipo.

Você pode acessar membros estáticos de um tipo sem ter que qualificar o acesso com o nome do tipo:

```c#
using static System.Console;
using static System.Math;
class Program
{
    static void Main()
    {
        WriteLine(Sqrt(3*3 + 4*4));
    }
}
```

Normalmente, ao chamar um membro estático, você fornece o nome do tipo junto com o nome do membro. Inserir repetidamente o mesmo nome de tipo para invocar membros do tipo pode resultar em código obscuro e prolixo. Por exemplo, a seguinte definição de uma classe `Circle` faz referência a muitos membros da classe `Math`.

```c#
using System;

public class Circle
{
   public Circle(double radius)
   {
      Radius = radius;
   }

   public double Radius { get; set; }

   public double Diameter
   {
      get { return 2 * Radius; }
   }

   public double Circumference
   {
      get { return 2 * Radius * Math.PI; }
   }

   public double Area
   {
      get { return Math.PI * Math.Pow(Radius, 2); }
   }
}
```

Ao eliminar a necessidade de referenciar explicitamente a classe `Math` cada vez que um membro é referenciado, a diretiva `using static` produz um código mais limpo:

```c#
using System;
using static System.Math;

public class Circle
{
   public Circle(double radius)
   {
      Radius = radius;
   }

   public double Radius { get; set; }

   public double Diameter
   {
      get { return 2 * Radius; }
   }

   public double Circumference
   {
      get { return 2 * Radius * PI; }
   }

   public double Area
   {
      get { return PI * Pow(Radius, 2); }
   }
}
```

A diretiva `using static` importa apenas membros estáticos acessíveis e tipos aninhados declarados no tipo especificado. Membros herdados não são importados.

A diretiva `using static` torna os métodos de extensão declarados no tipo especificado disponíveis para pesquisa de método de extensão. No entanto, os nomes dos métodos de extensão não são importados para o escopo para referência não qualificada no código.

Métodos com o mesmo nome importados de diferentes tipos por diferentes diretivas `using static` na mesma unidade de compilação ou namespace formam um grupo de métodos. A resolução de sobrecarga dentro desses grupos de métodos segue as regras normais do C#.

O exemplo a seguir usa a diretiva `using static` para tornar os membros estáticos das classes `Console`, `Math` e `String` disponíveis sem ter que especificar seu nome de tipo.

```c#
using System;
using static System.Console;
using static System.Math;
using static System.String;

class Program
{
   static void Main()
   {
      Write("Enter a circle's radius: ");
      var input = ReadLine();
      if (!IsNullOrEmpty(input) && double.TryParse(input, out var radius)) {
         var c = new Circle(radius);

         string s = "\nInformation about the circle:\n";
         s = s + Format("   Radius: {0:N2}\n", c.Radius);
         s = s + Format("   Diameter: {0:N2}\n", c.Diameter);
         s = s + Format("   Circumference: {0:N2}\n", c.Circumference);
         s = s + Format("   Area: {0:N2}\n", c.Area);
         WriteLine(s);
      }
      else {
         WriteLine("Invalid input...");
      }
   }
}

public class Circle
{
   public Circle(double radius)
   {
      Radius = radius;
   }

   public double Radius { get; set; }

   public double Diameter
   {
      get { return 2 * Radius; }
   }

   public double Circumference
   {
      get { return 2 * Radius * PI; }
   }

   public double Area
   {
      get { return PI * Pow(Radius, 2); }
   }
}
```

No exemplo, a diretiva `using static` também poderia ter sido aplicada ao tipo `Double`. Adicionar essa diretiva tornaria possível chamar o método `TryParse` (`String`, `Double`) sem especificar um nome de tipo. No entanto, usar `TryParse` sem um nome de tipo cria um código menos legível, uma vez que se torna necessário verificar as diretivas `using static` para determinar qual método `TryParse` do tipo numérico é chamado.

### alias

Crie uma diretiva `using` *alias* para tornar mais fácil qualificar um identificador para um *namespace* ou tipo. Em qualquer diretiva `using`, o *namespace* ou tipo totalmente qualificado deve ser usado independentemente das diretivas `using` anteriores. Nenhum *alias* `using` pode ser usado na declaração de uma diretiva `using`. Por exemplo, o exemplo a seguir gera um **erro** do compilador:

```c#
using s = System.Text;
using s.RegularExpressions; // Erro de compilação.
```

O exemplo a seguir mostra como definir e usar um *alias* `using` para um *namespace*:

```c#
namespace PC
{
    // Define um alias para o namespace aninhado.
    using Project = PC.MyCompany.Project;
    class A
    {
        void M()
        {
            // Usando o alias
            var mc = new Project.MyClass();
        }
    }
    // Namespace aninhado.
    namespace MyCompany
    {
        namespace Project
        {
            public class MyClass { }
        }
    }
}
```

Uma diretiva *alias* `using` não pode ter um tipo genérico aberto no lado direito. Por exemplo, você não pode criar um *alias* `using` para `List<T>`, mas pode criar um para `List<int>`. O exemplo a seguir mostra como definir uma diretiva `using` e um *alias* `using` para uma classe:

```c#
using System;
using AliasClass = NameSpace1.MyClass;             // Diretiva alias using para uma classe
using AliasGenericClass = NameSpace2.MyClass<int>; // Diretiva alias using para uma classe genérica

namespace NameSpace1
{
    public class MyClass
    {
        public override string ToString()
        {
            return "You are in NameSpace1.MyClass.";
        }
    }
}

namespace NameSpace2
{
    class MyClass<T>
    {
        public override string ToString()
        {
            return "You are in NameSpace2.MyClass.";
        }
    }
}

namespace NameSpace3
{
    class MainClass
    {
        static void Main()
        {
            var instance1 = new AliasClass();
            Console.WriteLine(instance1);

            var instance2 = new AliasGenericClass();
            Console.WriteLine(instance2);
        }
    }
}
// Saída:
// You are in NameSpace1.MyClass.
// You are in NameSpace2.MyClass.
```

## Referências

- <https://docs.microsoft.com/pt-br/dotnet/csharp/language-reference/keywords/using-statement>
- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/using-directive>
