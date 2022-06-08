# Functions

O _Azure Functions_ é uma solução sem servidor que permite escrever menos código, manter menos infraestrutura e economizar custos. Em vez de se preocupar com a implantação e manutenção de servidores, a infraestrutura em nuvem fornece todos os recursos atualizados necessários para manter seus aplicativos em execução.

Frequentemente, construímos sistemas para reagir a uma série de eventos críticos. Esteja você construindo uma API da web, respondendo a mudanças no banco de dados, processando fluxos de dados de IoT ou até mesmo gerenciando filas de mensagens. Todo aplicativo precisa de uma maneira de executar algum código conforme esses eventos ocorrem.

Para atender a essa necessidade, o _Azure Functions_ fornece "computação sob demanda" de duas maneiras significativas. Primeiro, o _Azure Functions_ permite que você implemente a lógica do seu sistema em blocos de código prontamente disponíveis. Esses blocos de código são chamados de "funções". Diferentes funções podem ser executadas a qualquer momento em que você precisar responder a eventos críticos. Em segundo lugar, conforme as solicitações aumentam, o _Azure Functions_ atende à demanda com quantos recursos e instâncias de função forem necessários, mas apenas enquanto for necessário. À medida que as solicitações caem, quaisquer recursos extras e instâncias de aplicativos são descartados automaticamente. Fornecer recursos de computação sob demanda é a essência da computação sem servidor no _Azure Functions_.

Em muitos casos, uma função se integra a uma variedade de serviços em nuvem para fornecer implementações ricas em recursos.

Os itens a seguir são um conjunto comum, mas de forma alguma exaustivo, de cenários para o _Azure Functions_.
| Se você quiser... | então... |
| --- | --- |
| Construir uma API da web | Implementar um endpoint para seus aplicativos da web usando o HTTP _trigger_ |
| Processar uploads de arquivos | Execute o código quando um arquivo for carregado ou alterado no armazenamento de _blob_ |
| Crie um fluxo de trabalho _serverless_ | Encadeie uma série de funções usando funções duráveis |
| Responder às mudanças do banco de dados | Execute a lógica personalizada quando um documento for criado ou atualizado no _Cosmos DB_ |
| Executar tarefas agendadas | Execute o código em intervalos de tempo predefinidos |
| Crie sistemas de fila de mensagens confiáveis | Processar filas de mensagens usando _Queue Storage_, _Service Bus_, ou _Event Hubs_ |
| Analise fluxos de dados IoT | Colete e processe dados de dispositivos IoT |
| Processar dados em tempo real | Use Funções e _SignalR_ para responder aos dados no momento |

## Criando funções

Podemos criar funções usando:

