# Markdown (GitHub Flavored Markdown)

[TOC]

## Links

- [Writing on GitLab](https://gitlab.com/help/user/markdown)
- [Writing on GitHub](https://help.github.com/categories/writing-on-github/)

## Cabeçalhos

```md
# Cabeçalho nível 1 (maior)
## Cabeçalho nível 2
###### Cabeçalho nível 6 (menor)
```

## Estilos

```md
Negrito: **Texto em negrito**, __Texto em negrito__

Itálico: *Texto em itálico*, _Texto em itálico_

Tachado: ~~Texto tachado~~

Negrito e itálico: **_Texto negrito e itálico_**, _**Texto negrito e itálico**_
```

## Citações de texto

```md
Como diria o meu avô:
> Água mole, pedra dura, tanto bate até que fura.
```

## Citações de código

```md
`# Texto sem formatação`
```

```md
`` `
Exemplo de **código**
que pode ser usado em *blocos*
`` `
```

## Links

Somente link

```md
<http://localhost:3000>
```

Texto com link

```md
[Link para cabeçalho](#nome-do-cabecalho)
[Link para linha](caminho/arquivo#L13)
[Link relativo](caminho/arquivo.md)
[Link relativo](./caminho/arquivo.md)
[Link absoluto](/caminho/arquivo.md)
[Link com texto](https://www.dominio.com)

```

## Listas

```md
- Primeiro
- Segundo
    - Primeiro filho
    - Segundo filho
        - Primeiro neto
```

## Listas numeradas

```md
1. Primeiro
    - Filho
1. Segundo
1. Terceiro
```

Ou usar a notação mais antiga

```md
1. Primeiro
    - Filho
2. Segundo
3. Terceiro
```

Se usar o modo antigo  com `markdownlint` habilitado, é necessário mudar a configuração para:

```json
"MD029": {
    "style":"ordered"
}
```

## Lista de tarefas

```md
- [x] Tarefa 1
- [ ] Tarefa 2
- [ ] Tarefa 3
- [ ] \(Se iniciar com parênteses tem que escapar)
```

## Parágrafos

Basta deixar uma linha em branco

```md
Parágrafo 1

Parágrafo 2
```

## Escapar

```md
Basta usar a barra \*invertida\* para escapar
```

## Quebra de Linha

```md
Basta quebrar a linha (ENTER) logo após a barra \*invertida\*
```

Evitar a separação de palavras durante uma quebra de linha.

```md
<nobr>Não serei quebrado</nobr>
```

## Tabelas

```md
| Primeiro cabeçalho | Segundo cabeçalho |
| ------------------ | ----------------- |
| Conteúdo célula    | Conteúdo célula   |
| Conteúdo célula    | Conteúdo célula   |
```

Tabela com formatação

```md
| Primeiro cabeçalho  | Segundo cabeçalho |
| --- | --- |
| **célula negrito**  | \| Usando pipe    |
| *célula itálico*    | Conteúdo célula   |
```

Tabela com alinhamento

```md
| Alinhado na esquerda | Alinhado no centro | Alinhado na direita |
| :--- | :---: | ---: |
| Conteúdo             | Conteúdo           | Conteúdo            |
| Conteúdo             | Conteúdo           | Conteúdo            |
```

## Vídeo (GitLab)

```md
![Amostra Vídeo](caminho/video.mp4)
```

## Imagem (GitLab)

```md
![alt text](caminho/img.png)
```

## Matemática (GitLab)

```md
`` `math
a^2+b^2=c^2
`` `
```

## Html

```md
<dl>
  <dt>Definition list</dt>
  <dd>Is something people use sometimes.</dd>

  <dt>Markdown in HTML</dt>
  <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
</dl>
```

## Linha horizontal

Três ou mais caracteres iguais a:

```md
***
---
___
```

## Notas rodapé

```md
Este texto possui uma anotação de rodapé.[^1]
[^1]: Este é meu rodapé.
```

## Referências (GitLab)

|Entrada               |Referência|
|--- |--- |
|@user_name            |specific user|
|@group_name           |specific group|
|@all                  |entire team|
|#123                  |issue|
|!123                  |merge request|
|$123                  |snippet|
|~123                  |label by ID|
|~bug                  |one-word label by name|
|~"feature request"    |multi-word label by name|
|%123                  |project milestone by ID|
|%v1.23                |one-word milestone by name|
|%"release candidate"  |multi-word milestone by name|
|9ba12248              |specific commit|
|9ba12248...b19a04f5|commit range comparison|
|[README](doc/README)  |repository file references|
|[README](doc/README#L13)|repository file line references|

Referência Cross-project

|Entrada                              |Referência|
|--- |--- |
|namespace/project#123                |issue|
|namespace/project!123                |merge request|
|namespace/project%123                |project milestone|
|namespace/project$123                |snippet|
|namespace/project@9ba12248           |specific commit|
|namespace/project@9ba12248...b19a04f5|commit range comparison|
|namespace/project~"Some label"       |issues with given label|

Referência Cross-project (Anotação curta)

|Entrada                      |Referência|
|--- |--- |
|project#123                  |issue|
|project!123                  |merge request|
|project%123                  |project milestone|
|project$123                  |snippet|
|project@9ba12248             |specific commit|
|project@9ba12248...b19a04f5  |commit range comparison|
|project~"Some label"         |issues with given label|

## Ferramentas

### Conversores

Converter tabela HTML para tabela markdown

<http://markdowntables.mrvautin.com>

Converter geral para markdown

<https://pandoc.org/try>

### Formatação

Formatar tabela markdown de modo mais legível

<http://markdowntable.com>

### Visualizadoreres

Visualizador markdown

<https://dillinger.io>

## Links
- Representação de comandos de prompt
    - <http://docopt.org/>
    - <https://developers.google.com/style/code-syntax>

- Convert texto em tabela
    - <https://jakebathman.github.io/Markdown-Table-Generator>

- Gerador de tabela
    - <https://www.tablesgenerator.com/markdown_tables>
