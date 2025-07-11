package com.todoapp.todolistapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTaskViewController {
    @FXML
    TextField titleTextField;

    @FXML
    TextArea descriptionTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void saveTask(ActionEvent event) throws IOException {
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();

        MainViewController.addNewTask(title,description);

        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void exitButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
