# Apache Kafka

## Introdução

O mundo tem gerado um volume cada vez maior de informações. Um cenário comum é que estes dados, uma vez gerados, precisam ser transportados para diversas aplicações. Por exemplo, quando fazemos uma compra com cartão de crédito a operadora precisa avisar várias aplicações diferentes para contabilizar a compra, os benefícios e até a análise contra fraudes. Neste caso, é desejável que cada aplicação seja responsável pela implementação das regras do negócio. E apenas isso. Uma aplicação não deveria se preocupar com a entrega ou captura dos dados. Este é um problema complexo envolvendo _data streaming_.

`Data streaming`

:   Um fluxo de dados constante e sem controle é chamada de data _streaming_. Sua principal característica é que os dados não tem limites definidos, assim, não é possível dizer quando começa ou acaba o fluxo (_stream_). Os dados são processados à medida em que chegam no destino. Alguns autores chamam de processamento em tempo real, ou _on-line_.

    Uma abordagem diferente é o processamento em bloco, _batch_, ou _off-line_, na qual blocos de dados são processados em janelas de tempo.

`Sistema de Mensagem`

:   O sistema de mensagens permite desacoplar a geração dos dados do seu processamento de forma assíncrona, assim, uma aplicação pode continuar funcionando sem esperar a outra terminar seu processamento.

## O que é Apache Kafka

O Apache Kafka é uma plataforma open-source de mensageria usado para criar aplicações de _streaming_, ou seja, aquelas com fluxo de dados contínuo.

Kafka é baseado em logs, algumas vez chamado de _write-ahead logs_, _commit logs_ ou até mesmo _transaction logs_. Um log é uma forma básica de armazenamento, porque cada nova informação é adicionada no final do arquivo. Este é o princípio de funcionamento do Kafka.

O Kafka é adequado para soluções com grande volume de dados (big data) porque uma das suas características é a alta taxa de transferência.

O Apache Kafka tem 3 funcionalidades principais:

- __Sistema de mensagens__: Usado para desacoplar sistemas com escopos diferentes. Sistema de mensagem do tipo _publish/subscribe_;
- __Sistema de armazenamento__: Usado para guardar os eventos (logs) de forma consistente, permitindo reconstruir o estado do sistema a qualquer momento a partir destes logs. As mensagens ficam armazenadas por um período de tempo pré-definido.
- __Processamento de _stream__: Usado para transformar dados em tempo real, como mapeamentos, agregações, junções, etc. É possível transformar a mensagem imediatamente após o seu recebimento.

Basicamente, o Kafka é um intermediário que coleta os dados da fonte e entrega para uma aplicação que consumirá esses dados, como visto na imagem:

<figure>
    <img src="../_kafka/kafka-visao-geral.png" title="Fonte: https://atitudereflexiva.wordpress.com/2020/03/05/apache-kafka-introducao"/>
    <figcaption>Arquitetura Macro</figcaption>
</figure>

!!! note "Natas"

    Um uso mais recente do Kafka é o processo de ETL (Extract, Transform and Load), que copia os registros de um banco de dados para outro, geralmente de uma base transacional (OLTP) para uma base analítica (OLAP).

    Alguns autores acreditam que o futuro do ETL passa pelo Kafka, porque as ferramentas de ETL tendem a ser complexas, e o Kafka pode ajudar a diminuir a dificuldade.

O ponto central do sistema de mensagem é, naturalmente, a mensagem, que pode ser chamada também de registro ou evento, e é composta por:

- __Tópico__: Fila na qual a mensagem será gravada. Pode ser comparado a uma tabela do banco de dados;
- __Partição__: Subdivisão do tópico, a partição é um recurso para ajudar a balancear a carga;
- __Timestamp__: Os registros são ordenados pela hora de gravação;
- __Chave__: Opcional, pode ser usada em cenários avançados;
- __Valor__: A informação que se pretende transferir. O ideal é que os dados usem um formato conhecido, como JSON ou XML.

Os sistemas de mensagem podem ser de dois tipos: _point-to-point_ e _publish-subscribe_.

