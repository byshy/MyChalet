package com.byshy.mychalet.Models;

public class Chalet {
    private String Name;
    private String Address;
    private String ChaletmEmail;
    private int ChaletNumber ;
    public Chalet(){}

    public Chalet(String name, String address, String chaletmEmail, int chaletNumber) {
        Name = name;
        Address = address;
        ChaletmEmail = chaletmEmail;
        ChaletNumber = chaletNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getChaletmEmail() {
        return ChaletmEmail;
    }

    public void setChaletmEmail(String chaletmEmail) {
        ChaletmEmail = chaletmEmail;
    }

    public int getChaletNumber() {
        return ChaletNumber;
    }

    public void setChaletNumber(int chaletNumber) {
        ChaletNumber = chaletNumber;
    }
}
