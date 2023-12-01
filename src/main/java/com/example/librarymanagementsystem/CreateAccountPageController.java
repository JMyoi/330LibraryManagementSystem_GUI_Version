package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountPageController {
    Library library = new Library();
    @FXML
    Parent root;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    public void receieveLibrary(Library lib){
        library = lib;
        System.out.println("library received in createAccount page controller: ");
    }

    public void back(ActionEvent actionEvent) throws IOException {
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
    private TextField newUserName, newUserPass;
    @FXML
    private Label nameTakenLabel, typeNotSelectedLabel;
    @FXML
    private RadioButton memberBttn,LibrarianBttn;
    public void addUser(ActionEvent actionEvent) throws IOException {
        nameTakenLabel.setText("");
        typeNotSelectedLabel.setText("");
        String name = newUserName.getText();
        String password = newUserPass.getText();
        System.out.println("new user will be: "+name+" and pass is "+password);
        if(library.isUserNameTaken(name)){
            nameTakenLabel.setText("UserName Taken.");
            return;
        }
        if(memberBttn.isSelected()){
            System.out.println("member button was selected");
            library.addUser(name, password, "M");
            back(actionEvent);
        }
        else if (LibrarianBttn.isSelected()){
            System.out.println("librarian button was selected");
           library.addUser(name, password, "L");
            back(actionEvent);
        }
        else{
            System.out.println("No member type was selected!!");
            typeNotSelectedLabel.setText("Please select a type.");
        }


    }
}
