# Sessão e usuário

## Principais comandos

### last

Comado para listar o histórico de login de usuários. Informa o tempo em que foi feito o login, assim como quando o máquina foi reiniciada ou desligada:

```bash
$ last
[-x] Tipo de registro.
    [reboot] Registros de quando a máquina foi ligada.
    [shutdown] Registros de quando a máquina foi desligada.
[-R] Ocultar informações do hostname.
[-n] Número de registros a serem mostrados.
```

Exemplos:

Obter informações de login de todos usuários.

```bash
last
```

Obter informações de login de um usuário específico.

```bash
last <usuario>
```

Obter informações dos 10 últimos registros de quando a máquina foi ligada e desligada.

```bash
last -x reboot shutdown -n 10
```
