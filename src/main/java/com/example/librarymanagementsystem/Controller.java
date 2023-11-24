package com.example.librarymanagementsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;



public class Controller {

    @FXML
    TextField userNameText;
    @FXML
    PasswordField passwordText;
    @FXML
    public void logIn() {
        String UserName = userNameText.getText();
        String pass = passwordText.getText();
        System.out.println(UserName+pass);
    }


    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private Circle myCircle;
    private double x;
    private double y;
    public void up(ActionEvent e){
        myCircle.setCenterY(y-=1);
        System.out.println("up");
    }
    public void down(ActionEvent e){
        myCircle.setCenterY(y+=1);
        System.out.println("down");
    }
    public void left(ActionEvent e){
        myCircle.setCenterX(x-=1);
        System.out.println("left");
    }
    public void right(ActionEvent e){
        myCircle.setCenterX(x+=1);
        System.out.println("right");
    }

    @FXML
    private Stage stage;
    @FXML

    private Scene scene;
    @FXML

    private Parent root;
    @FXML

    private FXMLLoader fxmlLoader;


    @FXML
    public void switchTologinScene(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Controller.class.getResource("login.fxml"));
        //root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
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
