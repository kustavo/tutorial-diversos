#!/usr/bin/python3

""" 
THREAD

Utilizando threads e processos
"""

"""
 Threads são relativamente caros para serem criados e são difíceis de controlar e programar. Nos próximos posts, abordarei outros detalhes do módulo asyncio. Antes de continuarmos com métodos assíncronos, vamos comparar o tempo de execução entre as soluções assíncronas, múltiplos threads e com múltiplos processos. Vamos avaliar versões modificadas da função calcula_algo para cada um dessas formas de paralelização. O problema testado será o tempo de total de execução de 20 chamadas a calcula_algo.
"""

import sys

"""
Solução assíncrona, sem uso de thread
"""
if sys.argv[1] == '1':

    import asyncio
    import time
    
    def calcula_algo(loop, id):
        limite = 40000
        print("Calculando %d" % id)
        z=1
        for x in range(1,limite):
            z*=x
        print("Fim do Cálculo %d" % id)
        if id < 40:
            loop.call_soon(calcula_algo, loop, id+1)
        else:
            loop.stop()
    
    inicio = time.time()
    loop = asyncio.get_event_loop()
    loop.call_soon(calcula_algo, loop, 1)
    loop.run_forever()
    fim = time.time()
    print("Tempo total: %f s" % (fim-inicio))


"""
Uso de thread
"""
if sys.argv[1] == '2':

    import time
    import threading
    
    def calcula_algo(id):
        limite = 40000
        print("Calculando %d" % id)
        z=1
        for x in range(1,limite):
            z*=x
        print("Fim do Cálculo %d" % id)
    
    inicio = time.time()
    ativos = []
    for x in range(40):
        t = threading.Thread(target=calcula_algo, args=(x,))
        t.start()
        ativos.append(t)
    for t in ativos:
        t.join()
    fim = time.time()
    print("Tempo total: %f s" % (fim-inicio))

"""
Devido a uma característica do interpretador Python, que utiliza um lock global, o famoso GIL, programas com múltiplos thread em Python não são eficientes, pois apenas um thread executa de cada vez, o que nos faz voltar ao problema do programa assíncrono adicionado ao tempo de criação e execução dos threads.
"""

"""
Utilizando múltiplos processos
"""
if sys.argv[1] == '3':

    import sys
    import time
    from multiprocessing import Pool
    
    def calcula_algo(id):
        limite = 40000
        print("Calculando %d" % id)
        z=1
        for x in range(1,limite):
            z*=x
        print("Fim do Cálculo %d" % id)
    
    if __name__ == '__main__':
        nproc = int(sys.argv[2])
        print("Executando com %d processos." % nproc)
        inicio = time.time()
        processos = Pool(nproc)
        processos.map(calcula_algo,list(range(40)))
        fim = time.time()
        print("Tempo total: %f s" % (fim-inicio))

"""
Uma das vantagens do multiprocessing é que cada processo roda seu próprio interpretador Python e assim são capazes de rodar simultaneamente, sem os problemas do GIL que falamos anteriormente.

O mau desempenho do módulo asyncio é apenas um exemplo de má utilização. Os métodos assíncronos devem ser utilizados com funções que não bloqueiam e que terminam rapidamente. Usar métodos assíncronos com funções CPU bound não pode trazer bons resultados. No entanto, devido aos problemas com o GIL, métodos assíncronos podem simplificar o trabalho de programação e manter a performance de múltiplos threads. Pois a execução sequencial das funções evita a necessidade de sincronizar seus dados. Mesmo com os problemas de GIL, programas em Python que usam threads devem se preparar para execução simultânea de funções, pois a execução salta de um thread a outro durante a execução das funções.
"""

