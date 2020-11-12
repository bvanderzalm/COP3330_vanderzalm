import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private Scanner scnr = new Scanner(System.in);

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
            } catch (Exception ex) {
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
            } catch (Exception ex) {
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
    }

    public void printCurrentTasks() {
        System.out.print("Current Tasks\n-------------\n");
    }

    public void addTask() {
        TaskItem items = getTaskItems();

        storeTaskItems(items);
    }

    public TaskItem getTaskItems() {
        TaskItem items = null;
        while(true) {
            try {
                String title = getTitle();
                String description = getDescription();
                LocalDate dueDate = getDueDate();
                boolean completed = false;

                items = new TaskItem(title, description, dueDate, completed);
                break;
            } catch (InvalidTitleException ex) {
                System.out.println("WARNING: title must be at least 1 character long; task was not created.");
            } catch (InvalidDueDateException ex) {
                System.out.println("WARNING: invalid due date; task was not created.");
            }
        }
        return items;
    }
}
