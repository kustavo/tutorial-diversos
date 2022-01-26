# Data Access Object

[TOC]

## Introdução

Objeto de acesso a dados (em inglês, *Data Access Object* - DAO) é um padrão de projetos para aplicações que utilizam persistência de dados, onde tem a separação das regras de negócio das regras de acesso a banco de dados, onde todas as funcionalidades de bancos de dados, tais como obter conexões, mapear objetos para tipos de dados SQL ou executar comandos SQL, devem ser feitas por classes DAO.

Seguindo o princípio de responsabilidade única do SOLID, um DAO não deve ser responsável por mais do que acesso aos dados.

## DAO x Repository

O DAO é considerado um padrão de integração e é usado como parte da infraestrutura da aplicação. Um DAO serve para você especificar qual o banco de dados vai ser usado, como ele será usado e todas as instruções que devem ser passadas a ele. Ou seja, o DAO conhece e sabe exatamente com qual infraestrutura (banco de dados, arquivos, memória, etc) ele está lidando.

O *Repository* é considerado um padrão de domínio e faz parte das regras de negócios de uma aplicação. É uma interface que não sabe nada da infra-estrutura. Ele não conhece o banco de dados. O que ele conhece é o domínio da aplicação. O *Resopitory* acessa o DAO que por sua vez devolve os dados de uma consulta. Portanto, *Repository* se torna apenas um "repositório de domínio".

O padrão *Repository* tem o objetivo de dar apoio ao domínio (modelo) fornecendo persistência ou não. Ao contrário do DAO, que é um objeto de infra-estrutura da aplicação e faz parte da camada de persistência. O *Repository* faz parte do modelo de domínio que é parte da camada de negócios. O *Repository* inclusive não precisa apenas ser usado em acesso a dados que venham de uma base de dados, podem servir para outros tipos de operações.
