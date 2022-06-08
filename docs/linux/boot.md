# Boot


## Adicionar o Windows nas opções do Grub

1. Instale os pacotes `os-prober` e `grub-mkconfig`.

1. Adicionar o seguinte texto no arquivo `/etc/grub.d/40_custom` onde são adicionados as novas entradas no Grub manualmente. Não escrever direto em `/boot/grub/grub.cfg` pois este arquivo é gerado automaticamente.

    ```txt
    menuentry "Windows 11" --class windows --class os {
        insmod part_gpt
        insmod fat
        insmod search_fs_uuid
        insmod chain
        search --fs-uuid --set=root ####
        chainloader /EFI/Microsoft/Boot/bootmgfw.efi
    }
    ```

1. Altere o valor de `####` pelo resultado do comando: 

    `grub-probe --target=fs_uuid /boot/efi/EFI/Microsoft/Boot/bootmgfw.efi`. 

1. Se o arquivo `/boot/efi/EFI/Microsoft/Boot/bootmgfw.efi` não existir, será necessário recriar através de uma imagem de instalação/recuperação do Windows executando.

    ```bash
    bcdboot x:\windows # x é a partição onde está o windows
    ```

1. Execute: 

    `grub-mkconfig -o /boot/grub/grub.cfg` 

## Recriar dados das partição EFI

Caso a partição EFI não existe ou está corrompida

```bash
mount /dev/sda7 /mnt          # partição linux
mkdir -p /mnt/boot/efi
mount /dev/sda2 /mnt/boot/efi # partição efi
arch-chroot /mnt
os-prober
grub-mkconfig -o /boot/grub/grub.cfg
grub-install /dev/sda
```

## Secure Boot

Execute os comamdos abaixo:

```bash
sudo update-secureboot-policy --enroll-key
sudo update-secureboot-policy --new-key
```

Reinicie e a bios deverá realizar pergunta de deseja realizar o MOK (Machine Owner Key)

## Remover entradas antigas ou não desejados do EFI

```bash
# instalar
sudo apt-get install efibootmgr

# ver a lista de entradas e os ids
sudo efibootmgr

# <id> é o id da entrada, ex: Boot0005 será "sudo efibootmgr -b 0005 -B"
sudo efibootmgr -b <id> -B
```



## Referências

- <https://gist.github.com/xtrymind/b895c640231adde21598d5188c75d4b5>
- <https://unix.stackexchange.com/questions/432176/how-to-add-windows-10-to-grub-on-arch-install-with-efi>
- <https://wiki.ubuntu.com/UEFI/SecureBoot>
