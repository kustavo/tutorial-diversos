# Introdução

[TOC]

[[_TOC_]]

## Instalação

Página de download: <https://dotnet.microsoft.com/download/dotnet-core>

Adicionar repositório e instalação.

```bash
wget -q https://packages.microsoft.com/config/ubuntu/20.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
sudo sdpkg -i packages-microsoft-prod.deb

sudo apt-get update
sudo apt-get install apt-transport-https
sudo apt-get update
sudo apt-get install dotnet-sdk-3.1
```

### Local da instalação

```bash
/usr/share/dotnet
```

### Configurar variáveis de sistema

```bash
export DOTNET_ROOT=$(dirname $(realpath $(which dotnet)))
export DOTNET_SYSTEM_GLOBALIZATION_INVARIANT=true
```

## Arquivos de configuração

### launchSettings.json

O arquivo `launchSettings.json` é usado apenas pelo `Visual Studio` para o usuário escolher o *environment* atual.

```json
{
  "iisSettings": {
    "windowsAuthentication": false,
    "anonymousAuthentication": true,
    "iisExpress": {
      "applicationUrl": "http://localhost:53836/",
      "sslPort": 0
    }
  },
  "profiles": {
    "IIS Express": {
      "commandName": "IISExpress",
      "launchBrowser": true,
      "environmentVariables": { "ASPNETCORE_ENVIRONMENT": "Development" }
    },
    "Gustavo": {
      "commandName": "Project",
      "launchBrowser": false,
      "environmentVariables": { "ASPNETCORE_ENVIRONMENT": "Gustavo" },
      "applicationUrl": "http://localhost:5000/"
    }
  }
}
```

### launch.json

O arquivo `launch.json` é usando pelo `Visual Studio Code`. O *environment* é definido pela chave `env`.

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": ".NET Core Launch (web)",
            "type": "coreclr",
            "request": "launch",
            "preLaunchTask": "build",
            "program": "${workspaceRoot}/bin/Debug/netcoreapp1.0/TestApp.dll",
            "args": [],
            "cwd": "${workspaceRoot}",
            "stopAtEntry": false,
            "launchBrowser": {
                "enabled": true,
                "args": "${auto-detect-url}",
                "windows": {
                    "command": "cmd.exe",
                    "args": "/C start ${auto-detect-url}"
                },
                "osx": {
                    "command": "open"
                },
                "linux": {
                    "command": "xdg-open"
                }
            },
            "env": {
                "ASPNETCORE_ENVIRONMENT": "Development"
            },
            "sourceFileMap": {
                "/Views": "${workspaceRoot}/Views"
            }
        }
    ]
}
```

### appsettings.json

O arquivo `appsettings.json` define as configurações da aplicação e pode ser definida específica para um ambiente. Basta criar um arquivo `appsettings.<environment>.json`.

```json
{
    "Logging": {
        "LogLevel": {
            "Default": "Information",
            "Microsoft": "Warning",
            "Microsoft.Hosting.Lifetime": "Information"
        }
    },
    "AllowedHosts": "*"
}
```

### web.config

O arquivo `web.config` é usado apenas pelo IIS (Windows).

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <location path="." inheritInChildApplications="false">
    <system.webServer>
      <handlers>
        <add name="aspNetCore" path="*" verb="*" modules="AspNetCoreModuleV2" resourceType="Unspecified" />
      </handlers>
      <aspNetCore processPath=".\MeuConsultorio.Api" stdoutLogEnabled="true" stdoutLogFile=".\logs\stdout" hostingModel="OutOfProcess" >
        <environmentVariables>
          <environmentVariable name="ASPNETCORE_ENVIRONMENT" value="ProducaoLinux"/>
        </environmentVariables>
      </aspNetCore>
    </system.webServer>
  </location>
</configuration>
```

## Criar solução

Criar nova solução

```bash
dotnet new sln -n <nome-solucao>

# Parâmetros
# -n: Por padrão usa o nome do diretório
```

## Criar projeto

