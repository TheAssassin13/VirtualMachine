package app.virtualmachine;

public class Program {
    public String[] instructions;
    public Ram ram;
    public Registers registers;
    public int outM;
    public boolean running;

    public Program() {
        reset();
    }

    // Sets the instructions
    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    // Resets the RAM and registers to their initial states.
    public void reset() {
        this.ram = new Ram(Constants.RAM_SIZE);
        this.registers = new Registers();
        this.outM = 0;
        this.running = false;
    }

    // Advances to the next instruction, decodes it, and updates the program counter unless at the end of the instruction list.
    public void nextInstruction() {
        if (this.registers.pc + 1 > this.instructions.length) return;

        this.decodeInstruction(this.instructions[this.registers.pc]);

        this.registers.pc++;
    }

    // Decodes the given instruction, executing what the instruction says
    public void decodeInstruction(String instruction) {
        if (instruction.charAt(0) == '0') {
            // Instruction type A
            setARegister(Constants.binaryToDecimal(instruction.substring(1)));
        } else {
            // Instruction type C
            if (instruction.charAt(10) == '1') setARegister(this.outM);

            // ALU does the operation
            if (instruction.charAt(3) == '1') alu(registers.d, ram.read(this.registers.a), instruction.substring(4, 10));
            else alu(this.registers.d, this.registers.a, instruction.substring(4, 10));

            // Sets the ALU's output to the register D
            if (instruction.charAt(11) == '1') this.registers.d = this.outM;

            // Writes the ALU's output in the corresponding RAM address
            if (instruction.charAt(12) == '1') this.ram.write(this.registers.a, this.outM);

            if ((instruction.charAt(13) == '1' && this.outM < 0)
                || (instruction.charAt(14) == '1' && this.outM == 0)
                || (instruction.charAt(15) == '1' && this.outM > 0)) {
                // Jump
                setPcRegister(this.registers.a - 1);
            }
        }
    }

    // Sets the given number to the register A
    public void setARegister(int data) {
        this.registers.a = data;
    }

    // Sets the given number to the register D
    public void setDRegister(int data) {
        this.registers.d = data;
    }

    // Sets the given number to the PC
    public void setPcRegister(int data) {
        if (data >= this.instructions.length) return;

        this.registers.pc = data;
    }

    // Does the corresponding operation with the given values
    private void alu(int x, int y, String operation) {
        this.outM = switch (operation) {
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
    }
}
