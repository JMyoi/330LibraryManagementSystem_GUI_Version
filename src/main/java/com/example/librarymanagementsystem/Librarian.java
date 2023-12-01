package com.example.librarymanagementsystem;

import java.util.UUID;
public class Librarian extends User  {
    UUID employeeId;
    private String stringID;

    Librarian(){
        employeeId = UUID.randomUUID();
    }

    Librarian( String name, String pass){
        super(name, pass);
        employeeId = UUID.randomUUID();
        stringID = employeeId.toString();
    }

    public UUID getId() {
        return employeeId;
    }
    public String getStringID(){return stringID;}

    public void printInfo(){
        super.printInfo();
        System.out.println("Employee Id: "+this.employeeId);
    }
}

