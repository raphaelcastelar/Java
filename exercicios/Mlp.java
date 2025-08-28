import java.util.Random;

public class Mlp {
    private int tamanhoEntrada = 16;   // 16 pixels
    private int tamanhoOculta = 2;     // 2 neurônios ocultos
    private int tamanhoSaida = 2;      
    private double[][] pesosEntradaOculta; 
    private double[][] pesosOcultaSaida;
    private double taxaAprendizado = 0.5;
    private Random aleatorio = new Random();

    public Mlp() {
        // inicializa pesos com valores aleatórios pequenos
        pesosEntradaOculta = new double[tamanhoEntrada + 1][tamanhoOculta]; // +1 para bias
        pesosOcultaSaida = new double[tamanhoOculta + 1][tamanhoSaida];     // +1 para bias
        inicializarPesos(pesosEntradaOculta);
        inicializarPesos(pesosOcultaSaida);
    }

    private void inicializarPesos(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz[0].length; j++)
                matriz[i][j] = (aleatorio.nextDouble() - 0.5);
    }

    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
    //gradiente de erro
    private double derivadaSigmoid(double y) {
        return y * (1 - y);
    }

    private double[] calcularSaida(double[] entrada) {
        double[] camadaOculta = new double[tamanhoOculta];
        for (int j = 0; j < tamanhoOculta; j++) {
            double soma = pesosEntradaOculta[tamanhoEntrada][j]; // bias
            for (int i = 0; i < tamanhoEntrada; i++)
                soma += entrada[i] * pesosEntradaOculta[i][j];
            camadaOculta[j] = sigmoid(soma);
        }

        double[] saida = new double[tamanhoSaida];
        for (int k = 0; k < tamanhoSaida; k++) {
            double soma = pesosOcultaSaida[tamanhoOculta][k]; // bias
            for (int j = 0; j < tamanhoOculta; j++)
                soma += camadaOculta[j] * pesosOcultaSaida[j][k];
            saida[k] = sigmoid(soma);
        }
        return saida;
    }

    public void treinar(double[][] entradas, double[][] desejado, int epocas) {
        for (int epoca = 0; epoca < epocas; epoca++) {
            double erroTotal = 0.0;
            for (int n = 0; n < entradas.length; n++) {
                double[] entrada = entradas[n];
                double[] alvo = desejado[n];

                // Forward
                double[] camadaOculta = new double[tamanhoOculta];
                for (int j = 0; j < tamanhoOculta; j++) {
                    double soma = pesosEntradaOculta[tamanhoEntrada][j];
                    for (int i = 0; i < tamanhoEntrada; i++)
                        soma += entrada[i] * pesosEntradaOculta[i][j];
                    camadaOculta[j] = sigmoid(soma);
                }

                double[] saida = new double[tamanhoSaida];
                for (int k = 0; k < tamanhoSaida; k++) {
                    double soma = pesosOcultaSaida[tamanhoOculta][k];
                    for (int j = 0; j < tamanhoOculta; j++)
                        soma += camadaOculta[j] * pesosOcultaSaida[j][k];
                    saida[k] = sigmoid(soma);
                }

                // Erro na saída
                double[] erroSaida = new double[tamanhoSaida];
                for (int k = 0; k < tamanhoSaida; k++) {
                    erroSaida[k] = (alvo[k] - saida[k]) * derivadaSigmoid(saida[k]);
                    // Acumula o erro quadrático para esta saída
                    erroTotal += 0.5 * Math.pow(alvo[k] - saida[k], 2); // Erro quadrático por neurônio
                }

                // Erro na camada oculta
                double[] erroOculta = new double[tamanhoOculta];
                for (int j = 0; j < tamanhoOculta; j++) {
                    double soma = 0;
                    for (int k = 0; k < tamanhoSaida; k++)
                        soma += erroSaida[k] * pesosOcultaSaida[j][k];
                    erroOculta[j] = soma * derivadaSigmoid(camadaOculta[j]);
                }

                // Atualizar pesos Oculta -> Saída
                for (int j = 0; j < tamanhoOculta; j++)
                    for (int k = 0; k < tamanhoSaida; k++)
                        pesosOcultaSaida[j][k] += taxaAprendizado * erroSaida[k] * camadaOculta[j];
                for (int k = 0; k < tamanhoSaida; k++)
                    pesosOcultaSaida[tamanhoOculta][k] += taxaAprendizado * erroSaida[k]; // bias

                // Atualizar pesos Entrada -> Oculta
                for (int i = 0; i < tamanhoEntrada; i++)
                    for (int j = 0; j < tamanhoOculta; j++)
                        pesosEntradaOculta[i][j] += taxaAprendizado * erroOculta[j] * entrada[i];
                for (int j = 0; j < tamanhoOculta; j++)
                    pesosEntradaOculta[tamanhoEntrada][j] += taxaAprendizado * erroOculta[j]; // bias
            }
            // Exibe o erro total por época
            if (epoca % 100 == 0 || epoca == epocas - 1) {
                System.out.println("Época " + epoca + ": Erro Total = " + erroTotal / entradas.length);
            }
        }
    }

    public int prever(double[] entrada) {
        double[] saida = calcularSaida(entrada);
        return (saida[0] > saida[1]) ? 0 : 1;
    }

    public static void imprimirMatriz(double[] entrada) {
        for (int i = 0; i < 16; i++) {
            System.out.print((entrada[i] == 1 ? "█" : " ") + " ");
            if ((i + 1) % 4 == 0) System.out.println();
        }
    }

    public static void main(String[] args) {
        Mlp rede = new Mlp();

        // Exemplos de treino (3 exemplos de cada dígito)
        double[][] entradas = {
            // Zero (0)
            {1,1,1,1, 1,0,0,1, 1,0,0,1, 1,1,1,1}, 
            {1,1,1,1, 1,0,1,1, 1,0,0,1, 1,1,1,1}, 
            {1,1,1,1, 1,0,0,1, 1,1,0,1, 1,1,1,1}, 
            // Um (1)
            {0,1,0,0, 1,1,0,0, 0,1,0,0, 1,1,1,0}, 
            {0,1,0,0, 0,1,0,0, 0,1,0,0, 0,1,0,0}, 
            {0,1,1,0, 0,0,1,0, 0,0,1,0, 0,1,1,0}  
        };

        double[][] desejado = {
            {1,0}, {1,0}, {1,0}, // "0"
            {0,1}, {0,1}, {0,1}  // "1"
        };

        // Treinamento
        rede.treinar(entradas, desejado, 5000);

        // Teste com exemplos novos
        double[] teste0 = {1,0,1,1, 1,0,0,1, 1,0,0,1, 1,1,0,1};
        

        System.out.println("\nTeste com número 0:");
        imprimirMatriz(teste0);
        System.out.println("Rede reconheceu como: " + rede.prever(teste0));

        //System.out.println("\nTeste com número 1:");
        //imprimirMatriz(teste1);
        //System.out.println("Rede reconheceu como: " + rede.prever(teste1));
    }
}