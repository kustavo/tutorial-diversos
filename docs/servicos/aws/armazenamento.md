# Armazenamento

## Instance stores

- Fornece armazenamento temporário em nível de bloco para uma instância do *Amazon EC2*.
- É um armazenamento em disco fisicamente conectado ao computador host para uma instância do *EC2*.
- Tem a mesma vida útil da instância.
    - Quando a instância é encerrada, você perde todos os dados armazenados.

## Amazon Elastic Block Store (Amazon EBS)

- Fornece volumes de armazenamento em nível de bloco para uma instância do *Amazon EC2*.
- HDD ou SSD
- Vida útil não depende da instância.
    - Se você interromper ou encerrar uma instância do *Amazon EC2*, todos os dados no volume do *EBS* anexado **permanecerão** disponíveis.
- Para criar um volume do *EBS*, você define a configuração (como tamanho e tipo de volume) e a provisiona.
    - Depois de criar um volume do *EBS*, ele pode ser anexado a uma instância do *Amazon EC2*.
- Criado em uma **única available zone**.
    - A instância do *Amazon EC2* deve estar na mesma **available zone**.

### Amazon EBS volumes

- Fornece os tipos de volume:
    - **Unidades de estado sólido (SSD)**: otimizadas para workloads de transação envolvendo **operações de leitura/gravação frequentes** com o tamanho pequeno de E/S, onde o atributo dominante de performance é IOPS. Os tipos de volume com suporte de SSD incluem:
        - Volumes SSD de uso geral.
            - Equilibram preço e performance para proporcionar uma ampla variedade de workloads transacionais.
        - Volumes de Provisioned IOPS SSD.
            - Volumes de armazenamento da mais alta performance, criados para workloads essenciais, com uso intenso de IOPS e de throughput que exigem baixa latência.
    - **Unidades de disco rígido (HDD)**: otimizadas para grandes workloads de transmissão em que o atributo de performance dominante é a **throughput**. Os tipos de volume com suporte de HDD incluem:
        - Volumes de HDD otimizado para throughput.
            - Um HDD de baixo custo criado para workloads acessadas com frequência e com alta throughput.
        - HDD frio com throughput.
            - O design de HDD de menor custo para workloads acessadas com menos frequência.
    - **Geração anterior**: unidades de disco rígido que você pode usar para workloads com pequenos conjuntos de dados em que os dados são **acessados raramente** e a performance não é de primordial importância.

- [Veja mais sobre os tipos de volumes](https://aws.amazon.com/pt/ebs/volume-types/)

### Amazon EBS snapshots

- Como os volumes do *EBS* são para dados que precisam persistir, é importante fazer backup dos dados.
- Você pode fazer backups incrementais de volumes do *EBS* criando snapshots do *Amazon EBS*.
- Um *snapshots* do EBS é um backup incremental. Isso significa que o primeiro backup feito de um volume copia todos os dados. Para backups subsequentes, apenas os blocos de dados que foram alterados desde o *snapshots* mais recente são salvos.
- Durante o processo de *snapshot* o volume EBS pode ser usando normalmente.

## Amazon Simple Storage Service (Amazon S3)

- Armazena dados como **objetos** em *buckets*.
- Aceita upload de qualquer tipo de arquivo.
- Oferece espaço de armazenamento ilimitado.
- O tamanho máximo de arquivo para um objeto no Amazon S3 é **5 TB**.
- Para cada arquivo é possível definir permissões para controlar a visibilidade e o acesso a ele.
- Possui recurso de versão para rastrear alterações em seus objetos ao longo do tempo.
- Você paga apenas pelo que usa.

### Amazon S3 storage classes

- Ao selecionar uma classe de armazenamento do Amazon S3, considere estes dois fatores:
    - Com que frequência você planeja recuperar seus dados
    - Quão disponíveis você precisa que seus dados estejam

#### S3 Standard

- Para dados acessados ​​com frequência.
- Armazena dados em no mínimo **três available zones** de disponibilidade.
- Oferece alta disponibilidade para objetos.
    - Usado para sites, distribuição de conteúdo e análise de dados.
- Custo mais alto.

#### S3 Standard-Infrequent Access (S3 Standard-IA)

- Para dados acessados ​​com pouca frequência.
- Semelhante ao S3 Standard, mas tem um preço de armazenamento mais baixo e um preço de recuperação mais alto.
- Ideal para dados acessados ​​com pouca frequência, mas requer alta disponibilidade quando necessário.
- Armazena dados em no mínimo **três available zones** de disponibilidade.

#### S3 One Zone-Infrequent Access (S3 One Zone-IA)

- Armazena dados em uma **única available zone** de disponibilidade.
- Tem um preço de armazenamento menor que o *S3 Standard-IA*.
- Ideal para economizar custos de armazenamento, mas os dados podem ser recuperados em caso de falha.

#### S3 Intelligent-Tiering

- Ideal para dados com padrões de acesso desconhecidos ou em mudança.
- Requer uma pequena taxa mensal de monitoramento e automação por objeto.
- Os padrões de acesso dos objetos são monitorados.
    - Se o objeto não tiver acesso por **30 dias consecutivos**, será movido para *S3 Standard-IA*.
    - Se o objeto que estava em *S3 Standard-IA* for acessado, será movido para *S3 Standard*.
- A movimentação de objetos entre as classes entra na cobrança de transferências de objetos.

#### S3 Glacier

- Armazenamento de baixo custo projetado para arquivamento de dados.
- Recupera objetos dentro de alguns **minutos a horas**.

#### S3 Glacier Deep Archive

- Armazenamento com menor custo, projetado para arquivamento de dados.
- Recupera objetos dentro de **12 horas**.

## Amazon Elastic File System (Amazon EFS)

- Armazenas dados como **arquivos**.
- É um sistema de arquivos escalável, serverless, usado com serviços da Nuvem AWS e recursos locais.
- Ideal para casos de uso em que um grande número de serviços e recursos precisam acessar os mesmos dados ao mesmo tempo.
- À medida que você adiciona e remove arquivos, o *Amazon EFS* cresce e diminui automaticamente.
- Pode ser dimensionado sob demanda para **petabytes** sem interromper os aplicativos.
- Armazenado em várias **available zones** da região.
- Servidores locais podem acessar o *Amazon EFS* usando o AWS Direct Connect.

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>