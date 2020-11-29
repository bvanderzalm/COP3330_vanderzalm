import java.util.Scanner;

public class App {

    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        App m = new App();
        m.selectApplication();
    }

    public void selectApplication() {
        int applicationChoice;
        while(true) {
            displayApplicationMenu();
            applicationChoice = scnr.nextInt();

            if (applicationChoice == 1) {
                TaskApp t = new TaskApp();
                t.run();
            } else if (applicationChoice == 2) {
                ContactApp c = new ContactApp();
                c.run();
            } else if (applicationChoice == 3) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
    }

    private void displayApplicationMenu() {
        System.out.println("Select Your Application");
        System.out.println("-----------------------\n");
        System.out.println("1) Task List");
        System.out.println("2) Contact List");
        System.out.println("3) Quit\n");
        System.out.print("> ");
    }

}
