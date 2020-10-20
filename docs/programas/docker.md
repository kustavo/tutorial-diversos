# Docker

[TOC]

## Introdução

A Conteinerização é o processo de distribuição e implantação de aplicativos de uma forma portátil e previsível. Ele faz isso empacotando componentes e suas dependências em um ambiente de processos padronizado, isolado e leve chamado contêiner.

A tecnologia Docker usa o kernel do Linux e recursos do kernel como *Cgroups* e *namespaces* para segregar processos. Assim, eles podem ser executados de maneira independente. O objetivo dos containers é criar essa independência: a habilidade de executar diversos processos e aplicações separadamente para utilizar melhor a infraestrutura e, ao mesmo tempo, manter a segurança que você teria em sistemas separados.

As ferramentas de container, incluindo o Docker, fornecem um modelo de implantação com base em **imagem**. Isso facilita o compartilhamento de uma aplicação ou conjunto de serviços, incluindo todas as dependências deles em vários ambientes. O Docker também automatiza a implantação da aplicação (ou de conjuntos de processos que constituem uma aplicação) dentro desse ambiente de container.

Essas ferramentas baseadas nos containers Linux (o que faz com que o Docker seja exclusivo e fácil de usar) oferecem aos usuários acesso sem precedentes a aplicações, além da habilidade de implantar com rapidez e de ter total controle sobre as versões e distribuição.

### Imagem

Pense em imagens como um template compostas por um sistema de camadas que ficam uma sobre as outras para rodar um container, elas são a nossa base para construção de nossas aplicações.

Em uma imagem temos um sistema de inicialização chamado *bootfs*, que é muito parecido com o sistema de boot do Linux, a partir de imagens conseguimos criar nossos containers e com facilidade fazer a migração de sistema operacional ou ambiente de trabalho.

Você precisa baixar as imagens de algum repositório ou criá-las, as imagens ficam armazenados no *Dockerhub*.

Containers são instâncias criadas à partir de imagens Docker.

#### Camadas e controle de versão

Cada arquivo de imagem Docker é composto por uma série de camadas. Elas são combinadas em uma única imagem. Uma nova camada é criada quando há alteração na imagem. Toda vez que um usuário especifica um comando, como executar ou copiar, uma nova camada é criada.

O Docker reutiliza essas camadas para a construção de novos containers, o que torna o processo de criação muito mais rápido. As alterações intermediárias são compartilhadas entre imagens, o que melhora ainda mais a velocidade, o tamanho e a eficiência. O controle de versões é inerente ao uso de camadas. Sempre que é realizada uma nova alteração, é gerado um *changelog* integrado, o que fornece controle total sobre as imagens do container.

<div class='imagem' markdown='1' style="width: 100%">

![exemplo-camadas](_docker/exemplo-camadas.png)

</div>

Na imagem acima, você pode começar a ver (em uma visão simplificada) como os contêineres se relacionam com o sistema host. Os contêineres isolam aplicações individuais e utilizam recursos do sistema operacional que foram abstraídos pelo Docker. Na visão explodida na direita, podemos ver que os contêineres podem ser construídos por "camadas", com vários contêineres compartilhando camadas subjacentes, diminuindo o uso de recursos.

### Volume

Quando um container é removido todas as suas informações são perdidas, portanto queremos criar uma cópia dos dados que estão no container para a nossa máquina. Caso o container venha a cair ou seja removido, podemos falar para ele onde está os dados. Dessa forma, nossas informações ficam salvas independente do estado do container.

Ou seja, queremos falar para o Docker criar um repositório de dados para os containers, ou, como é chamado **volume**.

### Funcionamento

A tecnologia Docker foi desenvolvida inicialmente com base na tecnologia *LXC*, que a maioria das pessoas associa aos containers Linux "tradicionais". No entanto, desde então, essa tecnologia tornou-se independente. O *LXC* era útil como uma virtualização leve, mas não oferecia uma boa experiência para usuários e desenvolvedores. A tecnologia Docker oferece mais do que a habilidade de executar containers: ela também facilita o processo de criação e construção de containers, o envio e o controle de versão de imagens, dentre outras coisas.

<div class='imagem' markdown='1' style="width: 100%">

