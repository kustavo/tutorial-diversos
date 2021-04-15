# Introdução

.NET (antigamente .NET Core) é um framework livre e de código aberto para os sistemas operacionais Windows, Linux e macOS. É um sucessor de código aberto do .NET Framework. O projeto é uma iniciativa da empresa Microsoft, lançado com a Licença MIT, que visa uma plataforma única para desenvolvimento e execução de sistemas e aplicações. Todo e qualquer código gerado para .NET pode ser executado em qualquer dispositivo que possua um framework de tal plataforma. Com ideia semelhante à plataforma Java, o programador deixa de escrever código para um sistema ou dispositivo específico, e passa a escrever para a plataforma .NET. Aplicações escritas para ele funcionam em um ambiente de software controlado, em oposição a um ambiente de hardware, através de uma máquina virtual de aplicação.

## Arquitetura

A **Common Language Infrastructure - CLI** é uma especificação aberta (padrão técnico) desenvolvida pela Microsoft que permite o uso de diferentes linguagens de alto nível em diferentes plataformas de computador sem a necessidade de reescrever o código para nenhuma arquitetura específica. Em termos simples, a CLI permite que uma aplicação escrita em qualquer linguagem de programação seja executada em qualquer sistema operacional usando um programa em tempo de execução comum, em vez de um programa específico para cada linguagem. Isso implica que é agnóstico de plataforma. O .NET e Mono são implementações da CLI.

Uma grande biblioteca de classes chamada **Framework Class Library - FCL** implementa as bibliotecas padrão fundamentais da CLI. Os programas escritos em qualquer uma das linguagens que suportam a plataforma .NET podem usar classes e métodos FCL e portanto criar objetos de classe, chamar seus métodos, herdar as classes FCL necessárias, etc. O FCL Oferece APIs para UI de console, acesso a dados, conectividade com banco de dados, redes, web, criptografia, acesso aos serviços do sistema operacional, estruturas de dados e algoritmos diversos, facilidades para a linguagem e muito mais.

Os programas escritos para .NET, são compilados em uma linguagem intermediária chamada **Common Intermediate Language - CIL** (antigamente chamado de *Microsoft Intermediate Language - MSIL*), em vez de serem compilados diretamente em código de máquina.

Os programas escritos para .NET são executados em um ambiente de software (em contraste com um ambiente de hardware) denominado **Common Language Runtime - CLR**. O CLR é uma máquina virtual de aplicação para a execução gerenciada de programas CLI. Realiza o gerenciamento de memória, controle de exceção, interoperabilidade, manipulação de processamento paralelo e concorrente, reflexão, segurança, serviços de compilação para a arquitetura específica, entre outros. Portanto, o código de computador escrito usando .NET é chamado de "código gerenciado".

O código CIL compilado é armazenado em *assemblies CLI*. Os *assemblies* são armazenados no formato de arquivo *Portable Executable - PE*, que é basicamente uma estrutura de dados que encapsula a informação necessária para que a CLR possa manipular o código executável que está empacotado.

Os *assemblies* formam as unidades fundamentais de implantação, controle de versão, reutilização, escopo de ativação e permissões de segurança para aplicações .NET. Um *assembly* é uma coleção de tipos e recursos compilados para funcionar juntos e formar uma unidade lógica de funcionalidade. Os *assemblies* assumem a forma de arquivos executáveis (`.exe`) ou *Dynamic Link Library* (`.dll`) e são os blocos de construção de aplicações .NET. Eles oferecem ao CLR as informações de que ele precisa para as implementações.

Os *assemblies* têm as seguintes propriedades:

- Os *assemblies* são implementados como arquivos `.exe` ou `.dll`.
- Para bibliotecas direcionadas ao .NET, você pode compartilhar *assemblies* entre aplicações, colocando-os no *Global Assembly Cache - GAC*.
- Os *assemblies* são carregados na memória somente se forem usados, podendo ser uma maneira eficiente de gerenciar recursos em projetos grandes.
- Você pode obter informações de um assembly programaticamente usando *reflection* (reflexão).
- Você pode carregar um assembly apenas para inspecioná-lo usando a classe `MetadataLoadContext` e os métodos `Assembly.ReflectionOnlyLoad` ou `Assembly.ReflectionOnlyLoadFrom`.

Em tempo de execução, um compilador **Just-In-Time - JIT** na CLR transforma o código CIL de um *assemble* em código nativo de máquina. Este é um código específico da CPU executado na mesma arquitetura de computador do compilador JIT. O JIT armazena o código nativo resultante na memória para que seja acessível para chamadas subsequentes no contexto desse processo.

O CLR fornece vários compiladores JIT, cada um funciona em uma arquitetura de CPU diferente dependendo do sistema operacional e torna possível executar o CIL (que é compilado a partir de diferentes linguagens .NET) em diferentes sistemas operacionais sem reescrever o Código fonte.

Vantagens da compilação Just-In-Time:

- Define o perfil da plataforma de destino enquanto ela é executada e recompilada em tempo real para fornecer desempenho aprimorado.
- A otimização do código com base na análise estatística pode ser realizada pelo compilador JIT enquanto o código está em execução.
- O compilador JIT requer menos uso de memória, pois apenas os métodos necessários em tempo de execução são compilados em código de máquina.
- *Page faults* são reduzidas, pois os métodos requeridos que possuem vínculos estão provavelmente na mesma página de memória.

