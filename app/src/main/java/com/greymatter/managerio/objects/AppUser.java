package com.greymatter.managerio.objects;

public class AppUser extends AObject {
    private String username, email;
    private boolean contactsImported;

    public AppUser() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isContactsImported() {
        return contactsImported;
    }

    public void setContactsImported(boolean contactsImported) {
        this.contactsImported = contactsImported;
    }
}
