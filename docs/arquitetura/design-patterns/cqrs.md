# CQRS

CQRS (Command and Query Responsibility Segregation) define a segregação de responsabilidades entre comandos e consultas. CQRS é um padrão proposto pelo mesmo propositor do Event Sourcing, *Greg Youg*, para resolver um problema bastante específico: facilitar a criação de consultas customizadas e otimizadas em sistemas focados em domínio.

Muitas vezes nosso modelo de domínio possui diversas propriedades, algumas delas coleções ou um grafo de objetos, e precisamos somente demanda de um subconjunto dessas propriedades. Em casos como este, sem o emprego de CQRS, há um custo de recuperação de todo o modelo de domínio junto ao ORM, mais o custo da construção do modelo de leitura (Read Model, e.g. DTOs) a ser retornado a partir das propriedades desejadas daquele modelo para retorno ao cliente. É um custo computacional considerável, ainda mais em cenários de demanda crescente ou de estresse eventual (como o famoso exemplo da Black Friday em e-commerces).

Pensando em *Event Sourcing*, imagine o custo de recuperar todos os eventos relacionados ao nosso modelo e carregá-los em memória, e então usar este modelo para gerar um modelo de leitura que vai ser entregue ao cliente. Agora imagine, ainda seguindo estes exemplos, que precisamos retornar uma composição entre duas entidades distintas para atender ao cliente.

A proposta do CQRS, na prática, é extrair do domínio a responsabilidade de prover dados ao cliente (por isso o *Responsibility Segregation*), atribuindo-a a um agente especializado, capaz de realizar as consultas demandadas pelo cliente.

A ideia básica é segregar as responsabilidades da aplicação em:

Command – Operações que modificam o estado dos dados na aplicação.
Query – Operações que recuperam informações dos dados na aplicação.

!!! note "Notas"
    Um *Command* também pode realizar consultas necessárias para validar uma operação. Entretanto essa consulta é realizada na fonte de dados de gravação, pois ela mantém o estado real dos dados.

## O que não é CQRS

A premissa para o CQRS é que um dado sistema contenha um modelo de domínio rico, que é exatamente o cenário que temos quando trabalhamos com *Event Sourcing*.

Essa é uma observação importante porque, não raro, encontram-se tentativas de implementação de CQRS com modelos anêmicos. E é preciso deixar claro que a separação entre comandos e consultas em sistemas que se utilizam de modelos anêmicos não caracteriza CQRS, uma vez que não há a segregação de responsabilidades prevista no padrão. Ou seja, não existe um modelo de domínio responsável pelas regras de negócio, e um modelo de leitura responsável por prover dados ao cliente. O mesmo modelo é compartilhado nos dois cenários, o que torna uma tentativa de implementação do padrão apenas uma separação entre o objeto de acesso a dados responsável pela persistência, e o objeto de acesso a dados responsável pelas consultas, o que na prática, não traz à aplicação o ganho pretendido pelo padrão, já que não existe o problema que ele se propõe a resolver.

Essa separação também é comumente interpretada como um exemplo de CQS (Command and Query Separation), um padrão proposto por *Bertrand Meyer* em seu livro "Object-Oriented Software Construction", mas entendemos essa interpretação como uma extrapolação da ideia do padrão, tendo em vista que *Meyer* o propôs considerando operações em um mesmo objeto, e não objetos independentes que ajam sobre um mesmo dado.

!!! note "CQS"

    CQS (Command Query Separation) e CQRS (Command Query Responsibility Segregation) estão muito relacionados. O CQRS originou-se do CQS. Você pode pensar no CQS como estando no nível da classe ou do componente, enquanto o CQRS está mais no nível do contexto limitado (*bounded context*). Podemos imaginar o CQS como estando no nível micro e CQRS no nível macro.

    O CQS prescreve métodos separados para consultar ou gravar em um modelo: a consulta não muda o estado, enquanto o comando muda o estado, mas não tem um valor de retorno.

    O CQRS prescreve uma abordagem semelhante, exceto que é voltado ao percurso pelo seu sistema. Uma solicitação de consulta segue um caminho separado de um comando. A consulta retorna dados sem alterar o sistema subjacente; o comando altera o sistema, mas não retorna dados. O CQRS trata de modelos separados para gravações e leituras.