- [Portal Azure](https://docs.microsoft.com/en-us/azure/azure-functions/functions-create-function-app-portal)
- [Visual Studio Code](https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-vs-code-csharp)
- [CLI](https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-csharp?tabs=in-process%2Cazure-cli)

??? note "Exemplo de função em .NET 5.0 usando gatilho de tempo"

    === "Function"

        ```c#
        using System;
        using Microsoft.Azure.Functions.Worker;
        using Microsoft.Extensions.Logging;

        namespace exemplo
        {
            public static class FunctionTimer
            {
                [Function("FunctionTimer")]
                public static void Run([TimerTrigger("0 */5 * * * *")] MyInfo myTimer, FunctionContext context)
                {
                    var logger = context.GetLogger("exemplo");
                    logger.LogInformation($"C# Timer trigger function executed at: {DateTime.Now}");
                    logger.LogInformation($"Next timer schedule at: {myTimer.ScheduleStatus.Next}");
                }
            }

            public class MyInfo
            {
                public MyScheduleStatus ScheduleStatus { get; set; }

                public bool IsPastDue { get; set; }
            }

            public class MyScheduleStatus
            {
                public DateTime Last { get; set; }

                public DateTime Next { get; set; }

                public DateTime LastUpdated { get; set; }
            }
        }
        ```

    === "Program"

        ```c#
        using System.Threading.Tasks;
        using Microsoft.Extensions.Configuration;
        using Microsoft.Extensions.Hosting;
        using Microsoft.Azure.Functions.Worker.Configuration;

        namespace teste
        {
            public class Program
            {
                public static void Main()
                {
                    var host = new HostBuilder()
                        .ConfigureFunctionsWorkerDefaults()
                        .Build();

                    host.Run();
                }
            }
        }
        ```

    === "Project"

        ```xml
        <Project Sdk="Microsoft.NET.Sdk">
        <PropertyGroup>
            <TargetFramework>net5.0</TargetFramework>
            <AzureFunctionsVersion>v3</AzureFunctionsVersion>
            <OutputType>Exe</OutputType>
        </PropertyGroup>
        <ItemGroup>
            <PackageReference Include="Microsoft.Azure.Functions.Worker.Extensions.Timer" Version="4.0.1" />
            <PackageReference Include="Microsoft.Azure.Functions.Worker.Sdk" Version="1.0.3" OutputItemType="Analyzer" />
            <PackageReference Include="Microsoft.Azure.Functions.Worker" Version="1.1.0" />
        </ItemGroup>
        <ItemGroup>
            <None Update="host.json">
            <CopyToOutputDirectory>PreserveNewest</CopyToOutputDirectory>
            </None>
            <None Update="local.settings.json">
            <CopyToOutputDirectory>PreserveNewest</CopyToOutputDirectory>
            <CopyToPublishDirectory>Never</CopyToPublishDirectory>
            </None>
        </ItemGroup>
        </Project>
        ```

    === "host.json"

        ```json
        {
            "version": "2.0",
            "logging": {
                "applicationInsights": {
                    "samplingSettings": {
                        "isEnabled": true,
                        "excludedTypes": "Request"
                    }
                }
            }
        }
        ```

    === "local.settings.json"

        ```json
        {
            "IsEncrypted": false,
            "Values": {
                "AzureWebJobsStorage": "",
                "FUNCTIONS_WORKER_RUNTIME": "dotnet-isolated"
            }
        }
        ```

??? note "Exemplo de função em .NET 5.0 usando gatilho http"

    === "Function"

        ```c#
        using System.Collections.Generic;
        using System.Net;
        using Microsoft.Azure.Functions.Worker;
        using Microsoft.Azure.Functions.Worker.Http;
        using Microsoft.Extensions.Logging;

        namespace exemplo
        {
            public static class FunctionHttp
            {
                [Function("FunctionHttp")]
                public static HttpResponseData Run([HttpTrigger(AuthorizationLevel.Anonymous, "get", "post")] HttpRequestData req,
                    FunctionContext executionContext)
                {
                    var logger = executionContext.GetLogger("FunctionHttp");
                    logger.LogInformation("C# HTTP trigger function processed a request.");

                    var response = req.CreateResponse(HttpStatusCode.OK);
                    response.Headers.Add("Content-Type", "text/plain; charset=utf-8");

                    response.WriteString("Welcome to Azure Functions!");

                    return response;
                }
            }
        }

        // Executando: https://exemplo.azurewebsites.net/api/FunctionHttp
        // Saída:
        // Welcome to Azure Functions!
        ```

    === "Program"

        ```c#
        using System.Threading.Tasks;
        using Microsoft.Extensions.Configuration;
        using Microsoft.Extensions.Hosting;
        using Microsoft.Azure.Functions.Worker.Configuration;

        namespace teste
        {
            public class Program
            {
                public static void Main()
                {
                    var host = new HostBuilder()
                        .ConfigureFunctionsWorkerDefaults()
                        .Build();

                    host.Run();
                }
            }
        }
        ```

    === "Project"

        ```xml
        <Project Sdk="Microsoft.NET.Sdk">
        <PropertyGroup>
            <TargetFramework>net5.0</TargetFramework>
            <AzureFunctionsVersion>v3</AzureFunctionsVersion>
            <OutputType>Exe</OutputType>
        </PropertyGroup>
        <ItemGroup>
            <PackageReference Include="Microsoft.Azure.Functions.Worker.Extensions.Http" Version="3.0.12" />
            <PackageReference Include="Microsoft.Azure.Functions.Worker.Sdk" Version="1.0.3" OutputItemType="Analyzer" />
            <PackageReference Include="Microsoft.Azure.Functions.Worker" Version="1.1.0" />
        </ItemGroup>
        <ItemGroup>
            <None Update="host.json">
            <CopyToOutputDirectory>PreserveNewest</CopyToOutputDirectory>
            </None>
            <None Update="local.settings.json">
            <CopyToOutputDirectory>PreserveNewest</CopyToOutputDirectory>
            <CopyToPublishDirectory>Never</CopyToPublishDirectory>
            </None>
        </ItemGroup>
        </Project>
        ```

    === "host.json"

        ```json
        {
            "version": "2.0",
            "logging": {
                "applicationInsights": {
                    "samplingSettings": {
                        "isEnabled": true,
                        "excludedTypes": "Request"
                    }
                }
            }
        }
        ```

    === "local.settings.json"

        ```json
        {
            "IsEncrypted": false,
            "Values": {
                "AzureWebJobsStorage": "",
                "FUNCTIONS_WORKER_RUNTIME": "dotnet-isolated"
            }
        }
        ```

## Referências

- <https://www.youtube.com/watch?v=BCI51DidFnM>
- <https://docs.microsoft.com/en-us/azure/azure-functions/functions-overview>
- <https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-vs-code-csharp?tabs=in-process>
