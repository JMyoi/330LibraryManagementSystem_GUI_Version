package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryMenuController {
    Library library = new Library();
    @FXML
    Parent root;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Label welcomeLabelL;
    @FXML
    private Label LibrarianIdLabel;
    public void displayLibrarianMenu(Library lib){
        System.out.println("displaying the librarian menu");
        library = lib;
        welcomeLabelL.setText("Welcome Librarian: "+ library.getCurrentUserName());
        LibrarianIdLabel.setText("Librarian ID: "+library.getCurrentUser().getId());
        System.out.println("current user is "+library.getCurrentUserName());

    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        System.out.println("Logout pressed From LibrarymenuController: ");
        library.displayUsers();
        System.out.println("CurrentUser = "+ library.getCurrentUserName());
        library.Logout();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        loginController logincontroller = loader.getController();
        logincontroller.receiveLibrary(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayUsers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("allUsers.fxml"));
        root = loader.load();
        allUsersController alluserscontroller = loader.getController();
        alluserscontroller.receiveLibrary(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void DisplayBooks(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BooksSceneLibrarian.fxml"));
        root = loader.load();
        BooksSceneLibrarianController bookController = loader.getController();
        bookController.receiveLibrary(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void DisplayTransactions(ActionEvent actionEvent) {
    }


}
