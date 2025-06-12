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

    // Tamanho do segmento de código
    public int textSize; 

    // Tamanho do segmento de dados
    public int dataSize; 

    // Tamanho do segmento de pilha
    public int stackSize; 

    // Tamanho do segmento BSS 
    public int bssSize; 

    // Tamanhos dos segmentos em bits
    public int textBits;

    // Tamanho do segmento de dados em bits 
    public int dataBits;

    // Tamanho do segmento de pilha em bits
    public int stackBits;

    // Construtor: inicializa os tamanhos e realiza os cálculos
    public MemoryConfig(int virtualBits, int physicalBits, int pageBits, int textBits, int dataBits, int stackBits) {
        this.virtualBits = virtualBits;
        this.physicalBits = physicalBits;
        this.pageBits = pageBits;

        this.virtualSize = (int) Math.pow(2, virtualBits);
        this.physicalSize = (int) Math.pow(2, physicalBits);
        this.pageSize = (int) Math.pow(2, pageBits);

        // Guardar bits dos segmentos
        this.textBits = textBits;
        this.dataBits = dataBits;
        this.stackBits = stackBits;

        // Calcular os tamanhos em bytes
        this.textSize = (int) Math.pow(2, textBits);
        this.dataSize = (int) Math.pow(2, dataBits);
        this.stackSize = (int) Math.pow(2, stackBits);

        int used = textSize + dataSize + stackSize;

        // Calcula o bss como 3 * usado, mas limitando ao que sobra
        this.bssSize = Math.min(3 * used, virtualSize - used);
    }

    // Retorna a quantidade de molduras disponíveis na memória física
    public int getNumFrames() {
        return physicalSize / pageSize;
    }

    // Retorna a quantidade de páginas possíveis no espaço virtual
    public int getNumPages() {
        return virtualSize / pageSize;
    }

    // Retorna o segmento correspondente a um endereço virtual
    public String getSegment(int virtualAddress) {
        if (virtualAddress < textSize)
            return ".text";
        else if (virtualAddress < textSize + dataSize)
            return ".data";
        else if (virtualAddress < textSize + dataSize + stackSize)
            return ".stack";
        else
            return ".bss";
    }

    @Override
    public String toString() {
    return String.format(
        "Configuração de Memória:\n" +
        "- Espaço de endereços virtuais: %d bits (%d bytes)\n" +
        "- Memória física: %d bits (%d bytes)\n" +
        "- Tamanho da página: %d bits (%d bytes)\n" +
        "- Segmento .text: %d bits (%d bytes)\n" +
        "- Segmento .data: %d bits (%d bytes)\n" +
        "- Segmento .stack: %d bits (%d bytes)\n" +
        "- Segmento .bss: calculado (%d bytes)\n",
        virtualBits, virtualSize,
        physicalBits, physicalSize,
        pageBits, pageSize,
        textBits, textSize,
        dataBits, dataSize,
        stackBits, stackSize,
        bssSize
    );
}

    
}
