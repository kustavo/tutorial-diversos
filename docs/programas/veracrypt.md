# VeraCrypt

[TOC]

## Introdução

<https://www.veracrypt.fr>

VeraCrypt é um utilitário freeware (software livre),, usado para criptografia on-the-fly (OTFE). Ele pode criar um disco virtual criptografado dentro de um arquivo ou criptografar uma partição ou o dispositivo de armazenamento inteiro com autenticação pré-boot.

## Problemas

### Codificação de caracteres no sistema de arquivos

Problemas ao exibir o nome dos arquivos com acentos.

Solução: Adicione o comando abaixo nas opções de montagem ou em "Preferences"  para uso geral.

```txt
iocharset=utf8
```
