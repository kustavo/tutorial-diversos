# Nginx

[TOC]

[[_TOC_]]

## Introdução

Nginx é um servidor leve de HTTP, proxy reverso, proxy de e-mail IMAP/POP3, feito por Igor Sysoev em 2005, sob licença BSD-like 2-clause.

O Nginx consome menos memória que o Apache, pois lida com requisições Web do tipo "event-based web server"; e o Apache é baseado no "process-based server", podendo trabalhar juntos. É possível diminuir o consumo de memória do Apache, passando as requisições Web primeiro no Nginx, assim, o Apache não precisa servir arquivos estáticos, e pode depender do bom controle de cache feito pelo Nginx.

## Status

```sh
sudo systemctl status nginx
# ou
sudo service nginx status
```

## Iniciar

```sh
sudo systemctl start nginx
# ou
sudo service nginx start
```

## Parar

```sh
sudo systemctl stop nginx
# ou
sudo service nginx stop
```

## Reiniciar

```sh
sudo systemctl restart nginx
# ou
sudo service nginx restart
```

## Recarregar configurações

Reinicia o nginx de forma mais rápida para apenas recarregar as configurações.

```sh
sudo systemctl reload nginx
# ou
sudo service nginx reload
```

