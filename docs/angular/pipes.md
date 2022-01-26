# Pipes

_Pipes_ `|` (barras verticais) são usados para transformar _strings_, valores monetários, datas e outros dados para exibição. _Pipes_ são funções simples para usar em expressões de _template_ para aceitar um valor de entrada e retornar um valor transformado. _Pipes_ são úteis porque você pode usá-los em todo o seu aplicativo, enquanto declara cada _pipe_ apenas uma vez.

O Angular fornece pipes integrados para transformações de dados típicas, incluindo transformações para internacionalização (i18n), que usam informações de localidade para formatar dados. A seguir estão os _pipes_ internos comumente usados para formatação de dados:

- **DatePipe**: formata um valor de data de acordo com as regras locais.
- **UpperCasePipe**: transforma o texto em maiúsculas.
- **LowerCasePipe**: transforma o texto em minúsculas.
- **CurrencyPipe**: transforma um número em uma string de moeda, formatada de acordo com as regras locais.
- **DecimalPipe**: transforma um número em uma string com um ponto decimal, formatado de acordo com as regras de localidade.
- **PercentPipe**: transforma um número em uma string de porcentagem, formatada de acordo com as regras do local.

## Criando pipes customizados

É possível criar _pipes_ personalizados para encapsular transformações que não são fornecidas com os _pipes_ integrados. Em seguida, use seu _pipe_ personalizado em expressões de _template_, da mesma forma que você usa _pipes_ internos para transformar valores de entrada em valores de saída para exibição.

Angular invoca o método `transform()` com o valor de um _binding_ como o primeiro argumento e quaisquer parâmetros como o segundo argumento na forma de lista e retorna o valor transformado.

=== "exponencial.pipe.ts"

    ```ts
    import { Pipe, PipeTransform } from '@angular/core';

    @Pipe({
    name: 'exponencial'
    })
    export class ExponencialPipe implements PipeTransform {
        transform(value: number, exponent = 1): number {
            return Math.pow(value, exponent);
        }
    }
    ```

=== "app.component.html"

    ```html
    <div>
        <p>Exponencial: {{2 | exponencial: 4}}</p>
        <!-- saída: 16 -->
    </div>
    ```

## Detectando mudanças

Por padrão, os _pipes_ são definidos como puros para que o Angular execute o _pipe_ apenas quando detectar uma alteração pura no valor de entrada. Uma mudança pura é uma mudança em um valor de entrada primitivo (como _String_, _Number_, _Boolean_ ou _Symbol_) ou uma referência de objeto alterada (como _Date_, _Array_, _Function_ ou _Object_).

Um _pipe_ puro deve usar uma função pura, que processa entradas e retorna valores sem efeitos colaterais. Em outras palavras, dada a mesma entrada, uma função pura deve sempre retornar a mesma saída.

Com um _pipe_ puro, o Angular ignora as alterações em objetos compostos, como um elemento recém-adicionado de um _array_ existente, porque verificar um valor primitivo ou referência de objeto é muito mais rápido do que realizar uma verificação profunda para diferenças dentro dos objetos. Verificando um valor primitivo ou referência de objeto, o Angular pode determinar rapidamente se pode pular a execução do _pipe_ e atualizar a visualização.

No entanto, um _pipe_ puro com uma matriz como entrada pode não funcionar da maneira que deseja. No exemplo abaixo, ao adicionar novos animais alados, eles não são filtrados pelo _pipe_.

=== "animal.ts"

    ```ts
    export class Animal {
      public podeVoar = false;
      public nome = '';
    }
    ```

=== "animal-alado.pipe.ts"

    ```ts
    import { Pipe, PipeTransform } from '@angular/core';
    import { Animal } from './animal';

    @Pipe({
      name: 'animalAlado',
    })
    export class AnimalAladoPipe implements PipeTransform {
      transform(animais: Animal[]) {
        return animais.filter((animal) => animal.podeVoar);
      }
    }
    ```

=== "app.component.ts"

    ```ts
    import { Component } from '@angular/core';
    import { Animal } from './animal';

    @Component({
      selector: 'app-root',
      templateUrl: './app.component.html',
      styleUrls: ['./app.component.scss'],
    })
    export class AppComponent {
      animais: Animal[] = [];
      podeVoar = true;

      constructor() {
        this.animais.push({ nome: 'Mosca', podeVoar: true });
        this.animais.push({ nome: 'Gato', podeVoar: false });
        this.animais.push({ nome: 'Ave', podeVoar: true });
        this.animais.push({ nome: 'Cachorro', podeVoar: false });
      }

      addAnimal(nome: string) {
        nome = nome.trim();
        if (!nome) {
          return;
        }
        const animal: Animal = { nome, podeVoar: this.podeVoar };
        this.animais.push(animal);
      }
    }
    ```

