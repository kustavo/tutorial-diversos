# Dispositivos de hardware e dispositivos

## Memória

### Ver informações da memória

Comando para ver as informações da memória:

```bash
cat /proc/meminfo
```

Comando para ver a quantidade de memória RAM e swap:

```bash
$ free
[-h] Grandeza informada baseada no tamanho.
[-m] Grandeza informada em MB.
[-g] Grandeza informada em GB.
```

## Processador

### Ver informações da CPU

Comando para ver as informações da CPU:

```bash
cat /proc/cpuinfo
```

## Placa de wireless

### Instalação

#### Archer T9E

```bash
# Arch Linux
sudo pacman -S b43-firmware
sudo pacman -S broadcom-wl

# Debian/Ubuntu
sudo apt install bcmwl-kernel-source
```

## Teclado

### Mudança de Layout

Configuração do teclado em layout americano internacional.

```bash
setxkbmap -model pc104 -layout us_intl
```

## Bluetooth

### Instalação

**Bluetooth 5.0 Realtek RTL8761B:**

Baixar os drivers [aqui](https://linuxreviews.org/Realtek_RTL8761B) e copiar para:

```bash
/lib/firmware/rtl_bt/rtl8761b_fw.bin
/lib/firmware/rtl_bt/rtl8761b_config.bin
```

### Problemas

Bluetooth não é habilitado.

```bash
sudo rmmod btusb
sudo modprobe btusb
```

Serviço não inicia automaticamente ao iniciar o sistema.

```bash
systemctl start bluetooth
systemctl enable bluetooth
```

## Disco

### Listar partições

Comando para listar as partições:

```bash
$ df
[-h] Grandeza informada baseada no tamanho.
```

### Badblocks

Comando para identificar e isolar badblocks:

```bash
$ badblocks
[-b] Especifica o tamanho de cada bloco.
[-s] Mostra o avanço do procedimento.
[-v] Verbose mode.
[-c] 10240 = Verifica 10 mil blocos de HD por vez.
[]
```

Exemplos:

Verificando apenas modo leitura.

```bash
sudo badblocks -sv -c 1024 /dev/sdaX
```

Verificando em modo leitura e escrita sem perda de dados.

```bash
sudo badblocks -nsv -c 10240 /dev/sdaX
```

Verificando em modo leitura e escrita com perda de dados.

```bash
sudo badblocks -wsv -c 10240 /dev/sdaX
```

## Outros

### Modo de dormêcia

Comando para o sistema entrar em modo de dormência até o tempo determinado. Substitui o antigo comando **apmsleep**.

```bash
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

```bash
rtcwake -m disk -s 7200
```

Desligar e acordar 19:00.

```bash
sudo rtcwake -m off -t 19:00
```

Não suspender ou desligar imediatamente, somente definir para acordar 8:00.

```bash
sudo rtcwake -m no -t 8:00
```

### Informação dos barramentos PCI

Comando para mostrar informações dos barramentos pci e todos os dispositivos conectados a ele.

```bash
$ lspci
[-v] Modo detalhado nível 1
[-vv] Modo detalhado nível 2
[-vvv] Modo detalhado nível 3
```
