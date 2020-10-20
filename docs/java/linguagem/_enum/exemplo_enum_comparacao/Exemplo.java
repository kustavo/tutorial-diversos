package _enum.exemplo_enum_comparacao;

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

    public static void comparaEnum(Turno opcao){

        if (opcao.equals(Turno.MANHA)) {

            System.out.println("Turno matutino");

        } else if (opcao.equals(Turno.TARDE)) {

            System.out.println("Turno vespertino");

        } else if (opcao.equals(Turno.NOITE)){

            System.out.println("Turno noturno");
        }
    }

    public static void main(String[] args) {

        comparaEnum(Turno.TARDE);
    }
}

// Saida
// > Turno vespertino