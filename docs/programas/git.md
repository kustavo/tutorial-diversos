# Git

[TOC]

## Autenticação via SSH

```sh
cd ~/.ssh
ls -l
```

Se aparecer os arquivos `id_rsa  id_rsa.pub  known_hosts` quer dizer que já existe uma chave privada e pública para ser usada pelo SVN. Caso contrário é necessário executar:

```sh
ssh-keygen
```

Copie o conteúdo do arquivo `id_rsa.pub` para as configurações de SSH disponível no site do servidor.

## Comandos

### config

Configurar nome do usuário e email que irá acessar o repositório

```sh
git config --global user.name "Nome Usuário"
git config --global user.email "email@gmail.com"
```

Ver as configurações definidas

```sh
git config --global --list
```

### clone

Clone tem efetivamente um checkout de tudo, sem colocar algum "bloqueio" nesses arquivos no repositório de referência

```sh
git clone https://gitlab.com/user/user-project.git
```

Entrar no reposítorio local criado

```sh
cd user-project
```

### status

Verifica o status dos arquivos em relação ao último checkout

```sh
git status
```

### branch

Listar as branchs

```sh
git branch
```

Ver branch atual

```sh
git rev-parse --abbrev-ref HEAD
```

Criar branch

```sh
git branch <nome>
```

Criar branch mais checkout

```sh
git checkout -b <nome>

# Atalho para:
# git branch <nome>
# git checkout <nome>
```

Criar branch mais checkout informando branch pai

```sh
git checkout -b <branch-filha> <branch-pai>
```

Excluir branch

```sh
git branch -D <NOME>
```

### checkout

Definir qual branch será usada

```sh
git checkout <NOME>
```

### merge

Mesclar uma branch com outra ou com a master

```sh
git merge <NOME>
```

### add

Adiciona os arquivos modificados ao controle de versionamento

```sh
git add -A
```

ou

```sh
git add .
```

Adiciona somente um arquivo específico ao controle de versionamento

```sh
git add <ARQUIVO>
```

### commit

Versiona os arquivos que foram adicionados pelo comando `add`.

```sh
git commit
```

Commit já informando a mensagem de descrição

```sh
git commit -m "Mensagem commit"
```

Commit já adicionando os arquivos (add + commit)

```sh
git commit -am "Mensagem commit"
```

Commit sem informar mensagem (add + commit)

```sh
git commit -am.
```

### remote prune

Remove todas *branchs* locais que não não estão mais no lado remoto.

```sh
git remote prune origin
```

###  log

Ver os últimos commits

```sh
git log
```

### fetch

Baixa os HEADs com nomes ou tags de um ou mais repositórios (caso você tenha outro remote além do origin configurado), junto com os objetos necessários para completá-los. Basicamente ele atualiza as referências locais com relações às remotas, mas não faz o merge com o branch local.

```sh
git fetch
```

### pull

Receber os arquivos que estão no repositório

Incorpora mudanças de um repositório remoto para o branch local. É equivalente a `$ git fetch` seguido de `$ git merge FETCH_HEAD`.

```sh
git pull origin master
```

### push

Enviar os arquivos que receberam commit para o repositório

```sh
git push -u origin master
```

ou

```sh
git push origin master
```

origin master é a branch mestre no repositório remoto chamado origin

### difftool

Comparar arquivos modificados com os arquivos já versionados

```sh
git difftool
```

### rm

Remover arquivo

```sh
git rm <ARQUIVO>
```

### mv

Mover arquivo

```sh
git mv <ARQUIVO ORIGEM> <ARQUIVO DESTINO>
```
