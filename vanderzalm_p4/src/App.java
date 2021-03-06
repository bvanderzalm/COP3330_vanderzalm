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

    private void mainMenu() {
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

    private void displayMainMenuOptions() {
        System.out.print("Main Menu\n---------\n\n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.print("3) Quit\n\n");
        System.out.print("> ");
    }

    private boolean processMainMenuUserInput(int userInput) {
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

    private boolean mainMenuInputValid(int userInput) {
        return (userInput >= 1 && userInput <= 3);
    }

    private boolean readyToExitProgram(int userInput) {
        return (userInput == 3);
    }

    private void createOrLoadList(int userInput) {
        if (userInput == 1) {
            System.out.print("New task list has been created\n\n");
            listOperationMenu();
        } else if (userInput == 2) {
            loadExistingList();
            listOperationMenu();
        }
    }

    private void loadExistingList() {
        scnr.nextLine();
        System.out.print("Enter the filename you wish to load: ");
        String filename = scnr.nextLine();
        tasks.load(filename);
        System.out.println("Task list has been loaded from " + filename + "\n");
    }

    private void listOperationMenu() {
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

    private void displayTaskMenuOptions() {
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

    private boolean processTaskMenuUserInput(int userInput) {
        boolean continueLoop = true;
        if (taskMenuInputValid(userInput)) {
            if (readyToQuitTaskMenu(userInput)) {
                continueLoop = false;
                destroyListInRAM();
            } else {
                taskMenuOptions(userInput);
                if (userInput == 7) {
                    if (tasks.isEmpty()) {
                        continueLoop = true;
                    } else {
                        continueLoop = false;
                        destroyListInRAM();
                    }
                }
            }
        }
        else
            System.out.print("That menu option doesn't exist. Please enter a number 1 through 8.\n\n");
        return continueLoop;
    }

    private boolean taskMenuInputValid(int userInput) {
        return (userInput >= 1 && userInput <= 8);
    }

    private boolean readyToQuitTaskMenu(int userInput) {
        return (userInput == 8);
    }

    private void taskMenuOptions(int userInput) {
        if (userInput == 1) printCurrentTasks(1);
        else if (userInput == 2) addTask();
        else if (userInput == 3) editTask(3);
        else if (userInput == 4) editTask(4);
        else if (userInput == 5) editTask(5);
        else if (userInput == 6) editTask(6);
        else if (userInput == 7) saveCurrentList();
        System.out.print("\n");
    }

    private void saveCurrentList() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks have been created yet, saving failed.");
            return;
        }
        scnr.nextLine();
        System.out.print("Enter the filename to save as: ");
        String filename = scnr.nextLine();
        tasks.write(filename);
        System.out.println("Task list has been saved as " + filename);
    }

    private void destroyListInRAM() {
        if (tasks.isEmpty())
            return;
        tasks.clear();
    }

    private void addTask() {
        // -1 is TaskIndex, but since we are creating a new Task that variable isn't needed.
        TaskItem items = getTaskItems(2, -1);

        storeTaskItems(items);
    }

    private void storeTaskItems(TaskItem items) {
        tasks.add(items);
    }

    private void printCurrentTasks(int menuUserInput) {
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

    private void printAllTasks() {
        for (int i = 0; i < tasks.getSize(); i++) {
            TaskItem task = tasks.get(i);
            System.out.print("\n"+i + ") ");
            if (task.getCompletedStatus() == true) {
                System.out.print("*** ");
            }
            System.out.print("[" +task.getDueDate() + "] " +task.getTitle() + ": " +task.getDescription());
        }
    }

    private void editTask(int menuUserInput) {
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
            } catch (InvalidTaskIndexException ex) {
                scnr.nextLine();
                System.out.print("Error, that task doesn't exist. Please select one of the numbers listed above.\n\n");
            } catch (Exception ex) {
                scnr.nextLine();
                System.out.print("Unexpected error, please try again.\n\n");
            }
        } while(continueLoop);
    }

    private boolean zeroTasks() {
        if (tasks.getSize() == 0) {
            System.out.print("You currently don't have any tasks.\n\n");
            return true;
        }
        return false;
    }

    private void printEditTaskPrompt(int menuUserInput) {
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

    private boolean processCurrentListUserInput(int selectedTask, int menuUserInput) {
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
        return continueLoop;
    }

    public boolean taskListInputValid(int selectedTask) {
        return tasks.indexValid(selectedTask);
    }

    private void removeTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    private void markTaskCompleted(int taskIndex) {
//        if (task.getCompletedStatus() == true) {
//            System.out.println("Task " + taskIndex + " was already marked as completed. Returning to menu.");
//            return;
//        }
        tasks.markTaskCompleted(taskIndex);

    }

    private void markTaskUncompleted(int taskIndex) {
//        if (task.getCompletedStatus() == false) {
//            System.out.println("Task " + taskIndex + " was already marked as uncompleted. Returning to menu.");
//            return;
//        }
        tasks.markTaskUncompleted(taskIndex);
    }

    private TaskItem getTaskItems(int menuUserInput, int taskIndex) {
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
                    setNewTaskItems(taskIndex, title, description, dueDate);
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

    private void setNewTaskItems(int taskIndex, String title, String description, LocalDate dueDate) {
        tasks.editTaskTitle(taskIndex, title);
        tasks.editTaskDescription(taskIndex, description);
        tasks.editTaskDueDate(taskIndex, dueDate);
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
