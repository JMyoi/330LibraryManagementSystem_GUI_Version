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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class memberBooksSceneController {
    Library library = new Library();
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    Parent root;


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
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, Integer> copiesColumn;

    @FXML
    private TableColumn<Book, String> isbnColumn;

    @FXML
    private TableColumn<Book,String> titleColumn;

    @FXML
    private Label bookTitleLabel;
    public void rowClicked(MouseEvent mouseEvent) {
        errorLabel.setText("");
        sucessLabel.setText("");
        Book clickedBook = booksTable.getSelectionModel().getSelectedItem();
        bookTitleLabel.setText("Selected: "+ clickedBook.getBookName());
        System.out.println("Selected: "+ clickedBook.getBookName());
    }
    @FXML
    private Label errorLabel;
    @FXML
    private Label sucessLabel;
    @FXML
    public void addTransaction(ActionEvent actionEvent) {
        sucessLabel.setText("");
        errorLabel.setText("");
        Book clickedBook = booksTable.getSelectionModel().getSelectedItem();
        if(clickedBook == null){
            System.out.println("nothing selected please select a book");
            errorLabel.setText("Please select a book");
        }
        else{
            System.out.println("adding " + clickedBook.getBookName());
            //check if any more copies
            if(clickedBook.noMoreCopies()){
                System.out.println("no more copies");
                errorLabel.setText("No more copies");
            }
            //check if the user already has the book
            else if(library.alreadyHaveThisBook(clickedBook)){
                System.out.println("you alreay have this book");
                errorLabel.setText("You already have this book");
            }
            //else add a new transaction to the library and decrement the book
            else{
                library.addTransaction(clickedBook);
                //rerender the books
               // displayBooks(library);
                booksTable.refresh();
                sucessLabel.setText("Successfully Added");

            }
        }

    }
    @FXML
    public void displayBooks(Library lib){
        library = lib;
        System.out.println("library received in Member Book Scene");
        //get the books from the library
        ArrayList<Book> books = library.getBooks();
        //populate table with book info
        ObservableList<Book> allBooks = FXCollections.observableArrayList(books);
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("bookName"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("authorName"));
        copiesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numCopies"));
        booksTable.setItems(allBooks);
    }
}
