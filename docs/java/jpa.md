# JPA

[TOC]

## Introdução

A JPA ( ***Java Persistence API***) é a API do Java que dita como os frameworks ORM (***Object-relational mapping***) devem ser implementados para prover a persistência de dados em bancos relacionais. Construída tendo como base a **JDBC**, a JPA abstrai detalhes dessa API base, simplificando o código de persistência.

Até a criação da JPA, a solução padrão do Java utilizada diretamente para realizar a persistência de dados era a JDBC. É a partir da JDBC que conseguimos nos conectar ao banco de dados e executar comandos SQL para inserção, atualização, remoção e consulta a dados.

Uma das principais limitações dessa API é que ao lidar com ela precisamos nos preocupar com a conversão dos dados recuperados no formato relacional, utilizado no banco de dados, para o formato orientado a objetos, utilizado no código de nosso programa, e vice-versa.

A essa diferença damos o nome de impedância objeto-relacional. Ela simboliza o motivo central de vários problemas, prejudicando a produtividade dos programadores e, também, levando a um aumento na quantidade de erros ao longo do desenvolvimento do software.

Com as limitações da JDBC, muitas empresas e programadores mais experientes começaram a criar soluções próprias que buscavam simplificar a persistência de dados. O principal objetivo desses inventos era reduzir o código necessário para mapear as tabelas do banco às classes Java, atendendo assim a um dos pontos mais críticos naquela época: o mapeamento objeto-relacional.

Em pouco tempo já existiam muitas soluções para abstrair as principais dificuldades de se trabalhar com a JDBC. Porém, com todas essas soluções, outro problema acabou surgindo. Qual desses tantos inventos adotar em um projeto para facilitar a programação da persistência de dados?

Sabendo que não havia uma padronização entre essas soluções, o programador ou o arquiteto tentava escolher a opção mais madura, pois sabia que com ela as chances de precisar migrar de uma solução de persistência para outra eram mínimas.

Ainda assim, essa necessidade se tornou real em muitos projetos. Sem a padronização, como cada solução resolvia cada problema de uma forma, era necessário reescrever grande parte da camada de acesso a dados quando a troca de tecnologia se fazia necessária.

Observando esse problema, a comunidade Java optou pela criação de uma especificação que ditasse esse padrão, e assim foi criada a JPA, ou Java Persistence API.

A API JPA é a especificação que determina como os frameworks de mapeamento objeto-relacional Java devem ser implementados. Com ela, temos um padrão para:

1. Realizar o mapeamento objeto-relacional entre as classes Java e as tabelas do banco de dados;
1. Escrever consultas SQL independentemente dos detalhes de implementação da linguagem de cada banco de dados;
1. E, por fim, temos uma referência, a qual deve ser seguida por todas as soluções de persistência relacional. Assim, caso seja necessário adotar outra opção ORM, não será necessário realizar muitas mudanças no código.

Para concluir, é importante informar que a JPA é uma especificação e, como tal, não podemos programar diretamente com ela. Isto é, precisamos de uma solução que a implemente. Atualmente, a implementação mais famosa é o ***Hibernate***. Além dele, no entanto, você também encontrará outras soluções, como a ***OpenJPA***, da Fundação Apache, o ***EclipseLink JPA***, da fundação Eclipse, entre outras.



## Anotações

**@Entity:** informa que a classe é uma entidade no banco de dados (tabela).

```java
@Entity
public class Lembrete
```

**@Id**: define qual campo da classe deve ser utilizado como chave primária.

**@GeneratedValue**: indica como a chave primária será persistida.

- **Strategy**: indica qual estratégia usada para gerenciar a sequência das chaves primárias.
   - **GenerationType.IDENTITY**: usa as colunas especiais IDENTITY no banco de dados para atribuir automaticamente um id. Neste caso a sequencia é gerenciada pelo próprio banco de dados. São suportadas em muitos bancos de dados, como **MySQL**, **DB2**, **SQL** **Server**, **Sybase** e **Postgres**. O Oracle não suporta colunas IDENTITY.
   - **GenerationType.SEQUENCE**: usa objetos especiais SEQUENCE para gerar os ids. Geralmente um objeto SEQUENCE possui um nome, um INCREMENT e outros objetos de configurações. Neste caso o ORM (***Object-relational mapping***), como o Hibernate, será responsável por obter e inserir o próximo valor da sequência. Suportado nos bancos **Oracle**, **DB2**, e **Postgres**
   - **GenerationType.AUTO**: deixa com o provedor de persistência a escolha da estratégia mais adequada de acordo com o banco de dados.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)      
