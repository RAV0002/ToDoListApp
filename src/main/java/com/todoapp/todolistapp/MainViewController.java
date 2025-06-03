package com.todoapp.todolistapp;

import Data.AppData;
import Data.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private ListView<Task> tasksListView;

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    private Label taskTitleLabel;

    @FXML
    private Label taskDescriptionLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Task> loadedTasks = AppData.loadTasks();
        taskList.addAll(loadedTasks);

        tasksListView.setItems(taskList);
        tasksListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        taskTitleLabel.setText(newValue.getTitle());
//                        System.out.println(taskTitleLabel);
                        taskDescriptionLabel.setText(newValue.getDescription());
//                        System.out.println(taskDescriptionLabel);
                    }
        });
    }

    public void menuFileSave() {
        AppData.saveTasks(taskList);
    }

    // Po naciśnięciu przycisku zmieniamy SCENE
    public void addNewTaskSceneChange(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addTask-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Dodawanie nowego taska po pracy na drugiej scenie
    public void addNewTask(String title, String description){
        Task task = new Task(title, description);
        taskList.add(task);
        AppData.saveTasks(taskList);
    }

    // Usuwanie zaznaczonego taska
    public void deleteTask(ActionEvent event){
        int index = tasksListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            tasksListView.getItems().remove(index);
        }
    }

//    public void addTask(ActionEvent e) {
//        System.out.println("Addfile");
//        Task task = new Task("gówno", "dupa");
//        taskList.add(task);
//        AppData.saveTasks(taskList);
//    }
}
