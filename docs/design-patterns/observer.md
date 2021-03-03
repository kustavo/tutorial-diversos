# Observer

O Observer é um padrão de projeto de software que define uma dependência um-para-muitos entre objetos de modo que quando um objeto muda o estado, todos seus dependentes são notificados e atualizados automaticamente. Permite que objetos interessados sejam avisados da mudança de estado ou outros eventos ocorrendo num outro objeto.

Este é um padrão de projeto de software no qual um objeto, denominado *subject*, mantém uma lista de seus dependentes, chamados de *observers*, e os notifica automaticamente sobre qualquer mudança de estado, geralmente chamando um algum método do *observer*.

## Aplicabilidade

O padrão Observer trata dos seguintes problemas:

- Uma dependência um-para-muitos entre os objetos deve ser definida sem torná-los fortemente acoplados.
- Deve-se garantir que, quando um objeto muda de estado, um número aberto de objetos dependentes seja atualizado automaticamente.
- Deve ser possível que um objeto possa notificar um número aberto de outros objetos.

## Solução

Defina os objetos *Subject* e *Observer* de modo que quando um *subject* muda de estado, todos os *observer* registrados são notificados e atualizados automaticamente (e provavelmente de forma assíncrona).

A única responsabilidade de um *subject* é manter uma lista de *observers* e notificá-los sobre as mudanças de estado, chamando algum método do *observers*. A responsabilidade dos *observers* é registrar-se (e cancelar o registro) em um *subject* (para ser notificado sobre mudanças de estado) e atualizar seu estado (sincronizar seu estado com o estado do *subject*) quando forem notificados. Isso torna o *subject* e os *observers* vagamente acoplados. *Subject* e *observers* não têm conhecimento explícito um do outro. Os *observers* podem ser adicionados e removidos independentemente em tempo de execução. Essa interação de "notification-registration" também é conhecida como "publish-subscribe".

## Prós e contras

### Prós

- Princípio aberto/fechado. Você pode introduzir novas classes assinantes sem ter que mudar o código da publicadora (e vice versa se existe uma interface publicadora).

- Você pode estabelecer relações entre objetos durante a execução.

### Contras

- Assinantes são notificados em ordem aleatória

- O padrão Observer pode causar vazamentos de memória (em inglês, *memory leaks*) conhecidos como o problema do ouvinte prescrito (em inglês, *lapsed listener*). Em uma implementação básica, ele requer tanto que o registro quanto o cancelamento do registro sejam explícitos, uma vez que o *subject* mantém fortes referências aos *observers*, mantendo-os vivos. Isso pode ser evitado pelo *subject* que contém referências fracas aos *observers*.

    O padrão Observer, conforme descrito no livro GoF, é um conceito muito básico e não aborda a remoção do interesse entre *subject* e *observers* antes ou depois de notificar os *observers*. O padrão também não trata do registro quando as notificações de mudança são enviadas ou da garantia de que estão sendo recebidas. Essas preocupações são normalmente tratadas em sistemas de enfileiramento de mensagens, dos quais o padrão do Observer é apenas uma pequena parte.

## Código

Exemplo em C#

```c#
using System;
using System.Collections.Generic;
using System.Threading;

namespace DesignPatterns.Observer
{
    public interface IObserver
    {
        // Recebe atualização do subject.
        void Update(ISubject subject);
    }

    public interface ISubject
    {
        // Anexa um observer ao subject.
        void Attach(IObserver observer);

        // Desanexa um observer ao subject.
        void Detach(IObserver observer);

        // Notificar todos os observers sobre um evento.
        void Notify();
    }

    // O Subject possui algum estado importante e notifica os observers quando 
    // o estado muda.
    public class Subject : ISubject
    {
        // O estado do Subject será armazenado nesta variável.
        public int State { get; set; } = -0;

        // Lista de assinantes.
        private List<IObserver> _observers = new List<IObserver>();

        // Os métodos de gerenciamento de assinatura.
        public void Attach(IObserver observer)
        {
            Console.WriteLine("Subject: Anexou um observer.");
            this._observers.Add(observer);
        }

        public void Detach(IObserver observer)
        {
            this._observers.Remove(observer);
            Console.WriteLine("Subject: Desanexou um observer.");
        }

        // Acione uma atualização em cada assinante
        public void Notify()
        {
            Console.WriteLine("Subject: Notificando observers...");

            foreach (var observer in _observers)
            {
                observer.Update(this);
            }
        }

        // Alguma lógica de inscrição do Subject
        public void SomeBusinessLogic()
        {
            Console.WriteLine("\nSubject: Fazendo algo importante...");
            this.State = new Random().Next(0, 10);

            Thread.Sleep(15);

            Console.WriteLine("Subject: Meu estado mudou para: " + this.State);
            this.Notify();
        }
    }

    // Observers concretos reagem às atualizações emitidas pelo Subject ao qual 
    // foram anexados.
    class ConcreteObserverA : IObserver
    {
        public void Update(ISubject subject)
        {            
            if ((subject as Subject).State <= 6)
            {
                Console.WriteLine("ObserverA: Reagiu ao evento.");
            }
        }
    }

    class ConcreteObserverB : IObserver
    {
        public void Update(ISubject subject)
        {
            if ((subject as Subject).State >= 3)
            {
                Console.WriteLine("ObserverB: Reagiu ao evento.");
            }
        }
    }
    
    class Program
    {
        static void Main(string[] args)
        {
            // O código do cliente
            var subject = new Subject();
            var observerA = new ConcreteObserverA();
            subject.Attach(observerA);

            var observerB = new ConcreteObserverB();
            subject.Attach(observerB);

            subject.SomeBusinessLogic();
            subject.SomeBusinessLogic();

            subject.Detach(observerB);

            subject.SomeBusinessLogic();
        }
    }
}
```

## Referências

- <https://en.wikipedia.org/wiki/Observer_pattern>
- <https://pt.wikipedia.org/wiki/Observer>
- <https://refactoring.guru/pt-br/design-patterns/observer>
