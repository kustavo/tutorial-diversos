# Introdução

A AWS é uma plataforma de nuvem altamente escalável. Ela suporta sistemas grandes e exigentes, como Amazon.com, Netflix ou sistemas bancários, e também pode ser usado para pequenas empresas e startups.

O Amazon Web Services (AWS) é uma plataforma de computação em nuvem bastante abrangente, desenvolvida e fornecida pela Amazon. A plataforma combina modelos de serviços cloud como:

- Infraestrutura como serviço (IaaS);
- Plataforma como serviço (PaaS);
- Pacote de software como serviço (SaaS).

## Serviços

Confira um detalhamento rápido dos principais serviços que você vai encontrar dentro dessa plataforma:

- **Computação**:
  - **EC2 (Elastic Compute Cloud)**: uma máquina virtual na nuvem na qual você tem controle no nível do sistema operacional. Você pode executar este servidor em nuvem sempre que desejar;
  - **LightSail**: ferramenta de computação em nuvem que implementa e gerencia automaticamente os recursos de computador, armazenamento e rede necessários para executar seus aplicativos;
  - **Elastic Beanstalk**: ferramenta que oferece implantação e provisionamento automatizados de recursos, como um site de produção altamente escalável;
  - **EKS (Elastic Container Service para Kubernetes)**: ferramenta que permite que você faça orquestração de contêineres de código aberto no ambiente de nuvem da Amazon sem instalação;
  - **AWS Lambda**: serviço que permite executar funções na nuvem. A ferramenta economiza muito para você pagar apenas quando suas funções são executadas.
- **Migração**:
  - **DMS (Serviço de Migração de Banco de Dados)**: pode ser usado para migrar bancos de dados no local para a AWS. Ajuda você a migrar de um tipo de banco de dados para outro — por exemplo, Oracle para MySQL;
  - **SMS (Serviço de migração de servidor)**: os serviços de migração de SMS permitem migrar servidores no local para a AWS de maneira fácil e rápida;
  - **Snowball**: um pequeno aplicativo que permite transferir terabytes de dados dentro e fora do ambiente da AWS.
- **Armazenamento**:
  - **Amazon Glacier**: serviço de armazenamento extremamente barato. Oferece armazenamento rápido e seguro para arquivamento e backup de dados;
  - **Amazon Elastic Block Store (EBS)**: fornece armazenamento em nível de bloco para uso com instâncias do Amazon EC2. Os volumes do Amazon Elastic Block Store são conectados à rede e permanecem independentes da vida útil de uma instância;
  - **AWS Storage Gateway**: conecta aplicativos de software local com armazenamento baseado em nuvem; oferece integração segura entre a empresa no local e a infraestrutura de armazenamento da AWS.
- **Segurança**:
  - **IAM (Gerenciamento de identidade e acesso)**: serviço de segurança em nuvem que ajuda a gerenciar usuários, atribuir políticas, formar grupos para gerenciar vários usuários;
  - **Inspetor**: agente que você pode instalar em suas máquinas virtuais, que reporta quaisquer vulnerabilidades de segurança;
  - **Gerenciador de Certificados**: oferece certificados SSL gratuitos para seus domínios gerenciados pelo Route53;
  - **WAF (Web Application Firewall)**: serviço de segurança WAF oferece proteção no nível do aplicativo e permite bloquear a injeção de SQL e ajuda a bloquear ataques de script entre sites;
  - **Diretório em nuvem**: permite criar diretórios nativos da nuvem flexíveis para gerenciar hierarquias de dados em várias dimensões;
  - **KMS (Serviço de Gerenciamento de Chaves)**: serviço gerenciado; ajuda você a criar e controlar as chaves de criptografia que permitem criptografar seus dados;
  - **Shield**: gerenciado por DDoS (serviço de proteção contra negação de serviço distribuída), oferece salvaguardas contra aplicativos da Web em execução na AWS;
  - **Macie**: oferece um serviço de segurança de visibilidade de dados que ajuda a classificar e proteger seu conteúdo crítico sensível;
  - **GuardDuty**: oferece detecção de ameaças para proteger suas contas e cargas de trabalho da AWS.
