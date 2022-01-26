# Componentes

Os componentes são classes escritas em TypeScript que recebem o decorator `@Component` que informa ao Angular que a classe é um componente. Neste decorator existem algumas propriedades mais utilizadas como: `selector`, `templateUrl` e `style`.

- `selector`: é como identificamos o componente. Para todo o componente existe um elemento único associado que permite que ele seja adicionado em um documento HTML. Nesse caso, o nome do elemento desse componente é `exemplo` e deve ser escrito como `<exemplo></exemplo>`.

- `templateUrl`: é o nome do documento HTML que será a parte visual do componente. Nele podemos ter código em HTML juntamente com todos os _bindings_ e diretivas necessários para a exibição do componente no navegador

- `template`: também usado para descrever a parte visual do componente, porém nesse caso podemos fornecer código HTML "hard coded", como texto.

- `styleUrl`: é onde informamos quais folhas de estilo contêm o código CSS que será aplicado ao template do componente.

```ts
@Component({
  selector: "exemplo",
  templateUrl: "./exemplo.component.html",
  styleUrls: ["./exemplo.component.scss"],
})
export class ExemploComponent {}
```

Com isso podemos concluir que a estrutura de um componente Angular deve ser formada por esses três elementos, template (HTML), estilo (CSS) e classe (TypeScript). Essas partes são organizadas em arquivos separados e unidas nos metadados do componente através do decorator `@Component`.

Outra forma de descrever o template do componente usando a propriedade template, sem a necessidade de criar um arquivo HTML no projeto.

```ts
@Component({
  selector: "exemplo",
  template: '<input type="text"[(ngModel)]="nome" name="nome" id="nome"/>',
  styleUrls: ["exemplo.component.css"],
})
export class ExemploComponent {}
```

## button

??? tip "Não acionar o botão ao acionar a tecla `enter`"

    Acionar o atributo `type="button"` como no exemplo abaixo:

        ```html
        <button type="button"></button>
        ```

## Referências

- <https://www.devmedia.com.br/angular-components-conhecendo-e-configurando-no-seu-projeto/40734>
