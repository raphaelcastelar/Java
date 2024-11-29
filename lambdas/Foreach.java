package lambdas;

import java.util.Arrays;
import java.util.List;

public class Foreach {
    public static void main(String[] args) {
        List<String> aprovados = Arrays.asList("Ana", "Bia", "Lia");

        System.out.println("Metodo Tradicional");
        for (String nome: aprovados){
            System.out.println(nome);
        } 

        System.out.println("\nLamba #1");
        aprovados.forEach(nome -> System.out.println(nome + "!!!"));

        System.out.println("\nMethod reference");
        aprovados.forEach(System.out::println);
    }
    
    
}


