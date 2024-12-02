module app.virtualmachine {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.virtualmachine to javafx.fxml;
    exports app.virtualmachine;
}