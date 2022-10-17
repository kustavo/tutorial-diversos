# Banco de dados

## Amazon Relational Database Service (Amazon RDS)

- Serviço que permite executar bancos de dados relacionais na Nuvem AWS.
- Serviço gerenciado que automatiza tarefas como provisionamento de hardware, configuração de banco de dados, aplicação de patches e backups.
- Mecanismos de banco de dados do Amazon RDS oferecem criptografia em repouso (protegendo dados enquanto estão armazenados) e criptografia em trânsito (protegendo dados enquanto estão sendo enviados e recebidos).
- Está disponível em seis mecanismos de banco de dados, que otimizam a memória, o desempenho ou a entrada/saída (E/S). Os mecanismos de banco de dados suportados incluem:
    - Amazon Aurora
    - PostgreSQL
    - MySQL
    - MariaDB
    - Banco de Dados Oracle
    - Microsoft SQL Server

### Amazon Aurora

- Banco de **dados relacional** de classe empresarial.
- É compatível com bancos de dados relacionais MySQL e PostgreSQL.
- É até 5x mais rápido que o MySQL padrão e até 3x mais rápido que PostgreSQL padrão.
- Ajuda a reduzir os custos do banco de dados ao reduzir as operações de entrada/saída (E/S) desnecessárias
- Considere o *Amazon Aurora* se suas cargas de trabalho exigirem alta disponibilidade.
    - Ele replica seis cópias de seus dados em **três available zones** e faz **backup contínuo** de seus dados no **Amazon S3**.

### Multi-AZ do Amazon RDS com uma instância secundária

- Uma instância RDS secundária é criada em outra AZ e mantida atualizada para caso de interrupção da instância RDS principal.
- Não é possível usar a instância RDS secundária se a principal está ativa (fica em *standby*).

## Amazon DynamoDB

- Banco de dados não relacional (NoSQL)
- Serverless.
- Dimensionado automaticamente a medida que o tamanho do banco de dados diminui ou aumenta.
- É um serviço de banco de dados de chave-valor.
- Oferece desempenho de milissegundos de um dígito em qualquer escala.

## Amazon Redshift

- Serviço de armazenamento de dados que você pode usar para análises de big data.
- Ele oferece a capacidade de coletar dados de várias fontes e ajuda você a entender relacionamentos e tendências em seus dados.

## AWS Database Migration Service (AWS DMS)

- Permite migrar bancos de dados relacionais, bancos de dados não relacionais e outros tipos de armazenamentos de dados.
- Move dados entre um banco de dados de origem e um banco de dados de destino.
    - Os bancos de dados de origem e destino podem ser do mesmo tipo ou de tipos diferentes.
- Durante a migração, seu banco de dados de origem permanece operacional.
- Outras aplicações:
    - Permitir que os desenvolvedores testem aplicativos em relação aos dados de produção sem afetar os usuários de produção.
    - Enviar cópias contínuas de seus dados para outras fontes de destino em vez de fazer uma migração única

## Amazon DocumentDB

É um serviço de banco de dados de documentos que oferece suporte a cargas de trabalho do MongoDB.

## Amazon Neptune

- É um serviço de banco de dados gráfico.
- Você pode usar para criar e executar aplicativos que funcionam com conjuntos de dados altamente conectados, como mecanismos de recomendação, detecção de fraude e gráficos de conhecimento.

## Amazon Quantum Ledger Database (Amazon QLDB)

- Serviço de banco de dados de livro-razão (ledger).
- Você pode usar para revisar um histórico completo de todas as alterações feitas nos dados do seu aplicativo.

## Amazon Managed Blockchain

É um serviço que você pode usar para criar e gerenciar redes blockchain com estruturas de código aberto.

## Amazon ElastiCache

- É um serviço que adiciona camadas de cache sobre seus bancos de dados para ajudar a melhorar os tempos de leitura de solicitações comuns.
- Oferece suporte a dois tipos de armazenamentos de dados: **Redis** e **Memcached**.

## O Amazon DynamoDB Accelerator (DAX)

- É um cache na memória para o *DynamoDB*.
- Ajuda a melhorar os tempos de resposta de milissegundos de um dígito para **microssegundos**.

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>
