# Propriedades de Instância - vm.$slot

[TOC]

## Introdução

Imagine um *template* assim:

```html
<child-component>
  {{ message }}
</child-component>
```

A message deveria ser ligada aos dados do pai ou aos dados do filho? **A resposta é do pai**. Uma regra simples para o escopo do componente é:

> Tudo que estiver no *template* do pai é compilado no escopo do pai; tudo que estiver no *template* do filho é compilado no escopo do filho.

Um erro comum é tentar ligar uma diretiva a um dado do filho no *template* do pai:

```html
<!-- NÃO funciona -->
<template>
  <child-component
    v-show="someChildProperty">
  </child-component>
</template>
```

Assumindo que **someChildProperty** é uma propriedade no componente filho, o exemplo acima não funcionaria. O template do pai não tem conhecimento do estado do filho.
Para ligar diretivas do escopo-filho a um nó de um componente raiz, deveria-se fazer isso no próprio template do componente filho:

```js
Vue.component('child-component', {
  // isto funciona, pois nós estamos no escopo correto
  template: '<div v-show="someChildProperty">Child</div>',
  data: function () {
    return {
      someChildProperty: true
    }
  }
})
```

## Slot simples

O conteúdo do pai será **descartado** a menos que o template do componente filho contenha pelo menos um elemento \<slot\>. Quando há apenas um slot sem campos, todo o fragmento de conteúdo será inserido em sua posição no DOM, substituindo o próprio slot.
Qualquer coisa originalmente dentro das tags \<slot\> é considerado conteúdo reserva. O conteúdo reserva é compilado no escopo do filho e será exibido somente se o elemento hospedeiro estiver vazio e não tiver conteúdo a ser inserido.

Suponha que nós temos um componente chamado my-component com o seguinte template:

```html
<!-- Componente filho -->
<div>
  <h2>Eu sou o título do filho</h2>
  <h3><slot name="nome1"></slot></h3>
  <slot>
    Isto só será exibido se não há conteúdo a ser distribuído.
  </slot>
  <h4><slot name="nome2">Nunca será exibido</slot></h4>
</div>
```

E um pai que usa o componente:

```html
<!-- Componente pai -->
<div>
  <h1>Eu sou o título do pai</h1>

  <componente-filho>
    <span slot="nome1">Slot 1</span>
    <p>Este é um conteúdo informado pelo pai</p>
    <span slot="nome2">Slot 2</span>
  </componente-filho>
</div>
```

O resultado renderizado será:

```html
<div>
  <h1>Eu sou o título do pai</h1>
  <div>
    <h2>Eu sou o título do filho</h2>
    <h3>Slot 1</h3>
    <p>Este é um conteúdo informado pelo pai</p>
    <h4>Slot 2</h4>
  </div>
</div>
```

Pode haver somente um slot sem nomeação, que será o slot padrão caso não seja informado um slot nomeado. Este slot pode fornecer um conteúdo pelo filho, caso o pai não informe nenhum conteúdo (não há conteúdo a ser distribuído). Os **slots nomeados não fornecem conteúdo** pelo filho. O pai pode chamar os slots fora da ordem em que foram definidos.

## Referência

- <https://br.vuejs.org/v2/guide/components.html#Distribuicao-de-Conteudo-com-Slots>
- <https://vuejs.org/v2/guide/components.html#Content-Distribution-with-Slots>
- <https://br.vuejs.org/v2/api/index.html#vm-slots>
- <https://vuejs.org/v2/api/#vm-slots>

Exemplos
- <https://jsfiddle.net/kustavo/wmw99tsL/>
