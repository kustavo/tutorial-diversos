# Interface de linha de comando

A **Command-Line Interface - CLI**  (interface de linha de comando) do .NET é uma ferramenta multi plataforma para desenvolvimento, criação, execução e publicação de aplicações .NET. A CLI do .NET está incluída no SDK do .NET.

!!! note ""
    Para ver os argumentos e opções de um comando, execute o comando:

    ```bash
    dotnet <comando> -h|--help
    ```

## dotnet

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet)

Para obter informações sobre os comandos disponíveis e o ambiente:

```bash
dotnet [--version] [--info] [--list-runtimes] [--list-sdks]

dotnet -h|--help
```

??? example "Exemplos"

    ```bash
    # Listar comandos
    dotnet -h

    # Versão corrente (geralmente a mais recente instalada)
    dotnet --version

    # Litar os Sdks instalados e o local de instalação
    dotnet --list-sdks
    ```

Para executar um comando (requer a instalação do SDK):

```bash
dotnet <COMMAND> [-d|--diagnostics] [-h|--help] [--verbosity <LEVEL>]
    [command-options] [arguments]
```

??? info "Principais parâmetros"

    - `-d|--diagnostics`: Habilita a saída de diagnóstico.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic].
    - `-h|--help`: Imprime a documentação para um determinado comando.

Para executar um aplicativo:

```bash
dotnet [--additionalprobingpath <PATH>] [--additional-deps <PATH>]
    [--fx-version <VERSION>]  [--roll-forward <SETTING>]
    <PATH_TO_APPLICATION> [arguments]

dotnet exec [--additionalprobingpath] [--additional-deps <PATH>]
    [--fx-version <VERSION>]  [--roll-forward <SETTING>]
    <PATH_TO_APPLICATION> [arguments]
```

## dotnet build

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-build)

Compila um projeto e todas as suas dependências.

```bash
dotnet build [<PROJECT>|<SOLUTION>] [-c|--configuration <CONFIGURATION>]
    [-f|--framework <FRAMEWORK>] [--force] [--interactive] [--no-dependencies]
    [--no-incremental] [--no-restore] [--nologo] [-o|--output <OUTPUT_DIRECTORY>]
    [-r|--runtime <RUNTIME_IDENTIFIER>] [--source <SOURCE>]
    [-v|--verbosity <LEVEL>] [--version-suffix <VERSION_SUFFIX>]

dotnet build -h|--help
```

??? info "Principais parâmetros"

    - `-f|--framework <FRAMEWORK>`: Especifica a versão do framework (net5.0, netcoreapp3.1, netcoreapp3.0). Padrão: última versão instalada.
    - `--force`: Forçará todas as dependências a serem resolvidas mesmo se última restauração tiver sido bem-sucedida (o mesmo que a exclusão do arquivo `project.assets.json`).
    - `--no-dependencies`: Ignora as referências P2P (projeto para projeto) e compila apenas o projeto raiz especificado.
    - `--no-incremental`: Marca o build como não segura para build incremental. Esse sinalizador desativa a compilação incremental e força uma nova recompilação do grafo de dependência do projeto.
    - `--no-restore`: Não executa uma restauração implícita durante o build.
    - `-o|--output <OUTPUT_DIRECTORY>`: Diretório no qual os binários compilados são colocados. Se não for especificado, o caminho padrão será `./bin/<configuration>/<framework>/`. Para projetos com várias estruturas de destino (por meio da propriedade `TargetFrameworks`), também é necessário definir `--framework` quando você especifica essa opção.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhamento do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O padrão é minimal.

O comando `dotnet build` compila o projeto e suas dependências em um conjunto de binários. Os binários incluem o código do projeto em arquivos de IL (linguagem intermediária) com uma extensão `.dll`. Dependendo do tipo de projeto e das configurações, outros arquivos podem ser incluídos, como:

- Um executável que pode ser usado para executar o aplicativo, se o tipo de projeto for um executável.
- Arquivos de símbolo usados para depuração com uma extensão `.pdb`.
- Um arquivo `.deps.json`, que lista as dependências do aplicativo ou da biblioteca.
- Um arquivo `.runtimeconfig.json`, que especifica o *runtime* compartilhado e sua versão para um aplicativo.
- Outras bibliotecas das quais o projeto depende (por meio de referências de projeto ou referências de pacote NuGet).

Para projetos executáveis que visam versões anteriores ao .NET Core 3,0, as dependências de biblioteca do NuGet normalmente **não** são copiadas para o diretório de saída. Eles são resolvidos no diretório de pacotes globais do NuGet em tempo de execução. Portanto, o produto de dotnet build não está pronto para ser transferido para outro computador para execução. Para criar uma versão do aplicativo que pode ser implantada, você precisa publicá-la (por exemplo, com o comando `dotnet Publish`).

Para projetos executáveis destinados ao .NET Core 3,0 e posterior, as dependências de biblioteca são copiadas para o diretório de saída. Isso significa que, se não houver nenhuma outra lógica específica de publicação (como os projetos Web têm), a saída da compilação deverá ser implantável.

<h3> Saída de biblioteca ou executável </h3>

O fato de o projeto ser executável ou não é determinado pela propriedade `<OutputType>` do arquivo de projeto. O seguinte exemplo mostra um projeto que produz um código executável:

```xml
<PropertyGroup>
  <OutputType>Exe</OutputType>
</PropertyGroup>
```

Para produzir uma biblioteca, omita a propriedade `<OutputType>` ou altere seu valor para `Library`. A DLL de IL para uma biblioteca não contém pontos de entrada e não pode ser executada.

<h3> MSBuild </h3>

O comando `dotnet build` usa o MSBuild para compilar o projeto e, portanto, dá suporte a builds paralelos e incrementais.

Além das próprias opções, o comando `dotnet build` também aceita opções do MSBuild, como `-p` para configurar propriedades ou `-l` para definir um *logger*.

A execução `dotnet build` é equivalente à execução `dotnet msbuild -restore`, no entanto, o detalhamento padrão da saída é diferente.

## dotnet clean

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-clean)

Limpa a saída de um projeto.

```bash
dotnet clean [<PROJECT>|<SOLUTION>] [-c|--configuration <CONFIGURATION>]
    [-f|--framework <FRAMEWORK>] [--interactive]
    [--nologo] [-o|--output <OUTPUT_DIRECTORY>]
    [-r|--runtime <RUNTIME_IDENTIFIER>] [-v|--verbosity <LEVEL>]

dotnet clean -h|--help
```

O comando `dotnet clean` limpará a saída da compilação anterior. Apenas as saídas criadas durante a compilação são limpas. As pastas de saídas intermediária (obj) e final (bin) serão limpas.

## dotnet msbuild

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-msbuild)

Compila um projeto e todas as suas dependências. Um arquivo de solução ou de projeto pode precisar ser especificado se houver vários.

```bash
dotnet msbuild <MSBUILD_ARGUMENTS>

dotnet msbuild -h
```

O comando `dotnet msbuild` permite o acesso a um MSBuild totalmente funcional.

O comando tem exatamente os mesmos recursos que o CLI do MSBuild existente para projetos no estilo SDK. As opções são todas iguais.

O comando `dotnet build` é equivalente ao comando `dotnet msbuild -restore`. Quando você não quiser compilar o projeto e tiver um destino específico que deseja executar, use `dotnet build` ou `dotnet msbuild` e especifique o destino.

??? example "Exemplos"

    ```bash
    # Compile um projeto e suas dependências
    dotnet msbuild

    # Compile um projeto e suas dependências usando a configuração da Versão
    dotnet msbuild -property:Configuration=Release

    # Execute o destino de publicação e publique para o RID "osx.10.11-x64"
    dotnet msbuild -target:Publish -property:RuntimeIdentifiers=osx.10.11-x64

    # Ver o projeto inteiro com todos os destinos incluídos pelo SDK
    dotnet msbuild -preprocess
    # Ou
    dotnet msbuild -preprocess:<fileName>.xml
    ```

## dotnet new

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-new)

Cria um novo projeto, arquivo de configuração ou solução com base no modelo especificado.

```bash
dotnet new <TEMPLATE> [--dry-run] [--force] [-i|--install {PATH|NUGET_ID}]
    [-lang|--language {"C#"|"F#"|VB}] [-n|--name <OUTPUT_NAME>]
    [--nuget-source <SOURCE>] [-o|--output <OUTPUT_DIRECTORY>]
    [-u|--uninstall] [--update-apply] [--update-check] [Template options]
```

??? info "Principais parâmetros"

    - `-n|--name`: nome da solução/projeto/config. Padrão: nome do diretório.
    - `-o|--output`: destino da solução/projeto/config. Padrão: diretório corrente.
    - `-f|--framework`: especifica a versão do framework (net5.0, netcoreapp3.1, netcoreapp3.0). Padrão: última versão instalada.
    - `<TEMPLATE>`: estrutura a ser criada.
        - `sln`: solução
        - `console`: projeto simples, entrada/saída via console.
        - `classlib`: projeto que contém apenas tipos e métodos que serão chamados por outro aplicativo.
        - `web`: projeto web vazio, sem classe Controller.
        - `webapi`: projeto web com uma classe Controller RESTful.
            - `--no-https`: desabilitar https.
        - `mvc`: projeto web com a estrutura MVC.
        - `webapp, razor`: projeto web com a estrutura MVC e páginas Razor.

??? example "Exemplos"

    ```bash
    # Criar arquivo da solução
    dotnet new sln -n <nome> -o <destino>

    # Criar projeto tipo "console"
    dotnet new console -n <nome> -o <destino> -f <framework {net5.0|netcoreapp3.1|netcoreapp3.0}>

    # Criar projeto tipo "biblioteca de classes"
    dotnet new classlib -n <nome> -o <destino>

    # Criar projeto tipo "ASP.NET vazio"
    dotnet new web -n <nome> -o <destino>

    # Criar projeto tipo "ASP.NET Web API" sem https
    dotnet new webapi --no-https -n <nome> -o <destino>

    # Criar projeto tipo "Aplicação Web ASP.NET MVC" com suporte a funcionalidade de login.
    dotnet new mvc -n <nome> -o <destino> -au Individual

    # Criar projeto com a ferramenta de teste "Xunit".
    dotnet new xunit -n <nome> -o <destino>
    ```

## dotnet nuget

### dotnet nuget delete

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-delete)

Exclui ou retira da lista um pacote do servidor.

```bash
dotnet nuget delete [<PACKAGE_NAME> <PACKAGE_VERSION>] [--force-english-output]
    [--interactive] [-k|--api-key <API_KEY>] [--no-service-endpoint]
    [--non-interactive] [-s|--source <SOURCE>]

dotnet nuget delete -h|--help
```

