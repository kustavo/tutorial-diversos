# Segurança, identidade e conformidade

Segurança (*security*), identidade (*identity*) e conformidade (*compliance*) na AWS.

## Serviço: AWS Identity and Access Management (IAM)

Gerencie com segurança o acesso a serviços e recursos.

### User groups

Um grupo de usuários (*user groups*) é uma coleção de usuários do IAM. Use grupos para especificar permissões para uma coleção de usuários.

### Policies

- Uma política (*policie*) é um objeto na AWS que define permissões.
- Pode ser aplicada a usuários (*users*) ou a grupos de usuários (*user groups*).

### Roles

Um papel/função (*role*) no IAM é uma identidade que você pode criar com permissões específicas com credenciais válidas por curtos períodos.

## AWS Organizations

Você pode usar o AWS Organizations para consolidar e gerenciar várias contas da AWS em um local central.

Quando você cria uma organização, o AWS Organizations cria automaticamente uma raiz (*root*), que é o contêiner pai para todas as contas em sua organização.

No AWS Organizations, você pode controlar centralmente as permissões para as contas em sua organização usando políticas de controle de serviço (*SCPs - Service Control Policies*). As SCPs permitem que você coloque restrições nos serviços, recursos e ações de API individuais da AWS que usuários e funções em cada conta podem acessar.

### Organizational units

No AWS Organizations, você pode agrupar contas em unidades organizacionais (*OUs - Organizational Units*) para facilitar o gerenciamento de contas com requisitos comerciais ou de segurança semelhantes. Quando você aplica uma política a uma OU, todas as contas na OU herdam automaticamente as permissões especificadas na política.

Ao organizar contas separadas em OUs, você pode isolar mais facilmente cargas de trabalho ou aplicativos que tenham requisitos de segurança específicos. Por exemplo, se sua empresa tiver contas que podem acessar apenas os serviços da AWS que atendem a determinados requisitos regulamentares, você poderá colocar essas contas em uma OU. Em seguida, você pode anexar uma política à OU que bloqueia o acesso a todos os outros serviços da AWS que não atendem aos requisitos regulamentares.

## Referências

- <https://aws.amazon.com/pt/products/security/>
- <https://docs.aws.amazon.com/whitepapers/latest/aws-overview/security-services.html>
