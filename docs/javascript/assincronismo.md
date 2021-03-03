# Javascript assíncrono

Com o avanço das tecnologias frontend e principalmente do Javascript, a programação assíncrona se tornou cada vez mais necessária e comum nas aplicações, principalmente em SPA’s. Desde os primórdios do jQuery possuímos as requisições XMLHttpRequest (AJAX) que faziam um trabalho assíncrono.

É para esse tipo de situação, que requer processamento assíncrono que existem as Promises, ou, literalmente, promessas. Promise é um objeto usado para processamento assíncrono. Um Promise (de "promessa") representa um valor que pode estar disponível agora, no futuro ou nunca. Em outras palavras, uma Promise é um objeto que representa a eventual conclusão ou falha de uma operação assíncrona.

Existem duas formas de se trabalhar com processamento assíncrono (ou seja, Promises) em JavaScript: utilizando o método `.then()` ou as palavras-chave `async` e `await`.

## Método .then()

Promises, como já dissemos, definem uma ação que vai ser executada no futuro, ou seja, ela pode ser resolvida (com sucesso) ou rejeitada (com erro). A Promise retorna um método `.then()` e outro `.catch()`. Utilizamos o `.then()` para tratar quando queremos resolver a Promise, e o `.catch()` quando queremos tratar os erros de uma Promise rejeitada. Tanto `.then()` quanto `.catch()` retornam outra Promise e é isso que permite que façamos o encadeamento de `then().then().then()`.

Para criarmos uma Promise é muito simples, basta inicializar um `new Promise()` que recebe uma função como parâmetro, esta função tem a assinatura `(resolve, reject) => {}` , então podemos realizar nossas tarefas assíncronas no corpo desta função, quando queremos retornar o resultado final fazemos `resolve(resultado)` e quando queremos retornar um erro fazemos `reject(erro)`. O método `finally()` sempre será executado, independente se a Promise foi resolvida ou rejeitada.

```js
function promessa(resolver = true) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (!resolver) {
                reject("Erro!");
            }
            resolve("Sucesso!");
        }, 3000);
    });
}

promessa(true)
    .then((resposta) => console.log(resposta))
    .catch((erro) => console.error(erro))
    .finally(console.log("Sempre executado"));
// Saída:
// Sucesso!
// Sempre executado


promessa(false)
    .then((resposta) => console.log(resposta))
    .catch((erro) => console.error(erro))
    .finally(console.log("Sempre executado"));
// Saída:
// Erro!
// Sempre executado
```

Formas de recuperar a resposta de uma Promise.

```js
const promessa = new Promise((resolve, reject) => {
    if (Math.random() > 0.5) resolve("Sucesso!");

    reject("Erro!");
});

// Forma 1
promessa
    .then(
        (resposta) => console.log(resposta),
        (erro) => console.error(erro)
    )
    .finally(() => console.log("Sempre executado"));

// Forma 2
promessa.then((resposta) => console.log(resposta));
promessa.catch((erro) => console.error(erro));
promessa.finally(() => console.log("Sempre executado"));

// Forma 3
promessa
    .then((resposta) => console.log(resposta))
    .catch((erro) => console.error(erro))
    .finally(() => console.log("Sempre executado"));

// Saída igual para todos:
// Sucesso!
// Sempre executado
// ou
// Erro!
// Sempre executado
```

Essencialmente, uma Promise é o objeto retornado no qual foi adicionado callbacks, ao invés de passar callbacks para uma função.

```js
function successCallback(result) {
  console.log("It succeeded with " + result);
}

function failureCallback(error) {
  console.log("It failed with " + error);
}

// Exemplo usando callbacks
doSomething(successCallback, failureCallback);

// Exemplo usando Promises
doSomething().then(successCallback, failureCallback);
```

Ao contrário do modo tradicional de passar callbacks, uma Promise vem com algumas garantias:

- Callbacks nunca serão chamados antes da conclusão da execução atual do loop de eventos do JavaScript.
- Callbacks adicionadas com `.then()` mesmo depois do sucesso ou falha da operação assíncrona, serão chamadas, como acima.
- Múltiplos callbacks podem ser adicionados chamando-se `.then()` várias vezes, para serem executados independentemente da ordem de inserção.

