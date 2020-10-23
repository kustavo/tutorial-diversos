# MkDocs

[MkDocs](https://www.mkdocs.org) é um gerador de site estático (SSG – Static Site Generators) rápido e simples que é voltado para criar documentações. Os arquivos de origem são em Markdown e configurados com um único arquivo de configuração YAML.

## Erros e soluções

Erros e soluções relacionados ao MkDocs.

### Caminhos relativos

Por padrão a configuração `use_directory_urls` é `true`, ou seja, caso o arquivo chame `pagina.md` a url de acesso será  `<site>\pagina\index.html` e não `<site>\pagina.html`. Portanto, usar o endereço relativo no atributo `src` pode não funcionar.

??? tip "Solução"

    Considerando a estrutura de arquivos:

    ```bash
    /docs/
    |-- img/
        |-- image.svg   # /docs/img/image.svg
    |-- index.md        # /docs/index.md
    |--pagina.md        # /docs/pagina.md

    ```

    Importando a imagem `image.svg` em `index.md` temos as seguintes possibilidades que funcionam:

    ```html
    ![](./img/image.svg)
    ![](img/image.svg)
    <img src="./img/image.svg" />
    <img src="img/image.svg" />
    ```

    Importando a imagem `image.svg` em `page.md`, ao usar o atributo `src` devemos voltar mais um nível no path "../", já que `use_directory_urls` adicionou mais nível ao criar o diretório `/pagina/`. Desta forma, temos as seguintes possibilidades que funcionam:

    ```html
    ![](../img/image.svg)
    ![](./img/image.svg)
    ![](img/image.svg)
    <img src="../img/image.svg" />
    ```

## Referências

- <https://github.com/mkdocs/mkdocs/issues/1757>
