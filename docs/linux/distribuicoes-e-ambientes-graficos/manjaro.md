# Manjaro

Manjaro Linux é uma distribuição linux baseada no Arch Linux, tendo o seu próprio conjunto de repositórios. A distribuição tem como objetivo ser uma nova "distro" amigável ao usuário(user friendly), mantendo a poderosa base Arch, mais notavelmente o gerenciador de pacotes Pacman e compatibilidade com o AUR(Repositório dos Usuários do Arch).

## Restauração do Grub

```bash
sudo efibootmgr --create --disk /dev/sda --part 2 --loader /EFI/Manjaro/grubx64.efi --label "Manjaro GRUB" --verbose
```

Onde:

* `/dev/sda`  é  a partição /boot/efi.
* `-part 2` é o número da partição /boot/efi (sda2).
* `-label` é a label (opcional)
