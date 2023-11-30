package com.example.librarymanagementsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("330 Library management System");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorLabel;


//    @FXML
//    public void switchSceneToCreateAccount(ActionEvent event) throws IOException {
//        System.out.println("current user is "+library.getCurrentUserName());
//        library.displayUsers();
//
//        fxmlLoader = new FXMLLoader(Controller.class.getResource("CreateAccountPage.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(fxmlLoader.load());
//        stage.setScene(scene);
//        stage.show();
//
//    }

    @FXML
    public void switchTologinScene(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Controller.class.getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }
}



//            Timer timer = new Timer();
//            TimerTask task = new TimerTask() {
//                @Override public void run() {
//                    System.out.println("Current user after: " + library.getCurrentUser());
//                }
//            };
//            timer.scheduleAtFixedRate(task, 0, 1000);


//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//            stage.setTitle("330 Library management System");
//            stage.setScene(scene);
//            stage.show();