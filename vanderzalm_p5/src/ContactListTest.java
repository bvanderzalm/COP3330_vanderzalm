import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {

    @Test
    public void addingItemsIncreasesSize() {
        ContactList contacts = new ContactList();
        int originalSize = contacts.getSize();

        assertDoesNotThrow(() -> contacts.add(new ContactItem("bob", "", "", "")));
        assertEquals(contacts.getSize(), originalSize + 1);
    }

    @Test
    public void editingItemsFailsWithAllBlankValues() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("bob", "smith", "", ""));

        assertThrows(IllegalArgumentException.class, () -> contacts.update(0, "", "", "", ""));
    }

    @Test
    public void editingItemsFailsWithInvalidIndex() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("bob", "smith", "", ""));

        assertThrows(IllegalArgumentException.class, () -> contacts.update(1000, "bill", "", "", ""));
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("bob", "smith", "", ""));

        assertDoesNotThrow(() -> contacts.update(0, "", "smith", "123-123-1234", "smith@email.com"));
        assertEquals("", contacts.getContactFirstName(0));
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("bob", "smith", "", ""));

        assertDoesNotThrow(() -> contacts.update(0, "bob", "", "123-123-1234", "smith@email.com"));
        assertEquals("", contacts.getContactLastName(0));
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("bob", "smith", "", ""));

        assertDoesNotThrow(() -> contacts.update(0, "bob", "smith", "", "smith@email.com"));
        assertEquals("", contacts.getContactPhoneNumber(0));
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("bob", "smith", "", ""));

        assertDoesNotThrow(() -> contacts.update(0, "bob", "smith", "123-123-1234", "smith@email.com"));
        assertEquals("bob", contacts.getContactFirstName(0));
        assertEquals("smith", contacts.getContactLastName(0));
        assertEquals("123-123-1234", contacts.getContactPhoneNumber(0));
        assertEquals("smith@email.com", contacts.getContactEmailAddress(0));
    }

    @Test
    public void newListIsEmpty() {
        ContactList contacts = new ContactList();
        assertTrue(contacts.isEmpty());
    }

    @Test
    public void removingItemsDecreasesSize() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("billy", "bob", "123-123-1234", "BB@email.com"));
        contacts.add(new ContactItem("bob", "smith", "123-123-1235", "bsmith@email.com"));
        int originalSize = contacts.getSize();

        assertDoesNotThrow(() -> contacts.remove(0));
        assertEquals(contacts.getSize(), originalSize - 1);
    }

    @Test
    public void removingItemsFailsWithInvalidIndex() {
        ContactList contacts = new ContactList();
        contacts.add(new ContactItem("bob", "smith", "123-123-1234", ""));

        assertThrows(IllegalArgumentException.class, () -> contacts.remove(1000));
    }

    @Test
    public void savedContactListCanBeLoaded() {
        // File is on GitHub repo labeled as the string below.
        ContactList contacts = new ContactList();
        assertDoesNotThrow(() -> contacts.load("TestCaseContactListOutput.txt"));
    }
}