# namespace

A palavra-chave `namespace` é usada para declarar um escopo que contém um conjunto de objetos relacionados. Você pode usar um *namespace* para organizar elementos de código e criar tipos globalmente exclusivos.

```c#
namespace ExemploNamespace
{
    class ExemploClass { }

    interface IExemploInterface { }

    struct ExemploStruct { }

    enum ExemploEnum { a, b }

    delegate void ExemploDelegate(int i);

    namespace ExemploNamespaceAninhado
    {
        class ExemploClass2 { }
    }
}
```

As declarações de *namespace* com escopo de arquivo permitem que você declare que todos os tipos em um arquivo estão em um único *namespace*. As declarações de *namespace* com escopo de arquivo estão disponíveis com C# 10.0. O exemplo a seguir é semelhante ao exemplo anterior, mas usa uma declaração de *namespace* com escopo de arquivo:

```c#
namespace ExemploNamespace;

class ExemploClass { }

interface IExemploInterface { }

struct ExemploStruct { }

enum ExemploEnum { a, b }

delegate void ExemploDelegate(int i);
```

O exemplo anterior não inclui um *namespace* aninhado. Os *namespaces* com escopo de arquivo não podem incluir declarações de *namespace* adicionais. Você não pode declarar um *namespace* aninhado ou um segundo *namespace* com escopo de arquivo:

```c#
namespace ExemploNamespace;

class AnotherExemploClass
{
    public void AnotherExemploMethod()
    {
        System.Console.WriteLine(
            "ExemploMethod inside ExemploNamespace");
    }
}

namespace AnotherNamespace; // Não permitido!

namespace ANestedNamespace // Não permitido!
{
   // declarations...
}
```

Em um *namespace*, você pode declarar zero ou mais dos seguintes tipos:

- class
- interface
- struct
- enum
- delegate
- namespaces aninhados podem ser declarados exceto em declarações de escopo de arquivo.

O compilador adiciona um *namespace* padrão. Esse *namespace* sem nome, às vezes chamado de *namespace* global, está presente em todos os arquivos. Ele contém declarações não incluídas em um namespace declarado. Qualquer identificador no *namespace* global está disponível para uso em um *namespace* nomeado.

Os *namespaces* têm acesso público implicitamente. É possível definir um *namespace* em duas ou mais declarações. Por exemplo, o exemplo a seguir define duas classes como parte do *namespace* `MyCompany`:

```c#
namespace MyCompany.Proj1
{
    class MyClass
    {
    }
}

namespace MyCompany.Proj1
{
    class MyClass1
    {https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/namespace
    }
}
```

O exemplo a seguir mostra como chamar um método estático em um *namespace* aninhado.

```c#
namespace SomeNameSpace
{
    public class MyClass
    {
        static void Main()
        {
            Nested.NestedNameSpaceClass.SayHello(); // saída: Hello
        }
    }

    // Namespace aninhado
    namespace Nested
    {
        public class NestedNameSpaceClass
        {
            public static void SayHello()
            {
                Console.WriteLine("Hello");
            }
        }
    }
}
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/namespace>
