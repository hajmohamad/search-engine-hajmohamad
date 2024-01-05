module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires elasticsearch.java;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports Controller;
    opens Controller to javafx.fxml;
}