=== "app.component.html"

    ```html
    <label for="animal-nome">Animal alado: </label>
    <input type="text" #box>
    <button (click)="addAnimal(box.value)">Adicionar</button>

    <p>--TODOS--</p>
    <div *ngFor="let animal of animais">
      {{animal.nome}} ({{animal.podeVoar ? "voa" : "não voa"}})
    </div>

    <p>--ALADOS--</p>
    <div *ngFor="let animal of (animais | animalAlado)">
      {{animal.nome}} ({{animal.podeVoar ? "voa" : "não voa"}})
    </div>

    <!--
    Saída:

    --TODOS--
    Mosca (voa)
    Gato (não voa)
    Ave (voa)
    Cachorro (não voa)
    Borboleta (voa)
    Besouro (voa)
    Barata (voa)

    --ALADOS--
    Mosca (voa)
    Ave (voa)
    -->
    ```

O detector de alterações ignora as alterações nos elementos de um _array_, de modo que o _pipe_ não funciona. O motivo pelo qual o Angular ignora o elemento alterado do _array_ é que a referência ao _array_ não mudou. Como o _array_ é a mesmo, o Angular não atualiza a exibição.

Uma maneira de obter o comportamento desejado é alterar a própria referência do objeto. Substitua a _array_ por uma nova que contém os elementos recém-alterados e, a seguir, insira a nova _array_ no _pipe_. Entretanto para manter seu componente independente de _templates_ HTML que usam _pipes_, você pode, como alternativa, usar um pipe "impuro" para detectar alterações em objetos compostos, como os _arrays_ por exemplo.

Para tornar um _pipe_ impuro definimos a _flag_ `pure` como `false`.

=== "animal-alado-impure.pipe.ts"

    ```ts
    import { Pipe, PipeTransform } from "@angular/core";
    import { AnimalAladoPipe } from "./animal-alado.pipe";

    @Pipe({
      name: "animalAladoImpure",
      pure: false,
    })
    export class AnimalAladoImpurePipe extends AnimalAladoPipe {}
    ```

=== "app.component.html"

    ```html
    <label for="animal-nome">Animal alado: </label>
    <input type="text" #box>
    <button (click)="addAnimal(box.value)">Adicionar</button>

    <p>--TODOS--</p>
    <div *ngFor="let animal of animais">
      {{animal.nome}} ({{animal.podeVoar ? "voa" : "não voa"}})
    </div>

    <p>--ALADOS--</p>
    <div *ngFor="let animal of (animais | animalAladoImpure)">
      {{animal.nome}} ({{animal.podeVoar ? "voa" : "não voa"}})
    </div>

    <!--
    Saída:

    --TODOS--
    Mosca (voa)
    Gato (não voa)
    Ave (voa)
    Cachorro (não voa)
    Borboleta (voa)
    Besouro (voa)
    Barata (voa)

    --ALADOS--
    Mosca (voa)
    Ave (voa)
    Borboleta (voa)
    Besouro (voa)
    Barata (voa)
    -->
    ```

### Pipe async

O _pipe_ assíncrono permite a assinatura de _observables_ ​​dentro da sintaxe do _template_ angular. Ele também se encarrega de cancelar a assinatura (_unsubscribe_) de observáveis ​​automaticamente.

=== "app.component.ts"

    ```ts
    import { Component } from '@angular/core';
    import { Observable } from 'rxjs';

    @Component({
      selector: 'app-root',
      templateUrl: './app.component.html',
      styleUrls: ['./app.component.scss'],
    })
    export class AppComponent {
      public observable = new Observable<number>();

      ngOnInit() {
        this.observable = new Observable<number>((observer) => {
          let value = 0;
          const interval = setInterval(() => {
            observer.next(value);
            value++;
          }, 1000);

          return () => clearInterval(interval);
        });
      }
    }
    ```

=== "app.component.html"

    ```html
    <p>{{ observable | async }}</p>

    <!--
    Saída:
    1
    2
    3
    ...
    -->
    ```

## Referências

- <https://angular.io/guide/pipes>
- <https://malcoded.com/posts/angular-async-pipe/>
