import java.util.Scanner;

public class exercicio8 {
    public static void main(String[] args) {
        Scanner leitorTeclado = new Scanner(System.in);
        double peso, altura, imc;
        System.out.print("Informe o peso do usuário: ");
        peso = leitorTeclado.nextDouble();
        System.out.print("Informe a altura do usuário: ");
        altura = leitorTeclado.nextDouble();
        imc = peso / (altura * altura);
        System.out.printf("imc: = %.2f %n", imc);
    }
}
