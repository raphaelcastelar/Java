
import java.util.Scanner;
public class exercicio1 {

    public static void main (String [] args){
        double celsius, fahrenheit, kelvin;

        Scanner leitorTeclado = new Scanner(System.in);
        System.out.println("Conversor de temperatura");
        System.out.print("Informe o valor em Celsius:");
        celsius = leitorTeclado.nextDouble();
        fahrenheit = (9 * celsius + 160) / 5;
        kelvin = celsius + 273;
        System.out.println("O valor em fahrenheit é igual a: " + fahrenheit);
        System.out.println("O valor em kelvin é igual a: " + kelvin);
    }


}
