# Migração e transferência

## AWS Cloud Adoption Framework (AWS CAF)

- Organiza a orientação em **seis** áreas de foco, chamadas Perspectivas (**Perspectives**).
- Cada Perspectiva aborda responsabilidades distintas. O processo de planejamento ajuda as pessoas certas em toda a organização a se prepararem para as mudanças futuras.
- As Perspectivas de **Business**, **People** e **Governance** se concentram nos recursos de negócios
- As Perspectivas de **Platform**, **Security** e **Operations** se concentram nos recursos técnicos.

### Business Perspective

- Garante que a TI se alinhe às necessidades de negócios e que os investimentos em TI se vinculem aos principais resultados de negócios.
- Ajuda você a passar de um modelo que separa as estratégias de negócios e de TI para um modelo de negócios que integra a estratégia de TI.
- Aplicação:
    - Use a Perspectiva de Negócios para criar um forte caso de negócios para a adoção da nuvem e priorize as iniciativas de adoção da nuvem.
- Certifique-se de que suas estratégias e objetivos de negócios estejam alinhados com suas estratégias e objetivos de TI.
- As funções comuns na Perspectiva de Negócios incluem:
    - Gerentes de negócios
    - Gerentes de finanças
    - Proprietários de orçamento (*budget owners*)
    - Partes interessadas da estratégia (*strategy stakeholders*)

### People Perspective

- Apoia o desenvolvimento de uma estratégia de gerenciamento de mudanças em toda a organização para a adoção bem-sucedida da nuvem.
- Ajuda os funcionários de Recursos Humanos (RH) a preparar suas equipes para a adoção da nuvem, atualizando os processos organizacionais e as habilidades da equipe para incluir competências baseadas na nuvem.
- Aplicação:
    - Use a Perspectiva de Pessoas para avaliar estruturas e funções organizacionais, novos requisitos de habilidades e processos e identificar lacunas. Isso ajuda a priorizar treinamento, pessoal e mudanças organizacionais.
- Os papéis comuns na Perspectiva de Pessoas incluem:
    - Recursos Humanos
    - Pessoal
    - Gestores de pessoas

### Governance Perspective

- Concentra-se nas habilidades e processos para alinhar a estratégia de TI com a estratégia de negócios. Isso garante que você maximize o valor do negócio e minimize os riscos.
- Aplicação:
    - Use a Perspectiva de Governança para entender como atualizar as habilidades da equipe e os processos necessários para garantir a governança de negócios na nuvem. Gerencie e avalie os investimentos em nuvem para avaliar os resultados dos negócios.
- As funções comuns na Perspectiva de Governança incluem:
    - Diretor de Informação (CIO)
    - Gerentes de programa
    - Arquitetos empresariais
    - Analistas de negócios
    - Gerentes de portfólio

#### Platform Perspective

- Inclui princípios e padrões para implementar novas soluções na nuvem e migrar cargas de trabalho locais para a nuvem.
- Aplicação:
    - Use uma variedade de modelos de arquitetura para entender e comunicar a estrutura dos sistemas de TI e seus relacionamentos. Descreva a arquitetura do ambiente de estado de destino em detalhes.
- As funções comuns na perspectiva da plataforma incluem:
    - Diretor de Tecnologia (CTO)
    - Gerentes de TI
    - Arquitetos de soluções

### Security Perspective

- Garante que a organização atenda aos objetivos de segurança para visibilidade, auditabilidade, controle e agilidade.
- Aplicação:
    - Use o AWS CAF para estruturar a seleção e implementação de controles de segurança que atendam às necessidades da organização.
- As funções comuns na Perspectiva de Segurança incluem:
    - Diretor de Segurança da Informação (CISO)
    - Gerentes de segurança de TI
    - Analistas de segurança de TI

### Operations Perspective

- Ajuda você a habilitar, executar, usar, operar e recuperar cargas de trabalho de TI no nível acordado com as partes interessadas do seu negócio.
- Aplicação:
    - Defina como os negócios diários, trimestrais e anuais são conduzidos.
    - Alinhar e apoiar as operações do negócio.
    - O AWS CAF ajuda essas partes interessadas a definir os procedimentos operacionais atuais e identificar as mudanças de processo e o treinamento necessários para implementar a adoção bem-sucedida da nuvem.
