# Interface de linha de comando

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

## Criar projeto

```bash
ng new <projeto>
```

## Executar projeto

```bash
cd <projeto>
ng serve
```

## Criar arquivos

```bash
ng generate <tipo> <nome>
```

??? info "Tipos possíveis"

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

??? example "Exemplos"

    ```bash
    # Criar um componente
    ng generate component <nome>
    ```
