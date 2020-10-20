# Raspberry

[TOC]

## Instalação

Instalação da imagem usando o `BalenaEtcher`.

## Configuração sem monitor

### Configurar WIFI

Adicionar os comandos abaixo no arquivo `/etc/wpa_supplicant/wpa_supplicant.conf`. Este arquivo também pode ser criado na partição `boot`, pois será automaticamente copaido para `/etc/wpa_supplicant/wpa_supplicant.conf`.

```ini
country=BR
network={
    ssid="NOME_WIFI"
    psk="SENHA_WIFI"
    key_mgmt=WPA-PSK
}
```

### Habilitar SSH

Na partição `boot`, criar um arquivo vazio chamado `SSH`.

Acesso:

```bash
Usuário: pi
Senha: raspberry

ssh pi@192.168.x.x
```

### VNC

Instalação:

```bash
sudo apt install realvnc-vnc-server realvnc-vnc-viewer
```

Para habilitar, execute o comando abaixo e escolha a opção `VNC` em `Interfacing Options`.

```bash
sudo raspi-config
```

```bash
Usuário: pi
Senha: raspberry
```

## Links

- <https://www.raspberrypi.org/documentation/configuration/wireless/wireless-cli.md>

- <https://www.raspberrypi.org/documentation/configuration/wireless/headless.md>
