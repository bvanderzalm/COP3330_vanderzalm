import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class TaskList {
    List<TaskItem> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(TaskItem task) {
        tasks.add(task);
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

    public boolean taskIndexValid(int index) {
        int size = tasks.size();
        return (index >= 0 && index <= (size - 1));
    }

    public String printAll() {
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            TaskItem task = tasks.get(i);
            if (task.getCompletedStatus() == true) {
                strBuild.append(String.format("%d) *** %s%n", i, task));
            } else {
                strBuild.append(String.format("%d) %s%n", i, task));
            }
        }
        return strBuild.toString();
    }

    public String printUncompleted() {
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            TaskItem task = tasks.get(i);
            if (task.getCompletedStatus() == false) {
                strBuild.append(String.format("%d) %s%n", i, task));
            }
        }
        return strBuild.toString();
    }

    public String printCompleted() {
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            TaskItem task = tasks.get(i);
            if (task.getCompletedStatus() == true) {
                strBuild.append(String.format("%d) *** %s%n", i, task));
            }
        }
        return strBuild.toString();
    }

    public void write(String filename) {
        String completedStatus = null;
        try (Formatter output = new Formatter(filename)) {
            output.format("tasks%n");
            for (int i = 0; i < tasks.size(); i++) {
                TaskItem task = tasks.get(i);
                if (task.getCompletedStatus() == true) {
                    completedStatus = "t";
                } else {
                    completedStatus = "f";
                }
                output.format("%s%n%s%n%s%n%s%n",
                        task.getTitle(), task.getDescription(), task.getDueDateString(), completedStatus);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void load(String filename) {
        List<TaskItem> backupList = tasks;

        try (Scanner input = new Scanner(Paths.get(filename))) {
            String type = input.nextLine();
            if (type.equalsIgnoreCase("tasks")) {
                while (input.hasNext()) {
                    String title = input.nextLine();
                    String description = input.nextLine();
                    String dueDate = input.nextLine();
                    String completedStatusString = input.nextLine();

                    boolean completedStatus = convertToBoolean(completedStatusString);
                    TaskItem task = new TaskItem(title, description, dueDate, completedStatus);
                    tasks.add(task);
                }
            } else {
                tasks = backupList;
                throw new InputMismatchException("WARNING: filename is not a TaskList, loading failed.");
            }
        } catch (FileNotFoundException ex) {
            tasks = backupList;
            throw new IllegalArgumentException("WARNING: file not found, loading failed.");
        } catch (IOException ex) {
            tasks = backupList;
            throw new IllegalArgumentException("An unexpected error has occured, loading failed.");
        }
    }

    private boolean convertToBoolean(String str) {
        return str.startsWith("t");
    }

    public void update(int taskIndex, String title, String description, String dueDate) {
        if (taskIndexValid(taskIndex)) {
            TaskItem task = tasks.get(taskIndex);
            task.update(title, description, dueDate);
        } else {
            throw new IllegalArgumentException("Invalid index, please select a task listed above.");
        }
    }

    public void markTaskCompleted(int taskIndex) {
        if (taskIndexValid(taskIndex)) {
            TaskItem task = tasks.get(taskIndex);
            task.markTaskComplete();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a task listed above.");
        }
    }

    public void markTaskUncompleted(int taskIndex) {
        if (taskIndexValid(taskIndex)) {
            TaskItem task = tasks.get(taskIndex);
            task.markTaskUncomplete();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a task listed above.");
        }
    }

    public String getTaskItemTitle(int taskIndex) {
        if (taskIndexValid(taskIndex)) {
            TaskItem task = tasks.get(taskIndex);
            return task.getTitle();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a task listed above.");
        }
    }

    public String getTaskItemDescription(int taskIndex) {
        if (taskIndexValid(taskIndex)) {
            TaskItem task = tasks.get(taskIndex);
            return task.getDescription();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a task listed above.");
        }
    }

    public String getTaskItemDueDate(int taskIndex) {
        if (taskIndexValid(taskIndex)) {
            TaskItem task = tasks.get(taskIndex);
            return task.getDueDateString();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a task listed above.");
        }
    }

    public boolean getTaskItemCompletedStatus(int taskIndex) {
        if (taskIndexValid(taskIndex)) {
            TaskItem task = tasks.get(taskIndex);
            return task.getCompletedStatus();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a task listed above.");
        }
    }

}
