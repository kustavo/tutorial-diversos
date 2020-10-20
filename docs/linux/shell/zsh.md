# Z-shell

O shell Z-shell ou Zsh, como também é conhecido, se assemelha ao Korn shell (ksh); sua compatibilidade com o shell Korn de 1988 vem aumentando gradualmente. Ele inclui vários tipos de aprimoramentos, principalmente no editor de linha de comando, opções para personalizar seu comportamento, globbing de nome de arquivo, recursos para fazer com que os usuários do C-shell (csh) se sintam mais à vontade e recursos extras extraídos do tcsh (outro shell personalizado).

## Instalação

Instalação do Zsh em derivados Debian:

```sh
sudo apt install zsh
```

## configuração

Instalar o framework `Oh-my-zsh`:

```sh
sh -c "$(curl -fsSL https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
```

Para alterar o tema, configure o arquivo `~/.zshrc`:

```sh
ZSH_THEME="af-magic"
```

Para definir como o shell do emulador de terminal, nas configurações do terminal, adicione o comando `zsh` como o comando de inicialização do terminal.
