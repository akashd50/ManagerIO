package com.greymatter.managerio.objects;

public class Contact extends AObject {
    private String firstName, lastName, countryCode, mobileNo;
    private AppUser owningUser;
    public Contact() {
        super();
        firstName = "";
        lastName = "";
        countryCode = "";
        mobileNo = "";
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
