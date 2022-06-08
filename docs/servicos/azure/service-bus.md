# Service bus

O Azure Service Bus oferece suporte a um conjunto de tecnologias de _middleware_ orientadas a mensagens baseadas em nuvem, incluindo enfileiramento de mensagens confiáveis e de publicação/assinatura duráveis. Esses recursos de mensagens intermediárias podem ser considerados como recursos de mensagens desacoplados que oferecem suporte a cenários de publicação-assinatura, desacoplamento temporal e balanceamento de carga usando a carga de trabalho de mensagens do _service bus_. A comunicação dissociada tem muitas vantagens. Por exemplo, clientes e servidores podem se conectar conforme necessário e fazer suas operações de maneira assíncrona.

As entidades de mensagens que formam o _core_ dos recursos de mensagens no _service bus_ são **filas**, **tópicos**, **assinaturas** e **regras/ações**.

## Filas

As filas oferecem entrega de mensagens no modo primeiro a entrar, primeiro a sair (_First In, First Out - FIFO_) para um ou mais consumidores concorrentes. Ou seja, os receptores geralmente recebem e processam mensagens na ordem em que foram adicionadas à fila. E, apenas um consumidor de mensagem recebe e processa cada mensagem. Um dos principais benefícios do uso de filas é obter o desacoplamento temporal dos componentes do aplicativo. Em outras palavras, os produtores (remetentes) e consumidores (destinatários) não precisam enviar e receber mensagens ao mesmo tempo. Isso ocorre porque as mensagens são armazenadas de forma duradoura na fila. Além disso, o produtor não precisa esperar por uma resposta do consumidor para continuar a processar e enviar mensagens.

Um benefício relacionado é o nivelamento de carga, que permite aos produtores e consumidores enviar e receber mensagens em taxas diferentes. Em muitos aplicativos, a carga do sistema varia com o tempo. No entanto, o tempo de processamento necessário para cada unidade de trabalho é normalmente constante. Intermediar produtores e consumidores de mensagens com uma fila significa que o aplicativo de consumo só precisa ser capaz de lidar com a carga média em vez da carga de pico. A profundidade da fila aumenta e se contrai conforme a carga de entrada varia. Esse recurso economiza dinheiro diretamente em relação à quantidade de infraestrutura necessária para atender à carga do aplicativo. Conforme a carga aumenta, mais processos de trabalho podem ser adicionados para ler da fila. Cada mensagem é processada por apenas um dos processos de trabalho. Além disso, esse balanceamento de carga baseado em _puxar_ mensagens permite o melhor uso dos computadores de trabalho, mesmo se os computadores de trabalho com poder de processamento _puxar_ mensagens em sua própria taxa máxima. Esse padrão é frequentemente denominado padrão de **consumidor competitivo**.

O uso de filas para intermediar produtores e consumidores de mensagens fornece um acoplamento inerente flexível entre os componentes. Como produtores e consumidores não estão cientes uns dos outros, um consumidor pode ser atualizado sem ter nenhum efeito sobre o produtor.

### Modos de recepção

Você pode especificar dois modos diferentes nos quais o _Service Bus_ recebe mensagens.

`Receba e apague`
: Nesse modo, quando o _Service Bus_ recebe a solicitação do consumidor, ele marca a mensagem como consumida e a retorna ao aplicativo do consumidor. Este modo é o modelo mais simples. Funciona melhor em cenários nos quais o aplicativo pode tolerar o não processamento de uma mensagem se ocorrer uma falha. Para entender esse cenário, considere um cenário em que o consumidor emite a solicitação de recebimento e, em seguida, trava antes de processá-la. Ele perderá a mensagem que consumia antes do acidente.

