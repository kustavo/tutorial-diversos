# Segurança, identidade e conformidade

Gerenciamento da segurança (*security*), identidade (*identity*) e conformidade (*compliance*) na AWS.

## AWS Identity and Access Management (IAM)

Gerencie com segurança o acesso a serviços e recursos.

### User Groups

Um grupo de usuários (*user groups*) é uma coleção de usuários do IAM. Use grupos para especificar permissões para uma coleção de usuários.

### Policies

- Uma política (*policie*) é um objeto na AWS que define permissões.
- Pode ser aplicada a usuários (*users*), grupos de usuários (*user groups*) e unidades organizacionais (*OUs - Organizational Units*).

### Roles

Um papel/função (*role*) no IAM é uma identidade que você pode criar com permissões específicas com credenciais válidas por curtos períodos.

## AWS Organizations

- Consolidar e gerenciar várias contas da AWS em um local central.
- Quando você cria uma organização, é criado uma raiz (*root*), que é o contêiner pai para todas as contas na organização.
- Controlar centralmente as permissões para as contas em sua organização usando políticas de controle de serviço (*SCPs - Service Control Policies*).
  - As SCPs permitem que você coloque restrições nos serviços, recursos e ações de API individuais da AWS que usuários e funções em cada conta podem acessar.

### Organizational Units

- No *AWS Organizations*, você pode agrupar contas em unidades organizacionais (*OUs - Organizational Units*) para facilitar o gerenciamento de contas com requisitos comerciais ou de segurança semelhantes.
- Quando você aplica uma política a uma OU, todas as contas na OU herdam automaticamente as permissões especificadas na política.

## Referências

- <https://aws.amazon.com/pt/products/security/>
- <https://docs.aws.amazon.com/whitepapers/latest/aws-overview/security-services.html>
