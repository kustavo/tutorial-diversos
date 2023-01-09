# Rede e Entrega de Conteúdo

## Amazon Virtual Private Cloud (Amazon VPC)

- Estabelecer limites em torno de seus recursos da AWS.
- Permite provisionar uma seção isolada da Nuvem AWS.
- Você pode iniciar recursos em uma rede virtual que você define.
- Dentro de uma VPC, você pode organizar seus recursos em sub-redes.
- Ao criar uma VPC é criado um *Security Group* padrão (*default*).
    - Não é possível excluir um grupo de segurança *default*, mas é possível adicionar ou remover as regras.

### Emparelhamento de VPC (VPC peering)

- É uma conexão de rede entre duas VPCs que permite direcionar o tráfego entre elas usando endereços IPv4 ou IPv6 privados.
    - Podem se comunicar umas com as outras como se estivessem na mesma rede.
- É possível criar emparelhamento em VPCs de contas diferentes.
- Não é nem um gateway nem uma conexão VPN, e não depende de uma parte separada do hardware físico.
- Não há um ponto único de falha de comunicação ou um gargalo de largura de banda.
- A comunicação entre VPCs emparelhadas ocorre diretamente entre duas VPCs, não é possível através de uma VPC emparelhada intermediária.
- Não é possível criar emparelhamento entre VPCs que tenham blocos CIDR IPv4 ou IPv6 coincidentes ou sobrepostos.

### Internet gateway

- Permitir que o tráfego público da Internet acesse sua VPC.
- É uma conexão entre uma VPC e a Internet.

<figure>
    <img src="../_rede-e-entrega-de-conteudo/internet-gateway.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>Internet gateway</figcaption>
</figure>

### Virtual private gateway

- Usado para acessar recursos privados em uma VPC.
- Permite que o tráfego protegido da Internet (ex: *Virtual Private Network - VPN*) entre na VPC.
- Permite que você estabeleça uma conexão de rede privada virtual (*Virtual Private Network - VPN*) entre sua VPC e uma rede privada.
- Permite o tráfego na VPC somente se for proveniente de uma rede aprovada.

<figure>
    <img src="../_rede-e-entrega-de-conteudo/virtual-private-gateway.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>Virtual private gateway</figcaption>
</figure>

### AWS Direct Connect

- Permite estabelecer uma conexão privada dedicada entre um data center e uma VPC.
- A conexão privada fornecida pelo AWS Direct Connect ajuda a reduzir os custos de rede e aumentar a quantidade de largura de banda que pode trafegar pela rede.

<figure>
    <img src="../_rede-e-entrega-de-conteudo/aws-direct-connect.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>AWS Direct Connect</figcaption>
</figure>

## Sub-rede

- É uma seção de uma VPC na qual você pode agrupar recursos com base em necessidades operacionais ou de segurança.
- Podem ser públicas ou privadas.
    - Sub-redes públicas: contêm recursos que precisam ser acessíveis ao público, como o site de uma loja online.
    - Sub-redes privadas: contêm recursos que devem ser acessíveis apenas por meio de sua rede privada, como um banco de dado.
- Em uma VPC, as sub-redes podem se comunicar umas com as outras.
- Os pacotes de dados entram em uma VPC por meio de um gateway de internet.
- Antes que um pacote possa entrar em uma sub-rede ou sair de uma sub-rede, é verificado as permissões.
    - Essas permissões indicam quem enviou o pacote e como o pacote está tentando se comunicar com os recursos em uma sub-rede.
    - O componente VPC que verifica as permissões de pacote para sub-redes é o *Network access control lists (ACLs)*.

### Network access control lists (ACLs)

- É um firewall virtual que controla o tráfego de entrada e saída no nível da sub-rede.
- Cada conta da AWS inclui uma rede ACL por padrão que permite todo o tráfego de entrada e saída.
- Mas você pode modificá-la adicionando suas próprias regras.
- Para ACLs personalizadas, todo o tráfego de entrada e saída é negado até que você adicione regras para especificar qual tráfego permitir.
- Para todas as ACLs, se um pacote não corresponder a nenhuma das regras da lista, o pacote será negado.
- Filtragem de pacotes **sem estado** (*stateless*), ou seja, verificam as regras para os pacotes na entrada e saída da sub-rede.
- Depois que um pacote entra em uma sub-rede, ele deve ter suas permissões avaliadas para recursos na sub-rede (ex: instâncias do *Amazon EC2*).
- O componente da VPC que verifica as permissões de pacote para uma instância do *Amazon EC2* é o *Security groups*.

### Security groups

- É um firewall virtual que controla o tráfego de entrada e saída de uma instância do *Amazon EC2*.
- Por padrão, um grupo de segurança nega todo o tráfego de entrada e permite todo o tráfego de saída.
    - Permite adicionar regras personalizadas para configurar qual tráfego permitir ou negar.
    - Várias instâncias do *Amazon EC2* podem estar em um mesmo *Security group*.
- Filtragem de pacotes **com estado** (*stateful*)
    - Eles se lembram de decisões anteriores feitas para os pacotes de entrada, ou seja, permite a saída dos pacotes independente das regras de entrada do security group.

