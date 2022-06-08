# Teclado keychron k3 v2

## Teclas de função

| Atalho             | Descrição                                                             |
| ------------------ | --------------------------------------------------------------------- |
| Fn + L + light effect key (6s)   | Bloqueia tecla de efeito de luz                         |
| Fn + (1, 2, 3) | Bluetooth pair |
| Fn + Backspace | igual delete |
| Fn + Z + J | reset |

## Desabilitar teclas Fn como padrão

```bash
echo options hid_apple fnmode=2 | sudo tee -a /etc/modprobe.d/hid_apple.conf

sudo update-initramfs -u -k all
```

## Referências

- <https://help.ubuntu.com/community/AppleKeyboard#Change_Function_Key_behavior>
- <https://www.keychron.com/blogs/news/keychron-keyboards-shortcuts-introduction>
