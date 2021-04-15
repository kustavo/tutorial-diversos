# Instalação e configuração

## Instalação do .NET SDK

=== "debian/ubuntu"
    Página de download: <https://dotnet.microsoft.com/download>

    Adicionar repositório e instalação.

    ```bash
    wget https://packages.microsoft.com/config/ubuntu/20.10/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
    sudo dpkg -i packages-microsoft-prod.deb

    sudo apt-get update; \
      sudo apt-get install -y apt-transport-https && \
      sudo apt-get update && \
      sudo apt-get install -y dotnet-sdk-5.0
    ```

## Locais da instalação

```bash
# onde estão instalados os sdks
dotnet --list-sdks

# onde estão instalados os runtimes
dotnet --list-runtimes
```

## Configurar Visual Studio Code

=== "launch.json"

    O arquivo `launch.json` é usando pelo `Visual Studio Code`.

    ```json
    --8<-- "./docs/dotnet/_instalacao-e-configuracao/launch.json"
    ```

    Outra forma de abrir o navegador automaticamente além do `launchBrowse` é usar a configuração:

    ```json
    "serverReadyAction": {
        // Ação a ser executada
        "action": "openExternally",
        // Padrão que deve ser encontrado na saída (visto no console de debug)
        "pattern": "\\bNow listening on:\\s+(https?://\\S+)",
        // URI que será aberta
        "uriFormat": "%s/swagger"
    }
    ```

=== "task.json"

    O arquivo `tasks.json` é usando pelo `Visual Studio Code`. Define as tarefas que serão chamas em `launch.json`.

    ```json
    --8<-- "./docs/dotnet/_instalacao-e-configuracao/tasks.json"
    ```

=== "launchSettings.json"

    O arquivo `launchSettings.json` é usado apenas pelo `Visual Studio`.

    ```json
    --8<-- "./docs/dotnet/_instalacao-e-configuracao/launchSettings.json"
    ```

=== "appsettings.json"

    O arquivo `appsettings.json` define as configurações da aplicação e pode ser definida específica para um ambiente. Basta criar um arquivo `appsettings.<environment>.json`.

    ```json
    --8<-- "./docs/dotnet/_instalacao-e-configuracao/appsettings.json"
    ```

=== "web.config"

    O arquivo `web.config` é usado apenas pelo IIS (Windows).

    ```xml
    --8<-- "./docs/dotnet/_instalacao-e-configuracao/web.config"
    ```

## Desinstalação

=== "Windows e Mac"
    ```bash
    dotnet-core-uninstall
    ```
