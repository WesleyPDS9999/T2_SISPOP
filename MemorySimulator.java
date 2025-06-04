
import java.io.*;
import java.util.*;

public class MemorySimulator {
    private MemoryConfig config;
    private SingleLevelPageTable pageTable;
    private PhysicalMemory physicalMemory;

    public MemorySimulator(MemoryConfig config) {
        this.config = config;
        this.pageTable = new SingleLevelPageTable(config.getNumPages());
        this.physicalMemory = new PhysicalMemory(config.getNumFrames());
    }

    public void runSimulation(String inputFile, String outputFile) throws IOException {
        List<Integer> virtualAddresses = readInput(inputFile);
        PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

        for (int vAddress : virtualAddresses) {
            int offset = vAddress % config.pageSize;

            int frame = pageTable.getFrame(vAddress, config, physicalMemory);

            int physicalAddress = frame * config.pageSize + offset;

            writer.printf("Endereco Virtual: %d, Moldura: %d, Endereco Fisico: %d\n",
                    vAddress, frame, physicalAddress);
        }

        writer.println("\nTabela de Paginas: " + pageTable);
        writer.println("Memoria Fisica: " + physicalMemory);

        writer.close();
    }

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
