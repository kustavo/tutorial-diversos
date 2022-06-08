# Docker

## Introdução

A conteinerização é o processo de distribuição e implantação de aplicativos de uma forma portátil e previsível. Ele faz isso empacotando componentes e suas dependências em um ambiente de processos padronizado, isolado e leve chamado contêiner.

A tecnologia Docker usa o kernel do Linux e recursos do kernel como *Cgroups* e *Namespaces* para segregar processos. Assim, eles podem ser executados de maneira independente. O objetivo dos containers é criar essa independência: a habilidade de executar diversos processos e aplicações separadamente para utilizar melhor a infraestrutura e, ao mesmo tempo, manter a segurança que você teria em sistemas separados.

As ferramentas de container, incluindo o Docker, fornecem um modelo de implantação com base em **imagem**. Isso facilita o compartilhamento de uma aplicação ou conjunto de serviços, incluindo todas as dependências deles em vários ambientes. O Docker também automatiza a implantação da aplicação (ou de conjuntos de processos que constituem uma aplicação) dentro desse ambiente de container.

Essas ferramentas baseadas nos containers Linux (o que faz com que o Docker seja exclusivo e fácil de usar) oferecem aos usuários acesso sem precedentes a aplicações, além da habilidade de implantar com rapidez e de ter total controle sobre as versões e distribuição.

### Imagem

Pense em imagens como um template compostas por um sistema de camadas que ficam uma sobre as outras para rodar um container, elas são a nossa base para construção de nossas aplicações.

Em uma imagem temos um sistema de inicialização chamado *bootfs*, que é muito parecido com o sistema de boot do Linux, a partir de imagens conseguimos criar nossos containers e com facilidade fazer a migração de sistema operacional ou ambiente de trabalho.

