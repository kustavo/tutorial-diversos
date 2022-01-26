# Palavras-chave

Palavras-chave são identificadores predefinidos e reservados que têm significados especiais para o compilador. Eles não podem ser usados ​​como identificadores em seu programa, a menos que incluam `@` como um prefixo. Por exemplo, `@if` é um identificador válido, mas `if` não é porque `if` é uma palavra-chave. A primeira tabela neste tópico lista palavras-chave que são identificadores reservados em qualquer parte de um programa C#. A segunda tabela neste tópico lista as palavras-chave contextuais em C#. Palavras-chave contextuais têm significado especial apenas em um contexto de programa limitado e podem ser usadas como identificadores fora desse contexto. Geralmente, à medida que novas palavras-chave são adicionadas à linguagem C#, elas são adicionadas como palavras-chave contextuais para evitar a interrupção de programas escritos em versões anteriores.

| Palavra-chave | Descrição |
| --- | ----- |
|abstract|Indica que uma classe, método, propriedade, indexador ou evento deve ser implementado na classe derivada.|
|as|Converte um objeto em um tipo diferente sem lançar exceção se o tipo for incompatível.|
|base|Acessa membros da classe base estando na classe derivada.|
|bool|Tipo de valor boleano|
|break|Termina o loop envolvente mais próximo ou instrução `switch` em que aparece.|
|byte|Tipo de valor numérico integral de faixa 0..255|
|case|Usando junto com `switch` e determina uma condição dentro do `switch`.|
|catch|Usando junto com `try` e captura uma exceção. |
|char|Tipo de valor caractere.|
|checked|Habilitar explicitamente a verificação de *overflow* para operações aritméticas de tipo integral e conversões. Verificação já feita por padrão.|
|class|Tipo de referência que declara uma classe.|
|const|Usado em declarações de campo e variável local para tornar a variável constante.|
|continue|Encerrar a iteração do loop atual e prosseguir para o próximo.|
|decimal|Tipo de valor numérico de ponto flutuante de faixa ±1.0 x 10<sup>-28</sup> até ±7.9228 x 10<sup>28</sup>|
|default (switch)|Usando junto com `switch` e especifica o rótulo padrão.|
|default (operador ou literal)|Produz o valor padrão de um tipo.|
|default (generics)|Sobrescreve ou explicita a implementação da interface.|
|delegate|Declara uma construção de programação usada para obter uma referência que pode ser chamada para um método de uma classe.|
|do|Executa um bloco de instruções enquanto uma expressão booleana é avaliada após cada execução do loop.|
|double|Tipo de valor numérico de ponto flutuante de faixa ±5.0 × 10<sup>−324</sup> até ±1.7 × 10<sup>308</sup>|
|else|Usado junto com `if` e executa um bloco de instrução quando a condição `if` é falsa.|
|enum|Tipo de valor de enumeração.|
|event|Declara um evento em uma classe.|
|explicit|Usada para criar operadores de conversão de tipo que só podem ser usados ​​especificando uma conversão de tipo explícita.|
|extern|Declara que um método que é implementado externamente em uma DLL.|
|false|Valor booleano falso.|
|finally|Usando junto com `try` e executa um bloco de instrução independente se houve alguma exceção.|
|fixed|Usada para evitar que o *garbage collector* realoque uma variável ou para criar *buffers* de tamanho fixo. |
|float|Tipo de valor numérico de ponto flutuante de faixa ±1.5 x 10<sup>−45</sup> até ±3.4 x 10<sup>38</sup>|
|for|Avalia se uma expressão booleana é válida e   executa um bloco de instruções enquanto essa expressão é verdadeira.|
|foreach|Executa um bloco de instruções para cada elemento em uma coleção.|
|goto|Transfere o controle do programa diretamente para uma instrução rotulada.|
|if|Executa um bloco de instruções se a condição avaliada é verdadeira.|
|implicit|Define quais tipos podem ser convertidos sem a necessidade de conversão explícita.|
|in (foreach)|Usado para enumerar um loop *foreach*.|
|in (generics) |Especifica que o parâmetro de tipo é contravariante.|
|in (modificar de parâmetro)|Permite que você passe um argumento para um método por referência em vez de por valor.|
|interface|Tipo de referência que declara uma interface.|
|internal|Modificador de acesso para tipos e membros de tipo que define visibilidade apenas dentro do *assembly* que o implementa.|
|is|Compara um objeto a um tipo e, se eles forem iguais ou do mesmo "tipo", retorna verdadeiro.|
|lock|Recurso *multi-threaded* que permite que uma seção de código use exclusivamente um recurso.|
|long|Tipo de valor numérico integral de faixa -9,223,372,036,854,775,808 até 9,223,372,036,854,775,807.|
|namespace|Declara um escopo que contém um conjunto de objetos relacionados.|
|new|Cria uma nova instância de um tipo.|
|null|Literal que representa uma referência nula, que não se refere a nenhum objeto.|
|object|Representa a classe base da qual derivam todos os outros tipos de referência. Alias de `System.Object`.|
|operator|Permite que uma classe sobrecarregue os operadores aritméticos e de conversão.|
|out|Especifica que uma variável deve ser passada por referência a um método e definida e inicializada nesse método.|
|override|Usada para estender ou modificar a implementação abstrata ou virtual de um método, propriedade, indexador ou evento herdado.|
|params|Especifica um parâmetro de método que leva um número variável de argumentos.|
|private|Modificador de acesso para tipos e membros de tipo que define visibilidade apenas dentro da classe envolvente.|
|protected|Modificador de acesso para tipos e membros de tipo que define visibilidade para a classe envolvente e classes derivadas.|
|public|Modificador de acesso para tipos e membros de tipo que define visibilidade para qualquer classe.|
|readonly (field)|Indica que a atribuição ao campo só pode ocorrer como parte da declaração ou em um construtor na mesma classe.|
|readonly (struct)|Indica que o tipo de estrutura é imutável.|
|ref|Especifica explicitamente que uma variável deve ser passada por referência em vez de por valor.|
|return|Retorna a execução de um método ou de um acessador de propriedade.|
|sbyte|Tipo de valor numérico integral de faixa -128..127|
|sealed|Especificar que uma classe não pode ser herdada.|
|short|Tipo de valor numérico integral de faixa -32,768 até 32,767.|
|sizeof|Retorna quantos bytes um objeto requer para ser armazenado.|
|stackalloc|Usado em um contexto de código não seguro para alocar um bloco de memória na pilha.|
|static|Declara uma classe ou um membro de classe como associados a classe em vez de instâncias de classe.|
|string|Tipo de referência que declara uma sequência de zero ou mais caracteres Unicode.|
|struct|Tipo de valor que pode encapsular dados e funcionalidades relacionadas.|
|switch|Instrução de controle que lida com várias seleções e enumerações, passando o controle para uma das instruções `case`.|
|this|Usada em um método de instância ou propriedade de instância para se referir à instância de classe atual.|
|throw|Usada para lançar um objeto de exceção.|
|true|Valor booleano verdadeiro.|
|try|Usada para identificar um bloco de instrução como o corpo de uma seqüência de tratamento de exceção.|
|typeof|Retorna uma instância da classe `System.Type` quando passa o nome de uma classe.|
|uint|Tipo de valor numérico integral de faixa 0 até 4,294,967,295.|
|ulong|Tipo de valor numérico integral de faixa 0 até 18,446,744,073,709,551,615.|
|unchecked|Evita a verificação de *overflow* ao fazer aritmética de inteiros.|
|unsafe|Usada para modificar um procedimento ou definir um bloco de código que usa código não seguro.|
|ushort|Tipo de valor numérico integral de faixa 0 até 65,535|
|using (statement)|Usada para definir um escopo no qual no final um objeto será descartado.|
|using (directive)|Permite o uso de tipos definidos em um *namespace* sem especificar o *namespace* totalmente qualificado.|
|using (alias)| Usado para atribuir a um identificador um *alias* de um *namespace* ou tipo.|
|virtual|Usada para modificar um método, propriedade, indexador ou declaração de evento para permitir que ele seja sobrescrito em uma classe derivada.|
|void|Usada em assinaturas de método para declarar um método que não retorna um valor|
|volatile|Declara uma variável que pode mudar seu valor ao longo do tempo por um processo externo, o hardware do sistema ou threads em execução simultânea.|
|while|Executa um bloco de instruções enquanto uma expressão booleana especificada é avaliada como verdadeira.|

