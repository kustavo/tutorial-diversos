package _string.exemplo_string;

class Exemplo {

    public static void main(String[] args) {

        char[] ch = {'j','a','v','a'};

        System.out.println(ch);

        String s = new String(ch);

        /*
         * Outras opções
         * String s = new String("java");
         * String s = "java";
         */

        System.out.println(s);
    }
}

// Saida
// > java
// > java