package _string.exemplo_string_mutavel;

class Exemplo {

    public static void main(String[] args) {

        String s1 = "java";
        String s2 = "java";

        /*
         * Strings são imutáveis, quando modificadas, um novo objeto é criado
         */
        System.out.println("s1: " + System.identityHashCode(s1));
        System.out.println("s2: " + System.identityHashCode(s2));

        s2 = s2 + "!";
        System.out.println("s2: " + System.identityHashCode(s2));

        /*
         * Modificação da string sem criar novas instâncias. Entretanto, cada instância,
         * mesmo usando uma mesma string, será um novo objeto.
         */
        StringBuffer sb1 = new StringBuffer("java");
        StringBuffer sb2 = new StringBuffer("java");

        System.out.println("sb1: " + System.identityHashCode(sb1));
        System.out.println("sb2: " + System.identityHashCode(sb2));

        sb1.append("!");
        System.out.println("sb2: " + System.identityHashCode(sb2));
    }
}

// Saida
// > s1: 1497836505
// > s2: 1497836505
// > s2: 135721597
// > sb1: 142257191
// > sb2: 1044036744
// > sb2: 1044036744