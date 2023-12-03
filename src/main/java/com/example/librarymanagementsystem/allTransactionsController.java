package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class allTransactionsController {
    Library library = new Library();
    @FXML
    Parent root;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<Transaction, String> isbnColumn;

    @FXML
    private TableColumn<Transaction, String> memberIdColumn;

    @FXML
    private TableColumn<Transaction, String> nameColumn;

    @FXML
    private TableColumn<Transaction, String> titleColumn;
    void showTransactions(Library lib){
        library = lib;
        System.out.println("library received by allTransactions controller");
        ArrayList<Transaction> allTransactions = library.getAllTransactions();
        ObservableList<Transaction> Transactions = FXCollections.observableArrayList(allTransactions);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("bookIsbn"));
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("stringID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("userName"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("bookName"));
        transactionsTable.setItems(Transactions);

    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LibrarianMenu.fxml"));
        root = loader.load();
        LibraryMenuController librarymenucontroller = loader.getController();
        librarymenucontroller.displayLibrarianMenu(library);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
