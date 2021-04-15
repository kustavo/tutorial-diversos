# Entity Framework

Entity Framework (EF) é uma estrutura de mapeamento objeto-relacional (ORM) de código aberto para ADO.NET, parte do Framework .NET. É um conjunto de tecnologias em ADO.NET que oferece suporte ao desenvolvimento de aplicativos de software orientados a dados. Arquitetos e desenvolvedores de aplicativos orientados a dados normalmente lutam com a necessidade de atingir dois objetivos muito diferentes. O Entity Framework permite que os desenvolvedores trabalhem com dados na forma de objetos e propriedades específicos de domínio, sem ter que se preocupar com as tabelas e colunas do banco de dados subjacentes onde esses dados são armazenados. Com o Entity Framework, os desenvolvedores podem trabalhar em um nível mais alto de abstração ao lidar com dados e podem criar e manter aplicativos orientados a dados com menos código do que os aplicativos tradicionais.

ADO.NET é uma tecnologia de acesso a dados do Framework .NET que fornece comunicação entre sistemas relacionais e não relacionais por meio de um conjunto comum de componentes. ADO.NET é um conjunto de componentes de software de computador que os programadores podem usar para acessar dados e serviços de dados de um banco de dados. É uma parte da *Base Class Library* incluída no Framework .NET. É comumente usado por programadores para acessar e modificar dados armazenados em sistemas de banco de dados relacionais, embora também possa acessar dados em fontes de dados não relacionais.

ADO.NET é conceitualmente dividido em consumidores e provedores de dados. Os consumidores são os aplicativos que precisam de acesso aos dados, e os provedores são os componentes de software que implementam a interface e, portanto, fornecem os dados ao consumidor.

Um provedor é um componente de software que interage com uma fonte de dados. Os provedores de dados ADO.NET são análogos aos *drivers ODBC*, *drivers JDBC* e *provedores OLE DB*.

Os provedores ADO.NET podem ser criados para acessar armazenamentos de dados simples como um arquivo de texto e planilha, por meio de bancos de dados complexos como banco de dados *Oracle*, *Microsoft SQL Server*, *MySQL*, *PostgreSQL*, *SQLite*, *IBM DB2*, *Sybase ASE* e muitos outros. Eles também podem fornecer acesso a armazenamentos de dados hierárquicos, como sistemas de e-mail.

No entanto, como diferentes tecnologias de armazenamento de dados podem ter recursos diferentes, cada provedor ADO.NET pode não implementar todas as interfaces possíveis disponíveis no padrão ADO.NET. A Microsoft descreve a disponibilidade de uma interface como "específica do provedor", pois pode não ser aplicável dependendo da tecnologia de armazenamento de dados envolvida. Os provedores podem aumentar as capacidades de um armazenamento de dados; esses recursos são conhecidos como "serviços" na linguagem da Microsoft.

## Instalação

Instalação global (a nível de usuário)

```bash
dotnet tool install --global dotnet-ef
```

## Atualização

Atualização global

```bash
dotnet tool update --global dotnet-ef
```

## Configuração de pacotes

Adicionar os pacotes ao projeto.

```bash
dotnet add package Microsoft.EntityFrameworkCore
dotnet add package Microsoft.EntityFrameworkCore.Design
```

Adicionar o pacote do provedor do banco de dados

```bash
dotnet add <projeto> package <pacote-provedor-banco>
```

