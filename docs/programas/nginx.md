# Nginx

## Introdução

Nginx é um servidor leve de HTTP, proxy reverso, proxy de e-mail IMAP/POP3, feito por Igor Sysoev em 2005, sob licença BSD-like 2-clause.

O Nginx consome menos memória que o Apache, pois lida com requisições Web do tipo "event-based web server"; e o Apache é baseado no "process-based server", podendo trabalhar juntos. É possível diminuir o consumo de memória do Apache, passando as requisições Web primeiro no Nginx, assim, o Apache não precisa servir arquivos estáticos, e pode depender do bom controle de cache feito pelo Nginx.

## Comandos

### Ver status

```bash
sudo systemctl status nginx
```

### Serviço

#### Iniciar serviço

```bash
sudo systemctl start nginx
```

#### Habilitar serviço para iniciar após boot

```bash
sudo systemctl enable nginx
```

#### Parar serviço

```bash
sudo systemctl stop nginx
```

#### Reiniciar serviço

```bash
sudo systemctl restart nginx
```

#### Recarregar configurações

```bash
sudo systemctl reload nginx
```

## Referências

- <https://pt.wikipedia.org/wiki/Nginx>
