# Bash

O shell Bash (acrônimo para "Bourne-Again SHell") é uma evolução retro-compatível muito mais interativa do Bourne Shell (sh). Lançado originalmente em 1989, o Bash é amplamente compatível com o shell sh, e incorpora características úteis do Korn shell (ksh), e do shell C (csh). Ele oferece melhorias funcionais em relação ao sh tanto para uso interativo como para programação.

O Bash apresenta recursos e características de uma linguagem de programação alto nível. É compatível por configuração com as normas POSIX, de forma que os scripts Bash podem ser executados em diversos sistemas tipo Unix. Desta forma, o Bash cresceu e se tornou facilmente o shell mais usado em todo o mundo Linux/Unix.

Com o passar do tempo, Bash adquiriu muitas extensões incompatíveis com o padrão POSIX. Porém, é possível executar o Bash de forma compatível com POSIX utilizando o parâmetro `--posix`:

```bash
bash --posix
```

## Arquivos de ambiente

Você pode customizar seu ambiente do Bash utilizando alguns arquivos como:

`.bash_profile`
:   Este arquivo fica localizado no diretório pessoal de cada usuário. É executado por shells que usam autenticação (nome e senha). Ele contém comandos que são executados para o usuário no momento do login no sistema após o `/etc/profile`.

`.bashrc`
:   Possui as mesmas características do `.bash_profile` mas é executado por shells que **não requerem autenticação** (como uma seção de terminal no modo gráfico)

`/etc/profile`
:   Este arquivo contém comandos que são executados para todos os usuários do sistema no momento de autenticação. Somente o usuário root pode ter permissão para modificar este arquivo.

    Este arquivo é lido antes do arquivo de configuração pessoal de cada usuário (.bash_profile ou .profile ). Pode ser utilizado caso seja necessário fazer alguma personalização do ambiente para todos os usuários que utilizam o computador.

    Quando é carregado através de um shell que requer autenticação (nome e senha), o Bash procura estes arquivos em sequência e executa os comandos contidos, caso existam:

    1. /etc/profile
    2. ~/.bash_profile
    3. ~/.bash_login
    4. ~/.profile

    A pesquisa será interrompida assim que localiza o primeiro arquivo no diretório do usuário. Por exemplo, se tem o arquivo `~/.bash_login` e `~/.bash_profile` no seu diretório de usuário, processará o `/etc/profile` e  `~/.bash_profile`, mas nunca processará o `~/.bash_login`.

    Caso o Bash seja carregado através de um shell que não requer autenticação (um terminal no modo gráfico), o seguinte arquivo é executado: ~/.bashrc.

## Estruturas da linguagem

### Comentários

Comentário de uma linha.

```bash
# Comentário de uma linha
```

Não há suporte para comentário de bloco, mas uma alternativa seria usar **here document**.

```bash
: << "END"
Conteúdo do bloco.
Tudo aqui será tratado como um bloco de comandos que será enviado para :
END
```

### Aspas

Aspas são usadas para delimitar uma string ou um parâmetro. Sem o uso de aspas é necessários usar a barra invertida para escapar os espaços.

```bash
COMANDO "arquivo de teste.txt"
COMANDO 'arquivo de teste.txt'  # Equivalente
COMANDO arquivo\ de\ teste.txt  # Equivalente
```

Aspas duplas são usadas se o conteúdo possui variáveis que serão substituídas por seu valor.

### Quebra de linha

Quebrar linhas para comandos grandes.

```bash
printf "Imprimindo: %s %s\n" \
        $(echo "Olá") \
        $(echo "Mundo!")
# Imprimindo: Olá Mundo!
```

### Expansão de nomes de arquivos

O Bash suporta uma série de notações especiais, conhecidas como expansões, para passar tipos de argumentos comumente usados em programas.

```bash
COMANDO arquivo1.txt arquivo2.txt arquivo3.txt
COMANDO arquivo{1,2,3}.txt  # Equivalente
COMANDO arquivo{1..3}.txt   # Equivalente
```

### Variáveis

Para atribuir um valor para uma variável é utilizado o sinal "**=**" e não deve haver espaços nem antes nem depois.

#### String

As aspas duplas são usadas se o conteúdo da string contém variáveis que devem retornar seu valor. Sem o uso de aspas é necessários escapar os espaços.

```bash
VAR='string simples'
VAR="string com variável $VAR"
VAR=string\ simples
VAR=string\ com\ variável\ "$VAR"
```

Concatenar strings.

