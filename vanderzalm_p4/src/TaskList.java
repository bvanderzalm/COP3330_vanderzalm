import java.util.ArrayList;
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
}
