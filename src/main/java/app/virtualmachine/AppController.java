package app.virtualmachine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AppController {
    @FXML
    public TextField currentInstructionTextField;
    public TextArea instructionsTextArea;
    public TextField pcText;
    public TextField aRegisterText;
    public TextField dRegisterText;
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

        handleTextFields();
    }


    public void nextInstruction(ActionEvent actionEvent) {
        if (this.instructionsTextArea.getText().isEmpty()) return;

        this.program.nextInstruction();

        handleTextFields();
    }

    public void runAll(ActionEvent actionEvent) {
        if (this.instructionsTextArea.getText().isEmpty()) return;

        this.program.runProgram();

        handleTextFields();
    }

    public void reset(ActionEvent actionEvent) {
        this.program.reset();

        this.currentInstructionTextField.setText("");
        this.pcText.setText("");
        this.aRegisterText.setText("");
        this.dRegisterText.setText("");
    }

    private void handleTextFields() {
        this.currentInstructionTextField.setText(program.instructions[program.registers.pc]);
        this.pcText.setText(String.valueOf(this.program.registers.pc));
        this.aRegisterText.setText(String.valueOf(this.program.registers.a));
        this.dRegisterText.setText(String.valueOf(this.program.registers.d));
    }

    public void onPcInputChange(KeyEvent keyEvent) {
        if (this.pcText.getText().isEmpty()) return;

        this.program.setPcRegister(Integer.parseInt(pcText.getText()));

        handleTextFields();
    }

    public void onARegisterInputChange(KeyEvent keyEvent) {
        if (this.aRegisterText.getText().isEmpty()) return;

        this.program.setARegister(Integer.parseInt(aRegisterText.getText()));

        handleTextFields();

    }

    public void onDRegisterInputChange(KeyEvent keyEvent) {
        if (this.dRegisterText.getText().isEmpty()) return;

        this.program.setDRegister(Integer.parseInt(dRegisterText.getText()));

        handleTextFields();
    }
}