`Peek lock`
: Neste modo, a operação de recebimento passa a ser de dois estágios, o que permite dar suporte a aplicativos que não toleram mensagens perdidas.

    1. Localiza a próxima mensagem a ser consumida, a bloqueia para evitar que outros consumidores a recebam e, em seguida, retorna a mensagem ao aplicativo.
    1. Depois que o aplicativo conclui o processamento da mensagem, ele solicita que o serviço do _Service Bus_ conclua o segundo estágio do processo de recebimento. Em seguida, o serviço marca a mensagem como sendo consumida.

    Se o aplicativo não puder processar a mensagem por algum motivo, ele poderá solicitar que o serviço do _Service Bus_ abandone a mensagem. O _Service Bus_ desbloqueia a mensagem e a torna disponível para ser recebida novamente, seja pelo mesmo consumidor ou por outro consumidor concorrente. Em segundo lugar, há um tempo limite associado ao bloqueio. Se o aplicativo não conseguir processar a mensagem antes que o tempo limite de bloqueio expire, o _Service Bus_ desbloqueia a mensagem e a torna disponível para ser recebida novamente.

    Se o aplicativo travar depois de processar a mensagem, mas antes de solicitar que o serviço do _Service Bus_ conclua a mensagem, o _Service Bus_ reenvia a mensagem ao aplicativo quando ele é reiniciado. Esse processo costuma ser chamado de processamento _uma vez pelo menos_ (_at-least once_). Ou seja, cada mensagem é processada pelo menos uma vez. No entanto, em certas situações, a mesma mensagem pode ser reenviada. Se o seu cenário não pode tolerar o processamento duplicado, adicione lógica adicional em seu aplicativo para detectar duplicatas. Esse recurso é conhecido como processamento _exatamente único_ (_exactly once_).

### Criando filas

Podemos criar filas usando:

- [Portal Azure](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-quickstart-portal)
- [PowerShell](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-quickstart-powershell)
- [CLI](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-quickstart-cli)
- [Resource Manager templates](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-resource-manager-namespace-queue)