!!! warning "Importante"
    É muito comum o emprego de mecanismos de notificação (como o *MediatR*) para comunicar ao modelo de domínio a necessidade de executar um comando, ou ao objeto de acesso a dados (Data Access Object, DAO) para executar uma consulta dentro de um mesmo processo. Entretanto entendemos essa solução como sub-ótima pois, dentro do mesmo processo, o modelo de domínio pode ser explicitamente invocado para realizar suas operações. Ou seja, há desperdício de recursos computacionais e aumento da complexidade técnica ao se introduzir mecanismos de notificação, o mesmo vale para o DAO.

## Entendendo melhor o CQRS

A ideia básica é segregar as responsabilidades da aplicação em:

- Command – Operações que modificam o estado dos dados na aplicação.
- Query – Operações que recuperam informações dos dados na aplicação.

Numa arquitetura de camadas poderíamos pensar em separar as responsabilidades em CommandStack e QueryStack.

<figure>
    <img src="../_cqrs/cqrs-basico.png" title="Fonte: https://herbertograca.com/2017/10/19/from-cqs-to-cqrs"/>
    <figcaption>CQRS básico</figcaption>
</figure>

### QueryStack

Após o CQS, o lado da consulta retornará apenas dados, não os alterando de forma alguma. Uma vez que não pretendemos executar um processo de negócios nesses dados, não precisamos de objetos de negócios (ou seja, entidades), então não precisamos de um ORM para preencher entidades para nós, e não precisamos obter todos os dados necessários para preencher uma entidade. Precisamos apenas consultar os dados brutos para mostrar ao usuário e exatamente os dados que precisamos no modelo mostrado ao usuário.

A *QueryStack* é muito mais simples que a CommandStack, afinal a responsabilidade dela é recuperar dados praticamente prontos para exibição. Podemos entender que a *QueryStack* é uma camada síncrona que recupera os dados de um banco de leitura preferencialmente desnormalizado.

Este banco desnormalizado pode ser um *NoSQL* como *MongoDB*, *Redis*, *RavenDB* etc. O conceito de desnormalizado pode ser aplicado com "*one table per view*" ou seja uma consulta *flat* que retorna todos os dados necessários para ser exibido em uma view (tela) específica.

O uso de consultas *flats* em um banco desnormalizado evita a necessidade de joins, tornando as consultas muito mais rápidas. É preciso aceitar que haverá a duplicidade de dados para poder atender este modelo.

Existe um ganho de desempenho pois ao consultar dados, não precisamos passar pelas camadas de lógica de negócios para obtê-los, apenas fazemos e obtemos exatamente o que precisamos.

Por causa dessa separação, outra otimização possível é separar completamente o armazenamento de dados em dois armazenamentos de dados separados: um otimizado para gravações e outro otimizado para leituras. Por exemplo, se estivermos usando um RDBMS (*Relational Database Management System*):

- As leituras não precisam de nenhuma validação de integridade de dados, elas não precisam de restrições de chave estrangeira porque a validação de integridade de dados é feita durante a gravação no armazenamento de dados. Portanto, podemos remover as restrições de integridade de dados do banco de dados de leitura.

- Também podemos usar visualizações de banco de dados com exatamente os dados que precisamos em cada modelo, tornando a consulta trivial e, portanto, mais rápida (embora precisemos manter a visualização em sincronia com as alterações do modelo, aumentando a complexidade do sistema).

Neste ponto, se temos uma visão de banco de dados especializada para cada modelo, o que torna a consulta trivial, podemos usar bancos para leituras, como Mongo DB ou mesmo Redis, que são mais rápidos.

A própria consulta pode ser feita usando um objeto *Query* que retorna um array de dados para ser usado no template, ou podemos usar algo mais sofisticado como um *Query Bus* que, por exemplo, recebe um nome de template, usa um objeto de consulta para consultar os dados e retorna uma instância do *ViewModel* que o modelo precisa.

Essa abordagem pode resolver vários problemas identificados por *Greg Young*:

- Um grande número de métodos de leitura em repositórios frequentemente também inclui paginação ou classificação de informações;
- Getters expondo o estado interno de objetos de domínio para construir DTOs;
- Uso de caminhos de pré-busca nos casos de uso de leitura, pois eles exigem que mais dados sejam carregados pelo ORM;
- O carregamento de múltiplas raízes agregadas para construir um DTO causa uma consulta não ideal ao modelo de dados. Por outro lado, os limites agregados podem ser confundidos por causa das operações de construção do DTO;
- O maior problema, porém, é que a otimização de consultas é extremamente difícil: como as consultas operam em um modelo de objeto e, em seguida, são convertidas em um modelo de dados, provavelmente por um ORM, pode ser muito difícil otimizar essas consultas.

