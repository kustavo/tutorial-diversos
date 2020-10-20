# Wildfly

[TOC]

## Introdução

Wildfly, também conhecido como JBoss é um servidor de aplicação de código fonte aberto baseado na plataforma JEE e implementado completamente na linguagem de programação Java. Em Abril de 2006, foi anunciada sua aquisição pela Red Hat. Contém implementações robusta de WebSocket, Processamento Batch, JSON, Concorrência, JMS 2, JAX-RS 2 (REST), CDI 1.1 e todas as demais tecnologias que fazem parte do Java EE 7.

A partir da versão 8 o JBoss passou a se chamar Wildfly, além da troca de nomes teve várias melhorias e mudanças como a troca do container que era o JBossWeb para o Undertow, que foi projetado para um maior throughput e escalabilidade, incluindo ambientes com milhões de conexões simultâneas. O número de portas foi reduzido através da multiplexação de protocolos em HTTP.

## Execução

1. Baixar a versão "Java EE7 Full & Web Distribution" no site oficial.

1. Executar o comando:

```sh
CAMINHO\bin\standalone.sh --server-config=standalone-full.xml
```
