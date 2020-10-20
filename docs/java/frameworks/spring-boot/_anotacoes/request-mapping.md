# RequestMapping

[TOC]

## Introdução

Esta anotação é usada no nível de classe ou de método. A anotação `@RequestMapping` é usada para mapear requisições *Web* para classes e métodos específicos que fazem parte de um `@Controller`. Quando o `@RequestMapping` é usado no nível da classe, ele cria um URI base para o qual o controlador será usado. Quando essa anotação é usada nos métodos, ele fornece o URI no qual os métodos do manipulador serão executados. A partir disso, é possível inferir que o mapeamento de requisição no nível de classe permanecerá o mesmo, enquanto cada método terá seu próprio mapeamento de requisição.

`@RequestMapping` pode ser configurado usando:

- **Caminho ou seus *aliases*, nome e valor**: da URL que será mapeada para o método.
- **Método**: métodos HTTP compatíveis
- **Parâmetros**: filtra solicitações com base na presença, ausência ou valor de parâmetros HTTP
- **Cabeçalhos**: filtra solicitações com base na presença, ausência ou valor dos cabeçalhos HTTP
- **Consumidor**: quais tipos de mídia o método pode consumir no corpo da solicitação HTTP
- **Produtor**: quais tipos de mídia o método pode produzir no corpo da resposta HTTP.

Usando em nível de classe:

```java
@Controller
class EmployeeController {

    /*
     * Manipula requisições Web de enderço "/employees/home" do tipo GET 
     */
    @RequestMapping(value = "/employees/home", method = RequestMethod.GET)
    String home() {
        return "home";
    }
}
```

Usando em nível de método:

```java
@Controller
@RequestMapping(value = "/employees", method = RequestMethod.GET)
class EmployeeController {

    @RequestMapping("/home")
    String home() {
        return "home";
    }
}
```

Outro exemplo:

```java
@Controller
public class SpringMVCController {

 @RequestMapping(
   value = {"/greetings", "/hello-world"},
   method = {RequestMethod.GET, RequestMethod.POST},
   consumes = {"application/json", "application/xml"},
   produces = {"application/json"},
   headers = {"application/json"})
 public String hellpWorld() {
  return "Hello";
 }
}
```

Além disso, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` e `@PatchMapping` são variantes diferentes de `@RequestMapping` com o método HTTP já definido como GET, POST, PUT, DELETE e PATCH, respectivamente.
