import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

    public boolean indexValid(int index) {
        int size = tasks.size();
        if (index >= 0 && index <= (size-1))
            return true;
        else
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
    }

    public void markTaskCompleted(int index) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            task.setCompletedStatus(true);
        }
    }

    public void markTaskUncompleted(int index) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            task.setCompletedStatus(false);
        }
    }

    public String getTaskItemDescription(int index) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            return task.getDescription();
        }
    }

    public void editTaskDescription(int index, String description) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            task.setDescription(description);
        }
    }

    public LocalDate getTaskItemDueDate(int index) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            return task.getDueDate();
        }
    }

    public void editTaskDueDate(int index, LocalDate dueDate) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            task.setDueDate(dueDate);
        }
    }

    public String getTaskItemTitle(int index) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            return task.getTitle();
        }
    }

    public void editTaskTitle(int index, String title) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            task.setTitle(title);
        }
    }

    public boolean getTaskItemCompletedStatus(int index) {
        if (!indexValid(index)) {
            throw new InvalidTaskIndexException("Invalid index, that task doesn't exist.");
        } else {
            TaskItem task = tasks.get(index);
            return task.getCompletedStatus();
        }
    }

    public void write(String filename) {
        String completedStatus = null;
        try (Formatter output = new Formatter(filename)) {
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

        } catch (FileNotFoundException ex) {
            System.out.println("Error, unable to find the file.");
        } catch (NoSuchElementException | IllegalStateException ex) {
            System.out.println("Error attempting to save " + filename);
        } catch (Exception ex) {
            System.out.println("Unexpected error occurred while attempting to save file. Please try again.");
        }
    }

    public void load(String filename) {
        try (Scanner input = new Scanner(Paths.get(filename))) {
            while (input.hasNext()) {
                String title = input.nextLine();
                String description = input.nextLine();
                String dueDateString = input.nextLine();
                String completedStatusString = input.nextLine();

                LocalDate dueDate = LocalDate.parse(dueDateString);
                boolean completedStatus = convertToBoolean(completedStatusString);
                TaskItem task = new TaskItem(title, description, dueDate, completedStatus);
                tasks.add(task);
            }
        } catch (NoSuchElementException | IllegalStateException | IOException ex) {
            System.out.println("Error occurred after attempting to load " + filename);
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred while attempting to load this file.");
        }
    }

    private boolean convertToBoolean(String str) {
        return str.startsWith("t");
    }
}
