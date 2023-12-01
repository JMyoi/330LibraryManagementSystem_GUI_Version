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


public class memberMenuController {
    Library library = new Library();
    @FXML
    Parent root;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private Label memberIdLabel;
    @FXML
    Label welcomeLabelM;
    public void displayMemberMenu(Library lib){
        library = lib;
        System.out.println("library received in memberMenu Controller");
        welcomeLabelM.setText("Welcome Member: "+ library.getCurrentUserName());
        memberIdLabel.setText("Member ID: " +library.getCurrentUser().getId());
        System.out.println(library.getCurrentUserName());

    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        System.out.println("Logout pressed From memberController: ");
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
    @FXML
    void displayBooks(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("memberBooksScene.fxml"));
        root = loader.load();
        memberBooksSceneController memberBooksController = loader.getController();
        memberBooksController.displayBooks(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void displayUserTransactions(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("myBooksScene.fxml"));
        root = loader.load();
        myBooksSceneController myBookController = loader.getController();
        myBookController.displayTransactions(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
