package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;


public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcomo JavaFX Application!");
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


    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchTologinScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage =(Stage)((Node).event.getSource()).getScene().getWindow();_
    }

}