??? info "Principais parâmetros"

    - `PACKAGE_NAME`: Nome/ID do pacote a ser excluído.
    - `PACKAGE_VERSION`: Versão do pacote a ser excluído.
    - `--force-english-output`: Força a execução do aplicativo usando uma cultura invariável com base em inglês.
    - `-k|--api-key <API_KEY>`: A chave da API para o servidor.
    - `--no-service-endpoint`: Não acrescenta "api/v2/package" à URL de origem.
    - `--non-interactive`: Não solicita entrada do usuário ou confirmações.
    - `-s|--source <SOURCE>`: Especifica a URL do servidor.

O comando `dotnet nuget delete` exclui ou retira da lista um pacote do servidor. Caso seja `nuget.org`, a ação remove o pacote da lista.

??? example "Exemplos"

    ```bash
    # Exclui a versão 1.0 do pacote Microsoft.AspNetCore.Mvc
    dotnet nuget delete Microsoft.AspNetCore.Mvc 1.0

    # Exclui a versão 1.0 do pacote Microsoft.AspNetCore.Mvc, não solicita ao usuário credenciais ou outra entrada
    dotnet nuget delete Microsoft.AspNetCore.Mvc 1.0 --non-interactive
    ```

### dotnet nuget locals

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-locals)

Limpa ou lista os recursos locais do NuGet.

```bash
dotnet nuget locals <CACHE_LOCATION> [(-c|--clear)|(-l|--list)] [--force-english-output]

dotnet nuget locals -h|--help
```

??? info "Principais parâmetros"

    - `CACHE_LOCATION`: O local do cache a ser listado ou limpo. Aceita um dos seguintes valores:
        - all: Indica que a operação especificada aplica-se a todos os tipos cache: o cache de solicitação http, cache de pacotes globais e o cache temporário.
        - http-cache: Indica que a operação especificada aplica-se apenas ao cache de solicitação http.
        - global-packages: Indica que a operação especificada aplica-se apenas ao cache de pacotes globais.
        - temp: Indica que a operação especificada aplica-se apenas ao cache temporário.
    - `--force-english-output`: Força a execução do aplicativo usando uma cultura invariável com base em inglês.
    - `-c|--clear`: Executa uma operação de limpeza no tipo de cache especificado. O conteúdo dos diretórios de cache é excluído recursivamente.
    - `-l|--list`: Exibir a localização do tipo de cache especificado.

O comando `dotnet nuget locals` limpa ou lista os recursos locais do NuGet no cache de solicitação HTTP, cache temporário ou pasta de pacotes globais em todo o computador.

??? example "Exemplos"

    ```bash
    # Exibe os caminhos de todos os diretórios de cache local (cache de http, cache de pacotes globais e cache temporário):
    dotnet nuget locals all -l

    # Exibe o caminho para o diretório local do cache de http:
    dotnet nuget locals http-cache --list

    # Limpa todos os arquivos dos diretórios de cache local 
    dotnet nuget locals all --clear

    # Limpa todos os arquivos no diretório local de cache de pacotes globais:
    dotnet nuget locals global-packages -c

    # Limpa todos os arquivos no diretório local de cache temporário:
    dotnet nuget locals temp -c
    ```

### dotnet nuget push

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-push)

Envia um pacote ao servidor e os publica.

```bash
dotnet nuget push [<ROOT>] [-d|--disable-buffering] [--force-english-output]
    [--interactive] [-k|--api-key <API_KEY>] [-n|--no-symbols]
    [--no-service-endpoint] [-s|--source <SOURCE>] [--skip-duplicate]
    [-sk|--symbol-api-key <API_KEY>] [-ss|--symbol-source <SOURCE>]
    [-t|--timeout <TIMEOUT>]

dotnet nuget push -h|--help
```

??? info "Principais parâmetros"

    - `ROOT`: Especifica o caminho do arquivo para o pacote a ser enviado por push.
    - `-d|--disable-buffering`: Desabilita o buffer durante o push para um servidor HTTP(S) a fim de reduzir o uso de memória.
    - `--force-english-output`: Força a execução do aplicativo usando uma cultura invariável com base em inglês.
    - `-k|--api-key <API_KEY>`: A chave da API para o servidor.
    - `-n|--no-symbols`: Não envia símbolos por push (mesmo se estiver presente).
    - `--no-service-endpoint`: Não acrescenta "api/v2/package" à URL de origem.
    - `-s|--source <SOURCE>`: O NuGet identifica uma fonte UNC ou de pasta local e simplesmente copia o arquivo lá em vez de enviá-lo usando HTTP.
    - `--skip-duplicate`: Ao enviar vários pacotes para um servidor HTTP (S), trata qualquer resposta "409 Conflict" como um aviso para que o envio possa continuar.
    - `-sk|--symbol-api-key <API_KEY>`: A chave da API para o servidor de símbolos.
    - `-ss|--symbol-source <SOURCE>`: Especifica a URL do servidor de símbolos.
    - `-t|--timeout <TIMEOUT>`: Especifica o tempo limite em segundos para enviar para um servidor. O padrão é 300 segundos.

O comando `dotnet nuget push` envia um pacote ao servidor e os publica. O comando push usa o servidor e detalhes de credencial encontradas no(s) arquivo(s) de configuração do sistema NuGet. A configuração padrão do NuGet é obtida ao carregar `%AppData%\NuGet\NuGet.config` (Windows) ou `$HOME/.local/share` (Linux/macOS) e, em seguida, carrega qualquer `nuget.config` ou `.nuget\nuget.config` iniciando pela raiz da unidade até o diretório atual.

O comando envia um pacote existente. Ele não cria um pacote. Para criar um pacote, use `dotnet pack`.

??? example "Exemplos"

    ```bash
    #  Envie foo.nupkg para a fonte padrão especificada no arquivo de configuração do NuGet usando uma chave de API
    dotnet nuget push foo.nupkg -k 4003d786-cc37-4004-bfdf-c4f3e8ef9b3a

    # Envie o foo.nupkg para o servidor do NuGet oficial, especificando uma chave de API
    dotnet nuget push foo.nupkg -k 4003d786-cc37-4004-bfdf-c4f3e8ef9b3a -s https://api.nuget.org/v3/index.json

    # Envia foo.nupkg à https://customsource, especificando uma chave de API
    dotnet nuget push foo.nupkg -k 4003d786-cc37-4004-bfdf-c4f3e8ef9b3a -s https://customsource/

    # Envie foo.nupkg para a fonte padrão especificada no arquivo de configuração do NuGet:
    dotnet nuget push foo.nupkg

    # Envie foo.Symbols.nupkg para a fonte de símbolos padrão:
    dotnet nuget push foo.symbols.nupkg

    # Envie todos os arquivos .nupkg no diretório atual para a fonte padrão especificada no arquivo de configuração do NuGet
    dotnet nuget push "*.nupkg"
    ```

### dotnet nuget add source

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-add-source)

Adicionar uma origem do NuGet.

```bash
dotnet nuget add source <PACKAGE_SOURCE_PATH> [--name <SOURCE_NAME>] [--username <USER>]
    [--password <PASSWORD>] [--store-password-in-clear-text]
    [--valid-authentication-types <TYPES>] [--configfile <FILE>]

dotnet nuget add source -h|--help
```

??? info "Principais parâmetros"

    - `PACKAGE_SOURCE_PATH`: Caminho para a origem do pacote.
    - `--configfile <FILE>`: O arquivo de configuração do NuGet. Se especificado, somente as configurações desse arquivo serão usadas. Se não for especificado, a hierarquia de arquivos de configuração do diretório atual será usada.
    - `-n|--name <SOURCE_NAME>`: Nome da origem.
    - `-p|--password <PASSWORD>`: Senha a ser usada ao conectar-se a uma fonte autenticada.
    - `--store-password-in-clear-text`: Habilita o armazenamento de credenciais de origem do pacote portátil desabilitando a criptografia de senha.
    - `-u|--username <USER>`: Nome de usuário a ser usado ao conectar a uma fonte autenticada.
    - `--valid-authentication-types <TYPES>`: Lista separada por vírgulas de tipos de autenticação válidos para esta fonte.

O comando `dotnet nuget add source` adiciona uma nova fonte de pacote aos arquivos de configuração NuGet.

??? example "Exemplos"

    ```bash
    # Adicionar nuget.org como uma fonte
    dotnet nuget add source https://api.nuget.org/v3/index.json -n nuget.org

    # Adicionar c:\packages como uma fonte local
    dotnet nuget add source c:\packages

    # Adicione uma fonte que precisa de autenticação
    dotnet nuget add source https://someServer/myTeam -n myTeam -u myUsername -p myPassword --store-password-in-clear-text

    # Adicione uma fonte que precisa de autenticação (em seguida, instale o provedor de credenciais)
    dotnet nuget add source https://azureartifacts.microsoft.com/myTeam -n myTeam
    ```

### dotnet nuget disable source

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-disable-source)

Desabilita uma origem do NuGet.

```bash
dotnet nuget disable source <NAME> [--configfile <FILE>]

dotnet nuget disable source -h|--help
```

??? info "Principais parâmetros"

    - `NAME`: Nome da origem.
    - `--configfile <FILE>`: O arquivo de configuração do NuGet. Se especificado, somente as configurações desse arquivo serão usadas. Se não for especificado, a hierarquia de arquivos de configuração do diretório atual será usada.

O comando `dotnet nuget disable source` desabilita uma fonte existente em seus arquivos de configuração do NuGet.

??? example "Exemplos"

    ```bash
    # Desabilita uma fonte com o nome de "mySource"
    dotnet nuget disable source mySource

    ```

### dotnet nuget enable source

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-enable-source)

Habilita uma origem do NuGet.

```bash
dotnet nuget enable source <NAME> [--configfile <FILE>]

dotnet nuget enable source -h|--help
```

??? info "Principais parâmetros"

    - `NAME`: Nome da origem.
    - `--configfile <FILE>`: O arquivo de configuração do NuGet. Se especificado, somente as configurações desse arquivo serão usadas. Se não for especificado, a hierarquia de arquivos de configuração do diretório atual será usada.

O comando `dotnet nuget enable source` habilita uma fonte existente em seus arquivos de configuração do NuGet.

??? example "Exemplos"

    ```bash
    # Habilita uma fonte com o nome de "mySource"
    dotnet nuget enable source mySource
    ```

### dotnet nuget list source

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-list-source)

Lista todas as fontes do NuGet configuradas.

```bash
dotnet nuget list source [--format [Detailed|Short]] [--configfile <FILE>]

dotnet nuget list source -h|--help
```

??? info "Principais parâmetros"

    - `--configfile <FILE>`: O arquivo de configuração do NuGet. Se especificado, somente as configurações desse arquivo serão usadas. Se não for especificado, a hierarquia de arquivos de configuração do diretório atual será usada.
    - `--format [Detailed|Short]`: O formato da saída: Detailed (o padrão) e Short.

