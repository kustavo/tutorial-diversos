# ComponentScan e ComponentScans

[TOC]

## Introdução

Essa anotação é usada com a anotação `@Configuration` para permitir que o Spring conheça os pacotes que serão analisados em busca das notações. Também é possível especificar pacotes externos para serem inclusos na análise através dos atributos `basePackageClasses` ou `basePackage`. Se pacotes específicos não forem definidos, a análise ocorrerá a partir do pacote da classe que declara essa anotação e prosseguirá pelos sub-pacotes apenas.

Definindo os pacotes a serem analisados.

```java
@Configuration
@ComponentScan(basePackages = {"com.exemplo.um", "com.exemplo.dois"})
class VehicleFactoryConfig {}
```

Especificando as classes do pacote que serão analisadas.

```java
@Configuration
@ComponentScan(basePackageClasses = {ClasseUm.class, ClasseDois.class})
class VehicleFactoryConfig {}
```

Especificando as classes dos pacotes que serão analisadas. Se as classes existirem em mais de um pacote, todas serão consideradas.

```java
@Configuration
@ComponentScan(basePackages = {"com.exemplo.um", "com.exemplo.dois"})
@ComponentScan(basePackageClasses = {ClasseUm.class, ClasseDois.class})
class VehicleFactoryConfig {}
```

Especifica qual classe de cada pacote será analisada.

```java
@Configuration
@ComponentScans({
    @ComponentScan(basePackages = "com.exemplo.um"),
    @ComponentScan(basePackageClasses = ClasseUm.class)
    }
    @ComponentScans({
    @ComponentScan(basePackages = "com.exemplo.dois"),
    @ComponentScan(basePackageClasses = ClasseDois.class)
    }
)
class VehicleFactoryConfig {}
```
