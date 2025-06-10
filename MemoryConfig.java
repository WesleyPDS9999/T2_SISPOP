// Classe que representa a configuração do sistema de memória
public class MemoryConfig {
    // Número de bits do espaço de endereços virtuais (ex: 8 -> 256 posições)
    public int virtualBits;

    // Número de bits da memória física (ex: 7 -> 128 posições)
    public int physicalBits;

    // Número de bits que define o tamanho da página (ex: 4 -> 16 posições por página)
    public int pageBits;

    // Tamanho total do espaço de endereços virtuais (em bytes)
    public int virtualSize;

    // Tamanho total da memória física (em bytes)
    public int physicalSize;

    // Tamanho de cada página (em bytes)
    public int pageSize;

    // Construtor: inicializa os tamanhos e realiza os cálculos
    public MemoryConfig(int virtualBits, int physicalBits, int pageBits) {
        this.virtualBits = virtualBits;
        this.physicalBits = physicalBits;
        this.pageBits = pageBits;

        this.virtualSize = (int) Math.pow(2, virtualBits);
        this.physicalSize = (int) Math.pow(2, physicalBits);
        this.pageSize = (int) Math.pow(2, pageBits);
    }

    // Retorna a quantidade de molduras disponíveis na memória física
    public int getNumFrames() {
        return physicalSize / pageSize;
    }

    // Retorna a quantidade de páginas possíveis no espaço virtual
    public int getNumPages() {
        return virtualSize / pageSize;
    }
}
