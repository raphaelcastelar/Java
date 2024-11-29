package lambdas;

public class soma implements Calculo {

    @Override
    public double executar(double a, double b) {
        return a + b;
    }
    
}
