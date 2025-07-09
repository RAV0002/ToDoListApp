package com.todoapp.todolistapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ToDoList by RAV0002");
        stage.setScene(scene);
        stage.setMinHeight(706);
        stage.setMinWidth(965);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}