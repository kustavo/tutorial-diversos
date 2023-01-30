# Convenções

## Formatação

Go adota uma abordagem incomum e deixa a máquina cuidar da maioria dos problemas de formatação. O programa `gofmt` lê um programa Go e emite o código fonte em um estilo padrão de recuo e alinhamento vertical, retendo e, se necessário, reformatando os comentários. Todo o código Go nos pacotes padrão foi formatado com gofmt.

Alguns detalhes de formatação:

- __Recuo__: Usamos tabs para recuo e `gofmt` as coloca por padrão.
- __Comprimento da linha__: não impõe limite de comprimento.
- __Parênteses__: Estruturas de controle (`if`, `for`, `switch`) não possuem parênteses em sua sintaxe.

## Comentários

Go fornece comentários de bloco `/* */` e comentários de linha `//`.

O programa (e servidor web) `godoc` processa os arquivos de origem Go para extrair a documentação sobre o conteúdo do pacote. Os comentários que aparecem antes das declarações _top-level_, sem novas linhas intermediárias, são extraídos junto com a declaração para servir como texto explicativo para o item. A natureza e o estilo desses comentários determinam a qualidade da documentação que o `godoc` produz.

Todo pacote deve ter um comentário de pacote, um comentário de bloco precedendo a cláusula do pacote. Para pacotes com vários arquivos, o comentário do pacote só precisa estar presente em um arquivo, e qualquer um servirá. O comentário do pacote deve apresentar o pacote e fornecer informações relevantes para o pacote como um todo. Ele aparecerá primeiro na página do `godoc` e deve configurar a documentação detalhada a seguir.

```go
/*
Package regexp implements a simple library for regular expressions.

The syntax of the regular expressions accepted is:

    regexp:
        concatenation { '|' concatenation }
    concatenation:
        { closure }
    closure:
        term [ '*' | '+' | '?' ]
    term:
        '^'
        '$'
        '.'
        character
        '[' [ '^' ] character-ranges ']'
        '(' regexp ')'
*/
package regexp
```

Se o pacote for simples, o comentário do pacote pode ser breve.

```go
// Package path implements utility routines for
// manipulating slash-separated filename paths.
```

Os comentários não precisam de formatação extra, como banners de estrelas. A saída gerada pode nem ser apresentada em uma fonte de largura fixa, portanto, não dependa do espaçamento para alinhamento - `godoc`, como `gofmt`, cuida disso. Os comentários são texto simples não interpretado, portanto, HTML e outras anotações como _this_ serão reproduzidos literalmente e não devem ser usados. Um ajuste que o `godoc` faz é exibir o texto recuado em uma fonte de largura fixa, adequada para trechos de programa. O comentário do pacote para o pacote `fmt` usa isso com bons resultados.

Dentro de um pacote, qualquer comentário imediatamente anterior a uma declaração de nível superior serve como um comentário doc para essa declaração. Cada nome exportado (em maiúsculas) em um programa deve ter um comentário doc.

Os comentários de documentos funcionam melhor como frases completas, que permitem uma ampla variedade de apresentações automatizadas. A primeira frase deve ser um resumo de uma frase que começa com o nome que está sendo declarado.

```go
// Compile parses a regular expression and returns, if successful,
// a Regexp that can be used to match against text.
func Compile(str string) (*Regexp, error) {
```

Se cada comentário doc começar com o nome do item que descreve, você pode usar o subcomando doc da ferramenta go e executar a saída por meio do `grep`. Imagine que você não conseguia se lembrar do nome "Compile", mas estava procurando a função de análise para expressões regulares, então você executou o comando,

```bash
go doc -all regexp | grep -i parse
```

Se todos os comentários doc no pacote começassem com "Esta função...", o `grep` não o ajudaria a lembrar o nome. Mas como o pacote inicia cada comentário de documento com o nome, você veria algo assim, que lembra a palavra que você está procurando.

```bash
$ go doc -all regexp | grep -i parse

$ Compile parses a regular expression and returns, if successful, a Regexp
MustCompile is like Compile but panics if the expression cannot be parsed.
parsed. It simplifies safe initialization of global variables holding
```

A sintaxe de declaração do Go permite o agrupamento de declarações. Um único comentário doc pode introduzir um grupo de constantes ou variáveis ​​relacionadas. Uma vez que toda a declaração é apresentada, tal comentário pode muitas vezes ser superficial.

```go
// Error codes returned by failures to parse an expression.
var (
    ErrInternal      = errors.New("regexp: internal error")
    ErrUnmatchedLpar = errors.New("regexp: unmatched '('")
    ErrUnmatchedRpar = errors.New("regexp: unmatched ')'")
    ...
)
```

