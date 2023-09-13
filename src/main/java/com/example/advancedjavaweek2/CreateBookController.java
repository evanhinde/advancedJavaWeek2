package com.example.advancedjavaweek2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateBookController implements Initializable {

    @FXML
    private TextField authorTextField;

    @FXML
    private CheckBox availabilityCheckBox;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private Label finalLabel;

    @FXML
    private ComboBox<String> genreComboBox;

    @FXML
    private TextField priceTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am in initialize method.");
        // make label invisible
        finalLabel.setVisible(false);

        // populate genres into combobox
        genreComboBox.getItems().addAll(Book.findGenres());
    }

    @FXML
    void saveBook(ActionEvent event) {
        System.out.println("Button Clicked");

        try {
            // store user data in variables
            String bookName = bookNameTextField.getText();
            String author = authorTextField.getText();
            String genre = genreComboBox.getSelectionModel().getSelectedItem();
            double price = (!priceTextField.getText().isBlank()) ? Double.parseDouble(priceTextField.getText()) : -1;
            boolean isAvailable = availabilityCheckBox.isSelected();

            // create book object
            Book book = new Book(1, bookName, author, genre, price, isAvailable);

            // show the values to the user
            finalLabel.setVisible(true);
            finalLabel.setText(book.toString());
        } catch (Exception e) {
            // show proper exception message to user
            finalLabel.setVisible(true);
            finalLabel.setText(e.getMessage());
        }
    }
}

