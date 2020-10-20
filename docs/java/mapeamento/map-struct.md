# MapStruct

[TOC]

## Introdução

MapStruct é um processador de anotação Java para a geração de classes de mapeamento de *bean* com segurança de tipo.

Tudo que é preciso fazer é definir uma interface do mapeador que declare qualquer método de mapeamento necessário. Durante a compilação, o MapStruct irá gerar uma implementação dessa interface. Essa implementação usa invocações simples do método Java para mapeamento entre objetos de origem e destino, ou seja, sem reflexão ou similar.

```java
import lombok.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public class Main {

    public static void main(String[] args) {

        Carro carroOriginal = new Carro( "Morris", 5, 90.5, CarroTipo.SEDAN );

        CarroDto carroDto = CarroMapa.INSTANCE.fromCarro(carroOriginal);
        System.out.println(carroDto);

        Carro carro = CarroMapa.INSTANCE.toCarro(carroDto);
        System.out.println(carro);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Carro {

    private String marca;
    private int numeroAcentos;
    private double potencia;
    private CarroTipo tipo;
}

@NoArgsConstructor
enum CarroTipo {

    SEDAN, HATCH, SUV;
}

@Getter
@Setter
@NoArgsConstructor
@ToString
class CarroDto {

    private String marca;
    private int acentos;
    private double cavalos;
    private String tipo;
}

@Mapper
interface CarroMapa {

    CarroMapa INSTANCE = Mappers.getMapper(CarroMapa.class);

    @Mapping(source = "numeroAcentos", target = "acentos")
    @Mapping(source = "potencia", target = "cavalos")
    CarroDto fromCarro(Carro carro);

    @InheritInverseConfiguration
    Carro toCarro(CarroDto carro);
}

// Saida
// > CarroDto(marca=Morris, acentos=5, cavalos=90.5, tipo=SEDAN)
// > Carro(marca=Morris, numeroAcentos=5, potencia=90.5, tipo=SEDAN)
```

A anotação `@Mapper` marca a interface como interface de mapeamento e permite que o processador MapStruct seja acionado durante a compilação.

Uma instância da implementação da interface pode ser recuperada da classe `Mappers`. Por convenção, a interface declara um membro `INSTANCE`, fornecendo aos clientes acesso à implementação do mapeador.

A anotação `@Mapping` permite definirmos qual campo da classe será mapeado para outro com nome diferente. Se os nomes forem iguais, não é necessário descreve-lo.

Onde necessário e possível, uma conversão de tipo será executada para campos com diferentes tipos na origem e no destino, por exemplo o campo `tipo` será convertido do tipo de enumeração em uma *string*.

A anotação `@InheritInverseConfiguration` permite realizar o mapeamento inverso sem a necessidade de informar novamente os pares de mapeamento.

## Links

- <https://mapstruct.org/documentation/stable/reference/html>
