import java.util.Scanner;
public class exercicio3 {
        public static void main(String[] args) {
        Scanner leitorTeclado = new Scanner(System.in);
        double comprimento, largura, altura, volume;
            System.out.print("Informe o valor do comprimento em metros : ");
            comprimento = leitorTeclado.nextDouble();
            System.out.print("Informe o valor da largura em metros : ");
            largura = leitorTeclado.nextDouble();
            System.out.print("Informe o valor da altura em metros : ");
            altura = leitorTeclado.nextDouble();
            volume = comprimento * largura * altura;
            System.out.printf("O volume da caixa d'agua em metros cúbicos é igual a: = %.2f %n", volume);
        }
}
