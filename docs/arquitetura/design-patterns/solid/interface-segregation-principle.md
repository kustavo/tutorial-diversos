# Princípio da Segregação de Interface

O Princípio da Segregação de Interface (em inglês, *Interface Segregation Principle - ISP*) trata da coesão de interfaces e diz que clientes não devem ser forçados a depender de métodos que não usam.

Esse princípio basicamente diz que é melhor criar interfaces mais específicas ao invés de termos uma única interface genérica.

**Exemplo de violação do ISP:**

```c#
public interface MembroDeTimeScrum
{
    void PriorizarBacklog();
    void BlindarTime();
    void ImplementarFuncionalidades();
}

public class Dev : MembroDeTimeScrum
{
    public void PriorizarBacklog() { }
    public void BlindarTime() { }
 
    public void ImplementarFuncionalidades()
    {
        Console.Writeline("Programando...");
    }
}
 
public class ScrumMaster : MembroDeTimeScrum
{
    public void PriorizarBacklog() { }
 
    public void BlindarTime()
    {
        Console.Writeline("Blindando time...");
    }
 
    public void ImplementarFuncionalidades() { }
}
 
public class ProductOwner : MembroDeTimeScrum
{
    public void PriorizarBacklog()
    {
        Console.Writeline("Priorizando tarefas...");
    }
 
    public void BlindarTime() { }
    public void ImplementarFuncionalidades() { }
}
```

Ao criarmos uma interface genérica demais, acabamos fazendo com que uma implementação, no caso `Dev`, não utilize certos métodos da interface. É o que acontece com os métodos `PriorizarBacklog` e `BlindarTime`, que não fazem nada, pois não são atribuições de um `Dev` e sim do `ProductOwner` e do `ScrumMaster`, respectivamente.

**Problemas:**

Suponhamos que alguma alteração seja necessária no método `BlindarTime`, que agora precisa receber alguns parâmetros. Dessa forma, somos obrigados a alterar todas implementações de `MembroDeTimeScrum` (`Dev`, `ScrumMaster` e `ProductOwner`) por causa de uma mudança que deveria afetar apenas a classe `ScrumMaster`.

Além disso, classes-cliente que dependiam de `MembroDeTimeScrum` terão que ser recompiladas e se estão em diversos componentes terão que ser redistribuídas. Algumas vezes desnecessariamente, pois nem sequer faziam uso do método `BlindarTime`.

Outro problema é que a implementação de métodos inúteis (chamados "degenerados") pode levar à violação do LSP, pois alguém utilizando `MembroDeTimeScrum` poderia supor o seguinte:

```c#
foreach(var membro in membrosDeTimeScrum)
    membro.ImplementarFuncionalidades();
```

No entanto, sabemos que apenas `Dev` executa o comportamento acima. Se a lista tivesse também objetos do tipo `ScrumMaster` ou `ProductOwner`, esses objetos não estariam realizando nada, ou pior, poderiam disparar alguma exceção, caso a implementação dos mesmos assim o fizesse.

**Código alterado para atender o ISP:**

A solução para o exemplo acima seria criamos interfaces mais específicas para que cada classe cliente dependa apenas do que realmente necessita. Por exemplo:

```c#
public interface FuncaoDeScrumMaster
{
    void BlindarTime();
}
 
public class ScrumMaster : FuncaoDeScrumMaster
{
    public void BlindarTime()
    {
        Console.Writeline("Blindando time...");
    }
}
```

Com a alteração acima, a classe concreta `ScrumMaster` não precisa mais implementar métodos desnecessários apenas para utilizar `BlindarTime` já que agora depende da interface `FuncaoDeScrumMaster`.

A mesma ideia pode ser aplicada para as funções específicas de `Dev` e `ProductOwner`. Assim todos os clientes de MembroDeTimeScrum agora podem depender especificamente das interfaces que utilizam.

## Conclusão

O Princípio da Segregação de Interface nos alerta quanto à dependência em relação a interfaces genéricas, forçando que classes concretas implementem métodos desnecessários e causando um acoplamento grande entre todos os clientes.

Ao usarmos interfaces mais específicas, quebramos esse acoplamento entre as classes clientes, além de deixarmos as implementações mais limpas e coesas.

## Referências

- <https://robsoncastilho.com.br/2013/04/14/principios-solid-principio-da-segregacao-de-interface-isp/>
