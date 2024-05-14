package PracticalTest;

import java.util.ArrayList;

public class AddressBookImpl implements AddressBook {
    ArrayList<Contact> list = new ArrayList<>();
    @Override
    public void addContact(String name, String company, String email, String phone) {
        Contact contact = new Contact(name, company, email, phone);
        list.add(contact);
    }

    @Override
    public ArrayList<Contact> findContactByName(String name) {
        ArrayList<Contact> contactList = new ArrayList<>();
        for (Contact contact : list) {
            if (contact.getName().equals(name)) {
                contactList.add(contact);
            }
        }
        return contactList;

    }

    @Override
    public ArrayList<Contact> displayContacts() {
        return list;
    }
}