- **Banco de dados**:
  - **Amazon RDS**: serviço de banco de dados que é fácil de configurar, operar e dimensionar um banco de dados relacional na nuvem;
  - **Amazon DynamoDB**: serviço de banco de dados NoSQL rápido e totalmente gerenciado. É um serviço simples que permite armazenamento e recuperação econômicos de dados. Também permite atender qualquer nível de tráfego de solicitações;
  - **Amazon ElastiCache**: facilita a implantação, a operação e a escalabilidade de um cache na memória na nuvem;
  - **Netuno**: serviço de banco de dados de gráficos rápido, confiável e escalável;
  - **Amazon RedShift**: solução de data warehousing da Amazon que você pode usar para executar consultas OLAP complexas.
- **Gestão**:
  - **Cloud Watch**: ajuda a monitorar ambientes da AWS, como EC2, instâncias RDS e utilização da CPU. Também aciona alarmes, dependendo de várias métricas.
  - **Cloud Formation**: é uma maneira de transformar a infraestrutura em nuvem. Você pode usar modelos para fornecer um ambiente de produção inteiro em minutos.
  - **Cloud Trail**: oferece um método fácil de auditar os recursos da AWS. Ajuda você a registrar todas as alterações.
  - **Opsworks**: o serviço permite implantações automatizadas de Chef/Puppet no ambiente da AWS.
  - **Config**: este serviço da AWS monitora seu ambiente. A ferramenta envia alertas sobre alterações quando você quebra certas configurações definidas.
  - **Catálogo de serviços**: ajuda grandes empresas a autorizar quais usuários de serviços serão usados ​​e quais não.
  - **AWS Auto Scaling**: permite que você dimensione automaticamente seus recursos para cima e para baixo com base nas métricas fornecidas do CloudWatch.
  - **Gerente de sistemas**: esse serviço da AWS permite agrupar seus recursos. Permite identificar problemas e agir de acordo com eles.
  - **Serviços gerenciados**: oferece gerenciamento de sua infraestrutura da AWS, permitindo que você se concentre em seus aplicativos.
- **Internet das Coisas**:
  - **IoT Core**: é um serviço da AWS em nuvem gerenciada. O serviço permite que dispositivos conectados, como carros, lâmpadas, grades de sensores, interajam com segurança com aplicativos em nuvem e outros dispositivos.
  - **Gerenciamento de dispositivos de IoT**: permite gerenciar seus dispositivos de IoT em qualquer escala.
  - **IoT Analytics**: este serviço da AWS IOT é útil para realizar análises dos dados coletados pelos seus dispositivos IoT.
  - **Amazon FreeRTOS**: este sistema operacional em tempo real para microcontroladores ajuda a conectar dispositivos IoT no servidor local ou na nuvem.
- **Aplicação**:
  - **Funções de etapa**: é uma maneira de visualizar o que está acontecendo dentro do seu aplicativo e quais os diferentes microsserviços que ele está usando;
  - **SWF (Serviço de fluxo de trabalho simples)**: serviço que ajuda a coordenar tarefas automatizadas e tarefas conduzidas por humanos;
  - **SNS (Serviço de Notificação Simples)**: você pode usar este serviço para enviar notificações na forma de email e SMS com base nos serviços da AWS fornecidos;
  - **SQS (Serviço de Fila Simples)**: use este serviço da AWS para desacoplar seus aplicativos. É um serviço baseado em pull;
  - **Transcodificador elástico**: essa ferramenta de serviço da AWS ajuda a alterar o formato e a resolução de um vídeo para suportar vários dispositivos, como tablets, smartphones e laptops de diferentes resoluções.
