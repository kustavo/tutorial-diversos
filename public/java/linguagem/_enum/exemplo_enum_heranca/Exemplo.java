package _enum.exemplo_enum_heranca;

enum Turno {

    MANHA(1, "manhã"),
    TARDE(2, "tarde"),
    NOITE(3, "noite");

    private int id;
    private String descricao;


    /*
     * Construtor, sempre possui restrição private
     */
    Turno(int id, String descricao) {

        this.id = id;
        this.descricao = descricao;
    }

    String getDescricao() {

        return descricao;
    }

    /*
     * Modificar as propriedades de um enum deve ser EVITADA, vai contra a
     * sua característica imutável.
     */
    void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public static void main(String[] args) {

        System.out.println(Turno.MANHA);
        System.out.println(Turno.MANHA.id + "-" + Turno.MANHA.getDescricao());

        /*
         * Permitido mas deve ser evitado
         */
        Turno.MANHA.setDescricao("desconhecido");

        System.out.println(Turno.MANHA.id + "-" + Turno.MANHA.getDescricao());
    }
}

// Saida:
// > MANHA
// > 1-manhã
// > 1-desconhecido