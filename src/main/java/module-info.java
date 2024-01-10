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
    requires java.desktop;

    opens com.example.languagelearningapp to javafx.fxml;
    exports com.example.languagelearningapp;
    exports com.example.languagelearningapp.Builder;
    opens com.example.languagelearningapp.Builder to javafx.fxml;

    exports com.example.languagelearningapp.Controllers to javafx.fxml;
    opens com.example.languagelearningapp.Controllers to javafx.fxml;
}