Desvantagens da compilação Just-In-Time:

- O compilador JIT requer mais tempo de inicialização enquanto a aplicação é executado inicialmente.
- A memória cache é muito usada pelo compilador JIT para armazenar os métodos de código-fonte que são necessários em tempo de execução.

!!! note ""  
    Muitas das desvantagens do compilador JIT podem ser tratadas usando a compilação *Ahead-of-time - AOT*. Isso envolve a compilação do CIL em código de máquina para que a compilação em tempo de execução não seja necessária e o arquivo de código de máquina possa ser executado nativamente.

<figure>
    <a href="../_introducao/dotnet-architecture.svg">
        <img src="../_introducao/dotnet-architecture.svg"/>
    </a>
    <figcaption>Arquitetura .Net</figcaption>
</figure>

## C\#

### História

No final da década de 1990 a Microsoft tinha diversas tecnologias e linguagens de programação para resolver muitos problemas diferentes. Toda vez que um programador precisava migrar para uma nova linguagem, era necessário aprender tanto a nova linguagem quanto suas bibliotecas e conceitos. Para solucionar esses problemas, a Microsoft recorreu à linguagem Java.

O Java agradou os engenheiros da Microsoft pois com ela podíamos construir programas que eram independentes do ambiente de execução, além de possuir diversas bibliotecas com soluções prontas para diversos problemas. Para lançar produtos baseados no Java, a Microsoft assinou um acordo de licenciamento com a Sun para utilizar o Java em ambiente Windows.

Porém, a linguagem Java possuía um grave problema: ela não se comunicava bem com as bibliotecas de código nativo (código de máquina) que já existiam. Para resolver isso, a Microsoft decidiu criar a sua própria implementação do Java chamado J++, que possuía extensões proprietárias que resolviam o problema de comunicação com o código nativo existente. Para o desenvolvimento dessa nova implementação do Java, a Microsoft contratou um engenheiro chamado Anders Hejlsberg, um dos principais nomes por trás do Delphi.

O J++ era uma versão da linguagem Java que só podia ser executada no ambiente Microsoft. Seu código não podia ser executado em mais nenhum ambiente Java, o que violava o licenciamento feito com a Sun e, por isso, a Microsoft foi processada. Uma das mais conhecidas batalhas judiciais da época.

Sem o J++, a Microsoft foi obrigada a repensar sua estratégia sobre como lidar com as diferentes linguagens e tecnologias utilizadas internamente. A empresa começou a trabalhar em um nova plataforma que seria a base de todas as suas soluções, que posteriormente foi chamada de .Net. Esse novo ambiente de desenvolvimento da Microsoft foi desde o início projetado para trabalhar com diversas linguagens de programação, assim diversas linguagens diferentes compartilhariam o mesmo conjunto de bibliotecas. Com isso, para um programador migrar de uma linguagem para outra ele precisaria apenas aprender a linguagem sem se preocupar com as bibliotecas e APIs.

Além de uma plataforma a Microsoft também precisava de uma linguagem de programação. Um novo projeto de linguagem de programação foi iniciado, o projeto COOL (C-like Object Oriented Language). Anders Hejlsberg foi escolhido como engenheiro chefe desse novo projeto. COOL teve seu design baseado em diversas outras linguagens do mercado como Java, C, C++, Smalltalk, Delphi e VB. A ideia era estudar os problemas existentes e incorporar soluções.

Em 2002, o projeto COOL foi lançado como linguagem C# 1.0, junto com o ambiente .Net 1.0.

### Características

C# é uma linguagem de programação moderna, de uso geral, multiparadigma, de tipagem forte, desenvolvida pela Microsoft como parte da plataforma .NET. A sua sintaxe orientada a objetos foi baseada no C++ mas inclui muitas influências de outras linguagens de programação, como Object Pascal e, principalmente, Java. Dentre as linguagens suportadas no framework .NET, C# (C Sharp) é a mais usada em aplicações .NET.

Motivos que tornam C# uma linguagem amplamente usada:

- Linguagem de programação moderna e de uso geral.
- Orientado a objetos.
- Orientado para componentes.
- Fácil aprendizagem.
- Linguagem estruturada.
- Produz programas eficientes.
- Pode ser compilada para várias plataformas de computador.
- Parte do framework .Net.
- Fortes recursos de programação.

Recursos importantes do C#:

- Condições Booleanas.
- *Garbage Collection* automático.
- Biblioteca Padrão.
- Versionamento *Assembly*
- Propriedades e Eventos
- *Delegates* e Gerenciadores de Eventos
- *Generics* fáceis de usar
- Indexadores
- Compilação Condicional
- *Multithreading* Simples
- Expressões LINQ e Lambda

## Referências

- <https://pt.wikipedia.org/wiki/.NET_Framework>
- <https://en.wikipedia.org/wiki/.NET_Framework>
- <https://docs.microsoft.com/pt-br/dotnet/standard/assembly/>
- <https://www.tutorialspoint.com/csharp/csharp_overview.htm>
- <https://www.caelum.com.br/apostila-csharp-orientacao-objetos/o-que-e-c-e-net>
