# Swagger

O Swagger é uma especificação aberta para definição de APIs REST.

Um documento do Swagger é a API REST equivalente de um documento WSDL para um serviço da web baseado em SOAP.

O documento do Swagger especifica a lista de recursos que estão disponíveis na API REST e as operações que podem ser chamadas nesses recursos. O documento Swagger também especifica a lista de parâmetros para uma operação, incluindo o nome e o tipo dos parâmetros, se os parâmetros são necessários ou opcionais e informações sobre valores aceitáveis para esses parâmetros. Além disso, o documento Swagger pode incluir um Esquema JSON que descreve a estrutura do corpo da solicitação que é enviada para uma operação em uma API REST e o esquema JSON descreve a estrutura de quaisquer corpos de resposta retornados de uma operação.

Os documentos swagger devem estar em qualquer formato JSON com uma extensão de arquivo .json ou formato YAML com uma extensão de arquivo .yaml ou .yml.

Documentação oficial em: <https://goswagger.io>

## Instalação

```bash
download_url=$(curl -s https://api.github.com/repos/go-swagger/go-swagger/releases/latest | \
  jq -r '.assets[] | select(.name | contains("'"$(uname | tr '[:upper:]' '[:lower:]')"'_amd64")) | .browser_download_url')

sudo curl -o /usr/local/bin/swagger -L'#' "$download_url"

sudo chmod +x /usr/local/bin/swagger
```

O Swagger criará um link para `/usr/local/bin/swagger` ou seja será acessado usando apenas o comando `swagger`.

[Veja outras formas de instalação](https://goswagger.io/install.html)

## Configuração

### Arquivo base

Use o arquivo `main` ou crie um arquivo `doc.go` na raiz do projeto com os metas para descrever o projeto.

[Veja mais](https://goswagger.io/generate/spec/meta.html)

??? example "Exemplo"

    ```go
    // Learning Golang
    //
    // Starting my first API in Golang
    //
    // Terms Of Service:
    //
    // there are no TOS at this moment, use at your own risk we take no responsibility
    //
    //  Schemes: http, https
    //  Host: localhost:3000
    //  BasePath: /
    //  Version: 0.0.1
    //  License: MIT http://opensource.org/licenses/MIT
    //  Contact: Gustavo Araújo<kustavo@gmail.com>
    //
    //  Consumes:
    //  - application/json
    //
    //  Produces:
    //  - application/json
    //
    //  Security:
    //  - api_key:
    //
    //  SecurityDefinitions:
    //  api_key:
    //      type: apiKey
    //      name: Authorization
    //      in: header
    // swagger:meta
    package main
    ```

## Comandos

Gerar documentação varrendo os arquivos do projeto:

```bash
swagger generate spec -o ./swagger.json --scan-models
```

Validar documentação:

```bash
swagger validate ./swagger.json
```

Iniciar o servidor swagger:

```bash
swagger serve -F swagger ./swagger.json 
```

Iniciar o servidor swagger em modo documento

```bash
swagger serve ./swagger.json 
```

Gerar documentação em Markdown

```bash
swagger generate markdown --output swagger.mode
```

## Referências

- <https://github.com/go-swagger/go-swagger>
- <https://www.ibm.com/docs/pt-br/integration-bus/10.0?topic=ssmkhh-10-0-0-com-ibm-etools-mft-doc-bi12018--htm>