### Encadeamento

Em alguns casos teremos uma Promise que retorna outra Promise e para acessar o resultado dessa segunda iremos precisar encadear as chamadas. No exemplo abaixo, estamos executando 3 ações após a primeira Promise, a cada ação nós escrevemos no console o que estamos fazendo e retornamos o **mesmo valor para a próxima Promise**. Cada `.then()` retorna outra Promise, então todo o valor retornado dentro de um `.then()` é como se estivéssemos dando um `resolve(res)` que retornará o valor (string) da Promise anterior. Estamos declarando dois handlers de erro. O que deve acontecer é que, quando a Promise for rejeitada, ele deverá chamar o primeiro handler `erro1()`, mas todo o resto do fluxo segue normalmente. Lembre-se que lançar um `throw()` é diferente de rejeitar uma Promise. O `throw()` irá parar a execução do sistema, mas um `reject()` irá manter o sistema sendo executado, por esta razão é possível ter múltiplos `.catch()` em uma Promise. Cada `.catch()` irá capturar o erro relativo às Promises anteriores, uma vez capturado, o valor que ele retornar será passado para a próxima Promise que executará normalmente.

Neste exemplo o último `.catch()` não será acionado, pois as demais só retornam um `resolve(string)` e não são passíveis de erro.

```js
const promessa = new Promise((resolve, reject) => {
    const n = Math.random();
    if (n > 0.5) resolve("Sucesso! Resposta" + n);

    reject("Erro!");
});

promessa
    .then(function acao1(res) {
        console.log(`${res} na ação 1`);
        return res;
    })
    .catch(function erro1(err) { // Erros que acontecem na ação 1
        console.error("Primeiro catch");
        return "Erro ação 1";
    })
    .then(function acao2(res) {
        console.log(`${res} na ação 2`);
        return res;
    })
    .then(function acao3(res) {
        console.log(`${res} na ação 3`);
        return res;
    })
    .catch(function erro(rej) { // Erros que acontecem nas ações 2 e 3
        console.error(rej);
    });


// Saída:
// "Sucesso! Resposta: 0.8493631956228549 na ação 1"
// "Sucesso! Resposta: 0.8493631956228549 na ação 2"
// "Sucesso! Resposta: 0.8493631956228549 na ação 3"
// ou
// "Primeiro catch"
// "Erro ação 1 na ação 2"
// "Erro ação 1 na ação 3"
```

### Estado Settled

O estado `settled` é quando temos uma Promise completamente resolvida, que já recebeu seus valores de *resolved* ou *reject*, ou seja, é uma Promise que já "acabou". Mesmo criando um novo *handler* após 2000ms, ele sempre irá retornar o valor de *resolve* que já foi resolvido.

```js
const promessa = new Promise((resolve, reject) => {
    const n = Math.random();
    if (n > 0.5) resolve("Resultado " + Math.random());

    reject("Erro!");
});

promessa.then((resposta) => console.log(resposta)).catch((erro) => console.error(erro));

setTimeout(() => promessa.then((resposta) => console.log(resposta)).catch((erro) => console.error(erro)), 2000);

// Saída:
// Resultado 0.9302392114354243
// Resultado 0.9302392114354243
```

### Promises de Promises

É possível para que uma Promise retorne outra Promise para ser resolvida. Quando retornamos uma Promise para outra Promise, só vamos ter a resolução completa do conjunto quando ambas as Promises entrarem no estado `settled`. Ou seja, se, dentro de uma Promise, chamamos outra Promise, a primeira só se resolverá após o retorno da segunda como `settled`.

```js
console.log("Start");

new Promise((resolve, reject) => resolve(1)).then((resposta) => {
    new Promise((resolve, reject) => resolve(resposta + 1)).then((resposta) => console.log(resposta));
});

console.log("Stop");

// Saída:
// Start
// Stop
// 2
```

### Métodos de Promises

#### Promise.resolve e Promise.reject

Estes dois métodos são atalhos para quando queremos retornar uma Promise que sempre terá o mesmo valor, ou sempre resolvida, ou sempre rejeitada.