O Apache Kafka é do segundo tipo, no qual uma aplicação que publica (_publish_) e uma aplicação que se inscreve (_subscribe_) para receber as mensagens.

O tempo entre publicar e receber a mensagem pode ser de milissegundos, assim, uma solução Kafka tem baixa latência.

## Arquitetura do Apache Kafka

Externamente Kafka se comporta como um sistema de mensageiria onde mensagens são publicadas e consumidas, similar a soluções como ActiveMQ, RabbitMQ, IBM’s MQSeries e outros, mas internamente foi desenhado para ser um sistema distribuível, escalável e confiável.

A arquitetura do Apache Kafka, combina diversas outras características, como:

- __Escalabilidade__: O cluster pode ser facilmente redimensionado para atender ao aumento ou diminuição das cargas de trabalho;
- __Distribuído__: O cluster Kafka opera com vários nós (_brokers_), dividindo o processamento;
- __Replicado, particionado e ordenado__: As mensagens são replicadas em partições nos nós do cluster na ordem em que chegam para garantir segurança e velocidade de entrega;
- __Alta disponibilidade__: o cluster tem diversos nós (_brokers_) e várias cópias dos dados.

A arquitetura do Apache Kafka é composta por __producers__, __consumers__ e o próprio __cluster__.

O _producer_ é qualquer aplicação que publica mensagens no _cluster_. O _consumer_ é qualquer aplicação que recebe as mensagens do Kafka. O cluster Kafka é um conjunto de nós (_brokers_) que funcionam como uma instância única do serviço de mensagem.

O Kafka funciona como um _cluster_ de _brokers_ e isso permite configurações interessantes de disponibilidade.

<figure>
    <img src="../_kafka/kafka-cluster.png" title="Fonte: https://medium.com/@jhansireddy007/basic-concepts-of-kafka-e49e7674585e"/>
    <figcaption>Visão geral de um cluster</figcaption>
</figure>

Um _cluster_ Kafka é composto por vários _brokers_. Um _broker_ é um servidor Kafka que recebe mensagens dos producers e as grava no disco. Cada _broker_ gerencia uma lista de tópicos e cada tópico é dividido em diversas partições.

Depois de receber as mensagens, o _broker_ as envia para os consumidores que estão registrados para cada tópico. Veja a imagem:

<figure>
    <img src="../_kafka/kafka-broker.png" title="Fonte: https://blog.geekhunter.com.br/apache-kafka/"/>
    <figcaption>Visão geral dos produtores, broker e consumidores.</figcaption>
</figure>

As configurações do Apache Kafka são gerenciadas pelo __Apache Zookeeper__, que armazena os metadados do _cluster_, como localização das partições, lista de nomes, lista de tópicos e nós disponíveis. Assim, o Zookeeper mantém a sincronização entre os diversos elementos do _cluster_.

Isso é importante porque o Kafka é um sistema distribuído, ou seja, as gravações e leituras são feitas por diversos clientes simultaneamente. Quando há uma falha, o Zookeeper elege um substituto e recupera a operação.

### Mensagens

O Kafka funciona como uma fila de mensagens, possuindo produtores e consumidores, mas a implementação tem particularidades, a começar pela própria mensagem em si, no Kafka ela é simplesmente um array de bytes, sem um formato específico, que pode ou não possuir uma chave. Quando a chave é fornecida, é gerado um cálculo de hash que garante que mensagens com a mesma chave sejam escritas sempre na mesma partição, essa escrita é realizada em lotes compactados de mensagens, diminuindo a carga de rede nos casos em que o volume de mensagens seja grande. O tamanho do lote é configurável e deve ser pensado com cautela para no caso não ser grande demais e aumentar o tempo de registro da mensagem.

!!! note "Notas"

    Para determinar em que partição uma mensagem será armazenada através do modelo __round-robin__, o Kafka calcula uma chave exclusiva e entrega a mensagem para o líder daquela partição. Ou seja, as mensagens são balanceadas entre as partições de um tópico. Os dados não são replicados entre as partições.

    O Kafka garante a ordem de mensagens em uma partição, mas não garante a ordem em que as mensagens foram recebidas considerando todas partições.

