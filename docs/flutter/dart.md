# Dart

Dart é uma linguagem de script voltada à web desenvolvida pela Google e lançada em 2011. O objetivo da linguagem Dart foi inicialmente a de substituir a JavaScript como a linguagem principal embutida nos navegadores. Programas nesta linguagem podem tanto serem executados em uma máquina virtual quanto compilados para JavaScript.

A missão inicial do Dart era substituir o JavaScript para desenvolvimento de scripts em páginas web. Porém, com a evolução da linguagem e com o passar dos anos, ela hoje pode ser considerada uma linguagem multi-paradigma, embora a linguagem apresente fortes estruturas típicas de linguagens orientadas a objeto.

Hoje, sabemos que o Dart não obteve muito sucesso em sua missão inicial em substituir o JavaScript nos navegadores. Porém, o desenvolvimento e posterior sucesso do Flutter, que é fundamentado no Dart, fez com que a linguagem voltasse à tona, atraindo a atenção de muitos desenvolvedores.

O Dart possui algumas variantes no que diz respeito a seu ambiente de execução. O código Dart pode ser executado em uma máquina virtual (chamada **DartVM**, máquina virtual está inserida em um conjunto de ferramentas chamado Dart Native). Esta máquina virtual ainda pode ser executada em dois modos diferentes: **JIT** (Just-in-Time Compiler) e **AOT** (Ahead-of-Time Compiler).

De maneira mais simplista, a compilação JIT ocorre no momento da execução de um trecho de código, onde o código Dart é convertido para código de máquina à medida em que ele é executado. Já na execução AOT, o código é convertido para código de máquina previamente. A outra maneira na qual podemos executar o código Dart é através de um processo de transpilação para JavaScript através da ferramenta **dart2js** (integrante do Dart SDK). Todos estes modos de execução tornam o Dart uma linguagem muito flexível e que pode ser executada tanto em ambientes nativos (como em aplicações mobile e desktop) como em ambientes web (como em uma aplicação web que utilize o Angular, por exemplo).

## Sintaxe

O Dart possui uma sintaxe com estilo baseado no C. Isso faz com que sua sintaxe seja muito similar à linguagens atualmente populares, como Java e C#. Porém, o Dart tenta reduzir um pouco os ruídos característicos de linguagens baseadas no C.

Um simples "Hello World" em Dart poderia ser escrito da seguinte forma:

```dart
main() {
  print('Hello World!');
}
```

Dart também possui algumas características de linguagens funcionais. Isso fica claro no exemplo abaixo, onde uma sequência de Fibonacci é gerada.

```dart
int fib(int n) => (n > 2) ? (fib(n - 1) + fib(n - 2)) : 1;

void main() {
  print('fib(20) = ${fib(20)}');
}
```

## Referências

- <https://www.treinaweb.com.br/blog/o-que-e-dart>
- <https://pt.wikipedia.org/wiki/Dart_(linguagem_de_programa%C3%A7%C3%A3o)>
