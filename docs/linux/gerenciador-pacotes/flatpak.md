# Flatpak

Flatpak, é um utilitário para implantação de software, gestão de pacote e virtualização para Linux. Uma aplicação empacotada no formato Flatpak provê um ambiente sandbox onde o usuário pode executar programas em isolamento do resto do sistema, ou seja, onde cada aplicação empacotada possui apenas as bibliotecas necessárias para a execução do programa. Aplicações usando Flatpak necessitam de autorização prévia do usuário para usar o hardware ou acessar arquivos do sistema, semelhante aos aplicativos para o sistema operacional Android.

A ideia de usar contêineres de aplicativos no GNOME foi proposta em 2013 por Lennart Poettering,que publicou um artigo sobre isso em 2014. Desenvolvido como parte do projeto freedesktop.org, foi chamado originalmente como xdg-app.

Diferente do Snappy, o Flatpak foi desenvolvido para ser descentralizado, permitindo adicionar diferentes fontes de programas. Uma fonte popular de aplicativos no formato Flatpak é o Flathub. Atualmente, alguns programas populares disponíveis inclui Blender, GIMP,LibreOffice, Pitivi, KDE Applications, Linphone e alguns não oficiais como versões de desenvolvimento do Mozilla Firefox (suporte oficial não planejado), Skype, Spotify.

## Instalação

```bash
$ sudo apt install gnome-software gnome-software-plugin-flatpak

# Adicionar o repositório flathub
$ flatpak remote-add --if-not-exists flathub https://flathub.org/repo/flathub.flatpakrepo
```

## Comandos

Procurar por runtimes e aplicações nos repositórios.

```bash
flatpak update
flatpak search <nome>
```

Executar aplicações

```bash
flatpak run <aplicacao>
```

Atualizar aplicação

```bash
flatpak update <aplicacao>
```

Desinstalar runtime ou aplicação

```bash
flatpak uninstall <aplicacao>
```

Remover runtimes não usados

```bash
flatpak uninstall --unused
```

Listar runtimes e aplicações

```bash
flatpak list
```

Ver permissões do sandbox da aplicação

```bash
flatpak info --show-permissions <aplicacao>
```

Sobrescrever permissões do sandbox da aplicação

```bash
# Previne a aplicação de acessar o diretório home
flatpak override --nofilesystem=home <aplicacao>
```

Outros parâmetros

| Comando                                       | Descrição                                  |
|-----------------------------------------------|--------------------------------------------|
| –filesystem=host                              | Access all files                           |
| –filesystem=home                              | Access the home directory                  |
| –filesystem=home:ro                           | Access the home directory, read-only       |
| –filesystem=/some/dir –filesystem=~/other/dir | Access paths                               |
| –filesystem=xdg-download                      | Access the XDG download directory          |
| –nofilesystem=...                             | Undo some of the above                     |
| –socket=x11 –share=ipc                        | Show windows using X11)                    |
| –device=dri                                   | OpenGL rendering                           |
| –socket=wayland                               | Show windows using Wayland                 |
| –socket=pulseaudio                            | Play sounds using PulseAudio               |
| –share=network                                | Access the network                         |
| –talk-name=org.freedesktop.secrets            | Talk to a named service on the session bus |
| –system-talk-name=org.freedesktop.GeoClue2    | Talk to a named service on the system bus  |
| –socket=system-bus                            | Unlimited access to all of D-Bus           |

Resetar permissões sobrescritas do sandbox da aplicação

```bash
flatpak override --reset <aplicacao>
```

## Referências

- <https://pt.wikipedia.org/wiki/Flatpak>
