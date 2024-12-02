package app.virtualmachine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AppController {
    @FXML
    public TextField currentInstructionTextField;
    public TextArea instructionsTextArea;
    public Button nextInstructionBtn;
    private Program program;

    @FXML
    public void initialize() {
        this.program = new Program();
    }

    public void onInstructionsInputChange(KeyEvent inputMethodEvent) {
        if (this.instructionsTextArea.getText().isEmpty()) {
            this.currentInstructionTextField.setText("");
            return;
        }

        String[] instructions = instructionsTextArea.getText().split("\n");

        this.program.setInstructions(instructions);

        this.currentInstructionTextField.setText(instructions[program.registers.pc]);
    }


    public void nextInstruction(ActionEvent actionEvent) {
        this.program.nextInstruction();

        this.currentInstructionTextField.setText(program.instructions[program.registers.pc]);
    }
}