# Introdução

[TOC]

## Spring Framework

O Spring é um *framework* open source para a plataforma Java criado por Rod Johnson e descrito em seu livro "*Expert One-on-One: JEE Design e Development*". 

O Spring é um *framework* Java criado com o objetivo de facilitar o desenvolvimento de aplicações, explorando, para isso, os conceitos de Inversão de Controle (IoC) e Injeção de Dependências. Dessa forma, ao adotá-lo, temos à nossa disposição uma tecnologia que nos fornece não apenas recursos necessários à grande parte das aplicações, como módulos para persistência de dados, integração, segurança, testes, desenvolvimento web, como também um conceito a seguir que nos permite criar soluções menos acopladas, mais coesas e, consequentemente, mais fáceis de compreender e manter.

O Spring foi criado por causa das dificuldades que os programadores enfrentavam ao criar determinado tipo de aplicação, mais precisamente, aplicações corporativas. Na época, a plataforma Java voltada para isso, de nome J2EE, ainda era jovem, com ótimas ideias para a construção de aplicações leves, distribuídas, com um amplo leque de opções/ferramentas, mas com algumas limitações. Essas limitações levavam a uma programação dependente de muitas interfaces e com muitas configurações. Ao final, era comum ter uma solução pesada e que trazia consigo muito mais do que o que realmente era necessário.

E para completar, precisávamos utilizar servidores de aplicação pesados, o que tornava a programação e a depuração das aplicações ainda mais lento.

Seguindo um caminho diferente, em pouco tempo o Spring conquistou seu espaço na comunidade. Mas, que caminho diferente foi esse?

- A primeira diferença é que ele não precisa de um servidor de aplicação para funcionar. Fazendo uso apenas da JVM, o Spring traz para o programador recursos que antes só estavam disponíveis para soluções corporativas;

- Com Spring também passamos a utilizar apenas aquilo que é necessário para o projeto. Como mencionado agora há pouco, a plataforma J2EE e os EJBs nos levavam a implementar comportamentos que não eram necessários. Esse diferencial do Spring torna a arquitetura mais leve, fácil de compreender, manter e evoluir;

- Outro diferencial é que ele é baseado na inversão de controle e injeção de dependência, fornecendo para isso um container, que representa o núcleo do framework e que é responsável por criar e gerenciar os componentes da aplicação, os quais são comumente chamados de *beans*.

## Spring Boot

O Spring Boot é um projeto da Spring que veio para facilitar o processo de configuração e publicação de nossas aplicações. A intenção é ter o seu projeto rodando o mais rápido possível e sem complicação.

Ele consegue isso favorecendo a convenção sobre a configuração. Basta que você diga pra ele quais módulos deseja utilizar (WEB, Template, Persistência, Segurança, etc.) que ele vai reconhecer e configurar.

Você escolhe os módulos que deseja através dos starters que inclui no `pom.xml` do seu projeto. Eles, basicamente, são dependências que agrupam outras dependências. Inclusive, como temos esse grupo de dependências representadas pelo starter, nosso pom.xml acaba por ficar mais organizado.

Apesar do Spring Boot, através da convenção, já deixar tudo configurado, nada impede que você crie as suas customizações caso sejam necessárias.

O maior benefício do Spring Boot é que ele nos deixa mais livres para pensarmos nas regras de negócio da nossa aplicação.

## Links

- Documentação oficial

  - <https://docs.spring.io/spring-boot/docs/2.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#getting-started-first-application>
