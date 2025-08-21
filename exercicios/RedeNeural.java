import java.util.Random;

public class RedeNeural {

    private double[][] pesosEntradaOculta;   // Pesos entre camada de entrada e camada oculta
    private double[][] pesosOcultaSaida;     // Pesos entre camada oculta e camada de saída
    private double taxaAprendizado = 0.1;    // Taxa de aprendizado (quanto os pesos são ajustados a cada iteração)
    private Random random = new Random();

    public RedeNeural(int entradas, int ocultas, int saidas) {
        // Inicializa os pesos com valores aleatórios entre -1 e 1
        pesosEntradaOculta = new double[entradas][ocultas];
        pesosOcultaSaida = new double[ocultas][saidas];

        for (int i = 0; i < entradas; i++) {
            for (int j = 0; j < ocultas; j++) {
                pesosEntradaOculta[i][j] = (random.nextDouble() * 2 - 1);
            }
        }

        for (int i = 0; i < ocultas; i++) {
            for (int j = 0; j < saidas; j++) {
                pesosOcultaSaida[i][j] = (random.nextDouble() * 2 - 1);
            }
        }
    }

    // Função sigmoid (normaliza valores para um intervalo entre 0 e 1)
    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    // Derivada da sigmoid (usada no ajuste dos pesos)
    private double derivadaSigmoid(double x) {
        return x * (1 - x);
    }

    // Treinamento da rede neural
    public void treinar(double[][] entradas, double[][] saidasEsperadas, int epocas) {
        for (int epoca = 0; epoca < epocas; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                // Propagação para frente (forward)
                double[] camadaOculta = new double[pesosEntradaOculta[0].length];
                for (int j = 0; j < camadaOculta.length; j++) {
                    double soma = 0;
                    for (int k = 0; k < entradas[i].length; k++) {
                        soma += entradas[i][k] * pesosEntradaOculta[k][j];
                    }
                    camadaOculta[j] = sigmoid(soma);
                }

                double[] camadaSaida = new double[pesosOcultaSaida[0].length];
                for (int j = 0; j < camadaSaida.length; j++) {
                    double soma = 0;
                    for (int k = 0; k < camadaOculta.length; k++) {
                        soma += camadaOculta[k] * pesosOcultaSaida[k][j];
                    }
                    camadaSaida[j] = sigmoid(soma);
                }

                // Cálculo do erro
                double[] errosSaida = new double[saidasEsperadas[i].length];
                for (int j = 0; j < errosSaida.length; j++) {
                    errosSaida[j] = saidasEsperadas[i][j] - camadaSaida[j];
                }

                // Ajuste dos pesos (retropropagação)
                double[] deltasSaida = new double[camadaSaida.length];
                for (int j = 0; j < camadaSaida.length; j++) {
                    deltasSaida[j] = errosSaida[j] * derivadaSigmoid(camadaSaida[j]);
                }

                double[] errosOculta = new double[camadaOculta.length];
                for (int j = 0; j < camadaOculta.length; j++) {
                    double soma = 0;
                    for (int k = 0; k < deltasSaida.length; k++) {
                        soma += deltasSaida[k] * pesosOcultaSaida[j][k];
                    }
                    errosOculta[j] = soma;
                }

                double[] deltasOculta = new double[camadaOculta.length];
                for (int j = 0; j < camadaOculta.length; j++) {
                    deltasOculta[j] = errosOculta[j] * derivadaSigmoid(camadaOculta[j]);
                }

                // Atualização dos pesos (gradiente descendente)
                for (int j = 0; j < pesosOcultaSaida.length; j++) {
                    for (int k = 0; k < pesosOcultaSaida[j].length; k++) {
                        pesosOcultaSaida[j][k] += taxaAprendizado * deltasSaida[k] * camadaOculta[j];
                    }
                }

                for (int j = 0; j < pesosEntradaOculta.length; j++) {
                    for (int k = 0; k < pesosEntradaOculta[j].length; k++) {
                        pesosEntradaOculta[j][k] += taxaAprendizado * deltasOculta[k] * entradas[i][j];
                    }
                }
            }
        }
    }

    // Testa a rede para entradas fornecidas
    public double[] prever(double[] entrada) {
        double[] camadaOculta = new double[pesosEntradaOculta[0].length];
        for (int j = 0; j < camadaOculta.length; j++) {
            double soma = 0;
            for (int k = 0; k < entrada.length; k++) {
                soma += entrada[k] * pesosEntradaOculta[k][j];
            }
            camadaOculta[j] = sigmoid(soma);
        }

        double[] camadaSaida = new double[pesosOcultaSaida[0].length];
        for (int j = 0; j < camadaSaida.length; j++) {
            double soma = 0;
            for (int k = 0; k < camadaOculta.length; k++) {
                soma += camadaOculta[k] * pesosOcultaSaida[k][j];
            }
            camadaSaida[j] = sigmoid(soma);
        }

        return camadaSaida;
    }

    // Método principal
    public static void main(String[] args) {
        // Problema clássico: Porta Lógica OR
        double[][] entradas = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };

        double[][] saidasEsperadas = {
            {0},
            {1},
            {1},
            {1}
        };

        RedeNeural rede = new RedeNeural(2, 4, 1); // 2 entradas, 4 neurônios ocultos, 1 saída
        rede.treinar(entradas, saidasEsperadas, 10000);

        System.out.println("Testando a Rede Neural após o treinamento:");
        for (int i = 0; i < entradas.length; i++) {
            double[] resultado = rede.prever(entradas[i]);
            System.out.printf("Entrada: %d, %d -> Saída prevista: %.4f%n", 
                              (int)entradas[i][0], (int)entradas[i][1], resultado[0]);
        }
    }
}
