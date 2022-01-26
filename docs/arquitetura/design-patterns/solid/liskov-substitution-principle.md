# Princípio da substituição de Liskov

O Princípio da substituição de Liskov (em inglês, *Liskov Substitution Principle - LSP*) diz que classes derivadas devem ser capazes de substituir suas classes base.

!!! quote

    Se S é um subtipo de T, então os objetos do tipo T, em um programa, podem ser substituídos pelos objetos de tipo S sem que seja necessário alterar as propriedades deste programa.

**Exemplo de violação do LSP:**

```c#
public class Arquivo
{
   // outros métodos...
}
 
public class ArquivoWord : Arquivo
{
   public void GerarDocX()
   {
      // codigo para geracao do arquivo
   }
}
 
public class ArquivoPdf : Arquivo
{
   public void GerarPdf()
   {
      // codigo para geracao do arquivo
   }
}
```

Nesta hierarquia de classes, `ArquivoWord` e `ArquivoPdf` herdam de `Arquivo`, provavelmente para reaproveitar algum campo/comportamento, mas cada uma das derivadas tem seu próprio método de geração, ignorando o uso de polimorfismo.

Este design fere o LSP, uma vez que nenhuma das classes derivadas pode ser usada como a classe base. É exatamente o que acontece no classe `GeradorDeArquivos` abaixo:

```c#
public class GeradorDeArquivos
{
   public void GerarArquivos(IList<Arquivo> arquivos)
   {
      foreach(var arquivo in arquivos)
      {
         if (arquivo is ArquivoWord)
            ((ArquivoWord)arquivo).GerarDocX();
         else if (arquivo is ArquivoPdf)
            ((ArquivoPdf)arquivo).GerarPdf();
      }
   }
}
```

Vejam que no método acima não conseguimos usar as derivadas de forma polimórfica (como, por ex, `arquivo.Gerar()`). Ao invés disso, somos obrigados a verificar o tipo e fazer o *downcast* para chamar o método apropriado.

Em resumo, ao ferir o LSP (na herança entre os tipos de `Arquivo`) acabamos ferindo o OCP por consequência.

**Outro exemplo de violação do LSP:**

Agora um exemplo mais sutil de violação do LSP. Neste exemplo até conseguimos usar a classe derivada no lugar da classe base em tempo de compilação, mas, em tempo de execução, temos um comportamento inesperado.

```c#
public class Retangulo
{
   public virtual double Altura { get; set; }
   public virtual double Comprimento { get; set; }
   public double Area { 
      get { return Altura*Comprimento; } 
   }
}
 
public class Quadrado : Retangulo
{
   public override double Altura
   {
      set { base.Altura = base.Comprimento = value; }
   }
 
   public override double Comprimento
   {
      set { base.Altura = base.Comprimento = value; }
   }
}
```

Neste exemplo, o `Quadrado` sobrescreve `Altura` e `Comprimento` para manter sua regra de que ambos devem ser iguais. Com isso, a classe derivada viola uma regra estabelecida na classe base: a de que altura e comprimento variam independentemente.

Vejamos que implicações podemos ter com isso, observando o seguinte código:

```c#
public void MetodoQualquer(Retangulo retangulo)
{
   retangulo.Altura = retangulo.Altura * 2;
   retangulo.Comprimento = retangulo.Comprimento * 4;
   // ..
}
```

Neste exemplo, vendo o parâmetro, percebemos que o programador assumiu que estava lidando com um retângulo e aplicou um cálculo que variasse suas dimensões.

No entanto, caso o método receba, em tempo de execução, um `Quadrado` (`Quadrado` herda de `Retangulo`) teremos um comportamento inesperado: o cálculo para redimensionar o retângulo o transformará em um quadrado, isto é, ao quadruplicar o comprimento, inadvertidamente, a altura também foi quadruplicada!

O problema acima só existe porque a classe derivada não respeita a regra da classe base de variar os lados de forma independente. Sendo assim, do ponto de vista computacional, `Quadrado` não é um `Retangulo`, pois ambos possuem comportamentos diferentes em relação a alteração de seus lados.

## Conclusão

Ao atender o Princípio de Substituição de Liskov (LSP), ou seja, ao garantir que as classes derivadas sejam usadas transparentemente onde se vê uma classe base, todo código que depende da classe base será capaz de usar, em tempo de execução, qualquer uma das derivadas sem sequer saber da existência delas.

Desta forma, estamos garantindo também o OCP, facilitando a extensão do software e deixa-lo livre de mau funcionamento.

## Referências

- <https://robsoncastilho.com.br/2013/03/21/principios-solid-principio-de-substituicao-de-liskov-lsp/>