# Package

Ao criar um pacote em Go, normalmente, o objetivo final é tornar o pacote acessível para uso por outros desenvolvedores. Ao importar o pacote, seu código poderá servir como o bloco de construção para outras ferramentas mais complexas. No entanto, apenas certos pacotes estão disponíveis para importação. Isso é determinado pela visibilidade do pacote.

Ao contrário do que ocorre em outras linguagens de programação, que usam modificadores de acesso, como `public`, `private` ou `protected` para especificar o escopo, a linguagem Go determina se um item é exportado (em inglês, _exported_) e não exportado (em inglês, _unexported_) pela forma como é declarado. Exportar um item neste caso torna ele visible fora do pacote atual. Caso não seja exportado, ele fica apenas visível e utilizável de dentro do pacote em que foi definido.

Esta visibilidade externa é controlada colocando-se a primeira letra do item declarado em maiúscula. Todas as declarações, tais como Types, Variables, Constants, Functions etc., que começam com uma letra maiúscula, ficam visíveis fora do pacote atual.

```go
package greet

import "fmt"

var Greeting string

func Hello(name string) string {
    return fmt.Sprintf(Greeting, name)
}
```

Esse código declara que ele está no pacote `greet`. Depois, ele declara dois símbolos, uma variável chamada `Greeting` e uma função chamada `Hello`. Como ambas começam com uma letra maiúscula, as duas são _exported_ e disponibilizadas para qualquer programa exterior.

## Escopo

O escopo de um pacote é o diretório no qual o arquivo se encontra. Arquivos de um mesmo diretório que declaram o mesmo pacote serão considerados arquivos desse pacote. Mesmo que um arquivo em um subdiretório declarar o mesmo nome de pacote, este será considerado outro pacote.

## Referência

- <https://www.digitalocean.com/community/tutorials/understanding-package-visibility-in-go-pt>
