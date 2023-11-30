package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorLabel;
    @FXML
    Parent root;
    Library library = new Library();

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String UserName = userNameText.getText();
        String pass = passwordText.getText();
        System.out.println(UserName+pass);
        if(library.logIn(UserName, pass)){
            if(library.getCurrentUserType().equals("M")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MemberMenu.fxml"));
                root = loader.load();
                memberMenuController membermenuController = loader.getController();
                membermenuController.displayName(UserName, library);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
            else if (library.getCurrentUserType().equals("L")) {
                fxmlLoader = new FXMLLoader(Controller.class.getResource("LibrarianMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }
            System.out.println("current user is "+library.getCurrentUserName());
        }
        else{
            errorLabel.setText("Incorrect credentials.");
            System.out.println("Incorrect credentials");
        }
        System.out.println("current user is "+library.getCurrentUserName());
        library.addBob();
        library.displayUsers();
    }
    public void receiveLibrary(Library lib){
        library = lib;
        System.out.println("library received in login controller");
    }

    public void switchSceneToCreateAccount(ActionEvent actionEvent) {
    }


}
