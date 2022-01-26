# Introdução

SOLID é um acrônimo dos cinco primeiros princípios da programação orientada a objetos e design de código identificados por Robert C. Martin (ou Uncle Bob) por volta do ano 2000. O acrônimo SOLID foi introduzido por Michael Feathers, após observar que os cinco princípios poderiam se encaixar nesta palavra.

|Letra|Sigla|Nome|Definição|
|---|---|---|---|
|S|SRP|[Principio da Responsabilidade Única](./single-responsability-principle.md)|Uma classe deve ter um, e somente um, motivo para mudar.|
|O|OCP|[Princípio Aberto-Fechado](./open-close-principle.md)|Você deve ser capaz de estender um comportamento de uma classe, sem modificá-lo.|
|L|LSP|[Princípio da Substituição de Liskov](./liskov-substitution-principle.md)|As classes derivadas devem ser substituíveis por suas classes base, sem alterar a funcionalidade do programa.|
|I|ISP|[Princípio da Segregação da Interface](./interface-segregation-principle.md)|Muitas interfaces específicas são melhores do que uma interface única.|
|D|DIP|[Princípio da inversão da dependência](./dependency-inversion-principle.md)|Dependa de uma abstração e não de uma implementação.|

Os princípios SOLID devem ser aplicados para se obter os benefícios da orientação a objetos, tais como:

- Seja fácil de se manter, adaptar e se ajustar às alterações de escopo;
- Seja testável e de fácil entendimento;
- Seja extensível para alterações com o menor esforço necessário;
- Que forneça o máximo de reaproveitamento;
- Que permaneça o máximo de tempo possível em utilização.

Utilizando os princípios SOLID é possível evitar problemas muito comuns:

- Dificuldade na testabilidade / criação de testes de unidade;
- Código macarrônico, sem estrutura ou padrão;
- Dificuldades de isolar funcionalidades;
- Duplicação de código, uma alteração precisa ser feita em N pontos;
- Fragilidade, o código quebra facilmente em vários pontos após alguma mudança.

## Referência

- <https://www.tiespecialistas.com.br/orientacao-a-objeto-solid/>
