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

## Referências

- <https://docs.aws.amazon.com/pt_br/sns/latest/dg/welcome.html>
- <https://docs.aws.amazon.com/pt_br/AWSSimpleQueueService/latest/SQSDeveloperGuide/welcome.html>
- <https://docs.aws.amazon.com/pt_br/AWSSimpleQueueService/latest/SQSDeveloperGuide/FIFO-queues-exactly-once-processing.html>
