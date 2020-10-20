# Gnome

[TOC]


## Configurações


### Montar partição NTFS automaticamente

Usar o `gnome-disk-utility` e em `"Edit Mount Options"` desabilitar `"User Session Defaults"` e escolher `"Mount at system startup"`

É necessário instalar o pacote `ntfs-3g` para ter permissao de escrita.

```sh
sudo pacman -S ntfs-3g
```

### Habilitar lixeira em partição NTFS

1. Acrescentar o comando `uid=1000` nas opções de montagem

    ```sh
    /dev/sda3 /home/user/shared ntfs defaults,uid=1000,noatime 0 0
    ```

1. Criar pasta `.Trash-1000` na raiz da partição

1. Desmontar e montar a partição

## File manager Bookmark

Local onde são armazenados os bookmarks

```sh
~/.config/gtk-3.0/bookmark
```

## Pastas padrão

Local de configuração `~/.config/user-dirs.dirs `. Para remover uma pasta padrão, basta deixar `$HOME/`. Na pasta `Templates` fica os modelos de arquivos que irão aparecer no menu do botão direito para criar um novo arquivo.

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

```sh
xdg-user-dirs-update
```

## Hot Corner

Função acionada quando o mouse encosta no canto superior esquerdo da tela.

Verificar se está habilitado.

```sh
gsettings get org.gnome.shell enable-hot-corners
```

Habilitadar.

```sh
gsettings set org.gnome.shell enable-hot-corners true
```

Desabilitar.

```sh
gsettings set org.gnome.shell enable-hot-corners false
```

## GDM

### Definir tela de login no monitor padrão

Tela de login no monitor definido como primário quando usado GDM (GNOME Display Manager), ou seja, o programa que permite o login gráfico do Gnome.

1. Definir as configurações das telas pela interface. Force alguma mudança para que o arquivo `~/.config/monitors.xml` seja criado.

1. Execute:

	```sh
	cp ~/.config/monitors.xml /var/lib/gdm3/.config/
	```

## Problemas

### Open containing folder

O gerenciador de arquivos não está sendo aberto quando um aplicativo tem a opção "Open containing folder".

Remover o aplicativo que está usando a diretiva "inode/directory=" no "mimeinfo.cache".

```sh
sudo nano /usr/share/applications/mimeinfo.cache
```

Adicionar a diretiva "inode/directory=" no "mimeapps.list".

```sh
nano ~/.config/mimeapps.list
```

```sh
[Default Applications]
inode/directory=nautilus.desktop
```

### Exibindo dois Docks

Abra a janela "extensions" (não é a do Tweak) e desmarque o "Ubuntu Dock". Depois `Alt + F2 e digite r`.