## Palavras-chave contextual

Uma palavra-chave contextual é usada para fornecer um significado específico no código, mas não é uma palavra reservada em C#. Algumas palavras-chave contextuais, como `partial` e `where`, têm significados especiais em dois ou mais contextos.

| Palavra-chave | Descrição |
| --- | ----- |
|add|Define um acessador de evento customizado que é chamado quando o código do cliente se inscreve no evento.|
|and|Cria um padrão que corresponde quando ambos os padrões aninhados coincidem.|
|alias|Usada para indicar um alias externo, quando queremos diferenciar várias versões do mesmo assembly com os mesmos nomes de tipo totalmente qualificados.|
|async|Indica que o método modificado, a expressão lambda ou o método anônimo é assíncrono.|
|await|Suspende um método assíncrono até que a tarefa esperada seja concluída.|
|dynamic|Permite que o tipo de uma variável, propriedade ou método seja determinada em tempo de execução.|
|get|Define um método acessador para uma propriedade ou indexador.|
|global|Alias do _namespace global_, útil para diferenciar o nome de uma classe e um _namespace_.|
|in|Identifica a coleção a ser enumerada em um loop `foreach`.|
|init|Define um método acessador para uma propriedade ou indexador.|
|managed (function pointer calling convention)|Define que é um tipo de ponteiro de objeto no heap gerenciado. É equivalente à referência C# a um objeto.|
|nameof|Obtem o nome de uma variável, tipo ou membro como _string_. |
|nint|Define um tipo de dados inteiro de tamanho nativo.|
|not|Cria um padrão que corresponde quando o padrão negado não corresponde.|
|notnull|Especifica que o argumento de tipo deve ser um tipo de valor não anulável ou tipo de referência não anulável.|
|nuint|Define um tipo de dados inteiro não assinado de tamanho nativo.|
|or|Cria um padrão que corresponde quando qualquer um dos padrões aninhados corresponde.|
|partial (type)|Permitem que a definição de uma classe, estrutura, interface ou registro seja dividida em vários arquivos.|
|partial (method)|Método definido em uma classe parcial mas sua implementação é definida na outra classe parcial.|
|record|Usado para definir um tipo de registro.|
|remove|Define um acessador de evento personalizado que é chamado quando o código do cliente cancela a assinatura do evento.|
|set|Define um método acessador para uma propriedade ou indexador.|
|unmanaged (function pointer calling convention)|Define que é um tipo de ponteiro não gerenciado. Equivalente a um ponteiro tradicional de estilo C.|
|unmanaged (generic type constraint)|Define que os tipos construídos sejam tipos não gerenciados.|
|value|Usado para definir acessadores e adicionar ou remover manipuladores de eventos.|
|var|Permite que o tipo de uma variável declarada no escopo do método seja determinado pelo compilador.|
|when (filter condition)|Especifica uma condição de filtro para um bloco `try-catch` ou `case` de uma instrução `switch`.|
|where (generic type constraint)|Adiciona restrições a uma declaração genérica.|
|with|Produz uma cópia de seu operando com as propriedades especificadas e os campos modificados.|
|yield|Usado em um bloco iterador para retornar um valor ao objeto enumerador ou para sinalizar o fim da iteração.|

