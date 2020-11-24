import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {

    @Test
    public void constructorFailsWithInvalidDueDate() {
        assertThrows(IllegalArgumentException.class, () -> new TaskItem("Title", "", "2000-01-01", false));
    }

    @Test
    public void constructorFailsWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, () -> new TaskItem("", "", "3000-01-01", false));
    }

    @Test
    public void constructorSucceedsWithValidDueDate() {
        TaskItem task = new TaskItem("t", "", "3000-01-01", false);
        assertDoesNotThrow(() -> task);
        assertEquals("3000-01-01", task.getDueDateString());
    }

    @Test
    public void constructorSucceedsWithValidTitle() {
        TaskItem task = new TaskItem("Title", "", "3000-01-01", false);
        assertDoesNotThrow(() -> task);
        assertEquals("Title", task.getTitle());
    }

    @Test
    public void editingDescriptionSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Title", "", "3000-01-01", false);
        task.edit("Title", "New description", "3000-01-01");
        assertEquals("New description", task.getDescription());
    }

    @Test
    public void editingDueDateFailsWithInvalidDateFormat() {
        TaskItem task = new TaskItem("Title", "", "3000-01-01", false);
        assertThrows(IllegalArgumentException.class, () -> task.edit("Title", "", "01-01-2021"));
    }

    @Test
    public void editingDueDateFailsWithInvalidYYYYMMDD() {
        TaskItem task = new TaskItem("Title", "", "3000-01-01", false);
        assertThrows(IllegalArgumentException.class, () -> task.edit("Title", "", "2000-96-01"));
    }

    @Test
    public void editingDueDateSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Title", "", "3000-01-01", false);
        assertDoesNotThrow(() -> task.edit("Valid", "desc.", "3000-12-12"));
        assertEquals("3000-12-12", task.getDueDateString());
    }

    @Test
    public void editingTitleFailsWithEmptyString() {
        TaskItem task = new TaskItem("Title", "", "3000-01-01", false);
        assertThrows(IllegalArgumentException.class, () -> task.edit("", "", "3000-01-01"));
    }

    @Test
    public void editingTitleSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Title", "", "3000-01-01", false);
        assertDoesNotThrow(() -> task.edit("Valid Title", "", "3000-01-01"));
        assertEquals("Valid Title", task.getTitle());
    }

}