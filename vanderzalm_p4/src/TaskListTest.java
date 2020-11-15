import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    // File on github and is used for testing.
    // and see TaskItemTest for note on LocalDate
//    private String filename = "TestCaseTaskList.txt";
    private String filename = "bradley.txt";
    private LocalDate validDate = LocalDate.parse("3000-01-01");

    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskList tasks = new TaskList();
        tasks.load(filename);
        int originalSize = tasks.getSize();

        TaskItem t = new TaskItem("Title", "", validDate, true);
        tasks.add(t);
        assertEquals(tasks.getSize(), originalSize + 1);
    }

    @Test
    public void completingTaskItemChangesStatus() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertDoesNotThrow(() -> tasks.markTaskCompleted(0));
        assertDoesNotThrow(() -> tasks.getTaskItemCompletedStatus(0));
        assertTrue(tasks.getTaskItemCompletedStatus(0));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertThrows(InvalidTaskIndexException.class, () -> tasks.markTaskCompleted(1000));
    }

//    @Test
//    public void editingTaskItemChangesValues() {
//
//    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        String edited = "New Description";
        assertDoesNotThrow(() -> tasks.editTaskDescription(0, edited));
        assertDoesNotThrow(() -> tasks.getTaskItemDescription(0));
        assertEquals(edited, tasks.getTaskItemDescription(0));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        String edited = "New description";
        assertThrows(InvalidTaskIndexException.class, () -> tasks.editTaskDescription(1000, edited));
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        LocalDate edited = LocalDate.parse("2100-01-01");
        assertDoesNotThrow(() -> tasks.editTaskDueDate(0, edited));
        assertDoesNotThrow(() -> tasks.getTaskItemDueDate(0));
        assertEquals(edited, tasks.getTaskItemDueDate(0));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        LocalDate edited = LocalDate.parse("2100-01-01");
        assertThrows(InvalidTaskIndexException.class, () -> tasks.editTaskDueDate(1000, edited));
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        String edited = "New Title!";
        assertDoesNotThrow(() -> tasks.editTaskTitle(0, edited));
        assertDoesNotThrow(() -> tasks.getTaskItemTitle(0));
        assertEquals(edited, tasks.getTaskItemTitle(0));
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        String edited = "New Title!";
        assertThrows(InvalidTaskIndexException.class, () -> tasks.editTaskTitle(1000, edited));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertThrows(InvalidTaskIndexException.class, () -> tasks.getTaskItemDescription(1000));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertDoesNotThrow(() -> tasks.getTaskItemDescription(0));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertThrows(InvalidTaskIndexException.class, () -> tasks.getTaskItemDueDate(1000));
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertDoesNotThrow(() -> tasks.getTaskItemDueDate(0));
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertThrows(InvalidTaskIndexException.class, () -> tasks.getTaskItemTitle(1000));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, false);
        tasks.add(t);

        assertDoesNotThrow(() -> tasks.getTaskItemTitle(0));
    }

    @Test
    public void newTaskListIsEmpty() {
        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void removingTaskItemsDecreasesSizes() {
        TaskList tasks = new TaskList();
        tasks.load(filename);
        int originalSize = tasks.getSize();

        tasks.remove(0);
        assertEquals(tasks.getSize(), originalSize - 1);
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        tasks.load(filename);

        assertThrows(InvalidTaskIndexException.class, () -> tasks.indexValid(1000));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        TaskList tasks = new TaskList();
        assertDoesNotThrow(() -> tasks.load(filename));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, true);
        tasks.add(t);

        assertDoesNotThrow(() -> tasks.markTaskUncompleted(0));
        assertDoesNotThrow(() -> tasks.getTaskItemCompletedStatus(0));
        assertFalse(tasks.getTaskItemCompletedStatus(0));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList tasks = new TaskList();
        TaskItem t = new TaskItem("Title", "", validDate, true);
        tasks.add(t);

        assertThrows(InvalidTaskIndexException.class, () -> tasks.markTaskUncompleted(1000));
    }


}