Criar projeto tipo `console`.

```bash
dotnet new console -o <projeto>
```

Criar projeto tipo `biblioteca de classes`.

```bash
dotnet new classlib -f <framework> -o <diretorio> -n <nome-projeto>
# Exemplo:
dotnet new classlib -f netcoreapp3.1 -o NerdStore.Catalogo.Domain
```

Criar projeto tipo `ASP.NET Core vazio`.

```bash
dotnet new web -o <projeto>
```

Criar projeto tipo `API Web do ASP.NET Core`.

```bash
dotnet new webapi -o <projeto>
```

Criar projeto tipo `Aplicativo Web ASP.NET Core`.

```bash
dotnet new webapp -o <projeto>
```

Criar projeto tipo `Aplicativo Web ASP.NET Core MVC`.

```bash
dotnet new mvc -o <projeto> -au Individual

# Parâmetros
# -au: Autenticação individual do identity
```

Criar projeto tipo `Xunit`.

```bash
dotnet new xunit -o <projeto>
```

## Criar pasta de projeto

```bash
dotnet new <typeproj> -o <pasta-solucao> -n <nome-pasta>

# Exemplo
dotnet new <typeproj> -o "Services/Catalogo" -n "Catalogo"
```

## Associar projetos

### Associar projeto a solução

```bash
dotnet sln add -s <pasta-solucao> <diretorio-projeto>/<arquivo>.csproj

# Exemplo
dotnet sln add -s Services/Catalogo src/NerdStore.Catalogo.Domain/NerdStore.Catalogo.Domain.csproj
```

### Desassociar projeto a solução

```bash
dotnet sln remove -s <pasta-solucao> <diretorio-projeto>/<arquivo>.csproj

# Exemplo
dotnet sln remove src/NerdStore.Catalogo.Domain/NerdStore.Catalogo.Domain.csproj
```

### Associar projeto a outro projeto

```bash
dotnet add <diretorio-projeto> reference <diretorio-projeto-referenciado>
```

### Desassociar projeto de outro projeto

```bash
dotnet remove <diretorio-projeto> reference <diretorio-projeto-referenciado>

# Exemplo
dotnet remove Projeto.csproj reference src/NerdStore.Catalogo.Domain/NerdStore.Catalogo.Domain.csproj
```

## Listar projetos da solução

```bash
dotnet sln list 
```

## Compilar solução ou projeto

```bash
dotnet build
```

## Publicar solução

Gerar o build para produção.

As implantações de aplicativo autocontatidas do .NET Core incluem as bibliotecas e o runtime do .NET Core. A partir do SDK do .NET Core 2.1 (.NET Core 2.1.300), uma implantação de aplicativo autocontida publica o runtime de patch mais recente no computador.

