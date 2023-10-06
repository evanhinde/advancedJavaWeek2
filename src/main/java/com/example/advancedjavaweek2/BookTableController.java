package com.example.advancedjavaweek2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookTableController implements Initializable {

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, Boolean> availabilityColumn;

    @FXML
    private TableColumn<Book, Integer> bookIdColumn;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> genreColumn;

    @FXML
    private TableColumn<Book, Double> priceColumn;


    @FXML
    private TableColumn<Book, Integer> unitsSoldColumn;

    @FXML
    void addNewBook_onClick(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "create-book-view.fxml", "Create-Book");
    }

    @FXML
    void viewChart_onClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Book> books = DBUtility.getBooksFromDB();
        System.out.println(books);
    }
}

