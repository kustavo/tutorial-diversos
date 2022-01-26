# Data Transfer Object

[TOC]

## Introdução

Objetos de transferência de dados (em inglês, *Data Transfer Object* - DTO) é um padrão de projetos bastante usado em Java para o transporte de dados entre diferentes componentes de um sistema, diferentes instâncias, diferentes processos de um sistema distribuído ou diferentes sistemas via serialização.

Um DTO não possui comportamento algum, exceto o de armazenamento e obtenção de seus próprios dados. DTOs são objetos simples que não contêm qualquer lógica de negócio que requeira testes.

A ideia consiste basicamente em agrupar um conjunto de campos de uma ou mais classes numa única classe simples de forma a otimizar a comunicação. Numa chamada remota, seria ineficiente passar cada campo individualmente. Da mesma forma seria ineficiente ou até causaria erros passar uma entidade mais complexa.

Além disso, muitas vezes os dados usados na comunicação não refletem exatamente os campos do seu modelo. Então, um DTO seria uma classe que provê exatamente aquilo que é necessário para um determinado processo.

Abaixo temos um exemplo de um DTO, onde não queremos expor o campo `cpf` de `Pessoa`. Além disso a classe `Pessoa` possui um tipo `Carro`, e podemos "achatá-la" para que o objeto DTO tenha apenas tipos primitivos.

```java
/* entidades */
class Pesssoa {

    int id;
    String nome;
    String cpf;
    Carro carro;

    public Pesssoa(int id, String nome, Carro carro) {
        this.id = id;
        this.nome = nome;
        this.carro = carro;
    }

    /* gets() e sets() */
}

class Carro {

    String modelo;

    public Carro(String modelo) {
        this.modelo = modelo;
    }

    /* gets() e sets() */
}
```

```java
/* dto */
class PessoaDto {

    int idPessoa;
    String nomePessoa;
    String modeloCarro;

    /*
     * Um DTO não tem lógicas de negócio, portanto não devem fazer mapeamentos. Portanto
     * o construtor possui apenas seus próprios campos como parâmetro.
     */
    public PessoaDto(int idPessoa, String nomePessoa, String modeloCarro) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.modeloCarro = modeloCarro;
    }
}
```

O mapeamento de um objeto da entidade `Pessoa` para um DTO ou vice-versa, deve ser feito pela camada *controller*.

```java
/* controller */
PessoaDto convertToPessoaDto(Pesssoa pessoa) {

    return new PessoaDto(pessoa.getId(), pessoa.getNome(), pessoa.carro.getModelo());
}

Pesssoa convertToPessoa(PessoaDto pessoaDto) {

    Carro carro = new Carro(pessoaDto.getModeloCarro());
    return new Pesssoa(pessoaDto.getIdPessoa(), pessoaDto.getNomePessoa(), carro);
}
```

No exemplo acima, poderíamos verificar se o `id` é nulo, se fosse, então seria considerado um novo objeto, caso contrário, poderíamos buscar o objeto persistido e alterar somente os novos valores dos campos.

<div class='importante' markdown='1'>

Fazer o mapeamento manualmente de DTOs para as classes de negócio pode criar rapidamente muito código, induzir a erros e consumir muito tempo. Para isso existem diversos frameworks de mapeamento de objetos como: `MapStruct`, `ModelMapper`, `Orika`, `JMapper`, `Dozer` etc.

</div>
