# Gnome terminal

GNOME Terminal é um emulador de terminal para o ambiente GNOME escrito por Havoc Pennington. 

## Configurações

### Autocompletar case-insensitive

```bash
echo 'set completion-ignore-case On' >> ~/.inputrc
```

### Informações do Git (Shell Bash)

Editar o arquivo `~/.bashrc` e adicionar os comandos abaixo:

```bash
parse_git_branch () {
  git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ (\1)/'
}
PS1='\[\033[01;96m\]\u@\h\[\033[00m\]:\[\033[01;37m\]\w\[\033[01;33m\]$(parse_git_branch)\[\033[00m\]\$ '
```