![traditional-linux-containers-vs-docker](_docker/traditional-linux-containers-vs-docker.png)

</div>

Os containers Linux tradicionais usam um sistema *init* capaz de gerenciar vários processos. Isso significa que aplicações inteiras são executadas como uma. A tecnologia Docker incentiva que as aplicações sejam segregadas em processos separados e oferece as ferramentas para fazer isso. Essa abordagem granular tem algumas vantagens.

#### Vantagens

- **Utilização leve de recursos**: Em vez da virtualização de um sistema operacional inteiro, os contêineres isolam no nível de processos e utilizam o kernel do host.

- **Portabilidade**: Todas as dependências para uma aplicação conteinerizada são empacotadas dentro do contêiner, permitindo-a executar em qualquer host Docker.

- **Modularidade**: A abordagem do Docker para a containerização se concentra na habilidade de desativar uma parte de uma aplicação, seja para reparo ou atualização, sem interrompê-la totalmente. Além dessa abordagem baseada em microsserviços, é possível compartilhar processos entre várias aplicações da mesma maneira como na arquitetura orientada a serviço (SOA).

- **Controle de versões**:  O controle de versões é inerente ao uso de camadas. Sempre que é realizada uma nova alteração, é gerado um *changelog* integrado, o que fornece controle total sobre as imagens do container.

- **Reversão**: Talvez a melhor vantagem da criação de camadas seja a habilidade de reverter quando necessário. Toda imagem possui camadas. Não gostou da iteração atual de uma imagem? Simples, basta reverter para a versão anterior. Esse processo é compatível com uma abordagem de desenvolvimento ágil e possibilita as práticas de integração e implantação contínuas (CI/CD) em relação às ferramentas.

- **Implantação rápida** Antigamente, colocar novo hardware em funcionamento, provisionado e disponível, levava dias. E as despesas e esforço necessários para mantê-lo eram onerosos. Os containers baseados em docker podem reduzir o tempo de implantação de horas para segundos. Ao criar um container para cada processo, é possível compartilhar rapidamente esses processos similares com novos aplicativos. Como não é necessário inicializar um sistema operacional para adicionar ou mover um container, o tempo de implantação é substancialmente menor. Além disso, com a velocidade de implantação, é possível criar dados e destruir os criados pelos containers sem nenhuma preocupação e com facilidade e economia.

Em resumo, a tecnologia Docker é uma abordagem mais granular, controlável e baseada em microsserviços que valoriza a eficiência.

#### Desvantagens

O Docker não fornece as mesmas funcionalidades parecidas com UNIX que os containers Linux tradicionais oferecem. Isso inclui a capacidade de usar processos como *cron* ou *syslog* dentro do container, junto à aplicação. O Docker também tem algumas limitações em questões como a limpeza de processos netos (*grandchild*) após o encerramento dos processos filhos (*child*), algo que é processado de forma natural nos containers Linux tradicionais. Essas desvantagens podem ser mitigadas ao modificar o arquivo de configuração e configurar essas funcionalidade desde o início, algo que não está imediatamente óbvio em um primeiro momento.

Além disso, há outros subsistemas e dispositivos do Linux sem espaço de nomes. Incluindo os dispositivos *SELinux*, *Cgroups* e `/dev/sd*`. Isso significa que, se um invasor adquirir controle sobre esses subsistemas, o host será comprometido. Para manter-se leve, o compartilhamento do kernel do host com os containers gera a possibilidade dessa vulnerabilidade na segurança. Isso é diferente nas máquinas virtuais, que são mais firmemente segregadas a partir do sistema host.

## Instalação

```sh
# Ubuntu
sudo apt install docker.io
```

## Versão

Ver a versão instalada:

```sh
sudo docker -v
```

## Status

Ver status do docker:

```sh
sudo systemctl status docker
```

```txt
● docker.service - Docker Application Container Engine
   Loaded: loaded (/usr/lib/systemd/system/docker.service; disabled; vendor preset: disabled)
   Active: inactive (dead)
     Docs: https://docs.docker.com
```

## Serviço

Iniciar serviço.

