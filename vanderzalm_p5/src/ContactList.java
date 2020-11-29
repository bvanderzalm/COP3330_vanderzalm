import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ContactList {
    List<ContactItem> contacts;

    public ContactList() {
        contacts = new ArrayList<>();
    }

    public void add(ContactItem contact) {
        contacts.add(contact);
    }

    public int getSize() {
        return contacts.size();
    }

    public void remove(int index) {
        if (contactIndexValid(index)) {
            contacts.remove(index);
        } else {
            throw new IllegalArgumentException("Invalid index, please select a contact listed above.");
        }
    }

    public void clear() {
        contacts.clear();
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }

    public boolean contactIndexValid(int index) {
        int size = contacts.size();
        return (index >= 0 && index <= (size - 1));
    }

    public String printAll() {
        StringBuilder strBld = new StringBuilder();
        for (int i = 0; i < contacts.size(); i++) {
            ContactItem contact = contacts.get(i);
            strBld.append(String.format("%d) %s%n", i, contact));
        }
        return strBld.toString();
    }

    public void write(String filename) {
        try (Formatter output = new Formatter(filename)) {
            output.format("contacts%n");
            for (int i = 0; i < contacts.size(); i++) {
                ContactItem contact = contacts.get(i);
                output.format("%s%n%s%n%s%n%s%n", contact.getFirstName(), contact.getLastName(),
                        contact.getPhoneNumber(), contact.getEmailAddress());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void load(String filename) {
        List<ContactItem> backupList = contacts;

        try (Scanner input = new Scanner(Paths.get(filename))) {
            String type = input.nextLine();
            if (type.equalsIgnoreCase("contacts")) {
                while (input.hasNext()) {
                    String firstName = input.nextLine();
                    String lastName = input.nextLine();
                    String phoneNumber = input.nextLine();
                    String emailAddress = input.nextLine();

                    ContactItem contact = new ContactItem(firstName, lastName, phoneNumber, emailAddress);
                    contacts.add(contact);
                }
            } else {
                contacts = backupList;
                throw new InputMismatchException("WARNING: filename is not a TaskList, loading failed.");
            }
        } catch (FileNotFoundException ex) {
            contacts = backupList;
            throw new IllegalArgumentException("WARNING: file not found, loading failed.");
        } catch (IOException ex) {
            contacts = backupList;
            throw new IllegalArgumentException("An unexpected error has occured, loading failed.");
        }
    }

    public void update(int contactIndex, String firstName, String lastName, String phoneNumber, String emailAddress) {
        if (contactIndexValid(contactIndex)) {
            ContactItem contact = contacts.get(contactIndex);
            contact.update(firstName, lastName, phoneNumber, emailAddress);
        } else {
            throw new IllegalArgumentException("Invalid index, please select a contact listed above.");
        }
    }

    public String getContactFirstName(int contactIndex) {
        if (contactIndexValid(contactIndex)) {
            ContactItem contact = contacts.get(contactIndex);
            return contact.getFirstName();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a contact listed above.");
        }
    }

    public String getContactLastName(int contactIndex) {
        if (contactIndexValid(contactIndex)) {
            ContactItem contact = contacts.get(contactIndex);
            return contact.getLastName();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a contact listed above.");
        }
    }

    public String getContactPhoneNumber(int contactIndex) {
        if (contactIndexValid(contactIndex)) {
            ContactItem contact = contacts.get(contactIndex);
            return contact.getPhoneNumber();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a contact listed above.");
        }
    }

    public String getContactEmailAddress(int contactIndex) {
        if (contactIndexValid(contactIndex)) {
            ContactItem contact = contacts.get(contactIndex);
            return contact.getEmailAddress();
        } else {
            throw new IllegalArgumentException("Invalid index, please select a contact listed above.");
        }
    }
}
