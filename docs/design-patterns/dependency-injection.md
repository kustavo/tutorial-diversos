# Injeção de dependência e Service Locator

## Introdução

A **injeção de dependência** (em inglês, *Dependency Injection - DI*) é um padrão de desenvolvimento de programas de computadores utilizado quando é necessário manter baixo o nível de acoplamento entre diferentes módulos de um sistema. O foco principal é fazer com que uma classe não tenha conhecimento de como instanciar um objeto de um tipo do qual é dependente. Isto permite que os objetos sejam fracamente acoplados e siga os princípios de **inversão de controle** (em inglês, *Inversion of Control - IoC*) e responsabilidade única.

Injeção de dependência é uma das duas maneiras de implementar a inversão de controle que é um termo mais amplo, onde a responsabilidade de informar a implementação a ser utilizada deixa de ser da classe, e passa a ser do consumidor da classe. A segunda maneira de implementar a inversão de controle seria com **Service Locator**.

## Injeção de dependência

O padrão injeção de dependência diz: Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações. Abstrações não devem depender de detalhes. Detalhes devem depender de abstrações.

!!! note ""

    Benefícios da Injeção de dependência:

    - uma interface pode expor apenas alguns métodos dependendo da camada de negócio.
    - usar uma interface permite que injetemos classes diferentes que são compatíveis com a mesma interface, e assim facilitar a implementação de lógicas diferentes e também a testabilidade de um software.
    - quem vai consumir um método de uma classe, não precisa conhecê-lo, só precisa conhecer a sua assinatura através da interface (muitas vezes ele nem foi construído ainda, mas a interface já nos diz como ele será).
    - pode-se no caso de um repositório de banco de dados ter uma implementação específica para testes, fazendo um "Mock" do banco de dados com comportamento análogo ao banco real.
    - podemos ter uma única instância de uma classe dentro da aplicação sendo injetada em locais diversos consumindo menos memória.

Abaixo, um exemplo de algo que deve ser evitado devido ao alto acoplamento. Neste exemplo a `ClasseA` tem conhecimento de como instanciar objetos da `ClasseB`. Toda alteração feita no construtor de `ClasseB` afetará diretamente `ClasseA`.

```c#
public class ClasseA 
{
    private ClasseB _itemB = new ClasseB();

    public void metodoA() 
    {
        _itemB.metodoB();
    }
}
```

Através da injeção de dependência, existem pelo menos quatro maneiras pelas quais um objeto cliente pode receber uma referência a um objeto externo.

- **Injeção por construtor**: a classe a ser utilizada recebe por construtor a injeção de dependência.

- **Injeção por propriedades get/set**: a classe a ser utilizada expõe uma propriedade que o injetor usa para injetar a dependência.

- **Injeção por método**: as dependências são fornecidas por meio de métodos de classe ou métodos de interface.

Existem muitos frameworks DI que podem ajudar na hora de implementar o padrão *Dependecy Injection*. A dependências entre os módulos não são definidas programaticamente, mas sim pela configuração de uma infraestrutura de software (*container*) que é responsável por "injetar" em cada componente suas dependências declaradas.

O .NET Core vem com seu próprio container de injeção de dependência (conhecido também como container de inversão de controle).

### Injeção por construtor

Acontece quando a classe a ser utilizada recebe por construtor uma instância de classe que ela mesmo irá utilizar. Este método requer que o `ClasseA` forneça um parâmetro em um construtor para a dependência.

```c#
public class ClasseA 
{
    private IClasseB _itemB;

    public ClasseA(IClasseB itemB)
    {
        _itemB = itemB;
    }
}
```

### Injeção por propriedades

Ocorre quando se tem a classe a ser injetada exposta como uma propriedade.

```c#
public class ClasseA 
{
    private IClasseB _itemB;

    public IClasseB ClasseB
    {
        get
        {
            if (_itemB == null)
                throw new Exception("Não inicializada");

            return _itemB;
        }
        set
        {
            _itemB = value;
        }
    }
}
```

### Injeção por métodos

Na injeção por método, as dependências são fornecidas por meio de métodos. Este método pode ser um método de classe ou um método de interface. A injeção por método de interface também é conhecida por **Injeção por interface**.

```c#
public interface IClasseI
{
    void metodoI(IClasseB itemB);
}

public class ClasseA : IClasseI
{
    private IClasseB _itemB;

    // Método de classe
    public void metodoA(IClasseB itemB) {
        _itemB = itemB;
    }

    // Método de interface
    public void metodoI(IClasseB itemB) {
        _itemB = itemB;
    }
}
```

## Service Locator