```bash
VAR="Hello"
VAR+=" World"
echo "$VAR" # Hello World
```

Concatenar valor de duas variáveis string.

```bash
VAR1="Hello"
VAR2="World"
VAR3="$VAR1 $VAR2"
echo "$VAR3" # Hello World

# ou

VAR1="Hello"
VAR2="$VAR1 World"
echo "$VAR2" # Hello World
```

Retornar o tamanho de uma variável string.

```bash
VAR='Hello World'
echo ${#VAR} # 11
```

Para retornar uma substrings segue-se o padrão "**${string:posicao:tamanho}**". Se a posição for negativa (informada a partir do fim), deve usar parênteses ou um espaço após "**:**" dois pontos. Se não for informado o tamanho, será considerado até o final da string. Se não for informado a posição, será considerado a posição inicial.

```bash
VAR=0123456789
echo ${VAR: -3}   # 789
echo ${VAR:(-3)}  # 789
echo ${VAR:-3}    # 0123456789 (não funciona)
echo ${VAR: -3:1} # 7
echo ${VAR:3:2}   # 34
echo ${VAR:5}     # 56789
echo ${VAR::3}    # 012
```

Para substituir caracteres em strings segue-se o padrão "**${string/substring/substituicao}**" para substituir somente a primeira substring encontrada ou "**${string//substring/substituicao}**" para substituir todas as ocorrências encontradas.

```bash
VAR=abcb
VAR=${VAR/b/x} # axcb
echo $VAR

VAR=abcb
VAR=${VAR//b/x} # axcx
echo $VAR
```

#### Inteiro

Operações de inteiros, usando parênteses duplos.

```bash
echo $(( 3 + 4 * (5 - 1) )) # 19

echo $(( 10 ** 2 )) # 100

VAR=1
((VAR+=2))
echo $VAR # 3
```

Usando o comando "**bc**" para valores grandes.

```bash
echo 2^100 | bc

# ou atribuir para uma variável

VAR=$(echo '2^100' | bc -l)
echo $VAR
```

Ou simplesmente usando a função Bash "**let**". Não pode haver espaço entre os operadores, a não ser se escapar o espaço ou usar aspas.

```bash
let VAR=5+4
echo $VAR       # 9
let VAR++
echo $VAR       # 10
let VAR=VAR**2
echo $VAR       # 100
let VAR="5 - 2" # 3
```

A função Bash "**expr**" é semelhante a "**let**" mas apenas imprime o resultado. Precisa haver espaço entre os operadores.

```bash
expr 5 + 4 # 9
```

#### Float

Para realizar operações com ponto flutuante geralmente utiliza o comando "**bc**". O parâmetro "**-l**" é passado para usar a biblioteca matemática para manipular float se não for soma ou subtração. Por padrão o parâmetro usa precisão de 20 casas, portanto para outros valores é necessário informar a escala.

```bash
echo '3.4 + 2.2' | bc  # 5.6
echo '5.5 / 2' | bc    # 2
echo 'scale=2; 5.5 / 2' | bc -l # 2.75

# ou atribuir para uma variável

VAR=$(echo '5.5 / 2' | bc -l)
echo $VAR
```

#### Comando

Atribuição de comandos.

```bash
VAR=`comando -parametro`
# ou
VAR=$(comando -parametro)
```

Executar um comando armazenado em variável.

```bash
VAR="ls -l"
eval "$VAR"
```

#### Vetor

Concatenação em vetores. Para imprimir todos valores do vetor pode-se usar "**\***" ou "**@**". O espaço separa os elementos de um vetor.

```bash
ARRAY=(1 2)
echo ${ARRAY[@]} # 1 2
ARRAY+=(3)
echo ${ARRAY[@]} # 1 2 3
echo ${ARRAY[0]} # 1
echo ${ARRAY[1]} # 2
echo ${ARRAY[2]} # 3
```

Remover elemento do vetor.

```bash
ARRAY=(1 2 3)
unset ARRAY[1]
echo ${ARRAY[@]} # 1 3
```

Criar vetor associativo usando "**declare -A**".

```bash
declare -A VAR
VAR=([a]=0 [b]=1 [c]=2)
echo ${VAR[b]}           # 1
VAR[d]=3
echo ${VAR[c]} ${VAR[d]} # 2 3
unset VAR[b]
echo ${VAR[*]}           # 0 2 3
```

#### Função

Para obter o retorno de uma função é usado "**$( )**".

```bash
function funcao {
    echo Olá
}
VAR=$(funcao)
echo "$VAR" # Olá
```