??? note "Exemplo de uso de filas em .NET 5.0"

    === "Produtor"

        ```c#
        // Producer.cs
        using System;
        using System.Globalization;
        using System.Threading.Tasks;
        using Azure.Messaging.ServiceBus;

        namespace QueueSender
        {
            class Producer
            {
                // Connection string para o namespace do Service Bus
                static string connectionString = "<connection-string>";

                // Nome da fila do Service Bus
                static string queueName = "<nome-fila>";

                // Cliente da conexão usado como emissor (produtor) ou receptor (consumidor)
                static ServiceBusClient client;

                // O emissor usado para publicar mensagens para a fila
                static ServiceBusSender sender;

                // Número de mensagens a serem enviadas para a fila
                private const int numOfMessages = 5;

                static async Task Main()
                {
                    // Os tipos de cliente do Service Bus são seguros para armazenar em cache e usar como
                    // um singleton durante a vida útil do aplicativo, o que é uma prática recomendada
                    // quando as mensagens estão sendo publicadas ou lidas regularmente.

                    // Criando os clientes que usaremos para enviar e receber mensagens.
                    client = new ServiceBusClient(connectionString);
                    sender = client.CreateSender(queueName);

                    // Criando um lote (batch)
                    using ServiceBusMessageBatch messageBatch = await sender.CreateMessageBatchAsync();

                    for (int i = 1; i <= numOfMessages; i++)
                    {
                        string timestamp = DateTime.UtcNow.ToString("yyyy-MM-dd HH:mm:ss.ffffff",
                                              CultureInfo.InvariantCulture);
                        // Tentando adicionar a mensagem ao lote
                        if (!messageBatch.TryAddMessage(new ServiceBusMessage($"Mensagem {i} | Hora {timestamp}")))
                        {
                            // Se a mensagem for muito grande para o lote
                            throw new Exception($"A mensagem {i} é muito grande para caber no lote.");
                        }
                    }

                    try
                    {
                        // Usando o cliente produtor para enviar o lote de mensagens para a fila do Service Bus
                        await sender.SendMessagesAsync(messageBatch);
                        Console.WriteLine($"Um lote de {numOfMessages} mensagens foi publicada na fila.");
                    }
                    finally
                    {
                        // Chamar DisposeAsync em tipos de cliente é necessário para garantir que os recursos de
                        // rede e outros objetos não gerenciados sejam limpos adequadamente.
                        await sender.DisposeAsync();
                        await client.DisposeAsync();
                    }

                    Console.WriteLine("Pressione qualquer tecla para encerrar a aplicação");
                    Console.ReadKey();
                }
            }
        }
        ```

    === "Consumidor"

        ```c#
        // Consumer.cs
        using System;
        using System.Threading.Tasks;
        using Azure.Messaging.ServiceBus;

        namespace QueueReceiver
        {
            class Consumer
            {
                // Connection string para o namespace do Service Bus
                static string connectionString = "<connection-string>";

                // Nome da fila do Service Bus
                static string queueName = "<nome-fila>";

                // Cliente da conexão usado como emissor (produtor) ou receptor (consumidor)
                static ServiceBusClient client;

                // Receptor que lê e processa mensagens da fila
                static ServiceBusProcessor processor;

                // Lida com as mensagens recebidas
                static async Task MessageHandler(ProcessMessageEventArgs args)
                {
                    string body = args.Message.Body.ToString();
                    Console.WriteLine($"Received: {body}");

                    // Completa a mensagem e a deleta da fila.
                    await args.CompleteMessageAsync(args.Message);
                }

                // Lida com os erros ocorridos
                static Task ErrorHandler(ProcessErrorEventArgs args)
                {
                    Console.WriteLine(args.Exception.ToString());
                    return Task.CompletedTask;
                }

                static async Task Main()
                {
                    // Os tipos de cliente do Service Bus são seguros para armazenar em cache e usar como
                    // um singleton durante a vida útil do aplicativo, o que é uma prática recomendada
                    // quando as mensagens estão sendo publicadas ou lidas regularmente.

                    // Criando os clientes que usaremos para enviar e receber mensagens.
                    client = new ServiceBusClient(connectionString);

                    // Criando o receptor que usaremos para receber e processar as mensagens.
                    processor = client.CreateProcessor(queueName, new ServiceBusProcessorOptions());

                    try
                    {
                        // Adiciona um handler para processar as mensagens
                        processor.ProcessMessageAsync += MessageHandler;

                        // Adiciona handler para processar os erros
                        processor.ProcessErrorAsync += ErrorHandler;

                        // Inicia o processamento
                        await processor.StartProcessingAsync();

                        Console.WriteLine("Espere um minuto e então pressione qualquer tecla para encerrar a aplicação.");
                        Console.ReadKey();

                        // Parando o processamento
                        Console.WriteLine("\nParando o receptor...");
                        await processor.StopProcessingAsync();
                        Console.WriteLine("Parou o recebimento de mensagens");
                    }
                    finally
                    {
                        // Chamar DisposeAsync em tipos de cliente é necessário para garantir que os recursos de
                        // rede e outros objetos não gerenciados sejam limpos adequadamente.
                        await processor.DisposeAsync();
                        await client.DisposeAsync();
                    }
                }
            }
        }
        ```

## Tópicos e assinaturas

Uma fila permite o processamento de uma mensagem por um único consumidor. Em contraste com as filas, os tópicos e as assinaturas fornecem uma forma de comunicação um para muitos em um padrão de publicação e assinatura. É útil para dimensionar para um grande número de destinatários. Cada mensagem publicada é disponibilizada para cada assinatura cadastrada no tópico. O publicador envia uma mensagem para um tópico e um ou mais assinantes recebem uma cópia da mensagem, dependendo das regras de filtro definidas nessas assinaturas. As assinaturas podem usar filtros adicionais para restringir as mensagens que desejam receber. Os publicadores enviam mensagens para um tópico da mesma forma que enviam mensagens para uma fila. Porém, os consumidores não recebem mensagens diretamente do tópico. Em vez disso, os consumidores recebem mensagens de assinaturas do tópico. Uma assinatura de tópico é semelhante a uma fila virtual que recebe cópias das mensagens enviadas para o tópico. Os consumidores recebem mensagens de uma assinatura de forma idêntica à maneira como recebem mensagens de uma fila.

