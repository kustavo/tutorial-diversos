# yay

yay (Yet Another Yogurt), é um AUR Helper escrito na linguagem Go. Yay é baseado no design do yaourt, apacman e pacaur. Ele projetou e desenvolveu de tal forma a fornecer uma interface para o pacman.

Ele usa dependências mínimas e solicita uma entrada mínima do usuário ao executar a instalação do pacote. Oferece busca interativa semelhante a Yaourt.

## Instalação

```bash
sudo pacman -S --needed git base-devel
git clone https://aur.archlinux.org/yay.git
cd yay
makepkg -si
```

## Comandos

- `yay -S <package-name>`: instala o pacote informado
- `yay -Syu`: atualiza todos pacotes
- `yay -Rns <package-name>`: desinstala o pacote informado
- `yay -Ss <package-name>`: procura o pacote informado no AUR e no repositório oficial
- `yay -Si <package-name>`: ver informações detalhadas do pacote informado no AUR e no repositório oficial
- `yay -Qs <package-name>`: procura o pacote informado entre os instalados
- `yay -Qi <package-name>`: ver informações detalhadas do pacote instalado

## Referências

- <https://www.edivaldobrito.com.br/aur-helper-yay-no-arch-linux/>
