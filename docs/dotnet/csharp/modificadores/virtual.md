# virtual

A palavra-chave `virtual` é usada para modificar um método, propriedade, indexador ou declaração de evento para permitir que ele seja sobrescrito em uma classe derivada. Por exemplo, este método pode ser sobrescrito por qualquer classe que o herde:

```c#
public virtual double Area()
{
    return x * y;
}
```

Quando um método virtual é chamado, o tipo do objeto é verificado (em tempo de execução) para um membro que irá sobrescrevê-lo.

**Características do modificador `virtual`:**

- O método que irá sobrescrever um método virtual precisa usar o modificador `override`.
- Não é possível sobrescrever um método não virtual.
- Não é obrigatório sobrescrever um método virtual.
- Não é possível usar o modificador `virtual` com os modificadores `static`, `abstract`, `private` ou `override`.

O exemplo a seguir mostra uma propriedade virtual:

```c#
class ClasseBase
{
    private string campo2;
    private string campo3;

    public virtual string Campo1 { get; set; }
    public virtual string Campo2
    {
        get => campo2;
        set => campo2 = $"ClasseBase: {value}";
    }
    public virtual string Campo3
    {
        get => campo3;
        set => campo3 = $"ClasseBase: {value}";
    }
}

class ClasseDerivada : ClasseBase
{
    private string campo1;
    private string campo2;

    public override string Campo1
    {
        get => campo1;
        set => campo1 = $"ClasseDerivada: {value}";
    }
    public override string Campo2
    {
        get => campo2;
        set => campo2 = $"ClasseDerivada: {value}";
    }
}

class Program
{
    static void Main(string[] args)
    {
        var c = new ClasseDerivada();
        c.Campo1 = "1";
        c.Campo2 = "2";
        c.Campo3 = "3";
        Console.WriteLine(c.Campo1); // saída: ClasseDerivada: 1
        Console.WriteLine(c.Campo2); // saída: ClasseDerivada: 2
        Console.WriteLine(c.Campo3); // saída: ClasseBase: 3
    }
}
```

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/virtual>
