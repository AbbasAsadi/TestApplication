package com.example.testapplication.model;

public class User {
    private String mName;
    private String mFamilyName;
    private String mEmail;
    private String mPhoneNumber;
    private String mPostalCode;
    private String mCityName;

    public User(String name, String familyName, String email, String phoneNumber, String postalCode, String cityName) {
        mName = name;
        mFamilyName = familyName;
        mEmail = email;
        mPhoneNumber = phoneNumber;
        mPostalCode = postalCode;
        mCityName = cityName;
    }

    public User(String email, String phoneNumber, String postalCode, String cityName) {
        mEmail = email;
        mPhoneNumber = phoneNumber;
        mPostalCode = postalCode;
        mCityName = cityName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFamilyName() {
        return mFamilyName;
    }

    public void setFamilyName(String familyName) {
        mFamilyName = familyName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public void setPostalCode(String postalCode) {
        mPostalCode = postalCode;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }
}
