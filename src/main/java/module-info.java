module org.example.parcial {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.parcial to javafx.fxml;
    exports org.example.parcial;
}