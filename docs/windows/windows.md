# Windows

## Introdução

Microsoft Windows (ou simplesmente Windows) é uma família de sistemas operacionais desenvolvidos, comercializados e vendidos pela Microsoft.

## Baixar ISO das últimas versões do Windows

<https://uupdump.net>

## Formatar e restaurar as partições EFI e MSR

```bash
diskpart
list disk
select disk <numero>           # disco que possui a partição EFI

list partition
select partition <numero>      # partição EFI
delete partition override
select partition <numero>      # partição MSR
delete partition override

list partition
select disk <numero>           # disco que possui a partição EFI
create partition efi size=700  # criar partição EFI com 700 MB

list partition
select partition <numero>      # partição EFI
format quick fs=fat32 label="EFI"
create partition msr size=20   # criar partição MSR com 700 MB

list partition
list vol
exit

bcdboot x:\windows            # x é a partição onde está o windows
```

## Restaurar a partição EFI

```bash
bcdboot x:\windows # x é a partição onde está o windows
```

## Compartilhar Wifi para conexão cabeada

1. Abra as configurações da Internet, visualize as conexões de rede e altere opções do adaptador ("Change adapter options").

1. Selecione o adaptador Wifi e a Ethernet.

1. Clique com o botão direito e faça uma ponte de rede entre os dois ("network bridge").

1. Selecione a ponte de rede e a desative e exclua.

1. Clique com o botão direito no adaptador Wifi e clique em propriedades. Vá para a guia de compartilhamento e ative o compartilhamento novamente (certifique-se de que ambas as caixas estejam marcadas).

1. Vá para a guia de configurações e certifique-se de que todas as caixas estejam marcadas, exceto "Microsoft Network Adapter Multiplexor Protocol".
