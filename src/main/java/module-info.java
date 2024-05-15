module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens org.example.tourplanner to javafx.fxml;
    exports org.example.tourplanner;
    exports org.example.tourplanner.Models;
    opens org.example.tourplanner.Models to javafx.fxml;
    exports org.example.tourplanner.viewModels;
    opens org.example.tourplanner.viewModels to javafx.fxml;
}
