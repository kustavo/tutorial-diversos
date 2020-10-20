package _string.exemplo_string_mutavel_benchmark;

class Exemplo {

    public static void main(String[] args) {

        int N = 1000000;
        long t;

        /*
         * String
         */
        String str = "";
        t = System.currentTimeMillis();
        for (int i = N; i-- > 0 ;) {
            str += "!";
        }
        System.out.println(System.currentTimeMillis() - t);

        /*
         * StringBuffer
         */
        StringBuffer sbf = new StringBuffer();
        t = System.currentTimeMillis();
        for (int i = N; i-- > 0 ;) {
            sbf.append("!");
        }
        System.out.println(System.currentTimeMillis() - t);

        /*
         * StringBuilder
         */
        StringBuilder sbb = new StringBuilder();
        t = System.currentTimeMillis();
        for (int i = N; i-- > 0 ;) {
            sbb.append("!");
        }
        System.out.println(System.currentTimeMillis() - t);
    }
}

// Saida
// > 100226
// > 18
// > 14