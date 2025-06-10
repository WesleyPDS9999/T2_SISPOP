import java.util.Arrays;

// Representa a memória física como um vetor de molduras
public class PhysicalMemory {
    // Vetor que representa as molduras físicas
    private int[] frames;

    // Índice da próxima moldura livre a ser usada
    private int nextFree = 0;

    // Construtor: inicializa a memória com molduras livres (-1)
    public PhysicalMemory(int numFrames) {
        frames = new int[numFrames];
        Arrays.fill(frames, -1);
    }

    // Aloca uma moldura para uma página, se houver disponível
    public int allocateFrame(int pageNumber) throws RuntimeException {
        if (nextFree >= frames.length) {
            throw new RuntimeException("Memoria fisica lotada!"); // Simula falha por falta de espaço
        }
        frames[nextFree] = pageNumber; // Associa a moldura à página
        return nextFree++; // Retorna moldura e avança para a próxima
    }

    // Retorna o estado da memória física como string
    public String toString() {
        return Arrays.toString(frames);
    }
}
