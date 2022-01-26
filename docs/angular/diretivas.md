# Diretivas

As diretivas são marcadores em um elemento DOM (como um atributo) que informam ao Angular para anexar um comportamento especificado a um elemento existente.

As diretivas são usadas com componentes, principalmente para criar _tags_ personalizadas em uma aplicação Angular. Existem muitas diretivas prontas que podemos usar, mas também podemos criar nossas próprias diretivas.

Algumas diretivas podem mudar completamente a estrutura da saída do template do componente. Essas diretivas podem alterar o layout do DOM adicionando e removendo elementos DOM de visualização. Podemos classificar essas diretivas em estruturais e diretivas de atributo:

## Diretivas estruturais

Diretivas estruturais são responsáveis por geralmente manipular o DOM, adicionando ou removendo elementos. Podemos reconhecê-los através do asterisco (`*`) que segue com o atributo da diretiva. Abaixo alguns exemplos de diretivas estruturais:

- `ngIf`: condicionalmente cria ou descarta _subviews_ do template
- `ngFor`: repete um nó para cada item de uma lista.
- `ngSwitch`: é um conjunto de três diretivas:
  - `ngSwitch`: uma diretiva de atributo que altera o comportamento de suas diretivas complementares.
  - `ngSwitchCase`: diretiva estrutural que adiciona seu elemento ao DOM quando seu valor de limite é igual ao valor de _switch_ e remove seu valor de limite quando ele não é igual ao valor de _switch_.
  - `ngSwitchDefault`: diretiva estrutural que adiciona seu elemento ao DOM quando não há `NgSwitchCase` selecionado.

### Diretiva estrutural customizada

Neste exemplo iremos replicar o comportamento da diretiva `ngFor`.

Criando o arquivo da nova diretiva, que vamos chamar de `MyForDirective`. E adicioná-la no `declarations` do `AppModule` (ou no módulo escolhido).

```ts
// AppModule.ts
@NgModule({
  declarations: [AppComponent, MyForDirective],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
```

Criando a diretiva `MyForDirective` com o seletor `myFor`. Precisamos injetar os serviços `ViewContainerRef` e `TemplateRef` no construtor da nossa diretiva, para que possamos manipular o DOM. - `ViewContainerRef`: é um serviço que nos provê funcionalidades relacionadas à manipulação do DOM. E podemos adicionar templates, limpar (remover) etc. - `TemplateRef`: é o que queremos exibir no contexto onde a diretiva esta sendo utilizada.

Devemos criar `@Input` `myForFrom` para receber os itens a serem iterados. E consequentemente exibidos na tela. A partir do `ViewContainerRef`, vamos criar _embeddedViews_ e passar nosso template, o que queremos exibir a cada iteração, passando também o item que está sendo iterado, para podermos utilizar no HTML, vejamos:

```ts
// MyForDirective.ts
@Directive({
  selector: "[myFor]",
})
export class MyForDirective {
  constructor(
    private view: ViewContainerRef,
    private template: TemplateRef<any>
  ) {}

  @Input()
  set myForFrom(collection: Array<any>) {
    if (Array.isArray(collection)) {
      collection.forEach((item, index) => {
        this.view.createEmbeddedView(this.template, {
          $implicit: item,
          index,
        });
      });
    }
  }
}
```

O método `createEmbeddedView`, espera dois parâmetros, o primeiro é o template. O segundo são os valores a serem passados ao contexto, para que possam ser utilizados no HTML e exibi-los.

O que informamos na propriedade `$implicit` será repassado como valor _default_ da iteração ao contexto. Já os seguintes valores, como `index`, deverão ser recuperados pelo nome.

Utilizando a diretiva:

```ts
// my-for.directive.ts
@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent {
  lista = [
    { name: "Maria", email: "maria@teste.com" },
    { name: "Jose", email: "jose@teste.com" },
  ];
}
```

```html
<!-- app.component.html -->
<ul>
  <ng-template myFor [myForFrom]="lista" let-elemento let-index="index">
    <li>{{ index }}: {{ elemento.name }} - {{ elemento.email }}</li>
  </ng-template>
</ul>

<!-- ou utilizando sugar syntax -->

<ul>
  <li *myFor="let elemento from lista; let index=index">
    {{ index }}: {{ elemento.name }} - {{ elemento.email }}
  </li>
</ul>
```

No primeiro exemplo é possivel observar que é utilizado a tag `ng-template` em conjunto com os atributos `myFor`, que passa a acionar a nossa diretiva. É utilizado, também, o `myForFrom`, que recebe os itens a serem iterados. O atributo `let-elemento` recebe o valor que definimos na propriedade `$implicit`. Então, como podemos perceber, ele não define de onde pegará o valor. Apenas define uma propriedade para recuperar o valor _default_ `$implicit`. Caso precise receber o `index` da iteração, é preciso definir um atributo e informar qual valor deseja recuperar. Exemplo: `let-index="index"`

No segundo exemplo utilizamos um _syntax sugar_, que deixa o uso mais simples. Para ativar o modo sugar syntax, devemos usar o asterisco (`*`). O Angular concatena o `myFor`com `from` e, com isso, consegue identificar que o itens devem ser repassados ao `@Input myForFrom`. Se o método `myForFrom` chamasse `myForDe` por exemplo, teríamos `let elemento de lista`.

No exemplo a seguir iremos replicar o comportamento da diretiva `ngIf`.

```ts
// my-if.directive.ts
@Directive({
  selector: "[myIf]",
})
export class MyIfDirective {
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}

  @Input()
  set myIf(condition: boolean) {
    if (condition) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else if (!condition) {
      this.viewContainer.clear();
    }
  }
}
```

```html
<!-- app.component.html -->
<div *myIf="true">Verdadeiro!</div>
<div *myIf="false">Falso!</div>
```

## Diretivas de atributos

Com as diretivas de atributo, você pode alterar a aparência ou o comportamento dos elementos DOM e componentes angulares.

As diretivas de atributos podem simplesmente alterar a aparência ou o comportamento dos elementos DOM e componentes angulares. Abaixo alguns exemplos de diretivas de atributos:

- `ngClass`
- `ngStyle`
- `ngControlName`
- `ngModel`

O exemplo abaixo mostra uma diretiva de atributo que muda o estilo de um elemento quando o mouse entra na área pertencente ao elemento.

```ts
// highlight.directive.ts
@Directive({
  selector: "[highlight]",
})
export class HighlightDirective {
  @Input() highlight = "";

  constructor(private el: ElementRef) {}

  @HostListener("mouseenter")
  onMouseEnter() {
    this.highlighter(this.highlight || "yellow");
  }

  @HostListener("mouseleave")
  onMouseLeave() {
    this.highlighter("unset");
  }

  private highlighter(color: string) {
    this.el.nativeElement.style.backgroundColor = color;
  }
}
```

```html
<!-- app.component.html -->
<p><span highlight>Highlight yellow!</span></p>
<p><span highlight="lightblue">Highlight lightblue!</span></p>

<!-- Passando cor como variável -->
<p><span [highlight]="cor">Highlight me!</span></p>
```

O `ElementRef` concede acesso direto ao elemento DOM do _host_ por meio de sua propriedade `nativeElement`. Também são adicionados os manipuladores com o decorador de eventos `@HostListener()` que respondem quando o mouse entra ou sai da área pertencente ao elemento.

## Referências

- <http://www.macoratti.net/18/06/ang_diret1.htm>
- <https://imasters.com.br/desenvolvimento/criando-diretivas-estruturais-com-o-angular>
