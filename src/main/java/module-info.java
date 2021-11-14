module com.upiicsa.crudjavafxmaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.upiicsa.crudjavafxmaven to javafx.fxml;
    exports com.upiicsa.crudjavafxmaven;
}
