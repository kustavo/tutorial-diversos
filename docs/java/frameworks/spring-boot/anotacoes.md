# Anotações

[TOC]

## Anotações do Spring Framework Core

### Anotações geral

#### @Required

Essa anotação indica que o método *setter* de um *bean* deve ser configurado para receber injeção de dependência com a propriedade necessária no momento da configuração. Em outras palavras, indica que o *bean* deve ser preenchido no momento da configuração com a propriedade necessária.

Exemplo usando `@Required` nos métodos *setter* para marcar dependências que queremos preencher através do XML:

```java
@Required
void setColor(String color) {
    this.color = color;
}
```

```xml
<bean class="com.javaguides.spring.Car">
    <property name="color" value="verde" />
</bean>
```

#### @Autowired

Permite a injeção da dependência do objeto implicitamente, ou seja, marca uma dependência que o Spring resolverá e injetará.

[Veja mais](_anotacoes/auto-wired.md)

#### @Qualifier

Indica qual bean será usada cosa haja mais de um bean do mesmo tipo.

[Veja mais](_anotacoes/qualifier.md)

#### <a name="configuration2"></a> @Configuration

Indica que uma classe declara um ou mais métodos `@Bean` e pode ser processada pelo contêiner Spring para gerar definições de *bean* e solicitações de serviço para esses *beans* em tempo de execução. `@Configuration` engloba a anotação `@Component`.

[Veja mais](_anotacoes/configuration.md)

#### @ComponentScan

Essa anotação é usada com a anotação `@Configuration` para permitir que o Spring conheça os pacotes que serão analisados em busca das notações. Também é possível especificar pacotes externos para serem inclusos na análise através dos atributos `basePackageClasses` ou `basePackage`. Se pacotes específicos não forem definidos, a análise ocorrerá a partir do pacote da classe que declara essa anotação e prosseguirá pelos sub-pacotes apenas.

[Veja mais](_anotacoes/component-scan.md)

#### @Bean

Esta anotação é usada no nível do método. A anotação `@Bean` é usada juntamente com o `@Configuration` para criar *beans* Spring. O método anotado com esta anotação funciona como ID do bean e cria e retorna o bean real.

O *bean* resultante tem o mesmo nome que o *factory method*. Para usar um nome diferente, ele deve ser passado como argumento.

```java
@Bean("engine")
Engine getEngine() {
    return new Engine();
}
```

#### @Value

A anotação `@Value` indica o valor padrão para o campo ou parâmetro do construtor ou método *setter*.

[Veja mais](_anotacoes/value.md)

#### @Valid

O Spring fornece suporte para validação declarativa com JSR-303. Esse suporte é ativado automaticamente se um provedor JSR-303, como o Hibernate Validator, estiver presente no seu `CLASSPATH`. Quando ativado, você pode acionar a validação simplesmente anotando um parâmetro do método *Controller* com a anotação `@Valid`.

```java
@PostMapping("/users")
ResponseEntity<String> addUser(@Valid @RequestBody User user) {
    // persisting the user
    return ResponseEntity.ok("User is valid");
}
```

#### @Lazy
#### @DependsOn
#### @Lookup
#### @Primary
#### @Scope
#### @Order

### Anotações de estereótipo

#### @Component

Esta anotação é usada em classes para indicar que é componente Spring. A anotação `@Component` marca a classe para que o mecanismo de busca de componente do Spring a adicione ao contexto da aplicação.

Por padrão, as instâncias de *bean* destas classes têm o mesmo nome que o nome da classe com uma **inicial em minúscula**. Entretanto, podemos especificar um nome diferente usando o argumento de valor opcional desta anotação.

Como `@Repository`, `@Service`, `@Configuration` e `@Controller` são todos meta-anotações de `@Component`, eles compartilham o mesmo comportamento de nomeação de *beans*. Além disso, o Spring os coleta automaticamente durante o processo de busca de componentes.

#### @Controller

A anotação que informa ao contêiner Spring IOC que a classe é um controlador do Spring MVC.

#### @Service

Anotação que informa que é uma classe que executa algum serviço, como lógica de negócios, cálculos e chamar APIs externas. Portanto, define que é uma classe que pertence a camada de serviços.

#### @Repository

Esta anotação é usada nas classes que estão trabalhando diretamente com a camada de banco de dados. As classes anotadas com `@Repository` capturam exceções específicas da plataforma do banco de dados e as lançam novamente como uma das exceções das subclasses de `DataAccessExeption` do Spring.

#### @Configuration

