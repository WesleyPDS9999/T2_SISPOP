import java.io.*;
import java.util.*;

// Classe responsável por executar a simulação
public class MemorySimulator {
    private MemoryConfig config; // Configuração da memória
    private SingleLevelPageTable pageTable; // Tabela de páginas
    private PhysicalMemory physicalMemory; // Memória física

    // Construtor: recebe a configuração e inicializa os componentes
    public MemorySimulator(MemoryConfig config) {
        this.config = config;
        this.pageTable = new SingleLevelPageTable(config.getNumPages());
        this.physicalMemory = new PhysicalMemory(config.getNumFrames());
    }

    // Executa a simulação com entrada e saída em arquivos
    public void runSimulation(String inputFile, String outputFile) throws IOException {
        List<Integer> virtualAddresses = readInput(inputFile);
        PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

        for (int vAddress : virtualAddresses) {
            int pageNumber = vAddress / config.pageSize;
            if (pageNumber >= config.getNumPages()) {
                throw new IllegalArgumentException("Tamanho do Endereço virtual: " + config.virtualSize + " bytes Foi ultrapassado!");
            }
            int offset = vAddress % config.pageSize; // Calcula o deslocamento dentro da página
            int frame = pageTable.getFrame(vAddress, config, physicalMemory); // Obtém moldura
            int physicalAddress = frame * config.pageSize + offset; // Calcula endereço físico

            writer.printf("Endereco Virtual: %d, Moldura: %d, Endereco Fisico: %d\n",
                    vAddress, frame, physicalAddress);
        }

        // Imprime o estado da tabela de páginas e da memória física
        writer.println("\nTabela de Paginas: " + pageTable);
        writer.println("Memoria Fisica: " + physicalMemory);

        writer.close();
    }

    // Lê os endereços virtuais de um arquivo e retorna em lista
    private List<Integer> readInput(String inputFile) throws IOException {
        List<Integer> addresses = new ArrayList<>();
        Scanner sc = new Scanner(new File(inputFile));
        while (sc.hasNextInt()) {
            addresses.add(sc.nextInt());
        }
        sc.close();
        return addresses;
    }
}
