
import java.util.Arrays;

public class PhysicalMemory {
    private int[] frames;
    private int nextFree = 0;

    public PhysicalMemory(int numFrames) {
        frames = new int[numFrames];
        Arrays.fill(frames, -1);
    }

    public int allocateFrame(int pageNumber) throws RuntimeException {
        if (nextFree >= frames.length) {
            throw new RuntimeException("Memoria fisica lotada!");
        }
        frames[nextFree] = pageNumber;
        return nextFree++;
    }

    public String toString() {
        return Arrays.toString(frames);
    }
}
