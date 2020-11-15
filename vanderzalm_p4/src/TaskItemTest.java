import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {

    // NOTE: TaskItem holds a LocalDate variable and this converts it (from a string)
    // Used throughout the test cases.
    private final LocalDate invalidDate = LocalDate.parse("2000-01-01");
    private final LocalDate validDate = LocalDate.parse("3000-01-01");

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
        assertDoesNotThrow(() -> new TaskItem("Task 1", "", validDate, true));
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        assertDoesNotThrow(() -> new TaskItem("Valid Task Title", "", validDate, true));
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        // Create task with a VALID date first, then set it with an INVALID date.
        TaskItem t = new TaskItem("Task1", "", validDate, false);
        assertThrows(InvalidDueDateException.class, () -> t.setDueDate(invalidDate));
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        TaskItem t = new TaskItem("Task1", "", validDate, false);
        LocalDate secondDueDate = LocalDate.parse("2100-12-12");
        t.setDueDate(secondDueDate);

        assertEquals(secondDueDate, t.getDueDate());
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {
        TaskItem t = new TaskItem("Initial valid title", "", validDate, false);
        assertThrows(InvalidTitleException.class, () -> t.setTitle(""));
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {
        TaskItem t = new TaskItem("Initial valid title", "", validDate, false);
        t.setTitle("Valid Task title");

        assertEquals("Valid Task title", t.getTitle());
    }

}