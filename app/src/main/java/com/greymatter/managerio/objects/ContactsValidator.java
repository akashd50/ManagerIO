package com.greymatter.managerio.objects;

public class ContactsValidator {
    public static boolean validateContact(Contact contact) throws Exception {
        if (contact.getFirstName().length() == 0) {
            throw new Exception("Empty First Name");
        }else if(contact.getLastName().length() == 0) {
            throw new Exception("Empty Last Name");
        }
        return true;
    }
}
