# Dispositivos de Hardware e Dispositivos de I/O

[TOC]

## Memória

### Informações da memória

```sh
cat /proc/meminfo
```

### Informações da quantidade de memória RAM e swap

Mostra a quantidade de memória RAM e swap.

```sh
$ free
[-h] Grandeza informada baseada no tamanho.
[-m] Grandeza informada em MB.
[-g] Grandeza informada em GB.
```

## Processador

### Informações da CPU

```sh
cat /proc/cpuinfo
```

## Placa de wireless

Instalacao da placa Archer T9E no Archer Linux

```sh
sudo pacman -S b43-firmware
sudo pacman -S broadcom-wl
```

Debian/Ubuntu

Pacote bcmwl-kernel-source

## Teclado

### Mudança de Layout

Configuração do teclado em layout americano.

```sh
setxkbmap -model pc104 -layout us_intl
```

## Bluetooth

Inicar serviço e habilitart após o boot.

```sh
sudo systemctl start bluetooth
sudo systemctl enable bluetooth
```

### Caixas de Som

1. Instalar os pacotes: pulseaudio-module-bluetooth, bluez, bluez-tools

1. Instalar o pacote "pavucontrol" para opções avançadas de controle de áudio, como controlar qual saída de áudio para cada aplicação.

### Auto connect

Conectar automaticamente ao dispositivo bluetooth.

```sh
$ bluetoothctl
[bluetooth]$ scan on # espere até encontrar
[bluetooth]$ scan off
[bluetooth]$ connect 34:88:5D:87:C0:A6
[bluetooth]$ trust 34:88:5D:87:C0:A6
```

#### Problemas

- Não aparece o dispositivo bluetooth de áudio nas opções de output quando usando o Gnome com GDM:

    When using GDM, another instance of PulseAudio is started, which "captures" your bluetooth device connection. This can be prevented by masking the pulseaudio socket for the GDM user:

    ```sh
    sudo mkdir -p  /var/lib/gdm/.config/systemd/user
    sudo ln -s /dev/null  /var/lib/gdm/.config/systemd/user/pulseaudio.socket
    ```

## Monitor

### Adicionar resolução

Ver dispositivos de tela

```sh
xrandr
```

Adicionar resolução

```sh
# criar novo modelo "1366x768-0"
xrandr --newmode "1366x768-0" 75.61  1366 1406 1438 1574  768 771 777 800 -hsync -vsync

# vincular modelo ao monitor (monitor do notebook - sDP1)
xrandr --addmode eDP1 "1366x768-0"

# ativar a saida para o novo modo
xrandr --output eDP1 --mode "1366x768-0"
```

Para salvar o novo modo permanente, basta criar o arquivo `~/.xprofile` e copiar os comandos anteriores de adicionar resolução.

```sh
#!/bin/bash

xrandr --newmode "1366x768-0" 75.61  1366 1406 1438 1574  768 771 777 800 -hsync -vsync
xrandr --addmode eDP1 "1366x768-0"
xrandr --output eDP1 --mode "1366x768-0"
```

## Disco

### Listar partições

Lista as partições

```sh
$ df
[-h] Grandeza informada baseada no tamanho.
```

### Badblocks

```sh
$ badblocks
[-b] Especifica o tamanho de cada bloco.
[-s] Mostra o avanço do procedimento.
[-v] Verbose mode.
[-c] 10240 = Verifica 10 mil blocos de HD por vez.
[]
```

Verificando apenas modo leitura.

```sh
sudo badblocks -sv -c 1024 /dev/sdaX
```

Verificando em modo leitura e escrita sem perda de dados.

```sh
sudo badblocks -nsv -c 10240 /dev/sdaX
```

Verificando em modo leitura e escrita com perda de dados.

```sh
sudo badblocks -wsv -c 10240 /dev/sdaX
```

badblocks [ -svwnf ] [ -b block-size ] [ -c blocks_at_once ] [ -e max_bad_blocks ] [ -d read_delay_factor ] [ -i input_file ] [ -o output_file ] [ -p num_passes ] [ -t test_pattern ] device [ last-block ] [ first-block ]

### Agendamento de atividade

Entrar em modo de dormência até o tempo determinado. Substitui o antigo comando **apmsleep**.

```sh
$ rtcwake
[-m] modo de desligamento.

    [standby] Modo suspender (standby). Economia de energia pequena.
    [mem] Suspender para a memória RAM. Todos os dispositivos são colocados em estado de baixo consumo, exceto a memória RAM, pois deve ficar ligada para evitar que as informações se percam. Economia de energia moderada.
    [disk] Suspender para o disco. Todo o conteúdo da memória RAM é salvo no disco, após isso o computador é desligado. Economia de energia alta.
    [off] Desligar o computador completamente. A BIOS precisa dar suporte.
    [no] Não suspende o computador, apenas define o horário para acordar.

[-v] Modo verboso. Mostrar detalhes.
[-t] Tempo absoluto.
[-s] Tempo em segundos.
[-l] Tempo informado é local, usado pelo sistema.
[-u] Tempo informado é o UTC.
```

Exemplos:

Suspender para o disco e acordar depois de duas horas.

```sh
rtcwake -m disk -s 7200
```

Desligar e acordar 19:00.

```sh
sudo rtcwake -m off -t 19:00
```

Não suspender ou desligar imediatamente, somente definir para acordar 8:00.

```sh
sudo rtcwake -m no -t 8:00
```

## Outros

### Informação dos barramentos PCI

Mostra informação dos barramentos pci e todos os dispositivos conectados a ele.

```sh
$ lspci
[-v] Modo detalhado nível 1
[-vv] Modo detalhado nível 2
[-vvv] Modo detalhado nível 3
```
