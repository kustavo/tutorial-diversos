# Windows

## Introdução

Microsoft Windows (ou simplesmente Windows) é uma família de sistemas operacionais desenvolvidos, comercializados e vendidos pela Microsoft.

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
