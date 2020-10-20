# Maven

[TOC]

## Introdução

Maven é uma ferramenta de gerenciamento e automação de construção (*build*) de projetos. Entretanto, por fornecer diversas funcionalidades adicionais através do uso de *plugins* e estimular o emprego de melhores práticas de organização, desenvolvimento e manutenção de projetos, é muito mais do que apenas uma ferramenta auxiliar.

Um desenvolvedor que seja alocado em um projeto Java EE que utilize o Maven corretamente não terá que saber de imediato quais dependências (bibliotecas) o projeto necessita para compilar e executar, não precisará descobrir onde obtê-las e nem irá se preocupar em como realizar a construção do pacote do aplicativo. Com um comando simples, como `mvn install`, na raiz do código-fonte do projeto, instruirá o Maven a gerar o código extra necessário (cliente de um web service, por exemplo), validar e compilar o projeto, testá-lo através de seus testes unitários e gerar o pacote com o código compilado. Outras etapas poderiam incluir auditoria de qualidade de código, documentação, geração de estatísticas, entre diversas possibilidades.

## Instalação

1. Download do arquivo compactado em <https://maven.apache.org/download.cgi>.

1. Extrair arquivo.

1. Adicionar o diretório `bin` na variável de ambiente `$PATH`.

    ```sh
    PATH=$PATH:\CAMINHO\bin
    ```

1. Conferir se foi configurado com sucesso.

    ```sh
    mvn -version
    ```

## Configurações

A unidade básica de configuração do Maven é um arquivo chamado `pom.xml`, que deve ficar na raiz do seu projeto. Ele é um arquivo conhecido como *Project Object Model*. Nele é declarado a estrutura, dependências e características do projeto.

O `pom.xml` é deixado na raiz do projeto para poder chamar as *targets* de *build* do projeto. O menor arquivo `pom.xml` válido é o seguinte:

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>br.com.empresa</groupId>
  <artifactId>teste</artifactId>
  <version>1.0</version>
</project>
```

Onde:

- **modelVersion**: identificação da versão do arquivo `pom.xml` e deve ser sempre 4.0.0.

- **groupId**: um identificador da empresa/grupo ao qual o projeto pertence. Geralmente o nome do site da empresa/grupo ao contrário. Ex: br.com.empresa.

- **artifactId**: o nome do projeto. Ex: teste.

- **version**: a versão atual do projeto. Ex: 1.0-SNAPSHOT.

## Gerenciamento de dependência

Para dizer que o `log4j 1.2.15`, por exemplo, é uma dependência da sua aplicação é só acrescentar no seu pom as linhas:

```xml
<project>
...
  <dependencies>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.15</version>
    </dependency>
  </dependencies>
...
</project>
```

Quando necessário, o Maven vai baixar o jar do `log4j 1.2.15`, e todas as suas dependências, e vai colocá-las no *classpath* da sua aplicação durante os *builds*, testes, etc. Ou seja, não é necessário entrar no site do *log4j*, baixar um zip com vários *jars* e ter que procurar quais *jars* devem ser colocados no *classpath*.

Todos os *jars* baixados pelo Maven são guardados na pasta `repository` dentro da `M2_HOME` que foi configurado durante a instalação de Maven. Assim, se mais de um projeto for depende do mesmo *jar*, ele não é baixado de novo.

## Utilização de plugins

Além dos principais *targets* do Maven, é possível executar *targets* de *plugins*. Você só precisa digitar na linha de comando:

```ssh
mvn [nomedoplugin]:[target]
```

E então o Maven baixa o *plugin*, se necessário, e executa a *target* pra você. Existe uma lista bem grande de *plugins* do Maven e uma boa parte desses plugins podem ser usados sem nenhuma configuração adicional no `pom.xml`.

| Comando         | Descrição                                       |
| --------------- | ----------------------------------------------- |
| dependency:tree | Listagem das dependências que o projeto possui. |
| spring-boot:run | Executar o projeto feito em Spring-boot.        |

## Ciclos de vida

Um conceito muito importante por trás do Maven é o ciclo de vida do projeto. Um ciclo de vida possui estágios, chamados de fases. Em cada fase, um ou mais objetivos podem ser executados.

O Maven possui nativamente 3 ciclos de vida: *clean*, *site*, *default*. Esses ciclos de vida são divididos em fases, e são essas fases que invocamos quando queremos executar alguma operação.

- **Clean**
  
  - **pre-clean**: executa ações necessárias antes da limpeza do build anterior.
  
  - **clean**: remove os artefatos gerados no build anterior.

- **Site**

  - **site**: cria as páginas web e a documentação do projeto.

  - **site-deploy**: faz o deploy(implantação) da documentação e das páginas web geradas na fase site em um servidor.

- **Default**

  - **initialize**: prepara o *build* configurando propriedades, criando diretórios, etc.

  - **process-resources**: processa e copia os resources para o diretório das classes compiladas.

  - **compile**: compila os códigos-fontes de produção.

  - **test-compile**: compila os códigos-fonte de teste.

  - **test**: executa os testes unitários e cria os relatórios.

  - **package**: empacota os artefatos do *build* em um arquivo distribuível, como um JAR, por exemplo.

  - **install**: salva no repositório local(/home/seuusername/.m2/repository) o arquivo distribuível gerado na fase package.

É possível invocar qualquer dessas fases na linha de comando, digitando:

```sh
mvn [fase]
```

Quando executamos uma fase, todas as fases anteriores do ciclo de vida são executadas. Por exemplo, quando invocamos a fase `compile`, antes de executar os ações dessa fase, o Maven executa as fases `initialize` e `process-resources`.

Algumas das fases do ciclo possuem *plugins* associadas a elas, e esses plugins são executados assim que a fase é chamada para ser executada. É possível registrar *plugins* para rodarem em qualquer fase do ciclo, conseguindo, assim, personalizar o *build* do seu projeto facilmente.

Por exemplo, se você quiser criar um *jar* com o código fonte do projeto, e que esse *jar* seja gerado depois que o projeto foi empacotado, é só acrescentar no `pom.xml`:

```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-source-plugin</artifactId>
        <executions>
          <execution>
            <id>attach-sources</id>
            <phase>package</phase>
            <goals>
              <goal>jar</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  ...
</project>
```

Assim, o `plugin` `Source` vai executar seu `goal` `jar` na fase `package` do ciclo de vida. É como se fosse chamado `mvn source:jar` quando o *build* passa pela fase de `package`. A fase `package` já possui um *plugin* associado a ela: o `jar:jar` (supondo que é um projeto jar), então o `plugin` `source` só será executado depois do `jar:jar`.

Em geral se for registrado mais de um plugin pra mesma fase, eles serão executados na ordem em que eles forem declarados.