O comando `dotnet nuget list source` lista todas as fontes existentes dos arquivos de configuração do NuGet.

??? example "Exemplos"

    ```bash
    # Listar fontes configuradas do diretório atual
    dotnet nuget list source
    ```

### dotnet nuget remove source

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-remove-source)

Remover uma origem do NuGet.

```bash
dotnet nuget remove source <NAME> [--configfile <FILE>]

dotnet nuget remove source -h|--help
```

??? info "Principais parâmetros"

    - `NAME`: Nome da origem.
    - `--configfile <FILE>`: O arquivo de configuração do NuGet. Se especificado, somente as configurações desse arquivo serão usadas. Se não for especificado, a hierarquia de arquivos de configuração do diretório atual será usada.

O comando `dotnet nuget remove source` remove uma fonte existente dos arquivos de configuração do NuGet.

??? example "Exemplos"

    ```bash
    # Remova uma fonte com o nome de "mySource"
    dotnet nuget remove source mySource
    ```

### dotnet nuget update source

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-update-source)

Atualizar uma origem do NuGet.

```bash
dotnet nuget update source <NAME> [--source <SOURCE>] [--username <USER>]
    [--password <PASSWORD>] [--store-password-in-clear-text]
    [--valid-authentication-types <TYPES>] [--configfile <FILE>]

dotnet nuget update source -h|--help
```

??? info "Principais parâmetros"

NAME: Nome da origem.

    - `--configfile <FILE>`: O arquivo de configuração do NuGet. Se especificado, somente as configurações desse arquivo serão usadas. Se não for especificado, a hierarquia de arquivos de configuração do diretório atual será usada.
    - `-p|--password <PASSWORD>`: Senha a ser usada ao conectar-se a uma fonte autenticada.
    - `-s|--source <SOURCE>`: Caminho para a origem do pacote.
    - `--store-password-in-clear-text`: Habilita o armazenamento de credenciais de origem do pacote portátil desabilitando a criptografia de senha.
    - `-u|--username <USER>`: Nome de usuário a ser usado ao conectar a uma fonte autenticada.
    - `--valid-authentication-types <TYPES>`: Lista separada por vírgulas de tipos de autenticação válidos para esta fonte.

O comando `dotnet nuget update source` atualiza uma origem existente nos arquivos de configuração do NuGet.

??? example "Exemplos"

    ```bash
    # Atualize uma fonte com o nome de "mySource"
    dotnet nuget update source mySource --source c:\packages
    ```

### dotnet nuget verify

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-nuget-verify)

Verifica um pacote NuGet assinado.

```bash
dotnet nuget verify [<package-path(s)>]
    [--all]
    [--certificate-fingerprint <FINGERPRINT>]
    [-v|--verbosity <LEVEL>]

dotnet nuget verify -h|--help
```

??? info "Principais parâmetros"

    - `package-path(s)`: Especifica o caminho de arquivo para os pacotes a serem verificados. 
    - `--all`: Especifica que todas as verificações possíveis devem ser executadas nos pacotes. Por padrão, somente assinaturas são verificadas.
    - `--certificate-fingerprint <FINGERPRINT>`: Verifique se o certificado de signatário corresponde a uma das impressões digitais SHA256 especificadas.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhamento do MSBuild. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O padrão é normal.

O comando `dotnet nuget verify` verifica um pacote NuGet assinado.

??? example "Exemplos"

    ```bash
    # Verifique foo.nupkg:
    dotnet nuget verify foo.nupkg

    # Verifique vários pacotes NuGet-foo.nupkg e todos os arquivos .nupkg no diretório especificado
    dotnet nuget verify foo.nupkg c:\mydir\*.nupkg

    # Verifique se a assinatura foo.nupkg corresponde a uma das impressões digitais do certificado especificado
    dotnet nuget verify foo.nupkg --certificate-fingerprint CE40881FF5F0AD3E58965DA20A9F571EF1651A56933748E1BF1C99E537C4E039 --certificate-fingerprint EC10992GG5F0AD3E58965DA20A9F571EF1651A56933748E1BF1C99E537C4E027
    ```

## dotnet pack

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-pack)

Empacota o código em um pacote NuGet.

```bash
 dotnet pack [<PROJECT>|<SOLUTION>] [-c|--configuration <CONFIGURATION>]
    [--force] [--include-source] [--include-symbols] [--interactive]
    [--no-build] [--no-dependencies] [--no-restore] [--nologo]
    [-o|--output <OUTPUT_DIRECTORY>] [--runtime <RUNTIME_IDENTIFIER>]
    [-s|--serviceable] [-v|--verbosity <LEVEL>]
    [--version-suffix <VERSION_SUFFIX>]

dotnet pack -h|--help
```

??? info "Principais parâmetros"

    - `PROJECT|SOLUTION`: O caminho do projeto ou solução a ser publicada. Se o diretório não for especificado, o padrão será o diretório atual.
    - `-c|--configuration <CONFIGURATION>`: Define a configuração da compilação. O padrão é *Debug*.
    - `--force`: Forçará todas as dependências a serem resolvidas mesmo se última restauração tiver sido bem-sucedida (o mesmo que a exclusão do arquivo `project.assets.json`).
    - `--include-source`: Inclui os pacotes de depuração do NuGet, além dos pacotes NuGet regulares no diretório de saída. Os arquivos de origem são incluídos no diretório `src` dentro do pacote de símbolos.
    - `--include-symbols`: Inclui os pacotes de depuração do NuGet, além dos pacotes NuGet regulares no diretório de saída.
    - `--no-build`: Não compila o projeto antes da publicação. Também define o sinalizador `--no-restore` implicitamente.
    - `--no-dependencies`: Ignora as referências projeto a projeto e só restaura o projeto raiz.
    - `--no-restore`: Não executa uma restauração implícita ao executar o comando.
    - `-o|--output <OUTPUT_DIRECTORY>`: Coloca os pacotes compilados no diretório especificado.
    - `-r|--runtime <RUNTIME_IDENTIFIER>`: Restaurar os pacotes o aplicativo para um determinado runtime.
    - `-s|--serviceable`: Define o sinalizador operacional no pacote. [Veja mais aqui](https://aka.ms/nupkgservicing).
    - `--version-suffix <VERSION_SUFFIX>`: Define o valor da propriedade do MSBuild `$(VersionSuffix)` no projeto.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O valor padrão é minimal.

O comando `dotnet pack` compila o projeto e cria pacotes NuGet. O resultado desse comando é um pacote NuGet (ou seja, um arquivo `.nupkg`).

Se você quiser gerar um pacote que contém os símbolos de depuração, terá duas opções disponíveis:

- `--include-symbols`: Cria o pacote de símbolos.
- `--include-source`: Cria o pacote de símbolos em um diretório `src` contendo os arquivos de origem.

As dependências do NuGet do projeto empacotado são adicionadas ao arquivo `.nuspec` para que possam ser resolvidas apropriadamente quando o pacote for instalado. As referências projeto para projeto não são empacotadas dentro do projeto. Você precisa ter um pacote por projeto.

Por padrão, `dotnet pack` compila primeiro o projeto. Se você quiser evitar esse comportamento, passe a opção `--no-build`. Com frequência, essa opção é útil em cenários de build de CI (integração contínua) nos quais você sabe que o código foi compilado anteriormente.

!!! note ""
    Em alguns casos, a compilação implícita não pode ser executada. Isso pode ocorrer quando `GeneratePackageOnBuild` é definido, para evitar uma dependência cíclica entre destinos de compilação e de pacote. A compilação também pode falhar se houver um arquivo bloqueado ou outro problema.

Você pode fornecer as propriedades de MSBuild para o comando `dotnet pack` para o processo de empacotamento. [Veja mais aqui](https://docs.microsoft.com/pt-br/nuget/reference/msbuild-targets#pack-target).

Projetos Web não são empacotáveis por padrão. Para substituir o comportamento padrão, adicione a seguinte propriedade ao seu arquivo `.csproj`:

```xml
<PropertyGroup>
   <IsPackable>true</IsPackable>
</PropertyGroup>
```

??? example "Exemplos"

    ```bash
    # Empacota o projeto no diretório atual e coloque os pacotes resultantes no diretório "nupkgs"
    dotnet pack --output nupkgs

    # Empacote o projeto app1
    dotnet pack ~/projects/app1/project.csproj

    # Com o sufixo da versão do projeto configurado como "<VersionSuffix>$(VersionSuffix)</VersionSuffix>"
    # no arquivo .csproj, empacote o projeto atual e atualize a versão do pacote resultante com o sufixo especificado
    dotnet pack --version-suffix "ci-1234"

    # Defina a versão do pacote como 2.1.0 com a propriedade MSBuild PackageVersion
    dotnet pack -p:PackageVersion=2.1.0

    # Empacote o projeto para um determinado framework
    dotnet pack -p:TargetFrameworks=net45

    # Empacotar o projeto e usar um runtime específico (Windows 10) para a operação de restauração
    dotnet pack --runtime win10-x64

    # Empacotar o projeto usando um arquivo ".nuspec"
    dotnet pack ~/projects/app1/project.csproj -p:NuspecFile=~/projects/app1/project.nuspec -p:NuspecBasePath=~/projects/app1/nuget
    ```

## dotnet publish

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-publish)

Publica o aplicativo e suas dependências em um diretório para implantação em um sistema de hospedagem.

```bash
dotnet publish [<PROJECT>|<SOLUTION>] [-c|--configuration <CONFIGURATION>]
    [-f|--framework <FRAMEWORK>] [--force] [--interactive]
    [--manifest <PATH_TO_MANIFEST_FILE>] [--no-build] [--no-dependencies]
    [--no-restore] [--nologo] [-o|--output <OUTPUT_DIRECTORY>]
    [-p:PublishReadyToRun=true] [-p:PublishSingleFile=true] [-p:PublishTrimmed=true]
    [-r|--runtime <RUNTIME_IDENTIFIER>] [--self-contained [true|false]]
    [--no-self-contained] [-v|--verbosity <LEVEL>]
    [--version-suffix <VERSION_SUFFIX>]

dotnet publish -h|--help
```

??? info "Principais parâmetros"

    - `PROJECT|SOLUTION`: O caminho do projeto ou solução a ser publicada. Se o diretório não for especificado, o padrão será o diretório atual.
    - `-c|--configuration <CONFIGURATION>`: Define a configuração da compilação. O padrão é *Debug*.
    - `-f|--framework <FRAMEWORK>`: Especifica a versão do framework (net5.0, netcoreapp3.1, netcoreapp3.0). Padrão: última versão instalada.
    - `--force`: Forçará todas as dependências a serem resolvidas mesmo se última restauração tiver sido bem-sucedida (o mesmo que a exclusão do arquivo `project.assets.json`).
    - `--no-build`: Não compila o projeto antes da publicação. Também define o sinalizador `--no-restore` implicitamente.
    - `--no-dependencies`: Ignora as referências projeto a projeto e só restaura o projeto raiz.
    - `--no-restore`: Não executa uma restauração implícita ao executar o comando.
    - `-o|--output <OUTPUT_DIRECTORY>`: Especifica o caminho para o diretório de saída. Se você especificar um caminho relativo ao publicar um projeto, o diretório de saída gerado será relativo ao diretório de trabalho atual. Para fazer com que a saída de publicação vá para diretórios separados para cada projeto, especifique um caminho relativo usando a propriedade `PublishDir` do MSBuild em vez da opção `--output`.
    - `-p:PublishSingleFile=true`: Empacota o aplicativo em um executável de arquivo único específico da plataforma.
    - `-p:PublishTrimmed=true`: Remove as bibliotecas não usadas para reduzir o tamanho da implantação de um aplicativo ao publicar um executável independente.
    - `-p:EnvironmentName`: Nome do ambiente do qual será usado as configurações.
    - `--self-contained [true|false]`: Publica o *runtime* do .NET com seu aplicativo para que o *runtime* não precise ser instalado no computador de destino. O padrão é true. 
    - `--no-self-contained`: Equivalente a `--self-contained false`
    - `-r|--runtime <RUNTIME_IDENTIFIER>`: Publica o aplicativo para um determinado runtime.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O valor padrão é minimal.

O comando `dotnet publish` compila o aplicativo, lê suas dependências especificadas no arquivo de projeto e publica o conjunto de arquivos resultantes em um diretório. A saída inclui os seguintes ativos:

- Código IL (Linguagem Intermediária) em um assembly com uma extensão dll.
- Um arquivo `.deps.json` que inclui todas as dependências do projeto.
- Um arquivo `.runtimeconfig.json`, que especifica o *runtime* compartilhado e sua versão para um aplicativo, bem como outras opções de configuração para o *runtime* (por exemplo, tipo de `garbage collection`).
- As dependências do aplicativo, que são copiadas do cache NuGet para o diretório de saída.

A saída do comando `dotnet publish` está pronta para implantação em um sistema de hospedagem para execução. É a única maneira com suporte oficial de preparar o aplicativo para implantação. Dependendo do tipo de implantação que o projeto especifica, o sistema de hospedagem pode ou não ter o *runtime* compartilhado do .NET instalado nele.

<h3> MSBuild </h3>

O comando `dotnet publish` chama MSBuild, que invoca o destino `Publish`. Quaisquer parâmetros passados para `dotnet publish` são passados para o MSBuild. Os parâmetros `-c` e `-o` mapeiam as propriedades `Configuration` e `PublishDir` do MSBuild, respectivamente.
O comando `dotnet publish` aceita opções do MSBuild, como `-p` para configurar propriedades e `-l` para definir um *logger*. Por exemplo, você pode definir uma propriedade do MSBuild usando o formato: `-p:<NAME>=<VALUE>`.
Você também pode definir propriedades relacionadas à publicação fazendo referência a um arquivo .pubxml. Por exemplo:

```bash
dotnet publish -p:PublishProfile=FolderProfile
```

O exemplo anterior usa o arquivo `FolderProfile.pubxml` que é encontrado no diretório `<project_folder>/Properties/PublishProfiles`. Se você especificar um caminho e uma extensão de arquivo ao definir a propriedade `PublishProfile`, eles serão ignorados. Por padrão, o MSBuild procura no diretório `Properties/PublishProfiles` e assume a extensão de arquivo `pubxml`. Para especificar o caminho e o nome de arquivo, incluindo a extensão, defina a propriedade `PublishProfileFullPath` em vez da propriedade `PublishProfile`.

??? example "Exemplos"

    ```bash
    # Publicar um projeto para o framework Core 2.2 para o runtime linux-x64 usando as configurações do ambiente "Producao" 
    # com todas dependências inclusas
    dotnet publish -f netcoreapp2.2 -o ~/DeployProducao --self-contained true -r linux-x64 p:EnvironmentName=Producao

    # Publicar um projeto para o framework atual para o runtime linux-x64 usando as configurações para Release
    # sem dependências inclusas
    dotnet publish --self-contained false -c Release -r linux-x64 
    ```

## dotnet restore

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-restore)

