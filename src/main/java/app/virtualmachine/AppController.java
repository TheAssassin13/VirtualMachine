package app.virtualmachine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;

public class AppController {
    @FXML
    public TextField currentInstructionTextField;
    public TextArea instructionsTextArea;
    public TextField pcText;
    public TextField aRegisterText;
    public TextField dRegisterText;
    public TableView<RamEntry> ramTableView;
    private Program program;

    @FXML
    public void initialize() {
        this.program = new Program();

        this.handleTextFields();
        this.initializeRamTable();
        this.updateRamTable();
    }

    // Handles changes in the instructions input, updates the program instructions, and manages the text fields.
    public void onInstructionsInputChange(KeyEvent inputMethodEvent) {
        if (this.instructionsTextArea.getText().isEmpty()) {
            this.currentInstructionTextField.setText("");
            return;
        }

        String[] instructions = instructionsTextArea.getText().split("\n");

        this.program.setInstructions(instructions);

        this.handleTextFields();
    }

    // Advances to the next instruction, updates the RAM table, and refreshes the text fields.
    public void nextInstruction(ActionEvent actionEvent) {
        if (this.instructionsTextArea.getText().isEmpty()) return;

        this.program.nextInstruction();

        this.updateRamTable();
        this.handleTextFields();
    }

    // Executes all instructions in the program, updates the RAM table, and refreshes the text fields.
    public void runAll(ActionEvent actionEvent) {
        if (this.instructionsTextArea.getText().isEmpty()) return;

        this.program.runProgram();

        this.updateRamTable();
        this.handleTextFields();
    }

    public void reset(ActionEvent actionEvent) {
        this.program.reset();

        this.handleTextFields();
    }

    // Updates the text fields with the current instruction, program counter, and register values.
    private void handleTextFields() {
        String currentInstruction = this.program.instructions == null ? "" : this.program.instructions[program.registers.pc];

        this.currentInstructionTextField.setText(currentInstruction);
        this.pcText.setText(String.valueOf(this.program.registers.pc));
        this.aRegisterText.setText(String.valueOf(this.program.registers.a));
        this.dRegisterText.setText(String.valueOf(this.program.registers.d));
    }

    public void onPcInputChange(KeyEvent keyEvent) {
        if (this.pcText.getText().isEmpty()) return;

        this.program.setPcRegister(Integer.parseInt(pcText.getText()));

        this.handleTextFields();
    }

    public void onARegisterInputChange(KeyEvent keyEvent) {
        if (this.aRegisterText.getText().isEmpty()) return;

        this.program.setARegister(Integer.parseInt(aRegisterText.getText()));

        this.handleTextFields();

    }

    public void onDRegisterInputChange(KeyEvent keyEvent) {
        if (this.dRegisterText.getText().isEmpty()) return;

        this.program.setDRegister(Integer.parseInt(dRegisterText.getText()));

        this.handleTextFields();
    }

    // Initializes the RAM table with two editable columns, sets up the editable value column to update the RAM, and populates the table with the initial RAM data.
    private void initializeRamTable() {
        TableColumn<RamEntry, Number> indexColumn = new TableColumn<>("Index");
        TableColumn<RamEntry, String> valueColumn = new TableColumn<>("Value");

        // Configure the columns
        indexColumn.setCellValueFactory(cellData -> cellData.getValue().indexProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());

        // Make the value column editable
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setOnEditCommit(event -> {
            RamEntry entry = event.getRowValue();
            int newValue = Integer.parseInt(event.getNewValue());
            this.program.ram.write(entry.getIndex(), newValue); // Updates RAM
            updateRamTable(); // Update the model
        });

        // Add columns to the table
        ramTableView.getColumns().add(indexColumn);
        ramTableView.getColumns().add(valueColumn);

        // Create initial data for the table
        ObservableList<RamEntry> ramEntries = FXCollections.observableArrayList();
        for (int i = 0; i < this.program.ram.getSize(); i++) {
            ramEntries.add(new RamEntry(i, this.program.ram.read(i))); // Adds the memory addresses with their values
        }

        ramTableView.setItems(ramEntries);

        // Make the table editable
        ramTableView.setEditable(true);
    }

    // Updates the RAM table by refreshing the data with the current values from the RAM.
    private void updateRamTable() {
        ObservableList<RamEntry> ramEntries = FXCollections.observableArrayList();

        for (int i = 0; i < 100; i++) {
            ramEntries.add(new RamEntry(i, this.program.ram.read(i)));
        }
        ramTableView.setItems(ramEntries);
    }
}