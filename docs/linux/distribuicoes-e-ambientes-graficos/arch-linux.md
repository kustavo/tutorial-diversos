# Arch Linux

Arch Linux é uma distribuição Linux para computadores com arquitetura x86-64. O desenvolvimento é focado na elegância, minimalismo e simplicidade no código, e espera que o usuário faça alguns esforços para compreender o modo de funcionamento do sistema. O gerenciador de pacotes, Pacman, foi escrito especialmente para o Arch Linux e é usado para instalar, remover, pesquisar e atualizar os pacotes do sistema.

O Arch Linux usa o modelo rolling release. Com esse sistema, os usuários podem ter acesso às últimas atualizações estáveis dos pacotes e também evita atualizações muito grandes que podem gerar erros nos componentes do sistema. As imagens de instalação lançadas pela equipe do Arch são apenas capturas instantâneas de imagens de disco atualizadas dos principais componentes do sistema.

Usuários da distribuição podem criar facilmente seus próprios pacotes compatíveis com o pacman usando ferramentas como o "Arch Build System", funcionalidade esta que ajudou a sustentar o AUR, um repositório de pacotes criados por usuários que complementam os repositórios oficiais.

## Instalação

### Configuração do instalador

#### Teclado

Layout abnt2

```bash
loadkeys br-abnt2
```

#### Idioma da instalação

```bash
nano /etc/locale.gen
```

Descomentar as linhas:

```bash
en_US.UTF-8 UTF-8
pt_BR.UTF-8 UTF-8
```

Executar os comandos:

```bash
locale-gen
export LANG=pt_BR.UTF-8
```

#### Conectar na rede wireless

```bash
wifi-menu
```

Verificar conexão com a internet

```bash
ping -c 3 www.google.com
```

#### Particionamento

Ver as partições

```bash
fdisk -l
```

Criar os particionamentos

É preciso criar a partição UEFI se ela não existe (>200MB)

```bash
cfdisk /dev/sda
```

Formatar as partições

```bash
mkfs.ext4 /dev/sdaX
```

Formatar a partição swap e ativá-lo

```bash
mkswap /dev/sdaX
swapon /dev/sdaX
```

Verificar se o swap foi habilitado

```bash
free -m
```

Ver o layout do particionamento

```bash
lsblk /dev/sda
```

Montar as partições

```bash
mount /dev/sdaX /mnt
```

Opcional: Criar a pasta home em uma partição diferente

```bash
mkdir /mnt/home
mount /dev/sda4 /mnt/home
```

Criar a pasta boot para o UEFI e montar. Sendo sdaX a partição do EFI.

```bash
mkdir -p /mnt/boot/efi
mount /dev/sdaX /mnt/boot/efi
```

#### Instalar o sistema base

```bash
pacstrap /mnt base base-devel
```

#### Gerar o arquivo fstab

O arquivo fstab armazenar as configurações das partições

```bash
genfstab -U -p /mnt >> /mnt/etc/fstab
```

Ver o que está escrito nesse arquivo

```bash
cat /mnt/etc/fstab
```

### Configuracao do sistema instalado

#### Acesso ao sistema instalado

```bash
arch-chroot /mnt
```

#### Idioma do sistema

```bash
nano /etc/locale.gen

descomentar essas linhas:
en_US.UTF-8 UTF-8
pt_BR.UTF-8 UTF-8

executar esse comando:
locale-gen
```

Armazenar no arquivo de configuração

```bash
echo LANG=pt_BR.UTF-8 > /etc/locale.conf
export LANG=pt_BR.UTF-8
```

#### Teclado

```bash
nano /etc/vconsole.conf
```

Adicionar as linhas

```bash
KEYMAP=br-abnt2
FONT=Lat2-Terminus16
FONT_MAP=
```

#### Fuso horário

```bash
ls /usr/share/zoneinfo/America
ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
```

#### Sincronização do relógio

```bash
hwclock --systohc --utc
```

#### Rede cabeada

```bash
systemctl enable dhcpcd@eth0.service
```

#### Rede wireless

```bash
pacman -S wireless_tools wpa_supplicant wpa_actiond dialog
```

#### Configurar os repositórios

```bash
nano /etc/pacman.conf

descomentar multilib
```

Sincronizar os repositórios

```bash
pacman -Sy
```

#### Criar senha de root

```bash
passwd
```

#### Criar usuário e definir senha

```bash
useradd -m -g users -G wheel,storage,power -s /bin/bash <USUARIO>
passwd <USUARIO>
```

#### Instalar o sudo

```bash
pacman -S sudo
```

Editar as propriedades para os usuarios do grupo `wheel` terem permissoes de root.

```bash
nano /etc/sudoers

Descomentar a linha que mostra "%wheel ALL=(ALL) ALL"
```

#### Editor padrão para o terminal

```bash
EDITOR=nano visudo
```

#### Instalação do grub

Baixar os pacotes

```bash
pacman -S grub
pacman -S efibootmgr
```

Instalar o grub

```bash
grub-install /dev/sda
```

Gerar o RAM Disk inicial

```bash
mkinitcpio -p linux
```

Criar o arquivo de configuração do grub

```bash
grub-mkconfig -o /boot/grub/grub.cfg
```

Idioma das mensagens

```bash
cp /usr/share/locale/en\@quot/LC_MESSAGES/grub.mo /boot/grub/locale/en.mo
```

#### Sair do arch-chroot

```bash
exit
```

#### Desmontar as partições

```bash
umount -a
```

#### Reiniciar

```bash
reboot
```

#### Nome do host

```bash
sudo hostnamectl set-hostname <HOSTNAME>
```

#### Conexão com a internet

```bash
dhcpcd
ping -c 3 www.google.com
```

Ativar o gerenciador de rede automaticamente

```bash
systemctl start NetworkManager.service
systemctl enable NetworkManager.service
```

#### Instalar xorg

```bash
sudo pacman -S xorg xorg-server
```

#### Instalar driver de video da intel

```bash
pacman -S xf86-video-intel
```

#### Instalar audio

```bash
sudo pacman -S pulseaudio pulseaudio-alsa
```

#### Dual boot

```bash
pacman -Syu os-prober

grub-mkconfig -o /boot/grub/grub.cfg
```

#### Gnome

```bash
sudo pacman -S gnome
```

Habilitar o GDM

```bash
sudo systemctl start gdm.service
sudo systemctl enable gdm.service
```

## Configuração do Repositório

### Configuração dos mirrors

#### Backup da lista de mirrors

```bash
cp /etc/pacman.d/mirrorlist /etc/pacman.d/mirrorlist.backup
```

#### Gerar a lista de mirrors

[Gerador de mirrors](https://www.archlinux.org/mirrorlist)

#### Descomentar a lista de mirrors

```bash
sed -i 's/^#Server/Server/' /etc/pacman.d/mirrorlist
```

#### Atualizar a lista de pacotes

```bash
pacman -Syyu
```

#### Gerar os 6 mirrors com melhor conexão

```bash
rankmirrors -n 6 /etc/pacman.d/mirrorlist
```

## Referências

- <https://pt.wikipedia.org/wiki/Arch_Linux>