!!! note "Notas"
    A melhor forma de implementar uma *query* é fazer com que ela retorne somente os dados necessários para a camada de apresentação. Portanto não é uma boa práticas utilizar as entidades como tipo de retorno. Prefira a utilização de DTOs.

### CommandStack

O *CommandStack* por sua vez é potencialmente assíncrono. Com o *CommandStack*, mudamos o aplicativo de um design centrado em dados para um design comportamental, que vem em linha com o Domain Driven Design. É nesta separação que estão as entidades, regras de negócio, processos e etc. Numa abordagem DDD podemos entender que o Domínio pertence a esta parte da aplicação.

O *CommandStack* segue uma abordagem *behavior-centric* onde toda intenção de negócio é inicialmente disparada pela UI como um caso de uso. Utilizamos o conceito de *Commands* para representar uma intenção de negócio. Os *Commands* são declarados de forma imperativa (ex. *FinalizarCompraCommand*) e são disparados assincronamente no formato de eventos, sendo interpretados pelos *CommandHandlers* e retornam um evento de sucesso ou falha.

Toda vez que um *Command* é disparado e altera o estado de uma entidade no banco de gravação um processo tem que ser disparado para os agentes que irão atualizar os dados necessários no banco de leitura.

Ao remover as operações de leitura do código que processa os comandos do Domínio, os problemas identificados por *Greg Young* simplesmente desaparecem:

- Objetos de domínio repentinamente não precisam mais expor o estado interno;
- Os repositórios têm poucos ou nenhum método de consulta além de *GetById*;
- Um foco mais comportamental pode ser obtido nos limites agregados.

Os relacionamentos "um para muitos" e "muitos para muitos" entre as entidades podem ter um impacto severo no desempenho do ORM. A boa notícia é que raramente precisamos dessas relações ao processar comandos, elas são usadas principalmente para consultas e acabamos de mover a consulta para longe do processamento de comandos para que possamos remover essas relações de entidade.

**Eventos de processo de negócios:**

Depois que um comando é processado, e se foi processado com sucesso, o manipulador dispara um evento notificando o restante do aplicativo sobre o que aconteceu. Os eventos devem ser nomeados igual ao comando que os disparou, exceto que, como é a regra com eventos, devem estar nomeados no pretérito.

## Sincronização

Existem algumas estratégias para manter as bases de leitura e gravação sincronizadas é necessário escolher a que melhor atende ao seu cenário:

`Atualização automática`
:   Toda alteração de estado de um dado no banco de gravação dispara um processo síncrono para atualização no banco de leitura.

`Atualização eventual`
:   Toda alteração de estado de um dado no banco de gravação dispara um processo assíncrono para atualização no banco de leitura oferecendo uma consistência eventual dos dados. É uma das estratégias mais utilizadas, pois parte do princípio que todo dado exibido já pode estar desatualizado, portanto não é necessário impor um processo síncrono de atualização.

`Atualização controlada`
:   Um processo periódico e agendado é disparado para sincronizar as bases.

`Atualização sob demanda`
:   Cada consulta verifica a consistência da base de leitura em comparação com a de gravação e força uma atualização caso esteja desatualizada.

## Enfileiramento

Muitas implementações de CQRS podem exigir um "Bus" para processamento de *Commands* e *Events*. Nesse caso teremos uma implementação conforme a seguinte ilustração:

<figure>
    <img src="../_cqrs/cqrs-bus.jpg" title="Fonte: https://www.eduardopires.net.br/2016/07/cqrs-o-que-e-onde-aplicar"/>
    <figcaption>CQRS com enfileiramentos</figcaption>
</figure>

Existem diversas opções de Bus para .NET

- Microsoft Azure Service Bus Queue
- NServiceBus
- Rebus (Free)
- MassTransit (Free)

## Implementando o CQRS com Event Sourcing

