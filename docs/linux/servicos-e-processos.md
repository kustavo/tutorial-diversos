# Serviços e processos

## Init

Em sistemas operacionais baseados em Unix, o sistema de inicialização init (abreviação em inglês de *initialization*) é o primeiro processo iniciado durante a inicialização do sistema operacional. Portanto, por padrão, possui o identificador do processo (PID) igual a `1`.

O init é um processo daemon (executado em segundo plano) que continua executando até o sistema ser desligado. Ele é o processo ancestral direto ou indireto de todos os outros processos, portanto adota automaticamente todos os processos órfãos.

Algumas versões mais conhecidas de init são: SysVinit, Upstart, Systemd e OpenRC.

### SysVinit

Foi um dos primeiros e mais utilizados init nas diversas distribuições Linux. Após iniciar o processo `/sbin/init`, o SysV lê o arquivo `/etc/inittab` para determinar o nível de execução (runlevel) padrão do sistema e iniciar os demais serviços. Os níveis de execução definem quais serviços deverão ser executados (ou finalizados) no sistema em um determinado nível (numerados de 0 a 6).

Basicamente são definidos 8 níveis de execução para a inicialização.

|Runlevel|Significado|
|---|---|
|0| Encerra o sistema (halt).|
|1| Inicializa o sistema em modo monousuário. Utilizado pelo superusuário para realizar atividades básicas para manutenção do sistema (sem acesso à rede ao compartilhamento de arquivos).|
|2, 3, 4, 5| Inicializa o sistema em modo multiusuário.|
|6| Reinicializa o sistema (reboot).|
|S| Joga o sistema no modo monousuário sem antes parar os processos em execução.|

!!! note ""
    Os runlevels 0, 1 e 6 são comuns a qualquer distribuição Linux.

Os scripts executados quando o sistema entra no `runlevel X` estão no diretório `/etc/rcX.d`, onde `X` pode ter valor de 0 a 6. Na realidade, estes diretórios apenas contém links simbólicos para os scrips localizados no diretório `/etc/init.d`. O diretório `/etc/init/` possui os arquivos de configuração dos processos inicializados pelo init.


#### Principais comandos

Comando para ver o runlevel usado:

```bash
runlevel
```

Comando para encerrar o sistema:

```
sudo init 0
```

Comando para reiniciar o sistema:

```
sudo init 6
```

Comando para alterar o runlevel para o nível 3:

```
sudo init 3
```

### Upstart

Upstart é um sistema de inicializaçãoÉ baseado em eventos, onde os serviços do sistema podem ser associados a estes eventos. O Upstart  define o que fazer quando um evento começa, muda ou termina. As metas do projeto eram compatibilidade total e fácil transição a partir do antigo SysVinit.

Upstart foi criado para o Ubuntu em 2006 e adotado por várias outras distribuições ao longo dos anos; entretanto, deixou de ser adotado no próprio Ubuntu após a decisão de adotar o Systemd no Debian.


### Systemd

Systemd é um sistema de inicialização presente atualmente na maioria das distribuições Linux. Desde a época em que foi lançado, o Systemd trouxe uma rica quantidade de recursos e funcionalidades em comparação aos tradicionais SysVinit e Upstart.

Na prática, o Systemd assume o controle assim que o kernel é ativado pelo gerenciador de bootloader (como por exemplo, Grub). Em segida, são carregados todos os dispositivos (placa de rede, processador gráfico etc.) e processos que se iniciam com o sistema.

Uma das vantagens do Systemd é a sua arquitetura e modo de funcionamento. Nele são usadas unidades de socket, que são arquivos de configuração que codificam informações relacionadas à comunicação entre processos (IPC - Inter Process Communication), a soquetes de rede ou a arquivos FIFO.

Tal capacidade permite que todos os daemons requisitados no boot sejam carregados simultaneamente, bem como possibilita a transmissão coordenada entre dois sockets, resultando a rápida inicialização do sistema operacional.

Para efeitos de comparação, o SysVinit, utilizado em distros mais antigas do Linux, carrega todos os serviços (um por vez, automática e ordenadamente), utilizando shell scripts durante o boot. Em razão disso, ele é um sistema lento e propício a problemas.

#### Arquitetura do Systemd

Basicamente, a estrutura do Systemd parte da organização de suas tarefas em unidades (units). Abaixo, algumas classes de units que constituem o Systemd:

`service`
:   Tais unidades são os serviços presentes no sistema operacional acessíveis ao usuário;

`timer`
:   Temporizadores usados para determinar ações para um serviço usando como base o tempo (não confundir com o cron);

