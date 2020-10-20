package _stream.exemplo_stream_criacao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exemplo {

    public static void main(String[] args) throws IOException {

        /*
         * Stream de uma coleção
         */
        List<String> lista = new ArrayList<String>();
        lista.addAll(Arrays.asList("A", "B", "C"));

        Stream<String> streamLista = lista.stream();


        /*
         * Stream de um map
         */
        Map<Integer, String> map = new HashMap<Integer, String>();

        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");

        Stream<String> streamMap = map.values().stream();


        /*
         * Stream de valores, arrays e I/O
         */
        Stream<Integer> valores = Stream.of(1, 2, 3, 4, 5);

        IntStream array = Arrays.stream(new int[] {1, 2, 3, 4, 5});

        Stream <String> io = Files.lines(Paths.get("Exemplo.java"),
                Charset.defaultCharset());
    }
}