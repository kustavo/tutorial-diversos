package _factory.exemplo_simple_factory;

public class Pizzaria {

    Pizza pizza;

    public static void main(String[] args) {

        Pizzaria pizzaria = new Pizzaria();

        /**
         * Gera uma instância do objeto sem expor nenhuma implementação. A fábrica toma a
         * decisão de qual subclasse deve ser retornada a instância baseado no argumento
         * passado.
         */
        pizzaria.pizza = PizzaFactory.criarPizza("portuguesa");
    }
}

final class PizzaFactory {

    private PizzaFactory() {}

    public static Pizza criarPizza(String tipo) {

        Pizza pizza = null;

        switch (tipo) {
            case "queijo":
                pizza = new PizzaQuatroQueijos();
                break;
            case "portuguesa":
                pizza = new PizzaPortuguesa();
                break;
            case "calabresa":
                pizza = new PizzaCalabresa();
                break;
            case "camarao":
                pizza = new PizzaCamarao();
                break;
        }
        return pizza;
    }
}

interface Pizza { }
class PizzaQuatroQueijos implements Pizza {}
class PizzaPortuguesa implements Pizza {}
class PizzaCalabresa implements Pizza {}
class PizzaCamarao implements Pizza {}
