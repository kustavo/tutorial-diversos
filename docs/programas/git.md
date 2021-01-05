# Git

## Introdução

Git é um sistema de controle de versões distribuído, usado principalmente no desenvolvimento de software, mas pode ser usado para registrar o histórico de edições de qualquer tipo de arquivo (Exemplo: alguns livros digitais são disponibilizados no GitHub e escrito aos poucos publicamente). O Git foi inicialmente projetado e desenvolvido por Linus Torvalds para o desenvolvimento do kernel Linux, mas foi adotado por muitos outros projetos.

Cada diretório de trabalho do Git é um repositório com um histórico completo e habilidade total de acompanhamento das revisões, não dependente de acesso a uma rede ou a um servidor central.

O Git é um software livre, distribuído sob os termos da versão 2 da GNU General Public License. Sua manutenção é atualmente supervisionada por Junio Hamano.

## Comandos

### Configurações

Configurar o nome e email do usuário para que estas informações sejam armazenadas no histórico de ações do gerenciador de repositório.

```bash
git config --global user.name "<nome usuario>"
git config --global user.email "<email-usuario>"
```

Configurar o editor padrão:

```bash
git config --global core.editor "<editor>"

# exemplo:
# git config --global core.editor vim
```

Configurar a ferramenta de merge padrão:

```bash
git config --global merge.tool "<ferramenta>"

# exemplo:
# git config --global merge.tool vimdiff
```

Configurar os arquivos a serem ignorados:

```bash
git config --global core.excludesfile ~/.gitignore
```

Ver as configurações definidas

```bash
git config --global --list
```

As configurações são armazenadas no arquivo `~/.gitconfig`.

### Init

Transforma o diretório atual em um repositório do Git. Pode ser usado para converter um projeto existente e não versionado em um repositório do Git ou inicializar um novo repositório vazio.

O comando `git init` cria um subdiretório `.git` no diretório de trabalho atual, que contém todos os metadados Git necessários para o novo repositório. Esses metadados incluem subdiretórios para objetos, referências e arquivos de template. Também é criado um arquivo HEAD que aponta para o commit em uso no momento.

```bash
git init
```

### Clone

Clonagem (download) de um repositório que já existe remotamente, incluindo todos os arquivos, branches e commits.

A clonagem de um repositório normalmente é feita apenas uma vez, no início de sua interação com um projeto. Uma vez que um repositório já exista em um local remoto, você clona esse repositório para que possa interagir com ele localmente.

```bash
git clone https://<caminho>.git
# ou
git clone https://<caminho>.git <destino>
```

### Remote

Ver o endereço do repositório remoto.

```bash
git remote -v
```

Caso tenhamos criado o repositório localmente antes de criar no servidor, podemos adicionar o caminho com o comando set-url.

```bash
git remote set-url <repo-remoto> <endereco>

# exemplo:
# git remote set-url origin git@github.com:usuario/usuario.github.io.git
```

Excluir todas branchs locais que não tem mais referência remota.

```bash
git remote prune origin
```

### Status

Verifica o status dos arquivos em relação ao último checkout. O comando exibe a lista de arquivos alterados juntamente com os arquivos que ainda não foram adicionados ou confirmados.

```bash
git status
```

Status de forma resumida:

```bash
git status -s
```

### Branch

Branch são ramificações independentes dentro de um único projeto. Uma ramificação no git é um ponteiro para as alterações feitas nos arquivos do projeto. É útil em situações nas quais você deseja adicionar um novo recurso ou corrigir um erro, gerando uma nova ramificação garantindo que o código instável não seja mesclado nos arquivos do projeto principal. Depois de concluir a atualização dos códigos da ramificação, a branch pode ser mesclada com outras e eventualmente mesclada com a branch principal, geralmente chamada de master.

Listar as branchs:

```bash
git branch
```

Ver branch atual:

```bash
git rev-parse --abbrev-ref HEAD
```

Criar branch:

```bash
git branch <branch>
```

Criar a branch a partir da branch corrente e já fazer o checkout:

