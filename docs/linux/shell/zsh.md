# Z-shell

O shell Z-shell ou Zsh, como também é conhecido, se assemelha ao Korn shell (ksh). Zsh é uma extensão do shell Bourne (Sh) com muitos recursos novos e suporte para plug-ins e temas, incluindo alguns recursos do Bash, Ksh e Tcsh.

Em Junho de 2019, A Apple anunciou que o Zsh seria adotado como shell padrão em substituição ao Bash.

Algumas de suas características:

- Autocompletar na linha de comando programável que pode ajudar o usuário a digitar opções e argumentos para a maioria dos comandos usados.
- Compartilhar o histórico de comandos entre os shells em execução.
- Extensão do globbing (casamento de padrões) que permite a especificação de arquivo sem a necessidade de executar um programa externo, como `find`.
- Manipulação de variável/vetor aprimorada.
- Edição de múltiplas linhas de comandos em um único buffer
- Correção ortográfica e preenchimento automático de nomes de comandos e argumentos.
- Vários modos de compatibilidade, por exemplo Zsh pode se passar por um shell Bourne quando executado como `/bin/sh`.
- Prompts com temas, incluindo a capacidade de colocar informações de prompt no lado direito da tela e ocultá-los automaticamente ao digitar um comando longo.
- Módulos carregáveis, fornecendo, entre outras coisas: controle total de sockets TCP e Unix, um cliente FTP e funções matemáticas estendidas.
- Comando `where` integrado. Funciona como o comando `which` mas mostra todas as localizações do comando de destino nos diretórios especificados em `$PATH` em vez de apenas aquele que será usado.
- Diretórios nomeados. Isso permite que o usuário configure atalhos como `~mydir`, que se comportam da mesma forma que `~` e `~user`.

## Instalação

Instalação do Zsh em derivados Debian:

```bash
sudo apt install zsh
```

## Framework Oh-my-zsh

Oh My Zsh é um framework de código-fonte aberto para gerenciar as configuração do Zsh.

### Instalação

Instalar o framework `Oh-my-zsh`:

```bash
sh -c "$(curl -fsSL https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
```

### Alterar tema

Para alterar o tema, altere o arquivo `~/.zshrc`:

```bash
ZSH_THEME="af-magic"
```

### Mostrar path completo

Alterar o arquivo do template:

```bash
~/.oh-my-zsh/themes/robbyrussell.zsh-theme
```

substituir `%c` por `%~` e executar:

```bash
source ~/.zshrc
```

## Configurar o emulador de terminal

Para definir como o shell do emulador de terminal, nas configurações do terminal, adicione o comando `zsh` como o comando de inicialização do terminal.

## Adicionar diretório ao $PATH

Adicionar ao arquivo `~/.zshrc`:

```bash
export PATH=$PATH:<caminho>
```

Exemplo:

```bash
export PATH=$PATH:~/Programas/node-v14.17.6-linux-x64/bin
```

## Adicionar alias

Adicionar ao arquivo `~/.zshrc`:

```bash
alias <nome>="<comando>"
```

Exemplo:

```bash
alias lsa="ls -la"
```

## Referências

- <https://en.wikipedia.org/wiki/Z_shell>
