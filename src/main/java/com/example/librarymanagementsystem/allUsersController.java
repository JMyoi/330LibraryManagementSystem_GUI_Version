package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class allUsersController {
    Library library = new Library();
    @FXML
    Parent root;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

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
    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> memberIDcolumn;
    @FXML
    private TableColumn<Member, String> memberNameColumn;
    @FXML
    private TableView<Librarian> librarianTable;
    @FXML
    private TableColumn<Librarian, String> librarianNameColumn;
    @FXML
    private TableColumn<Librarian, String> librarianIDcolumn;

    public void receiveLibrary(Library lib){
        library = lib;
        System.out.println("library received in all Users Controller: ");
        library.displayUsers();
        ArrayList<Member> allMembers = library.getMembers();
        //populate the member table
        ObservableList<Member> allMem = FXCollections.observableArrayList(allMembers);
        memberNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        memberIDcolumn.setCellValueFactory(new PropertyValueFactory<Member, String>("stringID"));
        memberTable.setItems(allMem);

        //populate the librarian table
        ArrayList<Librarian> allLibrarians = library.getLibrarians();
        ObservableList<Librarian> allLib = FXCollections.observableArrayList(allLibrarians);
        librarianNameColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("name"));
        librarianIDcolumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("stringID"));
        librarianTable.setItems(allLib);

    }

}