Containers são instâncias criadas à partir de imagens Docker. As imagens podem ser criadas pelo usuário ou baixadas de algum repositório como o [*Dockerhub*](https://hub.docker.com/) por exemplo.

#### Camadas e controle de versão

Cada arquivo de imagem Docker é composto por uma série de camadas. Elas são combinadas em uma única imagem. Uma nova camada é criada quando há alteração na imagem. Toda vez que um usuário especifica um comando, como executar ou copiar, uma nova camada é criada.

O Docker reutiliza essas camadas para a construção de novos containers, o que torna o processo de criação muito mais rápido. As alterações intermediárias são compartilhadas entre imagens, o que melhora ainda mais a velocidade, o tamanho e a eficiência. O controle de versões é inerente ao uso de camadas. Sempre que é realizada uma nova alteração, é gerado um *changelog* integrado, o que fornece controle total sobre as imagens do container.

<figure>
    <img src="../_docker/exemplo-camadas.png" width="100%" title="Fonte: https://www.digitalocean.com/community/tutorials/the-docker-ecosystem-an-introduction-to-common-components"/>
    <figcaption>Exemplo de camadas</figcaption>
</figure>

Na imagem acima, você pode começar a ver (em uma visão simplificada) como os contêineres se relacionam com o sistema host. Os contêineres isolam aplicações individuais e utilizam recursos do sistema operacional que foram abstraídos pelo Docker. Na visão explodida na direita, podemos ver que os contêineres podem ser construídos por "camadas", com vários contêineres compartilhando camadas subjacentes, diminuindo o uso de recursos.

### Volume

Quando um container é removido todas as suas informações são perdidas. Portanto para criar uma cópia dos dados que estão no container para a máquina hospedeira (host), o Docker pode criar um repositório de dados para os containers. Este repositório é chamado de **volume**. Dessa forma, caso o container seja removido e posteriormente recriado, podemos informar onde os dados estão armazenados.

### Funcionamento

A tecnologia Docker foi desenvolvida inicialmente com base na tecnologia *LXC*, que a maioria das pessoas associa aos containers Linux "tradicionais". No entanto, desde então, essa tecnologia tornou-se independente. O *LXC* era útil como uma virtualização leve, mas não oferecia uma boa experiência para usuários e desenvolvedores. A tecnologia Docker oferece mais do que a habilidade de executar containers: ela também facilita o processo de criação e construção de containers, o envio e o controle de versão de imagens, dentre outras coisas.

<figure>
    <img src="../_docker/traditional-linux-containers-vs-docker.png" width="100%" title="Fonte: https://www.redhat.com/en/topics/containers/what-is-docker"/>
    <figcaption>Diferença entre containers tradicional e docker </figcaption>
</figure>

Os containers Linux tradicionais usam um sistema *init* capaz de gerenciar vários processos. Isso significa que aplicações inteiras são executadas como um processo. A tecnologia Docker incentiva que as aplicações sejam segregadas em processos separados e oferece as ferramentas para fazer isso. Essa abordagem granular tem algumas vantagens e desvantagens.

#### Vantagens

`Utilização leve de recursos`
:  Em vez da virtualização de um sistema operacional inteiro, os contêineres isolam no nível de processos e utilizam o kernel do host.

`Portabilidade`
:  Todas as dependências para uma aplicação conteinerizada são empacotadas dentro do contêiner, permitindo-a executar em qualquer host Docker.

`Modularidade`
:  A abordagem do Docker para a containerização se concentra na habilidade de desativar uma parte de uma aplicação, seja para reparo ou atualização, sem interrompê-la totalmente. Além dessa abordagem baseada em microsserviços, é possível compartilhar processos entre várias aplicações da mesma maneira como na arquitetura orientada a serviço (SOA).

`Controle de versões`
:  O controle de versões é inerente ao uso de camadas. Sempre que é realizada uma nova alteração, é gerado um *changelog* integrado, o que fornece controle total sobre as imagens do container.

`Reversão`
:  Talvez a melhor vantagem da criação de camadas seja a habilidade de reverter quando necessário. Toda imagem possui camadas. Não gostou da iteração atual de uma imagem? Simples, basta reverter para a versão anterior. Esse processo é compatível com uma abordagem de desenvolvimento ágil e possibilita as práticas de integração e implantação contínuas (CI/CD) em relação às ferramentas.

`Implantação rápida`
:  Antigamente, colocar novo hardware em funcionamento, provisionado e disponível, levava dias. E as despesas e esforço necessários para mantê-lo eram onerosos. Os containers baseados em docker podem reduzir o tempo de implantação de horas para segundos. Ao criar um container para cada processo, é possível compartilhar rapidamente esses processos similares com novos aplicativos. Como não é necessário inicializar um sistema operacional para adicionar ou mover um container, o tempo de implantação é substancialmente menor. Além disso, com a velocidade de implantação, é possível criar dados e destruir os criados pelos containers sem nenhuma preocupação e com facilidade e economia.

Em resumo, a tecnologia Docker é uma abordagem mais granular, controlável e baseada em microsserviços que valoriza a eficiência.

#### Desvantagens

O Docker não fornece as mesmas funcionalidades parecidas com UNIX que os containers Linux tradicionais oferecem. Isso inclui a capacidade de usar processos como *cron* ou *syslog* dentro do container, junto à aplicação. O Docker também tem algumas limitações em questões como a limpeza de processos netos (*grandchild*) após o encerramento dos processos filhos (*child*), algo que é processado de forma natural nos containers Linux tradicionais. Essas desvantagens podem ser mitigadas ao modificar o arquivo de configuração e configurar essas funcionalidade desde o início, algo que não está imediatamente óbvio em um primeiro momento.

Além disso, há outros subsistemas e dispositivos do Linux sem espaço de nomes. Incluindo os dispositivos *SELinux*, *Cgroups* e `/dev/sd*`. Isso significa que, se um invasor adquirir controle sobre esses subsistemas, o host será comprometido. Para manter-se leve, o compartilhamento do kernel do host com os containers gera a possibilidade dessa vulnerabilidade na segurança. Isso é diferente nas máquinas virtuais, que são mais firmemente segregadas a partir do sistema host.

## Instalação

Opcional: Dependências geralmente necessárias. 

```bash
# Debian/Ubuntu
sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
```

Para obter a versão do repositório do Ubuntu:

```bash
# Debian/Ubuntu
sudo apt install docker.io
```

Para obter a versão mais recente:

```bash
# Debian/Ubuntu
# GPG key
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

# Repositório
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io
```

Ver a versão instalada:

```bash
sudo docker -v
```

## Serviço

### Ver status do serviço

```bash
sudo systemctl status docker
```

```txt
● docker.service - Docker Application Container Engine
   Loaded: loaded (/usr/lib/systemd/system/docker.service; disabled; vendor preset: disabled)
   Active: inactive (dead)
     Docs: https://docs.docker.com
```

### Iniciar serviço

```bash
sudo systemctl start docker
```

```txt
● docker.service - Docker Application Container Engine
   Loaded: loaded (/usr/lib/systemd/system/docker.service; disabled; vendor preset: disabled)
   Active: active (running) since Wed 2019-09-11 16:35:29 -03; 29s ago
     Docs: https://docs.docker.com
 Main PID: 46625 (dockerd)
    Tasks: 28
   Memory: 57.5M
   CGroup: /system.slice/docker.service
           ├─46625 /usr/bin/dockerd -H fd://
           └─46633 containerd --config /var/run/docker/containerd/containerd.toml --log-level info
```

### Habilitar serviço para iniciar após o boot

```bash
sudo systemctl enable docker
```

## Usuário

Para adicionar o usuário no grupo `docker` e realizar o acesso sem utilizar `sudo`. Execute o comando abaixo:

```bash
sudo usermod -aG docker ${USER}
# ou
sudo usermod -aG docker <nome-usuario>
```

Faça o *logout* ou execute:

```bash
su - ${USER}
```

Confira os grupos do usuário:

```bash
id -nG
```

## Comandos

Um comando docker segue o formato:

```bash
docker [option] [command] [arguments]
```

Para ver a lista de opções e comandos:

```bash
docker --help
```

Para ver mais informações de um comando:

```bash
docker <comando> --help
```

Para especificar se um comando será executado sobre um volume ou um container, podemos usar:

```bash
docker container <comando>
docker volume  <comando>
```

### Comandos para imagens

#### Verificar acesso ao Docker Hub

Verificar se você pode acessar e baixar imagens do Docker Hub:

```bash
docker run hello-world
```

!!! note "Saída"

    Unable to find image 'hello-world:latest' locally
    latest: Pulling from library/hello-world
    9bb5a5d4561a: Pull complete
    Digest: sha256:3e1764d0f546ceac4565547df2ac4907fe46f007ea229fd7ef2718514bcec35d
    Status: Downloaded newer image for hello-world:latest

    Hello from Docker!
    This message shows that your installation appears to be working correctly.

#### Procurar imagem

Procurar imagem no Docker Hub:

```bash
docker search <imagem>
```

#### Baixar imagem

Baixar imagem do Docker Hub:

```bash
docker pull <imagem>
```

#### Listar imagens

Listar todas imagens:

```bash
docker images
# ou
docker images --all
# ou
docker images -a
```

Listar imagens pendentes.

```bash
docker images -f dangling=true
```

!!! note "Notas"
    As imagens Docker consistem de várias camadas. As imagens pendentes são camadas que não têm relação com nenhuma imagem marcada. Elas não servem a um propósito e consomem espaço em disco.

#### Remover imagem

Remover imagem:

```bash
docker rmi <imagem>
```

Remover imagens pendentes:

```bash
docker images purge
```

### Comandos para containers

#### Criar container

Criar e inicia um container.

```bash
docker run <imagem>
```

##### Criar container com nome desejado

Criar e inicia um container com nome definido pelo usuário.

```bash
docker run --name <nome> <imagem>
```

##### Criar e acessar terminal do container

```bash
docker run -it <imagem>
# -i: iteratividade
# -t: link com o terminal do container
```

!!! note "Saída"
root@d9b100f2f636:/#

Onde `d9b100f2f636` é o id do container.

##### Criar container com portas mapeadas

```bash
docker run -it -p <porta-host>:<porta-container> <imagem> /bin/bash
# -p: portas
```

##### Criar container auto destrutivo

Ao usar um exit para sair do Terminal do S.O. rodando no container, o mesmo será removido.

```bash
docker run -it --rm <imagem> /bin/bash
# --rm: remover container
```

##### Criar container em segundo plano

Cria e inicia o container em segundo plano.

```bash
docker run -d -p <porta-host>:<porta-container> <imagem> <comando>
# -d: executar em segundo plano
```

##### Criar container com volume

Cria e inicia o container com volume mapeado. Se o volume não existe, ele será criado automaticamente.

```bash
docker run -d --name <nome> -v <volume>:<path-container> <imagem>
# -v: volume
```

#### Status do container

```bash
docker stats <id-ou-nome>
```

#### Informações do container

```bash
docker inspect <id-ou-nome>
```

[Veja mais](<https://docs.docker.com/engine/reference/commandline/inspect/>)

#### Logs do container

```bash
sudo docker logs -f <id-ou-nome>
```

#### Acessar container

Acessar terminal de um container ativo. O comando `exec` executa o comando passado, portando poderia executar qualquer comando dentro do container além de `bash`.

```bash
docker exec -it <id-container> bash
# ou
docker exec -it <id-container> /bin/bash
# ou
docker exec -it <id-container> /bin/sh
```

#### Listar containers

Listar somente containers ativos:

```bash
docker ps
```

Listar todos containers ativos e inativos:

```bash
docker ps -a
# ou
docker ps --all
```

Listar último container criado:

```bash
docker ps -l
```

#### Iniciar container parado

Para iniciar um container parado:

```bash
docker start <id-ou-nome>
```

#### Parar container

Para parar um container ativo:

```bash
docker stop <id-ou-nome>
```

#### Remover containers

Remover container:

```bash
docker rm <id-ou-nome>
```

As mudanças feitas no container serão perdidas para sempre.

#### Variáveis de ambiente

Definindo valor para as variáveis de ambiente presentes no container.

```bash
docker run -d -e <variavel>=<valor> -e <variavel>=<valor> <imagem>
```

#### Commit para uma Imagem Docker

Quando você inicia uma imagem Docker, você pode criar, modificar e excluir arquivos da mesma forma que você faz em máquinas virtuais. As alterações que você fizer serão aplicadas apenas a esse container. Você pode iniciá-lo ou pará-lo, mas uma vez que você o destrua com o comando `docker rm`, as mudanças serão perdidas para sempre.

Salvar o estado de um container como uma nova imagem do Docker localmente:

```bash
docker commit -m "MENSAGEM" -a "NOME-AUTOR" <id-container> <usuario>/<novo-nome-imagem>
# -m: mensagem de commit
# -a: especificar o autor
```

Autenticar no Docker Hub:

```bash
docker login -u <usuario-dockerhub>
```

Se seu nome de usuário do registro do Docker for diferente do nome de usuário local usado para criar a imagem, você terá que marcar sua imagem com o nome de usuário do registro:

```bash
docker tag <usuario>/<novo-nome-imagem> <usuario-dockerhub>/<novo-nome-imagem>
```

Enviar imagem:

```bash
docker push <usuario-dockerhub>/<novo-nome-imagem>
```

Agora estará disponível para baixar:

```bash
docker pull <usuario-dockerhub>/<novo-nome-imagem>
```

### Comando para volumes

#### Criar volume portável

Criar um volume portável, sem a necessidade de associá-lo a um container especial. O volume pode ser consumido por qualquer container.

```bash
docker volume create <nome>
```

Iniciar container com volume criado.

```bash
docker run -d -v <volume>:<path-container> <imagem>
# -v: volume
```

Iniciar container com volume criado com permissão de somente para leitura.

```bash
docker run -d -v <volume>:<path-container>:ro <imagem>
# -ro: somente leitura
```

#### Criar volume não portável

O volume será atrelado ao container.

Inicia container e cria e mapeia o volume para o *path* do container. O *path* do host será o padrão definido pelo docker. No Ubuntu o path padrão é `/var/lib/docker/volumes`

```bash
docker run -d --name <nome-container> -v <path-container> <imagem>
```

Iniciar container e também cria e mapeia o volume definindo o *path* do *host* e container.

```bash
docker run -d --name <nome-container> -v <path-host>:<path-container> <imagem>
```

#### Listar volumes

```bash
docker volume ls
```

#### Remover volume

```bash
docker volume rm <volume>
```

Remover volumes não usados

```bash
docker volume prune
```

#### Informações do volume

```bash
docker volume inspect <volume>
```

### Geral

#### Remover recursos não usados ou pendentes

Remover imagens, containers, volumes e redes não usadas ou pendentes.

```bash
docker system prune
```

## Referências

- <https://docs.docker.com/compose/compose-file>
- <https://www.digitalocean.com/community/tutorials/o-ecossistema-do-docker-uma-introducao-aos-componentes-comuns-pt>
- <https://www.digitalocean.com/community/tutorials/how-to-remove-docker-images-containers-and-volumes>
