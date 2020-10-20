# Flatpak

[TOC]

## Comandos

###  Runtimes e aplicações

Procurar por runtimes e aplicações nos repositórios.

```sh
$ flatpak update
$ flatpak search <NOME>
```

Executar aplicações

```sh
$ flatpak run <APLICACAO>
```

Atualizar aplicação

```sh
$ flatpak update <APLICACAO>
```

Desinstalar runtime ou aplicação

```sh
$ flatpak uninstall <APLICACAO>
```

Remover runtimes não usados

```sh
$ flatpak uninstall --unused
```

Listar runtimes e aplicações


```sh
$ flatpak list
```

Ver permissões do sandbox da aplicação

```sh
$ flatpak info --show-permissions <APLICACAO>
```

Sobrescrever permissões do sandbox da aplicação

```sh
# Previne a aplicação de acessar o diretório home
$ flatpak override --nofilesystem=home <APLICACAO>
```
Outros parâmetros

| Comando                     | Descrição         |
| --------------------------- | ----------------- |
| –filesystem=host            | Access all files   |
| –filesystem=home            | Access the home directory  |
| –filesystem=home:ro         | Access the home directory, read-only  |
| –filesystem=/some/dir –filesystem=~/other/dir | Access paths   |
| –filesystem=xdg-download    | Access the XDG download directory  |
| –nofilesystem=...           | Undo some of the above        |
| –socket=x11 –share=ipc      | Show windows using X11)  |
|–device=dri                  | OpenGL rendering   |
| –socket=wayland             | Show windows using Wayland   |
| –socket=pulseaudio          | Play sounds using PulseAudio   |
| –share=network              | Access the network |
| –talk-name=org.freedesktop.secrets  | Talk to a named service on the session bus |
| –system-talk-name=org.freedesktop.GeoClue2  | Talk to a named service on the system bus |
| –socket=system-bus  | Unlimited access to all of D-Bus |

Resetar permissões sobrescritas do sandbox da aplicação

```sh
$ flatpak override --reset <APLICACAO>
```