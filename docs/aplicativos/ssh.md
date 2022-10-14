# SSH

SSH é a sigla para **Secure Shell** e ele nada mais é que um  protocolo de rede que oferece aos usuários, uma maneira segura de acessar um computador em uma rede aberta, como a internet.

O SSH usa criptografia de chave pública para autenticar o computador remoto e permiti-lo autenticar o utilizador, se necessário.Há várias maneiras de usar o SSH, uma é usar pares de chave pública-privada geradas automaticamente para simplesmente encriptar uma conexão de rede e então usar autenticação por senha para logar.

Outra maneira é usar um par de chaves pública-privada geradas manualmente para realizar a autenticação, permitindo que usuários ou programas loguem sem ter que especificar uma senha. Neste cenário, qualquer um pode produzir um par correspondente de chaves diferentes (pública e privada). A chave pública é colocada em todos os computadores que devem permitir o acesso ao proprietário da chave privada correspondente (o proprietário mantém o segredo da chave privada). Uma vez que a autenticação é baseada na chave privada, a chave em si nunca é transferida por meio da rede durante a autenticação. O SSH apenas verifica se a mesma pessoa que oferece a chave pública também possui a chave privada correspondente. Em todas as versões do SSH é importante verificar chaves públicas desconhecidas, isto é, associar as chaves públicas com as identidades, antes de aceitá-las como válidas. Aceitar a chave pública de um atacante sem validação autorizará o atacante como um usuário válido.

## Referências

- <https://pt.wikipedia.org/wiki/Secure_Shell>