As mensagens são armazenadas pelo tempo que for configurado, e e as mesmas se tornam imutáveis e possuem garantias de entrega através de implementações de replicação e persistência, isso me fez entender que o Kafka também é um storage de dados.

#### Acesso sequencial no disco

O Apache Kafka __persiste a mensagem no disco__ e não na memória, como se imagina ser mais rápido. De fato, o acesso à memória é mais rápido na maioria das situações, principalmente quando consideramos acesso a dados que estão em posições aleatórias na memória.

Contudo, o Kafka faz acesso sequencial e, neste caso, o disco é mais eficiente. Há outras ferramenta de big data que trabalham da mesma forma, como por exemplo o HDFS.

No acesso sequencial o disco sabe onde começa e termina o bloco de dados, enquanto que no acesso aleatório o disco precisa procurar e se mover para a posição dos blocos de dados. É como se os dados estivessem ordenados. Pensando assim, o Kafka lê os dados como se fosse um livro, da primeira para a última página.

Quando temos leitura/gravação aleatória seria como ler o mesmo livro, mas com as páginas misturadas. Antes de ler, você teria de procurar a próxima página.

### Tópicos

No Kafka as mensagens são categorizadas em tópicos, autores fazem a analogia de que são similares a tabelas de um banco ou a pastas de um sistema de arquivos, os mesmos são nomeados e divididos em partições. Cada partição é ordenada e as mensagens recebem um id incremental independente chamado de __offset__ e ao criar um tópico é necessário informar a quantidade de partições.

O "endereço" da mensagem se torna único ao combinar o nome do tópico, partição e _offset_. A estrutura de partições é pensada para uma melhor escalabilidade do Kafka.

Cada tópico é dividido em partições, que determinam o nível de paralelismo do lado do consumidor. Cada partição pode ser lida por no máximo um consumidor por grupo, ou seja, se um tópico possui 8 partições, pode haver no máximo 8 consumidores concorrendo pelo mesmo tópico (Caso pertençam ao mesmo grupo).

Cada partição é servida por apenas um _broker_, o líder, mas as mensagens são replicadas entre os outros _brokers_ para tolerância a falhas. Caso o líder se torne inacessível, um de seus seguidores irá assumir a função de líder e passará a servir os clientes.

!!! note "Notas"

    As mensagens são armazenadas sequencialmente em um partição começando em 0. Se um consumidor cair e subir depois de algum tempo, ele pode continuar a leitura das mensagens de onde parou, pois o último _offset_ lido por aquele cliente é armazenado em um tópico escondido chamado __consumer offsets__.

    Os _offsets_ de partições diferentes são independentes entre si. Por exemplo, o _offset_ 0 da partição 0 não contém o mesmo dado do _offset_ 0 da partição 1.

<figure>
    <img src="../_kafka/kafka-topico.png" title="Fonte: https://ivanqueiroz.dev/2020/06/2020-06-14-conceitos-kafka.html"/>
    <figcaption>Tópico com múltiplas partições.</figcaption>
</figure>

### _Brokers_ e _Clusters_

As partições estão contidas dentro do _broker_. Um _broker_ simplesmente é um servidor Kafka, ele é identificado por um número arbitrário sendo responsável por receber e distribuir as mensagens. Ao receber uma mensagem o _broker_ associa um _offset_ para a mesma e realiza um _commit_ da mensagem no disco, quando recebe uma requisição em uma partição o mesmo responde com a mensagem salva.

O _broker_ Kafka foi construído para operar como parte de um _cluster_ e se tornar um _controller_ automaticamente caso seja eleito (eleição realizada aleatoriamente no _cluster_). Ao tornar-se um controlador, o _broker_ é responsável por administrar o _cluster_, vinculando partições aos outros e monitorando falhas.

