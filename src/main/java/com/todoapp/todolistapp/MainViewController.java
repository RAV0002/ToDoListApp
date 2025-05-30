package com.todoapp.todolistapp;

import Data.AppData;
import Data.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private ListView<Task> tasksListView;

    @FXML
    private Label taskTitleLabel;

    @FXML
    private Label taskDescriptionLabel;


    String currentTask;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tasksListView.getItems().addAll(AppDa
                new Task("Do homework", "Complete math and physics assignments."),
                new Task("Take trash out", "Remember to sort recycling."),
                new Task("Walk a dog", "Take Bella for a walk in the park."),
                new Task("Turn off TV", "Don't forget to save energy.")
        );
        tasksListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        taskTitleLabel.setText(newValue.getTitle());
                        System.out.println(taskTitleLabel);
                        taskDescriptionLabel.setText(newValue.getDescription());
                        System.out.println(taskDescriptionLabel);
                    }
        });
    }

    public void menuFileSave() {
        System.out.println("menuBar/file/save clicked!!");
    }
}
