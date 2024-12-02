package app.virtualmachine;

public class Ram {
    private int[] data;

    public Ram(int size) {
        this.data = new int[size];
    }

    public void write(int address, int data) {
        this.data[address] = data;
    }

    public int read(int address) {
        return this.data[address];
    }

    public int getSize() {
        return this.data.length;
    }
}
