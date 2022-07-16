# Segurança, identidade e conformidade

Gerenciamento da segurança (*security*), identidade (*identity*) e conformidade (*compliance*) na AWS.

## AWS Identity and Access Management (IAM)

- Permite que você gerencie o acesso aos serviços e recursos da AWS com segurança.
- Recursos do IAM:
  - Usuários, grupos e funções do IAM.
  - Políticas de IAM.
  - Autenticação multifator.

### AWS Root User

- Ao criar uma conta da AWS pela primeira vez, você começa com uma identidade conhecida como usuário **root**.
- O usuário root é acessado fazendo login com o endereço de e-mail e a senha que você usou para criar sua conta da AWS.

### IAM User

- É uma identidade que você cria na AWS.
- Representa a pessoa ou o aplicativo que interage com os serviços e recursos da AWS.
- Consiste em um nome e credenciais.
- Por padrão, quando você cria um novo usuário do IAM na AWS, ele não tem permissões associadas a ele.

### IAM groups

- Um *IAM groups* é um grupo de usuários do IAM.
- Use grupos para especificar permissões para um grupo de usuários.
  - Quando você atribui uma política do IAM a um grupo, todos os usuários do grupo recebem as permissões especificadas pela política.

### IAM Policies

- Uma política (*policie*) é um objeto na AWS que define permissões para serviços e recursos da AWS.
- Pode ser aplicada a usuários (*users*), grupos de usuários (*user groups*) e unidades organizacionais (*OUs - Organizational Units*).

### IAM Roles

Um papel/função (*role*) no IAM é uma identidade que você pode criar com permissões específicas com credenciais válidas por **curtos períodos**.

## AWS Organizations

- Consolidar e gerenciar várias contas da AWS em um local central.
- Quando você cria uma organização, é criado uma raiz (*root*), que é o contêiner pai para todas as contas na organização.
- Pode controlar centralmente as permissões para as contas em sua organização usando políticas de controle de serviço (*SCPs - Service Control Policies*).
  - As SCPs permitem que você coloque restrições nos serviços, recursos e ações de API individuais da AWS que usuários e funções em cada conta podem acessar.
  - SCPs podem ser aplicadas a *Organizational Units* e a conta AWS de membros individuais (não confundir com *IAM User*).

<figure>
    <img src="../_seguranca-e-identidade-e-conformidade/root-e-ou.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>AWS Organizations - Root e OU</figcaption>
</figure>

### Organizational Units

- No *AWS Organizations*, você pode agrupar contas em unidades organizacionais (*OUs - Organizational Units*) para facilitar o gerenciamento de contas com requisitos comerciais ou de segurança semelhantes.
- Quando você aplica uma política a uma OU, todas as contas na OU herdam automaticamente as permissões especificadas na política.

## Compliance

### AWS Artifact

Dependendo do setor de sua empresa, pode ser necessário manter padrões específicos. Uma auditoria ou inspeção garantirá que a empresa atendeu a esses padrões.

- AWS Artifact é um serviço que fornece acesso sob demanda a relatórios de segurança e conformidade da AWS e contratos online selecionados.
- Consiste em duas seções principais:
  - **AWS Artifact Agreements**:
    - Você pode revisar, aceitar e gerenciar contratos com a AWS para uma conta individual e para todas as suas contas no *AWS Organizations*.
  - **AWS Artifact Reports**:
    - Fornece relatórios de conformidade de auditores terceirizados que testaram e verificaram se a AWS está em conformidade com vários padrões e regulamentos de segurança globais, regionais e específicos do setor.
    - Permanece atualizado com os relatórios mais recentes lançados.
    - Você pode fornecer os artefatos de auditoria da AWS para seus auditores ou reguladores como evidência dos controles de segurança da AWS.

### Customer Compliance Center

- Contém recursos para ajudá-lo a saber mais sobre a conformidade com a AWS.
- Você pode ler histórias de conformidade de clientes para descobrir como as empresas em setores regulamentados resolveram vários desafios de conformidade, governança e auditoria.

