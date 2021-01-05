# SQL

## Introdução

SQL (Structured Query Language), é a linguagem de pesquisa declarativa padrão para banco de dados relacional. Muitas das características originais do SQL foram inspiradas na álgebra relacional.

## Comandos

### JOIN

A cláusula JOIN correspondente a uma operação de junção em álgebra relacional - combina colunas de uma ou mais tabelas em um banco de dados relacional. A cláusula JOIN permite que os dados de várias tabelas sejam combinados com base na relação existente entre elas. Por meio dessa cláusula, os dados de uma tabela são usados para selecionar os dados pertencentes à outra tabela.

O SQL padrão ANSI especifica cinco tipos de JOIN: `INNER`, `LEFT OUTER`, `RIGHT OUTER`, `FULL OUTER` e `CROSS`.

<figure>
    <img src="../_sql/join.png" title="Fonte: https://br.pinterest.com/pin/751608625289982481"/>
    <figcaption>Tipos de Join</figcaption>
</figure>

Os exemplos a seguir irão usar as seguintes tabelas como referência:

<figure>
    <img src="../_sql/join-tabela-exemplo.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Tabelas de exemplo</figcaption>
</figure>

#### INNER JOIN

`INNER JOIN` é um formato comum de join, que retorna dados apenas quando as duas tabelas tem chaves correspondentes na cláusula ON do join.

<figure>
    <img src="../_sql/join-inner.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Inner Join</figcaption>
</figure>

```sql
SELECT TabelaA.*, TabelaB.* FROM TabelaA INNER JOIN TabelaB ON TabelaA.Chave = TabelaB.Chave
```

<figure>
    <img src="../_sql/join-inner-resultado.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Resultado Inner Join</figcaption>
</figure>

#### LEFT JOIN

`LEFT OUTER JOIN` ou simplesmente `LEFT JOIN` é um dos formatos mais usados de join, que retorna a Tabela A inteira e apenas os registros que coincidirem com a igualdade do join na TabelaB (ou campos nulos para os campos sem correspondência).

<figure>
    <img src="../_sql/join-left.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Left Join</figcaption>
</figure>

```sql
SELECT TabelaA.*, TabelaB.* FROM TabelaA LEFT JOIN TabelaB ON TabelaA.Chave = TabelaB.Chave
```

<figure>
    <img src="../_sql/join-left-resultado.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Resultado Left Join</figcaption>
</figure>

#### RIGHT JOIN

`RIGHT OUTER JOIN` ou simplesmente `RIGHT JOIN` segue o mesmo raciocínio do `LEFT JOIN`, mas se aplicando à tabela B em vez da A:

<figure>
    <img src="../_sql/join-right.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Right Join</figcaption>
</figure>

```sql
SELECT TabelaA.*, TabelaB.* FROM TabelaA RIGHT JOIN TabelaB ON TabelaA.Chave = TabelaB.Chave
```

<figure>
    <img src="../_sql/join-right-resultado.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Resultado Right Join</figcaption>
</figure>

#### FULL OUTER JOIN

Conhecida como `OUTER JOIN` ou simplesmente `FULL JOIN`, este retorna todos os registros de ambas as tabelas.

<figure>
    <img src="../_sql/join-full.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Full Join</figcaption>
</figure>

```sql
SELECT TabelaA.*, TabelaB.* FROM TabelaA FULL OUTER JOIN TabelaB ON TabelaA.Chave = TabelaB.Chave
```

<figure>
    <img src="../_sql/join-full-resultado.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Resultado Full Join</figcaption>
</figure>

#### CROSS JOIN

Basicamente é o produto cartesiano entre as duas tabelas. Para cada linha de TabelaA, são retornadas todas as linhas de TabelaB.

É mais fácil entender o `CROSS JOIN` como um "Join sem cláusula ON", ou seja, todas as combinações de linhas de A e B são devolvidas. Inclusive, se você fizer um `CROSS JOIN` com cláusula ON, ele "vira" um mero `INNER JOIN`.

<figure>
    <img src="../_sql/join-cross.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Cross Join</figcaption>
</figure>

```sql
SELECT TabelaA.*, TabelaB.* FROM TabelaA CROSS JOIN TabelaB
-- ou
SELECT TabelaA.*, TabelaB.* FROM TabelaA, TabelaB
```

<figure>
    <img src="../_sql/join-cross-resultado.png" title="Fonte: https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join"/>
    <figcaption>Resultado Cross Join</figcaption>
</figure>

## Stored Procedure

Stored Procedure, é uma conjunto de comandos em SQL que podem ser executados de uma só vez, como em uma função. Ele armazena tarefas repetitivas e aceita parâmetros de entrada para que a tarefa seja efetuada de acordo com a necessidade individual.

Um Stored Procedure pode reduzir o tráfego na rede, melhorar a performance de um banco de dados, criar tarefas agendadas, diminuir riscos, criar rotinas de processamento, etc.

### PostgreSQL

Ao trabalharmos com o SGBDOR (Sistema Gerenciador de Banco de Dados Objeto Relacional) PostgreSQL não temos necessariamente a função Stored Procedure (SP) definida, como podemos encontrar em diversos outros tipos de SGBD’s. Elas são, na realidade, pequenos trechos de código armazenados do lado do servidor de uma base de dados. Ao contrário do que acontece em outras bases de dados, as SPs no Postgres são definidas como `FUNCTIONS`, assim como as triggers, tornando esse recurso um pouco mais complicado, dependendo inclusive do seu tipo de retorno. Essas funções possuem características importantes e diferentes, mas criadas de igual forma. Trabalhar com a criação destes pequenos trechos de código é, de certa forma, uma boa prática, pois podemos deixar códigos bastante complexos atuando do lado do servidor que poderão ser utilizados por várias aplicações, evitando assim a necessidade de replicá-los em cada uma dessas aplicações.

## Referências

- <https://pt.wikipedia.org/wiki/SQL>
- <https://www.devmedia.com.br/sql-join-entenda-como-funciona-o-retorno-dos-dados/31006>
- <https://www.w3schools.com/sql/sql_join.asp>
- <https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join>
