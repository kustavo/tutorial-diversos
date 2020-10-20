# MusicBrainz Picard

[TOC]

## Introdução

Renomeação de arquivos para ID3 e vice-versa com opção de busca em bancos on-line.

## Configuração

1. Em `Options` habilitar a opção `Rename Files`

1. Em `Options->File Naming` usar o padrão

    ```sh
    $if2(%albumartist%,%artist%)/$if($ne(%albumartist%,),%album%/,)$if($gt(%totaldiscs%,1),%discnumber%-,)$if($ne(%albumartist%,),$num(%tracknumber%,2) ,)$if(%_multiartist%,%artist% - ,) - %title%
    ```

1. Ao salvar os arquivos serão renomeados
