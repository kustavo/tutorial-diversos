# Erros e soluções

## HTTPS certificate

Erro quando não foi encontrado um certificado SSL/TLS válido.

??? info "Log"

      ```bash
      info: Microsoft.AspNetCore.DataProtection.KeyManagement.XmlKeyManager[0]
            User profile is available. Using '/home/<user>/.aspnet/DataProtection-Keys' as key repository; keys will not be encrypted at rest.
      crit: Microsoft.AspNetCore.Server.Kestrel[0]
            Unable to start Kestrel.
      System.InvalidOperationException: Unable to configure HTTPS endpoint. No server certificate was specified, and the default developer certificate could not be found.
      To generate a developer certificate run 'dotnet dev-certs https'. To trust the certificate (Windows and macOS only) run 'dotnet dev-certs https --trust'.
      ```

??? tip "Solução (Linux)"

      Executar os comandos abaixo:

      ```bash
      dotnet tool uninstall --global dotnet-dev-certs
      dotnet tool install --global dotnet-dev-certs
      export PATH="$PATH:~/.dotnet/tools"
      dotnet dev-certs https --trust
      ```

      Para permanecer após o reboot, adicione estes comandos em `~/.bash_profile`.

## OmniSharp

Erro quando OmniSharp não encontra os sdks do dotnet.

??? info "Log"

      ```bash
      warn: OmniSharp.MSBuild.ProjectManager
            Failed to load project file <path-to-project>
      Microsoft.Build.Exceptions.InvalidProjectFileException: The SDK 'Microsoft.NET.Sdk' specified could not be found. <path-to-project>
      ```

??? tip "Solução (Linux)"

      Criar link simbólico do dotnet em `/usr/local/bin/` conforme o comando abaixo.

      ```bash
      sudo ln -s /usr/bin/dotnet /usr/local/bin/dotnet
      ```
