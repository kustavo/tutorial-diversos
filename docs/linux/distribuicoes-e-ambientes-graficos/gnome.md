# Gnome

O GNOME (acrônimo para GNU Network Object Model Environment) é um ambiente desktop completo para sistemas operacionais das famílias GNU/Linux e UNIX.
Surgiu pouco tempo depois do lançamento do KDE, com a proposta de ser um projeto de código aberto e livre (que ainda não era o caso do KDE).

O desenvolvimento do GNOME é supervisionado pela Fundação GNOME, que representa oficialmente o projeto junto a empresas, organizações e a sociedade como um todo. O projeto conta ainda com uma série de equipes com missões específicas, inclusive com uma equipe de engenharia de lançamentos, responsável pelo característico calendário de lançamentos semestrais.

A comunidade de desenvolvimento do GNOME conta tanto com voluntários quanto com empregados de várias empresas, inclusive grandes empresas como Hewlett-Packard, IBM, Novell, Red Hat, Oracle, entre outras. Por sua vez, o GNOME é filiado ao Projeto GNU, de onde herdou a missão de prover um ambiente de trabalho composto inteiramente por software livre.

## Partição NTFS

### Montar partição automaticamente

Usar o `gnome-disk-utility` e em `"Edit Mount Options"` desabilitar `"User Session Defaults"` e escolher `"Mount at system startup"`

É necessário instalar o pacote `ntfs-3g` para ter permissão de escrita.

```bash
# Arch Linux
sudo pacman -S ntfs-3g
```

### Habilitar lixeira

Acrescentar o comando `uid=1000` nas opções de montagem, como no exemplo:

Exemplo:

```bash
/dev/sda3 /home/user/shared ntfs defaults,uid=1000,noatime 0 0
```

## Bookmarks

Local onde são armazenados os bookmarks

```bash
~/.config/gtk-3.0/bookmark
```

## Diretórios padrão

Local de configuração `~/.config/user-dirs.dirs`. Para remover uma pasta padrão, basta deixar `$HOME/` conforme o exemplo abaixo:

```txt
XDG_DESKTOP_DIR="$HOME/Desktop"
XDG_DOWNLOAD_DIR="$HOME/Downloads"
XDG_TEMPLATES_DIR="$HOME/Templates"
XDG_PUBLICSHARE_DIR="$HOME/"
XDG_DOCUMENTS_DIR="$HOME/"
XDG_MUSIC_DIR="$HOME/"
XDG_PICTURES_DIR="$HOME/"
XDG_VIDEOS_DIR="$HOME/"
```

Atualizar a configuração:

```bash
xdg-user-dirs-update
```

!!! note ""
    Na pasta `Templates` fica os modelos de arquivos que irão aparecer no menu do botão direito para criar um novo arquivo.

## Hot Corner

Função acionada quando o mouse encosta no canto superior esquerdo da tela.

Comando para verificar se está habilitado:

```bash
gsettings get org.gnome.shell enable-hot-corners
```

Comando para habilitar:

```bash
gsettings set org.gnome.shell enable-hot-corners true
```

Comando para desabilitar:

```bash
gsettings set org.gnome.shell enable-hot-corners false
```

## GDM

Alterar o monitor onde irá aparecer a tela de login quando usado GDM (GNOME Display Manager).

1. Definir as configurações das telas pela interface. Force alguma mudança para que o arquivo `~/.config/monitors.xml` seja criado.

1. Copie o arquivo para `/var/lib/gdm3/.config/`:

    ```bash
    cp ~/.config/monitors.xml /var/lib/gdm/.config/
    # ou
    cp ~/.config/monitors.xml /var/lib/gdm3/.config/
    ```

## Problemas e soluções

### Open containing folder

O gerenciador de arquivos não está sendo aberto quando um aplicativo tem a opção "Open containing folder".

??? tip "Solução"

    Remover o aplicativo que está usando a diretiva "inode/directory=" no "mimeinfo.cache".

    ```bash
    sudo nano /usr/share/applications/mimeinfo.cache
    ```

    Adicionar a diretiva "inode/directory=" no "mimeapps.list".

    ```bash
    nano ~/.config/mimeapps.list
    ```

    ```bash
    [Default Applications]
    inode/directory=nautilus.desktop
    ```

### Exibindo dois Docks

Exibido dois docks.

??? tip "Solução"

    Abra a janela "extensions" (não é a do Tweak) e desmarque o "Ubuntu Dock". Depois `Alt + F2 e digite r`.

### Partição NTFS sem permissão de escrita

Partição NTFS sem permissão de escrita.

??? tip "Solução"

    Se estiver em dual boot com o windows, desabilite a opção de "Inicialização rápida" no windows.

## Referêcias

- <https://en.wikipedia.org/wiki/Desktop_environment>
- <https://pt.wikipedia.org/wiki/GNOME>
