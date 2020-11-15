import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TaskList {
    List<TaskItem> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(TaskItem items) {
        tasks.add(items);
    }

    public int getSize() {
        return tasks.size();
    }

    public TaskItem get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void clear() {
        tasks.clear();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void write(String filename) {
        String completedStatus = null;
        try (Formatter output = new Formatter(filename)) {
            for (int i = 0; i < tasks.size(); i++) {
                TaskItem task = tasks.get(i);
                if (task.getCompletedStatus() == true) {
                    completedStatus = "completed";
                } else {
                    completedStatus = "uncompleted";
                }
                output.format("%s: %s [%s] - %s%n",
                        task.getTitle(), task.getDescription(), task.getDueDateString(), completedStatus);


            }
            System.out.println("Task list has been saved as " + filename);

        } catch (FileNotFoundException ex) {
            System.out.println("Error, unable to find the file.");
        } catch (Exception ex) {
            System.out.println("Unexpected error occurred while attempting to save file. Please try again.");
        }
    }
}
