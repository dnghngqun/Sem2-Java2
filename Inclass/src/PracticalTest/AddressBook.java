package PracticalTest;

import java.util.ArrayList;

public interface AddressBook {

    void addContact(String name, String company , String email, String phone);
    ArrayList<Contact> findContactByName(String name);
    ArrayList<Contact> displayContacts();
}
