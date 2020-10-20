# Arch Linux

[TOC]

##  Instalação da Distribuição

### Configuracao do instalador

#### Teclado

Layout abnt2

```sh
loadkeys br-abnt2
```

####  Idioma da instalação

```sh
nano /etc/locale.gen
```

Descomentar as linhas:

```sh
en_US.UTF-8 UTF-8
pt_BR.UTF-8 UTF-8
```

Executar os comandos:

```sh
locale-gen
export LANG=pt_BR.UTF-8
```

#### Conectar na rede wireless

```sh
$ wifi-menu
```

Verificar conexão com a internet

```sh
$ ping -c 3 www.google.com
```

#### Particionamento

Ver as partições

```sh
fdisk -l
```

Criar os particionamentos

É preciso criar a partição UEFI se ela não existe (>200MB)

```sh
cfdisk /dev/sda
```

Formatar as partições

```sh
mkfs.ext4 /dev/sdaX
```

Formatar a partição swap e ativá-lo

```sh
mkswap /dev/sdaX
swapon /dev/sdaX
```

Verificar se o swap foi habilitado

```sh
free -m
```

Ver o layout do particionamento

```sh
lsblk /dev/sda
```

Montar as partições

```sh
mount /dev/sdaX /mnt
```

Opcional: Criar a pasta home em uma partição diferente

```sh
mkdir /mnt/home
mount /dev/sda4 /mnt/home
```

Criar a pasta boot para o UEFI e montar. Sendo sdaX a partição do EFI.

```sh
mkdir -p /mnt/boot/efi
mount /dev/sdaX /mnt/boot/efi
```

#### Instalar o sistema base

```sh
pacstrap /mnt base base-devel
```

#### Gerar o arquivo fstab

O arquivo fstab armazenar as configurações das partições

```sh
genfstab -U -p /mnt >> /mnt/etc/fstab
```

Ver o que está escrito nesse arquivo

```sh
cat /mnt/etc/fstab
```

### Configuracao do sistema instalado

#### Acesso ao sistema instalado

```sh
arch-chroot /mnt
```

#### Idioma do sistema

```sh
nano /etc/locale.gen

descomentar essas linhas:
en_US.UTF-8 UTF-8
pt_BR.UTF-8 UTF-8

executar esse comando:
locale-gen
```

Armazenar no arquivo de configuração

```sh
echo LANG=pt_BR.UTF-8 > /etc/locale.conf
export LANG=pt_BR.UTF-8
```

#### Teclado

```sh
nano /etc/vconsole.conf
```

Adicionar as linhas

```sh
KEYMAP=br-abnt2
FONT=Lat2-Terminus16
FONT_MAP=
```

#### Fuso horário

```sh
ls /usr/share/zoneinfo/America
ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
```

#### Sincronização do relógio

```sh
hwclock --systohc --utc
```

#### Rede cabeada

```sh
systemctl enable dhcpcd@eth0.service
```

#### Rede wireless

```sh
pacman -S wireless_tools wpa_supplicant wpa_actiond dialog
```

#### Configurar os repositórios

```sh
nano /etc/pacman.conf

descomentar multilib
```

Sincronizar os repositórios

```sh
pacman -Sy
```

#### Criar senha de root

```sh
passwd
```

#### Criar usuário e definir senha

```sh
useradd -m -g users -G wheel,storage,power -s /bin/bash <USUARIO>
passwd <USUARIO>
```

#### Instalar o sudo

```sh
pacman -S sudo
```

Editar as propriedades para os usuarios do grupo ` wheel` terem permissoes de root.

```sh
nano /etc/sudoers

Descomentar a linha que mostra "%wheel ALL=(ALL) ALL"
```

#### Editor padrão para o terminal

```sh
EDITOR=nano visudo
```

#### Instalação do grub


Baixar os pacotes

```sh
pacman -S grub
pacman -S efibootmgr
```

Instalar o grub

```sh
grub-install /dev/sda
```

Gerar o RAM Disk inicial

```sh
mkinitcpio -p linux
```

Criar o arquivo de configuração do grub

```sh
grub-mkconfig -o /boot/grub/grub.cfg 
```

Idioma das mensagens

```sh
cp /usr/share/locale/en\@quot/LC_MESSAGES/grub.mo /boot/grub/locale/en.mo
```

