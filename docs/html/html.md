# HTML

## Introdução

HTML (HyperText Markup Language) é uma linguagem de marcação utilizada na construção de páginas na Web. Documentos HTML podem ser interpretados por navegadores. HTML é o bloco de construção mais básico da web. Define o significado e a estrutura do conteúdo da web. Outras tecnologias além do HTML geralmente são usadas para descrever a aparência/apresentação (CSS) ou a funcionalidade/comportamento (JavaScript) de uma página da web.

"Hipertexto" refere-se aos links que conectam páginas da Web entre si, seja dentro de um único site ou entre sites. Links são um aspecto fundamental da web. Ao carregar conteúdo na Internet e vinculá-lo a páginas criadas por outras pessoas, você se torna um participante ativo na world wide web.

O HTML usa "Marcação" para anotar texto, imagem e outros conteúdos para exibição em um navegador da Web. A marcação HTML inclui "elementos" especiais, como `<head>`, `<title>`, `<body>`, `<header>`, `<footer>`, `<article>`, `<section>`, `<p>`, `<div>`, `<span>`, `<img>`, `<aside>`, `<audio>`, `<canvas>`, `<datalist>`, `<details>`, `<embed>`, `<nav>`, `<output>`, `<progress>`, `<video>`, `<ul>`, `<ol>`, `<li>` e muitos outros.

Um elemento HTML é separado de outro texto em um documento por "tags", que consistem no nome do elemento entre `<` e `>`. O nome de um elemento dentro de uma tag é insensível a maiúsculas e minúsculas. Isto é, pode ser escrito em maiúsculas, minúsculas ou um mistura. Por exemplo, a tag `<title>` pode ser escrita como `<Title>`, `<TITLE>` ou de qualquer outra forma.

## DOM

O Modelo de Objeto de Documento (DOM - Document Object Model) é uma interface de programação para documentos HTML, XML e SVG. Ele fornece uma representação estruturada do documento como uma árvore. O DOM define métodos que permitem acesso à árvore, para que eles possam alterar a estrutura, estilo e conteúdo do documento. O DOM fornece uma representação do documento como um grupo estruturado de nós e objetos, possuindo várias propriedades e métodos. Os nós também podem ter manipuladores de eventos que lhe são inerentes, e uma vez que um evento é acionado, os manipuladores de eventos são executados. Essencialmente, ele conecta páginas web a scripts ou linguagens de programação.

Embora o DOM seja frequentemente acessado usando JavaScript, não é uma parte da linguagem JavaScript. Ele também pode ser acessado por outras linguagens.

Os padrões W3C DOM e WHATWG DOM são implementados na maioria dos navegadores modernos. Muitos navegadores estendem o padrão; portanto, é necessário ter cuidado ao usá-los na Web, onde os documentos podem ser acessados por vários navegadores com diferentes DOMs.

Por exemplo, o DOM padrão especifica que o método `getElementsByTagName` no código abaixo deve retornar uma lista de todos os elementos `<p>` no documento:

```js
var paragraphs = document.getElementsByTagName("p");
// paragraphs[0] é o primeiro elemento <p>
// paragraphs[1] é o segundo elemento <p>
// ...
alert(paragraphs[0].nodeName);
```

Todas as propriedades, métodos e eventos disponíveis para manipular e criar páginas da Web são organizados em objetos (por exemplo, o objeto de `document` que representa o próprio documento, o objeto de `table` que implementa a Interface especial `DOM HTMLTableElement` para acessar tabelas HTML e assim por diante).

## Referências

- <https://pt.wikipedia.org/wiki/HTML>
- <https://developer.mozilla.org/pt-BR/docs/Web/HTML>
