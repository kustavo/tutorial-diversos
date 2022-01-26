# Node.js

Node.js trata-se de um software open-source, cross-platform, e de um runtime de JavaScript que execute código de JavaScript a nível backend e frontend (dependendo apenas das bibliotecas e dos frameworks usados para esse mesmo fim). O runtime de JavaScript é constituído pelos seguintes commandos - node package manage (npm), e npx (node package extractor). Ao passo que o primeiro comando tem como propósito correr código armazenado num package de nodejs, e instalar software globalmente ou localmente, já o npx (node package extractor), tem como propósito instalar a nível local código instalado globalmente. Um exemplo disso é o npx create-react-app que tem como propósito instalar a nível local um template vazio de um site de react, pronto a ser usado, através de uma fórmula instalada com npm. 

## Instalação

```bash
# debian/ubuntu

sudo apt install nodejs
```

Atualizar para versões mais recentes.

```bash
sudo npm cache clean -f
sudo npm install -g n

# versão estável
sudo n stable 

# ou última versão
sudo n latest
```

## Referências

- <https://pt.wikipedia.org/wiki/Node.js>