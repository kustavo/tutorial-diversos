# Visual Studio

[TOC]

[[_TOC_]]

## Introdução

O Visual Studio Code é um editor de código-fonte desenvolvido pela Microsoft para Windows, Linux e macOS. Ele inclui suporte para depuração, controle Git incorporado, realce de sintaxe, complementação inteligente de código, snippets e refatoração de código. Ele também é customizável, fazendo com que os usuários possam mudar o tema do editor, teclas de atalho e preferências. Ele é um software livre e de código aberto, apesar do download oficial estar sob uma licença proprietária.

## Configuração

As configurações são armazenadas no arquivo `~/.config/Code/User/settings.json`. As últimas configurações usadas foram:

```json
{
  "editor.formatOnSave": true,
  "editor.rulers": [
      100
  ],
  "editor.suggestSelection": "first",
  "files.trimFinalNewlines": true,
  "files.exclude": {
      "**/.classpath": true,
      "**/.project": true,
      "**/.settings": true,
      "**/.factorypath": true
  }
}
```

## Debug

### Chrome

Executar o comando:

```sh
google-chrome --remote-debugging-port=9222
```

Instalar a extensão `Debugger for Chrome` e modificar o a rquivo `launch.json`:

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
| devtools.debugger.force-local       | false	(Somente para firefox remoto. Usar parâmetro `host`) |

Executar o comando:

```sh
firefox -start-debugger-server -no-remote
```

Instalar a extensão `Debugger for Firefox` e modificar o a rquivo `launch.json`:

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

Gera tabelas de conteúdos (*TOC - Table of Contents*) para o markdown. Cria sumários e mumeração dos cabeçalhos.

#### Configurações {ignore=true}

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

#### Configurações {ignore=true}

```json
"markdownlint.config": {
        "MD033": {
            "allowed_elements": [
                "div",
                "span"
            ]
        }
    },
```

### Markdown All in One

`yzhang.markdown-all-in-one`

Atalhos para o markdown.

### Markdown Shortcuts

`mdickin.markdown-shortcuts`

Atalhos para o markdown com criador de tabelas.

### Markdown Table Prettifier

`darkriszty.markdown-table-prettify`

Formator de tabelas.

#### Configurações {ignore=true}

```json
"markdownShortcuts.italics.marker": "*",
"markdownShortcuts.bullets.marker": "-"
```

### Markdown Preview Enhanced

`shd101wyy.markdown-preview-enhanced`

Visualizador markdown com opção de TOC.

#### Configurações {ignore=true}

Ignorar cabeçalho no TOC:

```markdown
# Cabeçalho {ignore=true}
```

Arquivo de estilos: `~/.mume/style.less`

```css
/* Please visit the URL below for more information: */
/*   https://shd101wyy.github.io/markdown-preview-enhanced/#/customize-css */ 

.markdown-preview.markdown-preview {

  text-align: justify;

  h1 {
    font-size: 34px;
  }
  
  h2 {
    margin-top: 130px;
    border-bottom: 1px solid #ccc;
    font-size: 28px;
  }

  h3 {
    font-size: 20px;
  }

  h4 {
    font-size: 16px;
  }

  table {
    border-collapse: collapse;
    border-spacing: 0;
    font-size: 100%;
    font: inherit; 
  }

  table th {
    font-weight: bold;
    border: 1px solid #ccc;
    padding: 6px 13px;
  }

  table td {
    border: 1px solid #ccc;
    padding: 6px 13px; 
  }

  table tr {
    border-top: 1px solid #ccc;
    background-color: #fff; 
  }

  table tr:nth-child(2n) {
    background-color: #f8f8f8;
  }

  pre {
    background-color: #f8f8f8cb;
    border: 1px solid #ccc;
    font-size: 14px;
    line-height: 19px;
    overflow: auto;
    border-radius: 3px;
    margin: 25px 0; 
  }
  
  div.importante:before {
    content: "Importante: ";
    font-weight: bold;
  }

  div.importante {
    background-color: #e4f3ff;
    border: 1px solid #a8c1d6;
    color: #304a68;
    padding: 6px 10px;
    border-radius: 3px;
    margin: 20px 0;
  }

  span.mais:before {
    content: "Veja mais em: ";
    font-weight: bold;
  }

  span.mais {
    background-color: #e4f3ff;
    border: 1px solid #a8c1d6;
    padding: 6px 10px;
    border-radius: 3px;
  }

  div.imagem {
    margin: 12px 10px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
  }
}
```

### Code Spell (Português)

`streetsidesoftware.code-spell-checker-portuguese`

Corretor ortográfico.

#### Configurações {ignore=true}

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

### Debugger for Java

`vscjava.vscode-java-debug`

Debug e build para java.

#### Configurações {ignore=true}

Arquivo de configuração: `<worspace>/.vscode/launch.json`

```json
{
    "configurations": [
        {
            "type": "java",
            "name": "Current File",
            "request": "launch",
            "mainClass": "${file}"
        }
    ]
}
```
