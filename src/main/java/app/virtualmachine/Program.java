package app.virtualmachine;

public class Program {
    public String[] instructions;
    public Ram ram;
    public Registers registers;

    public Program() {
        reset();
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public void reset() {
        this.ram = new Ram(Constants.RAM_SIZE);
        this.registers = new Registers();
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
        this.registers.a = data;
    }

    private void setDRegister(int data) {
        this.registers.d = data;
    }

    private void setPcRegister(int data) {
        this.registers.pc = data;
    }

    private void writeRam(String address, int data) {
        int decimalAddress = Constants.binaryToDecimal(address);
        this.ram.write(decimalAddress, data);
    }
    private int[] alu(int x, int y, String operation) {
        int[] result = new int[3];
        result[0] = switch (operation) {
            case "101010" -> 0;
            case "111111" -> 1;
            case "111010" -> -1;
            case "001100" -> x;
            case "110000" -> y;
            case "001101" -> Constants.negateDecimal(x);
            case "110001" -> Constants.negateDecimal(y);
            case "001111" -> -x;
            case "110011" -> -y;
            case "011111" -> x+1;
            case "110111" -> y+1;
            case "001110" -> x-1;
            case "110010" -> y-1;
            case "000010" -> x+y;
            case "010011" -> x-y;
            case "000111" -> y-x;
            case "000000" -> x&y;
            case "010101" -> x|y;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
        return result;
    }
}
