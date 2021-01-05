# Introdução

JavaScript (frequentemente abreviado como JS) é uma linguagem de programação interpretada estruturada, de script em alto nível com tipagem dinâmica fraca e multiparadigma (protótipos, orientado a objeto, imperativo e, funcional).Juntamente com HTML e CSS, o JavaScript é uma das três principais tecnologias da World Wide Web. JavaScript permite páginas da Web interativas e, portanto, é uma parte essencial dos aplicativos da web.

É atualmente a principal linguagem para programação client-side em navegadores web. É também bastante utilizada do lado do servidor através de ambientes como o node.js.

Como uma linguagem multiparadigma, o JavaScript suporta estilos de programação orientados a eventos, funcionais e imperativos (incluindo orientado a objetos e prototype-based), apresentando recursos como fechamentos (closures) e funções de alta ordem comumente indisponíveis em linguagens populares como Java e C++. Possui APIs para trabalhar com texto, matrizes, datas, expressões regulares e o DOM, mas a linguagem em si não inclui nenhuma E/S, como instalações de rede, armazenamento ou gráficos, contando com isso no ambiente host em que está embutido.

Foi originalmente implementada como parte dos navegadores web para que scripts pudessem ser executados do lado do cliente e interagissem com o usuário sem a necessidade deste script passar pelo servidor, controlando o navegador, realizando comunicação assíncrona e alterando o conteúdo do documento exibido, porém os mecanismos JavaScript agora estão incorporados em muitos outros tipos de software host, incluindo servidores em servidores e bancos de dados da Web e em programas que não são da Web, como processadores de texto e PDF, e em tempo de execução ambientes que disponibilizam JavaScript para escrever aplicativos móveis e de desktop, incluindo widgets de área de trabalho.

Os termos Vanilla JavaScript e Vanilla JS se referem ao JavaScript não estendido por qualquer estrutura ou biblioteca adicional. Scripts escritos em Vanilla JS são códigos JavaScript simples.

## Página HTML

O JavaScript é inserido em uma página HTML de uma maneira similar ao CSS. Enquanto o CSS usa o elemento `<link>` para aplicar folhas de estilo externas e o elemento `<style>` para aplicar folhas de estilo internas, o JavaScript só precisa do elemento `<script>`. Existem três formas de inserir um JavaScript em uma página HTML: interno, inline ou externo.

### JavaScript interno

O código JavaScript é inserido entre as tags `<script>` e `</script>`:

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Exemplo JavaScript interno</title>
    <script>
        // Código Javascript aqui
        alert('Olá');
    </script>
  </head>
  <body>
    <p>Exemplo JavaScript interno</p>
  </body>
</html>
```

### JavaScript inline

O código JavaScript é escrito dentro do HTML, como por exemplo o evento `onclick` chamando a função `funcaoJs()` do código JavaScript:

```js
// Código JavaScript aqui
function funcaoJs() {
    alert('Olá');
}
```

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <script src="exemplo.js"></script>
    <title>Exemplo JavaScript inline</title>
  </head>
  <body>
    <button onclick="funcaoJs()">clique aqui</button>
  </body>
</html>
```

### JavaScript externo

O código do JavaScript está em um arquivo externo como por exemplo `exemplo.js`:

```js
// Código JavaScript aqui
alert('Olá');
```

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <script src="exemplo.js"></script>
    <title>Exemplo JavaScript externo</title>
  </head>
  <body>
    <p>Exemplo JavaScript externo</p>
  </body>
</html>
```

## Estratégias para o carregamento de scripts

Há um considerável número de problemas envolvendo o carregamento de scripts na ordem correta. Um problema comum é que todo o HTML de uma página é carregado na ordem em que ele aparece. Se você estiver usando Javascript para manipular alguns elementos da página (manipular o Document Object Model), seu código não irá funcionar caso o JavaScript for carregado e executado antes mesmo dos elementos HTML estarem disponíveis.

Para resolver esse problema, muitos desenvolvedores antigamente usavam o código JavaScript logo antes do fechamento do body (tag `</body>`). Atualmente, uma forma mais moderna é usar o event listener que é acionado somente quando o corpo do HTML está completamente carregado e pronto.

```js
document.addEventListener("DOMContentLoaded", function() {
  // Código JavaScript aqui
});
```

Para o JavaScript externo, podemos usar o atributo `defer` que informa ao browser para continuar renderizando o conteúdo HTML quando a tag `<script>` for encontrada e rodar o código JavaScript somente quando o DOM estiver completamente disponível. Os arquivos JavaScript serão executados na ordem que aparecem no arquivo HTML após o carregamento do DOM.

Essa estratégia é recomendada quando os scripts dependem de outros scripts ou do DOM completamente disponível.

```js
<script src="exemplo.js" defer></script>
<script src="exemplo2.js" defer></script>
```

Outra estratégia de carregamento para o JavaScript externo é usar o atributo `async`. Assim como o atributo `defer`, ele informa ao browser para continuar renderizando o conteúdo HTML quando a tag `<script>` for encontrada. Entretanto, o JavaScript será executado assim que baixado e não somente quando o DOM estiver completamente disponível. Portanto não possuem uma ordem de execução específica.

Essa estratégia é recomendada quando os seus scripts podem rodar imediatamente, sem que dependam de outros ou do DOM completamente disponível.

```js
<script src="exemplo.js" async></script>
<script src="exemplo2.js" async></script>
```

## Comentários

Um comentário de uma linha é escrito depois de duas barras (`//`).

```js
// Eu sou um comentário
```

Um comentário de múltiplas linhas é escrito entre os caracteres `/*` e `*/`.

```js
/*
  Eu também sou
  um comentário
*/
```

## Referências

- <https://pt.wikipedia.org/wiki/JavaScript>
- <https://wiki.developer.mozilla.org/pt-BR/docs/Learn/JavaScript/First_steps/O_que_e_JavaScript>
