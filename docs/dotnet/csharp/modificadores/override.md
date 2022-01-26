# override

A palavra-chave `override` é usada para estender ou modificar a implementação abstrata ou virtual de um método, propriedade, indexador ou evento herdado.

Um método sobrescrito (declarado com `override`) fornece uma nova implementação do método herdado de uma classe base. Um método sobrescrito deve ter a mesma assinatura do método base que foi sobrescrito.

A partir do C# 9.0, os métodos sobrescritos oferecem suporte a tipos de retorno covariante. O tipo de retorno de um método sobrescrito pode derivar do tipo de retorno do método base correspondente.

```c#
abstract class ClasseAbstrata
{
    public abstract string Metodo1();

    public virtual string Metodo2()
    {
        return "Olá";
    }
}

class ClasseConcreta : ClasseAbstrata
{
    public override string Metodo1()
    {
        return "Olá!!!!";
    }
    public override string Metodo2()
    {
        return "Olá!!!!";
    }
}

class Program
{
    static void Main(string[] args)
    {
        var c = new ClasseConcreta();
        Console.WriteLine(c.Metodo1()); // saída: "Olá!!!";
        Console.WriteLine(c.Metodo2()); // saída: "Olá!!!";
    }
}
```

**Características do modificador `override`:**

- Não é permitido sobrescrever um método/propriedade não virtual ou estático.
- O método base sobrescrito deve ser `virtual`, `abstract` ou `override`.
- Um método/propriedade sobrescrito não pode alterar a acessibilidade do método/propriedade virtual. Tanto o método/propriedade sobrescrito quanto o método/propriedade virtual devem ter o mesmo modificador de nível de acesso.
- Não é permitido usar modificadores `new`, `static`, ou `virtual` para modificar um método sobrescrito.
- Uma declaração de propriedade de sobrescrita deve especificar exatamente o mesmo modificador de acesso, tipo e nome da propriedade herdada.
- A partir do C# 9.0, as propriedades de sobrescrita somente leitura oferecem suporte a tipos de retorno covariante.

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/override>
