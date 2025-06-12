
public class Main {
    public static void main(String[] args) {
        try {
            // EX 1 (ALTERAR ARQUIVO DE LEITURA - enderecos1.txt):
            MemoryConfig config = new MemoryConfig(8, 7, 4, 4, 4, 4);
            System.out.println(config);

            //EX 2 (ALTERAR ARQUIVO DE LEITURA - enderecos2.txt):
            // MemoryConfig config = new MemoryConfig(16, 15, 12);

            MemorySimulator simulator = new MemorySimulator(config);

            String inputFile = "enderecos1.txt";
            String outputFile = "saida.txt";

            simulator.runSimulation(inputFile, outputFile);

            System.out.println("Simulação concluída. Veja o arquivo de saída.");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
        
    }
}