```sh
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

set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.547513653-03:00" level=warning msg="Your kernel does not support cgroup blkio weight"
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.547523446-03:00" level=warning msg="Your kernel does not support cgroup blkio weight_device"
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.547724864-03:00" level=info msg="Loading containers: start."
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.687707718-03:00" level=info msg="Default bridge (docker0) is assigned with an IP address 172.17.0.0/>
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.758083807-03:00" level=info msg="Loading containers: done."
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.792834766-03:00" level=warning msg="Not using native diff for overlay2, this may cause degraded perf>
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.793107525-03:00" level=info msg="Docker daemon" commit=6a30dfca03 graphdriver(s)=overlay2 version=19>
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.793230539-03:00" level=info msg="Daemon has completed initialization"
set 11 16:35:29 archlinux dockerd[46625]: time="2019-09-11T16:35:29.828163899-03:00" level=info msg="API listen on /run/docker.sock"
set 11 16:35:29 archlinux systemd[1]: Started Docker Application Container Engine.
```

Habilitar serviço para iniciar após *boot*

```sh
sudo systemctl enable docker
```

## Usuário

Para adicionar o usuário no grupo `docker` e realizar o acesso sem utilizar `sudo`. Execute o comando abaixo:

```sh
sudo usermod -aG docker ${USER}
# ou
sudo usermod -aG docker <nome-usuario>
```

Faça o *logout* ou execute:

```sh
su - ${USER}
```

Confira os grupos do usuário:

```sh
id -nG
```

## Comandos

Um comando docker segue o formato:

```sh
docker [option] [command] [arguments]
```

Para ver a lista de opções e comandos:

```sh
docker --help
```

Para ver mais informações de um comando:

```sh
docker <comando> --help
```

Para especificar se um comando será executado sobre um volume ou um container, podemos usar:

```sh
docker container <comando>
docker volume  <comando>
```

### Trabalhando com imagens Docker

#### Verificar acesso ao Docker Hub

Verificar se você pode acessar e baixar imagens do Docker Hub:

```sh
docker run hello-world
```

```txt
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
9bb5a5d4561a: Pull complete
Digest: sha256:3e1764d0f546ceac4565547df2ac4907fe46f007ea229fd7ef2718514bcec35d
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.
```

#### Procurar imagem

Procurar imagem no Docker Hub:

```sh
docker search <imagem>
```

#### Baixar imagem

Baixar imagem do Docker Hub:

```sh
docker pull <imagem>
```

#### Listar imagens

Listar todas imagens:

```sh
docker images
# ou
docker images --all
# ou
docker images -a
```

#### Remover imagem

Remover imagem:

```sh
docker rmi <imagem>
```

Remover imagens pendentes:

```sh
docker images purge
```

### Trabalhando com containers Docker

#### Criar container

Criar e inicia um container.

```sh
docker run <imagem>
```

##### Criar container com nome desejado {ignore=true}

Criar e inicia um container com nome definido pelo usuário.

```sh
docker run --name <nome> <imagem>
```

##### Criar e acessar terminal do container {ignore=true}

```sh
docker run -it <imagem>
# -i: iteratividade
# -t: link com o terminal do container
```

```txt
root@d9b100f2f636:/#
```

Onde `d9b100f2f636` é o id do container.

É possível mudar o nome de um container com a chave `--name`

##### Criar container com portas mapeadas {ignore=true}

```sh
docker run -it -p <porta-host>:<porta-container> <imagem> /bin/bash
# -p: portas
```

##### Criar container auto destrutivo {ignore=true}

Ao usar um exit para sair do Terminal do SO rodando no container, o mesmo será removido.

```sh
docker run -it --rm <imagem> /bin/bash
# --rm: remover container
```

##### Criar container em segundo plano {ignore=true}

Cria e inicia o container em segundo plano.

```sh
docker run -d -p <porta-host>:<porta-container> <imagem> <comando>
# -d: executar em segundo plano
```

##### Criar container com volume {ignore=true}

Cria e inicia o container com volume mapeado. Se o volume não existe, ele será criado automaticamente.

```sh
docker run -d --name <nome> -v <volume>:<path-container> <imagem>
# -v: volume
```

#### Status do container

```sh
docker stats <id-ou-nome>
```

#### Informações do container

