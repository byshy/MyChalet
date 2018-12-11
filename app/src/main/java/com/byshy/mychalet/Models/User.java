package com.byshy.mychalet.Models;

public class User {

    private String mFirstName;
//    private String mLastName;
    private String mEmail;
//    private int mUserType;
//    private Chalet mChalet;

    public User(){}

    public User(String mFirstName, String mEmail) {
        this.mFirstName = mFirstName;
        this.mEmail = mEmail;
//        this.mUserType = 0;
//        this.mChalet = null;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

//    public String getLastName() {
//        return mLastName;
//    }
//
//    public void setLastName(String mLastName) {
//        this.mLastName = mLastName;
//    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

//    public int getUserType() {
//        return mUserType;
//    }
//
//    public void setUserType(int mUserType) {
//        this.mUserType = mUserType;
//    }
//
//    public Chalet getChalet() {
//        return mChalet;
//    }
//
//    public void setChalet(Chalet mChalet) {
//        this.mChalet = mChalet;
//    }

}