Restaura as dependências e as ferramentas de um projeto.

```bash
dotnet restore [<ROOT>] [--configfile <FILE>] [--disable-parallel]
    [-f|--force] [--force-evaluate] [--ignore-failed-sources]
    [--interactive] [--lock-file-path <LOCK_FILE_PATH>] [--locked-mode]
    [--no-cache] [--no-dependencies] [--packages <PACKAGES_DIRECTORY>]
    [-r|--runtime <RUNTIME_IDENTIFIER>] [-s|--source <SOURCE>]
    [--use-lock-file] [-v|--verbosity <LEVEL>]

dotnet restore -h|--help
```

??? info "Principais parâmetros"

    - `--configfile <FILE>`: O arquivo de configuração NuGet (`nuget.config`) a ser usado para a operação de restauração.
    - `--disable-parallel`: Desabilita a restauração de vários projetos paralelamente.
    - `--force`: Forçará todas as dependências a serem resolvidas mesmo se última restauração tiver sido bem-sucedida (o mesmo que a exclusão do arquivo `project.assets.json`).
    - `--force-evaluate`: Força a restauração a reavaliar todas as dependências mesmo se um arquivo *lock* já existir.
    - `--ignore-failed-sources`: Avise somente sobre fontes com falha se houver pacotes que atendem ao requisito de versão.
    - `--locked-mode`: Não permitir atualização do arquivo de bloqueio do projeto.
    - `--no-cache`: Especifica para não armazenar em cache solicitações HTTP.
    - `--no-dependencies`: Ignora as referências projeto a projeto e só restaura o projeto raiz.
    - `--packages <PACKAGES_DIRECTORY>`: Especifica o diretório para os pacotes restaurados.
    - `-r|--runtime <RUNTIME_IDENTIFIER>`: Restaura os pacotes para um determinado runtime.
    - `-s|--source <SOURCE>`: Especifica o URI da origem do pacote NuGet a ser usado durante a operação de restauração. Essa configuração substitui todas as fontes especificadas nos arquivos n`uget.config`.
    - `--use-lock-file`: Habilita o arquivo de bloqueio do projeto a ser gerado e usado com a restauração.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O valor padrão é minimal.

O comando `dotnet restore` usa o NuGet para restaurar as dependências e ferramentas específicas do projeto especificadas no arquivo do projeto. Na maioria dos casos, você não precisa usar o comando `dotnet restore` explicitamente, uma vez que uma restauração do NuGet é executada implicitamente, se necessário, quando você executa os seguintes comandos:

```bash
dotnet new
dotnet build
dotnet build-server
dotnet run
dotnet test
dotnet publish
dotnet pack
```

Às vezes, pode ser inconveniente executar a restauração implícita do NuGet com estes comandos. Por exemplo, alguns sistemas automatizados, como os sistemas de compilação (e.g. integração contínua), precisam chamar o `dotnet restore` explicitamente para controlar o momento em que a restauração ocorre para que possam controlar o uso de rede. Para evitar a restauração implícita do NuGet, você pode usar o sinalizador `--no-restore` com qualquer um desses comandos para desabilitar a restauração implícita.

<h3> Especificar feeds </h3>

Para restaurar as dependências, o NuGet precisa dos feeds nos quais os pacotes estão localizados. Os feeds são geralmente fornecidos por meio do arquivo de configuração `nuget.config`. Um arquivo de configuração padrão é fornecido quando o SDK do .NET é instalado. Para especificar Feeds adicionais, siga um destes procedimentos:

- Crie seu próprio arquivo de `nuget.config` no diretório do projeto.
- Use comandos `dotnet nuget` como `dotnet nuget add source`.

Você pode substituir os feeds `nuget.config` com a -s opção.

<h3> Diretório de pacotes globais </h3>

Para as dependências, você pode especificar onde os pacotes restaurados são colocados durante a operação de restauração usando o argumento `--packages`. Se não for especificado, o cache do pacote NuGet padrão é usado, o qual pode ser encontrado no diretório `.nuget/packages` do diretório base do usuário em todos os sistemas operacionais. Por exemplo, `/home/<usuario>/.nuget/packages` no Linux ou `C:\Users\<usuario>\.nuget\packages` no Windows.

<h3> Ferramentas específicas do projeto </h3>

Para ferramentas específicas do projeto, o `dotnet restore` primeiro restaura o pacote no qual a ferramenta foi empacotada e prossegue com a restauração das dependências da ferramenta conforme especificado no seu arquivo de projeto.

<h3> Diferenças do nuget.config </h3>

O comportamento do comando `dotnet restore` será afetado pelas configurações no arquivo `nuget.config`, se estiver presente. Por exemplo, a definição de `globalPackagesFolder` em `nuget.config` coloca os pacotes NuGet restaurados no diretório especificada. Essa é uma alternativa para especificar a opção `--packages` no comando `dotnet restore`.

Há três configurações específicas que são ignoradas por `dotnet restore`:

- `bindingRedirects`: Os redirecionamentos de associação não funcionam com elementos `<PackageReference> ` e o .NET só dá suporte a elementos `<PackageReference> ` para pacotes NuGet.

- `solution`:  Essa configuração é específica do Visual Studio e não se aplica ao .NET. O .NET não usa um arquivo `packages.config` e, em vez disso, usa elementos `<PackageReference> ` para pacotes NuGet.

- `trustedSigners`: O suporte para verificação de assinatura de pacotes multiplataformas foi adicionado no SDK do .NET 5.0.

??? example "Exemplos"

    ```bash
    # Restaure as dependências e as ferramentas para o projeto no diretório atual
    dotnet restore

    # Restaure as dependências e as ferramentas para o projeto "app1" encontrado no caminho fornecido
    dotnet restore ./projects/app1/app1.csproj

    # Restaure as dependências e as ferramentas para o projeto no diretório atual usando os dois caminhos de arquivo fornecidos como fontes:
    dotnet restore -s c:\packages\mypackages -s c:\packages\myotherpackages
    ```

## dotnet run

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-run)

Executa o código-fonte sem qualquer comando de compilação ou inicialização explícito.

```bash
dotnet run [-c|--configuration <CONFIGURATION>] [-f|--framework <FRAMEWORK>]
    [--force] [--interactive] [--launch-profile <NAME>] [--no-build]
    [--no-dependencies] [--no-launch-profile] [--no-restore]
    [-p|--project <PATH>] [-r|--runtime <RUNTIME_IDENTIFIER>]
    [-v|--verbosity <LEVEL>] [[--] [application arguments]]

dotnet run -h|--help
```

