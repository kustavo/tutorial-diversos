# RabbitMQ

RabbitMQ, é um message _broker_ distribuído de código aberto que facilita a entrega de mensagens em cenários de roteamento complexos de maneira eficiente. Os recursos do RabbitMQ podem ser expandidos por meio do uso de plug-ins ativados no servidor. Eles também podem ser distribuídos e configurados para serem confiáveis ​​no caso de falha do servidor ou da rede.

O RabbitMQ é uma espécie de servidor de mensageria que utiliza o padrão AMQP (Advanced Message Queuing Protocol), que permite o envio e recebimento de mensagens de forma assíncrona, e utilizando o modelo de enfileiramento de baixa latência, consistindo em um conjunto de processos de _broker_ que publicam mensagens de comando em filas para consumi-las.

O principal diferencial do RabbitMQ é que ele é simplesmente escalável, é um sistema de enfileiramento de alto desempenho que possui regras de consistência muito bem definidas e capacidade de criar muitos tipos de modelos de troca de mensagens. Por exemplo, existem três tipos de troca que você pode criar no RabbitMQ:

1. __Tipo Direto__: Tem regras de roteamento muito simples, ela roteará a mensagem para aquelas filas cujo _BindingKey_ e _RoutingKey_ coincidam exatamente.

    Troca direta é o modo de troca padrão do RabbitMQ e também o modo mais simples. Ele encontra a fila com base na correspondência exata de _RoutingKey_.

1. __Tipo de Tópico__: É semelhante ao tipo de troca Direta. Mas pode rotear para várias filas que correspondem "parcialmente" ao BindingKey e _RoutingKey_:

    - _BindingKey_ e _RoutingKey_ são strings separadas por `.`. Cada string independente separada por `.` é chamada de palavra. Ex:com.rabbitmq.client, java.util.Concurrent.

    - Pode haver duas strings especiais `*` e `#` no _BindingKey_, que são usados ​​para correspondência difusa, onde `*` é usado para corresponder a uma palavra e `#` é usado para combinar várias palavras regulares (0 ou mais palavras);

1. __Tipo Fanout__: Transmite a mensagem para todas as filas vinculadas a ela, independentemente do valor de _RoutingKey_ (sem chave de pipeline ou modo de roteamento). Se _RoutingKey_ for definido, _RoutingKey_ ainda será ignorado.

Um bom __mensage broker__ é aquele que garante o trabalho que assume e é nisso que o RabbitMQ é bom. É inclinado para garantias de entrega entre produtores e consumidores, com mensagens transitórias preferidas às duráveis.
