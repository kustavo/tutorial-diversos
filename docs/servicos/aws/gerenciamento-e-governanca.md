# Gerenciamento e governança

## Amazon CloudWatch

- É um serviço da web que permite monitorar e gerenciar várias métricas e configurar ações de alarme com base nos dados dessas métricas.
- Os serviços da AWS enviam métricas para o *CloudWatch*.
    - O CloudWatch usa essas métricas para criar gráficos automaticamente que mostram como o desempenho mudou ao longo do tempo.

### CloudWatch alarm

- Com o *CloudWatch*, você pode criar alarmes que executam ações automaticamente se o valor de sua métrica estiver acima ou abaixo de um limite predefinido.
- Exemplo de aplicação:
    - Suponha que os desenvolvedores de sua empresa usem instâncias do Amazon EC2 para fins de desenvolvimento ou teste de aplicativos. Se os desenvolvedores ocasionalmente esquecerem de parar as instâncias, as instâncias continuarão em execução e incorrerão em cobranças.
    - Nesse cenário, você pode criar um alarme do *CloudWatch* que interrompa automaticamente uma instância do Amazon EC2 quando a porcentagem de utilização da CPU permanecer abaixo de um determinado limite por um período especificado. 
- Ao configurar o alarme, você pode especificar para receber uma notificação sempre que este alarme for acionado.

### CloudWatch dashboard

- Permite acessar todas as métricas de seus recursos em um único local.
- Exemplo de aplicação:
    - Você pode usar um painel do *CloudWatch* para monitorar a utilização da CPU de uma instância do Amazon EC2, o número total de solicitações feitas a um bucket do Amazon S3 e muito mais.
- Você pode personalizar dashboards separados para diferentes finalidades de negócios, aplicativos ou recursos.

## AWS CloudTrail

- Registra chamadas de API para sua conta, como um log de ações.
    - As informações registradas incluem a identidade do chamador da API, a hora da chamada da API, o endereço IP de origem do chamador da API e muito mais.
- Você pode visualizar um histórico completo da atividade do usuário e chamadas de API para seus aplicativos e recursos.
- Os eventos geralmente são atualizados no *CloudTrail* dentro de **15 minutos** após uma chamada de API.
- Você pode filtrar eventos especificando a hora e a data em que ocorreu uma chamada de API, o usuário que solicitou a ação, o tipo de recurso envolvido na chamada de API e muito mais.

### CloudTrail Insights

- No CloudTrail, você também pode habilitar o CloudTrail Insights.
- Esse recurso opcional permite que o CloudTrail detecte automaticamente atividades de API incomuns em sua conta da AWS.
- Exemplo de aplicação:
    - Pode detectar que um número maior de instâncias do Amazon EC2 do que o normal foi iniciado recentemente em sua conta.
    - Você pode então revisar os detalhes completos do evento para determinar quais ações você precisa realizar em seguida.

## AWS Trusted Advisor

- É um serviço da web que inspeciona seu ambiente da AWS e fornece **recomendações em tempo real** de acordo com as melhores práticas da AWS.
- Compara suas descobertas com as melhores práticas da AWS em cinco categorias:
    - **otimização de custos**
    - **desempenho**
    - **segurança**
    - **tolerância a falhas**
    - **limites de serviço**
- Para as verificações em cada categoria, o Trusted Advisor oferece uma lista de ações recomendadas e recursos adicionais para saber mais sobre as melhores práticas da AWS.
- Pode beneficiar sua empresa em todos os estágios de implantação.
- Exemplo de aplicação:
    - Você pode usar o AWS Trusted Advisor para ajudá-lo enquanto cria novos fluxos de trabalho e desenvolve novos aplicativos.
    - Pode usá-lo enquanto faz melhorias contínuas em aplicativos e recursos existentes.

### AWS Trusted Advisor dashboard

- Mostrando o número de itens sem problemas detectados, investigações recomendadas e ações recomendadas para as categorias de otimização de custos, desempenho, segurança, tolerância a falhas e limites de serviço.
- Para cada categoria:
    - A verificação verde indica o número de itens para os quais não detectou problemas.
    - O triângulo laranja representa o número de investigações recomendadas.
    - O círculo vermelho representa o número de ações recomendadas.

## AWS CloudFormation

- É um serviço que ajuda você a modelar e configurar seus recursos da AWS.
- Você cria um modelo (em inglês, *template*) que descreve todos os recursos da AWS que você deseja e o CloudFormation cuida do provisionamento e da configuração desses recursos para você.
- Não é necessário criar e configurar individualmente os recursos da AWS e descobrir o que depende do que; o CloudFormation lida com isso.

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>
