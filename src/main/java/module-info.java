module com.example.gachagame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gachagame to javafx.fxml;
    exports com.example.gachagame;
}