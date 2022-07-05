# Redes e entrega de conteúdo

## Elastic Load Balancing

- Distribui automaticamente o tráfego de aplicativos de entrada em vários recursos, sendo alguns deles:
  - Amazon EC2 Autoscaling, AWS Lambda, AWS Fargate, Amazon EKS, Amazon ECS, IP Address.
- Atua como um único ponto de contato para todo o tráfego da Web de entrada para seu grupo da *Amazon EC2 Auto Scaling*.
  - À medida que você adiciona ou remove instâncias do Amazon EC2, essas solicitações são roteadas primeiro para o *load balancer*.
- Embora o *Elastic Load Balancing* e o *Amazon EC2 Auto Scaling* sejam serviços separados, eles trabalham juntos.
  - *Amazon EC2 Auto Scaling* cria ou remove instâncias EC2 e *Elastic Load Balancing* redistribui o tráfego entre elas.
- Pode ser usado como:
  - Application load balancer;
  - Network load balancer;
  - Gateway load balancer;

## Referências

- <https://aws.amazon.com/pt/elasticloadbalancing>
- <https://docs.aws.amazon.com/pt_br/elasticloadbalancing/index.html>