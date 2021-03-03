# Programação assíncrona

[TOC]

## Introdução

Se você tiver qualquer necessidade vinculada à E/S (como a solicitação de dados de uma rede ou o acesso a um banco de dados), você desejará usar a programação assíncrona. Você também pode ter código vinculado à CPU, como a execução de um cálculo dispendioso, que também é um bom cenário para escrever código assíncrono.

O `C#` tem um modelo de programação assíncrono em nível de linguagem que permite escrever facilmente o código assíncrono sem precisar manipular retornos de chamada ou estar em conformidade com uma biblioteca que dá suporte à assincronia. Ele segue o que é conhecido como  *TAP - Task-based Asynchronous Pattern* (Padrão Assíncrono Baseado em Tarefa).

O núcleo da programação assíncrona são os objetos `Task` e `Task<T>`, que modelam o trabalho que está sendo feito em segundo plano. Eles têm suporte das palavras-chave `async` e `await`. O modelo é bastante simples na maioria dos casos:

- O código assíncrono pode ser usado tanto para o código vinculado à E/S quanto vinculado à CPU, mas de maneira diferente para cada cenário.

  - Para o código vinculado à E/S, você espera (`await`) uma operação que retorna uma tarefa (`Task` ou `Task<T>`) dentro de um método assíncrono (`async`).

  - Para o código vinculado à CPU, você espera (`await`) uma operação que é iniciada em um *thread* em segundo plano com o método `Task.Run`.

- A palavra-chave `async` transforma um método em um método assíncrono, o que permite que você use a palavra-chave `await` em seu corpo.

- Quando a palavra-chave `await` é aplicada, ela suspende o método de chamada e transfere o controle de volta ao seu chamador até que a tarefa em espera seja concluída.

<div class='importante' markdown='1'>

Segundo a convenção usada no .NET, você deve adicionar **"Async"** como o sufixo de cada nome de método assíncrono que escrever.

</div>

```c#
using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace exemplo_async_await
{
    class Program
    {
        private const string URL = "https://docs.microsoft.com/en-us/dotnet/csharp/csharp";

        static void Main(string[] args)
        {
            TrabalhoSincrono();
            var tarefa = TrabalhoAssincrono();
            TrabalhoAposEspera();

            // Bloqueia o thread principal, de modo que o método "TrabalhoAssincrono()" possa concluir 
            // a execução antes do encerramento do aplicativo.
            // Caso contrário os passos 4, 5 e 6 não seriam executados até fim de execução do sistema.
            tarefa.Wait();
        }
        public static void TrabalhoSincrono()
        {
            Console.WriteLine("1. Trabalho síncrono");
        }

        static async Task TrabalhoAssincrono()
        {
            Console.WriteLine("2. Trabalho assíncrono!");

            // Eperando o método Async GetStringAsync
            await GetStringAsync();
        }

        static async Task GetStringAsync()
        {
            using (var httpClient = new HttpClient())
            {
                Console.WriteLine("3. Esperando o resultado de GetStringAsync");

                // Espera GetStringAsync (Task<string>) completar 
                string result = await httpClient.GetStringAsync(URL);

                // Para as linha abaixo, a execuçãoo será retomada assim que as espera for concluída.
                Console.WriteLine("4. A tarefa em espera foi completada");
                Console.WriteLine($"5. O tamanho do http Get para {URL}");
                Console.WriteLine($"6. {result.Length} caracteres");
            }
        }

        static void TrabalhoAposEspera()
        {
            // Executa o trabalho enquanto aguarda a conclusão da Tarefa assíncrona
            Console.WriteLine("7. Trabalho enquanto aguardamos a conclusão da tarefa assíncrona");
            for (var i = 0; i <= 5; i++)
            {
                for (var j = i; j <= 5; j++)
                {
                    Console.Write("*");
                }
                Console.WriteLine();
            }
        }
    }
}

// Saida
// > 1. Trabalho síncrono
// > 2. Trabalho assíncrono!
// > 3. Esperando o resultado de GetStringAsync
// > 7. Trabalho enquanto aguardamos a conclusão da tarefa assíncrona
// > ******
// > *****
// > ****
// > ***
// > **
// > *
// > 4. A tarefa em espera foi completada
// > 5. O tamanho do http Get para https://docs.microsoft.com/en-us/dotnet/csharp/csharp
// > 6. 40165 caracteres
```

