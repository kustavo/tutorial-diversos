# Observables

*Observable* é uma funcionalidade da biblioteca RxJS que é a implementação JavaScript do projeto *ReactiveX* que visa fornecer uma API para programação assíncrona para diferentes linguagens de programação.

A API *ReactiveX* é implementada em várias linguagens de programação. Conforme mencionado, RxJS é a implementação JavaScript do *ReactiveX*. Além disso, existem, por exemplo, implementações RxJava (Java), RxKotlin (Kotlin), Rx.rb (Ruby), RxPY (Python), RxSwift (Swift), Rx.NET (C #) e muitos mais

Com *Observables*, conseguimos lidar com transferência de dados assíncrona. Muitas vezes, seu uso é semelhante ao de *Promises* do Javascript, porém, as *Promises* lidam com um evento assíncrono por vez, enquanto os *Observables* ​​lidam com uma sequência de eventos assíncronos durante um período de tempo.

Os observables ​​são declarativos, isto é, você define uma função para publicar valores, mas não é executado até que um consumidor o assine (*subscribe*). O consumidor inscrito recebe notificações até que a função seja concluída ou até que elas sejam canceladas.

Os conceitos essenciais em RxJS que resolvem o gerenciamento de eventos são:

- **Observable**: o observável representa a ideia de uma coleção invocável de valores ou eventos futuros.
- **Observer**: o observador é uma coleção de callbacks que sabem ouvir os valores entregues pelo *Observable*.
- **Subscription**: a inscrição representa a execução de um *Observable*, é principalmente útil para cancelar a execução.
- **Operators**: os operadores são funções puras que permitem um estilo de programação funcional para lidar com coleções com operações como: *map*, *filter*, *concat*, *reduce*, etc.
- **Subject**: é equivalente a um *EventEmitter* sendo a única maneira realizar um *multicast* de um valor ou evento para vários *Observadores*.
- **Schedulers**: são despachantes centralizados para controlar concorrência, permitindo-nos coordenar quando a computação acontece (e.g. no *setTimeoutou* *requestAnimationFrame* ou outros).

## Diferenças entre Observables e Promises

| Observables                                                                                                            | Promises                                                   |
|------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------|
| Emite vários valores ao longo de um período de tempo.                                                                  | Emita um único valor de cada vez.                          |
| São preguiçosos: eles não são executados até que os assinemos usando o método `subscribe()`.                           | Não são preguiçosos: execute imediatamente após a criação. |
| Ter inscrições que podem ser canceladas usando o método `unsubscribe()`, que impede que o ouvinte receba mais valores. | Não são canceláveis.                                       |
| Fornece o suporte para os operadores *forEach*, *filter*, *reduce*, *retry* e *retryWhen*.                             | Não forneça quaisquer operações.                           |
| Entrega erros aos assinantes (subscribes).                                                                             | Empurre os erros para as *Promises* filhas.                |

### Multicast x Unicast

A função executora de uma *Promise* é executada exatamente uma vez (quando a promessa é criada). Isso significa que todas as chamadas para `then` em um determinado objeto de *Promise* apenas "batem" na execução em andamento da função do executor e obtêm uma cópia do valor do resultado. Portanto, as *Promises* realizam *multicast*, porque a mesma execução e valor de resultado é usado para vários "assinantes".

A função de inscrição de um *Observable* é executada em cada chamada para `subscribe` neste *Observable*. Portanto, os *Observables* ​​executam o *unicast*, porque há uma execução separada e um valor de resultado para cada inscrito/assinante.

**Promise:**

```js
const promise = new Promise(resolve => {
    console.log("Executing...");
    resolve(Math.random());
});
promise.then(result => console.log(result));
promise.then(result => console.log(result));

// Saída:
// Executing...
// 0.1951561731912439
// 0.1951561731912439
```

**Observable:**

```js
const observable = new Observable(observer => {
    console.log("Executing...");
    observer.next(Math.random());
});
observable.subscribe(result => console.log(result));
observable.subscribe(result => console.log(result));

// Saída:
// Executing...
// 0.5884515904517829
// Executing...
// 0.7974144930327094
```

### Encadeamento

Os *Observables* ​​não podem ser encadeados. Não há "observe isso, então observe essa outra coisa". *Observables* ​​retornam uma inscrição para o objeto observável. As *Promises* podem ser encadeadas. Eles retornam outra *Promise*.

## Observable

Os *Observables* ​​são coleções push preguiçosas (em inglês, *lazy*) de vários valores. Eles preenchem o espaço que faltava na tabela a seguir:

|                   | Single   | Multiple       |
|-------------------|----------|----------------|
| Pull (consumidor) | Function | Iterator       |
| Push (produtor)   | Promise  | **Observable** |

Para invocar o *Observable* e ver esses valores, precisamos assiná-lo (*subscribe*):

```js
const obs = new Observable(subscriber => {
    subscriber.next(1);
    subscriber.next(2);
    subscriber.next(3);
    setTimeout(() => {
        subscriber.next(4);
        subscriber.complete();
    }, 1000);
});

console.log('antes do subscribe');

obs.subscribe({
    next(x) {
        console.log('recebeu o valor ' + x);
    },
    error(err) {
        console.error('erro: ' + err);
    },
    complete() {
        console.log('fim');
    }
});

console.log('depois do subscribe');

// Saída:
// antes do subscribe
// recebeu o valor 1
// recebeu o valor 2
// recebeu o valor 3
// depois do subscribe
// recebeu o valor 4
// fim
```

### Pull x Push

*Pull* e *Push* são dois protocolos diferentes que descrevem como um *Produtor* de dados pode se comunicar com um *Consumidor* de dados.

O que é *Pull*? Em sistemas *pull*, o *Consumidor* determina quando recebe dados do *Produtor* de dados. O próprio *Produtor* não sabe quando os dados serão entregues ao *Consumidor*.

Cada função JavaScript é um sistema *pull*. A função é um *Produtor* de dados, e o código que chama a função os está consumindo, *"puxando"* um único valor de retorno de sua chamada.

O ES2015 introduziu funções geradoras e iteradores (function\*), outro tipo de sistema *Pull*. O código que chama `iterator.next()` é o *Consumidor*, *"puxando"* vários valores do iterador (o *Produtor*).

|      | Produtor                                  | Consumidor                                     |
|------|-------------------------------------------|------------------------------------------------|
| Pull | Passivo: produz dados quando solicitado.  | Ativo: decide quando os dados são solicitados. |
| Push | Ativo: produz dados em seu próprio ritmo. | Passivo: reage aos dados recebidos.            |

O que é *Push*? Em sistemas *Push*, o *Produtor* determina quando enviar dados ao *Consumidor*. O *Consumidor* não sabe quando receberá esses dados.

As *Promises* são o tipo mais comum de sistema *Push* em JavaScript atualmente. Uma *Promise* (o *Produtor*) entrega um valor resolvido aos retornos de chamada registrados (os *Consumidores*), mas ao contrário das funções, é a *Promise* que se encarrega de determinar quando esse valor é "empurrado" para os retornos de chamada.

RxJS apresenta *Observables*, um novo sistema *Push* para JavaScript. Um *Observable* é um *Produtor* de valores múltiplos, "empurrando-os" para observadores (*observers*) (*Consumidores*).

- Uma **Função** é uma computação avaliada lentamente que retorna de forma síncrona um único valor na invocação.
- Um **Gerador** (*generator*) é uma computação avaliada lentamente que retorna de forma síncrona zero a valores (potencialmente) infinitos na iteração.
- Uma **Promise** é uma computação que pode (ou não) eventualmente retornar um único valor.
- Um **Observable** é uma computação avaliada vagarosamente que pode retornar de forma síncrona ou assíncrona zero a (potencialmente) valores infinitos a partir do momento em que é invocado.

### Observables ​​como generalizações de funções

!!! warning "Importante"
    Ao contrário das afirmações populares, os *Observables* ​​não são como *EventEmitters* nem são como *Promises* de valores múltiplos. Os *Observables* podem atuar como *EventEmitters* em alguns casos, nomeadamente quando são *multicast* usando *RxJS Subject*, mas geralmente não atuam como *EventEmitters*.

Os *Observables* ​​são como funções sem argumentos, mas podem "retornar" vários valores ao longo do tempo, algo que as funções não podem. Inscrever-se em um *Observable* é análogo a chamar uma função.

- Uma chamada de função (e.g `func.call()`) significa "me dê **um** valor de forma **síncrona**".
- Uma inscrição (e.g. `observable.subscribe()`) significa "me dê **qualquer quantidade** de valores, seja de forma **síncrona** ou **assíncrona**".

Não é possível fazer isso:

```js
function foo() {
  console.log('Hello');
  return 42;
  return 100; // nunca executado
}
```

 Mas os *Observables*, no entanto, podem fazer isso:

```js
const foo = new Observable(subscriber => {
  console.log('Hello');
  subscriber.next(42);
  subscriber.next(100);
  subscriber.next(200);
});
 
console.log('antes do subscribe');
foo.subscribe(x => {
  console.log(x);
});
console.log('depois do subscribe');

// Saída:
// antes do subscribe
// Hello
// 42
// 100
// 200
// depois do subscribe
```

Mas você também podem "retornar" valores de forma assíncrona:

```js
const foo = new Observable(subscriber => {
  console.log('Hello');
  subscriber.next(42);
  subscriber.next(100);
  subscriber.next(200);
  setTimeout(() => {
    subscriber.next(300); // acorre de forma assíncrona
  }, 1000);
});
 
console.log('antes do subscribe');
foo.subscribe(x => {
  console.log(x);
});
console.log('depois do subscribe');

// Saída:
// antes do subscribe
// Hello
// 42
// 100
// 200
// depois do subscribe
// 300
```

### Anatomia de um observável

Os *Observables* ​​são **criados** usando `new Observable()` ou `Observable.create()`. São **inscritos** (*subscribed*) com um *Observer*. São **executados** para entregar as notificações `next`, `error` ou `complete` ao *Observer* e sua execução pode ser **descartada**. Esses quatro aspectos são todos codificados em uma instância *Observable*, mas alguns desses aspectos estão relacionados a outros tipos, como *Observer* e *Subscription*.

Portanto temos:

- Criação de *Observables*
- Inscrição em *Observables*
- Execução de *Observables*
- Descarte de *Observables*

#### Criação

```js
const obs = new Observable(observer => {
    setInterval(() => {
        observer.next('Hello');
    }, 1000);
});
// ou
const obs = Observable.create(observer => {
    setInterval(() => {
        observer.next('Hello');
    }, 1000);
})

// Saída em ambos
// Hello
// Hello
// Hello
// ...
```

#### Inscrição

Um *Observable* é executado somente quando há uma inscrição.

```js
const subscription = obs.subscribe((value) => {
    console.log(value)
});
```

Chamadas *subscribe* não são compartilhadas entre vários *Observer* do mesmo *Observable*. Ao chamar `obs.subscribe` com um *Observer*, a função `observer => {}` é executada para aquele determinado assinante. Cada chamada para `obs.subscribe` dispara sua própria configuração independente para aquele determinado assinante.

Inscrever-se em um Observable é como chamar uma função, fornecendo retornos de chamada para onde os dados serão entregues.

Isso é drasticamente diferente das APIs de manipulador de eventos como `addEventListener`/`removeEventListener`. Com `obs.subscribe`, o *Observer* fornecido não é registrado como um ouvinte no *Observable*. O *Observable* nem mesmo mantém uma lista de *Observers* anexados.

Uma chamada *subscribe* é simplesmente uma forma de iniciar uma "execução de *Observable*" e entregar valores ou eventos a um *Observer* dessa execução.

#### Execução

O código interno de `observer => {}` representa uma "execução do *Observable*", uma computação preguiçosa (*lazy*) que só acontece para cada *Observer* que se inscreve. A execução produz vários valores ao longo do tempo, de forma síncrona ou assíncrona.

Existem três tipos de valores que uma Execução Observável pode fornecer:

- Notificação **Next**: envia um valor como um número, uma string, um objeto, etc.
- Notificação **Error**: envia um erro ou exceção de JavaScript.
- Notificação **Complete**: não envia um valor.

As notificações **Next** são o tipo mais importante e mais comum: elas representam dados reais sendo entregues a um assinante. As notificações **Error** e **Complete** podem ocorrer apenas uma vez durante a Execução do *Observable* e só pode ocorrer uma delas, nunca ambas.

Os *Observables* ​​aderem estritamente ao Contrato, de modo que o código a seguir não entregaria a notificação `4`:

```js
const observable = new Observable(function subscribe(subscriber) {
  subscriber.next(1);
  subscriber.next(2);
  subscriber.next(3);
  subscriber.complete();
  subscriber.next(4); // Não será entregue porque viola o contrato
});
```

É uma boa ideia envolver qualquer código *subscribe* com *try/catch* para entregar uma notificação de erro se detectar uma exceção:

```js
const observable = new Observable(function subscribe(subscriber) {
  try {
    subscriber.next(1);
    subscriber.next(2);
    subscriber.next(3);
    subscriber.complete();
  } catch (err) {
    subscriber.error(err); // entrega o erro se ocorrer algum
  }
});
```

#### Desinscrição

Como as execuções de *Observables* ​​podem ser infinitas e é comum que um *Observer* queira abortar a execução em um tempo finito. Como cada execução é exclusiva para um *Observer* apenas, uma vez que o *Observer* acabou de receber os valores, ele deve ter uma forma de interromper a execução, para evitar desperdício de poder de computação ou recursos de memória.

Quando `obs.subscribe` é chamado, o *Observer* é anexado à execução *Observable* recém-criada. Essa chamada também retorna um objeto, o *Subscription*:

```js
const subscription = obs.subscribe(x => console.log(x));
```

A inscrição (*Subscription*) representa a execução em andamento e com `subscription.unsubscribe()` é possível cancelar a execução em andamento:

```js
const observable = from([10, 20, 30]);
const subscription = observable.subscribe(x => console.log(x));
// Depois:
subscription.unsubscribe();
```

Ao se inscrever (*subscribe*), você recebe de volta uma inscrição (*subscription*), que representa a execução em andamento. Basta executar `unsubscribe()` para cancelar a execução.

Cada *Observable* deve definir como descartar os recursos dessa execução quando criamos o *Observable* usando `create()`. Você pode fazer isso retornando uma função `unsubscribe` personalizada.

Por exemplo, é assim que limpamos um conjunto de execução de intervalo com `setInterval`:

```js
const obs = new Observable(function subscribe(subscriber) {

  const intervalId = setInterval(() => {
    subscriber.next('Hello');
  }, 1000);

  // Fornece uma maneira de cancelar e descartar o recurso de intervalo
  return function unsubscribe() {
    clearInterval(intervalId);
  };
});
```

Se removermos os tipos ReactiveX em torno desses conceitos, ficaremos com um JavaScript bastante direto.

```js
function subscribe(subscriber) {
  const intervalId = setInterval(() => {
    subscriber.next('hi');
  }, 1000);
 
  return function unsubscribe() {
    clearInterval(intervalId);
  };
}
 
const unsubscribe = subscribe({next: (x) => console.log(x)});
 
// Depois:
unsubscribe();
```

O motivo pelo qual usamos tipos Rx como *Observable*, *Observer* e *Subscription* é para obter segurança (como o Contrato *Observable*) e capacidade de composição com Operadores.

## Observer

Um *Observer* é um consumidor de valores fornecidos por um *Observable*. Os *Observers* são simplesmente um conjunto de chamadas de retorno, um para cada tipo de notificação entregue pelo *Observable*: `next`, `error`, e `complete`. A seguir está um exemplo de um objeto *Observer*:

```js
const observer = {
  next: x => console.log('Observer obtêm o valor next: ' + x),
  error: err => console.error('Observer obtêm um erro: ' + err),
  complete: () => console.log('Observer obtêm uma notificação complete'),
};

observable.subscribe(observer);
```

*Observers* são apenas objetos com três retornos de chamada, um para cada tipo de notificação que um Observável pode entregar.

Ao se inscrever em um *Observable*, você também pode fornecer apenas o *callback* `next` como um argumento, sem estar anexado a um objeto *Observer*. Internamente no `observable.subscribe`, ele criará um objeto *Observer* usando o argumento *callback* como o manipulador `next`.

## Operadores

Os operadores são as peças essenciais que permitem que um código assíncrono complexo seja facilmente composto de maneira declarativa.

Operadores são funções. Existem dois tipos de operadores:

- `Operadores *Pipeable*`
  : são os tipos que podem ser "canalizados" para *Observable* ​​usando a sintaxe `observableInstance.pipe(operator())`. Isso inclui `filter()`, e `mergeMap()`. Quando chamados, eles não alteram a instância *Observable* existente. Em vez disso, eles retornam um novo *Observable*, cuja lógica de inscrição é baseada no primeiro *Observable*.

  Um Operador *Pipeable* é uma função que recebe um *Observable* como entrada e retorna outro *Observable*. É uma operação pura: o *Observable* anterior permanece inalterado.

- `Operadores de criação`
  :  são os outros tipos de operadores, que podem ser chamados como funções autônomas para criar um novo *Observable*. Por exemplo: `of(1, 2, 3)` cria um *Observable* que emitirá `1, 2 e 3,` um após o outro.

  Por exemplo, o operador chamado `map` é análogo ao método *Array* de mesmo nome. Assim como `[1, 2, 3].map(x => x * x)` irá produzir `[1, 4, 9]`:

  ```js
  of(1, 2, 3)
    .pipe(map((x) => x * x))
    .subscribe((v) => console.log(`value: ${v}`));

  // Saída:
  // value: 1
  // value: 4
  // value: 9
  ```

  ```js
  of(1, 2, 3)
    .pipe(first())
    .subscribe((v) => console.log(`value: ${v}`));

  // Saída:
  // value: 1
  ```

  Observe que `map` logicamente deve ser construído em tempo real, uma vez que deve receber a função de mapeamento. Por outro lado, `first` pode ser uma constante, mas mesmo assim é construída em tempo real. Como prática geral, todos os operadores são construídos, precisem de argumentos ou não.

### Operadores Pipeable

Operadores `Pipeable` são funções, portanto eles poderiam ser usados como funções comuns: `op()(obs)`. Mas, na prática, tende a haver muitos deles em conjunto, e rapidamente se tornam ilegíveis: `op4()(op3()(op2()(op1()(obs))))`. Por esse motivo, os *Observable* ​​têm um método chamado `.pipe()` que realiza a mesma coisa, embora seja muito mais fácil de ler:

```js
obs.pipe(op1(), op2(), op3(), op4());
```

Por questão estilística, `op()(obs)` nunca é usado, mesmo que haja apenas um operador; `obs.pipe(op())` é universalmente preferido.

### Operadores de Criação

Diferentes dos operadores *Pipeable*, os operadores de criação são funções que podem ser usadas para criar um *Observable* com algum comportamento predefinido comum ou juntando-se a outros *Observable*.

Um exemplo típico de operador de criação seria a função `interval`. Leva um número (não um *Observable*) como argumento de entrada e produz um *Observable* como saída:

```js
const observable = interval(1000);
```

### Observáveis ​​de ordem superior

Os *Observables* ​​emitem mais comumente valores comuns, como *strings* e números, mas algumas vezes é necessário lidar com os *Observables* dos *Observables*, os chamados *Observables* ​​de ordem superior. Por exemplo, imagine que você tem um *Observable* emitindo uma strings ​​que são os URLs dos arquivos que você deseja ver. O código pode ser assim:

```js
const fileObservable = urlObservable.pipe(map((url) => http.get(url)));
```

`http.get()` retorna um *Observable* (de *string* ou *arrays* de *string* provavelmente) para cada URL individual. Agora você tem um *Observable* de *Observables*, um *Observable* de ordem superior.

Mas como se trabalha com um Observável de ordem superior? Normalmente, *flattening* convertendo um *Observable* de ordem superior em um *Observable* comum. Por exemplo:

```js
const fileObservable = urlObservable.pipe(
  map((url) => http.get(url)),
  concatAll()
);
```

O operador `concatAll()` assina cada *Observable* "interno" que sai do *Observable* "externo" e copia todos os valores emitidos até que o *Observable* seja concluído e prossegue para o próximo. Todos os valores são concatenados dessa forma. Outros operadores de nivelamento úteis (chamados de operadores de junção ) são:

- `mergeAll()` - assina cada *Observable* interno à medida que chega e, em seguida, emite cada valor à medida que chega.
- `switchAll()` - assina o primeiro *Observable* interno quando chega e emite cada valor conforme chega, mas quando o próximo *Observable* interno chega, cancela a inscrição do anterior e se inscreve no novo.
- `exhaust()` - inscreve-se no primeiro *Observable* interno quando chega e emite cada valor conforme chega, descartando todos os Observáveis ​​internos recém-chegados até que o primeiro seja concluído e, em seguida, aguarda o próximo *Observable* interno.

Tal como muitas bibliotecas de *array* combinam `map()` e `flat()` (ou `flatten()`) em um único` flatMap()`, existem equivalentes de mapeamento de todos os operadores RxJS de *flattening* como: `concatMap()`, `mergeMap()`, `switchMap()`, e `exhaustMap()`.

### Categorias de operadores

Existem operadores para diferentes finalidades, e podem ser categorizados como: criação, transformação, filtragem, junção, *multicast*, tratamento de erros, utilidade, etc. Veja a lista completa [aqui](https://rxjs-dev.firebaseapp.com/guide/operators) e [aqui](https://rxjs-dev.firebaseapp.com/api/operators).

#### Exemplos de operadores

##### zipWith (zip)

Combina vários *Observables* ​​para criar um *Observable* cujos valores são calculados a partir dos valores de cada um de seus *Observables* ​​de entrada.

```js
const obs1 = new Observable(subscriber => {
  setTimeout(() => {
    subscriber.next(1);
  }, 1000);
});

const obs2 = new Observable(subscriber => {
  setTimeout(() => {
    subscriber.next(2);
  }, 2000);
});

const obs3 = new Observable(subscriber => {
  setTimeout(() => {
    subscriber.next(3);
  }, 3000);
});

zipWith(obs1, obs2, obs3)
  .pipe(map(([res1, res2, res3]) => ({ res1, res2, res3 })))
  .subscribe(x => console.log(x));

// Saída:
// iniciando: obs1
// iniciando: obs2
// iniciando: obs3
// finalizando: obs2
// finalizando: obs1
// finalizando: obs3
// {res1: 1, res2: 2, res3: 3}
```

## Subscription

Um *Subscription* (Inscrição) é um objeto que representa um recurso descartável, geralmente a execução de um *Observable*. Um *Subscription* tem um método importante *unsubscribe*, que não aceita nenhum argumento e apenas descarta o recurso mantido pelo *Subscription*.

Um *Subscription* basicamente tem apenas uma função `unsubscribe()` para liberar recursos ou cancelar execuções *Observables*. Os *Subscriptions* também podem ser colocados juntos, de modo que uma chamada para um `unsubscribe()` de um *Subscription* pode cancelar de vários *Subscriptions*. Você pode fazer isso "adicionando" um *Subscription* a outro:

```js
const observable1 = interval(400);
const observable2 = interval(300);
 
const subscription = observable1.subscribe(x => console.log('first: ' + x));
const childSubscription = observable2.subscribe(x => console.log('second: ' + x));
 
subscription.add(childSubscription);
 
setTimeout(() => {
  // Desinscreve subscription e childSubscription
  subscription.unsubscribe();
}, 1000);

// Saída:
// second: 0
// first: 0
// second: 1
// first: 1
// second: 2
```

Um *Subscription* também têm um método `remove(otherSubscription)` para desfazer a adição de um *Subscription* filho.

## Subject

Um *Subject* RxJS é um tipo especial de *Observable* que permite que os valores sejam *multicast* para muitos *Observers*. Enquanto os *Observables* simples são unicast (cada *Observer* inscrito possui uma execução independente do *Observable*), os *Subjects* são *multicast*.

Um *Subject* é como um *Observable*, mas pode transmitir para vários *Observers*. Os *Subjects* são como *EventEmitters*: eles mantêm um registro de muitos *Observers*.

**Cada *Subject* é um *Observable:***

Dado um *Subject*, você pode inscrevêr a ele, fornecendo um *Observer*, que passará a receber valores normalmente. Da perspectiva do *Observer*, ele não pode dizer se a execução do *Observable* vem de um *Observable* *unicast* simples ou de um *Subject*.

Internamente ao *Subject*, `subscribe` não invoca uma nova execução que entrega valores. Ele simplesmente registra o *Observer* fornecido em uma lista de *Observers*, da mesma forma que `addListener` funciona em outras bibliotecas e linguagens.

**Cada *Subject* é um *Observer*:**

Um *Subject* é um objeto com os métodos de `next(v)`, `error(e)` e `complete()`. Para alimentar um novo valor ao *Subject*, basta chamar `next(theValue)`, e será feito o *multicast* para os *Observers* cadastrados para ouvir o *Subject*.

No exemplo abaixo, temos dois *Observers* anexados a um *Subject* e fornecemos alguns valores ao *Subject*:

```js
const subject = new Subject<number>();

subject.subscribe({
  next: v => console.log(`observerA: ${v}`)
});
subject.subscribe({
  next: v => console.log(`observerB: ${v}`)
});

subject.next(1);
subject.next(2);

// Saída:
// observerA: 1
// observerB: 1
// observerA: 2
// observerB: 2
```

Visto que um *Subject* é um *Observer*, isso também significa que você pode fornecer um *Subject* como argumento para o `subscribe` em um *Observable*, como mostra o exemplo abaixo:

```js
const subject = new Subject<number>();
 
subject.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});
subject.subscribe({
  next: (v) => console.log(`observerB: ${v}`)
});
 
const observable = from([1, 2, 3]);
 
observable.subscribe(subject); 
 
// Saída:
// observerA: 1
// observerB: 1
// observerA: 2
// observerB: 2
// observerA: 3
// observerB: 3
```

Com a abordagem acima, basicamente convertemos uma execução *Observable* *unicast* em *multicast*, por meio do *Subject*. Isso demonstra como os *Subjects* são a única maneira de fazer qualquer execução *Observable* ser compartilhada para vários *Observers*.

Há também algumas especializações do `Subject` como: `BehaviorSubject`, `ReplaySubject`, e `AsyncSubject`.

### Observables Multicast

Um *Observable* *multicast* passa notificações por meio de um *Subject* que pode ter muitos *Observers*, enquanto um *Observable* *unicast* simples envia notificações para um único *Observers*.

Um *Observable* *multicast* usa um *Subject* sob o capô para fazer com que vários *Observers* vejam a mesma execução de *Observable*.

Nos bastidores, é assim que o operador `multicast` funciona: *Observers* se inscrevem em um *Subject* e este *Subject* se inscreve na fonte *Observable*. O exemplo a seguir é semelhante ao exemplo anterior que usou `observable.subscribe(subject)`:

```js
const source = from([1, 2, 3]);
const subject = new Subject();
const multicasted = source.pipe(multicast(subject));
 
// temos implicitamente `subject.subscribe({...})`:
multicasted.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});
multicasted.subscribe({
  next: (v) => console.log(`observerB: ${v}`)
});
 
// temos implicitamente `source.subscribe(subject)`:
multicasted.connect();

// Saída:
// observerA: 1
// observerB: 1
// observerA: 2
// observerB: 2
// observerA: 3
// observerB: 3
```

`multicast` retorna um *Observable* que se parece com um *Observable* normal, mas funciona como um *Subject* quando inscrevemos. `multicast` retorna a `ConnectableObservable`, que é simplesmente um *Observable* com o método `connect()`.

O método `connect()` é importante para determinar exatamente quando a execução *Observable* compartilhada começará. Como o `connect()` faz `source.subscribe(subject)` nos bastidores, `connect()` retorna uma inscrição, da qual você pode desinscrever para cancelar a execução *Observable* compartilhada.

### Contagem de referência

Chamar `connect()` manualmente e lidar com a inscrição costuma ser complicado. Normalmente, queremos nos conectar automaticamente quando o primeiro *Observer* chegar e cancelar automaticamente a execução compartilhada quando o último *Observer* cancelar a inscrição.

Considere o seguinte exemplo onde as inscrições ocorrem conforme descrito nesta lista:

1. O primeiro *Observer* se inscreve no *Observable* *multicast*
1. O *Observable* *multicast* está conectado
1. O valor `next` 0 é entregue ao primeiro *Observer*
1. O segundo *Observer* se inscreve no *Observable* *multicast*
1. O valor `next` 1 é entregue ao primeiro *Observer*
1. O valor `next` 1 é entregue ao segundo *Observer*
1. O primeiro *Observer* cancela a inscrição do *Observable* *multicast*
1. O valor `next` 2 é entregue ao segundo *Observer*
1. O segundo *Observer* cancela a inscrição do *Observable* *multicast*
1. A conexão com o *Observable* *multicast* foi cancelada

Para conseguir isto com chamadas explícitas para `connect()`, escrevemos o seguinte código:

```js
const source = interval(500);
const subject = new Subject();
const multicasted = source.pipe(multicast(subject));
let subscription1, subscription2, subscriptionConnect;
 
subscription1 = multicasted.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});

// Devemos chamar `connect()` aqui, porque o primeiro inscrito de `multicast` está interessado em consumir valores
subscriptionConnect = multicasted.connect();
 
setTimeout(() => {
  subscription2 = multicasted.subscribe({
    next: (v) => console.log(`observerB: ${v}`)
  });
}, 600);
 
setTimeout(() => {
  subscription1.unsubscribe();
}, 1200);
 
// Devemos cancelar a inscrição da execução Observable compartilhada aqui, porque `multicast` não teria mais assinantes
setTimeout(() => {
  subscription2.unsubscribe();
  subscriptionConnect.unsubscribe(); // para a execução Observable compartilhada
}, 2000);

// Saída:
// observerA: 0
// observerA: 1
// observerB: 1
// observerB: 2
```

Se quisermos evitar chamadas explícitas para `connect()`, podemos usar o método `refCount()` de ConnectableObservable (contagem de referência), que retorna um *Observable* que rastreia quantos inscritos ele tem. Quando o número de inscritos aumentar de 0 para 1, ele chamará ` connect()`, o que iniciará a execução compartilhada. Somente quando o número de inscritos diminuir de 1 para 0 a inscrição será totalmente cancelada, interrompendo a execução posterior.

!!! note ""
  `refCount()` faz com que o *Observable* *multicast* comece a ser executado automaticamente quando o primeiro assinante chega e pare de executar quando o último assinante sai.

Abaixo está um exemplo:

```js
const source = interval(500);
const subject = new Subject();
const refCounted = source.pipe(multicast(subject), refCount());
let subscription1, subscription2;
 
// Irá chamar `connect()`, porque é o primeiro inscrito de `refCounted`
console.log('observerA subscribed');
subscription1 = refCounted.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});
 
setTimeout(() => {
  console.log('observerB subscribed');
  subscription2 = refCounted.subscribe({
    next: (v) => console.log(`observerB: ${v}`)
  });
}, 600);
 
setTimeout(() => {
  console.log('observerA unsubscribed');
  subscription1.unsubscribe();
}, 1200);
 
// Quando a execução Observable compartilhada irá parar, porque `refCounted` não teria mais inscritos
setTimeout(() => {
  console.log('observerB unsubscribed');
  subscription2.unsubscribe();
}, 2000);
 
// Saída:
// observerA subscribed
// observerA: 0
// observerB subscribed
// observerA: 1
// observerB: 1
// observerA unsubscribed
// observerB: 2
// observerB unsubscribed
```

O método `refCount()` existe apenas em `ConnectableObservable` e retorna um *Observable*, não outro `ConnectableObservable`.

### BehaviorSubject

Uma das variantes de *Subjects* é o `BehaviorSubject`, que tem uma noção de "valor corrente". Ele armazena o último valor emitido para seus consumidores, e sempre que um novo *Observer* se inscrever, ele receberá imediatamente o "valor corrente" do `BehaviorSubject`.

!!! note ""
  *BehaviorSubjects* são úteis para representar "valores ao longo do tempo". Por exemplo, um fluxo de evento de aniversários é um *Subject*, mas o fluxo da idade de uma pessoa seria um `BehaviorSubject`.

No exemplo a seguir, o `BehaviorSubject` é inicializado com o valor 0 que o primeiro *Observer* recebe ao se inscrever. O segundo *Observer* recebe o valor 2, embora tenha se inscrito depois que o valor 2 foi enviado.

```js
const subject = new BehaviorSubject(0); // 0 é o valor inicial
 
subject.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});
 
subject.next(1);
subject.next(2);
 
subject.subscribe({
  next: (v) => console.log(`observerB: ${v}`)
});
 
subject.next(3);
 
// Saída:
// observerA: 0
// observerA: 1
// observerA: 2
// observerB: 2
// observerA: 3
// observerB: 3
```

### ReplaySubject

Um `ReplaySubject` é semelhante a um `BehaviorSubject` no sentido de que pode enviar valores antigos para novos inscritos, mas também pode registrar uma parte da execução *Observable*.

!!! note ""
  Um `ReplaySubject` registra vários valores da execução *Observable* e os reproduz para novos inscritos.

Ao criar um `ReplaySubject`, você pode especificar quantos valores reproduzir:

```js
const subject = new ReplaySubject(3); // armazena 3 últimos valores para os novos inscritos
 
subject.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});
 
subject.next(1);
subject.next(2);
subject.next(3);
subject.next(4);
 
subject.subscribe({
  next: (v) => console.log(`observerB: ${v}`)
});
 
subject.next(5);
 
// Saída:
// observerA: 1
// observerA: 2
// observerA: 3
// observerA: 4
// observerB: 2
// observerB: 3
// observerB: 4
// observerA: 5
// observerB: 5
```

Você também pode especificar um tempo de janela em milissegundos além do tamanho do buffer, para determinar a idade dos valores registrados. No exemplo a seguir, usamos um tamanho de buffer grande de *100*, mas um parâmetro de tempo de janela de apenas *500* milissegundos.

```js
const subject = new ReplaySubject(100, 500);

subject.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});
 
let i = 1;
setInterval(() => subject.next(i++), 200);
 
setTimeout(() => {
  subject.subscribe({
    next: (v) => console.log(`observerB: ${v}`)
  });
}, 1000);
 
// Saída:
// observerA: 1
// observerA: 2
// observerA: 3
// observerA: 4
// observerA: 5
// observerB: 3
// observerB: 4
// observerB: 5
// observerA: 6
// observerB: 6
// ...
```

### AsyncSubject

O `AsyncSubject` é uma variante em que apenas o último valor da execução *Observable* é enviado a seus *Observers* e apenas quando a execução é concluída.

```js
const subject = new AsyncSubject();
 
subject.subscribe({
  next: (v) => console.log(`observerA: ${v}`)
});
 
subject.next(1);
subject.next(2);
subject.next(3);
subject.next(4);
 
subject.subscribe({
  next: (v) => console.log(`observerB: ${v}`)
});
 
subject.next(5);
subject.complete();
 
// Saída:
// observerA: 5
// observerB: 5
```

O `AsyncSubject` é semelhante ao operador` last()`, pois aguarda a notificação `complete` para entregar um único valor.

### Void subject

Às vezes, o valor emitido não importa tanto quanto o fato de que um valor foi emitido.

Por exemplo, o código abaixo sinaliza que um segundo se passou.

```js
const subject = new Subject<string>();
setTimeout(() => subject.next('dummy'), 1000);
```

Passar um valor fictício dessa forma é desajeitado e pode confundir os usuários.

Ao declarar um assunto vazio, você sinaliza que o valor é irrelevante. Apenas o evento em si importa.

```js
const subject = new Subject<void>();
setTimeout(() => subject.next(), 1000);
```

Um exemplo completo com contexto é mostrado abaixo:

```js
const subject = new Subject(); // atalho para Subject<void>

subject.subscribe({
  next: () => console.log('One second has passed')
});

setTimeout(() => subject.next(), 1000);
```

## Scheduler

Um *Scheduler* controla quando uma assinatura começa e quando as notificações são entregues. Consiste em três componentes.

- **Um Scheduler é uma estrutura de dados**. Ele sabe como armazenar e enfileirar tarefas com base na prioridade ou em outros critérios.
- **Um Scheduler é um contexto de execução**. Ele denota onde e quando a tarefa é executada (por exemplo, imediatamente, ou em outro mecanismo de retorno de chamada, como `setTimeout` ou `process.nextTick`, ou um *frame* de animação).
- **Um Scheduler possui um relógio (virtual)**. Ele fornece uma noção de "tempo" por um método *getter* `now()` no *Scheduler*. As tarefas sendo agendadas em um *Scheduler* específico respeitarão apenas a hora indicada por aquele relógio.

!!! note ""
  Um *Scheduler* permite definir em qual contexto de execução um *Observable* entregará notificações ao seu *Observer*.

No exemplo abaixo, o *Observable* emite os valores 1, 2, 3 sincronicamente, e usa o operador `observeOn` para especificar o *Scheduler* assíncrono para entregar esses valores.

```js
import { Observable, asyncScheduler } from 'rxjs';
import { observeOn } from 'rxjs/operators';

const observable = new Observable((observer) => {
  observer.next(1);
  observer.next(2);
  observer.next(3);
  observer.complete();
}).pipe(
  observeOn(asyncScheduler)
);
 
console.log('antes do subscribe');
observable.subscribe({
  next(x) {
    console.log('valor ' + x)
  },
  error(err) {
    console.error('algo errado ocorreu: ' + err);
  },
  complete() {
     console.log('fim');
  }
});
console.log('depois do subscribe');

// Saída:
// antes do subscribe
// depois do subscribe
// valor 1
// valor 2
// valor 3
// fim
```

Observe como as notificações` valor...` foram entregues depois de `depois do subscribe`. Isso ocorre porque `observeOn(asyncScheduler)` introduz um *Observer* proxy entre `new Observable` e o *Observer* final. Vamos renomear alguns identificadores para tornar essa distinção óbvia no código de exemplo:

```js
var observable = new Observable((proxyObserver) => {
  proxyObserver.next(1);
  proxyObserver.next(2);
  proxyObserver.next(3);
  proxyObserver.complete();
}).pipe(
  observeOn(asyncScheduler)
);
 
var finalObserver = {
  next(x) {
    console.log('valor ' + x)
  },
  error(err) {
    console.error('algo errado ocorreu: ' + err);
  },
  complete() {
     console.log('fim');
  }
};
 
console.log('antes do subscribe');
observable.subscribe(finalObserver);
console.log('depois do subscribe');
```

O `proxyObserver` é criado em `observeOn(asyncScheduler)` e sua função `next(val)` é aproximadamente a seguinte:

```js
const proxyObserver = {
  next(val) {
    asyncScheduler.schedule(
      (x) => finalObserver.next(x),
      0 /* delay */,
      val /* será o x para a função acima */
    );
  },

  // ...
}
```

O `asyncScheduler` opera com um `setTimeout` ou `setInterval`, mesmo se o dado *delay* for zero. Em JavaScript, `setTimeout(fn, 0)` é conhecido por executar a função `fn` "mais cedo" na próxima iteração do loop de evento. Isso explica por que `valor 1` é entregue ao `finalObserver` depois que `depois do subscribe` aconteceu.

O método `schedule()` de um *Scheduler* recebe um argumento `delay`, que se refere a uma quantidade de tempo relativa ao relógio interno do próprio *Scheduler*. O relógio de um *Scheduler* não precisa ter nenhuma relação com a hora do relógio real. É assim que os operadores temporais como o `delay` operam não no tempo real, mas no tempo ditado pelo relógio do *Scheduler*. Isso é especialmente útil em testes, onde um *Scheduler* de horário virtual pode ser usado para falsificar o horário do relógio real enquanto, na realidade, executa as tarefas agendadas de maneira síncrona.

### Tipos de Schedulers

O `asyncScheduler` é um dos *Schedulers* integrados fornecidos pelo RxJS. Cada um deles pode ser criado e retornado usando propriedades estáticas do objeto `Scheduler`.

| SCHEDULER        | OBJETIVO                                                                                                                                                                                 |
|------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `null`           | Por não passar nenhum *scheduler*, as notificações são entregues de forma síncrona e recursiva. Use isto para operações de tempo constante ou operações recursivas de cauda.             |
| `queueScheduler` | *Schedulers* em uma fila no *frame* de eventos atual (*scheduler* trampolim). Use isto para operações de iteração.                                                                       |
| `asapScheduler`  | *schedulers* na fila de microtarefas, que é a mesma fila usada para *Promises*. Basicamente, após o trabalho atual, mas antes do próximo trabalho. Use isto para conversões assíncronas. |
| `asyncScheduler` |*schedulers* funcionam com `setInterval`. Use isto para operações baseadas em tempo.
| animationFrameScheduler | Agenda a tarefa que acontecerá antes da próxima repintura do conteúdo do navegador. Pode ser usado para criar animações suaves de navegador. |

### Usando Schedulers

Você pode já ter usado *Schedulers* em seu código RxJS sem declarar explicitamente o tipo de *Scheduler* a ser usado. Isso ocorre porque todos os operadores *Observable* que lidam com simultaneidade têm *Schedulers* opcionais. Se você não fornecer o *Scheduler*, RxJS escolherá um *Scheduler* padrão usando o princípio da menor simultaneidade. Isso significa que o *Scheduler* que apresenta a menor quantidade de simultaneidade que satisfaça as necessidades do operador é escolhido. Por exemplo, para operadores que retornam um *Observable* com um número finito e pequeno de mensagens, RxJS não usa nenhum *Scheduler*, como por exemplo `null` ou  `undefined`. Para operadores que retornam um número potencialmente grande ou infinito de mensagens, o `queueScheduler` é usado. Para *Observables* que usam temporizadores, `async` é usado.

Como o RxJS usa o *Scheduler* de menos simultaneidade, você pode escolher um *Scheduler* diferente se quiser introduzir a simultaneidade para fins de desempenho. Para especificar um *Scheduler* específico, você pode usar os métodos do operador que usam um *Scheduler*, por exemplo` from([10, 20, 30], asyncScheduler)`.

**Operadores de criação estáticos geralmente usam um *Scheduler* como argumento**. Por exemplo, `from(array, scheduler)` permite que você especifique o *Scheduler* a ser usado ao entregar cada notificação convertida do `array`. Geralmente é o último argumento para o operador. Os seguintes operadores de criação estática usam um argumento *Scheduler*:

- bindCallback
- bindNodeCallback
- combineLatest
- concat
- empty
- from
- fromPromise
- interval
- merge
- of
- range
- throw
- timer

**Use `subscribeOn` para agendar em que contexto a chamada `subscribe()` acontecerá**. Por padrão, uma chamada `subscribe()` em um *Observable* acontecerá de forma síncrona e imediata. No entanto, você pode adiar ou agendar a assinatura real para acontecer em um determinado *Scheduler*, usando o operador de instância `subscribeOn(scheduler)`, onde `scheduler` é um argumento que você fornece.

**Use `observeOn` para agendar em que contexto as notificações serão entregues**. Como vimos nos exemplos acima, o operador de instância `observeOn(scheduler)` introduz um mediador *Observer* entre o *Observable* de origem e o *Observer* de destino, onde o mediador agenda chamadas para o *Observer* de destino usando o seu dado *scheduler*.

**Operadores de instância podem usar um *Scheduler* como argumento.**

Operadores de relacionados a tempo, como `bufferTime`, `debounceTime`, `delay`, `auditTime`, `sampleTime`, `throttleTime`, `timeInterval`, `timeout`, `timeoutWith`, `windowTime` tomam um *Scheduler* como o último argumento, ou caso contrário, operam por padrão no `asyncScheduler`.

Outros operadores de instância que levam um *Scheduler* como argumento: `cache`, `combineLatest`, `concat`, `expand`, `merge`, `publishReplay`, `startWith`.

Observe que ambos `cache` e `publishReplay` aceitam um *Scheduler* porque utilizam um `ReplaySubject`. O construtor de um *ReplaySubjects* leva um *Scheduler* opcional como o último argumento porque `ReplaySubject` pode lidar com o tempo, o que só faz sentido no contexto de um *Scheduler*. Por padrão, um `ReplaySubject` usa o `queueScheduler` para fornecer um temporizador.

## Referências

- <https://www.syncfusion.com/blogs/post/angular-promises-versus-observables.aspx>
- <https://rxjs-dev.firebaseapp.com/guide>
- <https://itnext.io/javascript-promises-vs-rxjs-observables-de5309583ca2>
