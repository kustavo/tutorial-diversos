# Preço e Suporte

## AWS Free Tier

- O nível gratuito da AWS permite que você comece a usar determinados serviços sem precisar se preocupar com custos para o período especificado.
- Estão disponíveis três tipos de ofertas:
    - **Sempre livre**:
        - Essas ofertas não expiram e estão disponíveis para todos os clientes da AWS.
        - Exemplos:
        - O *AWS Lambda* permite **1 milhão de solicitações** gratuitas e até **3,2 milhões de segundos** de tempo de computação por mês. 
        - O *Amazon DynamoDB* permite **25 GB** de armazenamento gratuito por mês.
    - **12 meses grátis**:
        - Essas ofertas são gratuitas por 12 meses após sua data de inscrição inicial na AWS.
        - Inclui:
        - Quantidades específicas de armazenamento padrão do *Amazon S3*.
        - Limites para horas mensais de tempo de computação do *Amazon EC2*.
        - Quantidades de transferência de dados do *Amazon CloudFront*.
    - **Trials**:
        - Ofertas de avaliação gratuita de curto prazo começam na data em que você ativa um serviço específico.
        - A duração de cada avaliação pode variar de acordo com o número de dias ou a quantidade de uso no serviço.
        - Exemplos:
            - O *Amazon Inspector* oferece uma avaliação gratuita de 90 dias.
            - O *Amazon Lightsail* (um serviço que permite executar servidores privados virtuais) oferece 750 horas gratuitas de uso em um período de 30 dias.

## Cobrança

### AWS Lambda

- Você é cobrado com base no número de solicitações para suas funções e no tempo necessário para que elas sejam executadas.
- O AWS Lambda permite 1 milhão de solicitações gratuitas e até 3,2 milhões de segundos de tempo de computação por mês.
- Você pode economizar nos custos do *AWS Lambda* inscrevendo-se em *Compute Savings Plans*.

### Amazon EC2

- Você paga apenas pelo tempo de computação usado enquanto suas instâncias estão em execução.
- Para algumas cargas de trabalho, você pode reduzir significativamente os custos do *Amazon EC2* usando instâncias *spot*.
- Você pode encontrar economias de custo adicionais para o *Amazon EC2* considerando *Savings Plans* e *Reserved Instances*.

### Amazon S3

- **Armazenamento**:
    - Você paga apenas pelo armazenamento que usa.
    - Você é cobrado pela taxa para armazenar objetos em seus *buckets* do *Amazon S3* com base nos tamanhos de seus objetos, classes de armazenamento e por quanto tempo você armazenou cada objeto durante o mês.
- **Solicitações e recuperações de dados**:
    - Você paga pelas solicitações feitas aos seus objetos e *buckets* do *Amazon S3*.
    - Por exemplo, suponha que você esteja armazenando arquivos de fotos em *buckets* do *Amazon S3* e os hospedando em um site. Toda vez que um visitante solicita o site que inclui esses arquivos de fotos, isso conta para as solicitações pelas quais você deve pagar.
- **Transferência de dados**:
    - Não há custo para transferir dados entre diferentes *buckets* do *Amazon S3* ou do *Amazon S3* para outros serviços na **mesma região da AWS**. 
    - Você paga pelos dados que **transfere para dentro e para fora do *Amazon S3***, com algumas exceções.
        - Não há custo para dados transferidos entre o *Amazon S3* e o *Amazon CloudFront*.
        - Não há custo para dados transferidos entre o *Amazon S3* e o *Amazon EC2* se tiverem na mesma região.
- **Gerenciamento e replicação**:
    - Você paga pelos recursos de gerenciamento de armazenamento que habilitou nos *buckets* do *Amazon S3* da sua conta.
        - Esses recursos incluem *inventory*, *analytics*, e *object tagging*.

## Billing dashboard

- Use o painel *AWS Billing & Cost Management* para pagar sua fatura da AWS, monitorar seu uso e analisar e controlar seus custos.
- Compare seu saldo atual do mês com o mês anterior e obtenha uma previsão do próximo mês com base no uso atual.
- Veja os gastos do mês até a data por serviço.
- Veja o uso do nível gratuito por serviço.
- Acesse o *Cost Explorer* e crie *budgets*.
- Adquira e gerencie *Savings Plans*.
- Publique relatórios de uso e custo da AWS.

## Consolidated billing

- *O AWS Organizations* também oferece a opção de faturamento consolidado.
- Permite que você receba uma única fatura para todas as contas da AWS em sua organização.
- Ao consolidar, você pode acompanhar facilmente os custos combinados de todas as contas vinculadas em sua organização.
- O número máximo padrão de contas permitido para uma organização é **4**, mas você pode entrar em contato com o AWS Support para aumentar sua cota, se necessário.
- Em sua fatura mensal, você pode revisar as cobranças detalhadas incorridas por cada conta.
- Permite que você tenha maior transparência nas contas da sua organização, mantendo a conveniência de receber uma única fatura mensal.
- Outro benefício do faturamento consolidado é a capacidade de compartilhar preços com desconto em massa, *savings Plans* e *reserved Instances* entre as contas da sua organização.
    - Por exemplo, uma conta pode não ter uso mensal suficiente para se qualificar para preços com desconto. No entanto, quando várias contas são combinadas, seu uso agregado pode resultar em um benefício que se aplica a todas as contas da organização.

## AWS Budgets