Para entregarmos ao cliente um modelo de leitura que o atenda, primeiro precisamos criá-lo. Sempre que nosso modelo for persistido na *Event Store*, um agente precisará ser acionado para criar ou atualizar o modelo de leitura correspondente, e este agente é geralmente chamado de *Projector*. Ele recebe este nome porque, muitas vezes, existe a necessidade de se transformar dados a fim de apresentá-los ao cliente da forma esperada, criando uma projeção do nosso modelo de domínio na forma de nosso modelo de leitura. Uma forma bastante conhecida de projeção é o padrão *Materialized View*.

!!! note ""
    É comum que se encontre implementações onde eventos de domínio sejam lançados quando há atualização no modelo de domínio, para que um *handler* realize a sincronização do modelo de leitura. Esta pode não ser um boa opção, pois detalhes de implementação de infraestrutura, como a persistência dos modelos de leitura, não devem ser confundidos com o processo de negócio. Portanto, a melhor prática é separar os dois procedimentos para aumentar a clareza de seu propósito no código.

Uma vez criado nosso modelo de leitura, é necessário armazená-lo e criar um agente para recuperá-lo quando demandado.

Existem algumas opções para essa persistência, como uma tabela no mesmo banco relacional em que nosso modelo de domínio é armazenado, uma coleção em um banco NoSql separado do banco relacional onde o modelo de domínio é armazenado, um sistema de cache em memória (como *Memcached* ou *Redis*) etc. Esta é uma escolha que está sujeita a algumas variáveis, como o custo da contratação da solução de armazenamento, o quão ótima ela é para leitura, o quão familiarizado o time de Ops está com essa solução etc.

Uma solução muito comum em cenários onde são empregados bancos de dados relacionais é o uso de uma tabela para cada modelo de leitura. Geralmente combina-se Micro-ORMs como o *Dapper* com *Stored Procedures* como forma de otimizar as consultas e a posterior construção do modelo de leitura. Também não é uma regra, mas entendemos como uma ótima sugestão pensando em simplicidade e desempenho.

<figure>
    <img src="../_cqrs/cqrs-event-sourcing.jpg" title="Fonte: https://rabbitstack.github.io/cqrs/introduction-to-cqrs"/>
    <figcaption>CQRS com Event Sourcing</figcaption>
</figure>

`Commands`
:   Comandos são os objetos que encapsulam tanto a intenção do usuário quanto as informações necessárias para realizar a operação. Por exemplo, *CreateNewUserCommand* conteria uma variedade de atributos como nome de usuário, endereço, idade, etc. Os comandos são enviados para o barramento de comando e posteriormente despachados para seu manipulador de comando correspondente. A mudança de estado no sistema é iniciada executando o comando.

`Domain model`
:   O modelo de domínio representa o coração do sistema. Como o CQRS é baseado em seu predecessor DDD (*Domain Driven Design*), a abordagem de design principal reside no modelo de domínio rico. O que isto significa? No design tradicional, os objetos de domínio geralmente desempenham o papel de entidades que apenas mantêm o estado do sistema e não possuem qualquer tipo de comportamento, sendo, portanto, frequentemente chamados de modelo de domínio anêmico. Isso também tende a criar ambiguidade entre os DTO (*Data Transfer Object*) e os objetos do modelo, fazendo com que o modelo de domínio acabe tendo informações que devem ser renderizadas na visualização e, portanto, criar acoplamento. A camada de serviço dedicada altera o estado do sistema. O CQRS promove um modelo de domínio estritamente baseado em comportamento.

`Repositories`
:   Repositórios fornecem acesso a objetos de domínio e permitem isolar o modelo de domínio do mecanismo de persistência. Os repositórios precisam apenas ser capazes de recuperar o agregado (objeto de domínio) através de seu identificador único, enquanto qualquer outro tipo de consulta é executado no modelo de leitura.

`Events`
:   Os eventos são a fonte de qualquer mudança de estado no aplicativo. Conforme mencionado acima, a execução de comandos no agregado inicia a mudança de estado no sistema, que por sua vez produzirá uma série de eventos de domínio. Não precisamos persistir objetos de domínio, mas sim os eventos de domínio gerados. Com isso, somos capazes de reconstruir o objeto de domínio ao seu último estado, apenas aplicando o fluxo de eventos nele. Esse padrão é conhecido como **event sourcing**. Os eventos são enviados para o barramento de eventos e despachados para qualquer componente interessado em consumi-lo.

