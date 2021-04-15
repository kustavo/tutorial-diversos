package exemplo_abstract_factory;

public class Pizzaria {

    Pizza pizza;

    public void criarPizza(String cidade, String tipo){

        switch (tipo) {
            case "queijo":
                this.pizza = new PizzaQuatroQueijos(getIngredientes(cidade));
                break;
            case "portuguesa":
                this.pizza = new PizzaPortuguesa(getIngredientes(cidade));
                break;
            case "calabresa":
                this.pizza = new PizzaCalabresa(getIngredientes(cidade));
                break;
        }
    }

    private PizzaIngredientesFactory getIngredientes(String cidade) {

        if (cidade.equals("sao-paulo")) {
            return new SPPizzaIngredientesFactory();
        }
        return new RJPizzaIngredientesFactory();
    }

    public static void main(String[] args) {

        Pizzaria pizzaria = new Pizzaria();
        pizzaria.criarPizza("queijo", "sao-paulo");
    }
}

abstract class PizzaIngredientesFactory {

    public abstract Massa criarMassa();
    public abstract Queijo criarQueijo();
}


class SPPizzaIngredientesFactory extends PizzaIngredientesFactory {

    @Override
    public Massa criarMassa() {
        return new MassaGrossa();
    }

    @Override
    public Queijo criarQueijo() {
        return new QueijoMinas();
    }
}

class RJPizzaIngredientesFactory extends PizzaIngredientesFactory {

    @Override
    public Massa criarMassa() {
        return new MassaFina();
    }

    @Override
    public Queijo criarQueijo() {
        return new QueijoParmesao();
    }
}


class PizzaCalabresa extends Pizza {

    private PizzaIngredientesFactory ingredientes;
    private Massa massa;
    private Queijo queijo;

    public PizzaCalabresa(PizzaIngredientesFactory ingredientes){
        this.ingredientes = ingredientes;
    }

    public void prepara(){
        massa = ingredientes.criarMassa();
        queijo = ingredientes.criarQueijo();
    }
}

class PizzaPortuguesa extends Pizza {

    private PizzaIngredientesFactory ingredientes;
    private Massa massa;
    private Queijo queijo;

    public PizzaPortuguesa(PizzaIngredientesFactory ingredientes){
        this.ingredientes = ingredientes;
    }

    public void prepara(){
        massa = ingredientes.criarMassa();
        queijo = ingredientes.criarQueijo();
    }
}

class PizzaQuatroQueijos extends Pizza {

    private PizzaIngredientesFactory ingredientes;
    private Massa massa;
    private Queijo queijo;

    public PizzaQuatroQueijos(PizzaIngredientesFactory ingredientes){
        this.ingredientes = ingredientes;
    }

    public void prepara(){
        massa = ingredientes.criarMassa();
        queijo = ingredientes.criarQueijo();
    }
}

class Pizza {}
class Massa {}
class MassaFina extends Massa {}
class MassaGrossa extends Massa {}
class Queijo {}
class QueijoMinas extends Queijo {}
class QueijoParmesao extends Queijo {}