# String

[TOC]

## Introdução

Em Java, *string* é basicamente um objeto que representa uma sequência de valores do tipo `char`. Uma matriz de caracteres funciona da mesma forma que *string* em Java. Por exemplo:

```java
package _string.exemplo_string;

class Exemplo {

    public static void main(String[] args) {

        char[] ch = {'j','a','v','a'};

        System.out.println(ch);

        String s = new String(ch);

        /*
         * Outras opções:
         * String s = new String("java");
         * String s = "java";
         */

        System.out.println(s);
    }
}

// Saida
// > java
// > java
```

## Strings mutáveis

A interface `CharSequence` é usada para representar a sequência de caracteres. `String`, `StringBuffer` e `StringBuilder` as implementam. Isso significa que podemos criar *strings* em java usando essas três classes.

*Strings* em Java são **imutáveis**, quando uma *string* é alterada, um novo objeto é criado. Toda vez que uma *string* é criada, a `JVM` verifica primeiro o "conjunto constante de *strings*" (em inglês, *string constant pool*). Se a *string* já existir, a referência à instância será retornada. Caso contrário, uma nova instância de *string* será criada e colocada no conjunto.

Para criar *strings* **mutáveis**, podemos usar as classes `StringBuffer` e `StringBuilder`.

Durante a execução de concatenações, a performance de `StringBuffer` ou  `StringBuilder` é melhor que de `String`, uma vez que não são criadas novas instâncias a cada concatenação.

A diferença entre `StringBuffer` e `StringBuilder` está no fato que `StringBuffer` é **sincronizado** enquanto que `StringBuilder` é **não sincronizado**. Dessa forma, `StringBuilder` possui performance superior ao `StringBuffer` por não ter custo de performance da sincronização.

```java
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
```

*Benchmark* das classes que implementam `CharSequence`:

```java
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
```

## Operações

Exemplos de operações com *strings*:

```java
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
```
