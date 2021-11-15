module com.upiicsa.crudjavafxmaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.desktop;
    requires de.mkammerer.argon2;

    opens com.upiicsa.crudjavafxmaven to javafx.fxml;
    exports com.upiicsa.crudjavafxmaven;
}