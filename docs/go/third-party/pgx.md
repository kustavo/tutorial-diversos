# Pgx

Pgx é um driver Go puro e um kit de ferramentas para PostgreSQL. O pgx visa ser de baixo nível, rápido e de alto desempenho, ao mesmo tempo em que habilita recursos específicos do PostgreSQL que o pacote padrão de banco de `database/sql` não permite. O componente de driver do pgx pode ser usado junto com o pacote padrão `database/sql`.

O componente do kit de ferramentas é um conjunto relacionado de pacotes que implementam a funcionalidade do PostgreSQL, como a análise do protocolo de conexão e o mapeamento de tipos entre o PostgreSQL e o Go. Esses pacotes subjacentes podem ser usados ​​para implementar drivers alternativos, proxies, balanceadores de carga, clientes de replicação lógica, etc.

## Prepared statement

Ao criar uma query, a substituição dos espaços reservados ($) deve ser feita pelo PostgreSQL, não deve ser tarefa do código Go.

```sql
UPDATE users SET name=$1, email=$2 WHERE id=$3;
```

A maioria dos bancos de dados relacionais lida com uma consulta (query) em quatro passos:

1. Interpretar (parse) a consulta SQL;
1. Compilar a consulta SQL;
1. Planejar e otimizar o caminho de busca dos dados;
1. Executar a consulta otimizada, buscando e retornando os dados.

As etapas acima são apenas um esboço, na realidade, há mais ciclos de solicitação-resposta envolvidos entre o cliente e o servidor de banco de dados. Apesar de haver mais requisições ao banco de dados ([leia mais](https://aloksinhanov.medium.com/query-vs-exec-vs-prepare-in-golang-e7c49212c36c)), há duas grandes vantagens em utilizar _prepared statements_:

`Performance`

:   - Um _Statement_ irá sempre passar pelos quatro passos acima para cada consulta SQL enviada para o banco. Já um _Prepared Statement_ pré-executa os passos 1, 2 e 3. Então, ao criar um _Prepared Statement_ você pode executar a mesma consulta repetidas vezes mudando apenas os parâmetros de cada uma, a execução usando _Prepared Statements_ será mais rápida e com menos carga sobre o banco.

    - _Prepared statments_ podem utilizar um protocolo binário, ao invés de enviar strings como o protocolo tradicional.

`Evitar injeções de SQL`

:   _Prepared Statement_ realiza o escape de caracteres, evitando ataques de Injeção de SQL.

    - __Evitar injeções de SQL__: realiza o escape de caracteres que poderiam formar um comando SQL.
    
    - __Performance__: 
        - A consulta é "parseada" apenas uma vez, nas próximas vezes o PostgreSQL não precisa fazer checagem de sintaxe da query. Muito útil, como por exemplo, fazer vários INSERTs.
        - _Prepared statements_ podem utilizar um protocolo binário, ao invés de enviar strings como o protocolo tradicional.

Portanto para uma consulta que vai ser executada poucas vezes e não requer nenhum parâmetro, _Statement_ basta. Para os demais casos, prefira _Prepared Statement_.

__Exemplos:__

```go
// Query sem utilizar prepared statement
db.Query("insert into items (name, price, description) values('brownie', 240, 'sizzling')")
db.Exec("UPDATE users SET name='Maria', email='maria@gmail.com' WHERE id=123;")

// Query utilizando prepared statement
db.Query("insert into items (name, price, description) values(?,?,?)", "brownie", 240, "sizzling")
db.Exec("UPDATE users SET name=$1, email=$2 WHERE id=$3;", "Maria", "maria@gmail.com", 123)
```

## Referências

- <https://stackoverflow.com/questions/67812745/placeholderformat-doesnt-replace-the-dollar-sign-for-the-parameter-value-during>
- <https://aloksinhanov.medium.com/query-vs-exec-vs-prepare-in-golang-e7c49212c36c>
- <https://pt.stackoverflow.com/questions/99620/qual-a-diferen%C3%A7a-entre-o-statement-e-o-preparedstatement>
