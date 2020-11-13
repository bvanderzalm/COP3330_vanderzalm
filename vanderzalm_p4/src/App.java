import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static Scanner scnr = new Scanner(System.in);

    private TaskList tasks;

    public App() {
        tasks = new TaskList();
    }

    public static void main(String[] args) {
        App m = new App();
        m.mainMenu();
    }

    public void mainMenu() {
        boolean continueLoop = true;
        do {
            try {
                displayMainMenuOptions();
                int mainMenuUserInput = scnr.nextInt();
                continueLoop = processMainMenuUserInput(mainMenuUserInput);
            } catch (InputMismatchException ex) {
                scnr.nextLine();
                System.out.print("Invalid input, only enter the numbers listed above (1, 2, 3).\n\n");
            }
            catch (Exception ex) {
                scnr.nextLine();
                System.out.print("Unexpected error, please try again.\n\n");
            }
        } while(continueLoop);
    }

    public void displayMainMenuOptions() {
        System.out.print("Main Menu\n---------\n\n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.print("3) Quit\n\n");
        System.out.print("> ");
    }

    public boolean processMainMenuUserInput(int userInput) {
        boolean continueLoop = true;
        if (mainMenuInputValid(userInput)) {
            if (readyToExitProgram(userInput))
                continueLoop = false;
            else
                createOrLoadList(userInput);
        }
        else System.out.print("That menu option doesn't exist. Please enter 1, 2, or 3.\n\n");
        return continueLoop;
    }

    public boolean mainMenuInputValid(int userInput) {
        return (userInput >= 1 && userInput <= 3);
    }

    public boolean readyToExitProgram(int userInput) {
        return (userInput == 3);
    }

    public void createOrLoadList(int userInput) {
        if (userInput == 1) {
            System.out.print("New task list has been created\n\n");
            listOperationMenu();
        } else
            System.out.print("Load list\n");
    }

    public void listOperationMenu() {
        boolean continueLoop = true;
        do {
            try {
                displayTaskMenuOptions();
                int taskMenuUserInput = scnr.nextInt();
                continueLoop = processTaskMenuUserInput(taskMenuUserInput);
            } catch (InputMismatchException ex) {
                scnr.nextLine();
                System.out.print("Invalid input, only enter the numbers listed above (1-8).\n\n");
            }
            catch (Exception ex) {
                scnr.nextLine();
                System.out.print("Unexpected error, please try again.\n\n");
            }
        } while(continueLoop);
    }

    public void displayTaskMenuOptions() {
        System.out.print("List Operation Menu\n---------\n\n");
        System.out.println("1) View the list");
        System.out.println("2) Add an item");
        System.out.println("3) Edit an item");
        System.out.println("4) Remove an item");
        System.out.println("5) Mark an item as completed");
        System.out.println("6) Unmark an item as completed");
        System.out.println("7) Save the current list");
        System.out.println("8) Quit to the main menu");
        System.out.print("\n> ");
    }

    public boolean processTaskMenuUserInput(int userInput) {
        boolean continueLoop = true;
        if (taskMenuInputValid(userInput)) {
            if (readyToQuitTaskMenu(userInput))
                continueLoop = false;
            else
                taskMenuOptions(userInput);
        }
        else
            System.out.print("That menu option doesn't exist. Please enter a number 1 through 8.\n\n");
        return continueLoop;
    }

    public boolean taskMenuInputValid(int userInput) {
        return (userInput >= 1 && userInput <= 8);
    }

    public boolean readyToQuitTaskMenu(int userInput) {
        return (userInput == 8);
    }

    public void taskMenuOptions(int userInput) {
        if (userInput == 1) printCurrentTasks();
        else if (userInput == 2) addTask();
        else if (userInput == 3) editTask();
//        else if (userInput == 4) removeTask();
//        else if (userInput == 5) markTask();
//        else if (userInput == 6) unmarkTask();
//        else if (userInput == 7) saveCurrentList();
    }

    public void printCurrentTasks() {
        System.out.print("Current Tasks\n-------------\n\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            TaskItem data = tasks.get(i);
            System.out.println(i + ") [" +data.getDueDate() + "] " +data.getTitle() + ": " +data.getDescription());
        }
        System.out.print("\n");
    }

    public void editTask() {
        printCurrentTasks();
        if (checkForNoTasks()) {
            return;
        }
        boolean continueLoop = true;
        do {
            try {
                System.out.print("Which task will you edit? ");
                int selectedTask = scnr.nextInt();
                continueLoop = processTaskListUserInput(selectedTask);
            } catch (InputMismatchException ex) {
                scnr.nextLine();
                System.out.print("Invalid input. Please select one of the numbers listed above.\n\n");
            } catch (Exception ex) {
                scnr.nextLine();
                System.out.print("Unexpected error, please try again.\n\n");
            }
        } while(continueLoop);
    }

    public boolean checkForNoTasks() {
        if (tasks.getSize() == 0) {
            System.out.print("You currently don't have any tasks that can be edited.\n\n");
            return true;
        }
        return false;
    }

    public boolean processTaskListUserInput(int selectedTask) {
        boolean continueLoop = true;
        if (taskListInputValid(selectedTask)) {
            continueLoop = false;
            getNewTaskItems(selectedTask);
        }
        else
            System.out.println("Invalid input, that task doesn't exist. Please try again.");
        return continueLoop;
    }

    public boolean taskListInputValid(int selectedTask) {
        int size = tasks.getSize();
        return (selectedTask >= 0 && selectedTask <= (size-1));
    }

    public void getNewTaskItems(int taskIndex) {
        scnr.nextLine();
        TaskItem task = tasks.get(taskIndex);
        while(true) {
            try {
                System.out.print("Enter a new title for task " + taskIndex + ": ");
                String title = getTitle();
                System.out.print("Enter a new description for task " + taskIndex + ": ");
                String description = getDescription();
                System.out.print("Enter a new task due date (YYYY-MM-DD) for task " + taskIndex + ": ");
                LocalDate dueDate = getDueDate();

                setNewTaskItems(task, title, description, dueDate);
                System.out.print("\n");
                break;
            } catch (InvalidTitleException ex) {
                System.out.print("WARNING: title must be at least 1 character long, please try again.\n\n");
            } catch (InvalidDueDateException ex) {
                System.out.print("WARNING: invalid due date, please try again.\n\n");
            } catch (Exception ex) {
                System.out.print("Unexpected error. Tip: make sure you are typing in the (YYYY-MM-DD) format.\n\n");
            }
        }
    }

    public void setNewTaskItems(TaskItem task, String title, String description, LocalDate dueDate) {
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
    }

    public void addTask() {
        TaskItem items = getTaskItems();
        System.out.print("\n");

        storeTaskItems(items);
    }

    private void storeTaskItems(TaskItem items) {
        tasks.add(items);
    }

    public TaskItem getTaskItems() {
        TaskItem items = null;
        scnr.nextLine();
        while(true) {
            try {
                System.out.print("Task title: ");
                String title = getTitle();
                System.out.print("Task description: ");
                String description = getDescription();
                System.out.print("Task due date (YYYY-MM-DD): ");
                LocalDate dueDate = getDueDate();
                boolean completed = false;

                items = new TaskItem(title, description, dueDate, completed);
                break;
            } catch (InvalidTitleException ex) {
                System.out.print("WARNING: title must be at least 1 character long, please try again.\n\n");
            } catch (InvalidDueDateException ex) {
                System.out.print("WARNING: invalid due date, please try again.\n\n");
            } catch (Exception ex) {
                System.out.print("Unexpected error. Tip: make sure you are typing in the (YYYY-MM-DD) format.\n\n");
            }
        }
        return items;
    }

    private String getTitle() {
        return scnr.nextLine();
    }

    private String getDescription() {
        return scnr.nextLine();
    }

    private LocalDate getDueDate() {
        String date = scnr.nextLine();
        return LocalDate.parse(date);
    }
}