private long id;
```

**@Column**: indica que o campo será uma coluna na tabela.

- **nullable**: indica se a coluna aceita valores mulos.

```java
@Column(nullable = false)
private String titulo;
```



## EntityManager

Na JPA utilizamos a classe **EntityManager** para interagir com o contexto de persistência. Um contexto de persistência mantém sob seu conhecimento as diferentes entidades que contêm dados a serem persistidos. Sendo assim, é através do EntityManager que podemos manipular as entidades mapeadas e realizar ações relacionadas aos registros da tabela para a qual a entidade aponta.

Através do EntityManager conseguimos adicionar, buscar, remover e realizar diferentes consultas no banco de dados.

Um EntityManager é criado a partir de uma **EntityManagerFactory**. Cada EntityManager é criado para **acesso a um único banco de dados**. As configurações transmitidas pelo EntityManagerFactory vêm do arquivo `persistence.xml` e são lidas pela classe Persistence. Por isso iniciamos o EntityManagerFactory usando o método `Persistence.createEntityManagerFactory`, que recebe como parâmetro o nome da unidade de persistência.

Uma vez criada uma nova instância da entidade, podemos associá-la ao **EntityManager** para torná-la persistente com o método `persist`. Com isso, a partir da estratégia definida para a chave primária, um novo identificador será gerado. Para atualizar é utilizado o método `merge` e para excluir `remove`.

Uma entidade pode ter diferentes estados. Quando uma entidade é criada, para a JPA seu estado é **new**. Ao utilizar os métodos `persist`, `merge` ou `find`, estamos alterando o estado dessa entidade para **managed**. Esse estado se mantém até que a entidade seja removida, quando ela se torna **removed**, ou deliberadamente retirada do contexto de persistência, momento em que ela se torna **detached**.

No estado managed, a JPA passa a observar as alterações realizadas nesta entidade, permitindo que seus dados sejam atualizados ou removidos. Assim sendo, para atualizar um registro precisamos obter a instância da entidade a partir de uma consulta gerenciada pelo framework, como por exemplo, pelo método `find`.

```java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String args[]) {

        // Leitura do persistence.xml
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        // Cria um EntityManager
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Lembrete> lembretesSalvos;
        List<Lembrete> lembretes = new ArrayList<>();
        Lembrete lembreteSalvo;

        lembretes.add(new Lembrete("Comprar leite", "Hoje, 10h30"));
        lembretes.add(new Lembrete("Comprar maça", "Hoje, 12h00"));
        lembretes.add(new Lembrete("Estudar", "Amanhã, 08h15"));

        try {

            // -- PERSISTENCIA

            // Abrir uma nova transação com o banco
            em.getTransaction().begin();
            // Envia a instrução de INSERT
            lembretes.forEach(lembrete -> em.persist(lembrete));
            // Encerra a transação, gravando os dados no banco
            em.getTransaction().commit();


            // -- UPDATE

            // Usando o método find para pegar id=2
            lembreteSalvo = em.find(Lembrete.class, 2L);

            lembreteSalvo.setTitulo("Comprar café");
            lembreteSalvo.setDescricao("Segunda, 15h20");

            em.getTransaction().begin();
            // Envia a instrução de UPDATE
            em.merge(lembreteSalvo);
            em.getTransaction().commit();


            // -- CONSULTAS

            // Usando JPQL + TypedQuery para buscar todos
            TypedQuery<Lembrete> query = em.createQuery("from Lembrete", Lembrete.class);
            lembretesSalvos = query.getResultList();

            if (lembretesSalvos != null) {
                lembretesSalvos.forEach(System.out::println);
            }

        } catch (Exception e) {

            // Rollback em caso de erro
            em.getTransaction().rollback();
            System.out.println("ERRO: " + e.getMessage());

        } finally {

            // Encerra a conexão
            em.close();
        }

    }
}
```




## Arquivo de configuração

Arquivo de configuração `persistence.xml`. Geralmente situado em: `src/main/resources/META-INF/persistence.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">

    <!-- Identificador da unidade de persistência -->
    <persistence-unit name="helloworld" transaction-type="RESOURCE_LOCAL">

        <!-- Indica qual a implementacao do JPA usada -->
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <!-- Entidade(s) mapeada(s). O Hiberbate faz o mapeamento automático, portanto é opcional -->
        <class>br.com.devmedia.java.hibernate.Lembrete</class>

        <properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost/jpa-exemplo"/>
            <property name="javax.persistence.jdbc.user" value="postgres"/>
            <property name="javax.persistence.jdbc.password" value="postgres"/>
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQL94Dialect"/>
            <!-- Mostrar SQL no console -->
            <property name="hibernate.show_sql" value="true"/>
            <!-- Mostrar SQL formatado -->
            <property name="hibernate.format_sql" value="true"/>
            <!--  Atualiza a estrutura do banco, gera as tabelas se for preciso -->
            <property name="hibernate.hbm2ddl.auto" value="update"/>
        </properties>
    </persistence-unit>
</persistence>
```



### Links

- Curso introdutório: <https://www.devmedia.com.br/view/viewaula.php?idcomp=38575>
- JPA 2.0: <https://www.devmedia.com.br/jpa-2-0-persistencia-a-toda-prova/17437>