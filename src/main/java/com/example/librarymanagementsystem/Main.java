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

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("330 Library Management System");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

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