`mount`
:   Arquivo de configuração que codifica informações sobre um diretório controlado e supervisionado pelo Systemd;

`Target`
:   Grupos de unidades que reúnem todas as units necessárias para iniciar um determinado serviço;

`snapshot`
:   Mecanismo usado para criar snapshots dinâmicos do estado atual do Systemd manager, útil para retomar o estado após problemas com indisponibilidade, por exemplo;

`path`
:   Unidades especialmente utilizadas para monitorar arquivos e diretórios para eventos e, também, executar serviços;

`socket`
:   Arquivo de configuração que armazena informações acerca de um IPC ou soquete de rede ou arquivo FIFO;

`swap`
:   Guarda informações relativas a dispositivos usados para swapping, bem como serviços que utilizam de memória Swap.

Cada serviço é alocado pelo Systemd em um grupo de controle dedicado (control group, ou cgroup). No cgroup são organizas informações voltadas aos processos que fazem parte do grupo, como limite, supervisão e contabilização de recursos computacionais que eles consomem.

O controle desses grupos é feito a partir de utilitários que acompanham o Systemd, tais como: `journalctl`, `cgls`, `cgtop` e `systemctl`.

#### Systemctl

O utilitário systemctl auxilia no controle do próprio Systemd e, também, atua como gerenciador de serviços. Ele nos permite decidir o que fazer com os processos: monitorar, encerrar, iniciar, analisar, recarregar, checar status etc.

##### Principais comandos

Comando para listar todos os serviços disponíveis:

```bash
systemctl list-unit-files --type=service
```

Comados para iniciar, reiniciar, parar, recarregar configurações e ver status de um serviço:

```bash
systemctl start <servico>.service
systemctl restart <servico>.service
systemctl stop <servico>.service
systemctl reload <servico>.service
systemctl status <servico>.service
```

Comando para verificar se houve falha ao iniciar um serviço:

```bash
systemctl is-failed <nome-servico>.service
```

Comandos para habilitar e desabilitar um serviço é mostrado abaixo. Um serviço habilitado é iniciado automaticamente sempre que iniciar o sistema.

```bash
systemctl enable <servico>.service
systemctl disable <servico>.service
```

Comando para verificar se um serviço está ativo (rodando)

```bash
systemctl is-active <servico>.service
```

Comando para matar um processo:

```bash
systemctl kill <servico>
```

Comandos para reiniciar e encerrar o sistema:

```bash
systemctl reboot
systemctl shutdown
```

Comando para verificar o nível de execução:

```bash
systemctl get-default
```

Comando para listar todas as unidades ativas:

```bash
systemctl list-units
```

Comando para listar todas as unidades ativas incluindo aquelas o Systemd não tentou analisar e carregar na memória:

```bash
systemctl list-unit-files
```

Comando para exibir os detalhes do arquivo de unidade:

```bash
systemctl cat <servico>.service
```

Comado para ver a árvore de dependências da unidade:

```bash
systemctl list-dependencies <servico>.service
```

O comando para mascarar um serviço é mostrado abaixo. A opção `mask` é uma versão mais forte de `disable`. Usando `disable` todos os `symlinks` do arquivo de unidade especificado são removidos. Usando `mask` as unidades serão linkadas para `/dev/null`. Isso será exibido se executar por exemplo `systemctl status halt.service`. A vantagem da máscara é marcar uma unidade como completamente não inicializável e impedir qualquer tipo de ativação, mesmo que manual.

```bash
systemctl mask <nome-servico>.service
```

### OpenRC

Existem outros sistemas de inicialização, como OpenRC, que assim como o Systemd, o OpenRC é compatível com o SysVinit, mas vai além: ele é compatível com FreeBSD e NetBSD. Além da portabilidade, o OpenRC tem como característica o uso de scripts para inicializar serviços, enquanto o Systemd apenas aciona scripts — e quando estes são necessários.

Embora o Systemd seja mais robusto, a sua complexidade tem levado muita gente a adotar o OpenRC, que é simples, minimalista e rápido. Enquanto o Systemd é considerado monolítico, o OpenRC é visto como um SysVinit incrementado.

## Referências

- <https://e-tinet.com/linux/systemd>
- <https://en.wikipedia.org/wiki/Init>
- <https://guialinux.uniriotec.br/init>
- <http://www.dltec.com.br/blog/linux/sysvinit-e-gerenciamento-de-inicializacao-do-linux-para-lpi-101/>
- <https://pt.wikipedia.org/wiki/Upstart>

