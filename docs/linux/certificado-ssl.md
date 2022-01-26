# Certificado SSL

## Introdução

SSL significa Secure Sockets Layer, uma tecnologia global de segurança padrão que permite a comunicação criptografada entre um navegador da Internet e um servidor da web.

O Certificado SSL é utilizado para proteger informações importantes dos usuários que navegam no seu website, impedindo que sejam interceptadas, capturadas ou visualizadas durante transferência dos dados até o servidor que hospeda a aplicação.

Essa proteção é criada a partir de uma chave de criptografia forte que embaralha as informações enviadas pelo usuário, de modo que seja impossível descobrir o conteúdo de dentro da chave e o único lugar que pode desembaralhar este conteúdo é o servidor onde está instalado o Certificado SSL.

Assim qualquer tentativa de capturar um pacote de dados se torna irrelevante, mesmo que alguém seja capaz de interceptar os dados, será impossível ler o conteúdo.

## Criação

Criar o par chave e certificado auto assinado.

```bash
sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/<NOME-ARQUIVO>.key -out /etc/ssl/certs/<NOME-ARQUIVO>.crt
```

## Autoridade de certificação

Uma autoridade de certificação (CA, do inglês Certificate Authority) é uma entidade responsável por emitir certificados digitais para verificar identidades na internet. Embora as CAs públicas sejam uma escolha popular para a verificação da identidade de sites e outros serviços oferecidos ao público em geral, as CAs privadas são normalmente usadas para grupos fechados e serviços privados.

A criação de uma autoridade de certificação privada permitirá que você configure, teste e execute programas que exigem conexões criptografadas entre um cliente e um servidor. Com uma CA privada, você pode emitir certificados para usuários, servidores, ou programas e serviços individuais em sua infraestrutura.

Alguns exemplos de programas no Linux que usam sua própria CA privada são o OpenVPN e o Puppet. Você também pode configurar seu servidor Web para que use certificados emitidos por uma CA privada, de forma a fazer com que os ambientes de desenvolvimento e de preparo correspondam aos servidores de produção que usam o TLS para criptografar conexões.

## Server Blocks

<https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-ubuntu-20-04-pt>

## Referências

- <https://blog.saninternet.com/o-que-e-certificado-ssl-o-guia-definitivo#o-que-e>
- <https://www.digitalocean.com/community/tutorials/how-to-set-up-and-configure-a-certificate-authority-ca-on-ubuntu-20-04-pt>