#### Variáveis especiais

| Variável  | Descrição                                                 |
| --------- | --------------------------------------------------------- |
| $0        | O nome do script Bash.                                    |
| $1 - $9   | Os primeiros 9 argumentos passados para o script.         |
| $#        | Quantos argumentos foram passados para o script.          |
| $@        | Todos os argumentos passados para o script.               |
| $?        | O status de saída da última função ou processo executado. |
| $$        | Id do processo do script atual.                           |
| $USER     | Usuário que está executando o script.                     |
| $HOSTNAME | Hostname da máquina que está executando o script.         |
| $SECONDS  | Quantos segundos o script está executando.                |
| $RANDOM   | Retorna um número randômico.                              |
| $LINENO   | Retorna a linha que está sendo executada.                 |

### Impressão

Imprimir o conteúdo de uma variável.

```bash
echo $VAR
```

Se a variável é uma string que possui quebra de linha ou tabulações é necessário usar aspas duplas.

```bash
VAR="Olá
Mundo"
echo "$VAR"
```

O comando "**tput**" pode ser usado para imprimir em uma posição específica da tela.

```bash
colunas=$(tput cols) # número colunas terminal
linhas=$(tput lines) # número linhas terminal
mensagem=$@

tamanho_mensagem=${#mensagem}
metade_tamanho_mensagem=$(($tamanho_mensagem / 2))
metade_linhas=$(($linhas / 2))
metade_colunas=$((($colunas / 2) - $metade_tamanho_mensagem))

tput clear # limpar terminal
tput cup $metade_linhas $metade_colunas # local de impressão
tput bold
echo $@
tput sgr0
tput cup $( tput lines ) 0
```

### Funções e escopo de variáveis

Para declarar variáveis locais é usado a palavra-chave "**local**". Parênteses em funções são opcionais.

```bash
VAR='Variável Global'

function funcao {
    local VAR='Varável Local'
    echo $VAR
}

echo $VAR # 'Variável Global'
funcao    # 'Varável Local'
echo $VAR # 'Variável Global'
```

Os parâmetros passados para as funções são acessados da mesma forma que os parâmetros passados para executar o script "**$ + número**".

```bash
function funcao {
    echo $1  # oi
}
funcao oi
```

O "**return**" também pode ser usado, mas serve apenar para retornar valores numéricos que podem ser usados como status da execução. Para obter o valor retornado pela última função é usado "**$?**". Normalmente, um status de retorno de 0 indica que tudo foi feito com sucesso. Um valor não zero indica que ocorreu um erro.

```bash
function funcao {
    return 5
}
funcao
echo $? # 5
```

Para retornar algum valor, é usado o próprio "**echo"** que pode ser capturado por "**$( )"**.

```bash
function funcao {
    echo Olá
}
VAR=$(funcao)
echo "$VAR" # Olá
```

Para criar uma função com o mesmo nome de um comando é usado a palavra-chave "**command**".

```bash
ls {
    command ls -lh # chamará o comando ls
}
ls
```

### Fluxos de entrada, saída e erro

Os fluxos padrão são canais de entrada/saída entre um programa de computador e o seu ambiente (tipicamente um terminal de texto) que são pré-conectados no início da execução.

- **Entrada padrão (stdin):**\
    A entrada padrão indica que o dado (frequentemente texto) está indo para um programa.

- **Saída padrão (stdout):**\
    A saída padrão é um fluxo onde o programa escreve dados de saída.

- **Erro padrão (stderr):**\
    O erro padrão é um outro tipo de saída padrão, é utilizada pelos programas para envio de mensagens de erro ou de diagnóstico.

Formas de capturar os fluxos padrões de I/O dos processos executados:

- arquivo para stdin

    ```bash
    COMANDO < ARQUIVO
    ```

- stdout para arquivo

    ```bash
    COMANDO > ARQUIVO
    # ou
    COMANDO 1> ARQUIVO
    ```

- stderr para arquivo

    ```bash
    COMANDO 2> ARQUIVO
    ```

- stdout e stderr para arquivo

    ```bash
    COMANDO &> ARQUIVO
    ```

- stdout para o stderr

    ```bash
    COMANDO 1>&2
    ```

- stderr para o stdout

    ```bash
    COMANDO 2>&1
    ```

**Obs:** Para anexar a saída ao final do arquivo, ao invés de sobreescrevê-lo é usado **>>** ao invés de **>**.

### Here document

Um here document é um bloco de código de propósito especial. Ele usa uma forma de redirecionamento de I/O para alimentar um programa interativo ou um comando.

