import java.time.LocalDate;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completedStatus;

    public TaskItem(String title, String description, String dueDate, boolean completedStatus) {

        if (title.length() == 0) {
            throw new IllegalArgumentException("Invalid title, must be at least 1 character.");
        }
        LocalDate date;
        try {
            date = LocalDate.parse(dueDate);
        } catch(java.time.format.DateTimeParseException ex) {
            throw new IllegalArgumentException("Error involving due date. Make sure to " +
                    "type in the (YYYY-MM-DD) format.");
        }

        if (isDueDateValid(date)) {
            this.title = title;
            this.dueDate = date;
            this.description = description;
            this.completedStatus = completedStatus;
        } else {
            throw new IllegalArgumentException("Invalid due date. Deadline cannot be before the current date.");
        }
    }

    private boolean isDueDateValid(LocalDate dueDate) {
        LocalDate currentDate = LocalDate.now();
        return (dueDate.isAfter(currentDate) || dueDate.isEqual(currentDate));
    }

    public void edit(String title, String description, String dueDate) {
        if (title.length() == 0) {
            throw new IllegalArgumentException("Invalid title, must be at least 1 character.");
        }
        LocalDate date;
        try {
            date = LocalDate.parse(dueDate);
        } catch(java.time.format.DateTimeParseException ex) {
            throw new IllegalArgumentException("Error involving due date. Make sure to " +
                    "type in the (YYYY-MM-DD) format.");
        }

        if (isDueDateValid(date)) {
            this.title = title;
            this.dueDate = date;
            this.description = description;
        } else {
            throw new IllegalArgumentException("Invalid due date. Deadline cannot be before the current date.");
        }
    }

    public void markTaskComplete() {
        this.completedStatus = true;
    }

    public void markTaskUncomplete() {
        this.completedStatus = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDueDateString() {
        return dueDate.toString();
    }

    public boolean getCompletedStatus() {
        return completedStatus;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s", dueDate, title, description);
    }
}
