# Princípio da Responsabilidade Única

O Princípio da Responsabilidade Única (em inglês, *Single Responsibility Principle - SRP*), define que "uma classe deve ter apenas um motivo para mudar". Isso significa que uma classe não pode ter mais de uma responsabilidade.

Este princípio nada mais é do que uma perspectiva diferente para um dos mais fundamentais princípios da orientação a objetos: **a coesão**.

**Eventuais problemas causados pela violação deste princípio:**

- Dificuldade de compreensão e, portanto, dificuldade de manutenção.
- Dificuldade de reuso.
- Com responsabilidades entrelaçadas em uma mesma classe, pode ficar difícil alterar uma dessas responsabilidades sem comprometer as outras (rigidez) e pode acabar quebrando outras partes do software (fragilidade).
- Acoplamento alto, ou seja, a classe tem um número excessivo de dependências, e portanto fica mais sujeita a mudanças em decorrência de alterações em outras classes (novamente a fragilidade).

**Exemplo de violação do SRP:**

```c#
public class Produto
{
    public int Id {get; set;}
    public string Descricao {get; set;}
    public decimal preco {get; set;}

    public void CalcularICMS(decimal taxa)
    {
        // ...
    }
}
```

No exemplo acima, a classe `Produto` tem duas responsabilidades, a primeira representar um `produto` (imaginando que é uma classe de persistência) e a segunda calcular o ICMS. Não é de responsabilidade da classe `Produto` calcular impostos, essa responsabilidade deve estar em outra classe. Se um novo imposto surgir, deveremos alterar a classe `Produto` para adicionar uma nova regra, violando assim o SRP.

**Código alterado para atender o SRP:**

Para resolver o problema do exemplo anterior, podemos criar uma segunda classe chamada `CalcularImpostos` e atribuir a mesma a responsabilidade de calcular impostos que serão adicionados ao preço do `produto`.

```c#
public class Produto
{
    public int Id {get; set;}
    public string Descricao {get; set;}
    public decimal preco {get; set;}
}

public class CalcularImpostos
{
    public void CalcularICMS(Produto produto, decimal taxa)
    {
        // ...
    }
}
```

**Outro exemplo de violação do SRP:**

```c#
public class Cliente
{
    // ... propriedades
    // ... métodos
 
    public decimal CalcularDescontoPara(Venda venda)
    {
        if (venda.FormaDePagamento == FormaDePagamento.AVista)
        {
            if (venda.Total > 2000m)
                return venda.Total * 0.2;
            return venda.Total * 0.1;
        }
        return 0m;
    }
}
```

Observem acima que o método `CalcularDescontoPara` não manipula nenhum dado da classe `Cliente`, ou seja, nenhuma informação do cliente é necessária para se determinar o valor do desconto.

Sendo assim, esta classe possui pelo menos duas razões para mudar: uma quando houver alteração na lógica de negócio referente a um `Cliente` e outra quando houver alguma alteração na lógica de uma `Venda`. Certamente, faz mais sentido que este método seja da classe `Venda`.

Agora em ambos exemplos, cada classe tem sua responsabilidade bem definida.

## Conclusão

Se uma classe tem mais de uma responsabilidade ou um motivo para ser alterada, ela esta ferindo o princípio SRP. No primeiro momento, pode parecer trabalhoso separar as classes por responsabilidades, mas utilizando esse princípio garantimos a qualidade do código e o respeito as bases da orientação à objetos. Nossas classes serão mais coesas e o entendimento do nosso código será mais fácil.

O Princípio da Responsabilidade Única (SRP) é um dos princípios mais importantes que existe na orientação a objetos. Quando falamos de *responsabilidades* e *coesão* estamos tocando em dois pontos-chave da orientação a objetos, que nos ajudam a criar classes menores, de mais fácil entendimento, manutenção e reuso.

## Referências

- <https://medium.com/@angelomribeiro/princ%C3%ADpio-da-responsabilidade-%C3%BAnica-6d633087fa4e>
- <https://robsoncastilho.com.br/2013/02/06/principios-solid-principio-da-responsabilidade-unica-srp/>
