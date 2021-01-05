# Imagemagick

Imagemagick é uma suite de aplicativos para edição não interativa de imagens, ou seja, com ele é possível editar, converter, combinar etc. imagens de vários formatos.

## Instalação

Instalação para distribuições Debian/Ubuntu

```bash
sudo apt install imagemagick
```

## Converter imagem em pdf

Converter imagens em pdf:

```bash
convert <imagem1> <imagem2> <imagem3> <arquivo>.pdf
```

## Problemas e soluções

### Política de segurança

Erro quanto não possui a política de segurança de ler e escrever pdf.

??? info "Log"

    ```bash
    convert: attempt to perform an operation not allowed by the security policy
    ```

??? tip "Solução"

    No arquivo `/etc/ImageMagick-<versao>/policy.xml`, modificar a linha abaixo para permitir leitura e ecrita.

    ```bash
    <policy domain="coder" rights="read|write" pattern="PDF" />
    ```
