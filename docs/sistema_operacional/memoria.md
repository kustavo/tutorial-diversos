# Memória

O Linux trabalha com memória virtual portanto medir o consumo de memória RAM de um processo não é uma tarefa simples.

Todo e qualquer acesso à memória passa pela Unidade de Gerenciamento de Memória (em inglês, _MMU - Memory Management Unit_) é um dispositivo de hardware que traduz endereços virtuais em endereços físicos, é geralmente implementada como parte da Unidade Central de Processamento (em inglês, _CPU - Central Processing Unit_), mas pode também estar na forma de um circuito integrado separado.

Os processos acessam a memória através de endereços virtuais. Durante o acesso à memória, a MMU consulta uma tabela de mapeamento (_page table_) e faz a conversão dos endereços virtuais do processos para os endereços físicos da RAM.

O uso da MMU e memórias virtuais possibilita a utilização de SWAP, controle de acesso, segurança e compartilhamento de memória.

Com os comandos `top` ou `ps aux` podemos ver a lista de processos e o quanto de memória cada processo consome. Entretanto são mostrados três tipos de memórias:
  
- virtual (_VIRT - Virtual Memory Size_) ou (_VSZ - Virtual Set Size_)
- residente (_RES - Resident Memory Size_) ou (_RSS - Resident Set Size_)
- compartilhada (SHR - Shared Memory Size)

```txt
  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND
16494 sprado    20   0  520304  34068   9648 S   3,0  0,2   0:08.12 chrome
```

Dependendo de como se observa o resultado, ele não aponta para a quantidade exata de memória usada pelos processos. O que o seu resultado realmente mostra é a quantidade de memória que usaria determinado processo se ele fosse o único processo em execução. Logicamente, um sistema Linux possui dezenas de processos em execução ao mesmo tempo, o que torna definitivamente os valores VIRT e RES "erados".

Muitas ferramentas, assim como o `ps` e `top`, não se preocupam com esse comportamento do sistema. Elas simplesmente mostram quanto de memória um processo está ocupando, independente se esta memória está sendo compartilhada com outros processos. Dois programas poderiam, portanto, utilizar a mesma biblioteca compartilhada, e mesmo assim ter o tamanho dessa biblioteca contabilizado na memória total de ambos os processos. Dessa maneira, a quantidade de memória ocupada pela biblioteca está sendo contabilizada duas vezes, gerando resultados enganadores caso não se saiba o que está realmente acontecendo.

!!! note "Notas"
    Infelizmente, um valor exato da quantidade de memória ocupada por um processo não é algo fácil de se obter. Não só é necessário saber como o sistema funciona, mas também decidir como lidar com algumas questões complexas. Se uma biblioteca compartilhada é usada apenas por um processo, o seu tamanho deve ser contabilizado apenas para esse processo? Se uma biblioteca compartilhada é usada por múltiplos processos, a quantidade de memória ocupada por ela deve ser distribuída entre os diferentes processos, ou apenas ignorada? Não existe uma regra fixa neste caso, pois cada situação terá uma resposta diferente. Assim fica fácil de entender a razão do `ps` e `top` não se esforçar para calcular um resultado mais "correto" do uso de memória, dada a ambiguidade.

## Virtual Memory

O espaço de endereço virtual é a quantidade de espaço de endereço que um processo está gerenciando. O espaço de endereço virtual contém tudo o que o processo pode acessar por meio de ponteiros (referências de endereço de memória). `VIRT` representa a quantidade de memória do programa que é capaz de acessar no momento presente. 

Se o seu programa obtiver acesso ao buffer de quadros da sua placa de vídeo, essa memória será mapeada para o espaço virtual do processo e receberá um endereço armazenado em um ponteiro. Arquivos de disco mapeados na memória (SWAP), memória compartilhada com outros processos e mapeamentos anônimos também são contabilizados no tamanho do espaço de endereço virtual. Praticamente tudo está no tamanho virtual. Se você resumir o tamanho de todos os intervalos de endereços listados `/proc/<PID>/maps`, ele deverá retornar aproximadamente o mesmo valor do tamanho virtual.

## Resident Memory

O espaço de endereço residente é a quantidade de memória que pertence especificamente ao processo que atualmente reside na memória (memória mapeada fisicamente para o processo). Isso significa a quantidade de memória que não está em troca. Observe que partes do processo podem estar na memória de troca mesmo quando o processo está em execução. O sistema operacional extrairá essas regiões da troca quando o processo tentar acessá-lo. Isso deve incluir a pilha, as pilhas de todos os threads e outros mapeamentos particulares. Se você olhar em `/proc/<PID>/maps`, as `stack`, `heap` e outros mapeamentos anônimos (aqueles sem caminhos de arquivos) ou são trocados ou contabilizada no tamanho residente.