Passar o conteúdo de uma variável como entrada para outro comando.

```bash
    COMANDO << "$VARIAVEL"
```

Passar uma lista de comandos como entrada parar outro comando.

```bash
COMANDO << DELIMITADOR
    comando1 "$VAR"
    comando2
DELIMITADOR
```

Para não substituir $VAR pelo valor da variável, basta usar aspas no delimitador inicial.

```bash
COMANDO << "DELIMITADOR"
    comando1 "$VAR"
    comando2
DELIMITADOR
```

### Passagem de parâmetros

São usados os caracteres especiais **$ + número**.

```bash
$ ./script.sh a b c

echo "$1" # a
echo "$2" # b
echo "$3" # c
```

"**Obs:**" As aspas duplas são usadas para obter quebra de linhas ou tabulações caso sejam fornecidas.

### Estrutura Condicional

O teste condicional pode ser definido entre os delimitadores [], [[ ]] e (( )). Deve haver um espaço ente o teste condicional e os delimitadores. Os colchetes simples [ ] permitem comandos condicionais mais limitado, compatíveis com o POSIX. Enquanto que os colchetes duplos [[ ]] é uma extensão mais completa que foi adotada do *ksh88* e possui algumas vantagens como:

- Não é necessário escapar uma varável que contenha quebra de linhas ou espaços.

    ```bash
    if [ -f "$file" ]
    # contra
    if [[ -f $file ]]
    ```

- Permite o uso de && ou || para comparações boleanas e uso de parênteses.

    ```bash
    if [ INTEIRO -eq 1 ] || [ INTEIRO -eq 2 ]
    # contra
    if [[ INTEIRO -eq 1 || INTEIRO -eq 2 ]]
    ```

- O uso de  < ou > sem escape para comparações com strings.

    ```bash
    if [ STRING1 \> STRING2 ]
    # contra
    if [[ STRING1 > STRING2 ]]
    ```

- Permite a comparação com expressões regulares

    ```bash
    if [[ $RESPOSTA =~ ^s(im)?$ ]]
    # ou
    if [[ $RESPOSTA = y* ]]
    ```

Os parênteses duplos (( )) permitem o uso dos operadores para as operações aritméticas de forma mais simples ( <, >, >=, <=, !=, ==).

```bash
if [[ $VAR1 -ge 5 || $VAR2 -eq -1 ]]
# contra
if (( $VAR1 >= 5 || $VAR2 == -1 ))

[[ 01 -eq 1 ]] e (( 01 == 1 )) # ambos verdadeiros (inteiros)
[[ 01 == 1 ]] e [ 01 = 1 ]     # ambos falsos (string)
(( 01 -eq 1 )) e (( 01 = 1 ))  # ambos retornam erro
```

Para o teste das condições, será retornado o estado de saída zero (sucesso) se a condição é verdadeira e estado de saída não zero (falha) se a condição é falsa.

```bash
T1="a"
T2="b"

if [[ "$T1" = "$T2" ]]; then
    echo Expressão verdadeira
elif [[ "$T1" > "$T2" ]]; then
    echo Expressão verdadeira
else
    echo Expressão falsa # entra aqui
fi
```

Condicionais combinadas.

```bash
if [[ -e origem.txt ]] && ! [[ -e destino.txt ]] ; then
  # Se origem.txt existe e destino.txt não existe
fi
```

**Obs:** Para usar mais de um comando na mesma linha, é usado o "**;**".

Estrutura "case".

```bash
VAR=segundo

case $VAR in
primeiro)
echo 1
;;
segundo)
echo 2 # 2
;;
terceiro)
echo 3
;;
*)
echo desconhecido
;;
esac
```

#### Operadores condicionais

##### Operadores condicionais para arquivos

| Expressão  | Descrição                                                 |
| ---------- | --------------------------------------------------------- |
| -e ARQUIVO | Se o arquivo existe.                                      |
| -d ARQUIVO | Se o arquivo existe e é um diretório.                     |
| -f ARQUIVO | Se o arquivo existe e é um arquivo comum.                 |
| -s ARQUIVO | Se o arquivo não possui tamanho 0 (zero).                 |
| -r ARQUIVO | Se o arquivo possui permissão de leitura para o usuário.  |
| -w ARQUIVO | Se o arquivo possui permissão de escrita para o usuário.  |
| -w ARQUIVO | Se o arquivo possui permissão de execução para o usuário. |

##### Operadores condicionais para inteiros

