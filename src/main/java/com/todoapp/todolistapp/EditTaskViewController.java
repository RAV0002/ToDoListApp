package com.todoapp.todolistapp;

import Data.AppData;
import Data.Task;
import com.sun.tools.javac.Main;
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

public class EditTaskViewController {
    @FXML
    TextField titleTextField;

    @FXML
    TextArea descriptionTextField;

    private Task taskToEdit;

    public void setTask(Task task) {
        this.taskToEdit = task;
        titleTextField.setText(task.getTitle());
        descriptionTextField.setText(task.getDescription());
    }

    public void saveTask(ActionEvent event) throws IOException {
        String newTitle = titleTextField.getText();
        String newDescription = descriptionTextField.getText();

        taskToEdit.setTitle(newTitle);
        taskToEdit.setDescription(newDescription);

        AppData.saveTasks(MainViewController.taskList);

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
