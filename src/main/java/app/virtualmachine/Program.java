package app.virtualmachine;

public class Program {
    public String[] instructions;
    public String currentInstruction;

    public Program(String[] instructions) {
        this.instructions = instructions;
        this.currentInstruction = instructions[0];
    }
    public void runProgram() {

    }
    public void nextInstruction() {

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
