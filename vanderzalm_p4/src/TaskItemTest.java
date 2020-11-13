import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {

    // NOTE: TaskItem holds a LocalDate variable and this converts it (from a string)
    // Used throughout the test cases.
    LocalDate invalidDate = LocalDate.parse("2000-01-01");
    LocalDate validDate = LocalDate.parse("3000-01-01");

    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        assertThrows(InvalidDueDateException.class, () -> new TaskItem("Task1", "", invalidDate, false));
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        assertThrows(InvalidTitleException.class, () -> new TaskItem("", "", validDate, false));
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem t = new TaskItem("Task1", "", validDate, false);
        assertEquals(validDate, t.getDueDate());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        TaskItem t = new TaskItem("Task1", "", validDate, false);
        assertEquals("Task1", t.getTitle());
    }
}