# Snap

[TOC]

## Comandos

### Sistema

Ver versão do snap

```sh
snap version
```

### Runtimes e aplicações

Busca de aplicativos

```sh
snap find "nome"
```

Informação do aplicativo

```sh
snap info <APLICATIVO>
```

Instalação de aplicativos

```bash
sudo snap install <APLICATIVO>

# Escolhendo o canal do repositório (stable, candidate, beta, edge)
sudo snap install --channel=edge <APLICATIVO>

# Mudando o canal do repositório
sudo snap switch --channel=stable <APLICATIVO>
```

Remover aplicativo

```bash
sudo snap remove <APLICATIVO>
```

Local onde os aplicativos são instalados (`/snap/bin`)

```sh
which <APLICATIVO>

$ /snap/bin/<APLICATIVO>
```

Executar aplicativo

```sh
/snap/bin/vlc
```

Listar aplicativos instalados

```sh
snap list

# Listar com todas as versões instaladas
snap list --all
```

Atualizar os aplicativos

```sh
sudo snap refresh <APLICATIVO>

# Mudar o canal e atualizar
sudo snap refresh --channel=beta <APLICATIVO>
```

Reverter para a versão anterior

```bash
sudo snap revert <APLICATIVO>
```

Desabilitar e habilitar um aplicativo

```bash
sudo snap disable <APLICATIVO>
sudo snap enable <APLICATIVO>
```
