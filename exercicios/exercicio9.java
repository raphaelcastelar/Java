import java.util.Scanner;

public class exercicio9 {
    public static void main(String[] args) {
        Scanner leitorTeclado = new Scanner(System.in);
        double salario, reajuste, newsalario;
        System.out.print("Informe o salário atual: ");
        salario = leitorTeclado.nextDouble();
        System.out.print("Qual a porcentagem do reajuste: ");
        reajuste = leitorTeclado.nextDouble();
        newsalario = salario + (salario * (reajuste / 100));
        System.out.println("O novo salário é de: " + newsalario);


    }
}
