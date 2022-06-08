# Rotas

!!! warning "Página em construção"

    TODO: Página em construção!

## Adicionar módulo de rotas

```bash
ng generate module app-routing --flat --module=app
```

## Criando rotas

As rotas são configuradas no arquivo: `src\app\app-routing.module.ts`

```ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComponentTeste } from './path/component-teste.component';

// Rotas adicionadas
const routes: Routes = [
  { path: 'rota-um', component: ComponentTeste },
  { path: 'rota-dois', loadChildren: () => import('./path/modulo-teste.module').then((m) => m.ModuloTeste) } // modo Lazy
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```
