import java.time.LocalDate;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completedStatus;

    public TaskItem(String title, String description, LocalDate dueDate, boolean completedStatus) {

        if (isTitleValid(title)) {
            this.title = title;
        } else {
            throw new InvalidTitleException("Title is not valid; must be at least 1 character long.");
        }

        if (isDueDateValid(dueDate)) {
            this.dueDate = dueDate;
            this.description = description;
            this.completedStatus = completedStatus;
        } else {
            throw new InvalidDueDateException("Due date is not valid; cannot be before the current date.");
        }
    }

    private boolean isTitleValid(String title) {
        return (title.length() > 0);
    }

    private boolean isDueDateValid(LocalDate dueDate) {
        LocalDate currentDate = LocalDate.now();
        return (dueDate.isAfter(currentDate) || dueDate.isEqual(currentDate));
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

    public String getDueDateString() {
        return dueDate.toString();
    }

    public void setDueDate(LocalDate dueDate) {
        if (isDueDateValid(dueDate)) {
            this.dueDate = dueDate;
        } else {
            throw new InvalidDueDateException("Due date is not valid; cannot be before the current date.");
        }
    }

    public boolean getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
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
