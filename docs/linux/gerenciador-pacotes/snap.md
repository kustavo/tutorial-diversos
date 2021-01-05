# Snap

Snappy é um software de implantação e um sistema de gerenciamento de pacotes originalmente projetado e construído pela Canonical para o sistema operacional Ubuntu phone. Os pacotes, chamados de "snaps" e a ferramenta para usá-los, "snapd", funcionam por toda uma gama de distribuições Linux e, portanto, permitem implantação de software upstream de forma distro-agnostic. O sistema é projetado para funcionar em smartphones, nuvem, internet das coisas e ambiente de desktop.

Pacotes de software "snap" são auto-contidos e o funcionam por toda uma gama de distribuições Linux. Essa é uma abordagem diferente do pacote Linux tradicional, como o APT ou o RPM, que exigem pacotes especificamente adaptados para cada distribuição de Linux. Isso adiciona atraso entre o desenvolvimento de aplicações e de sua implementação para os usuários finais.

## Comandos

Ver versão do snap

```bash
snap version
```

Busca de aplicativos

```bash
snap find "nome"
```

Informação do aplicativo

```bash
snap info <aplicativo>
```

Instalação de aplicativos

```bash
sudo snap install <aplicativo>

# Escolhendo o canal do repositório (stable, candidate, beta, edge)
sudo snap install --channel=edge <aplicativo>

# Mudando o canal do repositório
sudo snap switch --channel=stable <aplicativo>
```

Remover aplicativo

```bash
sudo snap remove <aplicativo>
```

Local onde os aplicativos são instalados (`/snap/bin`)

```bash
which <aplicativo>

$ /snap/bin/<aplicativo>
```

Executar aplicativo

```bash
/snap/bin/vlc
```

Listar aplicativos instalados

```bash
snap list

# Listar com todas as versões instaladas
snap list --all
```

Atualizar os aplicativos

```bash
sudo snap refresh <APLICATIVO>

# Mudar o canal e atualizar
sudo snap refresh --channel=beta <APLICATIVO>
```

Reverter para a versão anterior

```bash
sudo snap revert <APLICATIVO>
```

Desabilitar e habilitar um aplicativo

```bash
sudo snap disable <APLICATIVO>
sudo snap enable <APLICATIVO>
```

## Referências

- <https://pt.wikipedia.org/wiki/Snappy>
- <https://guialinux.uniriotec.br/snap/>
