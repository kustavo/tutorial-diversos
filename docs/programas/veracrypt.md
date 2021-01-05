# VeraCrypt

## Introdução

VeraCrypt é um utilitário freeware (software livre),, usado para criptografia on-the-fly (OTFE). Ele pode criar um disco virtual criptografado dentro de um arquivo ou criptografar uma partição ou o dispositivo de armazenamento inteiro com autenticação pré-boot.

## Erros e soluções

### Codificação de caracteres no sistema de arquivos

Problemas ao exibir o nome dos arquivos com acentos.

??? tip "Solução"

    Adicione o comando abaixo nas opções de montagem ou em "Preferences" para uso geral.

    ```ini
    iocharset=utf8
    ```
