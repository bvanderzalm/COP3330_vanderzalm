import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    public void addingItemsIncreasesSize() {
        TaskList tasks = new TaskList();
        int originalSize = tasks.getSize();

        assertDoesNotThrow(() -> tasks.add(new TaskItem("title", "", "3000-01-01", false)));
        assertEquals(tasks.getSize(), originalSize + 1);
    }

    @Test
    public void completingTaskItemChangesStatus() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertDoesNotThrow(() -> tasks.markTaskCompleted(0));
        assertDoesNotThrow(() -> tasks.getTaskItemCompletedStatus(0));
        assertTrue(tasks.getTaskItemCompletedStatus(0));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.markTaskCompleted(1000));
    }

    @Test
    public void editingItemDescriptionFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.edit(1000, "Title", ":)", "3000-02-02"));
    }

    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertDoesNotThrow(() -> tasks.edit(0, "Title", "edited", "3000-01-01"));
        assertDoesNotThrow(() -> tasks.getTaskItemDescription(0));
        assertEquals("edited", tasks.getTaskItemDescription(0));
    }

    @Test
    public void editingItemDueDateSucceedsWithExpectedValue() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertDoesNotThrow(() -> tasks.edit(0, "Title", "", "2500-12-25"));
        assertDoesNotThrow(() -> tasks.getTaskItemDueDate(0));
        assertEquals("2500-12-25", tasks.getTaskItemDueDate(0));
    }

    @Test
    public void editingItemTitleFailsWithEmptyString() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.edit(0, "", "", "3000-01-01"));
    }

    @Test
    public void editingItemTitleFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.edit(1000, "t", "", "3000-01-01"));
    }

    @Test
    public void editingItemTitleSucceedsWithExpectedValue() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertDoesNotThrow(() -> tasks.edit(0, "New Title", "", "3000-01-01"));
        assertDoesNotThrow(() -> tasks.getTaskItemTitle(0));
        assertEquals("New Title", tasks.getTaskItemTitle(0));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.edit(0, "t", "", "12-25-2020"));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.edit(1000, "t", "", "2500-01-01"));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYYMMDD() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.edit(0, "Title", "", "2030-96-06"));
    }

    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.getTaskItemDescription(1000));
    }

    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "Description", "3000-01-01", false));

        assertDoesNotThrow(() -> tasks.getTaskItemDescription(0));
        assertEquals("Description", tasks.getTaskItemDescription(0));
    }

    @Test
    public void gettingItemDueDateFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.getTaskItemDueDate(1000));
    }

    @Test
    public void gettingItemDueDateSucceedsWithValidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertDoesNotThrow(() -> tasks.getTaskItemDueDate(0));
        assertEquals("3000-01-01", tasks.getTaskItemDueDate(0));
    }

    @Test
    public void gettingItemTitleFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.getTaskItemTitle(1000));
    }

    @Test
    public void gettingItemTitleSucceedsWithValidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertDoesNotThrow(() -> tasks.getTaskItemTitle(0));
        assertEquals("Title", tasks.getTaskItemTitle(0));
    }

    @Test
    public void newListIsEmpty() {
        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void removingItemsDecreasesSize() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));
        tasks.add(new TaskItem("Title2", "", "3000-01-02", true));
        int originalSize = tasks.getSize();

        assertDoesNotThrow(() -> tasks.remove(0));
        assertEquals(tasks.getSize(), originalSize - 1);
    }

    @Test
    public void removingItemsFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.remove(1000));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        // File is on GitHub repo labeled as the string below.
//        TaskList saveThisTaskList = new TaskList();
//        saveThisTaskList.add(new TaskItem("Title", "", "3000-01-01", false));
//        saveThisTaskList.add(new TaskItem("Title2", "", "3000-01-02", true));
//
//        TaskList newTasks = new TaskList();
//        assertDoesNotThrow(() -> saveThisTaskList.write("output.txt"));
//        assertDoesNotThrow(() -> newTasks.load("output.txt"));

        TaskList tasks = new TaskList();
        assertDoesNotThrow(() -> tasks.load("TestCaseTaskListOutput.txt"));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", true));

        assertDoesNotThrow(() -> tasks.markTaskUncompleted(0));
        assertDoesNotThrow(() -> tasks.getTaskItemCompletedStatus(0));
        assertFalse(tasks.getTaskItemCompletedStatus(0));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskItem("Title", "", "3000-01-01", false));

        assertThrows(IllegalArgumentException.class, () -> tasks.markTaskUncompleted(1000));
    }

}