package com.example.librarymanagementsystem;

import java.util.UUID;

public class Member extends User {
    private UUID libraryCardNumber;
    private String stringID;


    public Member() {
        libraryCardNumber = UUID.randomUUID();
    }

    public Member(String name, String pass) {
        super(name, pass);
        libraryCardNumber = UUID.randomUUID();
        stringID = libraryCardNumber.toString();
    }

    public UUID getId() {
        return libraryCardNumber;
    }
    public String getStringID(){return stringID;}

    public void printInfo() {
        super.printInfo();
        System.out.println("Library Card Number: " + libraryCardNumber);
    }

}