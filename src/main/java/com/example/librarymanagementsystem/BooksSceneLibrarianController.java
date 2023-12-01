package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class BooksSceneLibrarianController {
    Library library = new Library();
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
    @FXML
    Parent root;

    @FXML
    private Button backBttn;
    @FXML
    void Back(ActionEvent actionEvent) throws IOException {
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
    private TextField titleField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField copiesfield;
    @FXML
    private TextField authorField;
    @FXML
    private Label copeisError;
    @FXML
    private Label authorError;
    @FXML
    private Label isbnError;
    @FXML
    private Label titleError;
    @FXML
    void addBook(ActionEvent event) {
        authorError.setText("");
        isbnError.setText("");
        titleError.setText("");
        copeisError.setText("");

        String title = titleField.getText();
        String Author = authorField.getText();
        String ISBN = isbnField.getText();
        String copies = copiesfield.getText();
        if(Author.isBlank()){
            authorError.setText("please enter an Author");
        }
        if(ISBN.isBlank()){
            isbnError.setText("please enter an ISBN");
        }
        if(title.isBlank()){
            titleError.setText("please enter a title");
        }
        if(copies.isBlank()){
            copeisError.setText("please enter the copies");
        }
        if(Author.isBlank()||ISBN.isBlank()||title.isBlank()||copies.isBlank()){
            return;
        }
        try{
            int parsedCopies = Integer.parseInt(copies);
            System.out.println("adding this book to the library: "+title+Author+ISBN+"copies: "+parsedCopies);
            library.addBook(ISBN, title, Author, parsedCopies);
            //update the book table
            receiveLibrary(library);


        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
            System.out.println("Please Input a number");
            copeisError.setText("Please Input a number");
        }

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
    private TableColumn<Book, String> titleColumn;
    public void receiveLibrary(Library lib){
        library = lib;
        System.out.println("library received in Librarians Book Scene");
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
