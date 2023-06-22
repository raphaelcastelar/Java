import java.util.Scanner;

public class exercicio6 {
    public static void main(String[] args) {
     Scanner leitorTeclado = new Scanner(System.in);
     int numero, ant, suc;
        System.out.print("Informe um número inteiro: ");
        numero = leitorTeclado.nextInt();
        ant = numero - 1;
        suc = numero + 1;
        System.out.println("O sucessor desse número é " + suc + " e o antecessor desse número é: " + ant);


    }
}
