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
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
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
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorLabel;
    @FXML
    private Label welcomeLabelM;
    @FXML
    private Label welcomeLabelL;

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


    public void Logout(ActionEvent event) throws IOException {
        currentUser = new User();
        fxmlLoader = new FXMLLoader(Controller.class.getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
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
    public void switchSceneToCreateAccount(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Controller.class.getResource("CreateAccountPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField newUserName, newUserPass;
    @FXML
    private Label nameTakenLabel, typeNotSelectedLabel;
    @FXML
    private RadioButton memberBttn,LibrarianBttn;

    public void addUser(ActionEvent event) throws IOException {
        nameTakenLabel.setText("");
        typeNotSelectedLabel.setText("");
        String name = newUserName.getText();
        String password = newUserPass.getText();
        System.out.println("new user will be: "+name+" and pass is "+password);
        //make sure the userName is unique
        for (User user : users) {
            if (user.getName().equals(name)) {
                System.out.println("User name already taken please use another name.");
                nameTakenLabel.setText("Name already taken.");
                return;
            }
        }
        if(memberBttn.isSelected()){
            System.out.println("member button was selected");
            Member temp = new Member(name, password);
            users.add(temp);
        }
        else if (LibrarianBttn.isSelected()){
            System.out.println("librarian button was selected");
            Librarian temp = new Librarian( name, password);
            users.add(temp);
        }
        else{
            System.out.println("No member type was selected!!");
            typeNotSelectedLabel.setText("Please select a type.");
            return;
        }
        displayUsers();
        //switch to login screen
        fxmlLoader = new FXMLLoader(Controller.class.getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void addBook(){
        Scanner input = new Scanner(System.in);//____________________________________________________
        String bookName;
        String authorName;
        String isbn;
        int copies;
        System.out.println("Enter the name of the book: ");
        bookName = input.nextLine();
        System.out.println("Enter the name of the author: ");
        authorName = input.nextLine();
        System.out.println("Enter the ISBN: ");
        isbn = input.nextLine();
        System.out.println("Enter the number of copies: ");
        copies = input.nextInt();
        Book newBook = new Book(isbn, bookName, authorName,copies);
        books.add(newBook);
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
        if(currentUser instanceof Member) {
            System.out.println("1: checkout a book\n2: return to menu");
            int key = input.nextInt();
            switch (key) {
                case 1:
                    System.out.println("Enter the book index you want to checkout: ");
                    int bookNum = input.nextInt();
                    if (bookNum <= 0 || bookNum > books.size()) {
                        System.out.println("Invalid Index");
                        break;
                    } else {
                        if(books.get(bookNum-1).noMoreCopies()){
                            System.out.println("Sorry, No more copies of this book.");
                        }
                        //if the user already has this book then they cannot get it again.
                        else{
                            ArrayList<Transaction> myTransactions = getUserTransactions();
                            boolean alreadyHave = false;
                            for (Transaction myTransaction : myTransactions) {
                                if (myTransaction.getBookIsbn().equals(books.get(bookNum - 1).getIsbn())) {
                                    alreadyHave = true;
                                    break;
                                }
                            }
                            if(alreadyHave){
                                System.out.println("You already have this book");
                            }else {
                                Transaction newTransaction = new Transaction(currentUser.getId(), currentUser.getName(), books.get(bookNum - 1).getIsbn(), books.get(bookNum - 1).getName());
                                books.get(bookNum - 1).decrementCopy();
                                transactions.add(newTransaction);
                                System.out.println("Transaction successful");
                            }
                        }
                    }
                    break;
                case 2:
                    break;
            }
        }
    }
    public void printAllTransactions(){
        for(int i = 0; i<transactions.size(); i++){
            System.out.println(i+1);
            System.out.println(transactions.get(i).toString());
        }
        if(transactions.isEmpty()){
            System.out.println("No transactions");
        }
    }
    public void printCurrentUserTransactions(){
        Scanner input = new Scanner(System.in);
        if(transactions.isEmpty()){
            System.out.println("No Transactions");
            return;
        }
        ArrayList<Transaction> myTransactions = getUserTransactions();
        System.out.println("*******************************************************");
        for(int i = 0; i<myTransactions.size(); i++){
            System.out.println(i+1);
            System.out.println(transactions.get(i).getBookInfo());
        }
        if(myTransactions.isEmpty()){
            System.out.println("You have no transactions");
            return;
        }
        System.out.println("*******************************************************");

        System.out.println("\t1: remove a book\n\t2: return to menu");
        int choice = input.nextInt();
        if(choice == 1){
            System.out.println("which numbered index do you want to remove: ");
            int key = input.nextInt();
            Transaction removeThisTransaction = myTransactions.get(key-1);
            transactions.remove(removeThisTransaction);
            System.out.println("Successfully removed "+removeThisTransaction.getBookName());
            //put the copy back into the library by incrementing the count of the book
            for(int i = 0; i<books.size(); i++){
                if(books.get(i).getIsbn().equals(removeThisTransaction.getBookIsbn())){
                    books.get(i).incrementCopy();
                }
            }
        }
    }
    //returns an array of the currently logged-in users transactions.
    private ArrayList<Transaction> getUserTransactions(){
        ArrayList<Transaction> myTransactions = new ArrayList<>();
        for(int i = 0; i<transactions.size(); i++){
            if(transactions.get(i).getUserId() == currentUser.getId()){
                myTransactions.add(transactions.get(i));
            }
        }
        return myTransactions;
    }

    private void loadDefaultUser() throws FileNotFoundException {
        File inFile = new File("C:\\School\\LibraryManagementSystem\\src\\main\\defaultUsers.txt");
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
        File inFile = new File("C:\\School\\LibraryManagementSystem\\src\\main\\defaultBooks.txt");
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
            Transaction newTransaction = new Transaction(users.get(1).getId(), users.get(1).getName(), books.get(i).getIsbn(), books.get(i).getName());
            transactions.add(newTransaction);
        }
        for(int i = 0; i<4; i++){
            Transaction newTransaction = new Transaction(users.get(2).getId(), users.get(2).getName(), books.get(i).getIsbn(), books.get(i).getName());
            transactions.add(newTransaction);
        }
        for(int i = 15; i<19; i++){
            Transaction newTransaction = new Transaction(users.get(3).getId(), users.get(3).getName(), books.get(i).getIsbn(), books.get(i).getName());
            transactions.add(newTransaction);
        }
        for(int i = 20; i<24; i++){
            Transaction newTransaction = new Transaction(users.get(4).getId(), users.get(4).getName(), books.get(i).getIsbn(), books.get(i).getName());
            transactions.add(newTransaction);
        }
        for(int i = 5; i<10; i++){
            Transaction newTransaction = new Transaction(users.get(5).getId(), users.get(5).getName(), books.get(i).getIsbn(), books.get(i).getName());
            transactions.add(newTransaction);
        }
    }


    @FXML

    public void switchToHelloScene(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Controller.class.getResource("hello-view.fxml"));
        //root = FXMLLoader.load(getClass().getResource("C:\\School\\LibraryManagementSystem\\src\\main\\resources\\com\\example\\librarymanagementsystem\\hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }



}