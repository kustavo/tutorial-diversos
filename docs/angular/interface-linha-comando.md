# Interface de linha de comando

!!! warning "Página em construção"

    Página em construção!

A **Command-Line Interface - CLI**  (interface de linha de comando) conta com comandos para criação, execução e publicação de aplicações Angular.

## Instalação

```bash
npm install -g @angular/cli
```

Especificando a versão de instalação. [Ver versões](https://github.com/angular/angular-cli/releases).

```bash
npm install -g @angular/cli@v10.0.0-rc.3
```

## Desinstalação

```bash
sudo npm uninstall -g @angular/cli
```

## ng new

[Link documentação](https://angular.io/cli/new)

Cria e inicializa uma nova aplicação Angular que é o projeto padrão para um novo *workspace*.

```bash
ng new <name> [options]

ng n <name> [options]
```

??? info "Principais parâmetros"

    - `<name>`: O nome (ou *path*) do novo *workspace* e projeto inicial.
    - `--directory`: O nome do diretório onde será criado o projeto.
    - `--routing`: Criar o módulo de roteamento.
    - `--skip-tests`: Não gerar arquivos de teste "spec.ts".
    - `--style`: Pré-processador de arquivos de estilo (css|scss|sass|less).
    - `--package-manager`: Gerenciador de pacotes (`npm|yarn|pnpm|cnpm`).
    - `--inline-style`: Estilo no mesmo arquivo do componente (padrão `false`).
    - `--inline-template`: Template no mesmo arquivo do componente (padrão `false`).
    - `--prefix`: Prefixo a ser aplicado ao seletor do componente gerado.

## ng serve

[Link documentação](https://angular.io/cli/serve)

Realiza o *build* e executa o aplicativo.

```bash
ng serve <project> [options]

ng s <project> [options]
```

??? info "Principais parâmetros"

    - `<project>`: Nome (ou *path*) do projeto a ser construído. Pode ser um aplicativo ou uma biblioteca.
    - `--hmr`: *Hot module replacement*. Troca, adiciona ou remove módulos enquanto um aplicativo está em execução (padrão `false`).
    - `--host`: Host (padrão `localhost`).
    - `--port`: Porta (padrão `4200`).
    - `--open`: Abrir url no browser (padrão `false`).
    - `--live-reload`: Live-reload (padrão `true`).
    - `--ssl`: Usar HTTPS (padrão `false`).
    - `--ssl-cert`: Certificado SSL a ser usado.
    - `--ssl-key`: Chave SSL a ser usada.
    - `--watch`: *Rebuild* ao alterar código (padrão `true`).

## ng generate

[Link documentação](https://angular.io/cli/generate)

O esquemático ou coleção a ser gerado.

```bash
ng generate <schematic> [options]

ng g <schematic> [options]
```

??? info "Principais parâmetros"

    - `<schematic>`: O esquemático ou coleção a ser gerado:
        - app-shell
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
        - resolver
        - service
        - service-worker
        - web-worker
    - `--interactive`: Habilita modo iterativo.

### ng generate component

[Link documentação](https://angular.io/cli/generate#component)

Cria uma nova definição de componente genérico no projeto fornecido ou padrão.

```bash
ng generate component <name> [options]

ng g component <name> [options]
```

??? info "Principais parâmetros"

    - `<name>`: Nome (ou *path*) do componente.
    - `--change-detection`: Estratégia de detecção de mudança (`Default|OnPush`).
    - `--export`: O `NgModule` declarado exporta este componente (padrão `false`).
    - `--flat`: Crie os novos arquivos sem criar diretório para o componente (padrão `false`)..
    - `--inline-style`: Estilo no mesmo arquivo do componente (padrão `false`).
    - `--inline-template`: Template no mesmo arquivo do componente (padrão `false`).
    - `--module`: `NgModule` declarado.
    - `--prefix`: Prefixo a ser aplicado ao seletor do componente gerado.
    - `--project`: Nome (ou *path*) do projeto.
    - `--skip-tests`: Não gerar arquivos de teste "spec.ts".
    - `--style`: Pré-processador de arquivos de estilo (css|scss|sass|less).

### ng generate directive

[Link documentação](https://angular.io/cli/generate#directive)

Cria uma nova definição de diretiva genérica no projeto fornecido ou padrão.

```bash
ng generate directive <name> [options]

ng g directive <name> [options]
```

??? info "Principais parâmetros"

    - `<name>`: Nome (ou *path*) da diretiva.
    - `--export`: O `NgModule` declarado exporta esta diretiva (padrão `false`).
    - `--flat`: Crie os novos arquivos sem criar diretório para a diretiva (padrão `true`).
    - `--module`: `NgModule` declarado.
    - `--prefix`: Prefixo a ser aplicado ao seletor da diretiva gerada.
    - `--project`: Nome (ou *path*) do projeto.
    - `--skip-tests`: Não gerar arquivos de teste "spec.ts".

## Referências

- <https://angular.io/cli>
