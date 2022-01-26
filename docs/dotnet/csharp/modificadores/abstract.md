# abstract

O modificador `abstract` indica que o elemento que está sendo modificado tem uma implementação ausente ou incompleta. O modificador `abstract` pode ser usado com classes, métodos, propriedades, indexadores e eventos. Use o modificador `abstract` em uma declaração de classe para indicar que uma classe se destina a ser apenas uma classe base de outras classes, não instanciada por conta própria. Os membros marcados como abstratos devem ser implementados por classes não abstratas que derivam da classe abstrata.

Use o modificador `abstract` em um método ou propriedade para indicar que o método ou propriedade não contém implementação. Um método abstrato é sobrescrito pela classe derivada usando o modificador `override`.

```c#
abstract class ClasseAbstrata
{
    public abstract string Propriedade { get; }

    public abstract string Metodo();
}

class ClasseConcreta : ClasseAbstrata
{
    // Não é possível implementar o accessor "set"
    public override string Propriedade { get; } 

    public override string Metodo()
    {
        return "Olá!!!";
    }
}

class Program
{
    static void Main(string[] args)
    {
        var c = new ClasseConcreta();
        Console.WriteLine(c.Metodo()); // saída: "Olá!!!";
    }
}
```

Se uma classe abstrata implementa uma interface, ela deve fornecer implementação para todos os membros da interface. Uma classe abstrata que implementa uma interface pode mapear os métodos da interface em métodos abstratos. Por exemplo:

```c#
interface I
{
    void Metodo();
}

abstract class C : I
{
    public abstract void Metodo();
}
```

**Características de classes abstratas:**

- Uma classe abstrata não pode ser instanciada.
- Uma classe abstrata pode conter métodos abstratos e acessadores.
- Não é possível modificar uma classe abstrata com o modificador `sealed` porque os dois modificadores têm significados opostos.
  - O modificador `sealed` evita que uma classe seja herdada e o modificador `abstract` requer que uma classe seja herdada.
- Uma classe não abstrata derivada de uma classe abstrata deve incluir implementações reais de todos os métodos e propriedades abstratos herdados.

**Características de métodos e propriedades abstratos:**

- Um método/propriedade abstrata é implicitamente virtual.
- Declarações de métodos/propriedades abstratas são permitidas **apenas em classes abstratas**.
- Como uma declaração de método/propriedade abstrata não fornece implementação real, não há corpo de método/propriedade.
  - A implementação é fornecida pela sobrescrita do método/propriedade pela classe herdeira não abstrata.
- Não é permitido usar os modificadores `static` ou `virtual` em uma declaração de método/propriedade abstrata.
- Se uma propriedade abstrata não tem um acessor `get` ou `set`, a classe herdeira será impedida de criar/usar este acessor.

**Diferença entre métodos e propriedades abstratos e virtuais:**

- Um método/propriedade abstrata não pode ter corpo, já virtuais podem.
- Classes derivadas são obrigadas a implementar todos métodos/propriedades abstratas, mas não é obrigada para virtuais.

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/abstract>
