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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private ListView<Task> tasksListView;

    public static final ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    private Label taskTitleLabel;

    @FXML
    private TextArea taskDescriptionLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Task> loadedTasks = AppData.loadTasks();
        taskList.clear();
        taskList.addAll(loadedTasks);

        tasksListView.setItems(taskList);
        tasksListView.setCellFactory(listView -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setOnAction(event -> {
                    Task task = getItem();
                    if (task != null) {
                        task.setDone(checkBox.isSelected());
                        // np. zapis do pliku:
                        AppData.saveTasks(MainViewController.taskList);
                        updateItem(task, false);
                    }
                });
            }

            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);

                if (empty || task == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("");
                } else {
                    setText(task.getTitle());
                    checkBox.setSelected(task.isDone());
                    setGraphic(checkBox);

                    if (task.isDone()) {
                        setStyle("-fx-background-color: #d4edda; -fx-text-fill: green;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        tasksListView.scrollTo(taskList.size() - 1);
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
    public void editTaskSceneChange(ActionEvent event) throws IOException {
        int selectedIndex = tasksListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >=0) {
            Task taskToEdit = taskList.get(selectedIndex);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editTask-view.fxml"));
            root = loader.load();

            EditTaskViewController editTaskViewController = loader.getController();
            editTaskViewController.setTask(taskToEdit);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Brak wybranego taska");
        }
    }

    //Dodawanie nowego taska po pracy na drugiej scenie
    public static void addNewTask(String title, String description){
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
}

