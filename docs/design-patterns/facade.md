# Facade

[TOC]

## Introdução

O padrão de projeto Facade oculta toda a complexidade de uma ou mais classes através de uma *facade* (fachada). A intenção desse padrão de projeto é simplificar uma interface, desconectando o cliente de um subsistema complexo.

O Padrão *Facade* é utilizado quando precisamos simplificar e unificar uma interface grande ou um conjunto complexo de interfaces. Um sistema pode ter diversos *Facades* simplificando diversos pontos do programa.

Com o padrão *Facade* podemos simplificar a utilização de um subsistema complexo apenas implementando uma classe que fornece uma interface única e mais razoável, porém se desejássemos acessar as funcionalidades de baixo nível do sistema isso seria perfeitamente possível.

O padrão *Facade* não "encapsula" as interfaces do sistema, apenas fornece uma interface simplificada para acessar as suas funcionalidades. Imagine que existe um sistema com diversas classes contendo diversos métodos e tenhamos que agrupar todas essas classes chamando diversos métodos para realizar uma determinada operação. Tendo uma *Facade* precisaríamos apenas construir um método que agrupe todas essas classes e chame todos esses métodos. Assim, quando usuário quiser fazer essa operação ele chamaria apenas a *Facade* que realizaria essa operação, simplificando muito todo o processo com uma simples interface. Vale ressaltar que isso não significa que uma *Facade* não tenha também funcionalidades próprias, ou seja, que tenha a sua própria inteligência e também utilize o subsistema.

<div class='imagem' markdown='1' style="width: 70%">

![exemplo_padrao_facade](_facade/exemplo_padrao_facade.png)

</div>

Abaixo um exemplo abstrato de como um cliente interage com um façade (o "computador") para um sistema complexo (as partes internas do computador como o processador e o disco rígido):

```java
class CPU {
    public void freeze() { ... }
    public void jump(long position) { ... }
    public void execute() { ... }
}

class Memory {
    public void load(long position, byte[] data) { ... }
}

class HardDrive {
    public byte[] read(long lba, int size) { ... }
}

class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        cpu.freeze();
        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }
}

class Client {
    public static void main(String[] args) {
        Computer facade = new Computer();
        facade.startComputer();
    }
}
```
