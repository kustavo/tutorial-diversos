# Retropie

RetroPie é um projeto criado para rodar os jogos dos consoles antigos em um Raspberry utilizando como base o sistema operacional Raspbian, e integrando a ele uma grande variedade de emuladores.

## Erros e soluções

### Controle bluetooth do xbox

Controle bluetooth do Xbox não é pareado.

??? tip "Solução"

    Executar o comando:

    ```bash
    sudo nano /etc/modprobe.d/bluetooth.conf
    ```

    e adicionar a opção abaixo:

    ```ini
    options bluetooth disable_ertm=Y
    ```

## Referências

- <https://retropie.org.uk/forum/topic/6198/xbox-one-controller-via-bluetooth/36>
