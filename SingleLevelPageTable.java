
import java.util.Arrays;

public class SingleLevelPageTable {
    private int[] table;

    public SingleLevelPageTable(int numPages) {
        table = new int[numPages];
        Arrays.fill(table, -1);
    }

    public int getFrame(int virtualAddress, MemoryConfig config, PhysicalMemory pm) throws RuntimeException {
        int pageNumber = virtualAddress / config.pageSize;

        if (table[pageNumber] == -1) {
            int frame = pm.allocateFrame(pageNumber);
            table[pageNumber] = frame;
            return frame;
        } else {
            return table[pageNumber];
        }
    }

    public String toString() {
        return Arrays.toString(table);
    }
}
