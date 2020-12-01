import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp {

    private static Scanner scnr = new Scanner(System.in);

    private ContactList contacts;

    public void run() {
        int contactMainMenuChoice;
        while(true) {
            displayContactMenu();
            contactMainMenuChoice = scnr.nextInt();

            if (contactMainMenuChoice == 1) {
                createContactList();
                contactMenu();
            } else if (contactMainMenuChoice == 2) {
                try {
                    loadExistingList();
                    contactMenu();
                } catch (IllegalArgumentException | InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (contactMainMenuChoice == 3) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
    }

    private void displayContactMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("---------\n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list.");
        System.out.println("3) Quit\n");
        System.out.print("> ");
    }

    private void loadExistingList() {
        scnr.nextLine();
        System.out.print("Enter the filename you wish to load: ");
        String filename = scnr.nextLine();
        contacts = new ContactList();
        contacts.load(filename);
        System.out.println("Contact list has been loaded from " + filename + "\n");
    }

    private void createContactList() {
        System.out.println("New contact list has been created\n");
        contacts = new ContactList();
    }

    private void contactMenu() {
        int contactMenuChoice;
        while(true) {
            displayContactMenuOptions();
            contactMenuChoice = scnr.nextInt();

            if (contactMenuInputValid(contactMenuChoice)) {
                processContactMenuInput(contactMenuChoice);
            } else if (contactMenuChoice == 6) {
                destroyListInRAM();
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
    }

    private void displayContactMenuOptions() {
        System.out.println();
        System.out.println("List Operation Menu");
        System.out.println("---------\n");
        System.out.println("1) View the list");
        System.out.println("2) Add an item");
        System.out.println("3) Edit an item");
        System.out.println("4) Remove an item");
        System.out.println("5) Save the current list");
        System.out.println("6) Quit to the main menu\n");
        System.out.print("> ");
    }

    private boolean contactMenuInputValid(int userInput) {
        return (userInput >= 1 && userInput <= 5);
    }

    private void destroyListInRAM() {
        if (contacts.isEmpty())
            return;
        contacts.clear();
    }

    private void processContactMenuInput(int contactMenuChoice) {
        if (contactMenuChoice == 1) printContacts();
        else if (contactMenuChoice == 2) addContact();
        else if (contactMenuChoice == 3) updateContact();
        else if (contactMenuChoice == 4) removeContact();
        else if (contactMenuChoice == 5) saveCurrentList();
        else {
            System.out.println("Invalid input. Please try again.\n");
        }
    }

    private void printContacts() {
        System.out.println("Current Contacts");
        System.out.println("----------------");
        System.out.println();
        System.out.println(contacts.printAll());
        System.out.println();
    }

    private void addContact() {
        scnr.nextLine();
        System.out.print("First name: ");
        String firstName = scnr.nextLine();
        System.out.print("Last name: ");
        String lastName = scnr.nextLine();
        System.out.print("Phone number (xxx-xxx-xxxx): ");
        String phoneNumber = scnr.nextLine();
        System.out.print("Email address (x@y.z): ");
        String emailAddress = scnr.nextLine();

        try {
            ContactItem contact = new ContactItem(firstName, lastName, phoneNumber, emailAddress);
            contacts.add(contact);
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void updateContact() {
        scnr.nextLine();
        printContacts();
        if (contacts.isEmpty()) {
            System.out.println("You currently don't have any contacts.");
            return;
        }
        System.out.print("Which contact would you like to update? ");
        int contactIndex = scnr.nextInt();
        scnr.nextLine();

        if (contactIndex < contacts.getSize()) {
            System.out.print("Enter a new first name for contact " + contactIndex + ": ");
            String firstName = scnr.nextLine();
            System.out.print("Enter a new last name for contact " + contactIndex + ": ");
            String lastName = scnr.nextLine();
            System.out.print("Enter a new phone number (xxx-xxx-xxxx) for contact " + contactIndex + ": ");
            String phoneNumber = scnr.nextLine();
            System.out.print("Enter a new email address (x@y.z) for contact " + contactIndex + ": ");
            String emailAddress = scnr.nextLine();

            try {
                contacts.update(contactIndex, firstName, lastName, phoneNumber, emailAddress);
            } catch(IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That contact doesn't exist. Please select one listed above.");
        }
    }

    private void removeContact() {
        printContacts();
        if (contacts.isEmpty()) {
            System.out.println("You currently don't have any contacts.");
            return;
        }
        System.out.print("Which contact would you like to delete? ");
        int contactIndex = scnr.nextInt();
        scnr.nextLine();

        try {
            contacts.remove(contactIndex);
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveCurrentList() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts have been created yet, saving failed.");
        } else {
            scnr.nextLine();
            System.out.print("Enter the filename to save as: ");
            String filename = scnr.nextLine();
            contacts.write(filename);
            System.out.println("Contact list has been saved as " + filename);
        }
    }

}