Tanto as ACLs de rede quanto os grupos de segurança permitem que você configure regras personalizadas para o tráfego em sua VPC. À medida que você continua aprendendo mais sobre segurança e rede da AWS, certifique-se de entender as diferenças entre ACLs de rede e grupos de segurança.

<figure>
    <img src="../_rede-e-entrega-de-conteudo/acl-e-security-group.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>ACLs e Security groups</figcaption>
</figure>

## Elastic Load Balancing

- Distribui automaticamente o tráfego de aplicativos de entrada em vários destinos em uma ou mais zonas de disponibilidade de uma **mesma região**, como:
    - Amazon EC2 Autoscaling, AWS Lambda, AWS Fargate, Amazon EKS, Amazon ECS, IP Address.
- Atua como um único ponto de contato para todo o tráfego da Web de entrada para seu grupo da *Amazon EC2 Auto Scaling*.
    - À medida que você adiciona ou remove instâncias do Amazon EC2, essas solicitações são roteadas primeiro para o *load balancer*.
- Embora o *Elastic Load Balancing* e o *Amazon EC2 Auto Scaling* sejam serviços separados, eles trabalham juntos.
    - *Amazon EC2 Auto Scaling* cria ou remove instâncias EC2 e *Elastic Load Balancing* redistribui o tráfego entre elas.
- Pode ser usado como:
    - Application load balancer;
    - Network load balancer;
    - Gateway load balancer;

## Amazon Route 53

- É um serviço web DNS.
- Conecta solicitações de usuários à infraestrutura em execução na AWS (como instâncias do *Amazon EC2* e balanceadores de carga).
- Pode rotear usuários para infraestrutura fora da AWS.
- Pode gerenciar os registros DNS para nomes de domínio.
    - Pode registrar novos nomes de domínio diretamente no *Route 53*.
    - Pode transferir registros DNS para nomes de domínio existentes gerenciados por outros registradores de domínio.

O exemplo a seguir descreve como o *Route 53* e o *Amazon CloudFront* trabalham juntos para entregar conteúdo aos clientes.

<figure>
    <img src="../_rede-e-entrega-de-conteudo/route-53-e-cloud-front.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>Route 53 e o Amazon CloudFront</figcaption>
</figure>

Suponha que um aplicativo esteja sendo executado em várias instâncias do *Amazon EC2*. Essas instâncias estão em um grupo de *Auto Scaling* que se conecta a um *Application Load Balancer*.

1. Um cliente solicita dados do aplicativo acessando um site.
1. O Amazon *Route 53* usa a resolução DNS para identificar o endereço IP correspondente ao site. Esta informação é enviada de volta ao cliente.
1. A solicitação do cliente é enviada para o ponto de presença mais próximo por meio do *Amazon CloudFront*.
1. O *Amazon CloudFront* se conecta ao *Application Load Balancer*, que envia o pacote de entrada para uma instância do *Amazon EC2*.

## Amazon CloudFront

- Serviço da web que acelera a distribuição do conteúdo estático e dinâmico da web para os usuários.
- Serviço de CDN.
- Distribui o conteúdo por meio de uma rede global de datacenters denominados pontos de presença.
    - Quando um usuário solicita um conteúdo que você está disponibilizando com o CloudFront, a solicitação é roteada para o ponto de presença que fornece a menor latência (atraso), assim o conteúdo é entregue com a melhor performance possível.
    - Se o conteúdo já estiver no ponto de presença com a menor latência, o CloudFront o entregará imediatamente.
    - Se o conteúdo não estiver nesse ponto de presença, o CloudFront o recuperará na fonte identificada como a fonte para a versão definitiva do conteúdo.

### Signed URLs e signed cookies

- Os signed URLs e signed cookies do CloudFront fornecem a mesma funcionalidade básica: eles permitem controlar quem pode acessar seu conteúdo.
- Use signed URLs nos seguintes casos:
    - Você quer restringir o acesso a **arquivos individuais**, por exemplo, o download de uma instalação para seu aplicativo.
    - Seus usuários estão usando um cliente (por exemplo, um cliente HTTP personalizado) incompatível com cookies.
- Use signed cookies nos seguintes casos:
    - Você quer fornecer acesso a **vários arquivos** restritos, por exemplo, todos os arquivos de um vídeo no formato HLS ou todos os arquivos da área de assinantes de um site.
    - Você **não** quer alterar seus URLs atuais.
- Se você não estiver usando URLs assinados e seus URLs (não assinados) contiverem um dos seguintes parâmetros de string de consulta, você **não poderá usar signed URLs ou signed cookies**:
    - Expires
    - Policy
    - Signature
    - Key-Pair-Id
- O CloudFront pressupõe que os URLs que contenham um desses parâmetros de query string sejam signed URLs, portanto, não analisará os signed cookies.

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>
- <https://aws.amazon.com/pt/elasticloadbalancing>
- <https://docs.aws.amazon.com/pt_br/elasticloadbalancing/index.html>
