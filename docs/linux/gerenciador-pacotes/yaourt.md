# Yaourt

O Yaourt (Yet AnOther User Repository Tool) é um gerenciador de pacotes desenvolvido para completar o conhecido Pacman. Foi desenvolvido por Julien Mischkowitz e Tuxce; usuários da Comunidade Arch Linux Francês e tem como o diferencial conseguir pesquisar, atualizar e instalar pacotes do AUR.

## Comandos

Instalar ou atualizar um pacote. O parâmetro `--noconfirm` desabilita as perguntas durante de instalação.

    ```bash
    yaourt -S pacote --noconfirm
    ```

Instale um pacote local ou a partir da web

    ```bash
    yaourt -U caminho_do_pacote --noconfirm
    ```

Encontre um pacote

    ```bash
    yaourt pacote
    ```

Informação sobre um pacote instalado

    ```bash
    yaourt -Qi pacote
    ```

Obter informações sobre um pacote nos repositórios

    ```bash
    yaourt pacote -Si
    ```

Remover um pacote

    ```bash
    yaourt -R pacote
    ```

Remover um pacote e suas dependências que não são necessários a outro pacote instalado

    ```bash
    yaourt -Rs pacote
    ```

Remover um pacote e suas dependências e todos os pacotes que dependem dele

    **ATENÇÃO**: Esta operação é recursiva, e deve ser usado com muito cuidado, pois poderia eliminar um pacote principal e corromper o sistema.

    ```bash
    yaourt -Rsc pacote
    ```

Remover um pacote que é exigido por outro, sem retirar suas dependências

    ```bash
    yaourt -Rdd pacote
    ```

Atualizar os pacotes de banco de dados

    ```bash
    yaourt -Syy
    ```

Limpar o cache de pacotes

    ```bash
    yaourt -Scc
    ```

Atualizar o sistema

    ```bash
    yaourt -Syu
    ```

A atualização do sistema, incluindo pacotes do AUR instalados

    ```bash
    yaourt -Syua
    ```