- **Implantação e gerenciamento**:
  - **AWS CloudTrail**: os serviços registram as chamadas da API da AWS e enviam arquivos de backlog para você;
  - **Amazon CloudWatch**: as ferramentas monitoram os recursos da AWS, como o Amazon EC2 e o Amazon RDS DB Instances. Também permite monitorar métricas personalizadas criadas pelos aplicativos e serviços do usuário;
  - **AWS CloudHSM**: este serviço da AWS ajuda a atender aos requisitos de conformidade corporativos, regulamentares e contratuais para manter a segurança dos dados usando os dispositivos Hardware Security Module (HSM) dentro do ambiente da AWS.
- **Desenvolvimento**:
  - **CodeStar**: é um serviço baseado em nuvem para criar, gerenciar e trabalhar com vários projetos de desenvolvimento de software na AWS;
  - **CodeCommit**: é o serviço de controle de versão da AWS que permite armazenar seu código e outros ativos de maneira privada na nuvem;
  - **CodeBuild**: este serviço de desenvolvedor da Amazon ajuda a automatizar o processo de criação e compilação do seu código;
  - **CodeDeploy**: é uma maneira de implantar seu código nas instâncias do EC2 automaticamente;
  - **CodePipeline**: ajuda a criar um pipeline de implantação, como teste, construção, teste, autenticação, implantação em ambientes de desenvolvimento e produção;
  - **Cloud9**: é um ambiente de desenvolvimento integrado para gravação, execução e depuração de código na nuvem.
- **Mobilidade**:
  - **Hub móvel**: permite adicionar, configurar e projetar recursos para aplicativos móveis;
  - **Cognito**: permite que os usuários se inscrevam usando sua identidade social;
  - **Farm de dispositivos**: o farm de dispositivos ajuda você a melhorar a qualidade dos aplicativos testando rapidamente centenas de dispositivos móveis;
  - **AWS AppSync**: é um serviço GraphQL totalmente gerenciado que oferece sincronização de dados em tempo real e recursos de programação offline.
- **Produtividade nos negócios**:
  - **Alexa for Business**: capacita sua organização com voz, usando Alexa. Isso ajudará você a criar habilidades de voz personalizadas para sua organização;
  - **WorkDocs**: ajuda a armazenar documentos na nuvem;
  - **WorkMail**: permite enviar e receber e-mails comerciais.
- **Desktop e streaming de aplicativos**:
  - **Workspaces**: é um VDI (Virtual Desktop Infrastructure). Ele permite que você use áreas de trabalho remotas na nuvem;
  - **AppStream**: uma maneira de transmitir aplicativos de desktop para seus usuários no navegador da web. Por exemplo, usando o MS Word no Google Chrome.
- **Inteligência Artificial**:
  - **Lex**: ajuda você a criar chatbots rapidamente;
  - **Polly**: é o serviço de conversão de texto em fala da AWS que permite criar versões em áudio de suas anotações;
  - **Reconhecimento**: é o serviço de reconhecimento de rosto da AWS. Este serviço da AWS ajuda a reconhecer rostos e objetos em imagens e vídeos;
  - **Sage Maker**: permite criar, treinar e implantar modelos de aprendizado de máquina em qualquer escala;
  - **Transcribe**: é o serviço de fala para texto da AWS que oferece transcrições de alta qualidade e acessíveis;
  - **Translate**: ferramenta muito semelhante ao Google Tradutor, que permite traduzir texto em um idioma para outro;
  - **Sumerian**: é um conjunto de ferramentas para oferecer experiências de realidade virtual (VR) de alta qualidade na web. O serviço permite criar cenas 3D interativas e publicá-las como um site para os usuários acessarem.
- **Envolvimento do cliente**:
  - **Amazon Connect**: permite criar seu centro de atendimento ao cliente na nuvem;
  - **Pinpoint**: ajuda a entender seus usuários e se envolver com eles;
  - **SES (Simple Email Service)**: ajuda a enviar e-mails em massa para seus clientes a um preço relativamente econômico.

## Referências

- <https://flexa.cloud/18-servicos-essenciais-da-aws-para-sua-empresa/>