Entretanto o espaço de endereço residente também não indica a quantidade de memória física total utilizada pelo processo. Outros processos podem estar compartilhando memória com ele. Por exemplo, quando o kernel carrega o código de uma biblioteca para a memória RAM, ele pode mapear esta mesma região de memória física para todos os processos que querem utilizar a biblioteca, economizando assim memória RAM, porque a biblioteca é carregada uma vez só e compartilhada por múltiplos processos. Um exemplo clássico é a biblioteca do sistema (`libc`). Portanto, este campo não serve como referência para a memória física utilizada (única e exclusivamente) pelo processo.

## Shared Memory

O espaço de endereço compartilhado é a quantidade de memória que pode pertencer a vários processos. Indica quanto do tamanho `VIRT` é realmente compartilhável (memória ou bibliotecas). No caso de bibliotecas, não significa necessariamente que toda a biblioteca é residente. Por exemplo, se um programa utiliza apenas algumas funções numa biblioteca, a biblioteca inteira é mapeada e serão contados em `VIRT` e `SHR`, mas apenas as partes do arquivo de biblioteca que contém as funções a ser utilizada irá, na verdade, ser carregado e ser contabilizado em `RES`.

Se você tiver quatro instâncias do mesmo aplicativo carregadas na memória, terá quatro instâncias do `heap` e pelo menos quatro pilhas, uma para cada processo (essa é a memória residente), mas terá apenas uma instância do código binário do programa e suas bibliotecas. Este é o espaço compartilhado. Não só ele inclui o código do programa binário e suas bibliotecas, mas também arquivos de localização, somente leitura de dados do programa, `SysV` e `POSIX` compartilhado segmentos de memória, semáforos, etc. Se você olhar no `/proc/<PID>/maps`, a maioria dos mapeamentos vinculados a arquivos de biblioteca e do programa são compartilhado.

## Comandos úteis

### pmap

```bash
# formato extenso
pmap -x <PID>
```

```txt
Address   Kbytes Mode  Offset           Device    Mapping

08048000      40 r-x– 0000000000000000 0fe:00000 kdeinit
08052000       4 rw— 0000000000009000 0fe:00000 kdeinit
08053000    1164 rw— 0000000008053000 000:00000   [ anon ]
40000000      84 r-x– 0000000000000000 0fe:00000 ld-2.3.5.so
40015000       8 rw— 0000000000014000 0fe:00000 ld-2.3.5.so
40017000       4 rw— 0000000040017000 000:00000   [ anon ]
40018000       4 r-x– 0000000000000000 0fe:00000 kedit.so
40019000       4 rw— 0000000000000000 0fe:00000 kedit.so
4066c000      68 r-x– 0000000000000000 0fe:00000 libkwalletclient.so.1.0.0
4067d000       4 rw— 0000000000011000 0fe:00000 libkwalletclient.so.1.0.0
4067e000       4 rw— 000000004067e000 000:00000   [ anon ]
4067f000    2148 r-x– 0000000000000000 0fe:00000 libkdecore.so.4.2.0
40898000      64 rw— 0000000000219000 0fe:00000 libkdecore.so.4.2.0
408a8000       8 rw— 00000000408a8000 000:00000   [ anon ]
...(cortado)...

mapped: 25404K    writeable/private: 2432K    shared: 0K
```

Cada biblioteca compartilhada (iniciadas por "lib") são listadas duas vezes: uma para o segmento de código e outra para o segmento de dados. O segmento de código tem a coluna referente ao modo em "r-x-", enquanto o segmento de dados possuí o modo "rw-".

O comando mosta em `writeable/private` a memória ocupada sem a quantidade de memória ocupada pelas bibliotecas compartilhadas.

### /proc/PID

```bash
sudo cat /proc/<PID>/status
```

```bash
sudo cat /proc/<PID>/smaps
```

### smem

Uma forma é identificar a quantidade de memória física consumida de forma exclusiva pelo processo é somar com uma proporção da quantidade de memória compartilhada com outros processos. É basicamente isso que o comando `smem --processfilter="chrome"` faz.

```txt
  PID User     Command                         Swap      USS      PSS      RSS 
15048 sprado   /opt/google/chrome/chrome          0   383364   395453   425564 
```

O campo USS (_Unique Set Size_) representa a quantidade de memória física mapeada de forma exclusiva para o processo, e somando mais uma proporção da memória compartilhada com outros processos tem-se o campo PSS (_Proportional Set Size_), que é o que você vai ter de mais perto do consumo de memória de um processo no Linux.

## Referências

- <https://sergioprado.org/como-identificar-o-consumo-de-memoria-de-um-processo-no-linux>
- <https://duda.blog.br/2008/02/entendendo-o-uso-de-memoria-no-linux>
- <http://mad3linux.blogspot.com/2016/05/a-diferenca-entre-virt-res-e-shr-no.html>
