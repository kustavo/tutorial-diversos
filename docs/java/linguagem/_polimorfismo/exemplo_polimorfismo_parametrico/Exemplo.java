package _polimorfismo.exemplo_polimorfismo_parametrico;

public class Exemplo  {

    /*
     * Método genérico
     */
    public static <E> void printArray( E[] inputArray )  {

        for (E element : inputArray) {

            System.out.printf("%s ", element);
        }
    }

    public static void main( String args[] )  {

        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};

        System.out.print( "Array integer: " );
        printArray( intArray  );

        System.out.print( "\nArray double: " );
        printArray( doubleArray );

        System.out.print( "\nArray character: " );
        printArray( charArray );
    }
}

// Saida
// > Array integer: 1 2 3 4 5
// > Array double: 1.1 2.2 3.3 4.4
// > Array character: H E L L O