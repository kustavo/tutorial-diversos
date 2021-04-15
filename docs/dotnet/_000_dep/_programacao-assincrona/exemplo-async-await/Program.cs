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

            /* 
             * Bloqueia o thread principal, de modo que o método "ShowTodaysInfo()" possa concluir a execução
             * antes do encerramento do aplicativo.
             * Caso contrário os passos 4, 5 e 6 não seriam executados até fim de execução do sistema.
             */
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
            // Este é o trabalho que podemos realizar enquanto aguardamos a conclusão da Tarefa assíncrona
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