```bash
git checkout -b <branch>
```

Criar a branch a partir de outra branch e já fazer o checkout:

```bash
git checkout -b <branch-filha> <branch-pai>
```

Excluir branch:

```bash
git branch -D <branch>
```

### Checkout

Define qual a branch corrente, ou seja, a branch atualmente usada:

```bash
git checkout <branch>
```

Desfazendo alterações não commitadas em um arquivo para o último commit:

```bash
git checkout <arquivo>
```

Desfazendo alterações não commitadas de todos arquivos para o último commit:

```bash
git checkout .
```

Isso faz com que seu diretório ativo corresponda ao estado exato da confirmação do commit informado. Você pode procurar arquivos, compilar o projeto, realizar testes e até mesmo editar arquivos sem se preocupar em perder o estado atual do projeto. Nada que você faça aqui será salvo no seu repositório. Para continuar o desenvolvimento, é necessário voltar para o estado "atual" do projeto, como por exemplo, `git checkout <branch>`.

```bash
git checkout <hash-commit>
```

Criar uma branch a partir de um commit:

```bash
git checkout <hash-commit>
git checkout -b <branch>
```

### Merge

Mesclar uma branch com a branch corrente:

```bash
git merge <branch>
```

### Add

Adiciona os arquivos modificados à área de staging, estando prontos para serem comitados. O Stage também é chamado de Index ou Staging Area (ou área temporária) no Git.

Adiciona todos arquivos criados, modificados ou excluídos à área de staging, incluindo arquivos dos diretórios superiores:

```bash
git add -A
```

Adiciona todos arquivos criados, modificados ou excluídos à área de staging do diretório corrente:

```bash
git add .
```

Adiciona arquivos criados e modificados somente. Não inclui arquivos excluídos: 

```bash
git add -u
```

Adiciona um arquivo específico à área de staging:

```bash
git add <arquivo>
```

### Reset

Retornar para um commit anterior, porém diferente do `git revert`, ele não gera um novo commit. O comando `git reset` é uma ferramenta complexa e versátil para desfazer alterações. Ele tem três formas principais de invocação. Estas formas correspondem aos argumentos `--soft`, `--mixed`, `--hard` da linha de comandos. Cada um dos três argumentos corresponde a um mecanismo de gerenciamento do estado interno do Git: a árvore de confirmação (HEAD), o índice de staging e o diretório de trabalho.

O ponteiro HEAD aponta para o último grupo de alterações(snapshot) comitado. Podemos alterar para qual commit o HEAD aponta executando os comandos commit ou reset, como veremos. A área de index, também chamada área de stage, contém o próximo snapshot a ser comitado. Alteramos a área de index quando executamos o comando add. Por fim, o diretório de trabalho contém as alterações que ainda não foram adicionadas à área de stage.

O `git reset` tem comportamento semelhante ao `git checkout`. Enquanto o `git checkout` opera apenas no indicador de referência HEAD, o `git reset` move o indicador de referência HEAD e o indicador de referência da branch atual.

#### Reset soft

Move apenas o ponteiro HEAD para algum outro commit, sem alterar a área de stage ou o diretório de trabalho. É importante notar que, de fato, a operação moverá o branch para o qual o HEAD aponta e, por consequência, moverá também o ponteiro HEAD.

Essa opção é a mais segura entre as três, pois caso seja executada por engano, todo o trabalho atual ainda está acessível através da branch local.

Desfazendo uma alteração, mas colocando ela em stage:

```bash
git reset --soft HEAD~1
```

#### Reset mixed

O opção mixed ou, por ser o tipo default, somente reset. Essa opção, além de mover o ponteiro HEAD, faz com que a área de stage contenha o mesmo snapshot do commit para o qual o ponteiro HEAD foi movido, porém não afeta o diretório de trabalho.

Remove um arquivo específico da áreas de stage:

```bash
git reset <arquivo>
```

Remove todos arquivos da áreas de stage:

```bash
git reset HEAD .
```

#### Reset hard

