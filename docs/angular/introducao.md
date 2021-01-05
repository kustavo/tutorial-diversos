# Introdução

[TOC]

## Sobre

Angular é uma plataforma e framework para construção da interface de aplicações usando HTML, CSS e, principalmente, JavaScript, criada pelos desenvolvedores da Google.

Ele possui alguns elementos básicos que tornam essa construção interessante como: componentes, templates, diretivas, roteamento, módulos, serviços, injeção de dependências e ferramentas de infraestrutura que automatizam tarefas, como a de executar os testes unitários de uma aplicação.

Angular nos ajuda a criar Single-Page Applications com uma qualidade e produtividade surpreendente.

## Angular CLI

### Instalação

```bash
npm install -g @angular/cli
```

Especificando a versão de instalação. [Ver versões](https://github.com/angular/angular-cli/releases).

```bash
npm install -g @angular/cli@v10.0.0-rc.3
```

### Criar projeto

```bash
ng new <projeto>
```

### Executar projeto

```bash
cd <projeto>
ng serve
```

### Criar arquivos

```bash
ng generate <tipo> <nome>
```

Tipos possíveis:

- appShell
- application
- class
- component
- directive
- enum
- guard
- interceptor
- interface
- library
- module
- pipe
- service
- serviceWorker
- webWorker

#### Criar componente

```bash
ng generate component <name>
```

#### Adicionar módulo de rotas

```bash
ng generate module app-routing --flat --module=app
```

Modificar o arquivo criado conforme exemplo abaixo:

```ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';

// Rotas adicionadas
const routes: Routes = [
  { path: 'heroes', component: HeroesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

## Elementos de uma aplicação Angular

Os elementos básicos (building blocks) de uma aplicação Angular são:

- Módulos
- Componentes
- Templates
- Metadata
- Data binding
- Diretivas
- Serviços
- Injeção de dependências

<div class='imagem' markdown='1' style="width: 70%">

![exemplo_sem_dip](_introducao/esquema-elementos-angular.png)

</div>

CONTINUAR!!!

- <https://blog.algaworks.com/o-que-e-angular/>

## Links

- <https://blog.algaworks.com/o-que-e-angular/>
- <https://medium.com/@danilodev.silva/aprendendo-angular-2-na-pr%C3%A1tica-antes-veja-depois-crie-49567b25dc1>
- <https://angular.io/start>
- <https://angular.io/guide/styleguide>
