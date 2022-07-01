# Computação

## Elastic Compute Cloud (Amazon EC2)

- Ambientes de computação virtual, conhecidos como instâncias.
- Disponibiliza modelos pré-configurados para suas instâncias, conhecidos como Imagens de máquina da Amazon (*AMIs - Amazon Machine Image*).

### Tipos de instâncias

Os tipos de instância do Amazon EC2 são otimizados para diferentes tarefas.

- **General purpose**:
  - Instâncias de uso geral que fornecem um equilíbrio de recursos de computação, memória e rede.
  - Você pode usá-los para uma variedade de cargas de trabalho, como:
    - Servidores de aplicativos;
    - Servidores de jogos;
    - Servidores back-end para aplicativos corporativos;
    - Bancos de dados de pequeno e médio porte.
- **Compute optimized**:
  - Instâncias otimizadas para computação são ideais para aplicativos vinculados a computação que se beneficiam de processadores de alto desempenho.
  - Você pode usá-los para uma variedade de cargas de trabalho, como:
    - Servidores Web de alto desempenho;
    - Servidores de aplicativos de computação intensiva;
    - Servidores de jogos dedicados;
    - Computação para cargas de trabalho de processamento em lote;
- **Memory optimized**:
  - Instâncias otimizadas para memória são projetadas para fornecer desempenho rápido para cargas de trabalho que processam grandes conjuntos de dados na memória.
  - Você pode usá-los para uma variedade de cargas de trabalho, como:
    - Processamento em tempo real de uma grande quantidade de dados não estruturados.
- **Accelerated computing**:
  - Instâncias de computação acelerada usam aceleradores de hardware, ou coprocessadores, para executar algumas funções com mais eficiência do que é possível em softwares executados em CPUs.
  - Você pode usá-los para uma variedade de cargas de trabalho, como:
    - Cálculos de números de ponto flutuante;
    - Processamento de gráficos;
    - Correspondência de padrões de dados.
    - Aplicativos gráficos;
    - Streaming de jogos.
- **Storage optimized**:
  - Instâncias otimizadas para armazenamento são projetadas para cargas de trabalho que exigem alto acesso sequencial de leitura e gravação a grandes conjuntos de dados no armazenamento local.
  - Você pode usá-los para uma variedade de cargas de trabalho, como:
    - Sistemas de arquivos distribuídos;
    - Aplicativos de armazenamento de dados;
    - Sistemas de processamento de transações online de alta frequência (OLTP).
    - Aplicativo que tenha um alto requisito de entrada/saída por segundo (IOPS).

### Preços

Você paga apenas pelo tempo de computação usado.

O Amazon EC2 oferece uma variedade de opções de preços para diferentes casos de uso:

- **On-Demand**:
  - As instâncias sob demanda são ideais para cargas de trabalho irregulares de curto prazo que não podem ser interrompidas.
  - Não se aplicam custos iniciais ou contratos mínimos.
  - As instâncias são executadas continuamente até que você as interrompa e você paga apenas pelo tempo de computação usado.
  - As instâncias sob demanda não são recomendadas para cargas de trabalho que duram um ano ou mais, devido o custo.
- **Amazon EC2 Savings Plans**:
  - Os planos de economia permitem que você reduza seus custos de computação comprometendo-se pagar por um plano de **1 ou 3 anos**.
  - Economias de até 66% em relação aos custos *On-Demand*.
- **Reserved Instances**:
  - As instâncias reservadas são um desconto de cobrança aplicado ao uso de instâncias *On-Demand* em sua conta.
  - Você pode comprar:
    - **Standard Reserved e Convertible Reserved**: instâncias reservadas padrão e reservadas Conversíveis por um período de 1 ou 3 anos;
    - **Scheduled Reserved**: instâncias Reservadas agendadas por um período de 1 ano.
  - Ao final de um período de instância reservada, a instância permanece sem interrupção sendo cobradas taxas *On-Demand* até que você faça um dos seguintes:
    - Encerre a instância.
    - Compre uma nova instância reservada que corresponda aos atributos da instância (tipo de instância, região, locação e plataforma).
- **Spot Instances**:
  - As instâncias spot são ideais para cargas de trabalho com horários de início e término flexíveis ou que podem suportar interrupções.
  - Usam a capacidade de computação não utilizada do Amazon EC2 e oferecem economia de custos de até 90% dos preços sob *On-Demand*.
  - Se a capacidade do Amazon EC2 estiver indisponível, o lançamento aguardará em segundo plano até a disponibilidade.
  - Numa instância spot em execução, se a capacidade não estiver mais disponível ou a demanda por instâncias spot aumentar, sua instância poderá ser interrompida.
- **Dedicated Hosts**:
  - Hosts dedicados são servidores físicos com capacidade de instância do Amazon EC2 totalmente dedicados ao seu uso.
  - Você pode usar suas licenças de software existentes por soquete, por núcleo ou por VM para ajudar a manter a conformidade com as licenças.
  - Opção mais cara.

## Amazon EC2 Auto Scaling

- Escalona automaticamente instâncias conforme necessário.
- Você pode usar duas abordagens:
  - Dimensionamento dinâmico (*Dynamic scaling*): responde à demanda em constante mudança.
  - Dimensionamento preditivo (*Predictive scaling*): agenda automaticamente o número certo de instâncias do Amazon EC2 com base na demanda prevista.
- Ao configurar o tamanho do *Auto Scaling group*, você pode definir o número mínimo, desejado e máximo de instâncias do Amazon EC2.
  - Se a quantidade desejada não for definida, será utilizada a quantidade mínima.

## Referências

- <https://aws.amazon.com/pt/ec2>