Modo em que construímos classes que servem como "localizadoras" de objetos que iremos instanciar em nossas outras classes. Geralmente é chamado de contêiner, pois podemos imaginar um contêiner que fornece várias instâncias no qual podemos solicitar uma instância específica para ele.

Primeiro, devemos ter uma classe para registrar e fornecer as instâncias:

```c#
public static class ServiceLocators
{
    private static IDicionary<string, Object> services = new Dicionary<string, Object>();

    public static T Get<T>(string id) => 
        (T)services[id];
    
    public bool Has(string id) =>
        services.ContainsKey(id);

    public static void Register<T>(string id, T service) =>
        services.Add(new KeyValuePair<string, Object>(id, service))
}
```

Em segundo lugar, em outra parte do seu código, podemos registrar todas as instâncias:

```c#
ServiceLocators.Register<IClasseB>("classeB", new ClasseB());
```

E finalmente podemos usar:

```c#
var objetoB = ServiceLocators.Get<IClasseB>("classeB");
```

## Inversão de controle

Inversão de controle é um princípio de padrão de projeto onde a sequência (controle) de chamadas dos métodos é invertida em relação à programação tradicional, ou seja, ela não é determinada diretamente pelo programador. Este controle é delegado a uma infraestrutura de software muitas vezes chamada de container ou a qualquer outro componente que possa tomar controle sobre a execução.

A inversão de controle é um conceito geral em que o fluxo de controle normal é "invertido" de alguma forma. Por fluxo "normal", podemos dizer um fluxo de aplicativo tradicional, onde o código é executado do início ao fim, criando recursos, solicitando dados e fornecendo saída. O fluxo de controle é ditado pelo próprio aplicativo, chamando as bibliotecas e recursos do sistema conforme necessário. Qualquer inversão disso onde por exemplo um contêiner, sistema operacional ou biblioteca, conduzem o fluxo de controle em vez do aplicativo, é em certo sentido, inversão de controle.

!!! note ""
    A inversão de controle ocorre quando ao invés de se criar explicitamente um código, ou acompanhar todo o ciclo de vida de uma execução, o programador delega alguma dessas funcionalidades para um terceiro.

Um dos exemplos mais comuns de IoC é um loop de eventos. Em um loop de evento, o GUI toolkit (ou o sistema operacional) está no controle, chamando de volta seu aplicativo para manipular eventos e processar a entrada. O fluxo normal, solicitando entrada e processando, é invertido para quem processa as entradas conduza o aplicativo, ao invés do contrário.

A **injeção de dependência é um uso específico de inversão de Controle**, onde a inversão de controle é aplicada à seleção e alocação de dependências. Em vez de ter um componente instanciando os subcomponentes de que necessita, o código de criação (seja o código do aplicativo, manualmente ou o contêiner IoC/DI) instancia os subcomponentes necessários e os injeta no componente.

Tanto a injeção de dependência quando o service locator possuem maneiras de controlar o ciclo de vida dos objetos gerados. Ou seja, segundo os exemplos anteriores, não é o código da `ClasseA` quem controla o ciclo de vida dos objetos. Tão pouco é ela quem define quais instâncias deseja usar. No máximo ela conhece um nome de serviço ou uma interface.

Na programação orientada a objetos, existem várias técnicas básicas para implementar a inversão de controle. Estes são:

- Usando um padrão de localizador de serviço
- Usando **injeção de dependência**
- Usando **service locator**
- Usando *contextualized lookup*
- Usando o padrão de projeto *method design*
- Usando o padrão de projeto *strategy*

Outras formas:

- Programação orientada a eventos
- Linguagens de programação funcional

!!! note ""
    A injeção de dependência é uma forma de inversão de controle, mas não necessariamente atinge muita desacoplamento. Inversão de dependência é o que alcança o desacoplamento, e é facilitada pelo uso de injeção de dependência com um container IoC/DI.

## Referências

- <https://stackoverflow.com/questions/48516359/difference-between-interface-injection-and-method-injection>
- <https://en.wikipedia.org/wiki/Dependency_injection#Three_types_of_dependency_injection>
- <https://www.tutorialsteacher.com/ioc/dependency-injection>
- <https://betterprogramming.pub/the-3-types-of-dependency-injection-141b40d2cebc>
- <https://balta.io/blog/aspnet-core-dependency-injection>
- <https://medium.com/@eduardolanfredi/inje%C3%A7%C3%A3o-de-depend%C3%AAncia-ff0372a1672>
- <https://imasters.com.br/software/inversao-de-controle-service-locator-e-injecao-de-dependencia>
- <https://softwareengineering.stackexchange.com/questions/131324/what-is-the-difference-between-di-and-ioc>