A estrutura do Kafka permite que uma partição seja associada múltiplos _brokers_, isso resulta em uma replicação da partição que provê uma redundância das mensagens armazenadas na mesma, caso um servidor caia outro pode assumir no lugar. Na replicação apenas um _broker_ pode receber e veicular dados o chamado _leader_, os outros serão utilizados para sincronia dentro da replicação. A replicação de partições em _brokers_ diferentes é realizada por um recurso chamado __Replication Factor__.

<figure>
    <img src="../_kafka/kafka-cluster-fluxo.png" title="Fonte: https://ivanqueiroz.dev/2020/06/2020-06-14-conceitos-kafka.html"/>
    <figcaption>Cluster Kafka.</figcaption>
</figure>

<figure>
    <img src="../_kafka/kafka-cluster-topicos.png" title="Fonte: https://medium.com/@jhansireddy007/basic-concepts-of-kafka-e49e7674585e"/>
    <figcaption>Cluster Kafka com vários tópicos.</figcaption>
</figure>

!!! note "Notas"

    A duração de uma mensagem pode ser configurado pelo tempo (1 semana por exemplo) ou pelo tamanho limite (ex.: 2 gb). Quando o limite é atingido, as mensagens são marcadas como expiradas e excluídas.

Os tópicos podem ter configurações de armazenamento das mensagens individualmente, isso permite escolher mais tempo para tópicos julgados mais importantes para a solução.

### Producer

O Kafka utiliza o conceito de produtores (_producers_ ) e consumidores (_consumers_) para definir os clientes que se conectam ao broker. Produtores criam as mensagens para um tópico específico, isso não é regra e sim o comportamento mais comum. O produtor no geral não se preocupa em qual partição a mensagem será salva e faz o balanceamento entre todas as disponíveis. Para enviar a um determinada partição é preciso utilizar um chave gerada internamente no formato de hash que é mapeada a uma partição, sempre que utilizar essa chave na mensagem ela será enviada para a mesma partição. Se ele não escolher uma partição, o Kafka escolherá.

Um produtor pode enviar mensagens simultaneamente para vários tópicos. Da mesma forma, um consumidor pode ler mensagens de vários tópicos simultaneamente. Isso garante a baixa latência citada anteriormente, porque tudo acontece ao mesmo tempo. Aqui, o __Zookeeper é o responsável por indicar os brokers para cada producer/consumer__.

### Consumer

Consumidores leem as mensagens produzidas e controlam o consumo através dos _offsets_ das mensagens. O _offset_ da última mensagem é armazenado e com isso o consumidor pode parar e iniciar sem perder o histórico.

No Kafka os consumidores trabalham como parte de um __grupo de consumidores__, o qual pode ter um ou mais participantes que trabalhem juntos no consumo de um tópico. O grupo garante que cada partição seja consumida por apenas um membro, isso permite escalar horizontalmente os consumidores no caso de uma grande quantidade de mensagens. Se um consumidor tiver algum problema, as partições são redistribuídas entre os outros membros ativos.

!!! note "Notas"

    Consumidores dentro de um grupo de consumidores dividem o acesso às partições do tópico. Uma mensagem será replicada em cada partição do tópico e cada partição entregará a mensagem para um consumidor por vez. Uma mesma partição pode ser atribuída a um consumidor diferente em um consumer group diferente, mas desde que os _offsets_ lidos sejam diferentes. O Coordenador do grupo (um dos _brokers_) é responsável por conectar uma partição a um consumidor específico. A quantidade de consumidores em um grupo deve ser menor ou igual a quantidade de partições em um tópico, caso contrário, alguns consumidores ficarão ociosos.

<figure>
    <img src="../_kafka/kafka-consumer-group.png" title="Fonte: https://atitudereflexiva.wordpress.com/2020/03/05/apache-kafka-introducao"/>
    <figcaption>Grupo de consumidores.</figcaption>
</figure>

<figure>
    <img src="../_kafka/kafka-consumer-groups.webp" title="Fonte: https://www.infoq.com/br/articles/apache-kafka-licoes"/>
    <figcaption>Múltiplos Grupos de consumidores.</figcaption>
</figure>

