# Instalação

## Windows (sem Android Studio)

### Flutter SDK

1. Baixar o SDK do Flutter [aqui](https://flutter.dev/docs/get-started/install).

1. Adicionar o caminho do diretório `bin` na variável de ambiente `PATH`:

    `Painel de Controle -> Sistema -> Editar variáveis de ambiente do sistema`.

1. Para verificar o ambiente e ver o que está faltando execute no terminal:

    ```sh
    flutter doctor
    ```

### Java SDK

1. Baixar o SDK do Java [aqui](https://adoptopenjdk.net/installation.html)

1. Adicionar o caminho que contém as pastas: `bin`, `jre`, etc. na variável de ambiente `JAVA_HOME`.

1. Adicionar o caminho `jre/bin` na variável de ambiente `PATH`.

### Android SDK

1. Criar uma pasta chamada `sdk` onde será instalado o SDK.
    - Exemplo: `<path>/Android/sdk`.
    - Não usar espaços no caminho

1. Baixar o Android SDK, escolhendo a opção "Command line tools only" [aqui](https://developer.android.com/studio/index.html#command-tools).

1. Dentro de `command-tools\bin` executar o comando abaixo para ver a versão mais recente dos pacotes:

   ```sh
   ./sdkmanager.bat --list
   ```

1. Dentro de `command-tools\bin` executar o comando abaixo onde:
    - `--sdk_root` deve informar o local da pasta `sdk` criada.
    - `29x` deve ser a versão do pacote.

    ```sh
    ./sdkmanager.bat "system-images;android-29;default;x86_64" --sdk_root="<caminho>\sdk"
    ./sdkmanager.bat "platform-tools" --sdk_root="<caminho>\sdk"
    ./sdkmanager.bat "platforms;android-29" --sdk_root="<caminho>\sdk"
    ./sdkmanager.bat "build-tools;29.0.3" --sdk_root="<caminho>\sdk"
    ./sdkmanager.bat emulator --sdk_root="<caminho>\sdk"
    ```

    Outra forma é adicionar o `command-tools\bin` na variável de ambiente `PATH` para executar de qualquer diretório.

1. Executar o comando abaixo para informar ao Flutter onde esta o diretório **pai** do `sdk`.
    - Se o diretório for `<path>/Android/sdk`, então será `<path>/Android/`.

    ```sh
    flutter config --android-sdk <path>/Android/
    ```

1. Executar o comando abaixo para aceitar as licenças:

    ```sh
    flutter doctor --android-licenses
    ```

1. Executar o comando abaixo para verificar se tudo ocorreu corretamente:

    ```sh
    flutter doctor
    ```

### Emulador

**Criar um emulador:**

```sh
# Parâmetros:
# -n: nome do dispositivo. Ex. nexus

avdmanager -s create avd -n <nome-dispositivo> -k "system-images;android-29;default;x86_64"
```

**Executar um emulador:**

```sh
flutter emulators --launch <nome-dispositivo>
```

**Excluir um emulador:**

```sh
avdmanager delete avd -n <nome-dispositivo>
```

**Listar emuladores:**

```sh
# Listar dispositivos físicos e virtuais
avdmanager list

# Listar dispositivos físicos
avdmanager list device

# Listar dispositivos virtuais
avdmanager list avd
```

### Visual Studio Code

#### Execução do projeto em um emulador de device

1. Abra a paleta de comandos e digite : Flutter e a seguir a opção `Select a device`;

1. Ao executar o modo *debug* o aplicativo será executado no device emulado;

## Referências

- <https://medium.com/@quicky316/install-flutter-sdk-on-windows-without-android-studio-102fdf567ce4>
- <https://stackoverflow.com/questions/65262340/cmdline-tools-could-not-determine-sdk-root>