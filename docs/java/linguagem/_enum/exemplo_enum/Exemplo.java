package _enum.exemplo_enum;

enum Turno {

    MANHA, TARDE, NOITE;

    public static void main(String[] args) {

        /*
         * Enums limitam os valores que podem ser atribuídos a uma variável.
         * O "Turno." é opcional, já que se refere a própria classe.
         */
        Turno turno = Turno.MANHA;

        System.out.println(turno);
    }
}

// Saida:
// > MANHA