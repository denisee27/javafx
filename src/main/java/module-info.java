module com.mycompany.tb2.pbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;


    opens com.mycompany.tb2.pbo to javafx.fxml;
    exports com.mycompany.tb2.pbo;
}