A funcionalidade de envio de mensagens de uma fila é mapeada diretamente para um tópico e sua funcionalidade de recebimento de mensagens é mapeada para uma assinatura. Entre outras coisas, esse recurso significa que as assinaturas suportam os mesmos padrões descritos anteriormente nesta seção em relação às filas: consumidor concorrente, desacoplamento temporal, nivelamento de carga e balanceamento de carga.

### Criando tópicos e assinaturas

Criar um tópico é semelhante a criar uma fila, conforme descrito na seção anterior. Você pode criar tópicos e assinaturas usando:

- [Portal Azure](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-quickstart-topics-subscriptions-portal)
- [PowerShell](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-quickstart-powershell)
- [CLI](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-tutorial-topics-subscriptions-cli)
- [Resource Manager templates](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-resource-manager-namespace-topic)

??? note "Exemplo de uso de filas em .NET 5.0"

    === "Produtor"

        ```c#
        // Producer.cs
        using System;
        using System.Globalization;
        using System.Threading.Tasks;
        using Azure.Messaging.ServiceBus;

        namespace QueueSender
        {
            class Producer
            {
                // Connection string para o namespace do Service Bus
                static string connectionString = "<connection-string>";

                // Nome do tópico do Service Bus
                static string topicName = "<nome-topico>";

                // Cliente da conexão usado como emissor (produtor) ou receptor (consumidor)
                static ServiceBusClient client;

                // O emissor usado para publicar mensagens para a tópico
                static ServiceBusSender sender;

                // Número de mensagens a serem enviadas para o tópico
                private const int numOfMessages = 3;

                static async Task Main()
                {

                    // Os tipos de cliente do Service Bus são seguros para armazenar em cache e usar como
                    // um singleton durante a vida útil do aplicativo, o que é uma prática recomendada
                    // quando as mensagens estão sendo publicadas ou lidas regularmente.

                    // Criando os clientes que usaremos para enviar e receber mensagens.
                    client = new ServiceBusClient(connectionString);
                    sender = client.CreateSender(topicName);

                    // Criando um lote (batch)
                    using ServiceBusMessageBatch messageBatch = await sender.CreateMessageBatchAsync();

                    for (int i = 1; i <= numOfMessages; i++)
                    {
                        string timestamp = DateTime.UtcNow.ToString("yyyy-MM-dd HH:mm:ss.ffffff",
                                            CultureInfo.InvariantCulture);

                        // Tentando adicionar a mensagem ao lote
                        if (!messageBatch.TryAddMessage(new ServiceBusMessage($"Mensagem {i} | Hora {timestamp}")))
                        {
                            // Se a mensagem for muito grande para o lote
                            throw new Exception($"A mensagem {i} é muito grande para caber no lote.");
                        }
                    }

                    try
                    {
                        // Usando o cliente produtor para enviar o lote de mensagens para o tópico do Service Bus
                        await sender.SendMessagesAsync(messageBatch);
                        Console.WriteLine($"A batch of {numOfMessages} messages has been published to the topic.");
                    }
                    finally
                    {
                        // Chamar DisposeAsync em tipos de cliente é necessário para garantir que os recursos de
                        // rede e outros objetos não gerenciados sejam limpos adequadamente.
                        await sender.DisposeAsync();
                        await client.DisposeAsync();
                    }

                    Console.WriteLine("Pressione qualquer tecla para encerrar a aplicação");
                    Console.ReadKey();
                }

            }
        }
        ```

    === "Consumidor"

        ```c#
        // Consumer.cs
        using System;
        using System.Threading.Tasks;
        using Azure.Messaging.ServiceBus;

        namespace QueueReceiver
        {
            class Consumer
            {
                // Connection string para o namespace do Service Bus
                static string connectionString = "<connection-string>";

                // Nome do tópico do Service Bus
                static string topicName = "<nome-topico>";

                // Nome da assinatura para o tópico
                static string subscriptionName = "<nome-assinatura>";

                // Cliente da conexão usado como emissor (produtor) ou receptor (consumidor)
                static ServiceBusClient client;

                // Receptor que lê e processa mensagens da assinatura
                static ServiceBusProcessor processor;

                // Lida com as mensagens recebidas
                static async Task MessageHandler(ProcessMessageEventArgs args)
                {
                    string body = args.Message.Body.ToString();
                    Console.WriteLine($"Received: {body} from subscription: {subscriptionName}");

                    // Completa a mensagem e a deleta da assinatura.
                    await args.CompleteMessageAsync(args.Message);
                }

                // Lida com os erros ocorridos
                static Task ErrorHandler(ProcessErrorEventArgs args)
                {
                    Console.WriteLine(args.Exception.ToString());
                    return Task.CompletedTask;
                }

                static async Task Main()
                {
                    // Os tipos de cliente do Service Bus são seguros para armazenar em cache e usar como
                    // um singleton durante a vida útil do aplicativo, o que é uma prática recomendada
                    // quando as mensagens estão sendo publicadas ou lidas regularmente.

                    // Create the clients that we'll use for sending and processing messages.
                    client = new ServiceBusClient(connectionString);

                    // Criando os clientes que usaremos para enviar e receber mensagens.
                    processor = client.CreateProcessor(topicName, subscriptionName, new ServiceBusProcessorOptions());

                    try
                    {
                        // Adiciona um handler para processar as mensagens
                        processor.ProcessMessageAsync += MessageHandler;

                        // Adiciona handler para processar os erros
                        processor.ProcessErrorAsync += ErrorHandler;

                        // Inicia o processamento
                        await processor.StartProcessingAsync();

                        Console.WriteLine("Espere um minuto e então pressione qualquer tecla para encerrar a aplicação.");
                        Console.ReadKey();

                        // Parando o processamento
                        Console.WriteLine("\nParando o receptor...");
                        await processor.StopProcessingAsync();
                        Console.WriteLine("Parou o recebimento de mensagens");
                    }
                    finally
                    {
                        // Chamar DisposeAsync em tipos de cliente é necessário para garantir que os recursos de
                        // rede e outros objetos não gerenciados sejam limpos adequadamente.
                        await processor.DisposeAsync();
                        await client.DisposeAsync();
                    }
                }
            }
        }
        ```

## Regras e ações

Em muitos cenários, as mensagens com características específicas devem ser processadas de maneiras diferentes. Para ativar esse processamento, você pode configurar assinaturas para localizar mensagens que possuem as propriedades desejadas e, em seguida, executar certas modificações nessas propriedades. Embora as assinaturas do _Service Bus_ vejam todas as mensagens enviadas para o tópico, você só pode copiar um subconjunto dessas mensagens para a fila de assinatura virtual. Essa filtragem é realizada usando filtros de assinatura. Essas modificações são chamadas de ações de filtro. Quando uma assinatura é criada, você pode fornecer uma expressão de filtro que opera nas propriedades da mensagem. As propriedades podem ser as propriedades do sistema (por exemplo, _Label_) e propriedades do aplicativo personalizado (por exemplo, _StoreName_). A expressão do filtro SQL é opcional neste caso. Sem uma expressão de filtro SQL, qualquer ação de filtro definida em uma assinatura será realizada em todas as mensagens dessa assinatura.

[Veja mais informações](https://docs.microsoft.com/en-us/azure/service-bus-messaging/topic-filters)

## Referências

- <https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-queues-topics-subscriptions>
