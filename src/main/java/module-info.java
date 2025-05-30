module com.todoapp.todolistapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.todoapp.todolistapp to javafx.fxml;
    exports com.todoapp.todolistapp;
}