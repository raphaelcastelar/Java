import java.util.Scanner;
public class exercicio5 {
    public static void main(String[] args) {
        Scanner leitorTeclado = new Scanner(System.in);
        double s0, v, t, s;
        System.out.print("Informe o valor do espaço inicial: ");
        s0 = leitorTeclado.nextDouble();
        System.out.print("Informe a velocidade: ");
        v = leitorTeclado.nextDouble();
        System.out.print("Informe o tempo: ");
        t = leitorTeclado.nextDouble();
        s = s0 + v * t;
        System.out.println("A posição de deslocamento foi: " + s);




    }
}