## AWS Shield

- É um serviço que protege aplicativos contra ataques **DDoS**.
- Oferece dois níveis de proteção:
  - **Standard**:
    - Protege automaticamente todos os clientes da AWS **sem nenhum custo**.
    - Protege seus recursos da AWS dos tipos mais comuns e frequentes de ataques DDoS.
    - Usa uma variedade de técnicas de análise para detectar tráfego malicioso em tempo real e mitiga-lo automaticamente
  - **Advanced**:
    - É um serviço pago que fornece diagnósticos detalhados de ataques e a capacidade de detectar e mitigar ataques DDoS sofisticados.
    - Se integra a outros serviços, como *Amazon CloudFront*, *Amazon Route 53* e *Elastic Load Balancing*.
    - Você pode integrar o *AWS Shield* ao *AWS WAF* escrevendo regras personalizadas para mitigar ataques DDoS complexos.

## AWS WAF

- É um firewall de aplicativo web que permite monitorar solicitações de rede que chegam aos seus aplicativos web.
- Funciona em conjunto com o *Amazon CloudFront* e um *Application Load Balancer*.
- Funciona de maneira semelhante ao *Network access control lists (ACL)* para bloquear ou permitir o tráfego.
  - No entanto, ele faz isso usando uma **Web access control list (ACL)**  para proteger seus recursos da AWS.
- Exemplo aplicação:
  - Suponha que seu aplicativo esteja recebendo solicitações de rede maliciosas de vários endereços IP. Você deseja impedir que essas solicitações continuem acessando seu aplicativo, mas também deseja garantir que usuários legítimos ainda possam acessá-lo. Você configura a *web ACL* para permitir todas as solicitações, exceto aquelas dos endereços IP que você especificou.
- Quando uma solicitação chega ao *AWS WAF*, ela verifica a lista de regras que você configurou na *web ACL*.
  - Se uma solicitação não veio de um dos endereços IP bloqueados, ele permite o acesso ao aplicativo.

## Amazon Inspector

- Ajuda a melhorar a segurança e a conformidade dos **aplicativos** executando avaliações de segurança automatizadas.
- Ele verifica os aplicativos quanto a vulnerabilidades de segurança e desvios das melhores práticas de segurança, como acesso aberto a instâncias do *Amazon EC2* e instalações de versões de software vulneráveis.
- Depois de realizar uma avaliação, fornece uma lista de descobertas de segurança.
  - A lista prioriza por nível de gravidade, incluindo uma descrição detalhada de cada problema de segurança e uma recomendação de como corrigi-lo.
  - No entanto, a AWS não garante que seguir as recomendações fornecidas resolva todos os possíveis problemas de segurança.
    - No modelo de responsabilidade compartilhada, os clientes são responsáveis ​​pela segurança de seus aplicativos, processos e ferramentas executados nos serviços da AWS.

## Amazon GuardDuty

- É um serviço que fornece detecção inteligente de ameaças para sua **infraestrutura e recursos** da AWS.
- Ele identifica ameaças monitorando continuamente a **atividade da rede** e o comportamento da conta em seu ambiente da AWS.
- Depois de habilitar o GuardDuty para sua conta da AWS, o GuardDuty começa a monitorar a atividade de sua rede e conta.
  - Você não precisa implantar ou gerenciar nenhum software de segurança adicional.
  - O GuardDuty analisa continuamente os dados de várias fontes da AWS, incluindo logs de fluxo de *VPC* e logs de *DNS*.
- Detectar alguma ameaça, você poderá analisar as descobertas detalhadas sobre elas no Console de gerenciamento da AWS.
  - As descobertas incluem etapas recomendadas para correção.
- Você pode configurar as funções do *AWS Lambda* para executar etapas de correção automaticamente em resposta às descobertas de segurança do *GuardDuty*.

## Referências

- <https://aws.amazon.com/pt/products/security/>
- <https://docs.aws.amazon.com/whitepapers/latest/aws-overview/security-services.html>