O agrupamento também pode indicar relacionamentos entre itens, como o fato de que um conjunto de variáveis ​​ser protegido por um `mutex`.

```go
var (
    countLock   sync.Mutex
    inputCount  uint32
    outputCount uint32
    errorCount  uint32
)
```

## Nomenclatura

- Um nome de função não pode começar com um número.
- A visibilidade de um nome fora de um pacote é determinada pelo fato de seu primeiro caractere ser maiúsculo.
- Um nome deve começar com uma letra e pode ter qualquer número de letras e números adicionais.
- Se um nome consiste em várias palavras, cada palavra após a primeira deve ser maiúscula. Não use sublinhados (ex: empName, EmpAddress). 
- Nomes são _case-sensitive_, ou seja, diferenciam maiúsculas de minúsculas (ex: carro, Carro e CARRO são três variáveis ​​diferentes).
- Para acrônimos como API, HTTP, etc. ou nomes como ID e DB. Convencionalmente, mantemos essas palavras em sua forma original. (ex: userID, productAPI)

### Nomenclatura de pacotes

Por convenção, os pacotes recebem nomes com letras minúsculas e uma única palavra; não deve haver necessidade de sublinhados ou mixedCaps. Erre no lado da brevidade, já que todos que usarem seu pacote estarão digitando esse nome. E não se preocupe com colisões a priori. O nome do pacote é apenas o nome padrão para importações; ele não precisa ser exclusivo em todo o código-fonte e, no caso raro de uma colisão, o pacote importador pode escolher um nome diferente para usar localmente.

Outra convenção é que o nome do pacote é o nome base de seu diretório de origem; o pacote em `src/encoding/base64` é importado como `encoding/base64`, mas tem o nome `base64`.

### Getters

Go não oferece suporte automático para `getters` e `setters`. Não há nada de errado você mesmo fornecer `getters` e `setters`, e muitas vezes é apropriado fazê-lo, mas não é idiomático nem necessário colocar `Get` no nome do `getter`. Se você tem um campo chamado "owner" (minúsculas, não exportado), o método `getter` deve ser chamado "Owner" (maiúsculas, exportado), não "GetOwner". O uso de nomes em maiúsculas para exportação fornece o meio para discriminar o campo do método. Uma função `setter`, se necessário, provavelmente será chamada "SetOwner". Ambos os nomes são bem lidos na prática:

```go
owner := obj.Owner()
if owner != user {
    obj.SetOwner(user)
}
```

### Nomenclatura de interface

Por convenção, as interfaces de um método são nomeadas pelo nome do método mais um sufixo `-er` ou modificação semelhante para construir um substantivo de agente: _Reader_, _Writer_, _Formatter_, _CloseNotifier_ etc.

!!! note "Notas"
    A regra geral é `NomeMetodo + er = NomeInterface`. A parte complicada aqui é quando você tem uma interface com mais de um método. Nomear seguindo a convenção nem sempre será óbvio. Devo dividir a interface em várias interfaces com um único método? Acho que é uma decisão subjetiva que depende do caso.

## Pacotes especiais

### Pacote main

Todo programa executável deve conter um pacote chamado `main` e uma função chamada `main`. Depois que seu programa for compilado e, quando você quiser executá-lo, a função `main` deste pacote será a primeira função a ser chamada.

## Diretórios especiais

### Diretório internal

Colocar um pacote dentro de um diretório chamado `internal`, esconde ainda mais as estruturas internas do pacote, traz mais encapsulamento. Este pacote só poderá ser importado dos pacotes de seu diretório pai.

O diretório `internal` é usado para tornar pacotes específicos não importáveis.

### Diretório vendoring

Como podemos compartilhar um código e garantir que todos tenham as dependências baixas e, o mais importante, a versão correta de cada dependência? Isso pode ser feito através de __vendoring__, que é basicamente á uma funcionalidade que permite aplicações Go utilizar dependências não só de `$GOPATH/src`, mas também de um diretório chamado `vendor` dentro de cada projeto. O compilador do Go primeiramente procurará pelos pacotes dentro do diretório `vendor`, antes de procurar em `$GOPATH`.

## Organização de projeto

[Veja aqui](https://github.com/golang-standards/project-layout) uma proposta de Oganização de projeto bem aceita pela comunidade Go.

Outros exemplos de estruturas de projetos de código aberto:

- <https://github.com/kubernetes/kubernetes>
- <https://github.com/tsuru/tsuru>

## Referências

- <https://go.dev/doc/effective_go>
- <https://www.golangprograms.com/naming-conventions-for-golang-functions.html>
