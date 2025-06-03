package Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppData {

    private static final String FILE_PATH = "src/main/resources/tasks.json";
    private static final Gson gson = new Gson();
    private final List<Task> taskList = loadTasks();

    public static void saveTasks(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
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