"""
LEITURA

https://pt.stackoverflow.com/questions/253308/o-que-%C3%A9-global-interpreter-lock-gil


O que exatamente é o GIL?
---------------------------------------------------------

O Global Interpreter Lock é uma flag que existe no interpretador do Python, e faz com que apenas uma sequência de bytecode na VM de Python seja executada por vez.

A razão inicial de sua criação é que o gerenciamento interno de memória do interpretador Python não é thread-safe. Isso é: o código em C que reserva memória para criação de objetos, mesmo os simples como inteiros ou tuplas, usa o GIL como Mutex para garantir que não vai ser interrompido pelo sistema operacional, numa mudança de thread, e deixando as estruturas de dados internas que o Python usa para memória num estado inconsistente.

Além disso, O GIL é o responsável pelas estruturas de dados do Python como listas, dicionários e, mais importante, as estruturas internas como frames de execução, os dicionários que armazenam variáveis, etc... funcionem de forma transparente em código que use várias threads.

Basicamente, o código central da VM do Python, a estrutura de "dispatch" que é um grande "switch case" para o bytecode só "anda" se tiver controle do GIL.

Acho que a fonte mais relevante em inglês sobre o GIL está no wiki do Python: https://wiki.python.org/moin/GlobalInterpreterLock (muito da informação que está lá está abaixo também)


Devido às implementações do CPython, apenas uma thread é analisada pelo interpretador por vez. Isso implica que as threads não são executadas em paralelo? Uma thread deve liberar o interpretador para que as outras sejam executadas? Se sim, quando isso ocorre?
------------------------------------------------------------

Sim, enquanto a thread estiver executando código Python. Isso evita que outra thread seja ativada no meio de uma expressão, e altere valores de variáveis em transações que tem que ser atômicas.

No entanto em todos os momentos que uma thread vai fazer uma ação bloqueante - ou seja, algo que não depende de executar mais bytecode, mas vai esperar a leitura de dados de um arquivo, ou de um socket, ou ainda vai simplesmente chamar uma função em código nativo (em geral em C), que não tem perigo de conflitar com o estado interno do interpretador Python, o GIL é "liberado": ou seja,a thread em execução libera o GIL, e outras threads podem executar seu código (mesmo que um núcleo CPU esteja em 100% processando código nativo).

Por isso, programas que usam threads para atender várias requisições de internet, ou processar arquivos de um disco lento, ou fazer cálculos numéricos pesados usando o NumPy, por exemplo, não sofrem tanto impacto do GIL. No entanto, programas que tenham algoritmos complexos em Python puro, por exemplo, várias operações em strings pequenas, sofrem um impacto grande - aí sim acontece de "só rodar uma thread por vez".

Em particular quando se usa o NumPy para processamento numérico, ele faz naturalmente uso de múltiplos núcleos da CPU, sem que o desenvolvedor tenha que se preocupar com threads ou qualquer outro recurso na parte em Python do código.


Dada que é uma característica do CPython, GIL é exclusivo para o CPython ou outros interpretadores também possuem tal característica?------------------------------------------------------------

Em geral outras implementações do Python, como o Pypy vão ter um GIL também. O Jython e o IronPython usam o mecanismo de suas respectivas VMs para poder ter código consistente em threads, e não tem o GIL.


E como faz então?
------------------------------------------------------------

Multiprocessing

https://docs.python.org/3/library/multiprocessing.html

A forma mais simples de ter código multithreaded efetivamente rodando em paralelo, sem preocupações com o GIL é o multiprocessing: é uma API criada para ser compatível com o threading, no entanto, cria um novo processo para o que seria uma thread. Os pontos negativos são: criar um novo processo em termos de recurso do sistema é algo muito mais "caro" do que criar uma thread, e, a passagem de argumentos entre os subprocessos é feita serializando-se todos os dados com pickle. Isso causa um overhead, e impede que objetos não serializáveis (como arquivos abertos, sockets, etc...) sejam passados como parâmetros de forma ordinária.

De qualquer forma, veja também o concurrent.futures - é uma biblioteca que permite o uso de pools de threads e processos de forma bem simplificada, com várias estruturas de controle que facilitam a criação de poucos processos ou threads e reaproveitam os mesmos de forma eficiente para executar várias vezes as tarefas. https://docs.python.org/3/library/concurrent.futures.html

asyncio

https://docs.python.org/3/library/asyncio.html

Começando no Python 3.4, o asyncio é a nova onda de programação eficiente em Python. Basicamente foi acrescentado suporte na linguagem, inicialmente apenas com a biblioteca, e no Python 3.5 com sintaxe específica para que a distribuição de várias tarefas que tipicamente eram executadas numa aplicação de servidor em threads distintas fiquem todas na mesma thread, e o uso de co-rotinas permite a troca de contexto entre as tarefas distintas.

AsyncIO não exatamente "dribla" o GIL - é apenas uma forma de programação concorrente que usa explicitamente uma única thread. Tipicamente nos mesmos pontos em que o GIL seria liberado no código, a thread passa a rodar outra tarefa assíncrona. Para os casos em que as tarefas finais não tenham implementações assíncronas, é necessário executa-las explicitamente num worker que pode ser em outra thread, ou outro processo, de forma muito parecida com o que é feito com o concurrent.futures.

Cython

http://cython.org/

Um jeito interessante de escrever seu algoritmo "intenso" em Python puro e não estar submetido ao GIL é usar o Cython. Cython é um super-conjunto da linguagem Python que traduz a sintaxe de Python direto para código C usando a Python API, mas com a opção de ter tipagem estática para variáveis que precisam ser acessadas mais rápidas. Se você tiver certeza que um trecho com bastante uso da CPU não vai interferir em variáveis fora da thread atual, o Cython tem chamadas para liberar o GIL explicitamente.

Se você vai programar suas próprias extensões de Python em código nativo, seja em C ou uma linguagem como Go, Rust, C++, etc... você é responsável, da mesma forma que programando em Cython, por liberar o GIL. Mais informações aqui: https://docs.python.org/3/c-api/init.html#thread-state-and-the-global-interpreter-lock

Celery

http://www.celeryproject.org/

Se você tem que criar código de backend para atender várias requisições em paralelo, com uso intenso de CPU, esse é o caminho a seguir: Celery é um conjunto de ferramentas que permite a distribuição de tarefas usando um "broker" terceirizado. Disparar uma tarefa para ser executada de forma assíncrona com celery é tão simples quanto chamar uma função, mas tem algumas vantagens sobre os outros métodos: Os seus workers de celery não precisam estar nem na mesma máquina - só é necessário que todos os workers possam se comunicar com o processo broker (que pode ser um banco como o redis, o rabbitmq, ou as próprias Queues da Amazon (SQS)). Além disso, o celery tem embutido um sistema simples de re-execução de uma tarefa caso elas não sejam completadas em tempo hábil. Celery é bem simples de usar em uma única máquina, e é tranquilo configurar num conjunto de máquinas na nuvem que tenha escalabilidade horizontal com preocupação praticamente zero.

PEP 554 - Múltiplos interpretadores

https://www.python.org/dev/peps/pep-0554/

Quando a PEP-554 for implementada (em algum ponto do Python 3.8 pra frente), será possível ter código de Python puro que tenha múltiplas instâncias do interpretador CPython no mesmo processo: isso vai permitir um GIL para cada interpretador. (No entanto, código usando múltiplos interpretadores dessa forma é algo bem avançado e ainda vai ser um pouco experimental - só mencionei aqui por que estamos prestes a superar a 'barreira de um GIL' com isso)

Outros

Além das formas citadas, há outros jeitos de se contornar o GIL - em geral outras bibliotecas de execução remota de código: o Celery não é a única, é apenas a mais usada. Há um protótipo do Pypy que tenta usar uma abordagem de "memória transacional" e remover o GIL, e há desenvolvedores do core do Python trabalhando em um fork que tenta fazer uma "GILectomia": eliminar o GIL completamente. Mas esse esforço vai demorar alguns anos ainda. https://github.com/larryhastings/gilectomy

"""