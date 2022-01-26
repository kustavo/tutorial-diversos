# Mkvtoolnix

## Introdução

Conjunto de ferramentas para Linux e Windows para edição de arquivos `.mkv`.

## Instalação

Baixar [aqui](https://mkvtoolnix.download/downloads.html). Não é necessário usar a versão com instalador.

## Alterar a faixa de áudio padrão

1. Usar a ferramenta `mkvinfo` para ver as faixas de aúdio.

    ```sh
    mkvinfo <nome-arquivo>
    ```

1. Usar a ferramenta `mkvpropedit` executando o script abaixo. Escolher a faixa de áudio padrão com `--set flag-default=1` e as outras com `--set flag-default=0`.

    === "Windows"

    ```bat
    @echo off
    for /f "tokens=*" %%G in ('dir *.mkv /a-d /b') do (
        mkvpropedit.exe "%%G" --edit track:2 --set flag-default=0
        mkvpropedit.exe "%%G" --edit track:3 --set flag-default=1
        echo.
    )
    pause
    ```

    === "Linux"

    ```bash
    #!/bin/bash
    OIFS="$IFS"
    IFS=$'\n'
    for file in "/path/to/files/"*.mkv
    do
        echo $file
        mkvpropedit "${file}" --edit track:2 --set flag-default=0
        mkvpropedit "${file}" --edit track:3 --set flag-default=1
    done
    IFS="$OIFS"
    ```

## Referências

- <https://archive.is/GlAfO#selection-751.0-1085.7>
