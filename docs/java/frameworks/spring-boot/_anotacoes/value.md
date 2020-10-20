# Value

[TOC]

## Introdução

A anotação `@Value` indica o valor padrão para o campo ou parâmetro do construtor ou método *setter*.

É semelhante à anotação `@Autowire`, mas a anotação `@Value` injeta valores das propriedades no *bean*.

Exemplo de formas de passagem de valores:

```java
/* campo */
@Value("8")
int cylinderCount;

/* construtor */
Engine(@Value("8") int cylinderCount) {
    this.cylinderCount = cylinderCount;
}

/* método setter */
@Autowired
void setCylinderCount(@Value("8") int cylinderCount) {
    this.cylinderCount = cylinderCount;
}

/* método setter segunda forma */
@Value("8")
void setCylinderCount(int cylinderCount) {
    this.cylinderCount = cylinderCount;
}
```

Exemplo de valores que podem ser passados:

```java
/* string */
@Value("Sem nome")
private String defaultName;

/* booleano */
@Value("true")
private boolean defaultBoolean;

/* inteiro */
@Value("10")
private int defaultInt;

/* propriedades do ambiente Spring */
@Value("${APP_NAME_NOT_FOUND}")
private String defaultAppName;

/* variáveis do sistema */
@Value("${java.home}")
private String javaHome;

/* variáveis do sistema */
@Value("${HOME}")
private String homeDir;

/* Spring Expression Language (SpEL) */
@Value("#{systemProperties['java.home']}")
private String javaHome;
```
