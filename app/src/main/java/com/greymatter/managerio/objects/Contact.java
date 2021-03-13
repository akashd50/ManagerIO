package com.greymatter.managerio.objects;

public class Contact {
    private int id;
    private String firstName, lastName, mobileNo;

    public Contact() {
        id = -1;
        firstName = "";
        lastName = "";
        mobileNo = "";
    }

    public int getId() {
        return id;
    }

    public Contact setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public Contact setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }
}
