import java.util.Scanner;
public class exercicio2 {
    public static void main(String[] args) {
        double metro, centimetro;
        Scanner leitorTeclado = new Scanner(System.in);
        System.out.print("Informe a medida em metros:");
        metro = leitorTeclado.nextDouble();
        centimetro = metro * 100;
        System.out.println("O valor em centímetros ficou em: " + centimetro + " centímetros");



    }

}
