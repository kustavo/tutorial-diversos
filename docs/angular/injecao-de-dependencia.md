# Injeção de dependência

A injeção de dependência permite declarar as dependências de suas classes TypeScript sem cuidar de sua instanciação. Em vez disso, o Angular trata da instanciação para você. Este _design pattern_ permite escrever um código mais testável e flexível.

Para ilustrar como a injeção de dependência funciona, considere o exemplo a seguir. O primeiro arquivo `logger.service.ts`, define uma classe `Logger`. Esta classe contém uma função `writeCount` que registra um número no console.

```ts
import { Injectable } from "@angular/core";

@Injectable({ providedIn: "root" })
export class Logger {
  writeCount(count: number) {
    console.warn(count);
  }
}
```

Em seguida, o arquivo `hello-world-di.component.ts` define um componente Angular. Este componente contém um `button` que usa a função `writeCount` da classe `Logger`. Para acessar essa função, o serviço `Logger` é injetado na classe `HelloWorldDI` adicionando `private logger: Logger` ao construtor.

```ts
import { Component } from "@angular/core";
import { Logger } from "../logger.service";

@Component({
  selector: "hello-world-di",
  templateUrl: "./hello-world-di.component.html",
})
export class HelloWorldDependencyInjectionComponent {
  count = 0;

  constructor(private logger: Logger) {}

  onLogMe() {
    this.logger.writeCount(this.count);
    this.count++;
  }
}
```

## Referências

- <https://angular.io/guide/what-is-angular#dependency-injection>
