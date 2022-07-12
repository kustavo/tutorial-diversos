# Rede e Entrega de Conteúdo

## Amazon Virtual Private Cloud (Amazon VPC)

- Estabelecer limites em torno de seus recursos da AWS.
- Permite provisionar uma seção isolada da Nuvem AWS.
- Você pode iniciar recursos em uma rede virtual que você define.
- Dentro de uma VPC, você pode organizar seus recursos em sub-redes.

### Internet gateway

- Permitir que o tráfego público da Internet acesse sua VPC.
- É uma conexão entre uma VPC e a Internet.

<figure>
    <img src="../_aws/internet-gateway.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>Internet gateway</figcaption>
</figure>

### Virtual private gateway

- Uasdo para acessar recursos privados em uma VPC.
- Permite que o tráfego protegido da Internet (ex: Virtual Private Network - VPN) entre na VPC.
- Permite que você estabeleça uma conexão de rede privada virtual (Virtual Private Network - VPN) entre sua VPC e uma rede privada.
- Permite o tráfego na VPC somente se for proveniente de uma rede aprovada.

<figure>
    <img src="../_aws/virtual-private-gateway.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>Virtual private gateway</figcaption>
</figure>

### AWS Direct Connect

- Permite estabelecer uma conexão privada dedicada entre um data center e uma VPC.
- A conexão privada fornecida pelo AWS Direct Connect ajuda a reduzir os custos de rede e aumentar a quantidade de largura de banda que pode trafegar pela rede.

<figure>
    <img src="../_aws/aws-direct-connect.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
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
  - O componente VPC que verifica as permissões de pacote para sub-redes é o *Network access control lists* (ACLs).

### Network access control lists (ACLs)

- É um firewall virtual que controla o tráfego de entrada e saída no nível da sub-rede.
- Cada conta da AWS inclui uma rede ACL por padrão que permite todo o tráfego de entrada e saída.
  - Mas você pode modificá-la adicionando suas próprias regras.
  - Para ACLs personalizadas, todo o tráfego de entrada e saída é negado até que você adicione regras para especificar qual tráfego permitir.
  - Para todas as ACLs, se um pacote não corresponder a nenhuma das regras da lista, o pacote será negado.
  - Filtragem de pacotes **sem estado** (*stateless*), ou seja, verificam as regras para os pacotes na entrada e saída da sub-rede.
  - Depois que um pacote entra em uma sub-rede, ele deve ter suas permissões avaliadas para recursos na sub-rede (ex: instâncias do Amazon EC2).
  - O componente da VPC que verifica as permissões de pacote para uma instância do Amazon EC2 é o *Security groups*.

### Security groups

- É um firewall virtual que controla o tráfego de entrada e saída de uma instância do Amazon EC2.
- Por padrão, um grupo de segurança nega todo o tráfego de entrada e permite todo o tráfego de saída.
  - Permite adicionar regras personalizadas para configurar qual tráfego permitir ou negar.
  - Várias instâncias do Amazon EC2 podem estar em um mesmo *Security group*.
- Filtragem de pacotes **com estado** (*stateful*)
  - Eles se lembram de decisões anteriores feitas para os pacotes de entrada, ou seja, permite a saída dos pacotes independente das regras de entrada do security group.

Tanto as ACLs de rede quanto os grupos de segurança permitem que você configure regras personalizadas para o tráfego em sua VPC. À medida que você continua aprendendo mais sobre segurança e rede da AWS, certifique-se de entender as diferenças entre ACLs de rede e grupos de segurança.

<figure>
    <img src="../_aws/acl-e-security-group.png" width="100%" title="Fonte: https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules"/>
    <figcaption>ACLs e Security groups</figcaption>
</figure>

## Referências

- <https://explore.skillbuilder.aws/learn/course/134/play/31418/aws-cloud-practitioner-essentials-all-modules>
