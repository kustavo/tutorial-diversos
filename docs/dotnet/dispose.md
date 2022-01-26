# IDisposable.Dispose

**Namespace**: System
**Assembly**: System.Runtime.dll

O método `Dispose` executa tarefas definidas pelo aplicativo associadas à liberação ou redefinição de recursos não gerenciados.

```c#
public void Dispose ();
```

!!! note "Notas"
    Liberar recursos é diferente de liberar memória. Somente o **Garbage Collector** gerencia a liberação de memória.

O exemplo a seguir mostra como você pode implementar o método `Dispose`.

```c#
using System;
using System.ComponentModel;

public class Exemplo : IDisposable
{
    private IntPtr handle; // Ponteiro para um recurso externo não gerenciado.
    private Component component = new Component(); // Recurso gerenciado.
    private bool disposed = false; // Se Dispose foi chamado.

    public Exemplo(IntPtr handle)
    {
        this.handle = handle;
    }

    // Implementa IDisposable.
    // Não deve ser virtual. Uma classe derivada não deve ser capaz de sobrescrevê-lo.
    public void Dispose()
    {
        Dispose(disposing: true);
        // Este objeto será limpo pelo método Dispose. Portanto, você deve chamar 
        // GC.SuppressFinalize para retirar este objeto da fila de finalização e evitar 
        // que o código de finalização para este objeto seja executado uma segunda vez.
        GC.SuppressFinalize(this);
    }

    // Se "disposing" é "true", o método foi chamado direta ou indiretamente pelo código de
    // um usuário. Recursos gerenciados e não gerenciados podem ser liberados.
    // Se "disposing" é "false", o método foi chamado em tempo de execução dentro do finalizador 
    // e não deve-se fazer referência a outros objetos. Apenas recursos não gerenciados podem ser descartados.
    protected virtual void Dispose(bool disposing)
    {
        // Verifica se Dispose já foi chamado.
        if (!this.disposed)
        {
            if (disposing)
            {
                // Libera os recursos gerenciados
                component.Dispose();
            }

            // Libera os os recursos não gerenciados chamando o método apropriado.
            CloseHandle(handle);
            handle = IntPtr.Zero;

            // Define que dispose foi realizado.
            disposed = true;
        }
    }

    // Usa interop para chamar o método necessário para limpar o recurso não gerenciado.
    [System.Runtime.InteropServices.DllImport("Kernel32")]
    private extern static Boolean CloseHandle(IntPtr handle);

    // Usando a sintaxe do destruidor C# para o código de finalização.
    // Este destruidor será executado somente se o método Dispose não for chamado.
    // Isso dá à sua classe base a oportunidade de finalizar.
    // Não forneça destruidores em tipos derivados desta classe.
    ~Exemplo()
    {
        Dispose(disposing: false);
    }
}
```

Use este método para manter ou liberar recursos não gerenciados, como arquivos, fluxos e identificadores mantidos por uma instância da classe que implementa esta interface. Por convenção, esse método é usado para todas as tarefas associadas à liberação de recursos mantidos por um objeto ou à preparação de um objeto para reutilização.

!!! note "Notas"
    Se você estiver usando uma classe que implementa a interface `IDisposable`, deverá chamar sua implementação `Dispose` quando terminar de usar a classe.

Ao implementar este método, certifique-se de que todos os recursos retidos sejam liberados propagando a chamada por meio da hierarquia de contenção. Por exemplo, se um objeto A aloca um objeto B, e o objeto B aloca um objeto C, então a implementação de `Dispose` de A deve chamar `Dispose` em B, que por sua vez deve chamar `Dispose` em C.

Um objeto também deve chamar o método `Dispose` de sua classe base se a classe base implementar `IDisposable`.

Se o método `Dispose` de um objeto for chamado mais de uma vez, devemos tratar para que o objeto ignore todas as chamadas após a primeira e evitar um `ObjectDisposedException`.

Os usuários podem esperar que um tipo de recurso use uma convenção específica para denotar um estado alocado versus um estado liberado. Um exemplo disso são as classes de fluxo, tradicionalmente consideradas abertas ou fechadas. O implementador de uma classe que possui tal convenção pode escolher implementar um método público com um nome personalizado, como `Close`, que chama o método `Dispose`.

Como o método `Dispose` deve ser chamado explicitamente, sempre há o perigo de os recursos não gerenciados não serem liberados, quando o consumidor de um objeto não chama seu método `Dispose`. Existem duas maneiras de evitar isso:

- Envolva o recurso gerenciado em um objeto derivado de `System.Runtime.InteropServices.SafeHandle`. Sua implementação `Dispose` irá chamar o método `Dispose` das instâncias `System.Runtime.InteropServices.SafeHandle`.

- Implemente um finalizador para liberar recursos quando `Dispose` não for chamado. Por padrão, o *garbage collector* chama automaticamente o finalizador de um objeto antes de liberar sua memória. No entanto, se o método `Dispose` foi chamado, normalmente é desnecessário para o *garbage collector*  chamar o finalizador do objeto descartado. Para evitar a finalização automática, as implementações de `Dispose` podem chamar o método `GC.SuppressFinalize`.

Quando você usa um objeto que acessa recursos não gerenciados, como um `StreamWriter`, uma boa prática é criar a instância com uma instrução `using`. A instrução `using` fecha automaticamente o fluxo e chama `Dispose` no objeto quando o código que o está usando for concluído.

## Referências

- <https://docs.microsoft.com/en-us/dotnet/api/system.idisposable.dispose?view=net-6.0>
