package PracticalTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Application {

    private final BufferedReader reader;
    AddressBookImpl addressBookImpl = new AddressBookImpl();
    public Application(BufferedReader reader) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private int menu() throws IOException {
        System.out.println("===========Address Books===========");
        System.out.println("1. Add new contact");
        System.out.println("2. Find a contact by name");
        System.out.println("3. Display contacts");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        return Integer.parseInt(reader.readLine());
    }

    private void addContact() throws IOException {
        System.out.print("Enter contact name: ");
        String name = reader.readLine();
        System.out.print("Enter contact company: ");
        String company = reader.readLine();
        System.out.print("Enter contact email: ");
        String email = reader.readLine();
        System.out.print("Enter contact phone number: ");
        String phoneNumber = reader.readLine();
        addressBookImpl.addContact(name, company, email, phoneNumber);
        System.out.println("Contact added successfully!");
    }

    private void findContactByName() throws IOException {
        System.out.print("Enter contact name: ");
        String name = reader.readLine();
        ArrayList<Contact> contactList = addressBookImpl.findContactByName(name);
        if (contactList.isEmpty()) {
            System.out.println("Contact not found!");
        } else {
            for (Contact contact : contactList) {
                System.out.println("Name: " + contact.getName()+ " Phone number: " + contact.getPhone());
            }
        }
    }

    private void displayContacts() {
        ArrayList<Contact> contactList = addressBookImpl.displayContacts();
        if (contactList.isEmpty()) {
            System.out.println("No contacts found!");
        } else {
            System.out.println("ContactName                 Company                 Email                 Phone number");
            for (Contact contact : contactList) {
                System.out.printf("%-28s%-24s%-22s%-20s\n", contact.getName(), contact.getCompany(), contact.getEmail(), contact.getPhone());
            }
        }
    }

    private void start() throws IOException {
        while (true){
            int choice = menu();
            switch (choice){
                case 1:
                    addContact();
                    break;
                case 2:
                    findContactByName();
                    break;
                case 3:
                    displayContacts();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
    public static void main(String[] args) {
        Application application = new Application(new BufferedReader(new InputStreamReader(System.in)));
        try {
            application.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
