# Windows

## Restaurar partição EFI

Caso queira formatar as partições EFI e MBR

```bash
diskpart
list disk
select disk 0 # selecionar disco que possui a partição EFI
list partition
select partition 1 # selecionar partição EFI
delete partition override
select partition 2 # selecionar partição MSR
delete partition override
list partition
select disk 0
create partition efi size=700 # criar partição EFI
list partition
select partition 1 # selecionar partição EFI
format quick fs=fat32 label="EFI"
create partition msr size=20
list partition
list vol
exit
bcdboot x:\windows # x é a partição onde está o windows (pode mudar de c:)
```

Caso não queira formatar as partições EFI e MBR

```bash
bcdboot x:\windows # x é a partição onde está o windows (pode mudar de c:)
```
