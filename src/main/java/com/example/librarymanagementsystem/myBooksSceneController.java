package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class myBooksSceneController {
    Library library = new Library();
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    Parent root;


    public void displayTransactions(Library lib){
        library = lib;
        System.out.println("library received in myTransactions");
        //get current user transactions from library
        ArrayList<Transaction> myTransactions = library.getUserTransactions();
        ObservableList<Transaction> Transactions = FXCollections.observableArrayList(myTransactions);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("bookIsbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("bookName"));
        transactionsTable.setItems(Transactions);

    }
    @FXML
    private TableColumn<Transaction, String> isbnColumn;

    @FXML
    private TableColumn<Transaction, String> titleColumn;

    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private Label errorLabel;

    @FXML
    void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MemberMenu.fxml"));
        root = loader.load();
        memberMenuController memberController = loader.getController();
        memberController.displayMemberMenu(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void removeTransaction(ActionEvent event) {
        errorLabel.setText("");
        Transaction clicedTransaction = transactionsTable.getSelectionModel().getSelectedItem();
        if(clicedTransaction == null){
            errorLabel.setText("No book Selected");
        }
        else{
            library.removeTransaction(clicedTransaction);
            displayTransactions(library);
        }
    }
}
