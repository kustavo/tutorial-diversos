# Integração de aplicativos

## Amazon SNS (Simple Notification Service)

- É um serviço de publicação/assinatura. Usando tópicos do Amazon SNS, um editor publica mensagens para os assinantes.
    - Os assinantes podem ser servidores web, endereços de e-mail, funções do AWS Lambda ou várias outras opções.
- Método Push - Amazon SNS envia mensagens para os assinantes (consumidores) de um tópico.

## Amazon SQS (Simple Queue Service)

- É um serviço de enfileiramento de mensagens.
- Você pode enviar, armazenar e receber mensagens entre componentes de software, sem perder mensagens ou exigir que outros serviços estejam disponíveis.
- No Amazon SQS, um aplicativo envia mensagens para uma fila. Um usuário ou serviço recupera uma mensagem da fila, a processa e a exclui da fila.
- Método Pull - Os consumidores é quem verificam se há mensagens na fila.
- Não possui assinantes em tópicos.
- FIFO
- Idempotência - não introduzem mensagens duplicadas no controle de janela de 5 minutos.
    - Método 1: SHA-256 baseado no corpo da mensagem (não utiliza os atributos).
    - Método 2: O produtor fornece explicitamente um ID na mensagem para o controle de duplicidade. 

## Amazon MQ

- É um serviço gerenciado de *message broker* que **facilita a migração** para um agente de mensagem na nuvem.
- Permite que aplicações de software e componentes se comuniquem usando várias linguagens de programação, sistemas operacionais e protocolos de sistemas de mensagens formais.
- Atualmente, o Amazon MQ é compatível com os mecanismos *Apache ActiveMQ e RabbitMQ*.

O Amazon MQ funciona com as aplicações e serviços existentes sem a necessidade de gerenciar, operar e manter seu próprio sistema de mensagens.

## Amazon Simple Workflow Service (Amazon SWF)

- Facilita a **criação de aplicativos que coordenem tarefas** entre componentes distribuídos.
- No Amazon SWF, uma tarefa representa uma unidade lógica de trabalho que é executada por um componente do seu aplicativo.
- A coordenação de tarefas dentro do aplicativo envolve o gerenciamento de dependências entre as tarefas, a programação e a concorrência de acordo com o fluxo lógico do aplicativo.
- Com o Amazon SWF, você tem o controle total das tarefas de implementação e da coordenação delas sem se preocupar com complexidades fundamentais, como o acompanhamento do progresso e a manutenção do estado delas.

## Referências

- <https://docs.aws.amazon.com/pt_br/sns/latest/dg/welcome.html>
- <https://docs.aws.amazon.com/pt_br/AWSSimpleQueueService/latest/SQSDeveloperGuide/welcome.html>
- <https://docs.aws.amazon.com/pt_br/AWSSimpleQueueService/latest/SQSDeveloperGuide/FIFO-queues-exactly-once-processing.html>
