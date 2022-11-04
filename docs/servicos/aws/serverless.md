# Serverless

## Amazon API Gateway

- É um serviço da AWS para criação, publicação, manutenção, monitoramento e proteção de APIs REST e WebSocket em qualquer escala.
- Os desenvolvedores de API podem criar APIs que acessem a AWS ou outros web services, bem como dados armazenados na Nuvem AWS.
- Cria APIs sem estado RESTful que Implementam os métodos HTTP padrão, como GET, POST, PUT, PATCH e DELETE.
- Cria APIs com estado WebSocket que seguem o protocolo WebSocket, que permite a comunicação full-duplex entre cliente e servidor.
- Roteiam mensagens recebidas com base no conteúdo da mensagem.
- Oferece mecanismos de autenticação poderosos e flexíveis.
- Oferece implantações de versão Canary para lançar alterações com segurança.
- Registro em log e monitoramento do *CloudTrail* do uso e alterações de API.
- Registro de acesso em logs e registro de execução em logs do *CloudWatch*, incluindo a capacidade de definir alarmes.
- Capacidade de usar modelos do *AWS CloudFormation* para permitir a criação de APIs.
- Suporte para nomes de domínio personalizados.
- Integração ao *AWS WAF* para proteger suas APIs contra explorações comuns da web.
- Integração com latências de desempenho *AWS X-Ray* para compreensão e triagem.

### Controle de picos com o API Gateway

- O Amazon API Gateway oferece controle (em inglês, *throttling*) de utilização em diversos níveis, incluindo os níveis global e por serviço.
- Os limites do controle de utilização podem ser definidos para taxas padrão e picos.
    - Por exemplo, os proprietários de uma API podem definir um limite de taxa de 1.000 solicitações por segundo para um método específico nas APIs REST, bem como configurar o Amazon API Gateway para processar picos de 2.000 solicitações por segundo durante alguns segundos.
    - O Amazon API Gateway controla o número de solicitações por segundo. Todas as solicitações acima do limite receberão uma resposta HTTP 429.
        - Os SDKs cliente (exceto o Javascript) gerados pelo Amazon API Gateway repetem automaticamente as chamadas quando recebem essa resposta.

## Referências

- <https://docs.aws.amazon.com/apigateway/latest/developerguide/welcome.html>