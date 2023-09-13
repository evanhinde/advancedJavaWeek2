module com.example.advancedjavaweek2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.advancedjavaweek2 to javafx.fxml;
    exports com.example.advancedjavaweek2;
}