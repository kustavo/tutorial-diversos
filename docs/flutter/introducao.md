# Introdução

Flutter é um kit de desenvolvimento de interface de usuário (UI toolkit), de código aberto, criado pelo Google, que possibilita a criação de aplicativos compilados nativamente. Atualmente pode compilar para Android, iOS, Windows, Mac, Linux, Google Fuchsia e Web.

## Linguagem de programação Dart

Os aplicativos Flutter são escritos na linguagem de programação Dart e fazem uso de muitos dos recursos mais avançados da linguagem.

No Windows, macOS e Linux, por meio do projeto *Flutter Desktop Embedding*, o Flutter é executado na máquina virtual Dart, que possui um mecanismo de compilação que ocorre em tempo de execução. Ao escrever e depurar um aplicativo, o Flutter usa a compilação JIT, permitindo o "hot reload", com a qual as modificações nos arquivos de origem podem ser injetadas em um aplicativo em execução. O Flutter estende isso com suporte para hot reload de *widgets stateful*, onde na maioria dos casos as alterações no código-fonte podem ser refletidas imediatamente no aplicativo em execução, sem a necessidade de uma reinicialização ou perda do estado.

## Flutter Engine

A engine do Flutter, escrito principalmente em C++, fornece suporte de renderização de baixo nível usando a biblioteca de gráficos *Skia* do Google. Além disso, ele faz interface com SDKs específicos da plataforma, como os fornecidos pelo Android e iOS. O *Flutter Engine* é um runtime portátil para hospedar aplicativos em Flutter. Ele implementa as bibliotecas principais do Flutter, incluindo animação e gráficos, I/O de arquivos e rede, suporte à acessibilidade, arquitetura de plugins e um conjunto de ferramentas de tempo de execução e compilação do Dart. A maioria dos desenvolvedores irá interagir com o Flutter por meio do Flutter Framework, que fornece uma estrutura moderna e reativa e um rico conjunto de platform, layout e foundation widgets.

## A biblioteca Foundation

A *biblioteca Foundation*, escrita em Dart, fornece classes e funções básicas que são usadas para construir aplicativos usando o Flutter, como APIs para se comunicar com a engine.

## Design-specific Widgets (Identidade Visual)

O framework Flutter contém dois conjuntos de widgets que estão em conformidade com linguagens de design específicas. Os widgets do *Material Design* implementam a identidade visual do Google e os widgets do *Cupertino* implementam as diretrizes de interface humana para iOS da Apple.

## Aplicação

### Função main

O arquivo principal do Flutter está localizado em `./lib/main.dart`. Este arquivo possui uma estrutura inicial semelhante ao código abaixo:

```dart
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Minha Primeira App'),
        ),
        body: Text('Hello world :)'),
      ),
    );
  }
}
```

A biblioteca `package:flutter/material.dart` possui os estilos do material design. Ela permite que tenhamos acesso a componentes para conseguirmos ter uma estrutura base para iniciar o desenvolvimento da aplicação.

A função `main()` é obrigatória no arquivo `material.dart`. Ela será executada no *bootstrap* da nossa aplicação.

Dentro da função `main()` temos a chamada da função `runApp()` que vem do pacote do Flutter e é a responsável por chamar o ponto de entrada principal que vai servir de base para a estrutura da aplicação. Passamos para `runApp()` um *widget* que veio do import `package:flutter/material.dart` chamado `MaterialApp()` que possui um atributo `home` no qual passamos outro *widget* chamado `Scaffold` que serve como uma estrutura para inserirmos componentes em lugares comuns do material design como a barra do topo, um botão no *Floating Action Button* e uma entrada para colocarmos o corpo da nossa tela.

### Estrutura de pastas

- **.idea**: É uma pasta criada pelas IDEs da JetBrain para facilitar algumas funcionalidades nas ferramentas.
- **android** e **ios**: É a pasta que possui uma casca para gerar o APK/IPA dos aplicativos para que os mesmos sejam publicados nas lojas. Também permite que seja possível acessar comportamentos específicos das plataformas por meio dos Platform Channels.
- **lib**: A pasta onde escrevemos o código da aplicação.
- **build**: É onde fica o build do nosso projeto em Dart. Ela é gerada sempre que rodarmos o comando `flutter run`.
- **test**: É a pasta onde podemos escrever testes para nossas aplicações.
- **.gitignore**: Já vem configurado com alguns arquivos para serem ignorados na hora de fazer os commits com o git.
- **.metadata**: É um arquivo usado pelo flutter para gerenciar alguns recursos internos e não devemos alterá-lo manualmente.
- **pubspec.yaml**: É o arquivo onde podemos gerenciar as dependências de um projeto Dart (similar ao package.json do mundo JavaScript).
- **.packages**: Esse arquivo faz um mapeamento de onde estão instaladas as bibliotecas do seu sistema para o sistema de imports do projeto. Ele é gerado automaticamente sempre que uma biblioteca nova é instalada.
- **\<nome-projeto\>.iml**: É um arquivo criado para facilitar algumas integrações com o IntelliJ.
- **pubspeck.lock**: É um arquivo usado para gerenciar quais versões as libs das libs que você está usando estarão utilizando.
- **README.MD**: Arquivo texto onde o usuário pode escrever informações sobre o projeto.

## Referências

- <https://pt.wikipedia.org/wiki/Flutter>
- <https://www.alura.com.br/artigos/como-criar-um-projeto-com-flutter-hello-world>
