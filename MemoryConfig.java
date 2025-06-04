
public class MemoryConfig {
    public int virtualBits;
    public int physicalBits;
    public int pageBits;

    public int virtualSize;
    public int physicalSize;
    public int pageSize;

    public MemoryConfig(int virtualBits, int physicalBits, int pageBits) {
        this.virtualBits = virtualBits;
        this.physicalBits = physicalBits;
        this.pageBits = pageBits;

        this.virtualSize = (int) Math.pow(2, virtualBits);
        this.physicalSize = (int) Math.pow(2, physicalBits);
        this.pageSize = (int) Math.pow(2, pageBits);
    }

    public int getNumFrames() {
        return physicalSize / pageSize;
    }

    public int getNumPages() {
        return virtualSize / pageSize;
    }
}
