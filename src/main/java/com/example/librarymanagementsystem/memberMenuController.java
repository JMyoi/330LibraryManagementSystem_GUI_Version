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
    Label welcomeLabelM;
    public void displayName(String username, Library lib){
        welcomeLabelM.setText("Welcome Member: "+ username);
        System.out.println(username);
        library = lib;
//        library.displayUsers();
//        System.out.println("CurrentUser = "+ library.getCurrentUserName());
    }

    public void Logout(ActionEvent event) throws IOException {
        System.out.println("Logout pressed From memberController: ");
        library.displayUsers();
        System.out.println("CurrentUser = "+ library.getCurrentUserName());
        library.Logout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        loginController logincontroller = loader.getController();
        logincontroller.receiveLibrary(library);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