A opção hard não apenas descarta as alterações na área de stage como também reverte todas as alterações no diretório de trabalho para o estado do commit que foi especificado no comando. Todas as alterações após o commit especificado, comitadas ou não, serão perdidas.

Desfazendo para o último commit sem colocar as alterações em stage:

```bash
git reset --hard HEAD~1
```

Desfazendo o último push:

```bash
git reset --hard HEAD~1 && git push -f origin master
```

### Revert

Pode ser considerado um comando do tipo "desfazer", entretanto ao invés vez de remover o commit do histórico do projeto, as alterações do commit especificado são desfeitas e um novo commit é gerado. Isto evita que o Git perca o histórico, o que é importante para a integridade do histórico de revisão e para uma colaboração confiável.

Desfazendo para um commit específico:

```bash
git revert <hash-commit>

# os primeiros números já são suficientes
```

### Diff

Ver a lista do que foi modificado em uma branch:

```bash
git diff
```

Ver o que foi modificado em um arquivo:

```bash
git diff <arquivo>
```

### Blame

Examina os pontos específicos do histórico de um arquivo e obtém quem foi o último autor que modificou a linha. É usado para explorar o histórico de código específico e responder dúvidas sobre o que, como e por que o código foi adicionado ao repositório.

Lista as pessoas que realizaram modificações no arquivo:

```bash
git blame <arquivo>
```

Lista as pessoas que realizaram modificações no arquivo ignorando as mudanças de espaço em branco.

```bash
git blame -w <arquivo>
```

Lista as pessoas que realizaram modificações no arquivo detectando linhas que foram movidas ou copiadas dentro do mesmo arquivo. Isto vai exibir o autor original das linhas, ao invés do último autor que moveu ou copiou as linhas.

```bash
git blame -M <arquivo>
```

Lista as pessoas que realizaram modificações no arquivo detectando linhas que foram movidas ou copiadas de outros arquivos. Isto vai exibir o autor original das linhas, ao invés do último autor que moveu ou copiou as linhas.

```bash
git blame -C <arquivo>
```

Lista as pessoas que realizaram modificações no arquivo nas linhas x até y:

```bash
git blame -L <x>,<y> <arquivo>

# exemplo
# git blame -L 1,5 README.md
```

### Commit

Commit é um processo simples que adiciona as alterações para o histórico do repositório e atribui um nome ao commit. O commit é como um *snapshot* do repositório no momento em que é executado.

Commit que irá abrir o editor de texto para informar a mensagem:

```bash
git commit
```

Commit já informando a mensagem de descrição:

```bash
git commit -m "<mensagem>"
```

Commit já adicionando os arquivos à área de staging (add + commit):

```bash
git commit -am "<mensagem>"
```

Commit sem informar mensagem de descrição:

```bash
git commit -m.

# add + commit
git commit -am.
```

Modificar a mensagem do último commit realizado:

```bash
git commit --amend -m "<mensagem>"
```

Ver os últimos commits

```bash
git log
```

### Push

Envia o conteúdo do repositório local para um repositório remoto.

Push da branch local corrente para a branch remota de mesmo nome:

```bash
git push
```

Push da branch local corrente para a branch informada:

```bash
git push <repo-remoto> <branch>

# exemplo:
# git push origin master
```

Push de todas novas branchs para o repositório remoto:

```bash
git push --all <repo-remoto>

# exemplo
# git push --all origin
```

Push de todas novas tags para o repositório remoto:

```bash
git push --tags <repo-remoto>
```

Remover uma branch remota:

```bash
git push <repo-remoto> :<branch>
```

### Pull

Busca e baixa conteúdo de repositórios remotos e faz a atualização imediata ao repositório local para que os conteúdos sejam iguais.

O comando `git pull` é a combinação de dois outros comandos, o `fetch`, seguido do `merge`. O comando `git pull` primeiro executa o `git fetch`, que baixa o conteúdo do repositório remoto especificado. Então, o `git merge` é executado e faz merge das refs e heads do conteúdo remoto para o novo commit de merge local.

Pull para a branch local corrente vinda da branch remota de mesmo nome:

