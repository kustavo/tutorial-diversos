package _modificadores.exemplo_acesso_campos.pacote1;

public class Classe1 {

    private String privateC1;
    String defaultC1;
    protected String protectedC1;
    public String publicC1;

    public Classe1() {

        this.privateC1 = "private";
        this.defaultC1 = "default";
        this.protectedC1 = "protected";
        this.publicC1 = "public";
    }

    public void fncPublicC1() {

        System.out.println("\nC1 Private: " + this.privateC1);
        System.out.println("C1 Default: " + this.defaultC1);
        System.out.println("C1 Protected: " + this.protectedC1);
        System.out.println("C1 Public: " + this.publicC1);
    }
}