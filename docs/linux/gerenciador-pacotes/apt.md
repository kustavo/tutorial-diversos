# Apt

[TOC]

## Comandos

### Verificar a versão do pacote

```sh
# Pacote que será instalado
apt policy <nome-pacote>

# Pacote instalado
apt-cache policy  <nome-pacote>
```

### Verificar dependências

```sh
# Pacote que será instalado
apt rdepends <nome-pacote>

# Pacote instalado
apt-cache rdepends <nome-pacote>
```
