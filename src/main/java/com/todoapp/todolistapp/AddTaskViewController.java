package com.todoapp.todolistapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTaskViewController {
    @FXML
    TextField titleTextField;

    @FXML
    TextField descriptionTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addTask(ActionEvent event) throws IOException {
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();

        MainViewController mainViewController = loader.getController();
        mainViewController.addNewTask(title, description);

        root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