??? info "Principais parâmetros"

    - `--`: Delimita os argumentos do `dotnet run` dos argumentos para o aplicativo que está sendo executado. Todos os argumentos após esse delimitador são passados para o aplicativo que está sendo executado.
    - `-c|--configuration <CONFIGURATION>`: Define a configuração da compilação. O padrão é Debug.
    - `-f|--framework <FRAMEWORK>`: Compila e executa o aplicativo usando o *framework* especificado. Padrão: última versão instalada.
    - `--force`: Forçará todas as dependências a serem resolvidas mesmo se última restauração tiver sido bem-sucedida (o mesmo que a exclusão do arquivo `project.assets.json`).
    - `--launch-profile <NAME>`: Nome do perfil de inicialização (se houver) a ser usado ao iniciar o aplicativo. Os perfis de inicialização são definidos no arquivo `launchSettings.json` e são normalmente chamados *Development*, *Staging* e *Production*.
    - `--no-build`: Não compila o projeto antes da publicação. Também define o sinalizador `--no-restore` implicitamente.
    - `--no-dependencies`: Ignora as referências projeto a projeto e só restaura o projeto raiz.
    - `--no-launch-profile`: Tenta não usar `launchSettings.json` para configurar o aplicativo.
    - `--no-restore`: Não executa uma restauração implícita ao executar o comando.
    - `-p|--project <PATH>`: Especifica o caminho do arquivo de projeto a ser executado (nome do diretório ou caminho completo). Padrão: diretório atual.
    - `-r|--runtime <RUNTIME_IDENTIFIER>`: Restaura os pacotes para um determinado runtime.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O valor padrão é minimal.

O comando `dotnet run` fornece uma opção conveniente para executar o aplicativo a partir do código-fonte com um comando. Ele é útil para o desenvolvimento iterativo rápido a partir da linha de comando. O comando depende do comando `dotnet build` para criar o código.

Os arquivos de saída são gravados no local padrão, que é `bin/<configuration>/<target>`. Por exemplo, se você tiver um aplicativo `netcoreapp2.1` e executar `dotnet run`, a saída será colocada em `bin/Debug/netcoreapp2.1`. Os arquivos são substituídos conforme necessário. Os arquivos temporários são colocados no diretório `obj`.

Se o projeto especificar várias *frameworks*, a execução de `dotnet run` resultará em um erro, a menos que a opção `-f|--framework <FRAMEWORK>` seja usada para especificar a estrutura.

O comando `dotnet run` é usado no contexto de projetos, não assemblies compilados. Se você estiver tentando executar uma DLL de aplicação dependente de *framework*, use `dotnet` sem um comando. Por exemplo, para executar myapp.dll, use:

```bash
dotnet myapp.dll
```

Para executar o aplicativo, o comando `dotnet run` resolve as dependências do aplicativo que estão fora do runtime compartilhado por meio do cache NuGet. Como ele usa as dependências em cache, não recomendamos usar `dotnet run` para executar aplicativos em produção. Em vez disso, crie uma implantação usando o comando dotnet publish e implante a saída publicada.

??? example "Exemplos"

    ```bash
    # Execute o projeto no diretório atual
    dotnet run

    # Execute o projeto especificado
    dotnet run --project ./projects/proj1/proj1.csproj

    # Execute o projeto no diretório atual com o argumento --help passado para o "aplicativo" (usado --)
    dotnet run --configuration Release -- --help

    # Restaure as dependências e as ferramentas mostrando apenas a saída mínima
    dotnet run --verbosity m
    ```

## dotnet sln

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-sln)

Lista ou modifica os projetos em um arquivo de solução .NET.

```bash
dotnet sln [<SOLUTION_FILE>] [command] -h|--help

dotnet sln [command] -h|--help
```

??? info "Principais parâmetros"

    - `SOLUTION_FILE`: O arquivo de solução a ser usado. Se esse argumento for omitido, o comando pesquisará no diretório atual.

### dotnet sln list

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-sln#list)

Lista todos os projetos em um arquivo de solução.

```bash
dotnet sln list [-h|--help]
```

### dotnet sln add

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-sln#add)

Adiciona um ou mais projetos ao arquivo de solução.

```bash
dotnet sln [<SOLUTION_FILE>] add [--in-root] [-s|--solution-folder <PATH>] <PROJECT_PATH> [<PROJECT_PATH>...]

dotnet sln add [-h|--help]
```

??? info "Principais parâmetros"

    - `SOLUTION_FILE`: O arquivo de solução a ser usado. Se esse argumento for omitido, o comando pesquisará no diretório atual.
    - `PROJECT_PATH`: O caminho para o projeto ou projetos a serem adicionados à solução. Funciona o padrão globbing do shell Unix/Linux.
    - `--in-root`: Coloca os projetos na raiz da solução, em vez de criar um diretório para a solução.
    - `-s|--solution-folder <PATH>`: O caminho do diretório **virtual** da solução no qual será adicionado o projeto. Esta é um diretório virtual que pode ser usada para agrupar projetos em uma solução.

??? example "Exemplos"

    ```bash
    # Adicionar o projeto à solução no diretório virtual da solução "Services/Catalogo"
    dotnet sln add -s Services/Catalogo src/Projeto.Catalogo.Domain/Projeto.Catalogo.Domain.csproj
    ```

### dotnet sln remove

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-sln#remove)

Remova um projeto ou vários projetos do arquivo da solução.

```bash
dotnet sln [<SOLUTION_FILE>] remove <PROJECT_PATH> [<PROJECT_PATH>...]

dotnet sln [<SOLUTION_FILE>] remove [-h|--help]
```

??? info "Principais parâmetros"

    - `SOLUTION_FILE`: O arquivo de solução a ser usado. Se esse argumento for omitido, o comando pesquisará no diretório atual.
    - `PROJECT_PATH`: O caminho para o projeto ou projetos a serem adicionados à solução. Funciona o padrão globbing do shell Unix/Linux.

## dotnet store

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-store)

Armazena os assemblies especificados no repositório de pacotes de runtime.

```bash
dotnet store -m|--manifest <PATH_TO_MANIFEST_FILE>
    -f|--framework <FRAMEWORK_VERSION> -r|--runtime <RUNTIME_IDENTIFIER>
    [--framework-version <FRAMEWORK_VERSION>] [--output <OUTPUT_DIRECTORY>]
    [--skip-optimization] [--skip-symbols] [-v|--verbosity <LEVEL>]
    [--working-dir <WORKING_DIRECTORY>]

dotnet store -h|--help
```

??? info "Principais parâmetros"

    - `-f|--framework <FRAMEWORK>`: Especifica a estrutura de destino. A estrutura de destino deve ser especificada no arquivo de projeto.
    - `-m|--manifest <PATH_TO_MANIFEST_FILE>`: O arquivo de manifesto do repositório de pacotes é um arquivo XML que contém a lista de pacotes a serem armazenados.
    - `-r|--runtime <RUNTIME_IDENTIFIER>`: O identificador do runtime a ser usado.
    - `--framework-version <FRAMEWORK_VERSION>`: Especifica a versão do SDK do .NET. Essa opção permite que você selecione uma versão da estrutura específica, além da estrutura especificada pela opção `-f|--framework`.
    - `-o|--output <OUTPUT_DIRECTORY>`: Especifica o caminho para o repositório de pacotes de runtime. Se não for especificado, o padrão será o subdiretório `store` do diretório de instalação do .NET do perfil do usuário.
    - `--skip-optimization`: Ignora a fase de otimização.
    - -`-skip-symbols`: Ignora a geração de símbolos.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic].
    - `-w|--working-dir <WORKING_DIRECTORY>`: O diretório de trabalho usado pelo comando. Se não for especificado, ele usará o subdiretório `obj` do diretório atual.

O comando `dotnet store` armazena os assemblies especificados no repositório de pacotes de runtime. Por padrão, os assemblies são otimizados para a estrutura e o runtime de destino.

??? example "Exemplos"

    ```bash
    # Armazene os pacotes especificados no arquivo de projeto packages.csproj
    dotnet store --manifest packages.csproj --framework-version 2.0.0

    # Armazene os pacotes especificados no packages.csproj sem otimização
    dotnet store --manifest packages.csproj --skip-optimization
    ```

## dotnet test

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-test)

Driver de teste do .NET usado para executar testes de unidade.

```bash
dotnet test [<PROJECT> | <SOLUTION> | <DIRECTORY> | <DLL>]
    [-a|--test-adapter-path <ADAPTER_PATH>] [--blame] [--blame-crash]
    [--blame-crash-dump-type <DUMP_TYPE>] [--blame-crash-collect-always]
    [--blame-hang] [--blame-hang-dump-type <DUMP_TYPE>]
    [--blame-hang-timeout <TIMESPAN>]
    [-c|--configuration <CONFIGURATION>]
    [--collect <DATA_COLLECTOR_NAME>]
    [-d|--diag <LOG_FILE>] [-f|--framework <FRAMEWORK>]
    [--filter <EXPRESSION>] [--interactive]
    [-l|--logger <LOGGER>] [--no-build]
    [--nologo] [--no-restore] [-o|--output <OUTPUT_DIRECTORY>]
    [-r|--results-directory <RESULTS_DIR>] [--runtime <RUNTIME_IDENTIFIER>]
    [-s|--settings <SETTINGS_FILE>] [-t|--list-tests]
    [-v|--verbosity <LEVEL>] [[--] <RunSettings arguments>]

dotnet test -h|--help
```

