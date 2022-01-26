# Princípio da Inversão de Dependência

O Princípio da Inversão de Dependência (em inglês, *Dependency Inversion Principle - DIP*) é um princípio de projeto de software que refere-se à dissociação de módulos de software. Este princípio sugere uma solução para o problema de dependência, mas não diz como implementá-la ou que técnica usar.

O Princípio da Inversão de Dependência procura manter o foco da tarefa de design no negócio, deixando este design independente ou desacoplado do componente que vai executar as tarefas de baixo nível que não fazem parte da modelagem do negócio.

!!! quote

    - Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações;
    - Abstrações não devem depender de detalhes. Detalhes devem depender de abstrações.

Inversão de dependência refere-se estritamente a alteração das dependências entre módulos. Por exemplo: Pensemos num fluxo muito comum, onde as requisições são feitas através da camada de apresentação, passam pela camada de negócios e terminam na camada de persistência. Nós teríamos o seguinte fluxo de dependências:

<figure>
    <img src="../_dependency-inversion-principle/fluxo-tradicional.png"/>
    <figcaption>Fluxo tradicional</figcaption>
</figure>

A camada de apresentação depende dos serviços declarados na camada de negócios, que por sua vez, depende das classes declaradas na camada de persistência. A inversão de dependência, em um cenário como o proposto, faria com que os diferentes módulos conversassem através de interfaces e as dependências seriam centralizadas na camada de negócio (a mais importante). O diagrama mudaria para:

<figure>
    <img src="../_dependency-inversion-principle/fluxo-dip.png"/>
    <figcaption>Fluxo com inversão de dependência</figcaption>
</figure>

A relação entre as camadas de apresentação e negócio continua a mesma, porém a dependência é mais fraca, já que é construída através de interfaces e não implementações. Já a relação entre as camadas de negócio e persistência mudaram. Antes a camada de negócio dependia da camada de persistência. Com a inversão de dependência, a camada de persistência é que passa a depender das interfaces declaradas na camada de negócios. Isso é Inversão de Dependência, que como você pode perceber, é bem diferente de [Inversão de Controle](../dependency-injection.md).

**Exemplo de violação do DIP:**

```c#
public class Botao
{
    private Lampada _lampada;
 
    public void Acionar()
    {
        if (condicao)
            _lampada.Ligar();
    }
}
```

O design acima viola o DIP uma vez que `Botao` depende de uma classe concreta `Lampada`. Ou seja, `Botao` conhece detalhes de implementação de `Lampada` ao invés de saber da abstração. O `Botao` deve ser capaz de tratar alguma ação e ligar ou desligar algum dispositivo, seja ele qual for: uma lâmpada, um motor, um alarme, etc.

**Código alterado para atender o DIP:**

Invertendo a dependência com a **[injeção de dependência](../dependency-injection.md)**:

A solução abaixo inverte a dependência de `Botao` para a `Lampada`, fazendo com que ambos agora dependam da abstração `IDispositivo`:

```c#
public class Botao
{
    private IDispositivo _dispositivo;
 
    public void Acionar()
    {
        if (condicao)
            _dispositivo.Ligar();
    }
}
 
public interface IDispositivo
{
    void Ligar();
    void Desligar();
}
 
public class Lampada : IDispositivo
{
    public void Ligar()
    {
        // ligar lampada
    }
    public void Desligar()
    {
        // desligar lampada
    }
}
```

<figure>
    <img src="../_dependency-inversion-principle/exemplo-violacao.png"/>
    <figcaption>Exemplo violação do DIP</figcaption>
</figure>
<figure>
    <img src="../_dependency-inversion-principle/exemplo-solucao.png"/>
    <figcaption>Exemplo solução da violação do DIP</figcaption>
</figure>

Antes, o componente de alto nível dependia do componente de baixo nível mas agora, com a solução acima, o componente de baixo nível é que depende de uma interface definida pelas diretrizes de negócio - dizemos então que houve uma inversão de dependência.

**Alguns benefícios da DIP:**

- Manter o foco nas necessidades do negócio em vez de depender dos detalhes de implementação e necessidades dos objetos de mais baixo nível.
- Componentes mais reutilizáveis e que não precisam mudar apenas para acompanhar as mudanças necessárias em outro componente.
- O componente de baixo nível pode ser substituído por um *mock* durante os testes automatizados.

!!! note ""
    [Injeção de dependência](../dependency-injection.md) é sobre fiação (*wiring*), [inversão de controle](../dependency-injection.md)  é sobre direção e princípio da inversão de dependência é sobre a forma.

!!! note ""
    A injeção de dependência é uma forma de inversão de controle, mas não necessariamente atinge muita desacoplamento. Inversão de dependência é o que alcança o desacoplamento, e é facilitada pelo uso de injeção de dependência com um container IoC/DI.

## Conclusão

O DIP é um princípio essencial para um bom design orientado a objetos, ao passo que o oposto leva a um design engessado e procedural. Identificar abstrações e inverter as dependências garantem que o software seja mais flexível e robusto, estando melhor preparado para mudanças.

## Referências

- <https://robsoncastilho.com.br/2013/05/01/principios-solid-principio-da-inversao-de-dependencia-dip>
- <https://imasters.com.br/software/inversao-de-controle-service-locator-e-injecao-de-dependencia>
