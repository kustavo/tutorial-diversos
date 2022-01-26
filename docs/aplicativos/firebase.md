# Firebase

## Introdução

Firebase é uma plataforma de desenvolvimento mobile (e web) adquirida pela Google em 2014. Com foco em ser um back-end completo e de fácil usabilidade, essa ferramenta disponibiliza diversos serviços diferentes que auxiliam no desenvolvimento e gerenciamento de aplicativos.

### Serviços

Segue abaixo uma lista e uma breve explicação dos principais serviços disponíveis:

`Realtime Database`
:   Banco de dados que sincroniza os dados com os dispositivos em tempo real. Regras de segurança podem ser configuradas para definir quem tem acesso a quais dados.
`Authentication`
:   Possibilita autenticação através de contas do Google, Facebook, Twitter, Github ou um sistema de contas próprio.
`Cloud Messaging`: Permite enviar mensagens para os usuários através do aplicativo. É possível definir para quais grupos de usuários a mensagem será enviada.
`Storage`
:   Armazena dados do usuário através de uma conexão segura e permite o compartilhamento dos mesmos.
`Hosting`
:   Serviço para hospedagem de sites com certificado SSL
`Test Lab`
:   Serviço para testar o aplicativo em diferentes tipos de dispositivos. Possui uma ferramenta de testes automatizada (Robo Test), porém permite que o desenvolvedor crie seus próprios scripts de teste.
`Crash Reporting`
:   Coleta informações de falhas que os usuários estão experienciando no aplicativo.
`Notifications`
:   Envia notificações personalizadas para o usuário.
`Remote Config`
:   Permite que versões diferentes do aplicativo sejam publicadas para diferentes usuários. Pode ser usado para testar mudanças com um grupo pequeno de usuários antes de aplica-las definitivamente.
`App Indexing`
:   Permite que o aplicativo seja encontrado e pesquisas no Google Search, caso o assunto que o usuário procura seja relacionado com o app.
`Dynamic Links`
:   Usado para criar links que executam determinadas ações no aplicativo. Também é possível definir diferentes ações para diferentes dispositivos e para casos em que o usuário ainda não tenha o aplicativo instalado.
`Invites`
:   Utiliza o Dynamic Links para criar convites personalizados para o aplicativo, que podem ser enviados pelo usuário para outras pessoas.
`AdWords`
:   Ferramenta para publicar anúncios do aplicativo no Google, YouTube ou Play Store.
`AdMob`
:   Facilita a monetização do aplicativo, colocando anúncios que encaixem no design do mesmo. O serviço prioriza automaticamente as fontes que retornam um maior lucro.
`Analytics`
:   Ferramenta de análise, que produz insights sobre o usuário e o aplicativo.
Outros serviços estão sendo adicionados, porém ainda estão em fases de teste no presente momento. Dentre estes, estão serviços para monitoração do desempenho do aplicativo e para gerar previsões de comportamento dos usuários utilizando machine learning.

## Comandos

### Instalação

```bash
# debian/ubuntu
npm -g install firebase-tools
```

### Versão instalada

```bash
firebase --version
```

### Inciar novo projeto

```bash
firebase init
```

### Autorização do Firebase CLI

```bash
<aplicacao>$ firebase login
```

### Associar a aplicação com o projeto Firebase

```bash
<aplicacao>$ firebase use --add
```

### Iniciar servidor local

```bash
<aplicacao>$ firebase serve --only hosting
```

### Deploy

```bash
firebase deploy
```

## Referências

- <https://micreiros.com/firebase-o-que-e-e-como-funciona/>