??? info "Principais parâmetros"

    - `PROJECT|SOLUTION|DIRECTORY|DLL`: Caminho de um projeto de teste, solução, um diretório (que contém um projeto ou uma solução) ou um arquivo `.dll` do projeto de teste. Padrão: pesquisa um projeto ou uma solução no diretório atual.
    - `--blame`: Executa os testes no modo *blame*. Essa opção é útil para isolar testes problemáticos que causam falha no host de teste. Quando uma falha é detectada, ela cria um arquivo de sequência no `TestResults/<Guid>/<Guid>_Sequence.xml` que captura a ordem dos testes que foram executados antes da falha.
    - `--blame-crash`:  Executa os testes no modo *blame* e coleta o despejo de memória (*crash dump*) quando o host de teste sai inesperadamente.
    - `--blame-crash-dump-type <DUMP_TYPE>`: O tipo de despejo de memória a ser coletado. Implica `--blame-crash`.
    - `--blame-crash-collect-always` Coleta um despejo de memória na saída e inesperada do host de teste.
    - `--blame-hang`: Execute os testes no modo *blame* e coleta um despejo de travamento quando um teste excede o tempo limite especificado.
    - `--blame-hang-dump-type <DUMP_TYPE>`: O tipo de despejo de memória a ser coletado. Deve ser full, mini ou none. Quando none é especificado, o host de teste é encerrado no tempo limite, mas nenhum despejo é coletado. Implica `--blame-hang`.
    - `--blame-hang-timeout <TIMESPAN>`: Tempo limite por teste, após o qual um despejo de travamento é disparado e o processo de host de teste e todos os seus processos filho são despejados e encerrados.
    - `-c|--configuration <CONFIGURATION>`:  Define a configuração da compilação. O valor padrão é Debug.
    - `--collect <DATA_COLLECTOR_NAME>`: Habilita o coletor de dados para a execução de teste. [Veja mais em](https://aka.ms/vstest-collect).
    - `-d|--diag <LOG_FILE>`: Habilita o modo de diagnóstico para a plataforma de teste e grava mensagens de diagnóstico no arquivo especificado. O processo que está registrando as mensagens determina quais arquivos são criados, como `*.host_<date>.txt` para o log do host de teste e `*.datacollector_<date>.txt` para o log do coletor de dados.
    - `-f|--framework <FRAMEWORK>`: Força o uso do host de teste de um determinado framework para os binários de teste. A versão real do Framework a ser usada é determinada pelo `runtimeconfig.json` do projeto de teste. Quando não especificado, o atributo de assembly `TargetFramework` é usado para determinar o tipo de host.
    - `--filter <EXPRESSION>`: Filtra os testes no projeto atual usando a expressão especificada.
    - `-l|--logger <LOGGER>`: Especifica um *logger* para os resultados do teste. Ao contrário do MSBuild, o `teste dotnet` não aceita abreviações: em vez de `-l "console;v=d"` usar -`l "console;verbosity=detailed"`.
    - `--no-build`: Não compila o projeto de teste antes de sua execução. Ele também define implicitamente a flag `--no-restore`.
    - `--no-restore`: Não executa uma restauração implícita ao executar o comando.
    - `-o|--output <OUTPUT_DIRECTORY>`: Diretório no qual os binários compilados são colocados. Se não for especificado, o caminho padrão será `./bin/<configuration>/<framework>/`. Para projetos com várias estruturas de destino (por meio da propriedade `TargetFrameworks`), também é necessário definir `--framework` quando você especifica essa opção.
    - `-r|--results-directory <RESULTS_DIR>`: O diretório em que os resultados de teste serão colocados. Se o diretório especificado não existir, ele será criado. O padrão é o diretório `TestResults` que contém o arquivo de projeto.
    - `--runtime <RUNTIME_IDENTIFIER>`: O `runtime` de destino para o qual testar.
    - `-s|--settings <SETTINGS_FILE>`: O arquivo `.runsettings` a ser usado para executar os testes.
    - `-t|--list-tests`: Liste os testes encontrados em vez de executá-los.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O valor padrão é minimal.
    - `RunSettings arguments`: São passados como os últimos argumentos na linha de comando após "--". São especificados como pares [name]=[value]. Exemplo: `dotnet test -- MSTest.DeploymentEnabled=false MSTest.MapInconclusiveToFailed=Tru`.

O comando `dotnet test` é usado para executar testes de unidade em uma determinada solução. O comando `dotnet test` cria a solução e executa um aplicativo de host de teste para cada projeto de teste na solução. O host de teste executa testes no projeto usando uma estrutura de teste, por exemplo: **MSTest**, **NUnit** ou **xUnit**, e relata o êxito ou a falha de cada teste. Se todos os testes forem bem-sucedidos, o executor de testes retornará `0` como um código de saída; caso contrário, se algum teste falhar, retornará `1`.

Para projetos de vários destinos, os testes são executados para cada estrutura de destino. O host de teste e a estrutura de teste de unidade são empacotados como pacotes NuGet e restaurados como dependências comuns para o projeto.

Os projetos de teste especificam o executor de teste usando um elemento comum `<PackageReference>`, conforme mostrado no exemplo de arquivo de projeto a seguir:

```xml
<Project Sdk="Microsoft.NET.Sdk">

  <PropertyGroup>
    <TargetFramework>netcoreapp3.1</TargetFramework>
  </PropertyGroup>

  <ItemGroup>
    <PackageReference Include="Microsoft.NET.Test.Sdk" Version="16.9.1" />
    <PackageReference Include="xunit" Version="2.4.1" />
    <PackageReference Include="xunit.runner.visualstudio" Version="2.4.3" />
  </ItemGroup>

</Project>
```

Onde `Microsoft.NET.Test.Sdk` é o host de teste, `xunit` é a estrutura de teste. E `xunit.runner.visualstudio` é um adaptador de teste, que permite que a estrutura `xUnit` funcione com o host de teste.

??? example "Exemplos"

    ```bash
    # Execute os testes no projeto no diretório atual
    dotnet test

    # Execute os testes no projeto "test1"
    dotnet test ~/projects/test1/test1.csproj

    # Execute os testes no projeto no diretório atual e gere um arquivo de resultados de teste no formato TRX
    dotnet test --logger trx

    # Execute os testes no projeto no diretório atual e gere um arquivo de cobertura de código 
    # depois de instalar a integração de coletores coverlet,
    dotnet test --collect:"XPlat Code Coverage"

    # Execute os testes no projeto no diretório atual e faça log com detalhes no console
    dotnet test --logger "console;verbosity=detailed"

    # Execute os testes no projeto no diretório atual e relate os testes que estavam em andamento quando o host de teste falhou
    dotnet test --blame
    ```

## dotnet list reference

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-list-reference)

Lista as referências projeto a projeto.

```bash
dotnet list [<PROJECT>] reference

dotnet list -h|--help
```

??? example "Exemplos"

    ```bash
    # Listar as referências de projeto do projeto especificado
    dotnet list app/app.csproj reference

    # Listar as referências de projeto do projeto no diretório atual
    dotnet list reference
    ```

## dotnet add reference

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-add-reference)

Adiciona as referências P2P (projeto para projeto).

```bash
dotnet add [<PROJECT>] reference [-f|--framework <FRAMEWORK>]
     [--interactive] <PROJECT_REFERENCES>

dotnet add reference -h|--help
```

??? info "Principais parâmetros"

    - `PROJECT`: Especifica o arquivo do projeto. Se não for especificado, o comando pesquisará um no diretório atual.
    - `PROJECT_REFERENCES`: Referências P2P (projeto para projeto) a serem adicionadas. Funciona o padrão globbing do shell Unix/Linux.
    - `-f|--framework <FRAMEWORK>`: Adiciona uma referência de projeto somente quando se trata de um framework específico como destino.

Este comando fornece uma opção conveniente para adicionar referências de projeto a outro projeto. Depois de executar o comando, os elementos `<ProjectReference>` são adicionados ao arquivo de projeto.

```xml
<ItemGroup>
  <ProjectReference Include="app.csproj" />
  <ProjectReference Include="..\lib2\lib2.csproj" />
  <ProjectReference Include="..\lib1\lib1.csproj" />
</ItemGroup>
```

??? example "Exemplos"

    ```bash
    # No projeto "app/app.csproj" é adicionado referencia ao projeto "lib/lib.csproj"
    dotnet add app/app.csproj reference lib/lib.csproj

    # Adicionar várias referências de outros projetos ao projeto no diretório atual.
    dotnet add reference lib1/lib1.csproj lib2/lib2.csproj
    ```

## dotnet remove reference

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-remove-reference)

Remove as referências P2P (projeto para projeto).

```bash
dotnet remove [<PROJECT>] reference [-f|--framework <FRAMEWORK>]
     <PROJECT_REFERENCES>

dotnet remove reference -h|--help
```

??? info "Principais parâmetros"

    - `PROJECT`: Especifica o arquivo do projeto. Se não for especificado, o comando pesquisará um no diretório atual.
    - `PROJECT_REFERENCES`: Referências P2P (projeto para projeto) a serem adicionadas. Funciona o padrão globbing do shell Unix/Linux.

Este comando fornece uma opção conveniente para remover referências de um projeto a outro projeto. Depois de executar o comando, os elementos `<ProjectReference>` são removidos ao arquivo de projeto.

??? example "Exemplos"

    ```bash
    # No projeto "app/app.csproj" é removida a referencia ao projeto "lib/lib.csproj"
    dotnet remove app/app.csproj reference lib/lib.csproj

    # Remove várias referências de outros projetos ao projeto no diretório atual.
    dotnet remove reference lib1/lib1.csproj lib2/lib2.csproj
    ```

## dotnet list package

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-list-package)

Lista as referências de pacote para um projeto ou solução.

```bash
dotnet list [<PROJECT>|<SOLUTION>] package [--config <SOURCE>]
    [--deprecated]
    [--framework <FRAMEWORK>] [--highest-minor] [--highest-patch]
    [--include-prerelease] [--include-transitive] [--interactive]
    [--outdated] [--source <SOURCE>] [-v|--verbosity <LEVEL>]

dotnet list package -h|--help
```

??? info "Principais parâmetros"

    - `PROJECT|SOLUTION`: Especifica o arquivo do projeto ou solução. Se não for especificado, o comando pesquisará um no diretório atual.
    - `--config <SOURCE>`: A fontes do NuGet a serem usadas ao procurar pacotes mais novos. Requer a opção `--outdated`.
    - `--deprecated`: Exibe os pacotes que foram depreciados.
    - `--framework <FRAMEWORK>`: Exibe apenas os pacotes aplicáveis para p framework especificado.
    - `--highest-minor`: Ao procurar pacotes mais novos, considerar apenas os pacotes com um número de versão principal correspondente. Requer a opção `--outdated` ou `--deprecated`.
    - `--highest-patch`: Ao procurar pacotes mais novos, considerar apenas os pacotes com números de versão principais e secundários correspondentes. Requer a opção `--outdated` ou `--deprecated`.
    - `--include-transitive`: Lista pacotes transitivos, além dos pacotes de nível superior. Ao especificar essa opção, você obtém uma lista de pacotes dos quais os pacotes de nível superior dependem.
    - `--outdated`: Lista os pacotes que têm as versões mais recentes disponíveis.
    - `-s|--source <SOURCE>`: As fontes do NuGet a serem usadas ao procurar pacotes mais novos. Requer a opção `--outdated` ou `--deprecated`.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic]. O valor padrão é minimal.

O comando `dotnet list package` fornece uma opção conveniente para listar todas as referências de pacotes do NuGet para um projeto específico ou uma solução.

O exemplo a seguir mostra a saída do comando dotnet list package para o projeto "SentimentAnalysis":

