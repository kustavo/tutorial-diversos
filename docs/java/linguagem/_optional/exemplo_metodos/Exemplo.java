package _optional.exemplo_metodos;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<Carro> carros = new ArrayList<>();

        carros.add(new Carro("Uno", 1000.0));
        carros.add(new Carro("Gol", 2000.0));
        carros.add(new Carro("Celta", 1500.0));
        carros.add(new Carro("HB20"));

        for (Carro carro : carros) {

            /*
             * ifPresent(): se um valor estiver presente, executa a ação no valor,
             * caso contrário, não faz nada.
             *
             * return: 1000.0 2000.0 1500.0
             */
            carro.getSeguro().ifPresent(s -> System.out.print(s.getValor() + " "));

            /*
             * or(): Se um valor estiver presente, retorna o valor, caso contrário,
             * retorna o valor Optional
             *
             * return: 1000.0 2000.0 1500.0 0.0
             */
            System.out.print(carro.getSeguro()
                    .or(() -> Optional.ofNullable(new Seguro(0.0)))
                    .map(Seguro::getValor).get());

            /*
             * orElse(): se um valor estiver presente, retorna o valor, caso contrário,
             * retorna o valor definido no parâmetro
             *
             * return: 1000.0 2000.0 1500.0 0.0
             */
            System.out.print(carro.getSeguro().map(Seguro::getValor)
                    .orElse(0.0) + " ");

            /*
             * ifPresentOrElse(): se um valor estiver presente, executa uma determinada
             * ação, caso contrário, executa uma outra ação
             *
             * return: 500.0 1000.0 750.0 0.0
             */
            carro.getSeguro().map(Seguro::getValor)
                    .ifPresentOrElse(s -> System.out.print(s * 0.5 + " "),
                            () -> System.out.print(0.0 + " "));

            /*
             * orElseThrow(): se um valor estiver presente, retorna o valor, caso
             * contrário, lança a exceção informada
             */
            System.out.print(carro.getSeguro().map(Seguro::getValor)
                    .orElseThrow(IllegalArgumentException::new));

            /*
             * filter(): retorna o valor se está presente satisfaz a condição, caso
             * contrário retorna um Optional vazio.
             *
             * return: 2000.0 1500.0
             */
            carro.getSeguro().filter(s -> s.getValor() > 1000.0)
                    .ifPresent(s -> System.out.print(s.getValor() + " "));

            /*
             * stream(): Se um valor estiver presente, retorna uma Stream contendo apenas
             * esse valor, caso contrário, retorna uma Stream vazia.
             *
             * return: 1000.0 2000.0 1500.0
             */
            Stream<Double> stream = carro.getSeguro().map(Seguro::getValor).stream();
            stream.forEach(System.out::println);
        }
    }
}

class Carro {

    String modelo;
    Seguro seguro;

    Carro(String modelo) {
        this.modelo = modelo;
    }

    Carro(String modelo, Double valorSeguro) {
        this.modelo = modelo;
        seguro = new Seguro(valorSeguro);
    }

    Optional<Seguro> getSeguro() {
        return Optional.ofNullable(this.seguro);
    }
}

class Seguro {

    Double valor;

    Seguro(Double valor) {
        this.valor = valor;
    }

    Double getValor() {
        return this.valor;
    }
}