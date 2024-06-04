module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;

    opens org.example.tourplanner to javafx.fxml;
    exports org.example.tourplanner;
    exports org.example.tourplanner.models;
    opens org.example.tourplanner.models to org.hibernate.orm.core, javafx.fxml, javafx.base;
    exports org.example.tourplanner.viewModels;
    opens org.example.tourplanner.viewModels to javafx.fxml;

}
