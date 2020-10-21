package _enum.exemplo_enum_heranca_metodos;

enum Turno {

    MANHA(1, "manhã"),
    TARDE(2, "tarde"),
    NOITE(3, "noite");

    private int id;
    private String descricao;


    Turno(int id, String descricao) {

        this.id = id;
        this.descricao = descricao;
    }

    public static void main(String[] args) {

        for (Turno t : Turno.values()) {

            System.out.println(t.ordinal() + " " + t.name() + " "
                    + t.descricao);
        }

        Turno v = Turno.MANHA;
        System.out.println(v.descricao);

        /*
         * Mesma forma, mas passando como argumento
         */
        Turno s = Turno.valueOf("MANHA");
        System.out.println(s.descricao);
    }
}

// Saida:
// > 0 MANHA manhã
// > 1 TARDE tarde
// > 2 NOITE noite
// > manhã
// > manhã