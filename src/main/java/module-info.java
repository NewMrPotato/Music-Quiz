module com.rather.capital_school_beta {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires unirest.java;
    requires java.desktop;
    requires org.json;
    requires service;
    requires api;

    opens com.rather.quiz to javafx.fxml;
    exports com.rather.quiz;
    exports com.rather.quiz.Controllers;
    opens com.rather.quiz.Controllers to javafx.fxml;
}