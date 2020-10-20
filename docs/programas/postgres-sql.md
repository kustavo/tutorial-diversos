# PostgreSQL

PostgreSQL é um sistema gerenciador de banco de dados objeto relacional (SGBD), desenvolvido como projeto de código aberto.

[TOC]

## Acesso ao usuário postgres

Para acessar o usuário PostgreSQL

```sh
sudo -u postgres -i
# ou
sudo su - postgres
```

## Configuração

1. Instalar a última versão do PostgreSQL

1. Acessar o usuário PostgreSQL

    ```sh
    sudo -u postgres -i
    ```

1. Antes que o PostgreSQL possa funcionar corretamente, o cluster do banco de dados deve ser inicializado:

    ```sh
    postgres> initdb --locale $LANG -E UTF8 -D '/var/lib/postgres/data'
    postgres> exit
    ```

## Alterar senha do usuário postgres

```sh
postgres> psql -c "alter user postgres with password 'NOVA-SENHA'"
```

## Serviço

1. Iniciar o serviço

    ```sh
    systemctl start postgresql
    ```

1. Iniciar serviço na inicialização do S.O.

    ```sh
    systemctl enable postgresql
    ```

1. Parar o serviço

    ```sh
    systemctl stop postgresql
    ```

1. Ver status

    ```sh
    systemctl status postgresql
    ```

## Criar banco

```sh
postgres> createdb <banco>
```

## Acesso ao shell do banco

```sh
psql -d <banco>
```

## Role

Até a versão 8.1, os conceitos de usuários e de grupos administrativos eram distintos em *PostgreSQL*. Esses conceitos foram abstraídos e absorvidos por uma única entidade chamada **role**.

Uma *role* descreve seu próprio papel e quais as funções em que ela atua no contexto da segurança do banco de dados.

De modo abstrato, uma *role* pode se comportar como um usuário, como um grupo ou ter ambos comportamentos ao mesmo tempo. A *role* pode conter e ser contida por outra *role*. Deste modo, fica claro que o conceito de *role* está relacionado com a definição de permissões, privilégios e garantias de acesso aos objetos do banco e aos dados.

*Roles* podem ser donas de seus próprios objetos (tabelas) e podem delegar permissões ou direitos para outras roles através de herança e relacionamentos de confiança.

| Nível   | Comando                  | Descrição                      |
| ------- | ------------------------ | ------------------------------ |
| usuário | createuser --interactive | Criar *role* em modo iterativo |
| banco   | \du                      | Mostrar roles                  |

## Schema

A partir da versão 7.3, o PostgreSQL iniciou o suporte a Schema, no qual é possível criar um espaço lógico (namespace) dentro do banco de dados para armazenar os objetos: Dados, tabelas, funções, sequence, etc.

O conceito de Schema é semelhante ao cross-database, a diferença é que o cross-database relaciona objetos (Tabelas, índices, sequence, etc) de banco de dados distintos, já o Schema relaciona objetos (Tabelas, índices, sequence, etc) que estão no **mesmo banco de dados**, mas em estruturas lógicas (namespace) distintas - schema.

### Schemas Especiais

Todo Banco de Dados PostgreSQL contém schemas especiais que são necessários para o Backend (processo postgreSQL que estabelece a conexão com o cliente) por isso, eles não podem ser removidos ou renomeados.

Os Schemas especiais:

- **pg_catalog**: Contém informações sobre as tabelas, funções, views e metainformações sobre o banco;
- **pg_temp_x**: É um schema usado na criação e armazenamento de tabelas temporárias;
- **pg_toast**: Relações internas do PostgreSQL usadas para várias atividades, como a reindexão (vacuum), por exemplo.

## Constraints

Constraints, ou Restrições, são regras específicas aplicadas nas colunas de uma tabela, ou na tabela em si. É possível definir restrições em colunas e em tabelas, permitindo um grande controle sobre os dados armazenados. Caso um usuário tente armazenar dados em uma coluna de forma a violar uma restrição, um erro será gerado. Ou seja, são usadas para limitar os tipos de dados que são inseridos.

As principais constraints SQL disponíveis no PostgreSQL são as seguintes:

- NOT NULL
- CHECK
- UNIQUE
- PRIMARY KEY
- FOREIGN KEY

Para especificar uma *constraint* nomeada, é usado a palavra `CONSTRAINT` seguido pelo identificador e a definição da *constraint*. Se o nome da *constraint* não for especificado, o sistema escolherá um automaticamente. A nomeação de *constraint* é útil durante o debug de erros na leitura de *logs*. O nome da *constraint* deve ser única no banco.

```sql
CREATE TABLE produtos (
    codigo integer,
    nome text,
    id_empresa integer NOT NULL,
    preco numeric CONSTRAINT produtos_preco_positivo CHECK (preco > 0),
    CONSTRAINT fk_produtos_empresas FOREIGN KEY (id_empresa) REFERENCES empresas (id),
);
```

## Problemas

- **An old version of the database format was found**:

    ```sh
    sudo rm -rf /var/lib/postgres/data
    sudo mkdir /var/lib/postgres/data
    sudo chown -R postgres:postgres /var/lib/postgres/data
    ```