A configuração padrão do Apache Kafka tem ótima performance, mesmo com hardware limitado. Ainda assim é necessário otimizar o _cluster_ quando temos grandes cargas de dados. Para escalar usamos várias estratégias, geralmente testando as combinações de configuração, por exemplo, alterando o número de produtores, consumidores e tópicos.

### Apache Zookeeper

Outro componente importante do ecossistema Kafka é o Apache Zookeeper, que gerencia e coordena o _cluster_ de Kafka _brokers_ e intermedeia a coordenação entre esses _brokers_. Ao criar um novo tópico com várias réplicas e partições, o controller (_broker_ principal do _cluster_), elegerá um novo broker líder por partição de forma equitativa. O Zookeeper sempre contém o estado atual do _cluster_. Se um _broker_ líder falhar, o Zookeeper entrará em contato com o controller e intermediará a transição do novo líder. O Zookeeper também monitora os tópicos, quotas de disco para leitura e escrita, quais são os grupos de consumidores existentes, quais são seus membros e o último _offset_ lido em cada partição por um consumidor específico.

Quando um broker líder ficar indisponível, o Zookeeper enviará uma notificação para o controller, e o controller elegerá uma nova líder. Após a eleição, o controller enviará o comando LeaderAndISRCommand para cada broker que é uma réplica da partição, informando aos brokers a nova líder e os novos ISR. Sempre que a ISR muda, o líder atualizar o Zookeeper da mudança.

O Zookeeper sempre está atualizado com o estado do cluster Kafka, se um líder falhar, o Zookeeper coordenará as notificações para o controller, para a transição do novo líder.

<figure>
    <img src="../_kafka/zookeeper.webp" title="Fonte: https://www.infoq.com/br/articles/apache-kafka-licoes"/>
    <figcaption>Zookeeper.</figcaption>
</figure>

## Ferramentas complementares

O Apache Kafka é responsável pela transferência dos dados entre aplicações, mas não é suficiente para construir uma solução completa. Por isso, são necessárias outras ferramentas para as tarefas que o Kafka não faz, como o CDC.

O CDC (Change Data Capture), como o nome sugere, é uma categoria de software que captura as mudanças em um banco de dados e pode ser usado em conjunto com o Kafka para entrega de dados em tempo real, como uma alternativa ao batch.

O CDC monitora as alterações no banco, que são entregues ao Kafka continuamente, o que minimiza o impacto na rede e na performance do banco. Outras ferramentas que valem menção:

- __Kafka Streams__: biblioteca do Kafka que pode ser usada no lugar da API de producer/consumer para processamento das mensagens em tempo real;
- __KSQL__: mecanismo de SQL para o Kafka. Permite fazer consultas no stream de dados;
- __Kafka Connect__: componente do Kafka para conexão com sistemas externos como bancos de dados (SQL ou NoSQL) ou sistema de arquivo;
- __Spark Streaming__: extensão do Apache Spark para processamento de streaming;
- __Apache Flume__: serviço de coleta e agregação de dados;
- __Spring for Apache Kafka__: permite o desenvolvimento de aplicações Kafka com as facilidades do Spring;
- __Debezium__: plataforma de CDC escalável, moderna e distribuída com suporte a diversos bancos de dados.

## Apache Kafka e microservices

Os microservices tem usado cada vez mais o Apache Kafka. Tradicionalmente, a comunicação entre microservices é feita pela própria API REST.

Entretanto, à medida em que a quantidade de microservices aumenta, aumenta a complexidade para integração. Novamente, um bom cenário para o Kafka pelo motivos já vistos acima:

- __Desacoplamento entre o produtor e o consumidor da mensagem__: isso aumenta até a velocidade de desenvolvimento;
- __Alta disponibilidade__: mesmo quando um microservice fica indisponível, o sistema continua funcionando;
- __Baixa latência__: a transferência é imediata.

O projeto de novas arquiteturas costuma levar em consideração o uso de sistemas de mensagens, especialmente com o envio de mensagens assíncronas, centralizando a integração de dados no Apache Kafka.

Um sistema de mensagem é recomendado inclusive quando temos a migração de uma aplicação legada (monólito) para microservices.