`Event store`
:   O armazenamento de eventos fornece um armazenamento de apoio para eventos de domínio. Esses são geralmente bancos de dados relacionais e bancos de dados NoSQL.

`Queries`
:   As consultas são executadas em uma camada de dados simples de somente leitura. As informações necessárias para serem renderizadas na visualização são refletidas no objeto que contém os resultados da consulta. Podemos dizer que o objeto é feito sob medida para o que a visão precisa representar.

## Vantagens

- Todos comandos são assíncronos e processados em fila, assim diminui-se o tempo de espera.
- Os processos que envolvem regras de negócio existem apenas no sentido da inclusão ou alteração do estado das informações.
- É possível escalar separadamente os processos da *CommandStack* e da *QueryStack*.
- Permite que o aplicativo seja distribuído em várias máquinas físicas ou virtuais (escalabilidade horizontal).
- Alta disponibilidade no nível do aplicativo. Se um componente falhar, o resto do sistema ainda pode funcionar.
- Auditar e rastrear as ações do usuário prontas para usar. Esse tipo de auditoria não é comparável a nenhum log de infraestrutura, pois os eventos de domínio agregam valor adicional ao negócio. É fácil extrair e ingerir os eventos de domínio em plataformas de aprendizado de máquina ou mecanismos de correlação, por exemplo, para prever ações do usuário, detectar anomalias, etc. Tendo o rastreamento de tudo o que acontece no aplicativo, temos uma única fonte de verdade. Também é mais fácil reproduzir as falhas de software.
- Em vez de vincular objetos de domínio com componentes de UI, temos um DTO simples que reflete com precisão o que queremos representar na visualização e pode ser recuperado diretamente do banco de dados. Assim, podemos obter todas as informações necessárias em uma única solicitação à fonte de dados.
- CQRS nos ajuda a escrever um modelo de domínio expressivo. Também coloca os modelos em uma "dieta", já que os modelos precisam apenas ter os atributos relevantes para a decisão do negócio.
- Quando o comando está prestes a ser processado, o repositório obterá o fluxo de eventos relacionados ao objeto do armazenamento de eventos. O estado do objeto é reconstruído a partir do fluxo de eventos. Assim, obtemos o objeto em seu estado original e não será necessário fornecer persistência ao modelo de domínio.
- As consultas na *QueryStack* são feitas de forma separada e independente e não dependem do processamento da *CommandStack*.
- Modelos de dados separados. Estes permanecem consistentes, sincronizados e desacoplados graças aos eventos de domínio. Para o modelo de leitura pode-se utilizar qualquer tecnologia, desde sistemas JDBC, ORM até soluções NoSQL, pois o único objetivo é preencher a visualização com dados o mais rápido possível. Podemos ter bancos de dados desnormalizados para otimizar as leituras e evitar consultas complexas com muitas uniões.

## Desvantagens

Você deve ter em mente as seguintes considerações:

- Use CQRS quando o modelo de domínio for complexo. Um modelo simples não vai se beneficiar desse padrão.
- A curva de aprendizado é relativamente alta e exige uma mudança de ponto de vista em relação ao design tradicional.
- Requisitos de infraestrutura mais elevados, uma vez que temos dois modelos (o modelo de leitura e o modelo de gravação).

## Conclusão

Ao usar o CQRS, podemos separar completamente o modelo de leitura do modelo de gravação, o que nos permite otimizar as operações de leitura e gravação. Isso aumenta o desempenho, mas também a clareza e a simplicidade da base de código, a capacidade da base de código de refletir o domínio e a capacidade de manutenção da base de código.

Isto é tudo sobre encapsulamento, baixo acoplamento, alta coesão e o Princípio de Responsabilidade Única. No entanto, é bom ter em mente que embora o CQRS forneça um estilo de design e várias soluções técnicas que podem tornar um aplicativo muito robusto, isso não significa que todos os aplicativos devam ser construídos desta forma: Devemos usar o que precisamos, quando nos precisamos.

## Referências

- <https://www.youtube.com/watch?v=8yV8UicF8GI>
- <https://dev.to/wsantosdev/event-sourcing-parte-5-cqrs-2m6o>
- <https://rabbitstack.github.io/cqrs/introduction-to-cqrs>
- <https://herbertograca.com/2017/10/19/from-cqs-to-cqrs>
- <https://www.eduardopires.net.br/2016/07/cqrs-o-que-e-onde-aplicar/>
