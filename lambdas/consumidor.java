package lambdas;

import java.util.function.Consumer;

public class consumidor {
    public static void main(String[] args) {
        Consumer<Produto> imprimir = p ->System.out.println(p.nome);
    }
    
}
