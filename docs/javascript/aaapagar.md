

## 1.1. Variáveis (const, let e var)

Variáveis definidas com "var" possuem escopo global, as variáveis definidas com "let" possuem escopo local e as variáveis definidas com "const" possuem escopo local e não podem ser alteradas.

Sempre que é definido uma variável "const", o Javascript faz referência ao endereço de memória da variável. Portanto, não pode alterar a variável para fazer referência a algum outro local de memória. O comando "push" apenas adiciona o elemento ao vetor, o ponteiro para o início do vetor se mantém inalterado.

```js
var V = "var"
let L = "let"
const C = "const"

if (true) {
    var v = "var_interno"
    let l = "let_interno"
    const c = "const_interno"
    const cv = ['Js', 'Ruby', 'Python', 'Go']

    v = "var_interno_novo"
    l = "let_interno_novo"
    // c = "const_interno_novo" // erro, não pose ser alterada
    // cv = ['Java']            // erro, não pose ser alterada
    cv.push('Java');            // funciona

    console.log("-------Interno--------")
    console.log(v)  // var_interno_novo
    console.log(l)  // let_interno_novo
    console.log(c)  // const_interno
    console.log(cv) // ["Js", "Ruby", "Python", "Go", "Java"]

    console.log(V) // var
    console.log(L) // let
    console.log(C) // const
}
console.log("-------Externo--------")
console.log(v)     // var_interno_novo
// console.log(l); // undefined, definida em escopo local
// console.log(c); // undefined, definida em escopo local
console.log(V)     // var
console.log(L)     // let
console.log(C)     // const
```

## 1.2. Arrow functions

A versão do JavaScript ES6 trouxe novas features e dentre elas uma nova forma de criar funções usando o operador =>. Esta nova forma de se trabalhar com funções são chamadas Arrow Functions.
Uma expressão arrow function possui uma síntaxe mais curta quando comparada com uma expressão de função (function expression) e não faz o bind do this. Seu valor de this é definido à partir das funções onde foram definidas. Ou seja, não é mais necessário fazer bind() ou armazenar o estado em that = this. Arrow functions sempre são anônimas.

Ausência de argumentos precisa ser indicada com "()". Para apenas um argumento os parentêses não são requeridos.

### Vários parâmetros

Sem arrow functions.

```js
var oldWay = function(name, nickname) {
    return 'My name is ' + nickname + ', ' + name
};
console.log( oldWay('James Bond', 'Bond')) // My name is Bond, James Bond
```

Com arrow functions.

```js
let newWay = (name, nickname) => {
    return 'My name is ' + nickname + ', ' + name;
};
console.log( newWay('James Bond', 'Bond') );
// My name is Bond, James Bond
```

Ou de uma maneira um pouco mais curta.

```js
let newWay2 = (name, nickname) => 'My name is ' + nickname + ', ' + name;

console.log( newWay2('James Bond', 'Bond') );
// My name is Bond, James Bond
```

### Um parâmentro

Sem arrow functions.

```js
var one = function(what) {
    return 'I ' + what + ' you';
};
console.log( one('hate') );
// I hate you
```

Com arrow functions.

```js
var oneNew = what => 'I ' + what + ' you';
console.log( oneNew('hate') );
// I hate you
```

### Escopo

Antes das arrow functions, toda nova função definia seu próprio valor de this (um novo objeto no caso de um construtor, undefined em chamadas de funções com strict mode, o objeto de contexto se a função é chamada como um "método de objeto", etc.). Este comportamento é importuno com um estilo de programação orientado a objeto.

Sem arrow functions.

```js
function Pessoa() {
    var self = this
    self.idade = 0

    setInterval(function crescer() {
        // O callback referência a variável self a qual o valor é o objeto esperado.
        self.idade++
    }, 1000)
}
```

Com arrow functions.

```js
function Pessoa(){
    this.idade = 0

    setInterval(() => {
        this.idade++ // |this| corretamente referência ao objeto Pessoa
    }, 1000)
}
var p = new Pessoa();
```