```js
const p = new Promise((resolve) => resolve(1056))

// Formas reduzidas
const p = Promise.resolve(1056)
const p = Promise.reject('Erro')
```

#### Promise.all

A ideia do método `Promise.all` é executar ações simultaneamente, ou seja, disparar uma série de Promises ao mesmo tempo e esperar pelo retorno de todas elas.

O método `Promise.all` recebe um Array de Promises não resolvidas e inicia todas elas. Ele só vai terminar em dois casos:

1. Todas as Promises do array foram resolvidas
1. Pelo menos uma Promise foi rejeitada

Abaixo um exemplo com todas Promises resolvidas. `Promise.all` espera todas as Promises se resolverem antes de chamar seu handler, no caso de sucesso, o `Promise.all` retorna um *array* com todos os resultados das Promises enviadas.

```js
Promise.all([
    new Promise((resolve) => setTimeout(() => resolve(), 2500)),
    new Promise((resolve) => setTimeout(() => resolve(), 500)),
    new Promise((resolve) => setTimeout(() => resolve(), 1000)),
])
    .then((res) => console.info(res.length))
    .catch((err) => console.error(err));

// Saída:
// 3
```

Abaixo um exemplo com uma promise rejeitada. Quando a segunda Promise é rejeitada, todos os *handlers* são chamados imediatamente, ou seja, o método retorna o valor do erro da segunda Promise para o `.catch()` e ignora completamente as outras Promises, elas ainda são executadas, mas não tem seus valores consumidos por ninguém.

```js
Promise.all([
    new Promise((resolve) => setTimeout(() => resolve(), 2500)),
    new Promise((resolve, reject) => setTimeout(() => reject(), 500)),
    new Promise((resolve) => setTimeout(() => resolve(), 1000)),
])
    .then((res) => console.info(res.length))
    .catch((err) => console.error(err));

// Saída:
// Error> undefined  
```

#### Promise.allSettled

Este método veio para sanar um grande problema com o `Promise.all`. Em muitos casos reais, nós queremos executar várias Promises de forma paralela e trazer o resultado de todas elas, e não só o erro ou então só o array de sucessos, nós queremos tanto os erros, quanto os sucessos.

Este é um problema comum com o `Promise.all` , quando queremos pegar o resultado de todas as Promises, temos que fazer uma função de reflexão, que nada mais faz do que atribuir um *handler* para cada uma das Promises no array e jogar isso tudo dentro do `Promise.all`. Desta forma estamos sobrescrevendo o comportamento original da Promise pelo nosso próprio e retornando para cada valor um objeto com as descrições do que aconteceu.

```js
function reflect(promise) {
    return promise.then(
        (res) => {
            return { status: "fulfilled", value: res };
        },
        (error) => {
            return { status: "rejected", reason: error };
        }
    );
}

var p1 = new Promise((resolve) => setTimeout(() => resolve(1), 1500));
var p2 = new Promise((resolve) => setTimeout(() => resolve(2), 500));
var p3 = new Promise((resolve) => setTimeout(() => resolve(3), 1000));
var p4 = new Promise((resolve, reject) => setTimeout(() => reject(4), 100));
var p5 = new Promise((resolve) => setTimeout(() => resolve(5), 300));

async function foo() {
    const promises = [p1, p2, p3, p4, p5];

    Promise.all(promises.map(reflect)).then((res) => {
        const successfulPromises = res.filter((p) => p.status === "fulfilled");
        console.log(successfulPromises);
    });
}

foo();

// Saída:
// [{
//   status: "fulfilled",
//   value: 1
// }, {
//   status: "fulfilled",
//   value: 2
// }, {
//   status: "fulfilled",
//   value: 3
// }, {
//   status: "fulfilled",
//   value: 5
// }]
```

O método `Promise.allSettled` tem a proposta de abstrair a função `reflect`.

