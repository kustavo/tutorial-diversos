# Princípio do Aberto/Fechado

O Princípio do Aberto/Fechado (em inglês, *Open-Closed Principle - OCP*) diz que entidades de software (classes, módulos, funções e etc) devem estar abertas para extensão, porém fechadas para modificação. Portanto, quando novos comportamentos e recursos precisam ser adicionados no software, devemos estender e não alterar o código existente.

**Eventuais problemas causados pela violação deste princípio:**

- Dificuldade para extensibilidade e manutenção: Na manutenção de códigos é necessário lidar com as classes já criadas e, possivelmente, adicionar ou remover funcionalidades. Para uma classe "não-extensível", a adição ou remoção de funcionalidades implica na modificação da classe por si só. Ao fazer tal mudança, você estará propenso a fazer com que outras partes do seu código não funcionem mais.

**Exemplo de violação do OCP:**

```c#
public class Arquivo
{
    // outros métodos...
}
 
public class ArquivoWord : Arquivo
{
    public void GerarDocX()
    {
        // ...
    }
}
 
public class ArquivoPdf : Arquivo
{
    public void GerarPdf()
    {
        // ...
    }
}
 
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

No exemplo acima temos classes que geram arquivos do Word e PDFs. E temos uma classe `GeradorDeArquivos` que recebe uma lista de arquivos e cria um arquivo novo no formato especificado.

Suponha agora que tenhamos que estender a aplicação para dar suporte a arquivos em outro formato, como, por exemplo, arquivos texto (.txt) e precisamos que o método `GerarArquivos` também gere arquivos no novo formato.

Além da nova classe, que poderíamos chamar de `ArquivoTxt`, seríamos obrigados a alterar o método `GerarArquivos` para atender a esse requisito. O mais óbvio seria colocar mais um `else if`, checando pelo novo tipo (txt) e chamando o método correspondente: `((ArquivoTxt)arquivo).GerarTxt()`. Esse padrão seguiria sucessivamente a cada necessidade de um novo formato de arquivo.

Sendo assim, podemos afirmar que o método `GerarArquivos` não está em conformidade com o OCP para mudanças do tipo "preciso de um novo formato de arquivo", uma vez que o método não está fechado para essas mudanças.

Alterar uma classe já existente para adicionar um novo comportamento, corremos um sério risco de introduzir *bugs* em algo que já estava funcionando.

**Código alterado para atender o OCP:**

```c#
public abstract class Arquivo
{
    public abstract void Gerar();
}
 
public class ArquivoWord : Arquivo
{
    public override void Gerar()
    {
        // ...
    }
}
 
public class ArquivoPdf : Arquivo
{
    public override void Gerar()
    {
        // ...
    }
}
 
public class ArquivoTxt : Arquivo
{
    public override void Gerar()
    {
        // ...
    }
}
 
public class GeradorDeArquivos
{
   public void GerarArquivos(IList<Arquivo> arquivos)
   {
      foreach(var arquivo in arquivos)
        arquivo.Gerar();
   }
}
```

**Mudanças no código:**

1. Tornamos `Arquivo` uma classe abstrata, uma vez que não temos intenção de instanciá-la.
1. Criamos um método abstrato para geração de arquivos na classe base chamado de `Gerar`.
1. Fizemos com que as classes derivadas implementem o método `Gerar`.
1. Introduzimos nosso novo requisito, ou seja, um novo tipo de arquivo `ArquivoTxt`, o qual também herda de `Arquivo` e implementa `Gerar`.
1. Por fim, eliminamos as checagens de tipo do método `GerarArquivos` e passamos a usar **polimorfismo**.

Agora, sempre que surgir um novo formato de arquivo, nós conseguimos estender o comportamento de `GerarArquivos` (ele saberá gerar esse novo arquivo) sem precisarmos alterá-lo. Apenas criamos o arquivo novo e pronto.

## Conclusão

O Princípio do Aberto/Fechado (OCP) nos atenta para a aplicação de abstrações e polimorfismo, de forma consciente, garantindo que tenhamos um software mais flexível e, portanto, mais fácil de ser mantido.

## Referências

- <https://robsoncastilho.com.br/2013/02/23/principios-solid-principio-do-abertofechado-ocp/>