```bash
Project 'SentimentAnalysis' has the following package references
   [netcoreapp2.1]:
   Top-level Package               Requested   Resolved
   > Microsoft.ML                  1.4.0       1.4.0
   > Microsoft.NETCore.App   (A)   [2.1.0, )   2.1.0

(A) : Auto-referenced package.
```

A coluna `Requested` refere-se à versão do pacote especificada no arquivo de projeto e pode ser um intervalo. A coluna `Resolved` lista a versão que o projeto está usando atualmente e é sempre um valor único. Os pacotes que exibem um `(A)` direito ao lado de seus nomes representam referências de pacote implícitas que são inferidas de suas configurações de projeto (tipo Sdk, ou ropriedade `<TargetFramework>` ou `<TargetFrameworks>`).

Use a opção `--outdated` para descobrir se existem versões mais recentes dos pacotes que você está usando em seus projetos. Por padrão, `--outdated` lista os pacotes estáveis mais recentes, a menos que a versão resolvida também seja uma versão de pré-lançamento. Para incluir versões de pré-lançamento ao listar versões mais recentes, especifique também a opção `--include-prerelease`. Os exemplos a seguir mostram a saída do comando dotnet list package --outdated --include-prerelease para o mesmo projeto do exemplo anterior:

```bash
The following sources were used:
   https://api.nuget.org/v3/index.json
   C:\Program Files (x86)\Microsoft SDKs\NuGetPackages\

Project `SentimentAnalysis` has the following updates to its packages
   [netcoreapp2.1]:
   Top-level Package      Requested   Resolved   Latest
   > Microsoft.ML         1.4.0       1.4.0      1.5.0-preview
```

Se você precisa descobrir se seu projeto tem dependências transitivas, use a opção `--include-transitive`. Dependências transitivas ocorrem quando você adiciona um pacote ao seu projeto que, por sua vez, depende de outro pacote. O exemplo a seguir mostra a saída da execução do comando `dotnet list package --include-transitive` para o projeto HelloPlugin, que exibe os pacotes de nível superior e os pacotes dos quais eles dependem:

```bash
Project 'HelloPlugin' has the following package references
   [netcoreapp3.0]:
   Transitive Package      Resolved
   > PluginBase            1.0.0
```

??? example "Exemplos"

    ```bash
    # Listar referências de pacote de um projeto específico
    dotnet list SentimentAnalysis.csproj package

    # Listar referências de pacote com versões mais recentes disponíveis, incluindo versões de pré-lançamento
    dotnet list package --outdated --include-prerelease

    # Listar referências de pacotes para um framework específico
    dotnet list package --framework netcoreapp3.0
    ```

## dotnet add package

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-add-package)

Adiciona uma referência de pacote a um arquivo de projeto.

```bash
dotnet add [<PROJECT>] package <PACKAGE_NAME>
    [-f|--framework <FRAMEWORK>] [--interactive]
    [-n|--no-restore] [--package-directory <PACKAGE_DIRECTORY>]
    [--prerelease] [-s|--source <SOURCE>] [-v|--version <VERSION>]

dotnet add package -h|--help
```

??? info "Principais parâmetros"

    - `PROJECT`: Especifica o arquivo do projeto. Se não for especificado, o comando pesquisará um no diretório atual.
    - `PACKAGE_NAME`: A referência de pacote a ser adicionada.
    - `-f|--framework <FRAMEWORK>`: Adiciona uma referência de pacote somente quando se trata de um framework específico como destino.
    - `-n|--no-restore`: Adiciona uma referência de pacote sem executar a visualização de restauração e a verificação de compatibilidade.
    - `--package-directory <PACKAGE_DIRECTORY>`: O diretório no qual restaurar os pacotes. O local de restauração de pacote padrão é `%userprofile%\.nuget\packages` no Windows e `~/.nuget/packages` no macOS e Linux.
    - `--prerelease`: Permite que pacotes de pré-lançamento sejam instalados.
    - `-s|--source <SOURCE>`: O URI da origem do pacote NuGet a ser usado durante a operação de restauração.
    - `-v|--version <VERSION>`: Versão do pacote.

O comando `dotnet add package` fornece uma opção conveniente para adicionar uma referência de pacote a um arquivo de projeto. Um elemento `<PackageReference>` será adicionado ao arquivo de projeto e` dotnet restore` será executada.

O arquivo de configuração do projeto irá conter um elemento `<PackageReference>` para o pacote referenciado.

```xml
<PackageReference Include="Newtonsoft.Json" Version="12.0.1" />
```

??? example "Exemplos"

    ```bash
    # Adicionar um pacote NuGet Newtonsoft.Json a um projeto
    dotnet add package Newtonsoft.Json

    # Adicionar uma versão específica de um pacote a um projeto
    dotnet add ToDo.csproj package Microsoft.Azure.DocumentDB.Core -v 1.0.0

    # Adicionar um pacote usando uma fonte específica do NuGet
    dotnet add package Microsoft.AspNetCore.StaticFiles -s https://dotnet.myget.org/F/dotnet-core/api/v3/index.json
    ```

## dotnet remove package

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-remove-package)

Remove a referência de pacote de um arquivo de projeto.

```bash
dotnet remove [<PROJECT>] package <PACKAGE_NAME>

dotnet remove package -h|--help
```

O comando `dotnet remove package` fornece uma opção conveniente para remover uma referência de pacote NuGet de um projeto.

??? info "Principais parâmetros"

    - `PROJECT`: Especifica o arquivo do projeto. Se não for especificado, o comando pesquisará um no diretório atual.
    - `PACKAGE_NAME`: A referência de pacote a ser removida..

??? example "Exemplos"

    ```bash
    # Remova o pacote NuGet Newtonsoft.Json de um projeto no diretório atual:
    dotnet remove app/app.csproj reference lib/lib.csproj
    ```

## dotnet tool

### dotnet tool install

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-tool-install)

Instala a ferramenta .NET especificada em seu computador.

```bash
dotnet tool install <PACKAGE_NAME> -g|--global
    [--add-source <SOURCE>] [--configfile <FILE>]
    [--framework <FRAMEWORK>] [-v|--verbosity <LEVEL>]
    [--version <VERSION_NUMBER>]

dotnet tool install <PACKAGE_NAME> --tool-path <PATH>
    [--add-source <SOURCE>] [--configfile <FILE>]
    [--framework <FRAMEWORK>] [-v|--verbosity <LEVEL>]
    [--version <VERSION_NUMBER>]

dotnet tool install <PACKAGE_NAME>
    [--add-source <SOURCE>] [--configfile <FILE>]
    [--framework <FRAMEWORK>] [-v|--verbosity <LEVEL>]
    [--version <VERSION_NUMBER>]

dotnet tool install -h|--help
```

??? info "Principais parâmetros"

    - `PACKAGE_NAME`: Nome/ID do pacote NuGet que contém a ferramenta .NET a ser instalada.
    - `--add-source <SOURCE>`: Adiciona outra origem do pacote NuGet a ser usada durante a instalação. Os feeds são acessados em paralelo, não sequencialmente em alguma ordem de precedência. Se o mesmo pacote e versão estiver em vários feeds, o feed mais rápido vence. 
    - `--configfile <FILE>`: O arquivo de configuração do NuGet (`nuget.config`) a ser usado.
    - `--framework <FRAMEWORK>`: Especifica o *framework* para a qual instalar a ferramenta. Por padrão tenta escolher a estrutura de destino mais apropriada.
    - `-g|--global`: Especifica que a instalação é a nível de usuário e não apenas diretório local. Não pode ser combinada com a opção `--tool-path`. Padrão: usuário local.
    - `--tool-path <PATH>`: Especifica o local do qual a Ferramenta Global será instalada. PATH pode ser absoluto ou relativo. Se PATH não existir, o comando tentará criá-lo.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic].
    - `--version <VERSION_NUMBER>`: A versão da ferramenta a ser instalada. Por padrão, a última versão estável do pacote é instalada.

O comando `dotnet tool install` fornece uma maneira de instalar as ferramentas do .NET em seu computador. Para usar o comando, especifique uma das seguintes opções de instalação:

- Para instalar uma ferramenta global no local padrão, use a opção `--global`.
- Para instalar uma ferramenta global em um local personalizado, use a opção `--tool-path`.
- Para instalar uma ferramenta local, omita as Opções `--global` e `--tool-path`.

As ferramentas globais são instaladas nos diretórios `$HOME/.dotnet/tools` em Linux/macOS e `%USERPROFILE%\.dotnet\tools` em windows:

As ferramentas locais são adicionadas a um arquivo `dotnet-tools.json` em um diretório `.config` no diretório atual. Se um arquivo *manifest* ainda não existir, crie-o executando o seguinte comando:

```bash
dotnet new tool-manifest
```

??? example "Exemplos"

    ```bash
    # Instala o dotnetsay como uma ferramenta global no local padrão.
    dotnet tool install -g dotnetsay

    # Instala o dotnetsay como uma ferramenta global em um diretório específico do linux/MacOS.
    dotnet tool install dotnetsay --tool-path ~/bin

    # Instala a versão 2.0.0 do dotnetsay como uma ferramenta global.
    dotnet tool install -g dotnetsay --version 2.0.0

    # Instala o dotnetsay como uma ferramenta local para o diretório atual.
    dotnet tool install dotnetsay
    ```

### dotnet tool list

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-tool-list)

Lista todas as ferramentas .NET do tipo especificado atualmente instalado em seu computador.

```bash
dotnet tool list -g|--global

dotnet tool list --tool-path <PATH>

dotnet tool list --local

dotnet tool list

dotnet tool list -h|--help
```

??? info "Principais parâmetros"

    - `-g|--global`: Lista as ferramentas globais. Não pode ser combinada com a opção `--tool-path`. Omitir `--global` e `--tool-path` lista as ferramentas locais.
    - `--local`: Lista as ferramentas locais para o diretório atual. Não pode ser combinado com as opções `--global` ou `--tool-path`. Omitir `--global` e `--tool-path` lista as ferramentas locais, mesmo se `--local` não for especificado.
    - `--tool-path <PATH>`: Especifica um local personalizado onde encontrar ferramentas globais. `PATH` pode ser absoluto ou relativo. Não pode ser combinada com a opção `--global`. Omitir `--global` e `--tool-path` lista as ferramentas locais.

O comando `dotnet tool list` fornece uma maneira de listar todas as ferramentas globais, ferramentas de um PATH ou ferramentas locais instaladas em seu computador. O comando lista o nome do pacote, a versão instalada e o comando da ferramenta. Para usar o comando, especifique um dos seguintes:

- Para listar as ferramentas globais instaladas no local padrão, use a opção `--global`.
- Para listar as ferramentas globais instaladas em um local personalizado, use a opção `--tool-path`.
- Para listar as ferramentas locais, use a opção `--local` ou omita as opções `--global`, `--tool-path` e `--local`.

