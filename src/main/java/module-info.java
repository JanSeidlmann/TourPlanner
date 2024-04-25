module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tourplanner to javafx.fxml;
    exports org.example.tourplanner;
    exports org.example.tourplanner.View;
    opens org.example.tourplanner.View to javafx.fxml;
}