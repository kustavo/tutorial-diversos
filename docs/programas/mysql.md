# MySQL

## Introdução

O MySQL é um sistema de gerenciamento de banco de dados (SGBD), que utiliza a linguagem SQL como interface. É atualmente um dos sistemas de gerenciamento de bancos de dados mais populares da Oracle Corporation.

### Características

- Portabilidade (suporta praticamente qualquer plataforma atual);
- Compatibilidade (existem drivers ODBC, JDBC e .NET e módulos de interface para diversas linguagens de programação, como Delphi, Java, C/C++, C#, Visual Basic, Python, Perl, PHP, ASP e Ruby)
- Excelente desempenho e estabilidade;
- Pouco exigente quanto a recursos de novos hardware;
- Facilidade no manuseio;
- É um Software Livre com base na GPL (entretanto, se o programa que acessar o Mysql não for GPL, uma licença comercial deverá ser adquirida)[9];
- Contempla a utilização de vários Storage Engines como MyISAM, InnoDB, Falcon, BDB, Archive, Federated, CSV, Solid…
- Suporta controle transacional;
- Suporta Triggers;
- Suporta Cursors (Non-Scrollable e Non-Updatable);
- Suporta Stored Procedures e Functions;
- Replicação facilmente configurável;
- Interfaces gráficas (MySQL Toolkit) de fácil utilização cedidos pela MySQL Inc.

## Comandos

### Criar banco

```bash
# shell
create database <banco> character set utf8 collate utf8_general_ci;
```

### Acesso ao MySQL CLI

```bash
mysql -p
```

### Definir banco corrente

```bash
# MySQL CLI
mysql> use <banco>;
```

### Importar arquivo

```bash
# shell
mysql <banco> < <arquivo.sql>

# MySQL CLI
mysql> use <banco>;
mysql> source <arquivo.sql>;
```

## Referências

- <https://pt.wikipedia.org/wiki/MySQL>
