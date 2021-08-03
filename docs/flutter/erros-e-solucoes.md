# Erros e Soluções

## Cannot find AVD system path

??? info "Log"

    ```bash
    PANIC: Cannot find AVD system path. Please define ANDROID_SDK_ROOT
    PANIC: Broken AVD system path. Check your ANDROID_SDK_ROOT value
    ```

??? tip "Solução (Windows)"

    Em `C:\Users\<usuario>\.android\avd\<dispositivo>.avd` na configuração `image.sysdir.1` colocar o caminho completo.

    ```bash
    image.sysdir.1=c:\<path>\sdk\system-images\android-29\default\x86_64\
    ```
