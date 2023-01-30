# Introdução

A GoLang, também conhecida apenas como Go é uma linguagem de programação criada pela Google e lançada em código livre em novembro de 2009. É uma linguagem compilada e focada em produtividade e programação concorrente.

Algumas funcionalidades ausentes em Go são tratamento de exceção, herança, assert e sobrecarga de métodos. Os autores argumentam abertamente contra asserções e defendem a omissão de herança de tipos em favor da eficiência. Ao contrário de Java, vetores associativos são parte intrínseca da linguagem, assim como strings.

## Instalação (Linux)

1. Segundo [link](https://go.dev/doc/install), baixar a última versão em <https://go.dev/dl> e configurar as variáveis de ambiente para o local onde se encontra o diretório `go`. Geralmente o local `/usr/local/go` é o mais recomendado.

1. No arquivo `~/.profile` adicionar a linha:

    ```bash
    export PATH=$PATH:/usr/local/go/bin
    ```

1. Recarregar o arquivo `.profile`

    ```bash
    source ~/.profile
    ```

1. Verificar versão instalada:

    ```bash
    go version
    ```

## Gerenciador de dependências

Go usa Módulos Go para configurar as dependências de pacotes para a importação de recursos. Os módulos Go são arquivos de configuração `.mod` colocados no seu diretório de pacotes que dizem ao compilador de onde importar os pacotes. Além das dependências, é nesse arquivo onde o Go adiciona o nome do seu package e a versão do Go utilizada.

```go
// go.mod
module github.com/example/cmd

go 1.19

require gorm.io/gorm
replace github.com/example/logging => ../logging
```

A primeira linha desse arquivo diz ao compilador que o nosso pacote chama `cmd` e tem o caminho de arquivo `$GOPATH/src/` + `github.com/example/cmd`. A segunda linha diz a versão Go utilizada. A terceira linha diz ao compilador que o pacote `gorm` está localizado em `gorm.io/gorm`. E a quarta linha diz ao compilador que o pacote `github.com/example/logging` pode ser encontrado localmente em disco, no diretório `../logging`.

### Comando "go mod init"

Um arquivo `.mod` pode ser criado usando o comando abaixo:

```bash
go mod init <caminho-importacao>

# Exemplo: go mod init github.com/example
```

O que `go mod init` fará é criar o arquivo `go.mod` no diretório que será a raiz do módulo e descrever qual o caminho base, ou seja, ao importar nossos pacotes usaremos o caminho base `github.com/example`. Abaixo o contéudo do arquivo `go.mod`:

```go
// go.mod
module github.com/example

go 1.19
```

### Comando "go get"

Para baixar um pacote e adicionar ao `go.mod` podemos usar o comando:

```bash
go get <caminho-pacote>

# Exemplo: go get github.com/gorilla/mux
```

O que `go get` fará é baixar o código fonte do GitHub e colocar os arquivos em `$GOPATH/src/github.com/gorilla/mux`.

Todos os pacotes são importados através de seu caminho completo começando de `$GOPATH/src`, o que explica a necessidade de definir o `$GOPATH` durante a instalação do Go. A única exceção para esta regra é a `stdib` que é importada de `$GOROOT/src`. Abaixo o contéudo do arquivo `go.mod`:

```go
// go.mod
module github.com/example

go 1.19

require github.com/gorilla/mux v1.5.2
```

### Comando "go install"

Esse comando compila todos os pacotes e gera os arquivos executáveis movendo-os para `$GOPATH/pkg` ou `$GOPATH/bin`.

```bash
go install
```

### Comando "go mod tidy"

Para atualizar o `go.mod`, ou seja, adicionar as dependências e remover dependências não usadas, podemos usar o comando:

```bash
go mod tidy
```

### Arquivo go.sum

O arquivo `go.sum` é responsável por manter todas as informações para checksum das dependências utilizadas no projeto.

Todos os packages que o Go referencia no `go.sum` foram adicionados ao seu diretório `$GOPATH/pkg/mod`. Com esse cache em mãos, o Go não precisa fazer download das dependências toda vez que você executar o projeto.

Esse cache é global, ou seja, serão compartilhados por todos os projetos que você tiver ou criar na sua máquina.

### Vendoring

Como podemos compartilhar um código e garantir que todos tenham as dependências baixas e, o mais importante, a versão correta de cada dependência? Isso pode ser feito através de __vendoring__, que basicamente permite que aplicações Go utilizem dependências não só de `$GOPATH/src`, mas também de um diretório chamado `vendor` dentro de cada projeto.

O compilador do Go primeiramente procurará pelos pacotes dentro do diretório `vendor`, antes de procurar em `$GOPATH`.

Você pode executar o comando abaixo para gerar uma nova pasta chamada `vendor`:

```bash
go mod vendor
```

### godoc

Iniciando um servidor godoc da aplicação:

```bash
~/go/bin/godoc -http=:6060
```

Abrir no navegador: `http://localhost:6060/pkg/<nome-modulo>`

## Referências

- <https://pt.wikipedia.org/wiki/Go_(linguagem_de_programa%C3%A7%C3%A3o)>
- <https://www.digitalocean.com/community/tutorials/understanding-package-visibility-in-go-pt>
