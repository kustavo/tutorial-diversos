# Container IoC/DI

O ASP.NET Core possui uma forma simples e direta para resolver dependências, já integrada a ele, sem necessidade de nenhum pacote adicional.

Há três service lifetimes no ASP.NET Core DI:

`Transient`
:   Uma instância para cada resolução de dependência encontrada. Por exemplo, se houver 5 dependências serão 5 instâncias diferentes. É altamente recomendado por não precisar se preocupar com multi-threading e falhas de memória;

`Scope`
:   Uma única instância dessa classe a cada requisição web. Não é recomendado o uso do serviço em aplicações que não sejam web.

`Singleton`
:   Uma única instância dessa classe no ciclo de vida da aplicação. O uso deve considerar multi-threading e prevenir falhas de memória.

A configuração da aplicação para resolver essas dependências é feita no método `ConfigureServices(IServiceCollection services)` no arquivo `Startup.cs`.

```c#
public void ConfigureServices(IServiceCollection services)
{    
    services.AddTransient<IRenderTransient, RenderTransient>();    
    services.AddScoped<IRenderScoped, RenderScoped>();
    services.AddSingleton<IRenderSingleton, RenderSingleton>();
}
```

```c#
public class HomeController : Controller
{
    public static int Count {get;set;}

    public readonly IRenderSingleton Singleton0;
    public readonly IRenderSingleton Singleton1;

    public readonly IRenderScoped Scope0;
    public readonly IRenderScoped Scope1;

    public readonly IRenderTransient Transient0;
    public readonly IRenderTransient Transient1;        

    public HomeController(
        IRenderScoped scope0, 
        IRenderScoped scope1,
        IRenderSingleton singleton0,
        IRenderSingleton singleton1,
        IRenderTransient transient0,
        IRenderTransient transient1
        )
    {
        Scope0 = scope0;
        Scope1 = scope1;
        Singleton0 = singleton0;
        Singleton1 = singleton1;
        Transient0 = transient0;
        Transient1 = transient1;            
    } 

    public IActionResult Index()
    {
        ViewBag.Scopes = new string[2]
        {
          Scope0.GetGuidNow.ToString(),
          Scope1.GetGuidNow.ToString()
        };

        ViewBag.Transients = new string[2]
        {
          Transient0.GetGuidNow.ToString(),
          Transient1.GetGuidNow.ToString()
        };

        ViewBag.Singletons = new string[2]
        {
          Singleton0.GetGuidNow.ToString(),
          Singleton1.GetGuidNow.ToString()
        };

        Count++;
        ViewBag.Count = Count;

        return View();
    }
}
```

Exemplos de lifetimes no ASP.NET Core DI

```c#
public readonly IRenderSingleton Singleton0;
public readonly IRenderSingleton Singleton1;

public readonly IRenderScoped Scope0;
public readonly IRenderScoped Scope1;

public readonly IRenderTransient Transient0;
public readonly IRenderTransient Transient1;

// ...

Scope0.GetGuidNow.ToString(),
Scope1.GetGuidNow.ToString()

Transient0.GetGuidNow.ToString(),
Transient1.GetGuidNow.ToString()

Singleton0.GetGuidNow.ToString(),
Singleton1.GetGuidNow.ToString()  
```

<figure>
        <img src="../_container-ioc-di/exemplos-ciclo-vida.png" title="Fonte: https://fulviocanducci.medium.com/inje%C3%A7%C3%A3o-de-depend%C3%AAncias-asp-net-core-baa3bc1ea9c9"/>
    <figcaption>Exemplos de lifetimes no ASP.NET Core DI</figcaption>
</figure>

No ciclo de vida para cada tipo, o *Singleton* nas duas requisições continuam com o mesmo valor, ou seja, independente se houver mais requisições o ciclo de vida só acaba quando a aplicação encerrar, o *Scoped* possui valores diferentes nas requisição, mas, nas instâncias de cada requisição o seu valor é o mesmo, seguindo o seu conceito que é uma única instância mediante aquela requisição, e por fim *Transient* para cada dependência possui uma nova instância e consequentemente os valores são diferentes independente da requisição.

## Referências

- <https://fulviocanducci.medium.com/inje%C3%A7%C3%A3o-de-depend%C3%AAncias-asp-net-core-baa3bc1ea9c9>
- <https://www.devmedia.com.br/injecao-de-dependencias-no-net-core-2-2/40562>