Outro exemplo, mostrando o fluxo de funcionamento:

<div class='imagem' markdown='1' style="width: 100%">

![exemplo-fluxo-assincrono](_programacao-assincrona/exemplo-fluxo-assincrono.png)

</div>

## Tipos de retorno

Métodos assíncronos podem conter os seguintes tipos de retorno:

- **`Task<TResult>`**: para um método assíncrono que retorna um valor.

- **`Task`**: para um método assíncrono que executa uma operação, mas não retorna nenhum valor.

- **`void`**: para um manipulador de eventos.

- Desde o `C# 7.0`, qualquer tipo que tenha um método acessível `GetAwaiter`. O objeto retornado pelo método `GetAwaiter` deve implementar a interface **`System.Runtime.CompilerServices.ICriticalNotifyCompletion`**.

[Veja mais](<https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/concepts/async/async-return-types>)

## Async void

Async void só deve ser usado para manipuladores de eventos.
O `async void` é a única maneira de permitir que os manipuladores de eventos assíncronos trabalhem, pois os eventos não têm tipos de retorno (portanto, não podem fazer uso de `Task` e `Task<T>`). Qualquer outro uso de `async void` não segue o modelo TAP e pode ser um desafio utilizá-lo, como:

- As exceções lançadas em um método `async void` não podem ser capturadas fora desse método.

- Os métodos `async void` são muito difíceis de testar.

- Os métodos `async void` poderão causar efeitos colaterais indesejados se o chamador não estiver esperando que eles sejam assíncronos.

## Bloqueio

Código que bloqueia a thread atual como um meio de aguardar a conclusão de uma tarefa pode resultar em deadlocks e threads de contexto bloqueados e pode exigir tratamento de erros significativamente mais complexo. A tabela a seguir fornece diretrizes de como lidar com a espera de tarefas de uma forma sem bloqueio:

Use isto... | Em vez disto... | Quando desejar fazer isso
---------|----------|---------
 await | Task.Wait ou Task.Result | Recuperação do resultado de uma tarefa em segundo plano
 await Task.WhenAny | Task.WaitAny | Aguardar a conclusão de qualquer tarefa
 await Task.WhenAll | Task.WaitAll | Aguardar a conclusão de todas as tarefas
 await Task.Delay | Thread.Sleep | Aguardar por um período de tempo

[Veja mais](<https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide/concepts/async/>)

### WhenAll

Retorna um `Task` que é concluído ao final de todas as tarefas na lista de argumentos:

```c#
await Task.WhenAll(eggTask, baconTask, toastTask);
Console.WriteLine("eggs are ready");
Console.WriteLine("bacon is ready");
Console.WriteLine("toast is ready");
Console.WriteLine("Breakfast is ready!");
```

### WhenAny

Retorna uma `Task<Task>` que é concluída quando qualquer um dos argumentos é concluído. Você pode aguardar a tarefa retornada, sabendo que ela já foi concluída.

O código a seguir mostra como você poderia usar `WhenAny` para aguardar a primeira tarefa concluir e, em seguida, processar seu resultado. Depois de processar o resultado da tarefa concluída, você remove essa tarefa concluída da lista de tarefas passada para `WhenAny`.

```c#
var allTasks = new List<Task>{eggsTask, baconTask, toastTask};
while (allTasks.Any())
{
    Task finished = await Task.WhenAny(allTasks);
    if (finished == eggsTask)
    {
        Console.WriteLine("eggs are ready");
    }
    else if (finished == baconTask)
    {
        Console.WriteLine("bacon is ready");
    }
    else if (finished == toastTask)
    {
        Console.WriteLine("toast is ready");
    }
    allTasks.Remove(finished);
}
Console.WriteLine("Breakfast is ready!");
```

## Monitoração de estado

Devemos escrever código que aguarda tarefas de uma maneira sem bloqueio. Não depender do estado de objetos globais ou da execução de determinados métodos. Em vez disso, depender apenas dos valores retornados dos métodos. Por quê?

- Será mais fácil raciocinar sobre o código.

- O código será mais fácil de testar.

- Misturar código assíncrono e síncrono será muito mais simples.

- As condições de corrida poderão, normalmente, ser completamente evitadas.

- Dependendo dos valores retornados, a coordenação de código assíncrono se tornará simples.

- Funciona muito bem com a injeção de dependência.
