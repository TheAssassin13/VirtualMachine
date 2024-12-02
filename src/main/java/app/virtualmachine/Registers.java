package app.virtualmachine;

public class Registers {
    private int pc;
    public int a;
    public int d;
    public Registers() {
        this.pc = 0;
        this.a = 0;
        this.d = 0;
    }

    public int getPc() {
        int pc = this.pc;
        this.pc++;
        return pc;
    }
    public void setPc(int pc) {
        this.pc = pc;
    }
}