```sh
docker inspect <id-ou-nome>
```

[Veja mais](<https://docs.docker.com/engine/reference/commandline/inspect/>)

#### Acessar container

Acessar terminal de um container ativo. O comando `exec` executa o comando passado, portando poderia executar qualquer comando dentro do container além de `bash`.

```sh
docker exec -it <id-container> bash
# ou
docker exec -it <id-container> /bin/bash
```

#### Listar containers

Listar somente containers ativos:

```sh
docker ps
```

Listar todos containers ativos e inativos:

```sh
docker ps -a
# ou
docker ps --all
```

Listar último container criado:

```sh
docker ps -l
```

#### Iniciar container parado

Para iniciar um container parado:

```sh
docker start <id-ou-nome>
```

#### Parar container

Para parar um container ativo:

```sh
docker stop <id-ou-nome>
```

#### Remover containers

Remover container:

```sh
docker rm <id-ou-nome>
```

As mudanças feitas no container serão perdidas para sempre.

#### Variáveis de ambiente

Definindo valor para as variáveis de ambiente presentes no container.

```sh
docker run -d -e <variavel>=<valor> -e <variavel>=<valor> <imagem>
```

#### Commit para uma Imagem Docker

Quando você inicia uma imagem Docker, você pode criar, modificar e excluir arquivos da mesma forma que você faz em máquinas virtuais. As alterações que você fizer serão aplicadas apenas a esse container. Você pode iniciá-lo ou pará-lo, mas uma vez que você o destrua com o comando `docker rm`, as mudanças serão perdidas para sempre.

Salvar o estado de um container como uma nova imagem do Docker localmente:

```sh
docker commit -m "MENSAGEM" -a "NOME-AUTOR" ID-CONTAINER USUARIO/<novo-nome-imagem>
# -m: mensagem de commit
# -a: especificar o autor
```

Para enviar a imagem para Docker Hub:

```sh
docker login -u <usuario-dockerhub>
```

Se seu nome de usuário do registro do Docker for diferente do nome de usuário local usado para criar a imagem, você terá que marcar sua imagem com o nome de usuário do registro:

```sh
docker tag USUARIO/<novo-nome-imagem> <usuario-dockerhub>/<novo-nome-imagem>
```

Enviar imagem:

```sh
docker push <usuario-dockerhub>/<novo-nome-imagem>
```

Agora estará disponível para baixar:

```sh
docker pull <usuario-dockerhub>/<novo-nome-imagem>
```

### Trabalhando com volumes Docker

#### Criar volume portável

Criar um volume portável, sem a necessidade de associá-lo a um container especial. O volume pode ser consumido por qualquer container.

```sh
docker volume create <nome>
```

Iniciar container com volume criado.

```sh
docker run -d -v <volume>:<path-container> <imagem>
# -v: volume
```

Iniciar container com volume criado com permissão de somente para leitura.

```sh
docker run -d -v <volume>:<path-container>:ro <imagem>
# -ro: somente leitura
```

#### Criar volume não portável

O volume será atrelado ao container.

Inicia container e cria e mapeia o volume para o *path* do container. O *path* do host será o padrão definido pelo docker. No Ubuntu o path padrão é `/var/lib/docker/volumes`

```sh
docker run -d --name <nome-container> -v <path-container> <imagem>
```

Iniciar container e também cria e mapeia o volume definindo o *path* do *host* e container.

```sh
docker run -d --name <nome-container> -v <path-host>:<path-container> <imagem>
```

#### Listar volumes

```sh
docker volume ls
```

#### Remover volume

```sh
docker volume rm <volume>
```

Remover volumes não usados

```sh
docker volume prune
```

#### Informações do volume

```sh
docker volume inspect <volume>
```

### Geral

#### Remover recursos não usados ou pendentes

Remover imagens, containers, volumes e redes não usadas ou pendentes.

```sh
docker system prune
```

## Links

- <https://docs.docker.com/compose/compose-file>

- <https://www.digitalocean.com/community/tutorials/o-ecossistema-do-docker-uma-introducao-aos-componentes-comuns-pt>

- <https://www.digitalocean.com/community/tutorials/how-to-remove-docker-images-containers-and-volumes>