| Expressão             | Descrição                |
| --------------------- | ------------------------ |
| INTEIRO1 -lt INTEIRO2 | Se INTEIRO1 < INTEIRO2.  |
| INTEIRO1 -gt INTEIRO2 | Se INTEIRO1 > INTEIRO2.  |
| INTEIRO1 -le INTEIRO2 | Se INTEIRO1 <= INTEIRO2. |
| INTEIRO1 -ge INTEIRO2 | Se INTEIRO1 >= INTEIRO2. |
| INTEIRO1 -eq INTEIRO2 | Se INTEIRO1 == INTEIRO2. |
| INTEIRO1 -ne INTEIRO2 | Se INTEIRO1 != INTEIRO2. |

**Obs:** Usando Parênteses duplos (( )) é possível usar os operadores tradicionais <, >, >=, <=, !=, ==.

##### Operadores condicionais para strings

| Expressão           | Descrição                          |
| ------------------- | ---------------------------------- |
| STRING1 == STRING2  | Se STRING1 == STRING2.             |
| STRING1 != STRING2  | Se STRING1 != STRING2.             |
| STRING1 \\< STRING2 | Se STRING1 < STRING2 ordem ASCII.  |
| STRING1 \\> STRING2 | Se STRING1 > STRING2  ordem ASCII. |
| STRING =~ PADRAO    | Se a string casa com o padrão.     |
| -n STRING           | Se a string não é vazia.           |
| -z STRING           | Se a string é vazia.               |

##### Operadores condicionais para boleanos

| Expressão    | Descrição         |
| ------------ | ----------------- |
| BOLEANO &&   | valor lógico "e"  |
| BOLEANO \|\| | valor lógico "ou" |

##### Operadores condicionais I/O

| Expressão | Descrição                                                                                      |
| --------- | ---------------------------------------------------------------------------------------------- |
| -t <0, 1> | Se o stdin [-t 0] ou o stdout [-t 1] está sendo feito pelo terminal. |

### Estrutura de repetição

Usando "**for**"

```bash
for file in $(ls) ; do
  echo "item: $file"
done

for i in {1..20} ; do
  echo "$i"
done

CORES=('rosa' 'verde lima' 'amarelo')
for COR in "${CORES[@]}" ; do
  echo "$COR"
done
```

Usando "**while**". Realiza o comando enquanto a condição for verdadeira.

```bash
while [[ -e arquivo.txt ]] ; do
  sleep 3
done
```

Usando "**until**". Realiza o comando enquanto a condição for falsa. O mesmo que usar o  "**while**" com "**!**".

```bash
until [[ -e arquivo.txt ]] ; do
  sleep 3
done
```

Todas as estruturas de repetição suporta "continue" e "break".

A estrutura "**select**" pode ser usada para criar menus.

```bash
names='Maria José Pedro Laura *Sair'
PS3='Selecione uma pessoa: '
select name in $names
do
    if [ $name == 'Sair' ]; then
        echo Bye
        break
    fi
    echo Olá $name
done
```

### Funções Shell

Esse recurso permite agrupar uma sequência de comandos em um único comando.

```bash
get() {
  if [[ -t 0 ]] ; then
    read -r -p 'Password:' -s "$1" && echo
  else
    return 1
  fi
}

get PASSWORD && echo "$PASSWORD"
```

O comando "**read**" armazena a entrada do usuário em variáveis. A variável "PASSWORD" é passada como parâmetro ("$1")e "**read**" irá armazenar a entrada do usuário na variável.

### Subshell

Um subshell recebe uma cópia do "ambiente de execução" do contexto circundante, que inclui quaisquer variáveis, entre outras coisas; mas as alterações que o subshell faz para o ambiente de execução não são copiadas de volta quando o subshell é concluído.

```bash
VAR1=olá
echo "$VAR1" # olá

(
  echo "$VAR1" # olá
  VAR2=mundo
  echo "$VAR2" # mundo
  VAR1=cruel
  echo "$VAR1" # cruel
)

echo "$VAR2" # nada, pois não existe
echo "$VAR1" # olá
```

### Interação com o usuário

Para obter a entrada do usuário, pode-se usar o comando "**read**".

```bash
echo Entre com seu primeiro e último nome:
read FN LN
echo "Olá $LN $FN!"
```

### Referências

- <https://pt.wikipedia.org/wiki/Bash>
- <https://en.wikipedia.org/wiki/Bash_(Unix_shell)>
- <https://devhints.io/bash>
- <http://tldp.org/LDP/abs/html/index.html>