[Presente em outra seção](#Anotações)

### Anotações de configuração do contexto

#### @Profile
#### @Import
#### @ImportResource
#### @PropertySource
#### @PropertySources

## Anotações do Spring Framework MVC

### @Controller

[Presente em outra seção](#Anotações)

### @CookieValue
### @CrossOrigin
### @RequestMapping

Esta anotação é usada no nível de classe ou de método. A anotação `@RequestMapping` é usada para mapear requisições *Web* para classes e métodos específicos que fazem parte de um `@Controller`.

[Veja mais](_anotacoes/request-mapping.md)

### Variantes que compõem @RequestMapping

#### @ModelAttribute

Com esta anotação, podemos acessar elementos que estão no Model de um MVC `@Controller`, passando o nome do Model como parâmetro.

```java
@PostMapping("/teste")
void assembleExemplo(@ModelAttribute("exemploClass") ExemploClass exemplo) { }

/* Sem parâmetro Spring irá usar o mesmo nome do argumento*/
@PostMapping("/teste")
void assembleExemplo(@ModelAttribute ExemploClass exemploClass) { }
```

Se anotarmos um método, o Spring adicionará automaticamente o valor de retorno do método ao modelo.

```java
@ModelAttribute("exemploClass")
ExemploClass getExemplo() { }

/* Sem parâmetro Spring irá usar o mesmo nome do método*/
@ModelAttribute
ExemploClass exemploClass() { }
```

#### @GetMapping

Mapeia requisições HTTP GET para os métodos que irão manipulá-los. Equivale a `@RequestMapping(method = RequestMethod.GET)`.

``` java
@GetMapping("/exemplo")
public List<Exemplo> getAllExemplos() {
    return exemploRepository.findAll();
}

/* ou */

@GetMapping("/exemplo/{id}")
public ResponseEntity<Exemplo> getExemploById(@PathVariable(value = "id") Long id) { }
```

#### @PostMapping

Mapeia requisições HTTP POST para os métodos que irão manipulá-los. Equivale a `@RequestMapping(method = RequestMethod.POST)`.

#### @PutMapping

Mapeia requisições HTTP PUT para os métodos que irão manipulá-los. Equivale a `@RequestMapping(method = RequestMethod.PUT)`.

#### @DeleteMapping

Mapeia requisições HTTP DELETE para os métodos que irão manipulá-los. Equivale a `@RequestMapping(method = RequestMethod.DELETE)`.

#### @PatchMapping

Mapeia requisições HTTP PATCH para os métodos que irão manipulá-los. Equivale a `@RequestMapping(method = RequestMethod.PATCH)`.

As requisições PUT e PATCH são usadas para indicar um requisição de alteração de dados. O PUT, indica que a alteração do dado será com referência a entidade completa. Já o PATCH indica atualização parcial, ou seja, somente alguns campos da entidade.

#### @ExceptionHandler
#### @InitBinder
#### @Mappings e @Mapping
#### @MatrixVariable
#### @PathVariable

Esta anotação indica que um argumento de método está vinculado a uma variável de template do URI.

```java
@RequestMapping("exemplo/{id}")
Exemplo getExemplo(@PathVariable("id") long id) { }

/* Sem parâmetro Spring irá usar o mesmo nome do argumento */
@RequestMapping("exemplo/{id}")
Exemplo getExemplo(@PathVariable long id) { }
```

Podemos marcar a variável como não obrigatória.

```java
@RequestMapping("/{id}")
Exemplo getExemplo(@PathVariable(required = false) long id) { }
```

#### @RequestAttribute
#### @RequestBody

A anotação `@RequestBody` indica que um parâmetro do método deve ser vinculado ao valor do corpo da requisição HTTP (`HttpRequest`). Em outras palavras, a anotação `@RequestBody` mapeia o corpo `HttpRequest` (JSON) para um objeto Java.

O `HttpMessageConveter` é responsável pela conversão da mensagem de requisição HTTP para o objeto.

 No exemplo abaixo, o corpo `HttpRequest` será convertido para o objeto da classe `ClasseExemplo`.

```java
@PostMapping("/teste")
public ResponseEntity funcao(@RequestBody ClasseExemplo exemplo) { }
```

#### @RequestHeader
#### @RequestParam
#### @RequestPart
#### @ResponseBody

A anotação `@ResponseBody` indica que o objeto retornado no método será serializado automaticamente em JSON e gravado na resposta HTTP (`HttpResponse`).

Assim como no `@RequestBody`, o `HttpMessageConveter` também é responsável pela conversão do objeto para a resposta HTTP.

```java
@ResponseBody
@PostMapping("/teste")
public ResponseEntity funcao() {

    return new ClasseExemplo();
}
```

Se anotarmos uma classe `@Controller` com `@ResponseBody`, a anotação será aplicada em todos métodos, equivalendo a anotação `@RestController`.

#### @ResponseStatus
#### @ControllerAdvice
#### @RestController

Incorpora o conjunto de anotações: `@Controller` e `@ResponseBody`.

Com a anotação `@RestController`, todos métodos da classe terão implicitamente a anotação `@ResponseBody`.

#### @RestControllerAdvice
#### @SessionAttribute
#### @SessionAttributes

## Anotações do Spring Boot

### @SpringBootApplication

Incorpora o conjunto de anotações:  `@EnableAutoConfiguration` + `@ComponentScan` + `@Configuration` + `@ConfigurationPropertiesScan`.

[Veja mais](_anotacoes/spring-boot-application.md)

### @EnableAutoConfiguration

Configura a aplicação automaticamente baseando nos *JARs* presentes no `CLASSPATH` e em como os *beans* foram definidos.

[Veja mais](_anotacoes/enable-auto-configuration.md)

### Condicionais da autoconfiguração

#### @ConditionalOnClass e @ConditionalOnMissingClass

A classe anotada será considerada pela configuração automática somente se a classe no argumento da anotação estiver presente/ausente.

```java
@Configuration
@ConditionalOnClass(DataSource.class)
class MySQLAutoconfiguration { }
```

#### @ConditionalOnBean e @ConditionalOnMissingBean

A classe anotada será considerada pela configuração automática somente se o *bean* no argumento da anotação estiver presente/ausente.

```java
@Bean
@ConditionalOnBean(name = "dataSource")
LocalContainerEntityManagerFactoryBean entityManagerFactory() { }
```

#### @ConditionalOnProperty

A classe anotada será considerada pela configuração automática somente se os valores das propriedades (campos) corresponderem as valores do argumento da anotação.

```java
@Bean
@ConditionalOnProperty(
    name = "usemysql",
    havingValue = "local"
)
DataSource dataSource() { }
```

#### @ConditionalOnResource

A classe anotada será considerada pela configuração automática somente se o recurso no argumento da anotação estiver presente/ausente.

```java
@ConditionalOnResource(resources = "classpath:mysql.properties")
Properties additionalProperties() { }
```

#### @ConditionalOnWebApplication e @ConditionalOnNotWebApplication

A classe anotada será considerada pela configuração automática somente se a aplicação atual é ou não uma aplicação Web.

```java
@ConditionalOnWebApplication
HealthCheckController healthCheckController() { }
```

#### @ConditionalExpression

A classe anotada será considerada pela configuração automática somente se a expressão SpEL (Spring Expression Language) for verdadeira.

```java
@Bean
@ConditionalOnExpression("${usemysql} && ${mysqlserver == 'local'}")
DataSource dataSource() { }
```

#### @Conditional

A classe anotada será considerada pela configuração automática somente se satisfazer a condição personalizada da classe passada como parâmetro.

```java
@Conditional(ExemploCondition.class)
Properties additionalProperties() { }
```

## Anotações do Spring Framework Scheduling

### @EnableAsync
### @EnableScheduling
### @Async
### @Scheduled
### @Schedules

## Anotações do Spring Framework Cloud

### @EnableConfigServer
### @EnableEurekaServer
### @EnableDiscoveryClient
### @EnableCircuitBreaker
### @HystrixCommand

## Anotações do Spring Framework DataAccess

### @Transactional
### @NoRepositoryBean
### @Param
### @Id
### @Transient
### @CreatedBy, @LastModifiedBy, @CreatedDate, @LastModifiedDate

## Anotações do Spring Framework Data JPA

### @Query
### @Procedure
### @Lock
### @Modifying
### @EnableJpaRepositories

## Anotações do Spring Framework Cache-Based

### @Cacheable
### @CachePut
### @CacheEvict
### @CacheConfig

## Anotações do Spring Framework Testing

### @BootstrapWith
### @ContextConfiguration
### @WebAppConfiguration
### @Timed
### @Repeat
### @Commit
### @RollBack
### @DirtiesContext
### @BeforeTransaction
### @AfterTransaction
### @Sql
### @SqlConfig
### @SqlGroup
### @SpringBootTest
### @DataJpaTest
### @DataMongoTest
### @WebMVCTest
### @AutoConfigureMockMVC
### @MockBean
### @JsonTest
### @TestPropertySource

## Links

- <https://www.baeldung.com/spring-core-annotations>
- <https://springframework.guru/spring-framework-annotations>
- <https://www.javaguides.net/2018/10/15-spring-core-annotations.html>
- <https://www.javadevjournal.com/spring/spring-annotations/>
