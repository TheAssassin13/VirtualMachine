package app.virtualmachine;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RamEntry {
    private final SimpleIntegerProperty index;
    private final SimpleStringProperty value;

    public RamEntry(int index, int value) {
        this.index = new SimpleIntegerProperty(index);
        this.value = new SimpleStringProperty(String.valueOf(value));
    }

    public int getIndex() {
        return index.get();
    }

    public SimpleIntegerProperty indexProperty() {
        return index;
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(String.valueOf(value));
    }
}
