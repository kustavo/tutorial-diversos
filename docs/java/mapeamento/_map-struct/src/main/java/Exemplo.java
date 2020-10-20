import lombok.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public class Exemplo {

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