#### Sair do arch-chroot

```sh
exit
```

#### Desmontar as partições

```sh
umount -a
```

#### Reiniciar

```sh
reboot
```

#### Nome do host

```sh
sudo hostnamectl set-hostname <HOSTNAME>
```

#### Conexão com a internet

```sh
dhcpcd
ping -c 3 www.google.com
```

Ativar o gerenciador de rede automaticamente

```sh
systemctl start NetworkManager.service
systemctl enable NetworkManager.service
```

#### Instalar xorg

```sh
sudo pacman -S xorg xorg-server
```

#### Instalar driver de video da intel

```sh
pacman -S xf86-video-intel
```

#### Instalar audio

```sh
sudo pacman -S pulseaudio pulseaudio-alsa
```

#### Dual boot

```sh
pacman -Syu os-prober

grub-mkconfig -o /boot/grub/grub.cfg
```

#### Gnome

```sh
sudo pacman -S gnome
```

Habilitar o GDM

```sh
sudo systemctl start gdm.service
sudo systemctl enable gdm.service
```

## Configuração do Repositório

### Configuração dos mirrors

#### Backup da lista de mirrors

```sh
cp /etc/pacman.d/mirrorlist /etc/pacman.d/mirrorlist.backup
```

#### Gerar a lista de mirrors

[Gerador de mirrors](https://www.archlinux.org/mirrorlist)

#### Descomentar a lista de mirrors

```sh
sed -i 's/^#Server/Server/' /etc/pacman.d/mirrorlist
```

#### Atualizar a lista de pacotes

```sh
pacman -Syyu
```

#### Gerar os 6 mirrors com melhor conexão

```sh
rankmirrors -n 6 /etc/pacman.d/mirrorlist
```

## Restauração do Grub

```sh
mount /dev/sda7 /mnt          # partição linux
mkdir -p /mnt/boot/efi
mount /dev/sda2 /mnt/boot/efi # partição efi
arch-chroot /mnt
os-prober
grub-mkconfig -o /boot/grub/grub.cfg
grub-install /dev/sda
```

Caso o comando "os-prober" não retorne o local do windows, o grub contará apenas com o linux. Neste caso entre no sistema linux e execute os comandos abaixo novamente:

```sh
os-prober
grub-mkconfig -o /boot/grub/grub.cfg
grub-install /dev/sda
```

## Yaourt

O Yaourt (Yet AnOther User Repository Tool) é um gerenciador de pacotes desenvolvido para completar o conhecido pacman. Foi desenvolvido por Julien Mischkowitz e Tuxce; usuários da Comunidade Arch Linux Francês e tem como o diferencial conseguir pesquisar, atualizar e instalar pacotes do AUR.

### Comandos

- Instalar ou atualizar um pacote
    ```sh
    yaourt -S pacote --noconfirm
    ```
O parâmetro `--noconfirm` desabilita as perguntas durante de instalação.

- Instale um pacote local ou a partir da web
  ```sh
  yaourt -U caminho_do_pacote --noconfirm
  ```

- Encontre um pacote
    ```sh
    yaourt pacote
    ```

- Informação sobre um pacote instalado
    ```sh
    yaourt -Qi pacote
    ```

- Obter informações sobre um pacote nos repositórios
    ```sh
    yaourt pacote -Si
    ```

- Remover um pacote
    ```sh
    yaourt -R pacote
    ```

- Remover um pacote e suas dependências que não são necessários a outro pacote instalado
    ```sh
    yaourt -Rs pacote
    ```

- Remover um pacote e suas dependências e todos os pacotes que dependem dele

    **ATENÇÃO**: Esta operação é recursiva, e deve ser usado com muito cuidado, pois poderia eliminar um pacote principal e corromper o sistema.

    ```sh
    yaourt -Rsc pacote
    ```

- Remover um pacote que é exigido por outro, sem retirar suas dependências
    ```sh
    yaourt -Rdd pacote
    ```

- Atualizar os pacotes de banco de dados
    ```sh
    yaourt -Syy
    ```

- Limpar o cache de pacotes
    ```sh
    yaourt -Scc
    ```

- Atualizar o sistema
    ```sh
    yaourt -Syu
    ```

- A atualização do sistema, incluindo pacotes do AUR instalados
    ```sh
    yaourt -Syua
    ```
