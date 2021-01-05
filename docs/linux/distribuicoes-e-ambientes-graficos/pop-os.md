# Pop!_OS

Pop!_OS é uma distribuição linux gratuita de código-aberto, com base no Ubuntu, apresentando um ambiente de trabalho GNOME customizado. A distribuição é desenvolvida pela fabricante de computadores americana System76.

## Systemd-boot

### Reparar o systemd-boot

```bash
sudo mount <particao-sistema> /mnt
sudo mount <particao-efi> /mnt/boot/efi
for i in /dev /dev/pts /proc /sys /run; do sudo mount -B $i /mnt$i; done
sudo cp /etc/resolv.conf /mnt/etc/
sudo chroot /mnt
apt install --reinstall linux-generic linux-headers-generic
update-initramfs -c -k all
exit
sudo bootctl --path=/mnt/boot/efi install
```

### Gerenciar opções de boot

As opções de boot são os arquivos `.conf` em `/boot/efi/loader/entries`.

Para colocar a opção do windows como padrão, por exemplo ou mudar o nome, é necessário criar um arquivo `.conf` contendo:

```ini
title Windows 10
# Nome que será exibido nas opções de boot
efi /EFI/Microsoft/Boot/bootmgfw.efi
# A partição `/boot` é só um ponto de montagem da partição `efi`
```

As configurações de boot estão em `/boot/efi/loader/loader.conf`:

```ini
default windows.conf
timeout 10
# Evitar que apareça opções de busca automática que não então em `/boot/efi/loader/entries`.
auto-entries 0
```

### Listar sistemas presentes na partição EFI

```bash
ls -l /boot/efi/EFI
```
