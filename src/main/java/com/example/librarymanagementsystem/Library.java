package com.example.librarymanagementsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<Transaction> transactions;
    private User currentUser;
    public Library(){
        books = new ArrayList<>();
        users = new ArrayList<>();
        transactions = new ArrayList<>();
        currentUser = new User();
        try {
            loadDefaultUser();
            loadDefaultBooks();
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
            System.out.println(e.getMessage());
        }
        loadDefaultTransactions();
    }

    public User getCurrentUser(){
        return currentUser;
    }
    public String getCurrentUserName(){ return currentUser.getName();}
    public ArrayList<Book> getBooks(){return books;}

    public boolean logIn(String Name, String Pass) throws IOException {
        for(User user: users){
            if(user.getName().equals(Name) && user.authenticate(Pass)){
                currentUser = user;
                System.out.println("logged in");
                return true;
            }
        }
        return false;
    }

    public String getCurrentUserType(){
            if(currentUser instanceof Member){
                return "M";
            }
            else if(currentUser instanceof Librarian){
                return "L";
            }
            return "NONE";
    }


    public void Logout(){
        currentUser = new User();
        System.out.println("logged out sucessfull: userName: "+currentUser.getName());
    }

    //can only be done by librarians
    public void displayUsers(){
        if(users.isEmpty()){
            System.out.println("No Users");
        }
        else{
            System.out.println("******************** Users *************************");
            for(User user: users){
                user.printInfo();
            }
            System.out.println("****************************************************");
        }
    }
    public ArrayList<Member> getMembers(){
        ArrayList<Member> memArry = new ArrayList<>();
        for(User user: users){
            if(user instanceof Member){
                memArry.add((Member) user);
            }
        }
        System.out.println("returning all members");
        for(Member member: memArry){
            member.printInfo();
        }
        return memArry;
    }

    public ArrayList<Librarian> getLibrarians(){
        ArrayList<Librarian> libArry = new ArrayList<>();
        for(User user: users){
            if(user instanceof Librarian){
                libArry.add((Librarian) user);
            }
        }
        System.out.println("returning all librarians: ");
        for(Librarian librarian: libArry){
            librarian.printInfo();
        }
        return libArry;
    }

   public boolean isUserNameTaken(String name){
       for (User user : users) {
           if (user.getName().equals(name)) {
               System.out.println("User name already taken please use another name.");
               return true;
           }
       }
       return false;
   }

    public void addUser(String name, String pass, String type)  {
        if(type.equals("M")){
            Member temp = new Member(name, pass);
            users.add(temp);
            System.out.println("member: "+name+"added successfully");
        } else if (type.equals("L")) {
            Librarian temp = new Librarian(name, pass);
            users.add(temp);
            System.out.println("librarian: "+name+" added successfully");
        }

    }

    public void addBook(String isbn, String bookName, String authorName,int copies){
        Book newBook = new Book(isbn, bookName, authorName, copies);
        books.add(newBook);
        System.out.println("book added.");
    }

    public void displayBooks() {
        Scanner input = new Scanner(System.in);//____________________________________________________
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
            return;
        } else {
            System.out.println("********************Books in the library*************************");
            for (int i = 0; i<books.size(); i++) {
                System.out.println(i+1 +" ");
                books.get(i).printInfo();
            }
            System.out.println("***************************************************************");
        }
    }

    public boolean alreadyHaveThisBook(Book b){
        ArrayList<Transaction> myTransactions = getUserTransactions();
        for (Transaction myTransaction : myTransactions) {
            if (myTransaction.getBookIsbn().equals(b.getIsbn())) {
                return true;
            }
        }
        return false;
    }

    public void addTransaction(Book b){
        Transaction newTransaction = new Transaction(currentUser.getId(), currentUser.getName(), b.getIsbn(), b.getBookName());
        transactions.add(newTransaction);
        for(Book book: books){
            if(book.getIsbn().equals(b.getIsbn())){
                book.decrementCopy();
                break;
            }
        }
        System.out.println("Transaction successful");
    }

    public ArrayList<Transaction> getAllTransactions(){
        return transactions;
    }

    public void removeTransaction(Transaction toRemove){
        transactions.remove(toRemove);
        System.out.println("Successfully removed "+toRemove.getBookName());
        for (Book book : books) {
            if (book.getIsbn().equals(toRemove.getBookIsbn())) {
                book.incrementCopy();
            }
        }
    }

    //returns an array of the currently logged-in users transactions.
    public ArrayList<Transaction> getUserTransactions(){
        ArrayList<Transaction> myTransactions = new ArrayList<>();
        for(int i = 0; i<transactions.size(); i++){
            if(transactions.get(i).getUserId() == currentUser.getId()){
                myTransactions.add(transactions.get(i));
            }
        }
        return myTransactions;
    }

    private void loadDefaultUser() throws FileNotFoundException {


        File inFile = new File("src/main/defaultUsers.txt");


        Scanner in = new Scanner(inFile);
        while(in.hasNext()){
            String userName = in.next();
            String Password = in.next();
            String type = in.next();
            if (type.equals("L")) {
                Librarian temp = new Librarian( userName, Password);
                users.add(temp);
            } else if (type.equals("M")) {
                Member temp = new Member(userName, Password);
                users.add(temp);
            }
        }

        in.close();
    }
    private void loadDefaultBooks() throws FileNotFoundException{


        File inFile = new File("src/main/defaultBooks.txt");


        Scanner in = new Scanner(inFile);
        while(in.hasNext()){
            String BookName = in.nextLine();
            String author = in.nextLine();
            String ISBN = in.nextLine();
            String amount = in.nextLine();
            Book temp = new Book(ISBN, BookName, author, Integer.parseInt(amount));
            books.add(temp);
            //System.out.println("name: "+BookName+"author: "+author+"ISBN: "+ISBN+"amount: "+amount);
        }
    }
    private void loadDefaultTransactions(){
        //make transactions for user 1
        for(int i = 35; i<40; i++){
            Transaction newTransaction = new Transaction(users.get(1).getId(), users.get(1).getName(), books.get(i).getIsbn(), books.get(i).getBookName());
            transactions.add(newTransaction);
        }
        for(int i = 0; i<4; i++){
            Transaction newTransaction = new Transaction(users.get(2).getId(), users.get(2).getName(), books.get(i).getIsbn(), books.get(i).getBookName());
            transactions.add(newTransaction);
        }
        for(int i = 15; i<19; i++){
            Transaction newTransaction = new Transaction(users.get(3).getId(), users.get(3).getName(), books.get(i).getIsbn(), books.get(i).getBookName());
            transactions.add(newTransaction);
        }
        for(int i = 20; i<24; i++){
            Transaction newTransaction = new Transaction(users.get(4).getId(), users.get(4).getName(), books.get(i).getIsbn(), books.get(i).getBookName());
            transactions.add(newTransaction);
        }
        for(int i = 5; i<10; i++){
            Transaction newTransaction = new Transaction(users.get(5).getId(), users.get(5).getName(), books.get(i).getIsbn(), books.get(i).getBookName());
            transactions.add(newTransaction);
        }
    }





}