[Lista de runtimes](https://github.com/dotnet/runtime/blob/master/src/libraries/pkg/Microsoft.NETCore.Platforms/runtime.json)

```bash
dotnet publish -f <framework> -c <configuracao> -o <diretorio-saida> --self-contained true -r <rid-runtime-id> -p:<propriedade-nome>=<valor>

# Exemplo
dotnet publish -f netcoreapp2.2 -o ~/MeuDeploy --self-contained true -r ubuntu.18.04-x64 p:EnvironmentName=ProducaoLinux
```

## Rodar a solução ou projeto publicado

```bash
dotnet <nome-solucao-ou-projeto>.dll --environment <nome-environment> --server.urls http://0.0.0.0:5000
```

## Instalar as dependências (pacotes) do projeto

```bash
dotnet restore
```

## Banco de dados

O EF Core (`Entity Framework Core`) é um O/RM (mapeador relacional de objeto) que permite que os desenvolvedores de .NET trabalhem com um banco de dados usando objetos .NET. Elimina a necessidade da maioria do código de acesso a dados que os desenvolvedores geralmente precisam gravar.

### Instalar o CLI do EF

```bash
dotnet tool install --global dotnet-ef
```

### Adicionar o pacote do EF

```bash
dotnet add package Microsoft.EntityFrameworkCore
dotnet add package Microsoft.EntityFrameworkCore.Design
```

### Instalar o provedor do banco para o EF

```bash
dotnet add <projeto> package <pacote-provedor-banco>

# Exemplo PostgreSQL:
# dotnet add package Npgsql.EntityFrameworkCore.PostgreSQL
```

O argumento `<projeto>` é opcional. Pode basear no diretório corrente. Lista de provedores de bancos de dados: [Veja mais](https://docs.microsoft.com/pt-br/ef/core/providers/index?tabs=dotnet-core-cli)

Exemplo de uso:

```dotnet
public class MeuContexto : DbContext {

    // ...

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseNpgsql("Host=localhost;Port=5432;Database=exemplo-migrations2;Username=postgres;Password=postgres");
    }
}
```

### Criar uma migration

```bash
dotnet ef migrations add <nome> -p <caminho-projeto-migrations> -s <caminho-projeto-startup> -c <nome-contexto> --framework <netcoreapp#.#>
```

### Remover migration

Remove a última *migration* ainda **não aplicada ao banco**.

```bash
dotnet ef migrations remove
```

Remover todas migrations

```bash
dotnet ef database update 0
dotnet ef migrations remove
```

### Reverter uma migration

Reverter uma ou várias *migrations*, até a migrations informada.

```bash
dotnet ef database update <nome>
```

### Lista as migrations

```bash
dotnet ef migrations list
```

### Gerar scripts SQL

Gera um script SQL das aplicações que serão feitas no banco.

```bash
dotnet ef migrations script -from <nome> -to <nome>
```

Os argumentos `<from>` e `<to>` são opcionais. Por padão será pego da primeira até a última *migration*.

### Atualizar o banco

Aplica as *migrations* no banco. Se o banco não existir, também será criado.

```bash
dotnet ef database update
```

### Aplicar migrações em runtime

```dotnet
<contexto>.Database.Migrate();
```

Exemplo chamando no `Main`.

```dotnet
class Program
    {
        static void Main(string[] args)
        {
            // ...
            using var context = new BloggingContext();
            context.Database.Migrate();
        }
    }
```

Não chame `EnsureCreated()` antes de `Migrate()`. O `EnsureCreated()` ignora as Migrações para criar o esquema e causa falha no `Migrate()`.

## Pacotes Nuget

### Instalar

```bash
dotnet add <projeto> package <pacote> <versao>

# exemplo: dotnet add ~/ToDo.csproj package Microsoft.Azure.DocumentDB.Core -v 1.0.0
```

Os argumento `<projeto>` e `<versao>` são opcionais. Pode basear no diretório corrente.

### Remover

```bash
dotnet remove <projeto> package <pacote>
```

O argumento `<projeto>` é opcional. Pode basear no diretório corrente.

### Restaurar

```bash
dotnet restore <projeto|solucao>
```

O argumento `<projeto|solucao>` é opcional. Pode basear no diretório corrente.

## Erros

## It is being used by another process

Erro de concorrencia de processos

```bash
dotnet build-server shutdown
```

Se não funcionar, matar todas os processos `dotnet`

```bash
killall dotnet
```

Se o erro ocorrer em `dotnet publish`, pode ser que o arquivo já está sendo copiado por outra thread, basta ignorar o erro.

### The required library libhostfxr.so could not be found

Solução, definir a variável de sistema `DOTNET_ROOT`.

```bash
export DOTNET_ROOT=$(dirname $(realpath $(which dotnet)))
```

### Couldn't find a valid ICU package installed on the system

Solução, definir a variável de sistema `DOTNET_SYSTEM_GLOBALIZATION_INVARIANT`. [Veja mais](https://github.com/dotnet/corefx/blob/8245ee1e8f6063ccc7a3a60cafe821d29e85b02f/Documentation/architecture/globalization-invariant-mode.md)

```bash
export DOTNET_SYSTEM_GLOBALIZATION_INVARIANT=true
```
