module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires javafx.web;
    requires org.json;
    requires java.desktop;
    requires kernel;
    requires layout;
    requires io;

    opens org.example.tourplanner to javafx.fxml;
    exports org.example.tourplanner;
    exports org.example.tourplanner.BL.models;
    opens org.example.tourplanner.BL.models to org.hibernate.orm.core, javafx.fxml, javafx.base;
    exports org.example.tourplanner.PL.controller;
    opens org.example.tourplanner.PL.controller to javafx.fxml;
    exports org.example.tourplanner.BL;
    exports org.example.tourplanner.DAL;
    opens org.example.tourplanner.DAL to javafx.fxml;
    exports org.example.tourplanner.DAL.repositories;
    opens org.example.tourplanner.DAL.repositories to javafx.fxml;
    opens org.example.tourplanner.BL to javafx.base, javafx.fxml, org.hibernate.orm.core;
    exports org.example.tourplanner.PL.viewmodels;
    opens org.example.tourplanner.PL.viewmodels to javafx.fxml;
    exports org.example.tourplanner.util;
    opens org.example.tourplanner.util to javafx.fxml;

}
