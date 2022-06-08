# Docker-Compose

## Introdução

Geralmente com o aumento do número de containers em execução, fica evidente a necessidade de um melhor gerenciamento da sua comunicação, pois é ideal que os serviços consigam trocar dados entre os containers quando necessário, ou seja, você precisa lidar com a rede desse novo ambiente.

Imagine o trabalho que seria executar algumas dezenas de containers manualmente na linha de comando, um por um e todos seus parâmetros necessários, suas configurações de rede entre containers, volumes e afins. Pode parar de imaginar, pois isso não será mais necessário. Para atender essa demanda de gerenciamento de múltiplos containers a solução é o **Docker Compose**.

Docker compose é uma ferramenta para definição e execução de múltiplos containers Docker. Com ela é possível configurar todos os parâmetros necessários para executar cada container a partir de um arquivo de definição. Dentro desse arquivo, definimos cada container como serviço, ou seja, sempre que esse texto citar serviço de agora em diante, imagine que é a definição que será usada para iniciar um container, tal como portas expostas, variáveis de ambiente e afins.

Com o Docker Compose podemos também especificar quais volumes e rede serão criados para serem utilizados nos parâmetros dos serviços, ou seja, isso quer dizer que não preciso criá-los manualmente para que os serviços utilizem recursos adicionais de rede e volume.

O arquivo de definição do Docker Compose é o local onde é especificado todo o ambiente (rede, volume e serviços), ele é escrito seguindo o formato YAML. Esse arquivo por padrão tem como nome **`docker-compose.yml`**.

Para ver a versão do arquivo que é compatível com a versão do docker, acesse esse [link](<https://docs.docker.com/compose/compose-file/>).

## Instalação

Usando a versão do repositório:

```bash
sudo apt instal docker-compose
```

Usando a versão do Github:

```bash
# Exemplo usando a versão 1.29.2
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose
```

Ver a versão instalada:

```bash
docker-compose --version
```

## Anatomia do docker-compose.yml

=== "Exemplo 1"

  ```yml
  version: '3.9'           # Versão do formato de arquivo
  services:
    app:                   # Nome escolhido do serviço  
      image: yuri/web      # Imagem do container na última versão
      container_name: app  # Nome escolhido do container
      ports:
        - 8080:80          # Portas <exposta>:<container>
      depends_on:
        - banco            # Depende que serviço banco seja iniciado primeiro
    
    banco:
      image: mysql
      container_name: db
      environment: # variáveis de ambiente do mysql
        - MYSQL_USER=root
        - MYSQL_ALLOW_EMPTY_PASSWORD=yes
        - MYSQL_DATABASE=loja
      volumes:
        # Mapeia o diretório "docker-entrypoint-initdb.d" do container para o diretório "./dados" no host
        - ./dados:/docker-entrypoint-initdb.d 
  ```

=== "Exemplo 2"

  ```yml
  version: '3.9'

  services:
    postgres:
      container_name: postgres-container
      image: postgres
      restart: always
      environment:
        POSTGRES_PASSWORD: postgres
        POSTGRES_USER: postgres
        POSTGRES_DB: gustavo
      ports:
        - 5432:5432
      volumes:
        # Mapeia o diretório "/var/lib/postgresql/data" do container para o local gerenciado pelo docker-compose
        # Ao ver que "db-data" está definido em volumes: docker-compose não irá tratar como path do host "./db-data"
        - db-data:/var/lib/postgresql/data

  # Volumes definidos aqui são armazenados num local gerenciado pelo docker-compose.
  # Se o driver é "local", ele será armazenado no mesmo host que executa o container.
  # No Ubuntu geralmente está em "/var/lib/docker/volumes/".
  volumes:
      db-data: 
        name: postgres-volume
        # Utilizando armazenamento local. Sistemas de armazenamentos em rede podem ter diferentes drivers.
        driver: local 
  ```

## Criar e iniciar Containers

Cria e inicia os containers. Se o nome do arquivo for `docker-compose.yml` **não** é necessário informá-lo com `-f <nome>`:

```bash
docker-compose -f <nome.yml> up -d
# -f: Path do arquivo .yml.
# -d, --detach: Não trava o terminal.
# --no-recreate: Se o container existe, não recria.
# --force-recreate: Recria os containers mesmo se a configuração e imagem não foram alterados.
# --abort-on-container-exit: Para todos container se algum parar (incompatível com -d).

Inicia os containers sem criá-los. Usado caso os containers já foram criados:

```bash
docker-compose start <nome.yml>
```

## Parar e remover Containers

Para os containers sem removê-los:

```bash
docker-compose stop <nome.yml>
```

Parar e remove os containers:

```bash
docker-compose -f <nome.yml> down
# -v, --volumes: Remove os volumes nomeados, declarados e, 'volumes'
# --remove-orphans: Remove containers dos serviços não declarados no arquivo compose
```

## Listar e analisar Containers

Listar containers iniciados a partir do arquivo compose.

```bash
docker-compose ps <nome.yml>
```

## Erros e soluções

### Localhost

Não encontrado em localhost.

??? tip "Solução"

  ```bash
  export DOCKER_HOST=127.0.0.1
  ```
