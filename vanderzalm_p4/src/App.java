import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        App m = new App();
        m.mainMenu();
    }

    public void mainMenu() {
        boolean continueLoop = true;
        do {
            try {
                displayMainMenuOptions();
                int userInput = scnr.nextInt();
                continueLoop = processUserInput(userInput);
            } catch (InputMismatchException ex) {
                scnr.nextLine();
                System.out.print("Invalid input, please only enter the numbers listed above (1, 2, 3)\n\n");
            } catch (Exception ex) {
                scnr.nextLine();
                System.out.print("Unexpected error, please try again.\n\n");
            }
        } while(continueLoop);
    }

    public boolean processUserInput(int userInput) {
        boolean continueLoop = true;
        if (inputInRange(userInput)) {
            if (readyToExit(userInput))
                continueLoop = false;
            else
                listOperationMenu(userInput);
        }
        else System.out.print("That menu option doesn't exist. Please enter 1, 2, or 3.\n\n");
        return continueLoop;
    }

    public void displayMainMenuOptions() {
        System.out.print("Main Menu\n---------\n\n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.print("3) Quit\n\n");
        System.out.print("> ");
    }

    public boolean inputInRange(int input) {
        return (input >= 1 && input <= 3);
    }

    public boolean readyToExit(int userInput) {
        return (userInput == 3);
    }

    public void listOperationMenu(int userInput) {
        // Doesn't do anything yet but should create a new list or load a list based on userInput
        System.out.println("List Operation Menu");
    }


}
