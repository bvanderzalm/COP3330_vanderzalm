import java.time.LocalDate;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public TaskItem(String title, String description, LocalDate dueDate, boolean completed) {

        if (isTitleValid(title)) {
            this.title = title;
        } else {
            throw new InvalidTitleException("Title is not valid; must be at least 1 character long.");
        }

        if (isDueDateValid(dueDate)) {
            this.dueDate = dueDate;
            this.description = description;
            this.completed = false;
        } else {
            throw new InvalidDueDateException("Due date is not valid; cannot be before the current date.");
        }
    }

    private boolean isTitleValid(String title) {
        return (title.length() > 0);
    }

    private boolean isDueDateValid(LocalDate dueDate) {
        LocalDate currentDate = LocalDate.now();
        return dueDate.isAfter(currentDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (isTitleValid(title)) {
            this.title = title;
        } else {
            throw new InvalidTitleException("Title is not valid; must be at least 1 character long.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (isDueDateValid(dueDate)) {
            this.dueDate = dueDate;
        } else {
            throw new InvalidDueDateException("Due date is not valid; cannot be before the current date.");
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

class InvalidTitleException extends IllegalArgumentException {
    public InvalidTitleException(String msg) {
        super(msg);
    }
}

class InvalidDueDateException extends IllegalArgumentException {
    public InvalidDueDateException(String msg) {
        super(msg);
    }
}