- Você pode criar orçamentos para planejar o uso do serviço, os custos do serviço e as reservas de instâncias.
- As informações nos Orçamentos da AWS são atualizadas **três vezes ao dia**.
- Você também pode definir alertas personalizados quando seu uso exceder (ou estiver previsto para exceder) o valor orçado.

## AWS Cost Explorer

- É uma ferramenta que permite visualizar, entender e gerenciar seus custos e uso da AWS ao longo do tempo.
- Inclui um relatório padrão dos custos e uso dos seus cinco principais serviços da AWS com custo acumulado.
- Você pode aplicar filtros e grupos personalizados para analisar seus dados.
    - Por exemplo, você pode visualizar o uso de recursos no nível de **hora em hora**.

## AWS Support

- A AWS oferece quatro planos de suporte diferentes para ajudá-lo a solucionar problemas, reduzir custos e usar os serviços da AWS com eficiência.
    - **Basic**:
        - Gratuito para todos os clientes da AWS.
        - Inclui acesso a whitepapers, documentação e comunidades de suporte.
        - Você também pode entrar em contato com a AWS para dúvidas sobre cobrança e aumentos de limite de serviço.
        - Você tem acesso a uma **seleção limitada** de verificações do *AWS Trusted Advisor*.
        - Você pode usar o *AWS Personal Health Dashboard*, uma ferramenta que fornece alertas e orientações de correção quando a AWS está passando por eventos que podem afetá-lo.
    - **Developer**:
        - Orientação de boas práticas.
        - Ferramentas de diagnóstico do lado do cliente.
        - Suporte à arquitetura *Building-block*, que consiste em orientações sobre como usar juntos ofertas, recursos e serviços da AWS.
        - Aplicação:
            - Suponha que sua empresa esteja explorando os serviços da AWS. Você já ouviu falar sobre alguns serviços diferentes da AWS. No entanto, você não tem certeza de como usá-los juntos para criar aplicativos que possam atender às necessidades da sua empresa. Nesse cenário, o suporte à arquitetura de bloco de construção incluído no plano de suporte ao desenvolvedor pode ajudá-lo a identificar oportunidades para combinar serviços e recursos específicos.
    - **Business**:
        - Orientação de casos de uso para identificar ofertas, recursos e serviços da AWS que podem atender melhor às suas necessidades específicas
        - Acesso a **todas** as verificações do *AWS Trusted Advisor*
        - Suporte limitado para software de terceiros, como sistemas operacionais comuns e componentes da *stack* de aplicativos
        - Aplicação:
            - Suponha que sua empresa tenha o plano Business Support e queira instalar um sistema operacional de terceiros comum em suas instâncias do Amazon EC2. Você pode entrar em contato com o AWS Support para obter assistência na instalação, configuração e solução de problemas do sistema operacional. Para tópicos avançados, como otimizar o desempenho, usar scripts personalizados ou resolver problemas de segurança, pode ser necessário entrar em contato diretamente com o fornecedor de software terceirizado.
    - **Enterprise**:
        - Incluí recursos de todos os planos.
        - Orientação de arquitetura de aplicativos, que é um relacionamento **consultivo** para dar suporte aos casos de uso e aplicativos específicos da sua empresa.
        - Gerenciamento de eventos de infraestrutura.
            - Um compromisso de curto prazo com o *AWS Support* que ajuda sua empresa a entender melhor seus casos de uso.
            - Fornece à sua empresa orientação de arquitetura e dimensionamento.
            - Um gerente técnico de contas.
- Nos planos **Developer**, **Business** e **Enterprise** os seguintes serviços estão incluídos.
    - Incluem todos os benefícios do Basic Support
    - Capacidade de abrir um número irrestrito de casos de suporte técnico.
    - Pagamento mensal e não exigem contratos de longo prazo.

## Technical Account Manager (TAM)

- O plano *Enterprise* inclui acesso a um *Technical Account Manager (TAM)*.
- Se sua empresa tiver um plano de suporte *Enterprise*, o TAM é seu principal ponto de contato na AWS.
    - Eles fornecem orientação, revisões de arquitetura e comunicação contínua com sua empresa à medida que você planeja, implanta e otimiza seus aplicativos.
- Seu TAM oferece experiência em toda a gama de serviços da AWS.
- Eles ajudam você a projetar soluções que usam com eficiência vários serviços juntos por meio de uma abordagem integrada.
- Aplicação:
    - Suponha que você esteja interessado em desenvolver um aplicativo que use vários serviços da AWS juntos. Seu TAM pode fornecer informações sobre como usar melhor os serviços juntos. Eles conseguem isso, ao mesmo tempo em que se alinham às necessidades específicas que sua empresa espera atender por meio do novo aplicativo.

## AWS Marketplace

- É um catálogo digital que inclui milhares de listagens de software de fornecedores independentes de software.
- Você pode usar o AWS Marketplace para encontrar, testar e comprar softwares executados na AWS.
- Para cada listagem no AWS Marketplace, você pode acessar informações detalhadas sobre opções de preços, suporte disponível e avaliações de outros clientes da AWS.
- Você também pode explorar soluções de software por setor e caso de uso.
    - Por exemplo, suponha que sua empresa esteja no setor de saúde. No AWS Marketplace, você pode analisar os casos de uso que o software ajuda a resolver, como implementar soluções para proteger os registros de pacientes ou usar modelos de machine learning para analisar o histórico médico de um paciente e prever possíveis riscos à saúde.

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>
