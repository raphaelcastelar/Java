import java.util.Scanner;
public class exercicio7 {
    public static void main(String[] args) {
        Scanner leitorTeclado = new Scanner(System.in);
         double nota1, nota2, nota3, peso1, peso2, peso3, mp;
        System.out.print("Informe a nota 1: ");
        nota1 = leitorTeclado.nextDouble();
        System.out.print("Informe o peso da nota 1: ");
        peso1 = leitorTeclado.nextDouble();
        System.out.print("Informe a nota 2: ");
        nota2 = leitorTeclado.nextDouble();
        System.out.print("Informe o peso da nota 2: ");
        peso2 = leitorTeclado.nextDouble();
        System.out.print("Informe a nota 3: ");
        nota3 = leitorTeclado.nextDouble();
        System.out.print("Informe o peso da nota 3: ");
        peso3 = leitorTeclado.nextDouble();
        mp = ((nota1 * peso1) + (nota2 * peso2) + (nota3 * peso3)) / (peso1 + peso2 + peso3);
        System.out.println("O valor da média ponderada é igual a: " + mp);

    }
}
