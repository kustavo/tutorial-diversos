package _factory.exemplo_factory_method;

public class Pizzaria {

    Pizza pizza;

    public static void main(String[] args) {

        Pizzaria pizzaria = new Pizzaria();

        /**
         * É responsabilidade das subclasses instanciar o objeto. Ou seja, somente
         * PizzaFactorySaoPaulo e PizzaFactoryRioDeJaneiro sabem criar suas respectivas
         * pizzas.
         */
        PizzaFactory pizzariaSaoPaulo = new PizzaFactorySaoPaulo();
        pizzariaSaoPaulo.criarPizza("queijo");
        pizzaria.pizza = pizzariaSaoPaulo.delivery();

        PizzaFactory pizzariaRioDeJaneiro = new PizzaFactoryRioDeJaneiro();
        pizzariaRioDeJaneiro.criarPizza("calabresa");
        pizzaria.pizza = pizzariaRioDeJaneiro.delivery();
    }
}

class PizzaFactorySaoPaulo extends PizzaFactory {

    @Override
    public void criarPizza(String tipo) {

        switch (tipo) {
            case "queijo":
                this.pizza = new SpPizzaQuatroQueijos();
                break;
            case "portuguesa":
                this.pizza = new SpPizzaPortuguesa();
                break;
            case "calabresa":
                this.pizza = new SpPizzaCalabresa();
                break;
        }
    }
}

class PizzaFactoryRioDeJaneiro extends PizzaFactory {

    @Override
    public void criarPizza(String tipo) {

        switch (tipo) {
            case "queijo":
                this.pizza = new RjPizzaQuatroQueijos();
                break;
            case "portuguesa":
                this.pizza = new RjPizzaPortuguesa();
                break;
            case "calabresa":
                this.pizza = new RjPizzaCalabresa();
                break;
        }
    }
}

abstract class PizzaFactory {

    protected Pizza pizza;

    /*
     * O método factory, que deve ser implementado pelas classes filhas.
     */
    public abstract void criarPizza(String tipo);

    public Pizza delivery() {
        return pizza;
    }
}

interface Pizza { }
class SpPizzaQuatroQueijos implements Pizza {}
class SpPizzaPortuguesa implements Pizza {}
class SpPizzaCalabresa implements Pizza {}
class RjPizzaQuatroQueijos implements Pizza {}
class RjPizzaPortuguesa implements Pizza {}
class RjPizzaCalabresa implements Pizza {}