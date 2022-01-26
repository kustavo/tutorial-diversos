# Generics

*Generics* introduz o conceito de parâmetros de tipo no .NET, que torna possível projetar classes e métodos que adiam a especificação de um ou mais tipos até que a classe ou método seja declarado e instanciado pelo código do cliente.

Um método genérico pode usar seu parâmetro de tipo como o tipo de seu valor de retorno ou como o tipo de um de seus parâmetros formais. O código a seguir ilustra uma definição de classe genérica simples.

```c#
public class Generic<T>
{
    public T Field;
}
```

Usando um parâmetro de tipo genérico `T`, você pode escrever uma única classe que outro código cliente pode usar sem incorrer no custo ou risco de conversões em tempo de execução ou operações de *boxing*, conforme mostrado aqui:

```c#
public class GenericList<T>
{
    public void Add(T input)
    {
        // ...
    }
}
class TestGenericList
{
    private class ExampleClass { }
    static void Main()
    {
        // Declara uma lista de tipo int.
        GenericList<int> list1 = new GenericList<int>();
        list1.Add(1);

        // Declara uma lista de tipo string.
        GenericList<string> list2 = new GenericList<string>();
        list2.Add("");

        // Declara uma lista de tipo ExampleClass.
        GenericList<ExampleClass> list3 = new GenericList<ExampleClass>();
        list3.Add(new ExampleClass());
    }
}
```

Classes e métodos genéricos combinam capacidade de reutilização, segurança de tipo e eficiência de uma forma que suas contrapartes não genéricas não podem. Os genéricos são usados ​​com mais frequência com coleções e os métodos que operam nelas. O *namespace* `System.Collections.Generic` contém várias classes de coleção baseadas em genéricos. As coleções não genéricas, como `ArrayList`, não são recomendadas e são mantidas para fins de compatibilidade.

Você também pode criar tipos e métodos genéricos personalizados para fornecer suas próprias soluções generalizadas e padrões de design que são seguros para tipos e eficientes.

O exemplo de código a seguir mostra uma classe de lista vinculada genérica simples para fins de demonstração. O parâmetro de tipo `T` é usado em vários locais onde um tipo concreto normalmente seria usado para indicar o tipo do item armazenado na lista. É usado das seguintes maneiras:

- Como o tipo de um parâmetro de método no método `AddHead`.
- Como o tipo de retorno da propriedade `Data` na classe aninhada `Node`.
- Como o tipo do membro privado `data` na classe aninhada.

`T` está disponível para a classe aninhada `Node`. `Quando GenericList<T>` é instanciado com um tipo concreto, por exemplo, como `GenericList<int>`, cada ocorrência de `T` será substituída por `int`.

```c#
public class GenericList<T>
{
    // A classe aninhada também é genérica em T.
    private class Node
    {
        // T usada em um construtor não genérica.
        public Node(T t)
        {
            next = null;
            data = t;
        }

        private Node next;
        public Node Next
        {
            get => next;
            set => next = value;
        }

        // T como tipo do campo privado data.
        private T data;

        // T como tipo de retorno da propriedade.
        public T Data
        {
            get => data;
            set => data = value;
        }
    }

    private Node head;

    // Construtor
    public GenericList()
    {
        head = null;
    }

    // T como tipo do parâmetro do método
    public void AddHead(T t)
    {
        Node n = new Node(t);
        n.Next = head;
        head = n;
    }

    public IEnumerator<T> GetEnumerator()
    {
        Node current = head;

        while (current != null)
        {
            yield return current.Data;
            current = current.Next;
        }
    }
}
```

O exemplo de código a seguir mostra como o código do cliente usa a classe genérica `GenericList<T>` para criar uma lista de inteiros. Simplesmente alterando o argumento de tipo, o código a seguir pode ser facilmente modificado para criar listas de *strings* ou qualquer outro tipo personalizado:

```c#
class TestGenericList
{
    static void Main()
    {
        // int is the type argument
        GenericList<int> list = new GenericList<int>();

        for (int x = 0; x < 10; x++)
        {
            list.AddHead(x);
        }

        foreach (int i in list)
        {
            System.Console.Write(i + " ");
        }
        System.Console.WriteLine("\nDone");
    }
}
```

Os métodos genéricos podem aparecer em tipos genéricos ou não genéricos. É importante notar que um método não é genérico apenas porque pertence a um tipo genérico, ou mesmo porque possui parâmetros formais cujos tipos são os parâmetros genéricos do tipo envolvente. Um método é genérico apenas se tiver sua própria lista de parâmetros de tipo. No código a seguir, apenas o método `MetodoG` é genérico.

```c#
class A
{
    T MetodoG<T>(T arg)
    {
        T temp = arg;
        //...
        return temp;
    }
}
class Generic<T>
{
    T MetodoM(T arg)
    {
        T temp = arg;
        //...
        return temp;
    }
}
```

**Visão geral:**

- Use tipos genéricos para maximizar a reutilização de código, segurança de tipo e desempenho.
- O uso mais comum de genéricos é para criar classes de coleção.
  - A biblioteca de classes .NET contém várias classes de coleção genéricas no *namespace* `System.Collections.Generic`. As coleções genéricas devem ser usadas sempre que possível, em vez de classes como `ArrayList` no `namespace System.Collections`.
- Você pode criar suas próprias interfaces, classes, métodos, eventos e *delegates* genéricos.
- As classes genéricas podem ser restringidas para permitir o acesso a métodos em tipos de dados específicos.
- Informações sobre os tipos que são usados ​​em um tipo de dados genérico podem ser obtidas em tempo de execução usando reflexão.

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/fundamentals/types/generics>
- <https://docs.microsoft.com/en-us/dotnet/standard/generics/>
