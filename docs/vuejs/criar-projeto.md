# Instalação

[TOC]

## Criar um projeto

```sh
vue init <TEMPLATE> <PROJETO>
cd PROJETO
```

Tipos de template:

- **webpack**:

    Usa o webpack como module bundler, possui o vue-loader com hot reload, javascript lint e testes unitários. É o pacote mais completo existente.

- **webpack-simple**:

    Usa o webpack, com menos recursos que o primeiro. Não possui hot reload, javascript lint e nem testes unitários. É recomendo para quem está começando com o vue.

- **browserify**:

    Ao invés do webpack, usa o browserify como module bundler. Possui o vue-loader com hot reload, javascript lint e testes unitários.

- **browserify-simple**:

    Mais simples que o anterior, sem hot reload, lint ou testes unitários.

- **simple**:

    Mais simples, impossível. Possui apenas uma única página com o Vue sendo carregado através de um endereço CDN.

## Dependências

Baixar dependências informadas no arquivo de configuração

```sh
npm install
```

Baixar dependência específica definidas no arquivo de configuração

```sh
npm install <elemento> --save
```

## Execução

Execução em modo de desenvolvimento

```sh
nmp run dev
```

Execução em modo de teste

```sh
nmp run test
```