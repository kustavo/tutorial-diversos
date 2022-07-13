# Armazenamento

## Instance stores

- Fornece armazenamento temporário em nível de bloco para uma instância do *Amazon EC2*.
- É um armazenamento em disco fisicamente conectado ao computador host para uma instância do *EC2*.
- Tem a mesma vida útil da instância.
  - Quando a instância é encerrada, você perde todos os dados armazenados.

## Amazon Elastic Block Store (Amazon EBS)

- Fornece volumes de armazenamento em nível de bloco para uma instância do *Amazon EC2*.
- Vida útil não depende da instância.
  - Se você interromper ou encerrar uma instância do *Amazon EC2*, todos os dados no volume do *EBS* anexado **permanecerão** disponíveis.
- Para criar um volume do *EBS*, você define a configuração (como tamanho e tipo de volume) e a provisiona.
  - Depois de criar um volume do *EBS*, ele pode ser anexado a uma instância do *Amazon EC2*.

### Amazon EBS snapshots

- Como os volumes do *EBS* são para dados que precisam persistir, é importante fazer backup dos dados.
- Você pode fazer backups incrementais de volumes do *EBS* criando snapshots do *Amazon EBS*.
- Um *snapshots* do EBS é um backup incremental. Isso significa que o primeiro backup feito de um volume copia todos os dados. Para backups subsequentes, apenas os blocos de dados que foram alterados desde o *snapshots* mais recente são salvos.

## Amazon Simple Storage Service (Amazon S3)

TODO: aqui

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>