```bash
git pull
```

Pull para a branch local corrente vinda da branch informada:

```bash
git pull <repo-remoto> <branch>

# exemplo:
# git pull origin master
```

Pull para a branch local corrente sem realizar merge. Os commits locais novos serão aplicados após o último commit remoto.

```bash
git pull --rebase
```

### Fetch

Baixa commits, arquivos e referências de um repositório remoto para seu repositório local. Ele vai baixar o conteúdo remoto, mas **não** vai atualizar a branch do repositório local, deixando a branch corrente intacta. O `git pull` é a alternativa mais agressiva: ele vai baixar o conteúdo remoto para a ramificação corrente local e vai executar o comando `git merge` imediatamente para criar um commit de merge para o novo conteúdo remoto.

Busca em todas as branchs do repositório remoto:

```bash
git fetch <repo-remoto>
```

Busca na branch informada do repositório remoto:

```bash
git fetch <repo-remoto> <branch>
```

### Stash

Pega as alterações sem commit e as salva para uso posterior. O stash é local, ou seja, não são transferidos para o repositório remoto ao realizar um push. Novos arquivos que não estão na área de staging (não executou o `git add`) e arquivos ignorados não são salvos ao realizar o `git stash`.

Realiza o stash dos arquivos não comitados da branch corrente.

```bash
git stash
```

Realiza o stash com descrição:

```bash
git stash save -u "<mensagem>"
```

Lista dos stashs criados:

```bash
git stash list
```

Aplica o stash mais recente na branch corrente:

```bash
git stash apply
```

Aplica o stash de índice i:

```bash
git stash apply stash@{i}
```

Remove o stash mais recente:

```bash
git stash pop
```

Remove o stash de índice i:

```bash
git stash pop stash@{i}
```

### Rm

Deletar arquivo ou diretório vazio:

```bash
git rm <arquivo-ou-diretorio>
```

Deletar diretório não vazio:

```bash
git rm -r <diretorio>
```

### Mv

Mover ou renomear arquivos ou diretórios:

```bash
git mv <caminho-origem> <caminho-destino>
```

### Log

Ver histórico de commits:

```bash
git log
```

Ver histórico dos últimos x commits:

```bash
git log -p -x

# exemplo:
# git log -p -2
```

Ver histórico de maneira resumida:

```bash
git log --pretty=oneline
```

Ver histórico de forma customizada:

```bash
git log --pretty=format:"%h = %an, %ar - %s"
```

Ver histórico por pessoa:

```bash
git log --author=nome_da_pessoa_ou_usuario
```

### Tag

Criar tag:

```bash
git tag <versao>

# exemplo:
# git tag 0.0.1
```

Criar uma tag com mensagem (anotada):

```bash
git tag -a <versao> -m "<mensagem>"

# exemplo:
# git tag -a 0.0.1 -m "versão 0.0.1"
```

Criar uma tag a partir de um commit:

```bash
git tag -a <versao> <hash-commit>

# exemplo:
# git tag -a 0.0.1 b6120
```

Enviar uma tag para o repositório remoto:

```bash
git push <repo-remoto> <versao>

# exemplo:
# git push origin 0.0.1
```

Enviar todas tags para o repositório remoto:

```bash
git push <repo-remoto> --tags

# exemplo:
# git push origin --tags
```

## Gerenciadores de repositórios

### Configurando a autenticação via SSH (Linux)

O arquivo com a chave pública pode ser encontrado em `~/.ssh`. Geralmente possui a extensão `.pub`, como por exemplo `id_rsa.pub`.

Caso o arquivo não exista, ele pode ser gerado através do comando:

```bash
ssh-keygen
```

Use o conteúdo do arquivo `.pub` nas configurações de acesso SSH do gerenciador de repositórios (GitHub, GitLab, Bitbucket).

## Referências

- <https://pt.wikipedia.org/wiki/Git>
- <https://www.devmedia.com.br/como-usar-os-comandos-do-git/33665>
- <https://www.atlassian.com/br/git/tutorials>
