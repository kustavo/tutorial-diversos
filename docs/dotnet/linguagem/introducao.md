# Introdução

[TOC]

C# é uma linguagem de programação, multiparadigma, de tipagem forte, desenvolvida pela Microsoft como parte da plataforma `.NET`. A sua sintaxe orientada a objetos foi baseada no C++ mas inclui muitas influências de outras linguagens de programação, como Object Pascal e, principalmente, Java. O código fonte é compilado para `Common Intermediate Language` (CIL) que é interpretado pela máquina virtual `Common Language Runtime` (CLR). C# é uma das linguagens projetadas para funcionar na `Common Language Infrastructure` (CLI) da plataforma .NET Framework.

## Execução do código na CLR e o JIT

Para executarmos uma aplicação C#, precisamos passar o código CIL do programa para a CLR, a máquina virtual do .Net. A CLR por sua vez precisa executar o código da aplicação no sistema operacional do usuário e, para isso, precisa emitir o código de máquina correto para o ambiente em que o programa está sendo executado. Mas a CLR não interpreta o CIL do programa, isso seria muito lento, ao invés disso, quando o programa C# é carregado na memória, a CLR converte automaticamente o código CIL para código de máquina, esse processo é feito por um compilador `Just in Time` (JIT) da CLR.

Esse carregamento utilizando o JIT faz com que o código escrito na linguagem C# execute com o desempenho máximo, o mesmo de um programa escrito em linguagens que compilam diretamente para o código de máquina, mas com a vantagem de executar no ambiente integrado do .Net.

## Links

- <https://pt.wikipedia.org/wiki/C_Sharp>

- <https://docs.microsoft.com/pt-br/dotnet/csharp/programming-guide>

- <https://docs.microsoft.com/pt-br/dotnet/csharp/language-reference>

- <https://docs.microsoft.com/pt-br/dotnet/csharp/whats-new>

### Editores on-line

- <https://dotnetfiddle.net>

