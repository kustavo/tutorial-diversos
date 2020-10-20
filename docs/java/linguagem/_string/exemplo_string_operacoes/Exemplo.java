package _string.exemplo_string_operacoes;

class Exemplo {

    public static void main(String[] args) {

        String s0;
        String s1;
        StringBuilder sb0;
        StringBuilder sb1;

        /*
         * Concatenação
         */
        s0 = "A";
        s0 += "B";

        s0 = "A";
        s1 = "B";
        s0 = s0 + s1;

        sb0 = new StringBuilder("A");
        sb0.append("B");

        sb0 = new StringBuilder("A");
        sb1 = new StringBuilder("B");
        sb0.append(sb1);

        /*
         * Busca
         */
        s0 = "ABCDE";
        s0.indexOf("CD");

        sb0 = new StringBuilder("ABCDE");
        sb0.indexOf("CD");

        /*
         * Substituição
         */
        s0 = "AXC";
        s0 = s0.replace("X", "B");

        sb0 = new StringBuilder("AXC");
        sb0.replace(sb0.indexOf("X"), sb0.indexOf("X") + 1, "B");

        /*
         * Tamanho
         */
        s0 = "ABCDE";
        s0.length();

        sb0 = new StringBuilder("ABCDE");
        sb0.length();

        /*
         * Substring
         */
        s0 = "ABCDE";
        s0 = s0.substring(1, 3);

        sb0 = new StringBuilder("ABCDE");
        sb0 = new StringBuilder(sb0.substring(1, 3));

        /*
         * Remoção
         */
        s0 = "ABCDE";
        s0 = s0.replaceFirst("CD", "");

        sb0 = new StringBuilder("ABCDE");
        sb0.delete(sb0.indexOf("CD"), sb0.indexOf("CD") + "CD".length());

        /*
         * Comparação
         */
        s0 = "ABCDE";
        s0.compareTo("ABCDE");

        sb0 = new StringBuilder("ABCDE");
        sb0.compareTo(new StringBuilder("ABCDE"));
    }
}