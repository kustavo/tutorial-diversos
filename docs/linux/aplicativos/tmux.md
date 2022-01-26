# Tmux

tmux é um multiplexador de terminal de código aberto para sistemas operacionais do tipo Unix. Ele permite que várias sessões de terminal sejam acessadas simultaneamente em uma única janela. É útil para executar mais de um programa de linha de comando ao mesmo tempo. Também pode ser usado para separar processos de seus terminais de controle, permitindo que sessões remotas permaneçam ativas sem serem visíveis.

## Comandos

### Criar sessão

```bash
# Criar sessão identificada com número
tmux

# Criar sessão nomeada
tmux new -s <nome-sessao>
```

### Desanexar (_detach_)

Enviar sessão para background, mas continuando ativa mesmo quando o terminal ou conexão ssh é encerrada.

`Ctrl+b e então d`

### Anexar (_Attach_)

Entrar na sessão que estava em background.

```bash
# Entrar na primeira sessão
tmux attach
# ou
tmux a

# Especificar o número da sessão
tmux a <numero-sessao>

# Especificar o nome da sessão
tmux a -t <nome-sessao>
```

### Listar sessões

```bash
tmux ls
```

## Referências

- <https://tmuxguide.readthedocs.io/en/latest/index.html>

