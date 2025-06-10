import java.util.Arrays;

// Representa uma tabela de páginas de 1 nível
public class SingleLevelPageTable {
    // Vetor onde cada índice representa uma página virtual
    // O valor armazenado representa a moldura física mapeada
    private int[] table;

    // Construtor: inicializa a tabela com -1 em todas as posições (sem mapeamento)
    public SingleLevelPageTable(int numPages) {
        table = new int[numPages];
        Arrays.fill(table, -1);
    }

    // Retorna a moldura física correspondente ao endereço virtual
    // Se a página ainda não estiver mapeada, aloca uma moldura
    public int getFrame(int virtualAddress, MemoryConfig config, PhysicalMemory pm) throws RuntimeException {
        int pageNumber = virtualAddress / config.pageSize; // Descobre o número da página

        // Se não estiver mapeada, aloca
        if (table[pageNumber] == -1) {
            int frame = pm.allocateFrame(pageNumber);
            table[pageNumber] = frame;
            return frame;
        } else {
            return table[pageNumber]; // Já está mapeada
        }
    }

    // Exibe a tabela de páginas como uma string
    public String toString() {
        return Arrays.toString(table);
    }
}
