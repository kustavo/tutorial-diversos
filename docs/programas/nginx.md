# Nginx

## Introdução

Nginx é um servidor leve de HTTP, proxy reverso, proxy de e-mail IMAP/POP3, feito por Igor Sysoev em 2005, sob licença BSD-like 2-clause.

O Nginx consome menos memória que o Apache, pois lida com requisições Web do tipo "event-based web server"; e o Apache é baseado no "process-based server", podendo trabalhar juntos. É possível diminuir o consumo de memória do Apache, passando as requisições Web primeiro no Nginx, assim, o Apache não precisa servir arquivos estáticos, e pode depender do bom controle de cache feito pelo Nginx.

## Instalação (debian/ubuntu)

```bash
sudo apt update
sudo apt install nginx
```

## Configuração (debian/ubuntu

### Configurando o firewall

Ativar e habilitar para iniciar automaticamente após o boot.

```bash
sudo ufw enable
```

Liste perfil de configurações de aplicativos com as quais o ufw sabe trabalhar.

```bash
sudo ufw app list
```

Executar o perfil de configuração do Nginx para habilitar HTTP e HTTPS.

```bash
sudo ufw allow 'Nginx Full'
```

Ver status e listas das regras configuradas.

```bash
sudo ufw status
```

### Configurando um certificado SSL

Criar o par chave e certificado auto assinado.

```bash
sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/nginx-selfsigned.key -out 'ls
```

Configurar para que use a troca de chaves de Diffie-Hellman.

```bash
sudo openssl dhparam -out /etc/nginx/dhparam.pem 4096
```

Criar um novo snippet de configuração Nginx `/etc/nginx/snippets/<nome>.conf` contendo o local da chave e certificado.

Exemplo: `sudo nano /etc/nginx/snippets/self-signed.conf`.

```conf
ssl_certificate /etc/ssl/certs/nginx-selfsigned.crt;
ssl_certificate_key /etc/ssl/private/nginx-selfsigned.key;
```

Criaremos outro snippet que definirá algumas configurações de SSL. Isso configurará o Nginx com um forte pacote de criptografia SSL e habilitará alguns recursos avançados que ajudarão a manter o servidor seguro.

Exemplo: `sudo nano /etc/nginx/snippets/ssl-params.conf`.

```conf
ssl_protocols TLSv1.2;
ssl_prefer_server_ciphers on;
ssl_dhparam /etc/nginx/dhparam.pem;
ssl_ciphers ECDHE-RSA-AES256-GCM-SHA512:DHE-RSA-AES256-GCM-SHA512:ECDHE-RSA-AES256-GCM-SHA384:DHE-RSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-SHA384;
ssl_ecdh_curve secp384r1; # Requires nginx >= 1.1.0
ssl_session_timeout  10m;
ssl_session_cache shared:SSL:10m;
ssl_session_tickets off; # Requires nginx >= 1.5.9
ssl_stapling on; # Requires nginx >= 1.3.7
ssl_stapling_verify on; # Requires nginx => 1.3.7
resolver 8.8.8.8 8.8.4.4 valid=300s;
resolver_timeout 5s;
# Disable strict transport security for now. You can uncomment the following
# line if you understand the implications.
# add_header Strict-Transport-Security "max-age=63072000; includeSubDomains; preload";
add_header X-Frame-Options DENY;
add_header X-Content-Type-Options nosniff;
add_header X-XSS-Protection "1; mode=block";
```

Criar arquivo `/etc/nginx/sites-available/<nome-dominio>`. O nome do domínio pode ser `localhost`, por exemplo.

```conf
server {
    listen 443 ssl;
    listen [::]:443 ssl;
    include snippets/self-signed.conf;
    include snippets/ssl-params.conf;

    server_name <nome-dominio>;

    root /var/www/html;

    index index.html index.htm index.nginx-debian.html; 
}

server {
    listen 80;
    listen [::]:80;

    server_name <nome-dominio>;

    return 301 https://$server_name$request_uri;
}
```

Criar talho em `/etc/nginx/sites-enabled`.

```bash
sudo ln -s /etc/nginx/sites-available/<nome-dominio> /etc/nginx/sites-enabled/<nome-dominio>
```

Verificar a sintaxe dos arquivos .conf

```bash
sudo nginx -t
```

Reiniciar serviço

```bash
sudo systemctl restart nginx
```

### Configurando Blocos do Servidor

Ao usar o servidor Web Nginx, os server blocks (similares aos hosts virtuais no Apache) podem ser usados para encapsular detalhes de configuração e hospedar mais de um domínio de um único servidor.

[Clique aqui](https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-ubuntu-20-04-pt#passo-5-%E2%80%94-configurando-blocos-do-servidor-(recomendado))

## Comandos (debian/ubuntu)

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

## Diretórios importantes (debian/ubuntu)

### Conteúdo

`/var/www/html`
    : O conteúdo Web em si, que por padrão apenas consiste na página Nginx padrão que você viu antes, é servido fora do diretório /var/www/html. Isso pode ser alterado mudando os arquivos de configuração do Nginx.

### Configuração do Servidor

`/etc/nginx`
:   o diretório de configuração do Nginx. Todos os arquivos de configuração do Nginx residem aqui.

`/etc/nginx/nginx.conf`
:   o arquivo de configuração principal do Nginx. Isso pode ser modificado para fazer alterações na configuração global do Nginx.

`/etc/nginx/sites-available/`
:   o diretório onde os blocos de servidor de cada site podem ser armazenados. O Nginx não usará os arquivos de configuração encontrados neste diretório a menos que estejam ligados ao diretório sites-enabled. Normalmente, todas as configurações de blocos de servidor são feitas neste diretório e então habilitadas pela ligação a outro diretório.

`/etc/nginx/sites-enabled/`
:   o diretório onde os blocos de servidor de cada site habilitados são armazenados. Normalmente, eles são criados pela ligação aos arquivos de configuração encontrados no diretório sites-available.

`/etc/nginx/snippets`
:   este diretório contém fragmentos de configuração que podem ser incluídos em outro lugar na configuração do Nginx. Os segmentos de configuração potencialmente repetíveis são bons candidatos à refatoração em snippets.

### Logs

`/var/log/nginx/access.log`
:   cada pedido ao seu servidor Web é registrado neste arquivo de registro a menos que o Nginx esteja configurado para fazer de outra maneira.

`/var/log/nginx/error.log`
:   qualquer erro do Nginx será gravado neste registro.

## Referências

- <https://pt.wikipedia.org/wiki/Nginx>
- <https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-ubuntu-20-04-pt>
- <https://www.digitalocean.com/community/tutorials/how-to-create-a-self-signed-ssl-certificate-for-nginx-in-ubuntu-18-04>
