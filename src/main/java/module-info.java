module com.example.laboration3_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laboration3_3 to javafx.fxml;
    exports com.example.laboration3_3;
}