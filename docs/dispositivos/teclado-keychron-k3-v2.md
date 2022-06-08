# Teclado keychron k3 v2

## Teclas de função

| Atalho                               | Descrição                                                     |
| ------------------------------------ | ------------------------------------------------------------- |
| Fn + L + light key (6s)              | Bloquear/Desbloquear tecla de efeito de luz                   |
| Fn + (1, 2, 3)                       | Bluetooth pair                                                |
| Fn + Backspace                       | Igual delete                                                  |
| Fn + Z + J                           | Reset                                                         |
| Fn + X + L                           | Alternar entre teclas de multimídia e teclas F1-F12 (Windows) |
| Fn + +/-                             | Velocidade dos efeito de luz                                  |
| Fn + Caps Lock + P (6s)              | Caps Lock não irá seguir os efeitos de luz                    |
| Fn + S + L + O (3s - bluetooth mode) | Desabilitar/Habilitar modo Auto Sleep                         |
| Fn + S + L + R (3s - bluetooth mode) | Auto Sleep em 10 minutos sem digitar                          |
| Fn + S + L + T (3s - bluetooth mode) | Auto Sleep em 20 Minutes sem digitar                          |
| Fn + S + L + Y (3s - bluetooth mode) | Auto Sleep em 30 Minutes sem digitar                          |
| Fn + win (3s)                        | Bloquear/Desbloquear teclado (Windows)                        |

## Desabilitar teclas Fn como padrão

```bash
echo options hid_apple fnmode=2 | sudo tee -a /etc/modprobe.d/hid_apple.conf

sudo update-initramfs -u -k all
```

## Referências

- <https://help.ubuntu.com/community/AppleKeyboard#Change_Function_Key_behavior>
- <https://www.keychron.com/blogs/news/keychron-keyboards-shortcuts-introduction>
