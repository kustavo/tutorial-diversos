# Docker-Compose

## Introdução

Geralmente com o aumento do número de containers em execução, fica evidente a necessidade de um melhor gerenciamento da sua comunicação, pois é ideal que os serviços consigam trocar dados entre os containers quando necessário, ou seja, você precisa lidar com a rede desse novo ambiente.

Imagine o trabalho que seria executar algumas dezenas de containers manualmente na linha de comando, um por um e todos seus parâmetros necessários, suas configurações de rede entre containers, volumes e afins. Pode parar de imaginar, pois isso não será mais necessário. Para atender essa demanda de gerenciamento de múltiplos containers a solução é o **Docker Compose**.

Docker compose é uma ferramenta para definição e execução de múltiplos containers Docker. Com ela é possível configurar todos os parâmetros necessários para executar cada container a partir de um arquivo de definição. Dentro desse arquivo, definimos cada container como serviço, ou seja, sempre que esse texto citar serviço de agora em diante, imagine que é a definição que será usada para iniciar um container, tal como portas expostas, variáveis de ambiente e afins.

Com o Docker Compose podemos também especificar quais volumes e rede serão criados para serem utilizados nos parâmetros dos serviços, ou seja, isso quer dizer que não preciso criá-los manualmente para que os serviços utilizem recursos adicionais de rede e volume.

O arquivo de definição do Docker Compose é o local onde é especificado todo o ambiente (rede, volume e serviços), ele é escrito seguindo o formato YAML. Esse arquivo por padrão tem como nome **`docker-compose.yml`**.

Para ver a versão do arquivo que é compatível com a versão do docker, acesse esse [link](<https://docs.docker.com/compose/compose-file/>).

## Anatomia do docker-compose.yml

Exemplo 1:

```yml
version: '3.4'           # versão do docker-compose
services:
  app:
    image: yuri/web      # imagem do container
    container_name: app  # nome do container definido pelo usuario
    ports:
      - 8080:80          # portas <exposta>:<container>
    depends_on:
      - db               # container que também deve ser iniciado
  db:
    image: mysql
    container_name: db
    environment:         # variáveis de ambiente do mysql
      - MYSQL_USER=root
      - MYSQL_ALLOW_EMPTY_PASSWORD=yes
      - MYSQL_DATABASE=loja
    volumes:
      - ./dados:/docker-entrypoint-initdb.d # mapeia o volume <exposta>:<container>
```

Exemplo 2:

```yml
version: '3.6'

services:
  db:
    container_name: postgres-container
    image: postgres
    restart: always
    environment:
      POSTGRES_PASSWORD: postgres
      POSTGRES_USER: postgres
      POSTGRES_DB: cqrs
    ports:
      - 5432:5432
    volumes:
      - db-data:/var/lib/postgresql/data  # mapeamento do volume

volumes:
    db-data:                              # definições do volume
      name: postgres-volume
```

## Criar e iniciar Containers

Cria e inicia os containers. Se o nome do arquivo for `docker-compose.yml` **não** é necessário informá-lo com `-f <nome>`.

```bash
docker-compose -f <nome.yml> up -d
# -f: arquivo
```

Inicia os containers sem criá-los. Usado caso os containers já foram criados.

```bash
docker-compose start <nome.yml>
```

## Parar containers

Para e remove containers.

```bash
docker-compose -f <nome.yml> down
```

Para containers sem removê-los.

```bash
docker-compose stop <nome.yml>
```

## Listar containers

Listar todos os serviços que foram iniciados a partir do arquivo yml.

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
