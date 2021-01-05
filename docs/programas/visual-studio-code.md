# Visual Studio

## Introdução

O Visual Studio Code é um editor de código-fonte desenvolvido pela Microsoft para Windows, Linux e macOS. Ele inclui suporte para depuração, controle Git incorporado, realce de sintaxe, complementação inteligente de código, snippets e refatoração de código. Ele também é customizável, fazendo com que os usuários possam mudar o tema do editor, teclas de atalho e preferências. Ele é um software livre e de código aberto, apesar do download oficial estar sob uma licença proprietária.

## Configuração

As configurações são armazenadas no arquivo:

```bash
# linux
~/.config/Code/User/settings.json
```

## Debug

### Chrome

Iniciar instância do Chrome em modo debug.

```bash
google-chrome --remote-debugging-port=9222
```

Instalar a extensão `Debugger for Chrome` e modificar o arquivo `launch.json`:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "chrome",
      "request": "attach",
      "sourceMaps": true,
      "port": 9222,
      "name": "Attach Chrome",
      "urlFilter": "https://localhost:<porta>/*",
      "webRoot": "${workspaceFolder}/<diretorio-base>",
    }
  ]
}
```

Outra forma mais simples, sem precisar executar via terminal. Entretanto sempre irá abrir uma nova janela no navegador.

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "chrome",
      "request": "launch",
      "sourceMaps": true,
      "name": "Launch Chrome",
      "url": "https://localhost:<porta>",
      "webRoot": "${workspaceFolder}/<diretorio-base>",
    }
  ]
}
```

### Firefox

Modificar os parâmetros do firefox através da url `about:config`.

| Parâmetro | Valor |
| --- | --- |
| devtools.debugger.remote-enabled    | true  |
| devtools.chrome.enabled             | true  |
| devtools.debugger.prompt-connection | false |
| devtools.debugger.force-local       | false (Somente para firefox remoto. Usar parâmetro `host`) |

Iniciar instância do Firefox em modo debug:

```bash
firefox -start-debugger-server -no-remote
```

Instalar a extensão `Debugger for Firefox` e modificar o arquivo `launch.json`:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Attach Firefox",
      "type": "firefox",
      "request": "attach",
      "url": "https://localhost:<porta>",
      "webRoot": "${workspaceFolder}/<diretorio-base>",
    }
  ]
}
```

Outra forma mais simples, sem precisar executar via terminal. Entretanto sempre irá abrir uma nova janela no navegador.

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Launch Firefox",
      "type": "firefox",
      "request": "launch",
      "url": "https://localhost:<porta>",
      "webRoot": "${workspaceFolder}/<diretorio-base>",
    }
  ]
}
```

## Extensões

### Markdown TO

`(AlanWalk.markdown-toc)`

Gera tabelas de conteúdos (*TOC - Table of Contents*) para o markdown. Cria sumários e numeração dos cabeçalhos.

#### Configurações

Numeração a partir do cabeçalho de nível 2.

No TOC gerado

```markdown
<!-- TOC depthFrom:2 -->
    - Lista gerada
<!-- /TOC -->
```

Ou no arquivo de configuração.

```json
"markdown-toc.depthFrom": 2
```

### Markdownlint

`(DavidAnson.vscode-markdownlint)`

Define um conjunto de padrões para manter o código markdown padronizado.

#### Configurações

```json
"markdownlint.config": {
  "MD033": {
    "allowed_elements": [
      "figure",
      "img",
      "figcaption"
    ]
  }
},
```

### Code Spell (Português)

`(streetsidesoftware.code-spell-checker-portuguese)`

Corretor ortográfico.

#### Configurações

```json
    "cSpell.language": "en,pt",
    "cSpell.ignoreRegExpList": [
        "/^\\s*```(.|\\n)+?^\\s*```/gm"
    ],
    "cSpell.enabledLanguageIds": [
        "markdown"
    ],
    "cSpell.userWords": [
        "Anônima",
        "aceitos",
        "anônimas",
        "customizável",
        "encapsuladora",
        "mumeração",
        "refatoração",
        "subrotina",
        "subrotinas"
    ]
```

## Referências

- <https://pt.wikipedia.org/wiki/Visual_Studio_Code>
