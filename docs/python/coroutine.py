#!/usr/bin/python3

""" 
ASYNCHRONOUS I/O

O módulo asyncio provê uma forma de se escalonar aplicações I/O bound com código concorrente, em uma única thread. 
""" 

""" 
COROUTINES

Para que seja possível implementar um modelo de programação concorrente, é  preciso que seja possível quebrar uma tarefa em pedaços que podem ser executados independentemente. 

As coroutines são funções que podem ser suspensas em determinados pontos de execução para serem retomadas depois, mantendo todos os estados de quando foi suspensa. Nós também podemos definir corotinas como um conjunto de sub-rotinas (instruções), que permitem vários pontos de entrada, de suspensão e retomada de execução em certas partes do código, realizando uma troca de contexto.
"""

"""
LOOP DE EVENTOS

Corotinas são executadas em um loop de eventos. Loops de eventos, por sua vez, são os responsáveis por:

- Controlar e manter o agendamento de corotinas e seus pontos de suspensão e retomada toda vez que uma corotina necessitar de tempo para execução de uma tarefa;
- Lidar com sinais do sistemas operacional;
- Transmitir dados através da rede;
- Prover abstrações para transporte para diversos canais de comunicação;
"""

"""
TASK/FUTURE

- Responsável pelo controle de execução de uma corroutine;
- Contém importantes métodos como done(), result() e cancel();
- Uma Task só é gerada pelo asyncio, utilizando o asyncio.ensure_future() ou o loop.create_task();
"""

"""
Python 3.4                 Python 3.5
                         
@asyncio.coroutine         => async def funcao():
def funcao():              

yield from x()             => await x()
"""

import sys

"""
Loop de eventos simples
"""
if sys.argv[1] == '1':

    import asyncio
    
    def print_and_repeat(loop):
        print('Hello World')
        loop.call_later(2, print_and_repeat, loop) # agenda a próxima chamada para daqui 2s


    loop = asyncio.get_event_loop() # retorna o loop de eventos atual
    loop.call_soon(print_and_repeat, loop) #  agenda a chamada do método print_and_repeat. O segundo parâmetro é o parâmetro para print_and_repeat.
    loop.run_forever()
    print('Imprime somente após execução do loop')


"""
Loop de eventos
"""
if sys.argv[1] == '2':

    import asyncio
    import time
    import random
    
    def faz_algo(loop):
        espera = random.random() + 0.5
        print("F1 Fazendo algo, espera: %f" % espera)
        loop.call_later(espera, faz_algo, loop)
    
    def print_and_repeat(loop):
        global ultimo
        agora = time.time()
        print('F2 Alô - Tempo decorrido: %f' % (agora - ultimo))
        ultimo = agora
        loop.call_later(2, print_and_repeat, loop)
    
    ultimo = time.time()
    loop = asyncio.get_event_loop()
    loop.call_soon(print_and_repeat, loop)
    loop.call_soon(faz_algo, loop)
    loop.run_forever()

"""
Faz_algo e print_and_repeat são chamadas alternadamente, ou melhor, quase que ao mesmo tempo, como se estivéssemos utilizando threads.
"""

"""
Loop de eventos cpu cpu bound
"""
if sys.argv[1] == '3':

    import asyncio
    import time
    import random
    
    def calcula_algo(loop, id):
        limite = random.randint(30000,50000)
        print("F1 Calculando %d" % id)
        z=1
        for x in range(1,limite):
            z*=x
        print("F1 Fim do Cálculo %d" % id)
        loop.call_soon(calcula_algo, loop, id+2)
    
    def faz_algo(loop):
        espera = random.random()
        print("F2 Fazendo algo, espera: %f" % espera)
        loop.call_later(espera, faz_algo, loop)
    
    def print_and_repeat(loop):
        global último
        agora = time.time()
        print('F3 Alô - Tempo decorrido: %f' % (agora - último))
        último = agora
        loop.call_later(2, print_and_repeat, loop)
    
    último = time.time()
    loop = asyncio.get_event_loop()
    loop.call_soon(print_and_repeat, loop)
    loop.call_soon(faz_algo, loop)
    loop.call_soon(calcula_algo, loop, 1)
    loop.call_soon(calcula_algo, loop, 2)
    loop.run_forever()

"""
Veja que o atraso para chamar as outras funções é agora muito mais importante, ultrapassando os 6 segundos entre as chamadas de print_and_repeat e tendo um atraso considerável também no processamento de faz_algo. Bem, este comportamento é esperado, uma vez que a função calcula_algo é o que se chama de CPU bound, ou seja, é uma função que precisa mais da atenção do processador do computador que uma operação de criação de arquivo (I/O bound). 

Para utilizar corretamente seu computador, você deve começar a separar seus problemas em CPU bound e I/O bound. No caso de problemas CPU bound, threads oferecem a melhor performance, pois temos vários processadores no mesmo computador. Já para problemas I/O bound, ou seja, que precisam acessar o disco ou a rede (ou entrada de dados vinda do teclado), o loop de eventos assíncrono é mais rápido e fácil de programar. Em problemas mistos, onde temos código CPU bound e código I/O bound a executar, uma solução mista precisa ser aplicada.
"""

"""
Coroutine
"""
if sys.argv[1] == '4':

    import asyncio
 
    async def print_and_repeat(loop):
        while True:
            print('Hello World')
            await asyncio.sleep(2)
    
    loop = asyncio.get_event_loop()
    try:
        loop.run_until_complete(print_and_repeat(loop))
    finally:
        loop.close()

"""
"async" transforma nossa função em uma corotina.

Uma corotina pode ser suspensa e esperar o processamento de uma outra corotina. No caso, asyncio.sleep(2) é uma corotina do módulo asyncio que suspende a execução da função pelo número de segundos passados como parâmetro. Na realidade, esta chamada retorna uma corotina que é marcada como completa após os 2 segundos. Isto pode ser realizado pois no "await", criamos a nova corotina e indicamos ao loop que não continue a executar print_and_repeat até que a nova corotina esteja concluída. A partir deste ponto, a execução volta ao loop de eventos que monitora a conclusão da nova corotina criada, suspendendo a execução da anterior. Uma vez que o a corotina do sleep é concluída, após 2 segundos, o loop reativa a chamada suspensa de print_and_repeat e a execução continua, voltando para o while.

""" 


if sys.argv[1] == '5':
    import asyncio

    async def slow_operation(n):
        await asyncio.sleep(1)
        print("Slow operation {} complete".format(n))


    async def main():
        await asyncio.wait([
            slow_operation(1),
            slow_operation(2),
            slow_operation(3),
        ])


    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())