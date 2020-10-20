# Manjaro

[TOC]

## Restauração do Grub

```sh
sudo efibootmgr --create --disk /dev/sda --part 2 --loader /EFI/Manjaro/grubx64.efi --label "Manjaro GRUB" --verbose
```
Onde:

* `/dev/sda`  é  a partição /boot/efi.
* `-part 2` é o número da partição /boot/efi (sda2).
* `-label` é a label (opcional)