[Veja mais lista de provedores de bancos de dados](https://docs.microsoft.com/pt-br/ef/core/providers/index?tabs=dotnet-core-cli)

??? example "Exemplos"

    ```bash
    # Adicionar o pacote do provedor do banco PostgreSQL
    dotnet add package Npgsql.EntityFrameworkCore.PostgreSQL

    # Adicionar o pacote do provedor do banco MySQL
    dotnet add package MySql.EntityFrameworkCore
    ```

    Exemplo configuração do arquivo Startup.cs

    ```dotnet
    public class MeuContexto : DbContext {

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            // Configuração para o PostgreSQL
            optionsBuilder.UseNpgsql("Host=localhost;Port=5432;Database=<banco>;Username=<usuario>;Password=<senha>");

            // Configuração para o MySQL
            optionsBuilder.UseMySQL("server=localhost; port=3306; database=<banco>; Uid=<usuario>; Pwd=<senha>;"));
        }
    }
    ```

## Migrations

Usando o recurso Migrations do Entity Framework podemos realizar alterações em nosso modelo de entidades e ter a atualização automática do banco de dados refletindo essas mudanças.

Podemos usar o Migrations de forma totalmente automática onde todo o processo de atualização do banco de dados é feita de forma transparente pelo Migrations.

Podemos também definir manualmente pontos de migração aplicando as últimas modificações ou regredindo para uma modificação que foi executada anteriormente.

## Interface de linha de comando

Para especificar o ambiente para projetos ASP.NET, defina a variável de ambiente `ASPNETCORE_ENVIRONMENT` antes de executar os comandos.

A partir do EF Core 5,0, argumentos adicionais também podem ser passados para `Program.CreateHostBuilder`, permitindo que você especifique o ambiente na linha de comando:

```bash
dotnet ef database update -- --environment Production
```

!!! info ""
O token `--` direciona `dotnet ef` para tratar tudo o que segue como um argumento e não tentar analisá-los como opções. Quaisquer argumentos extras não usados pelo `dotnet ef` são encaminhados para o aplicativo.

### dotnet ef migrations list

Listar todos pontos de migração.

```bash
dotnet ef migrations list [options]
```

??? info "Principais parâmetros"

    ```
    Options:
    --connection <CONNECTION>              The connection string to the database. Defaults to the one specified in AddDbContext or OnConfiguring.
    --no-connect                           Don't connect to the database.
    --json                                 Show JSON output. Use with --prefix-output to parse programatically.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```
### dotnet ef migrations add

Adiciona um ponto de migração.

```bash
dotnet ef migrations add [arguments] [options]
```

??? info "Principais parâmetros"

    ```
    Arguments:
    <NAME>  The name of the migration.

    Options:
    -o|--output-dir <PATH>                 The directory to put files in. Paths are relative to the project directory. Defaults to "Migrations".
    --json                                 Show JSON output. Use with --prefix-output to parse programatically.
    -n|--namespace <NAMESPACE>             The namespace to use. Matches the directory by default.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

??? example "Exemplos"

    ```bash
    # Criar uma migration chamada v89.00 no projeto Projeto.Data.EF.csproj onde o projeto principal é Projeto.Api.csproj usando o 
    # contexto ProjetoContext e framework netcoreapp2.2
    dotnet ef migrations add v89.00 -p Projeto.Data.EF.csproj -s ../../Projeto.Api.csproj -c ProjetoContext --framework netcoreapp2.2
    ```

!!! info ""

    Para que o banco seja atualizado é necessário executar o comando: `dotnet ef database update`

### dotnet ef migrations remove

Remove um ponto de migração.

```bash
dotnet ef migrations remove [options]
```

??? info "Principais parâmetros"

    ```
    Options:
    -f|--force                             Revert the migration if it has been applied to the database.
    --json                                 Show JSON output. Use with --prefix-output to parse programatically.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

??? example "Exemplos"

    ```bash
    # Remove a última migration
    dotnet ef migrations remove
    ```

!!! info ""

    Para que o banco seja atualizado é necessário executar o comando: `dotnet ef database update`

### dotnet ef migrations script

Gera o script SQL da migração que será aplicada.

```bash
dotnet ef migrations script [arguments] [options]
```

??? info "Principais parâmetros"

    ```
    Arguments:
    <FROM>  The starting migration. Defaults to '0' (the initial database).
    <TO>    The target migration. Defaults to the last migration.

    Options:
    -o|--output <FILE>                     The file to write the result to.
    -i|--idempotent                        Generate a script that can be used on a database at any migration.
    --no-transactions                      Don't generate SQL transaction statements.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

### dotnet ef database drop

Remove o banco de dados.

```bash
dotnet ef database drop [options]
```

??? info "Principais parâmetros"

    ```
    Options:
    -f|--force                             Don't confirm.
    --dry-run                              Show which database would be dropped, but don't drop it.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

### dotnet ef database update

Atualiza o banco de dados para um determinado ponto de migração.

```bash
dotnet ef database update [arguments] [options]
```

??? info "Principais parâmetro"

    ```
    Arguments:
    <MIGRATION>  The target migration. If '0', all migrations will be reverted. Defaults to the last migration.

    Options:
    --connection <CONNECTION>              The connection string to the database. Defaults to the one specified in AddDbContext or OnConfiguring.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

??? example "Exemplos"

    ```bash
    # Atualiza até a último ponto de migração.
    # Se o banco não existir também será criado.
    dotnet ef database update

    # Reverte todos pontos de migração.
    dotnet ef database update 0

    # Informando o ambiente
    dotnet ef database update -- --environment Production

    # Reverter até o ponto de migração chamado "20180904195021_InitialCreate".
    dotnet ef database update 20180904195021_InitialCreate
    ```

    Atualizar o banco de dados ao iniciar o aplicativo.

    ```bash
    class Program
    {
        static void Main(string[] args)
        {
            using var context = new BloggingContext();
            context.Database.Migrate();
        }
    }
    ```

### dotnet ef dbcontext info

Obtém informação sobre um tipo de `DbContext`.

```bash
dotnet ef dbcontext info [options]
```

??? info "Principais parâmetro"

    ```
    Options:
    --json                                 Show JSON output. Use with --prefix-output to parse programatically.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

### dotnet ef dbcontext list

Lista os tipos de `DbContext` disponíveis.

```bash
dotnet ef dbcontext list [options]
```

??? info "Principais parâmetro"

    ```
    Options:
    --json                                 Show JSON output. Use with --prefix-output to parse programatically.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

### dotnet ef dbcontext scaffold

Gera os códigos de um `DbContext` e tipos de entidade a partir de um banco de dados. Processo conhecido como *scaffolds*. Para que esse comando gere um tipo de entidade, a tabela do banco de dados deve ter uma chave primária.

```bash
dotnet ef dbcontext scaffold [arguments] [options]
```

??? info "Principais parâmetro"

    ```
    Arguments:
    <CONNECTION>  The connection string to the database.
    <PROVIDER>    The provider to use. (E.g. Microsoft.EntityFrameworkCore.SqlServer)

    Options:
    -d|--data-annotations                  Use attributes to configure the model (where possible). If omitted, only the fluent API is used.
    -c|--context <NAME>                    The name of the DbContext. Defaults to the database name.
    --context-dir <PATH>                   The directory to put the DbContext file in. Paths are relative to the project directory.
    -f|--force                             Overwrite existing files.
    -o|--output-dir <PATH>                 The directory to put files in. Paths are relative to the project directory.
    --schema <SCHEMA_NAME>...              The schemas of tables to generate entity types for.
    -t|--table <TABLE_NAME>...             The tables to generate entity types for.
    --use-database-names                   Use table and column names directly from the database.
    --json                                 Show JSON output. Use with --prefix-output to parse programatically.
    -n|--namespace <NAMESPACE>             The namespace to use. Matches the directory by default.
    --context-namespace <NAMESPACE>        The namespace of the DbContext class. Matches the directory by default.
    --no-onconfiguring                     Don't generate DbContext.OnConfiguring.
    --no-pluralize                         Don't use the pluralizer.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

??? example "Exemplos"

    ```bash
    # Aplica scaffold a todos os esquemas e tabelas e coloca os novos arquivos na pasta modelos.
    dotnet ef dbcontext scaffold "Server=(localdb)\mssqllocaldb;Database=Blogging;Trusted_Connection=True;" Microsoft.EntityFrameworkCore.SqlServer -o Models

    # Aplica scaffold apenas a tabelas selecionadas e cria o contexto em uma pasta separada com um nome e namespace especificados.
    dotnet ef dbcontext scaffold "Server=(localdb)\mssqllocaldb;Database=Blogging;Trusted_Connection=True;" Microsoft.EntityFrameworkCore.SqlServer -o Models -t Blog -t Post --context-dir Context -c BlogContext --context-namespace New.Namespace

    # Lê a cadeia de conexão do conjunto de configurações do projeto usando a ferramenta "Secret Manager tool".
    dotnet user-secrets set ConnectionStrings:Blogging "Data Source=(localdb)\MSSQLLocalDB;Initial Catalog=Blogging"
    dotnet ef dbcontext scaffold Name=ConnectionStrings:Blogging Microsoft.EntityFrameworkCore.SqlServer
    ```

### dotnet ef dbcontext script

Gera um script SQL a partir do DbContext. Ignora qualquer migração.

```bash
dotnet ef dbcontext script [options]
```

??? info "Principais parâmetro"

    ```
    Options:
    -o|--output <FILE>                     The file to write the result to.
    -c|--context <DBCONTEXT>               The DbContext to use.
    -p|--project <PROJECT>                 The project to use. Defaults to the current working directory.
    -s|--startup-project <PROJECT>         The startup project to use. Defaults to the current working directory.
    --framework <FRAMEWORK>                The target framework. Defaults to the first one in the project.
    --configuration <CONFIGURATION>        The configuration to use.
    --runtime <RUNTIME_IDENTIFIER>         The runtime to use.
    --msbuildprojectextensionspath <PATH>  The MSBuild project extensions path. Defaults to "obj".
    --no-build                             Don't build the project. Intended to be used when the build is up-to-date.
    -h|--help                              Show help information
    -v|--verbose                           Show verbose output.
    --no-color                             Don't colorize output.
    --prefix-output                        Prefix output with level.
    ```

## Lazy Loading e Eager Loading

### Lazy Loading

*Lazy Loading* é o mecanismo utilizado pelos frameworks de persistência para carregar informações sobre demanda. Esse mecanismo torna as entidades mais leves, pois suas associações são carregadas apenas no momento em que o método que disponibiliza o dado associativo é chamado. Assim quando objetos são retornados por uma consulta, os objetos relacionados não são carregados ao mesmo tempo, ao invés, eles são carregados automaticamente quando a propriedade de navegação for acessada.

O modificador `virtual` é utilizado pelo EF para fazer o *Lazy Loading*, que precisa criar instâncias de proxy que serão substituídas nessas propriedades virtuais.

Por isso tem-se a impressão de que esse objeto está sempre carregado. Mas de fato ele não está. Uma propriedade virtual só é carregado via *lazy loading* no momento em que há a primeira referência a essa propriedade. Nesse momento o EF executa uma nova consulta na base de dados solicitando apenas os dados abaixo da hierarquia do objeto dessa propriedade.

O uso de *Lazy Loading* pode sim trazer problemas de performance. No seu exemplo, se fizer uma consulta que retorna, digamos, 100 Pedidos, sem carregar os clientes explicitamente (*Eager Loading*), ao referenciar qualquer informação de Cliente, pra cada cliente diferente, será feita uma nova consulta na base de dados. Portanto nesse caso você poderia ter 100 novas consultas ao banco de dados.

```c#
# Classe Pedidos
public virtual Cliente Cliente {get; set;}

# ...

var pedidos = db.Pedidos.ToList(); // não traz nenhuma informação de cliente
foreach (var pedido in pedidos) 
{
    var nome = pedido.Cliente.Nome; // aqui é feita a carga por Lazy Loading
}
```

!!! info ""

    O EF cria uma classe derivada da classe persistente denominada proxy para trabalhar internamente. Este é um padrão chamado *dynamic proxy*. O principal intuito dessa classe derivada é a criação de gatilhos para os *getters* e *setters* da classe persistente, assim o ORM pode controlar, quanto, como e se um atributo/propriedade da classe persistente foi alterada, para então poder tomar as devidas ações em relação a isso.

    Assim se um atributo/propriedade não é virtual, você está explicitamente dizendo que o mesmo não pode ser sobrescrito (*override*) em uma classe derivada a classe persistente, sendo assim o ORM não conseguira implementar um proxy para este atributo/propriedade, não podendo consequentemente implementar estes gatilhos, no qual são necessários para fazer o *Lazy Loading* e o *tracking* das entidades.

### Eager Loading

Pra evitar esse efeito do *Lazy Loading* é possível informar que você deseja que o EF faça *Eager Loading* dos clientes usando uma cláusula `Include`.

```c#
# Classe Pedidos
public virtual Cliente Cliente {get; set;}

# ...

var pedidos = db.Pedidos.Include(m => m.Cliente).ToList(); // inclui cliente na query
foreach (var pedido in pedidos) 
{
    var nome = pedido.Cliente.Nome; // os dados de cliente já estão carregados
}
```

Dessa forma a query SQL irá incluir os clientes na consulta.

Nos casos em que não usamos o modificador `virtual` só poderemos acessar o Cliente por *Eager Loading*.

!!! note ""

    O EF usa *Lazy Loading* por default em propriedades marcadas com o modificador virtual, caso contrário ele é desligado e por isso a referência ao objeto é `null` (exceto quando usamos eager loading através de `Include`).

É possível configurar o EF pra que não trabalhe com *Lazy Loading* mesmo quando temos propriedades virtuais.

```c#
public class MeuContexto : DbContext 
{ 
    public MeuContexto () 
    { 
        this.Configuration.LazyLoadingEnabled = false; 
    } 
}
```

## DbContext

A principal classe responsável pela interação com os objetos de dados é a classe `System.Data.Entity.DbContext` (muitas vezes referida como o contexto).

Essa classe de contexto administra os objetos entidades durante o tempo de execução, o que inclui preencher objetos com dados de um banco de dados, controlar alterações, e persistir dados para o banco de dados.

### Tempo de vida

O tempo de vida de um `DbContext` começa quando a instância é criada e termina quando a instância é ‎‎eliminada‎‎. Uma instância `DbContext` é projetada para ser usada para uma única unidade de trabalho (em inglês, *unit-of-work*).

Uma unidade de trabalho mantém o controle de tudo que você faz durante uma transação de negócio‎ que pode afetar o banco de dados. Quando terminar, ela descobre tudo o que precisa ser feito para alterar o banco de dados como resultado do seu trabalho.

Uma unidade de trabalho típica ao usar *Entity Framework Core* envolve:

1. Criação de uma instância `DbContext`.
1. Rastreamento de instâncias de entidade pelo contexto. As entidades são rastreadas por:
    - Serem retornadas de uma consulta.
    - Serem adicionadas ou anexadas ao contexto.
1. As alterações são feitas nas entidades rastreadas conforme necessário para implementar a regra de negócio.
1. `SaveChanges` ou `SaveChangesAsync` é chamada. EF Core detecta as alterações feitas e as grava no banco de dados. 
1. A instância `DbContext` é descartada.

!!! info ""

    - Cada instância `DbContext` deve ser configurada para usar um e apenas um provedor de banco de dados.

    - É muito importante realizar o *dispose* do `DbContext` após o uso. Isso garante que todos os recursos não gerenciados sejam liberados e que quaisquer eventos ou outros *hooks* sejam cancelados para evitar vazamentos de memória caso a instância permaneça referenciada.

    - `DbContext` não é seguro para threads. Não compartilhe contextos entre threads. Certifique-se de aguardar todas as chamadas assíncronas antes de continuar a usar a instância de contexto.

    Uma `InvalidOperationException` lançada pelo código EF pode colocar o contexto em um estado irrecuperável. Essas exceções indicam um erro do programa e não foram projetadas para serem recuperadas.

### DbContext em injeção de dependência

Em muitos aplicativos da web, cada solicitação HTTP corresponde a uma única unidade de trabalho. Isso torna a vinculação do tempo de vida do contexto ao da solicitação um bom padrão para aplicativos da web.

Os aplicativos ASP.NET são configurados usando injeção de dependência. EF pode ser adicionado a esta configuração usando `AddDbContext` no método `ConfigureServices` de em `Startup.cs`. Por exedmplo:

```c#
# Startup.cs

public void ConfigureServices(IServiceCollection services)
{
    services.AddControllers();

    services.AddDbContext<ApplicationDbContext>(
        options => options.UseSqlServer("name=ConnectionStrings:DefaultConnection"));
}
```

Este exemplo registra uma subclasse `DbContext` chamada `ApplicationDbContext` como um serviço com escopo no provedor de serviços de aplicativo ASP.NET (também conhecido como o contêiner de injeção de dependência). Neste exemplo o contexto é configurado para usar o provedor de banco de dados SQL Server e lerá a *string* de conexão da configuração do ASP.NET.

A classe `ApplicationDbContext` deve expor um construtor público com um parâmetro `DbContextOptions<ApplicationDbContext>`. É assim que a configuração de contexto do `AddDbContext` é passada para o `DbContext`. Por exemplo:

```c#
public class ApplicationDbContext : DbContext
{
    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
        : base(options)
    {
    }
}
```

`ApplicationDbContext` pode ser usado em controladores ASP.NET ou outros serviços por meio de injeção de construtor. Por exemplo:

```c#
public class MyController
{
    private readonly ApplicationDbContext _context;

    public MyController(ApplicationDbContext context)
    {
        _context = context;
    }
}
```

O resultado final é uma instância `ApplicationDbContext` criada para cada solicitação e passada ao controlador para realizar uma unidade de trabalho antes de ser *disposed* quando a solicitação termina.

## Referências

- <https://docs.microsoft.com/pt-br/ef/core/cli/dotnet>
- <https://pt.wikipedia.org/wiki/Entity_Framework>
- <http://www.macoratti.net/12/09/ef4_mig1.htm>
- <https://docs.microsoft.com/pt-br/ef/core/dbcontext-configuration>
- <https://pt.stackoverflow.com/questions/52908/qual-a-diferen%C3%A7a-entre-usar-propriedade-virtual-ou-n%C3%A3o-no-ef>