## Conclusão sobre o Apache Kafka

O Kafka é um sistema de mensageria que traz a proposta de unir o melhor dos modelos tradicionais de __fila__ e __publish-subscribe__, permitindo a escalabilidade do processamento de mensagens do primeiro e a distribuição em massa das mensagens do segundo. Para entregar a proposta o Kafka utiliza o conceito de grupo de consumidores, distribuindo as mensagens para uma "fila" dos mesmos, permitindo a divisão do processamento por uma coleção de processadores.

Esse modelo proposto traz a vantagem de permitir que cada tópico criado possuam as propriedades de escalabilidade e de múltiplos canais de _subscribers_, sem necessidade de escolha entre uma abordagem ou outra. Além dessa propriedades a implementação do Kafka foi pensada para permitir o armazenamento das mensagens bem como alta velocidade de processamento das mesmas como um fluxo contínuo de dados (streaming) em baixa latência (real-time).

Com todas estas vantagens, o Apache Kafka desponta como uma ferramenta importante nesta nova era da análise de dados para ajudar a resolver o problema da complexidade da transferência de dados entre aplicações.

## Apache Kafka vs RabbitMQ

O principal diferencial de Apache Kafka em relação ao RabbitMQ é que ele pode ser usado como um sistema de filas, mas não é isso que está limitado. Kafka é algo mais parecido com um buffer circular que pode ser dimensionado tanto quanto um disco na máquina do cluster e, portanto, nos __permite reler as mensagens__. Isso pode ser feito pelo cliente sem ter que depender do cluster Kafka, pois é totalmente responsabilidade do cliente observar os metadados da mensagem que está lendo no momento e pode revisitar o Kafka mais tarde em um intervalo especificado para ler a mesma mensagem novamente.

Kafka não presuma que algum de seus consumidores está principalmente online e nem se importa.

Se um consumidor não estiver conectado a um _fanout exchange_ no RabbitMQ quando uma mensagem foi publicada, ela será perdida porque outros consumidores consumiram a mensagem, mas isso não acontece no Apache Kafka, pois qualquer consumidor pode ler qualquer mensagem Como eles mantêm seu próprio cursor.

Kafka é ideal para casos de uso de big data que exigem o melhor rendimento, enquanto RabbitMQ é ideal para entrega de mensagens de baixa latência, garantias por mensagem e roteamento complexo.

### Tabela comparativa

| Kafka | RabbitMQ |
| --- | --- |
| Somente padrões primitivos. | Padrões de protocolo HTTP, AMQ, etc. |
| Mensagens por partições. | Consumidores competitivos. |
| Armazenamento temporizado. | Mensagem é removida após leitura. |
| Padrão FIFO/lista sem priorização. | Padrão fila com priorização. |
| Sem interface de gerenciamento. | Possui interface de gerenciamento. |
| Roteamento fixo. | Roteamento flexível. |
| Pode ser usado como _data store_. | Não pode ser usado como _data store_. |
| Garante a ordem para uma partição em um tópico. | Ordenação não é garantida, uma vez que temos vários consumidores. |
| Armazena os dados na ordem em que são recebidos e oferece suporte à reprodução de mensagens com a ajuda de _offsets_. | Mensagens não podem ser reprocessadas, elas precisam ser reenviadas. |
| Oferece suporte a transações. | Não oferece suporte a transações nativamente, ele usa acknowledgments. |
| Requer a instância em execução do Zookeeper para o gerenciamento. | Não precisa de um processo externo em execução. |

## Referências

- <https://blog.geekhunter.com.br/apache-kafka/>
- <https://ivanqueiroz.dev/2020/06/2020-06-14-conceitos-kafka.html>
- <https://medium.com/engenharia-arquivei/subindo-um-cluster-de-kafka-ffec258b1175>
- <https://www.linkedin.com/pulse/como-e-quando-usar-rabbitmq-ou-kafka-vanessa-calandriello>
- <https://www.infoq.com/br/articles/apache-kafka-licoes>
- <https://www.youtube.com/watch?v=LX19wk2B5Ak>
