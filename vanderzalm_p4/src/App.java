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
        } else if (userInput == 2) {
            loadExistingList();
            listOperationMenu();
        }
    }

    public void loadExistingList() {
        scnr.nextLine();
        System.out.print("Enter the filename you wish to load: ");
        String fileName = scnr.nextLine();
        tasks.load(fileName);
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
            if (readyToQuitTaskMenu(userInput)) {
                continueLoop = false;
                destroyListInRAM();
            } else {
                taskMenuOptions(userInput);
                if (userInput == 7) {
                    continueLoop = false;
                    destroyListInRAM();
                }
            }
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
        if (userInput == 1) printCurrentTasks(1);
        else if (userInput == 2) addTask(2);
        else if (userInput == 3) editTask(3);
        else if (userInput == 4) editTask(4);
        else if (userInput == 5) editTask(5);
        else if (userInput == 6) editTask(6);
        else if (userInput == 7) saveCurrentList();
        System.out.print("\n");
    }

    public void saveCurrentList() {
        scnr.nextLine();
        System.out.print("Enter the filename to save as: ");
        String fileName = scnr.nextLine();
        tasks.write(fileName);
    }

    private void destroyListInRAM() {
        if (tasks.isEmpty())
            return;
        tasks.clear();
    }

    public void addTask(int menuUserInput) {
        // -1 is TaskIndex, but since we are creating new Task that variable isn't needed.
        TaskItem items = getTaskItems(menuUserInput, -1);

        storeTaskItems(items);
    }

    private void storeTaskItems(TaskItem items) {
        tasks.add(items);
    }

    public void printCurrentTasks(int menuUserInput) {
        if (menuUserInput == 5) {
            System.out.print("Uncompleted Tasks\n-------------\n");
        } else if (menuUserInput == 6) {
            System.out.print("Completed Tasks\n-------------\n");
        } else {
            System.out.print("Current Tasks\n-------------\n");
        }
        printAllTasks();
        System.out.print("\n\n");
    }

    public void printAllTasks() {
        for (int i = 0; i < tasks.getSize(); i++) {
            TaskItem task = tasks.get(i);
            System.out.print("\n"+i + ") ");
            if (task.getCompletedStatus() == true) {
                System.out.print("*** ");
            }
            System.out.print("[" +task.getDueDate() + "] " +task.getTitle() + ": " +task.getDescription());
        }
    }

    public void editTask(int menuUserInput) {
        printCurrentTasks(menuUserInput);
        if (zeroTasks()) {
            return;
        }
        boolean continueLoop = true;
        do {
            try {
                printEditTaskPrompt(menuUserInput);
                int selectedTask = scnr.nextInt();

                continueLoop = processCurrentListUserInput(selectedTask, menuUserInput);
            } catch (InputMismatchException ex) {
                scnr.nextLine();
                System.out.print("Invalid input. Please select one of the numbers listed above.\n\n");
            } catch (Exception ex) {
                scnr.nextLine();
                System.out.print("Unexpected error, please try again.\n\n");
            }
        } while(continueLoop);
    }

    public boolean zeroTasks() {
        if (tasks.getSize() == 0) {
            System.out.print("You currently don't have any tasks.\n\n");
            return true;
        }
        return false;
    }

    public void printEditTaskPrompt(int menuUserInput) {
        if (menuUserInput == 3) {
            System.out.print("Which task would you like to edit? ");
        } else if (menuUserInput == 4) {
            System.out.print("Which task would you like to remove? ");
        } else if (menuUserInput == 5) {
            System.out.print("Which task would you like to mark as completed? ");
        } else if (menuUserInput == 6) {
            System.out.print("Which task would you like to unmark as completed? ");
        }
    }

    public boolean processCurrentListUserInput(int selectedTask, int menuUserInput) {
        boolean continueLoop = true;
        if (taskListInputValid(selectedTask)) {
            continueLoop = false;
            if (menuUserInput == 3) {
                getTaskItems(menuUserInput, selectedTask);
            } else if (menuUserInput == 4) {
                removeTask(selectedTask);
            } else if (menuUserInput == 5) {
                markTaskCompleted(selectedTask);
            } else if (menuUserInput == 6) {
                markTaskUncompleted(selectedTask);
            }
        }
        else
            System.out.print("Invalid input, that task doesn't exist. Please try again.\n\n");
        return continueLoop;
    }

    public boolean taskListInputValid(int selectedTask) {
        int size = tasks.getSize();
        return (selectedTask >= 0 && selectedTask <= (size-1));
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public void markTaskCompleted(int taskIndex) {
        TaskItem task = tasks.get(taskIndex);
        if (task.getCompletedStatus() == true) {
            System.out.println("Task " + taskIndex + " was already marked as completed. Returning to menu.");
            return;
        }
        task.setCompletedStatus(true);
    }

    public void markTaskUncompleted(int taskIndex) {
        TaskItem task = tasks.get(taskIndex);
        if (task.getCompletedStatus() == false) {
            System.out.println("Task " + taskIndex + " was already marked as uncompleted. Returning to menu.");
            return;
        }
        task.setCompletedStatus(false);
    }

    public TaskItem getTaskItems(int menuUserInput, int taskIndex) {
        scnr.nextLine();
        TaskItem task = null;
        if (menuUserInput == 3) {
            task = tasks.get(taskIndex);
        }
        while(true) {
            try {
                printTitlePrompt(menuUserInput, taskIndex);
                String title = getTitle();
                printDescriptionPrompt(menuUserInput, taskIndex);
                String description = getDescription();
                printDueDatePrompt(menuUserInput, taskIndex);
                LocalDate dueDate = getDueDate();

                if (menuUserInput == 2) {
                    boolean completed = false;
                    task = new TaskItem(title, description, dueDate, completed);
                } else if (menuUserInput == 3) {
                    setNewTaskItems(task, title, description, dueDate);
                }
                break;
            } catch (InvalidTitleException ex) {
                System.out.print("WARNING: title must be at least 1 character long, please try again.\n\n");
            } catch (InvalidDueDateException ex) {
                System.out.print("WARNING: invalid due date, please try again.\n\n");
            } catch (Exception ex) {
                System.out.print("Unexpected error. Tip: make sure you are typing in the (YYYY-MM-DD) format.\n\n");
            }
        }
        return task;
    }

    public void setNewTaskItems(TaskItem task, String title, String description, LocalDate dueDate) {
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
    }

    private void printTitlePrompt(int menuUserInput, int taskIndex) {
        if (menuUserInput == 2) {
            System.out.print("Task title: ");
        } else if (menuUserInput == 3) {
            System.out.print("Enter a new title for task " + taskIndex + ": ");
        }
    }

    private void printDescriptionPrompt(int menuUserInput, int taskIndex) {
        if (menuUserInput == 2) {
            System.out.print("Task description: ");
        } else if (menuUserInput == 3) {
            System.out.print("Enter a new description for task " + taskIndex + ": ");
        }
    }

    private void printDueDatePrompt(int menuUserInput, int taskIndex) {
        if (menuUserInput == 2) {
            System.out.print("Task due date (YYYY-MM-DD): ");
        } else if (menuUserInput == 3) {
            System.out.print("Enter a new task due date (YYYY-MM-DD) for task " + taskIndex + ": ");
        }
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