```js
var p1 = new Promise((resolve) => setTimeout(() => resolve(1), 2500));
var p2 = new Promise((resolve) => setTimeout(() => resolve(2), 500));
var p3 = new Promise((resolve) => setTimeout(() => resolve(3), 1000));
var p4 = new Promise((resolve, reject) => setTimeout(() => reject(4), 100));
var p5 = new Promise((resolve) => setTimeout(() => resolve(5), 300));

async function foo() {
    const promises = [p1, p2, p3, p4, p5];
    Promise.allSettled(promises).then((res) => {
        const successfulPromises = res.filter((p) => p.status === "fulfilled");
        console.log(successfulPromises);
    });
}

foo();

// Saída:
// [{
//   status: "fulfilled",
//   value: 1
// }, {
//   status: "fulfilled",
//   value: 2
// }, {
//   status: "fulfilled",
//   value: 3
// }, {
//   status: "fulfilled",
//   value: 5
// }]
```

#### Promise.race

O método `Promise.race` faz exatamente o que o nome diz, ele recebe um array de Promises, inicia todas elas, a que retornar Primeiro vai ser o retorno do método por completo. Ele é um caso especial do `Promise.all` onde, ao invés de esperar todas as Promises serem resolvidas, simplesmente retorna o primeiro resultado que obtiver

No exemplo abaixo temos dois arrays de Promises, um deles é resolvido em 4s e depois rejeitado em 8s, enquanto o outro é rejeitado em 2s e depois resolvido em 6s e 10s. No primeiro `Promise.race` aguarda duas Promises. O primeiro tem sua Promise resolvida e ele já a retorna, porque é o resultado que ele espera, então a segunda Promise (que é rejeitada) não é sequer consumida.
No segundo `Promise.race` aguarda três Promises, temos uma Promise que é rejeitada logo de cara, então todas as demais Promises são ignoradas e o handler catch é chamado.

```js
const p1 = Promise.race([
  new Promise(resolve => setTimeout(resolve, 4000)),
  new Promise((resolve, reject) => setTimeout(reject, 8000))
])

const p2 = Promise.race([
  p1,
  new Promise(resolve => setTimeout(resolve, 6000)),
  new Promise(resolve => setTimeout(resolve, 10000)),
  new Promise((resolve, reject) => setTimeout(reject, 2000))
])

p2.then(result => console.log(result))
p2.catch(err => console.error(err))
```

## Async/Await

Os `async` e `await` são *keywords* que foram introduzidas no ES8 em 2017. Basicamente é um syntax sugar, adicionada somente para poder facilitar a escrita do `.then()` e `.catch()`. O `async/await` trabalha com o código baseado em Promises, porém esconde as promessas para que a leitura seja mais fluída e simples de entender.

O motivo pela adição do `async/await` foi facilitar quando era necessário o aninhando Promises dentro de Promises. Este aninhando tornava tudo muito mais difícil de se ler. No exemplo abaixo temos a comparação de trechos de código equivalentes usando `async/await` e `Promise` respectivamente.

```js
async function foo() {
 if (Math.random() > 0.5) return 'Ok!'
 throw new Error('Erro')
}

async function bar() {
 try {
    const res = await foo();
    console.log(res);
 } catch (erro) { console.log(erro.message) }
}

bar();
```

```js
const foo = new Promise((resolve, reject) => {
 if (Math.random() > 0.5) return resolve('Ok!')
 reject('Erro')
})

foo.then((res) => {console.log(res)}).catch((erro) => {console.log(erro)})
```

Definindo uma função como `async`, podemos utilizar a palavra-chave `await` antes de qualquer expressão que retorne uma promessa. A palavra-chave `await` recebe uma Promise e a transforma em um valor de retorno ou lança uma exceção em caso de erro. Quando utilizamos `await`, o JavaScript vai **aguardar** até que a Promise finalize.

Uma função declarada como `async` significa que o valor de retorno da função será uma Promise. Se a Promise se resolver normalmente, o objeto Promise retornará o valor. Caso lance uma exceção, podemos usar o `try/catch` assim como em programas síncronos.

```js
var p1 = new Promise((resolve) => setTimeout(() => resolve(), 1500));
var p2 = new Promise((resolve) => setTimeout(() => resolve(), 4000));

async function promises() {
    console.log("P1-Start");
    await p1; // espera resolução de p1
    console.log("P1-End, P2-Start");
    await p2; // espera resolução de p2
    console.log("P2-End");
}

console.log("Start");
promises();
console.log("End");

// Saída:
// "Start"
// "P1-Start"
// "End"
// "P1-End, P2-Start"
// "P2-End"
```

