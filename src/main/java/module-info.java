module com.example.languagelearningapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires static lombok;

    opens com.example.languagelearningapp to javafx.fxml;
    exports com.example.languagelearningapp;
}