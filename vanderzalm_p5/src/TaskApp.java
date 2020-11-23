import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp {

    private static Scanner scnr = new Scanner(System.in);

    private TaskList tasks;

    public void run() {
        int taskMainMenuChoice;
        while(true) {
            displayTaskMenu();
            taskMainMenuChoice = scnr.nextInt();

            if (taskMainMenuChoice == 1) {
                createTaskList();
                taskMenu();
            } else if (taskMainMenuChoice == 2) {
                try {
                    loadList();
                    taskMenu();
                } catch (IllegalArgumentException | InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (taskMainMenuChoice == 3) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
    }

    private void displayTaskMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("---------\n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list.");
        System.out.println("3) Quit\n");
        System.out.print("> ");
    }

    private void createTaskList() {
        System.out.println("New task list has been created\n");
        tasks = new TaskList();
    }

    private void taskMenu() {
        int taskMenuChoice;
        while(true) {
            displayTaskMenuOptions();
            taskMenuChoice = scnr.nextInt();

            if (taskMenuInputValid(taskMenuChoice)) {
                processTaskMenuInput(taskMenuChoice);
            } else if (taskMenuChoice == 8) {
//                destroyListInRAM();
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
    }

    private void displayTaskMenuOptions() {
        System.out.println();
        System.out.println("List Operation Menu");
        System.out.println("---------\n");
        System.out.println("1) View the list");
        System.out.println("2) Add an item");
        System.out.println("3) Edit an item");
        System.out.println("4) Remove an item");
        System.out.println("5) Mark an item as completed");
        System.out.println("6) Unmark an item as completed");
        System.out.println("7) Save the current list");
        System.out.println("8) Quit to the main menu\n");
        System.out.print("> ");
    }

    private boolean taskMenuInputValid(int userInput) {
        return (userInput >= 1 && userInput <= 7);
    }

//    private void destroyListInRAM() {
//        if (tasks.isEmpty())
//            return;
//        tasks.clear();
//    }

    private void processTaskMenuInput(int taskMenuChoice) {
        if (taskMenuChoice == 1) printTasks();
        else if (taskMenuChoice == 2) addTask();
        else if (taskMenuChoice == 3) editTask();
        else if (taskMenuChoice == 4) removeTask();
        else if (taskMenuChoice == 5) markTaskComplete();
        else if (taskMenuChoice == 6) markTaskUncomplete();
        else if (taskMenuChoice == 7) saveCurrentList();
        else {
            System.out.println("Invalid input. Please try again.\n");
        }
    }

    private void printTasks() {
        System.out.println("Current Tasks");
        System.out.println("-------------");
        System.out.println();
        System.out.println(tasks.printAll());
        System.out.println();
    }

    private void addTask() {
        System.out.print("Task Title: ");
        String title = scnr.nextLine();
        System.out.print("Task Description: ");
        String description = scnr.nextLine();
        System.out.print("Task Due Date (YYYY-MM-DD): ");
        String dueDate = scnr.nextLine();

        try {
            TaskItem task = new TaskItem(title, description, dueDate, false);
            tasks.add(task);
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void editTask() {
        printTasks();
        System.out.print("Which task would you like to edit? ");
        int taskIndex = scnr.nextInt();
        scnr.nextLine();

        if (taskIndex < tasks.getSize()) {
            System.out.print("Enter a new title for task " + taskIndex + ": ");
            String title = scnr.nextLine();
            System.out.print("Enter a new description for task " + taskIndex + ": ");
            String description = scnr.nextLine();
            System.out.print("Enter a new task due date (YYYY-MM-DD) for task " + taskIndex + ": ");
            String dueDate = scnr.nextLine();

            try {
                tasks.update(taskIndex, title, description, dueDate);
            } catch(IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That task doesn't exist. Please select one listed above.");
        }
    }

    private void removeTask() {
        printTasks();
        System.out.print("Which task would you like to remove? ");
        int taskIndex = scnr.nextInt();
        scnr.nextLine();

        if (taskIndex < tasks.getSize()) {
            tasks.remove(taskIndex);
        } else {
            System.out.println("That task doesn't exist. Please select one listed above.");
        }
    }

    private void printUncompletedTasks() {
        System.out.println("Uncompleted Tasks");
        System.out.println("-----------------\n");
        System.out.println(tasks.printUncompleted());
        System.out.println();
    }

    private void markTaskComplete() {
        int taskIndex;

        printUncompletedTasks();
        if (tasks.isEmpty()) {
            System.out.println("All tasks are currently complete!");
        } else {
            System.out.print("Which task would you like to mark as completed? ");
            taskIndex = scnr.nextInt();
            scnr.nextLine();

            if (taskIndex < tasks.getSize()) {
                if (tasks.getTaskItemCompletedStatus(taskIndex) == true) {
                    System.out.println("Task is already complete. No changes made.");
                } else {
                    tasks.markTaskCompleted(taskIndex);
                }
            } else {
                System.out.println("That task doesn't exist. Please select one listed above.");
            }
        }
    }

    private void printCompletedTasks() {
        System.out.println("Completed Tasks");
        System.out.println("---------------\n");
        System.out.println(tasks.printCompleted());
        System.out.println();
    }

    


}