??? example "Exemplos"

    ```bash
    # Lista todas as ferramentas globais instaladas por todo o usuário em seu computador (perfil de usuário atual).
    dotnet tool list -g

    # Lista as ferramentas globais de um diretório específico do Linux/macOS.
    dotnet tool list --tool-path ~/bin

    # Lista todas as ferramentas locais disponíveis no diretório atual.
    dotnet tool list
    # Ou
    dotnet tool list --local
    ```

### dotnet tool restore

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-tool-restore)

Instala as ferramentas locais do .NET que estão no escopo do diretório atual.

```bash
dotnet tool restore
    [--configfile <FILE>] [--add-source <SOURCE>]
    [--tool-manifest <PATH_TO_MANIFEST_FILE>] [--disable-parallel]
    [--ignore-failed-sources] [--no-cache] [--interactive]
    [-v|--verbosity <LEVEL>]

dotnet tool restore -h|--help
```

??? info "Principais parâmetros"

    - `--configfile <FILE>`: O arquivo de configuração do NuGet (`nuget.config`) a ser usado.
    - `--add-source <SOURCE>`: Adiciona outra origem do pacote NuGet a ser usada durante a instalação.
    - `--tool-manifest <PATH>`: Caminho para o arquivo de manifesto.
    - `--disable-parallel`: Impedir a restauração de vários projetos em paralelo.
    - `--ignore-failed-sources`: Tratar falhas de origem do pacote como avisos.
    - `--no-cache`: Não armazene em cache pacotes e solicitações HTTP.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic].

??? info "Principais parâmetros"

    - `COMMAND_NAME`: O nome do comando da ferramenta a ser executada.

O comando `dotnet tool restore` localiza o arquivo de manifesto da ferramenta que está no escopo do diretório atual e instala as ferramentas listadas nele.

??? example "Exemplos"

    ```bash
    # Restaura as ferramentas locais para o diretório atual.
    dotnet tool restore
    ```

### dotnet tool run

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-tool-run)

Invoca uma ferramenta local.

```bash
dotnet tool run <COMMAND NAME>

dotnet tool run -h|--help
```

??? info "Principais parâmetros"

    - `COMMAND_NAME`: O nome do comando da ferramenta a ser executada.

O comando `dotnet tool run` pesquisa os arquivos de manifesto da ferramenta que estão no escopo do diretório atual. Quando ele encontra uma referência à ferramenta especificada, ele executa a ferramenta.

??? example "Exemplos"

    ```bash
    # Executa a ferramenta local dotnetsay
    dotnet tool run dotnetsay
    ```

### dotnet tool search

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-tool-search)

Pesquisa todas as ferramentas .NET que são publicadas no NuGet.

```bash
dotnet tool search [--detail]  [--prerelease]
    [--skip <NUMBER>] [--take <NUMBER>] <SEARCH TERM>

dotnet tool search -h|--help
```

??? info "Principais parâmetros"

    - `--detail`: Mostra resultados detalhados da consulta.
    - `--prerelease`: Inclui pacotes de pré-lançamento.
    - `--skip <NUMBER>`: Especifica o número de resultados da consulta a serem ignorados. Usado para paginação.
    - `--take <NUMBER>`: Especifica o número de resultados da consulta a serem mostrados. Usado para paginação.

O comando `dotnet tool search` fornece uma maneira de pesquisar no NuGet ferramentas que podem ser usadas como .NET global, ferramentas do PATH ou ferramentas locais. O comando pesquisa os nomes de ferramentas e os metadados, como títulos, descrições e marcas.
O comando usa a API de pesquisa do NuGet. Ele filtra `packageType=dotnettool` para selecionar apenas pacotes de ferramentas .NET.

??? example "Exemplos"

    ```bash
    # Pesquisa no NuGet.org ferramentas .NET que tenham "Format" em seu nome de pacote ou descrição:
    dotnet tool run dotnetsay

    # Pesquisa no NuGet.org ferramentas .NET que tenham "format" em seu nome de pacote ou metadados, mostre apenas o primeiro resultado e mostre uma exibição detalhada.
    dotnet tool search format --take 1 --detail
    ```

### dotnet tool uninstall

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-tool-uninstall)

Desinstala a ferramenta .NET especificada do seu computador.

```bash
dotnet tool uninstall <PACKAGE_NAME> -g|--global

dotnet tool uninstall <PACKAGE_NAME> --tool-path <PATH>

dotnet tool uninstall <PACKAGE_NAME>

dotnet tool uninstall -h|--help
```

??? info "Principais parâmetros"

    - `PACKAGE_NAME`: Nome/ID do pacote NuGet que contém a ferramenta .NET a ser desinstalada.
    - `-g|--global`: Especifica que a ferramenta a ser removida pertence a uma instalação global. Não pode ser combinada com a opção `--tool-path`. Omitir ambos `--global` e `--tool-path` especifica que a ferramenta a ser removida é uma ferramenta local.
    - `--tool-path <PATH>`: Especifica o local onde a ferramenta será desinstalada. PATH pode ser absoluto ou relativo. Não pode ser combinada com a opção `--global`. Omitir ambos `--global` e `--tool-path` especifica que a ferramenta a ser removida é uma ferramenta local.

O comando `dotnet tool uninstall` fornece uma maneira de desinstalar as ferramentas .NET do seu computador. Para usar o comando, especifique uma das seguintes opções:

- Para desinstalar uma ferramenta global que foi instalada no local padrão, use a opção `--global`.
- Para desinstalar uma ferramenta global que foi instalada em um local personalizado, use a opção `--tool-path`.
- Para desinstalar uma ferramenta local, omita as opções `--global` e `--tool-path`.

??? example "Exemplos"

    ```bash
    # Desinstala a ferramenta global dotnetsay
    dotnet tool uninstall -g dotnetsay

    # Desinstala a ferramenta global dotnetsay de um diretório específico do linux/MacOS
    dotnet tool uninstall dotnetsay --tool-path ~/bin

    # Desinstala a ferramenta local dotnetsay do diretório atual
    dotnet tool uninstall dotnetsay
    ```

### dotnet tool update

[Link documentação](https://docs.microsoft.com/pt-br/dotnet/core/tools/dotnet-tool-update)

Atualiza a ferramenta .net especificada em seu computador.

```bash
dotnet tool update <PACKAGE_ID> -g|--global
    [--add-source <SOURCE>] [--configfile <FILE>]
    [--disable-parallel] [--framework <FRAMEWORK>]
    [--ignore-failed-sources] [--interactive] [--no-cache]
    [-v|--verbosity <LEVEL>] [--version <VERSION>]

dotnet tool update <PACKAGE_ID> --tool-path <PATH>
    [--add-source <SOURCE>] [--configfile <FILE>]
    [--disable-parallel] [--framework <FRAMEWORK>]
    [--ignore-failed-sources] [--interactive] [--no-cache]
    [-v|--verbosity <LEVEL>] [--version <VERSION>]

dotnet tool update <PACKAGE_ID> --local
    [--add-source <SOURCE>] [--configfile <FILE>]
    [--disable-parallel] [--framework <FRAMEWORK>]
    [--ignore-failed-sources] [--interactive] [--no-cache]
    [--tool-manifest <PATH>]
    [-v|--verbosity <LEVEL>] [--version <VERSION>]

dotnet tool update -h|--help
```

??? info "Principais parâmetros"

    - `PACKAGE_ID`: Nome/ID do pacote NuGet que contém a ferramenta global .NET a ser atualizada. Encontre o nome do pacote usando o comando `dotnet tool list`.
    - `--add-source <SOURCE>`: Adiciona outra origem do pacote NuGet a ser usada durante a instalação.
    - --configfile <FILE>: O arquivo de configuração do NuGet (`nuget.config`) a ser usado.
    - `--disable-parallel`: Impedir a restauração de vários projetos em paralelo.
    - `--framework <FRAMEWORK>`: Especifica o framework para a qual atualizar a ferramenta.
    - `--ignore-failed-sources`: Tratar falhas de origem do pacote apenas com avisos.
    - `--local`: Atualize a ferramenta e o manifesto da ferramenta local. Não pode ser combinado com a opção `--global` ou `--tool-path`.
    - `--no-cache`: Não armazene em cache pacotes e solicitações HTTP.
    - `--tool-manifest <PATH>`: Caminho para o arquivo de manifesto.
    - `--tool-path <PATH>`: Especifica o local onde a ferramenta global está instalada. PATH pode ser absoluto ou relativo. Não pode ser combinada com a opção `--global`. Omitir ambos `--global` e `--tool-path` especifica que a ferramenta a ser atualizada é uma ferramenta local.
    - `--version <VERSION>`: O intervalo de versão do pacote de ferramentas para o qual atualizar. Isso não pode ser usado para fazer *downgrade* de versões. primeiro, você deve desinstalar as versões mais recentes.
    - `-g|--global`: Especifica que a atualização destina-se a uma ferramenta de escopo de usuário (global). Não pode ser combinada com a opção `--tool-path`. Omitir ambos `--global` e `--tool-path` especifica que a ferramenta a ser atualizada é uma ferramenta local.
    - `-v|--verbosity <LEVEL>`: Define o nível de detalhes do comando. Os valores permitidos são q[uiet], m[inimal], n[ormal], d[etailed] e diag[nostic].

O comando `dotnet tool update` fornece uma maneira de atualizar as ferramentas do .NET em seu computador para a versão estável mais recente do pacote. O comando desinstala e reinstala uma ferramenta, atualizando-a efetivamente. Para usar o comando, especifique uma das seguintes opções:

- Para atualizar uma ferramenta global que foi instalada no local padrão, use a opção `--global`.
- Para atualizar uma ferramenta global que foi instalada em um local personalizado, use a opção `--tool-path`.
- Para atualizar uma ferramenta local, use a opção `--local`.

??? example "Exemplos"

    ```bash
    # Atualiza a ferramenta global dotnetsay
    dotnet tool update -g dotnetsay

    # Atualiza a ferramenta global dotnetsay localizada em um diretório específico do linux/MacOS
    dotnet tool update dotnetsay --tool-path ~/bin

    # Atualiza a ferramenta local dotnetsay instalada para o diretório atual.
    dotnet tool update dotnetsay

    # Atualiza a ferramenta global dotnetsay para a versão mais recente do patch, com uma versão principal do 2 e uma versão secundária do 0.
    dotnet tool update -g dotnetsay --version 2.0.*

    # Atualiza a ferramenta global dotnetsay para a versão mais baixa dentro do intervalo especificado (> 2.0.0 && < 2.1.4) , a versão 2.1.0 deve ser instalada.
    dotnet tool update -g dotnetsay --version (2.0.*,2.1.4)
    ```

## Referências

- <https://docs.microsoft.com/pt-br/dotnet/core/tools/>
