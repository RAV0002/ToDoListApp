package Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppData {

    private static final String FILE_PATH = "tasks.json";
    private static final Gson gson = new Gson();
    private static final List<Task> taskList = loadTasks();

    public static void saveTasks(List<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();  // tworzy plik, jeśli go nie ma
            }
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(tasks, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_PATH)){
            Type tasksListType = new TypeToken<List<Task>>(){}.getType();
            return gson.fromJson(reader, tasksListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