- As funções comuns na Perspectiva de Operações incluem:
    - Gerentes de operações de TI
    - Gerentes de suporte de TI

## Estratégias de migração

### Rehosting

- Também conhecida como **lift-and-shift**, envolve a movimentação de aplicativos sem alterações.
- Cenário:
    - No cenário de uma grande migração de legado, em que a empresa busca implementar sua migração e dimensionar rapidamente para atender a um caso de negócios, a maioria dos aplicativos é rehospedada.

### Replatforming

- Também conhecida como **lift, tinker, and shift**, envolve fazer algumas otimizações na nuvem para obter um benefício tangível.
- A otimização é alcançada **sem alterar** a arquitetura central do aplicativo.

### Refactoring/re-architecting

- Também conhecida como **re-architecting** envolve reimaginar como um aplicativo é arquitetado e desenvolvido usando recursos nativos da nuvem.
- Cenário:
    - A refatoração é impulsionada por uma forte necessidade comercial de adicionar recursos, escala ou desempenho que, de outra forma, seriam difíceis de alcançar no ambiente existente do aplicativo.

### Repurchasing

- Envolve a mudança de uma licença tradicional para um modelo de software como serviço.
- Cenário:
    - Por exemplo, uma empresa pode optar por implementar a estratégia de recompra migrando de um sistema de *Customer Relationship Management - CRM* para *Salesforce.com*.

### Retaining

- Consiste em manter os aplicativos críticos para os negócios no ambiente de origem.
- Isso pode incluir aplicativos que exigem grande refatoração antes de serem migrados ou trabalhos que podem ser adiados para um momento posterior.

### Retiring

- Processo de remoção de aplicativos que não são mais necessários.

## AWS Snow Family

- A família AWS Snow é uma coleção de **dispositivos físicos** que ajudam a transportar fisicamente até **exabytes** de dados para dentro e fora da AWS.
- A AWS possui e gerencia os dispositivos da Snow Family e se integra aos recursos de segurança, monitoramento, gerenciamento de armazenamento e computação da AWS.
- A família AWS Snow é composta por:
    - **AWS Snowcone**:
        - É um dispositivo de transferência de dados e computação de borda pequeno, robusto e seguro.
        - Possui 2 CPUs, 4 GB de memória e **8 TB** de armazenamento utilizável.
    - **AWS Snowball**:
        - **Snowball Edge Storage Optimized**:
            - Dispositivos adequados para migrações de dados em grande escala e fluxos de trabalho de transferência recorrentes, além de computação local com necessidades de maior capacidade.
            - Armazenamento: **80 TB HDD** para volumes de blocos e armazenamento de objetos compatível com Amazon S3 e **1 TB SSD Sata** para volumes de blocos.
            - Computação: **40 vCPUs e 80 GiB de memória** para dar suporte a instâncias sbe1 do Amazon EC2 (equivalente a C5).
        - **Snowball Edge Compute Optimized**:
            - Fornece recursos de computação poderosos para casos de uso, como aprendizado de máquina, análise de vídeo em movimento total, análise e pilhas de computação local.
            - Armazenamento: **42 TB HDD** para armazenamento de objetos compatível com Amazon S3 ou volumes de blocos compatíveis com Amazon EBS e **7,68 TB SSD NVMe** utilizável para volumes de blocos compatíveis com Amazon EBS.
            - Computação: **52 vCPUs, 208 GiB de memória e uma GPU NVIDIA Tesla V100 opcional**.
            - Os dispositivos executam instâncias sbe-c e sbe-g do Amazon EC2, que são equivalentes a instâncias C5, M5a, G3 e P3.
    - **AWS Snowmobile**
        - É um serviço de download de dados em escala de **exabytes** usado para mover grandes móveis de dados para a AWS.
        - Você pode transferir até **100 petabytes** de dados por Snowmobile, um contêiner de transporte robusto de 45 pés de comprimento, puxado por um caminhão semi-reboque.

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>
