import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest {

    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> new ContactItem("", "", "", ""));
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        assertDoesNotThrow(() -> new ContactItem("bob", "bob", "303-303-3030", ""));
    }

    @Test
    public void creationSucceedsWithBlankFirstName() {
        assertDoesNotThrow(() -> new ContactItem("", "bob", "303-303-3030", "bob@bob.com"));
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        assertDoesNotThrow(() -> new ContactItem("bob", "", "303-303-3030", "bob@bob.com"));
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        assertDoesNotThrow(() -> new ContactItem("bob", "bob", "", "bob@bob.com"));
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        assertDoesNotThrow(() -> new ContactItem("bob", "bob", "303-303-3030", "bob@bob.com"));
    }

    @Test
    public void editingFailsWithAllBlankValues() {
        ContactItem contact = new ContactItem("bob", "bob", "303-303-3030", "bob@bob.com");
        assertThrows(IllegalArgumentException.class, () -> contact.update("", "", "", ""));
    }

    @Test
    public void editingSucceedsWithBlankEmail() {
        ContactItem contact = new ContactItem("bob", "bob", "303-303-3030", "bob@bob.com");
        assertDoesNotThrow(() -> contact.update("sponge", "bob", "123-123-1234", ""));
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactItem contact = new ContactItem("bob", "bob", "303-303-3030", "bob@bob.com");
        assertDoesNotThrow(() -> contact.update("", "bob", "123-123-1234", "bob@sponge.com"));
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactItem contact = new ContactItem("bob", "bob", "303-303-3030", "bob@bob.com");
        assertDoesNotThrow(() -> contact.update("sponge", "", "123-123-1234", "bob@sponge.com"));
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactItem contact = new ContactItem("bob", "bob", "303-303-3030", "bob@bob.com");
        assertDoesNotThrow(() -> contact.update("sponge", "bob", "", "bob@sponge.com"));
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactItem contact = new ContactItem("bob", "bob", "303-303-3030", "bob@bob.com");
        assertDoesNotThrow(() -> contact.update("sponge", "bob", "123-123-1234", "bob@sponge.com"));
    }

//    @Test
//    public void testToString() {
//
//    }

}