# Struct

É possível programar orientado a objetos, mas não da forma mais comum, pois Go não utiliza classes e sim **estruturas**. Na orientação a objetos, são criados métodos sem classes, interface sem hierarquia, e reutilização de código sem herança (apenas composição).

```go
type Pessoa struct {
    Nome string
    Idade int
}
```

Exemplo do uso de composição:

```go
package main

import "fmt"

type Animal struct{
    
}

func (a Animal) Comer() {
    fmt.Println("Comendo")
}

type MembroFamilia struct{
    
}

func (mf MembroFamilia) Nome() {
    fmt.Println("Meu nome não é Johnny")
}

type Cachorro struct {
    Animal        // Struct incorporada
    MembroFamilia // Struct incorporada
}

func main() {
    d := Cachorro{}
    d.Comer() // "Comendo"
    d.Nome()  // "Meu nome não é Johnny"
}
```

## Referências

- <https://pt.wikipedia.org/wiki/Go_(linguagem_de_programa%C3%A7%C3%A3o)>
