package com.example.advancedjavaweek2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class BookChartController implements Initializable {
    @FXML
    private RadioButton availableRadioButton;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    void viewTable_onClick(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "new-table-view.fxml", "Book Table");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryAxis.setLabel("Book Name");
        numberAxis.setLabel("Units Sold");
    }
    @FXML
    void addNewData_onClick(ActionEvent event) {
        XYChart.Series<String, Integer> newData = new XYChart.Series<>();
        SecureRandom secureRandom= new SecureRandom();
        newData.setName(String.valueOf(secureRandom.nextInt(1900, 2023)));

        newData.getData().add(new XYChart.Data<>("A Clash of Kings", secureRandom.nextInt(10, 100)));
        newData.getData().add(new XYChart.Data<>("FakeBook2", secureRandom.nextInt(10, 100)));
        newData.getData().add(new XYChart.Data<>("FakeBook3", secureRandom.nextInt(10, 100)));

        barChart.getData().addAll(newData);
    }
    @FXML
    void availableRadioButton_onClick(ActionEvent event) {
        barChart.getData().clear();
        if (availableRadioButton.isSelected()) {
            barChart.getData().addAll(DBUtility.getAvailableChartDataFromDB());
        } else {
            barChart.getData().addAll(DBUtility.getChartDataFromDB());
        }
    }
}

