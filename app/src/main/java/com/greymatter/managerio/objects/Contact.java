package com.greymatter.managerio.objects;

public class Contact extends AObject {
    private String firstName, lastName, mobileNo;
    private AppUser owningUser;
    public Contact() {
        super();
        firstName = "";
        lastName = "";
        mobileNo = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public AppUser getOwningUser() {
        return owningUser;
    }

    public void setOwningUser(AppUser owningUser) {
        this.owningUser = owningUser;
    }
}