## Palavras-chave contextual usadas em _queries_

| Palavra-chave | Descrição |
| --- | ----- |
|ascending|Usada na cláusula `orderby` em expressões _query_ para ordenar do menor para o maior.|
|by|Usando junto com `group` para agrupar um conjunto de registros em uma _query_.|
|descending|Usada na cláusula `orderby` em expressões _query_ para ordenar do maior para o menor.|
|equals|As palavras-chave `join`, `in`, `on`, `equals` e às vezes `into` são usadas para relacionar itens em duas fontes de dados diferentes por um atributo comum em uma _query_.|
|from|Usado para iniciar uma _query_.|
|group|Usando junto com `by` para agrupar um conjunto de registros em uma _query_.|
|in|Usado juntamente com `from` em uma _query_.|
|into|As palavras-chave `join`, `in`, `on`, `equals` e às vezes `into` são usadas para relacionar itens em duas fontes de dados diferentes por um atributo comum em uma _query_.|
|join|As palavras-chave `join`, `in`, `on`, `equals` e às vezes `into` são usadas para relacionar itens em duas fontes de dados diferentes por um atributo comum em uma _query_.|
|let|Usado para definir uma subexpressão em uma _query_|
|on|As palavras-chave `join`, `in`, `on`, `equals` e às vezes `into` são usadas para relacionar itens em duas fontes de dados diferentes por um atributo comum em uma consulta.|
|orderby|Usado para definir a ordem de classificação dos resultados da _query_.|
|select|Usado para determinar quais variáveis ​​retornar no final de uma _query_.|
|where (query clause)|Usada para consultar uma fonte de dados e selecionar ou filtrar elementos para retornar.|

## Referências

- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/>
- <https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/contextual-keywords>
- <https://en.wikibooks.org/wiki/C_Sharp_Programming/Keywords>
