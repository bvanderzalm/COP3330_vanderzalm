public class ContactItem {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public ContactItem(String firstName, String lastName, String phoneNumber, String emailAddress) {

        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new IllegalArgumentException("No input was detected. Returning to main menu.");
        } else if (firstName.isBlank()) {
            throw new IllegalArgumentException("Invalid first name, you can't leave it blank.");
        } else if (lastName.isBlank()) {
            throw new IllegalArgumentException("Invalid last name, you can't leave it blank.");
        } else if (phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid phone number, you can't leave it blank.");
        } else if (emailAddress.isBlank()) {
            throw new IllegalArgumentException("Invalid email address, you can't leave it blank.");
        } else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }
    }

    public void update(String firstName, String lastName, String phoneNumber, String emailAddress) {

        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new IllegalArgumentException("No input was detected. Returning to main menu.");
        } else if (firstName.isBlank()) {
            throw new IllegalArgumentException("Invalid first name, you can't leave it blank.");
        } else if (lastName.isBlank()) {
            throw new IllegalArgumentException("Invalid last name, you can't leave it blank.");
        } else if (phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid phone number, you can't leave it blank.");
        } else if (emailAddress.isBlank()) {
            throw new IllegalArgumentException("Invalid email address, you can't leave it blank.");
        } else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s%n" + "Phone: %s%n" + "Email: %s",
                firstName, lastName, phoneNumber, emailAddress);
    }
}