O `await` só funciona se estiver dentro de outra função async. Caso não esteja, você ainda pode usar `.then()` normalmente:

```js
var p1 = new Promise((resolve) => setTimeout(() => resolve("um"), 1500));
var p2 = new Promise((resolve) => setTimeout(() => resolve("dois"), 3000));

async function promises() {
  var r1 = await p1;
  console.log(r1);
  var r2 = await p2;
  console.log(r2);
}

// Usando await em uma função async
async function foo() {
  await promises();
}
foo();

// ou usando o then()
promises().then();

// Saída em ambos:
// um
// dois
```

No estado *settled*, assim como utilizando os métodos `.then()` e `.catch()`, quando uma Promise é completamente resolvida, se definirmos um segundo handler para a Promise, o valor será retornado imediatamente, já que a Promise já foi resolvida.

```js
var p = new Promise((resolve) => setTimeout(() => resolve(), 4000));

async function promises() {
  await p; // retorno após 4s
  await p; // retorno imediato após o primeiro await
}
```

### Diferenças entre .then() e async/await

Em termos de sintaxe, o método `.then()` traz uma sintaxe que faz mais sentido quando utilizamos o JavaScript de forma funcional, enquanto o fluxo das declarações com `async/await` fazem sentido ao serem utilizadas em métodos de classes.

O `async/await` surgiu como uma opção mais legível ao `.then()`, mas é importante notar que estes métodos **não são logicamente equivalentes**. Enquanto `async/await` faz o processamento de forma sequencial, Promises com `.then()` são processadas em paralelo, o que faz com que este método seja mais rápido. O `async/await` simplifica a escrita e a interpretação do código, mas não é tão flexível e só funciona com uma Promise por vez.

```js
function novaPromise(mensagem) {
    return new Promise((resolve) => setTimeout(() => resolve(mensagem), 3000)).then((res) => console.log(res));
}

function usandoThen() {
    novaPromise("um");
    novaPromise("dois");
}
usandoThen(); // Resolução em 3s

async function usandoAsyncWait() {
    await novaPromise("um"); // espera resolver
    await novaPromise("dois"); // espera resolver
}
usandoAsyncWait(); // Resolução em 6s

// Saída em ambos:
// um
// dois
```

Uma forma de usar o paralelismo é iniciar as Promises antes do `await`, atribuindo em variáveis.

```js
function novaPromise(mensagem) {
    return new Promise((resolve) => setTimeout(() => resolve(mensagem), 3000)).then((res) => console.log(res));
}

async function bar() {
    var p1 = novaPromise("um");
    var p2 = novaPromise("dois");

    await p1;
    await p2;
}
bar(); // Resolução em 3s

// Saída:
// um
// dois
```

Outro exemplo de forma de paralelizar a execução de Promises.

```js
// Exemplo sequencial
async function logInOrder(urls) {
    for (const url of urls) {
        const response = await fetch(url);
        console.log(await response.text());
    }
}

// Exemplo paralelo
async function logInOrder(urls) {
    // Buscar todas url em paralelo
    const textPromises = urls.map(async (url) => {
        const response = await fetch(url);
        return response.text();
    });

    // Escreve log em sequencia
    for (const textPromise of textPromises) {
        console.log(await textPromise);
    }
}
```

Outra forma de usar o paralelismo em `async/await` é usar o método `Promise.all` que disparar uma série de Promises ao mesmo tempo e esperar pelo retorno de todas elas.

```js
function novaPromise(mensagem) {
    return new Promise((resolve) => setTimeout(() => resolve(mensagem), 3000)).then((res) => console.log(res));
}

async function bar() {
    let [user1, user2] = await Promise.all([novaPromise("um"), novaPromise("dois")]);
}
bar(); // Resolução em 3s

// Saída:
// um dois
```

## Referências

- <https://medium.com/trainingcenter/entendendo-promises-de-uma-vez-por-todas-32442ec725c2>
- <https://blog.rocketseat.com.br/javascript-assincrono-promises>
- <https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Guide/Usando_promises>
