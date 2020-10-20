# Usuário e variáveis de ambiente

[TOC]

## Lista do histórico de login de usuários

Lista do histórico de login de usuários. Informa o tempo em que foi feito o login, assim como quando o máquina foi reiniciada ou desligada.

```sh
$ last
[-x] Tipo de registro.
    [reboot] Registros de quando a máquina foi ligada.
    [shutdown] Registros de quando a máquina foi desligada.
[-R] Ocultar informações do hostname.
[-n] Número de registros a serem mostrados.
```

Exemplos:

Obter informações de login de todos usuários.

```sh
last
```

Obter informações de login de um usuário específico.

```sh
last USUARIO
```

Obter informações dos 10 últimos registros de quando a máquina foi ligada e desligada.

```sh
last -x reboot shutdown -n 10
```

## $PATH

É uma variável do sistema Linux que indica trajetória dos binários, que podem ser executados sem indicar o caminho completo de onde se encontram.

Exibir as variáveis de ambiente que já foram definidas.

```sh
echo "${PATH//:/$'\n'}"
```

Adicionar mais um caminho nas variáveis de ambiente.

```sh
export PATH=$PATH:/NOVO-CAMINHO
```

Para remover um caminho é necessário adicionar todos caminhos novamente, exceto o que será removido. Para isso basta copiar o conteudo de $PATH, remover o caminho desejado e defin[i-lo novamente em $PATH.

```sh
echo $PATH
export PATH=/CAMINHO1:/CAMINHO2:/CAMINHO3
```

### Diretórios e arquivos que formam o $PATH

Nível de usuário: `.bashrc`, `.bash_profile`, `.bash_login` e `.profile`.

Nível de sistema: `/etc/environment`, `/etc/profile`, `/etc/profile.d/` e `/etc/bash.bashrc`.
