package app.virtualmachine;

public class Program {
    public String[] instructions;
    public Registers registers;
    public Ram ram;

    public Program() {
        this.ram = new Ram(16384);
        this.registers = new Registers();
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public void reset() {

    }

    public void runProgram() {
        for (String instruction : instructions) {
            this.decodeInstruction(instruction);

            this.registers.pc++;
        }
    }

    public void nextInstruction() {
        if (this.registers.pc + 1 >= this.instructions.length) return;

        this.decodeInstruction(this.instructions[this.registers.pc]);

        this.registers.pc++;
    }

    public void decodeInstruction(String instruction) {

    }

    private void setARegister(int data) {

    }

    private void setDRegister(int data) {

    }

    private void setPcRegister(int data) {

    }

    private void writeRam(String address, int data) {

    }

    private int[] alu(int a, int b, int operation) {
        int[] result = new int[3];

        return result;
    }
}
