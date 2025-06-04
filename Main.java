
public class Main {
    public static void main(String[] args) {
        try {
            MemoryConfig config = new MemoryConfig(8, 8, 4);

            MemorySimulator simulator = new MemorySimulator(config);

            String inputFile = "enderecos.txt";
            String outputFile = "saida.txt";

            simulator.runSimulation(inputFile, outputFile);

            System.out.println("Simulação concluída. Veja o arquivo de saída.");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
