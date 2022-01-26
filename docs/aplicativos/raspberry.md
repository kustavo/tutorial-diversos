# Raspberry Pi

## Introdução

Raspberry Pi é uma série de computadores de placa única do tamanho reduzido, que se conecta a um monitor de computador ou TV, e usa um teclado e um mouse padrão, desenvolvido no Reino Unido pela Fundação Raspberry Pi. Todo o hardware é integrado numa única placa.

## Configurações

### Configurar WIFI

Adicionar os comandos abaixo no arquivo `/etc/wpa_supplicant/wpa_supplicant.conf`. Este arquivo também pode ser criado na partição `boot`, pois será automaticamente copiado para `/etc/wpa_supplicant/wpa_supplicant.conf`.

```ini
country=BR
network={
    ssid="<nome_wifi>"
    psk="<senha_wifi>"
    key_mgmt=WPA-PSK
}
```

### Habilitar SSH

Na partição `boot`, criar um arquivo vazio chamado `SSH`.

Após a configuração, o acesso via SSH será:

```bash
Usuário: pi
Senha: raspberry

ssh pi@192.168.x.x
```

### Instalar o VNC

Instalação:

```bash
sudo apt install realvnc-vnc-server realvnc-vnc-viewer
```

Para habilitar, execute o comando abaixo e escolha a opção `VNC` em `Interfacing Options`.

```bash
sudo raspi-config
```

Após a configuração, o acesso via VNC será:

```bash
Usuário: pi
Senha: raspberry
```

## Referências

- <https://www.raspberrypi.org/documentation/configuration/wireless/wireless-cli.md>
- <https://www.raspberrypi.org/documentation/configuration/wireless/headless.md>
