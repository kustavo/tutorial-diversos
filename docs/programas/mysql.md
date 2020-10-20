# MySQL

[TOC]

## Introdução

## Comandos

### Acesso ao MySQL

```sh
shell> mysql -p
```

### Criar banco

```sh
shell> create database <banco> character set utf8 collate utf8_general_ci;
```

### Definir banco corrente

```sh
mysql> use <banco>;
```

### Importar arquivo

Linux

```sh
shell> mysql <banco> < <arquivo.sql>
```

Ambiente MySQL

```sh
mysql> use <banco>;
mysql> source <arquivo.sql>;
```
