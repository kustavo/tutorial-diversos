# Serviços e processos

[TOC]

[[_TOC_]]

## [service, systemctl] Serviços

O comando `service` é um comando de alto nível usado para iniciar e para serviços em diferentes distribuições linux e unix. Ele redireciona para um comando de baixo nível. Dependendo da distribuição linux, pode redirecionar para `systemctl` ou `/etc/init.d`. O comando `service` é adequdo para o gerenciamento básico de serviços, enquanto o comando `systemctl` possui mais opções de controle.

Exemplos:

```sh
# Verificar se houve falha ao iniciar
systemctl is-failed name.service

# Ou mascarando servições
systemctl mask name.service
```

A opção `mask` e uma versão mais forte de `disable`. Usando `disable` todos os `symlinks` do arquivo de unidade especificado são removidos. Usando `mask` as unidades serão linkadas para `/dev/null`. Isso será exibido se executar por exemplo `systemctl status halt.service`. A vantagem da máscara é impedir qualquer tipo de ativação, mesmo manual.
